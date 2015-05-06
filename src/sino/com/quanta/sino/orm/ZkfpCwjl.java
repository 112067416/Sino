package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 分配错误记录
 * </p>
 * <p>
 * create: 2011-1-15 下午03:31:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ZKFP_CWJL")
public class ZkfpCwjl implements Serializable {

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
	 * 分配操作主键
	 */
	@Column(name = "CZID_", length = 40)
	private String czid;

	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 分配等级
	 */
	@Column(name = "FPDJ_", length = 3)
	private String fpdj;

	/**
	 * 现品尺寸.宽
	 */
	@Column(name = "XPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkn;

	/**
	 * 现品尺寸.厚
	 */
	@Column(name = "XPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xpho;

	/**
	 * 现品尺寸.长
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;

	/**
	 * 锯齿形式
	 */
	@Column(name = "JCXS_", length = 4)
	private String jcxs;

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
	 * 附页KPNO.1标识位
	 */
	@Column(name = "KPN1_FLAG_", length = 1)
	private String kpn1Flag;

	/**
	 * 附页KPNO.1
	 */
	@Column(name = "KPN1_", length = 9)
	private String kpn1;

	/**
	 * 附页KPNO.2标识位
	 */
	@Column(name = "KPN2_FLAG_", length = 1)
	private String kpn2Flag;

	/**
	 * 附页KPNO.2
	 */
	@Column(name = "KPN2_", length = 9)
	private String kpn2;

	/**
	 * 附页KPNO.标3识位
	 */
	@Column(name = "KPN3_FLAG_", length = 1)
	private String kpn3Flag;

	/**
	 * 附页KPNO.3
	 */
	@Column(name = "KPN3_", length = 9)
	private String kpn3;

	/**
	 * 附页KPNO.标4识位
	 */
	@Column(name = "KPN4_FLAG_", length = 1)
	private String kpn4Flag;

	/**
	 * 附页KPNO.4
	 */
	@Column(name = "KPN4_", length = 9)
	private String kpn4;

	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;

	/**
	 * 枚数
	 */
	@Column(name = "ZSHU_")
	private Integer zshu;

	/**
	 * 溶接个数.ETL
	 */
	@Column(name = "RJET_")
	private Integer rjet;

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
	 * 产量
	 */
	@Column(name = "CHAN_", length = 1)
	private String chan;

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
	 * 实绩附着量.反面
	 */
	@Column(name = "SCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scfm;

	/**
	 * 实绩附着量.正面
	 */
	@Column(name = "SCZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sczm;

	/**
	 * 硬度
	 */
	@Column(name = "YING_")
	private Integer ying;

	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the czid
	 */
	public String getCzid() {
		return czid;
	}

	/**
	 * @param czid
	 *            the czid to set
	 */
	public void setCzid(String czid) {
		this.czid = czid;
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
	 * @return the zzsd
	 */
	public String getZzsd() {
		return zzsd;
	}

	/**
	 * @param zzsd
	 *            the zzsd to set
	 */
	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

}
