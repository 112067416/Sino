package com.quanta.sino.ss.assist;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.ss.vo.SsItemVO;
import com.quanta.sino.ss.vo.SsRzVO;
import com.quanta.sino.ss.vo.SsVO;

/**
 * <p>
 * 日志Excel处理器
 * </p>
 * <p>
 * create: 2011-1-28 下午04:36:45
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class RizhiExcelDataExecuter implements ExcelbookDataExecuter<SsRzVO> {

	private SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy年MM月dd日");

	// private SimpleDateFormat sdfHm = new SimpleDateFormat("HH:mm");

	@Override
	public void execute(HSSFWorkbook book, SsRzVO data) {
		if (data.getDatas() == null || data.getDatas().isEmpty()) {
			return;
		}
		// 调用excel文档中第一个sheet
		HSSFSheet sheet = book.getSheetAt(0);
		this.executeSheet0(sheet, data);
		// 调用excel文档中第二个sheet
		sheet = book.getSheetAt(1);
		this.executeSheet1(sheet, data);
	}

	/**
	 * <p>
	 * 对分选日记报表中第一个sheet的赋值
	 * </p>
	 * 
	 * @param sheet
	 *            excel表的第一个sheet
	 * @param data
	 *            需要显示的数据内容
	 */
	public void executeSheet0(HSSFSheet sheet, SsRzVO data) {

		HSSFRow row, childRow;
		int rowIndex = 5, rownum;
		int minRows = 3, minRowIndex;
		String pileNo = "";
		String zppileNo = "";
		String fugm = "";
		row = sheet.getRow(0);
		HSSFCell titleCell = row.getCell(0);
		String title = ExcelUtils.getCellValue(titleCell);
		ExcelUtils.setCellValue(titleCell, title
				+ StringUtils.concat(sdfYmd.format(data.getCrea_begin() != null ? data.getCrea_begin()
						: new Date()), "至", sdfYmd.format(data.getCrea_end() != null ? data.getCrea_end()
						: data.getCrea_begin()), data.getBan(), " 班  ( ", data.getZu(), " 组 ) No.    "));

		for (SsVO vo : data.getDatas()) {
			pileNo = "";
			zppileNo = "";
			fugm = "";
			// 第一行
			row = sheet.createRow((rownum = rowIndex++));
			ExcelUtils.setCellValue(row.createCell(0), vo.getClq());
			ExcelUtils.setCellValue(row.createCell(1), vo.getCrea());
			ExcelUtils.setCellValue(row.createCell(2), vo.getAbbr());
			childRow = null;
			minRowIndex = 1;
			for (SsItemVO item : vo.getItems()) {
				if (childRow == null) {
					childRow = row;
				}
				else {
					childRow = sheet.createRow(rowIndex++);
					minRowIndex++;
				}
				if (item.getPlqf() != null) {
					pileNo = item.getPlqf();

				}
				if (vo.getZzsd() != null) {
					pileNo = pileNo + vo.getZzsd();
				}
				if (vo.getJbno() != null) {
					pileNo = pileNo + "-" + item.getJbno();
				}
				ExcelUtils.setCellValue(childRow.createCell(3), pileNo);
				ExcelUtils.setCellValue(childRow.createCell(4), item.getZshu());
			}
			for (int i = minRowIndex; i < minRows; i++) {
				sheet.createRow(rowIndex++);
			}
			if (vo.getPlq() != null) {
				zppileNo = vo.getPlq();

			}
			if (vo.getZzsd() != null) {
				zppileNo = zppileNo + vo.getZzsd();
			}
			if (vo.getJbno() != null) {
				zppileNo = zppileNo + "-" + vo.getJbno();
			}
			ExcelUtils.setCellValue(row.createCell(5), zppileNo);
			ExcelUtils.setCellValue(row.createCell(6), vo.getFace());
			ExcelUtils.setCellValue(row.createCell(7), vo.getHouu());
			ExcelUtils.setCellValue(row.createCell(8), vo.getKuan());
			ExcelUtils.setCellValue(row.createCell(9), vo.getCang());
			ExcelUtils.setCellValue(row.createCell(10), vo.getJdcn());
			ExcelUtils.setCellValue(row.createCell(11), vo.getYuny());
			if (vo.getFuzm() != null) {
				fugm = fugm + vo.getFuzm();
			}
			if (vo.getFufm() != null) {
				fugm = fugm + "/" + vo.getFuzm();
			}
			ExcelUtils.setCellValue(row.createCell(12), fugm);
			row.createCell(13);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 12, 13));
			String kpns = "";
			for (String kpn : vo.getKpns()) {
				kpns = kpns + kpn + " ";
			}
			ExcelUtils.setCellValue(row.createCell(14), kpns);
			row.createCell(15);
			row.createCell(16);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 14, 16));
			String ylnos = "";
			for (String yln : vo.getYlns()) {
				ylnos = ylnos + yln + " ";
			}
			ExcelUtils.setCellValue(row.createCell(17), ylnos);
			row.createCell(18);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 17, 18));

			// 第二行
			row = sheet.getRow(rownum + 1);
			ExcelUtils.setCellValue(row.createCell(5), "");
			ExcelUtils.setCellValue(row.createCell(6), "");
			ExcelUtils.setCellValue(row.createCell(7), vo.getJszl());
			ExcelUtils.setCellValue(row.createCell(8), "");
			ExcelUtils.setCellValue(row.createCell(9), vo.getSjzl());
			ExcelUtils.setCellValue(row.createCell(10), "");
			ExcelUtils.setCellValue(row.createCell(11), "");
			row.createCell(12);
			row.createCell(13);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 1, rownum + 1,
					12, 13));
			ExcelUtils.setCellValue(row.createCell(14), vo.getChan());
			ExcelUtils.setCellValue(row.createCell(15), vo.getDeng());
			row.createCell(16);
			ExcelUtils.setCellValue(row.createCell(17), vo.getQqdm());
			row.createCell(18);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 1, rownum + 1,
					17, 18));

			// 第三行
			row = sheet.getRow(rownum + 2);
			ExcelUtils.setCellValue(row.createCell(5), vo.getCkno() != null
					&& vo.getCkno() > 0 ? vo.getCkno() : null);
			ExcelUtils.setCellValue(row.createCell(6), vo.getVariable());
			row.createCell(7);
			row.createCell(8);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 2, rownum + 2,
					6, 8));
			row.createCell(9);
			ExcelUtils.setCellValue(row.createCell(10), vo.getZshu());
			ExcelUtils.setCellValue(row.createCell(11), vo.getSffz());
			ExcelUtils.setCellValue(row.createCell(12), vo.getJdyn());
			ExcelUtils.setCellValue(row.createCell(13), vo.getJsyn());
			ExcelUtils.setCellValue(row.createCell(14), vo.getTeji());
			row.createCell(15);
			row.createCell(16);
			row.createCell(17);
			row.createCell(18);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 2, rownum + 2,
					14, 18));
		}
	}

	/**
	 * <p>
	 * 对分选日记报表中第二个sheet的赋值
	 * </p>
	 * 
	 * @param sheet
	 *            excel表的第er个sheet
	 * @param data
	 *            需要显示的数据内容
	 */
	public void executeSheet1(HSSFSheet sheet, SsRzVO data) {

		HSSFRow row, childRow;
		int rowIndex = 3, rownum;
		int minRows = 1, minRowIndex;
		String pileNo = "";
		String zppileNo = "";
		String fugm = "";
		row = sheet.getRow(0);
		HSSFCell titleCell = row.getCell(0);
		String title = ExcelUtils.getCellValue(titleCell);
		ExcelUtils.setCellValue(titleCell, title
				+ StringUtils.concat(sdfYmd.format(data.getCrea_begin() != null ? data.getCrea_begin()
						: new Date()), "至", sdfYmd.format(data.getCrea_end() != null ? data.getCrea_end()
						: data.getCrea_begin()), data.getBan(), " 班  ( ", data.getZu(), " 组 ) No.    "));

		for (SsVO vo : data.getDatas()) {
			pileNo = "";
			zppileNo = "";
			fugm = "";
			// 第一行
			row = sheet.createRow((rownum = rowIndex++));
			ExcelUtils.setCellValue(row.createCell(0), vo.getClq());
			ExcelUtils.setCellValue(row.createCell(1), vo.getCrea());
			ExcelUtils.setCellValue(row.createCell(2), vo.getAbbr());
			childRow = null;
			minRowIndex = 1;
			for (SsItemVO item : vo.getItems()) {
				if (childRow == null) {
					childRow = row;
				}
				else {
					childRow = sheet.createRow(rowIndex++);
					minRowIndex++;
				}
				if (item.getPlqf() != null) {
					pileNo = item.getPlqf();

				}
				if (vo.getZzsd() != null) {
					pileNo = pileNo + vo.getZzsd();
				}
				if (vo.getJbno() != null) {
					pileNo = pileNo + "-" + item.getJbno();
				}
				ExcelUtils.setCellValue(childRow.createCell(3), pileNo);
				ExcelUtils.setCellValue(childRow.createCell(4), item.getZshu());
			}
			for (int i = minRowIndex; i < minRows; i++) {
				sheet.createRow(rowIndex++);
			}
			if (vo.getPlq() != null) {
				zppileNo = vo.getPlq();

			}
			if (vo.getZzsd() != null) {
				zppileNo = zppileNo + vo.getZzsd();
			}
			if (vo.getJbno() != null) {
				zppileNo = zppileNo + "-" + vo.getJbno();
			}
			ExcelUtils.setCellValue(row.createCell(5), zppileNo);
			ExcelUtils.setCellValue(row.createCell(6), vo.getFace());
			ExcelUtils.setCellValue(row.createCell(7), vo.getHouu());
			ExcelUtils.setCellValue(row.createCell(8), vo.getKuan());
			ExcelUtils.setCellValue(row.createCell(9), vo.getCang());
			ExcelUtils.setCellValue(row.createCell(10), vo.getJdcn());
			ExcelUtils.setCellValue(row.createCell(11), vo.getYuny());
			if (vo.getFuzm() != null) {
				fugm = fugm + vo.getFuzm();
			}
			if (vo.getFufm() != null) {
				fugm = fugm + "/" + vo.getFuzm();
			}
			ExcelUtils.setCellValue(row.createCell(12), fugm);
			row.createCell(13);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 12, 13));
			String kpns = "";
			for (String kpn : vo.getKpns()) {
				kpns = kpns + kpn + " ";
			}
			ExcelUtils.setCellValue(row.createCell(14), kpns);
			row.createCell(15);
			row.createCell(16);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 14, 16));
			String ylnos = "";
			for (String yln : vo.getYlns()) {
				ylnos = ylnos + yln + " ";
			}
			ExcelUtils.setCellValue(row.createCell(17), ylnos);
			row.createCell(18);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 17, 18));

			ExcelUtils.setCellValue(row.createCell(19), vo.getJszl());
			ExcelUtils.setCellValue(row.createCell(20), vo.getSjzl());
			ExcelUtils.setCellValue(row.createCell(21), vo.getChan());
			ExcelUtils.setCellValue(row.createCell(22), vo.getDeng());
			ExcelUtils.setCellValue(row.createCell(23), vo.getQqdm());

			ExcelUtils.setCellValue(row.createCell(24), vo.getCkno() != null
					&& vo.getCkno() > 0 ? vo.getCkno() : null);
			ExcelUtils.setCellValue(row.createCell(25), vo.getVariable());
			row.createCell(26);
			row.createCell(27);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 2, rownum + 2,
					26, 27));
			ExcelUtils.setCellValue(row.createCell(28), vo.getZshu());
			ExcelUtils.setCellValue(row.createCell(29), vo.getSffz());
			ExcelUtils.setCellValue(row.createCell(30), vo.getJdyn());
			ExcelUtils.setCellValue(row.createCell(31), vo.getJsyn());
			ExcelUtils.setCellValue(row.createCell(32), vo.getTeji());
			row.createCell(33);
			row.createCell(34);
			row.createCell(35);
			row.createCell(36);
			sheet.addMergedRegion(new CellRangeAddress(rownum + 2, rownum + 2,
					32, 36));
		}
	}
}
