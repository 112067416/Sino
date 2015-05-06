package com.quanta.sino.etl.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.bo.api.IRcBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.EtlStopMxVO;
import com.quanta.sino.etl.vo.EtlStopVO;
import com.quanta.sino.etl.vo.EtlSydjVO;
import com.quanta.sino.etl.vo.RcViewVO;
import com.quanta.sino.etl.vo.SBVO;
import com.quanta.sino.etl.vo.SBmxVO;
import com.quanta.sino.etl.vo.SjMainVO;
import com.quanta.sino.etl.vo.SjQE;
import com.quanta.sino.etl.vo.SjSLQE;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.etl.vo.SjlrVO;
import com.quanta.sino.etl.vo.ZsViewVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ETLVariTp;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 实绩录入控制器
 * </p>
 * <p>
 * create: 2010-12-28 下午05:02:00
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/etl/sj")
public class SjController {

	@Autowired
	private IScxbBO scBO;

	@Autowired
	private IEtlBO etlBO;

	@Autowired
	private IZpBO zpBO;

	@Autowired
	private IDhBO dhBO;

	@Autowired
	private IRcBO rcBO;

	/**
	 * <p>
	 * 重新设置制品所属组
	 * </p>
	 * 
	 * @param jbno
	 * @param zu
	 * @return
	 */
	@RequestMapping(value = "/updateZu", method = RequestMethod.POST)
	public @ResponseBody
	String updateZu(String jbno, String zu) {
		etlBO.updateZu(jbno, zu);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入ETL停线登录页面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexEtlStop", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String indexEtlStop(Model model) {
		return "/sino/etl/sj/indexEtlStop";
	}

	/**
	 * <p>
	 * 获取ETL停线信息
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEtlStopData", method = RequestMethod.POST)
	public String getEtlStopData(String dasr, String ban, Model model) {
		Date date = DateUtils.parse(dasr, "yyyy-MM-dd");
		List<EtlStopMxVO> items = etlBO.findEtlStopDatas(date, ban);
		model.addAttribute("items", items);
		model.addAttribute("dasr", date);
		model.addAttribute("ban", ban);
		ETLVariTp vari = etlBO.getETLVariTp(date, ban);
		model.addAttribute("itemvari", vari);
		return "/sino/etl/sj/dataEtlStop";
	}

	/**
	 * <p>
	 * 保存ETL停线信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/savestop", method = RequestMethod.POST)
	public @ResponseBody
	String savestop(EtlStopVO vo) {
		etlBO.saveEtlStop(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * ETL速报登录
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexsbdl", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String indexsbdl(Model model) {
		return "/sino/etl/sj/indexsbdl";
	}

	/**
	 * <p>
	 * ETL速报登录界面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findSblrs", method = RequestMethod.POST)
	public String findSblrs(String date, Model model) {
		Date $date = DateUtils.parse(date, DateUtils.YMD);
		List<SBmxVO> sbVos = etlBO.findSbDatas($date);
		model.addAttribute("items", sbVos);
		return "/sino/etl/sj/detailsb";
	}

	/**
	 * <p>
	 * 保存ETL速报数据
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/savesb", method = RequestMethod.POST)
	public @ResponseBody
	String savesb(SBVO vo) {
		if (vo.getItems() == null || vo.getItems().length == 0) {
			throw new CocoException(-1, "获取列表数据为空");
		}
		etlBO.saveEtlsb(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入ETL实绩录入初始页面<br />
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/sjlr", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String sjlr(Model model) {

		User user = Context.currentUser();
		// 创建生产线实体
		SjMainVO vo = new SjMainVO();
		// 取登录用户生产线
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz == null
				|| !ProductType.etl.code.equalsIgnoreCase(scpz.getType())) {
			throw new CocoException(-1, "非ETL生产线人员不能操作此项功能");
		}
		vo.setElin(scpz.getCode());
		vo.setScxname(scpz.getName());
		// 取正在生产的卷板NO
		etlBO.getZzsj(vo);
		// 添加到界面
		model.addAttribute("item", vo);
		// 查询入侧信息添加到界面
		RcViewVO rcVo = rcBO.findRc();
		model.addAttribute("rcitem", rcVo);
		return "/sino/etl/sj/indexSjlr";
	}

	/**
	 * <p>
	 * 查询入侧信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getRcInfo", method = RequestMethod.POST)
	public @ResponseBody
	String getRcInfo(Model model) {
		// 查询入侧信息添加到界面
		RcViewVO rcVo = rcBO.findRc();
		return new Result(rcVo).toJsObject();
	}

	/**
	 * <p>
	 * 查询操作方法和重内，判断要显示哪个登录(实绩录入)
	 * </p>
	 * 
	 * @param id
	 *            指示书号和COIL No组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getMode", method = RequestMethod.POST)
	public @ResponseBody
	String getMode(String zsno, String jbno, Model model) {
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		String rel = etlBO.get(zsno, jbno, scpz.getCode());
		if (rel == null) {
			return new Result(-1, "指示书号或COIL No不存在").toString();
		}
		return new Result(rel).toString();

	}

	/**
	 * <p>
	 * 查询操作方法和重内，判断要显示哪个登录（订正）
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PlieNo
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getModeForModify", method = RequestMethod.POST)
	public @ResponseBody
	String getModeForModify(String jbno, Model model) {
		String rel = etlBO.get(jbno);
		if (rel == null) {
			return new Result(-1, "制品不存在").toString();
		}
		return new Result(rel).toString();

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
	@RequestMapping(value = "/getLr", method = RequestMethod.POST)
	public @ResponseBody
	String getLr(String zsno, String jbno, Model model) {
		SjlrVO sjlrVO = etlBO.getFuZi(zsno, jbno);
		// 取登录用户生产线
		User user = Context.currentUser();
		// 取登录用户生产线
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz == null
				|| !ProductType.etl.code.equalsIgnoreCase(scpz.getType())) {
			throw new CocoException(-1, "非ETL生产线人员不能操作此项功能");
		}
		sjlrVO.setElin(scpz.getCode());
		sjlrVO.setBan(user.getShift());
		sjlrVO.setZu(user.getGroup());
		return new Result(sjlrVO).toJsObject();
	}

	/**
	 * <p>
	 * 查询制品库信息
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getZpInfo", method = RequestMethod.POST)
	public @ResponseBody
	String getZpInfo(String jbno, String mode, Model model) {
		// 取登录用户生产线
		User user = Context.currentUser();
		Scxbpz scpz = scBO.getByDept(user.getDeptId());
		if (scpz != null) {
			return etlBO.getZpInfo(jbno, scpz.getCode(), mode);
		}
		else {
			return new Result(-1, "不存在用户登陆生产线").toString();
		}
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
	@RequestMapping(value = "/getZpzl", method = RequestMethod.POST)
	public @ResponseBody
	String getZpzl(String zsno, Integer sjzl, Integer jbcn, Integer cutc,
			Integer loss, Integer loss2, String chan, String formTye,
			Model model) {
		if (ZtConstants.DHZS_CAOZ_C.equals(formTye)) {
			jbcn = etlBO.getJbcn(sjzl, zsno);
		}
		Integer zpzl = etlBO.getZpzl(zsno, sjzl, jbcn, cutc, loss, loss2, chan, formTye);
		return new Result(zpzl + "," + jbcn).toString();
	}

	/**
	 * <p>
	 * 保存实绩录入检查
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody
	String validate(SjSaveVO entity) {
		etlBO.validate(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 实绩附着量检查
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/checkFu", method = RequestMethod.POST)
	public @ResponseBody
	String checkFu(String dhno, Double sczm, Double scfm) {
		DhuoTp dhuo = null;
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhno);
		dhuo = dhBO.get(dhpk);
		etlBO.checkFu(dhuo, sczm, scfm);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 保存实绩录入
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/saveSj", method = RequestMethod.POST)
	public @ResponseBody
	String saveSj(SjSaveVO entity) {
		etlBO.saveSjLr(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 修改制品实绩录入
	 * </p>
	 * 
	 * @param entity
	 *            界面实体
	 * @return String
	 */
	@RequestMapping(value = "/updateSj", method = RequestMethod.POST)
	public @ResponseBody
	String updateSj(SjSaveVO entity) {
		etlBO.updateSjLr(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查询订货DB的附着量
	 * </p>
	 * 
	 * @param id
	 *            指示书号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getfu!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getCOILNo(@PathVariable String id, Model model) {

		return "";

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
		model.addAttribute("item", etlBO.getZs(id, ProductType.etl.name));
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
		ZsViewVO zsVO = etlBO.getZs(id, ProductType.etl.name);
		if (zsVO.getKpns().size() > 0 || zsVO.getYlns().size() > 0) {
			return Result.SUCCESS;
		}
		else {
			return new Result(-1, "不存在别纸和业联").toString();
		}

	}

	/**
	 * <p>
	 * etl参数管理记录
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexCsgl", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String indexCsgl() {
		return "/sino/etl/sj/indexCsgl";
	}

	/**
	 * <p>
	 * 加载参数管理记录
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getPzlp!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getPzlp(@PathVariable String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "装入卷板No不能为空").toString();
		}
		return etlBO.getForJs(id);
	}

	/**
	 * 选择实绩登录页面: 对指示书和入侧卷进行校验
	 * 
	 * @param zssNo
	 *            指示书NO
	 * @param coilNo
	 *            原板卷NO
	 * @return
	 */
	@RequestMapping(value = "/elect", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String elect(String zssNo, String coilNo) {
		return null;
	}

	/**
	 * <p>
	 * 实绩保存
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String save(ZpngTp entity) {
		return null;
	}

	/**
	 * <p>
	 * 转向实绩订正页面-打开新的TAB页
	 * </p>
	 * 
	 * @param coilNo
	 *            钢卷制品号
	 * @return
	 */
	@RequestMapping(value = "/sjdz", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String sjdz(String jbno, Model model) {
		model.addAttribute("jbno", jbno);
		ZpngTp zp = zpBO.getZp(jbno);
		if (zp != null) {
			model.addAttribute("isfp", zp.getIsfp());
		}

		return "/sino/etl/sj/indexSjdz";
	}

	/**
	 * <p>
	 * 实绩订正
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String update(ZpngTp entity) {
		return null;
	}

	/**
	 * <p>
	 * 获取实绩记录
	 * </p>
	 * 
	 * @param coilNo
	 * @return
	 */
	@RequestMapping(value = "/get!{coilNo}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String get(@PathVariable String coilNo) {
		return null;
	}

	/**
	 * <p>
	 * 实绩删除
	 * </p>
	 * 
	 * @param coilNos
	 *            钢卷号(制品在库DB)组合字符串
	 * @return
	 */
	@RequestMapping(value = "/delete!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id, Model model) {
		etlBO.delete(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量删除采购合同
	 * </p>
	 * 
	 * @param ids
	 *            合同No.和行号组合数组(no-line)
	 * @return String
	 */
	@RequestMapping(value = "/delAll", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		List<String> idsList = new ArrayList<String>();
		idsList = Arrays.asList(ids);
		etlBO.deleteAll(idsList);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 制品维护
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String query(SjQE page, String type, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys(" crea desc");
		String[] xpzk = { EXpzk.JZP_KEY, EXpzk.ZJP_KEY };
		page.setXpzk(xpzk);
		etlBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return page.isQuery() ? "/sino/etl/sj/list" : "/sino/etl/sj/index";
	}

	/**
	 * <p>
	 * 待生产的切板制品
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/querysl", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String querysl(SjSLQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys(" crea desc");
		String[] stat = { ZpStat.CS.stat, ZpStat.SJLR.stat };
		page.setStat(stat);
		page.setDuic(DC.D.name);
		page.setCang(0d);
		page.setFpyc(EFpyc.FP.key);
		etlBO.querySL(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/etl/sj/listsl" : "/sino/etl/sj/indexsl";
	}

	/**
	 * <p>
	 * 不良扣除联络单打印 查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexLosscDy")
	public String indexLosscDy(SjQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getLody() == null) {
			page.setLody(ZtConstants.DHZS_ZSFX_WDY);
		}
		// page.setSlin(true);
		page.setOrderBys(" crea desc");
		etlBO.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/etl/sj/listLosscDy"
				: "/sino/etl/sj/indexLosscDy";
	}

	/**
	 * <p>
	 * 不良扣除联络单打印更新
	 * </p>
	 * 
	 * @param jbno
	 *            制品NO
	 * @return String
	 */
	@RequestMapping(value = "/updateEtlLodyPrint", method = RequestMethod.POST)
	public @ResponseBody
	String updateEtlLodyPrint(String jbno) {
		etlBO.updateEtlLodyPrint(jbno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 打印制品卡-查询制品在库对象
	 * </p>
	 * 
	 * @param coils
	 *            钢卷号组合字符串
	 * @return
	 */
	@RequestMapping(value = "/dyZpk", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String dyZpk(String coils) {
		return null;
	}

	/**
	 * <p>
	 * 打印钢卷缺陷单-查询钢卷缺陷对象
	 * </p>
	 * 
	 * @param coils
	 *            钢卷号组合字符串
	 * @return
	 */
	@RequestMapping(value = "/dyGjcxd", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String dyGjcxd(String coils) {
		return null;
	}

	/**
	 * <p>
	 * 查看制品信息
	 * </p>
	 * 
	 * @param id
	 *            卷板No/PlieNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view!{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String view(@PathVariable String id, Model model) {
		String rel = etlBO.getMode(id);
		// 板材
		if (ZtConstants.DHZS_CAOZ_S.equals(rel)) {
			model.addAttribute("item", etlBO.getZpView(id));
			return "/sino/etl/sj/viewc";
		}
		// 卷材
		if (ZtConstants.DHZS_CAOZ_C.equals(rel)) {
			model.addAttribute("item", etlBO.getZpView(id));
			return "/sino/etl/sj/viewz";
		}
		return new Result(-1, "制品操作类型不存在").toString();
	}

	/**
	 * <p>
	 * ETL锡原单位统计表(按月)入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/etlsy", method = RequestMethod.GET)
	public String toBlmx() {
		return "/sino/etl/sj/etlSyExcel";
	}

	/**
	 * <p>
	 * 导出ETL锡原单位统计表(按月)Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outEtlSyExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outEtlSyExcel(EtlSydjVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			etlBO.fetchEtlsy(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * ETL生产速报入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/etlsb", method = RequestMethod.GET)
	public String etlsb() {
		return "/sino/etl/sj/etlSbExcel";
	}

	/**
	 * <p>
	 * 导出ETL生产速报Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outEtlSbExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outEtlSbExcel(EtlSydjVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			etlBO.fetchEtlsb(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	public IEtlBO getEtlBO() {
		return etlBO;
	}

	public void setEtlBO(IEtlBO etlBO) {
		this.etlBO = etlBO;
	}

	public IScxbBO getScBO() {
		return scBO;
	}

	public void setScBO(IScxbBO scBO) {
		this.scBO = scBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IRcBO getRcBO() {
		return rcBO;
	}

	public void setRcBO(IRcBO rcBO) {
		this.rcBO = rcBO;
	}

}
