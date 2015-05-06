package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khyf;

/**
 * <p>
 * 客户运费查询条件
 * </p>
 * <p>
 * create: 2011-2-13 下午07:26:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class KhyfQE extends QEntity<Khyf> {

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
	 * 出货重量
	 */
	@QF(alias = "chzl", operator = EO.GT)
	private Double chzl;

	/**
	 * 运输公司
	 */
	@QF
	private String ysgs;

	/**
	 * @return the chriS
	 */
	public Date getChriS() {
		return chriS;
	}

	/**
	 * @param chriS
	 *            the chriS to set
	 */
	public void setChriS(Date chriS) {
		this.chriS = chriS;
	}

	/**
	 * @return the chriE
	 */
	public Date getChriE() {
		return chriE;
	}

	/**
	 * @param chriE
	 *            the chriE to set
	 */
	public void setChriE(Date chriE) {
		this.chriE = chriE;
	}

	/**
	 * @return the chzl
	 */
	public Double getChzl() {
		return chzl;
	}

	/**
	 * @param chzl
	 *            the chzl to set
	 */
	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

}
