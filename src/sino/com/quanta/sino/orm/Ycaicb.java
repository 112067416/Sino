package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 原材成本管理
 * </p>
 * <p>
 * create: 2011-1-25 下午04:37:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_YCAICB")
public class Ycaicb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 原材卷板No.
	 */
	@Id
	@Column(name = "JBNO_", length = 7)
	private String jbno;

	/**
	 * 船名
	 */
	@Column(name = "SHIP_", length = 20)
	private String ship;

	/**
	 * 数量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;

	/**
	 * 单价
	 */
	@Column(name = "DJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double dj;

	/**
	 * 原板价格
	 */
	@Column(name = "YBJG_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double ybjg;

	/**
	 * 卸船码头
	 */
	@Column(name = "XCMT_", length = 2)
	private String xcmt;

	/**
	 * 港杂税率（每吨价格）
	 */
	@Column(name = "GZSL_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double gzsl;

	/**
	 * 港杂费
	 */
	@Column(name = "GZF_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double gzf;

	/**
	 * 运输公司（码头到中日达）
	 */
	@Column(name = "YSGS_", length = 2)
	private String ysgs;

	/**
	 * 运输税率（每吨价格）
	 */
	@Column(name = "YSSL_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double yssl;

	/**
	 * 运输费
	 */
	@Column(name = "YSF_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double ysf;

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
	 * 保险费
	 */
	@Column(name = "BXF_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double bxf;

	/**
	 * 关税税率
	 */
	@Column(name = "GSSL_", columnDefinition = "numeric(8,6)", scale = 8, precision = 6)
	private Double gssl;

	/**
	 * 进口关税
	 */
	@Column(name = "JKGS_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double jkgs;

	/**
	 * 进口增值税
	 */
	@Column(name = "JKZZ_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double jkzz;

	/**
	 * 基础费用
	 */
	@Column(name = "JCFY_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double jcfy;

	/**
	 * 海关加价
	 */
	@Column(name = "HGJJ_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double hgjj;

	/**
	 * 海关基价
	 */
	@Column(name = "HGBJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double hgbj;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;
	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 16)
	private String ddnm;

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the zpzl
	 */
	public Integer getZpzl() {
		return zpzl;
	}

	/**
	 * @param zpzl
	 *            the zpzl to set
	 */
	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	/**
	 * @return the dj
	 */
	public Double getDj() {
		return dj;
	}

	/**
	 * @param dj
	 *            the dj to set
	 */
	public void setDj(Double dj) {
		this.dj = dj;
	}

	/**
	 * @return the xcmt
	 */
	public String getXcmt() {
		return xcmt;
	}

	/**
	 * @param xcmt
	 *            the xcmt to set
	 */
	public void setXcmt(String xcmt) {
		this.xcmt = xcmt;
	}

	/**
	 * @return the gzsl
	 */
	public Double getGzsl() {
		return gzsl;
	}

	/**
	 * @param gzsl
	 *            the gzsl to set
	 */
	public void setGzsl(Double gzsl) {
		this.gzsl = gzsl;
	}

	/**
	 * @return the gzf
	 */
	public Double getGzf() {
		return gzf;
	}

	/**
	 * @param gzf
	 *            the gzf to set
	 */
	public void setGzf(Double gzf) {
		this.gzf = gzf;
	}

	/**
	 * @return the ysgs
	 */
	public String getYsgs() {
		return ysgs;
	}

	/**
	 * @param ysgs
	 *            the ysgs to set
	 */
	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	/**
	 * @return the yssl
	 */
	public Double getYssl() {
		return yssl;
	}

	/**
	 * @param yssl
	 *            the yssl to set
	 */
	public void setYssl(Double yssl) {
		this.yssl = yssl;
	}

	/**
	 * @return the ysf
	 */
	public Double getYsf() {
		return ysf;
	}

	/**
	 * @param ysf
	 *            the ysf to set
	 */
	public void setYsf(Double ysf) {
		this.ysf = ysf;
	}

	/**
	 * @return the bfhl
	 */
	public Double getBfhl() {
		return bfhl;
	}

	/**
	 * @param bfhl
	 *            the bfhl to set
	 */
	public void setBfhl(Double bfhl) {
		this.bfhl = bfhl;
	}

	/**
	 * @return the myhl
	 */
	public Double getMyhl() {
		return myhl;
	}

	/**
	 * @param myhl
	 *            the myhl to set
	 */
	public void setMyhl(Double myhl) {
		this.myhl = myhl;
	}

	/**
	 * @return the bxf
	 */
	public Double getBxf() {
		return bxf;
	}

	/**
	 * @param bxf
	 *            the bxf to set
	 */
	public void setBxf(Double bxf) {
		this.bxf = bxf;
	}

	/**
	 * @return the gssl
	 */
	public Double getGssl() {
		return gssl;
	}

	/**
	 * @param gssl
	 *            the gssl to set
	 */
	public void setGssl(Double gssl) {
		this.gssl = gssl;
	}

	/**
	 * @return the jkgs
	 */
	public Double getJkgs() {
		return jkgs;
	}

	/**
	 * @param jkgs
	 *            the jkgs to set
	 */
	public void setJkgs(Double jkgs) {
		this.jkgs = jkgs;
	}

	/**
	 * @return the jkzz
	 */
	public Double getJkzz() {
		return jkzz;
	}

	/**
	 * @param jkzz
	 *            the jkzz to set
	 */
	public void setJkzz(Double jkzz) {
		this.jkzz = jkzz;
	}

	/**
	 * @return the jcfy
	 */
	public Double getJcfy() {
		return jcfy;
	}

	/**
	 * @param jcfy
	 *            the jcfy to set
	 */
	public void setJcfy(Double jcfy) {
		this.jcfy = jcfy;
	}

	/**
	 * @return the ship
	 */
	public String getShip() {
		return ship;
	}

	/**
	 * @param ship
	 *            the ship to set
	 */
	public void setShip(String ship) {
		this.ship = ship;
	}

	/**
	 * @return the ybjg
	 */
	public Double getYbjg() {
		return ybjg;
	}

	/**
	 * @param ybjg
	 *            the ybjg to set
	 */
	public void setYbjg(Double ybjg) {
		this.ybjg = ybjg;
	}

	/**
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the ddnm
	 */
	public String getDdnm() {
		return ddnm;
	}

	/**
	 * @param ddnm
	 *            the ddnm to set
	 */
	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
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
