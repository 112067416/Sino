package com.coco.sys.vo;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * 转换结果信息
 * </p>
 * <p>
 * create: 2010-12-31 上午10:18:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ConvertVO<T> {

	/**
	 * 对象列表
	 */
	private List<T> vos;

	/**
	 * 若为Excel流转换，则存入Excel工作薄对象
	 */
	private HSSFWorkbook wb;

	/**
	 * 若为文本流转换，则存入文本字符串
	 */
	private List<String> lines;

	/**
	 * 若为其他流转换，则存入对应的对象
	 */
	private Object other;

	public ConvertVO() {
	}

	/**
	 * @return the vos
	 */
	public List<T> getVos() {
		return vos;
	}

	/**
	 * @param vos
	 *            the vos to set
	 */
	public void setVos(List<T> vos) {
		this.vos = vos;
	}

	/**
	 * @return the wb
	 */
	public HSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * @param wb
	 *            the wb to set
	 */
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	 * @return the other
	 */
	public Object getOther() {
		return other;
	}

	/**
	 * @param other
	 *            the other to set
	 */
	public void setOther(Object other) {
		this.other = other;
	}

	/**
	 * @return the lines
	 */
	public List<String> getLines() {
		return lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public void setLines(List<String> lines) {
		this.lines = lines;
	}

}
