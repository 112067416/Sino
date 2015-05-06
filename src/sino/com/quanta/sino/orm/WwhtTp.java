package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.coco.core.util.NumberUtils;

/**
 * 供应商合同表
 * 
 * @author 张红国 2010-12-9
 */
@Entity
@Table(name = "SINO_WWHTTP")
@IdClass(WwhtTpPk.class)
public class WwhtTp implements Serializable {

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
	 * 合同No
	 */
	@Id
	@Column(name = "HTNO_", length = 8)
	private String htno;

	/**
	 * 行号
	 */
	@Id
	@Column(name = "LINE_", length = 3)
	private String line;
	/**
	 * 签约日
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "QYRI_")
	private Date qyri;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 制造商名称
	 */
	@Column(name = "ZZSM_", length = 16)
	private String zzsm;
	/**
	 * 合同日
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "HTDT_")
	private Date htdt;
	/**
	 * 商社代码
	 */
	@Column(name = "SSNO_", length = 3)
	private String ssno;
	/**
	 * 商社名称
	 */
	@Column(name = "SSNM_", length = 512)
	private String ssnm;
	/**
	 * 制造商规格略称
	 */
	@Column(name = "ZZGG_", length = 40)
	private String zzgg;
	/**
	 * 中日达规格略称
	 */
	@Column(name = "ZRGG_", length = 30)
	private String zrgg;
	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 32)
	private String abbr;
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
	 * 合同重量(吨)
	 */
	@Column(name = "HTZL_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double htzl;
	/**
	 * 分配等级
	 */
	@Column(name = "FPDJ_", length = 3)
	private String fpdj;
	/**
	 * 表面精加工
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 内径
	 */
	@Column(name = "NEIJ_", length = 3)
	private String neij;
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
	// /**
	// * 尺寸允许范围.厚mm上限.符号
	// */
	// @Column(name = "MSFU_", length = 1)
	// private String msfu;
	/**
	 * 尺寸允许范围.厚mm上限.值
	 */
	@Column(name = "MSZI_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double mszi;
	// /**
	// * 尺寸允许范围.厚mm下限.符号
	// */
	// @Column(name = "MXFU_", length = 1)
	// private String mxfu;
	/**
	 * 尺寸允许范围.厚mm下限.值
	 */
	@Column(name = "MXZI_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double mxzi;
	// /**
	// * 尺寸允许范围.宽mm上限.符号
	// */
	// @Column(name = "KSFU_", length = 1)
	// private String ksfu;
	/**
	 * 尺寸允许范围.宽mm上限.值
	 */
	@Column(name = "KSZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double kszi;
	// /**
	// * 尺寸允许范围.宽mm下限.符号
	// */
	// @Column(name = "KXFU_", length = 1)
	// private String kxfu;
	/**
	 * 尺寸允许范围.宽mm下限.值
	 */
	@Column(name = "KXZI_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double kxzi;
	/**
	 * 到货数量(T)
	 */
	@Column(name = "DHSL_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double dhsl;
	/**
	 * 签约完了标志
	 */
	@Column(name = "QYWL_", length = 1)
	private String qywl;

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
	 * @return the htno
	 */
	public String getHtno() {
		return htno;
	}

	/**
	 * @param htno
	 *            the htno to set
	 */
	public void setHtno(String htno) {
		this.htno = htno;
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
	 * @return the qyri
	 */
	public Date getQyri() {
		return qyri;
	}

	/**
	 * @param qyri
	 *            the qyri to set
	 */
	public void setQyri(Date qyri) {
		this.qyri = qyri;
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

	/**
	 * @return the zzsm
	 */
	public String getZzsm() {
		return zzsm;
	}

	/**
	 * @param zzsm
	 *            the zzsm to set
	 */
	public void setZzsm(String zzsm) {
		this.zzsm = zzsm;
	}

	/**
	 * @return the htdt
	 */
	public Date getHtdt() {
		return htdt;
	}

	/**
	 * @param htdt
	 *            the htdt to set
	 */
	public void setHtdt(Date htdt) {
		this.htdt = htdt;
	}

	/**
	 * @return the ssno
	 */
	public String getSsno() {
		return ssno;
	}

	/**
	 * @param ssno
	 *            the ssno to set
	 */
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * @return the ssnm
	 */
	public String getSsnm() {
		return ssnm;
	}

	/**
	 * @param ssnm
	 *            the ssnm to set
	 */
	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	/**
	 * @return the zzgg
	 */
	public String getZzgg() {
		return zzgg;
	}

	/**
	 * @param zzgg
	 *            the zzgg to set
	 */
	public void setZzgg(String zzgg) {
		this.zzgg = zzgg;
	}

	/**
	 * @return the zrgg
	 */
	public String getZrgg() {
		return zrgg;
	}

	/**
	 * @param zrgg
	 *            the zrgg to set
	 */
	public void setZrgg(String zrgg) {
		this.zrgg = zrgg;
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
		if (houu == null) {
			this.houu = null;
			return;
		}
		this.houu = NumberUtils.format(houu, 3);
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
		if (kuan == null) {
			this.kuan = null;
			return;
		}
		this.kuan = NumberUtils.format(kuan, 3);
	}

	/**
	 * @return the htzl
	 */
	public Double getHtzl() {
		return htzl;
	}

	/**
	 * @param htzl
	 *            the htzl to set
	 */
	public void setHtzl(Double htzl) {
		if (htzl == null) {
			this.htzl = null;
			return;
		}
		this.htzl = NumberUtils.format(htzl, 3);
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
	 * @return the neij
	 */
	public String getNeij() {
		return neij;
	}

	/**
	 * @param neij
	 *            the neij to set
	 */
	public void setNeij(String neij) {
		this.neij = neij;
	}

	/**
	 * @return the thqf
	 */
	public String getThqf() {
		return thqf;
	}

	/**
	 * @param thqf
	 *            the thqf to set
	 */
	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	/**
	 * @return the htdj
	 */
	public Double getHtdj() {
		return htdj;
	}

	/**
	 * @param htdj
	 *            the htdj to set
	 */
	public void setHtdj(Double htdj) {
		this.htdj = htdj;
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
	 * @return the dhsl
	 */
	public Double getDhsl() {
		return dhsl;
	}

	/**
	 * @param dhsl
	 *            the dhsl to set
	 */
	public void setDhsl(Double dhsl) {
		this.dhsl = dhsl;
	}

	/**
	 * @return the qywl
	 */
	public String getQywl() {
		return qywl;
	}

	/**
	 * @param qywl
	 *            the qywl to set
	 */
	public void setQywl(String qywl) {
		this.qywl = qywl;
	}

}
