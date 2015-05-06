package com.quanta.sino.ch.vo;

import java.util.Date;
import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZxzsTp;

/**
 * <p>
 * 装箱指示书查询条件
 * </p>
 * <p>
 * create: 2011-2-23 上午11:57:51
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ZxzsQE extends QEntity<ZxzsTp> {

	/**
	 * 出货日期
	 */
	@QF(alias = "chri")
	private Date chri;

	/**
	 * 出货开始日
	 */
	@QF(alias = "chri", operator = EO.GE)
	private Date chriS;

	/**
	 * 出货结束日
	 */
	@QF(alias = "chri", operator = EO.LT, addDays = 1)
	private Date chriE;

	/**
	 * 装箱指示书开始日
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaS;

	/**
	 * 装箱指示书结束日
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaE;

	/**
	 * 装箱指示书NO
	 */
	@QF(alias = "zxno")
	private String zxno;

	/**
	 * 装箱指示书NO
	 */
	@QF(alias = "zxno", operator = EO.IN)
	private List<String> zxnos;

	/**
	 * 状态
	 */
	@QF(alias = "stat")
	private String stat;

	/**
	 * 状态
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 用户代码
	 */
	@QF
	private String user;

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getChriS() {
		return chriS;
	}

	public void setChriS(Date chriS) {
		this.chriS = chriS;
	}

	public Date getChriE() {
		return chriE;
	}

	public void setChriE(Date chriE) {
		this.chriE = chriE;
	}

	public Date getCreaS() {
		return creaS;
	}

	public void setCreaS(Date creaS) {
		this.creaS = creaS;
	}

	public Date getCreaE() {
		return creaE;
	}

	public void setCreaE(Date creaE) {
		this.creaE = creaE;
	}

	public List<String> getZxnos() {
		return zxnos;
	}

	public void setZxnos(List<String> zxnos) {
		this.zxnos = zxnos;
	}

}
