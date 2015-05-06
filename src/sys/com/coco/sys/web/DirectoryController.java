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
import com.coco.sys.bo.api.IDirectoryBO;
import com.coco.sys.orm.Directory;

/**
 * <p>
 * 资料室目录管理控制器
 * </p>
 * <p>
 * create: 2011-1-8 下午01:26:18
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/directory")
public class DirectoryController {

	/**
	 * 资料室目录管理业务接口
	 */
	@Autowired
	private IDirectoryBO bo;

	public DirectoryController() {
	}

	public void setBo(IDirectoryBO bo) {
		this.bo = bo;
	}

	@RequestMapping("/tree")
	public @ResponseBody
	Document tree(boolean all) {
		return bo.tree(!all);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Directory entity) {
		bo.saveOrUpdate(entity);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("items", bo.findAll());
		return "/sys/directory/index";
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
		return "/sys/directory/exclude";
	}

	@RequestMapping("/del!{id}")
	public @ResponseBody
	String delete(@PathVariable String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

}
