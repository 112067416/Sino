package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * <p>
 * 订货DB
 * </p>
 * <p>
 * create: 2011-1-3 下午02:57:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_DHUOTP")
@IdClass(DhuoTpPk.class)
public class DhuoTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private Date upda;

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
	 * 订货No
	 */
	@Id
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Id
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 分配区分
	 */
	@Column(name = "FPQF_", length = 1)
	private String fpqf;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

	/**
	 * 规格略称
	 */
	@Column(name = "GGNM_", length = 30)
	private String ggnm;

	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;

	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;

	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;

	/**
	 * 订货尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;

	/**
	 * 订货尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;

	/**
	 * 订货尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;
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
	 * 差厚镀锡
	 */
	@Column(name = "CHDX_", length = 2)
	private String chdx;
	/**
	 * 压延方向指示标志
	 */
	@Column(name = "YYAN_", length = 1)
	private String yyan;
	/**
	 * 锯齿形式
	 */
	@Column(name = "JCXS_", length = 4)
	private String jcxs;
	/**
	 * K-Plate
	 */
	@Column(name = "PLAT_", length = 1)
	private String plat;
	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;

	/**
	 * 加工用途名称
	 */
	@Column(name = "CDNM_", length = 24)
	private String cdnm;

	/**
	 * 表面加工精度
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;
	/**
	 * 内径
	 */
	@Column(name = "NEIJ_", length = 3)
	private String neij;
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
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 送货地点
	 */
	@Column(name = "ADDR_", length = 512)
	private String addr;

	/**
	 * 交货期
	 */
	@Column(name = "JHQI_")
	private Date jhqi;

	/**
	 * 合同期
	 */
	@Column(name = "HTQI_", length = 8)
	private String htqi;

	/**
	 * 合同重量（吨）
	 */
	@Column(name = "HTZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double htzl;
	/**
	 * 交货重量内容
	 */
	@Column(name = "JHNR_", length = 1)
	private String jhnr;
	/**
	 * 捆包指定张数.区分
	 */
	@Column(name = "KBSQ_", length = 1)
	private String kbsq;
	/**
	 * 捆包指定张数.值
	 */
	@Column(name = "KBSZ_", length = 4)
	private String kbsz;
	/**
	 * 捆包指定重量.区分
	 */
	@Column(name = "KBZQ_", length = 1)
	private String kbzq;
	/**
	 * 捆包指定重量.单位
	 */
	@Column(name = "KBZD_", length = 1)
	private String kbzd;
	/**
	 * 捆包指定重量.上限
	 */
	@Column(name = "KBZS_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double kbzs;
	/**
	 * 捆包指定重量.下限
	 */
	@Column(name = "KBZX_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double kbzx;
	/**
	 * 化成处理
	 */
	@Column(name = "HUAC_", length = 3)
	private String huac;
	/**
	 * 销售溶接部不可
	 */
	@Column(name = "RJIE_", length = 1)
	private String rjie;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;
	/**
	 * 货标标志
	 */
	@Column(name = "HBBJ_", length = 1)
	private String hbbj;
	/**
	 * 货标设定完了标志
	 */
	@Column(name = "HBWL_", length = 1)
	private String hbwl;
	/**
	 * 交货允许.区分
	 */
	@Column(name = "JHQF_", length = 1)
	private String jhqf;
	/**
	 * 交货允许值.正值(+)
	 */
	@Column(name = "JHZZ_")
	private Integer jhzz;
	/**
	 * 交货允许值.负值(-)
	 */
	@Column(name = "JHFZ_")
	private Integer jhfz;
	/**
	 * 特殊BNo.
	 */
	@Column(name = "TBNO_", length = 1)
	private String tbno;
	/**
	 * 特殊CNo.
	 */
	@Column(name = "TCNO_", length = 1)
	private String tcno;
	/**
	 * 用户标签名表示
	 */
	@Column(name = "YHBQ_", length = 1)
	private String yhbq;
	/**
	 * 商社代码
	 */
	@Column(name = "SSNO_", length = 4)
	private String ssno;
	/**
	 * 商社名称
	 */
	@Column(name = "SSNM_", length = 512)
	private String ssnm;
	/**
	 * 剪边
	 */
	@Column(name = "JIAN_", length = 1)
	private String jian;
	/**
	 * 合金层
	 */
	@Column(name = "HJIN_", length = 3)
	private String hjin;
	/**
	 * 涂油种类
	 */
	@Column(name = "YTYP_", length = 1)
	private String ytyp;
	/**
	 * 涂油量
	 */
	@Column(name = "YQTY_", length = 2)
	private String yqty;
	/**
	 * 分配等级
	 */
	@Column(name = "FPDJ_", length = 3)
	private String fpdj;
	/**
	 * 直角度
	 */

	@Column(name = "JIAO_", columnDefinition = "numeric(2,1)", scale = 2, precision = 1)
	private Double jiao;

	/**
	 * 内径保护筒
	 */
	@Column(name = "NJBH_", length = 1)
	private String njbh;
	/**
	 * 保护标志
	 */
	@Column(name = "PROT_", length = 1)
	private String prot;
	/**
	 * 零头下限.单位
	 */
	@Column(name = "LTDW_", length = 1)
	private String ltdw;
	/**
	 * 零头下限.值
	 */
	@Column(name = "LTZI_")
	private Integer ltzi;

	/**
	 * 捆包计算
	 */
	@Column(name = "KBJS_", length = 1)
	private String kbjs;

	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * 包装材料重量（Kg）
	 */
	@Column(name = "BZCL_", precision = 0, scale = 3)
	private Integer bzcl;
	/**
	 * 垫木重量(Kg)
	 */
	@Column(name = "DMZL_", precision = 0, scale = 3)
	private Integer dmzl;
	/**
	 * 附页KPNO.1标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN1_FLAG_", length = 1)
	private String kpn1Flag;
	/**
	 * 附页KPNO.1
	 */
	@Column(name = "KPN1_", length = 9)
	private String kpn1;
	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN2_FLAG_", length = 1)
	private String kpn2Flag;
	/**
	 * 附页KPNO.2
	 */
	@Column(name = "KPN2_", length = 9)
	private String kpn2;
	/**
	 * 附页KPNO.3标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN3_FLAG_", length = 1)
	private String kpn3Flag;
	/**
	 * 附页KPNO.3
	 */
	@Column(name = "KPN3_", length = 9)
	private String kpn3;
	/**
	 * 附页KPNO.4标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN4_FLAG_", length = 1)
	private String kpn4Flag;
	/**
	 * 附页KPNO.4
	 */
	@Column(name = "KPN4_", length = 9)
	private String kpn4;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 装入宽
	 */
	@Column(name = "ZRKN_", columnDefinition = "numeric(6,2)", precision = 6, scale = 2)
	private Double zrkn;
	/**
	 * 硬度.上限值
	 */
	@Column(name = "YMAX_", length = 2)
	private String ymax;
	/**
	 * 硬度.下限值
	 */
	@Column(name = "YMIN_", length = 2)
	private String ymin;
	/**
	 * 附着面.正面.上限值
	 */
	@Column(name = "FUZS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzs;
	/**
	 * 附着面.正面.下限值
	 */
	@Column(name = "FUZX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzx;
	/**
	 * 附着面.反面.上限值
	 */
	@Column(name = "FUFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufs;
	/**
	 * 附着面.反面.下限值
	 */
	@Column(name = "FUFX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufx;

	/**
	 * 尺寸允许范围.厚%上限.值
	 */
	@Column(name = "HSZI_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double hszi;

	/**
	 * 尺寸允许范围.厚%下限.值
	 */
	@Column(name = "HXZI_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double hxzi;

	/**
	 * 尺寸允许范围.宽mm上限.值
	 */
	@Column(name = "KSZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double kszi;

	/**
	 * 尺寸允许范围.宽mm下限.值
	 */
	@Column(name = "KXZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double kxzi;

	/**
	 * 尺寸允许范围.长mm上限.值
	 */
	@Column(name = "CSZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double cszi;

	/**
	 * 尺寸允许范围.长mm下限.值
	 */
	@Column(name = "CXZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double cxzi;

	/**
	 * 订货确认书发行标志
	 */
	@Column(name = "DHQR_", length = 1)
	private String dhqr;
	/**
	 * 制造仕样确认标志
	 */
	@Column(name = "ZZQR_", length = 1)
	private String zzqr;
	/**
	 * 分配量(吨)
	 */
	@Column(name = "FPLN_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double fpln;
	/**
	 * 分配积累量(吨)
	 */
	@Column(name = "FPJL_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double fpjl;
	/**
	 * ETL指示量(吨)
	 */
	@Column(name = "ETLZ_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double etlz;
	/**
	 * SL指示量(吨)
	 */
	@Column(name = "SLZS_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double slzs;
	/**
	 * ETL合格量(吨)
	 */
	@Column(name = "ETLH_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double etlh;
	/**
	 * SL合格量(吨)
	 */
	@Column(name = "SLHG_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double slhg;

	/**
	 * 出货指示量(Kg)
	 */
	@Column(name = "CHUZ_")
	private Long chuz;
	/**
	 * 出货实绩量(Kg)
	 */
	@Column(name = "CHUS_")
	private Long chus;
	/**
	 * 退货实绩量(Kg)
	 */
	@Column(name = "THUS_")
	private Long thus;

	/**
	 * 捆包实绩量(Kg)
	 */
	@Column(name = "KBUS_")
	private Long kbus;
	/**
	 * 通货区分
	 */
	@Column(name = "THQF_", length = 1)
	private String thqf;
	/**
	 * 合同单价
	 */
	@Column(name = "HTDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double htdj;
	/**
	 * 合同金额
	 */
	@Column(name = "HTJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double htje;
	/**
	 * 运费单价
	 */
	@Column(name = "YFEI_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double yfei;
	/**
	 * 结算条件.预付款比例
	 */
	@Column(name = "YFKN_", length = 3)
	private Integer yfkn;
	/**
	 * 结算条件.出货款比例
	 */
	@Column(name = "CHKN_", length = 3)
	private Integer chkn;
	/**
	 * 结算条件.后付款比例
	 */
	@Column(name = "HFKN_", length = 3)
	private Integer hfkn;
	/**
	 * 结算条件.期限
	 */
	@Column(name = "QIXN_", length = 3)
	private Integer qixn;
	/**
	 * 出货实绩金额
	 */
	@Column(name = "CHUJ_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double chuj;
	/**
	 * 结算条件.设定标志
	 */
	@Column(name = "JSTJ_", length = 1)
	private String jstj;
	/**
	 * 结算条件.状态
	 */
	@Column(name = "JSST_", length = 1)
	private String jsst;
	/**
	 * 木工所NO
	 */
	@Column(name = "MSGN_", length = 256)
	private String mgsn;

	/**
	 * 创建人代码
	 */
	@Column(name = "CJNO_", length = 3)
	private String cjno;

	/**
	 * 创建人名称
	 */
	@Column(name = "CJNM_", length = 20)
	private String cjnm;

	/**
	 * 修改人代码
	 */
	@Column(name = "XGNO_", length = 3)
	private String xgno;

	/**
	 * 修改人名称
	 */
	@Column(name = "XGNM_", length = 20)
	private String xgnm;

	/**
	 * 上锁人代码
	 */
	@Column(name = "LKNO_", length = 3)
	private String lkno;

	/**
	 * 上锁人名称
	 */
	@Column(name = "LKNM_", length = 20)
	private String lknm;

	/**
	 * 上锁时间
	 */
	@Column(name = "LKDT_")
	private Date lkdt;

	/**
	 * 解锁人代码
	 */
	@Column(name = "JSNO_", length = 3)
	private String jsno;

	/**
	 * 解锁人名称
	 */
	@Column(name = "JSNM_", length = 20)
	private String jsnm;

	/**
	 * 解锁时间
	 */
	@Column(name = "JSDT_")
	private Date jsdt;

	/**
	 * 仕样修订人名称
	 */
	@Column(name = "XDNM_", length = 20)
	private String xdnm;

	/**
	 * 仕样修订时间
	 */
	@Column(name = "XDDT_")
	private Date xddt;

	/**
	 * 制造仕样代码
	 */
	@Column(name = "SYNO_", length = 6)
	private String syno;

	/**
	 * 仕样确认人名称
	 */
	@Column(name = "SYNM_", length = 20)
	private String synm;

	/**
	 * 仕样确认时间
	 */
	@Column(name = "SYDT_")
	private Date sydt;

	/**
	 * 备注1(etl和木工所备注)
	 */
	@Column(name = "BZ1_", length = 512)
	private String bz1;

	/**
	 * 备注2(sl备注)
	 */
	@Column(name = "BZ2_", length = 512)
	private String bz2;

	/**
	 * 备注3(etl、木工所和sl备注)
	 */
	@Column(name = "BZ3_", length = 512)
	private String bz3;

	/**
	 * 订货确认书打印标记
	 */
	@Column(name = "SFDY_", length = 1)
	private String sfdy;

	/**
	 * 运输方式代码
	 */
	@Column(name = "YSFS_", length = 4)
	private String ysfs;

	/**
	 * 检查证明书页数
	 */
	@Column(name = "JCHA_", length = 2)
	private Integer jcha;

	/**
	 * 检查证明书提示符
	 */
	@Column(name = "JCTS_", length = 2)
	private String jcts;

	/**
	 * 客户订货单号
	 */
	@Column(name = "KHNO_", length = 10)
	private String khno;

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

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getFpqf() {
		return fpqf;
	}

	public void setFpqf(String fpqf) {
		this.fpqf = fpqf;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getGgnm() {
		return ggnm;
	}

	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
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

	public String getChdx() {
		return chdx;
	}

	public void setChdx(String chdx) {
		this.chdx = chdx;
	}

	public String getYyan() {
		return yyan;
	}

	public void setYyan(String yyan) {
		this.yyan = yyan;
	}

	public String getJcxs() {
		return jcxs;
	}

	public void setJcxs(String jcxs) {
		this.jcxs = jcxs;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public String getNeij() {
		return neij;
	}

	public void setNeij(String neij) {
		this.neij = neij;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJhqi() {
		return jhqi;
	}

	public void setJhqi(Date jhqi) {
		this.jhqi = jhqi;
	}

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public String getJhnr() {
		return jhnr;
	}

	public void setJhnr(String jhnr) {
		this.jhnr = jhnr;
	}

	public String getKbsq() {
		return kbsq;
	}

	public void setKbsq(String kbsq) {
		this.kbsq = kbsq;
	}

	public String getKbsz() {
		return kbsz;
	}

	public void setKbsz(String kbsz) {
		this.kbsz = kbsz;
	}

	public String getKbzq() {
		return kbzq;
	}

	public void setKbzq(String kbzq) {
		this.kbzq = kbzq;
	}

	public String getKbzd() {
		return kbzd;
	}

	public void setKbzd(String kbzd) {
		this.kbzd = kbzd;
	}

	public Double getKbzs() {
		return kbzs;
	}

	public void setKbzs(Double kbzs) {
		this.kbzs = kbzs;
	}

	public Double getKbzx() {
		return kbzx;
	}

	public void setKbzx(Double kbzx) {
		this.kbzx = kbzx;
	}

	public String getHuac() {
		return huac;
	}

	public void setHuac(String huac) {
		this.huac = huac;
	}

	public String getRjie() {
		return rjie;
	}

	public void setRjie(String rjie) {
		this.rjie = rjie;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getHbbj() {
		return hbbj;
	}

	public void setHbbj(String hbbj) {
		this.hbbj = hbbj;
	}

	public String getHbwl() {
		return hbwl;
	}

	public void setHbwl(String hbwl) {
		this.hbwl = hbwl;
	}

	public String getJhqf() {
		return jhqf;
	}

	public void setJhqf(String jhqf) {
		this.jhqf = jhqf;
	}

	public Integer getJhzz() {
		return jhzz;
	}

	public void setJhzz(Integer jhzz) {
		this.jhzz = jhzz;
	}

	public Integer getJhfz() {
		return jhfz;
	}

	public void setJhfz(Integer jhfz) {
		this.jhfz = jhfz;
	}

	public String getTbno() {
		return tbno;
	}

	public void setTbno(String tbno) {
		this.tbno = tbno;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getYhbq() {
		return yhbq;
	}

	public void setYhbq(String yhbq) {
		this.yhbq = yhbq;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public String getSsnm() {
		return ssnm;
	}

	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	public String getJian() {
		return jian;
	}

	public void setJian(String jian) {
		this.jian = jian;
	}

	public String getHjin() {
		return hjin;
	}

	public void setHjin(String hjin) {
		this.hjin = hjin;
	}

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public Double getJiao() {
		return jiao;
	}

	public void setJiao(Double jiao) {
		this.jiao = jiao;
	}

	public String getNjbh() {
		return njbh;
	}

	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}

	public String getProt() {
		return prot;
	}

	public void setProt(String prot) {
		this.prot = prot;
	}

	public String getLtdw() {
		return ltdw;
	}

	public void setLtdw(String ltdw) {
		this.ltdw = ltdw;
	}

	public Integer getLtzi() {
		return ltzi;
	}

	public void setLtzi(Integer ltzi) {
		this.ltzi = ltzi;
	}

	public String getKbjs() {
		return kbjs;
	}

	public void setKbjs(String kbjs) {
		this.kbjs = kbjs;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public Integer getBzcl() {
		return bzcl;
	}

	public void setBzcl(Integer bzcl) {
		this.bzcl = bzcl;
	}

	public Integer getDmzl() {
		return dmzl;
	}

	public void setDmzl(Integer dmzl) {
		this.dmzl = dmzl;
	}

	public String getKpn1Flag() {
		return kpn1Flag;
	}

	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
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

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Double getZrkn() {
		return zrkn;
	}

	public void setZrkn(Double zrkn) {
		this.zrkn = zrkn;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public Double getFuzs() {
		return fuzs;
	}

	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	public Double getFuzx() {
		return fuzx;
	}

	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	public Double getFufs() {
		return fufs;
	}

	public void setFufs(Double fufs) {
		this.fufs = fufs;
	}

	public Double getFufx() {
		return fufx;
	}

	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	public Double getHszi() {
		return hszi;
	}

	public void setHszi(Double hszi) {
		this.hszi = hszi;
	}

	public Double getHxzi() {
		return hxzi;
	}

	public void setHxzi(Double hxzi) {
		this.hxzi = hxzi;
	}

	public Double getKszi() {
		return kszi;
	}

	public void setKszi(Double kszi) {
		this.kszi = kszi;
	}

	public Double getKxzi() {
		return kxzi;
	}

	public void setKxzi(Double kxzi) {
		this.kxzi = kxzi;
	}

	public Double getCszi() {
		return cszi;
	}

	public void setCszi(Double cszi) {
		this.cszi = cszi;
	}

	public Double getCxzi() {
		return cxzi;
	}

	public void setCxzi(Double cxzi) {
		this.cxzi = cxzi;
	}

	public String getDhqr() {
		return dhqr;
	}

	public void setDhqr(String dhqr) {
		this.dhqr = dhqr;
	}

	public String getZzqr() {
		return zzqr;
	}

	public void setZzqr(String zzqr) {
		this.zzqr = zzqr;
	}

	public Double getFpln() {
		return fpln;
	}

	public void setFpln(Double fpln) {
		this.fpln = fpln;
	}

	public Double getFpjl() {
		return fpjl;
	}

	public void setFpjl(Double fpjl) {
		this.fpjl = fpjl;
	}

	public Double getEtlz() {
		return etlz;
	}

	public void setEtlz(Double etlz) {
		this.etlz = etlz;
	}

	public Double getSlzs() {
		return slzs;
	}

	public void setSlzs(Double slzs) {
		this.slzs = slzs;
	}

	public Double getEtlh() {
		return etlh;
	}

	public void setEtlh(Double etlh) {
		this.etlh = etlh;
	}

	public Double getSlhg() {
		return slhg;
	}

	public void setSlhg(Double slhg) {
		this.slhg = slhg;
	}

	public Long getChuz() {
		return chuz;
	}

	public void setChuz(Long chuz) {
		this.chuz = chuz;
	}

	public Long getChus() {
		return chus;
	}

	public void setChus(Long chus) {
		this.chus = chus;
	}

	public Long getThus() {
		return thus;
	}

	public void setThus(Long thus) {
		this.thus = thus;
	}

	public Long getKbus() {
		return kbus;
	}

	public void setKbus(Long kbus) {
		this.kbus = kbus;
	}

	public String getThqf() {
		return thqf;
	}

	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	public Double getHtdj() {
		return htdj;
	}

	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

	public Double getHtje() {
		return htje;
	}

	public void setHtje(Double htje) {
		this.htje = htje;
	}

	public Double getYfei() {
		return yfei;
	}

	public void setYfei(Double yfei) {
		this.yfei = yfei;
	}

	public Integer getYfkn() {
		return yfkn;
	}

	public void setYfkn(Integer yfkn) {
		this.yfkn = yfkn;
	}

	public Integer getChkn() {
		return chkn;
	}

	public void setChkn(Integer chkn) {
		this.chkn = chkn;
	}

	public Integer getHfkn() {
		return hfkn;
	}

	public void setHfkn(Integer hfkn) {
		this.hfkn = hfkn;
	}

	public Integer getQixn() {
		return qixn;
	}

	public void setQixn(Integer qixn) {
		this.qixn = qixn;
	}

	public Double getChuj() {
		return chuj;
	}

	public void setChuj(Double chuj) {
		this.chuj = chuj;
	}

	public String getJstj() {
		return jstj;
	}

	public void setJstj(String jstj) {
		this.jstj = jstj;
	}

	public String getJsst() {
		return jsst;
	}

	public void setJsst(String jsst) {
		this.jsst = jsst;
	}

	public String getMgsn() {
		return mgsn;
	}

	public void setMgsn(String mgsn) {
		this.mgsn = mgsn;
	}

	public String getCjno() {
		return cjno;
	}

	public void setCjno(String cjno) {
		this.cjno = cjno;
	}

	public String getCjnm() {
		return cjnm;
	}

	public void setCjnm(String cjnm) {
		this.cjnm = cjnm;
	}

	public String getXgno() {
		return xgno;
	}

	public void setXgno(String xgno) {
		this.xgno = xgno;
	}

	public String getXgnm() {
		return xgnm;
	}

	public void setXgnm(String xgnm) {
		this.xgnm = xgnm;
	}

	public String getLkno() {
		return lkno;
	}

	public void setLkno(String lkno) {
		this.lkno = lkno;
	}

	public String getLknm() {
		return lknm;
	}

	public void setLknm(String lknm) {
		this.lknm = lknm;
	}

	public Date getLkdt() {
		return lkdt;
	}

	public void setLkdt(Date lkdt) {
		this.lkdt = lkdt;
	}

	public String getJsno() {
		return jsno;
	}

	public void setJsno(String jsno) {
		this.jsno = jsno;
	}

	public String getJsnm() {
		return jsnm;
	}

	public void setJsnm(String jsnm) {
		this.jsnm = jsnm;
	}

	public Date getJsdt() {
		return jsdt;
	}

	public void setJsdt(Date jsdt) {
		this.jsdt = jsdt;
	}

	public String getSyno() {
		return syno;
	}

	public void setSyno(String syno) {
		this.syno = syno;
	}

	public String getSynm() {
		return synm;
	}

	public void setSynm(String synm) {
		this.synm = synm;
	}

	public Date getSydt() {
		return sydt;
	}

	public void setSydt(Date sydt) {
		this.sydt = sydt;
	}

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getSfdy() {
		return sfdy;
	}

	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}

	public String getYsfs() {
		return ysfs;
	}

	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	public Integer getJcha() {
		return jcha;
	}

	public void setJcha(Integer jcha) {
		this.jcha = jcha;
	}

	public String getXdnm() {
		return xdnm;
	}

	public void setXdnm(String xdnm) {
		this.xdnm = xdnm;
	}

	public Date getXddt() {
		return xddt;
	}

	public void setXddt(Date xddt) {
		this.xddt = xddt;
	}

	public String getJcts() {
		return jcts;
	}

	public void setJcts(String jcts) {
		this.jcts = jcts;
	}

	public String getCdnm() {
		return cdnm;
	}

	public void setCdnm(String cdnm) {
		this.cdnm = cdnm;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHtqi() {
		return htqi;
	}

	public void setHtqi(String htqi) {
		this.htqi = htqi;
	}

	public String getKhno() {
		return khno;
	}

	public void setKhno(String khno) {
		this.khno = khno;
	}

}
