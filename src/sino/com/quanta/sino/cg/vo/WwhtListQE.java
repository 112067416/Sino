package com.quanta.sino.cg.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.WwhtTp;

/**
 * <p>
 * 采购合同维护查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:44:17
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q
public class WwhtListQE extends QEntity<WwhtTp> {
	/**
	 * 合同NO
	 */
	@QF
	private String htno;

	/**
	 * 行号
	 */
	@QF
	private String line;

	/**
	 * 状态
	 */
	@QF
	private String qywl;

	/**
	 * 签约开始日期
	 */
	@QF(alias = "qyri", operator = EO.GE)
	private Date qyri_begin;

	/**
	 * 签约结束日期
	 */
	@QF(alias = "qyri", operator = EO.LE)
	private Date qyri_end;

	/**
	 * 制造商
	 */
	@QF
	private String zzsd;

	/**
	 * 合同开始日期
	 */
	@QF(alias = "htdt", operator = EO.GE)
	private Date htdt_begin;

	/**
	 * 合同结束日期
	 */
	@QF(alias = "htdt", operator = EO.LE)
	private Date htdt_end;

	/**
	 * 商社
	 */
	@QF
	private String ssno;

	public String getHtno() {
		return htno;
	}

	public void setHtno(String htno) {
		this.htno = htno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getQywl() {
		return qywl;
	}

	public void setQywl(String qywl) {
		this.qywl = qywl;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public Date getQyri_begin() {
		return qyri_begin;
	}

	public void setQyri_begin(Date qyri_begin) {
		this.qyri_begin = qyri_begin;
	}

	public Date getQyri_end() {
		return qyri_end;
	}

	public void setQyri_end(Date qyri_end) {
		this.qyri_end = qyri_end;
	}

	public Date getHtdt_begin() {
		return htdt_begin;
	}

	public void setHtdt_begin(Date htdt_begin) {
		this.htdt_begin = htdt_begin;
	}

	public Date getHtdt_end() {
		return htdt_end;
	}

	public void setHtdt_end(Date htdt_end) {
		this.htdt_end = htdt_end;
	}
}
