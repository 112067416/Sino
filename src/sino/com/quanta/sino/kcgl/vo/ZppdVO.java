/**
 * 
 */
package com.quanta.sino.kcgl.vo;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 库位盘点VO
 * </p>
 * <p>
 * create: 2011-5-30 下午09:30:58
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZppdVO {

	/**
	 * <p>
	 * 卷板号
	 * </p>
	 */
	@QF(alias = "jbno")
	private String jbno;

	/**
	 * <p>
	 * 库位
	 * </p>
	 */
	@QF(alias = "kw")
	private String kw;

	/**
	 * <p>
	 * 库位
	 * </p>
	 */
	@QF(alias = "duic")
	private String duic;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

}
