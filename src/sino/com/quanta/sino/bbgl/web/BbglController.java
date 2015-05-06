package com.quanta.sino.bbgl.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.util.DateUtils;
import com.coco.core.xml.XMLHelper;
import com.quanta.sino.bbgl.bo.api.IBbglBO;
import com.quanta.sino.bbgl.vo.CpcktzQE;
import com.quanta.sino.bbgl.vo.CpcktzVO;
import com.quanta.sino.bbgl.vo.TzbzQE;
import com.quanta.sino.bbgl.vo.YccktzQE;
import com.quanta.sino.bbgl.vo.YccktzVO;
import com.quanta.sino.cmn.constants.CodeSpbh;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.orm.Tzbz;

/**
 * <p>
 * 仓库台帐管理控制器
 * </p>
 * <p>
 * create: 2011-8-31 上午11:38:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/bbgl")
public class BbglController {

	/**
	 * 台帐报表管理业务接口
	 */
	@Autowired
	private IBbglBO bbglBO;

	/**
	 * <p>
	 * 成品仓库台帐查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexCpcktz")
	public String indexCpcktz(CpcktzQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			page.setNy(DateUtils.format(calendar.getTime(), "yyyyMM"));
			page.setChou(CodeSpbh.spbh1.key);
			page.setCplb("C");
		}
		StringBuilder cplb = new StringBuilder();
		cplb.append("'C':'").append(EXpzk.JZP.value).append("','Z':'").append(EXpzk.ZJP.value).append("','S':'").append(EXpzk.BZP.value).append("'");
		model.addAttribute("cplb", cplb);
		page.setOrderBys("RIQI_ asc,CHOU_ asc");
		bbglBO.queryCptz(page);
		model.addAttribute("page", page);
		model.addAttribute("year", page.getNy() != null ? page.getNy().substring(0, 4)
				: DateUtils.format(new Date(), "yyyy"));
		if (page.isQuery()) {
			if ("C".equals(page.getCplb())) {
				return "/sino/bbgl/listJzpcktz";
			}
			else if ("Z".equals(page.getCplb())) {
				return "/sino/bbgl/listZjpcktz";
			}
			return "/sino/bbgl/listBzpcktz";
		}
		return "/sino/bbgl/indexCpcktz";
	}

	/**
	 * <p>
	 * 保存成品仓库台帐数据
	 * </p>
	 * 
	 * @param cplb
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/saveCptz", method = RequestMethod.POST)
	public @ResponseBody
	String saveCptz(String cplb, HttpServletRequest request) {
		List<CpcktzVO> items = null;
		try {
			items = XMLHelper.createNode2Bean(CpcktzVO.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		bbglBO.saveCptz(items, cplb);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 原材料仓库台查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexYccktz")
	public String indexYccktz(YccktzQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			page.setNy(DateUtils.format(calendar.getTime(), "yyyyMM"));
			page.setSpbh(CodeSpbh.spbh1.key);
		}
		page.setOrderBys("RIQI_ asc,SPBH_ asc");
		bbglBO.queryYctz(page);
		model.addAttribute("page", page);
		model.addAttribute("year", page.getNy() != null ? page.getNy().substring(0, 4)
				: DateUtils.format(new Date(), "yyyy"));
		return page.isQuery() ? "/sino/bbgl/listYccktz"
				: "/sino/bbgl/indexYccktz";
	}

	/**
	 * <p>
	 * 保存原料台帐数据
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/saveYctz", method = RequestMethod.POST)
	public @ResponseBody
	String saveYctz(HttpServletRequest request) {
		List<YccktzVO> items = null;
		try {
			items = XMLHelper.createNode2Bean(YccktzVO.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		bbglBO.saveYctz(items);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查询台帐备注
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexTzbz")
	public String indexTzbz(TzbzQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
		}
		page.setOrderBys("ny asc,spbh asc");
		bbglBO.queryTzbz(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/bbgl/listTzbz" : "/sino/bbgl/indexTzbz";
	}

	/**
	 * <p>
	 * 保存台帐备注
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveTzbz", method = RequestMethod.POST)
	public @ResponseBody
	String saveTzbz(Tzbz entity) {
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			return bbglBO.updateTzbz(entity);
		}
		return bbglBO.saveTzbz(entity);
	}

	/**
	 * <p>
	 * 删除台帐备注数据
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delsTzbz", method = RequestMethod.POST)
	public @ResponseBody
	String delsTzbz(String[] ids) {
		bbglBO.delsTzbz(ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 加载台帐备注数据
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/loadTzbz", method = RequestMethod.POST)
	public @ResponseBody
	String loadTzbz(String id) {
		return bbglBO.loadTzbzForJS(id);
	}

	public IBbglBO getBbglBO() {
		return bbglBO;
	}

	public void setBbglBO(IBbglBO bbglBO) {
		this.bbglBO = bbglBO;
	}

}
