package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * etl锡原表面各班统计数据
 * </p>
 * <p>
 * create: 2011-3-3 上午11:46:27
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSyBanSheetVO {
	public static class Row {
		/**
		 * 班
		 */
		private String ban;
		/**
		 * 班名
		 */
		private String banname;
		/**
		 * 表面锡原统计
		 */
		private EtlSybmSheetVO bms;
		/**
		 * 里面锡原统计
		 */
		private EtlSylmSheetVO lms;
		/**
		 * 总锡原统计
		 */
		private EtlSyAllSheetVO alls;

		public String getBan() {
			return ban;
		}

		public void setBan(String ban) {
			this.ban = ban;
		}

		public EtlSybmSheetVO getBms() {
			if (bms == null) {
				this.bms = new EtlSybmSheetVO();
			}
			return bms;
		}

		public void setBms(EtlSybmSheetVO bms) {
			this.bms = bms;
		}

		public EtlSylmSheetVO getLms() {
			if (lms == null) {
				this.lms = new EtlSylmSheetVO();
			}
			return lms;
		}

		public void setLms(EtlSylmSheetVO lms) {
			this.lms = lms;
		}

		public String getBanname() {
			return banname;
		}

		public void setBanname(String banname) {
			this.banname = banname;
		}

		public EtlSyAllSheetVO getAlls() {
			if (alls == null) {
				this.alls = new EtlSyAllSheetVO();
			}
			return alls;
		}

		public void setAlls(EtlSyAllSheetVO alls) {
			this.alls = alls;
		}

	}

	private List<Row> rows;

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	/**
	 * <p>
	 * 增加数据行
	 * </p>
	 * 
	 * @param row
	 */
	public void add(Row row) {
		if (this.rows == null) {
			this.rows = new ArrayList<EtlSyBanSheetVO.Row>();
		}
		this.rows.add(row);
	}

}
