/**
 * 
 */
package com.quanta.sino.kcgl.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品盘点查询条件
 * </p>
 * <p>
 * create: 2011-5-30 下午09:32:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZpngTp.class))
public class ZppdQE extends QEntity<ZppdVO> {

	/**
	 * 现品状况
	 */
	@QF(alias = "xpzk", operator = EO.IN)
	private String[] xpzks;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.IN)
	private String[] duics;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.EQ)
	private String duic;

	/**
	 * 分配余材
	 */
	@QF(alias = "fpyc", operator = EO.IN)
	private String[] fpycs;

	/**
	 * 删除标记
	 */
	@QF
	private String scbj;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * 分配余材
	 */
	@QF
	private String fpyc;

	public String[] getXpzks() {
		return xpzks;
	}

	public void setXpzks(String[] xpzks) {
		this.xpzks = xpzks;
	}

	public String[] getDuics() {
		return duics;
	}

	public void setDuics(String[] duics) {
		this.duics = duics;
	}

	public String[] getFpycs() {
		return fpycs;
	}

	public void setFpycs(String[] fpycs) {
		this.fpycs = fpycs;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getFpyc() {
		return fpyc;
	}

	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

}
