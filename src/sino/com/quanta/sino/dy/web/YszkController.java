package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.vo.CzglQE;

/**
 * <p>
 * 应收账款管理打印类
 * </p>
 * <p>
 * create: 2011-7-4 下午12:55:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class YszkController {

	/**
	 * 付款发票bo
	 */
	@Autowired
	private IFkfpBO fpBo;

	/**
	 * 每面打印的生产卷条数
	 */
	private static int pageSize = 15;

	/**
	 * <p>
	 * 付款发票冲账明细打印入口
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/czmxs", method = RequestMethod.POST)
	public @ResponseBody
	String czmxs(String id) {
		int $pageSize = fpBo.getPageSize(id, pageSize);
		if ($pageSize <= 0) {
			return new Result(-1, "该笔付款记录还未冲帐").toString();
		}
		return "{pageSizes:\"" + $pageSize + "\"}";
	}

	/**
	 * <p>
	 * 付款发票打印冲账明细
	 * </p>
	 * 
	 * @param page
	 * @param pages
	 * @param model
	 * @return String
	 */
	@RequestMapping("/czmx!print")
	public String printCzmx(CzglQE page, Integer pages, Model model) {
		page.setSize(pageSize);
		page.setOrderBys("fkfp.chri asc, fkfp.dhno asc, fkfp.line asc, fkfp.id desc");
		fpBo.queryCzgl(page);
		double fpje = 0, fkje = 0, wsyk = 0, kfzl = 0;
		if (page.getDatas().size() > 0) {
			for (Czgl item : page.getDatas()) {
				fpje += (item.getFkfp().getFpje() != null ? item.getFkfp().getFpje()
						: 0d);
				fkje += (item.getFkje() != null ? item.getFkje() : 0d);
				wsyk += (item.getWsyk() != null ? item.getWsyk() : 0d);
				kfzl += (item.getFkfp().getKfzl() != null ? item.getFkfp().getKfzl()
						: 0d);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("pages", pages);
		model.addAttribute("index", page.getIndex());
		model.addAttribute("entity", fpBo.getKhfk(page.getKhfkId()));
		model.addAttribute("fpje", fpje);
		model.addAttribute("fkje", fkje);
		model.addAttribute("wsyk", wsyk);
		model.addAttribute("kfzl", kfzl);
		return "/sino/dy/czmx!print";
	}

	public IFkfpBO getFpBo() {
		return fpBo;
	}

	public void setFpBo(IFkfpBO fpBo) {
		this.fpBo = fpBo;
	}

}
