/**
 * 
 */
package com.quanta.sino.kcgl.vo;

/**
 * <p>
 * 制品入库对象
 * </p>
 * <p>
 * create: 2011-1-6 上午09:29:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZprkVO {

	/**
	 * 操作类别
	 */
	private String operate;

	/**
	 * 制品NO
	 */
	private String jbno;

	/**
	 * 库位
	 */
	private String kw;

	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}

	/**
	 * @param operate
	 *            the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the kw
	 */
	public String getKw() {
		return kw;
	}

	/**
	 * @param kw
	 *            the kw to set
	 */
	public void setKw(String kw) {
		this.kw = kw;
	}

}
