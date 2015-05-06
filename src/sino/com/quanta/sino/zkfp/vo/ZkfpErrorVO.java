package com.quanta.sino.zkfp.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quanta.sino.orm.ZsdxTp;

/**
 * <p>
 * 在库分配错误记录及明细
 * </p>
 * <p>
 * create: 2011-3-3 下午11:07:06
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZkfpErrorVO {

	/**
	 * 在库分配开始时间
	 */
	private Date fpBegin;

	/**
	 * 在库分配结束时间
	 */
	private Date fpEnd;

	/**
	 * 原材卷板NO
	 */
	private String jbno;

	/**
	 * 订货No
	 */
	private String dhnoAndLine;

	/**
	 * 在库分配错误记录明细
	 */
	private Map<ZsdxTp, List<CError>> cwjls = new HashMap<ZsdxTp, List<CError>>();

	public Date getFpBegin() {
		return fpBegin;
	}

	public void setFpBegin(Date fpBegin) {
		this.fpBegin = fpBegin;
	}

	public Date getFpEnd() {
		return fpEnd;
	}

	public void setFpEnd(Date fpEnd) {
		this.fpEnd = fpEnd;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getDhnoAndLine() {
		return dhnoAndLine;
	}

	public void setDhnoAndLine(String dhnoAndLine) {
		this.dhnoAndLine = dhnoAndLine;
	}

	public Map<ZsdxTp, List<CError>> getCwjls() {
		return cwjls;
	}

	public void setCwjls(Map<ZsdxTp, List<CError>> cwjls) {
		this.cwjls = cwjls;
	}

}
