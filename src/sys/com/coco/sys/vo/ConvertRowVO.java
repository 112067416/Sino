package com.coco.sys.vo;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * <p>
 * 转换成对象信息
 * </p>
 * <p>
 * create: 2010-12-31 上午10:18:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ConvertRowVO<T> {

	/**
	 * 返回对象
	 */
	private T vo;

	/**
	 * 对象所在的行号
	 */
	private int rowIndex;

	/**
	 * 若为Excel转换则表示对象所在的行
	 */
	private HSSFRow row;

	/**
	 * 抛出异常
	 */
	private List<ConvertErrorVO> errors;

	public ConvertRowVO(T vo, int rowIndex, List<ConvertErrorVO> errors) {
		this.vo = vo;
		this.rowIndex = rowIndex;
		this.errors = errors;
	}

	public ConvertRowVO(T vo, int rowIndex, HSSFRow row,
			List<ConvertErrorVO> errors) {
		this.vo = vo;
		this.rowIndex = rowIndex;
		this.row = row;
		this.errors = errors;
	}

	/**
	 * @return the vo
	 */
	public T getVo() {
		return vo;
	}

	/**
	 * @param vo
	 *            the vo to set
	 */
	public void setVo(T vo) {
		this.vo = vo;
	}

	/**
	 * @return the rowIndex
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex
	 *            the rowIndex to set
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return the row
	 */
	public HSSFRow getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(HSSFRow row) {
		this.row = row;
	}

	/**
	 * @return the errors
	 */
	public List<ConvertErrorVO> getErrors() {
		return errors;
	}

}
