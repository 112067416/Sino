package com.quanta.sino.zkfp.vo;

import java.util.List;

/**
 * <p>
 * 分配用参考LIST(现品)
 * </p>
 * <p>
 * create: 2011-3-12 下午05:30:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpckVO {

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 产量代码
	 */
	private String chan;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 起始厚
	 */
	private Double xphoS;

	/**
	 * 结束厚
	 */
	private Double xphoE;

	/**
	 * 起始宽
	 */
	private Double xpknS;

	/**
	 * 结束宽
	 */
	private Double xpknE;

	/**
	 * 起始长
	 */
	private Double xpcnS;

	/**
	 * 结束长
	 */
	private Double xpcnE;

	/**
	 * 开始现品NO
	 */
	private String jbnoS;

	/**
	 * 结束现品NO
	 */
	private String jbnoE;

	/**
	 * 品种
	 */
	private String pz;

	/**
	 * 
	 */
	private String ycbr;

	private List<FpckItemVO> mxs;

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getXphoS() {
		return xphoS;
	}

	public void setXphoS(Double xphoS) {
		this.xphoS = xphoS;
	}

	public Double getXphoE() {
		return xphoE;
	}

	public void setXphoE(Double xphoE) {
		this.xphoE = xphoE;
	}

	public Double getXpknS() {
		return xpknS;
	}

	public void setXpknS(Double xpknS) {
		this.xpknS = xpknS;
	}

	public Double getXpknE() {
		return xpknE;
	}

	public void setXpknE(Double xpknE) {
		this.xpknE = xpknE;
	}

	public Double getXpcnS() {
		return xpcnS;
	}

	public void setXpcnS(Double xpcnS) {
		this.xpcnS = xpcnS;
	}

	public Double getXpcnE() {
		return xpcnE;
	}

	public void setXpcnE(Double xpcnE) {
		this.xpcnE = xpcnE;
	}

	public String getJbnoS() {
		return jbnoS;
	}

	public void setJbnoS(String jbnoS) {
		this.jbnoS = jbnoS;
	}

	public String getJbnoE() {
		return jbnoE;
	}

	public void setJbnoE(String jbnoE) {
		this.jbnoE = jbnoE;
	}

	public List<FpckItemVO> getMxs() {
		return mxs;
	}

	public void setMxs(List<FpckItemVO> mxs) {
		this.mxs = mxs;
	}

	public String getPz() {
		return pz;
	}

	public void setPz(String pz) {
		this.pz = pz;
	}

	public String getYcbr() {
		return ycbr;
	}

	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

}
