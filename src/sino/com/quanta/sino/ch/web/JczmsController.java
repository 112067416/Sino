package com.quanta.sino.ch.web;

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
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.util.NumberUtils;
import com.coco.core.xml.XMLHelper;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.JczmsItemQE;
import com.quanta.sino.ch.vo.ZxzsQE;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.JczmsItem;

/**
 * <p>
 * 检查证明书管理控制器
 * </p>
 * create time 2011-1-12下午09:04:54
 * 
 * @author 张良[jimsonhappy@126.com]
 */
@Controller
@RequestMapping("/sino/ch/jczms")
public class JczmsController {

	/**
	 * 制品检查证明书业务接口
	 */
	@Autowired
	private IJczmsBO bo;

	/**
	 * 装箱指示书业务接口
	 */
	@Autowired
	private IZxzsBO zbo;

	/**
	 * <p>
	 * 判断制品检查证明书，是否存在
	 * </p>
	 * 
	 * @param jczmsItem
	 * @return String
	 */
	@RequestMapping(value = "/checkJczms", method = RequestMethod.POST)
	public @ResponseBody
	String checkJczms(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new Result(-1, "获取参数失败").toString();
		}
		return bo.checkJczms(ids.split(","));
	}

	/**
	 * <p>
	 * 修改制品检查证明书
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/zzJczms", method = RequestMethod.POST)
	public @ResponseBody
	String zzJczms(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new Result(-1, "获取参数失败").toString();
		}
		User user = Context.currentUser();
		if (user == null || user.getNo().isEmpty()) {
			return new Result(-1, "登录超时,请重新登录!").toString();
		}
		String[] zxnos = ids.split(",");
		if (zxnos.length > 40) {
			return new Result(-1, "对不起,一次最多只能制作40份检查证明书。").toString();
		}
		bo.zzJczms(zxnos, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 判断修改制品检查证明书是否成功
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/checkZzJczms", method = RequestMethod.POST)
	public @ResponseBody
	String checkZzJczms(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new Result(-1, "获取参数失败").toString();
		}
		User user = Context.currentUser();
		if (user == null || user.getNo().isEmpty()) {
			return new Result(-1, "登录超时,请重新登录!").toString();
		}
		return bo.checkZzJczms(Arrays.asList(ids.split(",")));
	}

	/**
	 * <p>
	 * 制品检查证明书入口
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ZxzsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getChriE() == null) {
			page.setChriE(page.getChriS());
		}
		page.setNeStat(ChStat.ZF.stat);
		page.setOrderBys("chri desc,zxno desc");
		zbo.queryZxzs(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ch/jczms/list" : "/sino/ch/jczms/index";
	}

	/**
	 * <p>
	 * 返回修改制品检查证明书页面
	 * </p>
	 * 
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexUpdate")
	public String indexUpdate(String zxno, Model model) {
		JczmsItemQE page = new JczmsItemQE();
		page.setZxno(zxno);
		page.setSize(-1);
		page.setOrderBys("zpno asc");
		bo.query(page);
		int chsu = 0;
		double chzl = 0d;
		Jczms jczms = null;
		if (page.getDatas().size() > 0) {
			for (JczmsItem item : page.getDatas()) {
				if (jczms == null) {
					jczms = item.getJczms();
				}
				chsu += item.getJssu();
				chzl += (item.getJszl() / 1000d);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("jczms", jczms);
		model.addAttribute("chsu", chsu);
		model.addAttribute("chzl", NumberUtils.format(chzl, 3));
		return "/sino/ch/jczms/indexUpdate";
	}

	/**
	 * <p>
	 * 修改制品检查证明书
	 * </p>
	 * 
	 * @param jczmsItem
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(JczmsItem jczmsItem) {
		if (jczmsItem == null || jczmsItem.getId().isEmpty()) {
			return new Result(-1, "获取参数失败").toString();
		}
		bo.update(jczmsItem);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量修改检查证明书
	 * </p>
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
	public @ResponseBody
	String updateAll(HttpServletRequest request) {
		List<JczmsItem> items = null;
		try {
			items = XMLHelper.createNode2Bean(JczmsItem.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		if (items == null || items.size() == 0) {
			return new Result(-1, "该检查证明书为空").toString();
		}
		bo.updateAll(items);
		return Result.SUCCESS;
	}

	public IJczmsBO getBo() {
		return bo;
	}

	public void setBo(IJczmsBO bo) {
		this.bo = bo;
	}

	public IZxzsBO getZbo() {
		return zbo;
	}

	public void setZbo(IZxzsBO zbo) {
		this.zbo = zbo;
	}

}
