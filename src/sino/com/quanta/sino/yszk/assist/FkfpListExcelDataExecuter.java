package com.quanta.sino.yszk.assist;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.cmn.constants.EFppz;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.yszk.vo.YszkQE;

/**
 * <p>
 * 付款发票LISTExcel处理器
 * </p>
 * <p>
 * create: 2011-7-5 下午09:14:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FkfpListExcelDataExecuter implements ExcelbookDataExecuter<YszkQE> {

	@Override
	public void execute(HSSFWorkbook workbook, YszkQE qentity) {
		List<Fkfp> datas = qentity.getDatas();
		if (datas == null || datas.size() == 0) {
			return;
		}
		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFCellStyle txtStyle;
		txtStyle = workbook.createCellStyle();
		txtStyle.setWrapText(true);
		txtStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFDataFormat format = workbook.createDataFormat();

		HSSFCellStyle txtKfzlStyle;
		txtKfzlStyle = workbook.createCellStyle();
		txtKfzlStyle.setWrapText(true);
		txtKfzlStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtKfzlStyle.setDataFormat(format.getFormat("#,##0.000"));

		HSSFCellStyle txtUsdStyle;
		txtUsdStyle = workbook.createCellStyle();
		txtUsdStyle.setWrapText(true);
		txtUsdStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtUsdStyle.setDataFormat(format.getFormat("$#,##0.0#"));

		HSSFCellStyle txtRmbStyle;
		txtRmbStyle = workbook.createCellStyle();
		txtRmbStyle.setWrapText(true);
		txtRmbStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtRmbStyle.setDataFormat(format.getFormat("¥#,##0.0#"));

		HSSFCellStyle hzStyle;
		hzStyle = workbook.createCellStyle();
		hzStyle.setFillForegroundColor(HSSFColor.RED.index);
		hzStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);

		HSSFCellStyle txtMoneyStyle = null;
		double $fpje = 0, $fkje = 0, $wsyk = 0, $kfzl = 0;
		HSSFRow row;
		HSSFCell cell;
		int rowIndex = 3;
		// String thqf = null;
		for (Fkfp item : datas) {
			// thqf = Code013.rmb.key.equals(item.getThqf()) ? "" : "$";
			txtMoneyStyle = Code013.rmb.key.equals(item.getThqf()) ? txtRmbStyle
					: txtUsdStyle;
			row = sheet.createRow((rowIndex++));

			if (EFppz.hz.key.equals(item.getFppz())) {
				cell = row.createCell(0);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, DateUtils.format(item.getChri(), "yyyy-MM-dd"));

				cell = row.createCell(1);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getName());

				cell = row.createCell(2);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getFpymc());

				cell = row.createCell(3);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getDhno() + "-"
						+ item.getLine());

				cell = row.createCell(4);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getGgno());

				cell = row.createCell(5);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getCdnm());

				cell = row.createCell(6);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getFuzm() + "/"
						+ item.getFufm());

				cell = row.createCell(7);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getHouu());

				cell = row.createCell(8);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getKuan());

				cell = row.createCell(9);
				cell.setCellStyle(hzStyle);
				ExcelUtils.setCellValue(cell, item.getCang() != null
						&& item.getCang() > 0 ? item.getCang() : "COIL");
			}
			else {
				cell = row.createCell(0);
				ExcelUtils.setCellValue(cell, DateUtils.format(item.getChri(), "yyyy-MM-dd"));

				cell = row.createCell(1);
				ExcelUtils.setCellValue(cell, item.getName());

				cell = row.createCell(2);
				ExcelUtils.setCellValue(cell, item.getFpymc());

				cell = row.createCell(3);
				ExcelUtils.setCellValue(cell, item.getDhno() + "-"
						+ item.getLine());

				cell = row.createCell(4);
				ExcelUtils.setCellValue(cell, item.getGgno());

				cell = row.createCell(5);
				ExcelUtils.setCellValue(cell, item.getCdnm());

				cell = row.createCell(6);
				ExcelUtils.setCellValue(cell, item.getFuzm() + "/"
						+ item.getFufm());

				cell = row.createCell(7);
				ExcelUtils.setCellValue(cell, item.getHouu());

				cell = row.createCell(8);
				ExcelUtils.setCellValue(cell, item.getKuan());

				cell = row.createCell(9);
				ExcelUtils.setCellValue(cell, item.getCang() != null
						&& item.getCang() > 0 ? item.getCang() : "COIL");
			}

			cell = row.createCell(10);
			cell.setCellStyle(txtKfzlStyle);
			ExcelUtils.setCellValue(cell, item.getKfzl());

			ExcelUtils.setCellValue(row.createCell(11), item.getKpdj());
			ExcelUtils.setCellValue(row.createCell(12), item.getZlzr());
			ExcelUtils.setCellValue(row.createCell(13), item.getLxzr());
			ExcelUtils.setCellValue(row.createCell(14), item.getTzje());

			cell = row.createCell(15);
			cell.setCellStyle(txtMoneyStyle);
			ExcelUtils.setCellValue(cell, item.getFpje());

			ExcelUtils.setCellValue(row.createCell(16), item.getFpno());
			ExcelUtils.setCellValue(row.createCell(17), DateUtils.format(item.getFpri(), "yyyy-MM-dd"));
			ExcelUtils.setCellValue(row.createCell(18), DateUtils.format(item.getSkri(), "yyyy-MM-dd"));

			cell = row.createCell(19);
			cell.setCellStyle(txtMoneyStyle);
			ExcelUtils.setCellValue(cell, item.getFkje());

			cell = row.createCell(20);
			cell.setCellStyle(txtMoneyStyle);
			ExcelUtils.setCellValue(cell, item.getWsyk());

			$fpje = NumberUtils.add($fpje, (item.getFpje() != null ? item.getFpje()
					: 0d)).doubleValue();
			$fkje = NumberUtils.add($fkje, (item.getFkje() != null ? item.getFkje()
					: 0d)).doubleValue();
			$wsyk = NumberUtils.add($wsyk, (item.getWsyk() != null ? item.getWsyk()
					: 0d)).doubleValue();
			$kfzl = NumberUtils.add($kfzl, (item.getKfzl() != null ? item.getKfzl()
					: 0d)).doubleValue();
		}
		// String nwai = qentity.getNwai();
		// System.out.println("nwai===============" + nwai);
		// thqf = (nwai == null || nwai.isEmpty() ||
		// CodeNwx.nei.key.equals(nwai) ? ""
		// : "$");
		// 最后一行的合计数
		row = sheet.createRow((rowIndex++));
		ExcelUtils.setCellValue(row.createCell(9), "合计");

		cell = row.createCell(10);
		cell.setCellStyle(txtKfzlStyle);
		ExcelUtils.setCellValue(cell, $kfzl);

		cell = row.createCell(15);
		cell.setCellStyle(txtMoneyStyle);
		ExcelUtils.setCellValue(cell, $fpje);

		cell = row.createCell(19);
		cell.setCellStyle(txtMoneyStyle);
		ExcelUtils.setCellValue(cell, $fkje);

		cell = row.createCell(20);
		cell.setCellStyle(txtMoneyStyle);
		ExcelUtils.setCellValue(cell, $wsyk);
	}
}
