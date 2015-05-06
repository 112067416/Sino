package com.coco.report.bean;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coco.core.util.DateUtils;

public class Param {

	private static final Pattern VAR_DATE = Pattern.compile("\\$\\{\\s*date\\s*\\}");

	private String name;

	private String type;

	private String column;

	private String scx;

	private String key;

	private String opt;

	private String select;

	private boolean required;

	private String value;

	private int length;

	private String jointChar;

	private String jointColumn;

	private int jointLength;

	private boolean jointed;

	private String valAppend;

	public String getDefaultValue() {
		if (value == null) {
			return "";
		}
		Matcher varDate = VAR_DATE.matcher(value);
		if (varDate.find()) {
			return varDate.replaceAll(DateUtils.format(new Date(), "yyyy-MM-dd"));
		}
		return "";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getScx() {
		return scx;
	}

	public void setScx(String scx) {
		this.scx = scx;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getJointChar() {
		return jointChar;
	}

	public void setJointChar(String jointChar) {
		this.jointChar = jointChar;
	}

	public String getJointColumn() {
		return jointColumn;
	}

	public void setJointColumn(String jointColumn) {
		this.jointColumn = jointColumn;
	}

	public int getJointLength() {
		return jointLength;
	}

	public void setJointLength(int jointLength) {
		this.jointLength = jointLength;
	}

	public boolean isJointed() {
		return jointed;
	}

	public void setJointed(boolean jointed) {
		this.jointed = jointed;
	}

	public String getValAppend() {
		return valAppend;
	}

	public void setValAppend(String valAppend) {
		this.valAppend = valAppend;
	}

}
