package com.coco.report.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entry {

	private String id;

	private String title;

	private int type;

	private String excel;

	private int[] sheetIndex;

	private int rowIndex;

	private int cellIndex;

	private int seqIndex;

	private Map<String, Module> modules;

	private List<Param> params;

	private String script;

	private String variable;

	private int copyProperty;

	public void add(Param param) {
		if (params == null) {
			params = new ArrayList<Param>();
		}
		params.add(param);
	}

	public void put(String name, Module module) {
		if (modules == null) {
			modules = new HashMap<String, Module>();
		}
		modules.put(name, module);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public int[] getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int[] sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getCellIndex() {
		return cellIndex;
	}

	public void setCellIndex(int cellIndex) {
		this.cellIndex = cellIndex;
	}

	public Map<String, Module> getModules() {
		return modules;
	}

	public int getSeqIndex() {
		return seqIndex;
	}

	public void setSeqIndex(int seqIndex) {
		this.seqIndex = seqIndex;
	}

	public List<Param> getParams() {
		return params;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public int getCopyProperty() {
		return copyProperty;
	}

	public void setCopyProperty(int copyProperty) {
		this.copyProperty = copyProperty;
	}

}
