/**
 * 
 */
package com.quanta.sino.sl.vo;

/**
 * <p>
 * 入端卷板VO【母卷板终了、中止、卷厚】——母卷完成情况
 * </p>
 * <p>
 * create: 2011-1-19 下午05:25:26
 * </p>
 * 
 * @author 方元龙[YuangLong.F@gmail.com]
 * @version 1.0
 */
public class SjzkVO {

	/**
	 * 实绩终了
	 */
	private String zl;

	/**
	 * 实绩中止
	 */
	private String zz;

	/**
	 * 卷厚
	 */
	private Integer jh;

	/**
	 * 硬度
	 */
	private Integer ying;

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public Integer getJh() {
		return jh;
	}

	public void setJh(Integer jh) {
		this.jh = jh;
	}

}
