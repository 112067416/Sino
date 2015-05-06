package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.orm.SczsTp;

/**
 * <p>
 * 生产指示单查询条件类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:26:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class SczsQE extends QEntity<SczsTp> {

	/**
	 * 查询出货开始时间段
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询出货结束时间段
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaEnd;

	/**
	 * 担当者代码
	 */
	@QF
	private String ddno;

	/**
	 * 业务员
	 */
	@QF
	private String ddnm;

	/**
	 * 订货号
	 */
	@QF
	private String dhno;

	/**
	 * 订货行号
	 */
	@QF
	private String line;

	/**
	 * 用户名称
	 */
	@QF(alias = "name", operator = EO.LIKE)
	private String name;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * <p>
	 * 获取吨数即总量的合计值
	 * </p>
	 * 
	 * @return Double 返回合计值
	 */
	public Double getYscls() {
		double yscls = 0.0;
		for (SczsTp entity : getDatas()) {
			yscls += entity.getYscl();
		}
		return NumberUtils.format(yscls, 3);
	}

	public Date getCreaBegin() {
		return creaBegin;
	}

	public void setCreaBegin(Date creaBegin) {
		this.creaBegin = creaBegin;
	}

	public Date getCreaEnd() {
		return creaEnd;
	}

	public void setCreaEnd(Date creaEnd) {
		this.creaEnd = creaEnd;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
