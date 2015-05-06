package com.coco.query.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.env.Context;
import com.coco.core.env.Helper;
import com.coco.core.util.Utils;
import com.coco.query.api.QueryConfig;
import com.coco.query.api.ValueParser;
import com.coco.query.bean.Entry;
import com.coco.query.bean.Meta;
import com.coco.query.bean.Opt;
import com.coco.query.bean.Page;
import com.coco.query.bean.ParamItem;
import com.coco.query.util.ExcelUtils;

@Controller
@RequestMapping("/coco/query")
public class QueryController {

	@Autowired
	private QueryConfig config;

	public void setConfig(QueryConfig config) {
		this.config = config;
	}

	public QueryController() {

	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String build() {
		return "/coco/query/index";
	}

	@RequestMapping(value = "/deploy", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String build(String xmlFile) {
		config.createRepository().deploy(xmlFile);
		return "OK";
	}

	@RequestMapping(value = "/load!{id}", method = RequestMethod.GET)
	public String load(@PathVariable String id, HttpServletRequest request,
			Model model) {
		Entry entry = config.getEntry(id);
		if (entry == null) {
			model.addAttribute("result", new Result("无法获取配置查询", "查询ID为：" + id));
			return Result.URL_ERROR;
		}
		model.addAttribute("entry", entry);

		StringBuilder js = new StringBuilder();
		js.append("<script type=\"text/javascript\"><!--\n");
		js.append("cocoquery.config.id=\"").append(id).append("\";");
		if (entry.getDefaultSize() > 0) {
			js.append("cocoquery.config.size=").append(entry.getDefaultSize()).append(";");
		}
		js.append("cocoquery.config.query_opts =").append(Opt.js()).append(";\n");
		js.append("//-->\n</script>");
		model.addAttribute("iniScript", js.toString());
		return "/coco/query/load";
	}

	@RequestMapping(value = "/table!{id}", method = RequestMethod.POST)
	public @ResponseBody
	Document table(@PathVariable String id, HttpServletRequest request) {
		Entry entry = config.getEntry(id);
		if (entry == null) {
			return null;
		}
		Document doc = null;
		SAXReader sax = new SAXReader();
		try {
			doc = sax.read(request.getInputStream());
		}
		catch (Exception e1) {
		}

		if (doc == null) {
			return null;
		}
		Element root = doc.getRootElement();
		Page page = new Page();
		page.needType = true;
		page.resultType = Page.TYPE_DATAS_ARRAY;
		page.id = id;
		page.user = Context.getUser(request);
		String sId;
		String sIndex = root.attributeValue("index");
		if (sIndex != null && !(sIndex = sIndex.trim()).isEmpty()) {
			try {
				page.index = Integer.parseInt(sIndex);
			}
			catch (NumberFormatException e) {
			}
		}
		String sSize = root.attributeValue("size");
		if (sSize != null && !(sSize = sSize.trim()).isEmpty()) {
			try {
				page.size = Integer.parseInt(sSize);
			}
			catch (NumberFormatException e) {
			}
		}
		Element metasElem = root.element("metas");
		if (metasElem != null) {
			@SuppressWarnings("unchecked")
			List<Element> metaElems = metasElem.elements("meta");
			if (!metaElems.isEmpty()) {
				page.metaIds = new ArrayList<String>();
				for (Element metaElem : metaElems) {
					sId = metaElem.attributeValue("id");
					if (sId != null && !(sId = sId.trim()).isEmpty()) {
						page.metaIds.add(sId);
					}
				}
			}
		}
		Element paramsElem = root.element("params");
		if (paramsElem != null) {
			@SuppressWarnings("unchecked")
			List<Element> paramElems = paramsElem.elements("param");
			if (!paramElems.isEmpty()) {
				page.params = new ArrayList<ParamItem>();
				parseParam(page.params, paramElems);
			}
		}
		if (page.size <= 0) {
			page.size = entry.getDefaultSize();
		}
		config.createEngine().query(page);
		doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		root = doc.addElement("root");
		root.addAttribute("index", String.valueOf(page.index));
		root.addAttribute("size", String.valueOf(page.size));
		root.addAttribute("count", String.valueOf(page.count));
		root.addAttribute("pageCount", String.valueOf(page.pageCount));
		root.addAttribute("meta", page.metaIdStr);
		Element columns = root.addElement("columns");
		Element column;
		for (Meta $meta : page.metas) {
			column = columns.addElement("column");
			column.addAttribute("id", $meta.getId());
			column.addAttribute("orderable", String.valueOf($meta.isOrderable()));
			column.addAttribute("label", $meta.getLabel());
		}
		if (page.objects != null && !page.objects.isEmpty()) {
			Element item = null;
			Element field = null;
			Meta meta;
			ValueParser parser = config.createValueParser();
			for (Object[] objs : page.objects) {
				item = root.addElement("item");
				for (int i = 0; i < objs.length; i++) {
					meta = page.metas[i];
					field = item.addElement(meta.getId());
					field.setText(parser.format(objs[i], page.types.get(meta.getId())));
				}
			}
		}
		return doc;
	}

	@RequestMapping(value = "/excel!{id}", method = RequestMethod.POST)
	public @ResponseBody
	Document excel(@PathVariable String id, HttpServletRequest request) {
		Entry entry = config.getEntry(id);
		if (entry == null) {
			return null;
		}
		Document doc = null;
		SAXReader sax = new SAXReader();
		try {
			doc = sax.read(request.getInputStream());
		}
		catch (Exception e1) {
		}

		if (doc == null) {
			return null;
		}
		Element root = doc.getRootElement();
		Page page = new Page();
		page.needType = true;
		page.resultType = Page.TYPE_DATAS_ARRAY;
		page.id = id;
		page.user = Context.getUser(request);
		String sId;
		String sIndex = root.attributeValue("index");
		if (sIndex != null && !(sIndex = sIndex.trim()).isEmpty()) {
			try {
				page.index = Integer.parseInt(sIndex);
			}
			catch (NumberFormatException e) {
			}
		}

		Element metasElem = root.element("metas");
		if (metasElem != null) {
			@SuppressWarnings("unchecked")
			List<Element> metaElems = metasElem.elements("meta");
			if (!metaElems.isEmpty()) {
				page.metaIds = new ArrayList<String>();
				for (Element metaElem : metaElems) {
					sId = metaElem.attributeValue("id");
					if (sId != null && !(sId = sId.trim()).isEmpty()) {
						page.metaIds.add(sId);
					}
				}
			}
		}
		Element paramsElem = root.element("params");
		if (paramsElem != null) {
			@SuppressWarnings("unchecked")
			List<Element> paramElems = paramsElem.elements("param");
			if (!paramElems.isEmpty()) {
				page.params = new ArrayList<ParamItem>();
				parseParam(page.params, paramElems);
			}
		}
		config.createEngine().query(page);
		doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		root = doc.addElement("root");
		String uuid = Utils.uuid();
		root.addAttribute("id", uuid);

		InputStream is = null;
		OutputStream os = null;
		String realPath = Helper.SERVLET_CONTEXT.getRealPath("/WEB-INF/temp/report");
		HSSFWorkbook workbook = null;
		HSSFSheet sheet;
		HSSFRow header;
		HSSFRow row;
		HSSFCell cell;
		HSSFCellStyle headerStyle, cellStyle;
		HSSFFont headerFont, cellFont;
		int rowIndex = 0, cellIndex;
		try {
			// 生成Excel
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet(entry.getName());
			headerStyle = workbook.createCellStyle();
			headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headerFont = workbook.createFont();
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headerFont.setFontHeight((short) 210);
			headerStyle.setFont(headerFont);
			headerStyle.setWrapText(false);
			headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
			headerStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
			headerStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
			headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			headerStyle.setFillPattern(HSSFPatternFormatting.SOLID_FOREGROUND);

			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellFont = workbook.createFont();
			cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			cellStyle.setFont(cellFont);
			cellStyle.setWrapText(false);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			header = sheet.createRow(rowIndex++);
			header.setHeight((short) 400);
			cellIndex = 0;
			for (Meta meta : page.metas) {
				cell = header.createCell(cellIndex++, HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(headerStyle);
				cell.setCellValue(new HSSFRichTextString(meta.getLabel()));
			}
			if (page.objects != null) {
				for (Object[] objs : page.objects) {
					row = sheet.createRow(rowIndex++);
					cellIndex = 0;
					for (Object obj : objs) {
						cell = row.createCell(cellIndex++);
						cell.setCellStyle(cellStyle);
						ExcelUtils.setCellValue(cell, obj);
					}
				}
			}
			os = new FileOutputStream(realPath + "/" + uuid);
			workbook.write(os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
				}
			}
		}
		return doc;
	}

	@RequestMapping(value = "/loadExcel!{id}", method = RequestMethod.GET)
	public String loadExcel(@PathVariable String id,
			HttpServletRequest request, Model model) {
		model.addAttribute("id", id);
		return "/coco/query/excel";
	}

	@RequestMapping(value = "/openExcel!{id}", method = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT })
	public void openExcel(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse response) {
		if (id == null || id.isEmpty()) {
			return;
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		InputStream is = null;
		String realPath = Helper.SERVLET_CONTEXT.getRealPath("/WEB-INF/temp/report");
		File file = new File(realPath + "/" + id);
		if (!file.isFile()) {
			return;
		}
		try {
			is = new FileInputStream(file);
			byte[] bs = new byte[8096];
			int len;
			OutputStream os = response.getOutputStream();
			while ((len = is.read(bs)) > 0) {
				os.write(bs, 0, len);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void parseParam(List<ParamItem> paramItems, List<Element> paramElems) {

		ParamItem paramItem;
		String sId;
		String sType;
		List<Element> valueElements;
		List<Element> orElems;
		List<ParamItem> ors;
		for (Element paramElem : paramElems) {
			sId = paramElem.attributeValue("id");
			if (sId == null || (sId = sId.trim()).isEmpty()) {
				continue;
			}
			paramItem = new ParamItem();
			paramItem.setId(sId);
			sType = paramElem.attributeValue("opt");
			if (sType != null && !(sType = sType.trim()).isEmpty()) {
				try {
					paramItem.setOptType(Integer.parseInt(sType));
				}
				catch (NumberFormatException e) {
				}
			}
			valueElements = paramElem.elements("value");
			if (valueElements.size() == 1) {
				Element valueElement = valueElements.get(0);
				paramItem.setValue(valueElement.getTextTrim());
			}
			else if (valueElements.size() > 1) {
				List<String> values = new ArrayList<String>();
				String value;
				for (Element valueElement : valueElements) {
					value = valueElement.getTextTrim();
					if (value != null && !value.isEmpty()) {
						values.add(value);
					}
				}
				String[] valueArr = new String[values.size()];
				values.toArray(valueArr);
				paramItem.setValues(valueArr);
			}
			paramItems.add(paramItem);
			orElems = paramElem.elements("param");
			if (!paramElems.isEmpty()) {
				ors = new ArrayList<ParamItem>();
				paramItem.setOrs(ors);
				parseParam(ors, orElems);
			}

		}
	}
}
