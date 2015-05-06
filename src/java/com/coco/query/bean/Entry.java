package com.coco.query.bean;

import java.util.HashMap;
import java.util.Map;

public class Entry implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String sql;

	private String description;

	private int defaultSize;

	private boolean valid;

	private boolean excel;

	private Meta[] metas;

	private Param[] params;

	private String $metas;

	private String[] $metaIds;

	private Map<String, Param> $params = new HashMap<String, Param>();

	public Entry(String id, String name, String sql, int defaultSize,
			Meta[] metas, Param[] params) {
		this.id = id;
		this.name = name;
		this.sql = sql;
		this.defaultSize = defaultSize;
		this.metas = metas;
		if (metas != null) {
			StringBuilder sb = new StringBuilder();
			$metaIds = new String[metas.length];
			int index = 0;
			for (Meta meta : metas) {
				$metaIds[index++] = meta.getId();
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(meta.getExpr()).append(" ").append(meta.getId());
			}
			this.$metas = sb.toString();
		}
		this.params = params;
		if (params != null) {
			for (Param param : params) {
				$params.put(param.getId(), param);
			}
		}
	}

	public String[] allMetaIds() {
		return $metaIds;
	}

	public String allColumn() {
		return $metas;
	}

	public Param getParam(String id) {
		if (id == null) {
			return null;
		}
		return $params.get(id);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSql() {
		return sql;
	}

	public Meta[] getMetas() {
		return metas.clone();
	}

	public Param[] getParams() {
		return params.clone();
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDefaultSize() {
		return defaultSize;
	}

	/**
	 * @return the excel
	 */
	public boolean isExcel() {
		return excel;
	}

	/**
	 * @param excel
	 *            the excel to set
	 */
	public void setExcel(boolean excel) {
		this.excel = excel;
	}

}
