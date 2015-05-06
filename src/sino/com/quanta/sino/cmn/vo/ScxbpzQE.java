package com.quanta.sino.cmn.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.orm.Scxbpz;

/**
 * <p>
 * 生产线别配置查询
 * </p>
 * <p>
 * create: 2011-2-12 上午10:06:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Q
public class ScxbpzQE extends QEntity<Scxbpz> {

	/**
	 * 默认查询ETL的配置
	 */
	@QF
	private String type = ProductType.etl.code;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
