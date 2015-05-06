package com.quanta.sino.zs.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdxTp;

/**
 * <p>
 * 指标DB查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:44:17
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q(entities = { @QE(clazz = ZsdxTp.class, alias = "a"),
		@QE(clazz = YcaiTp.class, alias = "b") }, where = "a.jbno=b.jbno and a.fpno=b.fpno")
public class ZsListQE extends QEntity<ZsdxVO> {
	/**
	 * 状态
	 */
	@QF(alias = "a.stat")
	private String stat;

	/**
	 * 余材状况
	 */
	@QF(alias = "a.yczk")
	private String yczk;

	/**
	 * 分配No
	 */
	@QF(alias = "a.fpno")
	private String fpno;

	/**
	 * 分配指示对象状态
	 */
	@QF(alias = "a.stat", operator = EO.NE)
	private String neZsdxStat;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getYczk() {
		return yczk;
	}

	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public String getNeZsdxStat() {
		return neZsdxStat;
	}

	public void setNeZsdxStat(String neZsdxStat) {
		this.neZsdxStat = neZsdxStat;
	}

}
