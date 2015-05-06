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
@Table(name = "COCO_SEQUENCE")
public class Seq implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", length = 40)
	private String id;

	@Column(name = "DESCRIPTION_", length = 64)
	private String description;

	@Column(name = "VALUE_", length = 256)
	private String value;

	@Column(name = "NUMBER_")
	private Long number;

	@Column(name = "SCALE_")
	private Integer scale;

	@Column(name = "STEP_")
	private Integer step;

	@Column(name = "VALID_")
	private boolean valid;

	@OneToMany(mappedBy = "seq", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order")
	private List<SeqRule> rules;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<SeqRule> getRules() {
		return rules;
	}

	public void setRules(List<SeqRule> rules) {
		this.rules = rules;
	}

}