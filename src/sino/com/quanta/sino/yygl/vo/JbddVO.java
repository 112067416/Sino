package com.quanta.sino.yygl.vo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基板订单VO
 * </p>
 * <p>
 * create: 2011-7-7 上午10:54:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddVO {

	/**
	 * 基板订单编号
	 */
	private String jbno;

	/**
	 * 基板订单状态
	 */
	private String stat;
	/**
	 * 
	 */
	private Date crea;

	/**
	 * 内销基板订单明细
	 */
	private List<JbddItemVO> nxItems;

	/**
	 * 外销基板订单明细
	 */
	private List<JbddItemVO> wxItems;

	/**
	 * 追加基板订单明细
	 */
	private List<JbddItemVO> zjItems;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public List<JbddItemVO> getNxItems() {
		return nxItems;
	}

	public void setNxItems(List<JbddItemVO> nxItems) {
		this.nxItems = nxItems;
	}

	public List<JbddItemVO> getWxItems() {
		return wxItems;
	}

	public void setWxItems(List<JbddItemVO> wxItems) {
		this.wxItems = wxItems;
	}

	public List<JbddItemVO> getZjItems() {
		return zjItems;
	}

	public void setZjItems(List<JbddItemVO> zjItems) {
		this.zjItems = zjItems;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
