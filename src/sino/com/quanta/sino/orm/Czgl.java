package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

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
 * 客户与发票冲账记录关联表
 * </p>
 * <p>
 * create: 2011-1-30 上午09:01:53
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_CZGL")
public class Czgl implements Serializable {

	/**
	 * 
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
	 * 所属付款发票主表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FKFP_", referencedColumnName = "ID_", nullable = true)
	private Fkfp fkfp;

	/**
	 * 所属客户付款主表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "KHFK_", referencedColumnName = "ID_", nullable = true)
	private Khfk khfk;

	/**
	 * 收款日期（每次冲账的收款日期）
	 */
	@Column(name = "SKRI_")
	private Date skri;

	/**
	 * 收款金额（每次冲账的收款金额即付款金额）
	 */
	@Column(name = "FKJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fkje;

	/**
	 * 未收余款（每次冲账的未收余款）
	 */
	@Column(name = "WSYK_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double wsyk;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Fkfp getFkfp() {
		return fkfp;
	}

	public void setFkfp(Fkfp fkfp) {
		this.fkfp = fkfp;
	}

	public Khfk getKhfk() {
		return khfk;
	}

	public void setKhfk(Khfk khfk) {
		this.khfk = khfk;
	}

	public Date getSkri() {
		return skri;
	}

	public void setSkri(Date skri) {
		this.skri = skri;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Double getWsyk() {
		return wsyk;
	}

	public void setWsyk(Double wsyk) {
		this.wsyk = wsyk;
	}

}
