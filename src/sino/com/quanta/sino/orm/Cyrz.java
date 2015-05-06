package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coco.core.util.StringUtils;

/**
 * <p>
 * 采样日志
 * </p>
 * <p>
 * create: 2011-1-6 下午05:31:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_CYRZ")
public class Cyrz implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 分析项目数值形式
	 * </p>
	 * 
	 * @return String[]
	 */
	public String[] getFxxms() {
		if (fxxm == null) {
			return null;
		}
		return fxxm.split(",");
	}

	/**
	 * <p>
	 * 分析项目数值形式
	 * </p>
	 */
	public void setFxxms(String[] fxxms) {
		if (fxxms != null && fxxms.length > 0) {
			fxxm = StringUtils.join(fxxms, ",");
		}
		else {
			fxxm = null;
		}
	}

	/**
	 * <p>
	 * 采样位置数值形式
	 * </p>
	 * 
	 * @return String[]
	 */
	public String[] getCywzs() {
		if (cywz == null) {
			return null;
		}
		return cywz.split(",");
	}

	/**
	 * <p>
	 * 采样位置数值形式
	 * </p>
	 */
	public void setCywzs(String[] cywzs) {
		if (cywzs != null && cywzs.length > 0) {
			cywz = StringUtils.join(cywzs, ",");
		}
		else {
			cywz = null;
		}
	}

	/**
	 * 单号
	 */
	@Id
	@Column(name = "ID_", length = 10)
	private String id;

	/**
	 * 指示连号
	 */
	@Column(name = "ZSNO_", length = 10)
	private String zsno;

	/**
	 * 卷板No.
	 */
	@Column(name = "JBNO_", length = 20)
	private String jbno;

	/**
	 * 采样时间
	 */
	@Column(name = "DATE_")
	private Date date;

	/**
	 * 班
	 */
	@Column(name = "BAN_", length = 10)
	private String ban;

	/**
	 * 组
	 */
	@Column(name = "ZU_", length = 10)
	private String zu;

	/**
	 * 镀锡量
	 */
	@Column(name = "DXL_", length = 12)
	private String dxl;

	/**
	 * 涂油量
	 */
	@Column(name = "TYL_", length = 10)
	private String tyl;

	/**
	 * 分析项目
	 */
	@Column(name = "FXXM_", length = 512)
	private String fxxm;

	/**
	 * 采样位置
	 */
	@Column(name = "CYWZ_", length = 10)
	private String cywz;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 512)
	private String bz;

	/**
	 * SL备注
	 */
	@Column(name = "SL_BZ_", length = 512)
	private String slBz;

	/**
	 * SL收单线别
	 */
	@Column(name = "SLXB_", length = 10)
	private String slxb;

	/**
	 * SL收单时间
	 */
	@Column(name = "SLSD_")
	private Date slsd;

	/**
	 * SL收单通知状态
	 */
	@Column(name = "SL_ZT_")
	private boolean slZt;

	/**
	 * SL送样时间
	 */
	@Column(name = "SLSY_")
	private Date slsy;

	/**
	 * 分析室收单时间
	 */
	@Column(name = "FXSD_")
	private Date fxsd;

	/**
	 * 分析室收单通知状态
	 */
	@Column(name = "FXSD_ZT_")
	private boolean fxsdZt;

	/**
	 * 分析室收样时间
	 */
	@Column(name = "FXSY_")
	private Date fxsy;

	/**
	 * 分析室收样通知状态
	 */
	@Column(name = "FXSY_ZT_")
	private boolean fxsyZt;

	/**
	 * 分析时间
	 */
	@Column(name = "FXSJ_")
	private Date fxsj;

	/**
	 * 分析室备注
	 */
	@Column(name = "FX_BZ_", length = 512)
	private String fxBz;

	/**
	 * 删除标志
	 */
	@Column(name = "DELETED_")
	private boolean deleted;

	/**
	 * 完成标志
	 */
	@Column(name = "END_")
	private boolean end;

	/**
	 * 分析状态
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * 发送采样单线别
	 */
	@Column(name = "FSXB_", length = 10)
	private String fsxb;

	/**
	 * 接收采样单线别
	 */
	@Column(name = "JSXB_", length = 10)
	private String jsxb;

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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the ban
	 */
	public String getBan() {
		return ban;
	}

	/**
	 * @param ban
	 *            the ban to set
	 */
	public void setBan(String ban) {
		this.ban = ban;
	}

	/**
	 * @return the zu
	 */
	public String getZu() {
		return zu;
	}

	/**
	 * @param zu
	 *            the zu to set
	 */
	public void setZu(String zu) {
		this.zu = zu;
	}

	/**
	 * @return the dxl
	 */
	public String getDxl() {
		return dxl;
	}

	/**
	 * @param dxl
	 *            the dxl to set
	 */
	public void setDxl(String dxl) {
		this.dxl = dxl;
	}

	/**
	 * @return the tyl
	 */
	public String getTyl() {
		return tyl;
	}

	/**
	 * @param tyl
	 *            the tyl to set
	 */
	public void setTyl(String tyl) {
		this.tyl = tyl;
	}

	/**
	 * @return the fxxm
	 */
	public String getFxxm() {
		return fxxm;
	}

	/**
	 * @param fxxm
	 *            the fxxm to set
	 */
	public void setFxxm(String fxxm) {
		this.fxxm = fxxm;
	}

	/**
	 * @return the cywz
	 */
	public String getCywz() {
		return cywz;
	}

	/**
	 * @param cywz
	 *            the cywz to set
	 */
	public void setCywz(String cywz) {
		this.cywz = cywz;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the slsd
	 */
	public Date getSlsd() {
		return slsd;
	}

	/**
	 * @param slsd
	 *            the slsd to set
	 */
	public void setSlsd(Date slsd) {
		this.slsd = slsd;
	}

	/**
	 * @return the slsy
	 */
	public Date getSlsy() {
		return slsy;
	}

	/**
	 * @param slsy
	 *            the slsy to set
	 */
	public void setSlsy(Date slsy) {
		this.slsy = slsy;
	}

	/**
	 * @return the fxsd
	 */
	public Date getFxsd() {
		return fxsd;
	}

	/**
	 * @param fxsd
	 *            the fxsd to set
	 */
	public void setFxsd(Date fxsd) {
		this.fxsd = fxsd;
	}

	/**
	 * @return the fxsy
	 */
	public Date getFxsy() {
		return fxsy;
	}

	/**
	 * @param fxsy
	 *            the fxsy to set
	 */
	public void setFxsy(Date fxsy) {
		this.fxsy = fxsy;
	}

	/**
	 * @return the fxsj
	 */
	public Date getFxsj() {
		return fxsj;
	}

	/**
	 * @param fxsj
	 *            the fxsj to set
	 */
	public void setFxsj(Date fxsj) {
		this.fxsj = fxsj;
	}

	/**
	 * @return the slxb
	 */
	public String getSlxb() {
		return slxb;
	}

	/**
	 * @param slxb
	 *            the slxb to set
	 */
	public void setSlxb(String slxb) {
		this.slxb = slxb;
	}

	/**
	 * @return the fxsdZt
	 */
	public boolean isFxsdZt() {
		return fxsdZt;
	}

	/**
	 * @param fxsdZt
	 *            the fxsdZt to set
	 */
	public void setFxsdZt(boolean fxsdZt) {
		this.fxsdZt = fxsdZt;
	}

	/**
	 * @return the fxsyZt
	 */
	public boolean isFxsyZt() {
		return fxsyZt;
	}

	/**
	 * @param fxsyZt
	 *            the fxsyZt to set
	 */
	public void setFxsyZt(boolean fxsyZt) {
		this.fxsyZt = fxsyZt;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the fxBz
	 */
	public String getFxBz() {
		return fxBz;
	}

	/**
	 * @param fxBz
	 *            the fxBz to set
	 */
	public void setFxBz(String fxBz) {
		this.fxBz = fxBz;
	}

	/**
	 * @return the slBz
	 */
	public String getSlBz() {
		return slBz;
	}

	/**
	 * @param slBz
	 *            the slBz to set
	 */
	public void setSlBz(String slBz) {
		this.slBz = slBz;
	}

	/**
	 * @return the end
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public boolean isSlZt() {
		return slZt;
	}

	public void setSlZt(boolean slZt) {
		this.slZt = slZt;
	}

	public String getJsxb() {
		return jsxb;
	}

	public void setJsxb(String jsxb) {
		this.jsxb = jsxb;
	}

	public String getFsxb() {
		return fsxb;
	}

	public void setFsxb(String fsxb) {
		this.fsxb = fsxb;
	}
}
