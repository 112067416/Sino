package com.quanta.sino.fxs.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.MktfxStat;
import com.quanta.sino.fxs.bo.api.ICyrzBO;
import com.quanta.sino.fxs.bo.api.IMktfxBO;
import com.quanta.sino.fxs.vo.ChpdbQE;
import com.quanta.sino.fxs.vo.CyfxxmVO;
import com.quanta.sino.fxs.vo.MktfxQE;
import com.quanta.sino.fxs.vo.MktfxVO;
import com.quanta.sino.orm.Chpdb;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.MktfxshJl;

/**
 * <p>
 * 马口铁分析数据记录
 * </p>
 * <p>
 * create: 2011-5-8 下午08:51:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/fxs/mktfx")
public class MktfxController {

	/**
	 * 马口铁分析数据业务接口
	 */
	@Autowired
	private IMktfxBO bo;

	/**
	 * 采样日志业务接口
	 */
	@Autowired
	private ICyrzBO cyrzBO;

	/**
	 * 25号材
	 */
	private static final String $025 = "025";

	/**
	 * <p>
	 * 删除马口铁分析记录
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	@RequestMapping(value = "/deleteChpdb", method = RequestMethod.POST)
	public @ResponseBody
	String deleteChpdb(String jbnos) {
		bo.deleteChpdb(jbnos.split(","));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 编辑出货判定信息
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/editChpdb")
	public String editChpdb(String jbno, Model model) {
		Chpdb $entity = null;
		if (jbno != null && !jbno.isEmpty()) {
			$entity = bo.getChpdb(jbno);
			model.addAttribute("entity", $entity);
		}
		model.addAttribute("cjsj", DateUtils.format($entity == null ? new Date()
				: $entity.getCjsj(), "yyyy-MM-dd"));
		return "/sino/fxs/mktfx/editChpdb";
	}

	/**
	 * <p>
	 * 加载出货判定信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/loadChpdb", method = RequestMethod.POST)
	public @ResponseBody
	String loadChpdb(String jbno) {
		return bo.getChpdbForJS(jbno);
	}

	/**
	 * <p>
	 * 保存出货判定表
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveChpdb", method = RequestMethod.POST)
	public @ResponseBody
	String saveChpdb(Chpdb entity) {
		bo.saveChpdb(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 高耐蚀性马口铁出荷判定表(#75以上)记录查询主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexChpdb")
	public String indexChpdb(ChpdbQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (page.getCreaEnd() == null) {
			page.setCreaEnd(page.getCreaBegin());
		}
		page.setOrderBys("cjsj desc, jbno asc");
		bo.queryChpdb(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/fxs/mktfx/listChpdb"
				: "/sino/fxs/mktfx/indexChpdb";
	}

	/**
	 * <p>
	 * 马口铁复检记录查询
	 * </p>
	 * 
	 * @param jbno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/queryFj")
	public String queryFj(String jbno, Model model) {
		MktfxQE page = new MktfxQE();
		page.setJbno(jbno);
		page.setSize(-1);
		page.setOrderBys("fxr desc");
		bo.query(page);
		List<MktfxshJl> vos = null;
		if (page.getDatas().size() > 0) {
			vos = new ArrayList<MktfxshJl>();
			Cyrz cyrz = null;
			List<String> fxxms = null;
			for (MktfxshJl entity : page.getDatas()) {
				cyrz = cyrzBO.get(entity.getCyrzId());
				if (cyrz == null) continue;
				fxxms = Arrays.asList(cyrz.getFxxms());
				if (!(fxxms.contains(ECyfxxm.xfzl.name)
						|| fxxms.contains(ECyfxxm.cr.name) || fxxms.contains(ECyfxxm.tyl.name))) continue;
				vos.add(entity);
			}
		}
		model.addAttribute("vos", vos);
		MktfxVO mktfxVO = bo.getMubiao(null, jbno);
		model.addAttribute("mktfxVO", mktfxVO);
		return "/sino/fxs/mktfx/listFj";
	}

	/**
	 * <p>
	 * 马口铁分析记录查询主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(MktfxQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("fxr desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/fxs/mktfx/list"
				: "/sino/fxs/mktfx/index";
	}

	/**
	 * <p>
	 * 马口铁分析记录详情
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/load")
	public String load(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "COIL NO不能为空").toString();
		}
		MktfxshJl mktfxshJl = bo.get(id);
		model.addAttribute("entity", mktfxshJl);
		return "/sino/fxs/mktfx/editDl";
	}

	/**
	 * <p>
	 * 进入新增马口铁分析数据页面
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexDl")
	public String indexDl(String cyrzId, Model model) {
		Cyrz cyrz = cyrzBO.get(cyrzId);
		if (cyrz == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "采样日志主键"
					+ cyrzId + "不存在"));
			return Result.URL_ERROR;
		}
		MktfxVO mktfxVO = bo.getMubiao(cyrzId, cyrz.getJbno());
		model.addAttribute("mktfxVO", mktfxVO);
		List<CyfxxmVO> vos = new ArrayList<CyfxxmVO>();
		CyfxxmVO vo = null;
		String fxxms = null;
		ECyfxxm eCyfxxm = null;
		List<String> xms = new ArrayList<String>();
		for (String xm : cyrz.getFxxms()) {
			xms.add(xm);
		}
		// 一周一次等于TCV、Epon Flow、污点和涂料密着性
		if (xms.contains(ECyfxxm.cdw.name)) {
			if (!xms.contains(ECyfxxm.epon.name)) {
				xms.add(ECyfxxm.epon.name);
			}
			if (!xms.contains(ECyfxxm.wdsy.name)) {
				xms.add(ECyfxxm.wdsy.name);
			}
			if (!xms.contains(ECyfxxm.tlmzx.name)) {
				xms.add(ECyfxxm.tlmzx.name);
			}
			if (!xms.contains(ECyfxxm.tcv.name)
					&& ($025.equals(mktfxVO.getFuzm()) || $025.equals(mktfxVO.getFufm()))) {
				xms.add(ECyfxxm.tcv.name);
			}
		}
		for (String s : xms) {
			eCyfxxm = ECyfxxm.getByName(s);
			if (eCyfxxm == null || eCyfxxm.key.equals(ECyfxxm.cdw.key)
					|| eCyfxxm.key.equals(ECyfxxm.yd.key)
					|| eCyfxxm.key.equals(ECyfxxm.snssd.key)) continue;
			vo = new CyfxxmVO();
			vo.setKey(eCyfxxm.key);
			vo.setDescription(eCyfxxm.description);
			vos.add(vo);
			if (fxxms == null) {
				fxxms = eCyfxxm.key;
				continue;
			}
			fxxms = fxxms + "," + eCyfxxm.key;
		}
		model.addAttribute("xms", vos);
		model.addAttribute("fxxms", fxxms);
		MktfxshJl mktfxshJl = bo.getByCyrzId(cyrzId);
		if (mktfxshJl != null) {
			if (mktfxshJl.getFxr() == null) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				try {
					mktfxshJl.setFxr(sdf.parse(sdf.format(date)));
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("entity", mktfxshJl);
			return "/sino/fxs/mktfx/editDl";
		}
		return "/sino/fxs/mktfx/indexDl";
	}

	/**
	 * <p>
	 * 判定状态
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/checkStat", method = RequestMethod.POST)
	public @ResponseBody
	String checkStat(MktfxshJl entity, boolean flag, String fxxms) {
		String stat = MktfxStat.CS.stat;
		if (flag) {
			stat = MktfxStat.HG.stat;
			if (fxxms != null && !fxxms.isEmpty()) {
				String cyrzId = entity.getCyrzId();
				String jbno = entity.getJbno();
				MktfxVO mktfxVO = bo.getMubiao(cyrzId, jbno);
				if (mktfxVO == null) {
					return stat;
				}
				String[] xms = fxxms.split(",");
				for (String xm : xms) {
					stat = toJudge(xm, entity, mktfxVO);
					if (MktfxStat.HG.stat.equals(stat)) continue;
					return new Result(1, stat).toString();
				}
			}
		}
		return new Result(1, stat).toString();
	}

	/**
	 * <p>
	 * 判定马口铁分析的各项数据
	 * </p>
	 * 
	 * @param xm
	 * @param entity
	 * @param mktfxVO
	 * @return String
	 */
	private String toJudge(String xm, MktfxshJl entity, MktfxVO mktfxVO) {
		//
		if (ECyfxxm.xfzl.key.equals(xm)) {
			Double hjin = mktfxVO.getHjin();
			if (hjin != null) {
				Double xfzlCat = entity.getXfzlCat();
				if (xfzlCat != null && xfzlCat < hjin) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlCab = entity.getXfzlCab();
				if (xfzlCab != null && xfzlCab < hjin) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlSat = entity.getXfzlSat();
				if (xfzlSat != null && xfzlSat < hjin) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlSab = entity.getXfzlSab();
				if (xfzlSab != null && xfzlSab < hjin) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlNat = entity.getXfzlNat();
				if (xfzlNat != null && xfzlNat < hjin) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlNab = entity.getXfzlNab();
				if (xfzlNab != null && xfzlNab < hjin) {
					return MktfxStat.BHG.stat;
				}
			}

			Double fuzs = mktfxVO.getFuzs();
			Double fuzx = mktfxVO.getFuzx();
			if (fuzs != null && fuzs != null) {
				Double xfzlCst = entity.getXfzlCst();
				if (xfzlCst != null && (xfzlCst > fuzs || xfzlCst < fuzx)) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlSst = entity.getXfzlSst();
				if (xfzlSst != null && (xfzlSst > fuzs || xfzlSst < fuzx)) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlNst = entity.getXfzlNst();
				if (xfzlNst != null && (xfzlNst > fuzs || xfzlNst < fuzx)) {
					return MktfxStat.BHG.stat;
				}
			}
			Double fufs = mktfxVO.getFufs();
			Double fufx = mktfxVO.getFufx();
			if (fufs != null && fufx != null) {
				Double xfzlCsb = entity.getXfzlCsb();
				if (xfzlCsb != null && (xfzlCsb > fufs || xfzlCsb < fufx)) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlSsb = entity.getXfzlSsb();
				if (xfzlSsb != null && (xfzlSsb > fufs || xfzlSsb < fufx)) {
					return MktfxStat.BHG.stat;
				}
				Double xfzlNsb = entity.getXfzlNsb();
				if (xfzlNsb != null && (xfzlNsb > fufs || xfzlNsb < fufx)) {
					return MktfxStat.BHG.stat;
				}
			}

		}
		else if (ECyfxxm.cr.key.equals(xm)) {
			Double crs = mktfxVO.getCrs();
			Double crx = mktfxVO.getCrx();
			if (crs != null && crx != null) {
				Double crCt = entity.getCrCt();
				if (crCt != null && (crCt > crs || crCt < crx)) {
					return MktfxStat.BHG.stat;
				}
				Double crCb = entity.getCrCb();
				if (crCb != null && (crCb > crs || crCb < crx)) {
					return MktfxStat.BHG.stat;
				}
				Double crSt = entity.getCrSt();
				if (crSt != null && (crSt > crs || crSt < crx)) {
					return MktfxStat.BHG.stat;
				}
				Double crSb = entity.getCrSb();
				if (crSb != null && (crSb > crs || crSb < crx)) {
					return MktfxStat.BHG.stat;
				}
				Double crNt = entity.getCrNt();
				if (crNt != null && (crNt > crs || crNt < crx)) {
					return MktfxStat.BHG.stat;
				}
				Double crNb = entity.getCrNb();
				if (crNb != null && (crNb > crs || crNb < crx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.tyl.key.equals(xm)) {
			Double tyx = mktfxVO.getTyx();
			Double tys = mktfxVO.getTys();
			if (tyx != null && tys != null) {
				Double tylSt = entity.getTylSt();
				if (tylSt != null && (tylSt > tys || tylSt < tyx)) {
					return MktfxStat.BHG.stat;
				}
				Double tylSb = entity.getTylSb();
				if (tylSb != null && (tylSb > tys || tylSb < tyx)) {
					return MktfxStat.BHG.stat;
				}
				Double tylCt = entity.getTylCt();
				if (tylCt != null && (tylCt > tys || tylCt < tyx)) {
					return MktfxStat.BHG.stat;
				}
				Double tylCb = entity.getTylCb();
				if (tylCb != null && (tylCb > tys || tylCb < tyx)) {
					return MktfxStat.BHG.stat;
				}
				Double tylNt = entity.getTylNt();
				if (tylNt != null && (tylNt > tys || tylNt < tyx)) {
					return MktfxStat.BHG.stat;
				}
				Double tylNb = entity.getTylNb();
				if (tylNb != null && (tylNb > tys || tylNb < tyx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.atc.key.equals(xm)) {
			Double atcx = mktfxVO.getAtcx();
			Double atcs = mktfxVO.getAtcs();
			if (atcx != null && atcs != null) {
				Double atcZt = entity.getAtcZt();
				if (atcZt != null && (atcZt > atcs || atcZt < atcx)) {
					return MktfxStat.BHG.stat;
				}
				Double atcZb = entity.getAtcZb();
				if (atcZb != null && (atcZb > atcs || atcZb < atcx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.isv.key.equals(xm)) {
			Double isvx = mktfxVO.getIsvx();
			Double isvs = mktfxVO.getIsvs();
			if (isvx != null && isvs != null) {
				Double isvZt = entity.getIsvZt();
				if (isvZt != null && (isvZt > isvs || isvZt < isvx)) {
					return MktfxStat.BHG.stat;
				}
				Double isvZb = entity.getIsvZb();
				if (isvZb != null && (isvZb > isvs || isvZb < isvx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.tcv.key.equals(xm)) {
			Double tcvzx = mktfxVO.getTcvzx();
			Double tcvzs = mktfxVO.getTcvzs();
			if (tcvzx != null && tcvzs != null) {
				Double tcvZt = entity.getTcvZt();
				if (tcvZt != null && (tcvZt > tcvzs || tcvZt < tcvzx)) {
					return MktfxStat.BHG.stat;
				}

			}
			Double tcvfx = mktfxVO.getTcvfx();
			Double tcvfs = mktfxVO.getTcvfs();
			if (tcvfx != null && tcvfs != null) {
				Double tcvZb = entity.getTcvZb();
				if (tcvZb != null && (tcvZb > tcvfs || tcvZb < tcvfx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.tcs.key.equals(xm)) {
			Double tcsx = mktfxVO.getTcsx();
			Double tcss = mktfxVO.getTcss();
			if (tcsx != null && tcss != null) {
				Double tcsPdt = entity.getTcsPdt();
				if (tcsPdt != null && (tcsPdt > tcss || tcsPdt < tcsx)) {
					return MktfxStat.BHG.stat;
				}
				Double tcsPdb = entity.getTcsPdb();
				if (tcsPdb != null && (tcsPdb > tcss || tcsPdb < tcsx)) {
					return MktfxStat.BHG.stat;
				}
			}

		}
		else if (ECyfxxm.epon.key.equals(xm)) {
			Double epfx = mktfxVO.getEpfx();
			Double epfs = mktfxVO.getEpfs();
			if (epfx != null && epfs != null) {
				Integer eponSt = entity.getEponSt();
				if (eponSt != null && (eponSt > epfs || eponSt < epfx)) {
					return MktfxStat.BHG.stat;
				}
				Integer eponSb = entity.getEponSb();
				if (eponSb != null && (eponSb > epfs || eponSb < epfx)) {
					return MktfxStat.BHG.stat;
				}
				Integer eponCt = entity.getEponCt();
				if (eponCt != null && (eponCt > epfs || eponCt < epfx)) {
					return MktfxStat.BHG.stat;
				}
				Integer eponCb = entity.getEponCb();
				if (eponCb != null && (eponCb > epfs || eponCb < epfx)) {
					return MktfxStat.BHG.stat;
				}
				Integer eponNt = entity.getEponNt();
				if (eponNt != null && (eponNt > epfs || eponNt < epfx)) {
					return MktfxStat.BHG.stat;
				}
				Integer eponNb = entity.getEponNb();
				if (eponNb != null && (eponNb > epfs || eponNb < epfx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.wdsy.key.equals(xm)) {
			Double wdx = mktfxVO.getWdx();
			Double wds = mktfxVO.getWds();
			if (wdx != null && wds != null) {
				Integer wdsyt = entity.getWdsyt();
				if (wdsyt != null && (wdsyt > wds || wdsyt < wdx)) {
					return MktfxStat.BHG.stat;
				}
				Integer wdsyb = entity.getWdsyb();
				if (wdsyb != null && (wdsyb > wds || wdsyb < wdx)) {
					return MktfxStat.BHG.stat;
				}
			}

		}
		else if (ECyfxxm.tlmzx.key.equals(xm)) {
			Double mzxs = mktfxVO.getMzxs();
			Double mzxx = mktfxVO.getMzxx();
			if (mzxs != null && mzxx != null) {
				Integer tlmzxSt = entity.getTlmzxSt();
				if (tlmzxSt != null && (tlmzxSt > mzxs || tlmzxSt < mzxx)) {
					return MktfxStat.BHG.stat;
				}
				Integer tlmzxSb = entity.getTlmzxSb();
				if (tlmzxSb != null && (tlmzxSb > mzxs || tlmzxSb < mzxx)) {
					return MktfxStat.BHG.stat;
				}
				Integer tlmzxCt = entity.getTlmzxCt();
				if (tlmzxCt != null && (tlmzxCt > mzxs || tlmzxCt < mzxx)) {
					return MktfxStat.BHG.stat;
				}
				Integer tlmzxCb = entity.getTlmzxCb();
				if (tlmzxCb != null && (tlmzxCb > mzxs || tlmzxCb < mzxx)) {
					return MktfxStat.BHG.stat;
				}
				Integer tlmzxNt = entity.getTlmzxNt();
				if (tlmzxNt != null && (tlmzxNt > mzxs || tlmzxNt < mzxx)) {
					return MktfxStat.BHG.stat;
				}
				Integer tlmzxNb = entity.getTlmzxNb();
				if (tlmzxNb != null && (tlmzxNb > mzxs || tlmzxNb < mzxx)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		else if (ECyfxxm.plt.key.equals(xm)) {
			Double pltx = mktfxVO.getPltx();
			Double plts = mktfxVO.getPlts();
			Double pltPd = entity.getPltPd();
			if (pltx != null && plts != null && pltPd != null
					&& (pltPd > plts || pltPd < pltx)) {
				return MktfxStat.BHG.stat;
			}
		}
		else if (ECyfxxm.pore.key.equals(xm)) {
			Double pex = mktfxVO.getPex();
			Double pes = mktfxVO.getPes();
			if (pex != null && pes != null) {
				Integer porePdt = entity.getPorePdt();
				if (porePdt != null && (porePdt > pes || porePdt < pex)) {
					return MktfxStat.BHG.stat;
				}
				Integer porePdb = entity.getPorePdb();
				if (porePdb != null && (porePdb > pes || porePdb < pex)) {
					return MktfxStat.BHG.stat;
				}
			}
		}
		return MktfxStat.HG.stat;
	}

	/**
	 * <p>
	 * 保存马口铁分析记录
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(MktfxshJl entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		if (entity.getId() == null || entity.getId().isEmpty()) {
			bo.save(entity, user);
		}
		else {
			bo.update(entity, user);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 修改马口铁分析记录
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(MktfxshJl entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		bo.update(entity, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获取附着量 涂油量 目标量
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getMubiao", method = RequestMethod.POST)
	public @ResponseBody
	String getMubiao(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "COIL NO不能为空").toString();
		}
		// return bo.getMubiao(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除马口铁分析记录
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		bo.deletes(ids);
		return Result.SUCCESS;
	}

	public IMktfxBO getBo() {
		return bo;
	}

	public void setBo(IMktfxBO bo) {
		this.bo = bo;
	}

	public ICyrzBO getCyrzBO() {
		return cyrzBO;
	}

	public void setCyrzBO(ICyrzBO cyrzBO) {
		this.cyrzBO = cyrzBO;
	}

}
