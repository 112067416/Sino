package com.quanta.sino.yccl.vo;

import java.util.List;

/**
 * <p>
 * 作业停止数据对象
 * </p>
 * <p>
 * create: 2011-5-17 下午07:05:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZpkDataVO {

	/**
	 * 查询条件
	 */
	private String jbno;

	/**
	 * 查询结果
	 */
	private List<ZyztVO> datas;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public List<ZyztVO> getDatas() {
		return datas;
	}

	public void setDatas(List<ZyztVO> datas) {
		this.datas = datas;
	}

}
