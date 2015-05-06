package com.quanta.sino.yd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.yd.bo.api.IYdBO;
import com.quanta.sino.yd.vo.GxVO;
import com.quanta.sino.yd.vo.YdQE;

/**
 * <p>
 * 硬度维护控制器
 * </p>
 * <p>
 * create: 2011-1-28 下午02:54:36
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yd")
public class YdController {

	/**
	 * 硬度维护业务
	 */
	@Autowired
	private IYdBO bo;

	/**
	 * <p>
	 * 实绩录入初始
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String query(YdQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			page.setYing(true);
		}
		String[] stats = { YbStat.SJLR.stat, YbStat.SJZL.stat, YbStat.SJZZ.stat };
		page.setStats(stats);
		if (page.getUserBegin() != null && !page.getUserBegin().isEmpty()
				&& (page.getUserEnd() == null || page.getUserEnd().isEmpty())) {
			page.setUser(page.getUserBegin());
			page.setUserBegin(null);
		}
		page.setOrderBys("SJSJ_ desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/yd/list" : "/sino/yd/index";
	}

	/**
	 * <p>
	 * 校验用户输入的硬度
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/checkYd", method = RequestMethod.POST)
	public @ResponseBody
	String checkYd(GxVO vo) {
		return bo.checkYd(vo);
	}

	/**
	 * <p>
	 * 更新硬度【code = -999时，表示硬度超出范围，其他表示异常】
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/updateYd", method = RequestMethod.POST)
	public @ResponseBody
	String updateYd(GxVO vo) {
		return bo.updateYd(vo);
	}

	/**
	 * <p>
	 * 加载硬度更新信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String jbno) {
		return bo.getYdgxjlForJS(jbno);
	}

	public IYdBO getBo() {
		return bo;
	}

	public void setBo(IYdBO bo) {
		this.bo = bo;
	}
}
