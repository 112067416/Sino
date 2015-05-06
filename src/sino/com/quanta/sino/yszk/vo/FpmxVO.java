package com.quanta.sino.yszk.vo;

/**
 * <p>
 * 发票明细信息
 * </p>
 * <p>
 * create: 2011-6-29 下午09:41:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpmxVO {

	/**
	 * 发票主键
	 */
	private String id;

	/**
	 * 发票状态
	 */
	private String stat;

	/**
	 * 发票单价
	 */
	private Double kpdj;

	/**
	 * 利息折扣
	 */
	private Double lxzr;

	/**
	 * 质量折扣
	 */
	private Double zlzr;

	/**
	 * 发票号
	 */
	private String fpno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Double getKpdj() {
		return kpdj;
	}

	public void setKpdj(Double kpdj) {
		this.kpdj = kpdj;
	}

	public Double getLxzr() {
		return lxzr;
	}

	public void setLxzr(Double lxzr) {
		this.lxzr = lxzr;
	}

	public Double getZlzr() {
		return zlzr;
	}

	public void setZlzr(Double zlzr) {
		this.zlzr = zlzr;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

}
