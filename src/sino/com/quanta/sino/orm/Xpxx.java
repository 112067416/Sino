package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * <p>
 * 现品信息表 记录现品更新前信息
 * </p>
 * <p>
 * create: 2011-2-16 下午03:41:15
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_XPXX")
public class Xpxx implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;
	/**
	 * 原材卷板ID
	 */
	@Column(name = "YCJB_ID_", length = 11)
	private String ycjbid;
	/**
	 * 制品在库DBID
	 */
	@Column(name = "ZPZK_ID_", length = 11)
	private String zpzkid;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 制品号
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;
	/**
	 * 模块类型
	 */
	@Column(name = "MKLX_", length = 2)
	private String mklx;
	/**
	 * 换算尺寸.长
	 */
	@Column(name = "HNGC_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double hngc;
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
	 * 实绩品种等级
	 */
	@Column(name = "SPDJ_", length = 1)
	private String spdj;
	/**
	 * 现品尺寸.长（coil）
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
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
	 * 现品尺寸.长（sheet）
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;
	/**
	 * 现品状况
	 */
	@Column(name = "XPZK_", length = 1)
	private String xpzk;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;
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
	 * 等级
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;
	/**
	 * 产量
	 */
	@Column(name = "CHAN_", length = 1)
	private String chan;
	/**
	 * 垫木重量
	 */
	@Column(name = "DMZL_")
	private Integer dmzl;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 包装材料重量
	 */
	@Column(name = "BZCL_")
	private Integer bzcl;
	/**
	 * PILE区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 出口包装
	 */
	@Column(name = "CKNO_")
	private Integer ckno;
	/**
	 * 钢种类型
	 */
	@Column(name = "GZLX_", length = 4)
	private String gzlx;
	/**
	 * 包装张数
	 */
	@Column(name = "BZZS_")
	private Integer bzzs;
	/**
	 * 再剪边标记
	 */
	@Column(name = "ZJBB_", length = 1)
	private String zjbb;
	/**
	 * 内径代码
	 */
	@Column(name = "NJNO_", length = 1)
	private String njno;
	/**
	 * 表面精加工
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;
	/**
	 * 涂油种类
	 */
	@Column(name = "YTYP_", length = 1)
	private String ytyp;
	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;
	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;
	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;
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
	 * 业连NO
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;
	/**
	 * 差厚镀锡标记
	 */
	@Column(name = "CHDX_", length = 2)
	private String chdx;
	/**
	 * 强制出货标记
	 */
	@Column(name = "QZCH_", length = 1)
	private String qzch;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * 硬度
	 */
	@Column(name = "YING_")
	private Integer ying;
	/**
	 * 锯齿形式
	 */
	@Column(name = "JCXS_", length = 4)
	private String jcxs;
	/**
	 * 标签换帖标记
	 */
	@Column(name = "BQHT_", length = 1)
	private String bqht;
	/**
	 * 作业停止标记
	 */
	@Column(name = "ZTBJ_", length = 2)
	private String ztbj;
	/**
	 * 修改人
	 */
	@Column(name = "XGR_", length = 10)
	private String xgr;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "XGSJ_")
	private Date xgsj;
	/**
	 * 生成人
	 */
	@Column(name = "SCR_", length = 10)
	private String scr;
	/**
	 * 生成时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "SCSJ_")
	private Date scsj;
	/**
	 * 删除标记
	 */
	@Column(name = "SCBJ_")
	private Boolean scbj;
	/**
	 * 删除人
	 */
	@Column(name = "SHCR_", length = 10)
	private String shcr;
	/**
	 * 删除时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "SHSJ_")
	private Date shsj;
	/**
	 * 新制品NO
	 */
	@Column(name = "NJBNO_", length = 11)
	private String njbno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYcjbid() {
		return ycjbid;
	}

	public void setYcjbid(String ycjbid) {
		this.ycjbid = ycjbid;
	}

	public String getZpzkid() {
		return zpzkid;
	}

	public void setZpzkid(String zpzkid) {
		this.zpzkid = zpzkid;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public Double getHngc() {
		return hngc;
	}

	public void setHngc(Double hngc) {
		this.hngc = hngc;
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

	public String getSpdj() {
		return spdj;
	}

	public void setSpdj(String spdj) {
		this.spdj = spdj;
	}

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public Double getXpcn() {
		return xpcn;
	}

	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
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

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public Integer getDmzl() {
		return dmzl;
	}

	public void setDmzl(Integer dmzl) {
		this.dmzl = dmzl;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public Integer getBzcl() {
		return bzcl;
	}

	public void setBzcl(Integer bzcl) {
		this.bzcl = bzcl;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	public String getGzlx() {
		return gzlx;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	public Integer getBzzs() {
		return bzzs;
	}

	public void setBzzs(Integer bzzs) {
		this.bzzs = bzzs;
	}

	public String getZjbb() {
		return zjbb;
	}

	public void setZjbb(String zjbb) {
		this.zjbb = zjbb;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public Double getScfm() {
		return scfm;
	}

	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	public Double getSczm() {
		return sczm;
	}

	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	public String getChdx() {
		return chdx;
	}

	public void setChdx(String chdx) {
		this.chdx = chdx;
	}

	public String getQzch() {
		return qzch;
	}

	public void setQzch(String qzch) {
		this.qzch = qzch;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getJcxs() {
		return jcxs;
	}

	public void setJcxs(String jcxs) {
		this.jcxs = jcxs;
	}

	public String getBqht() {
		return bqht;
	}

	public void setBqht(String bqht) {
		this.bqht = bqht;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getScr() {
		return scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public Date getScsj() {
		return scsj;
	}

	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}

	public String getShcr() {
		return shcr;
	}

	public void setShcr(String shcr) {
		this.shcr = shcr;
	}

	public Date getShsj() {
		return shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getNjbno() {
		return njbno;
	}

	public void setNjbno(String njbno) {
		this.njbno = njbno;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public Boolean getScbj() {
		return scbj;
	}

	public void setScbj(Boolean scbj) {
		this.scbj = scbj;
	}

}
