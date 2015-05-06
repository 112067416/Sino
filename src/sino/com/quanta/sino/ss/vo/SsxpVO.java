package com.quanta.sino.ss.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * SS分选数据
 * </p>
 * <p>
 * create: 2011-1-20 下午08:22:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsxpVO {
	/**
	 * 用户略称
	 */
	@QF(alias = "a.abbr")
	private String abbr;
	/**
	 * 表面精加工符号
	 */
	@QF(alias = "a.face")
	private String face;
	/**
	 * 制品尺寸.厚
	 */
	@QF(alias = "a.houu")
	private Double houu;
	/**
	 * 制品尺寸.宽
	 */
	@QF(alias = "a.kuan")
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@QF(alias = "a.cang")
	private Double cang;
	/**
	 * 运用规格
	 */
	@QF(alias = "a.yuny")
	private String yuny;
	/**
	 * 附着量.单位
	 */
	@QF(alias = "a.fudw")
	private String fudw;
	/**
	 * 附着量.正面
	 */
	@QF(alias = "a.fuzm")
	private String fuzm;
	/**
	 * 附着量.反面
	 */
	@QF(alias = "a.fufm")
	private String fufm;
	/**
	 * 制造商代码
	 */
	@QF(alias = "a.zzsd")
	private String zzsd;
	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	@QF(alias = "a.kpn1Flag")
	private String kpn1Flag;
	/**
	 * 附页KPNO.1
	 */
	@QF(alias = "a.kpn1")
	private String kpn1;
	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	@QF(alias = "a.kpn2Flag")
	private String kpn2Flag;
	/**
	 * 附页KPNO.2
	 */
	@QF(alias = "a.kpn2")
	private String kpn2;
	/**
	 * 附页KPNO.3标识位。区分ETL、SL和全部
	 */
	@QF(alias = "a.kpn3Flag")
	private String kpn3Flag;
	/**
	 * 附页KPNO.3
	 */
	@QF(alias = "a.kpn3")
	private String kpn3;
	/**
	 * 附页KPNO.4标识位。区分ETL、SL和全部
	 */
	@QF(alias = "a.kpn4Flag")
	private String kpn4Flag;
	/**
	 * 附页KPNO.4
	 */
	@QF(alias = "a.kpn4")
	private String kpn4;
	/**
	 * 业连No
	 */
	@QF(alias = "a.ylno")
	private String ylno;

	/**
	 * 订货合同
	 */
	@QF(alias = "a.dhno")
	private String dhno;
	/**
	 * 分选日志ID
	 */
	@QF(alias = "a.id")
	private String id;
	/**
	 * 修正区分
	 */
	@QF(alias = "b.clq")
	private String clq;
	/**
	 * Pile区分
	 */
	@QF(alias = "b.plq")
	private String plq;
	/**
	 * 日志时间
	 */
	@QF(alias = "a.crea")
	private Date crea;

	/**
	 * 制品pile-no
	 */
	@QF(alias = "b.jbn")
	private String jbno;
	/**
	 * 计算重量
	 */
	@QF(alias = "b.jsz")
	private Integer jszl;
	/**
	 * 实际重量
	 */
	@QF(alias = "b.sjz")
	private Integer sjzl;
	/**
	 * 产量
	 */
	@QF(alias = "b.cha")
	private String chan;
	/**
	 * 等级
	 */
	@QF(alias = "b.den")
	private String deng;
	/**
	 * 主缺陷
	 */
	@QF(alias = "b.qqd")
	private String qqdm;
	/**
	 * 包装张数
	 */
	@QF(alias = "a.zshu")
	private Integer zshu;
	/**
	 * 检定员
	 */
	@QF(alias = "a.jdyn")
	private String jdyn;
	/**
	 * 计数员
	 */
	@QF(alias = "a.jsyn")
	private String jsyn;

	public String getClq() {
		return clq;
	}

	public void setClq(String clq) {
		this.clq = clq;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Integer getJszl() {
		return jszl;
	}

	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getQqdm() {
		return qqdm;
	}

	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public String getJdyn() {
		return jdyn;
	}

	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	public String getJsyn() {
		return jsyn;
	}

	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	public String getPlq() {
		return plq;
	}

	public void setPlq(String plq) {
		this.plq = plq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getKpn1() {
		return kpn1;
	}

	public void setKpn1(String kpn1) {
		this.kpn1 = kpn1;
	}

	public String getKpn2Flag() {
		return kpn2Flag;
	}

	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
	}

	public String getKpn2() {
		return kpn2;
	}

	public void setKpn2(String kpn2) {
		this.kpn2 = kpn2;
	}

	public String getKpn3Flag() {
		return kpn3Flag;
	}

	public void setKpn3Flag(String kpn3Flag) {
		this.kpn3Flag = kpn3Flag;
	}

	public String getKpn3() {
		return kpn3;
	}

	public void setKpn3(String kpn3) {
		this.kpn3 = kpn3;
	}

	public String getKpn4Flag() {
		return kpn4Flag;
	}

	public void setKpn4Flag(String kpn4Flag) {
		this.kpn4Flag = kpn4Flag;
	}

	public String getKpn4() {
		return kpn4;
	}

	public void setKpn4(String kpn4) {
		this.kpn4 = kpn4;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getKpn1Flag() {
		return kpn1Flag;
	}

	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
	}

}
