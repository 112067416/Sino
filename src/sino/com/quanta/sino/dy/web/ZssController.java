package com.quanta.sino.dy.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.util.DateUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.bo.api.IZssBO;
import com.quanta.sino.dy.vo.LosscVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.util.SinoUtils;

@Controller
@RequestMapping("/sino/dy")
public class ZssController {

	@Autowired
	private IZssBO bo;

	@Autowired
	private IZpBO zpBo;

	@Autowired
	private ICodeBO codeBO;

	@Autowired
	private IDhBO dhbo;

	/**
	 * <p>
	 * 进入不良扣除单打印页面
	 * </p>
	 * 
	 * @param current
	 * @param dhno
	 * @param line
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/lossc")
	public String lossc(String jbno, Model model) {
		ZpngTp zp = zpBo.getZp(jbno);
		zp.setUpda(new Date());
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(zp.getDhno());
		DhuoTp dhuo = dhbo.get(dhpk);
		CodeItem code = codeBO.getCodeItem(CmnConstants.SINO_QX_ZC, zp.getLosq());
		if (code != null) {
			zp.setLosq(code.getValue());
		}
		if (code != null && dhuo != null && CodeNwx.CK.equals(dhuo.getNwai())) {
			zp.setLosq(code.getRemark());
		}
		code = codeBO.getCodeItem(CmnConstants.SINO_QX_ZC, zp.getLosq2());
		if (code != null) {
			zp.setLosq2(code.getValue());
		}
		if (code != null && dhuo != null && CodeNwx.CK.equals(dhuo.getNwai())) {
			zp.setLosq2(code.getRemark());
		}
		model.addAttribute("item", zp);
		int total = zp.getLosc2() != null && zp.getLosc2() > 0 ? 2 : 1;
		model.addAttribute("total", total);
		model.addAttribute("name", dhuo != null ? dhuo.getName() : null);
		return "/sino/dy/lossc";

	}

	/**
	 * <p>
	 * 打印不良扣除单
	 * </p>
	 * 
	 * @param jbno
	 * @param lxx
	 * @param jdx
	 * @param lx
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/lossc!print", method = RequestMethod.POST)
	public @ResponseBody
	String losscPrint(String jbno, String lxx, String jdx, String lx,
			int index, Model model) {
		ZpngTp zp = zpBo.getZp(jbno);
		zpBo.updateEtlLodyPrint(jbno);
		zp.setUpda(new Date());
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(zp.getDhno());
		DhuoTp dhuo = dhbo.get(dhpk);
		CodeItem code = null;
		LosscVO vo = new LosscVO();
		if (index == 1) {
			code = codeBO.getCodeItem(CmnConstants.SINO_QX_ZC, zp.getLosq());
			vo.setLosc(zp.getLosc());
		}
		else {
			code = codeBO.getCodeItem(CmnConstants.SINO_QX_ZC, zp.getLosq2());
			vo.setLosc(zp.getLosc2());
		}
		if (code != null) {
			vo.setLosq(code.getValue());
		}
		if (code != null && dhuo != null && CodeNwx.CK.equals(dhuo.getNwai())) {
			vo.setLosq(code.getRemark());
		}
		vo.setUpda(DateUtils.format(zp.getUpda() != null ? zp.getUpda()
				: new Date(), "yyyy-MM-dd"));
		vo.setJbno(zp.getJbno());
		vo.setZzsd(zp.getZzsd());
		vo.setJbcn(zp.getJbcn());
		vo.setName(dhuo != null ? dhuo.getName() : null);
		vo.setLxx("Y".equals(lxx) ? "V" : "");
		vo.setJdx("Y".equals(jdx) ? "V" : "");
		vo.setLx("Y".equals(lx) ? "V" : "");
		return new Result(vo).toJsObject();
		// model.addAttribute("item", zp);
		// model.addAttribute("name", dhuo != null ? dhuo.getName() : null);
		// model.addAttribute("lxx", "Y".equals(lxx) ? true : false);
		// model.addAttribute("jdx", "Y".equals(jdx) ? true : false);
		// model.addAttribute("lx", "Y".equals(lx) ? true : false);
		// return "/sino/dy/lossc!print";

	}

	@RequestMapping(value = "/zss!{type}")
	public String index(@PathVariable String type, String zsno, Model model) {
		model.addAttribute("type", type);
		model.addAttribute("zsno", zsno);
		return "/sino/dy/zss";
	}

	@RequestMapping(value = "/zss!{type}!print")
	public String print(@PathVariable String type, String zsno, Model model) {
		ProductType productType = ProductType.get(type);
		model.addAttribute("item", bo.get(zsno, productType));
		model.addAttribute("dysj", new Date());
		model.addAttribute("type", productType.name);
		return "/sino/dy/zss!print";
	}

	/**
	 * @return the bo
	 */
	public IZssBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZssBO bo) {
		this.bo = bo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public IDhBO getDhbo() {
		return dhbo;
	}

	public void setDhbo(IDhBO dhbo) {
		this.dhbo = dhbo;
	}

}
