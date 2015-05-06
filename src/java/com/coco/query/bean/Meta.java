package com.coco.query.bean;

public class Meta implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String expr;

	private String label;

	private boolean orderable;

	public Meta(String id, String expr, String label, boolean orderable) {
		this.id = id;
		this.expr = expr;
		this.label = label;
		this.orderable = orderable;
	}

	public String getId() {
		return id;
	}

	public String getExpr() {
		return expr;
	}

	public String getLabel() {
		return label;
	}

	public boolean isOrderable() {
		return orderable;
	}

}
