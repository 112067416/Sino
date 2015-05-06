package com.quanta.sino.yszk.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Czgl;

/**
 * <p>
 * 付款冲帐查询条件
 * </p>
 * <p>
 * create: 2011-7-4 下午01:01:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class CzglQE extends QEntity<Czgl> {

	/**
	 * 客户付款ID
	 */
	@QF(alias = "khfk.id")
	private String khfkId;

	public String getKhfkId() {
		return khfkId;
	}

	public void setKhfkId(String khfkId) {
		this.khfkId = khfkId;
	}

}
