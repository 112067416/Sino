package com.quanta.sino.ch.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.ch.bo.api.IThBO;
import com.quanta.sino.ch.bo.api.ITsBO;
import com.quanta.sino.ch.vo.TsQE;
import com.quanta.sino.cmn.constants.TsStat;
import com.quanta.sino.orm.TsTp;

/**
 * <p>
 * 投诉管理控制器
 * </p>
 * <p>
 * create: 2011-1-18 下午04:06:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ch/ts")
public class TsController {

	/**
	 * 投诉业务接口
	 */
	@Autowired
	private ITsBO tBo;

	/**
	 * 退货业务接口
	 */
	@Autowired
	private IThBO thBO;

	/**
	 * <p>
	 * 进入投诉操作页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexTs")
	public String indexTs() {
		return "/sino/ch/ts/indexTs";
	}

	/**
	 * <p>
	 * 制品厂外转卖操作
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/doCwzm", method = RequestMethod.POST)
	public @ResponseBody
	String doCwzm(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做厂外转卖的制品").toString();
		}
		tBo.doCwzm(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 撤销制品投诉
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/doCxts", method = RequestMethod.POST)
	public @ResponseBody
	String doCxts(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做撤销制品投诉的制品").toString();
		}

		tBo.doCxts(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 退货操作
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/doTh", method = RequestMethod.POST)
	public @ResponseBody
	String doTh(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要退货的记录").toString();
		}
		return thBO.doTh(ids);
	}

	/**
	 * <p>
	 * 投诉操作
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doTs", method = RequestMethod.POST)
	public @ResponseBody
	String doTs(TsTp entity) {
		return tBo.doTs(entity);
	}

	/**
	 * <p>
	 * 保存投诉记录
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(TsTp entity) {
		if (entity == null) {
			return new Result(-1, "投诉记录为空").toString();
		}
		tBo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 根据订货No和制品No，查询制品信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/zpMx", method = RequestMethod.POST)
	public @ResponseBody
	String zpMx(String jbno) {
		return tBo.get(jbno);
	}

	/**
	 * <p>
	 * 加载投诉记录
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getForUpdate", method = RequestMethod.POST)
	public @ResponseBody
	String getForUpdate(String id) {
		return tBo.getForJs(id);
	}

	/**
	 * <p>
	 * 投诉记录维护查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(TsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			// 设置状态为投诉
			page.setStat(TsStat.TS.stat);
		}
		page.setOrderBys("tsri desc");
		tBo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ch/ts/list" : "/sino/ch/ts/index";
	}

	public ITsBO gettBo() {
		return tBo;
	}

	public void settBo(ITsBO tBo) {
		this.tBo = tBo;
	}

	public IThBO getThBO() {
		return thBO;
	}

	public void setThBO(IThBO thBO) {
		this.thBO = thBO;
	}
}
