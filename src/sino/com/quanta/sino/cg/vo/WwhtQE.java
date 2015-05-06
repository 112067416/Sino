package com.quanta.sino.cg.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.WwhtTp;

/**
 * <p>
 * 采购合同查询组合主键
 * </p>
 * <p>
 * create: 2010-12-22 上午10:51:51
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q
public class WwhtQE extends QEntity<WwhtTp> {
	/**
	 * 合同NO
	 */
	@QF
	private String htno;

	/**
	 * 行号
	 */
	@QF
	private String line;

	public String getHtno() {
		return htno;
	}

	public void setHtno(String htno) {
		this.htno = htno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
