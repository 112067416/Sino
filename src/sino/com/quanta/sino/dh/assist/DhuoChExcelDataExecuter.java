package com.quanta.sino.dh.assist;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.DhuoChmxVO;

/**
 * <p>
 * 订货出货对比表处理器
 * </p>
 * <p>
 * create: 2011-12-15 下午08:48:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhuoChExcelDataExecuter implements ExcelbookDataExecuter<DhuoChVO> {

	@Override
	public void execute(HSSFWorkbook workbook, DhuoChVO vo) {
		List<DhuoChmxVO> datas = vo.getDatas();
		if (datas == null || datas.size() == 0) {
			return;
		}
		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFDataFormat format = workbook.createDataFormat();

		HSSFCellStyle numberStyle;
		numberStyle = workbook.createCellStyle();
		numberStyle.setWrapText(true);
		numberStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		numberStyle.setDataFormat(format.getFormat("#,##0.000"));

		HSSFRow row;
		HSSFCell cell;
		int rowIndex = 3;
		for (DhuoChmxVO item : datas) {
			row = sheet.createRow((rowIndex++));

			cell = row.createCell(0);
			ExcelUtils.setCellValue(cell, item.getName());

			cell = row.createCell(1);
			ExcelUtils.setCellValue(cell, item.getDhno() + item.getLine());

			cell = row.createCell(2);
			ExcelUtils.setCellValue(cell, item.getPzno());

			cell = row.createCell(3);
			ExcelUtils.setCellValue(cell, item.getYuny());

			cell = row.createCell(4);
			ExcelUtils.setCellValue(cell, item.getGgno());

			cell = row.createCell(5);
			ExcelUtils.setCellValue(cell, item.getCdnm());

			cell = row.createCell(6);
			ExcelUtils.setCellValue(cell, item.getFudw());

			cell = row.createCell(7);
			ExcelUtils.setCellValue(cell, item.getFuzm());

			cell = row.createCell(8);
			ExcelUtils.setCellValue(cell, item.getFufm());

			cell = row.createCell(9);
			ExcelUtils.setCellValue(cell, item.getHouu());

			cell = row.createCell(10);
			ExcelUtils.setCellValue(cell, item.getKuan());

			cell = row.createCell(11);
			ExcelUtils.setCellValue(cell, item.getCang() != null
					&& item.getCang() > 0 ? item.getCang() : "COIL");

			cell = row.createCell(12);
			ExcelUtils.setCellValue(cell, item.getQixn());

			cell = row.createCell(13);
			ExcelUtils.setCellValue(cell, item.getDdnm());

			cell = row.createCell(14);
			ExcelUtils.setCellValue(cell, item.getFace());

			cell = row.createCell(15);
			ExcelUtils.setCellValue(cell, item.getHtdj());

			cell = row.createCell(16);
			cell.setCellStyle(numberStyle);
			ExcelUtils.setCellValue(cell, item.getHtzl());

			cell = row.createCell(17);
			cell.setCellStyle(numberStyle);
			ExcelUtils.setCellValue(cell, item.getChzl());

			cell = row.createCell(18);
			cell.setCellStyle(numberStyle);
			ExcelUtils.setCellValue(cell, item.getCxzl());

		}
	}
}
