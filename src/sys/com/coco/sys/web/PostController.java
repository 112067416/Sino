package com.coco.sys.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IDeptBO;
import com.coco.sys.bo.api.IPostBO;
import com.coco.sys.bo.api.IRoleBO;
import com.coco.sys.orm.Post;
import com.coco.sys.vo.PostQE;
import com.coco.sys.vo.RoleQE;

@Controller
@RequestMapping("/sys/post")
public class PostController {

	@Autowired
	private IPostBO bo;

	@Autowired
	private IDeptBO deptBo;

	@Autowired
	private IRoleBO roleBo;

	public void setBo(IPostBO bo) {
		this.bo = bo;
	}

	public void setDeptBo(IDeptBO deptBo) {
		this.deptBo = deptBo;
	}

	public void setRoleBo(IRoleBO roleBo) {
		this.roleBo = roleBo;
	}

	public PostController() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("depts", deptBo.findValid());
		model.addAttribute("items", new ArrayList<Object>());
		RoleQE roleQe = new RoleQE();
		roleQe.setSize(-1);
		roleQe.setOrderBys("name");
		roleBo.query(roleQe);
		model.addAttribute("roles", roleQe.getDatas());
		return "/sys/post/index";
	}

	@RequestMapping(value = "/list!{dept}", method = RequestMethod.POST)
	public String list(@PathVariable String dept, Model model) {
		PostQE qe = new PostQE();
		qe.setDept(dept);
		qe.setSize(-1);
		bo.query(qe);
		model.addAttribute("items", qe.getDatas());
		return "/sys/post/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Post post) {
		if (post.getId() == null || post.getId().isEmpty()) {
			post.setId(null);
			bo.save(post);
		}
		else {
			bo.update(post);
		}
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/del!{id}", method = RequestMethod.POST)
	public String delete(@PathVariable String id, String dept, Model model) {
		bo.delete(id);
		return list(dept, model);
	}

	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id) {
		return bo.getForJs(id);
	}

	@RequestMapping(value = "/getUserIds!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getUserIds(@PathVariable String id) {
		List<String> items = bo.findUserIds(id);
		StringBuffer buf = new StringBuffer();
		for (String item : items) {
			if (buf.length() > 0) {
				buf.append(",");
			}
			buf.append(item);
		}
		return buf.toString();
	}

	@RequestMapping(value = "/getRoleIds!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getRoleIds(@PathVariable String id) {
		List<String> items = bo.findRoleIds(id);
		StringBuffer buf = new StringBuffer();
		for (String item : items) {
			if (buf.length() > 0) {
				buf.append(",");
			}
			buf.append(item);
		}
		return buf.toString();
	}

	@RequestMapping(value = "/grantUser!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantUser(@PathVariable String id, String[] users) {
		bo.grantUser(id, users);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/grantRole!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantRole(@PathVariable String id, String[] roles) {
		bo.grantRole(id, roles);
		return Result.SUCCESS;
	}

}
