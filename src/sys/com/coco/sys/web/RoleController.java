package com.coco.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IRoleBO;
import com.coco.sys.bo.api.ISourceBO;
import com.coco.sys.orm.Role;
import com.coco.sys.vo.RoleQE;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

	@Autowired
	private IRoleBO bo;

	@Autowired
	private ISourceBO sourceBo;

	public void setBo(IRoleBO bo) {
		this.bo = bo;
	}

	public RoleController() {
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(RoleQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sys/role/list";
		}
		return "/sys/role/index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Role post) {
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
	public @ResponseBody
	String delete(@PathVariable String id, String dept, Model model) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id) {
		return bo.getForJs(id);
	}

	@RequestMapping(value = "/getMenuIds!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getMenuIds(@PathVariable String id) {
		List<String> items = bo.findMenuIds(id);
		StringBuffer buf = new StringBuffer();
		for (String item : items) {
			if (buf.length() > 0) {
				buf.append(",");
			}
			buf.append(item);
		}
		return buf.toString();
	}

	@RequestMapping(value = "/grantMenu!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantMenu(@PathVariable String id, String[] menus) {
		bo.grantMenu(id, menus);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/loadSource!{id}", method = RequestMethod.POST)
	public String loadSource(@PathVariable String id, Model model) {
		model.addAttribute("ids", bo.findSourceIds(id));
		model.addAttribute("items", sourceBo.findAll());
		return "/sys/role/source";
	}

	@RequestMapping(value = "/grantSource!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantSource(@PathVariable String id, String[] sources) {
		bo.grantSource(id, sources);
		return Result.SUCCESS;
	}

	/**
	 * @return the sourceBo
	 */
	public ISourceBO getSourceBo() {
		return sourceBo;
	}

	/**
	 * @param sourceBo
	 *            the sourceBo to set
	 */
	public void setSourceBo(ISourceBO sourceBo) {
		this.sourceBo = sourceBo;
	}
}
