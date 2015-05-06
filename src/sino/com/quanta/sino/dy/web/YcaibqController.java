package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.dy.bo.api.IPrintLogBO;
import com.quanta.sino.dy.bo.api.IYcaibqBO;
import com.quanta.sino.dy.constants.PrintType;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;

/**
 * <p>
 * 原材打印控制器
 * </p>
 * <p>
 * create: 2010-12-21 下午06:44:08
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class YcaibqController {

	@Autowired
	private IYcaibqBO bo;

	@Autowired
	private IYcaitpBO ycauBo;

	@Autowired
	private IPrintLogBO printBo;

	/**
	 * <p>
	 * 查询原板信息
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/ycai")
	public String index(YcaiQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getSfdy() != null) {
			if ("Y".equals(page.getSfdy())) {
				page.setSfdy("sfdy=1");
			}
			else if ("N".equals(page.getSfdy())) {
				page.setSfdy("sdfy=0");
			}
		}
		page.setOrderBys("jbno desc");
		ycauBo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dy/ycai!list" : "/sino/dy/ycai";
	}

	/**
	 * <p>
	 * 获取整船原板信息
	 * </p>
	 * 
	 * @param ship
	 * @return String
	 */
	@RequestMapping(value = "/ycai!ship")
	public @ResponseBody
	String ship(String ship) {
		return bo.findByShip(ship);
	}

	/**
	 * <p>
	 * 打印
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/ycai!print")
	public String print(String[] jbno, Model model) {
		if (jbno == null || jbno.length == 0) {
			return "/sino/dy/ycai!print";
		}
		model.addAttribute("vo1", bo.get(jbno[0]));
		if (jbno.length > 1) {
			model.addAttribute("vo2", bo.get(jbno[1]));
		}
		User user = Context.currentUser();
		printBo.save(jbno, PrintType.YCAI.type, user != null ? user.getId()
				: null);
		return "/sino/dy/ycai!print";
	}

	/**
	 * @return the bo
	 */
	public IYcaibqBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IYcaibqBO bo) {
		this.bo = bo;
	}

	/**
	 * @return the ycauBo
	 */
	public IYcaitpBO getYcauBo() {
		return ycauBo;
	}

	/**
	 * @param ycauBo
	 *            the ycauBo to set
	 */
	public void setYcauBo(IYcaitpBO ycauBo) {
		this.ycauBo = ycauBo;
	}

	public IPrintLogBO getPrintBo() {
		return printBo;
	}

	public void setPrintBo(IPrintLogBO printBo) {
		this.printBo = printBo;
	}

}
