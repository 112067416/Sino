package com.coco.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coco.query.api.QueryParser;
import com.coco.query.bean.Entry;
import com.coco.query.bean.Message;
import com.coco.query.bean.Meta;
import com.coco.query.bean.Option;
import com.coco.query.bean.Param;
import com.coco.query.exception.QueryException;

public class DefaultQueryParser implements QueryParser {

	@Override
	public List<Entry> parse(String xmlFile) {
		if (xmlFile == null || xmlFile.isEmpty()) {
			xmlFile = DEFAULT_XML_FILE;
		}
		Document doc = null;
		try {
			doc = new SAXReader().read(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(xmlFile));
		} catch (Exception ex) {
			throw new QueryException(ex);
		}
		Message message = Message.build();
		List<Entry> entries = new ArrayList<Entry>();
		parse(message, doc, xmlFile, entries);
		if (message.next() != null) {
			throw new QueryException(message.next());
		}
		return entries;
	}

	@Override
	public List<Entry> parse(Document document) {
		if (document == null) {
			throw new QueryException("The argument is null");
		}
		Message message = Message.build();
		List<Entry> entries = new ArrayList<Entry>();
		parse(message, document, "document", entries);
		if (message.next() != null) {
			throw new QueryException(message.next());
		}
		return entries;
	}

	private String fetchValue(Element element, String key) {
		String value = element.attributeValue(key);
		if (value == null || (value = value.trim()).isEmpty()) {
			element = element.element(key);
			if (element != null) {
				value = element.attributeValue("value");
				if (value == null || (value = value.trim()).isEmpty()) {
					value = element.getTextTrim();
				}
			}
		}
		return value != null && !value.isEmpty() ? value : null;
	}

	@SuppressWarnings("unchecked")
	private void parse(Message message, Document document, String xmlFile,
			List<Entry> entries) {
		if (document == null) {
			return;
		}
		Element root = document.getRootElement();
		List<Element> elEntries = root.elements("entry");
		List<Element> elMetas, elParams;
		List<Meta> metas;
		Meta meta;
		List<Param> params;
		Param param;
		String id, name, sql, description, valid, excel, metaId, expr, label, type, orderable, paramId, area, column, option, sqlOption, sSize;
		Class<?> cls = null;
		int entryIndex = 0, metaIndex = 0, paramIndex = 0, includeIndex = 0, size;
		Entry entry;
		Element el;
		Meta[] metaArr;
		Param[] paramArr;
		for (Element elEntry : elEntries) {
			entryIndex++;
			id = fetchValue(elEntry, "id");
			if (id == null) {
				message = message.error(xmlFile + ">>entry(" + (entryIndex)
						+ "):没有指定id");
				continue;
			}
			name = fetchValue(elEntry, "name");
			if (name == null) {
				message = message.error(xmlFile + ">>entry(" + (entryIndex)
						+ "):没有指定name");
				continue;
			}
			sql = fetchValue(elEntry, "sql");
			if (sql == null) {
				message = message.error(xmlFile + ">>entry(" + (entryIndex)
						+ "):没有指定sql");
				continue;
			}
			sSize = fetchValue(elEntry, "size");
			size = 0;
			if (sSize != null && !sSize.isEmpty()) {
				try {
					size = Integer.parseInt(sSize);
				} catch (NumberFormatException e) {
				}
			}
			if (size <= 0) {
				size = DEFAULT_PAGE_SIZE;;
			}

			description = fetchValue(elEntry, "description");
			valid = fetchValue(elEntry, "valid");
			excel = fetchValue(elEntry, "excel");
			el = elEntry.element("metas");
			elMetas = el == null ? null : el.elements("meta");
			if (elMetas == null || elMetas.isEmpty()) {
				continue;
			}
			metas = new ArrayList<Meta>();
			metaIndex = 0;
			for (Element elMeta : elMetas) {
				metaIndex++;
				metaId = formatId(fetchValue(elMeta, "id"));
				if (metaId == null) {
					message = message.error(xmlFile + ">>entry(" + (entryIndex)
							+ "), meta(" + metaIndex + "):没有指定查询字段ID");
					continue;
				}
				expr = fetchValue(elMeta, "expr");
				if (expr == null) {
					message = message.error(xmlFile + ">>entry(" + (entryIndex)
							+ "), meta(" + metaIndex + "):没有指定查询表达式");
					continue;
				}
				label = fetchValue(elMeta, "label");
				if (label == null) {
					message = message.error(xmlFile + ">>entry(" + (entryIndex)
							+ "), meta(" + metaIndex + "):没有指定显示文字");
					continue;
				}
				orderable = fetchValue(elMeta, "orderable");
				meta = new Meta(metaId, expr, label,
						"true".equalsIgnoreCase(orderable));
				metas.add(meta);
			}
			if (metas.isEmpty()) {
				message = message.error(xmlFile + ">>entry(" + (entryIndex)
						+ "):没有指定任何查询元素");
				continue;
			}

			el = elEntry.element("params");
			elParams = el == null ? null : el.elements("param");
			params = new ArrayList<Param>();
			if (elParams != null && !elParams.isEmpty()) {
				paramIndex = 0;
				for (Element elParam : elParams) {
					paramIndex++;
					paramId = formatId(fetchValue(elParam, "id"));
					if (paramId == null) {
						message = message.error(xmlFile + ">>entry("
								+ (entryIndex) + "), param(" + paramIndex
								+ "):没有指定条件字段ID");
						continue;
					}
					column = fetchValue(elParam, "column");
					if (column == null) {
						message = message.error(xmlFile + ">>entry("
								+ (entryIndex) + "), param(" + paramIndex
								+ "):没有指定条件字段名称");
						continue;
					}
					label = fetchValue(elParam, "label");
					if (label == null) {
						message = message.error(xmlFile + ">>entry("
								+ (entryIndex) + "), param(" + paramIndex
								+ "):没有指定条件字段显示值");
						continue;
					}
					area = fetchValue(elParam, "area");
					if (area == null) {
						area = "1";
					}
					type = fetchValue(elParam, "type");
					if (type == null) {
						cls = String.class;
					} else {
						try {
							cls = Class.forName(type);
						} catch (Exception e) {
							cls = String.class;
						}
					}
					option = fetchValue(elParam, "option");
					sqlOption = fetchValue(elParam, "sqlOption");
					param = new Param(paramId, column, label, area, cls,
							Option.parse(option), sqlOption);
					params.add(param);
				}
			}
			metaArr = new Meta[metas.size()];
			paramArr = new Param[params.size()];
			entry = new Entry(id, name, sql, size, metas.toArray(metaArr),
					params.toArray(paramArr));
			entry.setValid(valid == null || "true".equalsIgnoreCase(valid)
					? true
					: false);
			entry.setDescription(description);
			entry.setExcel(excel == null || "true".equalsIgnoreCase(excel)
					? true
					: false);
			entries.add(entry);

		}

		List<Element> elIncludes = root.elements("include");

		for (Element elInclude : elIncludes) {
			includeIndex++;
			String resource = elInclude.attributeValue("resource");
			if (resource == null || (resource = resource.trim()).length() == 0) {
				continue;
			}
			try {
				Document doc = new SAXReader().read(Thread.currentThread()
						.getContextClassLoader().getResourceAsStream(resource));
				parse(message, doc, resource, entries);
			} catch (Exception e) {
				message = message.error(xmlFile + ">>include(" + (includeIndex)
						+ "):" + e.getMessage());
			}
		}
	}

	private String formatId(String id) {
		if (id == null || (id = id.trim()).isEmpty()) {
			return null;
		}
		return id.replaceAll("\\s+", "_").toLowerCase();
	}
}
