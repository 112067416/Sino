package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZsdhTp;

/**
 * <p>
 * 指示书分派查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:44:17
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZsdhTp.class, alias = "a"))
public class FpListQE extends QEntity<ZsdhVO> {
	/**
	 * 指示书NO
	 */
	@QF(operator = EO.LIKE)
	private String zsno;
	/**
	 * 订货No.行号
	 */
	@QF(operator = EO.LIKE)
	private String dhno;
	/**
	 * 用户略称
	 */
	@QF(operator = EO.LIKE)
	private String abbr;

	/**
	 * 状态
	 */
	@QF
	private String stat;
	/**
	 * 指示完了标记
	 */
	@QF
	private String zsbj;
	/**
	 * 指示书打印
	 */
	@QF
	private String zsfx;
	/**
	 * 指示书缺陷打印
	 */
	@QF
	private String zsqx;
	/**
	 * 卷板NO
	 */
	@QF
	private String jbno;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZsqx() {
		return zsqx;
	}

	public void setZsqx(String zsqx) {
		this.zsqx = zsqx;
	}

	public String getZsfx() {
		return zsfx;
	}

	public void setZsfx(String zsfx) {
		this.zsfx = zsfx;
	}

	/**
	 * 作成时间开始日期
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * 作成时间结束日期
	 */
	@QF(alias = "crea", operator = EO.LE, addDays = 1)
	private Date crea_end;
	/**
	 * 完成时间开始日期
	 */
	@QF(alias = "zsny", operator = EO.GE)
	private Date zsny_begin;

	/**
	 * 完成时间结束日期
	 */
	@QF(alias = "zsny", operator = EO.LE, addDays = 1)
	private Date zsny_end;
	/**
	 * 余材状况
	 */
	@QF(alias = "yczk")
	private String yczk;

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getCrea_begin() {
		return crea_begin;
	}

	public void setCrea_begin(Date crea_begin) {
		this.crea_begin = crea_begin;
	}

	public Date getCrea_end() {
		return crea_end;
	}

	public void setCrea_end(Date crea_end) {
		this.crea_end = crea_end;
	}

	public String getZsbj() {
		return zsbj;
	}

	public void setZsbj(String zsbj) {
		this.zsbj = zsbj;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getYczk() {
		return yczk;
	}

	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	public Date getZsny_begin() {
		return zsny_begin;
	}

	public void setZsny_begin(Date zsny_begin) {
		this.zsny_begin = zsny_begin;
	}

	public Date getZsny_end() {
		return zsny_end;
	}

	public void setZsny_end(Date zsny_end) {
		this.zsny_end = zsny_end;
	}

}
