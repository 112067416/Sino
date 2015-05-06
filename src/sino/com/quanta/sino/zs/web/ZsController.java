package com.quanta.sino.zs.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.vo.DscListQE;
import com.quanta.sino.zs.vo.FpListQE;
import com.quanta.sino.zs.vo.JqcQE;
import com.quanta.sino.zs.vo.ZsListQE;
import com.quanta.sino.zs.vo.ZsQE;
import com.quanta.sino.zs.vo.ZsdhVVO;
import com.quanta.sino.zs.vo.ZsdhViewVO;
import com.quanta.sino.zs.vo.ZssXpVO;

/**
 * <p>
 * 指示书控制器
 * </p>
 * <p>
 * create: 2010-12-28 下午05:02:00
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/zs")
public class ZsController {

	@Autowired
	private IZsBO bo;

	@Autowired
	private IYcaitpBO ycaiBo;

	@Autowired
	private IZpBO zpBo;

	@Autowired
	private IScxbBO scBO;

	/**
	 * <p>
	 * 指示书作成查询页面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexZc")
	public String etlIndexZc(ZsListQE page, Model model) {
		page.setSize(-1);
		// 查询状态为0，余材状况为1的指示DB
		page.setStat(ZtConstants.DHZS_STAT_WFP);
		page.setYczk(EXpzk.SC_KEY);
		page.setOrderBys(" a.dhno desc,a.jbkb asc,b.xpkn asc,b.xpho asc,b.yuny asc,b.face asc");
		bo.queryZsdx(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listZc" : "/sino/zs/etl/indexZc";
	}

	/**
	 * <p>
	 * 指示书作成
	 * </p>
	 * 
	 * @param ids
	 *            卷板No.和分配No组合数组(JBNO-FPNO)
	 * @return String
	 */
	@RequestMapping(value = "/etl/zc", method = RequestMethod.POST)
	public @ResponseBody
	String etlZc(String[] ids, boolean isPrint) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "没有指定卷板No.和分配No.").toString();
		}
		// 不能生成指示书号的卷板NO
		List<String> nozsnos = new ArrayList<String>();
		// 已经生成指示书号
		List<String> zsnos = null;
		zsnos = bo.zc(Arrays.asList(ids), nozsnos, isPrint);
		return new Result(StringUtils.join(zsnos, ",") + "|"
				+ StringUtils.join(nozsnos, ",")).toString();
	}

	/**
	 * <p>
	 * 指示书打印查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexDy")
	public String etlIndexDy(FpListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 指示书未完了
		// page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		page.setYczk(EXpzk.SC_KEY);
		if (page.getZsfx() == null) {
			page.setZsfx(ZtConstants.DHZS_ZSFX_WDY);
		}
		page.setOrderBys("  crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listDy" : "/sino/zs/etl/indexDy";
	}

	/**
	 * <p>
	 * 指示书打印更新
	 * </p>
	 * 
	 * @param ids
	 *            指示书NO
	 * @return String
	 */
	@RequestMapping(value = "/etl/updateZsPrint", method = RequestMethod.POST)
	public @ResponseBody
	String updateZsPrint(String ids) {
		List<String> zsnos = Arrays.asList(ids.split(","));
		bo.updateZsPrint(zsnos);
		return new Result(ids).toString();
	}

	/**
	 * <p>
	 * 指示书缺陷打印查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexQxDy")
	public String indexQxDy(FpListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 指示书未完了
		// page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		// page.setYczk(EXpzk.SC_KEY);
		// 指示书未完了
		// page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		page.setYczk(EXpzk.SC_KEY);
		if (page.getZsqx() == null) {
			page.setZsqx(ZtConstants.DHZS_ZSFX_WDY);
		}
		page.setOrderBys("  crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listQxDy"
				: "/sino/zs/etl/indexQxDy";
	}

	/**
	 * <p>
	 * 指示书缺陷打印更新
	 * </p>
	 * 
	 * @param ids
	 *            指示书NO
	 * @return String
	 */
	@RequestMapping(value = "/etl/updateZsQxPrint", method = RequestMethod.POST)
	public @ResponseBody
	String updateZsQxPrint(String ids) {
		// List<String> zsnos = new ArrayList<String>();
		List<String> zsnos = Arrays.asList(ids.split(","));
		bo.updateZsQxPrint(zsnos);
		return new Result(zsnos).toString();
	}

	/**
	 * <p>
	 * 指示书分派查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexFp")
	public String etlIndexFpEtl(FpListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 指示书未完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		page.setYczk(EXpzk.SC_KEY);
		page.setOrderBys(" abbr asc,substring(dhno,0,8 ) asc,substring(dhno,8,9 ) asc,crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz != null) {
			model.addAttribute("elin", scpz.getCode());
		}
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listFp" : "/sino/zs/etl/indexFp";
	}

	/**
	 * <p>
	 * 获取指示书
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/get", method = RequestMethod.POST)
	public @ResponseBody
	String get(String zsno, Model model) {
		if (zsno == null || zsno.isEmpty()) {
			return new Result(-1, "取界面指示书NO出错").toString();
		}
		return bo.getForJs(zsno);
	}

	/**
	 * <p>
	 * 获取指示书
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/get", method = RequestMethod.POST)
	public @ResponseBody
	String getsl(String zsno, Model model) {
		if (zsno == null || zsno.isEmpty()) {
			return new Result(-1, "取界面指示书NO出错").toString();
		}
		return bo.getForJs(zsno);
	}

	/**
	 * <p>
	 * 保存分派
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/etl/updateFP", method = RequestMethod.POST)
	public @ResponseBody
	String updateFP(String[] ids, String jinj, String shch, String remk) {
		if ((jinj == null) || (jinj.isEmpty())) {
			return new Result(-1, "请选择紧急程度").toString();
		}
		if ((shch == null) || (shch.isEmpty())) {
			return new Result(-1, "请选择镀锡线别").toString();
		}
		bo.updateZsdhTp(ids, jinj, shch, remk);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 取消分派
	 * </p>
	 * 
	 * @param id
	 *            指示NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/deleteFP", method = RequestMethod.POST)
	public @ResponseBody
	String deleteFP(String[] ids, Model model) {
		bo.deleteFP(ids);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 撤消指示书(权限判定,只有生产管理部才能撤消)
	 * </p>
	 * 
	 * @param zsno
	 *            指示NO
	 * @param dhno
	 *            订货NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/deleteZsdh", method = RequestMethod.POST)
	public @ResponseBody
	String deleteZsdh(String zsno, Model model) {
		bo.deleteZsdh(zsno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 待生产指示书
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexDsc!{type}")
	public String indexDsc(@PathVariable String type, DscListQE page,
			Model model) {
		if (page.getSize() <= 0) {
			page.setSize(30);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
		}

		// 已分派
		page.setStat(ZtConstants.DHZS_STAT_YFP);
		// 指示书未完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		// 余材状况为1
		page.setYczk(EXpzk.SC_KEY);
		// 按生产顺序和创建时间排序
		page.setOrderBys(" (case   WHEN   sort   is   NULL   then   99999999   else   sort   end) asc,crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz != null) {
			model.addAttribute("elin", scpz.getCode());
		}

		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return page.isQuery() ? "/sino/zs/etl/listDsc"
				: "/sino/zs/etl/indexDsc";
	}

	/**
	 * <p>
	 * etl指示书垫木设置
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/etl/indexETLDm")
	public String indexEtlDm(DscListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(30);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
		}

		// 已分派
		page.setStat(ZtConstants.DHZS_STAT_YFP);
		// 指示书未完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		// 余材状况为1
		page.setYczk(EXpzk.SC_KEY);
		// 按生产顺序和创建时间排序
		page.setOrderBys(" (case   WHEN   sort   is   NULL   then   99999999   else   sort   end) asc,crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz != null) {
			model.addAttribute("elin", scpz.getCode());
		}
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listDm" : "/sino/zs/etl/indexDm";
	}

	/**
	 * <p>
	 * 查看指示书
	 * </p>
	 * 
	 * @param id
	 *            指示书NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/view", method = RequestMethod.POST)
	public String view(String zsno, Model model) {
		if (zsno == null || zsno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "取界面指示书NO出错"));
			return Result.URL_BLANK;
		}
		// 根据指示书NO查询订货指示书DB
		ZsdhViewVO zsVo = bo.getZsdhTpVO(zsno, ProductType.etl.name);
		model.addAttribute("item", zsVo);
		// 根据指示书NO查询原材卷板
		List<ZsdhVVO> zsItems = bo.getYcaiByZsno(zsno);
		model.addAttribute("items", zsItems);
		Integer zpzl = 0;
		for (ZsdhVVO zsitem : zsItems) {
			if (zsitem.getZpzl() != null) {
				zpzl = zpzl + zsitem.getZpzl();
			}

		}
		Integer kbsz = 0;
		if (zsVo.getKbsz() != null) {
			kbsz = Integer.parseInt(zsVo.getKbsz());
		}
		Double bdan = 0.0;
		if (zsVo.getBdan() != null) {
			bdan = zsVo.getBdan();
		}
		Double dmsz1 = 0.0;
		Integer dmsz2 = 0;
		if (bdan.doubleValue() > 0 && kbsz > 0) {
			// 垫木个数＝总装入量/(包装张数*B单重)
			dmsz1 = zpzl / (kbsz * bdan);
			if (dmsz1 >= (NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 0.3)) {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 1;
			}
			else {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN);
			}
			zsVo.setDmsz(dmsz2);
		}
		// zsVo.setLotz(NumberUtils.format(kbsz * bdan * 0.001, 3).toString());
		return "/sino/zs/etl/view";
	}

	/**
	 * <p>
	 * 保存等待生产指示书排序
	 * </p>
	 * 
	 * @param ids
	 *            指示书号数组
	 * @return String
	 */
	@RequestMapping(value = "/etl/saveDyZzs", method = RequestMethod.POST)
	public @ResponseBody
	String saveDyZzs(String[] sorts) {
		bo.saveDyZzs(Arrays.asList(sorts));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 保存等待生产指示书排序
	 * </p>
	 * 
	 * @param ids
	 *            指示书号数组
	 * @return String
	 */
	@RequestMapping(value = "/sl/saveSLDyZzs", method = RequestMethod.POST)
	public @ResponseBody
	String saveSLDyZzs(String[] sorts) {
		bo.saveDyZzs(Arrays.asList(sorts));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 指示书已完成查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/indexYwc", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String etlIndexYwc(FpListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 余材状况为1的指示DB
		page.setYczk(EXpzk.SC_KEY);
		// 指示书完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_YDY);
		page.setOrderBys(" zsny desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.etl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryDscZsdhTp(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/etl/listYwc"
				: "/sino/zs/etl/indexYwc";
	}

	/**
	 * <p>
	 * 指示书作成材料列表（剪切材列表）
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/sl/indexJqc", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String slIndexJqc(JqcQE page, Model model) {
		page.setSize(-1);
		// page.setOrderBys("a.dhno");
		page.setSczm(true);
		page.setScfm(true);
		page.setOrderBys(" a.dhno desc,a.zjbb asc,a.xpkn asc,a.xpho asc,a.yuny asc,a.face asc,a.sczm asc,a.scfm asc");
		bo.queryJqc(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/sl/listJqc" : "/sino/zs/sl/indexJqc";
	}

	/**
	 * <p>
	 * SL指示书作成
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param nos
	 *            作成指示书的所有卷板号
	 * @return String
	 */
	@RequestMapping(value = "/sl/zc", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String slZc(String[] nos, boolean isPrint) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "用户没有登录").toString();
		}
		if (nos == null || nos.length == 0) {
			return new Result(-1, "请先选取剪切材").toString();
		}
		List<String> nojbnos = new ArrayList<String>();
		List<String> zsshs = bo.slzc(Arrays.asList(nos), nojbnos, user, isPrint);
		if (zsshs == null || zsshs.isEmpty()) {
			return new Result(-1, "未成功作成指示书，请确定是否已选中剪切板").toString();
		}
		return new Result(StringUtils.join(zsshs, ",") + "|"
				+ StringUtils.join(nojbnos, ",")).toString();
		// return new Result(zsshs).toString();
	}

	/**
	 * <p>
	 * SL指示书分派【分页查询】
	 * </p>
	 * <p>
	 * Author 张红国
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/sl/indexSLFp", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String indexSLFp(ZsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}

		// 设置余材状况：2-中间品
		page.setYczk(EXpzk.ZJP_KEY);
		// 设置指示完成标记：未完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 按“紧急”排序
		page.setOrderBys("crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.sl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryWwc(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/sl/listFp" : "/sino/zs/sl/indexFp";
	}

	/**
	 * <p>
	 * SL指示书垫木设置
	 * </p>
	 * <p>
	 * Author 张红国
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/sl/indexSLDm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String indexSLDm(ZsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}

		// 设置余材状况：2-中间品
		page.setYczk(EXpzk.ZJP_KEY);
		// 设置指示完成标记：未完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 按“紧急”排序
		page.setOrderBys("jinj desc,crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.sl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryWwc(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/sl/listDm" : "/sino/zs/sl/indexDm";
	}

	/**
	 * <p>
	 * 保存sl分派
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/sl/updateSLFP", method = RequestMethod.POST)
	public @ResponseBody
	String updateSLFP(String[] ids, String jinj, String remk) {
		bo.updateSLZsJinj(ids, jinj, remk);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 保存Etl备注
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/etl/updateREMK", method = RequestMethod.POST)
	public @ResponseBody
	String updateREMK(String zsno, String remk) {
		bo.updateREMK(zsno, remk);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 保存Etl备注
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/sl/updateREMK", method = RequestMethod.POST)
	public @ResponseBody
	String updateSLREMK(String zsno, String remk) {
		bo.updateREMK(zsno, remk);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 保存sl垫木设置
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/sl/updateSLDM", method = RequestMethod.POST)
	public @ResponseBody
	String updateSLDM(String[] ids, String sfdm) {
		bo.updateSLZsDM(ids, sfdm, ProductType.sl.name);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 保存etl垫木设置
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 * @param wsvo
	 *            采购合同保存下拉框实体
	 * @return String
	 */
	@RequestMapping(value = "/etl/updateSLDM", method = RequestMethod.POST)
	public @ResponseBody
	String updateEtlDM(String[] ids, String sfdm) {
		bo.updateSLZsDM(ids, sfdm, ProductType.etl.name);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 获取sL指示书的分派
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/getSLFp", method = RequestMethod.POST)
	public @ResponseBody
	String getSLFp(String zsno, Model model) {
		if (zsno == null || zsno.isEmpty()) {
			return new Result(-1, "取界面指示书NO出错").toString();
		}
		return bo.getForJs(zsno);
	}

	/**
	 * <p>
	 * 取消SL分派
	 * </p>
	 * 
	 * @param id
	 *            指示NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/deleteSLFP", method = RequestMethod.POST)
	public @ResponseBody
	String deleteSLFP(String[] ids, Model model) {
		bo.deleteSLFP(ids);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 取消SL分派
	 * </p>
	 * 
	 * @param id
	 *            指示NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/deleteSLDM", method = RequestMethod.POST)
	public @ResponseBody
	String deleteSLDM(String[] ids, Model model) {
		bo.deleteSLDM(ids, ProductType.sl.name);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 取消SL分派
	 * </p>
	 * 
	 * @param id
	 *            指示NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/etl/deleteSLDM", method = RequestMethod.POST)
	public @ResponseBody
	String deleteETLDM(String[] ids, Model model) {
		bo.deleteSLDM(ids, ProductType.etl.name);
		return Result.SUCCESS;

	}

	/**
	 * <p>
	 * 指示书未完成【分页查询】
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/sl/indexWwc!{type}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String slIndexWwc(@PathVariable String type, ZsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// 当前月的第一天
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 设置余材状况：2-中间品
		page.setYczk(EXpzk.ZJP_KEY);
		// 设置指示完成标记：未完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 按生产顺序和创建时间排序
		page.setOrderBys(" (case   WHEN   sort   is   NULL   then   99999999   else   sort   end) asc,jinj desc,crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.sl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryWwc(page);
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz != null) {
			model.addAttribute("elin", scpz.getCode());
		}
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return page.isQuery() ? "/sino/zs/sl/listWwc" : "/sino/zs/sl/indexWwc";
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
	@RequestMapping(value = "/getSLZsno", method = RequestMethod.POST)
	public @ResponseBody
	String getSLZsno(String jbno, Model model) {
		String zsno = bo.getZsno(jbno, ProductType.sl.name);
		return new Result(zsno).toString();
	}

	/**
	 * <p>
	 * SL指示书撤消
	 * </p>
	 * <p>
	 * Author 张良
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @return String
	 */
	@RequestMapping(value = "/sl/zssCx", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String slZssCx(String zsno) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "用户没有登录!").toString();
		}
		// 指示书撤消
		bo.doSlZssCx(zsno, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 指示书打印更新
	 * </p>
	 * 
	 * @param ids
	 *            指示书NO
	 * @return String
	 */
	@RequestMapping(value = "/sl/updateSLZsPrint", method = RequestMethod.POST)
	public @ResponseBody
	String updateSLZsPrint(String[] ids) {
		List<String> zsnos = new ArrayList<String>();
		zsnos = Arrays.asList(ids);
		bo.updateZsPrint(zsnos);
		return new Result(StringUtils.join(zsnos, ",")).toString();
	}

	/**
	 * <p>
	 * SL指示书打印查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/sl/indexDy")
	public String slIndexDy(ZsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		if (page.getZsfx() == null) {
			page.setZsfx(ZtConstants.DHZS_ZSFX_WDY);
		}
		// 设置余材状况：2-中间品
		page.setYczk(EXpzk.ZJP_KEY);
		// 设置指示完成标记：未完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 按“紧急”排序
		page.setOrderBys("crea desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.sl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryWwc(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/sl/listDy" : "/sino/zs/sl/indexDy";
	}

	/**
	 * <p>
	 * 已完成指示书
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/indexYwc", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String slIndexYwc(ZsQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
			// if ((page.getCrea_begin() == null) && (page.getCrea_end() ==
			// null)) {
			// // 当前月的第一天
			// Date now = new Date();
			// page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
			// // 当前日期
			// now = DateUtils.add(now, Calendar.DAY_OF_MONTH, 1);
			// page.setCrea_end(now);
			// }
		}
		// 设置余材状况：2-中间品
		page.setYczk(EXpzk.ZJP_KEY);
		// 设置指示完成标记：已完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_YWC);
		// 按指示书完成日期排序
		page.setOrderBys("zsny desc");
		// 卷板NO不为空，只查一个指示书
		if (page.getJbno() != null) {
			String zsno = bo.getZsno(page.getJbno(), ProductType.sl.name);
			page.setZsno(zsno);
			page.setJbno(null);
		}
		bo.queryWwc(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zs/sl/listYwc" : "/sino/zs/sl/indexYwc";
	}

	/**
	 * <p>
	 * 查看指示书
	 * </p>
	 * 
	 * @param id
	 *            指示书NO
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/sl/view", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String viewZss(String zsno, Model model) {
		// 检验非空
		if (zsno == null || zsno.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "取界面指示书NO出错"));
			return Result.URL_BLANK;
		}
		// 取指示书
		// ZsdhTp zss = bo.getZsdhTp(zsno);
		// model.addAttribute("zss", zss);
		// 根据指示书NO查询订货指示书DB
		// 根据指示书NO查询订货指示书DB
		ZsdhViewVO zsVo = bo.getZsdhTpVO(zsno, ProductType.sl.name);
		model.addAttribute("zss", zsVo);
		ZsdhTp zss = bo.getZsdhTp(zsno);
		// 现品列表
		List<ZssXpVO> items = bo.listZssXp(zss);
		model.addAttribute("items", items);
		Integer zpzl = 0;
		for (ZssXpVO zsitem : items) {
			if (zsitem.getZpzl() != null) {
				zpzl = zpzl + zsitem.getZpzl();
			}
		}
		Integer kbsz = 0;
		if (zsVo.getKbsz() != null) {
			kbsz = Integer.parseInt(zsVo.getKbsz());
		}
		Double bdan = 0.0;
		if (zsVo.getBdan() != null) {
			bdan = zsVo.getBdan();
		}
		Double dmsz1 = 0.0;
		Integer dmsz2 = 0;
		if (bdan.doubleValue() > 0 && kbsz > 0) {
			// 垫木个数＝总装入量/(包装张数*B单重)
			dmsz1 = zpzl / (kbsz * bdan);
			if (dmsz1 >= (NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 0.3)) {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 1;
			}
			else {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN);
			}
			zsVo.setDmsz(dmsz2);
		}
		return "/sino/zs/sl/view";
	}

	/**
	 * @return the bo
	 */
	public IZsBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZsBO bo) {
		this.bo = bo;
	}

	/**
	 * @return the ycaiBo
	 */
	public IYcaitpBO getYcaiBo() {
		return ycaiBo;
	}

	/**
	 * @param ycaiBo
	 *            the ycaiBo to set
	 */
	public void setYcaiBo(IYcaitpBO ycaiBo) {
		this.ycaiBo = ycaiBo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IScxbBO getScBO() {
		return scBO;
	}

	public void setScBO(IScxbBO scBO) {
		this.scBO = scBO;
	}
}
