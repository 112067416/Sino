package com.quanta.sino.yygl.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khyfdj;

/**
 * <p>
 * 客户运费单价查询条件
 * </p>
 * <p>
 * create: 2011-2-14 下午02:27:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class KhyfdjQE extends QEntity<Khyfdj> {

	/**
	 * 用户代码
	 */
	@QF
	private String user;

	/**
	 * 运输行
	 */
	@QF
	private String ysnm;

	/**
	 * 运输方式
	 */
	@QF
	private String ysfs;

	/**
	 * 运费单价状态
	 */
	@QF
	private String stat;

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the ysnm
	 */
	public String getYsnm() {
		return ysnm;
	}

	/**
	 * @param ysnm
	 *            the ysnm to set
	 */
	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	/**
	 * @return the ysfs
	 */
	public String getYsfs() {
		return ysfs;
	}

	/**
	 * @param ysfs
	 *            the ysfs to set
	 */
	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

}
