package com.quanta.sino.sl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.exception.CocoException;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.vo.ZsViewVO;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.sl.bo.api.ISlBO;
import com.quanta.sino.sl.vo.SLSjSaveVO;
import com.quanta.sino.sl.vo.SjzkVO;
import com.quanta.sino.sl.vo.SjzsVO;
import com.quanta.sino.sl.vo.SllrVO;
import com.quanta.sino.sl.vo.SlsjQE;

/**
 * SL实绩控件器
 * 
 * @author 方元龙
 */
@Controller
@RequestMapping("/sino/sl")
public class SlController {

	@Autowired
	private ISlBO bo;
	@Autowired
	private IEtlBO etlBO;
	@Autowired
	private IScxbBO scxBo;

	public IScxbBO getScxBo() {
		return scxBo;
	}

	public void setScxBo(IScxbBO scxBo) {
		this.scxBo = scxBo;
	}

	public ISlBO getBo() {
		return bo;
	}

	public void setBo(ISlBO bo) {
		this.bo = bo;
	}

	/**
	 * <p>
	 * 实绩录入初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lr", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String lr(Model model) {
		User user = Context.currentUser();
		SllrVO vo = bo.lead(user);
		model.addAttribute("vo", vo);
		return "/sino/sl/lr";
	}

	/**
	 * <p>
	 * 查询出货重量
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getJszl", method = RequestMethod.POST)
	public @ResponseBody
	String getJszl(String zsno, Integer zshu, Model model) {
		Integer jszl = bo.getJszl(zsno, zshu);
		return new Result(jszl).toString();
	}

	/**
	 * <p>
	 * 校验用户输入的指示书号和入端卷号
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/check", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String check(SllrVO vo) {
		User user = Context.currentUser();
		SLSjSaveVO zp = bo.check(vo, user);
		return new Result(zp).toJsObject();
	}

	/**
	 * <p>
	 * 校验用户输入的指示书号和入端卷号
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getcheck", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String getcheck(SllrVO vo) {
		User user = Context.currentUser();
		SLSjSaveVO zp = bo.getcheck(vo, user);
		return new Result(zp).toJsObject();
	}

	/**
	 * <p>
	 * 查看实绩指示【业连、附页、备注】
	 * </p>
	 * 
	 * @param jbno
	 *            入端卷号
	 * @return
	 */
	@RequestMapping(value = "/viewSjzs", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String viewSjzs(String rczpno, Model model) {
		SjzsVO vo = bo.viewSjzs(rczpno);
		model.addAttribute("vo", vo);
		return "/sino/sl/sjzs";
	}

	/**
	 * <p>
	 * 查看指示书信息
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/viewzs!{id}", method = RequestMethod.POST)
	public String viewzx(@PathVariable String id, Model model) {
		model.addAttribute("item", etlBO.getZs(id, ProductType.sl.name));
		return "/sino/etl/sj/viewzs";
	}

	/**
	 * <p>
	 * 判断是否存在别纸或业联
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/getYLKNS!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getYLKNS(@PathVariable String id, Model model) {
		ZsViewVO zsVO = etlBO.getZs(id, ProductType.sl.name);
		if (zsVO.getKpns().size() > 0 || zsVO.getYlns().size() > 0) {
			return Result.SUCCESS;
		}
		else {
			return new Result(-1, "不存在别纸和业联").toString();
		}

	}

	/**
	 * <p>
	 * 新增保存实绩录入检查
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/savevalidate", method = RequestMethod.POST)
	public @ResponseBody
	String savevalidate(SLSjSaveVO sjsave, SjzkVO vo) {
		User user = Context.currentUser();
		// 界面给制品实体赋值
		bo.validateCheck(sjsave, vo, user, ZtConstants.ZPNG_CLQ_ADD);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 订正保存实绩录入检查
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/dzvalidate", method = RequestMethod.POST)
	public @ResponseBody
	String dzvalidate(SLSjSaveVO sjsave, SjzkVO vo) {
		User user = Context.currentUser();
		// 界面给制品实体赋值
		bo.validateCheck(sjsave, vo, user, ZtConstants.ZPNG_CLQ_MODY);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 检查实测宽和实测剪断长
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/checkKuanCang", method = RequestMethod.POST)
	public @ResponseBody
	String checkKuanCang(Double sckn, Double jdcn, String zsno) {
		bo.checkKuanCang(sckn, jdcn, zsno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 保存实绩
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String save(SLSjSaveVO sjsave, SjzkVO vo) {
		User user = Context.currentUser();
		bo.sjlrSave(sjsave, vo, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 实绩订正初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dz", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String dz(String jbno, Model model) {
		User user = Context.currentUser();
		// 生产线用户判定
		Scxbpz scx = scxBo.getByDept(user.getDeptId());
		if (scx == null || !ProductType.sl.code.equalsIgnoreCase(scx.getType())) {
			throw new CocoException(-1, "非剪切生产线人员不能操作此项功能");
		}
		// 设入初始板号
		model.addAttribute("scxName", scx.getName());
		if (jbno != null && !jbno.isEmpty()) {
			model.addAttribute("pileno", jbno);
		}
		return "/sino/sl/dz";
	}

	/**
	 * <p>
	 * 订正校验
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dzCheck", method = RequestMethod.POST)
	public @ResponseBody
	String dzCheck(String jbno) {
		User user = Context.currentUser();
		Result ret = bo.dzCheck(jbno, user);
		return ret.toJsObject();
	}

	/**
	 * <p>
	 * 订正/更新制品
	 * </p>
	 * 
	 * @param zp
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String update(ZpngTp dzzp) {
		User user = Context.currentUser();
		bo.sjdz(dzzp, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 实绩制品分页查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String query(SlsjQE page, Model model) {
		if (!page.isQuery()) {
			page.setSize(15);
		}
		page.setIspssd(true);
		page.setOrderBys("crea desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/sl/list" : "/sino/sl/index";
	}

	/**
	 * <p>
	 * 查看制品实绩情况
	 * </p>
	 * 
	 * @param zp
	 * @return
	 */
	@RequestMapping(value = "/view", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String view(String jbno, Model model) {
		if (jbno == null) {
			return Result.ERROR;
		}
		ZpngTp zp = bo.view(jbno);
		model.addAttribute("zp", zp);
		return "/sino/sl/view";
	}

	/**
	 * <p>
	 * 删除实绩制品
	 * </p>
	 * 
	 * @param jbno
	 *            板材NO
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String delete(String jbno) {
		User user = Context.currentUser();
		bo.delete(jbno, user);
		return Result.SUCCESS;
	}

	public IEtlBO getEtlBO() {
		return etlBO;
	}

	public void setEtlBO(IEtlBO etlBO) {
		this.etlBO = etlBO;
	}

}
