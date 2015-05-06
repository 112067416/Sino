package com.quanta.sino.etl.web;

import java.util.Arrays;

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
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IMgBO;
import com.quanta.sino.etl.vo.DmQE;
import com.quanta.sino.etl.vo.MgVO;
import com.quanta.sino.orm.Dmgs;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 端板控制器
 * </p>
 * <p>
 * create: 2011-1-20 下午08:12:35
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/etl/mg")
public class MgController {

	@Autowired
	private IMgBO bo;
	@Autowired
	private IZsBO zsBo;
	@Autowired
	private IScxbBO scBO;

	/**
	 * <p>
	 * 端板登录
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toCreate", method = RequestMethod.GET)
	public String toCreate(Model model) {
		User user = Context.currentUser();
		model.addAttribute("ban", user.getShift());
		model.addAttribute("zu", user.getGroup());
		model.addAttribute("n", "N");
		return "sino/etl/mg/create";
	}

	/**
	 * <p>
	 * 查询订货DB的附着量和制品尺寸
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getZsdh", method = RequestMethod.POST)
	public @ResponseBody
	String getLr(String zsno, Model model) {
		User user = Context.currentUser();
		if (zsno == null) {
			throw new CocoException(-1, "指示书号为空");
		}
		ZsdhTp zsdh = zsBo.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "取指示书为空");
		}
		MgVO mg = new MgVO();
		mg.setZsno(zsdh.getZsno());
		mg.setKuan(zsdh.getKuan());
		mg.setCang(zsdh.getCang());
		mg.setDmfx(zsdh.getDmfx());
		mg.setKbao(zsdh.getKbao());
		mg.setBan(user.getShift());
		mg.setZu(user.getGroup());
		return new Result(mg).toJsObject();
	}

	/**
	 * <p>
	 * 保存个数
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(MgVO entity) {
		bo.save(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除
	 * </p>
	 * 
	 * @param coilNos
	 *            钢卷号(制品在库DB)组合字符串
	 * @return
	 */
	@RequestMapping(value = "/delete!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id, Model model) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查询垫木个数
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getDmgs", method = RequestMethod.POST)
	public @ResponseBody
	String getZpzl(Double kuan, Double cang, String dmfx, String kbao,
			String kw, Model model) {
		Dmgs dm = bo.getUnique(kuan, cang, dmfx, kbao, kw);
		Integer dmgsAll = 0;
		if (dm != null && dm.getDmgs() != null) {
			dmgsAll = dm.getDmgs();

		}
		return new Result(dmgsAll).toString();
	}

	/**
	 * <p>
	 * 查询垫木个数
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getDmkz", method = RequestMethod.POST)
	public @ResponseBody
	String getDmkz(Double kuan, Double cang, String dmfx, String kbao,
			String kw, Model model) {
		Dmgs dm = bo.getUnique(kuan, cang, dmfx, kbao, kw);
		return new Result(dm).toJsObject();
	}

	/**
	 * <p>
	 * 库存查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index!{type}")
	public String index(@PathVariable String type, DmQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if ("1".equals(type)) {
			page.setKw(ZtConstants.MG_KW);
		}
		page.setOrderBys(" kuan asc,cang asc,dmfx asc,kbao asc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return page.isQuery() ? "/sino/etl/mg/list" : "/sino/etl/mg/index";
	}

	/**
	 * <p>
	 * 加载采库存
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id, Model model) {
		Dmgs dm = bo.get(id);
		return new Result(dm).toJsObject();
	}

	/**
	 * <p>
	 * 修改合同
	 * </p>
	 * 
	 * @param vo
	 *            采购合同保存下拉框实体
	 * @param $htno
	 * @param $line
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(Dmgs vo) {
		bo.update(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 库存移动查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexMove")
	public String indexMove(DmQE page, Model model) {
		User user = Context.currentUser();
		model.addAttribute("ban", user.getShift());
		model.addAttribute("zu", user.getGroup());
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys(" kuan asc,cang asc,dmfx asc,kbao asc");
		bo.query(page);
		model.addAttribute("page", page);

		return page.isQuery() ? "/sino/etl/mg/listMove"
				: "/sino/etl/mg/indexMove";
	}

	/**
	 * <p>
	 * 保存移动
	 * </p>
	 * 
	 * @param ids
	 *            指示书号数组
	 * @return String
	 */
	@RequestMapping(value = "/saveMove", method = RequestMethod.POST)
	public @ResponseBody
	String saveMove(String[] sorts, String ban, String zu, String dann) {
		bo.saveMove(Arrays.asList(sorts), ban, zu, dann);
		return Result.SUCCESS;
	}

	public IMgBO getBo() {
		return bo;
	}

	public void setBo(IMgBO bo) {
		this.bo = bo;
	}

	public IZsBO getZsBo() {
		return zsBo;
	}

	public void setZsBo(IZsBO zsBo) {
		this.zsBo = zsBo;
	}

	public IScxbBO getScBO() {
		return scBO;
	}

	public void setScBO(IScxbBO scBO) {
		this.scBO = scBO;
	}

}
