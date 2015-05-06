package com.quanta.sino.yygl.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChlldStat;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.vo.ChlldQE;
import com.quanta.sino.yygl.vo.ChlldVO;

/**
 * <p>
 * 次日出货联络单控制器
 * </p>
 * <p>
 * create: 2010-12-20 上午11:29:00
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yygl/chlld")
public class ChlldController {

	/**
	 * 次日出货联络单业务接口
	 */
	@Autowired
	private IChlldBO bo;

	/**
	 * 用户业务接口
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * <p>
	 * 保存 次日出货联络单之前，验证次日出货联络单信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getCheck", method = RequestMethod.POST)
	public @ResponseBody
	String getCheck(Chlld entity) {
		return bo.getCheck(entity);
	}

	/**
	 * <p>
	 * 进入次日出货联络单登录页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ChlldQE page, String dhnoAndLine, Model model) {
		page.setSize(-1);
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		page.setDdno(user.getNo());
		if (!page.isQuery()) {
			Calendar calendar = Calendar.getInstance();
			// 获取当前时间的后一天
			calendar.add(Calendar.DATE, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
				model.addAttribute("chqi", date);
			}
			catch (ParseException e) {
			}
			page.setChqiBegin(date);
		}
		page.setChqiEnd(page.getChqiBegin());
		page.setOrderBys("user asc, dhno asc, line asc, crea desc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", ChlldStat.YS.key);

		if (dhnoAndLine != null && !dhnoAndLine.isEmpty()) {
			String[] arrs = dhnoAndLine.split("-");
			model.addAttribute("dhno", arrs[0]);
			model.addAttribute("line", arrs.length > 1 ? arrs[1] : null);
		}
		return page.isQuery() ? "/sino/yygl/chlld/list"
				: "/sino/yygl/chlld/index";
	}

	/**
	 * <p>
	 * 次日出货联络单查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexChlld")
	public String indexChlld(ChlldQE page, Model model) {
		// 设置页面为负数 表示在一个页面中显示 不需要分页
		page.setSize(-1);
		if (!page.isQuery()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			// 获取当前时间的后一天
			calendar.add(Calendar.DATE, 1);
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
				model.addAttribute("chqiBegin", date);
			}
			catch (ParseException e) {
			}
			page.setChqiBegin(date);
		}
		page.setChqiEnd(page.getChqiBegin());
		page.setDhnoNLike("W");
		page.setOrderBys("user asc, ysgs asc, shno asc, addr asc, dhno asc, line asc");
		bo.query(page);
		if (page.getDatas().size() > 0) {
			Chlld chlld = page.getDatas().get(0);
			// 设置天气
			model.addAttribute("weather", chlld.getWeather());
		}
		ChlldQE $page = new ChlldQE();
		$page.setChqiBegin(page.getChqiBegin());
		$page.setChqiEnd(page.getChqiEnd());
		$page.setDhnoLike("W");
		$page.setOrderBys(page.getOrderBys());
		bo.query($page);
		page.getDatas().addAll($page.getDatas());

		model.addAttribute("page", page);
		model.addAttribute("fixed", ChlldStat.YS.key);
		return page.isQuery() ? "/sino/yygl/chlld/listChlld"
				: "/sino/yygl/chlld/indexChlld";
	}

	/**
	 * <p>
	 * 通过用户代码及订货no获取订货DB的信息
	 * </p>
	 * 
	 * @param dhno
	 *            订货No
	 * @param line
	 *            行号
	 * @return String
	 */
	@RequestMapping(value = "/getDhInfo", method = RequestMethod.POST)
	public @ResponseBody
	String getDhInfo(String dhno, String line) {
		if (dhno == null || dhno.isEmpty()) {
			return new Result(-1, "订货合同号不能为空!").toString();
		}
		return bo.getDhInfo(dhno, line);

	}

	/**
	 * <p>
	 * 保存次日出货联络单
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Chlld entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (entity.getId() == null || entity.getId().isEmpty()) {
			bo.save(entity, user);
		}
		else {
			Chlld chlld = bo.get(entity.getId());
			if (chlld == null) {
				return new Result(-1, "次日出货联络单不存在").toString();
			}
			if (!ChlldStat.WS.key.equals(chlld.getStat())) {
				return new Result(-1, "该次日出货联络单状态为"
						+ ChlldStat.get(chlld.getStat()).value + ",不能再做修改操作").toString();
			}
			parseChlld(chlld, entity);
			bo.update(chlld);
		}
		return Result.SUCCESS;
	}

	//
	private void parseChlld(Chlld chlld, Chlld entity) {
		chlld.setChqi(entity.getChqi());
		chlld.setUser(entity.getUser());
		chlld.setName(entity.getName());
		chlld.setAbbr(entity.getAbbr());
		chlld.setDhno(entity.getDhno());
		chlld.setLine(entity.getLine());
		chlld.setChzl(entity.getChzl());
		chlld.setHouu(entity.getHouu());
		chlld.setKuan(entity.getKuan());
		chlld.setCang(entity.getCang());
		chlld.setAddr(entity.getAddr());
		chlld.setYsfs(entity.getYsfs());
		chlld.setYsfm(entity.getYsfm());
		chlld.setBhzk(entity.getBhzk());
	}

	/**
	 * <p>
	 * 加载次日出货联络单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "次日出货联络单主键不能为空").toString();
		}
		return bo.getForJs(id);
	}

	/**
	 * <p>
	 * 进入修改次日出货联络单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(String id, Model model) {
		Chlld chlld = bo.get(id);
		StringBuilder shdz = new StringBuilder();
		if (chlld != null) {
			List<YongShdz> yongShdzs = yongBO.findShdz(chlld.getUser());
			if (yongShdzs.size() > 0) {
				for (YongShdz yongShdz : yongShdzs) {
					if (shdz.length() > 0) {
						shdz.append(",'").append(yongShdz.getAddr()).append("':'").append(yongShdz.getAddr()).append("'");
						continue;
					}
					shdz.append("'").append(yongShdz.getAddr()).append("':'").append(yongShdz.getAddr()).append("'");
				}
			}
		}
		model.addAttribute("shdz", shdz.toString());
		model.addAttribute("entity", chlld);
		return "/sino/yygl/chlld/updateChlld";
	}

	/**
	 * <p>
	 * 修改次日出货联络单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public @ResponseBody
	String doUpdate(Chlld chlld) {
		Chlld entity = bo.get(chlld.getId());
		if (entity == null) {
			return new Result(-1, "次日出货联络单不存在").toString();
		}
		if (!ChlldStat.WS.key.equals(entity.getStat())) {
			return new Result(-1, "该次日出货联络单状态为"
					+ ChlldStat.get(entity.getStat()).value + ",不能再做修改操作").toString();
		}
		entity.setChqi(chlld.getChqi());
		entity.setChzl(chlld.getChzl());
		entity.setYsfs(chlld.getYsfs());
		entity.setYsfm(chlld.getYsfm());
		entity.setBhzk(chlld.getBhzk());
		entity.setAddr(chlld.getAddr());
		bo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除次日出货联络单
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "次日出货联络单主键不能为空").toString();
		}
		return bo.delete(ids);
	}

	/**
	 * <p>
	 * 次日出货联络单上锁
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	public @ResponseBody
	String lock(String[] ids) {
		Chlld chlld = null;
		for (String id : ids) {
			chlld = bo.get(id);
			if (!ChlldStat.WS.key.equals(chlld.getStat())) {
				return new Result(-1, "只能对状态为未锁的记录进行上锁操作").toString();
			}
		}
		bo.updateStat(ids, ChlldStat.YS.key);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 次日出货联络单解锁
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/deLock", method = RequestMethod.POST)
	public @ResponseBody
	String deLock(String[] ids) {
		Chlld chlld = null;
		for (String id : ids) {
			chlld = bo.get(id);
			if (!ChlldStat.YS.key.equals(chlld.getStat())) {
				return new Result(-1, "只能对状态为已锁的记录进行解锁操作").toString();
			}
		}
		bo.updateStat(ids, ChlldStat.WS.key);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入设置次日出货联络单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toSetting")
	public String toSetting(String id, Model model) {
		Chlld chlld = bo.get(id);
		model.addAttribute("entity", chlld);
		return "/sino/yygl/chlld/settingChlld";
	}

	/**
	 * <p>
	 * 保存联络单设置内容信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doSetting", method = RequestMethod.POST)
	public @ResponseBody
	String doSetting(ChlldVO vo, String id) {
		Chlld entity = bo.get(id);
		if (entity == null) {
			return new Result(-1, "次日出货联络单不存在").toString();
		}
		// dao.executeForValues(ql, Arrays.asList(vo.getIds()), vo.getYsgs(),
		// vo.getYsnm(), vo.getShno(), vo.getShnm(), vo.getBhzk(),
		// vo.getChzl());
		entity.setYsgs(vo.getYsgs());
		entity.setYsnm(vo.getYsnm());
		entity.setShno(vo.getShno());
		entity.setShnm(vo.getShnm());
		entity.setBhzk(vo.getBhzk());
		entity.setChzl(vo.getChzl());
		entity.setChqi(vo.getChqi());
		bo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入分解次日出货联络单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toDivide")
	public String toDivide(String id, Model model) {
		Chlld chlld = bo.get(id);
		model.addAttribute("entity", chlld);
		return "/sino/yygl/chlld/divideChlld";
	}

	/**
	 * <p>
	 * 次日出货联络单分解保存
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doDivide", method = RequestMethod.POST)
	public @ResponseBody
	String doDivide(Chlld entity) {
		return bo.doDivide(entity);
	}

	/**
	 * <p>
	 * 发送次日出货联络单上锁
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public @ResponseBody
	String send(String[] ids) {
		Chlld chlld = null;
		ChlldStat cStat = null;
		for (String id : ids) {
			chlld = bo.get(id);
			cStat = ChlldStat.get(chlld.getStat());
			if (cStat != null
					&& !(ChlldStat.WS.key.equals(cStat.key) || ChlldStat.YS.key.equals(cStat.key))) {
				return new Result(-1, "不能对状态为" + cStat.value + "的记录进行发送操作").toString();
			}
		}
		bo.updateStat(ids, ChlldStat.YS.key);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 设置天气
	 * </p>
	 * 
	 * @param chqi
	 *            出货日期
	 * @param weather
	 *            天气
	 * @return String
	 */
	@RequestMapping(value = "/setWeather", method = RequestMethod.POST)
	public @ResponseBody
	String setWeather(Date chqi, String weather) {
		bo.setWeather(chqi, weather);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得天气
	 * </p>
	 * 
	 * @param chqi
	 *            出货日期
	 * @return String
	 */
	@RequestMapping(value = "/getWeather", method = RequestMethod.POST)
	public @ResponseBody
	String getWeather(Date chqi) {
		String weather = bo.getWeather(chqi);
		weather = weather != null && !weather.isEmpty() ? weather : "";
		return new Result(1, weather).toString();
	}

	public IChlldBO getBo() {
		return bo;
	}

	public void setBo(IChlldBO bo) {
		this.bo = bo;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

}
