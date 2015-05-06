package com.quanta.sino.ch.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ChuhLp;

@Q
public class ChuhQE extends QEntity<ChuhLp> {

	/**
	 * 出货日起始
	 */
	@QF(alias = "chri", operator = EO.GE)
	private Date chriS;

	/**
	 * 出货日结束
	 */
	@QF(alias = "chri", operator = EO.LT, addDays = 1)
	private Date chriE;

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

}
