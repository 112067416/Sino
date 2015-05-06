package com.coco.sys.orm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "COCO_CODE")
public class Code implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", length = 64)
	private String id;

	@Column(name = "DESCRIPTION_", length = 256)
	private String description;

	@Column(name = "EDITABLE_")
	private boolean editable;

	@Column(name = "VALID_")
	private boolean valid;

	@OneToMany(mappedBy = "code", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order")
	private List<CodeItem> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<CodeItem> getItems() {
		return items;
	}

	public void setItems(List<CodeItem> items) {
		this.items = items;
	}

}