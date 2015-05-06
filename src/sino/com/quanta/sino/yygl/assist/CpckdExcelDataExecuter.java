package com.quanta.sino.yygl.assist;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.yygl.vo.CkdVO;
import com.quanta.sino.yygl.vo.CpckdVO;

/**
 * <p>
 * 成品出库单统计Excel处理器
 * </p>
 * <p>
 * create: 2011-6-2 下午08:13:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CpckdExcelDataExecuter implements ExcelbookDataExecuter<CkdVO> {

	@Override
	public void execute(HSSFWorkbook workbook, CkdVO data) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int columnIndex = 0; columnIndex < 6; columnIndex++) {
			sheet.setColumnWidth(columnIndex, 3800);
		}
		HSSFCellStyle headCellStyle;
		headCellStyle = workbook.createCellStyle();
		headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setWrapText(true);
		headCellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		headCellStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle cellStyle;
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(true);
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtStyle;
		txtStyle = workbook.createCellStyle();
		txtStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtStyle.setWrapText(true);
		txtStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFFont headFont = workbook.createFont();
		headFont.setFontHeightInPoints((short) 20);
		headFont.setFontName("Courier New");

		HSSFFont cellFont = workbook.createFont();
		cellFont.setFontHeightInPoints((short) 10);
		cellFont.setFontName("Courier New");

		sheet.autoSizeColumn((short) 1);

		CellRangeAddress r = null;
		HSSFRow row;
		HSSFCell cell;
		int rowIndex = 0;
		if (data.getVos() != null) {
			for (CpckdVO vo : data.getVos()) {
				r = new CellRangeAddress(rowIndex, rowIndex, 0, 5);
				sheet.addMergedRegion(r);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 500);

				cell = row.createCell(0);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "成品出库单表");
				headCellStyle.setFont(headFont);

				cell = row.createCell(1);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(2);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(3);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(4);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(5);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, DateUtils.format(vo.getChqi(), "yyyy-MM-dd"));

				cell = row.createCell(2);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(3);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "No."
						+ DateUtils.format(vo.getChqi(), "yyMMdd"));

				cell = row.createCell(5);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				r = new CellRangeAddress(rowIndex, rowIndex, 1, 5);
				sheet.addMergedRegion(r);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 1100);
				// row.setHeightInPoints((1 *
				// sheet.getDefaultRowHeightInPoints()));

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "送货单号码");

				cell = row.createCell(1);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getShnos());

				cell = row.createCell(2);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(3);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(4);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(5);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, "");

				r = new CellRangeAddress(rowIndex, rowIndex, 3, 4);
				sheet.addMergedRegion(r);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "成品名称");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "规格");

				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "数量");

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "出口");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(5);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "数量");

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "h<0.3");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getBsl1());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "h<0.3");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCbsl1());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.3<=h<0.5");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getBsl2());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.3<=h<0.5");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCbsl2());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.5<=h<1");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getBsl3());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁切板");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.5<=h<1");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCbsl3());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "h<0.3");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getJsl1());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "h<0.3");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCjsl1());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.3<=h<0.5");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getJsl2());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.3<=h<0.5");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCjsl2());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.5<=h<1");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getJsl3());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "马口铁卷材");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "0.5<=h<1");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getCjsl3());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "");

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "合计：");

				cell = row.createCell(2);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getHj());

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "内销");

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, "合计：");

				cell = row.createCell(5);
				cell.setCellStyle(txtStyle);
				cellStyle.setFont(cellFont);
				ExcelUtils.setCellValue(cell, vo.getNhj());

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 320);
			}
		}
	}
}