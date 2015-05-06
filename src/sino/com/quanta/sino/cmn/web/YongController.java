package com.quanta.sino.cmn.web;

import java.util.Arrays;
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
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.vo.YongQE;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.YongSize;

/**
 * <p>
 * 用户主文件模块控制器
 * </p>
 * <p>
 * create: 2011-2-12 上午10:07:19
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cmn/yong")
public class YongController {

	/**
	 * 
	 */
	@Autowired
	private IYongBO bo;

	public YongController() {
	}

	/**
	 * <p>
	 * 批量设置参数值
	 * </p>
	 * 
	 * @param users
	 * @param region
	 * @param valid
	 * @param ddnm
	 * @return String
	 */
	@RequestMapping(value = "/setParams", method = RequestMethod.POST)
	public @ResponseBody
	String setParams(String users, String region, String valid, String ddnm) {
		bo.setParams(Arrays.asList(users.split(",")), region, valid, ddnm);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入编辑用户信息界面
	 * </p>
	 * 
	 * @param user
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexEdit")
	public String indexEdit(String user, Model model) {
		YongMp entity = null;
		if (user != null && !user.isEmpty()) {
			entity = bo.get(user);
			model.addAttribute("entity", entity);
		}
		model.addAttribute("valid", entity != null ? entity.getValid() : "Y");
		return "/sino/cmn/yong/edit";
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(YongQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			page.setValid("Y");
		}
		page.setOrderBys("region asc,user asc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/cmn/yong/data" : "/sino/cmn/yong/index";
	}

	/**
	 * 加载主文件
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String user, Model model) {
		if (user == null || user.isEmpty()) {
			return new Result(-1, "用户代码不能为空").toString();
		}
		return bo.getForJs(user);
	}

	/**
	 * 保存主文件
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(YongMp entity) {
		if (entity.getUser() == null || entity.getUser().isEmpty()) {
			return new Result(-1, "用户代码不能为空").toString();
		}
		// 判断用户代码是否存在
		if (bo.isExistUser(entity.getUser())) {
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
	 * @param users
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String users) {
		if (users != null && !users.isEmpty()) {
			bo.deletes(Arrays.asList(users.split(",")));
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 验证用户代码
	 * </p>
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public @ResponseBody
	String checkUser(String user) {
		if (user != null && !user.isEmpty() && bo.isExistUser(user)) {
			return new Result(-1, "用户代码" + user + "已存在,请重新设置用户代码").toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * 维护送货地点
	 * 
	 * @param mast
	 * @param mkey
	 * @return
	 */
	@RequestMapping(value = "/shdz", method = RequestMethod.POST)
	public String shdz(String user, Model model) {
		if (user == null || user.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "用户代码不能为空"));
			return Result.URL_BLANK;
		}
		model.addAttribute("user", user);
		model.addAttribute("items", bo.findShdz(user));
		return "/sino/cmn/yong/shdz";
	}

	/**
	 * <p>
	 * 查询客户尺寸和用途
	 * </p>
	 * 
	 * @param user
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/khSize", method = RequestMethod.POST)
	public String khSize(String user, Model model) {
		if (user == null || user.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "用户代码不能为空"));
			return Result.URL_BLANK;
		}
		model.addAttribute("user", user);
		model.addAttribute("items", bo.findSize(user));
		return "/sino/cmn/yong/size";
	}

	@RequestMapping(value = "/shdz!save", method = RequestMethod.POST)
	public @ResponseBody
	String saveShdz(String user, HttpServletRequest request) {
		List<YongShdz> items = null;
		try {
			items = XMLHelper.createNode2Bean(YongShdz.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		return bo.saveShdz(user, items);
	}

	/**
	 * <p>
	 * 维护用户尺寸和用途
	 * </p>
	 * 
	 * @param user
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/size!save", method = RequestMethod.POST)
	public @ResponseBody
	String saveSize(String user, HttpServletRequest request) {
		List<YongSize> items = null;
		try {
			items = XMLHelper.createNode2Bean(YongSize.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		return bo.saveSize(user, items);
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String view(String user, Model model) {
		model.addAttribute("yong", bo.get(user));
		model.addAttribute("items", bo.findShdz(user));
		return "/sino/cmn/yong/view";
	}

	/**
	 * @return the bo
	 */
	public IYongBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IYongBO bo) {
		this.bo = bo;
	}

}