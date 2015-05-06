package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * etl锡原表面统计填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:44:35
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSybmSheetVO {

	public static class Row {

		/**
		 * 装入重量
		 */
		private Double zrzlAll;
		/**
		 * 付着量(g/m2)目标
		 */
		private Double fuzm;
		/**
		 * 付着量(g/m2)实绩
		 */
		private Double sjsczm;
		/**
		 * 付着量(g/m2)较差
		 */
		private Double jcha1;
		/**
		 * 锡原单位(g/kg)目标
		 */
		private Double mbfuzm;
		/**
		 * 锡原单位(g/kg)实绩
		 */
		private Double sjfuzm;
		/**
		 * 锡原单位(g/kg)较差
		 */
		private Double jcha2;
		/**
		 * 实际锡 用量比率
		 */
		private Double sjyl;
		/**
		 * 目标锡 用量比率
		 */
		private Double mbyl;

		public Double getZrzlAll() {
			return zrzlAll;
		}

		public void setZrzlAll(Double zrzlAll) {
			this.zrzlAll = zrzlAll;
		}

		public Double getFuzm() {
			return fuzm;
		}

		public void setFuzm(Double fuzm) {
			this.fuzm = fuzm;
		}

		public Double getSjsczm() {
			return sjsczm;
		}

		public void setSjsczm(Double sjsczm) {
			this.sjsczm = sjsczm;
		}

		public Double getJcha1() {
			return jcha1;
		}

		public void setJcha1(Double jcha1) {
			this.jcha1 = jcha1;
		}

		public Double getMbfuzm() {
			return mbfuzm;
		}

		public void setMbfuzm(Double mbfuzm) {
			this.mbfuzm = mbfuzm;
		}

		public Double getSjfuzm() {
			return sjfuzm;
		}

		public void setSjfuzm(Double sjfuzm) {
			this.sjfuzm = sjfuzm;
		}

		public Double getJcha2() {
			return jcha2;
		}

		public void setJcha2(Double jcha2) {
			this.jcha2 = jcha2;
		}

		public Double getSjyl() {
			return sjyl;
		}

		public void setSjyl(Double sjyl) {
			this.sjyl = sjyl;
		}

		public Double getMbyl() {
			return mbyl;
		}

		public void setMbyl(Double mbyl) {
			this.mbyl = mbyl;
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
			this.rows = new ArrayList<EtlSybmSheetVO.Row>();
		}
		this.rows.add(row);
	}

}
