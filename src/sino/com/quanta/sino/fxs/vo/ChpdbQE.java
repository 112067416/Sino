package com.quanta.sino.fxs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Chpdb;

/**
 * <p>
 * 高耐蚀性马口铁出荷判定表(#75以上)记录查询
 * </p>
 * <p>
 * create: 2011-8-26 上午11:32:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ChpdbQE extends QEntity<Chpdb> {

	/**
	 * 卷板No.
	 */
	@QF
	private String jbno;

	/**
	 * 卷板No.
	 */
	@QF(alias = "jbno", operator = EO.IN)
	private String[] jbnos;

	/**
	 * 查询作成日期开始时间段
	 */
	@QF(alias = "cjsj", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询作成日期结束时间段
	 */
	@QF(alias = "cjsj", operator = EO.LT, addDays = 1)
	private Date creaEnd;

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

	public Date getCreaEnd() {
		return creaEnd;
	}

	public void setCreaEnd(Date creaEnd) {
		this.creaEnd = creaEnd;
	}

	public String[] getJbnos() {
		return jbnos;
	}

	public void setJbnos(String[] jbnos) {
		this.jbnos = jbnos;
	}

}
