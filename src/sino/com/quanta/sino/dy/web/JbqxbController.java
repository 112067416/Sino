package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanta.sino.dy.bo.api.IJbqxbBO;
import com.quanta.sino.dy.vo.JbqxbDataVO;

@Controller
@RequestMapping("/sino/dy")
public class JbqxbController {

	@Autowired
	private IJbqxbBO bo;

	@RequestMapping(value = "/jbqxb")
	public String index(JbqxbDataVO vo, Model model) {
		model.addAttribute("vo", vo);
		return "/sino/dy/jbqxb";
	}

	@RequestMapping(value = "/jbqxb!print")
	public @ResponseBody
	String print(JbqxbDataVO vo) {
		return bo.getForJs(vo);
	}

	/**
	 * @return the bo
	 */
	public IJbqxbBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IJbqxbBO bo) {
		this.bo = bo;
	}

}
