package com.quanta.sino.etl.assist;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.etl.vo.EtlBanTjVO;
import com.quanta.sino.etl.vo.EtlSbMainDataVO;
import com.quanta.sino.etl.vo.EtlSbStopDataVO;
import com.quanta.sino.orm.ETLVariTp;

/**
 * <p>
 * etl速报Excel处理器
 * </p>
 * <p>
 * create: 2011-1-28 下午04:36:45
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
public class SbdjExcelDataExecuter implements
		ExcelbookDataExecuter<EtlSbMainDataVO> {

	@Override
	public void execute(HSSFWorkbook workbook, EtlSbMainDataVO data) {
		HSSFRow row;
		int rowIndex = 2;
		HSSFSheet sheet = null;
		HSSFCell cell;
		// 第1个Sheet
		sheet = workbook.getSheetAt(0);
		HSSFCellStyle headCellStyle;
		headCellStyle = workbook.createCellStyle();
		headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setWrapText(false);
		headCellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		headCellStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);

		HSSFFont headFont = workbook.createFont();
		headFont.setFontHeightInPoints((short) 12);
		headFont.setFontName("Courier New");
		headCellStyle.setFont(headFont);
		List<EtlBanTjVO> vos = data.getVos();

		row = sheet.getRow(1);
		cell = row.getCell(0);
		ExcelUtils.setCellValue(cell, DateUtils.format(data.getCrea(), "yyyy年MM月dd日"));

		int rowint = 0;
		for (EtlBanTjVO bmRow : vos) {
			rowint++;
			// 最后一行
			if (rowint == vos.size()) {
				row = sheet.getRow(13);
				ExcelUtils.setCellValue(row.getCell(2), data.getJszlAll());
				ExcelUtils.setCellValue(row.getCell(4), data.getJlAll());
				ExcelUtils.setCellValue(row.getCell(5), data.getSdsjAll());
				ExcelUtils.setCellValue(row.getCell(7), data.getSdAll());
				ExcelUtils.setCellValue(row.getCell(8), data.getNliAll());
				ExcelUtils.setCellValue(row.getCell(9), bmRow.getZrzlAll());
				ExcelUtils.setCellValue(row.getCell(12), bmRow.getHgzpzlS());
				ExcelUtils.setCellValue(row.getCell(13), bmRow.getHgblS());
				ExcelUtils.setCellValue(row.getCell(14), bmRow.getFszpzlS());
				ExcelUtils.setCellValue(row.getCell(15), bmRow.getFczpzlS());

				row = sheet.getRow(14);
				ExcelUtils.setCellValue(row.getCell(2), data.getSjzlAll());
				ExcelUtils.setCellValue(row.getCell(9), bmRow.getZrzlS());
				ExcelUtils.setCellValue(row.getCell(10), bmRow.getZrzlOR());
				ExcelUtils.setCellValue(row.getCell(11), bmRow.getZrzlC());
				ExcelUtils.setCellValue(row.getCell(12), bmRow.getHgzpzlC());
				ExcelUtils.setCellValue(row.getCell(13), bmRow.getHgblC());
				ExcelUtils.setCellValue(row.getCell(14), bmRow.getFszpzlC());
				ExcelUtils.setCellValue(row.getCell(15), bmRow.getFczpzlC());

				row = sheet.getRow(15);
				ExcelUtils.setCellValue(row.getCell(2), data.getChazlAll());
				ExcelUtils.setCellValue(row.getCell(4), data.getZyAll());
				ExcelUtils.setCellValue(row.getCell(7), data.getXzAll());

				break;
			}
			rowIndex = rowIndex + 2;
			row = sheet.getRow(rowIndex);
			if (rowint != vos.size() - 1) {
				ExcelUtils.setCellValue(row.getCell(0), bmRow.getZu());
				ExcelUtils.setCellValue(row.getCell(1), bmRow.getZrzlAll());

				if (rowint == 1) {
					ExcelUtils.setCellValue(row.getCell(3), data.getZysj1());
					ExcelUtils.setCellValue(row.getCell(5), data.getSdsj1());
					ExcelUtils.setCellValue(row.getCell(6), data.getSdli1());
					ExcelUtils.setCellValue(row.getCell(8), data.getNli1());
				}
				if (rowint == 2) {
					ExcelUtils.setCellValue(row.getCell(3), data.getZysj2());
					ExcelUtils.setCellValue(row.getCell(5), data.getSdsj2());
					ExcelUtils.setCellValue(row.getCell(6), data.getSdli2());
					ExcelUtils.setCellValue(row.getCell(8), data.getNli2());
				}
				if (rowint == 3) {
					ExcelUtils.setCellValue(row.getCell(3), data.getZysj3());
					ExcelUtils.setCellValue(row.getCell(5), data.getSdsj3());
					ExcelUtils.setCellValue(row.getCell(6), data.getSdli3());
					ExcelUtils.setCellValue(row.getCell(8), data.getNli3());
				}
			}
			else {
				ExcelUtils.setCellValue(row.getCell(2), data.getJszl());
				ExcelUtils.setCellValue(row.getCell(3), data.getZysj());
				ExcelUtils.setCellValue(row.getCell(5), data.getSdsj());
				ExcelUtils.setCellValue(row.getCell(6), data.getSdli());
				ExcelUtils.setCellValue(row.getCell(8), data.getNli());
			}

			ExcelUtils.setCellValue(row.getCell(9), bmRow.getZrzlAll());
			ExcelUtils.setCellValue(row.getCell(12), bmRow.getHgzpzlS());
			ExcelUtils.setCellValue(row.getCell(13), bmRow.getHgblS());
			ExcelUtils.setCellValue(row.getCell(14), bmRow.getFszpzlS());
			ExcelUtils.setCellValue(row.getCell(15), bmRow.getFczpzlS());

			row = sheet.getRow(rowIndex + 1);
			// if (rowint == vos.size() - 1) {
			// ExcelUtils.setCellValue(row.getCell(2), data.getSjzl());
			// }
			ExcelUtils.setCellValue(row.getCell(9), bmRow.getZrzlS());
			ExcelUtils.setCellValue(row.getCell(10), bmRow.getZrzlOR());
			ExcelUtils.setCellValue(row.getCell(11), bmRow.getZrzlC());
			ExcelUtils.setCellValue(row.getCell(12), bmRow.getHgzpzlC());
			ExcelUtils.setCellValue(row.getCell(13), bmRow.getHgblC());
			ExcelUtils.setCellValue(row.getCell(14), bmRow.getFszpzlC());
			ExcelUtils.setCellValue(row.getCell(15), bmRow.getFczpzlC());

			if (rowint == vos.size() - 1) {
				ExcelUtils.setCellValue(row.getCell(2), data.getSjzl());

				row = sheet.getRow(rowIndex + 2);
				ExcelUtils.setCellValue(row.getCell(2), data.getChazl());
			}
		}

		rowIndex = 17;
		List<EtlSbStopDataVO> stopbms = data.getStopbms();
		if (stopbms != null && stopbms.size() > 0) {
			for (EtlSbStopDataVO stopRow : stopbms) {
				rowIndex++;
				row = sheet.getRow(rowIndex);
				if (row == null || stopRow == null) continue;
				ExcelUtils.setCellValue(row.getCell(0), stopRow.getStop());
				ExcelUtils.setCellValue(row.getCell(3), stopRow.getXzda());
				ExcelUtils.setCellValue(row.getCell(4), stopRow.getXzzy());
				ExcelUtils.setCellValue(row.getCell(5), stopRow.getXzjx());
				ExcelUtils.setCellValue(row.getCell(6), stopRow.getXzdj());
				ExcelUtils.setCellValue(row.getCell(7), stopRow.getRemk());
			}
		}
		rowIndex = 29;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(3), data.getXz1());
		ExcelUtils.setCellValue(row.getCell(4), data.getXz2());
		ExcelUtils.setCellValue(row.getCell(5), data.getXz3());
		ExcelUtils.setCellValue(row.getCell(6), data.getXz4());

		rowIndex = 30;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(3), data.getXzAll1());
		ExcelUtils.setCellValue(row.getCell(4), data.getXzAll2());
		ExcelUtils.setCellValue(row.getCell(5), data.getXzAll3());
		ExcelUtils.setCellValue(row.getCell(6), data.getXzAll4());
		ExcelUtils.setCellValue(row.getCell(8), data.getPass());
		ExcelUtils.setCellValue(row.getCell(9), data.getPassli());
		rowIndex = 31;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(3), data.getXzAllhour1());
		ExcelUtils.setCellValue(row.getCell(4), data.getXzAllhour2());
		ExcelUtils.setCellValue(row.getCell(5), data.getXzAllhour3());
		ExcelUtils.setCellValue(row.getCell(6), data.getXzAllhour4());
		ExcelUtils.setCellValue(row.getCell(8), data.getPassAll());
		ExcelUtils.setCellValue(row.getCell(9), data.getPassAllli());
		// 特记
		rowIndex = 16;
		ETLVariTp etlVariTp = data.getVari1();
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari1());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari2());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari3());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari4());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari5());

		rowIndex = 21;
		etlVariTp = data.getVari2();
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari1());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari2());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari3());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari4());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari5());

		rowIndex = 26;
		etlVariTp = data.getVari3();
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari1());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari2());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari3());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari4());
		rowIndex++;
		row = sheet.getRow(rowIndex);
		ExcelUtils.setCellValue(row.getCell(11), etlVariTp.getVari5());
	}
}
