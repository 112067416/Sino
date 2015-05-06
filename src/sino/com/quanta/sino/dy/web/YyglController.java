package com.quanta.sino.dy.web;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.bo.api.IJbddBO;
import com.quanta.sino.yygl.bo.api.IKhyfBO;
import com.quanta.sino.yygl.bo.api.ISczsBO;
import com.quanta.sino.yygl.vo.ChlldQE;
import com.quanta.sino.yygl.vo.JbddItemQE;
import com.quanta.sino.yygl.vo.SczsQE;
import com.quanta.sino.yygl.vo.ZrchsjQE;

/**
 * <p>
 * 营业管理模块打印web层类
 * </p>
 * <p>
 * create: 2011-1-11 上午09:30:08
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class YyglController {

	/**
	 * 基板订购单bo
	 */
	@Autowired
	private IJbddBO jbddBo;

	/**
	 * 次日出货联络单bo
	 */
	@Autowired
	private IChlldBO chlldBo;

	/**
	 * 客户运费业务接口
	 */
	@Autowired
	private IKhyfBO khyfBO;

	/**
	 * 生产指示单业务接口
	 */
	@Autowired
	private ISczsBO sczsBO;

	/**
	 * 用户管理业务接口
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * 装箱指示书业务接口
	 */
	@Autowired
	private IZxzsBO zxzsBO;

	/**
	 * <p>
	 * 基板订购单查询打印
	 * </p>
	 * 
	 * @param current
	 * @param idStr
	 * @param pages
	 * @param model
	 * @return String
	 */
	@RequestMapping("/jbdd!print")
	public String printJbdd(Integer current, String idStr, Integer pages,
			Model model) {
		JbddItemQE page = new JbddItemQE();
		page.setIdStr(idStr);
		page.setOrderBys("crea asc");
		jbddBo.queryItem(page);
		model.addAttribute("page", page);
		model.addAttribute("current", current);
		model.addAttribute("date", new Date());
		model.addAttribute("pages", pages);
		return "/sino/dy/jbdd!print";
	}

	/**
	 * <p>
	 * 次日出货联络单打印入口
	 * </p>
	 * 
	 * @param chqi
	 * @param ddno
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/chlld")
	public String chlld(Date chqi, String ddno, Model model) {
		ChlldQE page = new ChlldQE();
		page.setSize(10);
		page.setChqiBegin(chqi);
		page.setChqiEnd(chqi);
		if (ddno != null && !ddno.isEmpty()) {
			page.setDdno(ddno);
		}
		page.setOrderBys("user asc, ysgs asc, shno asc, addr asc, dhno asc, line asc");
		chlldBo.query(page);
		model.addAttribute("pageCount", page.getPageCount());
		model.addAttribute("chqi", chqi);
		model.addAttribute("ddno", ddno);
		return "/sino/dy/chlld";
	}

	/**
	 * <p>
	 * 次日出货联络单打印
	 * </p>
	 * 
	 * @param chqi
	 * @param ddno
	 * @param current
	 * @param model
	 * @return String
	 */
	@RequestMapping("/chlld!print")
	public String printChlld(Date chqi, String ddno, Integer current,
			Integer pageCount, Model model) {
		ChlldQE page = new ChlldQE();
		page.setSize(10);
		page.setIndex(current);
		page.setChqiBegin(chqi);
		page.setChqiEnd(chqi);
		if (ddno != null && !ddno.isEmpty()) {
			page.setDdno(ddno);
		}
		page.setOrderBys("user asc, ysgs asc, shno asc, addr asc, dhno asc, line asc");
		chlldBo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("chqi", chqi);
		model.addAttribute("current", current);
		model.addAttribute("pageCount", pageCount);
		return "/sino/dy/chlld!print";
	}

	/**
	 * <p>
	 * 生产指示单打印入口
	 * </p>
	 * 
	 * @param crea
	 * @param ddno
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sczs")
	public String sczs(Date crea, String ddno, Model model) {
		SczsQE page = new SczsQE();
		page.setSize(10);
		page.setCreaBegin(crea);
		page.setCreaEnd(crea);
		if (ddno != null && !ddno.isEmpty()) {
			page.setDdno(ddno);
		}
		page.setOrderBys("crea desc, jhqi desc, dhno asc, line asc");
		sczsBO.query(page);
		model.addAttribute("pageCount", page.getPageCount());
		model.addAttribute("crea", crea);
		model.addAttribute("ddno", ddno);
		return "/sino/dy/sczs";
	}

	/**
	 * <p>
	 * 生产指示单打印
	 * </p>
	 * 
	 * @param crea
	 * @param ddno
	 * @param current
	 * @param model
	 * @return String
	 */
	@RequestMapping("/sczs!print")
	public String printSczs(Date crea, String ddno, Integer current,
			Integer pageCount, Model model) {
		SczsQE page = new SczsQE();
		page.setSize(10);
		page.setCreaBegin(crea);
		page.setCreaEnd(crea);
		if (ddno != null && !ddno.isEmpty()) {
			page.setDdno(ddno);
		}
		page.setOrderBys("crea desc, jhqi desc, dhno asc, line asc");
		sczsBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("crea", crea);
		model.addAttribute("current", current);
		model.addAttribute("pageCount", pageCount);
		return "/sino/dy/sczs!print";
	}

	/**
	 * <p>
	 * 出货实绩单打印入口
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @param ysgs
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/chsjd")
	public String chsjd(Date chriS, Date chriE, String ysgs, Model model) {
		ZrchsjQE page = new ZrchsjQE();
		page.setSize(10);
		page.setChriS(chriS);
		if (chriE == null) {
			page.setChriE(chriS);
		}
		else {
			page.setChriE(chriE);
		}
		page.setYsgs(ysgs);
		page.setChzl(0.0);
		page.setOrderBys("CHRI_ asc, NAME_ asc, YSNM_ asc, SHNM_ asc, ADDR_ asc, DHNO_ asc, LINE_ asc");
		khyfBO.query(page);
		model.addAttribute("pageCount", page.getPageCount());
		model.addAttribute("chriS", chriS);
		model.addAttribute("chriE", chriE);
		model.addAttribute("ysgs", ysgs);
		return "/sino/dy/chsjd";
	}

	/**
	 * <p>
	 * 出货实绩单打印
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @param current
	 * @param model
	 * @return String
	 */
	@RequestMapping("/chsjd!print")
	public String printChsjd(Date chriS, Date chriE, Integer current,
			Integer pageCount, String ysgs, Model model) {
		ZrchsjQE page = new ZrchsjQE();
		page.setSize(10);
		page.setIndex(current);
		page.setChriS(chriS);
		if (chriE == null) {
			page.setChriE(chriS);
		}
		else {
			page.setChriE(chriE);
		}
		page.setYsgs(ysgs);
		page.setChzl(0.0);
		page.setOrderBys("CHRI_ asc, NAME_ asc, YSNM_ asc, SHNM_ asc, ADDR_ asc, DHNO_ asc, LINE_ asc");
		khyfBO.query(page);
		if (current + 1 == pageCount) {
			// 统计件数和重量
			ChtjVO chtjVO = khyfBO.tj(page.getChriS(), page.getChriE(), page.getYsgs());
			model.addAttribute("chtjVO", chtjVO);
		}
		model.addAttribute("chriS", chriS);
		model.addAttribute("chriE", chriE);
		model.addAttribute("page", page);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("current", current);
		return "/sino/dy/chsjd!print";
	}

	/**
	 * <p>
	 * 客户管理表打印
	 * </p>
	 * 
	 * @param user
	 * @param model
	 * @return String
	 */
	@RequestMapping("/yong!print")
	public String printYong(String user, Model model) {
		YongMp entity = yongBO.get(user);
		model.addAttribute("entity", entity);
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		model.addAttribute("crea", date);
		int year = calendar.get(Calendar.YEAR);
		model.addAttribute("year", year);
		StringBuilder title = new StringBuilder();
		title.append("SJM").append(year).append("年").append(entity.getFxsl() != null
				&& entity.getFxsl() > 0 ? "" : "上半年").append("总销售量").append(entity.getAxsl() != null ? entity.getAxsl()
				: 0).append("T");
		model.addAttribute("title", title.toString());
		model.addAttribute("khchtjVO", zxzsBO.getKhchtj(date, user));
		return "/sino/dy/yong!print";
	}

	public IJbddBO getJbddBo() {
		return jbddBo;
	}

	public void setJbddBo(IJbddBO jbddBo) {
		this.jbddBo = jbddBo;
	}

	public IChlldBO getChlldBo() {
		return chlldBo;
	}

	public void setChlldBo(IChlldBO chlldBo) {
		this.chlldBo = chlldBo;
	}

	public IKhyfBO getKhyfBO() {
		return khyfBO;
	}

	public void setKhyfBO(IKhyfBO khyfBO) {
		this.khyfBO = khyfBO;
	}

	public ISczsBO getSczsBO() {
		return sczsBO;
	}

	public void setSczsBO(ISczsBO sczsBO) {
		this.sczsBO = sczsBO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

	public IZxzsBO getZxzsBO() {
		return zxzsBO;
	}

	public void setZxzsBO(IZxzsBO zxzsBO) {
		this.zxzsBO = zxzsBO;
	}
}
