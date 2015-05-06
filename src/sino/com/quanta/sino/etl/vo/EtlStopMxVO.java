package com.quanta.sino.etl.vo;

/**
 * <p>
 * 生产线停线明细信息实体类
 * </p>
 * <p>
 * create: 2011-12-9 上午11:39:06
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlStopMxVO {
	/**
	 * ID
	 */
	private String id;

	/**
	 * 停线时间
	 */
	private String stop;

	/**
	 * 休止I
	 */
	private Double xzda;
	/**
	 * 休止II操业
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
	/**
	 * 停线理由
	 */
	private String remk;

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

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

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
