package com.quanta.sino.yygl.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.util.DateUtils;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.cmn.constants.Chart;
import com.quanta.sino.orm.Khyfdj;
import com.quanta.sino.yygl.bo.api.IKhyfBO;
import com.quanta.sino.yygl.vo.CkdVO;
import com.quanta.sino.yygl.vo.KhyfVO;
import com.quanta.sino.yygl.vo.RChtjVO;
import com.quanta.sino.yygl.vo.ZrchsjQE;

/**
 * <p>
 * 客户运费控制器
 * </p>
 * <p>
 * create: 2011-2-13 下午07:29:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yygl/khyf")
public class KhyfController {

	/**
	 * 客户运费业务层接口
	 */
	@Autowired
	private IKhyfBO khyfBO;

	/**
	 * <p>
	 * 用途统计单入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/indexCond", method = RequestMethod.GET)
	public String toCondd(Model model) {
		Calendar calendar = Calendar.getInstance();
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("month", calendar.get(Calendar.MONTH) + 1);
		StringBuilder chart = new StringBuilder();
		chart.append("'").append(Chart.pie.key).append("':'").append(Chart.pie.name).append("','").append(Chart.bar.key).append("':'").append(Chart.bar.name).append("'");
		model.addAttribute("chart", chart.toString());
		return "/sino/yygl/khyf/indexCond";
	}

	/**
	 * <p>
	 * 统计用途
	 * </p>
	 * 
	 * @param yearS
	 * @param yearE
	 * @param monthS
	 * @param monthE
	 * @param chart
	 * @param model
	 * @return String
	 */
	@RequestMapping("/previewCond")
	public String previewCond(Integer yearS, Integer yearE, Integer monthS,
			Integer monthE, String chart, Model model) {
		model.addAttribute("vos", khyfBO.queryConds(yearS, yearE, monthS, monthE));
		model.addAttribute("yearS", yearS);
		model.addAttribute("yearE", yearE);
		model.addAttribute("monthS", monthS);
		model.addAttribute("monthE", monthE);
		model.addAttribute("chart", chart);
		StringBuffer title = new StringBuffer();
		title.append(yearS).append("年");
		if (yearS.intValue() != yearE.intValue()) {
			title.append(monthS).append("月份").append("-").append(yearS).append("年").append(monthE).append("月份");
		}
		else {
			if (monthS.intValue() != monthE.intValue()) {
				title.append(monthS).append("-").append(monthE).append("月份");
			}
			else {
				title.append(monthE).append("月份");
			}
		}
		model.addAttribute("title", title.toString());
		return "/sino/yygl/khyf/listCond";
	}

	/**
	 * <p>
	 * 按日统计出货入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/indexCpckd", method = RequestMethod.GET)
	public String toCpckd(Model model) {
		Date chriS = null, chriE = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			chriS = sdf.parse(sdf.format(calendar.getTime()));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			chriE = sdf.parse(sdf.format(calendar.getTime()));
		}
		catch (ParseException e) {
		}
		model.addAttribute("chriS", chriS);
		model.addAttribute("chriE", chriE);
		return "/sino/yygl/khyf/indexCpckd";
	}

	/**
	 * <p>
	 * 统计成品出库单
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return String
	 */
	@RequestMapping(value = "/tjCpckd", method = RequestMethod.POST)
	public @ResponseBody
	String tjCpckd(Date chriS, Date chriE) {
		if (chriE == null) {
			chriE = chriS;
		}
		long num = khyfBO.getNum(chriS, DateUtils.add(chriE, Calendar.DAY_OF_MONTH, 1));
		if (num <= 0) {
			return new Result(-1, "从" + DateUtils.format(chriS, "yyyy-MM-dd")
					+ "到" + DateUtils.format(chriE, "yyyy-MM-dd") + "没有出货记录").toString();
		}
		return new Result(1, num + "").toString();
	}

	/**
	 * <p>
	 * 导出成品出库单统计出货Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outCpckdExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outCpckdExcel(CkdVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			khyfBO.fetchCpckd(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 成品出库单打印
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @param model
	 * @return String
	 */
	@RequestMapping("/previewCpckd")
	public String previewCpckd(Date chriS, Date chriE, Model model) {
		if (chriE == null) {
			chriE = chriS;
		}
		model.addAttribute("vos", khyfBO.queryCpckds(chriS, DateUtils.add(chriE, Calendar.DAY_OF_MONTH, 1)));
		return "/sino/yygl/khyf/listCpckd";
	}

	/**
	 * <p>
	 * 按日统计出货入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/indexRChtj", method = RequestMethod.GET)
	public String toRChtj(Model model) {
		Date chriS = null, chriE = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			chriS = sdf.parse(sdf.format(calendar.getTime()));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			chriE = sdf.parse(sdf.format(calendar.getTime()));
		}
		catch (ParseException e) {
		}
		model.addAttribute("chriS", chriS);
		model.addAttribute("chriE", chriE);
		return "/sino/yygl/khyf/indexRChtj";
	}

	/**
	 * <p>
	 * 导出按日统计出货Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outRChtjExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outRChtjExcel(RChtjVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			khyfBO.fetchRChtj(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 昨天出货实绩查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ZrchsjQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			// 设置页面上显示的默认出货日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
			}
			catch (ParseException e) {
			}
			page.setChriS(date);
		}
		if (page.getChriE() == null) {
			page.setChriE(page.getChriS());
		}
		page.setChzl(0.0);
		page.setOrderBys("CHRI_ asc, XH_ asc, NAME_ asc, YSNM_ asc, SHNM_ asc, ADDR_ asc, MAZL_ asc, DHNO_ asc, LINE_ asc");
		khyfBO.query(page);
		// 统计件数和重量
		ChtjVO chtjVO = khyfBO.tj(page.getChriS(), page.getChriE(), page.getYsgs());
		model.addAttribute("chtjVO", chtjVO);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/yygl/khyf/list"
				: "/sino/yygl/khyf/index";
	}

	/**
	 * <p>
	 * 加载客户运费
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "主键为空").toString();
		}
		return khyfBO.getForJs(id);
	}

	/**
	 * <p>
	 * 保存客户运费
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	String save(KhyfVO vo) {
		khyfBO.saveYf(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获取运费单价
	 * </p>
	 * 
	 * @param id
	 * @param ysgs
	 * @param ysfs
	 * @param djdw
	 * @param ld
	 * @return String
	 */
	@RequestMapping(value = "/getYfdj", method = RequestMethod.POST)
	public @ResponseBody
	String getYfdj(String id, String ysnm, String ysfs, String djdw, String ld) {
		Khyfdj khyfdj = khyfBO.getYfdj(id, ysnm, ysfs, djdw, ld);
		if (khyfdj == null) {
			return new Result(-1, "客户运输单价不存在").toString();
		}
		return new Result(1, khyfdj.getYfdj().toString()).toString();
	}

	/**
	 * @return the khyfBO
	 */
	public IKhyfBO getKhyfBO() {
		return khyfBO;
	}

	/**
	 * @param khyfBO
	 *            the khyfBO to set
	 */
	public void setKhyfBO(IKhyfBO khyfBO) {
		this.khyfBO = khyfBO;
	}

}
