package com.coco.query.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "QUERY_META")
@IdClass(EntryFkPk.class)
public class Meta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTRY_", length = 64)
	private String entry;

	@Id
	@Column(name = "ID_", length = 64)
	private String id;

	@Column(name = "EXPR_", length = 256)
	private String expr;

	@Column(name = "LABEL_", length = 256)
	private String label;

	@Column(name = "ORDERABLE_")
	private boolean orderable;

	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the expr
	 */
	public String getExpr() {
		return expr;
	}

	/**
	 * @param expr
	 *            the expr to set
	 */
	public void setExpr(String expr) {
		this.expr = expr;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the orderable
	 */
	public boolean isOrderable() {
		return orderable;
	}

	/**
	 * @param orderable
	 *            the orderable to set
	 */
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

}
