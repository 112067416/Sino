package com.quanta.sino.dh.vo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订货进度及明细视图
 * </p>
 * <p>
 * create: 2011-3-4 下午04:01:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhjdVO {

	/**
	 * 
	 */
	private String dhno;

	/**
	 * 
	 */
	private String lineStart;

	/**
	 * 
	 */
	private String lineEnd;

	/**
	 * 
	 */
	private Map<DhuoVO, List<Dhjdmx>> dhjdmxs;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLineStart() {
		return lineStart;
	}

	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	public String getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	public Map<DhuoVO, List<Dhjdmx>> getDhjdmxs() {
		return dhjdmxs;
	}

	public void setDhjdmxs(Map<DhuoVO, List<Dhjdmx>> dhjdmxs) {
		this.dhjdmxs = dhjdmxs;
	}

}
