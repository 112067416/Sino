package com.quanta.sino.ss.vo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分选检查日志数据
 * </p>
 * <p>
 * create: 2011-1-27 上午11:47:46
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsRzVO {

	/**
	 * 选别时间
	 */
	private Date pssd;

	/**
	 * 检查班
	 */
	private String ban;

	/**
	 * 检查组
	 */
	private String zu;

	/**
	 * 合成新包的制品号
	 */
	private String jbno;

	/**
	 * 引用包的制品号
	 */
	private String pileNo;

	/**
	 * 合并包记录
	 */
	private List<SsVO> datas;
	/**
	 * 作成时间开始日期
	 */

	private Date crea_begin;

	/**
	 * 作成时间结束日期
	 */
	private Date crea_end;

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

	public List<SsVO> getDatas() {
		return datas;
	}

	public void setDatas(List<SsVO> datas) {
		this.datas = datas;
	}

	public Date getPssd() {
		return pssd;
	}

	public void setPssd(Date pssd) {
		this.pssd = pssd;
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

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getPileNo() {
		return pileNo;
	}

	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}

}
