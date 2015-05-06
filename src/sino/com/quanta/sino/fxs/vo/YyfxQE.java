package com.quanta.sino.fxs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YyfxjgJl;

/**
 * <p>
 * 药液分析结果记录查询
 * </p>
 * <p>
 * create: 2011-1-4 下午07:33:10
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Q
public class YyfxQE extends QEntity<YyfxjgJl> {

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "rq", operator = EO.GE)
	private Date rqBegin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "rq", operator = EO.LT, addDays = 1)
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
