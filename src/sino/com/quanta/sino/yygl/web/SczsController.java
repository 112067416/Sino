package com.quanta.sino.yygl.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.SczsStat;
import com.quanta.sino.orm.SczsTp;
import com.quanta.sino.yygl.bo.api.ISczsBO;
import com.quanta.sino.yygl.vo.SczsQE;

/**
 * <p>
 * 生产指示单控制器
 * </p>
 * <p>
 * create: 2011-8-4 下午03:43:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yygl/sczs")
public class SczsController {

	/**
	 * 生产指示单业务接口
	 */
	@Autowired
	private ISczsBO bo;

	/**
	 * 用户业务接口
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * <p>
	 * 分配完成
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public @ResponseBody
	String finish(String[] ids) {
		bo.updateStat(SczsStat.over.key, ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入生产指示单登录页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(SczsQE page, String dhnoAndLine, Model model) {
		page.setSize(-1);
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		page.setDdno(user.getNo());
		if (!page.isQuery()) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
				model.addAttribute("crea", date);
			}
			catch (ParseException e) {
			}
			page.setCreaBegin(date);
		}
		page.setCreaEnd(page.getCreaBegin());
		page.setOrderBys("crea desc, jhqi desc, dhno asc, line asc");
		bo.query(page);
		model.addAttribute("page", page);
		if (dhnoAndLine != null && !dhnoAndLine.isEmpty()) {
			String[] arrs = dhnoAndLine.split("-");
			model.addAttribute("dhno", arrs[0]);
			model.addAttribute("line", arrs.length > 1 ? arrs[1] : null);
		}
		model.addAttribute("ddno", page.getDdno());
		model.addAttribute("fixed", SczsStat.over.key);
		return page.isQuery() ? "/sino/yygl/sczs/list"
				: "/sino/yygl/sczs/index";
	}

	/**
	 * <p>
	 * 生产指示单查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexSczs")
	public String indexSczs(SczsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			page.setStat(SczsStat.newly.key);
		}
		page.setCreaEnd(page.getCreaBegin());
		page.setOrderBys("crea desc, jhqi desc, dhno asc, line asc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", SczsStat.over.key);
		return page.isQuery() ? "/sino/yygl/sczs/listSczs"
				: "/sino/yygl/sczs/indexSczs";
	}

	/**
	 * <p>
	 * 进入修改次生产指示单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(String id, Model model) {
		model.addAttribute("entity", bo.get(id));
		return "/sino/yygl/sczs/updateSczs";
	}

	/**
	 * <p>
	 * 通过用户代码及订货no获取订货DB的信息
	 * </p>
	 * 
	 * @param dhno
	 *            订货No
	 * @param line
	 *            行号
	 * @return String
	 */
	@RequestMapping(value = "/getDhInfo", method = RequestMethod.POST)
	public @ResponseBody
	String getDhInfo(String dhno, String line) {
		if (dhno == null || dhno.isEmpty()) {
			return new Result(-1, "订货合同号不能为空!").toString();
		}
		return bo.getDhInfo(dhno, line);

	}

	/**
	 * <p>
	 * 保存生产指示单
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(SczsTp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (entity.getId() == null || entity.getId().isEmpty()) {
			entity.setDdno(user.getNo());
			entity.setDdnm(user.getName());
			bo.save(entity);
		}
		else {
			bo.update(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除生产指示单
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "生产指示单主键不能为空").toString();
		}
		return bo.delete(ids);
	}

	public ISczsBO getBo() {
		return bo;
	}

	public void setBo(ISczsBO bo) {
		this.bo = bo;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

}
