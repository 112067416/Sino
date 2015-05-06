package com.quanta.sino.zk.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlpzGl;

/**
 * <p>
 * 品质管理记录查询
 * </p>
 * <p>
 * create: 2011-6-15 下午05:22:56
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class EtlpzglQE extends QEntity<EtlpzGl> {

	/**
	 * Coil No.
	 */
	@QF(alias = "jbno")
	private String jbno;

	/**
	 * 开始时间
	 */
	@QF(alias = "scsj", operator = EO.GE)
	private Date scsjBegin;

	/**
	 * 结束时间
	 */
	@QF(alias = "scsj", operator = EO.LT, addDays = 1)
	private Date scsjEnd;

	/**
	 * 状态
	 */
	@QF(alias = "stat")
	private String stat;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Date getScsjBegin() {
		return scsjBegin;
	}

	public void setScsjBegin(Date scsjBegin) {
		this.scsjBegin = scsjBegin;
	}

	public Date getScsjEnd() {
		return scsjEnd;
	}

	public void setScsjEnd(Date scsjEnd) {
		this.scsjEnd = scsjEnd;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
