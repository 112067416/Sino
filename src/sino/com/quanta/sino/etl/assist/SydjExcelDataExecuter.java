package com.quanta.sino.etl.assist;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.etl.vo.EtlSyAllSheetVO;
import com.quanta.sino.etl.vo.EtlSyBanSheetVO;
import com.quanta.sino.etl.vo.EtlSybmSheetVO;
import com.quanta.sino.etl.vo.EtlSydjDataVO;
import com.quanta.sino.etl.vo.EtlSylmSheetVO;

/**
 * <p>
 * etl锡原统计（按月）Excel处理器
 * </p>
 * <p>
 * create: 2011-1-28 下午04:36:45
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
public class SydjExcelDataExecuter implements
		ExcelbookDataExecuter<EtlSydjDataVO> {

	@Override
	public void execute(HSSFWorkbook workbook, EtlSydjDataVO data) {

		HSSFRow row;
		int rowIndex = 4;
		HSSFSheet sheet = null;
		// 第1个Sheet(表面锡原统计)
		sheet = workbook.getSheetAt(0);
		EtlSybmSheetVO bm = data.getBm();
		if (bm.getRows() != null && !bm.getRows().isEmpty()) {
			for (EtlSybmSheetVO.Row bmRow : bm.getRows()) {
				row = sheet.createRow((rowIndex++));
				ExcelUtils.setCellValue(row.createCell(0), bmRow.getZrzlAll());
				ExcelUtils.setCellValue(row.createCell(1), NumberUtils.format(bmRow.getFuzm(), 2));
				ExcelUtils.setCellValue(row.createCell(2), NumberUtils.format(bmRow.getSjsczm(), 2));
				ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(bmRow.getJcha1(), 2));
				ExcelUtils.setCellValue(row.createCell(4), NumberUtils.format(bmRow.getMbfuzm(), 2));
				ExcelUtils.setCellValue(row.createCell(5), NumberUtils.format(bmRow.getSjfuzm(), 2));
				ExcelUtils.setCellValue(row.createCell(6), NumberUtils.format(bmRow.getJcha2(), 2));
				ExcelUtils.setCellValue(row.createCell(7), NumberUtils.format(bmRow.getSjyl(), 2));
				ExcelUtils.setCellValue(row.createCell(8), NumberUtils.format(bmRow.getMbyl(), 2));
			}
		}
		// 第2个Sheet(里面锡原统计)
		rowIndex = 4;
		sheet = workbook.getSheetAt(1);
		EtlSylmSheetVO lm = data.getLm();
		if (lm.getRows() != null && !lm.getRows().isEmpty()) {
			for (EtlSylmSheetVO.Row lmRow : lm.getRows()) {
				row = sheet.createRow((rowIndex++));
				ExcelUtils.setCellValue(row.createCell(0), lmRow.getZrzlAll());
				ExcelUtils.setCellValue(row.createCell(1), NumberUtils.format(lmRow.getFufm(), 2));
				ExcelUtils.setCellValue(row.createCell(2), NumberUtils.format(lmRow.getSjscfm(), 2));
				ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(lmRow.getJcha1(), 2));
				ExcelUtils.setCellValue(row.createCell(4), NumberUtils.format(lmRow.getMbfufm(), 2));
				ExcelUtils.setCellValue(row.createCell(5), NumberUtils.format(lmRow.getSjfufm(), 2));
				ExcelUtils.setCellValue(row.createCell(6), NumberUtils.format(lmRow.getJcha2(), 2));
				ExcelUtils.setCellValue(row.createCell(7), NumberUtils.format(lmRow.getSjyl(), 2));
				ExcelUtils.setCellValue(row.createCell(8), NumberUtils.format(lmRow.getMbyl(), 2));

			}
		}
		// 第3个Sheet(里面锡原统计)
		rowIndex = 2;
		sheet = workbook.getSheetAt(2);
		EtlSyAllSheetVO all = data.getAll();
		if (all.getRows() != null && !all.getRows().isEmpty()) {
			for (EtlSyAllSheetVO.Row allRow : all.getRows()) {
				row = sheet.createRow((rowIndex++));
				ExcelUtils.setCellValue(row.createCell(0), allRow.getZrzlAll());
				ExcelUtils.setCellValue(row.createCell(1), NumberUtils.format(allRow.getMbfu(), 2));
				ExcelUtils.setCellValue(row.createCell(2), NumberUtils.format(allRow.getSjfu(), 2));
				ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(allRow.getJcha(), 2));
			}
		}
		// 第4个Sheet(表面锡原统计)
		Boolean blBan = true;
		int mergRow1 = 0;
		int mergRow2 = 0;
		rowIndex = 5;
		sheet = workbook.getSheetAt(3);
		EtlSyBanSheetVO ban = data.getBan();
		if (ban.getRows() != null && !ban.getRows().isEmpty()) {
			// 填充表面各班数据
			for (EtlSyBanSheetVO.Row banRow : ban.getRows()) {
				mergRow1 = rowIndex;
				mergRow2 = rowIndex;
				if (banRow.getBms().getRows() != null
						&& !banRow.getBms().getRows().isEmpty()) {
					ExcelUtils.insertRow(sheet, rowIndex - 1, banRow.getBms().getRows().size());
					for (EtlSybmSheetVO.Row bmRow : banRow.getBms().getRows()) {

						row = sheet.createRow((rowIndex++));
						if (blBan) {
							ExcelUtils.setCellValue(row.createCell(1), banRow.getBanname());
							blBan = false;
						}
						else {
							mergRow2 = mergRow2 + 1;
						}
						ExcelUtils.setCellValue(row.createCell(2), bmRow.getZrzlAll());
						ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(bmRow.getFuzm(), 2));
						ExcelUtils.setCellValue(row.createCell(4), NumberUtils.format(bmRow.getSjsczm(), 2));
						ExcelUtils.setCellValue(row.createCell(5), NumberUtils.format(bmRow.getJcha1(), 2));
						ExcelUtils.setCellValue(row.createCell(6), NumberUtils.format(bmRow.getMbfuzm(), 2));
						ExcelUtils.setCellValue(row.createCell(7), NumberUtils.format(bmRow.getSjfuzm(), 2));
						ExcelUtils.setCellValue(row.createCell(8), NumberUtils.format(bmRow.getJcha2(), 2));
						// ExcelUtils.setCellValue(row.createCell(9),
						// NumberUtils.format(bmRow.getSjyl(), 2));
						// ExcelUtils.setCellValue(row.createCell(10),
						// NumberUtils.format(bmRow.getMbyl(), 2));
					}
				}
				sheet.addMergedRegion(new CellRangeAddress(mergRow1, mergRow2,
						1, 1));
				blBan = true;
				mergRow1 = 0;
				mergRow2 = 0;

			}
			rowIndex = rowIndex + 5;
			blBan = true;
			// 填充里面各班数据
			for (EtlSyBanSheetVO.Row banRow : ban.getRows()) {
				mergRow1 = rowIndex;
				mergRow2 = rowIndex;
				if (banRow.getLms().getRows() != null
						&& !banRow.getLms().getRows().isEmpty()) {
					ExcelUtils.insertRow(sheet, rowIndex - 1, banRow.getLms().getRows().size());
					for (EtlSylmSheetVO.Row lmRow : banRow.getLms().getRows()) {
						row = sheet.createRow((rowIndex++));
						if (blBan) {
							ExcelUtils.setCellValue(row.createCell(1), banRow.getBanname());
							blBan = false;
						}
						else {
							mergRow2 = mergRow2 + 1;
						}
						ExcelUtils.setCellValue(row.createCell(2), lmRow.getZrzlAll());
						ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(lmRow.getFufm(), 2));
						ExcelUtils.setCellValue(row.createCell(4), NumberUtils.format(lmRow.getSjscfm(), 2));
						ExcelUtils.setCellValue(row.createCell(5), NumberUtils.format(lmRow.getJcha1(), 2));
						ExcelUtils.setCellValue(row.createCell(6), NumberUtils.format(lmRow.getMbfufm(), 2));
						ExcelUtils.setCellValue(row.createCell(7), NumberUtils.format(lmRow.getSjfufm(), 2));
						ExcelUtils.setCellValue(row.createCell(8), NumberUtils.format(lmRow.getJcha2(), 2));
						// ExcelUtils.setCellValue(row.createCell(9),
						// NumberUtils.format(lmRow.getSjyl(), 2));
						// ExcelUtils.setCellValue(row.createCell(10),
						// NumberUtils.format(lmRow.getMbyl(), 2));
					}
				}
				sheet.addMergedRegion(new CellRangeAddress(mergRow1, mergRow2,
						1, 1));
				blBan = true;
				mergRow1 = 0;
				mergRow2 = 0;

			}
			rowIndex = rowIndex + 4;
			blBan = true;
			// 填充总各班数据
			for (EtlSyBanSheetVO.Row banRow : ban.getRows()) {
				mergRow1 = rowIndex;
				mergRow2 = rowIndex;
				if (banRow.getLms().getRows() != null
						&& !banRow.getLms().getRows().isEmpty()) {
					for (EtlSyAllSheetVO.Row allRow : banRow.getAlls().getRows()) {
						row = sheet.createRow((rowIndex++));
						if (blBan) {
							ExcelUtils.setCellValue(row.createCell(1), banRow.getBanname());
							blBan = false;
						}
						else {
							mergRow2 = mergRow2 + 1;
						}
						ExcelUtils.setCellValue(row.createCell(2), allRow.getZrzlAll());
						ExcelUtils.setCellValue(row.createCell(3), NumberUtils.format(allRow.getMbfu(), 2));
						ExcelUtils.setCellValue(row.createCell(4), NumberUtils.format(allRow.getSjfu(), 2));
						ExcelUtils.setCellValue(row.createCell(5), NumberUtils.format(allRow.getJcha(), 2));
					}
				}
				sheet.addMergedRegion(new CellRangeAddress(mergRow1, mergRow2,
						1, 1));
				blBan = true;
				mergRow1 = 0;
				mergRow2 = 0;

			}

		}

	}

}
