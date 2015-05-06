package com.quanta.sino.dy.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.coco.core.exception.CocoException;
import com.coco.core.util.MoneyFormat;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.vo.DhQE;
import com.quanta.sino.dh.vo.GxhtMX;
import com.quanta.sino.orm.DhuoTp;

/**
 * <p>
 * 订货打印
 * </p>
 * <p>
 * create: 2011-3-24 下午05:22:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class DhdyController {

	/**
	 * 订货合同业务接口
	 */
	@Autowired
	private IDhBO bo;

	/**
	 * 每面打印的生产卷条数
	 */
	private static int pageSize = 10;

	/**
	 * 
	 */
	private static String SPTE = "SPTE-";

	/**
	 * <p>
	 * 订货确认表打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/dhqrb")
	public String dhqrb(String[] ids, Model model) {
		model.addAttribute("ids", StringUtils.join(ids, ","));
		return "/sino/dy/dhqrb";
	}

	/**
	 * <p>
	 * 订货确认表打印
	 * </p>
	 * 
	 * @param ids
	 *            订货合同号+行号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/dhqrb!print")
	public String printDhqrb(String ids, Model model) {
		if (ids == null || ids.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "请选择要打印的订货合同"));
			return Result.URL_ERROR;
		}
		List<DhuoTp> dhuoTps = bo.findDhqrPrints(ids.split(","));
		if (dhuoTps.size() > 0) {
			model.addAttribute("list", dhuoTps);
			// 传递第一条（主要是获取字段内容相同的信息）
			model.addAttribute("tp", dhuoTps.get(0));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			// 传递默认的当前时间为发行日
			model.addAttribute("fxr", sf.format(new Date()));
		}
		return "/sino/dy/dhqrb!print";
	}

	/**
	 * <p>
	 * 仕样未确认打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sywqr")
	public String sywqr(String ids, Model model) {
		if (ids != null && !ids.isEmpty()) {
			model.addAttribute("ids", ids);
		}
		return "/sino/dy/sywqr";
	}

	/**
	 * <p>
	 * 仕样未确认打印
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sywqr!print")
	public String printSywqr(String ids, Integer pages, Integer current,
			Model model) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.isEmpty()) {
			throw new CocoException(-1, "请选择要打印的订货合同");
		}
		model.addAttribute("list", bo.findSyPrints(ids.split(","), user));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("date", sf.format(new Date()));
		model.addAttribute("pages", pages);
		model.addAttribute("current", current);
		return "/sino/dy/sywqr!print";
	}

	/**
	 * <p>
	 * 工矿产品购销合同打印入口
	 * </p>
	 * 
	 * @param dhno
	 * @return String
	 */
	@RequestMapping(value = "/gxht", method = RequestMethod.POST)
	public @ResponseBody
	String gxht(String dhno) {
		int $pageSize = bo.getPageSize(dhno, pageSize);
		if ($pageSize <= 0) {
			return new Result(-1, "购销合同" + dhno + "不存在").toString();
		}
		if (!bo.isExistedGxht(dhno)) {
			return new Result(-1, "还未制作购销合同" + dhno + ",请先制作").toString();
		}
		return "{pageSizes:\"" + $pageSize + "\"}";
	}

	/**
	 * <p>
	 * 工矿产品购销合同打印
	 * </p>
	 * 
	 * @param qentity
	 * @param pageSizes
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/gxht!print")
	public String printGxht(DhQE qentity, Integer pageSizes, Model model) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		// DhQE qentity = new DhQE();
		// qentity.setDhno(dhno);
		qentity.setOrderBys("line asc");
		qentity.setSize(pageSize);
		bo.query(qentity);
		List<DhuoTp> entities = qentity.getDatas();
		List<GxhtMX> vos = new ArrayList<GxhtMX>();
		GxhtMX vo = null;
		String pzno = null, ggnm = null, kbsz = null;
		Code118 code118 = null;
		double htzl = 0d;
		double htje = 0d;

		double hjzl = 0d;
		double hjje = 0d;
		for (DhuoTp dhuoTp : entities) {
			vo = new GxhtMX();
			vo.setLine(Integer.valueOf(dhuoTp.getLine()));
			vo.setJhqi(dhuoTp.getJhqi());
			pzno = dhuoTp.getPzno();
			if (pzno != null && !pzno.isEmpty()
					&& (code118 = Code118.get(pzno.substring(0, 1))) != null) {
				vo.setPzno(code118.name);
			}
			ggnm = dhuoTp.getGgnm();
			if (ggnm != null && !ggnm.isEmpty()) {
				vo.setGgnm(ggnm.replaceFirst(SPTE, ""));
			}
			htzl = dhuoTp.getHtzl() != null ? dhuoTp.getHtzl() : 0d;
			htje = dhuoTp.getHtje() != null ? dhuoTp.getHtje() : 0d;
			vo.setFace(dhuoTp.getFace());
			vo.setFudw(dhuoTp.getFudw());
			vo.setFuzm(dhuoTp.getFuzm().replaceAll("^0", ""));
			vo.setFufm(dhuoTp.getFufm().replaceAll("^0", ""));
			vo.setHouu(dhuoTp.getHouu());
			vo.setKuan(dhuoTp.getKuan());
			vo.setCang(dhuoTp.getCang());
			kbsz = dhuoTp.getKbsz();
			if (kbsz != null && !kbsz.isEmpty()) {
				vo.setKbsz(Integer.valueOf(kbsz));
			}
			vo.setNeij(dhuoTp.getNeij());
			vo.setHtzl(htzl);
			vo.setHtdj(dhuoTp.getHtdj());
			vo.setHtje(htje);

			hjzl += htzl;
			hjje += htje;

			vos.add(vo);
		}
		model.addAttribute("list", vos);
		DhuoTp entity = entities.size() > 0 ? entities.get(0) : null;
		model.addAttribute("entity", entity);
		model.addAttribute("pageSizes", pageSizes);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("current", qentity.getIndex() + 1);
		hjzl = NumberUtils.format(hjzl, 3);
		model.addAttribute("hjzl", hjzl);
		hjje = NumberUtils.format(hjje, 2);
		model.addAttribute("hjje", hjje);
		model.addAttribute("cHjje", new MoneyFormat().format(hjje));
		model.addAttribute("gxhtTp", bo.getGxht(qentity.getDhno()));
		return "/sino/dy/gxht!print";
	}
}
