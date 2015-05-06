package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jbdd;

/**
 * <p>
 * 基板订单主表查询条件类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:26:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class JbddQE extends QEntity<Jbdd> {

	/**
	 * 基板订单编号
	 */
	@QF(operator = EO.LIKE)
	private String jbno;

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaBnd;

	/**
	 * 担当者
	 */
	@QF(operator = EO.LIKE)
	private String ddnm;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Date getCreaBegin() {
		return creaBegin;
	}

	public void setCreaBegin(Date creaBegin) {
		this.creaBegin = creaBegin;
	}

	public Date getCreaBnd() {
		return creaBnd;
	}

	public void setCreaBnd(Date creaBnd) {
		this.creaBnd = creaBnd;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

}
