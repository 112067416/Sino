package com.quanta.sino.dy.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.quanta.sino.ch.bo.api.IBzqdBO;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.vo.JczmsItemQE;
import com.quanta.sino.ch.vo.ZxzsDyQE;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZxzsTp;

/**
 * <p>
 * 出货管理模块打印控制器
 * </p>
 * <p>
 * create: 2011-5-7 下午08:48:10
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy/chdy")
public class ChdyController {
	/**
	 * 装箱指示书业务接口
	 */
	@Autowired
	private IZxzsBO zBo;
	/**
	 * 检查证明书业务接口
	 */
	@Autowired
	private IJczmsBO jcBo;
	/**
	 * 订货单业务接口
	 */
	@Autowired
	private IDhBO dhBo;
	/**
	 * 订货单业务接口
	 */
	@Autowired
	private IZpBO zpBo;
	/**
	 * 包装清单业务接口
	 */
	@Autowired
	private IBzqdBO bzqdBo;
	/**
	 * 引入代码业务层bo
	 */
	@Autowired
	private ICodeBO codeBo;

	/**
	 * <p>
	 * 装箱指示书打印入口
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/zxzs")
	public String zxzs(String zxno, Model model) {
		if (zxno == null || zxno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.不存在"));
			return Result.URL_ERROR;
		}
		int pageSize = zBo.getPageSize(zxno, ChglConstants.ZXZS_SIZE);
		if (pageSize == 0) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.为空"));
			return Result.URL_ERROR;
		}
		model.addAttribute("zxno", zxno);
		model.addAttribute("pageSize", pageSize);
		return "/sino/dy/chdy/zxzs";
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/zxzsA4", method = RequestMethod.POST)
	public @ResponseBody
	String zxzsA4(String ids, Model model) {
		String[] zxnos = ids.split(",");
		StringBuilder pageSizes = new StringBuilder();
		StringBuilder uuids = new StringBuilder();
		int pageSize = 0;
		for (String zxno : zxnos) {
			pageSize = zBo.getPageSize(zxno, ChglConstants.ZXZS_SIZE);
			if (uuids.length() == 0) {
				pageSizes.append(pageSize);
				uuids.append(zxno);
				continue;
			}
			pageSizes.append(",").append(pageSize);
			uuids.append(",").append(zxno);
		}
		return "{uuids:\"" + uuids.toString() + "\",pageSizes:\""
				+ pageSizes.toString() + "\"}";
	}

	/**
	 * <p>
	 * 装箱指示书打印
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/zxzs!print")
	public String zxzsPrint(String zxno, Integer current, Integer pages,
			Model model) {
		if (zxno == null || zxno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.为空"));
			return Result.URL_ERROR;
		}
		Zng1Tp zng1Tp = zBo.getUnique(zxno);
		if (zng1Tp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.为空"));
			return Result.URL_ERROR;
		}
		DhuoTp dhuoTp = dhBo.get(new DhuoTpPk(zng1Tp.getDhno(),
				zng1Tp.getLine()));
		if (dhuoTp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"装箱指示书对应的订货合同不存在"));
			return Result.URL_ERROR;
		}
		ZxzsDyQE page = new ZxzsDyQE();
		page.setSize(ChglConstants.ZXZS_SIZE);
		page.setIndex(current);
		page.setOrderBys("b.dhno asc, b.line asc, a.jbno asc");
		page.setZxno(zxno);
		zBo.queryZxzsMx(page);
		model.addAttribute("page", page);
		model.addAttribute("zng1Tp", zng1Tp);
		model.addAttribute("dhuoTp", dhuoTp);
		model.addAttribute("pageSize", ChglConstants.ZXZS_SIZE);
		model.addAttribute("current", current);
		model.addAttribute("pages", pages);
		model.addAttribute("date", new Date());
		return "/sino/dy/chdy/zxzs!print";
	}

	/**
	 * <p>
	 * 装箱指示书打印
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/zxzs!printA4")
	public String zxzsPrintA4(String zxno, Integer current, Integer pages,
			Model model) {
		if (zxno == null || zxno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.为空"));
			return Result.URL_ERROR;
		}
		Zng1Tp zng1Tp = zBo.getUnique(zxno);
		if (zng1Tp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书No.为空"));
			return Result.URL_ERROR;
		}
		DhuoTp dhuoTp = dhBo.get(new DhuoTpPk(zng1Tp.getDhno(),
				zng1Tp.getLine()));
		if (dhuoTp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"装箱指示书对应的订货合同不存在"));
			return Result.URL_ERROR;
		}
		ZxzsDyQE page = new ZxzsDyQE();
		page.setSize(ChglConstants.ZXZS_SIZE);
		page.setIndex(current);
		page.setOrderBys("b.dhno asc, b.line asc, a.jbno asc");
		page.setZxno(zxno);
		zBo.queryZxzsMx(page);
		model.addAttribute("page", page);
		model.addAttribute("zng1Tp", zng1Tp);
		model.addAttribute("dhuoTp", dhuoTp);
		model.addAttribute("pageSize", ChglConstants.ZXZS_SIZE);
		model.addAttribute("current", current);
		model.addAttribute("pages", pages);
		model.addAttribute("date", new Date());
		return "/sino/dy/chdy/zxzs!printA4";
	}

	/**
	 * <p>
	 * 制品检查证明书打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/jczms")
	public String jczms(String[] ids, Model model) {
		if (ids == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "装箱指示书号不能为空"));
			return Result.URL_ERROR;
		}
		List<Jczms> jczmss = jcBo.find(ids);
		if (jczmss.size() == 0) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"该装箱指示书没有制品检查证明书"));
			return Result.URL_ERROR;
		}
		String pageSizes = null;
		String uuids = null;
		int pageSize = 0;
		for (Jczms jczms : jczmss) {
			pageSize = jcBo.getPageSize(jczms.getId(), ChglConstants.JCZMS_SIZE);
			if (uuids == null) {
				for (int i = 0; i < jczms.getJcha(); i++) {
					if (uuids == null) {
						pageSizes = String.valueOf(pageSize);
						uuids = jczms.getId();
						continue;
					}
					pageSizes += "," + String.valueOf(pageSize);
					uuids += "," + jczms.getId();
				}
				continue;
			}
			for (int i = 0; i < jczms.getJcha(); i++) {
				pageSizes += "," + String.valueOf(pageSize);
				uuids += "," + jczms.getId();
			}
		}
		model.addAttribute("uuids", uuids);
		model.addAttribute("pageSizes", pageSizes);
		return "/sino/dy/chdy/jczms";
	}

	/**
	 * <p>
	 * 制品检查证明书打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/toJczms", method = RequestMethod.POST)
	public @ResponseBody
	String toJczms(String ids) {
		List<Jczms> jczmss = jcBo.find(ids.split(","));
		String pageSizes = null;
		String uuids = null;
		int pageSize = 0;
		for (Jczms jczms : jczmss) {
			if (jczms.getDhno() == null || jczms.getDhno().indexOf("W") >= 0) continue;
			pageSize = jcBo.getPageSize(jczms.getId(), ChglConstants.JCZMS_SIZE);
			if (uuids == null) {
				for (int i = 0; i < jczms.getJcha(); i++) {
					if (uuids == null) {
						pageSizes = String.valueOf(pageSize);
						uuids = jczms.getId();
						continue;
					}
					pageSizes += "," + String.valueOf(pageSize);
					uuids += "," + jczms.getId();
				}
				continue;
			}
			for (int i = 0; i < jczms.getJcha(); i++) {
				pageSizes += "," + String.valueOf(pageSize);
				uuids += "," + jczms.getId();
			}
		}
		return "{uuids:\"" + uuids + "\",pageSizes:\"" + pageSizes + "\"}";
	}

	/**
	 * <p>
	 * 制品检查证明书打印
	 * </p>
	 * 
	 * @param current
	 * @param jczmsId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/jczms!print")
	public String jczmsPrint(Integer current, String jczmsId, Integer pages,
			Model model) {
		if (jczmsId == null || jczmsId.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "缺少参数"));
			return Result.URL_ERROR;
		}
		Jczms jczms = jcBo.get(jczmsId);
		JczmsItemQE page = new JczmsItemQE();
		page.setJczmsId(jczmsId);
		page.setSize(ChglConstants.JCZMS_SIZE);
		page.setIndex(current);
		page.setOrderBys("zpno asc");
		jcBo.query(page);

		model.addAttribute("jczms", jczms);
		model.addAttribute("chzl", NumberUtils.formatDouToInt(jczms.getChzl() * 1000, 0));
		model.addAttribute("chsu", jczms.getChsu());
		model.addAttribute("page", page);
		model.addAttribute("date", new Date());
		model.addAttribute("pageSize", ChglConstants.JCZMS_SIZE);
		model.addAttribute("pages", pages);
		model.addAttribute("current", current);
		return "/sino/dy/chdy/jczms!print";
	}

	/**
	 * <p>
	 * 送货单打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping("/shd")
	public String shd(String ids[], Model model) {
		if (ids == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "送货单号不能为空"));
			return Result.URL_ERROR;
		}
		String pageSizes = null;
		String zxnos = null;
		for (int i = 0; i < ids.length; i++) {
			if (pageSizes == null) {
				pageSizes = String.valueOf(zBo.getPageSize(ids[i], ChglConstants.SHD_SIZE));
				zxnos = ids[i];
				continue;
			}
			pageSizes += ","
					+ String.valueOf(zBo.getPageSize(ids[i], ChglConstants.SHD_SIZE));
			zxnos += "," + ids[i];
		}
		if (pageSizes.split(",").length == 0 || "0".equals(pageSizes)) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "制品数据为空"));
			return Result.URL_ERROR;
		}
		// CodeItem codeItem = codeBo.getCodeItem(CmnConstants.SINO_DY_SHD,
		// "1");
		model.addAttribute("zxnos", zxnos);
		model.addAttribute("pageSizes", pageSizes);
		// model.addAttribute("dyr", codeItem != null ? codeItem.getValue() :
		// null);
		return "/sino/dy/chdy/shd";
	}

	/**
	 * <p>
	 * 送货单打印
	 * </p>
	 * 
	 * @param current
	 * @param pages
	 * @param zxno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/shd!print")
	public String shdPrint(Integer current, Integer pages, String zxno,
			String dyr, Model model) {
		if (zxno == null || zxno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "缺少参数"));
			return Result.URL_ERROR;
		}
		ZxzsTp zxzsTp = zBo.get(zxno);
		ZxzsDyQE page = new ZxzsDyQE();
		page.setSize(ChglConstants.SHD_SIZE);
		page.setIndex(current);
		page.setOrderBys("b.dhno,b.line,a.jbno asc");
		page.setZxno(zxno);
		zBo.queryZxzsMx(page);
		Zng2Tp zng2Tp = null;
		Zng1Tp zng1Tp = null;
		DhuoTp dhuoTp = null;
		if (page.getDatas().size() > 0) {
			zng2Tp = page.getDatas().get(0);
			zng1Tp = zng2Tp.getZng1Tp();
			dhuoTp = dhBo.get(new DhuoTpPk(zng2Tp.getZng1Tp().getDhno(),
					zng2Tp.getZng1Tp().getLine()));
		}
		if (pages == current + 1) {
			model.addAttribute("hjzs", zBo.getHjzs(zxno));
		}
		model.addAttribute("page", page);
		model.addAttribute("zxzsTp", zxzsTp);
		model.addAttribute("zng1Tp", zng1Tp);
		model.addAttribute("dhuoTp", dhuoTp);
		model.addAttribute("pageSize", ChglConstants.SHD_SIZE);
		model.addAttribute("current", current);
		model.addAttribute("date", new Date());
		model.addAttribute("pages", pages);
		model.addAttribute("dyr", dyr);
		return "/sino/dy/chdy/shd!print";

	}

	/**
	 * <p>
	 * 加载信息
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zsno
	 * @param dhno
	 * @param line
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String zxno, String dhno, String line) {
		return bzqdBo.get(dhno, line, zxno);
	}

	/**
	 * <p>
	 * 查询制品
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/pageZp")
	public String pageZp(ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		model.addAttribute("size", ChglConstants.BZQD_SIZE);
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			return "/sino/dy/chdy/bzqd";
		}
		String[] xpzks = { EXpzk.JZP.key, EXpzk.BZP.key };
		// 设置现品在库
		page.setXpzks(xpzks);
		// 设置堆场
		String[] duics = { DC.C.name, DC.F.name, DC.G.name };
		page.setDuics(duics);
		// 分配/余材标记
		page.setFpyc(EFpyc.FP.key);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		page.setStat(ZpStat.CS.stat);
		page.setOrderBys("jbno asc");
		zpBo.query(page);
		int zpzl, bzcl, dmzl;
		for (ZpngTp zpngTp : page.getDatas()) {
			zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
			bzcl = zpngTp.getBzcl() != null ? zpngTp.getBzcl() : 0;
			dmzl = zpngTp.getDmzl() != null ? zpngTp.getDmzl() : 0;
			zpngTp.setMazl(zpzl + bzcl + dmzl);
		}
		model.addAttribute("page", page);
		return "/sino/dy/chdy/bzqd!list";
	}

	/**
	 * <p>
	 * 查询出货记录
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/pageCh")
	public String pageCh(ZxzsDyQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		model.addAttribute("size", ChglConstants.BZQD_SIZE);
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			return "/sino/dy/chdy/bzqd";
		}
		page.setOrderBys("a.jbno asc");
		zBo.queryZxzsMx(page);
		model.addAttribute("page", page);
		return "/sino/dy/chdy/bzqd!list";
	}

	/**
	 * <p>
	 * 打印包装清单
	 * </p>
	 * 
	 * @param jbnos
	 * @param current
	 * @param pages
	 * @param zxno
	 * @param dhno
	 * @param line
	 * @param pm
	 * @param hjmz
	 * @param hjzl
	 * @param chsu
	 * @param model
	 * @return String
	 */
	@RequestMapping("/bzqd!print")
	public String bzqdPrint(String jbnos, Integer current, Integer pages,
			String zxno, String dhno, String line, String pm, Double hjmz,
			Double hjzl, Integer chsu, Model model) {
		if (jbnos == null || jbnos.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "制品为空"));
			return Result.URL_ERROR;
		}
		DhuoTp dhuoTp = dhBo.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"制品对应的订货合同部存在"));
			return Result.URL_ERROR;
		}
		// Double hjbz = 0d, hjjz = 0d;
		// if (Code118.coil.key.equals(dhuoTp.getPzno().substring(0, 1))) {
		// hjjz = hjzl;
		// }
		// else {
		// hjbz = hjzl;
		// }
		// 要打印的制品列表
		List<String> jbnoList = Arrays.asList(jbnos.split(","));
		List<PbzqdVO> vos = null;
		// 装箱指示书号为空则查制品
		if (zxno == null || zxno.isEmpty()) {
			vos = zpBo.findBzqdVos(jbnoList);
		}
		// 否则表示输入了装箱指示书号，那么就去装箱指示书中去查询
		else {
			vos = bzqdBo.findBzqdVos(jbnoList, zxno);
		}
		if (vos == null || vos.size() == 0) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "获取打印数据失败"));
			return Result.URL_ERROR;
		}
		model.addAttribute("vos", vos);
		model.addAttribute("pageSize", ChglConstants.BZQD_SIZE);
		model.addAttribute("dhuoTp", dhuoTp);
		model.addAttribute("date", new Date());
		model.addAttribute("current", current);
		model.addAttribute("pages", pages);
		model.addAttribute("pm", pm);
		model.addAttribute("hjmz", hjmz);
		// model.addAttribute("hjbz", hjbz);
		// model.addAttribute("hjjz", hjjz);
		model.addAttribute("hjzl", hjzl);
		model.addAttribute("chsu", chsu);
		return "/sino/dy/chdy/bzqd!print";
	}

	public IZxzsBO getzBo() {
		return zBo;
	}

	public void setzBo(IZxzsBO zBo) {
		this.zBo = zBo;
	}

	public IJczmsBO getJcBo() {
		return jcBo;
	}

	public void setJcBo(IJczmsBO jcBo) {
		this.jcBo = jcBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IBzqdBO getBzqdBo() {
		return bzqdBo;
	}

	public void setBzqdBo(IBzqdBO bzqdBo) {
		this.bzqdBo = bzqdBo;
	}
}
