package com.quanta.sino.fxs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.MktfxshJl;

/**
 * <p>
 * 马口铁分析记录查询
 * </p>
 * <p>
 * create: 2010-12-31 上午10:14:41
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class MktfxQE extends QEntity<MktfxshJl> {

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "fxr", operator = EO.GE)
	private Date fxr_begin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "fxr", operator = EO.LT, addDays = 1)
	private Date fxr_end;

	/**
	 * 卷板No.
	 */
	@QF
	private String jbno;

	public Date getFxr_begin() {
		return fxr_begin;
	}

	public void setFxr_begin(Date fxr_begin) {
		this.fxr_begin = fxr_begin;
	}

	public Date getFxr_end() {
		return fxr_end;
	}

	public void setFxr_end(Date fxr_end) {
		this.fxr_end = fxr_end;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

}
