package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 制造仕样（制造工艺）主文件
 * 
 * @author 许德建[xudejian@126.com] <div>数据库字段</div> <div>PCFDF</div> <div>PCFT
 *         1</div> <div>PCFO 1,1,3,5,1</div> <div>PCFL ZZFLAG 1 2</div>
 *         <div>PCFL ZZCREA 1 14</div> <div>PCFL ZZUPDA 1 14</div> <div>PCFL
 *         ZZPROG 1 8</div> <div>PCFL ZZSTAT 1 1</div> <div>PCFL ZZRSV1 1
 *         20</div> <div>PCFL ZZRSV2 2 22</div> <div>PCFL ZZSYNO 1 6</div>
 *         <div>PCFL ZZGGNO 1 4</div> <div>PCFL ZZFUDW 1 2</div> <div>PCFL
 *         ZZFUZM 1 3</div> <div>PCFL ZZFUFM 1 3</div> <div>PCFL ZZPZNO 1
 *         2</div> <div>PCFL ZZCHDX 1 2</div> <div>PCFL ZZHMAX 2 7/3</div>
 *         <div>PCFL ZZHMIN 2 7/3</div> <div>PCFL ZZKMAX 2 9/2</div> <div>PCFL
 *         ZZKMIN 2 9/2</div> <div>PCFL ZZCMAX 2 9/2</div> <div>PCFL ZZCMIN 2
 *         9/2</div> <div>PCFL ZZNWAI 1 1</div> <div>PCFL ZZUSER 1 4</div>
 *         <div>PCFL ZZFACE 1 2</div> <div>PCFL ZZCOND 1 4</div> <div>PCFL
 *         ZZPLAT 1 1</div> <div>PCFL ZZJCXS 1 4</div> <div>PCFL ZZHOUU 2
 *         7/3</div> <div>PCFL ZZKUAN 2 9/2</div> <div>PCFL ZZCANG 2 9/2</div>
 *         <div>PCFL ZZJIAN 1 1</div> <div>PCFL ZZHJIN 1 1</div> <div>PCFL
 *         ZZYTYP 1 1</div> <div>PCFL ZZYQTY 1 2</div> <div>PCFL ZZFPDJ 1
 *         3</div> <div>PCFL ZZJIAO 2 5/1</div> <div>PCFL ZZNJBH 1 1</div>
 *         <div>PCFL ZZPROT 1 1</div> <div>PCFL ZZLTDW 1 1</div> <div>PCFL
 *         ZZLTZI 2 6</div> <div>PCFL ZZKBAO 1 3</div> <div>PCFL ZZYYAN 1
 *         1</div> <div>PCFL ZZKPN1 1 9</div> <div>PCFL ZZKPN2 1 9</div>
 *         <div>PCFL ZZYUNY 1 6</div> <div>PCFL ZZZRKN 2 9/2</div> <div>PCFL
 *         ZZYMAX 1 2</div> <div>PCFL ZZYMIN 1 2</div> <div>PCFL ZZFUZS 2
 *         7/2</div> <div>PCFL ZZFUZX 2 7/2</div> <div>PCFL ZZFUFS 2 7/2</div>
 *         <div>PCFL ZZFUFX 2 7/2</div> <div>PCFL ZZHSFU 1 1</div> <div>PCFL
 *         ZZHSZI 2 5/1</div> <div>PCFL ZZHXFU 1 1</div> <div>PCFL ZZHXZI 2
 *         5/1</div> <div>PCFL ZZKSFU 1 1</div> <div>PCFL ZZKSZI 2 5/2</div>
 *         <div>PCFL ZZKXFU 1 1</div> <div>PCFL ZZKXZI 2 5/2</div> <div>PCFL
 *         ZZCSFU 1 1</div> <div>PCFL ZZCSZI 2 5/2</div> <div>PCFL ZZCXFU 1
 *         1</div> <div>PCFL ZZCXZI 2 5/2</div> <div>PCFL ZZBZCL 2 4</div>
 *         <div>PCFL ZZDMZL 2 4</div> <div>PCFL ZZDMFX 1 1</div>
 */
@Entity
@Table(name = "SINO_ZZSYMP")
public class ZzsyMp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 设置厚上公差，带符号
	 */
	public void setHsfz(String hsfz) {
		if (hsfz == null) {
			return;
		}
		double fu = 1.0;
		if (hsfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			hszi = Double.valueOf(hsfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			hszi = null;
		}
	}

	/**
	 * 设置厚下公差，带符号
	 */
	public void setHxfz(String hxfz) {
		if (hxfz == null) {
			return;
		}
		double fu = 1.0;
		if (hxfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			hxzi = Double.valueOf(hxfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			hxzi = null;
		}
	}

	/**
	 * 设置宽上公差，带符号
	 */
	public void setKsfz(String ksfz) {
		if (ksfz == null) {
			return;
		}
		double fu = 1.0;
		if (ksfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			kszi = Double.valueOf(ksfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			kszi = null;
		}
	}

	/**
	 * 设置宽下公差，带符号
	 */
	public void setKxfz(String kxfz) {
		if (kxfz == null) {
			return;
		}
		double fu = 1.0;
		if (kxfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			kxzi = Double.valueOf(kxfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			kxzi = null;
		}
	}

	/**
	 * 设置长上公差，带符号
	 */
	public void setCsfz(String csfz) {
		if (csfz == null) {
			return;
		}
		double fu = 1.0;
		if (csfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			cszi = Double.valueOf(csfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			cszi = null;
		}
	}

	/**
	 * 设置长下公差，带符号
	 */
	public void setCxfz(String cxfz) {
		if (cxfz == null) {
			return;
		}
		double fu = 1.0;
		if (cxfz.indexOf('-') != -1) {
			fu = -1.0;
		}
		try {
			cxzi = Double.valueOf(cxfz.replaceAll("\\+|-", "")) * fu;
		}
		catch (NumberFormatException e) {
			cxzi = null;
		}
	}

	/**
	 * 记录标识
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）
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
	@Column(name = "RSV2_", columnDefinition = "numeric(22)")
	private BigDecimal rsv2;

	/**
	 * 制造仕样NO
	 */
	@Id
	@Column(name = "SYNO_", length = 6)
	private String syno;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

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
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;

	/**
	 * 差厚镀锡
	 */
	@Column(name = "CHDX_", length = 2)
	private String chdx;

	/**
	 * 尺寸.厚上限值
	 */
	@Column(name = "HMAX_", columnDefinition = "numeric(7,3)")
	private Double hmax;

	/**
	 * 尺寸.厚下限值
	 */
	@Column(name = "HMIN_", columnDefinition = "numeric(7,3)")
	private Double hmin;

	/**
	 * 尺寸.宽上限值
	 */
	@Column(name = "KMAX_", columnDefinition = "numeric(9,2)")
	private Double kmax;

	/**
	 * 尺寸.宽下限值
	 */
	@Column(name = "KMIN_", columnDefinition = "numeric(9,2)")
	private Double kmin;

	/**
	 * 尺寸.长上限值
	 */
	@Column(name = "CMAX_", columnDefinition = "numeric(9,2)")
	private Double cmax;

	/**
	 * 尺寸.长下限值
	 */
	@Column(name = "CMIN_", columnDefinition = "numeric(9,2)")
	private Double cmin;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 表面精加工
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;

	/**
	 * K-PLATE
	 */
	@Column(name = "PLAT_", length = 1)
	private String plat;

	/**
	 * 锯齿形式
	 */
	@Column(name = "JCXS_", length = 4)
	private String jcxs;

	/**
	 * 尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(7,3)")
	private Double houu;
	/**
	 * 尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(9,2)")
	private Double kuan;
	/**
	 * 尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(9,2)")
	private Double cang;
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
	@Column(name = "JIAO_", columnDefinition = "numeric(5,1)")
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
	@Column(name = "LTZI_", columnDefinition = "numeric(6)")
	private Integer ltzi;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * 压延方向指示标志
	 */
	@Column(name = "YYAN_", length = 1)
	private String yyan;
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
	@Column(name = "ZRKN_", columnDefinition = "numeric(9,2)")
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
	@Column(name = "FUZS_", columnDefinition = "numeric(7,2)")
	private Double fuzs;
	/**
	 * 附着面.正面.下限值
	 */
	@Column(name = "FUZX_", columnDefinition = "numeric(7,2)")
	private Double fuzx;
	/**
	 * 附着面.反面.上限值
	 */
	@Column(name = "FUFS_", columnDefinition = "numeric(7,2)")
	private Double fufs;
	/**
	 * 附着面.反面.下限值
	 */
	@Column(name = "FUFX_", columnDefinition = "numeric(7,2)")
	private Double fufx;
	/**
	 * 尺寸允许范围.厚%上限.值
	 */
	@Column(name = "HSZI_", columnDefinition = "numeric(5,1)")
	private Double hszi;
	/**
	 * 尺寸允许范围.厚%下限.值
	 */
	@Column(name = "HXZI_", columnDefinition = "numeric(5,1)")
	private Double hxzi;

	/**
	 * 尺寸允许范围.宽mm上限.值
	 */
	@Column(name = "KSZI_", columnDefinition = "numeric(5,2)")
	private Double kszi;
	/**
	 * 尺寸允许范围.宽mm下限.值
	 */
	@Column(name = "KXZI_", columnDefinition = "numeric(5,2)")
	private Double kxzi;

	/**
	 * 尺寸允许范围.长mm上限.值
	 */
	@Column(name = "CSZI_", columnDefinition = "numeric(5,2)")
	private Double cszi;
	/**
	 * 尺寸允许范围.长mm下限.值
	 */
	@Column(name = "CXZI_", columnDefinition = "numeric(5,2)")
	private Double cxzi;
	/**
	 * 包装材料重量（Kg）
	 */
	@Column(name = "BZCL_", columnDefinition = "numeric(4)")
	private Integer bzcl;
	/**
	 * 垫木重量(Kg)
	 */
	@Column(name = "DMZL_", columnDefinition = "numeric(4)")
	private Integer dmzl;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;

	/**
	 * 木工所NO
	 */
	@Column(name = "MSGN_", length = 256)
	private String mgsn;

	/**
	 * 备注1
	 */
	@Column(name = "BZ1_", length = 512)
	private String bz1;

	/**
	 * 备注2
	 */
	@Column(name = "BZ2_", length = 512)
	private String bz2;

	/**
	 * 备注3
	 */
	@Column(name = "BZ3_", length = 512)
	private String bz3;

	/**
	 * 检查证明书提示符
	 */
	@Column(name = "JCTS_", length = 2)
	private String jcts;

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
	 * @return the syno
	 */
	public String getSyno() {
		return syno;
	}

	/**
	 * @param syno
	 *            the syno to set
	 */
	public void setSyno(String syno) {
		this.syno = syno;
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
	 * @return the hmax
	 */
	public Double getHmax() {
		return hmax;
	}

	/**
	 * @param hmax
	 *            the hmax to set
	 */
	public void setHmax(Double hmax) {
		this.hmax = hmax;
	}

	/**
	 * @return the hmin
	 */
	public Double getHmin() {
		return hmin;
	}

	/**
	 * @param hmin
	 *            the hmin to set
	 */
	public void setHmin(Double hmin) {
		this.hmin = hmin;
	}

	/**
	 * @return the kmax
	 */
	public Double getKmax() {
		return kmax;
	}

	/**
	 * @param kmax
	 *            the kmax to set
	 */
	public void setKmax(Double kmax) {
		this.kmax = kmax;
	}

	/**
	 * @return the kmin
	 */
	public Double getKmin() {
		return kmin;
	}

	/**
	 * @param kmin
	 *            the kmin to set
	 */
	public void setKmin(Double kmin) {
		this.kmin = kmin;
	}

	/**
	 * @return the cmax
	 */
	public Double getCmax() {
		return cmax;
	}

	/**
	 * @param cmax
	 *            the cmax to set
	 */
	public void setCmax(Double cmax) {
		this.cmax = cmax;
	}

	/**
	 * @return the cmin
	 */
	public Double getCmin() {
		return cmin;
	}

	/**
	 * @param cmin
	 *            the cmin to set
	 */
	public void setCmin(Double cmin) {
		this.cmin = cmin;
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
	 * @return the jian
	 */
	public String getJian() {
		return jian;
	}

	/**
	 * @param jian
	 *            the jian to set
	 */
	public void setJian(String jian) {
		this.jian = jian;
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
	 * @return the ltdw
	 */
	public String getLtdw() {
		return ltdw;
	}

	/**
	 * @param ltdw
	 *            the ltdw to set
	 */
	public void setLtdw(String ltdw) {
		this.ltdw = ltdw;
	}

	/**
	 * @return the ltzi
	 */
	public Integer getLtzi() {
		return ltzi;
	}

	/**
	 * @param ltzi
	 *            the ltzi to set
	 */
	public void setLtzi(Integer ltzi) {
		this.ltzi = ltzi;
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
	 * @return the kpn1Flag
	 */
	public String getKpn1Flag() {
		return kpn1Flag;
	}

	/**
	 * kpn1Flag String
	 * 
	 * @param kpn1Flag
	 *            the kpn1Flag to set
	 */
	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
	}

	/**
	 * @return the kpn2Flag
	 */
	public String getKpn2Flag() {
		return kpn2Flag;
	}

	/**
	 * kpn2Flag String
	 * 
	 * @param kpn2Flag
	 *            the kpn2Flag to set
	 */
	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
	}

	/**
	 * @return the kpn3Flag
	 */
	public String getKpn3Flag() {
		return kpn3Flag;
	}

	/**
	 * kpn3Flag String
	 * 
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
	 * kpn3 String
	 * 
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
	 * kpn4Flag String
	 * 
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
	 * kpn4 String
	 * 
	 * @param kpn4
	 *            the kpn4 to set
	 */
	public void setKpn4(String kpn4) {
		this.kpn4 = kpn4;
	}

	/**
	 * @return the mgsn
	 */
	public String getMgsn() {
		return mgsn;
	}

	/**
	 * mgsn String
	 * 
	 * @param mgsn
	 *            the mgsn to set
	 */
	public void setMgsn(String mgsn) {
		this.mgsn = mgsn;
	}

	/**
	 * @return the bz1
	 */
	public String getBz1() {
		return bz1;
	}

	/**
	 * @param bz1
	 *            the bz1 to set
	 */
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	/**
	 * @return the bz2
	 */
	public String getBz2() {
		return bz2;
	}

	/**
	 * @param bz2
	 *            the bz2 to set
	 */
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	/**
	 * @return the bz3
	 */
	public String getBz3() {
		return bz3;
	}

	/**
	 * @param bz3
	 *            the bz3 to set
	 */
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getJcts() {
		return jcts;
	}

	public void setJcts(String jcts) {
		this.jcts = jcts;
	}

}
