package com.quanta.sino.zkfp.assist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.zkfp.vo.FpckItemVO;
import com.quanta.sino.zkfp.vo.FpckVO;

/**
 * <p>
 * 在库分配参考LISTExcel处理器
 * </p>
 * <p>
 * create: 2011-3-12 下午07:26:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpckListExcelDataExecuter implements ExcelbookDataExecuter<FpckVO> {

	@Override
	public void execute(HSSFWorkbook workbook, FpckVO data) {
		if (data == null || data.getMxs() == null) {
			return;
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCellStyle cellStyle;
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(false);

		HSSFFont cellFont = workbook.createFont();
		cellFont.setFontHeightInPoints((short) 8);
		cellFont.setFontName("Courier New");
		cellStyle.setFont(cellFont);

		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle txtZlStyle;
		txtZlStyle = workbook.createCellStyle();
		txtZlStyle.setWrapText(true);
		txtZlStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		txtZlStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		txtZlStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		txtZlStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		txtZlStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		txtZlStyle.setDataFormat(format.getFormat("#,##"));
		txtZlStyle.setFont(cellFont);

		HSSFRow row;
		HSSFCell cell;
		row = sheet.getRow(0);
		cell = row.getCell(0);
		ChanType chanType = ChanType.get(data.getChan());
		String name = chanType != null ? chanType.name : EXpzk.SC.value;
		StringBuilder title = new StringBuilder();
		title.append(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm")).append("    ").append(cell.getRichStringCellValue()).append(name);
		HSSFRichTextString hrts = new HSSFRichTextString(title.toString());
		cell.setCellValue(hrts);
		Long zpzl = 0l;
		int sl = 0;
		List<String> sizes = new ArrayList<String>();
		StringBuffer size = new StringBuffer();
		StringBuffer dxsc = new StringBuffer();
		StringBuffer jbno = new StringBuffer();
		int rowIndex = 3;
		for (FpckItemVO item : data.getMxs()) {
			size.delete(0, size.length());
			size.append(item.getYuny() != null ? item.getYuny() : "").append(SinoUtils.formatProductSize(item.getXpho(), item.getXpkn(), item.getXpcn())).append(item.getFace() != null ? item.getFace()
					: "");
			if (sizes.size() > 0 && !sizes.contains(size.toString())) {
				row = sheet.createRow((rowIndex++));
				cell = row.createCell(7);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, "合计");
				cell = row.createCell(8);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, sl);
				cell = row.createCell(11);
				cell.setCellStyle(txtZlStyle);
				ExcelUtils.setCellValue(cell, zpzl);
				zpzl = 0l;
				sl = 0;
				sizes = new ArrayList<String>();
			}
			sizes.add(size.toString());
			zpzl += item.getZpzl();
			sl += 1;

			row = sheet.createRow(rowIndex++);
			if (sizes.size() == 1) {
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, item.getYuny());
				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, item.getXpho());
				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, item.getXpkn());
				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, item.getXpcn() != null
						&& item.getXpcn() > 0 ? item.getXpcn() : "COIL");
				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, item.getFace());
				sizes.add(size.toString());
			}
			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getXpzk());
			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getZtbj());
			jbno.delete(0, jbno.length());
			jbno.append(item.getPlqf() != null ? item.getPlqf() : "").append(item.getZzsd() != null ? item.getZzsd()
					: "").append("-").append(item.getJbno());
			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, jbno.toString());
			cell = row.createCell(8);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getDuic());
			cell = row.createCell(9);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getYyan());
			cell = row.createCell(10);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getDmfx());
			cell = row.createCell(11);
			cell.setCellStyle(txtZlStyle);
			ExcelUtils.setCellValue(cell, item.getZpzl());

			// cell = row.createCell(12);
			// cell.setCellStyle(cellStyle);
			// ExcelUtils.setCellValue(cell, item.getCgdj());
			// cell = row.createCell(13);
			// cell.setCellStyle(cellStyle);
			// ExcelUtils.setCellValue(cell, item.getCbfy());

			cell = row.createCell(12);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getChan());
			cell = row.createCell(13);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getDeng());
			cell = row.createCell(14);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getYtyp());
			dxsc.delete(0, dxsc.length());
			dxsc.append(item.getSczm() != null ? item.getSczm() : "").append(item.getSczm() != null ? "/"
					: "").append(item.getScfm() != null ? item.getScfm() : "");
			cell = row.createCell(15);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dxsc.toString());
			cell = row.createCell(16);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getYsuo());
			cell = row.createCell(17);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getRczpno());
			cell = row.createCell(18);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getZzsj());
			cell = row.createCell(19);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, item.getAbbr());
		}
		// 最后一行的合计数
		row = sheet.createRow((rowIndex++));
		cell = row.createCell(7);
		cell.setCellStyle(cellStyle);
		ExcelUtils.setCellValue(cell, "合计");
		cell = row.createCell(8);
		cell.setCellStyle(cellStyle);
		ExcelUtils.setCellValue(cell, sl);
		cell = row.createCell(11);
		cell.setCellStyle(txtZlStyle);
		ExcelUtils.setCellValue(cell, zpzl);
	}
}
