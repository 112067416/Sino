package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.quanta.sino.util.SinoUtils;

/**
 * 制品在库
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ZPNGTP")
public class ZpngTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 获取格式化的制品尺寸
	 * </p>
	 * 
	 * @return String
	 */
	public String getSize() {
		return SinoUtils.formatProductSize(houu, kuan, cang);
	}

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

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
	 * 卷板No/PlieNo
	 */
	@Id
	@Column(name = "JBNO_", length = 11)
	private String jbno;
	/**
	 * 现品状况
	 */
	@Column(name = "XPZK_", length = 1)
	private String xpzk;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 指示书No.
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
	 * 进度标记.SL指示
	 */
	@Column(name = "JDSZ_", length = 1)
	private String jdsz;
	/**
	 * 进度标记.SL实绩
	 */
	@Column(name = "JDSS_", length = 1)
	private String jdss;
	/**
	 * 进度标记.精整指示
	 */
	@Column(name = "JDJZ_", length = 1)
	private String jdjz;
	/**
	 * 进度标记.精整实绩
	 */
	@Column(name = "JDJS_", length = 1)
	private String jdjs;
	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;
	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
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
	 * 锯齿形式
	 */
	@Column(name = "JCXS_", length = 4)
	private String jcxs;
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
	 * 尺寸允许范围.厚mm上限.值
	 */
	@Column(name = "MSZI_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double mszi;
	/**
	 * 尺寸允许范围.厚mm下限.值
	 */
	@Column(name = "MXZI_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double mxzi;
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
	 * 直角度
	 */
	@Column(name = "JIAO_", columnDefinition = "numeric(2,1)", scale = 2, precision = 1)
	private Double jiao;
	/**
	 * 再剪边标记
	 */
	@Column(name = "ZJBB_", length = 1)
	private String zjbb;
	/**
	 * 涂油种类
	 */
	@Column(name = "YTYP_", length = 1)
	private String ytyp;
	/**
	 * 内径保护筒标记
	 */
	@Column(name = "NJBH_", length = 1)
	private String njbh;
	/**
	 * 化学处理方法
	 */
	@Column(name = "HXCL_", length = 4)
	private String hxcl;
	/**
	 * 压延方向指定标记
	 */
	@Column(name = "YYAN_", length = 1)
	private String yyan;
	/**
	 * K-Plate表示
	 */
	@Column(name = "PLAT_", length = 1)
	private String plat;
	/**
	 * 差厚镀锡标记
	 */
	@Column(name = "CHDX_", length = 2)
	private String chdx;
	/**
	 * 保护标志
	 */
	@Column(name = "PROT_", length = 1)
	private String prot;
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
	 * 交货重量内容
	 */
	@Column(name = "JHNR_", length = 1)
	private String jhnr;
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
	 * 包装张数
	 */
	@Column(name = "BZZS_")
	private Integer bzzs;
	/**
	 * 销售W可(ETL)
	 */
	@Column(name = "XSWK_", length = 1)
	private String xswk;
	/**
	 * 内径代码
	 */
	@Column(name = "NJNO_", length = 1)
	private String njno;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;
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
	 * 表面精加工符号
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * SL装入宽
	 */
	@Column(name = "ZRKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double zrkn;
	/**
	 * 钢种类型
	 */
	@Column(name = "GZLX_", length = 4)
	private String gzlx;
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
	 * 现品尺寸.长
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;
	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
	/**
	 * 现品尺寸公差.厚.下限
	 */
	@Column(name = "XPHX_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
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
	 * 垫木重量
	 */
	@Column(name = "DMZL_")
	private Integer dmzl;
	/**
	 * 包装材料重量（Kg）
	 */
	@Column(name = "BZCL_")
	private Integer bzcl;
	/**
	 * 张数（包装单位）
	 */
	@Column(name = "ZSHU_")
	private Integer zshu;
	/**
	 * m单重
	 */
	@Column(name = "MDAN_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double mdan;
	/**
	 * 薄板单重
	 */
	@Column(name = "BDAN_", columnDefinition = "numeric(5,3)", scale = 5, precision = 3)
	private Double bdan;
	/**
	 * 重量计算补正系统
	 */
	@Column(name = "BZXS_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double bzxs;
	/**
	 * 出口包装No.
	 */
	@Column(name = "CKNO_")
	private Integer ckno;
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
	 * 实绩附着量.正面
	 */
	@Column(name = "SCZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sczm;
	/**
	 * 实绩附着量.反面
	 */
	@Column(name = "SCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scfm;
	/**
	 * 硬度
	 */
	@Column(name = "YING_")
	private Integer ying;
	/**
	 * 卷上长
	 */
	@Column(name = "JSCN_")
	private Integer jscn;
	/**
	 * Cut长
	 */
	@Column(name = "CUTC_")
	private Integer cutc;
	/**
	 * Loss长
	 */
	@Column(name = "LOSC_")
	private Integer losc;
	/**
	 * Loss长缺陷
	 */
	@Column(name = "LOSQ_", length = 2)
	private String losq;
	/**
	 * Loss长
	 */
	@Column(name = "LOSC2_")
	private Integer losc2;
	/**
	 * Loss长缺陷
	 */
	@Column(name = "LOSQ2_", length = 2)
	private String losq2;
	/**
	 * P.H个数
	 */
	@Column(name = "PHGS_")
	private Integer phgs;
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
	 * 溶接个数.ETL
	 */
	@Column(name = "RJET_")
	private Integer rjet;
	/**
	 * 板厚不量有无
	 */
	@Column(name = "BHBL_", length = 1)
	private String bhbl;
	/**
	 * 卷上TRNo
	 */
	@Column(name = "JSNO_", length = 3)
	private String jsno;
	/**
	 * SL流水线代码（实绩）
	 */
	@Column(name = "SLIN_", length = 1)
	private String slin;
	/**
	 * D-Mark标记
	 */
	@Column(name = "DMRK_", length = 1)
	private String dmrk;
	/**
	 * 采取Piler
	 */
	@Column(name = "CQPL_", length = 1)
	private String cqpl;
	/**
	 * 长度分布(+1.5)
	 */
	@Column(name = "CP15_")
	private Integer cp15;
	/**
	 * 长度分布(+1.0)
	 */
	@Column(name = "CP10_")
	private Integer cp10;
	/**
	 * 长度分布(+0.5)
	 */
	@Column(name = "CP05_")
	private Integer cp05;
	/**
	 * 长度分布(+-0)
	 */
	@Column(name = "CP00_")
	private Integer cp00;
	/**
	 * 长度分布(-0.5)
	 */
	@Column(name = "CM05_")
	private Integer cm05;
	/**
	 * 实测宽
	 */
	@Column(name = "SCKN_", length = 7)
	private String sckn;
	/**
	 * 实测剪断长
	 */
	@Column(name = "JDCN_", length = 7)
	private String jdcn;
	/**
	 * 流水线速度
	 */
	@Column(name = "LNSD_")
	private Integer lnsd;
	/**
	 * 边波纹OP.高度
	 */
	@Column(name = "BOPG_", length = 2)
	private String bopg;
	/**
	 * 边波纹OP.间隔
	 */
	@Column(name = "BOPJ_", length = 3)
	private String bopj;
	/**
	 * 边波纹DR.高度
	 */
	@Column(name = "BDRG_", length = 2)
	private String bdrg;
	/**
	 * 边波纹DR.间隔
	 */
	@Column(name = "BDRJ_", length = 3)
	private String bdrj;
	/**
	 * 边波纹等级
	 */
	@Column(name = "BDJI_", length = 1)
	private String bdji;
	/**
	 * 直角度Op.值
	 */
	@Column(name = "ZOPZ_", length = 4)
	private String zopz;
	/**
	 * 直角度Dr.值
	 */
	@Column(name = "ZDRZ_", length = 4)
	private String zdrz;
	/**
	 * 纵向挠度.值
	 */
	@Column(name = "ZNDZ_", length = 3)
	private String zndz;
	/**
	 * 纵向挠度.等级
	 */
	@Column(name = "ZNDD_", length = 1)
	private String zndd;
	/**
	 * 横向挠度.值
	 */
	@Column(name = "HNDZ_", length = 3)
	private String hndz;
	/**
	 * 横向挠度.等级
	 */
	@Column(name = "HNDD_", length = 1)
	private String hndd;
	/**
	 * 中波纹等级
	 */
	@Column(name = "ZBWD_", length = 1)
	private String zbwd;
	/**
	 * 翘度值
	 */
	@Column(name = "QDUZ_", length = 3)
	private String qduz;
	/**
	 * 翻页必要标记
	 */
	@Column(name = "FMBY_", length = 1)
	private String fmby;
	/**
	 * 缺陷1.缺陷代码
	 */
	@Column(name = "QXN1_", length = 2)
	private String qxn1;
	/**
	 * 缺陷1.等级
	 */
	@Column(name = "QXD1_", length = 1)
	private String qxd1;
	/**
	 * 缺陷1.张数
	 */
	@Column(name = "QXZ1_")
	private Integer qxz1;
	/**
	 * 缺陷2.缺陷代码
	 */
	@Column(name = "QXN2_", length = 2)
	private String qxn2;
	/**
	 * 缺陷2.等级
	 */
	@Column(name = "QXD2_", length = 1)
	private String qxd2;
	/**
	 * 缺陷2.张数
	 */
	@Column(name = "QXZ2_")
	private Integer qxz2;
	/**
	 * 缺陷3.缺陷代码
	 */
	@Column(name = "QXN3_", length = 2)
	private String qxn3;
	/**
	 * 缺陷3.等级
	 */
	@Column(name = "QXD3_", length = 1)
	private String qxd3;
	/**
	 * 缺陷3.张数
	 */
	@Column(name = "QXZ3_")
	private Integer qxz3;
	/**
	 * 缺陷4.缺陷代码
	 */
	@Column(name = "QXN4_", length = 2)
	private String qxn4;
	/**
	 * 缺陷4.等级
	 */
	@Column(name = "QXD4_", length = 1)
	private String qxd4;
	/**
	 * 缺陷4.张数
	 */
	@Column(name = "QXZ4_")
	private Integer qxz4;
	/**
	 * 缺陷5.缺陷代码
	 */
	@Column(name = "QXN5_", length = 2)
	private String qxn5;
	/**
	 * 缺陷5.等级
	 */
	@Column(name = "QXD5_", length = 1)
	private String qxd5;
	/**
	 * 缺陷5.张数
	 */
	@Column(name = "QXZ5_")
	private Integer qxz5;
	/**
	 * 缺陷6.缺陷代码
	 */
	@Column(name = "QXN6_", length = 2)
	private String qxn6;
	/**
	 * 缺陷6.等级
	 */
	@Column(name = "QXD6_", length = 1)
	private String qxd6;
	/**
	 * 缺陷6.张数
	 */
	@Column(name = "QXZ6_")
	private Integer qxz6;
	/**
	 * 缺陷7.缺陷代码
	 */
	@Column(name = "QXN7_", length = 2)
	private String qxn7;
	/**
	 * 缺陷7.等级
	 */
	@Column(name = "QXD7_", length = 1)
	private String qxd7;
	/**
	 * 缺陷7.张数
	 */
	@Column(name = "QXZ7_")
	private Integer qxz7;
	/**
	 * 缺陷8.缺陷代码
	 */
	@Column(name = "QXN8_", length = 2)
	private String qxn8;
	/**
	 * 缺陷8.等级
	 */
	@Column(name = "QXD8_", length = 1)
	private String qxd8;
	/**
	 * 缺陷8.张数
	 */
	@Column(name = "QXZ8_")
	private Integer qxz8;
	/**
	 * 前Mark
	 */
	@Column(name = "QMRK_", length = 1)
	private String qmrk;
	/**
	 * 目的
	 */
	@Column(name = "MUDI_", length = 1)
	private String mudi;
	/**
	 * 方向
	 */
	@Column(name = "FANG_", length = 1)
	private String fang;
	/**
	 * 后Mark
	 */
	@Column(name = "HMRK_", length = 1)
	private String hmrk;
	/**
	 * 作业者
	 */
	@Column(name = "ZYZE_", length = 4)
	private String zyze;
	/**
	 * Variable
	 */
	@Column(name = "VARI_", length = 10)
	private String vari;
	/**
	 * 业连No
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;
	/**
	 * 堆场
	 */
	@Column(name = "DUIC_", length = 4)
	private String duic;
	/**
	 * 堆场搬入年月日
	 */
	@Column(name = "DUIB_")
	private Date duib;
	/**
	 * 原板收货年月日
	 */
	@Column(name = "YSUO_")
	private Date ysuo;
	/**
	 * ETL实绩登录年月日
	 */
	@Column(name = "ETSD_")
	private Date etsd;
	/**
	 * SL实绩登录年月日
	 */
	@Column(name = "SLSD_")
	private Date slsd;
	/**
	 * Pile生成实绩登录年月日
	 */
	@Column(name = "PSSD_")
	private Date pssd;
	/**
	 * Pile消灭实绩登录年月日
	 */
	@Column(name = "PXSD_")
	private Date pxsd;
	/**
	 * 翻面实绩登录年月日
	 */
	@Column(name = "FMSD_")
	private Date fmsd;
	/**
	 * 捆包实绩登录年月日
	 */
	@Column(name = "KBSD_")
	private Date kbsd;
	/**
	 * 出货指示年月日
	 */
	@Column(name = "CHZS_")
	private Date chzs;
	/**
	 * 出货实绩登录年月日
	 */
	@Column(name = "CHSD_")
	private Date chsd;
	/**
	 * 实绩品种等级
	 */
	@Column(name = "SPDJ_", length = 1)
	private String spdj;
	/**
	 * 作业停止标记
	 */
	@Column(name = "ZTBJ_", length = 2)
	private String ztbj;
	/**
	 * 标签换帖标记
	 */
	@Column(name = "BQHT_", length = 1)
	private String bqht;
	/**
	 * 强制出货标记
	 */
	@Column(name = "QZCH_", length = 1)
	private String qzch;
	/**
	 * 出货指示No
	 */
	@Column(name = "CHNO_", length = 10)
	private String chno;
	/**
	 * 检查证明书No
	 */
	@Column(name = "JCNO_", length = 11)
	private String jcno;
	/**
	 * 送货单No
	 */
	@Column(name = "SHNO_", length = 20)
	private String shno;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 制造商卷板No
	 */
	@Column(name = "ZZSJ_", length = 15)
	private String zzsj;
	/**
	 * 原板采购No
	 */
	@Column(name = "YBNO_", length = 10)
	private String ybno;
	/**
	 * 原板规格略称
	 */
	@Column(name = "YBLC_", length = 40)
	private String yblc;
	/**
	 * 制造年月日
	 */
	@Column(name = "ZZNY_")
	private Date zzny;
	/**
	 * BL-Date(装船日)
	 */
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
	 * 删除标记
	 */
	@Column(name = "SCBJ_", length = 1)
	private String scbj;

	/**
	 * 矢切量
	 */
	@Column(name = "SIQL_", length = 5)
	private String siql;

	/**
	 * 镜面检查测试
	 */
	@Column(name = "JMJC_", length = 1)
	private String jmjc;

	/**
	 * 毛边上
	 */
	@Column(name = "MAOS_", length = 5)
	private String maos;

	/**
	 * 毛边下
	 */
	@Column(name = "MAOX_", length = 5)
	private String maox;

	/**
	 * 修边毛边DR
	 */
	@Column(name = "MADR_", length = 5)
	private String madr;

	/**
	 * 修边毛边OP
	 */
	@Column(name = "MAOP_", length = 5)
	private String maop;

	/**
	 * 垫木方向确认
	 */
	@Column(name = "DMQR_", length = 1)
	private String dmqr;

	/**
	 * 针孔确认
	 */
	@Column(name = "ZKQR_", length = 1)
	private String zkqr;

	/**
	 * 是否紧急
	 */
	@Column(name = "SFJJ_", length = 1)
	private String sfjj;

	/**
	 * ETL流水线代码（实绩）
	 */
	@Column(name = "ELIN_", length = 1)
	private String elin;

	/**
	 * 入侧端COIL/PILE
	 */
	@Column(name = "RCZPNO_", length = 11)
	private String rczpno;

	/**
	 * 库位
	 */
	@Column(name = "KW_", length = 4)
	private String kw;

	/**
	 * 标签打印时间
	 */
	@Column(name = "BJDY_")
	private Date bjdy;
	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 4)
	private String ddno;
	/**
	 * 中波纹高度
	 */
	@Column(name = "ZBOG_", length = 2)
	private String zbog;
	/**
	 * 中波纹间隔
	 */
	@Column(name = "ZBOJ_", length = 3)
	private String zboj;

	/**
	 * 车牌号
	 */
	@Column(name = "CPNO_", length = 10)
	private String cpno;

	/**
	 * <p>
	 * 组别
	 * </p>
	 */
	@Column(name = "ZU_", length = 1)
	private String zu;

	/**
	 * <p>
	 * 班别
	 * </p>
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;

	/**
	 * <p>
	 * 端板标记
	 * </p>
	 */
	@Column(name = "DBBJ_", length = 1)
	private String dbbj;

	/**
	 * <p>
	 * 缺陷代码2
	 * </p>
	 */
	@Column(name = "QQD2_", length = 2)
	private String qqd2;

	/**
	 * <p>
	 * 缺陷代码3
	 * </p>
	 */
	@Column(name = "QQD3_", length = 2)
	private String qqd3;

	/**
	 * <p>
	 * 理论硬度
	 * </p>
	 */
	@Column(name = "LLYD_")
	private Integer llyd;
	/**
	 * 是否保税
	 */
	@Column(name = "SFBS_", length = 1)
	private String sfbs;
	/**
	 * <p>
	 * SL实绩完成时间(终了或中止时间)
	 * </p>
	 */
	@Column(name = "SJSJ_")
	private Date sjsj;
	/**
	 * 商品编号
	 */
	@Column(name = "SPBH_", length = 1)
	private String spbh;

	/**
	 * <p>
	 * 装入重量
	 * </p>
	 */
	@Column(name = "ZRZL_")
	private Integer zrzl;

	/**
	 * <p>
	 * 原材号码
	 * </p>
	 */
	@Column(name = "YCNO_")
	private String ycno;

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
	 * 不良扣除联络单打印标志
	 */
	@Column(name = "LODY_", length = 1)
	private String lody;

	/**
	 * <p>
	 * 硬度录入
	 * </p>
	 */
	@Column(name = "YDSJ_")
	private Date ydsj;
	/**
	 * <p>
	 * 是否废品
	 * </p>
	 */
	@Column(name = "ISFP_", length = 1)
	private String isfp;
	/**
	 * <p>
	 * 是否紧急
	 * </p>
	 */
	@Column(name = "JINJ_", length = 1)
	private String jinj;
	/**
	 * <p>
	 * 保留标志
	 * </p>
	 */
	@Column(name = "BLBJ_", length = 2)
	private String blbj;

	public String getIsfp() {
		return isfp;
	}

	public void setIsfp(String isfp) {
		this.isfp = isfp;
	}

	public String getLody() {
		return lody;
	}

	public void setLody(String lody) {
		this.lody = lody;
	}

	public String getYcno() {
		return ycno;
	}

	public void setYcno(String ycno) {
		this.ycno = ycno;
	}

	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	public Integer getLlyd() {
		return llyd;
	}

	public void setLlyd(Integer llyd) {
		this.llyd = llyd;
	}

	/**
	 * @return the siql
	 */
	public String getSiql() {
		return siql;
	}

	/**
	 * @param siql
	 *            the siql to set
	 */
	public void setSiql(String siql) {
		this.siql = siql;
	}

	/**
	 * @return the jmjc
	 */
	public String getJmjc() {
		return jmjc;
	}

	/**
	 * @param jmjc
	 *            the jmjc to set
	 */
	public void setJmjc(String jmjc) {
		this.jmjc = jmjc;
	}

	/**
	 * @return the maos
	 */
	public String getMaos() {
		return maos;
	}

	/**
	 * @param maos
	 *            the maos to set
	 */
	public void setMaos(String maos) {
		this.maos = maos;
	}

	/**
	 * @return the maox
	 */
	public String getMaox() {
		return maox;
	}

	/**
	 * @param maox
	 *            the maox to set
	 */
	public void setMaox(String maox) {
		this.maox = maox;
	}

	/**
	 * @return the dmqr
	 */
	public String getDmqr() {
		return dmqr;
	}

	/**
	 * @param dmqr
	 *            the dmqr to set
	 */
	public void setDmqr(String dmqr) {
		this.dmqr = dmqr;
	}

	/**
	 * @return the zkqr
	 */
	public String getZkqr() {
		return zkqr;
	}

	/**
	 * @param zkqr
	 *            the zkqr to set
	 */
	public void setZkqr(String zkqr) {
		this.zkqr = zkqr;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the prog
	 */
	public String getProg() {
		return prog;
	}

	/**
	 * @param prog
	 *            the prog to set
	 */
	public void setProg(String prog) {
		this.prog = prog;
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
	 * @return the rsv1
	 */
	public String getRsv1() {
		return rsv1;
	}

	/**
	 * @param rsv1
	 *            the rsv1 to set
	 */
	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
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
	 * @return the xpzk
	 */
	public String getXpzk() {
		return xpzk;
	}

	/**
	 * @param xpzk
	 *            the xpzk to set
	 */
	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	/**
	 * @return the zzno
	 */
	public String getZzno() {
		return zzno;
	}

	/**
	 * @param zzno
	 *            the zzno to set
	 */
	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	/**
	 * @return the plqf
	 */
	public String getPlqf() {
		return plqf;
	}

	/**
	 * @param plqf
	 *            the plqf to set
	 */
	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
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
	 * @return the fpno
	 */
	public String getFpno() {
		return fpno;
	}

	/**
	 * @param fpno
	 *            the fpno to set
	 */
	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	/**
	 * @return the fpyc
	 */
	public String getFpyc() {
		return fpyc;
	}

	/**
	 * @param fpyc
	 *            the fpyc to set
	 */
	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

	/**
	 * @return the jdez
	 */
	public String getJdez() {
		return jdez;
	}

	/**
	 * @return the rsv2
	 */
	public BigDecimal getRsv2() {
		return rsv2;
	}

	/**
	 * @param rsv2
	 *            the rsv2 to set
	 */
	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	/**
	 * @return the jdes
	 */
	public String getJdes() {
		return jdes;
	}

	/**
	 * @param jdes
	 *            the jdes to set
	 */
	public void setJdes(String jdes) {
		this.jdes = jdes;
	}

	/**
	 * @return the jdsz
	 */
	public String getJdsz() {
		return jdsz;
	}

	/**
	 * @param jdsz
	 *            the jdsz to set
	 */
	public void setJdsz(String jdsz) {
		this.jdsz = jdsz;
	}

	/**
	 * @return the jdss
	 */
	public String getJdss() {
		return jdss;
	}

	/**
	 * @param jdss
	 *            the jdss to set
	 */
	public void setJdss(String jdss) {
		this.jdss = jdss;
	}

	/**
	 * @return the jdjz
	 */
	public String getJdjz() {
		return jdjz;
	}

	/**
	 * @param jdjz
	 *            the jdjz to set
	 */
	public void setJdjz(String jdjz) {
		this.jdjz = jdjz;
	}

	/**
	 * @return the jdjs
	 */
	public String getJdjs() {
		return jdjs;
	}

	/**
	 * @param jdjs
	 *            the jdjs to set
	 */
	public void setJdjs(String jdjs) {
		this.jdjs = jdjs;
	}

	/**
	 * @return the nwai
	 */
	public String getNwai() {
		return nwai;
	}

	/**
	 * @param nwai
	 *            the nwai to set
	 */
	public void setNwai(String nwai) {
		this.nwai = nwai;
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
	 * @return the cang
	 */
	public Double getCang() {
		return cang;
	}

	/**
	 * @param cang
	 *            the cang to set
	 */
	public void setCang(Double cang) {
		this.cang = cang;
	}

	/**
	 * @return the hngh
	 */
	public Double getHngh() {
		return hngh;
	}

	/**
	 * @param hngh
	 *            the hngh to set
	 */
	public void setHngh(Double hngh) {
		this.hngh = hngh;
	}

	/**
	 * @return the hngk
	 */
	public Double getHngk() {
		return hngk;
	}

	/**
	 * @param hngk
	 *            the hngk to set
	 */
	public void setHngk(Double hngk) {
		this.hngk = hngk;
	}

	/**
	 * @return the hngc
	 */
	public Double getHngc() {
		return hngc;
	}

	/**
	 * @param hngc
	 *            the hngc to set
	 */
	public void setHngc(Double hngc) {
		this.hngc = hngc;
	}

	/**
	 * @return the jcxs
	 */
	public String getJcxs() {
		return jcxs;
	}

	/**
	 * @param jcxs
	 *            the jcxs to set
	 */
	public void setJcxs(String jcxs) {
		this.jcxs = jcxs;
	}

	/**
	 * @return the hszi
	 */
	public Double getHszi() {
		return hszi;
	}

	/**
	 * @param hszi
	 *            the hszi to set
	 */
	public void setHszi(Double hszi) {
		this.hszi = hszi;
	}

	/**
	 * @return the hxzi
	 */
	public Double getHxzi() {
		return hxzi;
	}

	/**
	 * @param hxzi
	 *            the hxzi to set
	 */
	public void setHxzi(Double hxzi) {
		this.hxzi = hxzi;
	}

	/**
	 * @return the mszi
	 */
	public Double getMszi() {
		return mszi;
	}

	/**
	 * @param mszi
	 *            the mszi to set
	 */
	public void setMszi(Double mszi) {
		this.mszi = mszi;
	}

	/**
	 * @return the mxzi
	 */
	public Double getMxzi() {
		return mxzi;
	}

	/**
	 * @param mxzi
	 *            the mxzi to set
	 */
	public void setMxzi(Double mxzi) {
		this.mxzi = mxzi;
	}

	/**
	 * @return the kszi
	 */
	public Double getKszi() {
		return kszi;
	}

	/**
	 * @param kszi
	 *            the kszi to set
	 */
	public void setKszi(Double kszi) {
		this.kszi = kszi;
	}

	/**
	 * @return the kxzi
	 */
	public Double getKxzi() {
		return kxzi;
	}

	/**
	 * @param kxzi
	 *            the kxzi to set
	 */
	public void setKxzi(Double kxzi) {
		this.kxzi = kxzi;
	}

	/**
	 * @return the cszi
	 */
	public Double getCszi() {
		return cszi;
	}

	/**
	 * @param cszi
	 *            the cszi to set
	 */
	public void setCszi(Double cszi) {
		this.cszi = cszi;
	}

	/**
	 * @return the cxzi
	 */
	public Double getCxzi() {
		return cxzi;
	}

	/**
	 * @param cxzi
	 *            the cxzi to set
	 */
	public void setCxzi(Double cxzi) {
		this.cxzi = cxzi;
	}

	/**
	 * @return the jiao
	 */
	public Double getJiao() {
		return jiao;
	}

	/**
	 * @param jiao
	 *            the jiao to set
	 */
	public void setJiao(Double jiao) {
		this.jiao = jiao;
	}

	/**
	 * @return the zjbb
	 */
	public String getZjbb() {
		return zjbb;
	}

	/**
	 * @param zjbb
	 *            the zjbb to set
	 */
	public void setZjbb(String zjbb) {
		this.zjbb = zjbb;
	}

	/**
	 * @return the ytyp
	 */
	public String getYtyp() {
		return ytyp;
	}

	/**
	 * @param ytyp
	 *            the ytyp to set
	 */
	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	/**
	 * @return the njbh
	 */
	public String getNjbh() {
		return njbh;
	}

	/**
	 * @param njbh
	 *            the njbh to set
	 */
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}

	/**
	 * @return the hxcl
	 */
	public String getHxcl() {
		return hxcl;
	}

	/**
	 * @param hxcl
	 *            the hxcl to set
	 */
	public void setHxcl(String hxcl) {
		this.hxcl = hxcl;
	}

	/**
	 * @return the yyan
	 */
	public String getYyan() {
		return yyan;
	}

	/**
	 * @param yyan
	 *            the yyan to set
	 */
	public void setYyan(String yyan) {
		this.yyan = yyan;
	}

	/**
	 * @return the plat
	 */
	public String getPlat() {
		return plat;
	}

	/**
	 * @param plat
	 *            the plat to set
	 */
	public void setPlat(String plat) {
		this.plat = plat;
	}

	/**
	 * @return the chdx
	 */
	public String getChdx() {
		return chdx;
	}

	/**
	 * @param chdx
	 *            the chdx to set
	 */
	public void setChdx(String chdx) {
		this.chdx = chdx;
	}

	/**
	 * @return the prot
	 */
	public String getProt() {
		return prot;
	}

	/**
	 * @param prot
	 *            the prot to set
	 */
	public void setProt(String prot) {
		this.prot = prot;
	}

	/**
	 * @return the kpn1
	 */
	public String getKpn1() {
		return kpn1;
	}

	/**
	 * @param kpn1
	 *            the kpn1 to set
	 */
	public void setKpn1(String kpn1) {
		this.kpn1 = kpn1;
	}

	/**
	 * @return the kpn2
	 */
	public String getKpn2() {
		return kpn2;
	}

	/**
	 * @param kpn2
	 *            the kpn2 to set
	 */
	public void setKpn2(String kpn2) {
		this.kpn2 = kpn2;
	}

	/**
	 * @return the jhnr
	 */
	public String getJhnr() {
		return jhnr;
	}

	/**
	 * @param jhnr
	 *            the jhnr to set
	 */
	public void setJhnr(String jhnr) {
		this.jhnr = jhnr;
	}

	/**
	 * @return the kbzs
	 */
	public Double getKbzs() {
		return kbzs;
	}

	/**
	 * @param kbzs
	 *            the kbzs to set
	 */
	public void setKbzs(Double kbzs) {
		this.kbzs = kbzs;
	}

	/**
	 * @return the kbzx
	 */
	public Double getKbzx() {
		return kbzx;
	}

	/**
	 * @param kbzx
	 *            the kbzx to set
	 */
	public void setKbzx(Double kbzx) {
		this.kbzx = kbzx;
	}

	/**
	 * @return the bzzs
	 */
	public Integer getBzzs() {
		return bzzs;
	}

	/**
	 * @param bzzs
	 *            the bzzs to set
	 */
	public void setBzzs(Integer bzzs) {
		this.bzzs = bzzs;
	}

	/**
	 * @return the xswk
	 */
	public String getXswk() {
		return xswk;
	}

	/**
	 * @param xswk
	 *            the xswk to set
	 */
	public void setXswk(String xswk) {
		this.xswk = xswk;
	}

	/**
	 * @return the njno
	 */
	public String getNjno() {
		return njno;
	}

	/**
	 * @param njno
	 *            the njno to set
	 */
	public void setNjno(String njno) {
		this.njno = njno;
	}

	/**
	 * @return the kbao
	 */
	public String getKbao() {
		return kbao;
	}

	/**
	 * @param kbao
	 *            the kbao to set
	 */
	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	/**
	 * @return the dmfx
	 */
	public String getDmfx() {
		return dmfx;
	}

	/**
	 * @param dmfx
	 *            the dmfx to set
	 */
	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	/**
	 * @return the pzno
	 */
	public String getPzno() {
		return pzno;
	}

	/**
	 * @param pzno
	 *            the pzno to set
	 */
	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	/**
	 * @return the fudw
	 */
	public String getFudw() {
		return fudw;
	}

	/**
	 * @param fudw
	 *            the fudw to set
	 */
	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	/**
	 * @return the fuzm
	 */
	public String getFuzm() {
		return fuzm;
	}

	/**
	 * @param fuzm
	 *            the fuzm to set
	 */
	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	/**
	 * @return the fufm
	 */
	public String getFufm() {
		return fufm;
	}

	/**
	 * @param fufm
	 *            the fufm to set
	 */
	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	/**
	 * @return the face
	 */
	public String getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the face to set
	 */
	public void setFace(String face) {
		this.face = face;
	}

	/**
	 * @return the zrkn
	 */
	public Double getZrkn() {
		return zrkn;
	}

	/**
	 * @param zrkn
	 *            the zrkn to set
	 */
	public void setZrkn(Double zrkn) {
		this.zrkn = zrkn;
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the xpho
	 */
	public Double getXpho() {
		return xpho;
	}

	/**
	 * @param xpho
	 *            the xpho to set
	 */
	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	/**
	 * @return the xpkn
	 */
	public Double getXpkn() {
		return xpkn;
	}

	/**
	 * @param xpkn
	 *            the xpkn to set
	 */
	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	/**
	 * @return the xpcn
	 */
	public Double getXpcn() {
		return xpcn;
	}

	/**
	 * @param xpcn
	 *            the xpcn to set
	 */
	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	/**
	 * @return the jbcn
	 */
	public Integer getJbcn() {
		return jbcn;
	}

	/**
	 * @param jbcn
	 *            the jbcn to set
	 */
	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	/**
	 * @return the xphx
	 */
	public Double getXphx() {
		return xphx;
	}

	/**
	 * @param xphx
	 *            the xphx to set
	 */
	public void setXphx(Double xphx) {
		this.xphx = xphx;
	}

	/**
	 * @return the xphs
	 */
	public Double getXphs() {
		return xphs;
	}

	/**
	 * @param xphs
	 *            the xphs to set
	 */
	public void setXphs(Double xphs) {
		this.xphs = xphs;
	}

	/**
	 * @return the xpkx
	 */
	public Double getXpkx() {
		return xpkx;
	}

	/**
	 * @param xpkx
	 *            the xpkx to set
	 */
	public void setXpkx(Double xpkx) {
		this.xpkx = xpkx;
	}

	/**
	 * @return the xpks
	 */
	public Double getXpks() {
		return xpks;
	}

	/**
	 * @param xpks
	 *            the xpks to set
	 */
	public void setXpks(Double xpks) {
		this.xpks = xpks;
	}

	/**
	 * @return the jszl
	 */
	public Integer getJszl() {
		return jszl;
	}

	/**
	 * @param jszl
	 *            the jszl to set
	 */
	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	/**
	 * @return the sjzl
	 */
	public Integer getSjzl() {
		return sjzl;
	}

	/**
	 * @param sjzl
	 *            the sjzl to set
	 */
	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	/**
	 * @return the jnzl
	 */
	public Integer getJnzl() {
		return jnzl;
	}

	/**
	 * @param jnzl
	 *            the jnzl to set
	 */
	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	/**
	 * @return the mazl
	 */
	public Integer getMazl() {
		return mazl;
	}

	/**
	 * @param mazl
	 *            the mazl to set
	 */
	public void setMazl(Integer mazl) {
		this.mazl = mazl;
	}

	/**
	 * @return the zpzl
	 */
	public Integer getZpzl() {
		return zpzl;
	}

	/**
	 * @param zpzl
	 *            the zpzl to set
	 */
	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	/**
	 * @return the dmzl
	 */
	public Integer getDmzl() {
		return dmzl;
	}

	/**
	 * @param dmzl
	 *            the dmzl to set
	 */
	public void setDmzl(Integer dmzl) {
		this.dmzl = dmzl;
	}

	/**
	 * @return the bzcl
	 */
	public Integer getBzcl() {
		return bzcl;
	}

	/**
	 * @param bzcl
	 *            the bzcl to set
	 */
	public void setBzcl(Integer bzcl) {
		this.bzcl = bzcl;
	}

	/**
	 * @return the zshu
	 */
	public Integer getZshu() {
		return zshu;
	}

	/**
	 * @param zshu
	 *            the zshu to set
	 */
	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	/**
	 * @return the mdan
	 */
	public Double getMdan() {
		return mdan;
	}

	/**
	 * @param mdan
	 *            the mdan to set
	 */
	public void setMdan(Double mdan) {
		this.mdan = mdan;
	}

	/**
	 * @return the bdan
	 */
	public Double getBdan() {
		return bdan;
	}

	/**
	 * @param bdan
	 *            the bdan to set
	 */
	public void setBdan(Double bdan) {
		this.bdan = bdan;
	}

	/**
	 * @return the bzxs
	 */
	public Double getBzxs() {
		return bzxs;
	}

	/**
	 * @param bzxs
	 *            the bzxs to set
	 */
	public void setBzxs(Double bzxs) {
		this.bzxs = bzxs;
	}

	/**
	 * @return the ckno
	 */
	public Integer getCkno() {
		return ckno;
	}

	/**
	 * @param ckno
	 *            the ckno to set
	 */
	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	/**
	 * @return the chan
	 */
	public String getChan() {
		return chan;
	}

	/**
	 * @param chan
	 *            the chan to set
	 */
	public void setChan(String chan) {
		this.chan = chan;
	}

	/**
	 * @return the deng
	 */
	public String getDeng() {
		return deng;
	}

	/**
	 * @param deng
	 *            the deng to set
	 */
	public void setDeng(String deng) {
		this.deng = deng;
	}

	/**
	 * @return the czdm
	 */
	public String getCzdm() {
		return czdm;
	}

	/**
	 * @param czdm
	 *            the czdm to set
	 */
	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	/**
	 * @return the qqdm
	 */
	public String getQqdm() {
		return qqdm;
	}

	/**
	 * @param qqdm
	 *            the qqdm to set
	 */
	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	/**
	 * @return the jdyn
	 */
	public String getJdyn() {
		return jdyn;
	}

	/**
	 * @param jdyn
	 *            the jdyn to set
	 */
	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	/**
	 * @return the jsyn
	 */
	public String getJsyn() {
		return jsyn;
	}

	/**
	 * @param jsyn
	 *            the jsyn to set
	 */
	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	/**
	 * @return the sczm
	 */
	public Double getSczm() {
		return sczm;
	}

	/**
	 * @param sczm
	 *            the sczm to set
	 */
	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	/**
	 * @return the scfm
	 */
	public Double getScfm() {
		return scfm;
	}

	/**
	 * @param scfm
	 *            the scfm to set
	 */
	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	/**
	 * @return the ying
	 */
	public Integer getYing() {
		return ying;
	}

	/**
	 * @param ying
	 *            the ying to set
	 */
	public void setYing(Integer ying) {
		this.ying = ying;
	}

	/**
	 * @return the jscn
	 */
	public Integer getJscn() {
		return jscn;
	}

	/**
	 * @param jscn
	 *            the jscn to set
	 */
	public void setJscn(Integer jscn) {
		this.jscn = jscn;
	}

	/**
	 * @return the cutc
	 */
	public Integer getCutc() {
		return cutc;
	}

	/**
	 * @param cutc
	 *            the cutc to set
	 */
	public void setCutc(Integer cutc) {
		this.cutc = cutc;
	}

	/**
	 * @return the losc
	 */
	public Integer getLosc() {
		return losc;
	}

	/**
	 * @param losc
	 *            the losc to set
	 */
	public void setLosc(Integer losc) {
		this.losc = losc;
	}

	/**
	 * @return the losq
	 */
	public String getLosq() {
		return losq;
	}

	/**
	 * @param losq
	 *            the losq to set
	 */
	public void setLosq(String losq) {
		this.losq = losq;
	}

	/**
	 * @return the phgs
	 */
	public Integer getPhgs() {
		return phgs;
	}

	/**
	 * @param phgs
	 *            the phgs to set
	 */
	public void setPhgs(Integer phgs) {
		this.phgs = phgs;
	}

	/**
	 * @return the rjsx
	 */
	public Integer getRjsx() {
		return rjsx;
	}

	/**
	 * @param rjsx
	 *            the rjsx to set
	 */
	public void setRjsx(Integer rjsx) {
		this.rjsx = rjsx;
	}

	/**
	 * @return the rjly
	 */
	public Integer getRjly() {
		return rjly;
	}

	/**
	 * @param rjly
	 *            the rjly to set
	 */
	public void setRjly(Integer rjly) {
		this.rjly = rjly;
	}

	/**
	 * @return the rjet
	 */
	public Integer getRjet() {
		return rjet;
	}

	/**
	 * @param rjet
	 *            the rjet to set
	 */
	public void setRjet(Integer rjet) {
		this.rjet = rjet;
	}

	/**
	 * @return the bhbl
	 */
	public String getBhbl() {
		return bhbl;
	}

	/**
	 * @param bhbl
	 *            the bhbl to set
	 */
	public void setBhbl(String bhbl) {
		this.bhbl = bhbl;
	}

	/**
	 * @return the jsno
	 */
	public String getJsno() {
		return jsno;
	}

	/**
	 * @param jsno
	 *            the jsno to set
	 */
	public void setJsno(String jsno) {
		this.jsno = jsno;
	}

	/**
	 * @return the slin
	 */
	public String getSlin() {
		return slin;
	}

	/**
	 * @param slin
	 *            the slin to set
	 */
	public void setSlin(String slin) {
		this.slin = slin;
	}

	/**
	 * @return the dmrk
	 */
	public String getDmrk() {
		return dmrk;
	}

	/**
	 * @param dmrk
	 *            the dmrk to set
	 */
	public void setDmrk(String dmrk) {
		this.dmrk = dmrk;
	}

	/**
	 * @return the cqpl
	 */
	public String getCqpl() {
		return cqpl;
	}

	/**
	 * @param cqpl
	 *            the cqpl to set
	 */
	public void setCqpl(String cqpl) {
		this.cqpl = cqpl;
	}

	/**
	 * @return the cp15
	 */
	public Integer getCp15() {
		return cp15;
	}

	/**
	 * @param cp15
	 *            the cp15 to set
	 */
	public void setCp15(Integer cp15) {
		this.cp15 = cp15;
	}

	/**
	 * @return the cp10
	 */
	public Integer getCp10() {
		return cp10;
	}

	/**
	 * @param cp10
	 *            the cp10 to set
	 */
	public void setCp10(Integer cp10) {
		this.cp10 = cp10;
	}

	/**
	 * @return the cp05
	 */
	public Integer getCp05() {
		return cp05;
	}

	/**
	 * @param cp05
	 *            the cp05 to set
	 */
	public void setCp05(Integer cp05) {
		this.cp05 = cp05;
	}

	/**
	 * @return the cp00
	 */
	public Integer getCp00() {
		return cp00;
	}

	/**
	 * @param cp00
	 *            the cp00 to set
	 */
	public void setCp00(Integer cp00) {
		this.cp00 = cp00;
	}

	/**
	 * @return the cm05
	 */
	public Integer getCm05() {
		return cm05;
	}

	/**
	 * @param cm05
	 *            the cm05 to set
	 */
	public void setCm05(Integer cm05) {
		this.cm05 = cm05;
	}

	public String getSckn() {
		return sckn;
	}

	public void setSckn(String sckn) {
		this.sckn = sckn;
	}

	public String getJdcn() {
		return jdcn;
	}

	public void setJdcn(String jdcn) {
		this.jdcn = jdcn;
	}

	/**
	 * @return the lnsd
	 */
	public Integer getLnsd() {
		return lnsd;
	}

	/**
	 * @param lnsd
	 *            the lnsd to set
	 */
	public void setLnsd(Integer lnsd) {
		this.lnsd = lnsd;
	}

	/**
	 * @return the bdji
	 */
	public String getBdji() {
		return bdji;
	}

	/**
	 * @param bdji
	 *            the bdji to set
	 */
	public void setBdji(String bdji) {
		this.bdji = bdji;
	}

	/**
	 * @return the zndd
	 */
	public String getZndd() {
		return zndd;
	}

	/**
	 * @param zndd
	 *            the zndd to set
	 */
	public void setZndd(String zndd) {
		this.zndd = zndd;
	}

	/**
	 * @return the hndd
	 */
	public String getHndd() {
		return hndd;
	}

	/**
	 * @param hndd
	 *            the hndd to set
	 */
	public void setHndd(String hndd) {
		this.hndd = hndd;
	}

	/**
	 * @return the zbwd
	 */
	public String getZbwd() {
		return zbwd;
	}

	/**
	 * @param zbwd
	 *            the zbwd to set
	 */
	public void setZbwd(String zbwd) {
		this.zbwd = zbwd;
	}

	public String getQduz() {
		return qduz;
	}

	public void setQduz(String qduz) {
		this.qduz = qduz;
	}

	/**
	 * @return the fmby
	 */
	public String getFmby() {
		return fmby;
	}

	/**
	 * @param fmby
	 *            the fmby to set
	 */
	public void setFmby(String fmby) {
		this.fmby = fmby;
	}

	/**
	 * @return the qxn1
	 */
	public String getQxn1() {
		return qxn1;
	}

	/**
	 * @param qxn1
	 *            the qxn1 to set
	 */
	public void setQxn1(String qxn1) {
		this.qxn1 = qxn1;
	}

	/**
	 * @return the qxd1
	 */
	public String getQxd1() {
		return qxd1;
	}

	/**
	 * @param qxd1
	 *            the qxd1 to set
	 */
	public void setQxd1(String qxd1) {
		this.qxd1 = qxd1;
	}

	/**
	 * @return the qxz1
	 */
	public Integer getQxz1() {
		return qxz1;
	}

	/**
	 * @param qxz1
	 *            the qxz1 to set
	 */
	public void setQxz1(Integer qxz1) {
		this.qxz1 = qxz1;
	}

	/**
	 * @return the qxn2
	 */
	public String getQxn2() {
		return qxn2;
	}

	/**
	 * @param qxn2
	 *            the qxn2 to set
	 */
	public void setQxn2(String qxn2) {
		this.qxn2 = qxn2;
	}

	/**
	 * @return the qxd2
	 */
	public String getQxd2() {
		return qxd2;
	}

	/**
	 * @param qxd2
	 *            the qxd2 to set
	 */
	public void setQxd2(String qxd2) {
		this.qxd2 = qxd2;
	}

	/**
	 * @return the qxz2
	 */
	public Integer getQxz2() {
		return qxz2;
	}

	/**
	 * @param qxz2
	 *            the qxz2 to set
	 */
	public void setQxz2(Integer qxz2) {
		this.qxz2 = qxz2;
	}

	/**
	 * @return the qxn3
	 */
	public String getQxn3() {
		return qxn3;
	}

	/**
	 * @param qxn3
	 *            the qxn3 to set
	 */
	public void setQxn3(String qxn3) {
		this.qxn3 = qxn3;
	}

	/**
	 * @return the qxd3
	 */
	public String getQxd3() {
		return qxd3;
	}

	/**
	 * @param qxd3
	 *            the qxd3 to set
	 */
	public void setQxd3(String qxd3) {
		this.qxd3 = qxd3;
	}

	/**
	 * @return the qxz3
	 */
	public Integer getQxz3() {
		return qxz3;
	}

	/**
	 * @param qxz3
	 *            the qxz3 to set
	 */
	public void setQxz3(Integer qxz3) {
		this.qxz3 = qxz3;
	}

	/**
	 * @return the qxn4
	 */
	public String getQxn4() {
		return qxn4;
	}

	/**
	 * @param qxn4
	 *            the qxn4 to set
	 */
	public void setQxn4(String qxn4) {
		this.qxn4 = qxn4;
	}

	/**
	 * @return the qxd4
	 */
	public String getQxd4() {
		return qxd4;
	}

	/**
	 * @param qxd4
	 *            the qxd4 to set
	 */
	public void setQxd4(String qxd4) {
		this.qxd4 = qxd4;
	}

	/**
	 * @return the qxz4
	 */
	public Integer getQxz4() {
		return qxz4;
	}

	/**
	 * @param qxz4
	 *            the qxz4 to set
	 */
	public void setQxz4(Integer qxz4) {
		this.qxz4 = qxz4;
	}

	/**
	 * @return the qxn5
	 */
	public String getQxn5() {
		return qxn5;
	}

	/**
	 * @param qxn5
	 *            the qxn5 to set
	 */
	public void setQxn5(String qxn5) {
		this.qxn5 = qxn5;
	}

	/**
	 * @return the qxd5
	 */
	public String getQxd5() {
		return qxd5;
	}

	/**
	 * @param qxd5
	 *            the qxd5 to set
	 */
	public void setQxd5(String qxd5) {
		this.qxd5 = qxd5;
	}

	/**
	 * @return the qxz5
	 */
	public Integer getQxz5() {
		return qxz5;
	}

	/**
	 * @param qxz5
	 *            the qxz5 to set
	 */
	public void setQxz5(Integer qxz5) {
		this.qxz5 = qxz5;
	}

	/**
	 * @return the qxn6
	 */
	public String getQxn6() {
		return qxn6;
	}

	/**
	 * @param qxn6
	 *            the qxn6 to set
	 */
	public void setQxn6(String qxn6) {
		this.qxn6 = qxn6;
	}

	/**
	 * @return the qxd6
	 */
	public String getQxd6() {
		return qxd6;
	}

	/**
	 * @param qxd6
	 *            the qxd6 to set
	 */
	public void setQxd6(String qxd6) {
		this.qxd6 = qxd6;
	}

	/**
	 * @return the qxz6
	 */
	public Integer getQxz6() {
		return qxz6;
	}

	/**
	 * @param qxz6
	 *            the qxz6 to set
	 */
	public void setQxz6(Integer qxz6) {
		this.qxz6 = qxz6;
	}

	/**
	 * @return the qmrk
	 */
	public String getQmrk() {
		return qmrk;
	}

	/**
	 * @param qmrk
	 *            the qmrk to set
	 */
	public void setQmrk(String qmrk) {
		this.qmrk = qmrk;
	}

	/**
	 * @return the mudi
	 */
	public String getMudi() {
		return mudi;
	}

	/**
	 * @param mudi
	 *            the mudi to set
	 */
	public void setMudi(String mudi) {
		this.mudi = mudi;
	}

	/**
	 * @return the fang
	 */
	public String getFang() {
		return fang;
	}

	/**
	 * @param fang
	 *            the fang to set
	 */
	public void setFang(String fang) {
		this.fang = fang;
	}

	/**
	 * @return the hmrk
	 */
	public String getHmrk() {
		return hmrk;
	}

	/**
	 * @param hmrk
	 *            the hmrk to set
	 */
	public void setHmrk(String hmrk) {
		this.hmrk = hmrk;
	}

	/**
	 * @return the zyze
	 */
	public String getZyze() {
		return zyze;
	}

	/**
	 * @param zyze
	 *            the zyze to set
	 */
	public void setZyze(String zyze) {
		this.zyze = zyze;
	}

	/**
	 * @return the vari
	 */
	public String getVari() {
		return vari;
	}

	/**
	 * @param vari
	 *            the vari to set
	 */
	public void setVari(String vari) {
		this.vari = vari;
	}

	/**
	 * @return the duic
	 */
	public String getDuic() {
		return duic;
	}

	/**
	 * @param duic
	 *            the duic to set
	 */
	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getKpn1Flag() {
		return kpn1Flag;
	}

	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
	}

	public String getKpn2Flag() {
		return kpn2Flag;
	}

	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
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

	public Date getEtsd() {
		return etsd;
	}

	public void setEtsd(Date etsd) {
		this.etsd = etsd;
	}

	public Date getSlsd() {
		return slsd;
	}

	public void setSlsd(Date slsd) {
		this.slsd = slsd;
	}

	public Date getPssd() {
		return pssd;
	}

	public void setPssd(Date pssd) {
		this.pssd = pssd;
	}

	public Date getPxsd() {
		return pxsd;
	}

	public void setPxsd(Date pxsd) {
		this.pxsd = pxsd;
	}

	public Date getFmsd() {
		return fmsd;
	}

	public void setFmsd(Date fmsd) {
		this.fmsd = fmsd;
	}

	public Date getKbsd() {
		return kbsd;
	}

	public void setKbsd(Date kbsd) {
		this.kbsd = kbsd;
	}

	public Date getChzs() {
		return chzs;
	}

	public void setChzs(Date chzs) {
		this.chzs = chzs;
	}

	public Date getChsd() {
		return chsd;
	}

	public void setChsd(Date chsd) {
		this.chsd = chsd;
	}

	public String getSpdj() {
		return spdj;
	}

	public void setSpdj(String spdj) {
		this.spdj = spdj;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getBqht() {
		return bqht;
	}

	public void setBqht(String bqht) {
		this.bqht = bqht;
	}

	public String getQzch() {
		return qzch;
	}

	public void setQzch(String qzch) {
		this.qzch = qzch;
	}

	public String getChno() {
		return chno;
	}

	public void setChno(String chno) {
		this.chno = chno;
	}

	public String getJcno() {
		return jcno;
	}

	public void setJcno(String jcno) {
		this.jcno = jcno;
	}

	public String getShno() {
		return shno;
	}

	public void setShno(String shno) {
		this.shno = shno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
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

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public void setJdez(String jdez) {
		this.jdez = jdez;
	}

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public Date getBjdy() {
		return bjdy;
	}

	public void setBjdy(Date bjdy) {
		this.bjdy = bjdy;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getCpno() {
		return cpno;
	}

	public void setCpno(String cpno) {
		this.cpno = cpno;
	}

	public String getQxn7() {
		return qxn7;
	}

	public void setQxn7(String qxn7) {
		this.qxn7 = qxn7;
	}

	public String getQxd7() {
		return qxd7;
	}

	public void setQxd7(String qxd7) {
		this.qxd7 = qxd7;
	}

	public Integer getQxz7() {
		return qxz7;
	}

	public void setQxz7(Integer qxz7) {
		this.qxz7 = qxz7;
	}

	public String getQxn8() {
		return qxn8;
	}

	public void setQxn8(String qxn8) {
		this.qxn8 = qxn8;
	}

	public String getQxd8() {
		return qxd8;
	}

	public void setQxd8(String qxd8) {
		this.qxd8 = qxd8;
	}

	public Integer getQxz8() {
		return qxz8;
	}

	public void setQxz8(Integer qxz8) {
		this.qxz8 = qxz8;
	}

	public String getDbbj() {
		return dbbj;
	}

	public void setDbbj(String dbbj) {
		this.dbbj = dbbj;
	}

	public String getQqd2() {
		return qqd2;
	}

	public void setQqd2(String qqd2) {
		this.qqd2 = qqd2;
	}

	public String getQqd3() {
		return qqd3;
	}

	public void setQqd3(String qqd3) {
		this.qqd3 = qqd3;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getSfbs() {
		return sfbs;
	}

	public void setSfbs(String sfbs) {
		this.sfbs = sfbs;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public Integer getZrzl() {
		return zrzl;
	}

	public void setZrzl(Integer zrzl) {
		this.zrzl = zrzl;
	}

	/**
	 * @return the ymax
	 */
	public String getYmax() {
		return ymax;
	}

	/**
	 * @param ymax
	 *            the ymax to set
	 */
	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	/**
	 * @return the ymin
	 */
	public String getYmin() {
		return ymin;
	}

	/**
	 * @param ymin
	 *            the ymin to set
	 */
	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public Date getYdsj() {
		return ydsj;
	}

	public void setYdsj(Date ydsj) {
		this.ydsj = ydsj;
	}

	public String getBopg() {
		return bopg;
	}

	public void setBopg(String bopg) {
		this.bopg = bopg;
	}

	public String getBopj() {
		return bopj;
	}

	public void setBopj(String bopj) {
		this.bopj = bopj;
	}

	public String getBdrg() {
		return bdrg;
	}

	public void setBdrg(String bdrg) {
		this.bdrg = bdrg;
	}

	public String getBdrj() {
		return bdrj;
	}

	public void setBdrj(String bdrj) {
		this.bdrj = bdrj;
	}

	public String getZopz() {
		return zopz;
	}

	public void setZopz(String zopz) {
		this.zopz = zopz;
	}

	public String getZdrz() {
		return zdrz;
	}

	public void setZdrz(String zdrz) {
		this.zdrz = zdrz;
	}

	public String getZndz() {
		return zndz;
	}

	public void setZndz(String zndz) {
		this.zndz = zndz;
	}

	public String getHndz() {
		return hndz;
	}

	public void setHndz(String hndz) {
		this.hndz = hndz;
	}

	public String getZbog() {
		return zbog;
	}

	public void setZbog(String zbog) {
		this.zbog = zbog;
	}

	public String getZboj() {
		return zboj;
	}

	public void setZboj(String zboj) {
		this.zboj = zboj;
	}

	public String getJinj() {
		return jinj;
	}

	public void setJinj(String jinj) {
		this.jinj = jinj;
	}

	public String getBlbj() {
		return blbj;
	}

	public void setBlbj(String blbj) {
		this.blbj = blbj;
	}

	public Integer getLosc2() {
		return losc2;
	}

	public void setLosc2(Integer losc2) {
		this.losc2 = losc2;
	}

	public String getLosq2() {
		return losq2;
	}

	public void setLosq2(String losq2) {
		this.losq2 = losq2;
	}

	public String getMadr() {
		return madr;
	}

	public void setMadr(String madr) {
		this.madr = madr;
	}

	public String getMaop() {
		return maop;
	}

	public void setMaop(String maop) {
		this.maop = maop;
	}

}
