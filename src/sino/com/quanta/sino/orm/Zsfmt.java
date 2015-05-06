package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实绩日志（指示情报共通格式）
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_ZSFMT")
public class Zsfmt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;
	/**
	 * 指示书NO
	 */
	@Column(name = "ZSN_", length = 6)
	private String zsn;

	/**
	 * 操作MODE
	 */
	@Column(name = "CAO_", length = 1)
	private String cao;
	/**
	 * 装入宽
	 */
	@Column(name = "ZRK_", columnDefinition = "numeric(6,2)")
	private Double zrk;
	/**
	 * 剪断长
	 */
	@Column(name = "JDC_", columnDefinition = "numeric(6,2)")
	private Double jdc;
	/**
	 * 剪边标记
	 */
	@Column(name = "JBB_", length = 1)
	private String jbb;
	/**
	 * 剪边后宽
	 */
	@Column(name = "JBK_", columnDefinition = "numeric(6,2)")
	private Double jbk;
	/**
	 * 运用规格
	 */
	@Column(name = "YUN_", length = 6)
	private String yun;
	/**
	 * 附着量(g/m2)
	 */
	@Column(name = "FGM_", length = 12)
	private String fgm;
	/**
	 * 附着量-正面-下限值
	 */
	@Column(name = "FZX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fzx;
	/**
	 * 附着量-正面-上限值
	 */
	@Column(name = "FZS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fzs;
	/**
	 * 附着量-反面-下限值
	 */
	@Column(name = "FFX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ffx;
	/**
	 * 附着量-反面-上限值
	 */
	@Column(name = "FFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ffs;
	/**
	 * REFLOW要否
	 */
	@Column(name = "FLO_", length = 1)
	private String flo;
	/**
	 * 订货表面精加工符号
	 */
	@Column(name = "FAC_", length = 2)
	private String fac;
	/**
	 * 合金层
	 */
	@Column(name = "HJI_", length = 3)
	private String hji;
	/**
	 * K-PLATE洗涤强化
	 */
	@Column(name = "HDQ_", length = 1)
	private String hdq;
	/**
	 * 化学处理方法
	 */
	@Column(name = "HXC_", length = 4)
	private String hxc;
	/**
	 * 目标涂油种类
	 */
	@Column(name = "YTY_", length = 3)
	private String yty;
	/**
	 * 目标涂油量
	 */
	@Column(name = "YQT_", length = 2)
	private String yqt;
	/**
	 * 用户略称
	 */
	@Column(name = "ABB_", length = 16)
	private String abb;
	/**
	 * 订货NO行号
	 */
	@Column(name = "DHN_", length = 9)
	private String dhn;
	/**
	 * 销售W否
	 */
	@Column(name = "XSW_", length = 1)
	private String xsw;
	/**
	 * 分配等级
	 */
	@Column(name = "FPD_", length = 3)
	private String fpd;
	/**
	 * 钢种类型
	 */
	@Column(name = "GZL_", length = 2)
	private String gzl;
	/**
	 * 直角度
	 */
	@Column(name = "JIA_", columnDefinition = "numeric(2,1)", precision = 1, scale = 2)
	private Double jia;
	/**
	 * m单重
	 */
	@Column(name = "MDA_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double mda;
	/**
	 * 薄板单重
	 */
	@Column(name = "BDA_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double bda;
	/**
	 * 112张重量
	 */
	@Column(name = "Z112_", columnDefinition = "numeric(5,1)", scale = 5, precision = 1)
	private Double z112;
	/**
	 * 交货地点
	 */
	@Column(name = "JHD_", length = 1)
	private String jhd;
	/**
	 * 内径代码
	 */
	@Column(name = "NJN_", length = 1)
	private String njn;
	/**
	 * 内径保护筒
	 */
	@Column(name = "NJB_", length = 1)
	private String njb;
	/**
	 * 保护标记
	 */
	@Column(name = "PRO_", length = 1)
	private String pro;
	/**
	 * 锯齿可否
	 */
	@Column(name = "JCK_", length = 1)
	private String jck;
	/**
	 * 锯齿种类
	 */
	@Column(name = "JCZ_", length = 1)
	private String jcz;
	/**
	 * 制品尺寸(W表示编辑完)
	 */
	@Column(name = "ZPC_", length = 22)
	private String zpc;
	/**
	 * 订货规格
	 */
	@Column(name = "DHG_", length = 6)
	private String dhg;
	/**
	 * 订货附着量略称
	 */
	@Column(name = "DHF_", length = 12)
	private String dhf;
	/**
	 * 尺寸允许范围-厚%上限-值
	 */
	@Column(name = "HSZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double hsz;
	/**
	 * 尺寸允许范围-厚%下限-值
	 */
	@Column(name = "HXZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double hxz;
	/**
	 * 尺寸允许范围-厚mm上限-值
	 */
	@Column(name = "MSZ_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double msz;
	/**
	 * 尺寸允许范围-厚mm下限-值
	 */
	@Column(name = "MXZ_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double mxz;
	/**
	 * 尺寸允许范围-宽mm上限-值
	 */
	@Column(name = "KSZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double ksz;
	/**
	 * 尺寸允许范围-宽mm下限-值
	 */
	@Column(name = "KXZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double kxz;
	/**
	 * 尺寸允许范围-剪断长mm上限-值
	 */
	@Column(name = "CSZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double csz;
	/**
	 * 尺寸允许范围-剪断长mm下限-值
	 */
	@Column(name = "CXZ_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double cxz;
	/**
	 * 包装张数
	 */
	@Column(name = "BZZ_", columnDefinition = "numeric(4,0)")
	private Integer bzz;
	/**
	 * 批重量
	 */
	@Column(name = "PIZ_", columnDefinition = "numeric(8,3)")
	private Double piz;
	/**
	 * 箱数
	 */
	@Column(name = "XSH_")
	private Integer xsh;
	/**
	 * B/B重量
	 */
	@Column(name = "BBZ_", columnDefinition = "numeric(5,1)", scale = 5, precision = 1)
	private Double bbz;
	/**
	 * 零头可否
	 */
	@Column(name = "LTK_", length = 1)
	private String ltk;
	/**
	 * 零头下限
	 */
	@Column(name = "LTX_")
	private Integer ltx;
	/**
	 * 重量计算补正系数
	 */
	@Column(name = "BZX_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double bzx;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMF_", length = 1)
	private String dmf;
	/**
	 * 垫木重量
	 */
	@Column(name = "DMZ_", columnDefinition = "numeric(3,0)")
	private Integer dmz;
	/**
	 * 包装材料重量(Kg)
	 */
	@Column(name = "BZC_", columnDefinition = "numeric(3,0)")
	private Integer bzc;
	/**
	 * 捆包指定重量-上限
	 */
	@Column(name = "KBS_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double kbs;
	/**
	 * 捆包指定重量-下限
	 */
	@Column(name = "KBX_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double kbx;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBA_", length = 3)
	private String kba;
	/**
	 * SPECIFICATION[规格]
	 */
	@Column(name = "SPE_", length = 20)
	private String spe;
	/**
	 * 用户名称
	 */
	@Column(name = "NAM_", length = 60)
	private String nam;
	/**
	 * 附页KpNo.1标示流水线
	 */
	@Column(name = "KL1_", length = 1)
	private String kl1;
	/**
	 * 附页KpNo.1
	 */
	@Column(name = "KN1_", length = 9)
	private String kn1;
	/**
	 * 附页KpNo.2标示流水线
	 */
	@Column(name = "KL2_", length = 1)
	private String kl2;
	/**
	 * 附页KpNo.2
	 */
	@Column(name = "KN2_", length = 9)
	private String kn2;
	/**
	 * 附页KpNo.3标示流水线
	 */
	@Column(name = "KL3_", length = 1)
	private String kl3;
	/**
	 * 附页KpNo.3
	 */
	@Column(name = "KN3_", length = 9)
	private String kn3;
	/**
	 * 附页KpNo.4标示流水线
	 */
	@Column(name = "KL4_", length = 1)
	private String kl4;
	/**
	 * 附页KpNo.4
	 */
	@Column(name = "KN4_", length = 9)
	private String kn4;
	/**
	 * 制品尺寸-厚
	 */
	@Column(name = "HOU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double hou;
	/**
	 * 制品尺寸-宽
	 */
	@Column(name = "KUA_", columnDefinition = "numeric(6,2)")
	private Double kua;
	/**
	 * 制品尺寸-长
	 */
	@Column(name = "CAN_", columnDefinition = "numeric(6,2)")
	private Double can;
	/**
	 * 压延方向指定
	 */
	@Column(name = "YYA_", length = 1)
	private String yya;
	/**
	 * 换算尺寸-厚
	 */
	@Column(name = "HSH_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double hsh;
	/**
	 * 换算尺寸-宽
	 */
	@Column(name = "HSK_", columnDefinition = "numeric(6,2)")
	private Double hsk;
	/**
	 * 换算尺寸-长
	 */
	@Column(name = "HSC_", columnDefinition = "numeric(6,2)")
	private Double hsc;
	/**
	 * 订货区分
	 */
	@Column(name = "DHQ_", length = 1)
	private String dhq;
	/**
	 * 品种代码
	 */
	@Column(name = "PZN_", length = 2)
	private String pzn;
	/**
	 * 加工用途条件
	 */
	@Column(name = "CON_", length = 4)
	private String con;
	/**
	 * 规格代码
	 */
	@Column(name = "GGN_", length = 4)
	private String ggn;
	/**
	 * 交货期
	 */
	@Column(name = "JHQ_")
	private Date jhq;
	/**
	 * 采取指示量
	 */
	@Column(name = "CQZ_", columnDefinition = "numeric(7,0)")
	private Integer cqz;
	/**
	 * 装入卷板No/PileNo
	 */
	@Column(name = "ZRJ_", length = 11)
	private String zrj;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZN_", length = 1)
	private String zzn;
	/**
	 * PILE区分
	 */
	@Column(name = "PLQ_", length = 1)
	private String plq;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZ_")
	private Integer zpz;
	/**
	 * 卷板长(ZXFMT)
	 */
	@Column(name = "JBC_", columnDefinition = "numeric(6,0)")
	private Integer jbc;
	/**
	 * 溶接个数-酸洗
	 */
	@Column(name = "RJS_", precision = 0, scale = 1)
	private Integer rjs;
	/**
	 * 溶接个数-冷延
	 */
	@Column(name = "RJL_", precision = 0, scale = 1)
	private Integer rjl;
	/**
	 * 板厚有无不良
	 */
	@Column(name = "BHB_", length = 1)
	private String bhb;
	/**
	 * 业连NO
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;

	/**
	 * 前工程作业年月日
	 */
	@Column(name = "QGC_")
	private Date qgc;
	/**
	 * 现品尺寸-厚
	 */
	@Column(name = "XPH_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xph;
	/**
	 * 现品尺寸-宽
	 */
	@Column(name = "XPK_", columnDefinition = "numeric(6,2)")
	private Double xpk;
	/**
	 * 现品尺寸-长
	 */
	@Column(name = "XPC_", columnDefinition = "numeric(6,2)")
	private Double xpc;
	/**
	 * ETL流水线代码（实绩）
	 */
	@Column(name = "ELI_", length = 1)
	private String eli;
	/**
	 * 用户代码
	 */
	@Column(name = "USR_", length = 4)
	private String usr;

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getZsn() {
		return zsn;
	}

	public void setZsn(String zsn) {
		this.zsn = zsn;
	}

	public String getCao() {
		return cao;
	}

	public void setCao(String cao) {
		this.cao = cao;
	}

	/**
	 * @return the zrk
	 */
	public Double getZrk() {
		return zrk;
	}

	/**
	 * @param zrk
	 *            the zrk to set
	 */
	public void setZrk(Double zrk) {
		this.zrk = zrk;
	}

	/**
	 * @return the jdc
	 */
	public Double getJdc() {
		return jdc;
	}

	/**
	 * @param jdc
	 *            the jdc to set
	 */
	public void setJdc(Double jdc) {
		this.jdc = jdc;
	}

	/**
	 * @return the jbb
	 */
	public String getJbb() {
		return jbb;
	}

	/**
	 * @param jbb
	 *            the jbb to set
	 */
	public void setJbb(String jbb) {
		this.jbb = jbb;
	}

	/**
	 * @return the jbk
	 */
	public Double getJbk() {
		return jbk;
	}

	/**
	 * @param jbk
	 *            the jbk to set
	 */
	public void setJbk(Double jbk) {
		this.jbk = jbk;
	}

	/**
	 * @return the yun
	 */
	public String getYun() {
		return yun;
	}

	/**
	 * @param yun
	 *            the yun to set
	 */
	public void setYun(String yun) {
		this.yun = yun;
	}

	/**
	 * @return the fgm
	 */
	public String getFgm() {
		return fgm;
	}

	/**
	 * @param fgm
	 *            the fgm to set
	 */
	public void setFgm(String fgm) {
		this.fgm = fgm;
	}

	/**
	 * @return the fzx
	 */
	public Double getFzx() {
		return fzx;
	}

	/**
	 * @param fzx
	 *            the fzx to set
	 */
	public void setFzx(Double fzx) {
		this.fzx = fzx;
	}

	/**
	 * @return the fzs
	 */
	public Double getFzs() {
		return fzs;
	}

	/**
	 * @param fzs
	 *            the fzs to set
	 */
	public void setFzs(Double fzs) {
		this.fzs = fzs;
	}

	/**
	 * @return the ffx
	 */
	public Double getFfx() {
		return ffx;
	}

	/**
	 * @param ffx
	 *            the ffx to set
	 */
	public void setFfx(Double ffx) {
		this.ffx = ffx;
	}

	/**
	 * @return the ffs
	 */
	public Double getFfs() {
		return ffs;
	}

	/**
	 * @param ffs
	 *            the ffs to set
	 */
	public void setFfs(Double ffs) {
		this.ffs = ffs;
	}

	/**
	 * @return the flo
	 */
	public String getFlo() {
		return flo;
	}

	/**
	 * @param flo
	 *            the flo to set
	 */
	public void setFlo(String flo) {
		this.flo = flo;
	}

	/**
	 * @return the fac
	 */
	public String getFac() {
		return fac;
	}

	/**
	 * @param fac
	 *            the fac to set
	 */
	public void setFac(String fac) {
		this.fac = fac;
	}

	/**
	 * @return the hji
	 */
	public String getHji() {
		return hji;
	}

	/**
	 * @param hji
	 *            the hji to set
	 */
	public void setHji(String hji) {
		this.hji = hji;
	}

	/**
	 * @return the hdq
	 */
	public String getHdq() {
		return hdq;
	}

	/**
	 * @param hdq
	 *            the hdq to set
	 */
	public void setHdq(String hdq) {
		this.hdq = hdq;
	}

	/**
	 * @return the hxc
	 */
	public String getHxc() {
		return hxc;
	}

	/**
	 * @param hxc
	 *            the hxc to set
	 */
	public void setHxc(String hxc) {
		this.hxc = hxc;
	}

	/**
	 * @return the yty
	 */
	public String getYty() {
		return yty;
	}

	/**
	 * @param yty
	 *            the yty to set
	 */
	public void setYty(String yty) {
		this.yty = yty;
	}

	/**
	 * @return the yqt
	 */
	public String getYqt() {
		return yqt;
	}

	/**
	 * @param yqt
	 *            the yqt to set
	 */
	public void setYqt(String yqt) {
		this.yqt = yqt;
	}

	/**
	 * @return the abb
	 */
	public String getAbb() {
		return abb;
	}

	/**
	 * @param abb
	 *            the abb to set
	 */
	public void setAbb(String abb) {
		this.abb = abb;
	}

	/**
	 * @return the dhn
	 */
	public String getDhn() {
		return dhn;
	}

	/**
	 * @param dhn
	 *            the dhn to set
	 */
	public void setDhn(String dhn) {
		this.dhn = dhn;
	}

	/**
	 * @return the xsw
	 */
	public String getXsw() {
		return xsw;
	}

	/**
	 * @param xsw
	 *            the xsw to set
	 */
	public void setXsw(String xsw) {
		this.xsw = xsw;
	}

	/**
	 * @return the fpd
	 */
	public String getFpd() {
		return fpd;
	}

	/**
	 * @param fpd
	 *            the fpd to set
	 */
	public void setFpd(String fpd) {
		this.fpd = fpd;
	}

	/**
	 * @return the gzl
	 */
	public String getGzl() {
		return gzl;
	}

	/**
	 * @param gzl
	 *            the gzl to set
	 */
	public void setGzl(String gzl) {
		this.gzl = gzl;
	}

	/**
	 * @return the jia
	 */
	public Double getJia() {
		return jia;
	}

	/**
	 * @param jia
	 *            the jia to set
	 */
	public void setJia(Double jia) {
		this.jia = jia;
	}

	/**
	 * @return the mda
	 */
	public Double getMda() {
		return mda;
	}

	/**
	 * @param mda
	 *            the mda to set
	 */
	public void setMda(Double mda) {
		this.mda = mda;
	}

	/**
	 * @return the bda
	 */
	public Double getBda() {
		return bda;
	}

	/**
	 * @param bda
	 *            the bda to set
	 */
	public void setBda(Double bda) {
		this.bda = bda;
	}

	/**
	 * @return the z112
	 */
	public Double getZ112() {
		return z112;
	}

	/**
	 * @param z112
	 *            the z112 to set
	 */
	public void setZ112(Double z112) {
		this.z112 = z112;
	}

	/**
	 * @return the jhd
	 */
	public String getJhd() {
		return jhd;
	}

	/**
	 * @param jhd
	 *            the jhd to set
	 */
	public void setJhd(String jhd) {
		this.jhd = jhd;
	}

	/**
	 * @return the njn
	 */
	public String getNjn() {
		return njn;
	}

	/**
	 * @param njn
	 *            the njn to set
	 */
	public void setNjn(String njn) {
		this.njn = njn;
	}

	/**
	 * @return the njb
	 */
	public String getNjb() {
		return njb;
	}

	/**
	 * @param njb
	 *            the njb to set
	 */
	public void setNjb(String njb) {
		this.njb = njb;
	}

	/**
	 * @return the pro
	 */
	public String getPro() {
		return pro;
	}

	/**
	 * @param pro
	 *            the pro to set
	 */
	public void setPro(String pro) {
		this.pro = pro;
	}

	/**
	 * @return the jck
	 */
	public String getJck() {
		return jck;
	}

	/**
	 * @param jck
	 *            the jck to set
	 */
	public void setJck(String jck) {
		this.jck = jck;
	}

	/**
	 * @return the jcz
	 */
	public String getJcz() {
		return jcz;
	}

	/**
	 * @param jcz
	 *            the jcz to set
	 */
	public void setJcz(String jcz) {
		this.jcz = jcz;
	}

	/**
	 * @return the zpc
	 */
	public String getZpc() {
		return zpc;
	}

	/**
	 * @param zpc
	 *            the zpc to set
	 */
	public void setZpc(String zpc) {
		this.zpc = zpc;
	}

	/**
	 * @return the dhg
	 */
	public String getDhg() {
		return dhg;
	}

	/**
	 * @param dhg
	 *            the dhg to set
	 */
	public void setDhg(String dhg) {
		this.dhg = dhg;
	}

	/**
	 * @return the dhf
	 */
	public String getDhf() {
		return dhf;
	}

	/**
	 * @param dhf
	 *            the dhf to set
	 */
	public void setDhf(String dhf) {
		this.dhf = dhf;
	}

	/**
	 * @return the hsz
	 */
	public Double getHsz() {
		return hsz;
	}

	/**
	 * @param hsz
	 *            the hsz to set
	 */
	public void setHsz(Double hsz) {
		this.hsz = hsz;
	}

	/**
	 * @return the hxz
	 */
	public Double getHxz() {
		return hxz;
	}

	/**
	 * @param hxz
	 *            the hxz to set
	 */
	public void setHxz(Double hxz) {
		this.hxz = hxz;
	}

	/**
	 * @return the msz
	 */
	public Double getMsz() {
		return msz;
	}

	/**
	 * @param msz
	 *            the msz to set
	 */
	public void setMsz(Double msz) {
		this.msz = msz;
	}

	/**
	 * @return the mxz
	 */
	public Double getMxz() {
		return mxz;
	}

	/**
	 * @param mxz
	 *            the mxz to set
	 */
	public void setMxz(Double mxz) {
		this.mxz = mxz;
	}

	/**
	 * @return the ksz
	 */
	public Double getKsz() {
		return ksz;
	}

	/**
	 * @param ksz
	 *            the ksz to set
	 */
	public void setKsz(Double ksz) {
		this.ksz = ksz;
	}

	/**
	 * @return the kxz
	 */
	public Double getKxz() {
		return kxz;
	}

	/**
	 * @param kxz
	 *            the kxz to set
	 */
	public void setKxz(Double kxz) {
		this.kxz = kxz;
	}

	/**
	 * @return the csz
	 */
	public Double getCsz() {
		return csz;
	}

	/**
	 * @param csz
	 *            the csz to set
	 */
	public void setCsz(Double csz) {
		this.csz = csz;
	}

	/**
	 * @return the cxz
	 */
	public Double getCxz() {
		return cxz;
	}

	/**
	 * @param cxz
	 *            the cxz to set
	 */
	public void setCxz(Double cxz) {
		this.cxz = cxz;
	}

	/**
	 * @return the bzz
	 */
	public Integer getBzz() {
		return bzz;
	}

	/**
	 * @param bzz
	 *            the bzz to set
	 */
	public void setBzz(Integer bzz) {
		this.bzz = bzz;
	}

	/**
	 * @return the piz
	 */
	public Double getPiz() {
		return piz;
	}

	/**
	 * @param piz
	 *            the piz to set
	 */
	public void setPiz(Double piz) {
		this.piz = piz;
	}

	/**
	 * @return the xsh
	 */
	public Integer getXsh() {
		return xsh;
	}

	/**
	 * @param xsh
	 *            the xsh to set
	 */
	public void setXsh(Integer xsh) {
		this.xsh = xsh;
	}

	/**
	 * @return the bbz
	 */
	public Double getBbz() {
		return bbz;
	}

	/**
	 * @param bbz
	 *            the bbz to set
	 */
	public void setBbz(Double bbz) {
		this.bbz = bbz;
	}

	/**
	 * @return the ltk
	 */
	public String getLtk() {
		return ltk;
	}

	/**
	 * @param ltk
	 *            the ltk to set
	 */
	public void setLtk(String ltk) {
		this.ltk = ltk;
	}

	/**
	 * @return the ltx
	 */
	public Integer getLtx() {
		return ltx;
	}

	/**
	 * @param ltx
	 *            the ltx to set
	 */
	public void setLtx(Integer ltx) {
		this.ltx = ltx;
	}

	/**
	 * @return the bzx
	 */
	public Double getBzx() {
		return bzx;
	}

	/**
	 * @param bzx
	 *            the bzx to set
	 */
	public void setBzx(Double bzx) {
		this.bzx = bzx;
	}

	/**
	 * @return the dmf
	 */
	public String getDmf() {
		return dmf;
	}

	/**
	 * @param dmf
	 *            the dmf to set
	 */
	public void setDmf(String dmf) {
		this.dmf = dmf;
	}

	/**
	 * @return the dmz
	 */
	public Integer getDmz() {
		return dmz;
	}

	/**
	 * @param dmz
	 *            the dmz to set
	 */
	public void setDmz(Integer dmz) {
		this.dmz = dmz;
	}

	/**
	 * @return the bzc
	 */
	public Integer getBzc() {
		return bzc;
	}

	/**
	 * @param bzc
	 *            the bzc to set
	 */
	public void setBzc(Integer bzc) {
		this.bzc = bzc;
	}

	/**
	 * @return the kbs
	 */
	public Double getKbs() {
		return kbs;
	}

	/**
	 * @param kbs
	 *            the kbs to set
	 */
	public void setKbs(Double kbs) {
		this.kbs = kbs;
	}

	/**
	 * @return the kbx
	 */
	public Double getKbx() {
		return kbx;
	}

	/**
	 * @param kbx
	 *            the kbx to set
	 */
	public void setKbx(Double kbx) {
		this.kbx = kbx;
	}

	/**
	 * @return the kba
	 */
	public String getKba() {
		return kba;
	}

	/**
	 * @param kba
	 *            the kba to set
	 */
	public void setKba(String kba) {
		this.kba = kba;
	}

	/**
	 * @return the spe
	 */
	public String getSpe() {
		return spe;
	}

	/**
	 * @param spe
	 *            the spe to set
	 */
	public void setSpe(String spe) {
		this.spe = spe;
	}

	/**
	 * @return the nam
	 */
	public String getNam() {
		return nam;
	}

	/**
	 * @param nam
	 *            the nam to set
	 */
	public void setNam(String nam) {
		this.nam = nam;
	}

	/**
	 * @return the kl1
	 */
	public String getKl1() {
		return kl1;
	}

	/**
	 * @param kl1
	 *            the kl1 to set
	 */
	public void setKl1(String kl1) {
		this.kl1 = kl1;
	}

	/**
	 * @return the kn1
	 */
	public String getKn1() {
		return kn1;
	}

	/**
	 * @param kn1
	 *            the kn1 to set
	 */
	public void setKn1(String kn1) {
		this.kn1 = kn1;
	}

	/**
	 * @return the kl2
	 */
	public String getKl2() {
		return kl2;
	}

	/**
	 * @param kl2
	 *            the kl2 to set
	 */
	public void setKl2(String kl2) {
		this.kl2 = kl2;
	}

	/**
	 * @return the kn2
	 */
	public String getKn2() {
		return kn2;
	}

	/**
	 * @param kn2
	 *            the kn2 to set
	 */
	public void setKn2(String kn2) {
		this.kn2 = kn2;
	}

	/**
	 * @return the hou
	 */
	public Double getHou() {
		return hou;
	}

	/**
	 * @param hou
	 *            the hou to set
	 */
	public void setHou(Double hou) {
		this.hou = hou;
	}

	/**
	 * @return the kua
	 */
	public Double getKua() {
		return kua;
	}

	/**
	 * @param kua
	 *            the kua to set
	 */
	public void setKua(Double kua) {
		this.kua = kua;
	}

	/**
	 * @return the can
	 */
	public Double getCan() {
		return can;
	}

	/**
	 * @param can
	 *            the can to set
	 */
	public void setCan(Double can) {
		this.can = can;
	}

	/**
	 * @return the yya
	 */
	public String getYya() {
		return yya;
	}

	/**
	 * @param yya
	 *            the yya to set
	 */
	public void setYya(String yya) {
		this.yya = yya;
	}

	/**
	 * @return the hsh
	 */
	public Double getHsh() {
		return hsh;
	}

	/**
	 * @param hsh
	 *            the hsh to set
	 */
	public void setHsh(Double hsh) {
		this.hsh = hsh;
	}

	/**
	 * @return the hsk
	 */
	public Double getHsk() {
		return hsk;
	}

	/**
	 * @param hsk
	 *            the hsk to set
	 */
	public void setHsk(Double hsk) {
		this.hsk = hsk;
	}

	/**
	 * @return the hsc
	 */
	public Double getHsc() {
		return hsc;
	}

	/**
	 * @param hsc
	 *            the hsc to set
	 */
	public void setHsc(Double hsc) {
		this.hsc = hsc;
	}

	/**
	 * @return the dhq
	 */
	public String getDhq() {
		return dhq;
	}

	/**
	 * @param dhq
	 *            the dhq to set
	 */
	public void setDhq(String dhq) {
		this.dhq = dhq;
	}

	/**
	 * @return the pzn
	 */
	public String getPzn() {
		return pzn;
	}

	/**
	 * @param pzn
	 *            the pzn to set
	 */
	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	/**
	 * @return the con
	 */
	public String getCon() {
		return con;
	}

	/**
	 * @param con
	 *            the con to set
	 */
	public void setCon(String con) {
		this.con = con;
	}

	/**
	 * @return the ggn
	 */
	public String getGgn() {
		return ggn;
	}

	/**
	 * @param ggn
	 *            the ggn to set
	 */
	public void setGgn(String ggn) {
		this.ggn = ggn;
	}

	/**
	 * @return the cqz
	 */
	public Integer getCqz() {
		return cqz;
	}

	/**
	 * @param cqz
	 *            the cqz to set
	 */
	public void setCqz(Integer cqz) {
		this.cqz = cqz;
	}

	/**
	 * @return the zrj
	 */
	public String getZrj() {
		return zrj;
	}

	/**
	 * @param zrj
	 *            the zrj to set
	 */
	public void setZrj(String zrj) {
		this.zrj = zrj;
	}

	/**
	 * @return the zzn
	 */
	public String getZzn() {
		return zzn;
	}

	/**
	 * @param zzn
	 *            the zzn to set
	 */
	public void setZzn(String zzn) {
		this.zzn = zzn;
	}

	/**
	 * @return the plq
	 */
	public String getPlq() {
		return plq;
	}

	/**
	 * @param plq
	 *            the plq to set
	 */
	public void setPlq(String plq) {
		this.plq = plq;
	}

	/**
	 * @return the zpz
	 */
	public Integer getZpz() {
		return zpz;
	}

	/**
	 * @param zpz
	 *            the zpz to set
	 */
	public void setZpz(Integer zpz) {
		this.zpz = zpz;
	}

	/**
	 * @return the jbc
	 */
	public Integer getJbc() {
		return jbc;
	}

	/**
	 * @param jbc
	 *            the jbc to set
	 */
	public void setJbc(Integer jbc) {
		this.jbc = jbc;
	}

	/**
	 * @return the rjs
	 */
	public Integer getRjs() {
		return rjs;
	}

	/**
	 * @param rjs
	 *            the rjs to set
	 */
	public void setRjs(Integer rjs) {
		this.rjs = rjs;
	}

	/**
	 * @return the rjl
	 */
	public Integer getRjl() {
		return rjl;
	}

	/**
	 * @param rjl
	 *            the rjl to set
	 */
	public void setRjl(Integer rjl) {
		this.rjl = rjl;
	}

	/**
	 * @return the bhb
	 */
	public String getBhb() {
		return bhb;
	}

	/**
	 * @param bhb
	 *            the bhb to set
	 */
	public void setBhb(String bhb) {
		this.bhb = bhb;
	}

	/**
	 * @return the qgc
	 */
	public Date getQgc() {
		return qgc;
	}

	/**
	 * @param qgc
	 *            the qgc to set
	 */
	public void setQgc(Date qgc) {
		this.qgc = qgc;
	}

	/**
	 * @return the xph
	 */
	public Double getXph() {
		return xph;
	}

	/**
	 * @param xph
	 *            the xph to set
	 */
	public void setXph(Double xph) {
		this.xph = xph;
	}

	/**
	 * @return the xpk
	 */
	public Double getXpk() {
		return xpk;
	}

	/**
	 * @param xpk
	 *            the xpk to set
	 */
	public void setXpk(Double xpk) {
		this.xpk = xpk;
	}

	/**
	 * @return the xpc
	 */
	public Double getXpc() {
		return xpc;
	}

	/**
	 * @param xpc
	 *            the xpc to set
	 */
	public void setXpc(Double xpc) {
		this.xpc = xpc;
	}

	/**
	 * @return the jhq
	 */
	public Date getJhq() {
		return jhq;
	}

	/**
	 * @param jhq
	 *            the jhq to set
	 */
	public void setJhq(Date jhq) {
		this.jhq = jhq;
	}

	/**
	 * @return the ylno
	 */
	public String getYlno() {
		return ylno;
	}

	/**
	 * @param ylno
	 *            the ylno to set
	 */
	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getKl3() {
		return kl3;
	}

	public void setKl3(String kl3) {
		this.kl3 = kl3;
	}

	public String getKn3() {
		return kn3;
	}

	public void setKn3(String kn3) {
		this.kn3 = kn3;
	}

	public String getKl4() {
		return kl4;
	}

	public void setKl4(String kl4) {
		this.kl4 = kl4;
	}

	public String getKn4() {
		return kn4;
	}

	public void setKn4(String kn4) {
		this.kn4 = kn4;
	}

	public String getEli() {
		return eli;
	}

	public void setEli(String eli) {
		this.eli = eli;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
