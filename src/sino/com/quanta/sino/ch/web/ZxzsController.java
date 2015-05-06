package com.quanta.sino.ch.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.orm.Seq;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.vo.XgZxzsVO;
import com.quanta.sino.ch.vo.ZxzsDyQE;
import com.quanta.sino.ch.vo.ZxzsQE;
import com.quanta.sino.ch.vo.ZxzsVO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.ChlldStat;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.ZxzsTp;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.vo.ChlldQE;
import com.quanta.sino.yygl.vo.ChlldVO;

/**
 * <p>
 * 装箱指示书管理控制器
 * </p>
 * <p>
 * create: 2010-12-29 上午08:49:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ch/zxzs")
public class ZxzsController {

	/**
	 * 装箱指示书业务接口
	 */
	@Autowired
	private IZxzsBO zbo;
	/**
	 * 制品业务接口
	 */
	@Autowired
	private IZpBO zpBo;

	/**
	 * 出货联络单业务处理接口
	 */
	@Autowired
	private IChlldBO cbo;

	/**
	 * 订货业务接口
	 */
	@Autowired
	private IDhBO dhBO;

	/**
	 * 用户管理业务接口
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * 
	 */
	@Autowired
	private ISeqBO seqBO;

	@Autowired
	private IZxdzBO zxdzBO;
	/**
	 * 根据堆场升序
	 */
	private static final String ASC = "asc";

	/**
	 * <p>
	 * 今日出货联络单查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/chlld!{type}")
	public String chlld(@PathVariable String type, ChlldQE page, Model model) {
		page.setSize(-1);
		if (!page.isQuery()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
				model.addAttribute("chqiBegin", date);
			}
			catch (ParseException e) {
			}
			page.setChqiBegin(date);
		}
		page.setNeStat(ChlldStat.WS.key);
		page.setChqiEnd(page.getChqiBegin());
		page.setDhnoNLike("W");
		page.setOrderBys("user asc, ysgs asc, shno asc, addr asc, dhno asc, line asc");
		cbo.query(page);

		ChlldQE $page = new ChlldQE();
		$page.setChqiBegin(page.getChqiBegin());
		$page.setChqiEnd(page.getChqiEnd());
		$page.setDhnoLike("W");
		$page.setNeStat(page.getNeStat());
		$page.setOrderBys(page.getOrderBys());
		cbo.query($page);
		page.getDatas().addAll($page.getDatas());

		model.addAttribute("page", page);
		model.addAttribute("fixed", ChlldStat.YJS.key);
		model.addAttribute("ddzl", zbo.getTj(page.getChqiBegin()));
		model.addAttribute("type", type);
		return page.isQuery() ? "/sino/ch/zxzs/listChlld"
				: "/sino/ch/zxzs/indexChlld";
	}

	/**
	 * <p>
	 * 修改出货联络单状态为结束
	 * </p>
	 * 
	 * @param id
	 * @param stat
	 * @return String
	 */
	@RequestMapping(value = "/updateChlld", method = RequestMethod.POST)
	public @ResponseBody
	String updateChlld(String id, String stat) {
		Chlld entity = cbo.get(id);
		if (entity == null) {
			return new Result(-1, "该出货联络单不存在").toString();
		}
		if (ChlldStat.YS.key.equals(stat) && zbo.isExistByChlld(id)) {
			return new Result(-1, "该出货联络单已打单,不允许取消已打单").toString();
		}
		entity.setStat(stat);
		cbo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入制作装箱指示主页
	 * </p>
	 * 
	 * @param id
	 * @param order
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/zzZxzs")
	public String zzZxzs(String id, String order, ZpQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("chqi", sdf.format(new Date()));
			model.addAttribute("id", id);
			model.addAttribute("page", page);
			Seq seq = seqBO.get(com.quanta.sino.cmn.constants.Seq.zxzsno.name);
			long maxNo = -1;
			if (seq != null) {
				try {
					maxNo = seq.getNumber();
				}
				catch (NumberFormatException e) {
				}
			}
			if (maxNo < 0) {
				maxNo = 1;
			}
			else {
				maxNo += 1;
			}
			model.addAttribute("zxno", String.format("%010d", maxNo));
			return "/sino/ch/zxzs/indexZxzs";
		}
		page.setScbj(EScbj.CS.key);
		String[] fpycs = { EFpyc.CS.key, EFpyc.FP.key };
		page.setFpycs(fpycs);
		page.setChnoIsNull(true);
		if (ASC.equals(order)) {
			page.setOrderBys("kw asc, jbno_ asc");
		}
		else {
			page.setOrderBys("kw desc, jbno_ asc");
		}
		String[] duics = { DC.C.name, DC.F.name, DC.G.name };
		page.setDuics(duics);
		String xpzks[] = { EXpzk.JZP.key, EXpzk.BZP.key };
		page.setXpzks(xpzks);
		zpBo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "/sino/ch/zxzs/listZxzs";
	}

	/**
	 * <p>
	 * 加载出货联络单信息
	 * </p>
	 * 
	 * @param id
	 * @param dhno
	 * @param line
	 * @return String
	 */
	@RequestMapping(value = "/chlldData", method = RequestMethod.POST)
	public @ResponseBody
	String chlldData(String id, String dhno, String line) {
		if ((id == null || id.isEmpty()) && (dhno == null || dhno.isEmpty())
				&& (line == null || line.isEmpty())) {
			return new Result(-1, "参数为空").toString();
		}
		Chlld chlld = null;
		if (id != null && !id.isEmpty() && (chlld = cbo.get(id)) == null) {
			return new Result(-1, "参数为空").toString();
		}

		DhuoTp dhuoTp = null;
		if (dhno != null && (dhuoTp = dhBO.get(dhno, line, null)) == null) {
			return new Result(-1, "订货No.不存在").toString();
		}
		Date chqi = new Date();
		ChlldVO vo = new ChlldVO();
		vo.setChqi(chqi);
		String user = null;
		if (chlld != null) {
			user = chlld.getUser();
			vo.setDhno(chlld.getDhno());
			vo.setLine(chlld.getLine() != null && !chlld.getLine().isEmpty() ? chlld.getLine()
					: line);
			vo.setAbbr(chlld.getAbbr());
			vo.setPid(chlld.getId());
			vo.setYsgs(chlld.getYsgs());
			vo.setShno(chlld.getShno());
			vo.setYsnm(chlld.getYsnm());
			vo.setShnm(chlld.getShnm());
			vo.setAddr(chlld.getAddr());
			vo.setChqi(chlld.getChqi());
			double chzl = chlld.getChzl() != null ? chlld.getChzl() : 0;
			double $chzl = zbo.getTj(id);
			vo.setWczl(NumberUtils.format((chzl - $chzl), 3));
		}
		else if (dhuoTp != null) {
			user = dhuoTp.getUser();
			vo.setDhno(dhno);
			vo.setLine(line);
			vo.setAbbr(dhuoTp.getAbbr());
		}
		vo.setUser(user);
		YongMp yongMp = yongBO.get(user);
		if (yongMp != null) {
			// 设置用户中文名称
			vo.setName(yongMp.getRsv1());
		}
		List<YongShdz> shdzs = yongBO.findShdz(user);
		if (shdzs != null) {
			StringBuilder builder = new StringBuilder();
			for (YongShdz shdz : shdzs) {
				if (builder.length() > 0) {
					builder.append(",");
				}
				builder.append(shdz.getAddr());
			}
			vo.setAddrs(builder.toString());
		}
		return new Result(vo).toJsObject();
	}

	/**
	 * <p>
	 * 保存装箱指示书信息
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(ZxzsVO vo) {
		User user = Context.currentUser();
		if (user == null || user.getNo().isEmpty()) {
			return new Result(-1, "登录超时,请重新登录!").toString();
		}
		zbo.save(vo, user);
		int pageSize = vo.getChsu() / ChglConstants.ZXZS_SIZE;
		if (vo.getChsu() % ChglConstants.ZXZS_SIZE != 0) {
			pageSize++;
		}
		return "{zxno:\"" + vo.getZxno() + "\",pageSize:" + pageSize + "}";
	}

	/**
	 * <p>
	 * 判断制作装箱指示书
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody
	String check(String jbnos) {
		return zbo.getCheck(jbnos);
	}

	/**
	 * <p>
	 * 修改装箱指示书
	 * </p>
	 * 
	 * @param zxno
	 * @return String
	 */
	@RequestMapping(value = "/getForJs", method = RequestMethod.POST)
	public @ResponseBody
	String getForJs(String zxno) {
		if (zxno == null || zxno.isEmpty()) {
			return new Result(-1, "装箱指示书No.为空").toString();
		}
		return zbo.getForJs(zxno);
	}

	/**
	 * <p>
	 * 修改装箱指示书
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(XgZxzsVO vo) {
		if (vo.getZxno() == null || vo.getZxno().isEmpty()) {
			return new Result(-1, "装箱指示书No.为空").toString();
		}
		return zbo.update(vo);
	}

	/**
	 * <p>
	 * 装箱指示书分页查询
	 * </p>
	 * 
	 * @param type
	 * @param page
	 * @param dhno
	 * @param line
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index!{type}")
	public String index(@PathVariable String type, ZxzsQE page, String dhno,
			String line, String jbno, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			page.setStat(ChStat.WFH.stat);
		}
		if (page.getChriE() == null) {
			page.setChriE(page.getChriS());
		}
		if (page.getCreaE() == null) {
			page.setCreaE(page.getCreaS());
		}
		page.setOrderBys("chri desc, zxno desc");
		List<String> zxnos = null;
		if ((dhno != null && !dhno.isEmpty())
				|| (line != null && !line.isEmpty())
				|| (jbno != null && !jbno.isEmpty())) {
			zxnos = zbo.queryZxnos(dhno, line, jbno);
			if (zxnos.size() == 0) {
				model.addAttribute("page", page);
				return "/sino/ch/zxzs/list";
			}
			page.setZxnos(zxnos);
		}
		zbo.queryZxzs(page);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("title", "1".equals(type) ? "装箱指示书管理" : "装箱指示书打印");
		return page.isQuery() ? "/sino/ch/zxzs/list" : "/sino/ch/zxzs/index";
	}

	/**
	 * <p>
	 * 查看装箱指示书
	 * </p>
	 * 
	 * @param zxno
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/view")
	public String view(String zxno, ZxzsDyQE page, Model model) {
		if (zxno == null || zxno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "缺少参数"));
			return Result.URL_ERROR;
		}
		if (page == null) {
			page = new ZxzsDyQE();
		}
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys("b.dhno,b.line,a.jbno");
		page.setZxno(zxno);
		zbo.queryZxzsMx(page);
		Zng1Tp zng1Tp = null;
		if (page.getDatas().size() > 0) {
			zng1Tp = page.getDatas().get(0).getZng1Tp();
		}
		ZxzsTp zxzsTp = zbo.get(zxno);
		model.addAttribute("zxzsTp", zxzsTp);
		model.addAttribute("page", page);
		model.addAttribute("zng1Tp", zng1Tp);
		return page.isQuery() ? "/sino/ch/zxzs/zsmxList"
				: "/sino/ch/zxzs/indexZsmx";
	}

	/**
	 * <p>
	 * 装箱指示书作废
	 * </p>
	 * 
	 * @param zxno
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String zxno) {
		if (zxno == null || zxno.isEmpty()) {
			return new Result(-1, "装箱指示书No.为空").toString();
		}
		return zbo.delete(zxno);
	}

	public IZxzsBO getZbo() {
		return zbo;
	}

	public void setZbo(IZxzsBO zbo) {
		this.zbo = zbo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IChlldBO getCbo() {
		return cbo;
	}

	public void setCbo(IChlldBO cbo) {
		this.cbo = cbo;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

	public ISeqBO getSeqBO() {
		return seqBO;
	}

	public void setSeqBO(ISeqBO seqBO) {
		this.seqBO = seqBO;
	}

	public IZxdzBO getZxdzBO() {
		return zxdzBO;
	}

	public void setZxdzBO(IZxdzBO zxdzBO) {
		this.zxdzBO = zxdzBO;
	}

}
