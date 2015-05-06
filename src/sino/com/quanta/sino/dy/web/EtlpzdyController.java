package com.quanta.sino.dy.web;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.util.DateUtils;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zk.bo.api.IEtlyyglBO;
import com.quanta.sino.zk.vo.EtlpzglQE;
import com.quanta.sino.zk.vo.EtlyyglQE;

/**
 * <p>
 * ETL实时品质记录打印控制器
 * </p>
 * <p>
 * create: 2011-4-18 下午01:06:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class EtlpzdyController {

	/**
	 * 品质管理业务接口
	 */
	@Autowired
	private IEtlpzglBO bo;

	/**
	 * 药液管理业务接口
	 */
	@Autowired
	private IEtlyyglBO etlyyglBO;

	/**
	 * 每面打印的生产卷条数
	 */
	private static int pageSize = 15;

	/**
	 * <p>
	 * ETL实时品质记录表
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexPzjl")
	public String indexPzjl(EtlpzglQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(pageSize);
		}
		Calendar cal = Calendar.getInstance();
		if (!page.isQuery()) {
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			model.addAttribute("scsj", cal.getTime());
		}
		if (page.getScsjEnd() == null) {
			page.setScsjEnd(page.getScsjBegin());
		}
		if (page.getScsjEnd() != null) {
			cal.setTime(page.getScsjEnd());
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		page.setOrderBys("scsj desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dy/listPzjl" : "/sino/dy/indexPzjl";
	}

	/**
	 * <p>
	 * 打印ETL品质管理表
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/pzjls", method = RequestMethod.POST)
	public @ResponseBody
	String pzjls(Date scsjBegin, Date scsjEnd) {
		if (scsjEnd == null) {
			scsjEnd = scsjBegin;
		}
		StringBuilder scsjs = new StringBuilder();
		StringBuilder pageSizes = new StringBuilder();
		int $pageSize = 0;
		Date start = scsjBegin;
		Date end = DateUtils.add(scsjEnd, Calendar.DAY_OF_MONTH, 1);
		while (start != null && start.before(end)) {
			$pageSize = bo.getPageSize(start, pageSize);
			if ($pageSize == 0) {
				start = DateUtils.add(start, Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			if (scsjs.length() == 0) {
				scsjs.append(DateUtils.format(start, "yyyy-MM-dd"));
			}
			else {
				scsjs.append(",").append(DateUtils.format(start, "yyyy-MM-dd"));
			}
			if (pageSizes.length() == 0) {
				pageSizes.append($pageSize);
			}
			else {
				pageSizes.append(",").append($pageSize);
			}
			start = DateUtils.add(start, Calendar.DAY_OF_MONTH, 1);
		}
		return "{scsjs:\"" + scsjs.toString() + "\",pageSizes:\""
				+ pageSizes.toString() + "\"}";
	}

	/**
	 * <p>
	 * 打印ETL品质管理表
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/pzjl!print")
	public String printPzjl(EtlpzglQE page, Integer pages, Model model) {
		page.setSize(pageSize);
		page.setOrderBys("scsj desc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("pages", pages);
		model.addAttribute("index", page.getIndex());
		return "/sino/dy/pzjl!print";
	}

	/**
	 * <p>
	 * 打印ETL药液管理记录
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/yygl!print")
	public String printYygl(Date jlsj, Model model) {
		Etlyygljl entity = etlyyglBO.getByJlsj(jlsj);
		EtlyyglQE page = new EtlyyglQE();
		page.setSize(-1);
		Calendar c = Calendar.getInstance();
		c.setTime(jlsj);
		c.add(Calendar.HOUR_OF_DAY, 7);
		c.add(Calendar.MINUTE, 30);
		page.setRqBegin(c.getTime());
		page.setRqEnd(c.getTime());
		etlyyglBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("count", page.getDatas().size());
		model.addAttribute("entity", entity);
		model.addAttribute("pageSize", 28);
		return "/sino/dy/yygl!print";
	}

	public IEtlpzglBO getBo() {
		return bo;
	}

	public void setBo(IEtlpzglBO bo) {
		this.bo = bo;
	}

	public IEtlyyglBO getEtlyyglBO() {
		return etlyyglBO;
	}

	public void setEtlyyglBO(IEtlyyglBO etlyyglBO) {
		this.etlyyglBO = etlyyglBO;
	}

}
