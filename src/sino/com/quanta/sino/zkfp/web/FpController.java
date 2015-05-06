package com.quanta.sino.zkfp.web;

import java.io.IOException;
import java.util.Arrays;
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
import com.coco.sys.bo.api.ICodeBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Code119;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.EFace;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhCh;
import com.quanta.sino.dh.constants.DhConstants;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zkfp.bo.api.IFpBO;
import com.quanta.sino.zkfp.constants.ZkfpConstance;
import com.quanta.sino.zkfp.vo.FpVO;
import com.quanta.sino.zkfp.vo.FpckVO;
import com.quanta.sino.zkfp.vo.PError;
import com.quanta.sino.zkfp.vo.ZkfpErrorVO;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.vo.JqcQE;
import com.quanta.sino.zs.vo.ZsListQE;

/**
 * <p>
 * 分配指示管理
 * </p>
 * <p>
 * create: 2010-12-31 上午10:37:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/zkfp/fp")
public class FpController {

	/**
	 * 原材卷板业务层接口
	 */
	@Autowired
	private IYcaitpBO ycaitpBO;

	/**
	 * 制品业务层接口
	 */
	@Autowired
	private IZpBO zpBO;

	/**
	 * 订货业务层接口
	 */
	@Autowired
	private IDhBO dhBO;

	/**
	 * 分配业务层接口
	 */
	@Autowired
	private IFpBO fpBO;

	/**
	 * 指示对象业务层接口
	 */
	@Autowired
	private IZsBO zsBO;

	/**
	 * 码表业务接口
	 */
	@Autowired
	private ICodeBO codeBO;

	/**
	 * <p>
	 * 分配参考LIST一览入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/fpckList", method = RequestMethod.GET)
	public String toFpckList(Model model) {
		StringBuilder builder = new StringBuilder();
		builder.append("'").append(EXpzk.SC.key).append("':'").append(EXpzk.SC.value).append("'");
		for (ChanType chan : ChanType.values()) {
			if (ChanType.ns.key.equals(chan.key)) continue;
			builder.append(",'").append(chan.key).append("':'").append(chan.name).append("'");
		}
		model.addAttribute("chan", builder.toString());
		StringBuilder pz = new StringBuilder();
		pz.append("'").append(Code118.coil.key).append("':'").append(Code118.coil.name).append("','").append(Code118.sheet.key).append("':'").append(Code118.sheet.name).append("'");
		model.addAttribute("pz", pz.toString());
		return "/sino/zkfp/fp/fpckList";
	}

	/**
	 * <p>
	 * 导出在库分配参考LIST明细Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outFpckListExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outFpckListExcel(FpckVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			fpBO.fetchFpckList(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 分配错误一览入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/zkfpErrors", method = RequestMethod.GET)
	public String toZkfpError() {
		return "/sino/zkfp/fp/zkfpErrors";
	}

	/**
	 * <p>
	 * 导出在库分配错误明细Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outZkfpErrorExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outZkfpErrorExcel(ZkfpErrorVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			fpBO.fetchZkfpError(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 分配取消操作
	 * </p>
	 * 
	 * @param ids
	 *            制品No.
	 * @param fpno
	 *            分配No.
	 * @param xpzk
	 *            现品状况
	 * @param dhno
	 *            订货No
	 * @param line
	 *            行号
	 * @return String
	 */
	@RequestMapping(value = "/doFpqx", method = RequestMethod.POST)
	public @ResponseBody
	String doFpqx(String[] ids, String fpno, String xpzk, String dhno,
			String line) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时，请重新登录系统").toString();
		}
		if (ids == null || ids.length == 0) {
			return new Result(-1, "未选择分配取消的现品").toString();
		}
		List<String> jbnos = Arrays.asList(ids);
		if (fpno == null || fpno.isEmpty()) {
			return new Result(-1, "分配No.为空").toString();
		}
		if (xpzk == null || xpzk.isEmpty()) {
			return new Result(-1, "分配指示对象信息有误,没有现品状况").toString();
		}
		if ((dhno == null || dhno.isEmpty())
				&& (line == null || line.isEmpty())) {
			return new Result(-1, "分配No.没有对应的订货No.").toString();
		}
		return fpBO.doFpqx(jbnos, fpno, dhno, line, xpzk, user);
	}

	/**
	 * <p>
	 * 根据分配No.,获得分配信息
	 * </p>
	 * 
	 * @param fpno
	 *            分配No
	 * @return String
	 */
	@RequestMapping(value = "getFpqx", method = RequestMethod.POST)
	public @ResponseBody
	String getFpqx(String fpno) {
		if (fpno == null || (fpno = fpno.trim()).isEmpty()) {
			return new Result(-1, "待取消分配No.为空").toString();
		}
		return fpBO.getFpqx(fpno);
	}

	/**
	 * <p>
	 * 根据分配No,查询剪切材指示对象。对应分配取消功能菜单
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexJqc")
	public String indexJqc(JqcQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setStat(null);
		page.setYczk(null);
		page.setXpzk(null);
		page.setFpyc(null);
		page.setNeZsdxStat(ZtConstants.ZSDX_STAT_QX);
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			return "/sino/zkfp/fp/indexFpqx";
		}
		zsBO.queryJqc(page);
		model.addAttribute("page", page);
		return "/sino/zkfp/fp/listJqc";
	}

	/**
	 * <p>
	 * 根据分配No,查询素材指示对象
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexSc")
	public String indexSc(ZsListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setNeZsdxStat(ZtConstants.ZSDX_STAT_QX);
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			return "/sino/zkfp/fp/indexFpqx";
		}
		zsBO.queryZsdx(page);
		model.addAttribute("page", page);
		return "/sino/zkfp/fp/listSc";
	}

	/**
	 * <p>
	 * 分配操作
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/doFp", method = RequestMethod.POST)
	public @ResponseBody
	String doFp(FpVO vo) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		if (vo.getFpno() != null && !vo.getFpno().isEmpty()) {
			return new Result(-1, "该订货合同已做分配,想重新再分配请返回").toString();
		}
		if (vo.getDhno() == null || vo.getDhno().isEmpty()
				|| vo.getLine() == null || vo.getLine().isEmpty()) {
			return new Result(-1, "订货No.为空").toString();
		}
		if (vo.getJbnos() == null || vo.getJbnos().size() == 0) {
			return new Result(-1, "未选择待分配的现品").toString();
		}
		if (vo.getZxbb() != null
				&& !(ZkfpConstance.ZKFP_ZXBB_0.equals(vo.getZxbb()) || ZkfpConstance.ZKFP_ZXBB_1.equals(vo.getZxbb()))) {
			return new Result(-1, "再选输入框中,只能输入" + ZkfpConstance.ZKFP_ZXBB_0
					+ "和" + ZkfpConstance.ZKFP_ZXBB_1).toString();
		}
		// 不做剪边生产用0表示;不能用空表示
		if (vo.getJbkb() == null || vo.getJbkb().isEmpty()) {
			vo.setJbkb(ZkfpConstance.ZKFP_JBKB_0);
		}
		if (vo.getJbkb() != null
				&& !(ZkfpConstance.ZKFP_JBKB_0.equals(vo.getJbkb()) || ZkfpConstance.ZKFP_JBKB_1.equals(vo.getJbkb()))) {
			return new Result(-1, "剪边输入框中,只能输入" + ZkfpConstance.ZKFP_JBKB_0
					+ "和" + ZkfpConstance.ZKFP_JBKB_1).toString();
		}
		if (vo.getClfp() != null
				&& !(ZkfpConstance.ZKFP_CLFP_0.equals(vo.getClfp()) || ZkfpConstance.ZKFP_CLFP_1.equals(vo.getClfp()))) {
			return new Result(-1, "允许超出输入框中,只能输入" + ZkfpConstance.ZKFP_CLFP_0
					+ "和" + ZkfpConstance.ZKFP_CLFP_1).toString();
		}
		if (vo.getQzbj() != null
				&& !(ZkfpConstance.ZKFP_QZBJ_0.equals(vo.getQzbj()) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
			return new Result(-1, "强制输入框中,只能输入" + ZkfpConstance.ZKFP_QZBJ_0
					+ "和" + ZkfpConstance.ZKFP_QZBJ_1).toString();
		}
		if (EXpzk.SC.key.equals(vo.getXpzk())
				&& ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj())) {
			return new Result(-1, "素材不能进行强制分配").toString();
		}
		return fpBO.doFp(vo, user);
	}

	/**
	 * <p>
	 * 分配操作
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/checkFp", method = RequestMethod.POST)
	public @ResponseBody
	String checkFp(FpVO vo) {
		if (vo.getFpno() != null && !vo.getFpno().isEmpty()) {
			return new Result(-1, "该订货合同已做分配,想重新再分配请返回").toString();
		}
		if (vo.getDhno() == null || vo.getDhno().isEmpty()
				|| vo.getLine() == null || vo.getLine().isEmpty()) {
			return new Result(-1, "订货No.为空").toString();
		}
		if (vo.getJbnos() == null || vo.getJbnos().size() == 0) {
			return new Result(-1, "未选择待分配的现品").toString();
		}
		if (vo.getZxbb() != null
				&& !(ZkfpConstance.ZKFP_ZXBB_0.equals(vo.getZxbb()) || ZkfpConstance.ZKFP_ZXBB_1.equals(vo.getZxbb()))) {
			return new Result(-1, "再选输入框中,只能输入" + ZkfpConstance.ZKFP_ZXBB_0
					+ "和" + ZkfpConstance.ZKFP_ZXBB_1).toString();
		}
		// 不做剪边生产用0表示;不能用空表示
		if (vo.getJbkb() == null || vo.getJbkb().isEmpty()) {
			vo.setJbkb(ZkfpConstance.ZKFP_JBKB_0);
		}
		if (vo.getJbkb() != null
				&& !(ZkfpConstance.ZKFP_JBKB_0.equals(vo.getJbkb()) || ZkfpConstance.ZKFP_JBKB_1.equals(vo.getJbkb()))) {
			return new Result(-1, "剪边输入框中,只能输入" + ZkfpConstance.ZKFP_JBKB_0
					+ "和" + ZkfpConstance.ZKFP_JBKB_1).toString();
		}
		if (vo.getClfp() != null
				&& !(ZkfpConstance.ZKFP_CLFP_0.equals(vo.getClfp()) || ZkfpConstance.ZKFP_CLFP_1.equals(vo.getClfp()))) {
			return new Result(-1, "允许超出输入框中,只能输入" + ZkfpConstance.ZKFP_CLFP_0
					+ "和" + ZkfpConstance.ZKFP_CLFP_1).toString();
		}
		if (vo.getQzbj() != null
				&& !(ZkfpConstance.ZKFP_QZBJ_0.equals(vo.getQzbj()) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
			return new Result(-1, "强制输入框中,只能输入" + ZkfpConstance.ZKFP_QZBJ_0
					+ "和" + ZkfpConstance.ZKFP_QZBJ_1).toString();
		}
		if (EXpzk.SC.key.equals(vo.getXpzk())
				&& ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj())) {
			return new Result(-1, "素材不能进行强制分配").toString();
		}
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(vo.getDhno(), vo.getLine()));
		if (dhuoTp == null) {
			return new Result(-1, "订货合同不存在").toString();
		}
		// 订货合同状态为仁样确认和已分配的才能做分配操作
		if (!(DhStat.assign.key.equals(dhuoTp.getStat()) || DhStat.confirm.key.equals(dhuoTp.getStat()))) {
			return new Result(-1, "订货合同状态为"
					+ DhStat.get(dhuoTp.getStat()).name() + "。不能对其做分配操作").toString();
		}
		if (!DhConstants.JSTJ_DL.equals(dhuoTp.getJstj())) {
			return new Result(-1, "该订货合同未设置结算条件").toString();
		}
		// 品种代码
		String pzno = dhuoTp.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-1, "该订货合同信息有误,没有设置品种代码").toString();
		}
		Code118 code118 = Code118.get(pzno.substring(0, 1));
		if (code118 == null) {
			return new Result(-1, "该订货合同信息有误,品种代码在码表中不存在").toString();
		}
		//
		if (Code118.coil.key.equals(code118.key)
				&& EXpzk.BZP.key.equals(vo.getXpzk())) {
			return new Result(-1, "不能将剪切板制品分配给钢卷类型的订货合同").toString();
		}
		if (!EXpzk.BZP.key.equals(vo.getXpzk())
				&& ZkfpConstance.ZKFP_ZXBB_1.equals(vo.getZxbb())) {
			return new Result(-1, "只有剪切板制品才能去做分选操作").toString();
		}
		// 订货合同的差厚
		String chdx = null;
		if ((chdx = dhuoTp.getChdx()) != null && !chdx.isEmpty()
				&& DhCh.D.equals(chdx.substring(0, 1))
				&& ZkfpConstance.ZKFP_JBKB_1.equals(vo.getJbkb())) {
			return new Result(100, "不能对D-Mark标识的订货合同做剪边操作,是否确定分配?").toString();
		}
		if (vo.getWfpl() < vo.getCqzs()
				&& !ZkfpConstance.ZKFP_CLFP_1.equals(vo.getClfp())) {
			return new Result(-1, "分配指示量大于该订货合同的未分配量,要允许超出才能分配").toString();
		}
		if (EXpzk.BZP.key.equals(vo.getXpzk())
				|| EXpzk.JZP.key.equals(vo.getXpzk())) {
			String checkResult = zpBO.getZtbjJbnos(vo.getJbnos());
			if (checkResult != null && !checkResult.isEmpty()) {
				return new Result(100, "制品" + checkResult + "有作业停止标识,是否确定分配?").toString();
			}
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入分配指示页面。同时查询原材卷板待分配记录
	 * </p>
	 * 
	 * @param page
	 * @param dhno
	 * @param line
	 * @param sczsId
	 * @param flag
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexYcai")
	public String indexYcai(YcaiQE page, String dhno, String line,
			String sczsId, boolean flag, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setLine(null);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 分配余材
		String[] fpycs = { EFpyc.CS.key, EFpyc.YC.key };
		page.setFpyc(fpycs);
		// 状态
		String[] stats = { YbStat.CS.stat, YbStat.YRK.stat };
		page.setStats(stats);
		page.setOrderBys("xpho, xpkn, yuny, face,jbno");
		if (!page.isQuery()) {
			model.addAttribute("page", page);
			model.addAttribute("dhno", dhno);
			model.addAttribute("line", line);
			model.addAttribute("sczsId", sczsId);
			return "/sino/zkfp/fp/indexFp";
		}
		if (flag) {
			DhuoTp entity = dhBO.get(new DhuoTpPk(dhno, line));
			page.setYuny(entity.getYuny());
			page.setHouu(entity.getHouu());
			page.setKuan(entity.getKuan());
			// page.setSfbs(entity.getNwai());
			page.setGzlx(entity.getGgno().substring(0, 1));
			EFace eFace = EFace.get(entity.getFace());
			if (eFace != null) {
				page.setFaces(eFace.values);
			}
		}
		if (page.getXphoE() == null || page.getXphoE().doubleValue() == 0) {
			page.setXphoE(page.getXphoS());
		}
		if (page.getXpknE() == null || page.getXpknE().doubleValue() == 0) {
			page.setXpknE(page.getXpknS());
		}
		ycaitpBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("dhno", dhno);
		model.addAttribute("line", line);
		return "/sino/zkfp/fp/listYcai";
	}

	/**
	 * <p>
	 * 制品分页查询
	 * </p>
	 * 
	 * @param zpzk
	 *            现品状况
	 * @param ids
	 * @param page
	 * @param dhno
	 * @param line
	 * @param flag
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/indexZp!{xpzk}")
	public String indexZp(@PathVariable String xpzk, ZpQE page, String dhno,
			String line, boolean flag, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		// 设置现品在库
		page.setXpzk(xpzk);
		// 分配/余材标记
		String[] fpycs = { EFpyc.CS.key, EFpyc.YC.key };
		page.setFpycs(fpycs);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		page.setStat(ZpStat.CS.stat);
		page.setOrderBys("xpho asc, xpkn asc, xpcn asc, yuny asc, face asc,jbno asc");
		if (flag) {
			DhuoTp entity = dhBO.get(new DhuoTpPk(dhno, line));
			page.setYuny(entity.getYuny());
			page.setXpho(entity.getHouu());
			page.setXpkn(entity.getKuan());
			if (Code118.coil.key.equals(entity.getPzno().substring(0, 1))) {
				page.setXpcn(entity.getCang());
			}
			// page.setGzlx(entity.getGgno().substring(0, 1));
			page.setFace(entity.getFace());
		}
		if (page.getXphoE() == null || page.getXphoE().doubleValue() == 0) {
			page.setXphoE(page.getXphoS());
		}
		if (page.getXpknE() == null || page.getXpknE().doubleValue() == 0) {
			page.setXpknE(page.getXpknS());
		}
		if (!EXpzk.JZP_KEY.equals(xpzk)
				&& (page.getXpcnE() == null || page.getXpcnE().doubleValue() == 0)) {
			page.setXpcnE(page.getXpcnS());
		}
		zpBO.query(page);
		model.addAttribute("page", page);
		model.addAttribute("dhno", dhno);
		model.addAttribute("line", line);
		return page.isQuery() ? "/sino/zkfp/fp/listZp"
				: "/sino/zkfp/fp/indexFp";
	}

	/**
	 * <p>
	 * 根据订货合同查询待分配现品信息
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @return String
	 */
	@RequestMapping(value = "checkDhno", method = RequestMethod.POST)
	public @ResponseBody
	String checkDhno(String dhno, String line) {
		if (dhno == null || dhno.isEmpty()) {
			return new Result(-1, "订货合同为空").toString();
		}
		if (line == null || line.isEmpty()) {
			return new Result(-1, "订货合同行号为空").toString();
		}
		return fpBO.getFpzs(dhno, line);
	}

	/**
	 * <p>
	 * 查看现品配匹情况
	 * </p>
	 * 
	 * @param xpzk
	 *            现品状况
	 * @param jbno
	 *            现品No.
	 * @param dhno
	 *            订货号
	 * @param line
	 *            订货行号
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/viewMatch")
	public String viewMatch(String xpzk, String jbno, String dhno, String line,
			Model model) {
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "订货合同不存在"));
			return Result.URL_ERROR;
		}
		YcaiTp ycaiTp = null;
		ZpngTp zpngTp = null;
		if (EXpzk.SC.key.equals(xpzk)) {
			ycaiTp = ycaitpBO.get(jbno);
		}
		else {
			zpngTp = zpBO.getZp(jbno);
		}
		if (ycaiTp == null && zpngTp == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "现品No.不存在"));
			return Result.URL_ERROR;
		}
		List<PError> errors = fpBO.findCompareErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		model.addAttribute("errors", errors);
		model.addAttribute("xpzk", EXpzk.get(xpzk).value);
		Code119 code119 = Code119.get(dhuoTp.getPzno().substring(1));
		model.addAttribute("grade", code119.name);
		model.addAttribute("jbno", jbno);
		model.addAttribute("pageSize", ZkfpConstance.PAGE_SIZE);
		return "/sino/zkfp/fp/matchErrors";
	}

	/**
	 * @return the ycaitpBO
	 */
	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	/**
	 * @param ycaitpBO
	 *            the ycaitpBO to set
	 */
	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

	/**
	 * @return the zpBO
	 */
	public IZpBO getZpBO() {
		return zpBO;
	}

	/**
	 * @param zpBO
	 *            the zpBO to set
	 */
	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	/**
	 * @return the dhBO
	 */
	public IDhBO getDhBO() {
		return dhBO;
	}

	/**
	 * @param dhBO
	 *            the dhBO to set
	 */
	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	/**
	 * @return the fpBO
	 */
	public IFpBO getFpBO() {
		return fpBO;
	}

	/**
	 * @param fpBO
	 *            the fpBO to set
	 */
	public void setFpBO(IFpBO fpBO) {
		this.fpBO = fpBO;
	}

	/**
	 * @return the zsBO
	 */
	public IZsBO getZsBO() {
		return zsBO;
	}

	/**
	 * @param zsBO
	 *            the zsBO to set
	 */
	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

}
