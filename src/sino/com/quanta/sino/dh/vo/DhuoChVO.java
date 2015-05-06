package com.quanta.sino.dh.vo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 查询订货出货对比表实体类
 * </p>
 * <p>
 * create: 2011-12-15 下午08:34:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhuoChVO {

	/**
	 * 
	 */
	private Date jhqiB;

	/**
	 * 
	 */
	private Date jhqiE;

	/**
	 * 
	 */
	private Date chqiB;

	/**
	 * 
	 */
	private Date chqiE;

	/**
	 * 
	 */
	private List<DhuoChmxVO> datas;

	public Date getJhqiB() {
		return jhqiB;
	}

	public void setJhqiB(Date jhqiB) {
		this.jhqiB = jhqiB;
	}

	public Date getJhqiE() {
		return jhqiE;
	}

	public void setJhqiE(Date jhqiE) {
		this.jhqiE = jhqiE;
	}

	public Date getChqiB() {
		return chqiB;
	}

	public void setChqiB(Date chqiB) {
		this.chqiB = chqiB;
	}

	public Date getChqiE() {
		return chqiE;
	}

	public void setChqiE(Date chqiE) {
		this.chqiE = chqiE;
	}

	public List<DhuoChmxVO> getDatas() {
		return datas;
	}

	public void setDatas(List<DhuoChmxVO> datas) {
		this.datas = datas;
	}

}
