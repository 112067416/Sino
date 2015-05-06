package com.quanta.sino.ss.vo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.coco.core.util.DateUtils;

/**
 * <p>
 * 步留统计和明细查询视图对象
 * </p>
 * <p>
 * create: 2011-1-30 上午09:27:06
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsBlVO {

	/**
	 * <p>
	 * 步留统计明细缺陷项
	 * </p>
	 * <p>
	 * create: 2011-1-30 下午04:17:48
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	public static class Quex {

		private String qxdm;

		private Integer zshu;

		public Quex(String qxdm, Integer zshu) {
			this.qxdm = qxdm;
			this.zshu = zshu;
		}

	}

	/**
	 * <p>
	 * 步留统计行项
	 * </p>
	 * <p>
	 * create: 2011-1-30 下午03:28:44
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	public static class CountItem {

		/**
		 * 日期
		 */
		private String date;

		/**
		 * 日期表示文本
		 */
		private String dateLabel;

		/**
		 * 装入重量
		 */
		private double zrzl = 0.0;

		/**
		 * 缺陷张数列表
		 */
		private Map<String, Integer> qxzses = new HashMap<String, Integer>();

		private CountItem(String date) {
			this.date = date;
		}

		public String getDate() {
			return date;
		}

		public Map<String, Integer> getQxzses() {
			return qxzses;
		}

		public String getDateLabel() {
			return dateLabel;
		}

		public double getZrzl() {
			return zrzl;
		}

	}

	/**
	 * <p>
	 * 步留明细行项
	 * </p>
	 * <p>
	 * create: 2011-1-30 下午03:28:44
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	public static class DetailItem {

		/**
		 * 卷板No.
		 */
		private String jbno;

		/**
		 * 时间表示文本
		 */
		private String timeLabel;

		/**
		 * 装入张数
		 */
		private Integer zrzs = 0;

		/**
		 * 单重
		 */
		private Double danz = 0.0;

		/**
		 * 缺陷张数列表
		 */
		private Map<String, Integer> qxzses = new HashMap<String, Integer>();

		private DetailItem(String jbno) {
			this.jbno = jbno;
		}

		public Map<String, Integer> getQxzses() {
			return qxzses;
		}

		public String getJbno() {
			return jbno;
		}

		public String getTimeLabel() {
			return timeLabel;
		}

		public Integer getZrzs() {
			return zrzs;
		}

		public Double getDanz() {
			return danz;
		}

		public Double getZrzl() {
			return zrzs * danz;
		}

	}

	/**
	 * 是否为统计，true表示为统计
	 */
	private boolean count;

	/**
	 * 创建时间起
	 */
	private Date pxsdBegin;

	/**
	 * 创建时间止
	 */
	private Date pxsdEnd;

	/**
	 * 步留统计行项列表
	 */
	private Map<String, CountItem> counts = new HashMap<String, CountItem>();

	/**
	 * 步留明细行项列表
	 */
	private Map<String, DetailItem> details = new HashMap<String, DetailItem>();

	public Date getPxsdBegin() {
		return pxsdBegin;
	}

	public void setPxsdBegin(Date pxsdBegin) {
		this.pxsdBegin = pxsdBegin;
	}

	public Date getPxsdEnd() {
		return pxsdEnd;
	}

	public void setPxsdEnd(Date pxsdEnd) {
		this.pxsdEnd = pxsdEnd;
	}

	/**
	 * <p>
	 * 获取统计数据
	 * </p>
	 * 
	 * @return CountItem[]
	 */
	public CountItem[] getCounts() {
		if (counts.isEmpty()) {
			return new CountItem[0];
		}
		CountItem[] datas = new CountItem[counts.size()];
		counts.values().toArray(datas);
		Arrays.sort(datas, new Comparator<CountItem>() {

			@Override
			public int compare(CountItem o1, CountItem o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		return datas;
	}

	/**
	 * <p>
	 * 获取明细数据
	 * </p>
	 * 
	 * @return DetailItem[]
	 */
	public DetailItem[] getDetails() {
		if (details.isEmpty()) {
			return new DetailItem[0];
		}
		DetailItem[] datas = new DetailItem[details.size()];
		details.values().toArray(datas);
		Arrays.sort(datas, new Comparator<DetailItem>() {

			@Override
			public int compare(DetailItem o1, DetailItem o2) {
				return o1.getTimeLabel().compareTo(o2.getTimeLabel());
			}
		});
		return datas;
	}

	/**
	 * <p>
	 * 填入统计数据
	 * </p>
	 * 
	 * @param date
	 *            消灭时间
	 * @param danz
	 *            单重
	 * @param qxes
	 *            缺陷列表
	 */
	public void putCount(Date date, Double danz, Quex... qxes) {
		if (!count) return;
		String dateStr = DateUtils.format(date, "yyyy-MM-dd");
		CountItem item = counts.get(dateStr);
		if (item == null) {
			item = new CountItem(dateStr);
			item.dateLabel = DateUtils.format(date, "dd/MM");
			counts.put(dateStr, item);
		}
		double zl = 0.0;
		Integer qxzs = 0;
		if (qxes != null && danz != null) {
			for (Quex qx : qxes) {
				// 按缺陷代码张数累加
				qxzs = item.qxzses.get(qx.qxdm);
				if (qxzs == null) {
					if (qx.zshu != null) {
						qxzs = qx.zshu;
					}

				}
				else {
					if (qx.zshu != null) {
						qxzs += qx.zshu;
					}

				}
				item.qxzses.put(qx.qxdm, qxzs);
				// 求重量
				if (qx.zshu != null) {
					zl += qx.zshu * danz;
				}

			}
		}
		// 按日期重量累加
		item.zrzl += zl;
	}

	/**
	 * <p>
	 * 填入明细数据
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No.
	 * @param date
	 *            消灭时间
	 * @param danz
	 *            单重
	 * @param qxes
	 *            缺陷列表
	 */
	public void putDetail(String jbno, Date date, Double danz, Quex... qxes) {
		if (count) return;
		DetailItem item = details.get(jbno);
		if (item == null) {
			item = new DetailItem(jbno);
			item.timeLabel = DateUtils.format(date, "hh/mm");
			details.put(jbno, item);
		}
		item.danz = danz;
		if (qxes != null && danz != null) {
			for (Quex qx : qxes) {
				item.qxzses.put(qx.qxdm, qx.zshu);
				// 张数累计
				if (qx.zshu != null) {
					item.zrzs += qx.zshu;
				}

			}
		}
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}
}
