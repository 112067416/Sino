package com.quanta.sino.yccl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 * <p>
 * create: 2011-3-27 下午02:40:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YldlVO {

	/**
	 * 
	 */
	private List<String> ids;

	/**
	 * 
	 */
	private List<Yl> items;

	/**
	 * <p>
	 * </p>
	 * <p>
	 * create: 2011-3-27 下午02:44:47
	 * </p>
	 * 
	 * @author 张良[jimsonhappy@126.com]
	 * @version 1.0
	 */
	public static class Yl {

		/**
		 * 
		 */
		private String ylFlag;

		/**
		 * 
		 */
		private String ylno;

		public String getYlFlag() {
			return ylFlag;
		}

		public void setYlFlag(String ylFlag) {
			this.ylFlag = ylFlag;
		}

		public String getYlno() {
			return ylno;
		}

		public void setYlno(String ylno) {
			this.ylno = ylno;
		}

	}

	public List<Yl> getItems() {
		return items;
	}

	public void setItems(List<Yl> items) {
		this.items = items;
	}

	public void setJbnos(String jbnos) {
		if (jbnos == null || jbnos.isEmpty()) {
			this.ids = null;
			return;
		}
		String[] arrs = jbnos.split("&");
		this.ids = new ArrayList<String>();
		for (String arr : arrs) {
			this.ids.add(arr.split("=")[1]);
		}
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
