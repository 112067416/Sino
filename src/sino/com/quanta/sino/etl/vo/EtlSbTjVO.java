package com.quanta.sino.etl.vo;

/**
 * <p>
 * ETL速报统计实体类
 * </p>
 * <p>
 * create: 2011-12-15 下午01:28:58
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlSbTjVO {

	/**
	 * 计划生产量
	 */
	private Double jszl;

	/**
	 * 操业时间
	 */
	private Double gzda;

	/**
	 * 实动时间
	 */
	private Double sdsj;

	/**
	 * 休止I
	 */
	private Double xzda;

	/**
	 * 2pass时间
	 */
	private Double pass;

	public Double getJszl() {
		return jszl;
	}

	public void setJszl(Double jszl) {
		this.jszl = jszl;
	}

	public Double getGzda() {
		return gzda;
	}

	public void setGzda(Double gzda) {
		this.gzda = gzda;
	}

	public Double getSdsj() {
		return sdsj;
	}

	public void setSdsj(Double sdsj) {
		this.sdsj = sdsj;
	}

	public Double getXzda() {
		return xzda;
	}

	public void setXzda(Double xzda) {
		this.xzda = xzda;
	}

	public Double getPass() {
		return pass;
	}

	public void setPass(Double pass) {
		this.pass = pass;
	}

}
