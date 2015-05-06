package com.quanta.sino.ycai.web;

import java.text.SimpleDateFormat;
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
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.orm.Seq;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.orm.WwhtTpPk;
import com.quanta.sino.orm.Ybcbmx;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.Ycaicb;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.constants.OperateType;
import com.quanta.sino.ycai.vo.DrVO;
import com.quanta.sino.ycai.vo.YcaiQE;

/**
 * <p>
 * 原板管理控制器
 * </p>
 * <p>
 * create: 2010-12-21 下午06:29:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ycai")
public class YcaitpController {

	/**
	 * 原材卷板业务接口
	 */
	@Autowired
	private IYcaitpBO bo;

	/**
	 * 码表业务接口
	 */
	@Autowired
	private ICodeBO codeBO;

	/**
	 * 公共接口
	 */
	@Autowired
	private ICmnBO cmnBO;

	/**
	 * 
	 */
	@Autowired
	private ISeqBO seqBO;

	/**
	 * 
	 */
	private static final String KEY = "1";

	/**
	 * <p>
	 * 原板维护页面
	 * </p>
	 * 
	 * @param type
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index!{type}")
	public String index(@PathVariable String type, YcaiQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		// String[] scbjs = { EScbj.CS.key, EScbj.YSJZL.key, };
		// page.setScbjs(scbjs);
		page.setOrderBys("jbno desc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		OperateType oType = OperateType.get(type);
		model.addAttribute("title", oType != null ? oType.name : null);
		return page.isQuery() ? "/sino/ycai/list" : "/sino/ycai/index";
	}

	/**
	 * <p>
	 * 获取原板信息
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/getForUpdate")
	public String getForUpdate(String jbno, Model model) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "主键不能为空").toString();
		}
		model.addAttribute("ycaiTp", bo.getForUpdate(jbno));
		return "/sino/ycai/view";
	}

	/**
	 * <p>
	 * 获取原板信息
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/getYc")
	public String getYc(String jbno, Model model) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "主键不能为空").toString();
		}
		model.addAttribute("ycaiTp", bo.getForUpdate(jbno));
		return "/sino/ycai/viewYc";
	}

	/**
	 * <p>
	 * 修改原板信息
	 * </p>
	 * 
	 * @param entity
	 * @return Sting
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(YcaiTp entity) {
		if (entity.getZzsj() == null || entity.getZzsj().isEmpty()) {
			return new Result(-1, "制造商卷板No不能为空").toString();
		}
		if (entity.getJbno() == null || entity.getJbno().isEmpty()) {
			return new Result(-1, "原材卷板No不能为空").toString();
		}
		bo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除原板信息（多个）
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		bo.deletes(ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 原板登录
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexYbdl")
	public String toYbdl(Model model) {
		Seq seq = seqBO.get(com.quanta.sino.cmn.constants.Seq.ybno.name);
		long maxNo = -1;
		if (seq != null) {
			try {
				maxNo = seq.getNumber();
			}
			catch (NumberFormatException e) {
			}
		}
		if (maxNo < 0) {
			maxNo = 1;
		}
		else {
			maxNo += 1;
		}
		model.addAttribute("jbno", String.format("%06d", maxNo));
		return "/sino/ycai/indexYbdl";
	}

	/**
	 * <p>
	 * 获取供应商合同
	 * </p>
	 * 
	 * @param htno
	 * @param line
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getWwht", method = RequestMethod.POST)
	public @ResponseBody
	String getWwht(String htno, String line, Model model) {
		if (htno == null || htno.isEmpty() || line == null || line.isEmpty()) {
			return new Result(-1, "供应商合同No.不能为空").toString();
		}
		return bo.getForJs(new WwhtTpPk(htno, line));
	}

	/**
	 * <p>
	 * 计算原板长
	 * </p>
	 * 
	 * @param kuan
	 * @param hou
	 * @param zl
	 * @return String
	 */
	@RequestMapping(value = "/getJbcn", method = RequestMethod.POST)
	public @ResponseBody
	String getJbcn(Double kuan, Double hou, Double zl) {
		if (kuan == null || kuan.doubleValue() == 0 || hou == null
				|| hou.doubleValue() == 0 || zl == null
				|| zl.doubleValue() == 0) {
			return new Result(-1, "缺少参数").toString();
		}
		int jbcn = SinoUtils.calculate(kuan, hou, zl);
		return new Result(1, String.valueOf(jbcn)).toString();
	}

	/**
	 * <p>
	 * 保存原板信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(YcaiTp entity) {
		if (entity.getZzsj() == null || entity.getZzsj().isEmpty()) {
			return new Result(-1, "制造商卷板No不能为空").toString();
		}
		bo.save(entity);
		return new Result(1, cmnBO.generateYcaiTpNo()).toString();
	}

	/**
	 * <p>
	 * 进入原板导入页面
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexYbdr")
	public String indexYbdr(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dt = sdf.format(date);
		// 默认装船时间为当前时间
		model.addAttribute("blny", dt);
		sdf = new SimpleDateFormat("yyMM");
		model.addAttribute("prefix", sdf.format(date));
		return "/sino/ycai/indexYbdr";
	}

	/**
	 * <p>
	 * 导入操作
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "ybdr", method = RequestMethod.POST)
	public @ResponseBody
	String ybdr(DrVO entity) {
		if (entity.getShip() == null || entity.getShip().isEmpty()) {
			return new Result(-1, "船名不能为空").toString();
		}
		if (entity.getBlny() == null) {
			return new Result(-1, "装船时间不能为空").toString();
		}
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		return bo.importYbqd(entity);
	}

	/**
	 * <p>
	 * 进入原板入库页面
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexYbrk")
	public String indexYbrk(Model model) {
		return "/sino/ycai/indexYbrk";
	}

	/**
	 * <p>
	 * 原板入库操作
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "ybrk", method = RequestMethod.POST)
	public @ResponseBody
	String ybrk(DrVO entity) {
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		return bo.importYbrk(entity);
	}

	/**
	 * <p>
	 * 判断船名是否存在
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ship
	 * @return String
	 */
	@RequestMapping(value = "/checkShip", method = RequestMethod.POST)
	public @ResponseBody
	String checkShip(String ship) {
		if (ship == null || ship.isEmpty()) {
			return new Result(-1, "船名为空").toString();
		}
		if (bo.isExistedShip(ship)) {
			return new Result(-1, "该船名已存在").toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入设置入库时间页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/indexSzsj")
	public String toSzsj() {
		return "/sino/ycai/indexSzsj";
	}

	/**
	 * <p>
	 * 设置原板入库时间
	 * </p>
	 * 
	 * @param ship
	 * @param date
	 * @return String
	 */
	@RequestMapping(value = "/setRksj", method = RequestMethod.POST)
	public @ResponseBody
	String setRksj(String ship, Date date) {
		if (ship == null || ship.isEmpty() || date == null) {
			return new Result(-1, "参数为空").toString();
		}
		bo.setRksj(ship, date);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入原板成本费用页面
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexYbcb")
	public String indexYbcb(Model model) {
		CodeItem codeItem = codeBO.getCodeItem(CmnConstants.CODE_BFHL, KEY);
		if (codeItem == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "码表中未配置保费汇率"));
			return Result.URL_BLANK;
		}
		// 保费汇率
		Double bfhl = null;
		try {
			bfhl = Double.valueOf(codeItem.getValue());
		}
		catch (Exception e) {
		}
		codeItem = codeBO.getCodeItem(CmnConstants.CODE_MYHL, KEY);
		if (codeItem == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "码表中未配置美元汇率"));
			return Result.URL_ERROR;
		}
		// 美元汇率
		Double myhl = null;
		try {
			myhl = Double.valueOf(codeItem.getValue());
		}
		catch (Exception e) {
		}
		codeItem = codeBO.getCodeItem(CmnConstants.CODE_GSSL, KEY);
		if (codeItem == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "码表中未配置关税税率"));
			return Result.URL_ERROR;
		}
		model.addAttribute("bfhl", bfhl);
		model.addAttribute("myhl", myhl);
		return "/sino/ycai/indexYbcb";
	}

	/**
	 * <p>
	 * 加载原板成本费用
	 * </p>
	 * 
	 * @param ship
	 * @return String
	 */
	@RequestMapping(value = "/loadYbcb", method = RequestMethod.POST)
	public @ResponseBody
	String loadYbcb(String ship) {
		return bo.loadYbcb(ship);
	}

	/**
	 * <p>
	 * 计算原板成本费用
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/saveYbcb", method = RequestMethod.POST)
	public @ResponseBody
	String saveYbcb(Ybcbmx vo, String flag) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登录系统").toString();
		}
		if (vo.getGzsl() == null || vo.getGzsl().doubleValue() <= 0) {
			return new Result(-1, "港杂税率为空").toString();
		}
		if (vo.getYssl() == null || vo.getYssl().doubleValue() <= 0) {
			return new Result(-1, "运输税率为空").toString();
		}
		if (vo.getBfhl() == null || vo.getBfhl().doubleValue() <= 0) {
			return new Result(-1, "保费汇率为空").toString();
		}
		if (vo.getMyhl() == null || vo.getMyhl().doubleValue() <= 0) {
			return new Result(-1, "美元汇率为空").toString();
		}
		if (vo.getHgjj() != null && vo.getHgjj() > 0 && vo.getHgbj() != null
				&& vo.getHgbj() > 0) {
			return new Result(-1, "海关加价和海关基价只能存在一个").toString();
		}
		// if (vo.getGssl() == null || vo.getGssl().doubleValue() <= 0) {
		// return new Result(-1, "关税税率为空").toString();
		// }
		return bo.saveYbcb(vo, flag, user);
	}

	/**
	 * <p>
	 * 修改海关加价
	 * </p>
	 * 
	 * @param jbno
	 * @param hgjj
	 * @return String
	 */
	@RequestMapping(value = "/updateHgjj", method = RequestMethod.POST)
	public @ResponseBody
	String updateHgjj(String jbno, Double hgjj) {
		return bo.updateHgjj(jbno, hgjj);
	}

	/**
	 * <p>
	 * 加载海关加价
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/loadHgjj", method = RequestMethod.POST)
	public @ResponseBody
	String loadHgjj(String jbno) {
		Ycaicb entity = bo.getYbcb(jbno);
		if (entity == null) {
			return new Result(-1, "该原板还未设置原板成本费用,请先通过原板成本管理模块设置其原板成本费用。").toString();
		}
		return "{hgjj:" + entity.getHgjj() + "}";
	}

	/**
	 * @return the bo
	 */
	public IYcaitpBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IYcaitpBO bo) {
		this.bo = bo;
	}

	/**
	 * @return the codeBO
	 */
	public ICodeBO getCodeBO() {
		return codeBO;
	}

	/**
	 * @param codeBO
	 *            the codeBO to set
	 */
	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}

	public ISeqBO getSeqBO() {
		return seqBO;
	}

	public void setSeqBO(ISeqBO seqBO) {
		this.seqBO = seqBO;
	}

}
