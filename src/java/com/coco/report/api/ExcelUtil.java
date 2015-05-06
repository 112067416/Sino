package com.coco.report.api;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
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

		for (int i = 0; i < rows; i++) {

			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;

			sourceRow = sheet.getRow(startRow);
			targetRow = sheet.createRow(++startRow);
			net.sf.jxls.util.Util.copyRow(sheet, sourceRow, targetRow);
		}

	}

}
