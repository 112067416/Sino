package com.coco.report.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coco.report.bean.Entry;
import com.coco.report.bean.Module;
import com.coco.report.bean.Param;

public class DefaultReportFactory {

	private static final Logger logger = Logger.getLogger(DefaultReportFactory.class.getName());

	// private static ReportFactory factory;

	private Map<String, Entry> entries = new HashMap<String, Entry>();

	// private ReportFactory() {
	// init("report.xml");
	// }

	// public synchronized static ReportFactory getInstance() {
	// if (factory == null) {
	// factory = new ReportFactory();
	// }
	// return factory;
	// }

	public DefaultReportFactory() {
		init("report.xml");
	}

	public DefaultReportFactory(String xmlFile) {
		init(xmlFile);
	}

	public void init(String xmlFile) {
		if (entries.size() > 0) {
			entries.clear();
		}
		load(xmlFile);
	}

	@SuppressWarnings("unchecked")
	public void load(String xmlFile) {
		Document doc = null;
		try {
			doc = new SAXReader().read(this.getClass().getClassLoader().getResourceAsStream(xmlFile));
			Element root = doc.getRootElement();
			List<Element> elements = root.elements("entry");
			Entry entry;
			Param param;
			Module module;
			String id, title, sql, type, opt, name, column, length, jointLength, group, newline;
			for (Element element : elements) {
				id = element.elementTextTrim("id");
				title = element.elementTextTrim("title");
				if (id == null) {
					continue;
				}
				entry = new Entry();
				entry.setId(id);
				entry.setTitle(title);
				entry.setExcel(element.elementTextTrim("excel"));
				String copyProperty = element.elementTextTrim("copy-property");
				if (copyProperty != null) {
					try {
						entry.setCopyProperty(Integer.parseInt(copyProperty));
					}
					catch (Exception e1) {
					}
				}
				else {
					entry.setCopyProperty(0);
				}
				String sType = element.elementTextTrim("type");
				if (sType != null) {
					try {
						entry.setType(Integer.parseInt(sType));
					}
					catch (Exception e1) {
					}
				}
				String sSheetIndex = element.elementTextTrim("sheet-index");
				if (sSheetIndex != null) {
					String[] sis = sSheetIndex.split(",");
					Set<String> siSet = new java.util.HashSet<String>();
					List<Integer> siList = new ArrayList<Integer>();
					int sheetIndex;
					for (String si : sis) {
						if (siSet.contains(si)) {
							continue;
						}
						siSet.add(si);
						try {
							sheetIndex = Integer.parseInt(si);
							if (sheetIndex >= 0) {
								siList.add(sheetIndex);
							}
						}
						catch (Exception e) {
						}
					}
					int[] siArr = new int[siList.size()];
					int index = 0;
					for (Integer si : siList) {
						siArr[index++] = si;
					}
					entry.setSheetIndex(siArr);
				}
				String sRowIndex = element.elementTextTrim("row-index");
				if (sRowIndex != null) {
					try {
						entry.setRowIndex(Integer.parseInt(sRowIndex));
					}
					catch (Exception e1) {
					}
				}
				String sCellIndex = element.elementTextTrim("cell-index");
				if (sCellIndex != null) {
					try {
						entry.setCellIndex(Integer.parseInt(sCellIndex));
					}
					catch (Exception e1) {
					}
				}
				String sSeqIndex = element.elementTextTrim("seq-index");
				if (sCellIndex != null) {
					try {
						entry.setSeqIndex(Integer.parseInt(sSeqIndex));
					}
					catch (Exception e1) {
					}
				}
				Element params = element.element("params");
				if (params != null) {
					List<Element> pes = params.elements("param");
					for (Element pe : pes) {
						name = pe.elementTextTrim("name");
						column = pe.elementTextTrim("column");
						if (name == null || column == null) {
							continue;
						}
						type = pe.elementTextTrim("type");
						if (type == null) {
							type = "string";
						}
						opt = pe.elementTextTrim("opt");
						if (opt == null) {
							opt = "eq";
						}
						length = pe.elementTextTrim("length");
						if (length == null || length.isEmpty()) {
							length = "0";
						}
						jointLength = pe.elementTextTrim("jointLength");
						if (jointLength == null || jointLength.isEmpty()) {
							jointLength = "0";
						}
						param = new Param();
						param.setName(name);
						param.setColumn(column);
						param.setType(type);
						param.setOpt(opt);
						param.setScx(pe.elementTextTrim("scx"));
						param.setKey(pe.elementTextTrim("key"));
						param.setSelect(pe.elementTextTrim("select"));
						param.setRequired("true".equalsIgnoreCase(pe.elementTextTrim("required")));
						param.setValue(pe.elementTextTrim("value"));
						param.setLength(Integer.parseInt(length));
						param.setJointChar(pe.elementTextTrim("jointChar"));
						param.setJointColumn(pe.elementTextTrim("jointColumn"));
						param.setJointLength(Integer.parseInt(jointLength));
						param.setJointed("true".equalsIgnoreCase(pe.elementTextTrim("jointed")));
						param.setValAppend(pe.elementTextTrim("valAppend"));
						entry.add(param);
					}
				}
				Element modules = element.element("modules");
				if (modules != null) {
					List<Element> pes = modules.elements("module");
					for (Element pe : pes) {
						name = pe.elementTextTrim("name");
						sql = pe.elementTextTrim("sql");
						if (name == null || sql == null) {
							continue;
						}
						group = pe.elementTextTrim("group");
						newline = pe.elementTextTrim("newline");
						module = new Module();
						module.setName(name);
						module.setSql(sql);
						if (group != null && !group.isEmpty()) {
							String[] groups = group.split(",");
							List<String> groupNames = new ArrayList<String>();
							for (String g : groups) {
								if ((g = g.trim()).length() > 0) {
									groupNames.add(g);
								}
							}
							module.setGroupNames(groupNames);
						}
						if (newline != null && !newline.isEmpty()) {
							String[] newlineArr = newline.split(",");
							int[] newlines = new int[newlineArr.length];
							for (int i = 0; i < newlines.length; i++) {
								try {
									newlines[i] = Integer.parseInt(newlineArr[i]);
								}
								catch (Exception e) {
									newlines[i] = 0;
								}
								if (newlines[i] < 0) {
									newlines[i] = 0;
								}
							}
							module.setNewlines(newlines);
						}
						entry.put(name, module);
					}
				}
				entry.setScript(element.elementTextTrim("script"));
				entry.setVariable(element.elementTextTrim("variable"));
				entries.put(entry.getId(), entry);
			}
			List<Element> imports = root.elements("include");
			for (Element eImport : imports) {
				String fileName = eImport.attributeValue("resource");
				if (fileName == null
						|| (fileName = fileName.trim()).length() == 0) {
					continue;
				}
				load(fileName);
			}
			logger.info("导入报表配置: " + xmlFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Entry get(String id) {
		return entries.get(id);
	}
}
