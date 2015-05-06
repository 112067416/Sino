package com.coco.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coco.core.env.Helper;
import com.coco.core.excel.api.ExcelDataExecuter;
import com.coco.core.excel.api.ExcelbookDataExecuter;

/**
 * <p>
 * Excel工具
 * </p>
 * <p>
 * create: 2011-1-28 上午11:19:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ExcelUtils {

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * <p>
	 * insert row into the target sheet, the style of cell is the same as
	 * startRow
	 * </p>
	 * 
	 * @param sheet
	 * @param startRow
	 * @param rows
	 */
	public static void insertRow(HSSFSheet sheet, int startRow, int rows) {
		sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), rows, true, false);
		for (int i = 0; i < rows; i++) {
			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;
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
			String v = SDF.format((Date) o).replaceAll("((\\s|:)00)+$", "");
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(v));
		}
		else if (o instanceof String) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if (((String) o).isEmpty()) {
				cell.setCellValue(new HSSFRichTextString((null)));
			}
			else {
				cell.setCellValue(new HSSFRichTextString((String) o));
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
			return cell.getBooleanCellValue() ? "1" : "0";
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
	 * 填充表格数据
	 * </p>
	 * 
	 * @param <T>
	 * @param path
	 * @param data
	 * @param exec
	 * @param os
	 */
	public static <T> void fillData(String path, T data,
			ExcelbookDataExecuter<T> exec, OutputStream os) {
		if (path == null || (path = path.trim()).isEmpty()) {
			return;
		}

		InputStream is = null;
		try {
			if (path.indexOf("classpath:") == 0) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.substring(10));
			}
			else if (path.indexOf("file:") == 0) {
				is = new FileInputStream(path.substring(5));
			}
			else {
				is = new FileInputStream(Helper.REAL_PATH + path);
			}
			if (is == null) {
				return;
			}
			HSSFWorkbook book = new HSSFWorkbook(is);
			exec.execute(book, data);
			book.write(os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}

	}

	/**
	 * <p>
	 * 填充表格数据
	 * </p>
	 * 
	 * @param <T>
	 * @param path
	 * @param data
	 * @param exec
	 * @param os
	 */
	public static <T> void fillData(String path, T data,
			ExcelDataExecuter<T> exec, OutputStream os) {
		if (path == null || (path = path.trim()).isEmpty()) {
			return;
		}

		InputStream is = null;
		try {
			if (path.indexOf("classpath:") == 0) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.substring(10));
			}
			else if (path.indexOf("file:") == 0) {
				is = new FileInputStream(path.substring(5));
			}
			else {
				is = new FileInputStream(Helper.REAL_PATH + path);
			}
			if (is == null) {
				return;
			}
			HSSFWorkbook book = new HSSFWorkbook(is);
			HSSFSheet sheet = book.getSheetAt(0);
			exec.execute(sheet, data);
			book.write(os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}

	}

	/**
	 * <p>
	 * 填充表格数据
	 * </p>
	 * 
	 * @param <T>
	 * @param path
	 * @param sheetIndex
	 * @param data
	 * @param exec
	 * @param os
	 */
	public static <T> void fillData(String path, int sheetIndex, T data,
			ExcelDataExecuter<T> exec, OutputStream os) {
		if (path == null || (path = path.trim()).isEmpty()) {
			return;
		}

		InputStream is = null;
		try {
			if (path.indexOf("classpath:") == 0) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.substring(10));
			}
			else if (path.indexOf("file:") == 0) {
				is = new FileInputStream(path.substring(5));
			}
			else {
				is = new FileInputStream(Helper.REAL_PATH + path);
			}
			if (is == null) {
				return;
			}
			HSSFWorkbook book = new HSSFWorkbook(is);
			HSSFSheet sheet = book.getSheetAt(sheetIndex);
			exec.execute(sheet, data);
			book.write(os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}

	}
}
