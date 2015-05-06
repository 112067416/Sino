package com.quanta.sino.bbgl.vo;

import java.util.Date;

/**
 * <p>
 * 原材料仓库台帐vo
 * </p>
 * <p>
 * create: 2011-8-31 上午11:04:10
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YccktzVO {

	/**
	 * 
	 */
	private String id;

	/**
	 * 日期（年年年年月月）
	 */
	private String ny;

	/**
	 * 日期
	 */
	private Date riqi;

	/**
	 * 商品编号
	 */
	private String spbh;

	/**
	 * 购进总量
	 */
	private Long gjzl;

	/**
	 * 购进一般贸易
	 */
	private Long gjybmy;

	/**
	 * 购进保税
	 */
	private Long gjbs;

	/**
	 * 领用总量
	 */
	private Long lyzl;

	/**
	 * 领用一般贸易
	 */
	private Long lyybmy;

	/**
	 * 领用保税
	 */
	private Long lybs;

	/**
	 * 结存重量
	 */
	private Long jczl;

	/**
	 * 保税品结存
	 */
	private Long bspjc;

	/**
	 * 一般贸易结存
	 */
	private Long ybmyjc;

	/**
	 * 
	 */
	private YccktzVO[] items;

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public Long getGjzl() {
		return gjzl;
	}

	public void setGjzl(Long gjzl) {
		this.gjzl = gjzl;
	}

	public Long getGjybmy() {
		return gjybmy;
	}

	public void setGjybmy(Long gjybmy) {
		this.gjybmy = gjybmy;
	}

	public Long getGjbs() {
		return gjbs;
	}

	public void setGjbs(Long gjbs) {
		this.gjbs = gjbs;
	}

	public Long getLyzl() {
		return lyzl;
	}

	public void setLyzl(Long lyzl) {
		this.lyzl = lyzl;
	}

	public Long getLyybmy() {
		return lyybmy;
	}

	public void setLyybmy(Long lyybmy) {
		this.lyybmy = lyybmy;
	}

	public Long getLybs() {
		return lybs;
	}

	public void setLybs(Long lybs) {
		this.lybs = lybs;
	}

	public Long getJczl() {
		return jczl;
	}

	public void setJczl(Long jczl) {
		this.jczl = jczl;
	}

	public Long getBspjc() {
		return bspjc;
	}

	public void setBspjc(Long bspjc) {
		this.bspjc = bspjc;
	}

	public Long getYbmyjc() {
		return ybmyjc;
	}

	public void setYbmyjc(Long ybmyjc) {
		this.ybmyjc = ybmyjc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public YccktzVO[] getItems() {
		return items;
	}

	public void setItems(YccktzVO[] items) {
		this.items = items;
	}

}
