package com.quanta.sino.dy.vo;

import java.util.List;

/**
 * <p>
 * 制品卡页面数据对象
 * </p>
 * <p>
 * create: 2011-1-13 下午01:10:18
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZpkDataVO {

	/**
	 * 查询条件
	 */
	private String[] jbnos;

	/**
	 * 查询结果
	 */
	private List<ZpkVO> datas;

	/**
	 * @return the jbnos
	 */
	public String[] getJbnos() {
		return jbnos;
	}

	/**
	 * @param jbnos
	 *            the jbnos to set
	 */
	public void setJbnos(String[] jbnos) {
		this.jbnos = jbnos;
	}

	/**
	 * @return the datas
	 */
	public List<ZpkVO> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<ZpkVO> datas) {
		this.datas = datas;
	}

}
