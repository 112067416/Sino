package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.coco.core.util.NumberUtils;

/**
 * 原材卷板
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_YCAITP")
public class YcaiTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 将制品重量由吨转换为千克
	 * </p>
	 * 
	 * @param tun
	 */
	public void setTun(Double tun) {
		this.zpzl = NumberUtils.formatDouToInt(tun * 1000, 0);
	}

	/**
	 * <p>
	 * 将制品重量由千克转换为吨
	 * </p>
	 * 
	 * @return Double
	 */
	public Double getTun() {
		return this.zpzl / 1000d;
	}

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 更新程序名
	 */
	@Column(name = "PROG_", length = 8)
	private String prog;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 预备1
	 */
	@Column(name = "RSV1_", length = 40)
	private String rsv1;

	/**
	 * 预备2
	 */
	@Column(name = "RSV2_", columnDefinition = "numeric(20,0)")
	private BigDecimal rsv2;

	/**
	 * 原材卷板No.
	 */
	@Id
	@Column(name = "JBNO_", length = 7)
	private String jbno;
	/**
	 * 现品状况
	 */
	@Column(name = "XPZK_", length = 1)
	private String xpzk;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 指示书No
	 */
	@Column(name = "ZSNO_", length = 6)
	private String zsno;
	/**
	 * 分配No
	 */
	@Column(name = "FPNO_", length = 8)
	private String fpno;
	/**
	 * 分配/余材标记
	 */
	@Column(name = "FPYC_", length = 1)
	private String fpyc;
	/**
	 * 进度标记.ETL指示
	 */
	@Column(name = "JDEZ_", length = 1)
	private String jdez;
	/**
	 * 进度标记.ETL实绩
	 */
	@Column(name = "JDES_", length = 1)
	private String jdes;
	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;
	/**
	 * 规格代码（马口铁），中日达规格
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

	/**
	 * 原板导入规格()
	 */
	@Column(name = "YBGG_", length = 16)
	private String ybgg;

	/**
	 * 运用规格（马口铁），中日达规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 规格略称（马口铁），中日达规格
	 */
	@Column(name = "GGNM_", length = 40)
	private String ggnm;
	/**
	 * 钢种类型
	 */
	@Column(name = "GZLX_", length = 1)
	private String gzlx;
	/**
	 * 表面精加工符号
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 内径代码
	 */
	@Column(name = "NJNO_", length = 1)
	private String njno;
	/**
	 * 制品尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;
	/**
	 * 制品尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;
	/**
	 * 压延方向指定
	 */
	@Column(name = "YYAN_", length = 1)
	private String yyan;
	/**
	 * 换算尺寸.厚
	 */
	@Column(name = "HNGH_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double hngh;
	/**
	 * 换算尺寸.宽
	 */
	@Column(name = "HNGK_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double hngk;
	/**
	 * 换算尺寸.长
	 */
	@Column(name = "HNGC_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double hngc;
	/**
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 原材客户略称
	 */
	@Column(name = "YCBR_", length = 20)
	private String ycbr;
	/**
	 * 现品尺寸.厚
	 */
	@Column(name = "XPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xpho;
	/**
	 * 现品尺寸.宽
	 */
	@Column(name = "XPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkn;
	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
	/**
	 * 现品尺寸公差.厚.下限
	 */
	@Column(name = "XPHX_", columnDefinition = "numeric(4,3)")
	private Double xphx;
	/**
	 * 现品尺寸公差.厚.上限
	 */
	@Column(name = "XPHS_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xphs;
	/**
	 * 现品尺寸公差.宽.下限
	 */
	@Column(name = "XPKX_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkx;
	/**
	 * 现品尺寸公差.宽.上限
	 */
	@Column(name = "XPKS_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpks;
	/**
	 * 计算重量
	 */
	@Column(name = "JSZL_")
	private Integer jszl;
	/**
	 * 实际重量
	 */
	@Column(name = "SJZL_")
	private Integer sjzl;
	/**
	 * 净重量
	 */
	@Column(name = "JNZL_")
	private Integer jnzl;
	/**
	 * 毛重量
	 */
	@Column(name = "MAZL_")
	private Integer mazl;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;
	/**
	 * m单重
	 */
	@Column(name = "MDAN_")
	private Integer mdan;
	/**
	 * 产量
	 */
	@Column(name = "CHAN_", length = 1)
	private String chan;
	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;
	/**
	 * 处置代码
	 */
	@Column(name = "CZDM_", length = 1)
	private String czdm;
	/**
	 * 缺陷代码
	 */
	@Column(name = "QQDM_", length = 2)
	private String qqdm;
	/**
	 * 检定员
	 */
	@Column(name = "JDYN_", length = 2)
	private String jdyn;
	/**
	 * 计数员
	 */
	@Column(name = "JSYN_", length = 2)
	private String jsyn;
	/**
	 * 溶接个数.酸洗
	 */
	@Column(name = "RJSX_")
	private Integer rjsx;
	/**
	 * 溶接个数.冷延
	 */
	@Column(name = "RJLY_")
	private Integer rjly;
	/**
	 * 堆场
	 */
	@Column(name = "DUIC_", length = 4)
	private String duic;
	/**
	 * 库位
	 */
	@Column(name = "KW_", length = 4)
	private String kw;

	/**
	 * 堆场搬入年月日
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "DUIB_")
	private Date duib;
	/**
	 * 原板收货年月日
	 */
	@Column(name = "YSUO_")
	private Date ysuo;
	/**
	 * 作业停止标志
	 */
	@Column(name = "ZTBJ_", length = 2)
	private String ztbj;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 是否保税
	 */
	@Column(name = "SFBS_", length = 1)
	private String sfbs;
	/**
	 * 制造商卷板No
	 */
	@Column(name = "ZZSJ_", length = 15)
	private String zzsj;
	/**
	 * 制造商制品NO
	 */
	@Column(name = "ZZZP_", length = 10)
	private String zzzp;
	/**
	 * 供应商合同号
	 */
	@Column(name = "YBNO_", length = 8)
	private String ybno;
	/**
	 * 供应商合同行号
	 */
	@Column(name = "LINE_", length = 3)
	private String line;
	/**
	 * 业连NO
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;
	/**
	 * 原板规格略称
	 */
	@Column(name = "YBLC_", length = 40)
	private String yblc;
	/**
	 * 制造年月日
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "ZZNY_")
	private Date zzny;
	/**
	 * BL-Date(装船日)
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "BLNY_")
	private Date blny;
	/**
	 * 船名
	 */
	@Column(name = "SHIP_", length = 20)
	private String ship;
	/**
	 * 商社代码
	 */
	@Column(name = "SSNO_", length = 4)
	private String ssno;
	/**
	 * 生产状态
	 */
	@Column(name = "SCZT_", length = 1)
	private String sczt;
	/**
	 * 通货区分
	 */
	@Column(name = "THQF_", length = 1)
	private String thqf;
	/**
	 * 采购单价
	 */
	@Column(name = "CGDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double cgdj;
	/**
	 * 确定标记
	 */
	@Column(name = "QDBJ_", length = 1)
	private String qdbj;
	/**
	 * 删除标记： 0-初始[未删除]，1-已实绩终了，2-已消灭，3-已出货， 4-异常处理删除
	 */
	@Column(name = "SCBJ_", length = 1)
	private String scbj;

	/**
	 * 商品编号
	 */
	@Column(name = "SPBH_", length = 1)
	private String spbh;

	/**
	 * 实际生产完成时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "SCSJ_")
	private Date scsj;

	/**
	 * 实绩录入完成时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "SJSJ_")
	private Date sjsj;
	/**
	 * 入侧端COIL/PILE
	 */
	@Column(name = "RCZPNO_", length = 11)
	private String rczpno;
	/**
	 * 班组
	 */
	@Column(name = "BANZ_", length = 2)
	private String banz;
	/**
	 * ETL流水线代码（实绩）
	 */
	@Column(name = "ELIN_", length = 1)
	private String elin;

	/**
	 * <p>
	 * 装入重量
	 * </p>
	 */
	@Column(name = "ZRZL_")
	private Integer zrzl;

	/**
	 * ETL 入侧 备用卷是否确认
	 */
	@Column(name = "SFQR_", length = 1)
	private String sfqr;

	/**
	 * 原板成本费用（每吨的成本）
	 */
	@Column(name = "YBCB_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double ybcb;

	/**
	 * 原板标签打印标记
	 */
	@Column(name = "SFDY_")
	private boolean sfdy;

	/**
	 * 硬度值
	 */
	@Column(name = "YING_")
	private Integer ying;

	/**
	 * 硬度录入人
	 */
	@Column(name = "YJDR_", length = 4)
	private String yjdr;

	/**
	 * 硬度录入时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "YDSJ_")
	private Date ydsj;
	/**
	 * 原板度锡开始时间
	 */
	@Column(name = "DXKS_")
	private Date dxks;
	/**
	 * 原板度锡结束时间
	 */
	@Column(name = "DXJS_")
	private Date dxjs;
	/**
	 * <p>
	 * 垫木状态
	 * </p>
	 */
	@Column(name = "SFDM_", length = 1)
	private String sfdm;

	/**
	 * 是否紧急
	 */
	@Column(name = "JINJ_", length = 1)
	private String jinj;

	/**
	 * 制品No
	 */
	@Column(name = "ZPNO_", length = 24)
	private String zpno;

	/**
	 * 前缀
	 */
	@Column(name = "PREX_", length = 4)
	private String prex;

	/**
	 * 原制造商卷板NO
	 */
	@Column(name = "YZZSJ_", length = 13)
	private String yzzsj;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Date getUpda() {
		return upda;
	}

	public void setUpda(Date upda) {
		this.upda = upda;
	}

	public String getProg() {
		return prog;
	}

	public void setProg(String prog) {
		this.prog = prog;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRsv1() {
		return rsv1;
	}

	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}

	public BigDecimal getRsv2() {
		return rsv2;
	}

	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public String getFpyc() {
		return fpyc;
	}

	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

	public String getJdez() {
		return jdez;
	}

	public void setJdez(String jdez) {
		this.jdez = jdez;
	}

	public String getJdes() {
		return jdes;
	}

	public void setJdes(String jdes) {
		this.jdes = jdes;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getYbgg() {
		return ybgg;
	}

	public void setYbgg(String ybgg) {
		this.ybgg = ybgg;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getGgnm() {
		return ggnm;
	}

	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
	}

	public String getGzlx() {
		return gzlx;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		if (houu == null) {
			this.houu = null;
			return;
		}
		this.houu = NumberUtils.format(houu, 3);
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		if (kuan == null) {
			this.kuan = null;
			return;
		}
		this.kuan = NumberUtils.format(kuan, 3);
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getYyan() {
		return yyan;
	}

	public void setYyan(String yyan) {
		this.yyan = yyan;
	}

	public Double getHngh() {
		return hngh;
	}

	public void setHngh(Double hngh) {
		this.hngh = hngh;
	}

	public Double getHngk() {
		return hngk;
	}

	public void setHngk(Double hngk) {
		this.hngk = hngk;
	}

	public Double getHngc() {
		return hngc;
	}

	public void setHngc(Double hngc) {
		this.hngc = hngc;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getYcbr() {
		return ycbr;
	}

	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		if (xpho == null) {
			this.xpho = null;
			return;
		}
		this.xpho = NumberUtils.format(xpho, 3);
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		if (xpkn == null) {
			this.xpkn = null;
			return;
		}
		this.xpkn = NumberUtils.format(xpkn, 3);
	}

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	public Double getXphx() {
		return xphx;
	}

	public void setXphx(Double xphx) {
		this.xphx = xphx;
	}

	public Double getXphs() {
		return xphs;
	}

	public void setXphs(Double xphs) {
		this.xphs = xphs;
	}

	public Double getXpkx() {
		return xpkx;
	}

	public void setXpkx(Double xpkx) {
		this.xpkx = xpkx;
	}

	public Double getXpks() {
		return xpks;
	}

	public void setXpks(Double xpks) {
		this.xpks = xpks;
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

	public Integer getJnzl() {
		return jnzl;
	}

	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	public Integer getMazl() {
		return mazl;
	}

	public void setMazl(Integer mazl) {
		this.mazl = mazl;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Integer getMdan() {
		return mdan;
	}

	public void setMdan(Integer mdan) {
		this.mdan = mdan;
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

	public String getCzdm() {
		return czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	public String getQqdm() {
		return qqdm;
	}

	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
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

	public Integer getRjsx() {
		return rjsx;
	}

	public void setRjsx(Integer rjsx) {
		this.rjsx = rjsx;
	}

	public Integer getRjly() {
		return rjly;
	}

	public void setRjly(Integer rjly) {
		this.rjly = rjly;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public Date getDuib() {
		return duib;
	}

	public void setDuib(Date duib) {
		this.duib = duib;
	}

	public Date getYsuo() {
		return ysuo;
	}

	public void setYsuo(Date ysuo) {
		this.ysuo = ysuo;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getSfbs() {
		return sfbs;
	}

	public void setSfbs(String sfbs) {
		this.sfbs = sfbs;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String getZzzp() {
		return zzzp;
	}

	public void setZzzp(String zzzp) {
		this.zzzp = zzzp;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getYblc() {
		return yblc;
	}

	public void setYblc(String yblc) {
		this.yblc = yblc;
	}

	public Date getZzny() {
		return zzny;
	}

	public void setZzny(Date zzny) {
		this.zzny = zzny;
	}

	public Date getBlny() {
		return blny;
	}

	public void setBlny(Date blny) {
		this.blny = blny;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public String getSczt() {
		return sczt;
	}

	public void setSczt(String sczt) {
		this.sczt = sczt;
	}

	public String getThqf() {
		return thqf;
	}

	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	public Double getCgdj() {
		return cgdj;
	}

	public void setCgdj(Double cgdj) {
		this.cgdj = cgdj;
	}

	public String getQdbj() {
		return qdbj;
	}

	public void setQdbj(String qdbj) {
		this.qdbj = qdbj;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public Date getScsj() {
		return scsj;
	}

	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}

	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

	public String getBanz() {
		return banz;
	}

	public void setBanz(String banz) {
		this.banz = banz;
	}

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public Integer getZrzl() {
		return zrzl;
	}

	public void setZrzl(Integer zrzl) {
		this.zrzl = zrzl;
	}

	public String getSfqr() {
		return sfqr;
	}

	public void setSfqr(String sfqr) {
		this.sfqr = sfqr;
	}

	public Double getYbcb() {
		return ybcb;
	}

	public void setYbcb(Double ybcb) {
		this.ybcb = ybcb;
	}

	public boolean isSfdy() {
		return sfdy;
	}

	public void setSfdy(boolean sfdy) {
		this.sfdy = sfdy;
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getYjdr() {
		return yjdr;
	}

	public void setYjdr(String yjdr) {
		this.yjdr = yjdr;
	}

	public Date getYdsj() {
		return ydsj;
	}

	public void setYdsj(Date ydsj) {
		this.ydsj = ydsj;
	}

	public Date getDxks() {
		return dxks;
	}

	public void setDxks(Date dxks) {
		this.dxks = dxks;
	}

	public Date getDxjs() {
		return dxjs;
	}

	public void setDxjs(Date dxjs) {
		this.dxjs = dxjs;
	}

	public String getSfdm() {
		return sfdm;
	}

	public void setSfdm(String sfdm) {
		this.sfdm = sfdm;
	}

	public String getJinj() {
		return jinj;
	}

	public void setJinj(String jinj) {
		this.jinj = jinj;
	}

	public String getZpno() {
		return zpno;
	}

	public void setZpno(String zpno) {
		this.zpno = zpno;
	}

	public String getPrex() {
		return prex;
	}

	public void setPrex(String prex) {
		this.prex = prex;
	}

	public String getYzzsj() {
		return yzzsj;
	}

	public void setYzzsj(String yzzsj) {
		this.yzzsj = yzzsj;
	}

}
