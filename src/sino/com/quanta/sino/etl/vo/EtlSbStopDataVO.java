package com.quanta.sino.etl.vo;

/**
 * <p>
 * etl速报报表填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:41:56
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSbStopDataVO {

	/**
	 * 停线时间
	 */
	private String stop;
	/**
	 * 班
	 */
	private String ban;
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
	/**
	 * 停线特记
	 */
	private String vari;

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
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

	public String getVari() {
		return vari;
	}

	public void setVari(String vari) {
		this.vari = vari;
	}
}
