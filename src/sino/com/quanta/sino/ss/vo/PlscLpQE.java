/**
 * 
 */
package com.quanta.sino.ss.vo;

import java.util.Date;
import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.PlscLp;
import com.quanta.sino.orm.Xpfmt;

/**
 * <p>
 * 制品在库表查询条件
 * </p>
 * <p>
 * create: 2011-1-13 下午02:35:52
 * </p>
 * 
 * @author 张红国[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(entities = { @QE(clazz = PlscLp.class, alias = "a"),
		@QE(clazz = Xpfmt.class, alias = "b") }, where = "a.xpfmt=b.id ")
public class PlscLpQE extends QEntity<SsxpVO> {

	/**
	 * 生成时间起始
	 */
	@QF(alias = "a.crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 生成时间终止
	 */
	@QF(alias = "a.crea", operator = EO.LT, addDays = 1)
	private Date creaEnd;

	/**
	 * 班
	 */
	@QF(alias = "a.ban")
	private String ban;

	/**
	 * 组
	 */
	@QF(alias = "a.zu")
	private String zu;

	/**
	 * 分选生成新制品号
	 */
	@QF(alias = "b.jbn", operator = EO.EQ)
	private String jbno;

	/**
	 * 打包记录ID
	 */
	@QF(alias = "a.id", operator = EO.IN)
	private List<String> ids;

	public Date getCreaBegin() {
		return creaBegin;
	}

	public void setCreaBegin(Date creaBegin) {
		this.creaBegin = creaBegin;
	}

	public Date getCreaEnd() {
		return creaEnd;
	}

	public void setCreaEnd(Date creaEnd) {
		this.creaEnd = creaEnd;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
