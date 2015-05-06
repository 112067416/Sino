package com.quanta.sino.ch.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jczms;

/**
 * <p>
 * 检查证明书查询条件
 * </p>
 * <p>
 * create: 2011-1-12 上午11:33:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class JczmsQE extends QEntity<Jczms> {

	/**
	 * 装箱指示书号
	 */
	@QF
	private String zsno;

	/**
	 * 出货日期
	 */
	@QF
	private Date chri;
	/**
	 * 订货NO
	 */
	@QF
	private String dhno;
	/**
	 * 行号
	 */
	@QF
	private String line;
	/**
	 * 用户代码
	 */
	@QF
	private String user;

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
