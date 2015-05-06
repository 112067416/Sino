/**
 * 
 */
package com.quanta.sino.sl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * SL实绩查询体
 * </p>
 * <p>
 * create: 2011-1-27 下午04:16:55
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZpngTp.class))
public class SlsjQE extends QEntity<SlsjVO> {

	/**
	 * <p>
	 * 生产线别
	 * </p>
	 */
	@QF
	private String slin;

	/**
	 * <p>
	 * 班
	 * </p>
	 */
	@QF
	private String ban;

	/**
	 * <p>
	 * 组
	 * </p>
	 */
	@QF
	private String zu;

	/**
	 * <p>
	 * 指示书号
	 * </p>
	 */
	@QF(operator = EO.LIKE)
	private String zsno;
	/**
	 * <p>
	 * 不是堆场
	 * </p>
	 */
	@QF(alias = "pssd", operator = EO.NULL)
	private boolean ispssd;
	/**
	 * <p>
	 * 卷板号
	 * </p>
	 */
	@QF(operator = EO.LIKE)
	private String jbno;

	/**
	 * <p>
	 * 作成时间-开始
	 * </p>
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * <p>
	 * 作成时间-结束
	 * </p>
	 */
	@QF(alias = "crea", operator = EO.LE, addDays = 1)
	private Date crea_end;

	/**
	 * <p>
	 * 现品状况【默认为制品】
	 * </p>
	 */
	@QF
	private String xpzk = EXpzk.BZP_KEY;

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
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

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public boolean isIspssd() {
		return ispssd;
	}

	public void setIspssd(boolean ispssd) {
		this.ispssd = ispssd;
	}

}
