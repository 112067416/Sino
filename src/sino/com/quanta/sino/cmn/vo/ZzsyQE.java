package com.quanta.sino.cmn.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 制造仕样查询配置
 * </p>
 * <p>
 * create: 2011-2-11 下午02:54:19
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Q
public class ZzsyQE extends QEntity<ZzsyMp> {

	@QF
	private String syno;

	@QF
	private String ggno;

	/**
	 * @return the syno
	 */
	public String getSyno() {
		return syno;
	}

	/**
	 * @param syno
	 *            the syno to set
	 */
	public void setSyno(String syno) {
		this.syno = syno;
	}

	/**
	 * @return the ggno
	 */
	public String getGgno() {
		return ggno;
	}

	/**
	 * @param ggno
	 *            the ggno to set
	 */
	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

}
