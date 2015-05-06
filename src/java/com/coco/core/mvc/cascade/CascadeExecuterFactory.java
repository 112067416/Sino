package com.coco.core.mvc.cascade;

import java.util.Map;

import com.coco.core.mvc.cascade.api.ICascadeExecuter;

public class CascadeExecuterFactory {

	private Map<String, ICascadeExecuter> executers;

	public ICascadeExecuter get(String key) {
		if (key == null || executers == null) {
			return null;
		}
		return executers.get(key);
	}

	/**
	 * @return the executers
	 */
	public Map<String, ICascadeExecuter> getExecuters() {
		return executers;
	}

	/**
	 * @param executers
	 *            the executers to set
	 */
	public void setExecuters(Map<String, ICascadeExecuter> executers) {
		this.executers = executers;
	}

}
