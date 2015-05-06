package com.quanta.sino.dh.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 次级品VO
 * </p>
 * <p>
 * create: 2011-7-10 下午08:54:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FspZlVO {

	/**
	 * 制品重量
	 */
	private Integer zpzl;

	/**
	 * 附着量.单位
	 */
	private String fudw;

	/**
	 * 附着量.正面
	 */
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	private String fufm;

	/**
	 * 制品号
	 */
	private List<String> jbnos = new ArrayList<String>();

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public List<String> getJbnos() {
		return jbnos;
	}

	public void setJbnos(List<String> jbnos) {
		this.jbnos = jbnos;
	}

}
