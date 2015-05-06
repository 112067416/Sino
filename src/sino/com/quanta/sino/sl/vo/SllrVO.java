/**
 * 
 */
package com.quanta.sino.sl.vo;

/**
 * <p>
 * SL实绩录入之前的准备VO
 * </p>
 * <p>
 * create: 2011-1-15 下午12:26:48
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SllrVO {

	/**
	 * <p>
	 * 剪切生产线别
	 * </p>
	 */
	private String slin;

	/**
	 * <p>
	 * 指示书号
	 * </p>
	 */
	private String zsno;

	/**
	 * <p>
	 * 入侧卷号
	 * </p>
	 */
	private String rczpno;

	public SllrVO() {
		super();
	}

	public SllrVO(String slin, String zsno, String rcjbno) {
		super();
		this.slin = slin;
		this.zsno = zsno;
		this.rczpno = rcjbno;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

}
