/**
 * 
 */
package com.quanta.sino.yd.vo;

/**
 * <p>
 * 硬度维护值对象
 * </p>
 * <p>
 * create: 2011-1-28 下午03:04:56
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class GxVO {

	/**
	 * 卷板号
	 */
	private String jbno;

	/**
	 * 硬度
	 */
	private Integer ying;

	/**
	 * 检定员
	 */
	private String jdyn;

	/**
	 * 硬度下限
	 */
	private String ymin;

	/**
	 * 硬度上限
	 */
	private String ymax;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getJdyn() {
		return jdyn;
	}

	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

}
