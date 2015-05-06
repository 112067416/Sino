package com.quanta.sino.etl.vo;

import java.util.List;

/**
 * <p>
 * etl 入厕显示 vo
 * </p>
 * <p>
 * create: 2011-1-26 下午03:21:04
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class RcVO {

	private String slin;

	private String zu;
	private String ban;
	private List<RcmxVO> rcmxs;

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public List<RcmxVO> getRcmxs() {
		return rcmxs;
	}

	public void setRcmxs(List<RcmxVO> rcmxs) {
		this.rcmxs = rcmxs;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

}
