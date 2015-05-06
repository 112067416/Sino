package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订货指示DB
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ZSDHTP")
public class ZsdhTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * 指示书No
	 */
	@Id
	@Column(name = "ZSNO_", length = 6)
	private String zsno;
	/**
	 * 操作Mode
	 */
	@Column(name = "CAOZ_", length = 1)
	private String caoz;
	/**
	 * 装入宽
	 */
	@Column(name = "ZRKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double zrkn;
	/**
	 * 剪切长
	 */
	@Column(name = "JDCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double jdcn;
	/**
	 * 剪边标记
	 */
	@Column(name = "JBBJ_", length = 1)
	private String jbbj;
	/**
	 * 剪边后宽
	 */
	@Column(name = "JBHK_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double jbhk;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 附着量（g/m2）
	 */
	@Column(name = "FUGM_", length = 12)
	private String fugm;
	/**
	 * 附着量.正面.下限值
	 */
	@Column(name = "FUZX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzx;
	/**
	 * 附着量.正面.上限值
	 */
	@Column(name = "FUZS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzs;
	/**
	 * 附着量.反面.下限值
	 */
	@Column(name = "FUFX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufx;
	/**
	 * 附着量.反面.上限值
	 */
	@Column(name = "FUFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufs;
	/**
	 * reflow要否
	 */
	@Column(name = "FLOW_", length = 1)
	private String flow;
	/**
	 * 订货表面精加工符号
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 合金层
	 */
	@Column(name = "HJIN_", length = 3)
	private String hjin;
	/**
	 * K-Plate洗涤强化
	 */
	@Column(name = "XDQH_", length = 1)
	private String xdqh;
	/**
	 * 化学处理方法
	 */
	@Column(name = "HXCL_", length = 4)
	private String hxcl;
	/**
	 * 目标涂油种类
	 */
	@Column(name = "YTYP_", length = 3)
	private String ytyp;
	/**
	 * 目标涂油量
	 */
	@Column(name = "YQTY_", length = 2)
	private String yqty;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 销售W否
	 */
	@Column(name = "XSWF_", length = 1)
	private String xswf;
	/**
	 * 分配等级
	 */
	@Column(name = "FPDJ_", length = 3)
	private String fpdj;
	/**
	 * 钢种类型
	 */
	@Column(name = "GZLX_", length = 4)
	private String gzlx;
	/**
	 * 直角度
	 */
	@Column(name = "JIAO_", columnDefinition = "numeric(2,1)", scale = 2, precision = 1)
	private Double jiao;
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
	 * 112张单重
	 */
	@Column(name = "D112_", columnDefinition = "numeric(5,1)", scale = 5, precision = 1)
	private Double d112;
	/**
	 * 交货地点
	 */
	@Column(name = "JHDD_", length = 1)
	private String jhdd;
	/**
	 * 内径代码
	 */
	@Column(name = "NJNO_", length = 1)
	private String njno;
	/**
	 * 内径保护筒
	 */
	@Column(name = "NJBH_", length = 1)
	private String njbh;
	/**
	 * 保护标记
	 */
	@Column(name = "PROT_", length = 1)
	private String prot;
	/**
	 * 锯齿可否
	 */
	@Column(name = "JCKF_", length = 1)
	private String jckf;
	/**
	 * 锯齿种类
	 */
	@Column(name = "JCZL_", length = 4)
	private String jczl;
	/**
	 * 制品尺寸（W表示编辑完）
	 */
	@Column(name = "ZPCC_", length = 22)
	private String zpcc;
	/**
	 * 订货规格
	 */
	@Column(name = "DHGG_", length = 6)
	private String dhgg;
	/**
	 * 订货附着量略称
	 */
	@Column(name = "DHFZ_", length = 12)
	private String dhfz;
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
	 * 尺寸允许范围.剪断长mm上限.值
	 */
	@Column(name = "CSZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double cszi;
	/**
	 * 尺寸允许范围.剪断长mm下限.值
	 */
	@Column(name = "CXZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double cxzi;
	/**
	 * 包装张数
	 */
	@Column(name = "BZZS_")
	private Integer bzzs;
	/**
	 * 批重量
	 */
	@Column(name = "PIZL_", columnDefinition = "numeric(8,3)", scale = 4, precision = 3)
	private Double pizl;
	/**
	 * 箱数
	 */
	@Column(name = "XSHU_")
	private Integer xshu;
	/**
	 * B/B重量
	 */
	@Column(name = "BBZ_", columnDefinition = "numeric(5,1)", scale = 5, precision = 1)
	private Double bbz;
	/**
	 * 零头可否
	 */
	@Column(name = "LTKF_", length = 1)
	private String ltkf;
	/**
	 * 零头下限
	 */
	@Column(name = "LTXX_")
	private Integer ltxx;
	/**
	 * 重量计算补正系数
	 */
	@Column(name = "BZXS_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double bzxs;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;
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
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * Specification
	 */
	@Column(name = "SPEC_", length = 20)
	private String spec;
	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;
	/**
	 * 出口包装No.开始No.
	 */
	@Column(name = "CKKS_")
	private Integer ckks;
	/**
	 * 出口包装No.终了No.
	 */
	@Column(name = "CKZL_")
	private Integer ckzl;
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
	 * 订货区分
	 */
	@Column(name = "DHQF_", length = 1)
	private String dhqf;
	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;
	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;
	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;
	/**
	 * 交货期
	 */
	@Column(name = "JHQI_")
	private Date jhqi;
	/**
	 * 采取指示量
	 */
	@Column(name = "CQZS_")
	private Integer cqzs;
	/**
	 * 余材状况
	 */
	@Column(name = "YCZK_", length = 1)
	private String yczk;
	/**
	 * 剪边宽变更标记
	 */
	@Column(name = "JBKB_", length = 1)
	private String jbkb;
	/**
	 * 再选别标记
	 */
	@Column(name = "ZXBB_", length = 1)
	private String zxbb;
	/**
	 * 强制标记
	 */
	@Column(name = "QZBJ_", length = 1)
	private String qzbj;
	/**
	 * 超量分配标记
	 */
	@Column(name = "CLFP_", length = 1)
	private String clfp;
	/**
	 * 指示书发行完标记
	 */
	@Column(name = "ZSFX_", length = 1)
	private String zsfx;
	/**
	 * 指示书缺陷打印标志
	 */
	@Column(name = "ZSQX_", length = 1)
	private String zsqx;

	public String getZsqx() {
		return zsqx;
	}

	public void setZsqx(String zsqx) {
		this.zsqx = zsqx;
	}

	/**
	 * 出口包装No.最新终了No.
	 */
	@Column(name = "CKZX_")
	private Integer ckzx;
	/**
	 * 装入中止标记
	 */
	@Column(name = "ZLZZ_", length = 1)
	private String zlzz;
	/**
	 * 指示完了标记
	 */
	@Column(name = "ZSBJ_", length = 1)
	private String zsbj;
	/**
	 * 指示完了时间（年月日时分秒）
	 */
	@Column(name = "ZSNY_", length = 14)
	private Date zsny;
	/**
	 * 实绩修正可否标记
	 */
	@Column(name = "SJXZ_", length = 1)
	private String sjxz;
	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;
	/**
	 * 附着量.正面(g/m2)
	 */
	@Column(name = "FUZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzm;
	/**
	 * 附着量.反面(g/m2)
	 */
	@Column(name = "FUFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufm;

	/**
	 * 是否紧急
	 */
	@Column(name = "JINJ_", length = 1)
	private String jinj;

	/**
	 * 生产线
	 */
	@Column(name = "SHCH_", length = 1)
	private String shch;
	/**
	 * 生产顺序
	 */
	@Column(name = "SORT_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double sort;

	/**
	 * <p>
	 * 是否实绩
	 * </p>
	 */
	@Column(name = "SFSJ_", length = 1)
	private String sfsj;
	/**
	 * <p>
	 * 是否Coil转sheet
	 * </p>
	 */
	@Column(name = "SFCL_", length = 1)
	private String sfcl;
	/**
	 * <p>
	 * 垫木状态
	 * </p>
	 */
	@Column(name = "SFDM_", length = 1)
	private String sfdm;
	/**
	 * <p>
	 * 备注
	 * </p>
	 */
	@Column(name = "REMK_", length = 200)
	private String remk;

	public String getSfsj() {
		return sfsj;
	}

	public void setSfsj(String sfsj) {
		this.sfsj = sfsj;
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
	 * @return the caoz
	 */
	public String getCaoz() {
		return caoz;
	}

	/**
	 * @param caoz
	 *            the caoz to set
	 */
	public void setCaoz(String caoz) {
		this.caoz = caoz;
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
	 * @return the jdcn
	 */
	public Double getJdcn() {
		return jdcn;
	}

	/**
	 * @param jdcn
	 *            the jdcn to set
	 */
	public void setJdcn(Double jdcn) {
		this.jdcn = jdcn;
	}

	/**
	 * @return the jbbj
	 */
	public String getJbbj() {
		return jbbj;
	}

	/**
	 * @param jbbj
	 *            the jbbj to set
	 */
	public void setJbbj(String jbbj) {
		this.jbbj = jbbj;
	}

	/**
	 * @return the jbhk
	 */
	public Double getJbhk() {
		return jbhk;
	}

	/**
	 * @param jbhk
	 *            the jbhk to set
	 */
	public void setJbhk(Double jbhk) {
		this.jbhk = jbhk;
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
	 * @return the fugm
	 */
	public String getFugm() {
		return fugm;
	}

	/**
	 * @param fugm
	 *            the fugm to set
	 */
	public void setFugm(String fugm) {
		this.fugm = fugm;
	}

	/**
	 * @return the fuzx
	 */
	public Double getFuzx() {
		return fuzx;
	}

	/**
	 * @param fuzx
	 *            the fuzx to set
	 */
	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	/**
	 * @return the fuzs
	 */
	public Double getFuzs() {
		return fuzs;
	}

	/**
	 * @param fuzs
	 *            the fuzs to set
	 */
	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	/**
	 * @return the fufx
	 */
	public Double getFufx() {
		return fufx;
	}

	/**
	 * @param fufx
	 *            the fufx to set
	 */
	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	/**
	 * @return the fufs
	 */
	public Double getFufs() {
		return fufs;
	}

	/**
	 * @param fufs
	 *            the fufs to set
	 */
	public void setFufs(Double fufs) {
		this.fufs = fufs;
	}

	/**
	 * @return the flow
	 */
	public String getFlow() {
		return flow;
	}

	/**
	 * @param flow
	 *            the flow to set
	 */
	public void setFlow(String flow) {
		this.flow = flow;
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
	 * @return the hjin
	 */
	public String getHjin() {
		return hjin;
	}

	/**
	 * @param hjin
	 *            the hjin to set
	 */
	public void setHjin(String hjin) {
		this.hjin = hjin;
	}

	/**
	 * @return the xdqh
	 */
	public String getXdqh() {
		return xdqh;
	}

	/**
	 * @param xdqh
	 *            the xdqh to set
	 */
	public void setXdqh(String xdqh) {
		this.xdqh = xdqh;
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
	 * @return the yqty
	 */
	public String getYqty() {
		return yqty;
	}

	/**
	 * @param yqty
	 *            the yqty to set
	 */
	public void setYqty(String yqty) {
		this.yqty = yqty;
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
	 * @return the xswf
	 */
	public String getXswf() {
		return xswf;
	}

	/**
	 * @param xswf
	 *            the xswf to set
	 */
	public void setXswf(String xswf) {
		this.xswf = xswf;
	}

	/**
	 * @return the fpdj
	 */
	public String getFpdj() {
		return fpdj;
	}

	/**
	 * @param fpdj
	 *            the fpdj to set
	 */
	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
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
	 * @return the d112
	 */
	public Double getD112() {
		return d112;
	}

	/**
	 * @param d112
	 *            the d112 to set
	 */
	public void setD112(Double d112) {
		this.d112 = d112;
	}

	/**
	 * @return the jhdd
	 */
	public String getJhdd() {
		return jhdd;
	}

	/**
	 * @param jhdd
	 *            the jhdd to set
	 */
	public void setJhdd(String jhdd) {
		this.jhdd = jhdd;
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
	 * @return the jckf
	 */
	public String getJckf() {
		return jckf;
	}

	/**
	 * @param jckf
	 *            the jckf to set
	 */
	public void setJckf(String jckf) {
		this.jckf = jckf;
	}

	/**
	 * @return the jczl
	 */
	public String getJczl() {
		return jczl;
	}

	/**
	 * @param jczl
	 *            the jczl to set
	 */
	public void setJczl(String jczl) {
		this.jczl = jczl;
	}

	/**
	 * @return the zpcc
	 */
	public String getZpcc() {
		return zpcc;
	}

	/**
	 * @param zpcc
	 *            the zpcc to set
	 */
	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
	}

	/**
	 * @return the dhgg
	 */
	public String getDhgg() {
		return dhgg;
	}

	/**
	 * @param dhgg
	 *            the dhgg to set
	 */
	public void setDhgg(String dhgg) {
		this.dhgg = dhgg;
	}

	/**
	 * @return the dhfz
	 */
	public String getDhfz() {
		return dhfz;
	}

	/**
	 * @param dhfz
	 *            the dhfz to set
	 */
	public void setDhfz(String dhfz) {
		this.dhfz = dhfz;
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
	 * @return the pizl
	 */
	public Double getPizl() {
		return pizl;
	}

	/**
	 * @param pizl
	 *            the pizl to set
	 */
	public void setPizl(Double pizl) {
		this.pizl = pizl;
	}

	/**
	 * @return the xshu
	 */
	public Integer getXshu() {
		return xshu;
	}

	/**
	 * @param xshu
	 *            the xshu to set
	 */
	public void setXshu(Integer xshu) {
		this.xshu = xshu;
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
	 * @return the ltkf
	 */
	public String getLtkf() {
		return ltkf;
	}

	/**
	 * @param ltkf
	 *            the ltkf to set
	 */
	public void setLtkf(String ltkf) {
		this.ltkf = ltkf;
	}

	/**
	 * @return the ltxx
	 */
	public Integer getLtxx() {
		return ltxx;
	}

	/**
	 * @param ltxx
	 *            the ltxx to set
	 */
	public void setLtxx(Integer ltxx) {
		this.ltxx = ltxx;
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
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param spec
	 *            the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ckks
	 */
	public Integer getCkks() {
		return ckks;
	}

	/**
	 * @param ckks
	 *            the ckks to set
	 */
	public void setCkks(Integer ckks) {
		this.ckks = ckks;
	}

	/**
	 * @return the ckzl
	 */
	public Integer getCkzl() {
		return ckzl;
	}

	/**
	 * @param ckzl
	 *            the ckzl to set
	 */
	public void setCkzl(Integer ckzl) {
		this.ckzl = ckzl;
	}

	/**
	 * @return the houu
	 */
	public Double getHouu() {
		return houu;
	}

	/**
	 * @return the kpn1Flag
	 */
	public String getKpn1Flag() {
		return kpn1Flag;
	}

	/**
	 * @param kpn1Flag
	 *            the kpn1Flag to set
	 */
	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
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
	 * @return the kpn2Flag
	 */
	public String getKpn2Flag() {
		return kpn2Flag;
	}

	/**
	 * @param kpn2Flag
	 *            the kpn2Flag to set
	 */
	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
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
	 * @return the kpn3Flag
	 */
	public String getKpn3Flag() {
		return kpn3Flag;
	}

	/**
	 * @param kpn3Flag
	 *            the kpn3Flag to set
	 */
	public void setKpn3Flag(String kpn3Flag) {
		this.kpn3Flag = kpn3Flag;
	}

	/**
	 * @return the kpn3
	 */
	public String getKpn3() {
		return kpn3;
	}

	/**
	 * @param kpn3
	 *            the kpn3 to set
	 */
	public void setKpn3(String kpn3) {
		this.kpn3 = kpn3;
	}

	/**
	 * @return the kpn4Flag
	 */
	public String getKpn4Flag() {
		return kpn4Flag;
	}

	/**
	 * @param kpn4Flag
	 *            the kpn4Flag to set
	 */
	public void setKpn4Flag(String kpn4Flag) {
		this.kpn4Flag = kpn4Flag;
	}

	/**
	 * @return the kpn4
	 */
	public String getKpn4() {
		return kpn4;
	}

	/**
	 * @param kpn4
	 *            the kpn4 to set
	 */
	public void setKpn4(String kpn4) {
		this.kpn4 = kpn4;
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
	 * @return the dhqf
	 */
	public String getDhqf() {
		return dhqf;
	}

	/**
	 * @param dhqf
	 *            the dhqf to set
	 */
	public void setDhqf(String dhqf) {
		this.dhqf = dhqf;
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
	 * @return the cond
	 */
	public String getCond() {
		return cond;
	}

	/**
	 * @param cond
	 *            the cond to set
	 */
	public void setCond(String cond) {
		this.cond = cond;
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
	 * @return the jhqi
	 */
	public Date getJhqi() {
		return jhqi;
	}

	/**
	 * @param jhqi
	 *            the jhqi to set
	 */
	public void setJhqi(Date jhqi) {
		this.jhqi = jhqi;
	}

	/**
	 * @return the cqzs
	 */
	public Integer getCqzs() {
		return cqzs;
	}

	/**
	 * @param cqzs
	 *            the cqzs to set
	 */
	public void setCqzs(Integer cqzs) {
		this.cqzs = cqzs;
	}

	/**
	 * @return the yczk
	 */
	public String getYczk() {
		return yczk;
	}

	/**
	 * @param yczk
	 *            the yczk to set
	 */
	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	/**
	 * @return the jbkb
	 */
	public String getJbkb() {
		return jbkb;
	}

	/**
	 * @param jbkb
	 *            the jbkb to set
	 */
	public void setJbkb(String jbkb) {
		this.jbkb = jbkb;
	}

	/**
	 * @return the zxbb
	 */
	public String getZxbb() {
		return zxbb;
	}

	/**
	 * @param zxbb
	 *            the zxbb to set
	 */
	public void setZxbb(String zxbb) {
		this.zxbb = zxbb;
	}

	/**
	 * @return the qzbj
	 */
	public String getQzbj() {
		return qzbj;
	}

	/**
	 * @param qzbj
	 *            the qzbj to set
	 */
	public void setQzbj(String qzbj) {
		this.qzbj = qzbj;
	}

	/**
	 * @return the clfp
	 */
	public String getClfp() {
		return clfp;
	}

	/**
	 * @param clfp
	 *            the clfp to set
	 */
	public void setClfp(String clfp) {
		this.clfp = clfp;
	}

	/**
	 * @return the zsfx
	 */
	public String getZsfx() {
		return zsfx;
	}

	/**
	 * @param zsfx
	 *            the zsfx to set
	 */
	public void setZsfx(String zsfx) {
		this.zsfx = zsfx;
	}

	/**
	 * @return the ckzx
	 */
	public Integer getCkzx() {
		return ckzx;
	}

	/**
	 * @param ckzx
	 *            the ckzx to set
	 */
	public void setCkzx(Integer ckzx) {
		this.ckzx = ckzx;
	}

	/**
	 * @return the zlzz
	 */
	public String getZlzz() {
		return zlzz;
	}

	/**
	 * @param zlzz
	 *            the zlzz to set
	 */
	public void setZlzz(String zlzz) {
		this.zlzz = zlzz;
	}

	/**
	 * @return the zsbj
	 */
	public String getZsbj() {
		return zsbj;
	}

	/**
	 * @param zsbj
	 *            the zsbj to set
	 */
	public void setZsbj(String zsbj) {
		this.zsbj = zsbj;
	}

	/**
	 * @return the zsny
	 */
	public Date getZsny() {
		return zsny;
	}

	/**
	 * @param zsny
	 *            the zsny to set
	 */
	public void setZsny(Date zsny) {
		this.zsny = zsny;
	}

	/**
	 * @return the sjxz
	 */
	public String getSjxz() {
		return sjxz;
	}

	/**
	 * @param sjxz
	 *            the sjxz to set
	 */
	public void setSjxz(String sjxz) {
		this.sjxz = sjxz;
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
	public Double getFuzm() {
		return fuzm;
	}

	/**
	 * @param fuzm
	 *            the fuzm to set
	 */
	public void setFuzm(Double fuzm) {
		this.fuzm = fuzm;
	}

	/**
	 * @return the fufm
	 */
	public Double getFufm() {
		return fufm;
	}

	/**
	 * @param fufm
	 *            the fufm to set
	 */
	public void setFufm(Double fufm) {
		this.fufm = fufm;
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

	public String getJinj() {
		return jinj;
	}

	public void setJinj(String jinj) {
		this.jinj = jinj;
	}

	public String getShch() {
		return shch;
	}

	public void setShch(String shch) {
		this.shch = shch;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getSfcl() {
		return sfcl;
	}

	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}

	public String getSfdm() {
		return sfdm;
	}

	public void setSfdm(String sfdm) {
		this.sfdm = sfdm;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}

}
