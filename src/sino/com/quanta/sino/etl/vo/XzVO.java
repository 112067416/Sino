package com.quanta.sino.etl.vo;


/**
 * <p>
 * 统计休止实体类
 * </p>
 * <p>
 * create: 2011-12-14 下午01:21:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class XzVO {
	/**
	 * 休止I
	 */
	private Double xzda;

	/**
	 * 休止II操业时间
	 */
	private Double xzzy;

	/**
	 * 休止II机械
	 */
	private Double xzjx;
	/**
	 * 休止II电气
	 */
	private Double xzdj;

	public Double getXzda() {
		return xzda;
	}

	public void setXzda(Double xzda) {
		this.xzda = xzda;
	}

	public Double getXzzy() {
		return xzzy;
	}

	public void setXzzy(Double xzzy) {
		this.xzzy = xzzy;
	}

	public Double getXzjx() {
		return xzjx;
	}

	public void setXzjx(Double xzjx) {
		this.xzjx = xzjx;
	}

	public Double getXzdj() {
		return xzdj;
	}

	public void setXzdj(Double xzdj) {
		this.xzdj = xzdj;
	}
}
