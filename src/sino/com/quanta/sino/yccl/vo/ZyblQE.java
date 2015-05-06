package com.quanta.sino.yccl.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 原材与制品联合查询
 * </p>
 * <p>
 * create: 2011-1-13 下午05:26:29
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Q
public class ZyblQE extends QEntity<ZpngTp> {

	/**
	 * 卷板NO
	 */
	@QF(operator = EO.LIKE)
	private String jbno;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

}
