package com.quanta.sino.etl.vo;

import com.coco.core.util.NumberUtils;

/**
 * <p>
 * 按班统计ETL产量实体类。
 * </p>
 * <p>
 * create: 2011-12-15 上午09:21:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlBanTjVO {
	/**
	 * 组
	 */
	private String zu;
	/**
	 * 生产量
	 */
	private double zpzl;
	/**
	 * 总装入量
	 */
	private double zrzlAll;
	/**
	 * SL材总装入量
	 */
	private double zrzlS;
	/**
	 * OR材总装入量
	 */
	private double zrzlOR;
	/**
	 * 外卖材总装入量
	 */
	private double zrzlC;
	/**
	 * SL材合格产出量
	 */
	private double hgzpzlS;
	/**
	 * 外卖材合格产出量
	 */
	private double hgzpzlC;
	/**
	 * SL材合格产出量步留
	 */
	private double hgblS;
	/**
	 * 外卖材合格产出量步留
	 */
	private double hgblC;
	/**
	 * SL材发生品（2－4）产出量
	 */
	private double fszpzlS;
	/**
	 * 外卖材发生品（2－4）产出量
	 */
	private double fszpzlC;
	/**
	 * SL材废材（S）产出量
	 */
	private double fczpzlS;
	/**
	 * 外卖材废材（S）产出量
	 */
	private double fczpzlC;

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public double getZpzl() {
		return zpzl;
	}

	public void setZpzl(double zpzl) {
		this.zpzl = zpzl;
	}

	public double getZrzlAll() {
		return zrzlAll;
	}

	public void setZrzlAll(double zrzlAll) {
		this.zrzlAll = zrzlAll;
	}

	public double getZrzlS() {
		return zrzlS;
	}

	public void setZrzlS(double zrzlS) {
		this.zrzlS = zrzlS;
	}

	public double getZrzlOR() {
		return zrzlOR;
	}

	public void setZrzlOR(double zrzlOR) {
		this.zrzlOR = zrzlOR;
	}

	public double getZrzlC() {
		return zrzlC;
	}

	public void setZrzlC(double zrzlC) {
		this.zrzlC = zrzlC;
	}

	public double getHgzpzlS() {
		return hgzpzlS;
	}

	public void setHgzpzlS(double hgzpzlS) {
		this.hgzpzlS = hgzpzlS;
	}

	public double getHgzpzlC() {
		return hgzpzlC;
	}

	public void setHgzpzlC(double hgzpzlC) {
		this.hgzpzlC = hgzpzlC;
	}

	public double getHgblS() {
		double _zrzlS = this.zrzlS > 0d ? this.zrzlS : 1d;
		this.hgblS = NumberUtils.format(this.hgzpzlS / _zrzlS * 100, 1);
		return hgblS;
	}

	public void setHgblS(double hgblS) {
		this.hgblS = hgblS;
	}

	public double getHgblC() {
		double _zrzlC = this.zrzlC > 0d ? this.zrzlC : 1d;
		this.hgblC = NumberUtils.format(this.hgzpzlC / _zrzlC * 100, 1);
		return hgblC;
	}

	public void setHgblC(double hgblC) {
		this.hgblC = hgblC;
	}

	public double getFszpzlS() {
		return fszpzlS;
	}

	public void setFszpzlS(double fszpzlS) {
		this.fszpzlS = fszpzlS;
	}

	public double getFszpzlC() {
		return fszpzlC;
	}

	public void setFszpzlC(double fszpzlC) {
		this.fszpzlC = fszpzlC;
	}

	public double getFczpzlS() {
		return fczpzlS;
	}

	public void setFczpzlS(double fczpzlS) {
		this.fczpzlS = fczpzlS;
	}

	public double getFczpzlC() {
		return fczpzlC;
	}

	public void setFczpzlC(double fczpzlC) {
		this.fczpzlC = fczpzlC;
	}
}
