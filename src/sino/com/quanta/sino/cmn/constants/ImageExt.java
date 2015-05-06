package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 图片后缀名
 * </p>
 * <p>
 * create: 2011-4-15 下午03:52:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ImageExt {

	/**
	 * 
	 */
	jpeg,
	/**
	 * 
	 */
	jpeg2000,

	/**
	 * 
	 */
	jpg,

	/**
	 * 
	 */
	gif,

	/**
	 * 
	 */
	bmp,

	/**
	 * 
	 */
	tiff,

	/**
	 * 
	 */
	psd,

	/**
	 * 
	 */
	png,

	/**
	 * 
	 */
	svg,

	/**
	 * 
	 */
	pcx,

	/**
	 * 
	 */
	dxf,

	/**
	 * 
	 */
	wmf,

	/**
	 * 
	 */
	emf,

	/**
	 * 
	 */
	lic,

	/**
	 * 
	 */
	eps,

	/**
	 * 
	 */
	tga;

	public static boolean isExisted(String key) {
		if (key == null || key.isEmpty()) {
			return false;
		}
		for (ImageExt value : values()) {
			if (value.name().equalsIgnoreCase(key)) {
				return true;
			}
		}
		return false;
	}
}
