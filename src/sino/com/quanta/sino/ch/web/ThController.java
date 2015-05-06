package com.quanta.sino.ch.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.ch.bo.api.IThBO;
import com.quanta.sino.ch.vo.ThQE;
import com.quanta.sino.cmn.constants.ThStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.orm.ThTp;

/**
 * <p>
 * 退货管理控制器
 * </p>
 * <p>
 * create: 2011-1-20 下午07:33:17
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ch/th")
public class ThController {

	/**
	 * 退货业务接口
	 */
	@Autowired
	private IThBO tBo;

	/**
	 * 订货合同业务接口
	 */
	@Autowired
	private IDhBO htBo;

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
		return tBo.doTh(ids);
	}

	/**
	 * <p>
	 * 获取退货信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getForUpdate", method = RequestMethod.POST)
	public String getForUpdate(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "参数为空").toString();
		}
		ThTp thTp = tBo.get(id);
		model.addAttribute("thTp", thTp);
		return "/sino/ch/th/thDetail";
	}

	/**
	 * <p>
	 * 修改退货信息
	 * </p>
	 * 
	 * @param id
	 * @param bz
	 * @param thri
	 * @return String
	 */
	@RequestMapping("/update")
	public @ResponseBody
	String update(String id, String bz, Date thri) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "退货主键为空").toString();
		}
		ThTp entity = tBo.get(id);
		entity.setBz(bz);
		entity.setThri(thri);
		tBo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 设置退货记录对应的发票
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 * @return String
	 */
	@RequestMapping(value = "/setFpno", method = RequestMethod.POST)
	public @ResponseBody
	String setFpno(String[] ids, String fpno) {
		tBo.setFpno(ids, fpno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 撤销退货单条
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/doCxth", method = RequestMethod.POST)
	public @ResponseBody
	String doCxth(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要撤销退货的记录").toString();
		}
		return tBo.doCxth(ids);
	}

	/**
	 * <p>
	 * 退货记录查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ThQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys("thri desc");
		page.setZt(ThStat.TH.stat);
		tBo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ch/th/list" : "/sino/ch/th/index";
	}

	public IThBO gettBo() {
		return tBo;
	}

	public void settBo(IThBO tBo) {
		this.tBo = tBo;
	}

	public IDhBO getHtBo() {
		return htBo;
	}

	public void setHtBo(IDhBO htBo) {
		this.htBo = htBo;
	}

}
