package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.dy.bo.api.IPrintLogBO;
import com.quanta.sino.dy.bo.api.IZpdbbqBO;
import com.quanta.sino.dy.constants.PrintType;
import com.quanta.sino.dy.vo.ZpdbbqVO;
import com.quanta.sino.etl.bo.api.IZpBO;

/**
 * 制品端板标签打印
 * 
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sino/dy")
public class ZpdbbqController {

	@Autowired
	private IZpdbbqBO bo;

	@Autowired
	private IZpBO zpBo;

	@Autowired
	private IPrintLogBO printBo;

	@RequestMapping(value = "/dbbq")
	public String index(String jbnos, Model model) {
		StringBuilder sb = new StringBuilder();
		if (jbnos != null && !jbnos.isEmpty()) {
			String[] arr = jbnos.split(",");
			sb.append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				sb.append(",").append(arr[i]);
			}
		}
		model.addAttribute("jbnos", sb.toString());
		return "/sino/dy/zpdbbq";
	}

	@RequestMapping(value = "/dbbq!list")
	public String list(String dhno, Model model) {
		model.addAttribute("items", zpBo.listZpByDh(dhno, EXpzk.JZP.key, EXpzk.BZP.key));
		return "/sino/dy/zpbq!list";
	}

	@RequestMapping(value = "/dbbq!print")
	public String print(String jbno, Model model) {
		ZpdbbqVO vo = bo.get(jbno);
		if (vo != null) {
			model.addAttribute("item", vo);
			User user = Context.currentUser();
			printBo.save(new String[] { jbno }, PrintType.DBBJ.type, user != null ? user.getId()
					: null);
		}
		return "/sino/dy/zpdbbq!print";
	}

	/**
	 * @return the zpBo
	 */
	public IZpBO getZpBo() {
		return zpBo;
	}

	/**
	 * @param zpBo
	 *            the zpBo to set
	 */
	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IPrintLogBO getPrintBo() {
		return printBo;
	}

	public void setPrintBo(IPrintLogBO printBo) {
		this.printBo = printBo;
	}

	public IZpdbbqBO getBo() {
		return bo;
	}

	public void setBo(IZpdbbqBO bo) {
		this.bo = bo;
	}

}
