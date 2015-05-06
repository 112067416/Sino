package com.quanta.sino.dh.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.IPersonBO;
import com.coco.sys.orm.Person;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.Dybs;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhConstants;
import com.quanta.sino.dh.constants.DhHc;
import com.quanta.sino.dh.constants.DhYy;
import com.quanta.sino.dh.vo.DefaultDhVO;
import com.quanta.sino.dh.vo.DhQE;
import com.quanta.sino.dh.vo.DhjdQE;
import com.quanta.sino.dh.vo.DhjdVO;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.FspHtVO;
import com.quanta.sino.dh.vo.FspQE;
import com.quanta.sino.dh.vo.GxhtQE;
import com.quanta.sino.dh.vo.JstjVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.GxhtTp;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;

/**
 * <p>
 * 订货管理
 * </p>
 * <p>
 * create: 2011-1-3 下午03:45:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dh")
public class DhController {

	/**
	 * 订货管理业务接口
	 */
	@Autowired
	private IDhBO bo;

	/**
	 * 员工业务接口
	 */
	@Autowired
	private IPersonBO personBO;

	/**
	 * 用户主文件业务接口
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * 
	 */
	@Autowired
	private ICodeBO codeBO;

	/**
	 * <p>
	 * 修改客户订货单号
	 * </p>
	 * 
	 * @param ids
	 * @param khno
	 * @return String
	 */
	@RequestMapping(value = "/updateKhno", method = RequestMethod.POST)
	public @ResponseBody
	String updateKhno(String[] ids, String khno) {
		bo.updateKhno(ids, khno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 修改合同日
	 * </p>
	 * 
	 * @param ids
	 * @param htqi
	 * @return String
	 */
	@RequestMapping(value = "/updateHtqi", method = RequestMethod.POST)
	public @ResponseBody
	String updateHtqi(String[] ids, String htqi) {
		bo.doHtqi(ids, htqi);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入制作购销合同页面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexGxht")
	public String indexGxht(GxhtQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("year_ desc, user_ asc, month_ desc");
		bo.queryGxht(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listGxht" : "/sino/dh/indexGxht";
	}

	/**
	 * <p>
	 * 保存购销合同信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveGxht", method = RequestMethod.POST)
	public @ResponseBody
	String saveGxht(GxhtTp entity) {
		bo.saveGxht(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得购销合同信息
	 * </p>
	 * 
	 * @param dhno
	 * @return String
	 */
	@RequestMapping("/loadGxht")
	public String loadGxht(String dhno, Model model) {
		GxhtTp entity = bo.getGxht(dhno);
		if (entity == null) {
			entity = new GxhtTp();
			DhuoTp dhuoTp = bo.get(dhno, null, null);
			GxhtTp $entity = null;
			String user = dhuoTp != null ? dhuoTp.getUser() : "";
			String name = dhuoTp != null ? dhuoTp.getName() : "";
			$entity = bo.getByUser(user);
			if (entity != null) {
				ReflectUtils.copy(entity, $entity, false);
			}
			entity.setUser(user);
			entity.setName(name);
			entity.setDhno(dhno);
			entity.setHwpm("W".equals(dhno.substring(0, 1)) ? "马口铁次级品"
					: "马口铁一级品");
		}
		model.addAttribute("entity", entity);
		return "/sino/dh/editGxht";
	}

	/**
	 * <p>
	 * 订货合同进度管理
	 * </p>
	 * 
	 * @param page
	 * @param finish
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDhjd")
	public String indexDhjd(DhjdQE page, String finish, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		if (page.getCreaEnd() == null) {
			page.setCreaEnd(page.getCreaBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		if ("Y".equals(finish)) {
			page.setNeStat(DhStat.over.key);
			page.setStat(null);
		}
		String[] pznos = { "11", "21" };
		page.setPznos(pznos);
		page.setOrderBys("JHQI_ desc,DHNO_ asc,LINE_ asc");
		bo.queryDhjd(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", DhStat.over.key);
		return page.isQuery() ? "/sino/dh/listDhjd" : "/sino/dh/indexDhjd";
	}

	/**
	 * <p>
	 * 次级品订货合同管理
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexCjp")
	public String indexCjp(FspQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			StringBuilder xpzks = new StringBuilder();
			xpzks.append("'").append(EXpzk.BZP.key).append("':'").append(EXpzk.BZP.value).append("','").append(EXpzk.JZP.key).append("':'").append(EXpzk.JZP.value).append("'");
			model.addAttribute("xpzks", xpzks.toString());

			StringBuilder chan = new StringBuilder();
			chan.append("'").append(ChanType.fs.key).append("':'").append(ChanType.fs.name).append("','").append(ChanType.bhg.key).append("':'").append(ChanType.bhg.name).append("','").append(ChanType.fc.key).append("':'").append(ChanType.fc.name).append("'");
			model.addAttribute("chan", chan.toString());

			page.setXpzk(EXpzk.BZP.key);
			page.setChan(ChanType.fs.key);
		}
		if (page.getXphoE() == null || page.getXphoE().doubleValue() <= 0) {
			page.setXphoE(page.getXphoS());
		}
		if (page.getXpknE() == null || page.getXpknE().doubleValue() <= 0) {
			page.setXpknE(page.getXpknS());
		}
		if (page.getXpcnE() == null || page.getXpcnE().doubleValue() <= 0) {
			page.setXpcnE(page.getXpcnS());
		}
		page.setScbj(EScbj.CS.key);
		page.setStat(ZpStat.CS.stat);
		// page.setChan(ChanType.fs.key);

		String[] fpycs = { EFpyc.CS.key, EFpyc.YC.key };
		page.setFpycs(fpycs);
		page.setOrderBys("yuny_ asc, ggno_ asc, xpho_ asc, xpkn_ asc, xpcn_ asc, face_ asc");
		bo.queryFsp(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listCjp" : "/sino/dh/indexCjp";
	}

	/**
	 * <p>
	 * 制作发生品订货合同
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/doCjpHt", method = RequestMethod.POST)
	public @ResponseBody
	String doCjpHt(FspHtVO vo) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		return bo.doCjpHt(vo, user);
	}

	/**
	 * <p>
	 * 打印全部的特打印订货确认表
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/queryIds", method = RequestMethod.POST)
	public @ResponseBody
	String queryIds() {
		List<String> ids = bo.queryIds(Dybs.WDY.key);
		if (ids == null || ids.size() == 0) {
			return new Result(-1, "没有未打印的订货同确认表").toString();
		}
		DhuoTp dhuoTp = null;
		String[] arrs = null;
		String dhno, line;
		for (String id : ids) {
			arrs = id.split("-");
			dhno = arrs[0];
			line = arrs.length > 1 ? arrs[1] : null;
			dhuoTp = bo.get(new DhuoTpPk(dhno, line));
			if (dhuoTp == null) continue;
			if (!DhConstants.JSTJ_DL.equals(dhuoTp.getJstj())) {
				return new Result(-1, "订货合同No." + id + "结算条件未登录,不能打印订货确认表").toString();
			}
		}
		return "{ids:\"" + StringUtils.join(ids, ",") + "\"}";
	}

	/**
	 * <p>
	 * 修改订货合同是否打印
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/updateSfdy", method = RequestMethod.POST)
	public @ResponseBody
	String updateSfdy(String[] ids) {
		List<String> dhnos = new ArrayList<String>();
		List<String> lines = new ArrayList<String>();
		for (String id : ids) {
			if (id.length() != 9) continue;
			dhnos.add(id.substring(0, 7));
			lines.add(id.substring(7));
		}
		bo.updateSfdy(dhnos, lines, Dybs.YDY.key);
		return Result.SUCCESS;
	}

	// /**
	// * <p>
	// * 根据送货地址代码，获得送货地址名称
	// * </p>
	// *
	// * @param dzdm
	// * @return String
	// */
	// @RequestMapping(value = "/getShnm", method = RequestMethod.POST)
	// public @ResponseBody
	// String getShnm(String shno) {
	// YongShdz yongShdz = yongBO.getByDzdm(shno);
	// if (yongShdz == null) {
	// return new Result(-1, "地址不存在").toString();
	// }
	// return "{shnm:\"" + yongShdz.getAddr() + "\"}";
	// }

	/**
	 * <p>
	 * 根据用户代码，获得用户名称
	 * </p>
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping(value = "/getYong", method = RequestMethod.POST)
	public @ResponseBody
	String getYong(String user) {
		YongMp yongMp = yongBO.get(user);
		if (yongMp == null) {
			return new Result(-1, "用户不存在").toString();
		}
		StringBuilder shdz = new StringBuilder();
		List<YongShdz> yongShdzs = yongBO.findShdz(user);
		if (yongShdzs.size() > 0) {
			for (YongShdz yongShdz : yongShdzs) {
				if (shdz.length() > 0) {
					shdz.append(",").append(yongShdz.getAddr());
					continue;
				}
				shdz.append(yongShdz.getAddr());
			}
		}
		String ssno = yongMp.getSsno();
		String ssnm = yongMp.getSsnm();
		StringBuilder builder = new StringBuilder();
		builder.append("{abbr:\"").append(yongMp.getAbbr()).append("\",name:\"").append(yongMp.getRsv1()).append("\"");
		if (ssno != null && !ssno.isEmpty()) {
			builder.append(",ssno:\"").append(ssno).append("\"");
		}
		if (ssnm != null && !ssnm.isEmpty()) {
			builder.append(",ssnm:\"").append(ssnm).append("\"");
		}
		builder.append(", shdz:\"").append(shdz.toString()).append("\"");
		builder.append("}");
		return builder.toString();

	}

	/**
	 * <p>
	 * 订货进度明细入口
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/indexDhjdmx", method = RequestMethod.GET)
	public String toDhjdmx() {
		return "/sino/dh/indexDhjdmx";
	}

	/**
	 * <p>
	 * 导出订货进度明细Excel流
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outDhjdmxExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outDhjdmxExcel(DhjdVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchDhjdmx(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 进入订货登陆页面
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexDhdl")
	public String indexDhdl(Model model) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		// 传递担当者为登录人名称
		model.addAttribute("ddno", user.getNo());
		// 传递化成默认为解Chromate处理——311
		model.addAttribute("huac", DhHc.HC_DJ.stat);
		// 捆包计算
		model.addAttribute("kbjs", DhConstants.KBJS_Z);
		return "/sino/dh/indexDhdl";
	}

	/**
	 * <p>
	 * 验证订货No和line的唯一性
	 * </p>
	 * 
	 * @param dhbo
	 * @param line
	 * @return String
	 */
	@RequestMapping(value = "/checkDhno", method = RequestMethod.POST)
	public @ResponseBody
	String checkDhno(String dhno, String line, String ddno) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (dhno == null || dhno.isEmpty()) {
			return new Result(-1, "订货号不能为空").toString();
		}
		if (line == null || line.isEmpty()) {
			return new Result(-1, "订货行号不能为空").toString();
		}
		// 订货No长度验证
		if (dhno.length() != 7) {
			return new Result(-1, "订货号的长度必须为7位").toString();
		}
		// 行号长度验证
		if (line.length() != 2) {
			return new Result(-1, "订货行号的长度必须为2位").toString();
		}
		if (ddno == null || ddno.isEmpty()) {
			return new Result(-1, "担当者不能为空").toString();
		}
		if (bo.isExisted(new DhuoTpPk(dhno, line))) {
			return new Result(-1, "该订货No.已存在,请重新输入").toString();
		}
		if (!Pattern.matches("\\w+", dhno)) {
			return new Result(-1, "订货号只能是数字和字母").toString();
		}
		if (!Pattern.matches("\\d+", line)) {
			return new Result(-1, "订货行号只能是数字").toString();
		}
		if ("00".equals(line)) {
			return new Result(-1, "订货行号只能在00以上").toString();
		}
		Person person = personBO.getUniqueByNo(ddno);
		if (person == null) {
			return new Result(-1, "担当者" + ddno + "不存在").toString();
		}
		DefaultDhVO vo = bo.getDefaultDhInfo(dhno);
		vo.setDdnm(person.getName());
		Result result = new Result(vo);
		if (!ddno.equals(user.getNo())) {
			result.setCode(2);
		}
		return result.toJsObject();
	}

	/**
	 * <p>
	 * 根据订货单信息加载制造仕样信息
	 * </p>
	 * 
	 * @param dhuoTp
	 * @param flag
	 * @return String
	 */
	@RequestMapping(value = "/loadZzsy", method = RequestMethod.POST)
	public @ResponseBody
	String loadZzsy(DhuoTp dhuoTp, boolean flag) {
		Result result = bo.checkDhInfo(dhuoTp, flag);
		if (result.getCode() <= 0) {
			return result.toString();
		}
		return bo.getZzsy(dhuoTp);
	}

	/**
	 * <p>
	 * 保存之前，检验订货信息
	 * </p>
	 * 
	 * @param dhuoTp
	 * @param flag
	 * @return String
	 */
	@RequestMapping(value = "/checkDhInfo", method = RequestMethod.POST)
	public @ResponseBody
	String checkDhInfo(DhuoTp dhuoTp, boolean flag) {
		Result result = bo.checkDhInfo(dhuoTp, flag);
		if (result.getCode() <= 0) {
			return result.toString();
		}
		return bo.checkZzsy(dhuoTp).toString();
	}

	/**
	 * <p>
	 * 获取捆包计算结果信息
	 * </p>
	 * 
	 * @param kbjs
	 *            捆包计算
	 * @param pzno
	 *            品种
	 * @param kuan
	 *            宽
	 * @param cang
	 *            长
	 * @return String
	 */
	@RequestMapping(value = "/kbjs", method = RequestMethod.POST)
	public @ResponseBody
	String kbjs(String kbjs, String pzno, Double kuan, Double cang) {
		return bo.getKbjs(kbjs, pzno, kuan, cang);
	}

	/**
	 * <p>
	 * 获取压延方向
	 * </p>
	 * 
	 * @param kuan
	 *            宽
	 * @param cang
	 *            长
	 * @return String
	 */
	@RequestMapping(value = "/getYyan", method = RequestMethod.POST)
	public @ResponseBody
	String getYyan(Double kuan, Double cang) {
		if (kuan == null || cang == null) {
			return new Result(-1, "缺少参数").toString();
		}
		// 宽 > 长 。宽 > 965mm 压延方向2
		// 宽 > 长 。宽 <= 965mm 压延方向1
		// 宽 <= 长 。长 > 965mm 压延方向2
		// 宽 <= 长 。长 <= 965mm 压延方向1
		if (kuan < cang) {
			return new Result(1, DhYy.YY_CB.key).toString();
		}
		return new Result(1, DhYy.YY_DB.key).toString();
	}

	/**
	 * <p>
	 * 保存订货合同信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(DhuoTp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		return bo.save(entity, user);
	}

	/**
	 * <p>
	 * 修改订货合同信息
	 * </p>
	 * 
	 * @param entity
	 * @param ydhno
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(DhuoTp entity, String ydhno) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		return bo.update(entity, user, ydhno);
	}

	/**
	 * <p>
	 * 修改订货合同重量
	 * </p>
	 * 
	 * @param dhnoAndLine
	 * @param htzl
	 * @return String
	 */
	@RequestMapping(value = "/updateHtzl", method = RequestMethod.POST)
	public @ResponseBody
	String updateHtzl(String dhnoAndLine, Double htzl) {
		// 获取当前登录用户信息
		DhuoTp entity = bo.get(new DhuoTpPk(dhnoAndLine.substring(0, 7),
				dhnoAndLine.substring(8)));
		if (entity == null) {
			return new Result(-1, "订货合同No" + dhnoAndLine + ".不存在").toString();
		}
		double htdj = entity.getHtdj() != null ? entity.getHtdj() : 0d;
		entity.setHtzl(htzl);
		entity.setHtje(NumberUtils.format(htdj * htzl, 2));
		bo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 订货合同管理
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		if (page.getCreaEnd() == null) {
			page.setCreaEnd(page.getCreaBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		// 若无查询条件时 默认显示状态为初始
		if (!page.isQuery()) {
			page.setStat(DhStat.newly.key);
		}
		page.setOrderBys(" crea desc,dhno asc,line asc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", DhStat.over.key);
		return page.isQuery() ? "/sino/dh/list" : "/sino/dh/index";
	}

	/**
	 * <p>
	 * 加载订货合同信息
	 * </p>
	 * 
	 * @param id
	 * @param flag
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id, String flag) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "订货No.不能为空").toString();
		}
		String[] arr = id.split("-");
		if (arr.length != 2) {
			return new Result(-1, "订货No.有误").toString();
		}
		return bo.getForJs(new DhuoTpPk(arr[0], arr[1]), flag);
	}

	/**
	 * <p>
	 * 加载编辑订货合同的信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/editDh")
	public String editDh(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "订货No.不能为空").toString();
		}
		String[] arr = id.split("-");
		if (arr.length < 2) {
			return new Result(-1, "订货No.有误").toString();
		}
		DhuoTp dhuoTp = bo.get(new DhuoTpPk(arr[0], arr[1]));
		model.addAttribute("dhuoTp", dhuoTp);
		StringBuilder shdz = new StringBuilder();
		if (dhuoTp != null) {
			List<YongShdz> yongShdzs = yongBO.findShdz(dhuoTp.getUser());
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
		return "/sino/dh/editDh";
	}

	/**
	 * <p>
	 * 加载订货合同仕样确认的信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/syqrDh")
	public String syqrDh(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "订货No.不能为空").toString();
		}
		String[] arr = id.split("-");
		if (arr.length != 2) {
			return new Result(-1, "订货No.有误").toString();
		}
		DhuoTp dhuoTp = bo.get(new DhuoTpPk(arr[0], arr[1]));
		model.addAttribute("dhuoTp", dhuoTp);
		return "/sino/dh/syqrDh";
	}

	/**
	 * <p>
	 * 删除订货合同
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做删除操作的订货合同").toString();
		}
		return bo.delete(ids);
	}

	/**
	 * <p>
	 * 删除不合格订货合同
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delCjpHt", method = RequestMethod.POST)
	public @ResponseBody
	String delCjpHt(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做删除操作的订货合同").toString();
		}
		return bo.delCjpHt(ids);
	}

	/**
	 * <p>
	 * 查看订货DB信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/view")
	public String view(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "订货No.不能为空").toString();
		}
		String[] arr = id.split("-");
		if (arr.length < 2) {
			return new Result(-1, "订货No.有误").toString();
		}
		DhuoTp dhuoTp = bo.get(new DhuoTpPk(arr[0], arr[1]));
		model.addAttribute("dhuoTp", dhuoTp);
		return "/sino/dh/view";
	}

	/**
	 * <p>
	 * 结算条件登录主页面
	 * </p>
	 * 
	 * @param dhno
	 * @param lineStart
	 * @param lineEnd
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexJstj")
	public String indexJstj(String dhno, String lineStart, String lineEnd,
			Model model) {
		DhQE page = new DhQE();
		page.setSize(-1);
		page.setDhno(dhno);
		page.setLineStart(lineStart);
		lineEnd = lineEnd != null && !lineEnd.isEmpty() ? lineEnd : lineStart;
		page.setLineEnd(lineEnd);
		page.setOrderBys(" dhno, line");
		bo.query(page);
		model.addAttribute("page", page);
		// 当订货合同的状态为已完成时，将其颜色设为红色
		model.addAttribute("fixed", DhStat.over.key);
		return "/sino/dh/listJstj";
	}

	/**
	 * <p>
	 * 结算条件登录主页面
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param model
	 * @return String
	 */
	@RequestMapping("/toJstj")
	public String toJstj() {
		return "/sino/dh/indexJstj";
	}

	/**
	 * <p>
	 * 保存订货合同的结算条件
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveJstj", method = RequestMethod.POST)
	public @ResponseBody
	String saveJstj(JstjVO jstjVO) {
		if (jstjVO.getItems() == null || jstjVO.getItems().length == 0) {
			return new Result(-1, "请输入要设定结算条件的订货合同").toString();
		}
		String dhno = null, line = null;
		for (JstjVO entity : jstjVO.getItems()) {
			dhno = entity.getDhno();
			line = entity.getLine();
			if (dhno == null || dhno.isEmpty() || line == null
					|| line.isEmpty()) continue;
			// 预付款比例
			int yfkn = entity.getYfkn() != null ? entity.getYfkn() : 0;
			// 出货款比例
			int chkn = entity.getChkn() != null ? entity.getChkn() : 0;
			// 后付款比例
			int hfkn = entity.getHfkn() != null ? entity.getHfkn() : 0;
			// 期限
			int qixn = entity.getQixn() != null ? entity.getQixn() : 0;
			if ((chkn + hfkn + yfkn) != 100) {
				return new Result(-1, "订货No." + dhno + "-" + line
						+ ",预付款比例、出货款比例与后付款比例的和必须等于100").toString();
			}
			if (hfkn > 0 && qixn == 0) {
				return new Result(-1, "订货No." + dhno + "-" + line
						+ ",后付款比例大于0时,期限为必填项").toString();
			}
			if (hfkn == 0 && qixn > 0) {
				return new Result(-1, "订货No." + dhno + "-" + line
						+ ",后付款比例等于0时,没有期限").toString();
			}
		}
		bo.saveJstj(jstjVO);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 订货确认表打印查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDhqrb")
	public String indexDhqrb(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		// page.setJstj(DhConstants.JSTJ_WDL);
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		if (!page.isQuery()) {
			page.setSfdy(Dybs.WDY.key);
		}
		page.setOrderBys("dhno desc, line asc, crea desc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", DhStat.over.key);
		return page.isQuery() ? "/sino/dh/listDhqrb" : "/sino/dh/indexDhqrb";
	}

	/**
	 * <p>
	 * 打印订货确认表的时候，判断结算条件是否登录，如果没有登录，则不能打印。
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/checkDhqrbDy", method = RequestMethod.POST)
	public @ResponseBody
	String checkDhqrbDy(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "选择要打印的订货合同").toString();
		}
		List<DhuoTp> dhuoTps = bo.findByIds(Arrays.asList(ids));
		String dhno, line, dhqr;
		StringBuilder builder = new StringBuilder();
		for (DhuoTp dhuoTp : dhuoTps) {
			dhno = dhuoTp.getDhno();
			line = dhuoTp.getLine();
			dhqr = dhuoTp.getDhqr() != null ? dhuoTp.getDhqr() : "";
			if (!DhConstants.JSTJ_DL.equals(dhuoTp.getJstj())) {
				return new Result(-1, "订货合同No." + dhno + "-" + line
						+ "结算条件未登录,不能打印订货确认表").toString();
			}
			if (builder.length() == 0) {
				builder.append(dhno).append("-").append(line).append("-").append(dhqr);
				continue;
			}
			builder.append(",").append(dhno).append("-").append(line).append("-").append(dhqr);
		}
		return "{ids:\"" + builder.toString() + "\"}";
	}

	/**
	 * <p>
	 * 仕样未确认查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexSywqr")
	public String indexSywqr(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		// 值显示初始及上锁的状态
		String[] stats = { DhStat.newly.key, DhStat.locked.key };
		page.setStats(stats);
		// 按照用户、用途和作成时间的升序排列
		page.setOrderBys("user asc,cond asc,crea asc ");
		model.addAttribute("fixed", DhStat.over.key);
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listSywqr" : "/sino/dh/indexSywqr";
	}

	/**
	 * <p>
	 * 查看仕样未确认
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/viewSywqr")
	public String viewSywqr(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(3);
		}
		page.setJhqiEnd(page.getJhqiBegin());
		// 值显示初始及上锁的状态
		String[] stats = { DhStat.newly.key, DhStat.locked.key };
		page.setStats(stats);
		// 按照用户、用途和作成时间的升序排列
		page.setOrderBys(" user asc,cond asc,crea asc ");
		model.addAttribute("fixed", DhStat.over.key);
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listViewSywqr" : "/sino/dh/viewSywqr";
	}

	/**
	 * <p>
	 * 仕样确认主页面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexSyqr")
	public String indexSyqr(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		if (page.getSydtEnd() == null) {
			page.setSydtEnd(page.getSydtBegin());
		}
		// 若无查询条件时 默认显示状态为初始
		if (!page.isQuery()) {
			page.setStat(DhStat.newly.key);
			page.setSyxded(null);
		}
		page.setOrderBys("user asc,cond asc,crea asc ");
		model.addAttribute("fixed", DhStat.over.key);
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listSyqr" : "/sino/dh/indexSyqr";
	}

	/**
	 * <p>
	 * 订货合同仕样确认
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doSyqr", method = RequestMethod.POST)
	public @ResponseBody
	String doSyqr(DhuoTp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		return bo.doSyqr(entity, user);
	}

	/**
	 * <p>
	 * 订货合同仕样保存
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doSybc", method = RequestMethod.POST)
	public @ResponseBody
	String doSybc(DhuoTp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		return bo.doSybc(entity, user);
	}

	/**
	 * <p>
	 * 订货合同上锁
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	public @ResponseBody
	String lock(String ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "请选择要做上锁操作的订货合同").toString();
		}
		return bo.lock(ids.split(","), user);
	}

	/**
	 * <p>
	 * 订货合同解锁
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/deLock", method = RequestMethod.POST)
	public @ResponseBody
	String deLock(String ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "请选择要做解锁操作的订货合同").toString();
		}
		return bo.deLock(ids.split(","), user);
	}

	/**
	 * <p>
	 * 取消仕样操作
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/cancelSyqr", method = RequestMethod.POST)
	public @ResponseBody
	String cancelSyqr(String ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "请选择要做取消仕样操作的订货合同").toString();
		}
		return bo.cancelSyqr(ids.split(","), user);
	}

	/**
	 * <p>
	 * 批量仕样确认操作
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/plSyqr", method = RequestMethod.POST)
	public @ResponseBody
	String plSyqr(String ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "请选择要做仕样确认操作的订货合同").toString();
		}
		bo.doSyqr(ids.split(","), user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 订货合同完成确认
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDhwcqr")
	public String indexDhwcqr(DhQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getJhqiEnd() == null) {
			page.setJhqiEnd(page.getJhqiBegin());
		}
		String userBegin = page.getUserBegin();
		String userEnd = page.getUserEnd();
		String user = null;
		if (userBegin != null
				&& !userBegin.isEmpty()
				&& (userBegin.equals(userEnd) || userEnd == null || userEnd.isEmpty())) {
			user = userBegin;
		}
		else if (userEnd != null
				&& !userEnd.isEmpty()
				&& (userEnd.equals(userBegin) || userBegin == null || userBegin.isEmpty())) {
			user = userEnd;
		}
		if (user != null) {
			page.setUser(user);
			page.setUserBegin(null);
			page.setUserEnd(null);
		}
		if (!page.isQuery()) {
			page.setStat(DhStat.assign.key);
			model.addAttribute("stat", DhStat.assign.key);
		}
		// 按照作成时间的降序排列
		page.setOrderBys(" crea desc ");
		model.addAttribute("fixed", DhStat.over.key);
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dh/listDhwcqr" : "/sino/dh/indexDhwcqr";
	}

	/**
	 * <p>
	 * 提交合同完成确认
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public @ResponseBody
	String finish(String[] ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做合同完成操作的订货合同").toString();
		}
		return bo.finish(ids, user);
	}

	/**
	 * <p>
	 * 撤消合同完成确认
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/cancelFinish", method = RequestMethod.POST)
	public @ResponseBody
	String cancelFinish(String[] ids) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录已超时，请重新登录").toString();
		}
		if (ids == null || ids.length == 0) {
			return new Result(-1, "请选择要做撤消操作的订货合同").toString();
		}
		return bo.cancelFinish(ids, user);
	}

	/**
	 * <p>
	 * 订货完一个订货合同后，要连续获得下个订货合同No.
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getDhno", method = RequestMethod.POST)
	public @ResponseBody
	String getDhno() {
		DhuoTp dhuoTp = bo.getLatest();
		if (dhuoTp == null) {
			return new Result(-1, "系统中还未登录订货合同").toString();
		}
		String dhno = dhuoTp.getDhno();
		int hh;
		try {
			hh = Integer.parseInt(dhuoTp.getLine());
		}
		catch (NumberFormatException e) {
			hh = 1;
		}
		hh += 1;
		String line = hh > 10 ? "" + hh : "0" + hh;
		return "{dhno:\"" + dhno + "\",line:\"" + line + "\"}";
	}

	/**
	 * <p>
	 * 订货出货对比明细入口
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toDhuoChmx", method = RequestMethod.GET)
	public String toDhuoChmx() {
		return "/sino/dh/indexDhuoChMx";
	}

	/**
	 * <p>
	 * 导出订货出货对比明细Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outDhDhuoChmxExcel", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void outDhDhuoChmxExcel(DhuoChVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchDhuoChDatas(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IDhBO getBo() {
		return bo;
	}

	public void setBo(IDhBO bo) {
		this.bo = bo;
	}

	public IPersonBO getPersonBO() {
		return personBO;
	}

	public void setPersonBO(IPersonBO personBO) {
		this.personBO = personBO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

}
