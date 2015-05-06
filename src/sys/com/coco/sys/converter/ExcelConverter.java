package com.coco.sys.converter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coco.core.exception.CocoException;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.StringUtils;
import com.coco.sys.converter.api.IConverter;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.orm.ConvertField;
import com.coco.sys.vo.ConvertErrorVO;
import com.coco.sys.vo.ConvertRowVO;
import com.coco.sys.vo.ConvertVO;

public class ExcelConverter implements IConverter {

	@Override
	public <C> ConvertVO<C> convert(Class<C> clazz, ConvertEntity entity,
			InputStream is, IConverterCallback<C> callback) {
		if (clazz == null || entity == null || is == null) {
			return null;
		}
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		try {
			wb = new HSSFWorkbook(is);
			sheet = wb.getSheetAt(0);
		}
		catch (IOException e) {
			throw new CocoException(-1, "Excel文件流出错");
		}
		if (sheet == null) {
			throw new CocoException(-1, "Excel文件里没有表格");
		}
		List<ConvertField> convertFields = entity.getFields();
		if (convertFields == null || convertFields.isEmpty()) {
			throw new CocoException(-1, "没有配置转换对象的字段");
		}
		HSSFRow row;
		HSSFCell cell;
		String value;
		// 设置字段映射关系
		List<Object> fields = new ArrayList<Object>();
		Field field;
		Method method;
		Object fm;
		if (entity.getTitleRow() >= 0) {
			HSSFRow headRow = sheet.getRow(entity.getTitleRow());
			for (int cellnum = 0; cellnum < headRow.getPhysicalNumberOfCells(); cellnum++) {
				cell = headRow.getCell(cellnum);
				if (cell == null) {
					fields.add(null);
					continue;
				}

				value = null;
				if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()
						|| HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
					value = cell.getRichStringCellValue().getString();
				}
				else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					value = new BigDecimal(cell.getNumericCellValue()).toString();
				}
				else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
					value = cell.getBooleanCellValue() ? "true" : "false";
				}
				if (value == null || value.isEmpty()) {
					fields.add(null);
					continue;
				}
				field = null;
				method = null;
				for (ConvertField convertField : convertFields) {
					if (value.equals(convertField.getTitle())) {
						field = ReflectUtils.field(clazz, convertField.getField());
						if (field == null) {
							method = ReflectUtils.method(clazz, ReflectUtils.formatSetMethodName(convertField.getField()), 1);
						}
						break;
					}
				}
				if (field != null) {
					fields.add(field);
				}
				else {
					fields.add(method);
				}
			}
		}
		// 若没有题头，则按配置字段索引排序
		else {
			int cellIndex = -1;
			for (ConvertField convertField : convertFields) {
				if (convertField.getIndex() == null
						|| convertField.getIndex() < 0) {
					fields.add(null);
					continue;
				}
				if (cellIndex == convertField.getIndex()) {
					continue;
				}
				cellIndex = convertField.getIndex();
				for (int cellpos = 0; cellpos < cellIndex; cellpos++) {
					fields.add(null);
				}
				field = null;
				method = null;
				field = ReflectUtils.field(clazz, convertField.getField());
				if (field == null) {
					method = ReflectUtils.method(clazz, ReflectUtils.formatSetMethodName(convertField.getField()), 1);
					fields.add(method);
				}
				else {
					fields.add(field);
				}
			}
		}

		int first = entity.getFirst();
		if (first < 0) {
			first = 0;
		}
		if (entity.getTitleRow() >= 0) {
			first += entity.getTitleRow() + 1;
		}
		int length = entity.getLength();
		if (length <= 0) {
			length = sheet.getPhysicalNumberOfRows() - first;
		}
		int rowCount = first + length;
		int cellCount = fields.size();
		ConvertRowVO<C> rowVo = null;
		List<C> items = new ArrayList<C>();
		List<ConvertErrorVO> errors;
		C item = null;
		for (int rowIndex = first; rowIndex < rowCount; rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			try {
				item = clazz.newInstance();
			}
			catch (Exception e) {
				throw new CocoException(-1, "指定的解析类不能实例");
			}
			items.add(item);
			errors = new ArrayList<ConvertErrorVO>();
			for (int cellnum = 0; cellnum < cellCount; cellnum++) {
				fm = fields.get(cellnum);
				field = null;
				method = null;
				if (fm == null) {
					continue;
				}
				if (fm instanceof Field) {
					field = (Field) fm;
				}
				else {
					method = (Method) fm;
				}
				cell = row.getCell(cellnum);
				if (cell == null) {
					continue;
				}
				value = null;
				if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()
						|| HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
					value = cell.getRichStringCellValue().getString();
					value = value != null && !value.isEmpty() ? value.trim()
							: null;
				}
				else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					value = new BigDecimal(cell.getNumericCellValue()).toString();
				}
				else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
					value = cell.getBooleanCellValue() ? "true" : "false";
				}
				if (value == null) {
					continue;
				}
				try {
					if (field != null) {
						ReflectUtils.putFieldValue(item, field, StringUtils.valueOf(value, field.getType()));
					}
					else {
						method.invoke(item, StringUtils.valueOf(value, method.getParameterTypes()[0]));
					}
				}
				catch (Exception e) {
					if (field != null) {
						errors.add(new ConvertErrorVO(cellnum, value,
								field.getName(), e));
					}
					else {
						errors.add(new ConvertErrorVO(cellnum, value,
								method.getName(), e));
					}
				}
			}
			rowVo = new ConvertRowVO<C>(item, rowIndex, row,
					!errors.isEmpty() ? errors : null);
			if (callback != null) {
				callback.doRow(rowVo);
			}
		}
		ConvertVO<C> vo = new ConvertVO<C>();
		vo.setVos(items);
		vo.setWb(wb);
		return vo;
	}
}
