package com.quanta.sino.ch.bo;

import java.util.Date;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.quanta.sino.ch.bo.api.IShdBO;
import com.quanta.sino.ch.dao.api.IChuhDAO;
import com.quanta.sino.ch.dao.api.IZng1DAO;
import com.quanta.sino.ch.dao.api.IZxzsDAO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.EFppz;
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
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZxzsTp;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yygl.bo.api.IKhyfBO;

/**
 * <p>
 * 送货单业务实现类
 * </p>
 * <p>
 * create: 2011-3-1 上午10:39:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ShdBO implements IShdBO {
	/**
	 * 装箱指示书数据接口
	 */
	private IZng1DAO dao;
	/**
	 * 制品在库业务接口
	 */
	private IZpBO zpbo;
	/**
	 * 订货单业务层高级接口
	 */
	private IDhBO dhBo;
	/**
	 * 出货实绩数据访问接口
	 */
	private IChuhDAO chuhDAO;
	/**
	 * 客户运费业务层接口
	 */
	private IKhyfBO khyfBO;
	/**
	 * 应收账款业务层接口
	 */
	private IFkfpBO yszkBO;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;

	/**
	 * 装箱指示书数据访问接口
	 */
	private IZxzsDAO zxzsDAO;

	public IZpBO getZpbo() {
		return zpbo;
	}

	public void setZpbo(IZpBO zpbo) {
		this.zpbo = zpbo;
	}

	@Override
	public void query(QEntity<Zng1Tp> qentity) {

	}

	@Override
	public void delete(String zxno) {
		ZxzsTp zxzsTp = zxzsDAO.get(zxno);
		zxzsTp.setStat(ChStat.ZF.stat);
		zxzsDAO.update(zxzsTp);
		Date chri = zxzsTp.getChri();
		List<Zng1Tp> zng1Tps = dao.find(zxno);
		if (zng1Tps.size() == 0) {
			throw new CocoException(-1, "该装箱指示书不存在");
		}
		DhuoTp dhuoTp = null;
		Long chus = 0l;
		Long chzl = 0l;
		// 出货日志
		ChuhLp chuhLp = null;
		// 付款发票
		Fkfp fkfp = null;
		// 客户运费
		Khyf khyf = null;
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
			dhuoTp.setChus(chus - chzl);
			dhBo.update(dhuoTp);
			chuhLp = parseChuhLp(chuhDAO.getChuhLp(zng1Tp.getDhno(), zng1Tp.getLine(), chri), zng1Tp, dhuoTp);
			chuhDAO.saveOrUpdate(chuhLp);
			fkfp = parseFkfp(yszkBO.get(zng1Tp.getDhno(), zng1Tp.getLine(), chri, EFppz.zp.key), zng1Tp, dhuoTp);
			if (!FpStat.CS.key.equals(fkfp.getStat())) {
				throw new CocoException(-1, "付款发票状态为"
						+ FpStat.get(fkfp.getStat()).name + "，不能进行删除操作");
			}
			if (fkfp.getKfzl() > 0) {
				yszkBO.saveOrUpdate(fkfp);
			}
			else {
				yszkBO.delete(fkfp.getId());
			}
			khyf = parseKhyf(khyfBO.getUnique(chri, zxzsTp.getYsgs(), zxzsTp.getUser(), zng1Tp.getDhno(), zng1Tp.getLine(), zxzsTp.getShno(), zxzsTp.getAddr()), zng1Tp);
			if (khyf.getChzl() > 0) {
				khyfBO.saveOrUpdate(khyf);
			}
			else {
				khyfBO.delete(khyf.getId());
			}
		}
		List<ZpngTp> zpTps = zpbo.listZpByChno(zxno);
		if (zpTps.size() == 0) {
			throw new CocoException(-1, "该装箱指示书内容为空");
		}
		for (ZpngTp zpTp : zpTps) {
			// 设置送货单号为空
			zpTp.setShno(null);
			// 设置出货单号为空
			zpTp.setChno(null);
			// 设置出货实绩登录年月为空
			zpTp.setChsd(null);
			// 设置删除标记为已初始
			zpTp.setScbj(EScbj.CS.key);
			// 设置出货指示年月日为空
			zpTp.setChzs(null);
			zpbo.update(zpTp);
		}
	}

	@Override
	public void dels(String[] zxnos) {
		for (String zxno : zxnos) {
			this.delete(zxno);
		}
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
			throw new CocoException(-1, "出货日志信息已丢失");
		}
		Double chus = chuhLp.getChus() != null ? chuhLp.getChus() : 0;
		chuhLp.setChus(chus - chzl);
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
		Double htdj = dhuoTp.getHtdj() != null ? dhuoTp.getHtdj() : 0;
		Double fpje = NumberUtils.format(NumberUtils.multiply(chzl, htdj).doubleValue(), 2);
		if (fkfp == null) {
			throw new CocoException(-1, "付款发票信息已丢失");
		}
		if (!FpStat.CS.key.equals(fkfp.getStat())) {
			throw new CocoException(-1, "付款发票不为初始，不能删除");
		}
		fkfp.setKfzl(NumberUtils.subtract(fkfp.getKfzl(), chzl).doubleValue());
		fkfp.setFpje(NumberUtils.subtract(fkfp.getFpje(), fpje).doubleValue());
		fkfp.setWsyk(NumberUtils.subtract(fkfp.getWsyk(), fpje).doubleValue());
		return fkfp;
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
		// 出货制品数量
		Integer chsu = zng1Tp.getChsu() != null ? zng1Tp.getChsu() : 0;
		if (khyf == null) {
			throw new CocoException(-1, "客户运费信息已丢失");
		}
		if (!KhyfStat.CS.stat.equals(khyf.getStat())) {
			throw new CocoException(-1, "客户运费已设置，不能删除");
		}
		khyf.setChzl(NumberUtils.format(NumberUtils.subtract(khyf.getChzl(), chzl).doubleValue(), 3));
		khyf.setChsu(khyf.getChsu() - chsu);
		String zxno = zng1Tp.getZxzsTp().getZxno();
		khyf.setZxno(khyf.getZxno().replaceAll("(,)" + zxno + "|" + zxno
				+ "(,)|" + zxno, ""));
		return khyf;
	}

	public IZng1DAO getDao() {
		return dao;
	}

	public void setDao(IZng1DAO dao) {
		this.dao = dao;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IChuhDAO getChuhDAO() {
		return chuhDAO;
	}

	public void setChuhDAO(IChuhDAO chuhDAO) {
		this.chuhDAO = chuhDAO;
	}

	public IKhyfBO getKhyfBO() {
		return khyfBO;
	}

	public void setKhyfBO(IKhyfBO khyfBO) {
		this.khyfBO = khyfBO;
	}

	public IFkfpBO getYszkBO() {
		return yszkBO;
	}

	public void setYszkBO(IFkfpBO yszkBO) {
		this.yszkBO = yszkBO;
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

}
