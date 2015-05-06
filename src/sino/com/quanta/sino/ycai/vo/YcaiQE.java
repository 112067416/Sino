package com.quanta.sino.ycai.vo;

import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YcaiTp;

/**
 * <p>
 * 原材卷板查询条件
 * </p>
 * <p>
 * create: 2011-3-7 下午03:58:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class YcaiQE extends QEntity<YcaiTp> {

	/**
	 * 原材客户略称
	 */
	@QF(operator = EO.LIKE)
	private String ycbr;

	/**
	 * 船名称
	 */
	@QF
	private String ship;
	/**
	 * 供应商合同号
	 */
	@QF
	private String ybno;

	/**
	 * 卷板NO
	 */
	@QF(alias = "jbno")
	private String coilNo;

	/**
	 * 供应商合同行号
	 */
	@QF
	private String line;

	/**
	 * 制造商卷板NO
	 */
	@QF(alias = "zzsj", operator = EO.LIKE)
	private String zzsj;

	/**
	 * 卷板NO 开始
	 */
	@QF(alias = "jbno", operator = EO.GE)
	private String coilNoS;

	/**
	 * 卷板NO 结束
	 */
	@QF(alias = "jbno", operator = EO.LE)
	private String coilNoE;

	/**
	 * 指示书NO
	 */
	@QF
	private String zsno;

	/**
	 * 删除标记
	 */
	@QF(alias = "scbj", operator = EO.IN)
	private String[] scbjs;

	/**
	 * 删除标记
	 */
	@QF
	private String scbj;

	/**
	 * 分配余材标记
	 */
	@QF(operator = EO.IN)
	private String[] fpyc;

	/**
	 * 原板状态
	 */
	@QF(alias = "stat", operator = EO.IN)
	private String[] stats;

	/**
	 * 原板状态
	 */
	@QF
	private String stat;

	/**
	 * 
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 原材原板NO
	 */
	@QF(alias = "jbno", operator = EO.IN)
	private String[] jbnos;

	/**
	 * 规格代码
	 */
	@QF
	private String ggno;

	/**
	 * 大于现品宽
	 */
	@QF(alias = "xpkn", operator = EO.GE)
	private Double kuanS;

	/**
	 * 小于现品宽
	 */
	@QF(alias = "xpkn", operator = EO.LE)
	private Double kuanE;

	/**
	 * 大于现品厚
	 */
	@QF(alias = "xpho", operator = EO.GE)
	private Double houuS;

	/**
	 * 小于现品厚
	 */
	@QF(alias = "xpho", operator = EO.LE)
	private Double houuE;

	/**
	 * 大于现品宽
	 */
	@QF(alias = "xpkn", operator = EO.GE)
	private Double xpknS;

	/**
	 * 小于现品宽
	 */
	@QF(alias = "xpkn", operator = EO.LE)
	private Double xpknE;

	/**
	 * 大于现品厚
	 */
	@QF(alias = "xpho", operator = EO.GE)
	private Double xphoS;

	/**
	 * 小于现品厚
	 */
	@QF(alias = "xpho", operator = EO.LE)
	private Double xphoE;

	/**
	 * 运用规格
	 */
	@QF
	private String yuny;

	/**
	 * 现品厚
	 */
	@QF(alias = "xpho", operator = EO.EQ)
	private Double houu;

	/**
	 * 现品宽
	 */
	@QF(alias = "xpkn", operator = EO.EQ)
	private Double kuan;

	/**
	 * 钢种类型
	 */
	@QF(alias = "gzlx")
	private String gzlx;

	/**
	 * 是否保税
	 */
	@QF(alias = "sfbs")
	private String sfbs;

	/**
	 * 表面
	 */
	@QF
	private String face;

	/**
	 * 表面
	 */
	@QF(alias = "face", operator = EO.IN)
	private List<String> faces;

	/**
	 * 是否打印
	 */
	@QF(operator = EO.VQL)
	private String sfdy;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.EQ)
	private String duic;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.IN)
	private String[] duics;

	/**
	 * @return the ycbr
	 */
	public String getYcbr() {
		return ycbr;
	}

	/**
	 * @param ycbr
	 *            the ycbr to set
	 */
	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

	/**
	 * @return the ship
	 */
	public String getShip() {
		return ship;
	}

	/**
	 * @param ship
	 *            the ship to set
	 */
	public void setShip(String ship) {
		this.ship = ship;
	}

	/**
	 * @return the ybno
	 */
	public String getYbno() {
		return ybno;
	}

	/**
	 * @param ybno
	 *            the ybno to set
	 */
	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	/**
	 * @return the coilNo
	 */
	public String getCoilNo() {
		return coilNo;
	}

	/**
	 * @param coilNo
	 *            the coilNo to set
	 */
	public void setCoilNo(String coilNo) {
		this.coilNo = coilNo;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the coilNoS
	 */
	public String getCoilNoS() {
		return coilNoS;
	}

	/**
	 * @param coilNoS
	 *            the coilNoS to set
	 */
	public void setCoilNoS(String coilNoS) {
		this.coilNoS = coilNoS;
	}

	/**
	 * @return the coilNoE
	 */
	public String getCoilNoE() {
		return coilNoE;
	}

	/**
	 * @param coilNoE
	 *            the coilNoE to set
	 */
	public void setCoilNoE(String coilNoE) {
		this.coilNoE = coilNoE;
	}

	/**
	 * @return the zsno
	 */
	public String getZsno() {
		return zsno;
	}

	/**
	 * @param zsno
	 *            the zsno to set
	 */
	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	/**
	 * @return the fpyc
	 */
	public String[] getFpyc() {
		return fpyc;
	}

	/**
	 * @param fpyc
	 *            the fpyc to set
	 */
	public void setFpyc(String[] fpyc) {
		this.fpyc = fpyc;
	}

	/**
	 * @return the stats
	 */
	public String[] getStats() {
		return stats;
	}

	/**
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(String[] stats) {
		this.stats = stats;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the jbnos
	 */
	public String[] getJbnos() {
		return jbnos;
	}

	public void setJbnoStr(String jbnos) {
		if (jbnos == null || jbnos.isEmpty()) {
			this.jbnos = null;
			return;
		}
		this.jbnos = jbnos.replaceAll("，", ",").split(",");
	}

	public void setJbnos(String[] jbnos) {
		this.jbnos = jbnos;
	}

	/**
	 * @return the ggno
	 */
	public String getGgno() {
		return ggno;
	}

	/**
	 * @param ggno
	 *            the ggno to set
	 */
	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	/**
	 * @return the kuanS
	 */
	public Double getKuanS() {
		return kuanS;
	}

	/**
	 * @param kuanS
	 *            the kuanS to set
	 */
	public void setKuanS(Double kuanS) {
		this.kuanS = kuanS;
	}

	/**
	 * @return the kuanE
	 */
	public Double getKuanE() {
		return kuanE;
	}

	/**
	 * @param kuanE
	 *            the kuanE to set
	 */
	public void setKuanE(Double kuanE) {
		this.kuanE = kuanE;
	}

	/**
	 * @return the houuS
	 */
	public Double getHouuS() {
		return houuS;
	}

	/**
	 * @param houuS
	 *            the houuS to set
	 */
	public void setHouuS(Double houuS) {
		this.houuS = houuS;
	}

	/**
	 * @return the houuE
	 */
	public Double getHouuE() {
		return houuE;
	}

	/**
	 * @param houuE
	 *            the houuE to set
	 */
	public void setHouuE(Double houuE) {
		this.houuE = houuE;
	}

	public String[] getScbjs() {
		return scbjs;
	}

	public void setScbjs(String[] scbjs) {
		this.scbjs = scbjs;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	/**
	 * @return the yuny
	 */
	public String getYuny() {
		return yuny;
	}

	/**
	 * @param yuny
	 *            the yuny to set
	 */
	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	/**
	 * @return the houu
	 */
	public Double getHouu() {
		return houu;
	}

	/**
	 * @param houu
	 *            the houu to set
	 */
	public void setHouu(Double houu) {
		this.houu = houu;
	}

	/**
	 * @return the kuan
	 */
	public Double getKuan() {
		return kuan;
	}

	/**
	 * @param kuan
	 *            the kuan to set
	 */
	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	/**
	 * @return the gzlx
	 */
	public String getGzlx() {
		return gzlx;
	}

	/**
	 * @param gzlx
	 *            the gzlx to set
	 */
	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	/**
	 * @return the sfbs
	 */
	public String getSfbs() {
		return sfbs;
	}

	/**
	 * @param sfbs
	 *            the sfbs to set
	 */
	public void setSfbs(String sfbs) {
		this.sfbs = sfbs;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String getSfdy() {
		return sfdy;
	}

	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}

	public List<String> getFaces() {
		return faces;
	}

	public void setFaces(List<String> faces) {
		this.faces = faces;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String[] getDuics() {
		return duics;
	}

	public void setDuics(String[] duics) {
		this.duics = duics;
	}

	public Double getXpknS() {
		return xpknS;
	}

	public void setXpknS(Double xpknS) {
		this.xpknS = xpknS;
	}

	public Double getXpknE() {
		return xpknE;
	}

	public void setXpknE(Double xpknE) {
		this.xpknE = xpknE;
	}

	public Double getXphoS() {
		return xphoS;
	}

	public void setXphoS(Double xphoS) {
		this.xphoS = xphoS;
	}

	public Double getXphoE() {
		return xphoE;
	}

	public void setXphoE(Double xphoE) {
		this.xphoE = xphoE;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

}
