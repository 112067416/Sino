package com.quanta.sino.fxs.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.CodeXq;
import com.quanta.sino.fxs.bo.api.IFxzyrzBO;
import com.quanta.sino.fxs.vo.FxzyQE;
import com.quanta.sino.fxs.vo.YcsxVO;
import com.quanta.sino.orm.FxzyRz;
import com.quanta.sino.orm.FxzyRzYcsx;

/**
 * <p>
 * 分析作业日志
 * </p>
 * <p>
 * create: 2011-2-15 下午04:40:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/fxs/fxzy")
public class FxzyrzController {

	/**
	 * 分析作业日志业务接口
	 */
	@Autowired
	private IFxzyrzBO bo;

	/**
	 * <p>
	 * 分析作业日志表查询主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(FxzyQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("tbrq desc, id desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/fxs/fxzy/list" : "/sino/fxs/fxzy/index";
	}

	/**
	 * <p>
	 * 进入新增分析作业日志页面
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDl")
	public String indexDl(Model model) {
		Calendar calendar = Calendar.getInstance();
		// 星期
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		model.addAttribute("tbxq", CodeXq.get(String.valueOf(week)).name);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		// 记录日期
		model.addAttribute("tbrq", sdf.format(date));
		// 纯水时间
		sdf = new SimpleDateFormat("HH:mm");
		model.addAttribute("cssj1", sdf.format(date));
		// 星期下拉框值
		String xqs = null;
		for (CodeXq codeXq : CodeXq.values()) {
			if (xqs == null) {
				xqs = "'" + codeXq.name + "':'" + codeXq.name + "'";
				continue;
			}
			xqs = xqs + "," + "'" + codeXq.name + "':'" + codeXq.name + "'";
		}
		model.addAttribute("xqs", xqs);
		return "/sino/fxs/fxzy/indexDl";

	}

	/**
	 * <p>
	 * 分析作业日志表详情
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/load")
	public String load(String id, Model model) {
		// 星期下拉框值
		String xqs = null;
		for (CodeXq codeXq : CodeXq.values()) {
			if (xqs == null) {
				xqs = "'" + codeXq.name + "':'" + codeXq.name + "'";
				continue;
			}
			xqs = xqs + "," + "'" + codeXq.name + "':'" + codeXq.name + "'";
		}
		model.addAttribute("xqs", xqs);
		FxzyRz entity = bo.get(id);
		model.addAttribute("entity", entity);
		return "/sino/fxs/fxzy/editDl";
	}

	/**
	 * <p>
	 * 保存分析作业日志表
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(FxzyRz entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		if (entity.getId() == null || entity.getId().isEmpty()) {
			bo.save(entity, user);
		}
		else {
			bo.update(entity, user);
		}
		return new Result(1, entity.getId()).toString();
	}

	/**
	 * <p>
	 * 修改分析作业日志表
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(FxzyRz entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		bo.update(entity, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除分析作业日志表
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		bo.delete(ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入异常事项界面
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/ycsx")
	public String toYcsx(String id, Model model) {
		if (id != null && !id.isEmpty()) {
			List<FxzyRzYcsx> ycsxs = bo.find(id);
			model.addAttribute("ycsxs", ycsxs);
			model.addAttribute("fxzyRz", bo.get(id));
		}
		return "/sino/fxs/fxzy/ycsx";
	}

	/**
	 * <p>
	 * 获得Coil No.的订货信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/getDhForJs")
	public @ResponseBody
	String getDhForJs(String jbno) {
		return bo.getDhForJs(jbno);
	}

	/**
	 * <p>
	 * 对分析作业日志，新增异常事项
	 * </p>
	 * 
	 * @param items
	 * @param pid
	 * @return String
	 */
	@RequestMapping(value = "/saveYcsx")
	public @ResponseBody
	String saveYcsx(YcsxVO vo) {
		return bo.saveYcsx(vo);
	}

	public IFxzyrzBO getBo() {
		return bo;
	}

	public void setBo(IFxzyrzBO bo) {
		this.bo = bo;
	}

}
