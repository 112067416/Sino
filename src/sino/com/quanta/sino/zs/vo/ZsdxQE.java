/**
 * 
 */
package com.quanta.sino.zs.vo;

import java.util.Date;
import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZsdxTp;

/**
 * <p>
 * 指示对象查询条件
 * </p>
 * <p>
 * create: 2011-2-13 下午05:12:28
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ZsdxQE extends QEntity<ZsdxTp> {

	/**
	 * 分配No.
	 */
	@QF
	private String fpno;

	/**
	 * 现品NO.
	 */
	@QF(alias = "jbno", operator = EO.IN)
	private List<String> jbnos;

	/**
	 * 分配开始时间
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaB;

	/**
	 * 分配结束时间
	 */
	@QF(alias = "crea", operator = EO.LE, addDays = 1)
	private Date creaE;

	/**
	 * 订货No.
	 */
	@QF
	private String dhno;

	/**
	 * 现品No.
	 */
	@QF
	private String jbno;

	/**
	 * 开始现品No.
	 */
	@QF(alias = "jbno", operator = EO.GE)
	private String jbnoStart;

	/**
	 * 结束现品No.
	 */
	@QF(alias = "jbno", operator = EO.LE)
	private String jbnoEnd;

	/**
	 * 分配No.
	 */
	@QF(alias = "fpno", operator = EO.NE)
	private String neFpno;

	/**
	 * 强制标记
	 */
	@QF
	private String qzbj;

	/**
	 * 是否配匹
	 */
	@QF
	private String sfpp;

	/**
	 * 分配指示对象状态
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neZsdxStat;

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public List<String> getJbnos() {
		return jbnos;
	}

	public void setJbnos(List<String> jbnos) {
		this.jbnos = jbnos;
	}

	public Date getCreaB() {
		return creaB;
	}

	public void setCreaB(Date creaB) {
		this.creaB = creaB;
	}

	public Date getCreaE() {
		return creaE;
	}

	public void setCreaE(Date creaE) {
		this.creaE = creaE;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getJbnoStart() {
		return jbnoStart;
	}

	public void setJbnoStart(String jbnoStart) {
		this.jbnoStart = jbnoStart;
	}

	public String getJbnoEnd() {
		return jbnoEnd;
	}

	public void setJbnoEnd(String jbnoEnd) {
		this.jbnoEnd = jbnoEnd;
	}

	public String getNeFpno() {
		return neFpno;
	}

	public void setNeFpno(String neFpno) {
		this.neFpno = neFpno;
	}

	public String getQzbj() {
		return qzbj;
	}

	public void setQzbj(String qzbj) {
		this.qzbj = qzbj;
	}

	public String getSfpp() {
		return sfpp;
	}

	public void setSfpp(String sfpp) {
		this.sfpp = sfpp;
	}

	public String getNeZsdxStat() {
		return neZsdxStat;
	}

	public void setNeZsdxStat(String neZsdxStat) {
		this.neZsdxStat = neZsdxStat;
	}

}
