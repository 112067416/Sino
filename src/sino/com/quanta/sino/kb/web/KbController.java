package com.quanta.sino.kb.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.etl.vo.ZptjVO;
import com.quanta.sino.kb.bo.api.IKbBO;
import com.quanta.sino.kb.constants.KbConstants;

/**
 * <p>
 * 捆包生产web处理层操作
 * </p>
 * <p>
 * create: 2011-1-12 下午03:28:22
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/kb")
public class KbController {

	/**
	 * 
	 */
	@Autowired
	private IKbBO bo;

	/**
	 * 
	 */
	@Autowired
	private IZpBO zpBO;

	/**
	 * <p>
	 * 设定紧急材 列表显示调用方法
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexSdjjc!{duic}")
	public String indexSdjjc(@PathVariable String duic, ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getUserEnd() == null || page.getUserEnd().isEmpty()) {
			page.setUserEnd(page.getUserBegin());
		}
		if (page.getXphoE() == null) {
			page.setXphoE(page.getXphoS());
		}
		if (page.getXpknE() == null) {
			page.setXpknE(page.getXpknS());
		}
		if (page.getXpcnE() == null) {
			page.setXpcnE(page.getXpcnS());
		}
		// 默认删除状态为未删除
		page.setScbj(EScbj.CS.key);
		String[] chans = { ChanType.hg.key, ChanType.bl.key };
		page.setChans(chans);
		page.setOrderBys("sfjj desc,abbr asc,dhno asc,jbno asc");
		page.setDuic(duic);
		zpBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("duic", duic);
		return page.isQuery() ? "/sino/kb/listSdjjc" : "/sino/kb/indexSdjjc";

	}

	/**
	 * <p>
	 * 紧急材设定
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/saveJjcSd", method = RequestMethod.POST)
	public @ResponseBody
	String saveJjcSd(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "制品在库DB主键不能为空").toString();
		}
		bo.updateSfjj(ids, KbConstants.ISJJ_YES);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 紧急材取消
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/saveJjcQx", method = RequestMethod.POST)
	public @ResponseBody
	String saveJjcQx(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "制品在库DB主键不能为空").toString();
		}
		bo.updateSfjj(ids, KbConstants.ISJJ_NO);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 保存库位设定操作
	 * </p>
	 * 
	 * @param vo
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/saveKw", method = RequestMethod.POST)
	public @ResponseBody
	String saveKw(String kw, String[] ids) {
		bo.saveKw(kw, ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 待捆包制品检索
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/queryDkb!{duic}")
	public String queryDkb(@PathVariable String duic, ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		// 默认删除状态为未删除
		page.setScbj(EScbj.CS.key);
		page.setDuic(duic);
		String[] chans = { ChanType.hg.key, ChanType.bl.key };
		page.setChans(chans);
		page.setKbsded(true);
		page.setOrderBys("sfjj desc,abbr asc,dhno asc,jbno asc");
		zpBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("duic", duic);
		return page.isQuery() ? "/sino/kb/list" : "/sino/kb/index";

	}

	/**
	 * <p>
	 * 验证输入的制品号信息
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @param duic
	 * @return String
	 */
	@RequestMapping(value = "/getKb", method = RequestMethod.POST)
	public @ResponseBody
	String getKb(String jbno, String duic) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "输入的PILE No.不能为空值").toJsObject();
		}
		return bo.getKb(jbno, duic);
	}

	/**
	 * <p>
	 * 捆包实绩查询列表入口（包括清除操作）
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param duic
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexKbsj!{duic}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String indexKbsj(@PathVariable String duic, String ids, Model model) {
		User user = Context.currentUser();
		if (user == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "已超时，请重新登陆。"));
			return Result.ERROR;
		}
		model.addAttribute("group", user.getGroup());
		model.addAttribute("duic", duic);
		return "/sino/kb/indexKbsj";
	}

	/**
	 * <p>
	 * 确认捆包
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param bz
	 * @param duic
	 * @return String
	 */
	@RequestMapping(value = "/saveKbsj", method = RequestMethod.POST)
	public @ResponseBody
	String saveKbsj(String jbnos, String bz, String duic) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (jbnos == null || jbnos.length() == 0) {
			return new Result(-1, "制品信息主键不能为空").toString();
		}
		bo.saveKbsj(jbnos.split(","), bz, user, duic);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 捆包历史页面
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexKbls!{xpzk}")
	public String indexKbls(@PathVariable String xpzk, ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setXpzk(xpzk);
		page.setKbsded(false);
		Date rqE = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!page.isQuery()) {
			try {
				page.setKbsdBegin(sdf.parse(sdf.format(calendar.getTime())));
			}
			catch (ParseException e) {
			}
		}
		if (page.getKbsdEnd() == null) {
			page.setKbsdEnd(page.getKbsdBegin());
		}
		if (page.getKbsdEnd() != null) {
			calendar.setTime(page.getKbsdEnd());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			rqE = calendar.getTime();
		}
		ZptjVO zptjVO = zpBO.getKbtj(page.getKbsdBegin(), rqE, xpzk);
		page.setOrderBys("kbsd desc,jbno asc");
		zpBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("zptjVO", zptjVO);
		model.addAttribute("xpzk", xpzk);
		return page.isQuery() ? "/sino/kb/listKbls" : "/sino/kb/indexKbls";
	}

	/**
	 * <p>
	 * 捆包回退操作
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param xpzk
	 * @return String
	 */
	@RequestMapping(value = "/rollback", method = RequestMethod.POST)
	public @ResponseBody
	String rollback(String[] ids, String xpzk) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (ids == null || ids.length == 0) {
			return new Result(-1, "制品信息主键不能为空").toString();
		}
		bo.rollBack(ids, user, xpzk);
		return Result.SUCCESS;
	}

	public IKbBO getBo() {
		return bo;
	}

	public void setBo(IKbBO bo) {
		this.bo = bo;
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}
}
