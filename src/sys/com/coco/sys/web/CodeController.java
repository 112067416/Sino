package com.coco.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.xml.XMLHelper;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.vo.CodeQE;

/**
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sys/code")
public class CodeController {

	@Autowired
	private ICodeBO bo;

	public CodeController() {

	}

	public void setBo(ICodeBO bo) {
		this.bo = bo;
	}

	/**
	 * 主页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(CodeQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sys/code/list";
		}
		return "/sys/code/index";
	}

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Code entity, String $type) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			return Result.ERROR;
		}
		if ("modify".equals($type)) {
			bo.update(entity);
		}
		else {
			bo.save(entity);
		}
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody
	String get(String id) {
		return bo.getForJs(id);
	}

	@RequestMapping(value = "/getItem", method = RequestMethod.POST)
	public @ResponseBody
	String get(String code, String key) {
		return bo.getCodeItemForJs(code, key);
	}

	@RequestMapping(value = "/getByValue", method = RequestMethod.POST)
	public @ResponseBody
	String getByValue(String code, String value) {
		return bo.getCodeItemByValueForJs(code, value);
	}

	@RequestMapping(value = "/listItem", method = RequestMethod.POST)
	public String listItem(String id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("items", bo.findItems(id));
		return "/sys/code/items";
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String view(String id, Model model) {
		model.addAttribute("code", bo.get(id));
		model.addAttribute("items", bo.findItems(id));
		return "/sys/code/view";
	}

	@RequestMapping(value = "/saveItem", method = RequestMethod.POST)
	public @ResponseBody
	String saveItem(String id, HttpServletRequest request) {
		List<CodeItem> items = null;
		try {
			items = XMLHelper.createNode2Bean(CodeItem.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		bo.saveItems(id, items);
		return Result.SUCCESS;
	}

}
