package com.quanta.sino.ch.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.TsTp;

/**
 * <p>
 * 投诉查询条件
 * </p>
 * <p>
 * create: 2011-2-24 上午09:37:50
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class TsQE extends QEntity<TsTp> {

	/**
	 * 出货日
	 */
	@QF
	private Date chri;
	/**
	 * 投诉日
	 */
	@QF
	private Date tsri;
	/**
	 * 制品号
	 */
	@QF
	private String jbno;

	/**
	 * 装箱指示书号
	 */
	@QF(alias = "shno")
	private String zxno;

	/**
	 * 不等于状态
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 等于状态
	 */
	@QF
	private String stat;

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public Date getTsri() {
		return tsri;
	}

	public void setTsri(Date tsri) {
		this.tsri = tsri;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
