package com.quanta.sino.yccl.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.bo.api.IInformBO;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EPlqf;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.EYlType;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.SjDeng;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZpnoCd;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhCh;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.Dcrz;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Jlbg;
import com.quanta.sino.orm.Xpxx;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.Ylrz;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.Zyrz;
import com.quanta.sino.sj.bo.api.ISjBO;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.dao.api.IJinhlpDAO;
import com.quanta.sino.yccl.bo.api.IXpxxBO;
import com.quanta.sino.yccl.constants.BGMC;
import com.quanta.sino.yccl.constants.Mklx;
import com.quanta.sino.yccl.constants.Zylx;
import com.quanta.sino.yccl.dao.api.IDcrzDAO;
import com.quanta.sino.yccl.dao.api.IJlbgDAO;
import com.quanta.sino.yccl.dao.api.IXpxxDAO;
import com.quanta.sino.yccl.dao.api.IYlrzDAO;
import com.quanta.sino.yccl.dao.api.IZyrzDAO;
import com.quanta.sino.yccl.vo.YldlVO;
import com.quanta.sino.yccl.vo.ZpVO;
import com.quanta.sino.yccl.vo.ZpkDataVO;
import com.quanta.sino.yccl.vo.ZyblQE;
import com.quanta.sino.yccl.vo.ZyztQE;
import com.quanta.sino.yccl.vo.ZyztVO;
import com.quanta.sino.zkfp.constants.ZkfpConstance;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 现品信息操作
 * </p>
 * <p>
 * create: 2011-2-15 下午04:45:33
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class XpxxBO implements IXpxxBO {
	/**
	 * 现品dao
	 */
	private IXpxxDAO dao;
	/**
	 * 作业日志dao
	 */
	private IZyrzDAO zyrzDao;
	/**
	 * 业连日志dao
	 */
	private IYlrzDAO ylrzDao;
	/**
	 * 堆场日志dao
	 */
	private IDcrzDAO dcrzDao;
	/**
	 * 制品bo
	 */
	private IZpBO zpBo;
	/**
	 * 原材bo
	 */
	private IYcaitpBO ycaiBo;
	/**
	 * 记录变更dao
	 */
	private IJlbgDAO jlbgdao;
	/**
	 * 引入代码业务层bo
	 */
	private ICodeBO codeBo;

	/**
	 * 供应商合同接口
	 */
	private IWwhtBO wwhtBo;

	/**
	 * 实绩日志公共格式业务接口
	 */
	private ISjBO sjBo;

	/**
	 * 进货日志数据访问层接口
	 */
	private IJinhlpDAO jinhlpDao;

	/**
	 * 
	 */
	private ICmnBO cmnBo;

	/**
	 * 
	 */
	private IZsBO zsBo;

	/**
	 * 
	 */
	private IDhBO dhBo;

	/**
	 * 
	 */
	private ISqlDAO sqlDao;

	/**
	 * 
	 */
	private ICzjlBO czjlBO;

	/**
	 * 资料业务接口
	 */
	private IInformBO informBO;

	/**
	 * 余材化处理业务层接口
	 */
	private IYchBO ychBO;

	/**
	 * 堆场转换规则
	 */
	private static final Set<String> DCGZ = new HashSet<String>();

	static {
		DCGZ.add(DC.A.name + DC.A.name);
		DCGZ.add(DC.A.name + DC.B.name);
		DCGZ.add(DC.A.name + DC.X.name);
		DCGZ.add(DC.B.name + DC.B.name);
		DCGZ.add(DC.B.name + DC.X.name);
		DCGZ.add(DC.B.name + DC.A.name);
		DCGZ.add(DC.X.name + DC.X.name);
		DCGZ.add(DC.X.name + DC.A.name);
		DCGZ.add(DC.X.name + DC.B.name);
		DCGZ.add(DC.C.name + DC.C.name);
		DCGZ.add(DC.C.name + DC.D.name);
		DCGZ.add(DC.D.name + DC.D.name);
		DCGZ.add(DC.D.name + DC.C.name);
		DCGZ.add(DC.E.name + DC.E.name);
		DCGZ.add(DC.E.name + DC.F.name);
		DCGZ.add(DC.E.name + DC.G.name);
		DCGZ.add(DC.F.name + DC.F.name);
		DCGZ.add(DC.F.name + DC.E.name);
		DCGZ.add(DC.F.name + DC.G.name);
		DCGZ.add(DC.G.name + DC.G.name);
		DCGZ.add(DC.G.name + DC.F.name);
		DCGZ.add(DC.G.name + DC.E.name);
	}

	@Override
	public void query(ZyztQE qentity) {
		sqlDao.query(qentity);
	}

	@Override
	public void queryBl(ZyblQE qentity) {
		sqlDao.query(qentity);
	}

	@Override
	public String getForJs(String jbno) {
		ZpnoCd zpnoCd = ZpnoCd.get(jbno.length());
		YcaiTp ycaiTp;
		ZpngTp zpngTp;
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			ycaiTp = ycaiBo.get(jbno);
			if (ycaiTp == null) {
				throw new CocoException(-1, "原材卷板表中不存在该原材卷板No.");
			}
			return new Result(ycaiTp).toJsObject();
		}
		zpngTp = zpBo.getZp(jbno);
		if (zpngTp == null) {
			throw new CocoException(-1, "制品在库表中不存在该现品No.");
		}
		return new Result(zpngTp).toJsObject();
	}

	/**
	 * <p>
	 * 实例作业停止日志
	 * </p>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @return Zyrz
	 */
	private Zyrz parseZyrz(YcaiTp ycaiTp, ZpngTp zpngTp) {
		Zyrz zyrz = new Zyrz();
		if (ycaiTp != null) {
			zyrz.setAbbr(ycaiTp.getAbbr());
			zyrz.setCzr(null);
			zyrz.setCzsj(null);
			zyrz.setFuzl(null);
			zyrz.setGgno(ycaiTp.getGgno());
			zyrz.setJbno(ycaiTp.getJbno());
			// 设定现品尺寸组合
			zyrz.setXpcc(SinoUtils.formatProductSize(ycaiTp.getXpho(), ycaiTp.getXpkn(), 0d));
			zyrz.setXtyh(null);
			zyrz.setZtbj(ycaiTp.getZtbj());
			zyrz.setZylx(null);
			zyrz.setZyly(null);
			zyrz.setZzsd(ycaiTp.getZzsd());
			zyrz.setDhno(ycaiTp.getDhno());
			return zyrz;
		}
		zyrz.setAbbr(zpngTp.getAbbr());
		zyrz.setCzr(null);
		zyrz.setCzsj(null);
		zyrz.setFuzl("#" + zpngTp.getFuzm() + "/" + zpngTp.getFufm());
		zyrz.setGgno(zpngTp.getGgno());
		zyrz.setJbno(zpngTp.getJbno());
		// 设定现品尺寸组合
		zyrz.setXpcc(SinoUtils.formatProductSize(zpngTp.getXpho(), zpngTp.getXpkn(), zpngTp.getXpcn()));
		zyrz.setXtyh(null);
		zyrz.setZtbj(zpngTp.getZtbj());
		zyrz.setZylx(null);
		zyrz.setZyly(null);
		zyrz.setZzsd(zpngTp.getZzsd());
		zyrz.setPlqf(zpngTp.getPlqf());
		zyrz.setDhno(zpngTp.getDhno());
		return zyrz;
	}

	@Override
	public void updateZytz(String[] ids, String ztbj, String czr, String zyly,
			String cz, User user) {
		YcaiTp ycai = null;
		ZpngTp zpng = null;
		Zyrz zyrz;
		String zylx = (ztbj == null || (ztbj = ztbj.trim()).isEmpty()) ? Zylx.ZYSZ.key
				: Zylx.ZYSC.key;
		if (Zylx.ZYSC.key.equals(zylx)) {
			zyrzDao.deleteByJbno(Arrays.asList(ids));
		}
		else {
			zyrzDao.deleteByJbno(Arrays.asList(ids), zylx);
		}
		for (String id : ids) {
			ZpnoCd zpnoCd = ZpnoCd.get(id.length());
			if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
				ycai = ycaiBo.get(id);
				if (ycai == null) {
					throw new CocoException(-1, "原材卷板No." + id + "不存在");
				}
				String stat = ycai.getStat();
				if (!(YbStat.CS.stat.equals(stat) || YbStat.YRK.stat.equals(stat))) {
					throw new CocoException(-1, "原材卷板" + id + "状态为"
							+ ZpStat.getName(stat) + ",不能对其设定作业停止");
				}
				if (!EScbj.CS.key.equals(ycai.getScbj())) {
					throw new CocoException(-1, "原材卷板" + id + "删除标记为"
							+ EScbj.get(ycai.getScbj()).value + ",不能对其设定作业停止");
				}
				if (Zylx.ZYSZ.key.equals(zylx)
						&& (ycai.getScbj() == null || ycai.getScbj().isEmpty())) {
					continue;
				}
				zyrz = parseZyrz(ycai, zpng);
				ycai.setZtbj(ztbj);
				ycaiBo.updateEntity(ycai);
			}
			else {
				zpng = zpBo.getZp(id);
				if (zpng == null) {
					throw new CocoException(-1, "现品No." + id + "不存在");
				}
				if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
					throw new CocoException(-1, "现品" + id + "状态为"
							+ ZpStat.SJLR.name + ",不能对其设定作业停止");
				}
				if (!EScbj.CS.key.equals(zpng.getScbj())) {
					throw new CocoException(-1, "现品" + id + "删除标记为"
							+ EScbj.get(zpng.getScbj()).value + ",不能对其设定作业停止");
				}
				if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
					throw new CocoException(-1, "现品" + id
							+ "已制作装箱指示书,不能对其设定作业停止");
				}
				if (Zylx.ZYSZ.key.equals(zylx)
						&& (zpng.getScbj() == null || zpng.getScbj().isEmpty())) {
					continue;
				}
				zyrz = parseZyrz(ycai, zpng);
				zpng.setZtbj(ztbj);
				zpBo.update(zpng);
			}
			// 作业停止标记设定和作业停止标记删除
			zyrz.setZylx(zylx);
			// 修改前的状态
			zyrz.setZtbj(ztbj);
			// 用户输入的操作者
			zyrz.setCzr(czr);
			zyrz.setCzsj(new Date());
			// 登入系统的用户
			zyrz.setXtyh(user.getNo());
			zyrz.setZyly(zyly);
			zyrz.setCz(cz);
			zyrzDao.save(zyrz);
		}
	}

	@Override
	public void updateCz(String[] ids, String zyly, String cz, User user) {
		Zyrz zyrz = null;
		for (String id : ids) {
			zyrz = zyrzDao.get(id, Zylx.ZYSC.key);
			if (zyrz == null) continue;
			if (zyly != null && !zyly.isEmpty()) {
				zyrz.setZyly(zyly);
			}
			if (cz != null && !cz.isEmpty()) {
				zyrz.setCz(cz);
			}
			zyrzDao.update(zyrz);
		}
	}

	@Override
	public void updateXpbl(String[] ids, String blbj, String czr, String zyly,
			String cz, User user) {
		ZpngTp zpng = null;
		Zyrz zyrz;
		Date date = new Date();
		for (String id : ids) {
			zpng = zpBo.getZp(id);
			if (zpng == null) {
				throw new CocoException(-1, "现品No." + id + "不存在");
			}
			zyrz = parseZyrz(null, zpng);
			zpng.setBlbj(blbj);
			zpBo.update(zpng);
			// 现品保留标记设定和现品保留标记删除
			zyrz.setZylx((blbj == null || blbj.trim().isEmpty()) ? Zylx.ZYWL.key
					: Zylx.ZYBL.key);
			// 修改前的状态
			zyrz.setZtbj(blbj);
			// 用户输入的操作者
			zyrz.setCzr(czr);
			zyrz.setCzsj(date);
			// 登入系统的用户
			zyrz.setXtyh(user.getNo());
			zyrz.setZyly(zyly);
			zyrz.setCz(cz);
			zyrzDao.save(zyrz);
		}
	}

	/**
	 * <p>
	 * 修改业联时，比较现品原业联和现有的业联
	 * </p>
	 * 
	 * @param org
	 * @param desc
	 * @return Result
	 */
	private Result checkYlno(String org, String desc) {
		if (org == null || org.isEmpty()) {
			return new Result(1, desc);
		}
		if (desc == null || desc.isEmpty()) {
			return new Result(1, desc);
		}
		for (String d : desc.split(",")) {
			if (org.indexOf(d) >= 0) {
				return new Result(-1, "业连" + d.substring(2) + "已重复");
			}
		}
		return new Result(1, org + "," + desc);
	}

	/**
	 * <p>
	 * 实例业联订正日志
	 * </p>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @return Ylrz
	 */
	private Ylrz parseYlrz(YcaiTp ycaiTp, ZpngTp zpngTp) {
		Ylrz ylrz = new Ylrz();
		if (ycaiTp != null) {
			ylrz.setAbbr(ycaiTp.getAbbr());
			ylrz.setDeng(ycaiTp.getDeng());
			ylrz.setDhno(ycaiTp.getDhno());
			ylrz.setDuic(ycaiTp.getDuic());
			ylrz.setGgno(ycaiTp.getGgno());
			ylrz.setJbno(ycaiTp.getJbno());
			ylrz.setNyln(null);
			ylrz.setPlqf(null);
			ylrz.setStat(ycaiTp.getStat());
			ylrz.setXgr(null);
			ylrz.setXgsj(null);
			ylrz.setYcjbid(ycaiTp.getJbno());
			ylrz.setYln(ycaiTp.getYlno());
			ylrz.setZpzkid(null);
			ylrz.setZtbj(ycaiTp.getZtbj());
			ylrz.setZzno(null);
			ylrz.setZzsd(ycaiTp.getZzsd());
			ylrz.setZzsj(ycaiTp.getZzsj());
			return ylrz;
		}
		ylrz.setAbbr(zpngTp.getAbbr());
		ylrz.setDeng(zpngTp.getDeng());
		ylrz.setDhno(zpngTp.getDhno());
		ylrz.setDuic(zpngTp.getDuic());
		ylrz.setGgno(zpngTp.getGgno());
		ylrz.setJbno(zpngTp.getJbno());
		ylrz.setNyln(null);
		ylrz.setPlqf(zpngTp.getPlqf());
		ylrz.setStat(zpngTp.getStat());
		ylrz.setXgr(null);
		ylrz.setXgsj(null);
		ylrz.setYcjbid(zpngTp.getJbno());
		ylrz.setYln(zpngTp.getYlno());
		ylrz.setZpzkid(null);
		ylrz.setZtbj(zpngTp.getZtbj());
		ylrz.setZzno(null);
		ylrz.setZzsd(zpngTp.getZzsd());
		ylrz.setZzsj(zpngTp.getZzsj());
		return ylrz;
	}

	@Override
	public void updateYlno(YldlVO vo, User user) {
		String ylno = null;
		if (vo.getItems() != null && vo.getItems().size() > 0) {
			for (YldlVO.Yl item : vo.getItems()) {
				if (!informBO.isExisted(item.getYlno())) {
					throw new CocoException(-1, "业连" + item.getYlno()
							+ "在资料室中不存在");
				}
				if (ylno != null
						&& ylno.indexOf(item.getYlFlag() + "-" + item.getYlno()) >= 0) {
					throw new CocoException(-1, "业连" + item.getYlno() + "已重复");
				}
				if (ylno == null || ylno.isEmpty()) {
					ylno = item.getYlFlag() + "-" + item.getYlno();
					continue;
				}
				ylno = ylno + "," + item.getYlFlag() + "-" + item.getYlno();
			}
		}
		YcaiTp ycai = null;
		ZpngTp zpng = null;
		Ylrz ylrz = null;
		List<Ylrz> ylrzs = new ArrayList<Ylrz>();
		StringBuilder error;
		StringBuilder errors = new StringBuilder();
		Date date = new Date();
		// 用户登录检查
		for (String id : vo.getIds()) {
			error = new StringBuilder();
			ZpnoCd zpnoCd = ZpnoCd.get(id.length());
			if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
				ycai = ycaiBo.get(id);
				if (ycai == null) {
					error.append("原材卷板No." + id + "不存在; ");
				}
				if (YbStat.SJLR.stat.equals(ycai.getStat())) {
					error.append("原材卷板" + id + "状态为" + YbStat.SJLR.name
							+ ",不能设置业连; ");
				}
				if (!EScbj.CS.key.equals(ycai.getScbj())) {
					error.append("原材卷板" + id + "删除标记为"
							+ EScbj.get(ycai.getScbj()).value + ",不能设置业连; ");
				}
				if (zsBo.isExistedZszrTp(ycai.getJbno())
						&& ylno != null
						&& !ylno.isEmpty()
						&& (ylno.indexOf(EYlType.ETL.key + "-") >= 0 || ylno.indexOf(EYlType.BOTH.key
								+ "-") >= 0)) {
					error.append("原材卷板" + id + "已制作生产指示书,不能设置业连; ");
				}
				Result result = checkYlno(ycai.getYlno(), ylno);
				if (result.getCode() <= 0) {
					error.append("原材卷板" + id + result.getMessage() + "; ");
				}
				if (error.length() > 0) {
					errors.append(error).append("<br />");
					continue;
				}
				ylrz = parseYlrz(ycai, zpng);
				ycai.setYlno(result.getMessage());
				ycaiBo.updateEntity(ycai);
			}
			else {
				zpng = zpBo.getZp(id);
				if (zpng == null) {
					error.append("现品No." + id + "不存在; ");
				}
				if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
					error.append("现品" + id + "状态为" + ZpStat.SJLR.name
							+ ",不能设置业连; ");
				}
				if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
					error.append("现品" + id + "已制作装箱指示书,不能设置业连; ");
				}
				if (!EScbj.CS.key.equals(zpng.getScbj())) {
					error.append("现品" + id + "删除标记为"
							+ EScbj.get(zpng.getScbj()).value + ",不能设置业连; ");
				}
				Result result = checkYlno(zpng.getYlno(), ylno);
				if (result.getCode() <= 0) {
					error.append("现品" + id + result.getMessage() + "; ");
				}
				if (error.length() > 0) {
					errors.append(error).append("<br />");
					continue;
				}
				ylrz = parseYlrz(ycai, zpng);
				zpng.setYlno(result.getMessage());
				zpBo.update(zpng);
			}
			ylrz.setNyln(ylno);
			ylrz.setXgr(user.getNo());
			ylrz.setXgsj(date);
			ylrzs.add(ylrz);
		}
		if (errors.length() > 0) {
			throw new CocoException(-1, errors.toString());
		}
		ylrzDao.saveAll(ylrzs);
	}

	@Override
	public void updateYlno(String xpzk, String jbnoStart, String jbnoEnd,
			String ylno) {
		if (EXpzk.SC.key.equals(xpzk)) {
			ycaiBo.updateYlno(jbnoStart, jbnoEnd, ylno);
		}
		else {
			zpBo.updateYlno(jbnoStart, jbnoEnd, ylno);
		}
	}

	/**
	 * <p>
	 * 实例堆场订正日志
	 * </p>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @return Dcrz
	 */
	private Dcrz parseDcrz(YcaiTp ycaiTp, ZpngTp zpngTp) {
		Dcrz dcrz = new Dcrz();
		if (ycaiTp != null) {
			dcrz.setAbbr(ycaiTp.getAbbr());
			dcrz.setCang(null);
			dcrz.setDeng(ycaiTp.getDeng());
			dcrz.setDhno(ycaiTp.getDhno());
			dcrz.setFace(ycaiTp.getFace());
			dcrz.setGgno(ycaiTp.getGgno());
			dcrz.setJbno(ycaiTp.getJbno());
			dcrz.setKbao(null);
			dcrz.setNduic(null);
			dcrz.setOduic(ycaiTp.getKw());
			dcrz.setPlqf(null);
			dcrz.setStat(ycaiTp.getStat());
			dcrz.setXgr(null);
			dcrz.setXgsj(null);
			dcrz.setYcjbid(ycaiTp.getJbno());
			dcrz.setZpzkid(null);
			dcrz.setZpzl(ycaiTp.getZpzl());
			dcrz.setZtbj(ycaiTp.getZtbj());
			dcrz.setZzno(null);
			dcrz.setZzsd(ycaiTp.getZzsd());
			dcrz.setZzsj(ycaiTp.getZzsj());
			return dcrz;
		}
		dcrz.setAbbr(zpngTp.getAbbr());
		dcrz.setCang(zpngTp.getCang());
		dcrz.setDeng(zpngTp.getDeng());
		dcrz.setDhno(zpngTp.getDhno());
		dcrz.setFace(zpngTp.getFace());
		dcrz.setGgno(zpngTp.getGgno());
		dcrz.setJbno(zpngTp.getJbno());
		dcrz.setKbao(zpngTp.getKbao());
		dcrz.setNduic(null);
		dcrz.setOduic(zpngTp.getKw());
		dcrz.setPlqf(zpngTp.getPlqf());
		dcrz.setStat(zpngTp.getStat());
		dcrz.setXgr(null);
		dcrz.setXgsj(null);
		dcrz.setYcjbid(zpngTp.getJbno());
		dcrz.setZpzkid(null);
		dcrz.setZpzl(zpngTp.getZpzl());
		dcrz.setZtbj(zpngTp.getZtbj());
		dcrz.setZzno(null);
		dcrz.setZzsd(zpngTp.getZzsd());
		dcrz.setZzsj(zpngTp.getZzsj());
		return dcrz;
	}

	@Override
	public void updateDc(String[] jbnos, String[] kws, User user) {
		YcaiTp ycai = null;
		ZpngTp zpng = null;
		Dcrz dcrz = null;
		// 用户登录检查
		String id;
		String dc;
		String kw;
		Date date = new Date();
		for (int i = 0; i < jbnos.length; i++) {
			id = jbnos[i];
			kw = kws[i];
			// 判断库位在码表中是否存在（最新要求）张良加
			if (!codeBo.isExisted(CmnConstants.CODE_011, kw)) {
				throw new CocoException(-1, "库位" + kw + "在码表中不存在; ");
			}
			dc = kw.substring(0, 1);
			ZpnoCd zpnoCd = ZpnoCd.get(id.length());
			if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
				ycai = ycaiBo.get(id);
				if (ycai == null) {
					throw new CocoException(-1, "原材卷板No." + id + "不存在");
				}
				if (YbStat.SJLR.stat.equals(ycai.getStat())) {
					throw new CocoException(-1, "原材卷板" + id + "状态为"
							+ YbStat.SJLR.name + ",不能做修改堆场操作");
				}
				if (!EScbj.CS.key.equals(ycai.getScbj())) {
					throw new CocoException(-1, "原材卷板" + id + "删除标记为"
							+ EScbj.get(ycai.getScbj()).value + ",不能做修改堆场操作");
				}
				dcrz = parseDcrz(ycai, zpng);
				ycai.setDuic(dc);
				ycai.setKw(kw);
				ycaiBo.updateEntity(ycai);
			}
			else {
				zpng = zpBo.getZp(id);
				if (zpng == null) {
					throw new CocoException(-1, "现品No." + id + "不存在");
				}
				if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
					throw new CocoException(-1, "现品" + id + "状态为"
							+ ZpStat.SJLR.name + ",不能做修改堆场操作");
				}
				if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
					throw new CocoException(-1, "现品" + id
							+ "已制作装箱指示书,不能做修改堆场操作");
				}
				if (!EScbj.CS.key.equals(zpng.getScbj())) {
					throw new CocoException(-1, "现品" + id + "删除标记为"
							+ EScbj.get(zpng.getScbj()).value + ",不能做修改堆场操作");
				}
				dcrz = parseDcrz(ycai, zpng);
				zpng.setDuic(dc);
				zpng.setKw(kw);
				zpBo.update(zpng);
			}
			// 修改后的库位
			dcrz.setNduic(kw);
			dcrz.setXgr(user.getNo());
			dcrz.setXgsj(date);
			dcrzDao.save(dcrz);
		}
	}

	@Override
	public String getCheckDcgz(String[] jbnos, String[] kws) {
		YcaiTp ycaiTp = null;
		ZpngTp zpngTp = null;
		ZpnoCd zpnoCd = null;
		String duic1 = null;
		String duic2 = null;
		String ztbj = null;
		int i = 0;
		StringBuilder builder = new StringBuilder();
		for (String jbno : jbnos) {
			if (builder.length() > 0) {
				builder.append("\n");
			}
			if (kws[i] == null || kws[i].isEmpty() == true) {
				return new Result(-1, "搬送堆场不能为空").toString();
			}
			duic2 = kws[i].substring(0, 1);
			if (DC.get(duic2) == null) {
				return new Result(-1, "PILE No.为" + jbno
						+ "的现品,堆场输入有误。注:堆场第一位只能为" + DC.get()).toString();
			}
			zpnoCd = ZpnoCd.get(jbno.length());
			if (zpnoCd == null) {
				return new Result(-1, "PILE No.为" + jbno + ",长度有误").toString();
			}
			if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
				ycaiTp = ycaiBo.get(jbno);
			}
			else {
				zpngTp = zpBo.getZp(jbno);
			}
			if (ycaiTp == null && zpngTp == null) {
				return new Result(-1, "PILE No.为" + jbno + "的现品,不存在").toString();
			}
			duic1 = ycaiTp != null ? ycaiTp.getDuic() : zpngTp.getDuic();
			if (duic1 == null || duic1.isEmpty()) {
				return new Result(-1, "PILE No.为" + jbno + "的现品,堆场为空").toString();
			}
			ztbj = ycaiTp != null ? ycaiTp.getZtbj() : zpngTp.getZtbj();
			if (ztbj != null && !ztbj.isEmpty()) {
				builder.append("PILE No.为").append(jbno).append("的现品,作业停止为").append(ztbj).append(",");
			}
			if (!DCGZ.contains(duic1 + duic2)) {
				builder.append("不能将PILE No.为").append(jbno).append("的现品,从堆场").append(duic1).append("转到").append(duic2).append(",");
			}
			i++;
		}
		return new Result(1, builder.toString()).toString();
	}

	@Override
	public void updateYcaiTp(YcaiTp entity, User user) {
		String jbno = entity.getJbno();
		// 获取修改前制品在库信息
		YcaiTp ycai = ycaiBo.get(jbno);
		// if (ycai == null) {
		// throw new CocoException(-1, "原材卷板No." + jbno + "不存在");
		// }
		// if (YbStat.SJLR.stat.equals(ycai.getStat())) {
		// throw new CocoException(-1, "原材卷板" + jbno + "状态为"
		// + YbStat.SJLR.name + ",不能做修正操作");
		// }
		// if (!EScbj.CS.key.equals(ycai.getScbj())) {
		// throw new CocoException(-1, "原材卷板" + jbno + "删除标记为"
		// + EScbj.get(ycai.getScbj()).value + ",不能做修正操作");
		// }
		Date date = new Date();
		// Result result = checkYcaiTp(entity);
		// if (result.getCode() <= 0) {
		// throw new CocoException(-1, result.getMessage());
		// }
		Xpxx xpxx = new Xpxx();
		// 把制品在库表修改以前的信息保存到现品信息修正表中去
		ReflectUtils.copy(xpxx, ycai, false);
		// 记录原材卷板记录变更
		List<Jlbg> jlbgs = parseYcaiTpJlbg(ycai, entity, Mklx.XPXZ.key, user, date);
		if (jlbgs.size() > 0) {
			jlbgdao.saveAll(jlbgs);
			// 更新原材卷板DB
			ycaiBo.updateEntity(ycai);
			xpxx.setYcjbid(jbno);
			// 0：现品信息修正
			xpxx.setMklx(Mklx.XPXZ.key);
			// 创建人
			xpxx.setScr(user.getNo());
			// 创建时间
			xpxx.setScsj(date);
			// 保存更新日志
			dao.save(xpxx);
		}
	}

	@Override
	public String getCheckXpxx(ZpVO zpVO, boolean flag) {
		String xpzk = zpVO.getXpzk();
		if (flag) {
			Result result = checkJbno(xpzk, zpVO.getJbno(), zpVO.getNewJbno());
			if (result.getCode() <= 0) {
				return result.toString();
			}
		}
		if (EXpzk.SC.key.equals(xpzk)) {
			// 判断素材的现品信息
			return getCheckSc(zpVO);
		}
		else if (EXpzk.ZJP.key.equals(xpzk)) {
			// 判断中间品的现品信息
			return getCheckZjp(zpVO);
		}
		else if (EXpzk.JZP.key.equals(xpzk)) {
			// 判断卷制品的现品信息
			return getCheckJzp(zpVO);
		}
		else if (EXpzk.BZP.key.equals(xpzk)) {
			// 判断剪板制品的现品信息
			return getCheckBzp(zpVO);
		}
		return Result.ERROR;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param xpzk
	 * @param jbno
	 * @param newJbno
	 * @return Result
	 */
	private Result checkJbno(String xpzk, String jbno, String newJbno) {
		NoGenType noGenType = null;
		String[] jbnos = null;
		if (newJbno == null) {
			return new Result(-3, "新现品NO不存在");
		}
		if (zpBo.isExisted(newJbno)) {
			return new Result(-3, "新现品NO已存在,不允许重复增加");
		}
		if (ZpnoCd.get(newJbno.length()) == null) {
			return new Result(-3, "新现品NO有误");
		}
		String rczpno = null;
		if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)) {
			noGenType = NoGenType.cut;
			rczpno = jbno;
		}
		else {
			noGenType = null;
		}
		if (!(EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk))) {
			ZpngTp zpngTp = zpBo.getZp(jbno);
			if (zpngTp == null) {
				return new Result(-1, "引用COIL / PILE No." + jbno + "不存在");
			}
			rczpno = zpngTp.getRczpno();
		}
		if ((jbnos = cmnBo.generateNo(rczpno, noGenType)) == null) {
			return new Result(-1, "该卷板号无法生成");
		}
		String cut;
		String $rczpno;
		String $newJbno = jbnos[0];
		String cutNo = jbnos[1];
		if (EXpzk.SC.key.equals(xpzk) && !newJbno.equals(cutNo)) {
			// 判断素材的现品信息
			return new Result(-3, "新现品NO有误,不能修改新生成的素材No.");
		}
		else if (EXpzk.ZJP.key.equals(xpzk) && !newJbno.equals(cutNo)) {
			// 判断中间品的现品信息
			return new Result(-3, "新现品NO有误,不能修改新生成的中间品No.");
		}
		else if (EXpzk.JZP.key.equals(xpzk) && !newJbno.equals($newJbno)) {
			// 判断卷制品的现品信息
			if (ZpnoCd.eight.len != newJbno.length()) {
				return new Result(-3, "新现品NO长度有误,卷制品长度为8位");
			}
			cut = newJbno.substring(6, 7);
			$rczpno = "0".equals(cut) ? newJbno.substring(0, 6)
					: newJbno.substring(0, 7);
			if (!ycaiBo.isExistedYcaiByJbno($rczpno)) {
				return new Result(-3, "新现品NO有误,素材" + $rczpno + "不存在");
			}
			if ((jbnos = cmnBo.generateNo($rczpno, null)) == null) {
				return new Result(-1, "该卷板号无法生成");
			}
			if (!newJbno.equals(jbnos[0])) {
				return new Result(-3, "新现品NO有误,素材" + $rczpno + "生成的卷制品No,只能是"
						+ jbnos[0]);
			}
		}
		else if (EXpzk.BZP.key.equals(xpzk) && !newJbno.equals($newJbno)) {
			// 判断剪板制品的现品信息
			if (ZpnoCd.eleven.len != newJbno.length()) {
				return new Result(-3, "新现品NO长度有误,板制品长度为11位");
			}
			cut = newJbno.substring(8, 9);
			$rczpno = "0".equals(cut) ? newJbno.substring(0, 8)
					: newJbno.substring(0, 9);
			if (!zpBo.isExisted($rczpno)) {
				return new Result(-3, "新现品NO有误,中间品" + $rczpno + "不存在");
			}
			if ((jbnos = cmnBo.generateNo($rczpno, null)) == null) {
				return new Result(-1, "该卷板号无法生成");
			}
			if (!newJbno.equals(jbnos[0])) {
				return new Result(-3, "新现品NO有误,中间品" + $rczpno + "生成的切板制品No,只能是"
						+ jbnos[0]);
			}
		}
		return new Result(1, "OK");
	}

	private String getCheckSc(ZpVO entity) {
		String jbno = entity.getJbno();
		// 获取修改前制品在库信息
		YcaiTp ycai = ycaiBo.get(jbno);
		if (ycai == null) {
			return new Result(-1000, "原材卷板No." + jbno + "不存在").toString();
		}
		if (entity.getNewJbno() == null || entity.getNewJbno().isEmpty()) {
			// if (YbStat.SJLR.stat.equals(ycai.getStat())) {
			// return new Result(-1000, "原材卷板" + jbno + "状态为"
			// + YbStat.SJLR.name + ",不能做修正操作").toString();
			// }
			// if (!EScbj.CS.key.equals(ycai.getScbj())) {
			// return new Result(-1000, "原材卷板" + jbno + "删除标记为"
			// + EScbj.get(ycai.getScbj()).value + ",不能做修正操作").toString();
			// }
		}
		// 卷板长
		Integer jbcn = entity.getJbcn();
		if (jbcn == null || jbcn == 0) {
			return new Result(-11, "卷板长为空").toString();
		}
		// 验证制造商代码。判断不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称
		String zzsd = entity.getZzsd();
		if (zzsd == null || zzsd.isEmpty()) {
			return new Result(-21, "制造商代码为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_012, zzsd)) {
			return new Result(-21, "制造商在码表中不存在").toString();
		}
		// 验证表面。判断不能为空和在码表中是否存在
		String face = entity.getFace();
		if (face == null || face.isEmpty()) {
			return new Result(-30, "表面为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_005, face)) {
			return new Result(-30, "表面在码表中不存在").toString();
		}
		// 验证品种。判断不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-31, "品种为空").toString();
		}
		if (pzno.length() != 2) {
			return new Result(-31, "品种格式不正确。正确格式如：XX").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-31, "品种第一位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-31, "品种第二位在码表中不存在").toString();
		}
		// 验证内径。判断不能为空和在码表中是否存在。
		String njno = entity.getNjno();
		if (njno == null || njno.isEmpty()) {
			return new Result(-29, "内径代码为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_020, njno)) {
			return new Result(-29, "内径代码在码表中不存在").toString();
		}
		// 验证等级。判断不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表
		String fpdj = entity.getDeng();
		if (fpdj == null || fpdj.isEmpty()) {
			return new Result(-18, "等级为空").toString();
		}
		if (fpdj.length() != 3) {
			return new Result(-18, "分配等级格式不正确。正确格式如：XXX").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_103, fpdj.substring(0, 1))) {
			return new Result(-18, "分配等级第一位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_104, fpdj.substring(1, 2))) {
			return new Result(-18, "分配等级第二位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_105, fpdj.substring(2, 3))) {
			return new Result(-18, "分配等级第三位在码表中不存在").toString();
		}
		// 规格代码
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-33, "规格代码为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_018, ggno)) {
			return new Result(-33, "规格代码在码表中不存在").toString();
		}
		// 运用规格
		String yuny = entity.getYuny();
		if (yuny == null || yuny.isEmpty()) {
			return new Result(-24, "运用规格为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_019, yuny)) {
			return new Result(-24, "运用规格在码表中不存在").toString();
		}
		// 判断钢种类型
		String gzlx = entity.getGzlx();
		if (gzlx == null || gzlx.isEmpty()) {
			return new Result(-26, "钢种类型为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_109, gzlx)) {
			return new Result(-26, "钢种类型在码表中不存在").toString();
		}
		return Result.SUCCESS;
	}

	private String getCheckZjp(ZpVO entity) {
		String jbno = entity.getJbno();
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			return new Result(-1000, "现品No." + jbno + "不存在").toString();
		}
		if (entity.getNewJbno() == null || entity.getNewJbno().isEmpty()) {
			if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
				return new Result(-1000, "现品" + jbno + "状态为" + ZpStat.SJLR.name
						+ ",不能做修正操作").toString();
			}
			if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
				return new Result(-1000, "现品" + jbno + "已制作装箱指示书,不能做修正操作").toString();
			}
			if (!EScbj.CS.key.equals(zpng.getScbj())) {
				return new Result(-1000, "现品" + jbno + "删除标记为"
						+ EScbj.get(zpng.getScbj()).value + ",不能做修正操作").toString();
			}
		}
		Integer sjzl = entity.getSjzl();
		// 实际重量
		if (sjzl == null || sjzl <= 0) {
			return new Result(-13, "实际重量不能为空").toString();
		}
		// 验证等级。判断不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表
		String deng = entity.getDeng();
		if (deng == null || deng.isEmpty()) {
			return new Result(-18, "等级为空").toString();
		}
		String chan = entity.getChan();
		// 产量代码
		if (chan == null || chan.isEmpty()) {
			return new Result(-19, "产量代码不能为空").toString();
		}
		DhuoTp dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(entity.getDhno()));
		String fpdj = dhuoTp != null ? dhuoTp.getFpdj() : null;
		// 产量、等级与分配等级的检查
		Result result = cmnBo.getCheck(chan, deng, fpdj);
		if (result.getCode() < 0) {
			return result.toString();
		}
		// PILE区分
		String plqf = entity.getPlqf();
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& (plqf == null || plqf.isEmpty())) {
			return new Result(-23, "PILE区分不能为空").toString();
		}
		if (ZpnoCd.eleven.len != entity.getJbno().length() && plqf != null
				&& !plqf.isEmpty()) {
			return new Result(-23, "钢卷制品没有PILE区分").toString();
		}
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& EPlqf.get(plqf) == null) {
			return new Result(-23, "PILE区分只能是B、C、S和T").toString();
		}
		// 实绩品种等级
		String spdj = entity.getSpdj();
		if (spdj == null || spdj.isEmpty()) {
			return new Result(-37, "实绩品种等级不能为空").toString();
		}
		if (SjDeng.get(spdj) == null) {
			return new Result(-37, "实绩品种等级只能是1、2、3、4和5").toString();
		}
		// 验证制造商代码。判断不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称
		String zzsd = entity.getZzsd();
		if (zzsd == null || zzsd.isEmpty()) {
			return new Result(-21, "制造商代码为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_012, zzsd)) {
			return new Result(-21, "制造商在码表中不存在").toString();
		}
		// 验证表面。判断不能为空和在码表中是否存在
		String face = entity.getFace();
		if (face == null || face.isEmpty()) {
			return new Result(-30, "表面为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_005, face)) {
			return new Result(-30, "表面在码表中不存在").toString();
		}
		// 验证品种。判断不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-31, "品种为空").toString();
		}
		if (pzno.length() != 2) {
			return new Result(-31, "品种格式不正确。正确格式如：XX").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-31, "品种第一位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-31, "品种第二位在码表中不存在").toString();
		}
		// 验证内径。判断不能为空和在码表中是否存在。
		String njno = entity.getNjno();
		if (njno != null && !njno.isEmpty()
				&& !codeBo.isExisted(CmnConstants.CODE_020, njno)) {
			return new Result(-29, "内径代码在码表中不存在").toString();
		}
		// 规格代码
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-33, "规格代码为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_018, ggno)) {
			return new Result(-33, "规格代码在码表中不存在").toString();
		}
		// 运用规格
		String yuny = entity.getYuny();
		if (yuny == null || yuny.isEmpty()) {
			return new Result(-24, "运用规格为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_019, yuny)) {
			return new Result(-24, "运用规格在码表中不存在").toString();
		}
		// 判断钢种类型
		String gzlx = entity.getGzlx();
		if (gzlx == null || gzlx.isEmpty()) {
			return new Result(-26, "钢种类型为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_109, gzlx)) {
			return new Result(-26, "钢种类型在码表中不存在").toString();
		}
		String fudw = entity.getFudw();
		// 验证附着量.正面
		String fuzm = entity.getFuzm();
		if (fuzm == null || fuzm.isEmpty()) {
			return new Result(-35, "附着量.正面为必填项").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fuzm)) {
			return new Result(-35, "附着量.正面输入有误,在码表中不存在").toString();
		}
		// 验证附着量.反面
		String fufm = entity.getFufm();
		if (fufm == null || fufm.isEmpty()) {
			return new Result(-36, "附着量.反面为必填项").toString();
		}
		// 校验附着量.反面
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fufm)) {
			return new Result(-36, "附着量.反面输入有误,在码表中不存在").toString();
		}
		// 验证差厚
		String chdx = entity.getChdx();
		if (fuzm.equals(fufm)) {
			if (chdx != null && !chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面相同,差厚必须为空").toString();
			}
		}
		else {
			if (chdx == null || chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面不相同时,差厚为必填项").toString();
			}
			else {
				// 验证输入的值必须为A1、A2、D1、D2中的一个
				if (DhCh.get(chdx) == null) {
					return new Result(-41, "差厚输入有误,差厚只能是A1、A2、D1或D2").toString();
				}
			}
		}
		// 剪边标记
		String zjbb = entity.getZjbb();
		if (zjbb != null
				&& !(ZkfpConstance.ZKFP_JBKB_0.equals(zjbb) || ZkfpConstance.ZKFP_JBKB_1.equals(zjbb))) {
			return new Result(-28, "剪边标记只能是" + ZkfpConstance.ZKFP_JBKB_0 + "和"
					+ ZkfpConstance.ZKFP_JBKB_1).toString();
		}
		// 捆包形式
		String kbao = entity.getKbao();
		if (kbao == null || kbao.isEmpty()) {
			return new Result(-43, "捆包形式为必填项").toString();
		}
		// 校验捆包形式
		// 根据捆包形式查询主文件为015对应的信息
		if (!codeBo.isExisted(CmnConstants.CODE_015, kbao)) {
			return new Result(-43, "捆包形式输入有误,捆包形式在码表中不存在").toString();
		}
		return Result.SUCCESS;
	}

	private String getCheckJzp(ZpVO entity) {
		String jbno = entity.getJbno();
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			return new Result(-1000, "现品No." + jbno + "不存在").toString();
		}
		if (entity.getNewJbno() == null || entity.getNewJbno().isEmpty()) {
			if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
				return new Result(-1000, "现品" + jbno + "状态为" + ZpStat.SJLR.name
						+ ",不能做修正操作").toString();
			}
			// if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
			// return new Result(-1000, "现品" + jbno +
			// "已制作装箱指示书,不能做修正操作").toString();
			// }
			if (!(EScbj.CS.key.equals(zpng.getScbj()) || EScbj.YCH.key.equals(zpng.getScbj()))) {
				return new Result(-1000, "现品" + jbno + "删除标记为"
						+ EScbj.get(zpng.getScbj()).value + ",不能做修正操作").toString();
			}
		}
		Integer sjzl = entity.getSjzl();
		// 实际重量
		if (sjzl == null || sjzl <= 0) {
			return new Result(-13, "实际重量不能为空").toString();
		}
		// 验证等级。判断不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表
		String deng = entity.getDeng();
		if (deng == null || deng.isEmpty()) {
			return new Result(-18, "等级为空").toString();
		}
		String chan = entity.getChan();
		// 产量代码
		if (chan == null || chan.isEmpty()) {
			return new Result(-19, "产量代码不能为空").toString();
		}
		DhuoTp dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(entity.getDhno()));
		String fpdj = dhuoTp != null ? dhuoTp.getFpdj() : null;
		// 产量、等级与分配等级的检查
		Result result = cmnBo.getCheck(chan, deng, fpdj);
		if (result.getCode() < 0) {
			return result.toString();
		}
		// PILE区分
		String plqf = entity.getPlqf();
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& (plqf == null || plqf.isEmpty())) {
			return new Result(-23, "PILE区分不能为空").toString();
		}
		if (ZpnoCd.eleven.len != entity.getJbno().length() && plqf != null
				&& !plqf.isEmpty()) {
			return new Result(-23, "钢卷制品没有PILE区分").toString();
		}
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& EPlqf.get(plqf) == null) {
			return new Result(-23, "PILE区分只能是B、C、S和T").toString();
		}
		// 实绩品种等级
		String spdj = entity.getSpdj();
		if (spdj == null || spdj.isEmpty()) {
			return new Result(-37, "实绩品种等级不能为空").toString();
		}
		if (SjDeng.get(spdj) == null) {
			return new Result(-37, "实绩品种等级只能是1、2、3、4和5").toString();
		}
		// 验证制造商代码。判断不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称
		String zzsd = entity.getZzsd();
		if (zzsd == null || zzsd.isEmpty()) {
			return new Result(-21, "制造商代码为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_012, zzsd)) {
			return new Result(-21, "制造商在码表中不存在").toString();
		}
		// 验证表面。判断不能为空和在码表中是否存在
		String face = entity.getFace();
		if (face == null || face.isEmpty()) {
			return new Result(-30, "表面为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_005, face)) {
			return new Result(-30, "表面在码表中不存在").toString();
		}
		// 验证品种。判断不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-31, "品种为空").toString();
		}
		if (pzno.length() != 2) {
			return new Result(-31, "品种格式不正确。正确格式如：XX").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-31, "品种第一位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-31, "品种第二位在码表中不存在").toString();
		}
		// 验证内径。判断不能为空和在码表中是否存在。
		String njno = entity.getNjno();
		if (njno != null && !njno.isEmpty()
				&& !codeBo.isExisted(CmnConstants.CODE_020, njno)) {
			return new Result(-29, "内径代码在码表中不存在").toString();
		}
		// 规格代码
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-33, "规格代码为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_018, ggno)) {
			return new Result(-33, "规格代码在码表中不存在").toString();
		}
		// 运用规格
		String yuny = entity.getYuny();
		if (yuny == null || yuny.isEmpty()) {
			return new Result(-24, "运用规格为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_019, yuny)) {
			return new Result(-24, "运用规格在码表中不存在").toString();
		}
		// 判断钢种类型
		String gzlx = entity.getGzlx();
		if (gzlx == null || gzlx.isEmpty()) {
			return new Result(-26, "钢种类型为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_109, gzlx)) {
			return new Result(-26, "钢种类型在码表中不存在").toString();
		}
		String fudw = entity.getFudw();
		// 验证附着量.正面
		String fuzm = entity.getFuzm();
		if (fuzm == null || fuzm.isEmpty()) {
			return new Result(-35, "附着量.正面为必填项").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fuzm)) {
			return new Result(-35, "附着量.正面输入有误,在码表中不存在").toString();
		}
		// 验证附着量.反面
		String fufm = entity.getFufm();
		if (fufm == null || fufm.isEmpty()) {
			return new Result(-36, "附着量.反面为必填项").toString();
		}
		// 校验附着量.反面
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fufm)) {
			return new Result(-36, "附着量.反面输入有误,在码表中不存在").toString();
		}
		// 验证差厚
		String chdx = entity.getChdx();
		if (fuzm.equals(fufm)) {
			if (chdx != null && !chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面相同,差厚必须为空").toString();
			}
		}
		else {
			if (chdx == null || chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面不相同时,差厚为必填项").toString();
			}
			else {
				// 验证输入的值必须为A1、A2、D1、D2中的一个
				if (DhCh.get(chdx) == null) {
					return new Result(-41, "差厚输入有误,差厚只能是A1、A2、D1或D2").toString();
				}
			}
		}
		// 剪边标记
		String zjbb = entity.getZjbb();
		if (zjbb != null
				&& !(ZkfpConstance.ZKFP_JBKB_0.equals(zjbb) || ZkfpConstance.ZKFP_JBKB_1.equals(zjbb))) {
			return new Result(-28, "剪边标记只能是" + ZkfpConstance.ZKFP_JBKB_0 + "和"
					+ ZkfpConstance.ZKFP_JBKB_1).toString();
		}
		// 捆包形式
		String kbao = entity.getKbao();
		if (kbao == null || kbao.isEmpty()) {
			return new Result(-43, "捆包形式为必填项").toString();
		}
		// 校验捆包形式
		// 根据捆包形式查询主文件为015对应的信息
		if (!codeBo.isExisted(CmnConstants.CODE_015, kbao)) {
			return new Result(-43, "捆包形式输入有误,捆包形式在码表中不存在").toString();
		}
		return Result.SUCCESS;
	}

	private String getCheckBzp(ZpVO entity) {
		String jbno = entity.getJbno();
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			return new Result(-1000, "现品No." + jbno + "不存在").toString();
		}
		if (entity.getNewJbno() == null || entity.getNewJbno().isEmpty()) {
			if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
				return new Result(-1000, "现品" + jbno + "状态为" + ZpStat.SJLR.name
						+ ",不能做修正操作").toString();
			}
			// if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
			// return new Result(-1000, "现品" + jbno +
			// "已制作装箱指示书,不能做修正操作").toString();
			// }
			if (!(EScbj.CS.key.equals(zpng.getScbj()) || EScbj.YCH.key.equals(zpng.getScbj()))) {
				return new Result(-1000, "现品" + jbno + "删除标记为"
						+ EScbj.get(zpng.getScbj()).value + ",不能做修正操作").toString();
			}
		}
		Integer sjzl = entity.getSjzl();
		// 实际重量
		if (sjzl == null || sjzl <= 0) {
			return new Result(-13, "实际重量不能为空").toString();
		}
		// 验证等级。判断不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表
		String deng = entity.getDeng();
		if (deng == null || deng.isEmpty()) {
			return new Result(-18, "等级为空").toString();

		}
		String chan = entity.getChan();
		// 产量代码
		if (chan == null || chan.isEmpty()) {
			return new Result(-19, "产量代码不能为空").toString();
		}
		DhuoTp dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(entity.getDhno()));
		String fpdj = dhuoTp != null ? dhuoTp.getFpdj() : null;
		// 产量、等级与分配等级的检查
		Result result = cmnBo.getCheck(chan, deng, fpdj);
		if (result.getCode() < 0) {
			return result.toString();
		}
		// PILE区分
		String plqf = entity.getPlqf();
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& (plqf == null || plqf.isEmpty())) {
			return new Result(-23, "PILE区分不能为空").toString();
		}
		if (ZpnoCd.eleven.len != entity.getJbno().length() && plqf != null
				&& !plqf.isEmpty()) {
			return new Result(-23, "钢卷制品没有PILE区分").toString();
		}
		if (ZpnoCd.eleven.len == entity.getJbno().length()
				&& EPlqf.get(plqf) == null) {
			return new Result(-23, "PILE区分只能是B、C、S和T").toString();
		}
		// 实绩品种等级
		String spdj = entity.getSpdj();
		if (spdj == null || spdj.isEmpty()) {
			return new Result(-37, "实绩品种等级不能为空").toString();
		}
		if (SjDeng.get(spdj) == null) {
			return new Result(-37, "实绩品种等级只能是1、2、3、4和5").toString();
		}
		// 验证制造商代码。判断不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称
		String zzsd = entity.getZzsd();
		if (zzsd == null || zzsd.isEmpty()) {
			return new Result(-21, "制造商代码为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_012, zzsd)) {
			return new Result(-21, "制造商在码表中不存在").toString();
		}
		// 验证表面。判断不能为空和在码表中是否存在
		String face = entity.getFace();
		if (face == null || face.isEmpty()) {
			return new Result(-30, "表面为空").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_005, face)) {
			return new Result(-30, "表面在码表中不存在").toString();
		}
		// 验证品种。判断不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-31, "品种为空").toString();
		}
		if (pzno.length() != 2) {
			return new Result(-31, "品种格式不正确。正确格式如：XX").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-31, "品种第一位在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-31, "品种第二位在码表中不存在").toString();
		}
		// 验证内径。判断不能为空和在码表中是否存在。
		String njno = entity.getNjno();
		if (njno != null && !njno.isEmpty()
				&& !codeBo.isExisted(CmnConstants.CODE_020, njno)) {
			return new Result(-29, "内径代码在码表中不存在").toString();
		}
		// 规格代码
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-33, "规格代码为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_018, ggno)) {
			return new Result(-33, "规格代码在码表中不存在").toString();
		}
		// 运用规格
		String yuny = entity.getYuny();
		if (yuny == null || yuny.isEmpty()) {
			return new Result(-24, "运用规格为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_019, yuny)) {
			return new Result(-24, "运用规格在码表中不存在").toString();
		}
		// 判断钢种类型
		String gzlx = entity.getGzlx();
		if (gzlx == null || gzlx.isEmpty()) {
			return new Result(-26, "钢种类型为空").toString();
		}
		if (!codeBo.isValueExisted(CmnConstants.CODE_109, gzlx)) {
			return new Result(-26, "钢种类型在码表中不存在").toString();
		}
		String fudw = entity.getFudw();
		// 验证附着量.正面
		String fuzm = entity.getFuzm();
		if (fuzm == null || fuzm.isEmpty()) {
			return new Result(-35, "附着量.正面为必填项").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fuzm)) {
			return new Result(-35, "附着量.正面输入有误,在码表中不存在").toString();
		}
		// 验证附着量.反面
		String fufm = entity.getFufm();
		if (fufm == null || fufm.isEmpty()) {
			return new Result(-36, "附着量.反面为必填项").toString();
		}
		// 校验附着量.反面
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fufm)) {
			return new Result(-36, "附着量.反面输入有误,在码表中不存在").toString();
		}
		// 验证差厚
		String chdx = entity.getChdx();
		if (fuzm.equals(fufm)) {
			if (chdx != null && !chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面相同,差厚必须为空").toString();
			}
		}
		else {
			if (chdx == null || chdx.isEmpty()) {
				return new Result(-41, "差厚输入有误,当附着量.正面与附着量.反面不相同时,差厚为必填项").toString();
			}
			else {
				// 验证输入的值必须为A1、A2、D1、D2中的一个
				if (DhCh.get(chdx) == null) {
					return new Result(-41, "差厚输入有误,差厚只能是A1、A2、D1或D2").toString();
				}
			}
		}
		// 剪边标记
		String zjbb = entity.getZjbb();
		if (zjbb != null
				&& !(ZkfpConstance.ZKFP_JBKB_0.equals(zjbb) || ZkfpConstance.ZKFP_JBKB_1.equals(zjbb))) {
			return new Result(-28, "剪边标记只能是" + ZkfpConstance.ZKFP_JBKB_0 + "和"
					+ ZkfpConstance.ZKFP_JBKB_1).toString();
		}
		// 捆包形式
		String kbao = entity.getKbao();
		if (kbao == null || kbao.isEmpty()) {
			return new Result(-43, "捆包形式为必填项").toString();
		}
		// 校验捆包形式
		// 根据捆包形式查询主文件为015对应的信息
		if (!codeBo.isExisted(CmnConstants.CODE_015, kbao)) {
			return new Result(-43, "捆包形式输入有误,捆包形式在码表中不存在").toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public void updateZpngTp(ZpngTp entity, User user) {
		String jbno = entity.getJbno();
		// 获取修改前制品在库信息
		ZpngTp zpng = zpBo.getZp(jbno);
		String oldchan = zpng.getChan();
		// if (zpng == null) {
		// throw new CocoException(-1, "制品No." + jbno + "不存在");
		// }
		// if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
		// throw new CocoException(-1, "制品" + jbno + "状态为"
		// + ZpStat.SJLR.name + ",不能做修正操作");
		// }
		// if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
		// throw new CocoException(-1, "制品" + jbno + "已制作装箱指示书,不能做修正操作");
		// }
		// if (!EScbj.CS.key.equals(zpng.getScbj())) {
		// throw new CocoException(-1, "制品" + jbno + "删除标记为"
		// + EScbj.get(zpng.getScbj()).value + ",不能做修正操作");
		// }
		Date date = new Date();
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpng.getDhno()));
		if (dhuo != null) {
			// String fpdj = dhuo.getFpdj();
			// Result result = checkZpngTp(entity, fpdj);
			// if (result.getCode() <= 0) {
			// throw new CocoException(-1, result.getMessage());
			// }
			// ETL合格量
			double etlh = dhuo.getEtlh() != null ? dhuo.getEtlh() : 0;
			// SL合格量
			double slhg = dhuo.getSlhg() != null ? dhuo.getSlhg() : 0;
			// 原制品重量
			int yzpzl = zpng.getZpzl() != null ? zpng.getZpzl() : 0;
			// 订正后的制品重量
			int zpzl = entity.getZpzl() != null ? entity.getZpzl() : 0;
			// 等级订正
			int flag = SinoUtils.compare(ChanType.get(zpng.getChan()), ChanType.get(entity.getChan()));
			switch (flag) {
			// 合格转非合格（降级）
			case -1:
				slhg = slhg - yzpzl / 1000d;
				etlh = etlh - yzpzl / 1000d;
				ychBO.doScYch(zpng, oldchan, dhuo, date);
				break;
			// 非合格转合格
			case 1:
				slhg = slhg + zpzl / 1000d;
				etlh = etlh + zpzl / 1000d;
				break;
			// 合格品转为合格品
			case 2:
				slhg = slhg + (zpzl - yzpzl) / 1000d;
				etlh = etlh + (zpzl - yzpzl) / 1000d;
				break;
			// 非合格品转为非合格品
			case -2:
				// 其他
			default:
				break;
			}
			// 如果是板制品，修改SL合格量
			if (EXpzk.BZP.key.equals(zpng.getXpzk())) {
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
			}
			else {
				// 其它修改ETL合格量
				dhuo.setEtlh(NumberUtils.format(etlh, 3));
			}
			dhBo.update(dhuo);
		}
		// 把制品在库表修改以前的信息保存到现品信息修正表中去
		Xpxx xpxx = new Xpxx();
		ReflectUtils.copy(xpxx, zpng, false);
		List<Jlbg> jlbgs = parseZpngTpJlbg(zpng, entity, Mklx.XPXZ.key, user, date);
		if (jlbgs.size() > 0) {
			jlbgdao.saveAll(jlbgs);
			xpxx.setZpzkid(zpng.getJbno());
			// ，0：现品信息修正、1：现品信息作成、2：现品信息删除
			xpxx.setMklx(Mklx.XPXZ.key);
			// 创建人
			xpxx.setScr(user.getNo());
			// 创建时间
			xpxx.setScsj(date);
			// 更新制品在库DB
			zpBo.update(zpng);
			// 保存更新日志
			dao.save(xpxx);
		}
	}

	/**
	 * <p>
	 * 记录原材卷板信息变更
	 * </p>
	 * 
	 * @param entityOld
	 *            变更前记录
	 * @param entityNew
	 *            变更后记录
	 * @param mklx
	 *            0 修改 1生成
	 * @param user
	 *            系统用户
	 * @param date
	 *            修正时间
	 * @return List<Jlbg>
	 */
	private List<Jlbg> parseZpngTpJlbg(ZpngTp entityOld, ZpngTp entityNew,
			String mklx, User user, Date date) {
		String no = user.getNo();
		String jbno1 = entityOld.getJbno();
		String jbno2 = entityNew.getJbno();
		String bgqz, bghz;
		List<Jlbg> jlbgs = new ArrayList<Jlbg>();
		Double xpho1 = entityOld.getXpho();
		Double xpho2 = entityNew.getXpho();
		if (!compareValue(xpho1, xpho2)) {
			bgqz = xpho1 != null ? xpho1.toString() : null;
			bghz = xpho2 != null ? xpho2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("xpho")));
			entityOld.setXpho(xpho2);
		}
		Double xpkn1 = entityOld.getXpkn();
		Double xpkn2 = entityNew.getXpkn();
		if (!compareValue(xpkn1, xpkn2)) {
			bgqz = xpkn1 != null ? xpkn1.toString() : null;
			bghz = xpkn2 != null ? xpkn2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("xpkn")));
			entityOld.setXpkn(xpkn2);
		}
		Double xpcn1 = entityOld.getXpcn();
		Double xpcn2 = entityNew.getXpcn();
		if (!compareValue(xpcn1, xpcn2)) {
			bgqz = xpcn1 != null ? xpcn1.toString() : null;
			bghz = xpcn2 != null ? xpcn2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("xpcn")));
			entityOld.setXpcn(xpcn2);
		}
		Integer jbcn1 = entityOld.getJbcn();
		Integer jbcn2 = entityNew.getJbcn();
		if (!compareValue(jbcn1, jbcn2)) {
			bgqz = jbcn1 != null ? jbcn1.toString() : null;
			bghz = jbcn2 != null ? jbcn2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("jbcn")));
			entityOld.setJbcn(jbcn2);
		}
		Integer jszl1 = entityOld.getJszl();
		Integer jszl2 = entityNew.getJszl();
		if (!compareValue(jszl1, jszl2)) {
			bgqz = jszl1 != null ? jszl1.toString() : null;
			bghz = jszl2 != null ? jszl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("jszl")));
			entityOld.setJszl(jszl2);
		}
		Integer sjzl1 = entityOld.getSjzl();
		Integer sjzl2 = entityNew.getSjzl();
		if (!compareValue(sjzl1, sjzl2)) {
			bgqz = sjzl1 != null ? sjzl1.toString() : null;
			bghz = sjzl2 != null ? sjzl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("sjzl")));
			entityOld.setSjzl(sjzl2);
		}
		Integer jnzl1 = entityOld.getJnzl();
		Integer jnzl2 = entityNew.getJnzl();
		if (!compareValue(jnzl1, jnzl2)) {
			bgqz = jnzl1 != null ? jnzl1.toString() : null;
			bghz = jnzl2 != null ? jnzl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("jnzl")));
			entityOld.setJnzl(jnzl2);
		}
		Integer mazl1 = entityOld.getMazl();
		Integer mazl2 = entityNew.getMazl();
		if (!compareValue(mazl1, mazl2)) {
			bgqz = mazl1 != null ? mazl1.toString() : null;
			bghz = mazl2 != null ? mazl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("mazl")));
			entityOld.setMazl(mazl2);
		}
		Integer zpzl1 = entityOld.getZpzl();
		Integer zpzl2 = entityNew.getZpzl();
		if (!compareValue(zpzl1, zpzl2)) {
			bgqz = zpzl1 != null ? zpzl1.toString() : null;
			bghz = zpzl2 != null ? zpzl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("zpzl")));
			entityOld.setZpzl(zpzl2);
		}
		Integer zrzl1 = entityOld.getZrzl();
		Integer zrzl2 = entityNew.getZrzl();
		if (!compareValue(zrzl1, zrzl2)) {
			bgqz = zrzl1 != null ? zrzl1.toString() : "";
			bghz = zrzl2 != null ? zrzl2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("zrzl")));
			entityOld.setZrzl(zrzl2);
		}
		String deng1 = entityOld.getDeng();
		String deng2 = entityNew.getDeng();
		if (!compareValue(deng1, deng2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, deng1, deng2, BGMC.getValue("deng")));
			entityOld.setDeng(deng2);
		}
		String chan1 = entityOld.getChan();
		String chan2 = entityNew.getChan();
		if (!compareValue(chan1, chan2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, chan1, chan2, BGMC.getValue("chan")));
			entityOld.setChan(chan2);
		}
		Integer dmzl1 = entityOld.getDmzl();
		Integer dmzl2 = entityNew.getDmzl();
		if (!compareValue(dmzl1, dmzl2)) {
			bgqz = dmzl1 != null ? dmzl1.toString() : null;
			bghz = dmzl2 != null ? dmzl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("dmzl")));
			entityOld.setDmzl(dmzl2);
		}
		CodeItem item = null;
		String zzsd1 = entityOld.getZzsd();
		String zzsd2 = entityNew.getZzsd();
		if (!compareValue(zzsd1, zzsd2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, zzsd1, zzsd2, BGMC.getValue("zzsd")));
			// 根据制造商代码，查询该原材卷板是否为保税品。在码头中备注字段是用来设置制造商代码是否为保税品。注：1：非保税，2：保税
			item = codeBo.getCodeItem(CmnConstants.CODE_012, zzsd2);
			entityOld.setSfbs(item != null ? item.getRemark() : null);
			entityOld.setZzsd(zzsd2);
		}
		Integer bzcl1 = entityOld.getBzcl();
		Integer bzcl2 = entityNew.getBzcl();
		if (!compareValue(bzcl1, bzcl2)) {
			bgqz = bzcl1 != null ? bzcl1.toString() : null;
			bghz = bzcl2 != null ? bzcl2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("bzcl")));
			entityOld.setBzcl(bzcl2);
		}
		String plqf1 = entityOld.getPlqf();
		String plqf2 = entityNew.getPlqf();
		if (!compareValue(plqf1, plqf2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, plqf1, plqf2, BGMC.getValue("plqf")));
			entityOld.setPlqf(plqf2);
		}
		String yuny1 = entityOld.getYuny();
		String yuny2 = entityNew.getYuny();
		String yuny = null, ggno = null, yblc = null, gzlx = null;
		if (!compareValue(yuny1, yuny2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, yuny1, yuny2, BGMC.getValue("yuny")));
			yuny = yuny2;
			// entityOld.setYuny(yuny2);

			item = codeBo.getUniqueItemByValue(CmnConstants.CODE_019, yuny2);
			item = codeBo.getCodeItem(CmnConstants.CODE_018, item.getKey());
			ggno = item.getValue();
			yblc = item.getKey();
			// entityOld.setGgno(item.getValue());
			// entityOld.setYblc(item.getKey());
			item = codeBo.getCodeItem(CmnConstants.CODE_109, entityOld.getGgno().substring(0, 1));
			gzlx = item != null ? item.getValue() : null;
			// entityOld.setGzlx(item != null ? item.getValue() : null);
		}
		Integer ckno1 = entityOld.getCkno();
		Integer ckno2 = entityNew.getCkno();
		if (!compareValue(ckno1, ckno2)) {
			bgqz = ckno1 != null ? ckno1.toString() : null;
			bghz = ckno2 != null ? ckno2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("ckno")));
			entityOld.setCkno(ckno2);
		}
		String gzlx1 = entityOld.getGzlx();
		String gzlx2 = entityNew.getGzlx();
		if (!compareValue(gzlx1, gzlx2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, gzlx1, gzlx2, BGMC.getValue("gzlx")));
			entityOld.setGzlx(gzlx2);
		}
		Integer zshu1 = entityOld.getZshu();
		Integer zshu2 = entityNew.getZshu();
		if (!compareValue(zshu1, zshu2)) {
			bgqz = zshu1 != null ? zshu1.toString() : null;
			bghz = zshu2 != null ? zshu2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("zshu")));
			entityOld.setZshu(zshu2);
		}
		String zjbb1 = entityOld.getZjbb();
		String zjbb2 = entityNew.getZjbb();
		if (!compareValue(zjbb1, zjbb2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, zjbb1, zjbb2, BGMC.getValue("zjbb")));
			entityOld.setZjbb(zjbb2);
		}
		String njno1 = entityOld.getNjno();
		String njno2 = entityNew.getNjno();
		if (!compareValue(njno1, njno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, njno1, njno2, BGMC.getValue("njno")));
			entityOld.setNjno(njno2);
		}
		String face1 = entityOld.getFace();
		String face2 = entityNew.getFace();
		if (!compareValue(face1, face2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, face1, face2, BGMC.getValue("face")));
			entityOld.setFace(face2);
		}
		String pzno1 = entityOld.getPzno();
		String pzno2 = entityNew.getPzno();
		if (!compareValue(pzno1, pzno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, pzno1, pzno2, BGMC.getValue("pzno")));
			entityOld.setPzno(pzno2);
		}
		String ytyp1 = entityOld.getYtyp();
		String ytyp2 = entityNew.getYtyp();
		if (!compareValue(ytyp1, ytyp2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ytyp1, ytyp2, BGMC.getValue("ytyp")));
			entityOld.setYtyp(ytyp2);
		}
		String ggno1 = entityOld.getGgno();
		String ggno2 = entityNew.getGgno();
		if (!compareValue(ggno1, ggno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ggno1, ggno2, BGMC.getValue("ggno")));
			ggno = ggno2;
			// entityOld.setGgno(ggno2);
			item = codeBo.getUniqueItemByValue(CmnConstants.CODE_018, ggno2);
			item = codeBo.getCodeItem(CmnConstants.CODE_019, item.getKey());
			yuny = item.getValue();
			yblc = item.getKey();
			// entityOld.setYuny(item.getValue());
			// entityOld.setYblc(item.getKey());
			item = codeBo.getCodeItem(CmnConstants.CODE_109, ggno2.substring(0, 1));
			gzlx = item != null ? item.getValue() : null;
			// entityOld.setGzlx(item != null ? item.getValue() : null);
		}
		String fuzm1 = entityOld.getFuzm();
		String fuzm2 = entityNew.getFuzm();
		if (!compareValue(fuzm1, fuzm2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, fuzm1, fuzm2, BGMC.getValue("fuzm")));
			entityOld.setFuzm(fuzm2);
		}
		String fufm1 = entityOld.getFufm();
		String fufm2 = entityNew.getFufm();
		if (!compareValue(fufm1, fufm2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, fufm1, fufm2, BGMC.getValue("fufm")));
			entityOld.setFufm(fufm2);
		}
		String spdj1 = entityOld.getSpdj();
		String spdj2 = entityNew.getSpdj();
		if (!compareValue(spdj1, spdj2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, spdj1, spdj2, BGMC.getValue("spdj")));
			entityOld.setSpdj(spdj2);
		}
		String ztbj1 = entityOld.getZtbj();
		String ztbj2 = entityNew.getZtbj();
		if (!compareValue(ztbj1, ztbj2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ztbj1, ztbj2, BGMC.getValue("ztbj")));
			entityOld.setZtbj(ztbj2);
		}
		Double sczm1 = entityOld.getSczm();
		Double sczm2 = entityNew.getSczm();
		if (!compareValue(sczm1, sczm2)) {
			bgqz = sczm1 != null ? sczm1.toString() : null;
			bghz = sczm2 != null ? sczm2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("sczm")));
			entityOld.setSczm(sczm2);
		}
		Double scfm1 = entityOld.getScfm();
		Double scfm2 = entityNew.getScfm();
		if (!compareValue(scfm1, scfm2)) {
			bgqz = scfm1 != null ? scfm1.toString() : null;
			bghz = scfm2 != null ? scfm2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("scfm")));
			entityOld.setScfm(scfm2);
		}
		String chdx1 = entityOld.getChdx();
		String chdx2 = entityNew.getChdx();
		if (!compareValue(chdx1, chdx2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, chdx1, chdx2, BGMC.getValue("chdx")));
			entityOld.setChdx(chdx2);
		}
		String qzch1 = entityOld.getQzch();
		String qzch2 = entityNew.getQzch();
		if (!compareValue(qzch1, qzch2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, qzch1, qzch2, BGMC.getValue("qzch")));
			entityOld.setQzch(qzch2);
		}
		String kbao1 = entityOld.getKbao();
		String kbao2 = entityNew.getKbao();
		if (!compareValue(kbao1, kbao2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, kbao1, kbao2, BGMC.getValue("kbao")));
			entityOld.setKbao(kbao2);
		}
		Integer ying1 = entityOld.getYing();
		Integer ying2 = entityNew.getYing();
		if (!compareValue(ying1, ying2)) {
			zpBo.updateYing(jbno1.substring(0, 6) + "%", ying2);
			bgqz = ying1 != null ? ying1.toString() : null;
			bghz = ying2 != null ? ying2.toString() : null;
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("ying")));
			entityOld.setYing(ying2);
		}

		String jcxs1 = entityOld.getJcxs();
		String jcxs2 = entityNew.getJcxs();
		if (!compareValue(jcxs1, jcxs2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, jcxs1, jcxs2, BGMC.getValue("jcxs")));
			entityOld.setJcxs(jcxs2);
		}
		String bqht1 = entityOld.getBqht();
		String bqht2 = entityNew.getBqht();
		if (!compareValue(bqht1, bqht2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bqht1, bqht2, BGMC.getValue("bqht")));
			entityOld.setBqht(bqht2);
		}
		String ylno1 = entityOld.getYlno();
		String ylno2 = entityNew.getYlno();
		if (!compareValue(ylno1, ylno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ylno1, ylno2, BGMC.getValue("ylno")));
			entityOld.setYlno(ylno2);
		}
		if (yuny != null && !yuny.isEmpty()) {
			entityOld.setYuny(yuny);
			entityOld.setGgno(ggno);
			entityOld.setYblc(yblc);
			entityOld.setGzlx(gzlx);
		}
		return jlbgs;
	}

	/**
	 * <p>
	 * 比较双精度
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param d1
	 * @param d2
	 * @return boolean
	 */
	private boolean compareValue(Double d1, Double d2) {
		if (d1 == null && d2 == null) {
			return true;
		}
		if ((d1 == null && d2 != null) || (d1 != null && d2 == null)) {
			return false;
		}
		if (d1.doubleValue() != d2.doubleValue()) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 比较整型
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param d1
	 * @param d2
	 * @return boolean
	 */
	private boolean compareValue(Integer int1, Integer int2) {
		if (int1 == null && int2 == null) {
			return true;
		}
		if ((int1 == null && int2 != null) || (int1 != null && int2 == null)) {
			return false;
		}
		if (int1.intValue() != int2.intValue()) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 比较字符串
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param s1
	 * @param s2
	 * @return boolean
	 */
	private boolean compareValue(String s1, String s2) {
		if ((s1 == null || s1.isEmpty()) && (s2 == null || s2.isEmpty())) {
			return true;
		}
		if (((s1 == null || s1.isEmpty()) && (s2 != null && !s2.isEmpty()))
				|| ((s1 != null && !s1.isEmpty()) && (s2 == null || s2.isEmpty()))) {
			return false;
		}
		if (!s1.equals(s2)) {
			return false;
		}
		return true;
	}

	private Jlbg parseJlbg(Date date, String no, String jbno1, String jbno2,
			String mklx, String bgqz, String bghz, String xmmc) {
		Jlbg entity = new Jlbg();
		entity.setXgr(no);
		entity.setXgsj(date);
		entity.setJbno(jbno1);
		entity.setNjbno(jbno2);
		entity.setMklx(mklx);
		entity.setBgqz(bgqz);
		entity.setBghz(bghz);
		entity.setXmmc(xmmc);
		return entity;
	}

	/**
	 * <p>
	 * 记录原材卷板信息变更
	 * </p>
	 * 
	 * @param entityOld
	 *            变更前记录
	 * @param entityNew
	 *            变更后记录
	 * @param mklx
	 *            0 修改 1生成
	 * @param user
	 *            系统用户
	 * @param date
	 *            修正时间
	 * @return List<Jlbg>
	 */
	private List<Jlbg> parseYcaiTpJlbg(YcaiTp entityOld, YcaiTp entityNew,
			String mklx, User user, Date date) {
		String no = user.getNo();
		String jbno1 = entityOld.getJbno();
		String jbno2 = entityNew.getJbno();
		String bgqz, bghz;
		List<Jlbg> jlbgs = new ArrayList<Jlbg>();
		Double xpho1 = entityOld.getXpho();
		Double xpho2 = entityNew.getXpho();
		if (!compareValue(xpho1, xpho2)) {
			bgqz = xpho1 != null ? xpho1.toString() : "";
			bghz = xpho2 != null ? xpho2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("xpho")));
			entityOld.setXpho(xpho2);
		}

		Double xpkn1 = entityOld.getXpkn();
		Double xpkn2 = entityNew.getXpkn();
		if (!compareValue(xpkn1, xpkn2)) {
			bgqz = xpkn1 != null ? xpkn1.toString() : "";
			bghz = xpkn2 != null ? xpkn2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("xpkn")));
			entityOld.setXpkn(xpkn2);
		}
		Integer jbcn1 = entityOld.getJbcn();
		Integer jbcn2 = entityNew.getJbcn();
		if (!compareValue(jbcn1, jbcn2)) {
			bgqz = jbcn1 != null ? jbcn1.toString() : "";
			bghz = jbcn2 != null ? jbcn2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("jbcn")));
			entityOld.setJbcn(jbcn2);
		}
		Integer zpzl1 = entityOld.getZpzl();
		Integer zpzl2 = entityNew.getZpzl();
		if (!compareValue(zpzl1, zpzl2)) {
			bgqz = zpzl1 != null ? zpzl1.toString() : "";
			bghz = zpzl2 != null ? zpzl2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("zpzl")));
			entityOld.setZpzl(zpzl2);
		}
		Integer zrzl1 = entityOld.getZrzl();
		Integer zrzl2 = entityNew.getZrzl();
		if (!compareValue(zrzl1, zrzl2)) {
			bgqz = zrzl1 != null ? zrzl1.toString() : "";
			bghz = zrzl2 != null ? zrzl2.toString() : "";
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, bgqz, bghz, BGMC.getValue("zrzl")));
			entityOld.setZrzl(zrzl2);
		}

		String deng1 = entityOld.getDeng();
		String deng2 = entityNew.getDeng();
		if (!compareValue(deng1, deng2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, deng1, deng2, BGMC.getValue("deng")));
			entityOld.setDeng(deng2);
		}
		String zzsd1 = entityOld.getZzsd();
		String zzsd2 = entityNew.getZzsd();
		CodeItem item = null;
		if (!compareValue(zzsd1, zzsd2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, zzsd1, zzsd2, BGMC.getValue("zzsd")));
			// 根据制造商代码，查询该原材卷板是否为保税品。在码头中备注字段是用来设置制造商代码是否为保税品。注：1：非保税，2：保税
			item = codeBo.getCodeItem(CmnConstants.CODE_012, zzsd2);
			entityOld.setSfbs(item != null ? item.getRemark() : null);
			entityOld.setZzsd(zzsd2);
		}
		String yuny1 = entityOld.getYuny();
		String yuny2 = entityNew.getYuny();
		String yuny = null, ggno = null, gzlx = null, yblc = null, ggnm = null;
		if (!compareValue(yuny1, yuny2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, yuny1, yuny2, BGMC.getValue("yuny")));
			yuny = yuny2;
			// entityOld.setYuny(yuny2);
			item = codeBo.getUniqueItemByValue(CmnConstants.CODE_019, yuny2);
			item = codeBo.getCodeItem(CmnConstants.CODE_018, item.getKey());
			ggno = item.getValue();
			gzlx = ggno.substring(0, 1);
			yblc = item.getKey();
			// entityOld.setGgno(item.getValue());
			// entityOld.setGzlx(item.getValue().substring(0, 1));
			// entityOld.setYblc(item.getKey());
			item = codeBo.getCodeItem(CmnConstants.CODE_017, item.getKey());
			// entityOld.setGgnm(item.getValue());
			ggnm = item.getValue();
		}
		String gzlx1 = entityOld.getGzlx();
		String gzlx2 = entityNew.getGzlx();
		if (!compareValue(gzlx1, gzlx2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, gzlx1, gzlx2, BGMC.getValue("gzlx")));
			entityOld.setGzlx(gzlx2);
		}
		String njno1 = entityOld.getNjno();
		String njno2 = entityNew.getNjno();
		if (!compareValue(njno1, njno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, njno1, njno2, BGMC.getValue("njno")));
			entityOld.setNjno(njno2);
		}
		String face1 = entityOld.getFace();
		String face2 = entityNew.getFace();
		if (!compareValue(face1, face2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, face1, face2, BGMC.getValue("face")));
			entityOld.setFace(face2);
		}
		String pzno1 = entityOld.getPzno();
		String pzno2 = entityNew.getPzno();
		if (!compareValue(pzno1, pzno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, pzno1, pzno2, BGMC.getValue("pzno")));
			entityOld.setPzno(pzno2);
		}
		String ggno1 = entityOld.getGgno();
		String ggno2 = entityNew.getGgno();
		if (!compareValue(ggno1, ggno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ggno1, ggno2, BGMC.getValue("ggno")));
			ggno = ggno2;
			gzlx = ggno.substring(0, 1);
			// entityOld.setGgno(ggno2);
			item = codeBo.getUniqueItemByValue(CmnConstants.CODE_018, ggno2);
			item = codeBo.getCodeItem(CmnConstants.CODE_019, item.getKey());
			yuny = item.getValue();
			// entityOld.setYuny(item.getValue());
			// entityOld.setGzlx(ggno2.substring(0, 1));
			// entityOld.setYblc(item.getKey());
			yblc = item.getKey();
			item = codeBo.getCodeItem(CmnConstants.CODE_017, item.getKey());
			// entityOld.setGgnm(item.getValue());
			ggnm = item.getValue();
		}
		String ztbj1 = entityOld.getZtbj();
		String ztbj2 = entityNew.getZtbj();
		if (!compareValue(ztbj1, ztbj2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ztbj1, ztbj2, BGMC.getValue("ztbj")));
			entityOld.setZtbj(ztbj2);
		}
		String ylno1 = entityOld.getYlno();
		String ylno2 = entityNew.getYlno();
		if (!compareValue(ylno1, ylno2)) {
			jlbgs.add(parseJlbg(date, no, jbno1, jbno2, mklx, ylno1, ylno2, BGMC.getValue("ylno")));
			entityOld.setYlno(ylno2);
		}
		if (yuny != null && !yuny.isEmpty()) {
			entityOld.setYuny(yuny);
			entityOld.setGgno(ggno);
			entityOld.setGzlx(gzlx);
			entityOld.setYblc(yblc);
			entityOld.setGgnm(ggnm);
		}
		return jlbgs;
	}

	@Override
	public void saveZpngTp(ZpngTp entity, User user, String newJbno) {
		if (zpBo.isExisted(newJbno)) {
			throw new CocoException(-1, "制品No" + newJbno + "已存在");
		}
		// 取订货合同
		// DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(entity.getDhno()));
		// String fpdj = dhuo != null ? dhuo.getFpdj() : null;
		// Result result = checkZpngTp(entity, fpdj);
		// if (result.getCode() <= 0) {
		// throw new CocoException(-1, result.getMessage());
		// }
		String jbno = entity.getJbno();
		entity.setJbno(newJbno);
		ZpngTp zpng = zpBo.getZp(jbno);
		Date date = new Date();
		Xpxx xpxx = new Xpxx();
		ReflectUtils.copy(xpxx, zpng, false);
		xpxx.setZpzkid(jbno);
		xpxx.setNjbno(newJbno);
		xpxx.setMklx(Mklx.XPZC.key);
		xpxx.setScr(user.getNo());
		xpxx.setScsj(date);
		// 保存更新异常日志
		dao.save(xpxx);

		ZpngTp entityOld = new ZpngTp();
		ReflectUtils.copy(entityOld, zpng, false);
		List<Jlbg> jlbgs = parseZpngTpJlbg(entityOld, entity, Mklx.XPZC.key, user, date);
		if (jlbgs.size() > 0) {
			jlbgdao.saveAll(jlbgs);
		}
		else {
			Jlbg jlbg = new Jlbg();
			jlbg.setXgr(user.getNo());
			jlbg.setXgsj(date);
			jlbg.setJbno(jbno);
			jlbg.setNjbno(newJbno);
			jlbg.setMklx(Mklx.XPZC.key);
			jlbgdao.save(jlbg);
		}
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpng.getDhno()));
		if (dhuo != null
				&& (ChanType.hg.key.equals(entity.getChan()) || ChanType.bl.key.equals(entity.getChan()))) {
			// ETL合格量
			double etlh = dhuo.getEtlh() != null ? dhuo.getEtlh() : 0;
			// SL合格量
			double slhg = dhuo.getSlhg() != null ? dhuo.getSlhg() : 0;
			// 订正后的制品重量
			int zpzl = entity.getZpzl() != null ? entity.getZpzl() : 0;
			slhg = slhg + zpzl / 1000d;
			etlh = etlh + zpzl / 1000d;
			// 如果是板制品，修改SL合格量
			if (EXpzk.BZP.key.equals(entity.getXpzk())) {
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
			}
			else {
				// 其它修改ETL合格量
				dhuo.setEtlh(NumberUtils.format(etlh, 3));
			}
			dhBo.update(dhuo);
		}
		ZpngTp newZpngTp = new ZpngTp();
		ReflectUtils.copy(newZpngTp, entityOld, false);
		newZpngTp.setJbno(newJbno);
		newZpngTp.setScbj(EScbj.CS.key);
		newZpngTp.setStat(ZpStat.CS.stat);
		if (ZpnoCd.seven.len == newJbno.length()
				|| ZpnoCd.nine.len == newJbno.length()
				|| !newJbno.startsWith(jbno.substring(0, 6))) {
			newZpngTp.setFpno(null);
			newZpngTp.setFpyc(EFpyc.YC.key);
			newZpngTp.setDhno(null);
			newZpngTp.setAbbr(null);
			newZpngTp.setUser(null);
		}
		String cut = null, rczpno = null;
		if (!newJbno.startsWith(jbno.substring(0, 6))) {
			if (EXpzk.JZP.key.equals(newZpngTp.getXpzk())) {
				// 判断卷制品的现品信息
				cut = newJbno.substring(6, 7);
				rczpno = "0".equals(cut) ? newJbno.substring(0, 6)
						: newJbno.substring(0, 7);
			}
			else if (EXpzk.BZP.key.equals(newZpngTp.getXpzk())) {
				// 判断剪板制品的现品信息
				cut = newJbno.substring(8, 9);
				rczpno = "0".equals(cut) ? newJbno.substring(0, 8)
						: newJbno.substring(0, 9);
			}
			newZpngTp.setRczpno(rczpno);
		}
		if (EXpzk.BZP.key.equals(newZpngTp.getXpzk())) {
			newZpngTp.setBdan(SinoUtils.calculateBdan(newZpngTp.getXpho(), newZpngTp.getXpkn(), newZpngTp.getXpcn()));
		}
		newZpngTp.setLlyd(entityOld.getYing());
		// 清空出货指示书No
		newZpngTp.setChno(null);
		// 清空出货指示年月日
		newZpngTp.setChzs(null);
		// 清空送货单号
		newZpngTp.setShno(null);
		// 设置出货实绩登录年月
		newZpngTp.setChsd(null);
		// 清空车牌号
		newZpngTp.setCpno(null);
		newZpngTp.setQqdm(null);
		newZpngTp.setLosq(null);
		newZpngTp.setQxn1(null);
		newZpngTp.setQxd1(null);
		newZpngTp.setQxz1(null);
		newZpngTp.setQxn2(null);
		newZpngTp.setQxd2(null);
		newZpngTp.setQxz2(null);
		newZpngTp.setQxn3(null);
		newZpngTp.setQxd3(null);
		newZpngTp.setQxz3(null);
		newZpngTp.setQxn4(null);
		newZpngTp.setQxd4(null);
		newZpngTp.setQxz4(null);
		newZpngTp.setQxn5(null);
		newZpngTp.setQxd5(null);
		newZpngTp.setQxz5(null);
		newZpngTp.setQxn6(null);
		newZpngTp.setQxd6(null);
		newZpngTp.setQxz6(null);
		newZpngTp.setQxn7(null);
		newZpngTp.setQxd7(null);
		newZpngTp.setQxz7(null);
		newZpngTp.setQxn8(null);
		newZpngTp.setQxd8(null);
		newZpngTp.setQxz8(null);
		zpBo.save(newZpngTp);
	}

	@Override
	public void saveYcaiTp(YcaiTp ycaiTp, ZpngTp entity, User user,
			String newJbno) {
		if (ycaiBo.isExistedYcaiByJbno(newJbno)) {
			throw new CocoException(-1, "素材No" + newJbno + "已存在");
		}
		// 原卷板No.
		String jbno = entity.getJbno();
		ycaiTp.setJbno(newJbno);
		Date date = new Date();
		Xpxx xpxx = new Xpxx();
		ReflectUtils.copy(xpxx, ycaiTp, false);
		xpxx.setZpzkid(jbno);
		xpxx.setNjbno(newJbno);
		xpxx.setMklx(Mklx.XPZC.key);
		xpxx.setScr(user.getNo());
		xpxx.setScsj(date);
		// 保存更新异常日志
		dao.save(xpxx);
		YcaiTp $ycaiTp = new YcaiTp();
		ReflectUtils.copy($ycaiTp, ycaiTp, false);
		$ycaiTp.setJbno(newJbno);
		$ycaiTp.setXpho(entity.getXpho());
		$ycaiTp.setXpkn(entity.getXpkn());
		$ycaiTp.setJbcn(entity.getJbcn());
		$ycaiTp.setZpzl(entity.getZpzl());
		$ycaiTp.setDeng(entity.getDeng());
		$ycaiTp.setZzsd(entity.getZzsd());
		$ycaiTp.setYuny(entity.getYuny());
		$ycaiTp.setGzlx(entity.getGzlx());
		$ycaiTp.setNjno(entity.getNjno());
		$ycaiTp.setFace(entity.getFace());
		$ycaiTp.setPzno(entity.getPzno());
		$ycaiTp.setGgno(entity.getGgno());
		$ycaiTp.setZtbj(entity.getZtbj());
		$ycaiTp.setYlno(entity.getYlno());
		List<Jlbg> jlbgs = parseYcaiTpJlbg(ycaiTp, $ycaiTp, Mklx.XPZC.key, user, date);
		if (jlbgs.size() > 0) {
			jlbgdao.saveAll(jlbgs);
		}
		else {
			Jlbg jlbg = new Jlbg();
			jlbg.setXgr(user.getNo());
			jlbg.setXgsj(date);
			jlbg.setJbno(jbno);
			jlbg.setNjbno(newJbno);
			jlbg.setMklx(Mklx.XPZC.key);
			jlbgdao.save(jlbg);
		}
		// 用户代码
		$ycaiTp.setUser(null);
		// 用户略称
		$ycaiTp.setAbbr(null);
		// 分配NO
		$ycaiTp.setFpno(null);
		// 指示书NO
		$ycaiTp.setZsno(null);
		// 分配/余材标记
		$ycaiTp.setFpyc(EFpyc.KEY_YC);
		// ETL流水线代码（实绩）
		$ycaiTp.setElin(null);
		// 订货合同号
		$ycaiTp.setDhno(null);
		// 进度标记.ETL实绩
		$ycaiTp.setJdes(null);
		// 进度标记.ETL指示
		$ycaiTp.setJdez(null);
		// 确定标记0
		$ycaiTp.setQdbj(ZtConstants.YCAI_QDBJ_WL);
		// 删除标记
		$ycaiTp.setScbj(EScbj.CS.key);
		// 状态
		$ycaiTp.setStat(YbStat.YRK.stat);
		// 堆场
		$ycaiTp.setDuic(DC.B.name);
		// 库位
		$ycaiTp.setKw(DC.B.name);
		// 创建时间
		$ycaiTp.setCrea(date);
		// 修改时间
		$ycaiTp.setUpda(null);
		// 实际时间
		$ycaiTp.setSjsj(null);
		ycaiBo.saveEntity($ycaiTp);
	}

	@Override
	public void deleteZpngTp(String jbno, User user) {
		ZpngTp zpngTp = zpBo.getZp(jbno);
		if (zpngTp == null) {
			throw new CocoException(-1, "制品No." + jbno + "不存在");
		}
		if (ZpStat.SJLR.stat.equals(zpngTp.getStat())) {
			throw new CocoException(-1, "制品" + jbno + "状态为" + ZpStat.SJLR.name
					+ ",不能做删除操作");
		}
		if (zpngTp.getChno() != null && !zpngTp.getChno().isEmpty()) {
			throw new CocoException(-1, "制品" + jbno + "已制作装箱指示书,不能做删除操作");
		}
		if (!EScbj.CS.key.equals(zpngTp.getScbj())) {
			throw new CocoException(-1, "制品" + jbno + "删除标记为"
					+ EScbj.get(zpngTp.getScbj()).value + ",不能做删除操作");
		}
		zpBo.delete(jbno);
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpngTp.getDhno()));
		if (dhuo != null
				&& (ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))) {
			// ETL合格量
			double etlh = dhuo.getEtlh() != null ? dhuo.getEtlh() : 0;
			// SL合格量
			double slhg = dhuo.getSlhg() != null ? dhuo.getSlhg() : 0;
			// 订正后的制品重量
			int zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
			slhg = slhg - zpzl / 1000d;
			etlh = etlh - zpzl / 1000d;
			// 如果是板制品，修改SL合格量
			if (EXpzk.BZP.key.equals(zpngTp.getXpzk())) {
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
			}
			else {
				// 其它修改ETL合格量
				dhuo.setEtlh(NumberUtils.format(etlh, 3));
			}
			dhBo.update(dhuo);
		}
		Xpxx xpxx = new Xpxx();
		// 把制品在库表修改以前的信息保存到现品信息修正表中去
		ReflectUtils.copy(xpxx, zpngTp, false);
		xpxx.setZpzkid(jbno);
		// 0：现品信息修正、1：现品信息作成、2：现品信息删除
		xpxx.setMklx(Mklx.XPSC.key);
		// 设置修改人
		xpxx.setXgr(user.getName());
		// 修改时间
		xpxx.setXgsj(new Date());
		// 保存删除日志
		dao.save(xpxx);
	}

	@Override
	public void deleteYcaiTp(String jbno, User user) {
		YcaiTp ycai = ycaiBo.get(jbno);
		if (ycai == null) {
			throw new CocoException(-1, "原材卷板No." + jbno + "不存在");
		}
		if (YbStat.SJLR.stat.equals(ycai.getStat())) {
			throw new CocoException(-1, "原材卷板" + jbno + "状态为"
					+ YbStat.SJLR.name + ",不能做删除操作");
		}
		if (!EScbj.CS.key.equals(ycai.getScbj())) {
			throw new CocoException(-1, "原材卷板" + jbno + "删除标记为"
					+ EScbj.get(ycai.getScbj()).value + ",不能做删除操作");
		}
		ycaiBo.delete(jbno);
		// 获取修改前制品在库信息
		Xpxx xpxx = new Xpxx();
		// 把制品在库表修改以前的信息保存到现品信息修正表中去
		ReflectUtils.copy(xpxx, ycai, false);
		xpxx.setYcjbid(jbno);
		// 0：现品信息修正、1：现品信息作成、2：现品信息删除
		xpxx.setMklx(Mklx.XPSC.key);
		// 设置修改人
		xpxx.setXgr(user.getNo());
		// 修改时间
		xpxx.setXgsj(new Date());
		// 保存删除日志
		dao.save(xpxx);
	}

	@Override
	public String getCheckDelXp(String jbno) {
		ZpnoCd zpnoCd = null;
		if ((zpnoCd = ZpnoCd.get(jbno.length())) == null) {
			return new Result(-1, "引用COIL / PILE No.输入有误").toString();
		}
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			YcaiTp ycai = ycaiBo.get(jbno);
			if (ycai == null) {
				return new Result(-1, "原材卷板No." + jbno + "不存在").toString();
			}
			if (YbStat.SJLR.stat.equals(ycai.getStat())) {
				return new Result(-1, "原材卷板" + jbno + "状态为" + YbStat.SJLR.name
						+ ",不能做删除操作").toString();
			}
			if (!EScbj.CS.key.equals(ycai.getScbj())) {
				return new Result(-1, "原材卷板" + jbno + "删除标记为"
						+ EScbj.get(ycai.getScbj()).value + ",不能做删除操作").toString();
			}
		}
		else {
			ZpngTp zpngTp = zpBo.getZp(jbno);
			if (zpngTp == null) {
				return new Result(-1, "现品No." + jbno + "不存在").toString();
			}
			if (ZpStat.SJLR.stat.equals(zpngTp.getStat())) {
				return new Result(-1, "现品" + jbno + "状态为" + ZpStat.SJLR.name
						+ ",不能做删除操作").toString();
			}
			if (zpngTp.getChno() != null && !zpngTp.getChno().isEmpty()) {
				return new Result(-1, "现品" + jbno + "已制作装箱指示书,不能做删除操作").toString();
			}
			if (!EScbj.CS.key.equals(zpngTp.getScbj())) {
				return new Result(-1, "现品" + jbno + "删除标记为"
						+ EScbj.get(zpngTp.getScbj()).value + ",不能做删除操作").toString();
			}
		}
		return Result.SUCCESS;
	}

	@Override
	public String getZpForJS(ZpngTp zpngTp, YcaiTp ycaiTp, boolean flag) {
		ZpVO zpVO = new ZpVO();
		DhuoTp dhuoTp = null;
		String xpzk = null;
		String jbno = null;
		if (ycaiTp != null) {
			xpzk = ycaiTp.getXpzk();
			jbno = ycaiTp.getJbno();
			zpVO.setDhno(ycaiTp.getDhno());
			zpVO.setJbno(ycaiTp.getJbno());
			zpVO.setXpzk(ycaiTp.getXpzk());
			zpVO.setQzch(null);
			zpVO.setChno(null);
			zpVO.setScbj(ycaiTp.getScbj());
			zpVO.setXpho(ycaiTp.getXpho());
			zpVO.setXpkn(ycaiTp.getXpkn());
			zpVO.setXpcn(null);
			zpVO.setJbcn(ycaiTp.getJbcn());
			zpVO.setJszl(null);
			zpVO.setSjzl(null);
			zpVO.setJnzl(null);
			zpVO.setMazl(null);
			zpVO.setZpzl(ycaiTp.getZpzl());
			zpVO.setZrzl(ycaiTp.getZrzl());
			zpVO.setDeng(ycaiTp.getDeng());
			zpVO.setChan(null);
			zpVO.setDmzl(null);
			zpVO.setZzsd(ycaiTp.getZzsd());
			zpVO.setBzcl(null);
			zpVO.setPlqf(null);
			zpVO.setYuny(ycaiTp.getYuny());
			zpVO.setCkno(null);
			zpVO.setGzlx(ycaiTp.getGzlx());
			zpVO.setZshu(null);
			zpVO.setZjbb(null);
			zpVO.setNjno(ycaiTp.getNjno());
			zpVO.setFace(ycaiTp.getFace());
			zpVO.setPzno(ycaiTp.getPzno());
			zpVO.setYtyp(null);
			zpVO.setGgno(ycaiTp.getGgno());
			zpVO.setFudw(null);
			zpVO.setFuzm(null);
			zpVO.setFufm(null);
			zpVO.setSpdj(null);
			zpVO.setZtbj(ycaiTp.getZtbj());
			zpVO.setSczm(null);
			zpVO.setScfm(null);
			zpVO.setChdx(null);
			zpVO.setKbao(null);
			zpVO.setYing(null);
			zpVO.setJcxs(null);
			zpVO.setBqht(null);
			zpVO.setYlno(ycaiTp.getYlno());
			zpVO.setStatName(YbStat.getName(ycaiTp.getStat()));
			zpVO.setXpzkName(EXpzk.getValue(ycaiTp.getXpzk()));
			zpVO.setYing(ycaiTp.getYing());
			dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(ycaiTp.getDhno()));
			if (dhuoTp != null) {
				zpVO.setDhcc(SinoUtils.formatProductSize(dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getCang()));
			}
			// return new Result(zpVO).toJsObject();
		}
		if (zpngTp != null) {
			xpzk = zpngTp.getXpzk();
			jbno = zpngTp.getJbno();
			zpVO.setDhno(zpngTp.getDhno());
			zpVO.setJbno(zpngTp.getJbno());
			zpVO.setXpzk(zpngTp.getXpzk());
			zpVO.setQzch(zpngTp.getQzch());
			zpVO.setChno(zpngTp.getChno());
			zpVO.setScbj(zpngTp.getScbj());
			zpVO.setXpho(zpngTp.getXpho());
			zpVO.setXpkn(zpngTp.getXpkn());
			zpVO.setXpcn(zpngTp.getXpcn());
			zpVO.setJbcn(zpngTp.getJbcn());
			zpVO.setJszl(zpngTp.getJszl());
			zpVO.setSjzl(zpngTp.getSjzl());
			zpVO.setJnzl(zpngTp.getJnzl());
			zpVO.setMazl(zpngTp.getMazl());
			zpVO.setZpzl(zpngTp.getZpzl());
			zpVO.setZrzl(zpngTp.getZrzl());
			zpVO.setDeng(zpngTp.getDeng());
			zpVO.setChan(zpngTp.getChan());
			zpVO.setDmzl(zpngTp.getDmzl());
			zpVO.setZzsd(zpngTp.getZzsd());
			zpVO.setBzcl(zpngTp.getBzcl());
			zpVO.setPlqf(zpngTp.getPlqf());
			zpVO.setYuny(zpngTp.getYuny());
			zpVO.setCkno(zpngTp.getCkno());
			zpVO.setGzlx(zpngTp.getGzlx());
			zpVO.setZshu(zpngTp.getZshu());
			zpVO.setZjbb(zpngTp.getZjbb());
			zpVO.setNjno(zpngTp.getNjno());
			zpVO.setFace(zpngTp.getFace());
			zpVO.setPzno(zpngTp.getPzno());
			zpVO.setYtyp(zpngTp.getYtyp());
			zpVO.setGgno(zpngTp.getGgno());
			zpVO.setFudw(zpngTp.getFudw());
			zpVO.setFuzm(zpngTp.getFuzm());
			zpVO.setFufm(zpngTp.getFufm());
			zpVO.setSpdj(zpngTp.getSpdj());
			zpVO.setZtbj(zpngTp.getZtbj());
			zpVO.setSczm(zpngTp.getSczm());
			zpVO.setScfm(zpngTp.getScfm());
			zpVO.setChdx(zpngTp.getChdx());
			zpVO.setKbao(zpngTp.getKbao());
			zpVO.setYing(zpngTp.getYing());
			zpVO.setJcxs(zpngTp.getJcxs());
			zpVO.setBqht(zpngTp.getBqht());
			zpVO.setYlno(zpngTp.getYlno());
			zpVO.setStatName(ZpStat.getName(zpngTp.getStat()));
			zpVO.setXpzkName(EXpzk.getValue(zpngTp.getXpzk()));
			dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(zpngTp.getDhno()));
			if (dhuoTp != null) {
				zpVO.setDhcc(SinoUtils.formatProductSize(dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getCang()));
			}
			zpVO.setChed(EScbj.YCH.key.equals(zpngTp.getScbj()) ? "是" : null);
		}
		if (flag) {
			NoGenType noGenType = null;
			String[] jbnos = null;
			if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)) {
				noGenType = NoGenType.cut;
			}
			else {
				noGenType = null;
				jbno = zpngTp.getRczpno();
			}
			if ((jbnos = cmnBo.generateNo(jbno, noGenType)) == null) {
				return new Result(-1, "该卷板号无法生成").toString();
			}
			if (noGenType == null) {
				zpVO.setNewJbno(jbnos[0]);
			}
			else {
				zpVO.setNewJbno(jbnos.length > 1 ? jbnos[1] : jbnos[0]);
				zpVO.setDhno(null);
			}
			Integer ckno = null;
			if (dhuoTp != null && CodeNwx.wai.key.equals(dhuoTp.getNwai())) {
				ckno = cmnBo.generatePackageNo(dhuoTp.getDhno()
						+ dhuoTp.getLine());
				zpVO.setCkno(ckno);
			}
		}
		return new Result(zpVO).toJsObject();
	}

	@Override
	public String getZyztForJS(ZpkDataVO vo) {
		if (vo == null || vo.getJbno() == null || vo.getJbno().isEmpty()) {
			return new Result(-1, "至少给定一个制品号").toString();
		}
		String jbno = vo.getJbno();
		ZpnoCd zpnoCd = null;
		if ((zpnoCd = ZpnoCd.get(jbno.length())) == null) {
			return new Result(-1, "引用COIL / PILE No.输入有误").toString();
		}
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			if (!ycaiBo.isExistedYcaiByJbno(jbno)) {
				return new Result(-1, "COIL / PILE No." + jbno + "不存在").toString();
			}
		}
		else {
			if (!zpBo.isExisted(jbno)) {
				return new Result(-1, "COIL / PILE No." + jbno + "不存在").toString();
			}
		}
		ZyztQE page = new ZyztQE();
		page.setJbno(vo.getJbno());
		page.setSize(-1);
		page.setScbj(EScbj.CS.key);
		page.setOrderBys("JBNO_ asc");
		query(page);
		if (page.getDatas().size() == 0) {
			return new Result(-1, "COIL / PILE No." + jbno + "对应的制品"
					+ EScbj.YSJZL.value).toString();
		}
		vo.setDatas(page.getDatas());
		return new Result(vo).toJsObject();
	}

	@Override
	public String getZyblForJS(ZpkDataVO vo) {
		if (vo == null || vo.getJbno() == null || vo.getJbno().isEmpty()) {
			return new Result(-1, "至少给定一个制品号").toString();
		}
		String jbno = vo.getJbno();

		ZpngTp zp = zpBo.getZp(jbno);
		if (zp == null) {
			return new Result(-1, "COIL / PILE No." + jbno + "不存在").toString();
		}
		List<ZyztVO> zyzt = new ArrayList<ZyztVO>();
		ZyztVO zyVO = new ZyztVO();
		zyVO.setJbno(zp.getJbno());
		zyVO.setDhno(zp.getDhno());
		zyVO.setAbbr(zp.getAbbr());
		zyVO.setXpho(zp.getXpho());
		zyVO.setXpkn(zp.getXpkn());
		zyVO.setXpcn(zp.getXpcn());
		zyVO.setFuzm(zp.getFuzm());
		zyVO.setFufm(zp.getFufm());
		zyVO.setZpzl(zp.getZpzl());
		zyVO.setDuic(zp.getDuic());
		zyVO.setZtbj(zp.getZtbj());
		zyVO.setBlbj(zp.getBlbj());
		zyVO.setFace(zp.getFace());
		zyVO.setZzsd(zp.getZzsd());
		zyVO.setPlqf(zp.getPlqf());
		zyVO.setKw(zp.getKw());
		zyVO.setChno(zp.getChno());
		zyzt.add(zyVO);
		vo.setDatas(zyzt);
		return new Result(vo).toJsObject();
	}

	@Override
	public String getCheckZytz(String[] ids) {
		YcaiTp ycai = null;
		ZpngTp zpng = null;
		String ztbj = null;
		String chno = null;
		StringBuilder errors = new StringBuilder();
		for (String id : ids) {
			ZpnoCd zpnoCd = ZpnoCd.get(id.length());
			if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
				ycai = ycaiBo.get(id);
				if (ycai == null) continue;
				if (YbStat.YFP.stat.equals(ycai.getStat())
						|| YbStat.SJLR.stat.equals(ycai.getStat())
						|| YbStat.SJZL.stat.equals(ycai.getStat())) {
					return new Result(-2, "COIL No.为" + ycai.getJbno() + "的状态为"
							+ YbStat.getName(ycai.getStat()) + ",不能对其设置作业停止标识。").toString();
				}
				if (ZtConstants.ZPNG_JD_ETLZS_YZS.equals(ycai.getJdez())) {
					return new Result(-2, "COIL No.为" + ycai.getJbno()
							+ "的原板已制作ETL指示书" + ",不能对其设置作业停止标识。").toString();
				}
				ztbj = ycai.getZtbj();
			}
			else {
				zpng = zpBo.getZp(id);
				if (zpng == null) continue;
				if (ZpStat.SJLR.stat.equals(zpng.getStat())
						|| ZpStat.SJZL.stat.equals(zpng.getStat())) {
					return new Result(-2, "PILE No.为" + zpng.getJbno() + "的状态为"
							+ ZpStat.getName(zpng.getStat()) + ",不能对其设置作业停止标识。").toString();
				}
				if (EXpzk.ZJP_KEY.equals(zpng.getXpzk())
						&& ZtConstants.ZPNG_JD_SLZS_YZS.equals(zpng.getJdez())) {
					return new Result(-2, "PILE No.为" + zpng.getJbno()
							+ "的中间品已制作SL指示书" + ",不能对其设置作业停止标识。").toString();
				}
				chno = zpng.getChno();
				if (chno != null && !chno.isEmpty()) {
					return new Result(-2, "PILE No.为" + zpng.getJbno()
							+ "的制品已制作装箱指示书" + ",不能对其设置作业停止标识。").toString();
				}
				ztbj = zpng.getZtbj();
			}
			if (ztbj != null && !ztbj.isEmpty()) {
				if (errors.length() == 0) {
					errors.append(id);
				}
				else {
					errors.append(",").append(id);
				}
			}
		}
		if (errors.length() > 0) {
			errors.append("已设置作业停止标记。是否确定重新设置作业停止标记?");
			return new Result(-1, errors.toString()).toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public String getCheckXpbl(String[] ids) {
		String xpblJbnos = zpBo.getXpblJbnos(Arrays.asList(ids));
		if (xpblJbnos != null && !xpblJbnos.isEmpty()) {
			StringBuilder errors = new StringBuilder();
			errors.append(xpblJbnos).append("已设置保留标记。是否确定重新设置鉙标记?");
			return new Result(-1, errors.toString()).toString();
		}
		return Result.SUCCESS;
	}

	public ICzjlBO getCzjlBO() {
		return czjlBO;
	}

	public void setCzjlBO(ICzjlBO czjlBO) {
		this.czjlBO = czjlBO;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IZsBO getZsBo() {
		return zsBo;
	}

	public void setZsBo(IZsBO zsBo) {
		this.zsBo = zsBo;
	}

	public ICmnBO getCmnBo() {
		return cmnBo;
	}

	public void setCmnBo(ICmnBO cmnBo) {
		this.cmnBo = cmnBo;
	}

	public IJlbgDAO getJlbgdao() {
		return jlbgdao;
	}

	public void setJlbgdao(IJlbgDAO jlbgdao) {
		this.jlbgdao = jlbgdao;
	}

	public IDcrzDAO getDcrzDao() {
		return dcrzDao;
	}

	public void setDcrzDao(IDcrzDAO dcrzDao) {
		this.dcrzDao = dcrzDao;
	}

	public IYlrzDAO getYlrzDao() {
		return ylrzDao;
	}

	public void setYlrzDao(IYlrzDAO ylrzDao) {
		this.ylrzDao = ylrzDao;
	}

	public IZyrzDAO getZyrzDao() {
		return zyrzDao;
	}

	public void setZyrzDao(IZyrzDAO zyrzDao) {
		this.zyrzDao = zyrzDao;
	}

	public ISjBO getSjBo() {
		return sjBo;
	}

	public void setSjBo(ISjBO sjBo) {
		this.sjBo = sjBo;
	}

	public IJinhlpDAO getJinhlpDao() {
		return jinhlpDao;
	}

	public void setJinhlpDao(IJinhlpDAO jinhlpDao) {
		this.jinhlpDao = jinhlpDao;
	}

	public IWwhtBO getWwhtBo() {
		return wwhtBo;
	}

	public void setWwhtBo(IWwhtBO wwhtBo) {
		this.wwhtBo = wwhtBo;
	}

	public IXpxxDAO getDao() {
		return dao;
	}

	public void setDao(IXpxxDAO dao) {
		this.dao = dao;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IYcaitpBO getYcaiBo() {
		return ycaiBo;
	}

	public void setYcaiBo(IYcaitpBO ycaiBo) {
		this.ycaiBo = ycaiBo;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public ISqlDAO getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISqlDAO sqlDao) {
		this.sqlDao = sqlDao;
	}

	public IInformBO getInformBO() {
		return informBO;
	}

	public void setInformBO(IInformBO informBO) {
		this.informBO = informBO;
	}

	public IYchBO getYchBO() {
		return ychBO;
	}

	public void setYchBO(IYchBO ychBO) {
		this.ychBO = ychBO;
	}

}
