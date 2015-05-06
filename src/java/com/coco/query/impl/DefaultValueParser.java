package com.coco.query.impl;

import com.coco.query.api.StringConverter;
import com.coco.query.api.ValueParser;

public class DefaultValueParser implements ValueParser {

	private StringConverter converter;

	@Override
	public StringConverter getConverter() {
		if (converter == null) {
			converter = new DefaultStringConverter();
		}
		return converter;
	}

	@Override
	public <T> T parse(String value, Class<T> clazz) {
		return getConverter().convert(value, clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] parse(String[] values, Class<T> clazz) {
		if (values == null || clazz == null) {
			return null;
		}
		if (String.class.equals(clazz)) {
			return (T[]) values;
		}
		StringConverter conv = getConverter();
		T[] ts = (T[]) new Object[values.length];
		for (int i = 0; i < values.length; i++) {
			ts[i] = conv.convert(values[i], clazz);
		}
		return ts;
	}

	@Override
	public String format(Object obj, Class<?> type) {
		return format(obj, type, -1, -1);
	}

	@Override
	public String format(Object obj, Class<?> type, int length, int precision) {
		if (obj == null) {
			return "";
		}

		return obj.toString();
	}

}
