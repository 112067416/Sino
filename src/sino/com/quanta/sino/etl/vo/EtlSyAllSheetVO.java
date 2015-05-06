package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * etl锡原总统计填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:44:35
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSyAllSheetVO {

	public static class Row {

		/**
		 * 装入重量
		 */
		private Double zrzlAll;

		/**
		 * 锡原单位(g/kg)总目标
		 */
		private Double mbfu;
		/**
		 * 锡原单位(g/kg)总实绩
		 */
		private Double sjfu;
		/**
		 * 锡原单位(g/kg)总较差
		 */
		private Double jcha;

		public Double getZrzlAll() {
			return zrzlAll;
		}

		public void setZrzlAll(Double zrzlAll) {
			this.zrzlAll = zrzlAll;
		}

		public Double getMbfu() {
			return mbfu;
		}

		public void setMbfu(Double mbfu) {
			this.mbfu = mbfu;
		}

		public Double getSjfu() {
			return sjfu;
		}

		public void setSjfu(Double sjfu) {
			this.sjfu = sjfu;
		}

		public Double getJcha() {
			return jcha;
		}

		public void setJcha(Double jcha) {
			this.jcha = jcha;
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
			this.rows = new ArrayList<EtlSyAllSheetVO.Row>();
		}
		this.rows.add(row);
	}

}
