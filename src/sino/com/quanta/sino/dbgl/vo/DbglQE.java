package com.quanta.sino.dbgl.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DbglTp;

/**
 * <p>
 * 端板查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:51:51
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q
public class DbglQE extends QEntity<DbglTp> {
	/**
	 * 制品号
	 */
	@QF(operator = EO.LIKE)
	private String jbno;
	/**
	 * 生产线
	 */
	@QF
	private String slin;
	/**
	 * 捆包形式
	 */
	@QF
	private String kbao;
	/**
	 * 堆场
	 */
	@QF
	private String duic;

	/**
	 * 状态
	 */
	@QF
	private String zt;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
