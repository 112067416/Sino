package com.coco.query.bean;

import java.util.List;

public class ParamItem {

	private String id;

	private Opt opt;

	private String value;

	private String[] values;

	// private ParamItem or;

	private List<ParamItem> ors;

	public void setOptType(int type) {
		opt = Opt.parse(type);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	// public ParamItem getOr() {
	// return or;
	// }
	//
	// public void setOr(ParamItem or) {
	// this.or = or;
	// }

	public Opt getOpt() {
		return opt;
	}

	public List<ParamItem> getOrs() {
		return ors;
	}

	public void setOrs(List<ParamItem> ors) {
		this.ors = ors;
	}

	// public List<ParamItem> getAnds() {
	// return ands;
	// }
	//
	// public void setAnds(List<ParamItem> ands) {
	// this.ands = ands;
	// }

}
