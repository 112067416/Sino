package com.quanta.sino.ch.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JczmsItem;

/**
 * <p>
 * 制品检查证明书明细表查询
 * </p>
 * <p>
 * create: 2011-1-13 上午09:58:37
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class JczmsItemQE extends QEntity<JczmsItem> {

	/**
	 * 装箱指示书No.
	 */
	@QF(alias = "jczms.zxno")
	private String zxno;

	/**
	 * 
	 */
	@QF(alias = "jczms.id")
	private String jczmsId;

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getJczmsId() {
		return jczmsId;
	}

	public void setJczmsId(String jczmsId) {
		this.jczmsId = jczmsId;
	}

}
