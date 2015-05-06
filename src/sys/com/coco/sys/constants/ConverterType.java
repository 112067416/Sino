package com.coco.sys.constants;

import com.coco.sys.converter.ExcelConverter;
import com.coco.sys.converter.TextConverter;
import com.coco.sys.converter.api.IConverter;

/**
 * <p>
 * 转换器类型
 * </p>
 * <p>
 * create: 2010-12-31 上午11:31:37
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum ConverterType {

	excel("0", "Excel转换器", new ExcelConverter()), text("1", "文本转换器",
			new TextConverter());

	public final String type;

	public final String description;

	public final IConverter converter;

	ConverterType(String type, String description, IConverter converter) {
		this.type = type;
		this.description = description;
		this.converter = converter;
	}

	public static IConverter getConverter(String type) {
		for (ConverterType ct : values()) {
			if (ct.type.equals(type)) {
				return ct.converter;
			}
		}
		return null;
	}

}
