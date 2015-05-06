package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 装箱对照页面题头内容
 * </p>
 * <p>
 * create: 2011-1-11 上午09:57:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxdzTitleVO {

	/**
	 * 装箱指示书号
	 */
	private String zxno;

	/**
	 * 出货日
	 */
	private Date chri;

	/**
	 * 应有数量
	 */
	private Integer yysl;

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public Integer getYysl() {
		return yysl;
	}

	public void setYysl(Integer yysl) {
		this.yysl = yysl;
	}

}
