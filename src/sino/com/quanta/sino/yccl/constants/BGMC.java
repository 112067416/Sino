package com.quanta.sino.yccl.constants;

/**
 * <p>
 * 现品修正和现品生成页面提交字段对应的中文名称
 * </p>
 * <p>
 * create: 2011-2-15 下午04:48:07
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public enum BGMC {
	/**
	 * 现品尺寸 厚
	 */
	xpho("xpho", "现品尺寸 厚"),
	/**
	 * 现品尺寸 宽
	 */
	xpkn("xpkn", "现品尺寸 宽"),
	/**
	 * 现品尺寸 长(coil)
	 */
	jbcn("jbcn", "现品尺寸 长(coil)"),
	/**
	 * 现品尺寸 长(sheet)
	 */
	xpcn("xpcn", "现品尺寸 长(sheet)"),
	/**
	 * 计算重量
	 */
	jszl("jszl", "计算重量"),
	/**
	 * 实际重量
	 */
	sjzl("sjzl", "实际重量"),
	/**
	 * 净重量
	 */
	jnzl("jnzl", "净重量"),
	/**
	 * 毛重量
	 */
	mazl("mazl", "毛重量"),
	/**
	 * 制品重量
	 */
	zpzl("zpzl", "制品重量"),

	/**
	 * 装入重量
	 */
	zrzl("zrzl", "装入重量"),
	/**
	 * 等级
	 */
	deng("deng", "等级"),
	/**
	 * 产量
	 */
	chan("chan", "产量"),
	/**
	 * 垫木重量
	 */
	dmzl("dmzl", "垫木重量"),
	/**
	 * 制造商代码
	 */
	zzsd("zzsd", "制造商代码"),
	/**
	 * 包装材料重量
	 */
	bzcl("bzcl", "包装材料重量"),
	/**
	 * pile区分
	 */
	plqf("plqf", " pile区分"),
	/**
	 * 运用规格
	 */
	yuny("yuny", "运用规格"),
	/**
	 * 出口包装no
	 */
	ckno("ckno", "出口包装no"),
	/**
	 * 钢种类型
	 */
	gzlx("gzlx", "钢种类型"),
	/**
	 * 包装张数
	 */
	zshu("zshu", "包装张数"),
	/**
	 * 再剪边标记
	 */
	zjbb("zjbb", "再剪边标记"),
	/**
	 * 内径代码
	 */
	njno("njno", "内径代码"),
	/**
	 * 表面精加工
	 */
	face("face", "表面精加工"),
	/**
	 * 品种代码
	 */
	pzno("pzno", "品种代码"),
	/**
	 * 涂油种类
	 */
	ytyp("ytyp", "涂油种类"),
	/**
	 * 规格代码
	 */
	ggno("ggno", "规格代码"),
	/**
	 * 附着量 正面
	 */
	fuzm("fuzm", "附着量 正面"),
	/**
	 * 附着量 反面
	 */
	fufm("fufm", "附着量 反面"),
	/**
	 * 实绩品种等级
	 */
	spdj("spdj", "实绩品种等级"),
	/**
	 * 作业停止标记
	 */
	ztbj("ztbj", "作业停止标记"),
	/**
	 * 实绩附着量 正面
	 */
	sczm("sczm", "实绩附着量 正面"),
	/**
	 * 实绩附着量 反面
	 */
	scfm("scfm", "实绩附着量 反面"),
	/**
	 * 差厚镀锡标记
	 */
	chdx("chdx", "差厚镀锡标记"),
	/**
	 * 强制出货标记
	 */
	qzch("qzch", "强制出货标记"),
	/**
	 * 捆包形式
	 */
	kbao("kbao", "捆包形式"),
	/**
	 * 硬度
	 */
	ying("ying", "硬度"),
	/**
	 * 锯齿形式
	 */
	jcxs("jcxs", "锯齿形式"),
	/**
	 * 标签换贴标记
	 */
	bqht("bqht", "标签换贴标记"),
	/**
	 * 业连no
	 */
	ylno("ylno", "业连no");

	/**
	 * 键
	 */
	public final String key;
	/**
	 * 值
	 */
	public final String value;

	BGMC(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * <p>
	 * 根据键取值
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (BGMC o : values()) {
			if (key.equals(o.key)) {
				return o.value;
			}
		}
		return null;
	}

}
