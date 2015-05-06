package com.quanta.sino.yygl.vo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 成品出库单
 * </p>
 * <p>
 * create: 2011-6-15 上午09:58:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CkdVO {
	/**
	 * 出货开始时间
	 */
	private Date chriS;

	/**
	 * 出货结束时间
	 */
	private Date chriE;

	/**
	 * 成品出库单信息
	 */
	private List<CpckdVO> vos;

	public Date getChriS() {
		return chriS;
	}

	public void setChriS(Date chriS) {
		this.chriS = chriS;
	}

	public Date getChriE() {
		return chriE;
	}

	public void setChriE(Date chriE) {
		this.chriE = chriE;
	}

	public List<CpckdVO> getVos() {
		return vos;
	}

	public void setVos(List<CpckdVO> vos) {
		this.vos = vos;
	}
}
