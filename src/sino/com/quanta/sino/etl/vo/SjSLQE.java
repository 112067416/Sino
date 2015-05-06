package com.quanta.sino.etl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 待生产的切板制品查询条件
 * </p>
 * <p>
 * create: 2011-1-28 上午10:45:45
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZpngTp.class, alias = "a"))
public class SjSLQE extends QEntity<ZpSLVO> {

	/**
	 * COIL.No.
	 */
	@QF(alias = "a.jbno", operator = EO.LIKE)
	private String jbno;

	/**
	 * 堆场
	 */
	@QF(alias = "a.duic")
	private String Duic;
	/**
	 * <p>
	 * 垫木方向
	 * </p>
	 */
	@QF(alias = "a.dmfx")
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@QF(alias = "a.kbao")
	private String kbao;
	/**
	 * 订货No.行号
	 */
	@QF(alias = "a.dhno")
	private String dhno;

	/**
	 * 分配余材
	 */
	@QF(alias = "a.fpyc")
	private String fpyc;

	/**
	 * 堆场
	 */
	@QF(alias = "a.cang", operator = EO.GT)
	private Double cang;

	/**
	 * 制品状态
	 */
	@QF(alias = "a.stat", operator = EO.IN)
	private String[] stat;

	/**
	 * <p>
	 * 作成时间-开始
	 * </p>
	 */
	@QF(alias = "a.crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * <p>
	 * 作成时间-结束
	 * </p>
	 */
	@QF(alias = "a.crea", operator = EO.LE, addDays = 1)
	private Date crea_end;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Date getCrea_begin() {
		return crea_begin;
	}

	public void setCrea_begin(Date crea_begin) {
		this.crea_begin = crea_begin;
	}

	public Date getCrea_end() {
		return crea_end;
	}

	public void setCrea_end(Date crea_end) {
		this.crea_end = crea_end;
	}

	public String[] getStat() {
		return stat;
	}

	public void setStat(String[] stat) {
		this.stat = stat;
	}

	public String getDuic() {
		return Duic;
	}

	public void setDuic(String duic) {
		Duic = duic;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getFpyc() {
		return fpyc;
	}

	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

}
