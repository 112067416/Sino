package com.coco.query.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

public class ExcelUtils {

	/**
	 * <p>
	 * insert row into the target sheet, the style of cell is the same as
	 * startRow
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param startRow
	 * @param rows
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet,
			int startRow, int rows) {

		sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), rows, true, false);

		HSSFRow sourceRow = null;
		HSSFRow targetRow = null;
		for (int i = 0; i < rows; i++) {
			sourceRow = sheet.getRow(startRow);
			targetRow = sheet.createRow(++startRow);
			net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow);
		}

	}

	/**
	 * <p>
	 * 设置单元格值
	 * </p>
	 * 
	 * @param cell
	 * @param o
	 */
	public static void setCellValue(HSSFCell cell, Object o) {
		if (cell == null) {
			return;
		}
		if (o == null) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			cell.setCellValue(new HSSFRichTextString(null));
			return;
		}
		cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		if (o instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String v = sdf.format((Date) o);
			v = v.replaceAll("((\\s|:)00)+$", "");
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 向此单元格中放入值
			cell.setCellValue(new HSSFRichTextString(v));
		}
		else if (o instanceof String) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if (((String) o).length() == 0) {
				cell.setCellValue(new HSSFRichTextString((null)));
			}
			else {
				String v = (String) o;
				int rowIndex = cell.getRowIndex();
				int cellIndex = cell.getColumnIndex();
				Pattern p = Pattern.compile("^_+#");
				Matcher m = p.matcher(v);
				HSSFRow row = cell.getSheet().getRow(rowIndex);
				if (m.find()) {
					String g = m.group();
					CellRangeAddress cr = new CellRangeAddress(rowIndex,
							rowIndex, cellIndex - g.length() + 1, cellIndex);
					StringBuffer buf = new StringBuffer();
					for (int i = cellIndex - g.length() + 1; i < cellIndex; i++) {
						HSSFCell c = row.getCell(i);
						if (c != null) {
							buf.append(getCellValue(c)).append(" ");
						}
					}
					cell.getSheet().addMergedRegion(cr);
					cell = row.createCell(cellIndex - g.length() + 1);
					v = buf + m.replaceAll("");
				}
				p = Pattern.compile("#_+$");
				m = p.matcher(v);
				if (m.find()) {
					String g = m.group();
					CellRangeAddress cr = new CellRangeAddress(rowIndex,
							rowIndex, cellIndex, cellIndex + g.length() - 1);
					StringBuffer buf = new StringBuffer();
					for (int i = cellIndex + 1; i <= cellIndex + g.length() - 1; i++) {
						HSSFCell c = row.getCell(i);
						if (c != null) {
							buf.append(getCellValue(c)).append(" ");
						}
					}
					cell.getSheet().addMergedRegion(cr);
					v = m.replaceAll("") + buf;
				}
				cell.setCellValue(new HSSFRichTextString(v));
			}
		}
		else {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			String numStr = o.toString();
			if (numStr.indexOf('.') != -1) {
				numStr = numStr.replaceAll("(\\.|)0+$", "");
				try {
					BigDecimal dec = new BigDecimal(numStr);
					cell.setCellValue(dec.doubleValue());
				}
				catch (Exception e) {
					cell.setCellValue(new HSSFRichTextString(numStr));
				}
			}
			else {
				if (numStr.length() == 0) {
					cell.setCellValue(new HSSFRichTextString(null));
				}
				else {
					try {
						BigDecimal dec = new BigDecimal(numStr);
						cell.setCellValue(dec.doubleValue());
					}
					catch (Exception e) {
						cell.setCellValue(new HSSFRichTextString(numStr));
					}
				}
			}
		}
	}

	/**
	 * <p>
	 * 获取单元格值
	 * </p>
	 * 
	 * @param cell
	 * @return String
	 */
	public static String getCellValue(HSSFCell cell) {
		if (cell == null) {
			return null;
		}
		HSSFRichTextString rscv;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			rscv = cell.getRichStringCellValue();
			return rscv != null ? rscv.getString() : "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "Y" : "N";
		case HSSFCell.CELL_TYPE_ERROR:
			return "";
		case HSSFCell.CELL_TYPE_FORMULA:
			cell.setCellFormula(cell.getCellFormula());
		case HSSFCell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue() + "";
		case HSSFCell.CELL_TYPE_STRING:
		default:
			rscv = cell.getRichStringCellValue();
			return rscv != null ? rscv.getString() : "";
		}
	}

	/**
	 * <p>
	 * 获取Excel模板定义的变量值
	 * </p>
	 * 
	 * @param values
	 * @param var
	 * @return Object
	 */
	public static Object fetchValue(Map<String, Value> values, Variable var) {
		if (values == null || var == null || var.getIndex() < 0) {
			return null;
		}
		Value value = values.get(var.getName());
		if (value == null) {
			return null;
		}
		List<Map<String, Object>> vs = null;
		if (var.getKeys() != null && var.getKeys().size() > 0) {
			vs = value.getMapGroup(var.getKeys());
		}
		else {
			vs = value.getMapValues();
		}
		if (vs == null || vs.size() <= var.getIndex()) {
			return null;
		}
		Map<String, Object> vm = vs.get(var.getIndex());
		return vm.get(var.getField());
	}

	/**
	 * <p>
	 * 获取Excel模板定义的变量值
	 * </p>
	 * 
	 * @param variables
	 * @param var
	 * @return Object
	 */
	public static Object fetchVariable(Map<String, Object> variables,
			Variable var) {
		if (variables == null || var == null || var.getIndex() < 0) {
			return null;
		}
		return variables.get(var.getField());
	}

	/**
	 * <p>
	 * 解析变量，变量格式如下:
	 * </p>
	 * <ul>
	 * <li>${A.a}</li>
	 * <li>${A[0,1,2].a}</li>
	 * <li>${A[0,1,2].1.a}</li>
	 * </ul>
	 * 
	 * @param rule
	 * @return Variable
	 */
	public static Variable parseVariable(String rule) {
		if (rule == null || (rule = rule.trim()).length() == 0) {
			return null;
		}
		if (!Pattern.matches("\\$\\{.+\\}", rule)) {
			return null;
		}
		rule = rule.replaceAll("\\$\\{|\\}", "");
		String[] vs = rule.split("\\.");
		String name = null, field = null;
		int index = 0;
		name = vs[0].trim(); // 名称为第一段，指向Module中的name
		String groupKey = null;
		List<String> keys = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\[.+\\]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			groupKey = matcher.group();
			groupKey = groupKey.substring(1, groupKey.length() - 1);
			String[] arr = groupKey.split(",");

			for (String s : arr) {
				s = s.replaceAll("#44;", "");
				keys.add(s);
			}
			name = matcher.replaceAll("");
		}
		int i = 1;
		if (vs.length > i) {
			try {
				index = Integer.parseInt(vs[i].trim());
				i++;
			}
			catch (Exception e) {
			}
		}
		if (vs.length > i) {
			field = vs[i].trim();
		}
		if (name.length() == 0) {
			return null;
		}
		Variable var = new Variable();
		var.setName(name);
		var.setKeys(keys);
		var.setIndex(index);
		if (field == null || field.length() == 0) {
			var.setList(true);
		}
		else {
			var.setField(field);
		}
		return var;
	}
}
