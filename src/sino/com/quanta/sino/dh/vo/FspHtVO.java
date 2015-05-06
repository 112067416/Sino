package com.quanta.sino.dh.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 发生品VO
 * </p>
 * <p>
 * create: 2011-7-10 下午08:54:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FspHtVO {

	/**
	 * 订货合同
	 */
	private String dhno;

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 商社代码
	 */
	private String ssno;

	/**
	 * 商社名称
	 */
	private String ssnm;

	/**
	 * 送货地址
	 */
	private String addr;

	/**
	 * 交货期
	 */
	private Date jhqi;

	/**
	 * 捆包形式
	 */
	private String kbao;

	/**
	 * 发生品特性VO
	 */
	private List<FspTxVO> items;

	/**
	 * 
	 */
	private String ids;

	/**
	 * 现品状况
	 */
	private String xpzk;

	/**
	 * 垫木重量
	 */
	private Integer dmzl;

	/**
	 * 包装材料重量
	 */
	private Integer bzcl;

	/**
	 * 产量代码
	 */
	private String chan;

	/**
	 * 合同单价
	 */
	private Double htdj;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FspTxVO> getItems() {
		return items;
	}

	public void setItems(List<FspTxVO> items) {
		this.items = items;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public Date getJhqi() {
		return jhqi;
	}

	public void setJhqi(Date jhqi) {
		this.jhqi = jhqi;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public String getSsnm() {
		return ssnm;
	}

	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		if (ids == null || ids.isEmpty()) {
			return;
		}
		List<FspTxVO> items = new ArrayList<FspTxVO>();
		FspTxVO txVO = null;
		String[] arrs = null;
		String houu, kuan, cang;
		for (String id : ids.split(",")) {
			arrs = id.split("#");
			if (arrs.length != 7) continue;
			txVO = new FspTxVO();
			txVO.setYuny(arrs[0]);
			txVO.setGgno(arrs[1]);
			houu = arrs[2];
			txVO.setXpho(Double.valueOf(houu));
			kuan = arrs[3];
			txVO.setXpkn(Double.valueOf(kuan));
			cang = arrs[4];
			txVO.setXpcn("COIL".equals(cang) ? null : Double.valueOf(cang));
			txVO.setFace(arrs[5]);
			txVO.setXpzk(arrs[6]);
			items.add(txVO);
		}
		this.items = items;
		this.ids = ids;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public Integer getDmzl() {
		return dmzl;
	}

	public void setDmzl(Integer dmzl) {
		this.dmzl = dmzl;
	}

	public Integer getBzcl() {
		return bzcl;
	}

	public void setBzcl(Integer bzcl) {
		this.bzcl = bzcl;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public Double getHtdj() {
		return htdj;
	}

	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

}
