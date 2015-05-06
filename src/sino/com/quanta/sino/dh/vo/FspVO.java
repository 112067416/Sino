package com.quanta.sino.dh.vo;

/**
 * <p>
 * 发生品VO
 * </p>
 * <p>
 * create: 2011-7-10 下午08:54:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FspVO {

	/**
	 * 由运用规格、尺寸厚、尺寸宽、尺寸长和表面加工组成的主键
	 */
	private String id;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 规格代码
	 */
	private String ggno;

	/**
	 * 中日达规格略称
	 */
	private String ggnm;

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

	/**
	 * 保税重量
	 */
	private Integer bszl;

	/**
	 * 制品重量
	 */
	private Integer zpzl;

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

	public String getGgnm() {
		return ggnm;
	}

	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
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

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBszl() {
		return bszl;
	}

	public void setBszl(Integer bszl) {
		this.bszl = bszl;
	}

}
