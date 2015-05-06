package com.quanta.sino.etl.vo;

/**
 * <p>
 * 速报登录实体类
 * </p>
 * <p>
 * create: 2011-11-22 下午10:38:53
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class SBVO {

	/**
	 * 速报明细
	 */
	private SBmxVO[] items;

	public SBmxVO[] getItems() {
		return items;
	}

	public void setItems(SBmxVO[] items) {
		this.items = items;
	}

}
