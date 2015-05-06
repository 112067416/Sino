package com.quanta.sino.zs.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdxTp;

/**
 * <p>
 * 剪切材查询视图
 * </p>
 * <p>
 * create: 2011-1-18 上午09:06:47
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Q(entities = { @QE(clazz = ZpngTp.class, alias = "a"),
		@QE(clazz = ZsdxTp.class, alias = "b") }, where = "a.jbno = b.jbno and a.fpno = b.fpno and a.dhno!=null")
public class JqcQE extends QEntity<JqcVO> {

	/**
	 * 指示对象的状态为:0-已分配未指示
	 */
	@QF(alias = "b.stat")
	private String stat = ZtConstants.ZSDX_STAT_WZS;

	/**
	 * 指示对象的余材状况为:B-中间品
	 */
	@QF(alias = "b.yczk")
	private String yczk = EXpzk.ZJP_KEY;

	/**
	 * 制品在库表中的订货合同号不为空
	 */
	@QF(alias = "a.dhno", operator = EO.NOT_NULL)
	private String dhno;
	/**
	 * 制品在库表中的镀锡付着量.正不为空
	 */
	@QF(alias = "a.sczm", operator = EO.NOT_NULL)
	private boolean sczm;
	/**
	 * 制品在库表中的镀锡付着量.反不为空
	 */
	@QF(alias = "a.scfm", operator = EO.NOT_NULL)
	private boolean scfm;

	/**
	 * 制品记录的现品状况为:B-中间品
	 */
	@QF(alias = "a.xpzk")
	private String xpzk = EXpzk.ZJP_KEY;

	/**
	 * 制品记录的分配/余材标记为:1-分配
	 */
	@QF(alias = "a.fpyc")
	private String fpyc = EFpyc.KEY_FP;

	/**
	 * 分配No.
	 */
	@QF(alias = "b.fpno")
	private String fpno;

	/**
	 * 分配指示对象状态
	 */
	@QF(alias = "b.stat", operator = EO.NE)
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

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public String getFpyc() {
		return fpyc;
	}

	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

	/**
	 * @return the fpno
	 */
	public String getFpno() {
		return fpno;
	}

	/**
	 * @param fpno
	 *            the fpno to set
	 */
	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public String getNeZsdxStat() {
		return neZsdxStat;
	}

	public void setNeZsdxStat(String neZsdxStat) {
		this.neZsdxStat = neZsdxStat;
	}

	public boolean getSczm() {
		return sczm;
	}

	public void setSczm(boolean sczm) {
		this.sczm = sczm;
	}

	public boolean getScfm() {
		return scfm;
	}

	public void setScfm(boolean scfm) {
		this.scfm = scfm;
	}

}