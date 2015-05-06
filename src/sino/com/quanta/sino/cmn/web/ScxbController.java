package com.quanta.sino.cmn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.vo.ScxbpzFormVO;
import com.quanta.sino.cmn.vo.ScxbpzQE;

/**
 * <p>
 * 生产线别配置控制层
 * </p>
 * <p>
 * create: 2011-1-6 上午09:21:36
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cmn/scxb")
public class ScxbController {

	@Autowired
	private IScxbBO bo;

	public ScxbController() {
	}

	/**
	 * <p>
	 * 主页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(Model model) {
		listEtl(model);
		listSl(model);
		model.addAttribute("etlType", ProductType.etl.code);
		model.addAttribute("slType", ProductType.sl.code);
		return "/sino/cmn/scxb/index";
	}

	/**
	 * <p>
	 * ETL生产线别配置列表
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/listEtl", method = { RequestMethod.POST })
	public String listEtl(Model model) {
		ScxbpzQE qentity = new ScxbpzQE();
		qentity.setType(ProductType.etl.code);
		qentity.setSize(-1);
		bo.query(qentity);
		model.addAttribute("etls", qentity.getDatas());
		return "/sino/cmn/scxb/listEtl";
	}

	/**
	 * <p>
	 * SL生产线别配置列表
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/listSl", method = { RequestMethod.POST })
	public String listSl(Model model) {
		ScxbpzQE qentity = new ScxbpzQE();
		qentity.setType(ProductType.sl.code);
		qentity.setSize(-1);
		bo.query(qentity);
		model.addAttribute("sls", qentity.getDatas());
		return "/sino/cmn/scxb/listSl";
	}

	/**
	 * <p>
	 * 保存生产线别配置
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public @ResponseBody
	String save(ScxbpzFormVO vo) {
		bo.save(vo);
		return Result.SUCCESS;
	}

	/**
	 * @return the bo
	 */
	public IScxbBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IScxbBO bo) {
		this.bo = bo;
	}

}