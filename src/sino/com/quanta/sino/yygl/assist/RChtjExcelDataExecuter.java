package com.quanta.sino.yygl.assist;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.coco.core.excel.api.ExcelDataExecuter;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.yygl.vo.RChtjVO;

/**
 * <p>
 * 日出货统计Excel处理器
 * </p>
 * <p>
 * create: 2011-6-2 下午08:13:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class RChtjExcelDataExecuter implements ExcelDataExecuter<RChtjVO> {

	@Override
	public void execute(HSSFSheet sheet, RChtjVO data) {
		HSSFRow row;
		row = sheet.getRow(0);
		HSSFCell cell = row.getCell(0);
		Date chriS = data.getChriS();
		Date chriE = data.getChriE();
		String rq = "";
		if (chriS != null) {
			rq = DateUtils.format(chriS, "yyyy-MM-dd");
		}
		if (chriE != null) {
			rq = rq + "至" + DateUtils.format(chriE, "yyyy-MM-dd");
		}
		String title = rq + cell.getRichStringCellValue();
		HSSFRichTextString hrts = new HSSFRichTextString(title);
		cell.setCellValue(hrts);
		int rowIndex = 2;
		List<ChtjVO> vos = data.getChtjs();
		double chzl = 0d;
		if (vos != null && vos.size() > 0) {
			for (ChtjVO vo : vos) {
				if (vo.getChzl() == null) continue;

				chzl = NumberUtils.format((chzl + vo.getChzl()), 3);
				row = sheet.createRow(rowIndex++);
				ExcelUtils.setCellValue(row.createCell(0), DateUtils.format(vo.getChri(), "yyyy-MM-dd"));
				ExcelUtils.setCellValue(row.createCell(2), vo.getChzl());
				ExcelUtils.setCellValue(row.createCell(4), chzl);
			}
		}
	}
}
