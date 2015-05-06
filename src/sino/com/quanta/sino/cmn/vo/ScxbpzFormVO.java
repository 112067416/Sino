package com.quanta.sino.cmn.vo;

import com.quanta.sino.orm.Scxbpz;

/**
 * <p>
 * 生产线别配置列表
 * </p>
 * <p>
 * create: 2011-1-6 上午11:35:07
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ScxbpzFormVO {

	/**
	 * 生产类型
	 */
	private String type;

	/**
	 * 生产线别配置列表
	 */
	private Scxbpz[] scxbs;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the scxbs
	 */
	public Scxbpz[] getScxbs() {
		return scxbs;
	}

	/**
	 * @param scxbs
	 *            the scxbs to set
	 */
	public void setScxbs(Scxbpz[] scxbs) {
		this.scxbs = scxbs;
	}
}
