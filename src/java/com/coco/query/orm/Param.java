package com.coco.query.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "QUERY_PARAM")
@IdClass(EntryFkPk.class)
public class Param implements Serializable {

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

	@Column(name = "COLUMN_", length = 256, nullable = false)
	private String column;
	@Column(name = "LABEL_", length = 256, nullable = false)
	private String label;
	@Column(name = "AREA_", length = 128, nullable = false)
	private String area;
	@Column(name = "TYPE_", length = 256, nullable = false)
	private String type;
	@Column(name = "OPTIONS_", length = 2000)
	private String options;
	@Column(name = "SQLOPTION_", length = 512)
	private String sqloption;

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
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
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
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	/**
	 * @return the sqloption
	 */
	public String getSqloption() {
		return sqloption;
	}

	/**
	 * @param sqloption
	 *            the sqloption to set
	 */
	public void setSqloption(String sqloption) {
		this.sqloption = sqloption;
	}

}
