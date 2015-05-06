package com.quanta.sino.yszk.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.coco.core.exception.CocoException;
import com.coco.core.util.DateUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Code119;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.EFppz;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yszk.vo.FpVO;
import com.quanta.sino.yszk.vo.KhfkVO;
import com.quanta.sino.yszk.vo.YszkQE;

/**
 * <p>
 * 付款发票管理
 * </p>
 * <p>
 * create: 2011-7-5 下午08:20:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/hkgl/yszk")
public class FkfpController {

	/**
	 * 应收账款业务层接口
	 */
	@Autowired
	private IFkfpBO bo;

	/**
	 * 客户付款业务接口
	 */
	@Autowired
	private IKhfkBO khfkBO;

	/**
	 * 
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * 升序
	 */
	private static final String ASC = "asc";

	/**
	 * 降序
	 */
	private static final String DESC = "desc";

	/**
	 * <p>
	 * 设置发票参数
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 * @param fpymc
	 * @param kpdj
	 * @return String
	 */
	@RequestMapping(value = "/setFpcs", method = RequestMethod.POST)
	public @ResponseBody
	String setFpcs(String ids, String fpno, String fpymc, Double kpdj) {
		bo.setFpcs(ids.split(","), fpno, fpymc, kpdj);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 付款发票LIST一览入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toFkfp", method = RequestMethod.GET)
	public String toFkfp(Model model) {
		// 品种
		StringBuilder pz = new StringBuilder();
		pz.append("'").append(Code118.coil.key).append("':'").append(Code118.coil.name).append("','").append(Code118.sheet.key).append("':'").append(Code118.sheet.name).append("'");
		model.addAttribute("pz", pz.toString());
		// 等级
		StringBuilder deng = new StringBuilder();
		deng.append("'").append(Code119.prime.key).append("':'").append(Code119.prime.name).append("','").append(Code119.grade2.key).append("':'").append(Code119.grade2.name);
		deng.append("','").append(Code119.grade3.key).append("':'").append(Code119.grade3.name).append("','").append(Code119.grade4.key).append("':'").append(Code119.grade4.name).append("','").append(Code119.grade5.key).append("':'").append(Code119.grade5.name).append("'");
		model.addAttribute("deng", deng.toString());
		return "/sino/hkgl/yszk/fkfpList";
	}

	/**
	 * <p>
	 * 导出付款发票LIST明细Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outFkfpExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outFkfpExcel(YszkQE qentity, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchFkfp(qentity, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 发票编辑管理
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexFpbj")
	public String indexFpbj(String chriOrder, String fpdmOrder, YszkQE page,
			Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setKfzl(0.0);
		page.setStat(null);
		page.setFppz(EFppz.zp.key);
		StringBuilder orders = new StringBuilder();
		if (ASC.equals(chriOrder)) {
			orders.append("chri asc, ");
		}
		else {
			orders.append("chri desc, ");
		}
		if (ASC.equals(fpdmOrder)) {
			orders.append("fpymc asc, ");
		}
		else {
			orders.append("fpymc desc, ");
		}
		orders.append("dhno asc, line asc, id desc");
		page.setOrderBys(orders.toString());
		if (!page.isQuery()) {
			// 品种
			StringBuilder pz = new StringBuilder();
			pz.append("'").append(Code118.coil.key).append("':'").append(Code118.coil.name).append("','").append(Code118.sheet.key).append("':'").append(Code118.sheet.name).append("'");
			model.addAttribute("pz", pz.toString());
			// 等级
			StringBuilder deng = new StringBuilder();
			deng.append("'").append(Code119.prime.key).append("':'").append(Code119.prime.name).append("','").append(Code119.grade2.key).append("':'").append(Code119.grade2.name);
			deng.append("','").append(Code119.grade3.key).append("':'").append(Code119.grade3.name).append("','").append(Code119.grade4.key).append("':'").append(Code119.grade4.name).append("','").append(Code119.grade5.key).append("':'").append(Code119.grade5.name).append("'");
			model.addAttribute("deng", deng.toString());
			model.addAttribute("page", page);
			List<String> list = yongBO.findFpkhs();
			StringBuilder fpkhs = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (String fpkh : list) {
					if (fpkhs.length() > 0) {
						fpkhs.append(",").append("['").append(fpkh).append("','").append(fpkh).append("']");
						continue;
					}
					fpkhs.append("[['").append(fpkh).append("','").append(fpkh).append("']");
				}
				fpkhs.append("]");
			}
			model.addAttribute("fpkhs", fpkhs.toString());
			return "/sino/hkgl/yszk/indexFpbj";
		}
		String nwai = page.getNwai();
		if (page.getNwai() != null && CodeNwx.jeck.key.equals(nwai)) {
			page.setNwai(CodeNwx.NX);
			page.setDhno("E");
		}
		else if (page.getNwai() != null && CodeNwx.nei.key.equals(nwai)) {
			page.setNwai(CodeNwx.NX);
			page.setNotLikeDhno("E");
		}
		model.addAttribute("thqf", (nwai == null || nwai.isEmpty()
				|| CodeNwx.nei.key.equals(nwai) ? Code013.rmb.key
				: Code013.usd.key));
		bo.query(page);
		model.addAttribute("page", page);
		// 统计合计数
		model.addAttribute("hjVO", bo.getHj(page.getChriBegin(), DateUtils.add(page.getChriEnd(), Calendar.DAY_OF_MONTH, 1), page.getFpymc(), nwai, page.getDeng(), page.getPz(), null, page.getHouuS(), page.getHouuE(), EFppz.zp.key, page.getEqFpno()));
		model.addAttribute("fixed", FpStat.YJS.key);
		return page.isQuery() ? "/sino/hkgl/yszk/listFpbj"
				: "/sino/hkgl/yszk/indexFpbj";
	}

	/**
	 * <p>
	 * 红字发票管理
	 * </p>
	 * 
	 * @param chriOrder
	 * @param fpdmOrder
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexHzfp")
	public String indexHzfp(String chriOrder, String fpdmOrder, YszkQE page,
			Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setFpje(0d);
		page.setStat(null);
		page.setFppz(EFppz.hz.key);
		StringBuilder orders = new StringBuilder();
		if (chriOrder == null || chriOrder.isEmpty() || ASC.equals(chriOrder)) {
			orders.append("chri asc, ");
		}
		else {
			orders.append("chri desc, ");
		}
		if (fpdmOrder == null || fpdmOrder.isEmpty() || ASC.equals(fpdmOrder)) {
			orders.append("fpymc asc, ");
		}
		else {
			orders.append("fpymc desc, ");
		}
		orders.append("dhno asc, line asc, id desc");
		page.setOrderBys(orders.toString());
		if (!page.isQuery()) {
			// 品种
			StringBuilder pz = new StringBuilder();
			pz.append("'").append(Code118.coil.key).append("':'").append(Code118.coil.name).append("','").append(Code118.sheet.key).append("':'").append(Code118.sheet.name).append("'");
			model.addAttribute("pz", pz.toString());
			// 等级
			StringBuilder deng = new StringBuilder();
			deng.append("'").append(Code119.prime.key).append("':'").append(Code119.prime.name).append("','").append(Code119.grade2.key).append("':'").append(Code119.grade2.name);
			deng.append("','").append(Code119.grade3.key).append("':'").append(Code119.grade3.name).append("','").append(Code119.grade4.key).append("':'").append(Code119.grade4.name).append("'");
			model.addAttribute("deng", deng.toString());
			List<String> list = yongBO.findFpkhs();
			StringBuilder fpkhs = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (String fpkh : list) {
					if (fpkhs.length() > 0) {
						fpkhs.append(",").append("['").append(fpkh).append("','").append(fpkh).append("']");
						continue;
					}
					fpkhs.append("[['").append(fpkh).append("','").append(fpkh).append("']");
				}
				fpkhs.append("]");
			}
			model.addAttribute("fpkhs", fpkhs.toString());
			// bo.query(page);
			// model.addAttribute("page", page);
			// return "/sino/hkgl/yszk/indexHzfp";
		}
		String nwai = page.getNwai();
		if (page.getNwai() != null && CodeNwx.jeck.key.equals(nwai)) {
			page.setNwai(CodeNwx.NX);
			page.setDhno("E");
		}
		else if (page.getNwai() != null && CodeNwx.nei.key.equals(nwai)) {
			page.setNwai(CodeNwx.NX);
			page.setNotLikeDhno("E");
		}
		model.addAttribute("thqf", (nwai == null || nwai.isEmpty()
				|| CodeNwx.nei.key.equals(nwai) ? Code013.rmb.key
				: Code013.usd.key));
		bo.query(page);
		model.addAttribute("page", page);
		// 统计合计数
		model.addAttribute("hjVO", bo.getHj(page.getChriBegin(), DateUtils.add(page.getChriEnd(), Calendar.DAY_OF_MONTH, 1), page.getFpymc(), nwai, page.getDeng(), page.getPz(), null, page.getHouuS(), page.getHouuE(), EFppz.hz.key, null));
		model.addAttribute("fixed", FpStat.YJS.key);
		return page.isQuery() ? "/sino/hkgl/yszk/listHzfp"
				: "/sino/hkgl/yszk/indexHzfp";
	}

	/**
	 * <p>
	 * 设置付款发票信息
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/saveFp", method = RequestMethod.POST)
	public @ResponseBody
	String saveFp(FpVO vo) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (vo == null || vo.getItems() == null || vo.getItems().length == 0) {
			return new Result(-1, "发票信息为空").toString();
		}
		bo.saveFp(vo, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 编辑付款发票信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/editFp", method = RequestMethod.POST)
	public @ResponseBody
	String editFp(Fkfp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		double kfzl = entity.getKfzl() != null ? entity.getKfzl() : 0d;
		if (kfzl <= 0) {
			return new Result(-1, "吨数输入有误").toString();
		}
		bo.editFp(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 编辑红字发票信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/editHzfp", method = RequestMethod.POST)
	public @ResponseBody
	String editHzfp(Fkfp entity) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		return bo.editHzfp(entity);
	}

	/**
	 * <p>
	 * 生成红字发票信息
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/outHzfp", method = RequestMethod.POST)
	public @ResponseBody
	String outHzfp(String ids) {
		return bo.editHzfp(ids.split(","));
	}

	/**
	 * <p>
	 * 生成蓝字发票信息
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/outLzfp", method = RequestMethod.POST)
	public @ResponseBody
	String outLzfp(String ids) {
		return bo.editLzfp(ids.split(","));
	}

	/**
	 * <p>
	 * 计算发票金额
	 * </p>
	 * 
	 * @param kfzl
	 * @param kpdj
	 * @param lxzr
	 * @param zlzr
	 * @return String
	 */
	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public @ResponseBody
	String calculate(Double kfzl, Double kpdj, Double lxzr, Double zlzr) {
		kfzl = kfzl != null ? kfzl : 0;
		kpdj = kpdj != null ? kpdj : 0;
		lxzr = lxzr != null ? lxzr : 0;
		zlzr = zlzr != null ? zlzr : 0;
		// kfzl=77.675;kpdj=8561时，kfzl*kpdj的结果是错误的
		// BigDecimal $kfzl = new BigDecimal(String.valueOf(kfzl));
		// BigDecimal $kpdj = new BigDecimal(String.valueOf(kpdj));
		// Double fpje = NumberUtils.format($kfzl.multiply($kpdj).doubleValue()
		// - lxzr - zlzr, 2);
		Double fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
		return "{fpje:" + fpje + "}";
	}

	/**
	 * <p>
	 * 生成应收帐款发票数据
	 * </p>
	 * 
	 * @param chriBegin
	 * @param chriEnd
	 * @return String
	 */
	@RequestMapping(value = "/outFpDatas", method = RequestMethod.POST)
	public @ResponseBody
	String outFpDatas(Date chriBegin, Date chriEnd) {
		bo.outFpDatas(chriBegin, DateUtils.add(chriEnd, Calendar.DAY_OF_MONTH, 1));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除付款发票信息
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "主键不能为空").toString();
		}
		return bo.delete(id);
	}

	/**
	 * <p>
	 * 加载付款发票信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/load")
	public String load(String id, Model model) {
		model.addAttribute("entity", bo.get(id));
		StringBuilder thqf = new StringBuilder();
		thqf.append("'").append(Code013.rmb.key).append("':'").append(Code013.rmb.value).append("','").append(Code013.usd.key).append("':'").append(Code013.usd.value).append("'");
		model.addAttribute("thqf", thqf.toString());
		return "/sino/hkgl/yszk/editFp";
	}

	/**
	 * <p>
	 * 加载红字发票信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping("/loadHzfp")
	public String loadHzfp(String id, Model model) {
		if (id != null && !id.isEmpty()) {
			model.addAttribute("entity", bo.get(id));
		}
		StringBuilder thqf = new StringBuilder();
		thqf.append("'").append(Code013.rmb.key).append("':'").append(Code013.rmb.value).append("','").append(Code013.usd.key).append("':'").append(Code013.usd.value).append("'");
		model.addAttribute("thqf", thqf.toString());
		return "/sino/hkgl/yszk/editHzfp";
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
	@RequestMapping(value = "/getFpdhInfo", method = RequestMethod.POST)
	public @ResponseBody
	String getFpdhInfo(String dhno, String line) {
		if (dhno == null || dhno.isEmpty() || line == null || line.isEmpty()) {
			return new Result(-1, "订货合同号不能为空").toString();
		}
		return bo.getFpdhInfo(dhno, line);

	}

	/**
	 * <p>
	 * 判断发票编辑页面
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/doCheck", method = RequestMethod.POST)
	public @ResponseBody
	String doCheck(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "主文件ID不能为空").toString();
		}
		Fkfp entity = bo.get(id);
		if (entity == null) {
			return new Result(-1, "付款发票状态不存在,请联系管理员").toString();
		}
		if (!FpStat.CS.key.equals(entity.getStat())) {
			return new Result(-1, "付款发票状态为" + FpStat.get(entity.getStat()).name
					+ ",不能进行修改操作").toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入分解付款发票信息页面
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/toDivied", method = RequestMethod.POST)
	public @ResponseBody
	String toDivied(String id) {
		Fkfp entity = bo.get(id);
		if (entity == null) {
			return new Result(-1, "付款发票信息不存在,请联系管理员").toString();
		}
		if (!FpStat.CS.key.equals(entity.getStat())) {
			return new Result(-1, "付款发票状态为" + FpStat.get(entity.getStat()).name
					+ ",不能进行分解操作").toString();
		}
		return new Result(entity).toJsObject();
	}

	/**
	 * <p>
	 * 保存分解付款发票信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveDivied", method = RequestMethod.POST)
	public @ResponseBody
	String saveDivied(Fkfp entity) {
		return bo.saveDivied(entity);
	}

	/**
	 * <p>
	 * 付款冲帐记录查询
	 * </p>
	 * 
	 * @param chriOrder
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexFkcz")
	public String indexFkcz(String chriOrder, YszkQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setStat(null);
		page.setFpje(0.0);
		StringBuilder orders = new StringBuilder();
		if (ASC.equals(chriOrder)) {
			orders.append("chri asc, ");
			model.addAttribute("chriOrder", DESC);
		}
		else {
			orders.append("chri desc, ");
			model.addAttribute("chriOrder", ASC);
		}
		orders.append("dhno asc, line asc, id desc");
		page.setOrderBys(orders.toString());
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			List<String> list = yongBO.findFpkhs();
			StringBuilder fpkhs = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (String fpkh : list) {
					if (fpkhs.length() > 0) {
						fpkhs.append(",").append("['").append(fpkh).append("','").append(fpkh).append("']");
						continue;
					}
					fpkhs.append("[['").append(fpkh).append("','").append(fpkh).append("']");
				}
				fpkhs.append("]");
			}
			model.addAttribute("fpkhs", fpkhs.toString());
			StringBuilder fppz = new StringBuilder();
			fppz.append("'").append(EFppz.zp.key).append("':'").append(EFppz.zp.value).append("','").append(EFppz.hz.key).append("':'").append(EFppz.hz.value).append("'");
			model.addAttribute("fppz", fppz.toString());
			return "/sino/hkgl/yszk/indexFkcz";
		}
		model.addAttribute("hjVO", bo.getHj(page.getChriBegin(), DateUtils.add(page.getChriEnd(), Calendar.DAY_OF_MONTH, 1), page.getFpymc(), null, null, null, null, null, null, page.getFppz(), null));
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/hkgl/yszk/listFkcz"
				: "/sino/hkgl/yszk/indexFkcz";
	}

	/**
	 * <p>
	 * 进入冲账页面
	 * </p>
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping("/toCz")
	public String toCz(String fpymc, Model model) {
		List<String> stats = new ArrayList<String>();
		stats.add(FpStat.CS.key);
		stats.add(FpStat.WJS.key);
		List<Khfk> items = khfkBO.find(fpymc, stats);
		if (items == null || items.size() == 0) {
			throw new CocoException(-1, "该客户还未付款,因此不能做冲帐操作");
		}
		model.addAttribute("items", items);
		model.addAttribute("entity", items.get(0));
		return "/sino/hkgl/yszk/listKhfk";
	}

	/**
	 * <p>
	 * 检验客户付款是否付款
	 * </p>
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping(value = "/checkKhfk", method = RequestMethod.POST)
	public @ResponseBody
	String checkKhfk(String fpymc) {
		if (fpymc == null || fpymc.isEmpty()) {
			return new Result(-1, "用户不能为空").toString();
		}
		List<String> stats = new ArrayList<String>();
		stats.add(FpStat.CS.key);
		stats.add(FpStat.WJS.key);
		if (!khfkBO.isExisted(fpymc, stats)) {
			return new Result(-1, "该客户还未付款,因此不能做冲帐操作").toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获取客户付款金额信息
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getKhfk", method = RequestMethod.POST)
	public @ResponseBody
	String getKhfk(String id) {
		if (id == null) {
			return new Result(-1, "付款日期不能为空").toString();
		}
		Khfk khfk = bo.getKhfk(id);
		if (khfk == null) {
			return new Result(-1, "该客户付款记录不存在,请联系管理员").toString();
		}
		KhfkVO khfkVo = new KhfkVO();
		// khfkVo.setAbbr(khfk.getAbbr());
		// khfkVo.setUser(khfk.getUser());
		khfkVo.setName(khfk.getName());
		khfkVo.setCrea(khfk.getCrea());
		khfkVo.setFkje(khfk.getFkje());
		khfkVo.setFkye(khfk.getFkye());
		khfkVo.setId(khfk.getId());
		return new Result(khfkVo).toJsObject();
	}

	/**
	 * <p>
	 * 保存冲账信息
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/doCz", method = RequestMethod.POST)
	public @ResponseBody
	String doCz(KhfkVO vo) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (vo == null || vo.getKhfkIds() == null
				|| vo.getKhfkIds().length == 0 || vo.getIds() == null
				|| vo.getIds().length == 0) {
			return new Result(-1, "冲帐发票信息不完整,请联系管理员").toString();
		}
		return bo.doCz(vo);
	}

	public IFkfpBO getBo() {
		return bo;
	}

	public void setBo(IFkfpBO bo) {
		this.bo = bo;
	}

	public IKhfkBO getKhfkBO() {
		return khfkBO;
	}

	public void setKhfkBO(IKhfkBO khfkBO) {
		this.khfkBO = khfkBO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

}
