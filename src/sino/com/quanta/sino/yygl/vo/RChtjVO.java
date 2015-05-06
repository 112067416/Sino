package com.quanta.sino.yygl.vo;

import java.util.Date;
import java.util.List;

import com.quanta.sino.ch.vo.ChtjVO;

/**
 * <p>
 * 按日统计出货
 * </p>
 * <p>
 * create: 2011-6-2 下午08:08:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class RChtjVO {

	/**
	 * 出货开始时间
	 */
	private Date chriS;

	/**
	 * 出货结束时间
	 */
	private Date chriE;

	/**
	 * 日出货统计结果
	 */
	List<ChtjVO> chtjs;

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

	public List<ChtjVO> getChtjs() {
		return chtjs;
	}

	public void setChtjs(List<ChtjVO> chtjs) {
		this.chtjs = chtjs;
	}

}
