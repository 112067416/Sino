package com.coco.query.bean;

import java.util.ArrayList;
import java.util.List;

public class Option {

	public String key;

	public String value;

	public Option() {

	}

	public Option(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static List<Option> parse(String values) {
		if (values == null) {
			return null;
		}
		values = values.replaceAll("\\s+'|'\\s+", "'");
		if (values.length() > 2) {
			String[] arr = values.substring(1, values.length() - 1)
					.split("','");
			List<Option> options = new ArrayList<Option>();
			for (String a : arr) {
				String[] kv = a.split("':'");
				options.add(new Option(kv[0], kv.length > 1 ? kv[1] : kv[0]));
			}
			return options.isEmpty() ? null : options;
		}
		return null;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
