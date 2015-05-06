package com.quanta.sino.etl.vo;

/**
 * <p>
 * etl锡原统计报表填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:41:56
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSydjDataVO {
	private EtlSybmSheetVO bm;
	private EtlSylmSheetVO lm;
	private EtlSyBanSheetVO ban;
	private EtlSyAllSheetVO all;

	public EtlSybmSheetVO getBm() {
		return bm;
	}

	public void setBm(EtlSybmSheetVO bm) {
		this.bm = bm;
	}

	public EtlSylmSheetVO getLm() {
		return lm;
	}

	public void setLm(EtlSylmSheetVO lm) {
		this.lm = lm;
	}

	public EtlSyBanSheetVO getBan() {
		return ban;
	}

	public void setBan(EtlSyBanSheetVO ban) {
		this.ban = ban;
	}

	public EtlSyAllSheetVO getAll() {
		return all;
	}

	public void setAll(EtlSyAllSheetVO all) {
		this.all = all;
	}

}
