package com.quanta.sino.ch.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ThTp;

/**
 * <p>
 * 退货记录查询条件
 * </p>
 * <p>
 * create: 2011-2-24 下午02:29:22
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ThQE extends QEntity<ThTp> {

	/**
	 * 出货日
	 */
	@QF(alias = "tsTp.chri")
	private Date chri;

	/**
	 * 退货日
	 */
	@QF
	private Date thri;

	/**
	 * 装箱指示书号
	 */
	@QF(alias = "tsTp.shno")
	private String zxno;

	/**
	 * 制品号
	 */
	@QF(alias = "tsTp.jbno")
	private String jbno;

	/**
	 * 发票状态
	 */
	@QF
	private String fpcz;

	/**
	 * 退货记录
	 */
	@QF
	private String zt;

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public Date getThri() {
		return thri;
	}

	public void setThri(Date thri) {
		this.thri = thri;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getFpcz() {
		return fpcz;
	}

	public void setFpcz(String fpcz) {
		this.fpcz = fpcz;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
