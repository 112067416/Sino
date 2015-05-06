package com.quanta.sino.ch.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;

/**
 * <p>
 * 装箱指示书打印
 * </p>
 * <p>
 * create: 2011-1-11 下午03:23:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(entities = { @QE(clazz = Zng2Tp.class, alias = "a"),
		@QE(clazz = Zng1Tp.class, alias = "b") }, where = "a.zng1Tp.id=b.id")
public class ZxzsDyQE extends QEntity<Zng2Tp> {

	/**
	 * 装箱指示书号
	 */
	@QF(alias = "b.zxzsTp.zxno")
	private String zxno;

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	/**
	 * <p>
	 * 获取制品重量和
	 * </p>
	 * 
	 * @return Double
	 */
	public Integer getHjzls() {
		int hjzls = 0;
		for (Zng2Tp tp : getDatas()) {
			hjzls += tp.getZpzl() == null ? 0 : tp.getZpzl();
		}
		return hjzls;
	}

	/**
	 * <p>
	 * 统计包数
	 * </p>
	 * 
	 * @return Integer
	 */
	public Integer getHjbs() {
		int hjbs = getDatas().size();
		return hjbs;
	}

}
