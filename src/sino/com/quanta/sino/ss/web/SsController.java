package com.quanta.sino.ss.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.ss.bo.api.ISsBO;
import com.quanta.sino.ss.vo.SlsjQE;
import com.quanta.sino.ss.vo.SsBlVO;
import com.quanta.sino.ss.vo.SsItemVO;
import com.quanta.sino.ss.vo.SsRzVO;
import com.quanta.sino.ss.vo.SsVO;
import com.quanta.sino.ss.vo.SsXmVO;
import com.quanta.sino.ss.vo.ssXmSaveVO;

/**
 * <p>
 * SS分选控制器
 * </p>
 * <p>
 * create: 2011-1-20 下午08:12:35
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ss")
public class SsController {

	@Autowired
	private ISsBO bo;

	/**
	 * 制品查询业务接口
	 */
	@Autowired
	private IZpBO zpBo;

	@Autowired
	private IScxbBO scxBo;
	/**
	 * 生成js串忽略的字段列表
	 */
	private Set<String> ignoreFields;

	/**
	 * 生产js串的表达式列表
	 */
	private Set<String> exprs;

	/**
	 * 生产js串的明细表达式列表
	 */
	private Set<String> itemExprs;

	public SsController() {
		ignoreFields = new HashSet<String>();
		ignoreFields.add("items");
		exprs = new HashSet<String>();
		exprs.add("size");
		exprs.add("jszs");
		exprs.add("jszszl");
		itemExprs = new HashSet<String>();
		itemExprs.add("size");
	}

	/**
	 * <p>
	 * Pile生成页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toCreate", method = RequestMethod.GET)
	public String toCreate() {
		return "/sino/ss/indexcreate";
	}

	/**
	 * <p>
	 * 获取制品信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/fetchItem", method = RequestMethod.POST)
	public @ResponseBody
	String fetchItem(String newJbno, SsItemVO header) {
		if (header != null
				&& (header.getJbno() == null || header.getJbno().isEmpty())) {
			header = null;
		}
		try {
			SsItemVO vo = bo.fetchItem(newJbno, header);
			return new Result(vo).toJsObject(null, null, itemExprs);
		}
		catch (CocoException e) {
			return new Result(e.getCode(), e.getMessage()).toString();
		}
	}

	/**
	 * <p>
	 * 生成合并包信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/build", method = RequestMethod.POST)
	public @ResponseBody
	String build(SsVO vo) {
		bo.build(vo);
		return new Result(vo).toJsObject(null, ignoreFields, exprs);
	}

	/**
	 * <p>
	 * 保存新生成的包
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	String create(SsVO vo) {
		bo.save(vo);
		return new Result(vo.getJbno()).toString();
	}

	/**
	 * <p>
	 * Pile消灭
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toDestroy", method = RequestMethod.GET)
	public String toDestroy() {
		return "/sino/ss/indexdestroy";
	}

	/**
	 * <p>
	 * 获取制品信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/fetchXm", method = RequestMethod.POST)
	public @ResponseBody
	String fetchXm(String jbno) {
		try {
			SsXmVO vo = bo.fetchXmVO(jbno);
			return new Result(vo).toJsObject(null, null, itemExprs);
		}
		catch (CocoException e) {
			return new Result(e.getCode(), e.getMessage()).toString();
		}
	}

	/**
	 * <p>
	 * 生成合并包信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/destroyvalidate", method = RequestMethod.POST)
	public @ResponseBody
	String destroyvalidate(ssXmSaveVO vo) {
		if (vo.getItems() == null || vo.getItems().length == 0) {
			throw new CocoException(-1, "获取列表数据为空");
		}

		bo.destroyvalidate(vo.getItems());
		// return new Result(vo.getJbno()).toString();
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 生成合并包信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/destroy", method = RequestMethod.POST)
	public @ResponseBody
	String destroy(ssXmSaveVO vo) {
		if (vo.getItems() == null || vo.getItems().length == 0) {
			throw new CocoException(-1, "获取列表数据为空");
		}

		bo.destroy(vo.getItems());
		// return new Result(vo.getJbno()).toString();
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 实绩订正初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dz", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String dz(String jbno, Model model) {
		// 设入初始板号
		if (jbno != null && !jbno.isEmpty()) {
			model.addAttribute("pileno", jbno);
		}
		// SsVO ss = new SsVO();
		// model.addAttribute("item", ss);
		return "/sino/ss/dz";
	}

	/**
	 * <p>
	 * 实绩删除初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteindex", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String deleteindex(String jbno, Model model) {
		// 设入初始板号
		if (jbno != null && !jbno.isEmpty()) {
			model.addAttribute("pileno", jbno);
		}
		// SsVO ss = new SsVO();
		// model.addAttribute("item", ss);
		return "/sino/ss/delete";
	}

	/**
	 * <p>
	 * 订正校验
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dzCheck", method = RequestMethod.POST)
	public String dzCheck(String jbno, Model model) {
		SsVO ss = bo.dzCheck(jbno);
		model.addAttribute("item", ss);
		return "/sino/ss/dzDetail";
	}

	/**
	 * <p>
	 * 删除校验
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteCheck", method = RequestMethod.POST)
	public String deleteCheck(String jbno, Model model) {
		SsVO ss = bo.dzCheck(jbno);
		model.addAttribute("item", ss);
		return "/sino/ss/deleteDetail";
	}

	/**
	 * <p>
	 * 订正/更新制品
	 * </p>
	 * 
	 * @param zp
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String update(SsVO ss) {
		bo.update(ss);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 实绩制品分页查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String query(SlsjQE page, Model model) {
		if (!page.isQuery()) {
			page.setSize(15);
			if ((page.getCrea_begin() == null) && (page.getCrea_end() == null)) {
				// 开始初始值：当前日期前一天
				page.setCrea_begin(DateUtils.getCurrentDay());
				// 结束初始值：当前日期
				page.setCrea_end(new Date());
			}
			// 设生产线
			User user = Context.currentUser();
			if (page.getBan() == null) {
				// 设班
				page.setBan(user.getShift());
			}

			if (page.getZu() == null) {
				// 设组
				page.setZu(user.getGroup());
			}
		}
		// page.setNeFpyc(EFpyc.YC.key);
		page.setDuic(DC.F.name);
		page.setJdjs(ZtConstants.ZPNG_JD_JDJS);
		page.setJdjz(ZtConstants.ZPNG_JD_JDJZ);
		page.setOrderBys("crea desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ss/index!list" : "/sino/ss/indexWh";
	}

	/**
	 * <p>
	 * 删除实绩制品
	 * </p>
	 * 
	 * @param jbno
	 *            板材NO
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String delete(String jbno) {
		bo.delete(jbno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 选别紧急设置
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexFP", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String queryFP(ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setNscbj(ZtConstants.ZPNG_SC_YXM);
		page.setDuic(DC.E.name);
		// page.setNeFpyc(EFpyc.YC.key);
		page.setZshu(0);
		page.setOrderBys("  crea desc, dhno desc");
		zpBo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ss/listFP" : "/sino/ss/indexFP";

	}

	/**
	 * <p>
	 * 保存sl分派
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/updateFP", method = RequestMethod.POST)
	public @ResponseBody
	String updateFP(String[] ids, String jinj) {
		zpBo.updateJinj(ids, jinj);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 取消SL分派
	 * </p>
	 * 
	 * @param id
	 *            指示NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/deleteFP", method = RequestMethod.POST)
	public @ResponseBody
	String deleteFP(String[] ids, Model model) {
		zpBo.updateJinj(ids, null);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 选别检索
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String query(ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setNscbj(ZtConstants.ZPNG_SC_YXM);
		page.setDuic(DC.E.name);
		// page.setNeFpyc(EFpyc.YC.key);
		page.setZshu(0);
		page.setOrderBys(" jinj desc, crea desc,dhno desc");
		zpBo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/ss/list" : "/sino/ss/index";

	}

	/**
	 * <p>
	 * 查询日志入口
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/rizhi", method = RequestMethod.GET)
	public String queryRz() {
		return "/sino/ss/rizhi";

	}

	/**
	 * <p>
	 * 导出日志Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outRzExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outRzExcel(SsRzVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchRz(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 步留明细入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/blmx", method = RequestMethod.GET)
	public String toBlmx() {
		return "/sino/ss/blmx";
	}

	/**
	 * <p>
	 * 导出步留明细Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outBlmxExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outBlmxExcel(SsBlVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchBlmx(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 步留统计入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/bltj", method = RequestMethod.GET)
	public String toBltj() {
		return "/sino/ss/bltj";
	}

	/**
	 * <p>
	 * 导出步留统计Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outBltjExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outBltjExcel(SsBlVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchBltj(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the bo
	 */
	public ISsBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(ISsBO bo) {
		this.bo = bo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IScxbBO getScxBo() {
		return scxBo;
	}

	public void setScxBo(IScxbBO scxBo) {
		this.scxBo = scxBo;
	}

}
