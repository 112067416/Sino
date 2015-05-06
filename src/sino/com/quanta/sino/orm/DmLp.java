package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 垫木日志
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_DMLP")
public class DmLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

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
	 * 录入员
	 */
	@Column(name = "DANN_", length = 40)
	private String dann;
	/**
	 * 创建时间
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间
	 */
	@Column(name = "UPDA_")
	private Date upda;
	/**
	 * 垫木方向
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
	/**
	 * 移出库位
	 */
	@Column(name = "OUKW_", length = 3)
	private String oukw;
	/**
	 * 移出个数
	 */
	@Column(name = "OUGS_")
	private Integer ougs;
	/**
	 * 移进库位
	 */
	@Column(name = "INKW_", length = 3)
	private String inkw;
	/**
	 * 移进个数
	 */
	@Column(name = "INGS_")
	private Integer ings;
	/**
	 * 操作类型
	 */
	@Column(name = "CLQ_", length = 1)
	private String clq;
	/**
	 * 班
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;
	/**
	 * 组
	 */
	@Column(name = "ZU_", length = 1)
	private String zu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDann() {
		return dann;
	}

	public void setDann(String dann) {
		this.dann = dann;
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

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getClq() {
		return clq;
	}

	public void setClq(String clq) {
		this.clq = clq;
	}

	public String getOukw() {
		return oukw;
	}

	public void setOukw(String oukw) {
		this.oukw = oukw;
	}

	public String getInkw() {
		return inkw;
	}

	public void setInkw(String inkw) {
		this.inkw = inkw;
	}

	public Integer getOugs() {
		return ougs;
	}

	public void setOugs(Integer ougs) {
		this.ougs = ougs;
	}

	public Integer getIngs() {
		return ings;
	}

	public void setIngs(Integer ings) {
		this.ings = ings;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

}
