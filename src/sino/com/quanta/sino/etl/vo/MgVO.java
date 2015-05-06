package com.quanta.sino.etl.vo;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 木工vo
 * </p>
 * <p>
 * create: 2011-1-26 下午03:21:04
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class MgVO {
	/**
	 * 指示号
	 */
	@QF
	private String zsno;
	/**
	 * 操作员
	 */
	@QF
	private String dann;
	/**
	 * 制品尺寸.厚
	 */
	@QF
	private Double houu;

	/**
	 * 制品尺寸.宽
	 */
	@QF
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@QF
	private Double cang;
	/**
	 * 库存个数
	 */
	@QF
	private Integer dmgs;
	/**
	 * 新增个数
	 */
	@QF
	private Integer adddmgs;
	/**
	 * 垫木方向
	 */
	@QF
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@QF
	private String kbao;
	/**
	 * 库位
	 */
	@QF
	private String kw;
	/**
	 * 班
	 */
	@QF
	private String ban;
	/**
	 * 组
	 */
	@QF
	private String zu;

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public Integer getDmgs() {
		return dmgs;
	}

	public void setDmgs(Integer dmgs) {
		this.dmgs = dmgs;
	}

	public Integer getAdddmgs() {
		return adddmgs;
	}

	public void setAdddmgs(Integer adddmgs) {
		this.adddmgs = adddmgs;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getDann() {
		return dann;
	}

	public void setDann(String dann) {
		this.dann = dann;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

}
