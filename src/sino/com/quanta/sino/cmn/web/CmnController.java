package com.quanta.sino.cmn.web;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanta.sino.cmn.bo.api.ICmnBO;

/**
 * <p>
 * 中日达生产公共模块控制器
 * </p>
 * <p>
 * create: 2011-1-12 上午10:06:41
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cmn")
public class CmnController {

	@Autowired
	private ICmnBO bo;

	/**
	 * <p>
	 * 查看制品
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zp/view", method = RequestMethod.GET)
	public String viewZp(String jbno, Model model) {
		model.addAttribute("jbno", jbno);
		return "/sino/cmn/zp/view";
	}

	/**
	 * <p>
	 * 制品树型结构
	 * </p>
	 * 
	 * @param jbno
	 * @return Document
	 */
	@RequestMapping(value = "/zp/tree", method = RequestMethod.GET)
	public @ResponseBody
	Document treeZp(String jbno) {
		return bo.treeNo(jbno);
	}

	/**
	 * @return the bo
	 */
	public ICmnBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(ICmnBO bo) {
		this.bo = bo;
	}
}
