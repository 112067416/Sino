package com.quanta.sino.yygl.vo;

import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.orm.JbddItemLog;

/**
 * <p>
 * 基板订单与日志VO
 * </p>
 * <p>
 * create: 2011-8-8 下午07:48:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddItemVO {

	/**
	 * 
	 */
	private JbddItem item;

	/**
	 * 
	 */
	private JbddItemLog log;

	public JbddItem getItem() {
		return item;
	}

	public void setItem(JbddItem item) {
		this.item = item;
	}

	public JbddItemLog getLog() {
		return log;
	}

	public void setLog(JbddItemLog log) {
		this.log = log;
	}

}
