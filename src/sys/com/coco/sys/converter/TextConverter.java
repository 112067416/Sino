package com.coco.sys.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

public class TextConverter implements IConverter {

	@Override
	public <C> ConvertVO<C> convert(Class<C> clazz, ConvertEntity entity,
			InputStream is, IConverterCallback<C> callback) {
		if (clazz == null || entity == null || is == null) {
			return null;
		}
		InputStreamReader reader = null;
		BufferedReader br = null;
		try {
			reader = new InputStreamReader(is, "GBK");
			br = new BufferedReader(reader);
		}
		catch (Exception e) {
			throw new CocoException(-1, "文本文件流出错");
		}
		try {
			return this.convert(clazz, entity, br, callback);
		}
		catch (IOException e) {
			throw new CocoException(-1, "读取文本文件流出错");
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
				}
			}
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	private <C> ConvertVO<C> convert(Class<C> clazz, ConvertEntity entity,
			BufferedReader br, IConverterCallback<C> callback)
			throws IOException {

		List<ConvertField> convertFields = entity.getFields();
		if (convertFields == null || convertFields.isEmpty()) {
			throw new CocoException(-1, "没有配置转换对象的字段");
		}
		String split = entity.getSplit();
		if (split == null || split.isEmpty()) {
			split = ",";
		}
		List<String> lines = new ArrayList<String>();
		String line;
		String[] arr;
		String value;
		// 设置字段映射关系
		List<Object> fields = new ArrayList<Object>();
		Field field;
		Method method;
		Object fm;
		if (entity.getTitleRow() >= 0) {
			line = null;
			for (int i = -1; i < entity.getTitleRow(); i++) {
				line = br.readLine();
				if (line == null) {
					throw new CocoException(-1, "无效的文件，不能获取题头行");
				}
				lines.add(line);
			}
			arr = line.split(split);
			for (int cellnum = 0; cellnum < arr.length; cellnum++) {
				value = arr[cellnum];
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
				field = ReflectUtils.field(clazz, convertField.getField());
				method = null;
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
		int cellCount = fields.size();
		ConvertRowVO<C> rowVo = null;
		List<C> items = new ArrayList<C>();
		List<ConvertErrorVO> errors;
		C item = null;
		int rowIndex = entity.getTitleRow();
		while ((line = br.readLine()) != null) {
			lines.add(line);
			rowIndex++;
			if (first > rowIndex) {
				continue;
			}
			if (length > 0 && rowIndex - first > length) {
				break;
			}
			try {
				item = clazz.newInstance();
			}
			catch (Exception e) {
				throw new CocoException(-1, "指定的解析类不能实例");
			}
			errors = new ArrayList<ConvertErrorVO>();
			arr = line.split(split);
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
				value = cellnum < arr.length ? arr[cellnum] : null;
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
			rowVo = new ConvertRowVO<C>(item, rowIndex,
					!errors.isEmpty() ? errors : null);
			if (callback != null) {
				callback.doRow(rowVo);
			}
		}

		ConvertVO<C> vo = new ConvertVO<C>();
		vo.setVos(items);
		vo.setLines(lines);
		return vo;
	}
}
