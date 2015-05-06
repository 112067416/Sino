package com.quanta.sino.dh.assist;

import java.util.Date;
import java.util.HashMap;
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
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.dh.vo.DhjdVO;
import com.quanta.sino.dh.vo.Dhjdmx;
import com.quanta.sino.dh.vo.DhuoVO;
import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 订货进度Excel处理器
 * </p>
 * <p>
 * create: 2011-3-4 下午04:25:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhjdExcelDataExecuter implements ExcelbookDataExecuter<DhjdVO> {
	private final static Map<Integer, Integer> COLUMN_WIDTH = new HashMap<Integer, Integer>();

	static {
		COLUMN_WIDTH.put(0, 2600);
		COLUMN_WIDTH.put(1, 2400);
		COLUMN_WIDTH.put(2, 1400);
		COLUMN_WIDTH.put(3, 2000);
		COLUMN_WIDTH.put(4, 2600);
		COLUMN_WIDTH.put(5, 4300);
		COLUMN_WIDTH.put(6, 2000);
		COLUMN_WIDTH.put(7, 1800);
		COLUMN_WIDTH.put(8, 1300);
		COLUMN_WIDTH.put(9, 2200);
		COLUMN_WIDTH.put(10, 2800);
		COLUMN_WIDTH.put(11, 2600);
		COLUMN_WIDTH.put(12, 2600);
		COLUMN_WIDTH.put(13, 2600);
	}

	@Override
	public void execute(HSSFWorkbook workbook, DhjdVO data) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (Integer key : COLUMN_WIDTH.keySet()) {
			sheet.setColumnWidth(key, COLUMN_WIDTH.get(key));
		}

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
		headFont.setFontHeightInPoints((short) 8);
		headFont.setFontName("Courier New");
		headCellStyle.setFont(headFont);

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

		HSSFRow row;
		HSSFCell cell;

		row = sheet.createRow(1);
		cell = row.createCell(12);
		ExcelUtils.setCellValue(cell, DateUtils.format(new Date(), "yyyy-MM-dd HH:mm"));

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		int rowIndex = 2;

		Map<DhuoVO, List<Dhjdmx>> dhjdmxs = data.getDhjdmxs();
		for (DhuoVO dhuoTp : dhjdmxs.keySet()) {
			if (rowIndex > 2) {
				row = sheet.createRow(rowIndex++);
			}
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "用户");
			ExcelUtils.setCellValue(row.createCell(1), dhuoTp.getUser() + " "
					+ dhuoTp.getAbbr());

			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "交货期");
			cell = row.createCell(1);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "订货No");
			cell = row.createCell(2);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "品种");
			cell = row.createCell(3);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "规格");
			cell = row.createCell(4);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "附着量");
			cell = row.createCell(5);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "订货尺寸");
			cell = row.createCell(6);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "表面");
			cell = row.createCell(7);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "压方");
			cell = row.createCell(8);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "S足");
			cell = row.createCell(9);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "订货量(T)");
			cell = row.createCell(10);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "交货允许");
			cell = row.createCell(11);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "分配量");
			cell = row.createCell(12);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "ELT实绩(T)");
			cell = row.createCell(13);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "SL实绩(T)");

			row = sheet.createRow(rowIndex++);
			cell = row.createCell(3);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "运用规格");
			cell = row.createCell(10);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "计划外实绩(T)");
			cell = row.createCell(11);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "出货实绩(T)");
			cell = row.createCell(12);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "退货实绩(T)");
			cell = row.createCell(13);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "捆包实绩(T)");

			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getJhqi());

			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getDhno() + "-"
					+ dhuoTp.getLine());

			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getPzno());

			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getGgno());

			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getFudw() + " "
					+ dhuoTp.getFuzm() + " " + dhuoTp.getFufm());

			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, SinoUtils.formatProductSize(dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getCang()));

			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getFace());

			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getYyan());
			// S足
			cell = row.createCell(8);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getDmfx());

			cell = row.createCell(9);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getHtzl());
			// 交货允许
			String jhrs = dhuoTp.getJhqf() != null ? dhuoTp.getJhqf() : "";
			jhrs = dhuoTp.getJhfz() != null ? jhrs + dhuoTp.getJhfz() : jhrs;
			jhrs = dhuoTp.getJhzz() != null ? jhrs + " " + dhuoTp.getJhzz()
					: jhrs;
			cell = row.createCell(10);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, jhrs);

			cell = row.createCell(11);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getFpln());

			cell = row.createCell(12);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getEtlh());

			cell = row.createCell(13);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getSlhg());

			row = sheet.createRow(rowIndex++);

			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getYuny());
			// 计划外实绩（T）
			cell = row.createCell(10);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, "");

			cell = row.createCell(11);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getChus());
			cell = row.createCell(12);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getThus());

			cell = row.createCell(13);
			cell.setCellStyle(cellStyle);
			ExcelUtils.setCellValue(cell, dhuoTp.getKbus());
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(5);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "保留");
			cell = row.createCell(6);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "分配NO.");
			cell = row.createCell(7);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "指示NO.");
			cell = row.createCell(8);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "STOP");
			cell = row.createCell(9);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "P区");
			cell = row.createCell(10);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "产量");
			cell = row.createCell(11);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "现品NO.");
			cell = row.createCell(12);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "制品重量（T）");
			cell = row.createCell(13);
			cell.setCellStyle(headCellStyle);
			ExcelUtils.setCellValue(cell, "堆场");

			for (Dhjdmx mx : dhjdmxs.get(dhuoTp)) {
				row = sheet.createRow((rowIndex++));
				cell = row.createCell(5);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getBlbj());

				cell = row.createCell(6);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getFpno());

				cell = row.createCell(7);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getZsno());

				cell = row.createCell(8);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getStop());

				cell = row.createCell(9);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getPlqf());

				cell = row.createCell(10);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getChan());

				cell = row.createCell(11);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getJbno());

				cell = row.createCell(12);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, (mx.getZpzl() != null ? NumberUtils.format(mx.getZpzl() / 1000d, 3)
						: null));

				cell = row.createCell(13);
				cell.setCellStyle(cellStyle);
				ExcelUtils.setCellValue(cell, mx.getKw());
			}
		}
	}
}
