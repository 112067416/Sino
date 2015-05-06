package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanta.sino.dy.bo.api.IZpkBO;
import com.quanta.sino.dy.vo.ZpkDataVO;
import com.quanta.sino.etl.bo.api.IZpBO;

/**
 * 制品卡打印
 * 
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sino/dy")
public class ZpkController {

	@Autowired
	private IZpkBO bo;

	@Autowired
	private IZpBO zpBo;

	@RequestMapping(value = "/zpk")
	public String index(String jbnos, Model model) {
		StringBuilder sb = new StringBuilder();
		if (jbnos != null && !jbnos.isEmpty()) {
			String[] arr = jbnos.split(",");
			sb.append("jbnos=").append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				sb.append("&jbnos=").append(arr[i]);
			}
		}
		model.addAttribute("jbnos", sb.toString());
		return "/sino/dy/zpk";
	}

	@RequestMapping(value = "/zpdbk")
	public String indexdb(String jbnos, Model model) {
		StringBuilder sb = new StringBuilder();
		if (jbnos != null && !jbnos.isEmpty()) {
			String[] arr = jbnos.split(",");
			sb.append("jbnos=").append(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				sb.append("&jbnos=").append(arr[i]);
			}
		}
		model.addAttribute("jbnos", sb.toString());
		return "/sino/dy/zpdbk";
	}

	@RequestMapping(value = "/zpk!list", method = RequestMethod.POST)
	public String list(String dhno, Model model) {
		model.addAttribute("items", zpBo.listZpByDh(dhno));
		return "/sino/dy/zpk!list";
	}

	@RequestMapping(value = "/zpdbk!list", method = RequestMethod.POST)
	public String listdb(String dhno, Model model) {
		model.addAttribute("items", zpBo.listZpByDh(dhno));
		return "/sino/dy/zpdbk!list";
	}

	@RequestMapping(value = "/zpk!print", method = RequestMethod.POST)
	public @ResponseBody
	String print(ZpkDataVO vo) {
		return bo.getForJs(vo);
	}

	@RequestMapping(value = "/zpdbk!print", method = RequestMethod.POST)
	public @ResponseBody
	String printdb(ZpkDataVO vo) {
		return bo.getdbForJs(vo);
	}

	/**
	 * @return the bo
	 */
	public IZpkBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZpkBO bo) {
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

}
