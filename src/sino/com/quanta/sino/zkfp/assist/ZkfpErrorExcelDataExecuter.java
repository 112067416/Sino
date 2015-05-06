package com.quanta.sino.zkfp.assist;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.zkfp.vo.CError;
import com.quanta.sino.zkfp.vo.ZkfpErrorVO;

/**
 * <p>
 * 分配错误Excel处理器
 * </p>
 * <p>
 * create: 2011-3-4 上午08:29:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZkfpErrorExcelDataExecuter implements
		ExcelbookDataExecuter<ZkfpErrorVO> {

	@Override
	public void execute(HSSFWorkbook workbook, ZkfpErrorVO data) {
		Map<ZsdxTp, List<CError>> cwjls = null;
		if ((cwjls = data.getCwjls()) == null || cwjls.size() == 0) {
			return;
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCellStyle headCellStyle, valueCellStyle, rowStyle;
		headCellStyle = workbook.createCellStyle();
		headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setWrapText(false);
		headCellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		headCellStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);

		rowStyle = workbook.createCellStyle();
		rowStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		rowStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);

		valueCellStyle = workbook.createCellStyle();
		valueCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont headCellFont, valueCellFont;
		headCellFont = workbook.createFont();
		headCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headCellFont.setFontHeight(((short) 210));
		headCellStyle.setFont(headCellFont);

		valueCellFont = workbook.createFont();
		valueCellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		valueCellStyle.setFont(valueCellFont);
		valueCellStyle.setWrapText(false);
		int rowIndex = 1;
		int cellIndex;
		HSSFRow row = null;
		HSSFCell cell = null;
		ZsdxTp zsdxTp;
		List<CError> errors;
		Iterator<ZsdxTp> iterator = cwjls.keySet().iterator();
		while (iterator.hasNext()) {
			zsdxTp = iterator.next();
			errors = cwjls.get(zsdxTp);

			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "订货No.");
			cell = row.createCell(1);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getDhno());
			cell = row.createCell(2);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "采取指示量");
			cell = row.createCell(3);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getCqzs());
			cell = row.createCell(4);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "T变");
			cell = row.createCell(5);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getJbkb());
			cell = row.createCell(6);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "再选");
			cell = row.createCell(7);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getZxbb());
			cell = row.createCell(8);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "强制");
			cell = row.createCell(9);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getQzbj());
			cell = row.createCell(10);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "OVER");
			cell = row.createCell(11);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getStat());

			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "现品No.");
			String jbno = zsdxTp.getZzno() + "-" + zsdxTp.getJbno();
			jbno = zsdxTp.getPlqf() != null ? zsdxTp.getPlqf() + jbno : jbno;
			cell = row.createCell(1);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, jbno);
			cell = row.createCell(2);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "制品重量");
			cell = row.createCell(3);
			cell.setCellStyle(valueCellStyle);
			ExcelUtils.setCellValue(cell, zsdxTp.getZpzl());

			row = sheet.createRow(rowIndex++);
			row.setRowStyle(rowStyle);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "分配诸元 CHECK ERROR");

			cellIndex = 1;
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "项目名称");
			for (CError error : errors) {
				cell = row.createCell(cellIndex++);
				cell.setCellStyle(headCellStyle);
				ExcelUtils.setCellValue(cell, error.getName());
			}

			cellIndex = 1;
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "现品情报");
			for (CError error : errors) {
				cell = row.createCell(cellIndex++);
				cell.setCellStyle(valueCellStyle);
				ExcelUtils.setCellValue(cell, error.getField1());
			}

			cellIndex = 1;
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "订货情报");
			for (CError error : errors) {
				cell = row.createCell(cellIndex++);
				cell.setCellStyle(valueCellStyle);
				ExcelUtils.setCellValue(cell, error.getField2());
			}
			row = sheet.createRow(rowIndex++);
		}
	}

}
