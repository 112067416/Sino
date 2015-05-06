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
import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.IDeptBO;
import com.coco.sys.bo.api.IPersonBO;
import com.coco.sys.orm.Person;
import com.coco.sys.vo.PersonQE;

@Controller
@RequestMapping("/sys/person")
public class PersonController {

	@Autowired
	private IPersonBO bo;

	@Autowired
	private IDeptBO deptBO;

	public PersonController() {
	}

	public void setBo(IPersonBO bo) {
		this.bo = bo;
	}

	public void setDeptBo(IDeptBO deptBO) {
		this.deptBO = deptBO;
	}

	/**
	 * <p>
	 * 主页面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(PersonQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sys/person/list";
		}
		model.addAttribute("depts", deptBO.findValid());
		return "/sys/person/index";
	}

	/**
	 * <p>
	 * 加载用户
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id, Model model) {
		return bo.getForJs(id);
	}

	/**
	 * <p>
	 * 保存用户
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Person entity) {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			return new Result(-1, "姓名不能为空").toString();
		}
		bo.saveOrUpdate(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id) {
		try {
			bo.delete(id);
		}
		catch (Exception e) {
			throw new CocoException(-10004, "该员工已经绑定用户，请删除已经绑定的用户再删除此用户。");
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除多个用户
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		// bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping("/tree")
	public @ResponseBody
	Document tree(boolean all) {
		return bo.tree(!all);
	}

	@RequestMapping(value = "/view!{id}", method = RequestMethod.POST)
	public String view(@PathVariable String id, Model model) {
		model.addAttribute("item", bo.get(id));
		return "/sys/person/view";
	}
}
