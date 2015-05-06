package com.coco.core.xml.api;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coco.core.exception.CocoException;

public abstract class AbsXmlToBean<T> implements IXmlToBean<T> {

	@Override
	public T parseToBean(Object input) {
		if (input == null) {
			return null;
		}
		if (input instanceof Document) {
			return parse((Document) input);
		}
		if (input instanceof InputStream) {
			return parse((InputStream) input);
		}
		if (input instanceof Element) {
			return parse((Element) input);
		}
		if (input instanceof String) {
			return parse((String) input);
		}
		return null;
	}

	@Override
	public List<T> parseToBeans(Object input) {
		if (input == null) {
			return null;
		}
		if (input instanceof Document) {
			return parses((Document) input);
		}
		if (input instanceof InputStream) {
			return parses((InputStream) input);
		}
		if (input instanceof Element) {
			return parses((Element) input);
		}
		if (input instanceof String) {
			return parses((String) input);
		}
		return null;
	}

	public abstract T parse(Element element);

	private T parse(Document doc) {
		return parse(doc.getRootElement());
	}

	private T parse(InputStream is) {
		SAXReader sax = new SAXReader();
		try {
			return parse(sax.read(is));
		} catch (DocumentException e) {
			throw new CocoException(-1, e);
		}
	}

	private T parse(String xml) {
		try {
			return parse(DocumentHelper.parseText(xml));
		} catch (DocumentException e) {
			throw new CocoException(-1, e);
		}
	}

	private List<T> parses(Element element) {
		List<T> list = new ArrayList<T>();
		if (element == null) {
			return list;
		}
		@SuppressWarnings("unchecked")
		List<Element> items = element.elements();
		T t;
		for (Element item : items) {
			if (item.getName() != null && !item.getName().isEmpty()) {
				t = parse(item);
				if (t != null) {
					list.add(t);
				}
			}
		}
		return list;
	}

	private List<T> parses(Document doc) {
		return parses(doc.getRootElement());
	}

	private List<T> parses(InputStream is) {
		SAXReader sax = new SAXReader();
		try {
			return parses(sax.read(is));
		} catch (DocumentException e) {
			throw new CocoException(-1, e);
		}
	}

	private List<T> parses(String xml) {
		try {
			return parses(DocumentHelper.parseText(xml));
		} catch (DocumentException e) {
			throw new CocoException(-1, e);
		}
	}
}
