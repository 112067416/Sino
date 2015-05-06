package com.quanta.sino.fxs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.FxzyRz;

/**
 * <p>
 * 分析作业日志查询
 * </p>
 * <p>
 * create: 2011-1-7 下午04:35:46
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Q
public class FxzyQE extends QEntity<FxzyRz> {

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "tbrq", operator = EO.GE)
	private Date tbsjBegin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "tbrq", operator = EO.LT, addDays = 1)
	private Date tbsjEnd;

	public Date getTbsjBegin() {
		return tbsjBegin;
	}

	public void setTbsjBegin(Date tbsjBegin) {
		this.tbsjBegin = tbsjBegin;
	}

	public Date getTbsjEnd() {
		return tbsjEnd;
	}

	public void setTbsjEnd(Date tbsjEnd) {
		this.tbsjEnd = tbsjEnd;
	}

}
