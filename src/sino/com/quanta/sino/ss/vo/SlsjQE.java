/**
 * 
 */
package com.quanta.sino.ss.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
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
	 * 班
	 * </p>
	 */
	@QF
	private String ban;
	/**
	 * <p>
	 * 堆场
	 * </p>
	 */
	@QF
	private String duic;
	/**
	 * <p>
	 * 进度标记.精整实绩-已精整实绩
	 * </p>
	 */
	@QF
	private String jdjs;
	/**
	 * <p>
	 * 进度标记.精整实绩-已精整指示
	 * </p>
	 */
	@QF
	private String jdjz;

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
	 * 分配余材
	 */
	@QF(alias = "fpyc", operator = EO.NE)
	private String neFpyc;

	public String getJdjs() {
		return jdjs;
	}

	public void setJdjs(String jdjs) {
		this.jdjs = jdjs;
	}

	public String getJdjz() {
		return jdjz;
	}

	public void setJdjz(String jdjz) {
		this.jdjz = jdjz;
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

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getNeFpyc() {
		return neFpyc;
	}

	public void setNeFpyc(String neFpyc) {
		this.neFpyc = neFpyc;
	}

}
