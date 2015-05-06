package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * etl锡原里面统计填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:44:35
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSylmSheetVO {

	public static class Row {

		/**
		 * 装入重量
		 */
		private Double zrzlAll;
		/**
		 * 付着量(g/m2)目标
		 */
		private Double fufm;
		/**
		 * 付着量(g/m2)实绩
		 */
		private Double sjscfm;
		/**
		 * 付着量(g/m2)较差
		 */
		private Double jcha1;
		/**
		 * 锡原单位(g/kg)目标
		 */
		private Double mbfufm;
		/**
		 * 锡原单位(g/kg)实绩
		 */
		private Double sjfufm;
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

		public Double getJcha1() {
			return jcha1;
		}

		public void setJcha1(Double jcha1) {
			this.jcha1 = jcha1;
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

		public Double getFufm() {
			return fufm;
		}

		public void setFufm(Double fufm) {
			this.fufm = fufm;
		}

		public Double getSjscfm() {
			return sjscfm;
		}

		public void setSjscfm(Double sjscfm) {
			this.sjscfm = sjscfm;
		}

		public Double getMbfufm() {
			return mbfufm;
		}

		public void setMbfufm(Double mbfufm) {
			this.mbfufm = mbfufm;
		}

		public Double getSjfufm() {
			return sjfufm;
		}

		public void setSjfufm(Double sjfufm) {
			this.sjfufm = sjfufm;
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
			this.rows = new ArrayList<EtlSylmSheetVO.Row>();
		}
		this.rows.add(row);
	}

}
