package com.coco.sys.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.env.Config;
import com.coco.core.env.Context;
import com.coco.core.env.Helper;
import com.coco.core.env.Init;
import com.coco.sys.auth.User;
import com.coco.sys.bo.api.IAuth;
import com.coco.sys.bo.api.IMenuBO;
import com.coco.sys.bo.api.IPostBO;
import com.quanta.sino.cmn.bo.api.IBanBO;

/**
 * 系统管理控制器
 * 
 * @author 许德建[xudejian@126.com]
 */
@Controller
public class SysController {

	@Autowired
	private IAuth auth;

	@Autowired
	private IMenuBO menuBo;

	@Autowired
	private IPostBO postBo;

	@Autowired
	private IBanBO banBO;

	private SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm:ss EEE");

	public void setAuth(IAuth auth) {
		this.auth = auth;
	}

	public void setMenuBo(IMenuBO menuBo) {
		this.menuBo = menuBo;
	}

	public void setPostBo(IPostBO postBo) {
		this.postBo = postBo;
	}

	public void setBanBO(IBanBO banBO) {
		this.banBO = banBO;
	}

	public SysController() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		User user = (User) Context.getUser(request);
		if (user == null) {
			return "login";
		}
		model.addAttribute("shift", banBO.getBan());
		if (Config.isAdmin(user.getId())) {
			model.addAttribute("isAdmin", Boolean.TRUE);
			return "index";
		}
		if (user.getCurrentPost() != null) {
			model.addAttribute("singlePost", user.getPostIds().size() == 1);
			return "index";
		}
		if (user.getPostIds() == null || user.getPostIds().isEmpty()) {
			request.getSession().invalidate();
			model.addAttribute("result", new Result(-1, "该用户没有分配岗位"));
			return "global/error";
		}
		model.addAttribute("posts", postBo.find(user.getPostIds()));
		model.addAttribute("postId", user.getPostIds().get(0));
		return "changePost";
	}

	@RequestMapping(value = "/toChangePost", method = RequestMethod.GET)
	public String toChangePost(HttpServletRequest request, Model model) {
		User user = (User) Context.getUser(request);
		if (user == null) {
			return "login";
		}
		model.addAttribute("posts", postBo.find(user.getPostIds()));
		if (user.getCurrentPost() != null) {
			model.addAttribute("postId", user.getCurrentPost());
		}
		else {
			model.addAttribute("postId", user.getPostIds().get(0));
		}
		return "changePost";
	}

	@RequestMapping(value = "/changePost", method = RequestMethod.POST)
	public String changePost(String postId, HttpServletRequest request,
			Model model) {
		User user = (User) Context.getUser(request);
		if (user == null) {
			return "login";
		}
		if (user.getPostIds() == null || user.getPostIds().isEmpty()) {
			request.getSession().invalidate();
			model.addAttribute("result", new Result(-1, "该用户没有分配岗位"));
			return "global/error";
		}
		if (!user.getPostIds().contains(postId)) {
			request.getSession().invalidate();
			model.addAttribute("result", new Result(-1, "该用户没有分配该岗位"));
			return "global/error";
		}
		auth.changePost(request, postId);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(boolean init) {
		if (init) {
			Helper.getBean(Init.class).init(new String[] {
					"init/COCO_MENU.sql", "init/COCO_SYS.sql" });
		}
		return "login";
	}

	@RequestMapping(value = "/login!ajax", method = RequestMethod.POST)
	public @ResponseBody
	String login(String userId, String password, HttpServletRequest request) {
		try {
			auth.signIn(userId, password, request);
			return Result.SUCCESS;
		}
		catch (Exception e) {
			return new Result(-1, e.getMessage()).toString();
		}
	}

	@RequestMapping(value = "/login!form", method = RequestMethod.POST)
	public String login(String userId, String password,
			HttpServletRequest request, Model model) {
		try {
			auth.signIn(userId, password, request);
			return index(request, model);
		}
		catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		auth.signOut(request);
		return "login";
	}

	@RequestMapping(value = "/sys/data/init", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String init(String[] tables, Model model, boolean loaded) {
		Init init = Helper.getBean(Init.class);
		if (tables != null && tables.length > 0) {
			init.init(tables);
			model.addAttribute("msg", "初始化完毕");
		}
		else if (loaded) {
			model.addAttribute("msg", "至少要选择一条记录");
		}
		model.addAttribute("modules", init.getModules());
		return "init";
	}

	@RequestMapping(value = "/desktop", method = RequestMethod.GET)
	public String desktop() {
		return "desktop";
	}

	@RequestMapping("/tree")
	public @ResponseBody
	Document tree(HttpServletRequest request) {
		User user = (User) Context.getUser(request);
		if (Config.isAdmin(user.getId())) {
			return menuBo.tree(true);
		}
		return menuBo.treeByRoles(user.getRoleIds());
	}

	/**
	 * <p>
	 * 设定班别和组别
	 * </p>
	 * 
	 * @param group
	 * @param shift
	 * @return String
	 */
	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public @ResponseBody
	String config(String group, String shift, HttpServletRequest request) {
		User user = (User) Context.getUser(request);
		user.setGroup(group);
		user.setShift(shift);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 当前时间
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/currTime", method = RequestMethod.GET)
	public @ResponseBody
	String currTime() {
		return sdf.format(new java.util.Date());
	}
}
