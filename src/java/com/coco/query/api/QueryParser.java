package com.coco.query.api;

import java.util.List;

import org.dom4j.Document;

import com.coco.query.bean.Entry;

public interface QueryParser {

	public static final String DEFAULT_XML_FILE = "query.xml";

	public static final int DEFAULT_PAGE_SIZE = 20;

	public List<Entry> parse(String xmlFile);

	public List<Entry> parse(Document document);
}
