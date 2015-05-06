package com.quanta.sino.yygl.vo;

/**
 * <p>
 * 用途统计VO
 * </p>
 * <p>
 * create: 2011-7-7 上午10:54:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CondVO {

	/**
	 * 年
	 */
	private Integer year;

	/**
	 * 月
	 */
	private Integer month;

	/**
	 * 出货重量
	 */
	private Double chzl;

	/**
	 * 饮料重量
	 */
	private Double ylzl;

	/**
	 * 杂罐重量
	 */
	private Double zgzl;

	/**
	 * 食品重量
	 */
	private Double spzl;

	/**
	 * 电池重量
	 */
	private Double dczl;

	/**
	 * 喷雾重量
	 */
	private Double pwzl;

	/**
	 * 电子部件重量
	 */
	private Double dzbjzl;

	/**
	 * 皇冠重量
	 */
	private Double hgzl;

	/**
	 * 化工罐重量
	 */
	private Double hggzl;

	/**
	 * 奶粉
	 */
	private Double nfzl;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Double getYlzl() {
		return ylzl;
	}

	public void setYlzl(Double ylzl) {
		this.ylzl = ylzl;
	}

	public Double getZgzl() {
		return zgzl;
	}

	public void setZgzl(Double zgzl) {
		this.zgzl = zgzl;
	}

	public Double getSpzl() {
		return spzl;
	}

	public void setSpzl(Double spzl) {
		this.spzl = spzl;
	}

	public Double getDczl() {
		return dczl;
	}

	public void setDczl(Double dczl) {
		this.dczl = dczl;
	}

	public Double getPwzl() {
		return pwzl;
	}

	public void setPwzl(Double pwzl) {
		this.pwzl = pwzl;
	}

	public Double getHgzl() {
		return hgzl;
	}

	public void setHgzl(Double hgzl) {
		this.hgzl = hgzl;
	}

	public Double getHggzl() {
		return hggzl;
	}

	public void setHggzl(Double hggzl) {
		this.hggzl = hggzl;
	}

	public Double getNfzl() {
		return nfzl;
	}

	public void setNfzl(Double nfzl) {
		this.nfzl = nfzl;
	}

	public Double getDzbjzl() {
		return dzbjzl;
	}

	public void setDzbjzl(Double dzbjzl) {
		this.dzbjzl = dzbjzl;
	}

	public void setZl(Double zl, String name) {
		Cdnm cdnm = Cdnm.get(name);
		zl = zl != null ? zl : 0d;
		chzl = chzl != null ? chzl : 0d;
		chzl += zl;
		if (cdnm == null) return;
		if (Cdnm.yl.name.equals(name)) {
			this.setYlzl(zl);
		}
		else if (Cdnm.zg.name.equals(name)) {
			this.setZgzl(zl);
		}
		else if (Cdnm.sp.name.equals(name)) {
			this.setSpzl(zl);
		}
		else if (Cdnm.dc.name.equals(name)) {
			this.setDczl(zl);
		}
		else if (Cdnm.pw.name.equals(name)) {
			this.setPwzl(zl);
		}
		else if (Cdnm.dzbj.name.equals(name)) {
			this.setDzbjzl(zl);
		}
		else if (Cdnm.hg.name.equals(name)) {
			this.setHgzl(zl);
		}
		else if (Cdnm.hgg.name.equals(name)) {
			this.setHggzl(zl);
		}
		else if (Cdnm.nf.name.equals(name)) {
			this.setNfzl(zl);
		}
	}

	public enum Cdnm {

		/**
		 * 饮料
		 */
		yl("饮料"),

		/**
		 * 杂罐
		 */
		zg("杂罐"),

		/**
		 * 食品
		 */
		sp("食品"),

		/**
		 * 电池
		 */
		dc("电池"),

		/**
		 * 喷雾
		 */
		pw("喷雾"),

		/**
		 * 电子部件
		 */
		dzbj("电子部件"),

		/**
		 * 皇冠
		 */
		hg("皇冠"),

		/**
		 * 化工罐
		 */
		hgg("化工罐"),

		/**
		 * 奶粉
		 */
		nf("奶粉");

		public final String name;

		Cdnm(String name) {
			this.name = name;
		}

		public static Cdnm get(String name) {
			if (name == null || name.isEmpty()) {
				return null;
			}
			for (Cdnm cdnm : values()) {
				if (cdnm.name.equals(name.trim())) {
					return cdnm;
				}
			}
			return null;
		}
	}

}
