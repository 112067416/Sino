package com.coco.sys.web;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IMenuBO;
import com.coco.sys.orm.Menu;

/**
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	private IMenuBO bo;

	public MenuController() {

	}

	public void setBo(IMenuBO bo) {
		this.bo = bo;
	}

	@RequestMapping("/tree")
	public @ResponseBody
	Document tree(boolean all) {
		return bo.tree(!all);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Menu entity) {
		bo.saveOrUpdate(entity);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("items", bo.findAll());
		return "/sys/menu/index";
	}

	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id) {
		return bo.getForJs(id);
	}

	@RequestMapping("/exclude!{id}")
	public String exclude(@PathVariable String id, Model model) {
		model.addAttribute("items", "0".equals(id) ? bo.findAll()
				: bo.findExclude(id));
		return "/sys/menu/exclude";
	}

	@RequestMapping("/del!{id}")
	public @ResponseBody
	String delete(@PathVariable String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}
}
