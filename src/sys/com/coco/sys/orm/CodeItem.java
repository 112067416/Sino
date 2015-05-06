package com.coco.sys.orm;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "COCO_CODE_ITEM")
@IdClass(CodeItemPk.class)
public class CodeItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Code code;

	@Id
	private String key;

	@Column(name = "VALUE_", length = 512)
	private String value;

	@Column(name = "REMARK_", length = 512)
	private String remark;

	@Column(name = "NUMBER_", columnDefinition = "numeric(25,5)")
	private BigDecimal number;

	@Column(name = "EDITABLE_")
	private boolean editable;

	@Column(name = "DELETABLE_")
	private boolean deletable;

	@Column(name = "ORDER_", length = 10)
	private Integer order;

	@Column(name = "VALID_")
	private boolean valid;

	/**
	 * <p>
	 * 获取码表Id
	 * </p>
	 * 
	 * @return String
	 */
	public String getCodeId() {
		return code != null ? code.getId() : null;
	}

	/**
	 * @return the code
	 */
	public Code getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Code code) {
		this.code = code;
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

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the number
	 */
	public BigDecimal getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the deletable
	 */
	public boolean isDeletable() {
		return deletable;
	}

	/**
	 * @param deletable
	 *            the deletable to set
	 */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}