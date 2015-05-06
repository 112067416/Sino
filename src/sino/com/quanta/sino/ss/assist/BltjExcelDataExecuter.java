package com.quanta.sino.ss.assist;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.coco.core.excel.api.ExcelDataExecuter;
import com.coco.core.util.ExcelUtils;
import com.quanta.sino.ss.vo.SsBlVO;
import com.quanta.sino.ss.vo.SsBlVO.CountItem;

/**
 * <p>
 * 步留统计Excel处理器
 * </p>
 * <p>
 * create: 2011-1-28 下午04:36:45
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class BltjExcelDataExecuter implements ExcelDataExecuter<SsBlVO> {

	private final static Map<String, Integer> CELL_NAME_NUM = new HashMap<String, Integer>();

	static {
		int cellnum = 2;
		CELL_NAME_NUM.put("A0", cellnum++);
		CELL_NAME_NUM.put("A1", cellnum++);
		CELL_NAME_NUM.put("A2", cellnum++);
		CELL_NAME_NUM.put("A3", cellnum++);
		CELL_NAME_NUM.put("A4", cellnum++);
		CELL_NAME_NUM.put("A5", cellnum++);
		CELL_NAME_NUM.put("A6", cellnum++);
		CELL_NAME_NUM.put("A7", cellnum++);
		CELL_NAME_NUM.put("A8", cellnum++);
		CELL_NAME_NUM.put("A9", cellnum++);
		CELL_NAME_NUM.put("B0", cellnum++);
		CELL_NAME_NUM.put("B1", cellnum++);
		CELL_NAME_NUM.put("B2", cellnum++);
		CELL_NAME_NUM.put("B3", cellnum++);
		CELL_NAME_NUM.put("B4", cellnum++);
		CELL_NAME_NUM.put("B5", cellnum++);
		CELL_NAME_NUM.put("B6", cellnum++);
		CELL_NAME_NUM.put("B7", cellnum++);
		CELL_NAME_NUM.put("B8", cellnum++);
		CELL_NAME_NUM.put("B9", cellnum++);
		CELL_NAME_NUM.put("C0", cellnum++);
		CELL_NAME_NUM.put("C1", cellnum++);
		CELL_NAME_NUM.put("C2", cellnum++);
		CELL_NAME_NUM.put("C3", cellnum++);
		CELL_NAME_NUM.put("C4", cellnum++);
		CELL_NAME_NUM.put("C5", cellnum++);
		CELL_NAME_NUM.put("C6", cellnum++);
		CELL_NAME_NUM.put("C7", cellnum++);
		CELL_NAME_NUM.put("C8", cellnum++);
		CELL_NAME_NUM.put("C9", cellnum++);
		CELL_NAME_NUM.put("D0", cellnum++);
		CELL_NAME_NUM.put("D1", cellnum++);
		CELL_NAME_NUM.put("D2", cellnum++);
		CELL_NAME_NUM.put("D3", cellnum++);
		CELL_NAME_NUM.put("D4", cellnum++);
		CELL_NAME_NUM.put("D5", cellnum++);
		CELL_NAME_NUM.put("D6", cellnum++);
		CELL_NAME_NUM.put("D7", cellnum++);
		CELL_NAME_NUM.put("D8", cellnum++);
		CELL_NAME_NUM.put("D9", cellnum++);
		CELL_NAME_NUM.put("Q0", cellnum++);
		CELL_NAME_NUM.put("Q1", cellnum++);
		CELL_NAME_NUM.put("Q2", cellnum++);
		CELL_NAME_NUM.put("Q3", cellnum++);
		CELL_NAME_NUM.put("Q4", cellnum++);
		CELL_NAME_NUM.put("Q5", cellnum++);
		CELL_NAME_NUM.put("Q6", cellnum++);
		CELL_NAME_NUM.put("Q7", cellnum++);
		CELL_NAME_NUM.put("Q8", cellnum++);
		CELL_NAME_NUM.put("Q9", cellnum++);
		CELL_NAME_NUM.put("E0", cellnum++);
		CELL_NAME_NUM.put("E1", cellnum++);
		CELL_NAME_NUM.put("E2", cellnum++);
		CELL_NAME_NUM.put("E3", cellnum++);
		CELL_NAME_NUM.put("E4", cellnum++);
		CELL_NAME_NUM.put("E5", cellnum++);
		CELL_NAME_NUM.put("E6", cellnum++);
		CELL_NAME_NUM.put("E7", cellnum++);
		CELL_NAME_NUM.put("E8", cellnum++);
		CELL_NAME_NUM.put("E9", cellnum++);
		CELL_NAME_NUM.put("F0", cellnum++);
		CELL_NAME_NUM.put("F1", cellnum++);
		CELL_NAME_NUM.put("F2", cellnum++);
		CELL_NAME_NUM.put("F3", cellnum++);
		CELL_NAME_NUM.put("F4", cellnum++);
		CELL_NAME_NUM.put("F5", cellnum++);
		CELL_NAME_NUM.put("F6", cellnum++);
		CELL_NAME_NUM.put("F7", cellnum++);
		CELL_NAME_NUM.put("F8", cellnum++);
		CELL_NAME_NUM.put("F9", cellnum++);
		CELL_NAME_NUM.put("G0", cellnum++);
		CELL_NAME_NUM.put("G1", cellnum++);
		CELL_NAME_NUM.put("G2", cellnum++);
		CELL_NAME_NUM.put("G3", cellnum++);
		CELL_NAME_NUM.put("G4", cellnum++);
		CELL_NAME_NUM.put("G5", cellnum++);
		CELL_NAME_NUM.put("G6", cellnum++);
		CELL_NAME_NUM.put("G7", cellnum++);
		CELL_NAME_NUM.put("G8", cellnum++);
		CELL_NAME_NUM.put("G9", cellnum++);
		CELL_NAME_NUM.put("H0", cellnum++);
		CELL_NAME_NUM.put("H1", cellnum++);
		CELL_NAME_NUM.put("H2", cellnum++);
		CELL_NAME_NUM.put("H3", cellnum++);
		CELL_NAME_NUM.put("H4", cellnum++);
		CELL_NAME_NUM.put("H5", cellnum++);
		CELL_NAME_NUM.put("H6", cellnum++);
		CELL_NAME_NUM.put("H7", cellnum++);
		CELL_NAME_NUM.put("H8", cellnum++);
		CELL_NAME_NUM.put("H9", cellnum++);
		CELL_NAME_NUM.put("J0", cellnum++);
		CELL_NAME_NUM.put("J1", cellnum++);
		CELL_NAME_NUM.put("J2", cellnum++);
		CELL_NAME_NUM.put("J3", cellnum++);
		CELL_NAME_NUM.put("J4", cellnum++);
		CELL_NAME_NUM.put("J5", cellnum++);
		CELL_NAME_NUM.put("J6", cellnum++);
		CELL_NAME_NUM.put("J7", cellnum++);
		CELL_NAME_NUM.put("J8", cellnum++);
		CELL_NAME_NUM.put("J9", cellnum++);
		CELL_NAME_NUM.put("K1", cellnum++);
		CELL_NAME_NUM.put("K2", cellnum++);
		CELL_NAME_NUM.put("K3", cellnum++);
		CELL_NAME_NUM.put("K4", cellnum++);
		CELL_NAME_NUM.put("K5", cellnum++);
		CELL_NAME_NUM.put("K6", cellnum++);
		CELL_NAME_NUM.put("K7", cellnum++);
		CELL_NAME_NUM.put("K8", cellnum++);
		CELL_NAME_NUM.put("K9", cellnum++);
		CELL_NAME_NUM.put("L0", cellnum++);
		CELL_NAME_NUM.put("L1", cellnum++);
		CELL_NAME_NUM.put("L2", cellnum++);
		CELL_NAME_NUM.put("L3", cellnum++);
		CELL_NAME_NUM.put("L4", cellnum++);
		CELL_NAME_NUM.put("L5", cellnum++);
		CELL_NAME_NUM.put("L6", cellnum++);
		CELL_NAME_NUM.put("L7", cellnum++);
		CELL_NAME_NUM.put("L8", cellnum++);
		CELL_NAME_NUM.put("L9", cellnum++);
		CELL_NAME_NUM.put("M0", cellnum++);
		CELL_NAME_NUM.put("M1", cellnum++);
		CELL_NAME_NUM.put("M2", cellnum++);
		CELL_NAME_NUM.put("M3", cellnum++);
		CELL_NAME_NUM.put("M4", cellnum++);
		CELL_NAME_NUM.put("M5", cellnum++);
		CELL_NAME_NUM.put("M6", cellnum++);
		CELL_NAME_NUM.put("M7", cellnum++);
		CELL_NAME_NUM.put("M8", cellnum++);
		CELL_NAME_NUM.put("M9", cellnum++);
		CELL_NAME_NUM.put("N2", cellnum++);
		CELL_NAME_NUM.put("N3", cellnum++);
		CELL_NAME_NUM.put("N4", cellnum++);
		CELL_NAME_NUM.put("N5", cellnum++);
		CELL_NAME_NUM.put("N6", cellnum++);
		CELL_NAME_NUM.put("N7", cellnum++);
		CELL_NAME_NUM.put("N8", cellnum++);
		CELL_NAME_NUM.put("P2", cellnum++);
		CELL_NAME_NUM.put("P3", cellnum++);
		CELL_NAME_NUM.put("P4", cellnum++);
		CELL_NAME_NUM.put("P5", cellnum++);
		CELL_NAME_NUM.put("P6", cellnum++);
		CELL_NAME_NUM.put("P7", cellnum++);
		CELL_NAME_NUM.put("P8", cellnum++);
		CELL_NAME_NUM.put("P9", cellnum++);
	}

	@Override
	public void execute(HSSFSheet sheet, SsBlVO data) {
		if (data.getCounts() == null || data.getCounts().length == 0) {
			return;
		}
		HSSFRow row;
		int rowIndex = 4;
		Integer cellnum;
		Integer zshu;
		for (CountItem vo : data.getCounts()) {
			//最多限制35行
			if (rowIndex == 40) {
				break;
			}
			row = sheet.createRow((rowIndex++));
			ExcelUtils.setCellValue(row.createCell(0), vo.getDateLabel());
			ExcelUtils.setCellValue(row.createCell(1), vo.getZrzl() > 0 ? vo.getZrzl()
					: "");
			for (String qxdm : vo.getQxzses().keySet()) {
				cellnum = CELL_NAME_NUM.get(qxdm);
				if (cellnum == null) {
					continue;
				}
				zshu = vo.getQxzses().get(qxdm);
				ExcelUtils.setCellValue(row.createCell(cellnum), zshu != null
						&& zshu > 0 ? zshu : "");
			}
		}
		//合计行计算
		row = sheet.getRow(40);
		if (row != null) {
			HSSFCell cell;
			int maxCellnum = row.getLastCellNum();
			for (cellnum = 2; cellnum <= maxCellnum; cellnum++) {
				cell = row.getCell(cellnum);
				if (cell != null) {
					if (HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
						cell.setCellFormula(cell.getCellFormula());
					}
				}
			}
		}
	}

}
