package com.coco.query.bean;

import java.util.ArrayList;
import java.util.List;

public class Param implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String column;

	private String label;

	private String area;

	private Class<?> type;

	private List<Option> options;

	private String sqlOption;

	private Opt[] opts;

	public Param(String id, String column, String label, String area,
			Class<?> type, List<Option> options, String sqlOption) {
		this.id = id;
		this.column = column;
		this.label = label;
		this.area = area;
		this.type = type;
		this.options = options;
		this.sqlOption = sqlOption;
	}

	public Param(String id, String column, String label, String area,
			String type, List<Option> options, String sqlOption) {
		this.id = id;
		this.column = column;
		this.label = label;
		this.area = area;
		if (type == null || type.isEmpty()) {
			this.type = String.class;
		} else {
			try {
				this.type = Class.forName(type);
			} catch (Exception e) {
				this.type = String.class;
			}
		}
		this.options = options;
		this.sqlOption = sqlOption;
	}

	public String getId() {
		return id;
	}

	public String getArea() {
		if (area == null || area.isEmpty()) {
			return "1";
		}
		return area;
	}

	public String getColumn() {
		return column;
	}

	public String getLabel() {
		return label;
	}

	public Class<?> getType() {
		return type;
	}

	public Option[] getOptions() {
		if (options == null || options.isEmpty()) {
			return null;
		}
		Option[] arr = new Option[options.size()];
		return options.toArray(arr);
	}

	public String getSqlOption() {
		return sqlOption;
	}

	public Opt[] getAllowOpts() {
		if (opts != null) {
			return opts;
		}
		List<Opt> optList = new ArrayList<Opt>();
		optList.add(Opt.eq);
		optList.add(Opt.ne);
		optList.add(Opt.ge);
		optList.add(Opt.gt);
		optList.add(Opt.le);
		optList.add(Opt.lt);

		if (type != null && String.class.isAssignableFrom(type)) {
			optList.add(Opt.like);
			optList.add(Opt.isEmpty);
		}
		optList.add(Opt.isNull);
		optList.add(Opt.isNotNull);
		optList.add(Opt.in);
		optList.add(Opt.notIn);

		opts = new Opt[optList.size()];
		optList.toArray(opts);
		return opts;
	}

	/**
	 * @return the opts
	 */
	public Opt[] getOpts() {
		return opts;
	}

	/**
	 * @param opts
	 *            the opts to set
	 */
	public void setOpts(Opt[] opts) {
		this.opts = opts;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Class<?> type) {
		this.type = type;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(List<Option> options) {
		this.options = options;
	}

	/**
	 * @param sqlOption
	 *            the sqlOption to set
	 */
	public void setSqlOption(String sqlOption) {
		this.sqlOption = sqlOption;
	}

}
