package com.coco.report.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.coco.core.env.Helper;
import com.coco.report.bean.Entry;
import com.coco.report.bean.Value;
import com.coco.report.bean.Variable;

/**
 * @author 许德建[xudejian@126.com]
 */
public class ExcelReportExecuter implements ReportExecuter<HSSFWorkbook> {

	private DefaultReportFactory factory;

	/*
	 * (non-Javadoc)
	 * @see com.coco.report.api.ReportExecuter#getEntry(java.lang.String)
	 */
	@Override
	public Entry getEntry(String id) {
		return factory.get(id);
	}

	/**
	 * <p>
	 * 创建一个带有消息内容的Excel
	 * </p>
	 * 
	 * @param msg
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook createWorkbook(String msg) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 5000);
		HSSFCell cell = row.createCell(0);
		sheet.setColumnWidth(0, 30000);
		HSSFCellStyle cs = workbook.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setWrapText(true);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_RED);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeight((short) 500);
		cs.setFont(font);
		cell.setCellStyle(cs);
		cell.setCellValue(new HSSFRichTextString(msg));
		return workbook;
	}

	/**
	 * <p>
	 * 输出Excel报表
	 * </p>
	 * 
	 * @param id
	 * @param conditions
	 * @return HSSFWorkbook
	 */
	@Override
	public HSSFWorkbook execute(String id, Map<String, String> conditions) {
		if (id == null) {
			throw new RuntimeException("没有指定报表ID");
		}
		Entry entry = factory.get(id);
		if (entry == null) {
			throw new RuntimeException("报表没有配置");
		}
		// 执行SQL模块
		Map<String, Value> values = SqlExecuter.execute(entry, conditions);
		// 执行变量模块
		Map<String, Object> variables = SqlExecuter.executeForVarible(entry, conditions);

		int[] sheetIndexs = entry.getSheetIndex();
		if (sheetIndexs == null || sheetIndexs.length == 0) {
			sheetIndexs = new int[] { 0 };
		}

		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String rule;
		Variable var;
		InputStream is = null;
		String realPath = Helper.REAL_PATH;
		try {
			is = new FileInputStream(realPath + entry.getExcel());
			workbook = new HSSFWorkbook(is);
			// sheet = workbook.getSheetAt(0);
		}
		catch (Exception e1) {
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e2) {
				}
			}
		}
		List<HSSFCell> formulaCells = new ArrayList<HSSFCell>();
		for (int si : sheetIndexs) {
			sheet = workbook.getSheetAt(si);
			if (sheet == null) {
				continue;
				// throw new RuntimeException("报表没有配置输出模板");
			}
			for (int rowIndex = sheet.getFirstRowNum(); rowIndex < sheet.getLastRowNum(); rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
					cell = row.getCell(cellIndex);
					if (cell == null) {
						continue;
					}
					if (HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
						formulaCells.add(cell);
						continue;
					}
					rule = getCellValue(cell);
					if (rule == null || rule.length() == 0) {
						continue;
					}
					var = parseVariable(rule);
					if (var == null) {
						continue;
					}
					if (var.isList()) {
						Value value = values.get(var.getName());
						if (value == null) {
							setCellValue(cell, null);
							break;
						}
						List<List<Object>> rs = null;
						if (var.getKeys() != null && var.getKeys().size() > 0) {
							rs = value.getListGroup(var.getKeys());
						}
						else {
							rs = value.getListValues();
						}
						if (rs == null || rs.size() == 0) {
							setCellValue(cell, null);
							break;
						}
						int dataCount = rs.size();
						int cellCount;
						int[] newlines = value.getModule().getNewlines();
						boolean hasNewline = newlines != null
								&& newlines.length > 0;
						// 检查一行是否分行显示
						int newRows = dataCount;
						if (hasNewline) {
							newRows *= newlines.length;
						}
						// 复制行的方式
						if (dataCount > 1 && entry.getCopyProperty() != 1) {
							ExcelUtil.insertRow(workbook, sheet, rowIndex, newRows - 1);
						}
						int rowPos;
						for (int n = 0; n < dataCount; n++) {
							rowPos = rowIndex
									+ (hasNewline ? n * newlines.length : n);
							row = sheet.getRow(rowPos);
							if (row == null) {
								row = sheet.createRow(rowPos);
							}
							List<Object> list = rs.get(n);
							cellCount = list.size();
							if (!hasNewline) {
								for (int l = 0; l < cellCount; l++) {
									cell = row.getCell(cellIndex + l);
									if (cell == null) {
										cell = row.createCell(cellIndex + l);
									}
									setCellValue(cell, list.get(l));
								}
							}
							else {
								int pos = 0;
								for (int newlineIndex = 0; newlineIndex < newlines.length; newlineIndex++) {
									if (newlineIndex > 0) {
										rowPos++;
										row = sheet.getRow(rowPos);
										if (row == null) {
											row = sheet.createRow(rowPos);
										}
									}
									for (int newlinePos = 0; newlinePos < newlines[newlineIndex]; newlinePos++) {
										cell = row.getCell(cellIndex
												+ newlinePos);
										if (cell == null) {
											cell = row.createCell(cellIndex
													+ newlinePos);
										}
										if (pos < cellCount) {
											setCellValue(cell, list.get(pos));
										}
										else {
											setCellValue(cell, null);
										}
										pos++;
									}
								}
							}
						}
						rowIndex += newRows - 1;
						break;
					}
					Object o = "V".equalsIgnoreCase(var.getName()) ? fetchVariable(variables, var)
							: fetchValue(values, var);
					if (o != null && o instanceof String) {
						String str = (String) o;
						// Date date = new Date();
						// o = str.replaceAll("#.*_end#", DateUtils.format(date,
						// "yyyy-MM-dd"));
						o = str.replaceAll("#.*_end#", "");
					}
					setCellValue(cell, o);
				}
			}
		}
		// 刷新公式
		for (HSSFCell formulaCell : formulaCells) {
			formulaCell.setCellFormula(formulaCell.getCellFormula());
		}
		return workbook;
	}

	/**
	 * <p>
	 * 设置单元格值
	 * </p>
	 * 
	 * @param cell
	 * @param o
	 */
	private static void setCellValue(HSSFCell cell, Object o) {
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
	private static String getCellValue(HSSFCell cell) {
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
	private static Object fetchValue(Map<String, Value> values, Variable var) {
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
	 * @param values
	 * @param var
	 * @return Object
	 */
	private static Object fetchVariable(Map<String, Object> variables,
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
	 * <p>
	 * <ul>
	 * <li>${A.a}</li>
	 * <li>${A[0,1,2].a}</li>
	 * <li>${A[0,1,2].1.a}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param rule
	 * @return Variable
	 */
	private static Variable parseVariable(String rule) {
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

	public DefaultReportFactory getFactory() {
		return factory;
	}

	public void setFactory(DefaultReportFactory factory) {
		this.factory = factory;
	}

}
