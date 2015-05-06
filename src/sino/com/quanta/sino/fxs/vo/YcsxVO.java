package com.quanta.sino.fxs.vo;

import com.quanta.sino.orm.FxzyRzYcsx;

/**
 * <p>
 * 为作业分析日志，增加异常事项
 * </p>
 * <p>
 * create: 2011-4-12 上午09:56:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YcsxVO {

	/**
	 * 分析作业日志
	 */
	private String pid;

	/**
	 * 异常事项备注
	 */
	private String ycsx;

	/**
	 * 异常事项
	 */
	private FxzyRzYcsx[] items;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public FxzyRzYcsx[] getItems() {
		return items;
	}

	public void setItems(FxzyRzYcsx[] items) {
		this.items = items;
	}

	public String getYcsx() {
		return ycsx;
	}

	public void setYcsx(String ycsx) {
		this.ycsx = ycsx;
	}

}
