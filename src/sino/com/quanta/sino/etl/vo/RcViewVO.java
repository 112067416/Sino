package com.quanta.sino.etl.vo;


/**
 * <p>
 * ETL品质管理 页面当前卷 和备用卷查询VO
 * </p>
 * <p>
 * create: 2011-2-15 下午04:37:16
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class RcViewVO {

	/**
	 * 当前卷
	 */
	private String dqjbno;

	/**
	 * 备用卷
	 */
	private String byjbno;

	public String getDqjbno() {
		return dqjbno;
	}

	public void setDqjbno(String dqjbno) {
		this.dqjbno = dqjbno;
	}

	public String getByjbno() {
		return byjbno;
	}

	public void setByjbno(String byjbno) {
		this.byjbno = byjbno;
	}

}
