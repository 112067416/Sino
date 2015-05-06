package com.quanta.sino.dh.vo;

/**
 * <p>
 * 发生品特性VO
 * </p>
 * <p>
 * create: 2011-7-12 下午01:55:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FspTxVO {
	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 规格代码
	 */
	private String ggno;

	/**
	 * 尺寸.厚
	 */
	private Double xpho;

	/**
	 * 尺寸.宽
	 */
	private Double xpkn;

	/**
	 * 尺寸.长
	 */
	private Double xpcn;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 现品状况
	 */
	private String xpzk;

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public Double getXpcn() {
		return xpcn;
	}

	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

}
