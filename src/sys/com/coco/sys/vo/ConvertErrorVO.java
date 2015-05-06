package com.coco.sys.vo;

public class ConvertErrorVO {

	private int cellIndex;

	private String value;

	private String field;

	private Throwable cause;

	public ConvertErrorVO() {

	}

	public ConvertErrorVO(int cellIndex, String value, String field,
			Throwable cause) {
		this.cellIndex = cellIndex;
		this.value = value;
		this.field = field;
		this.cause = cause;
	}

	/**
	 * @return the cellIndex
	 */
	public int getCellIndex() {
		return cellIndex;
	}

	/**
	 * @param cellIndex
	 *            the cellIndex to set
	 */
	public void setCellIndex(int cellIndex) {
		this.cellIndex = cellIndex;
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
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
