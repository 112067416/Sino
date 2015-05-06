package com.quanta.sino.fxs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Cyrz;

/**
 * <p>
 * 采样日志查询条件
 * </p>
 * <p>
 * create: 2011-5-23 上午10:40:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class CyrzQE extends QEntity<Cyrz> {

	@QF
	private boolean deleted;

	@QF
	private String zsno;

	@QF
	private String jbno;

	@QF
	private String id;

	@QF(alias = "date", operator = EO.GE)
	private Date dateBegin;

	@QF(alias = "date", operator = EO.LT, addDays = 1)
	private Date dateEnd;

	@QF
	private String stat;

	@QF(alias = "jsxb", operator = EO.IN)
	private String[] jsxbs;

	@QF(alias = "fsxb", operator = EO.EQ)
	private String fsxb;

	@QF(alias = "fsxb", operator = EO.IN)
	private String[] fsxbs;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String[] getJsxbs() {
		return jsxbs;
	}

	public void setJsxbs(String[] jsxbs) {
		this.jsxbs = jsxbs;
	}

	public String getFsxb() {
		return fsxb;
	}

	public void setFsxb(String fsxb) {
		this.fsxb = fsxb;
	}

	public String[] getFsxbs() {
		return fsxbs;
	}

	public void setFsxbs(String[] fsxbs) {
		this.fsxbs = fsxbs;
	}

}
