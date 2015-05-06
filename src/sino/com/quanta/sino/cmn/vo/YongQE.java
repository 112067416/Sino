package com.quanta.sino.cmn.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YongMp;

/**
 * <p>
 * 制造试样查询配置
 * </p>
 * <p>
 * create: 2011-2-11 下午02:54:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Q
public class YongQE extends QEntity<YongMp> {

	@QF
	private String user;

	@QF(operator = EO.LIKE)
	private String name;

	@QF(operator = EO.LIKE)
	private String abbr;

	@QF(operator = EO.LIKE)
	private String rsv1;

	@QF(operator = EO.LIKE)
	private String addr;

	@QF(operator = EO.LIKE)
	private String guom;

	@QF
	private String ssno;

	@QF
	private String nwai;

	@QF
	private String valid;

	@QF
	private String region;

	@QF(alias = "ddnm", operator = EO.LIKE)
	private String ddnm;

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
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the guom
	 */
	public String getGuom() {
		return guom;
	}

	/**
	 * @param guom
	 *            the guom to set
	 */
	public void setGuom(String guom) {
		this.guom = guom;
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

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

}
