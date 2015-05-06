package com.coco.report.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.env.Helper;
import com.coco.core.util.Utils;
import com.coco.report.api.ExcelReportExecuter;
import com.coco.report.api.ReportExecuter;
import com.coco.report.bean.Entry;

@Controller
@RequestMapping("/coco/report")
public class ReportController {

	@Autowired
	private ReportExecuter<?> executer;

	public void setExecuter(ReportExecuter<?> executer) {
		this.executer = executer;
	}

	public ReportController() {

	}

	/**
	 * <p>
	 * 装载报表
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/load!{id}", method = RequestMethod.GET)
	public String load(@PathVariable String id, Model model) {
		if (id == null || id.length() == 0) {
			model.addAttribute(Result.DEFAULT_KEY, new Result("执行错误", "没有指定报表"));
			return Result.URL_ERROR;
		}
		Entry entry = executer.getEntry(id);
		if (entry == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result("执行错误",
					"无法获取报表配置"));
			return Result.URL_ERROR;
		}
		// model.addAttribute("entry", executer.getEntry(id));
		model.addAttribute("entry", entry);
		return "/coco/report/load";
	}

	private Map<String, String> requestValues(HttpServletRequest request) {
		Map<String, String> values = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration = request.getParameterNames();
		String name, value;
		while (enumeration.hasMoreElements()) {
			name = enumeration.nextElement();
			value = request.getParameter(name);
			if (name == null || (name = name.trim()).length() == 0
					|| value == null || (value = value.trim()).length() == 0) {
				continue;
			}
			try {
				values.put(name, value);
			}
			catch (Exception e) {
			}
		}
		return values;
	}

	@RequestMapping(value = "/excel!{id}", method = { RequestMethod.POST })
	public @ResponseBody
	String excel(@PathVariable String id, HttpServletRequest request,
			Model model) {
		HSSFWorkbook workbook = null;
		if (id != null) {
			Map<String, String> values = requestValues(request);
			try {
				Object obj = executer.execute(id, values);
				if (obj == null || !(obj instanceof HSSFWorkbook)) {
					workbook = ExcelReportExecuter.createWorkbook("无法获取Excel执行接口实现");
				}
				else {
					workbook = (HSSFWorkbook) obj;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				workbook = ExcelReportExecuter.createWorkbook(e.getMessage());
			}
		}
		if (workbook == null) {
			return new Result(-1, "无法执行报表查询").toString();
			// model.addAttribute("result", new Result(-1, "无法执行报表查询"));
			// return Result.URL_BLANK;
		}
		OutputStream os = null;
		String realPath = Helper.SERVLET_CONTEXT.getRealPath("/WEB-INF/temp/report");
		String uuid = Utils.uuid();
		try {
			os = new FileOutputStream(realPath + "/" + uuid);
			workbook.write(os);
		}
		catch (IOException e) {
			return new Result(-1, "读写文件出错").toString();
			// model.addAttribute("result", new Result(-1, "读写文件出错"));
			// return Result.URL_BLANK;
		}
		return new Result(1, uuid).toString();
		// model.addAttribute("uuid", uuid);
		// return "/coco/report/executed";
	}

	@RequestMapping(value = "/openExcel!{uuid}", method = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT })
	public void openExcel(@PathVariable String uuid,
			HttpServletRequest request, HttpServletResponse response) {
		if (uuid == null || uuid.isEmpty()) {
			return;
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		InputStream is = null;
		String realPath = Helper.SERVLET_CONTEXT.getRealPath("/WEB-INF/temp/report");
		File file = new File(realPath + "/" + uuid);
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
}
