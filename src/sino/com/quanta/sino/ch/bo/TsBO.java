package com.quanta.sino.ch.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.quanta.sino.ch.bo.api.ITsBO;
import com.quanta.sino.ch.dao.api.ITsDAO;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.TsStat;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.TsTp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 投诉业务接口
 * </p>
 * <p>
 * create: 2011-1-18 下午04:38:32
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class TsBO implements ITsBO {

	/**
	 * 投诉管理数据访问接口
	 */
	private ITsDAO dao;
	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 装箱指示书2数据访问接口
	 */
	private IZng2DAO zng2DAO;

	@Override
	public String doTs(TsTp entity) {
		String jbno = entity.getJbno();
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "制品No.不存在").toString();
		}
		String zxno = entity.getShno();
		if (zxno == null || zxno.isEmpty()) {
			return new Result(-1, "送货单号不存在").toString();
		}
		Zng2Tp zng2Tp = zng2DAO.get(jbno, zxno);
		if (zng2Tp == null) {
			return new Result(-1, "该制品还未发货,不能做投诉操作").toString();
		}
		if (!(TsStat.CS.stat.equals(zng2Tp.getStat()) || TsStat.CX.stat.equals(zng2Tp.getStat()))) {
			return new Result(-1, "该制品已做" + TsStat.get(zng2Tp.getStat()).name
					+ ",不能做投诉操作").toString();
		}
		entity.setStat(TsStat.TS.stat);
		dao.save(entity);
		zng2Tp.setStat(TsStat.TS.stat);
		zng2DAO.update(zng2Tp);
		return Result.SUCCESS;
	}

	@Override
	public void doCwzm(List<String> ids) {
		TsTp tsTp = null;
		Zng2Tp zng2Tp = null;
		for (String id : ids) {
			tsTp = dao.get(id);
			if (tsTp == null) continue;
			zng2Tp = zng2DAO.get(tsTp.getJbno(), tsTp.getShno());
			if (zng2Tp == null) continue;
			zng2Tp.setStat(TsStat.CWZM.stat);
			zng2DAO.update(zng2Tp);
			tsTp.setStat(TsStat.CWZM.stat);
			dao.update(tsTp);
		}
	}

	@Override
	public void doCxts(List<String> ids) {
		TsTp tsTp = null;
		Zng2Tp zng2Tp = null;
		for (String id : ids) {
			tsTp = dao.get(id);
			if (tsTp == null) continue;
			zng2Tp = zng2DAO.get(tsTp.getJbno(), tsTp.getShno());
			if (zng2Tp == null) continue;
			zng2Tp.setStat(TsStat.CX.stat);
			zng2DAO.update(zng2Tp);
			tsTp.setStat(TsStat.CX.stat);
			dao.update(tsTp);
		}
	}

	@Override
	public void update(TsTp entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<TsTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public TsTp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String get(String jbno) {
		ZpngTp zpngTp = zpBo.getZp(jbno);
		if (zpngTp == null) {
			return new Result(-1, "该出货制品不存在 ").toString();
		}
		if (!EScbj.YCH.key.equals(zpngTp.getScbj())) {
			return new Result(-1, "该制品还未发货,不能做投诉操作 ").toString();
		}
		Zng2Tp zng2Tp = zng2DAO.get(jbno, zpngTp.getChno());
		if (zng2Tp == null) {
			return new Result(-1, "该制品还未发货,不能做投诉操作").toString();
		}
		if (!(TsStat.CS.stat.equals(zng2Tp.getStat()) || TsStat.CX.stat.equals(zng2Tp.getStat()))) {
			return new Result(-1, "该制品已做" + TsStat.get(zng2Tp.getStat()).name
					+ ",不能做投诉操作").toString();
		}
		TsTp tsTp = new TsTp();
		ReflectUtils.copy(tsTp, zpngTp, true);
		tsTp.setYsnm(zng2Tp.getZng1Tp().getZxzsTp().getYsnm());
		tsTp.setChri(zng2Tp.getZng1Tp().getZxzsTp().getChri());
		tsTp.setShno(zng2Tp.getZng1Tp().getZxzsTp().getZxno());
		String dhno = zpngTp.getDhno();
		tsTp.setDhno(dhno.substring(0, 7));
		tsTp.setLine(dhno.substring(7));
		tsTp.setTsri(new Date());
		return new Result(tsTp).toJsObject();
	}

	@Override
	public String getForJs(Serializable id) {
		TsTp entity = this.get(id);
		if (entity == null) {
			return new Result(-1, "该制品投诉记录不存在").toString();
		}
		if (!TsStat.TS.stat.equals(entity.getStat())) {
			return new Result(-1, "该制品已做" + TsStat.get(entity.getStat())
					+ ",不能再做修改操作").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public TsTp getTsTp(String jbno, String shno) {
		return dao.getTsTp(jbno, shno, TsStat.CS.stat);
	}

	public IZng2DAO getZng2DAO() {
		return zng2DAO;
	}

	public void setZng2DAO(IZng2DAO zng2dao) {
		zng2DAO = zng2dao;
	}

	public ITsDAO getDao() {
		return dao;
	}

	public void setDao(ITsDAO dao) {
		this.dao = dao;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

}
