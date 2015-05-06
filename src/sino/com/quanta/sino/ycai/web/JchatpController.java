package com.quanta.sino.ycai.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.DrVO;
import com.quanta.sino.ycai.vo.JchatpQE;

/**
 * <p>
 * 原板商品检查书通管理
 * </p>
 * <p>
 * create: 2010-12-21 下午06:09:06
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ycai/spjcs")
public class JchatpController {

	@Autowired
	private IYcaitpBO ycaitpBO;

	/**
	 * <p>
	 * 商品检查书主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(JchatpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (page.getJbnoEnd() == null || page.getJbnoEnd().isEmpty()) {
			page.setJbnoEnd(page.getJbnoBegin());
		}
		page.setOrderBys(" crea desc");
		ycaitpBO.queryJchatp(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ycai/spjcs/list"
				: "/sino/ycai/spjcs/index";
	}

	/**
	 * <p>
	 * 查看商品检查书详情
	 * </p>
	 * 
	 * @param jbno
	 *            原材卷板No.
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String jbno, Model model) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "原材卷板NO不能为空").toString();
		}
		return ycaitpBO.getJchatpForJs(jbno);
	}

	/**
	 * <p>
	 * 保存商品检查书详情
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(JchaTp entity, String jbno, String $type) {
		if ("modify".equals($type)) {
			return ycaitpBO.updateJchatp(entity);
		}
		return ycaitpBO.saveJchatp(entity);
	}

	/**
	 * <p>
	 * 根据制造商卷板NO，获得原材卷板信息
	 * </p>
	 * 
	 * @param zzsj
	 *            制造商卷板NO
	 * @return String
	 */
	@RequestMapping(value = "/getYcai", method = RequestMethod.POST)
	public @ResponseBody
	String getYcai(String zzsj, Model model) {
		YcaiTp ycaiTp = ycaitpBO.getByZzsj(zzsj);
		if (ycaiTp == null) {
			return new Result(-1, "制造商卷板NO" + zzsj + "在原材卷板中不存在").toString();
		}
		// 在商品检查书中检查
		if (ycaitpBO.isExistedJchatp(ycaiTp.getJbno())) {
			return new Result(-1, "制造商卷板NO" + zzsj + "已添加商品检查书表中").toString();
		}
		return "{ship:\"" + ycaiTp.getShip() + "\",jbno:\"" + ycaiTp.getJbno()
				+ "\"}";
	}

	/**
	 * <p>
	 * 删除商品检查书
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String id) {
		ycaitpBO.deleteJchatp(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除商品检查书（多个）
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String dels(String[] ids) {
		ycaitpBO.deleteJchatps(ids);
		return "删除成功";
	}

	/**
	 * <p>
	 * 进入原板商品检查书导入页面
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexDr")
	public String indexDr() {
		return "/sino/ycai/spjcs/indexDr";
	}

	/**
	 * <p>
	 * 原板商品检查书导入操作
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "dr", method = RequestMethod.POST)
	public @ResponseBody
	String dr(DrVO entity) {
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		return ycaitpBO.importJczms(entity);
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}
}
