package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 实绩录入查询指示书实体
 * </p>
 * <p>
 * create: 2011-2-14 下午04:25:09
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class ZsViewVO {
	/**
	 * 生产备注
	 */
	private String bz;
	/**
	 * 附页KPNO
	 */
	private List<String> kpns;
	/**
	 * 业连
	 */
	private List<String> ylns;

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public List<String> getKpns() {
		if (kpns == null) {
			kpns = new ArrayList<String>();
		}
		return kpns;
	}

	public void setKpns(List<String> kpns) {
		this.kpns = kpns;
	}

	public List<String> getYlns() {
		if (ylns == null) {
			ylns = new ArrayList<String>();
		}
		return ylns;
	}

	public void setYlns(List<String> ylns) {
		this.ylns = ylns;
	}

}
