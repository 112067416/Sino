package com.quanta.sino.yszk.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khfk;

/**
 * <p>
 * 客户付款查询条件
 * </p>
 * <p>
 * create: 2011-7-4 下午01:03:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class KhfkQE extends QEntity<Khfk> {

	/**
	 * 查询交货期开始时间段
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询交货期结束时间段
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaEnd;

	/**
	 * 客户名称
	 */
	@QF
	private String name;

	/**
	 * 状态
	 */
	@QF
	private String stat;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
