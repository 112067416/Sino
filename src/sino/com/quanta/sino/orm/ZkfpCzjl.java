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
 * <p>
 * 分配操作记录
 * </p>
 * <p>
 * create: 2011-1-15 下午03:31:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ZKFP_CZJL")
public class ZkfpCzjl implements Serializable {

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
	 * 卷板No/PileNo
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;

	/**
	 * 分配No
	 */
	@Column(name = "FPNO_", length = 8)
	private String fpno;

	/**
	 * 现品状况
	 */
	@Column(name = "XPZK_", length = 1)
	private String xpzk;

	/**
	 * 是否匹配
	 */
	@Column(name = "SFPP_", length = 1)
	private String sfpp;

	/**
	 * 操作类型
	 */
	@Column(name = "CZLX_", length = 1)
	private String czlx;

	/**
	 * 操作时间
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

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
	 * @return the sfpp
	 */
	public String getSfpp() {
		return sfpp;
	}

	/**
	 * @param sfpp
	 *            the sfpp to set
	 */
	public void setSfpp(String sfpp) {
		this.sfpp = sfpp;
	}

	/**
	 * @return the czlx
	 */
	public String getCzlx() {
		return czlx;
	}

	/**
	 * @param czlx
	 *            the czlx to set
	 */
	public void setCzlx(String czlx) {
		this.czlx = czlx;
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
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the ddnm
	 */
	public String getDdnm() {
		return ddnm;
	}

	/**
	 * @param ddnm
	 *            the ddnm to set
	 */
	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

}
