package com.quanta.sino.fxs.web;

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
import com.quanta.sino.fxs.bo.api.IYyfxjgjlBO;
import com.quanta.sino.fxs.vo.YyfxQE;
import com.quanta.sino.fxs.vo.YyfxVO;
import com.quanta.sino.orm.YyfxjgJl;

/**
 * <p>
 * 药液分析结果记录
 * </p>
 * <p>
 * create: 2011-5-8 下午03:12:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/fxs/yyfx")
public class YyfxController {

	/**
	 * 药液分析业务接口
	 */
	@Autowired
	private IYyfxjgjlBO bo;

	/**
	 * <p>
	 * 药液分析记录查询主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(YyfxQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			try {
				page.setRqBegin(sdf.parse(sdf.format(calendar.getTime())));
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (page.getRqEnd() == null) {
			page.setRqEnd(page.getRqBegin());
		}
		page.setOrderBys("rq desc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("yypdVO", new YyfxVO());
		return page.isQuery() ? "/sino/fxs/yyfx/list" : "/sino/fxs/yyfx/index";
	}

	/**
	 * <p>
	 * 进入新增药液分析数据页面
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDl")
	public String indexDl(Model model) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("rq", sdf.format(date));
		model.addAttribute("yypdVO", new YyfxVO());
		return "/sino/fxs/yyfx/indexDl";

	}

	/**
	 * <p>
	 * 药液分析记录详情
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/load")
	public String load(String id, Model model) {
		YyfxjgJl entity = bo.get(id);
		model.addAttribute("entity", entity);
		model.addAttribute("yypdVO", new YyfxVO());
		return "/sino/fxs/yyfx/editDl";
	}

	/**
	 * <p>
	 * 保存或修改药液分析记录
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(YyfxjgJl entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			bo.update(entity, user);
			return Result.SUCCESS;
		}
		bo.save(entity, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除药液分析结果记录表（多个）
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		bo.deletes(ids);
		return Result.SUCCESS;
	}

	public IYyfxjgjlBO getBo() {
		return bo;
	}

	public void setBo(IYyfxjgjlBO bo) {
		this.bo = bo;
	}
}
