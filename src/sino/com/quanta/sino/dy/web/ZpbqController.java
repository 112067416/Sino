package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.dy.bo.api.IPrintLogBO;
import com.quanta.sino.dy.bo.api.IZpbqBO;
import com.quanta.sino.dy.constants.PrintType;
import com.quanta.sino.dy.vo.ZpbqVO;
import com.quanta.sino.etl.bo.api.IZpBO;

/**
 * 制品标签打印
 * 
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sino/dy")
public class ZpbqController {

	@Autowired
	private IZpbqBO bo;

	@Autowired
	private IZpBO zpBo;

	@Autowired
	private IPrintLogBO printBo;

	@RequestMapping(value = "/zpbq")
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
		return "/sino/dy/zpbq";
	}

	@RequestMapping(value = "/zpbq!list")
	public String list(String dhno, Model model) {
		model.addAttribute("items", zpBo.listZpByDh(dhno, EXpzk.JZP.key, EXpzk.BZP.key));
		return "/sino/dy/zpbq!list";
	}

	@RequestMapping(value = "/zpbq!print")
	public String print(String jbno, Model model) {
		ZpbqVO vo = bo.get(jbno);
		if (vo != null) {
			model.addAttribute("item", vo);
			User user = Context.currentUser();
			printBo.save(new String[] { jbno }, PrintType.ZPNG.type, user != null ? user.getId()
					: null);
		}
		return "/sino/dy/zpbq!print";
	}

	/**
	 * @return the bo
	 */
	public IZpbqBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZpbqBO bo) {
		this.bo = bo;
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

}
