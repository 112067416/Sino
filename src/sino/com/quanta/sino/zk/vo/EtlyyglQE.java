package com.quanta.sino.zk.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlyygljlItem;

/**
 * <p>
 * 药液管理记录查询
 * </p>
 * <p>
 * create: 2011-4-30 下午09:17:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class EtlyyglQE extends QEntity<EtlyygljlItem> {

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "rq", operator = EO.GE)
	private Date rqBegin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "rq", operator = EO.LE, addDays = 1)
	private Date rqEnd;

	public Date getRqBegin() {
		return rqBegin;
	}

	public void setRqBegin(Date rqBegin) {
		this.rqBegin = rqBegin;
	}

	public Date getRqEnd() {
		return rqEnd;
	}

	public void setRqEnd(Date rqEnd) {
		this.rqEnd = rqEnd;
	}

}
