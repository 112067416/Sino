package com.quanta.sino.ch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.ch.bo.api.IShdBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.ZxzsQE;
import com.quanta.sino.cmn.constants.ChStat;

/**
 * <p>
 * 送货单管理控制器
 * </p>
 * <p>
 * create: 2011-1-13 下午04:42:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ch/shd")
public class ShdController {

	/**
	 * 送货单业务接口
	 */
	@Autowired
	private IShdBO zBo;

	/**
	 * 装箱指示业务接口
	 */
	@Autowired
	private IZxzsBO zxBo;

	/**
	 * <p>
	 * 送货单查询
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
		page.setStat(ChStat.YFH.stat);
		page.setOrderBys("chri desc, zxno desc");
		zxBo.queryZxzs(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ch/shd/list" : "/sino/ch/shd/index";
	}

	/**
	 * <p>
	 * 删除送货单
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String dels(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做删除操作的送货单").toString();
		}
		zBo.dels(ids);
		return Result.SUCCESS;
	}

	public IShdBO getzBo() {
		return zBo;
	}

	public void setzBo(IShdBO zBo) {
		this.zBo = zBo;
	}

	public IZxzsBO getZxBo() {
		return zxBo;
	}

	public void setZxBo(IZxzsBO zxBo) {
		this.zxBo = zxBo;
	}

}
