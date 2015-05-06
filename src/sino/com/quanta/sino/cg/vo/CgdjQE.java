package com.quanta.sino.cg.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YbCgdj;

/**
 * <p>
 * 原板采购单价查询条件
 * </p>
 * <p>
 * create: 2011-7-8 下午04:12:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class CgdjQE extends QEntity<YbCgdj> {

	/**
	 * 
	 */
	@QF(alias = "xpho", operator = EO.GE)
	private Double xphoS;

	/**
	 * 
	 */
	@QF(alias = "xpho", operator = EO.LE)
	private Double xphoE;

	/**
	 * 
	 */
	@QF(alias = "xpkn", operator = EO.GE)
	private Double xpknS;

	/**
	 * 
	 */
	@QF(alias = "xpkn", operator = EO.LE)
	private Double xpknE;

	/**
	 * 
	 */
	@QF
	private String yuny;

	@QF
	private String knfw;

	public Double getXphoS() {
		return xphoS;
	}

	public void setXphoS(Double xphoS) {
		this.xphoS = xphoS;
	}

	public Double getXphoE() {
		return xphoE;
	}

	public void setXphoE(Double xphoE) {
		this.xphoE = xphoE;
	}

	public Double getXpknS() {
		return xpknS;
	}

	public void setXpknS(Double xpknS) {
		this.xpknS = xpknS;
	}

	public Double getXpknE() {
		return xpknE;
	}

	public void setXpknE(Double xpknE) {
		this.xpknE = xpknE;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getKnfw() {
		return knfw;
	}

	public void setKnfw(String knfw) {
		this.knfw = knfw;
	}

}
