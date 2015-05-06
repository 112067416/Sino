package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 装箱指示DB1
 * </p>
 * <p>
 * create: 2011-2-23 上午11:27:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ZNG1TP")
public class Zng1Tp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 对应的装箱指示书
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ZXZSTP_", referencedColumnName = "ID_", nullable = false)
	private ZxzsTp zxzsTp;

	/**
	 * 出货重量
	 */
	@Column(name = "CHZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chzl;

	/**
	 * 出货毛重量
	 */
	@Column(name = "CHMA_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chma;

	/**
	 * 出货制品数
	 */
	@Column(name = "CHSU_")
	private Integer chsu;

	/**
	 * 订货No.
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;
	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;
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
	 * 尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;
	/**
	 * 尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;
	/**
	 * 尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 商品编号
	 */
	@Column(name = "SPBH_", length = 1)
	private String spbh;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 1)
	private String deng;

	/**
	 * 品种
	 */
	@Column(name = "PZ_", length = 1)
	private String pz;

	/**
	 * 表面
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 硬度下限值
	 */
	@Column(name = "YMIN_", length = 2)
	private String ymin;

	/**
	 * 硬度上限值
	 */
	@Column(name = "YMAX_", length = 2)
	private String ymax;

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
	 * 客户订货单号
	 */
	@Column(name = "KHNO_", length = 10)
	private String khno;

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
	 * @return the zxzsTp
	 */
	public ZxzsTp getZxzsTp() {
		return zxzsTp;
	}

	/**
	 * @param zxzsTp
	 *            the zxzsTp to set
	 */
	public void setZxzsTp(ZxzsTp zxzsTp) {
		this.zxzsTp = zxzsTp;
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
	 * @return the ggnm
	 */
	public String getGgnm() {
		return ggnm;
	}

	/**
	 * @param ggnm
	 *            the ggnm to set
	 */
	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
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
	 * @return the chzl
	 */
	public Double getChzl() {
		return chzl;
	}

	/**
	 * @param chzl
	 *            the chzl to set
	 */
	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	/**
	 * @return the chsu
	 */
	public Integer getChsu() {
		return chsu;
	}

	/**
	 * @param chsu
	 *            the chsu to set
	 */
	public void setChsu(Integer chsu) {
		this.chsu = chsu;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getPz() {
		return pz;
	}

	public void setPz(String pz) {
		this.pz = pz;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	public Double getChma() {
		return chma;
	}

	public void setChma(Double chma) {
		this.chma = chma;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getCdnm() {
		return cdnm;
	}

	public void setCdnm(String cdnm) {
		this.cdnm = cdnm;
	}

	public String getKhno() {
		return khno;
	}

	public void setKhno(String khno) {
		this.khno = khno;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

}
