package com.quanta.sino.ch.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.Attachment;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.dao.api.IChuhDAO;
import com.quanta.sino.ch.dao.api.IZng1DAO;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.ch.dao.api.IZxzsDAO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.CpdrVO;
import com.quanta.sino.ch.vo.ZxdzItem;
import com.quanta.sino.ch.vo.ZxdzVO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFppz;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.cmn.constants.KhyfStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.ChuhLp;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZxzsTp;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yygl.bo.api.IKhyfBO;

/**
 * <p>
 * 装箱对照业务实现类
 * </p>
 * <p>
 * create: 2011-3-1 上午10:40:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxdzBO implements IZxdzBO {

	private IChuhDAO dao;

	/**
	 * 装箱指示业务接口
	 */
	private IZxzsBO bo;

	/**
	 * 制品在库业务接口
	 */
	private IZpBO zpbo;

	/**
	 * 订货单业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 检查证明书业务接口
	 */
	private IJczmsBO jczmBo;

	/**
	 * 装箱指示书1数据接口
	 */
	private IZng1DAO zng1DAO;

	/**
	 * 装箱指示书1数据接口
	 */
	private IZng2DAO zng2DAO;

	/**
	 * 应收账款业务接口
	 */
	private IFkfpBO yszkBO;

	/**
	 * 客户运费业务接口
	 */
	private IKhyfBO khyfBO;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;

	/**
	 * 装箱指示数据接口
	 */
	private IZxzsDAO zxzsDAO;

	/**
	 * 用户主文件业务接口
	 */
	private IYongBO yongBO;

	/**
	 * 附件业务层接口
	 */
	private IAttachmentBO attachmentBO;

	private static String E = "E";

	@Override
	public void zxdz(ZxdzVO vo) {
		String zxno = vo.getZxno();
		List<Zng1Tp> zng1Tps = zng1DAO.find(zxno);
		if (zng1Tps.size() == 0) {
			throw new CocoException(-1, "装箱指示书No." + zxno + "不存在");
		}
		// 判断装箱指示书是否已做装箱对照
		ZxzsTp zxzsTp = zng1Tps.get(0).getZxzsTp();
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			throw new CocoException(-1, "该装箱指示书为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能重复做装箱对照操作");
		}
		// 修改状态为已发货
		zxzsTp.setStat(ChStat.YFH.stat);
		zxzsTp.setChri(vo.getChri());
		zxzsTp.setDdr(vo.getDdr());
		zxzsDAO.update(zxzsTp);
		List<Zng2Tp> zng2Tps = bo.findZxzsMx(zxno);
		if (zng2Tps.size() == 0) {
			throw new CocoException(-1, "该装箱指示书No." + zxno + "不存在");
		}
		// 装箱指示书中的制品信息。制品号为key,合同号，行号为value
		Map<String, String> zxzsMap = new HashMap<String, String>();
		// 设值给map
		for (Zng2Tp zng2Tp : zng2Tps) {
			if (zng2Tp.getZng1Tp() == null) {
				continue;
			}
			zxzsMap.put(zng2Tp.getJbno(), zng2Tp.getZng1Tp().getDhno()
					+ zng2Tp.getZng1Tp().getLine());
		}
		String zpno = null;
		String dhno = null;
		String line = null;
		// 实物的制品信息。制品号为key,合同号，行号为value
		Map<String, String> swMap = new HashMap<String, String>();
		// 错误提示
		StringBuilder errors = new StringBuilder();
		// 制品
		ZpngTp zpngTp = null;
		String result;
		for (ZxdzItem item : vo.getItems()) {
			if ((zpno = item.getZpno()) == null || zpno.isEmpty()) {
				continue;
			}
			if ((dhno = item.getDhno()) == null) {
				errors.append("实物制品" + zpno + "没有输入对应的订货合同No.").append("<br />");
				continue;
			}
			if (dhno.length() < 9) {
				errors.append("实物制品" + zpno + "对应的订货合同No.有误").append("<br />");
				continue;
			}
			line = dhno.substring(7);
			dhno = dhno.substring(0, 7);
			if (swMap.get(zpno) != null) {
				errors.append("实物制品" + zpno + "重复输入").append("<br />");
				continue;
			}
			if (zxzsMap.get(zpno) == null) {
				errors.append("制品" + zpno + ",在装箱指示书中不存在").append("<br />");
				continue;
			}
			if (!zxzsMap.get(zpno).equals(dhno + line)) {
				errors.append("实物制品" + zpno + "对应的订货合同No.与装箱指示书中不 一致").append("<br />");
				continue;
			}
			zpngTp = zpbo.getRef(zpno);
			// 判断要发 货的现品，是否符合发货条件
			result = this.isZp(zpngTp, zxno);
			if (result != null) {
				errors.append(zpno + result).append("<br />");
				continue;
			}
			swMap.put(zpno, dhno + line);
			// 设置送货单号
			zpngTp.setShno(zxno);
			// 设置出货实绩登录年月
			zpngTp.setChsd(vo.getChri());
			// 设置删除标记为已出货
			zpngTp.setScbj(EScbj.YCH.key);
		}
		if (!errors.toString().isEmpty()) {
			throw new CocoException(-1, errors.toString());
		}
		if (zxzsMap.size() != swMap.size()) {
			throw new CocoException(-1, "装箱指示书的制品标签个数与实物标签个数不相等");
		}
		DhuoTp dhuoTp = null;
		Date chri = vo.getChri();
		// 出货日志
		ChuhLp chuhLp = null;
		// 付款发票
		Fkfp fkfp = null;
		// 客户运费
		Khyf khyf = null;
		// 统计每个订货合同的出货重量
		long chzl = 0l;
		// 统计每个订货合同的出货数量
		long chus = 0l;
		// 循环装箱指示书主表
		for (Zng1Tp zng1Tp : zng1Tps) {
			dhuoTp = dhBo.get(new DhuoTpPk(zng1Tp.getDhno(), zng1Tp.getLine()));
			if (dhuoTp == null) {
				throw new CocoException(-1, "该订货合同不存在");
			}
			// 出货重量
			chzl = zng1Tp.getChzl() != null ? NumberUtils.formatDouToLong(zng1Tp.getChzl() * 1000, 0)
					: 0;
			// 原出货重量
			chus = dhuoTp.getChus() != null ? dhuoTp.getChus() : 0;
			dhuoTp.setChus(chzl + chus);
			dhBo.update(dhuoTp);
			chuhLp = parseChuhLp(dao.getChuhLp(zng1Tp.getDhno(), zng1Tp.getLine(), chri), zng1Tp, dhuoTp);
			dao.saveOrUpdate(chuhLp);
			fkfp = parseFkfp(yszkBO.get(zng1Tp.getDhno(), zng1Tp.getLine(), chri, EFppz.zp.key), zng1Tp, dhuoTp);
			yszkBO.saveOrUpdate(fkfp);
			khyf = parseKhyf(khyfBO.getUnique(chri, zxzsTp.getYsgs(), zxzsTp.getUser(), zng1Tp.getDhno(), zng1Tp.getLine(), zxzsTp.getShno(), zxzsTp.getAddr()), zng1Tp);
			khyfBO.saveOrUpdate(khyf);
		}
	}

	@Override
	public String getCheck(ZxdzVO vo) {
		String zxno = vo.getZxno();
		List<Zng1Tp> zng1Tps = zng1DAO.find(zxno);
		if (zng1Tps.size() == 0) {
			return new Result(-1, "装箱指示书No." + zxno + "不存在").toString();
		}
		// 判断装箱指示书是否已做装箱对照
		ZxzsTp zxzsTp = zng1Tps.get(0).getZxzsTp();
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			return new Result(-1, "该装箱指示书状态为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能重复做装箱对照操作").toString();
		}
		List<Zng2Tp> zng2Tps = bo.findZxzsMx(zxno);
		if (zng2Tps.size() == 0) {
			return new Result(-1, "该装箱指示书No." + zxno + "不存在").toString();
		}
		// 装箱指示书中的制品信息。制品号为key,合同号，行号为value
		Map<String, String> zxzsMap = new HashMap<String, String>();
		// 设值给map
		for (Zng2Tp zng2Tp : zng2Tps) {
			if (zng2Tp.getZng1Tp() == null) continue;
			zxzsMap.put(zng2Tp.getJbno(), zng2Tp.getZng1Tp().getDhno()
					+ zng2Tp.getZng1Tp().getLine());
		}
		String zpno = null;
		String dhno = null;
		String line = null;
		// 实物的制品信息。制品号为key,合同号，行号为value
		Map<String, String> swMap = new HashMap<String, String>();
		// 错误提示
		StringBuilder errors = new StringBuilder();
		// 制品
		ZpngTp zpngTp = null;
		String result;
		for (ZxdzItem item : vo.getItems()) {
			if ((zpno = item.getZpno()) == null || zpno.isEmpty()) {
				continue;
			}
			if ((dhno = item.getDhno()) == null) {
				errors.append("实物制品" + zpno + "没有输入对应的订货合同No.").append("\n").toString();
				continue;
			}
			if (dhno.length() < 9) {
				errors.append("实物制品" + zpno + "对应的订货合同No.有误").append("\n").toString();
				continue;
			}
			line = dhno.substring(7);
			dhno = dhno.substring(0, 7);
			if (swMap.get(zpno) != null) {
				errors.append("实物制品" + zpno + "重复输入").append("\n");
				continue;
			}
			if (zxzsMap.get(zpno) == null) {
				errors.append("制品" + zpno + ",在装箱指示书中不存在").append("\n").toString();
				continue;
			}
			if (!zxzsMap.get(zpno).equals(dhno + line)) {
				errors.append("实物制品" + zpno + "对应的订货合同No.与装箱指示书中不 一致").append("\n").toString();
				continue;
			}
			zpngTp = zpbo.getZp(zpno);
			// 判断要发 货的现品，是否符合发货条件
			result = this.isZp(zpngTp, zxno);
			if (result != null) {
				errors.append(zpno + result).append("<br />");
				continue;
			}
			swMap.put(zpno, dhno + line);
		}
		if (!errors.toString().isEmpty()) {
			return new Result(-1, errors.toString()).toString();
		}
		if (zxzsMap.size() != swMap.size()) {
			Iterator<String> iter = zxzsMap.keySet().iterator();
			String key;
			while (iter.hasNext()) {
				key = iter.next();
				if (swMap.get(key) != null) continue;
				if (errors.length() > 0) {
					errors.append(",").append(key);
					continue;
				}
				errors.append(key);
			}
			return new Result(-1, "装箱指示书的制品标签个数与实物标签个数不相等。缺少实物标签"
					+ errors.toString()).toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public void cjpZxdz(ZxdzVO vo, List<String> swList) {
		String zxno = vo.getZxno();
		List<Zng1Tp> zng1Tps = zng1DAO.find(zxno);
		if (zng1Tps.size() == 0) {
			throw new CocoException(-1, "装箱指示书No." + zxno + "不存在");
		}
		// 判断装箱指示书是否已做装箱对照
		ZxzsTp zxzsTp = zng1Tps.get(0).getZxzsTp();
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			throw new CocoException(-1, "该装箱指示书为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能重复做装箱对照操作");
		}
		// 修改状态为已发货
		zxzsTp.setStat(ChStat.YFH.stat);
		zxzsTp.setChri(vo.getChri());
		zxzsTp.setDdr(vo.getDdr());
		zxzsDAO.update(zxzsTp);
		List<Zng2Tp> zng2Tps = bo.findZxzsMx(zxno);
		if (zng2Tps.size() == 0) {
			throw new CocoException(-1, "该装箱指示书No." + zxno + "不存在");
		}
		// 装箱指示书中的制品信息。制品号为key,合同号，行号为value
		List<String> zxzsList = new ArrayList<String>();
		for (Zng2Tp zng2Tp : zng2Tps) {
			if (zng2Tp.getZng1Tp() == null) {
				continue;
			}
			zxzsList.add(zng2Tp.getJbno());
		}
		// String dhno = null;
		// String line = null;
		// 错误提示
		StringBuilder errors = new StringBuilder();
		// 制品
		ZpngTp zpngTp = null;
		String result;
		List<String> zpnos = new ArrayList<String>();
		for (String zpno : swList) {
			if (zpno == null || zpno.isEmpty()) continue;
			if (zpnos.contains(zpno)) {
				errors.append("实物制品" + zpno + "重复输入").append("<br />");
				continue;
			}
			zpngTp = zpbo.getRef(zpno);
			// 判断要发 货的现品，是否符合发货条件
			result = this.isZp(zpngTp, zxno);
			if (result != null) {
				errors.append(zpno + result).append("<br />");
				continue;
			}
			// 设置送货单号
			zpngTp.setShno(zxno);
			// 设置出货实绩登录年月
			zpngTp.setChsd(vo.getChri());
			// 设置删除标记为已出货
			zpngTp.setScbj(EScbj.YCH.key);
			zpnos.add(zpno);
		}
		if (!errors.toString().isEmpty()) {
			throw new CocoException(-1, errors.toString());
		}
		if (zxzsList.size() != zpnos.size()) {
			throw new CocoException(-1, "装箱指示书的制品标签个数与实物标签个数不相等");
		}
		DhuoTp dhuoTp = null;
		Date chri = vo.getChri();
		// 出货日志
		ChuhLp chuhLp = null;
		// 付款发票
		Fkfp fkfp = null;
		// 客户运费
		Khyf khyf = null;
		// 统计每个订货合同的出货重量
		long chzl = 0l;
		// 统计每个订货合同的出货数量
		long chus = 0l;
		// 循环装箱指示书主表
		for (Zng1Tp zng1Tp : zng1Tps) {
			dhuoTp = dhBo.get(new DhuoTpPk(zng1Tp.getDhno(), zng1Tp.getLine()));
			if (dhuoTp == null) {
				throw new CocoException(-1, "该订货合同不存在");
			}
			// 出货重量
			chzl = zng1Tp.getChzl() != null ? NumberUtils.formatDouToLong(zng1Tp.getChzl() * 1000, 0)
					: 0;
			// 原出货重量
			chus = dhuoTp.getChus() != null ? dhuoTp.getChus() : 0;
			dhuoTp.setChus(chzl + chus);
			dhBo.update(dhuoTp);
			chuhLp = parseChuhLp(dao.getChuhLp(zng1Tp.getDhno(), zng1Tp.getLine(), chri), zng1Tp, dhuoTp);
			dao.saveOrUpdate(chuhLp);
			fkfp = parseFkfp(yszkBO.get(zng1Tp.getDhno(), zng1Tp.getLine(), chri, EFppz.zp.key), zng1Tp, dhuoTp);
			yszkBO.saveOrUpdate(fkfp);
			khyf = parseKhyf(khyfBO.getUnique(chri, zxzsTp.getYsgs(), zxzsTp.getUser(), zng1Tp.getDhno(), zng1Tp.getLine(), zxzsTp.getShno(), zxzsTp.getAddr()), zng1Tp);
			khyfBO.saveOrUpdate(khyf);
		}

	}

	@Override
	public String getCjpCheck(ZxdzVO vo, List<String> swList) {
		String zxno = vo.getZxno();
		List<Zng1Tp> zng1Tps = zng1DAO.find(zxno);
		if (zng1Tps.size() == 0) {
			return new Result(-1, "装箱指示书No." + zxno + "不存在").toString();
		}
		// 判断装箱指示书是否已做装箱对照
		ZxzsTp zxzsTp = zng1Tps.get(0).getZxzsTp();
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			return new Result(-1, "该装箱指示书状态为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能重复做装箱对照操作").toString();
		}
		List<Zng2Tp> zng2Tps = bo.findZxzsMx(zxno);
		if (zng2Tps.size() == 0) {
			return new Result(-1, "该装箱指示书No." + zxno + "不存在").toString();
		}
		// 装箱指示书中的制品信息。制品号为key,合同号，行号为value
		List<String> zxzsList = new ArrayList<String>();
		for (Zng2Tp zng2Tp : zng2Tps) {
			if (zng2Tp.getZng1Tp() == null) {
				continue;
			}
			zxzsList.add(zng2Tp.getJbno());
		}
		// 错误提示
		StringBuilder errors = new StringBuilder();
		// 制品
		ZpngTp zpngTp = null;
		String result;
		List<String> zpnos = new ArrayList<String>();
		for (String zpno : swList) {
			if (zpno == null || zpno.isEmpty()) continue;
			if (zpnos.contains(zpno)) {
				errors.append("实物制品" + zpno + "重复输入").append("\n");
				continue;
			}
			zpngTp = zpbo.getZp(zpno);
			// 判断要发 货的现品，是否符合发货条件
			result = this.isZp(zpngTp, zxno);
			if (result != null) {
				errors.append(zpno + result).append("\n");
				continue;
			}
			zpnos.add(zpno);
		}
		if (!errors.toString().isEmpty()) {
			return new Result(-1, errors.toString()).toString();
		}
		if (zxzsList.size() != zpnos.size()) {
			for (String key : zxzsList) {
				if (zpnos.contains(key)) continue;
				if (errors.length() > 0) {
					errors.append(",").append(key);
					continue;
				}
				errors.append(key);
			}
			return new Result(-1, "装箱指示书的制品标签个数与实物标签个数不相等。缺少实物标签"
					+ errors.toString()).toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 判断该制品是否符合发货条件
	 * </p>
	 * 
	 * @param zpngTp
	 * @param zxno
	 * @return boolean
	 */
	private String isZp(ZpngTp zpngTp, String zxno) {
		if (zpngTp == null) {
			return "制品不存在";
		}
		String duic = zpngTp.getDuic();
		// 若堆场不为C F G则返回false
		if (!(DC.C.name.equals(duic) || DC.F.name.equals(duic) || DC.G.name.equals(duic))) {
			return "制品的堆场不为C或F或G";
		}
		// 若删除标记不为初始则返回false
		if (!EScbj.CS.key.equals(zpngTp.getScbj())) {
			return "删除标记为" + EScbj.get(zpngTp.getScbj()).value;
		}
		// 若分配余材不为分配则返回false
		if (!EFpyc.FP.key.equals(zpngTp.getFpyc())) {
			return "分配余材不为分配";
		}
		// 若出货号和装箱指示书号不一致返回false
		if (!zxno.equals(zpngTp.getChno())) {
			return "出货号和装箱指示书号不一致";
		}
		return null;
	}

	/**
	 * <p>
	 * 写客户运费初始数据
	 * </p>
	 * 
	 * @param khyf
	 * @param zng1Tp
	 * @return Khyf
	 */
	private Khyf parseKhyf(Khyf khyf, Zng1Tp zng1Tp) {
		// 出货制品重量
		Double chzl = zng1Tp.getChzl() != null ? zng1Tp.getChzl() : 0;
		// 出货制品毛重量
		Double chma = zng1Tp.getChma() != null ? zng1Tp.getChma() : 0;
		// 出货制品数量
		Integer chsu = zng1Tp.getChsu() != null ? zng1Tp.getChsu() : 0;
		if (khyf == null) {
			khyf = new Khyf();
			khyf.setChri(zng1Tp.getZxzsTp().getChri());
			khyf.setYsgs(zng1Tp.getZxzsTp().getYsgs());
			khyf.setYsnm(zng1Tp.getZxzsTp().getYsnm());
			khyf.setUser(zng1Tp.getZxzsTp().getUser());
			khyf.setAbbr(zng1Tp.getZxzsTp().getAbbr());
			khyf.setName(zng1Tp.getZxzsTp().getName());
			khyf.setDhno(zng1Tp.getDhno());
			khyf.setLine(zng1Tp.getLine());
			khyf.setChzl(chzl);
			khyf.setChsu(chsu);
			khyf.setMazl(chma);
			khyf.setShno(zng1Tp.getZxzsTp().getShno());
			khyf.setShnm(zng1Tp.getZxzsTp().getShnm());
			khyf.setAddr(zng1Tp.getZxzsTp().getAddr());
			khyf.setHouu(zng1Tp.getHouu());
			khyf.setKuan(zng1Tp.getKuan());
			khyf.setCang(zng1Tp.getCang());
			khyf.setStat(KhyfStat.CS.stat);
			khyf.setZxno(zng1Tp.getZxzsTp().getZxno());
			khyf.setSpbh(zng1Tp.getSpbh());
			khyf.setNwai(zng1Tp.getNwai());
			return khyf;
		}
		khyf.setChzl(NumberUtils.format(NumberUtils.add(khyf.getChzl(), chzl).doubleValue(), 3));
		khyf.setMazl(NumberUtils.format(NumberUtils.add(khyf.getMazl(), chma).doubleValue(), 3));
		khyf.setChsu(khyf.getChsu() + chsu);
		String zxno = khyf.getZxno();
		zxno = zxno + "," + zng1Tp.getZxzsTp().getZxno();
		khyf.setZxno(zxno);
		return khyf;
	}

	/**
	 * <p>
	 * 写出货日志
	 * </p>
	 * 
	 * @param chuhLp
	 * @param zng1Tp
	 * @param dhuoTp
	 * @return ChuhLp
	 */
	private ChuhLp parseChuhLp(ChuhLp chuhLp, Zng1Tp zng1Tp, DhuoTp dhuoTp) {
		Double chzl = zng1Tp.getChzl() != null ? zng1Tp.getChzl() : 0;
		if (chuhLp == null) {
			chuhLp = new ChuhLp();
			chuhLp.setAbbr(dhuoTp.getAbbr());
			// chuhLp.setChkn(dhuoTp.getChkn());
			chuhLp.setChus(chzl);
			chuhLp.setChri(zng1Tp.getZxzsTp().getChri());
			chuhLp.setDhno(zng1Tp.getDhno());
			// chuhLp.setHfkn(dhuoTp.getHfkn());
			chuhLp.setHtdj(dhuoTp.getHtdj());
			chuhLp.setLine(zng1Tp.getLine());
			chuhLp.setNwai(dhuoTp.getNwai());
			chuhLp.setPzno(dhuoTp.getPzno());
			// chuhLp.setQixn(dhuoTp.getQixn());
			chuhLp.setShnm(zng1Tp.getZxzsTp().getShnm());
			chuhLp.setShno(zng1Tp.getZxzsTp().getShno());
			chuhLp.setStat(ChglConstants.CHRZ_START_STAT);
			chuhLp.setThqf(dhuoTp.getThqf());
			chuhLp.setUser(dhuoTp.getUser());
			// chuhLp.setYfkn(dhuoTp.getYfkn());
			chuhLp.setYsgs(zng1Tp.getZxzsTp().getYsgs());
			return chuhLp;
		}
		Double chus = chuhLp.getChus() != null ? chuhLp.getChus() : 0;
		chuhLp.setChus(chus + chzl);
		return chuhLp;
	}

	/**
	 * <p>
	 * 写付款发票
	 * </p>
	 * 
	 * @param fkfp
	 * @param zng1Tp
	 * @param dhuoTp
	 * @return Fkfp
	 */
	private Fkfp parseFkfp(Fkfp fkfp, Zng1Tp zng1Tp, DhuoTp dhuoTp) {
		Double chzl = zng1Tp.getChzl() != null ? zng1Tp.getChzl() : 0;
		// Double htdj = dhuoTp.getHtdj() != null ? dhuoTp.getHtdj() : 0;
		// Double fpje = NumberUtils.format(chzl * htdj, 2);
		Double htdj;
		Double fpje;
		// BigDecimal $chzl;
		// BigDecimal $htdj;
		YongMp yongMp = yongBO.get(dhuoTp.getUser());
		if (fkfp == null) {
			fkfp = new Fkfp();
			if (yongMp != null) {
				fkfp.setFpymc(yongMp.getFpkh());
				fkfp.setUser(yongMp.getUser());
				fkfp.setAbbr(yongMp.getAbbr());
				fkfp.setName(yongMp.getRsv1());
			}
			else {
				fkfp.setFpymc(dhuoTp.getName());
				fkfp.setUser(dhuoTp.getUser());
				fkfp.setAbbr(dhuoTp.getAbbr());
				fkfp.setName(dhuoTp.getName());
			}
			// 计算发票金额（算出总价）
			htdj = dhuoTp.getHtdj() != null ? dhuoTp.getHtdj() : 0;
			fpje = NumberUtils.format(NumberUtils.multiply(chzl, htdj).doubleValue(), 2);

			fkfp.setChri(zng1Tp.getZxzsTp().getChri());
			fkfp.setFpri(zng1Tp.getZxzsTp().getChri());
			fkfp.setDhno(dhuoTp.getDhno());
			fkfp.setLine(dhuoTp.getLine());
			fkfp.setGgno(zng1Tp.getGgno());
			fkfp.setKfzl(chzl);
			fkfp.setKpdj(htdj);
			fkfp.setFpje(fpje);
			fkfp.setFkje(0d);
			fkfp.setWsyk(fpje);
			fkfp.setHtdj(htdj);
			fkfp.setCang(dhuoTp.getCang());
			fkfp.setHouu(dhuoTp.getHouu());
			fkfp.setKuan(dhuoTp.getKuan());
			fkfp.setStat(FpStat.CS.key);
			CodeItem codeItem = codeBO.getCodeItem(CmnConstants.CODE_BFHL, "1");
			fkfp.setBxfl(Double.valueOf(codeItem.getValue()));
			fkfp.setFudw(dhuoTp.getFudw());
			fkfp.setFuzm(dhuoTp.getFuzm());
			fkfp.setFufm(dhuoTp.getFufm());
			fkfp.setSpbh(zng1Tp.getSpbh());
			fkfp.setNwai(zng1Tp.getNwai());
			fkfp.setPz(zng1Tp.getPz());
			fkfp.setDeng(zng1Tp.getDeng());
			fkfp.setThqf(E.equalsIgnoreCase(dhuoTp.getDhno().substring(0, 1)) ? Code013.usd.key
					: Code013.rmb.key);
			fkfp.setFppz(EFppz.zp.key);
			fkfp.setYuny(dhuoTp.getYuny());
			fkfp.setCond(dhuoTp.getCond());
			fkfp.setCdnm(dhuoTp.getCdnm());
			fkfp.setYyno(dhuoTp.getDdno());
			fkfp.setYynm(dhuoTp.getDdnm());
			return fkfp;
		}
		// fpje += fkfp.getFpje();
		chzl = NumberUtils.format(NumberUtils.add(chzl, fkfp.getKfzl()).doubleValue(), 3);
		// $chzl = new BigDecimal(String.valueOf(chzl));
		htdj = fkfp.getKpdj() != null ? fkfp.getKpdj() : 0;
		// $htdj = new BigDecimal(String.valueOf(htdj));
		// fpje = NumberUtils.format($chzl.multiply($htdj).doubleValue(), 2);
		fpje = NumberUtils.format(NumberUtils.multiply(chzl, htdj).doubleValue(), 2);
		fkfp.setKfzl(chzl);
		fkfp.setFpje(NumberUtils.format(fpje, 2));
		fkfp.setWsyk(NumberUtils.format(NumberUtils.subtract(fpje, fkfp.getFkje()).doubleValue(), 2));
		return fkfp;
	}

	@Override
	public void setCpno(String cpno, String[] jbnos) {
		// ZpngTp zpngTp = null;
		// for (String jbno : jbnos) {
		// zpngTp = zpbo.getZp(jbno);
		// if (zpngTp == null) {
		// throw new CocoException(-1, "制品No.为" + jbno + "的制品,不存在");
		// }
		// zpngTp.setCpno(cpno);
		// if (!EScbj.YCH.key.equals(zpngTp.getScbj())) {
		// throw new CocoException(-1, "制品No.为" + jbno + "的制品,还未发货");
		// }
		// zpbo.update(zpngTp);
		// }
		zng2DAO.setCpno(cpno, Arrays.asList(jbnos));
	}

	@Override
	public String importCpno(CpdrVO vo) {
		Attachment attachment = attachmentBO.get(vo.getAttachId());
		if (attachment == null) {
			return new Result(-1, "上传文件不存在").toString();
		}
		byte[] bytes = attachment.getStream();
		if (bytes == null || bytes.length == 0) {
			return new Result(-1, "上传文件内容为空").toString();
		}
		// 记录上传文档的错误信息
		StringBuilder errors = new StringBuilder();
		String content = new String(bytes);
		String[] lines = content.split("\n");
		// 记录当前行的数据验证结果
		String[] arrs = null;
		int index = 1;
		Map<String, List<String>> cpnos = new HashMap<String, List<String>>();
		List<String> jbnos;
		String jbno, cpno;
		for (String line : lines) {
			arrs = line.split(",");
			if (arrs.length != 2) {
				errors.append("第").append(index).append("行信息有误。").append(line).append("<br />");
				continue;
			}
			jbno = arrs[0].trim();
			cpno = arrs[1].trim();
			jbnos = cpnos.get(cpno);
			if (jbnos == null || jbnos.size() == 0) {
				jbnos = new ArrayList<String>();
				jbnos.add(jbno);
				cpnos.put(cpno, jbnos);
				continue;
			}
			jbnos.add(jbno);
		}
		Iterator<String> iterator = cpnos.keySet().iterator();
		while (iterator.hasNext()) {
			cpno = iterator.next();
			jbnos = cpnos.get(cpno);
			zng2DAO.setCpno(cpno, jbnos);
		}
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		return new Result(1, "保存成功").toString();
	}

	@Override
	public ChtjVO getChtj(Date chri) {
		Date chriS = chri;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(chri);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date chriE = calendar.getTime();
		return zng1DAO.getChtj(chriS, chriE);
	}

	@Override
	public List<ChtjVO> findChtj(String chlldId) {
		return zng1DAO.findChtj(chlldId);
	}

	public IZng1DAO getZng1DAO() {
		return zng1DAO;
	}

	public void setZng1DAO(IZng1DAO zng1dao) {
		zng1DAO = zng1dao;
	}

	public IJczmsBO getJczmBo() {
		return jczmBo;
	}

	public void setJczmBo(IJczmsBO jczmBo) {
		this.jczmBo = jczmBo;
	}

	public IChuhDAO getDao() {
		return dao;
	}

	public void setDao(IChuhDAO dao) {
		this.dao = dao;
	}

	public IZxzsBO getBo() {
		return bo;
	}

	public void setBo(IZxzsBO bo) {
		this.bo = bo;
	}

	public IZpBO getZpbo() {
		return zpbo;
	}

	public void setZpbo(IZpBO zpbo) {
		this.zpbo = zpbo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IFkfpBO getYszkBO() {
		return yszkBO;
	}

	public void setYszkBO(IFkfpBO yszkBO) {
		this.yszkBO = yszkBO;
	}

	public IKhyfBO getKhyfBO() {
		return khyfBO;
	}

	public void setKhyfBO(IKhyfBO khyfBO) {
		this.khyfBO = khyfBO;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	/**
	 * @return the zxzsDAO
	 */
	public IZxzsDAO getZxzsDAO() {
		return zxzsDAO;
	}

	/**
	 * @param zxzsDAO
	 *            the zxzsDAO to set
	 */
	public void setZxzsDAO(IZxzsDAO zxzsDAO) {
		this.zxzsDAO = zxzsDAO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

	public IZng2DAO getZng2DAO() {
		return zng2DAO;
	}

	public void setZng2DAO(IZng2DAO zng2dao) {
		zng2DAO = zng2dao;
	}

	public IAttachmentBO getAttachmentBO() {
		return attachmentBO;
	}

	public void setAttachmentBO(IAttachmentBO attachmentBO) {
		this.attachmentBO = attachmentBO;
	}

}
