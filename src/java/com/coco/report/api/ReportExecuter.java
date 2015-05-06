package com.coco.report.api;

import java.util.Map;

import com.coco.report.bean.Entry;

public interface ReportExecuter<T> {

	public Entry getEntry(String id);

	public T execute(String id, Map<String, String> conditions);
}
