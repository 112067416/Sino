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
import com.coco.sys.bo.api.IUserBO;
import com.coco.sys.orm.User;
import com.coco.sys.vo.UserQE;

@Controller
@RequestMapping("/sys/user")
public class UserController {

	@Autowired
	private IUserBO bo;

	public void setBo(IUserBO bo) {
		this.bo = bo;
	}

	public UserController() {

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
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(UserQE page, Model model) {
		return "/sys/user/index";
	}

	/**
	 * <p>
	 * 通过员工加载用户,仅允许一个帐号的情况
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getByPerson!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getByPerson(@PathVariable String id, Model model) {
		return bo.getByPersonForJs(id);
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
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(User entity, String $type) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			return new Result(-1, "用户名不能为空").toString();
		}
		if ("modify".equals($type)) {
			bo.update(entity);
		}
		else {
			bo.save(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除用户
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping("/tree")
	public @ResponseBody
	Document tree(boolean all) {
		return bo.tree(!all);
	}

}
