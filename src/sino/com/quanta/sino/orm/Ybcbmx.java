package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 原材成本费用明细管理
 * </p>
 * <p>
 * create: 2011-1-25 下午04:37:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_YBCBMX")
public class Ybcbmx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 船名
	 */
	@Id
	@Column(name = "SHIP_", length = 20)
	private String ship;

	/**
	 * 卸船码头
	 */
	@Column(name = "XCMT_", length = 32)
	private String xcmt;

	/**
	 * 港杂税率（每吨价格）
	 */
	@Column(name = "GZSL_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double gzsl;

	/**
	 * 运输公司（码头到中日达）
	 */
	@Column(name = "YSGS_", length = 32)
	private String ysgs;

	/**
	 * 运输税率（每吨价格）
	 */
	@Column(name = "YSSL_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double yssl;

	/**
	 * 保费汇率
	 */
	@Column(name = "BFHL_", columnDefinition = "numeric(10,8)", scale = 10, precision = 8)
	private Double bfhl;

	/**
	 * 美元汇率
	 */
	@Column(name = "MYHL_", columnDefinition = "numeric(8,6)", scale = 8, precision = 6)
	private Double myhl;

	/**
	 * 进口报关报检费
	 */
	@Column(name = "BGBJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double bgbj;

	/**
	 * 进口换小提单费
	 */
	@Column(name = "HXTD_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double hxtd;

	/**
	 * 进口检验检疫费
	 */
	@Column(name = "JYJY_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double jyjy;

	/**
	 * 重量鉴定费
	 */
	@Column(name = "ZLJD_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double zljd;

	/**
	 * 过磅费
	 */
	@Column(name = "GDF_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double gdf;

	/**
	 * 海关加价
	 */
	@Column(name = "HGJJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double hgjj;

	/**
	 * 海关基价
	 */
	@Column(name = "HGBJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double hgbj;

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getXcmt() {
		return xcmt;
	}

	public void setXcmt(String xcmt) {
		this.xcmt = xcmt;
	}

	public Double getGzsl() {
		return gzsl;
	}

	public void setGzsl(Double gzsl) {
		this.gzsl = gzsl;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	public Double getYssl() {
		return yssl;
	}

	public void setYssl(Double yssl) {
		this.yssl = yssl;
	}

	public Double getBfhl() {
		return bfhl;
	}

	public void setBfhl(Double bfhl) {
		this.bfhl = bfhl;
	}

	public Double getMyhl() {
		return myhl;
	}

	public void setMyhl(Double myhl) {
		this.myhl = myhl;
	}

	public Double getBgbj() {
		return bgbj;
	}

	public void setBgbj(Double bgbj) {
		this.bgbj = bgbj;
	}

	public Double getHxtd() {
		return hxtd;
	}

	public void setHxtd(Double hxtd) {
		this.hxtd = hxtd;
	}

	public Double getJyjy() {
		return jyjy;
	}

	public void setJyjy(Double jyjy) {
		this.jyjy = jyjy;
	}

	public Double getZljd() {
		return zljd;
	}

	public void setZljd(Double zljd) {
		this.zljd = zljd;
	}

	public Double getGdf() {
		return gdf;
	}

	public void setGdf(Double gdf) {
		this.gdf = gdf;
	}

	public Double getHgjj() {
		return hgjj;
	}

	public void setHgjj(Double hgjj) {
		this.hgjj = hgjj;
	}

	public Double getHgbj() {
		return hgbj;
	}

	public void setHgbj(Double hgbj) {
		this.hgbj = hgbj;
	}

}
