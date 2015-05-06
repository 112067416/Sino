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
 * 指示书查询QE
 * </p>
 * <p>
 * create: 2011-1-18 上午09:07:53
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZsdhTp.class, alias = "a"))
public class ZsQE extends QEntity<ZsVO> {

	/**
	 * 状态(默认：0-未分派)
	 */
	@QF
	private String stat;

	/**
	 * 指示完成标记，即指示书中的所有卷是否实绩完成
	 */
	@QF
	private String zsbj;

	/**
	 * 余材状况(默认：2-中间品)
	 */
	@QF
	private String yczk;

	/**
	 * 指示书号
	 */
	@QF(operator = EO.LIKE)
	private String zsno;

	/**
	 * 订货合同号
	 */
	@QF(operator = EO.LIKE)
	private String dhno;

	/**
	 * 指示书作成日期
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * 指示书作成日期
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
	 * 指示书打印
	 */
	@QF
	private String zsfx;
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

	public String getZsfx() {
		return zsfx;
	}

	public void setZsfx(String zsfx) {
		this.zsfx = zsfx;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getZsbj() {
		return zsbj;
	}

	public void setZsbj(String zsbj) {
		this.zsbj = zsbj;
	}

	public String getYczk() {
		return yczk;
	}

	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
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
