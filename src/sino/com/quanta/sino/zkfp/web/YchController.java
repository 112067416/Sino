package com.quanta.sino.zkfp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 余材化功能
 * </p>
 * <p>
 * create: 2011-1-18 上午09:00:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/zkfp/ych")
public class YchController {

	@Autowired
	private IZpBO zpBO;

	@Autowired
	private IYchBO ychBO;

	/**
	 * <p>
	 * 制品分页查询
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			return "/sino/zkfp/ych/index";
		}
		String[] xpzks = { EXpzk.JZP.key, EXpzk.BZP.key };
		// 设置现品在库
		page.setXpzks(xpzks);
		// 设置堆场
		String[] duics = { DC.C.name, DC.F.name, DC.G.name };
		page.setDuics(duics);
		// 分配/余材标记
		page.setFpyc(EFpyc.FP.key);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		page.setStat(ZpStat.CS.stat);
		page.setOrderBys("jbno");
		zpBO.query(page);
		model.addAttribute("page", page);
		return "/sino/zkfp/ych/list";
	}

	/**
	 * <p>
	 * 对制品进行余材化处理
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	@RequestMapping(value = "/ych", method = RequestMethod.POST)
	public @ResponseBody
	String doYch(String ids) {
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "选择要做余材化处理的制品").toString();
		}
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		ychBO.doZpYch(ids.split(","), user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查看现品信息
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/viewZp", method = RequestMethod.POST)
	public String viewZp(String jbno, Model model) {
		if (jbno == null || jbno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "现品No.不能为空"));
			return Result.URL_ERROR;
		}
		ZpngTp zpngTp = zpBO.getZp(jbno);
		model.addAttribute("zpngTp", zpngTp);
		return "/sino/zkfp/ych/viewZp";
	}

	/**
	 * @return the zpBO
	 */
	public IZpBO getZpBO() {
		return zpBO;
	}

	/**
	 * @param zpBO
	 *            the zpBO to set
	 */
	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	/**
	 * @return the ychBO
	 */
	public IYchBO getYchBO() {
		return ychBO;
	}

	/**
	 * @param ychBO
	 *            the ychBO to set
	 */
	public void setYchBO(IYchBO ychBO) {
		this.ychBO = ychBO;
	}

}
