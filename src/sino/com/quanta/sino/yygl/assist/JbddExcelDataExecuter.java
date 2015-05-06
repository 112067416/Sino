package com.quanta.sino.yygl.assist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.orm.JbddItemLog;
import com.quanta.sino.yygl.vo.JbddItemVO;
import com.quanta.sino.yygl.vo.JbddVO;

/**
 * <p>
 * 基板订单统计Excel处理器
 * </p>
 * <p>
 * create: 2011-8-8 下午04:45:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddExcelDataExecuter implements ExcelbookDataExecuter<JbddVO> {

	@Override
	public void execute(HSSFWorkbook workbook, JbddVO data) {
		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFCellStyle headStyle;
		headStyle = workbook.createCellStyle();
		headStyle.setWrapText(true);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtCenterStyle1;
		txtCenterStyle1 = workbook.createCellStyle();
		txtCenterStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle1.setWrapText(true);
		txtCenterStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		txtCenterStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtLeftStyle1;
		txtLeftStyle1 = workbook.createCellStyle();
		txtLeftStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle1.setWrapText(true);
		txtLeftStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		txtLeftStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtRightStyle1;
		txtRightStyle1 = workbook.createCellStyle();
		txtRightStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtRightStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtRightStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtRightStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtRightStyle1.setWrapText(true);
		txtRightStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtRightStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtCenterStyle2;
		txtCenterStyle2 = workbook.createCellStyle();
		txtCenterStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtCenterStyle2.setFillForegroundColor(HSSFColor.YELLOW.index);
		txtCenterStyle2.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		txtCenterStyle2.setWrapText(true);
		txtCenterStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		txtCenterStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtLeftStyle2;
		txtLeftStyle2 = workbook.createCellStyle();
		txtLeftStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtLeftStyle2.setFillForegroundColor(HSSFColor.YELLOW.index);
		txtLeftStyle2.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		txtLeftStyle2.setWrapText(true);
		txtLeftStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		txtLeftStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle txtRightStyle2;
		txtRightStyle2 = workbook.createCellStyle();
		txtRightStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtRightStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtRightStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtRightStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtRightStyle2.setFillForegroundColor(HSSFColor.YELLOW.index);
		txtRightStyle2.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		txtRightStyle2.setWrapText(true);
		txtRightStyle2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtRightStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFDataFormat format = workbook.createDataFormat();

		HSSFCellStyle numStyle1;
		numStyle1 = workbook.createCellStyle();
		numStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		numStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		numStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		numStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		numStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		numStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		numStyle1.setDataFormat(format.getFormat("#,##0.000"));

		HSSFCellStyle numStyle2;
		numStyle2 = workbook.createCellStyle();
		numStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		numStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		numStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		numStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		numStyle2.setFillForegroundColor(HSSFColor.YELLOW.index);
		numStyle2.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);
		numStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		numStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		numStyle2.setDataFormat(format.getFormat("#,##0.000"));

		HSSFFont cellFont = workbook.createFont();
		cellFont.setFontHeightInPoints((short) 11);
		cellFont.setFontName("Courier New");

		HSSFFont headFont = workbook.createFont();
		headFont.setFontHeightInPoints((short) 12);
		headFont.setFontName("Courier New");

		headStyle.setFont(headFont);

		txtCenterStyle1.setFont(cellFont);
		txtLeftStyle1.setFont(cellFont);
		txtRightStyle1.setFont(cellFont);

		txtCenterStyle2.setFont(cellFont);
		txtLeftStyle2.setFont(cellFont);
		txtRightStyle2.setFont(cellFont);

		numStyle1.setFont(cellFont);
		numStyle2.setFont(cellFont);

		HSSFRow row = null;
		HSSFCell cell;
		int rowIndex = 3;

		int num = 0;

		int hjtl = 0;
		int hjcn = 0;
		int xjtl = 0;
		int xjcn = 0;
		Integer index;
		JbddItem item;
		JbddItemLog log;
		String fuzm, fufm, $fuzm = "", $fufm = "", hanc;
		StringBuilder title = new StringBuilder();
		title.append("TMBP ORDER OF SJM ").append(data.getJbno()).append(" FUKUYAMA");
		row = sheet.getRow(0);
		cell = row.getCell(2);
		cell.setCellStyle(headStyle);
		ExcelUtils.setCellValue(cell, title.toString());

		if (data.getCrea() != null) {
			row = sheet.getRow(1);

			HSSFCellStyle rightStyle;
			rightStyle = workbook.createCellStyle();
			rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			rightStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			cell = row.createCell(20);
			cell.setCellStyle(rightStyle);
			ExcelUtils.setCellValue(cell, DateUtils.format(data.getCrea(), "yyyy-MM-dd"));
		}

		// 国内数据
		if (data.getNxItems() != null && data.getNxItems().size() > 0) {
			List<String> ajbs = new ArrayList<String>();
			String ajb = null;
			for (JbddItemVO vo : data.getNxItems()) {
				num++;

				item = vo.getItem();
				log = vo.getLog();
				index = item.getItem();
				ajb = item.getAjb() != null ? item.getAjb().toString() : "0";
				if (ajbs.size() == 0) {
					ajbs.add(ajb);
				}
				if (!ajbs.contains(ajb)) {
					ajbs.add(ajb);
					row = sheet.createRow(rowIndex++);
					row.setHeight((short) 335);
					cell = row.createCell(14);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, xjtl);

					cell = row.createCell(15);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, xjcn);

					cell = row.createCell(16);
					ExcelUtils.setCellValue(cell, null);

					xjtl = 0;
					xjcn = 0;
				}

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 335);

				cell = row.createCell(0);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(1);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, index == null
						|| index.intValue() <= 0 ? num : index);

				cell = row.createCell(2);
				if (log == null || compareValue(item.getIsgi(), log.getIsgi())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getIsgi());

				cell = row.createCell(3);
				if (log == null || compareValue(item.getInpu(), log.getInpu())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getInpu());

				cell = row.createCell(4);
				if (log == null || compareValue(item.getFace(), log.getFace())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getFace());

				cell = row.createCell(5);
				if (log == null || compareValue(item.getYuny(), log.getYuny())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getYuny());

				cell = row.createCell(6);
				if (log == null || compareValue(item.getHoua(), log.getHoua())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoua());

				cell = row.createCell(7);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, "X");

				cell = row.createCell(8);
				if (log == null
						|| compareValue(item.getWidth(), log.getWidth())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getWidth());

				cell = row.createCell(9);
				if (log == null || compareValue(item.getHoub(), log.getHoub())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoub());

				cell = row.createCell(10);
				if (log == null || compareValue(item.getAjb(), log.getAjb())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAjb());

				cell = row.createCell(11);
				if (log == null || compareValue(item.getDhsl(), log.getDhsl())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getDhsl());

				cell = row.createCell(12);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(13);
				cell.setCellStyle(txtLeftStyle1);
				ExcelUtils.setCellValue(cell, item.getCalc());

				cell = row.createCell(14);
				if (log == null
						|| compareValue(item.getTotal(), log.getTotal())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getTotal());

				cell = row.createCell(15);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, item.getConf());

				cell = row.createCell(16);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(17);
				if (log == null || compareValue(item.getAbbr(), log.getAbbr())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAbbr());

				cell = row.createCell(18);
				if (log == null || compareValue(item.getCond(), log.getCond())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCond());

				cell = row.createCell(19);
				if (log == null || compareValue(item.getCode(), log.getCode())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCode());

				cell = row.createCell(20);
				if (log == null || compareValue(item.getRema(), log.getRema())) {
					cell.setCellStyle(txtLeftStyle1);
				}
				else {
					cell.setCellStyle(txtLeftStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getRema());

				hanc = item.getHanc();
				cell = row.createCell(21);
				if (log == null || compareValue(item.getHanc(), log.getHanc())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, hanc != null
						&& hanc.length() == 2 ? new StringBuilder(hanc).insert(1, "/")
						: hanc);

				fuzm = item.getFuzm() != null ? item.getFuzm() : "";
				fufm = item.getFufm() != null ? item.getFufm() : "";

				if (log != null) {
					$fuzm = log.getFuzm() != null ? log.getFuzm() : "";
					$fufm = log.getFufm() != null ? log.getFufm() : "";
				}

				cell = row.createCell(22);
				if (log == null || compareValue(fuzm + fufm, $fuzm + $fufm)) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, fuzm + "/" + fufm);

				hjtl += (item.getTotal() != null ? item.getTotal() : 0);
				hjcn += (item.getConf() != null ? item.getConf() : 0);

				xjtl += (item.getTotal() != null ? item.getTotal() : 0);
				xjcn += (item.getConf() != null ? item.getConf() : 0);

			}

			if (xjtl > 0) {
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 335);
				cell = row.createCell(14);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, xjtl);

				cell = row.createCell(15);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, xjcn);

				cell = row.createCell(16);
				ExcelUtils.setCellValue(cell, null);

				xjtl = 0;
				xjcn = 0;
			}

			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 335);
			cell = row.createCell(14);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjtl);

			cell = row.createCell(15);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjcn);

			cell = row.createCell(16);
			ExcelUtils.setCellValue(cell, null);
		}

		// 国外数据
		if (data.getWxItems() != null && data.getWxItems().size() > 0) {
			for (JbddItemVO vo : data.getWxItems()) {
				num++;
				item = vo.getItem();
				log = vo.getLog();
				index = item.getItem();
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 335);

				cell = row.createCell(0);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(1);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, index == null
						|| index.intValue() <= 0 ? num : index);

				cell = row.createCell(2);
				if (log == null || compareValue(item.getIsgi(), log.getIsgi())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getIsgi());

				cell = row.createCell(3);
				if (log == null || compareValue(item.getInpu(), log.getInpu())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getInpu());

				cell = row.createCell(4);
				if (log == null || compareValue(item.getFace(), log.getFace())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getFace());

				cell = row.createCell(5);
				if (log == null || compareValue(item.getYuny(), log.getYuny())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getYuny());

				cell = row.createCell(6);
				if (log == null || compareValue(item.getHoua(), log.getHoua())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoua());

				cell = row.createCell(7);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, "X");

				cell = row.createCell(8);
				if (log == null
						|| compareValue(item.getWidth(), log.getWidth())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getWidth());

				cell = row.createCell(9);
				if (log == null || compareValue(item.getHoub(), log.getHoub())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoub());

				cell = row.createCell(10);
				if (log == null || compareValue(item.getAjb(), log.getAjb())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAjb());

				cell = row.createCell(11);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(12);
				if (log == null || compareValue(item.getCksl(), log.getCksl())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCksl());

				cell = row.createCell(13);
				cell.setCellStyle(txtLeftStyle1);
				ExcelUtils.setCellValue(cell, item.getCalc());

				cell = row.createCell(14);
				if (log == null
						|| compareValue(item.getTotal(), log.getTotal())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getTotal());

				cell = row.createCell(15);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, item.getConf());

				cell = row.createCell(16);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(17);
				if (log == null || compareValue(item.getAbbr(), log.getAbbr())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAbbr());

				cell = row.createCell(18);
				if (log == null || compareValue(item.getCond(), log.getCond())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCond());

				cell = row.createCell(19);
				if (log == null || compareValue(item.getCode(), log.getCode())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCode());

				cell = row.createCell(20);
				if (log == null || compareValue(item.getRema(), log.getRema())) {
					cell.setCellStyle(txtLeftStyle1);
				}
				else {
					cell.setCellStyle(txtLeftStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getRema());

				hanc = item.getHanc();
				cell = row.createCell(21);
				if (log == null || compareValue(item.getHanc(), log.getHanc())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, hanc != null
						&& hanc.length() == 2 ? new StringBuilder(hanc).insert(1, "/")
						: hanc);

				fuzm = item.getFuzm() != null ? item.getFuzm() : "";
				fufm = item.getFufm() != null ? item.getFufm() : "";

				if (log != null) {
					$fuzm = log.getFuzm() != null ? log.getFuzm() : "";
					$fufm = log.getFufm() != null ? log.getFufm() : "";
				}

				cell = row.createCell(22);
				if (log == null || compareValue(fuzm + fufm, $fuzm + $fufm)) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, fuzm + "/" + fufm);

				hjtl += (item.getTotal() != null ? item.getTotal() : 0);
				hjcn += (item.getConf() != null ? item.getConf() : 0);

				xjtl += (item.getTotal() != null ? item.getTotal() : 0);
				xjcn += (item.getConf() != null ? item.getConf() : 0);

			}
			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 335);

			cell = row.createCell(14);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, xjtl);

			cell = row.createCell(15);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, xjcn);

			cell = row.createCell(16);
			ExcelUtils.setCellValue(cell, null);

			xjtl = 0;
			xjcn = 0;

			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 335);

			cell = row.createCell(14);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjtl);

			cell = row.createCell(15);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjcn);

			cell = row.createCell(16);
			ExcelUtils.setCellValue(cell, null);
		}

		// 追加数据
		if (data.getZjItems() != null && data.getZjItems().size() > 0) {
			List<Date> zjrqs = new ArrayList<Date>();
			Date zjrq = null;
			for (JbddItemVO vo : data.getZjItems()) {
				num++;
				item = vo.getItem();
				log = vo.getLog();

				index = item.getItem();
				zjrq = item.getZjrq() != null ? item.getZjrq()
						: DateUtils.parse(new Date(), "yyyy-MM-dd");
				if (zjrqs.size() == 0) {
					zjrqs.add(zjrq);
				}
				if (!zjrqs.contains(zjrq)) {
					zjrqs.add(zjrq);
					row = sheet.createRow(rowIndex++);
					row.setHeight((short) 335);
					cell = row.createCell(14);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, xjtl);

					cell = row.createCell(15);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, xjcn);

					cell = row.createCell(16);
					ExcelUtils.setCellValue(cell, null);

					xjtl = 0;
					xjcn = 0;

					row = sheet.createRow(rowIndex++);
					row.setHeight((short) 335);

					cell = row.createCell(14);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, hjtl);

					cell = row.createCell(15);
					cell.setCellStyle(txtRightStyle1);
					ExcelUtils.setCellValue(cell, hjcn);

					cell = row.createCell(16);
					ExcelUtils.setCellValue(cell, null);
				}

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 335);

				cell = row.createCell(0);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, "additionl");

				cell = row.createCell(1);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, index == null
						|| index.intValue() <= 0 ? num : index);

				cell = row.createCell(2);
				if (log == null || compareValue(item.getIsgi(), log.getIsgi())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getIsgi());

				cell = row.createCell(3);
				if (log == null || compareValue(item.getInpu(), log.getInpu())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getInpu());

				cell = row.createCell(4);
				if (log == null || compareValue(item.getFace(), log.getFace())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getFace());

				cell = row.createCell(5);
				if (log == null || compareValue(item.getYuny(), log.getYuny())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getYuny());

				cell = row.createCell(6);
				if (log == null || compareValue(item.getHoua(), log.getHoua())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoua());

				cell = row.createCell(7);
				cell.setCellStyle(txtCenterStyle1);
				ExcelUtils.setCellValue(cell, "X");

				cell = row.createCell(8);
				if (log == null
						|| compareValue(item.getWidth(), log.getWidth())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getWidth());

				cell = row.createCell(9);
				if (log == null || compareValue(item.getHoub(), log.getHoub())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getHoub());

				cell = row.createCell(10);
				if (log == null || compareValue(item.getAjb(), log.getAjb())) {
					cell.setCellStyle(numStyle1);
				}
				else {
					cell.setCellStyle(numStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAjb());

				cell = row.createCell(11);
				if (log == null || compareValue(item.getDhsl(), log.getDhsl())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getDhsl());

				cell = row.createCell(12);
				if (log == null || compareValue(item.getCksl(), log.getCksl())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCksl());

				cell = row.createCell(13);
				cell.setCellStyle(txtLeftStyle1);
				ExcelUtils.setCellValue(cell, item.getCalc());

				cell = row.createCell(14);
				if (log == null
						|| compareValue(item.getTotal(), log.getTotal())) {
					cell.setCellStyle(txtRightStyle1);
				}
				else {
					cell.setCellStyle(txtRightStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getTotal());

				cell = row.createCell(15);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, item.getConf());

				cell = row.createCell(16);
				ExcelUtils.setCellValue(cell, null);

				cell = row.createCell(17);
				if (log == null || compareValue(item.getAbbr(), log.getAbbr())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getAbbr());

				cell = row.createCell(18);
				if (log == null || compareValue(item.getCond(), log.getCond())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCond());

				cell = row.createCell(19);
				if (log == null || compareValue(item.getCode(), log.getCode())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getCode());

				cell = row.createCell(20);
				if (log == null || compareValue(item.getRema(), log.getRema())) {
					cell.setCellStyle(txtLeftStyle1);
				}
				else {
					cell.setCellStyle(txtLeftStyle2);
				}
				ExcelUtils.setCellValue(cell, item.getRema());

				hanc = item.getHanc();
				cell = row.createCell(21);
				if (log == null || compareValue(item.getHanc(), log.getHanc())) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, hanc != null
						&& hanc.length() == 2 ? new StringBuilder(hanc).insert(1, "/")
						: hanc);

				fuzm = item.getFuzm() != null ? item.getFuzm() : "";
				fufm = item.getFufm() != null ? item.getFufm() : "";

				if (log != null) {
					$fuzm = log.getFuzm() != null ? log.getFuzm() : "";
					$fufm = log.getFufm() != null ? log.getFufm() : "";
				}

				cell = row.createCell(22);
				if (log == null || compareValue(fuzm + fufm, $fuzm + $fufm)) {
					cell.setCellStyle(txtCenterStyle1);
				}
				else {
					cell.setCellStyle(txtCenterStyle2);
				}
				ExcelUtils.setCellValue(cell, fuzm + "/" + fufm);

				hjtl += (item.getTotal() != null ? item.getTotal() : 0);
				hjcn += (item.getConf() != null ? item.getConf() : 0);

				xjtl += (item.getTotal() != null ? item.getTotal() : 0);
				xjcn += (item.getConf() != null ? item.getConf() : 0);

			}
			if (xjtl > 0) {
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 335);

				cell = row.createCell(14);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, xjtl);

				cell = row.createCell(15);
				cell.setCellStyle(txtRightStyle1);
				ExcelUtils.setCellValue(cell, xjcn);

				cell = row.createCell(16);
				ExcelUtils.setCellValue(cell, null);

				xjtl = 0;
				xjcn = 0;
			}

			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 335);

			cell = row.createCell(14);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjtl);

			cell = row.createCell(15);
			cell.setCellStyle(txtRightStyle1);
			ExcelUtils.setCellValue(cell, hjcn);

			cell = row.createCell(16);
			ExcelUtils.setCellValue(cell, null);
		}
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	private boolean compareValue(String v1, String v2) {
		String $v1 = v1 != null ? v1 : "";
		String $v2 = v2 != null ? v2 : "";
		if (!$v1.equals($v2)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	private boolean compareValue(Integer v1, Integer v2) {
		int $v1 = v1 != null ? v1.intValue() : 0;
		int $v2 = v2 != null ? v2.intValue() : 0;
		if ($v1 != $v2) return false;
		return true;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	private boolean compareValue(Double v1, Double v2) {
		double $v1 = v1 != null ? v1.doubleValue() : 0;
		double $v2 = v2 != null ? v2.doubleValue() : 0;
		if ($v1 != $v2) return false;
		return true;
	}
}