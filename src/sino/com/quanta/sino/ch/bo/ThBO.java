package com.quanta.sino.ch.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.bo.api.IThBO;
import com.quanta.sino.ch.bo.api.ITsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.dao.api.IThDAO;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.ThStat;
import com.quanta.sino.cmn.constants.TsStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ThTp;
import com.quanta.sino.orm.TsTp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 退货业务实现类
 * </p>
 * <p>
 * create: 2011-2-28 下午03:46:14
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ThBO implements IThBO {

	/**
	 * 退货数据访问接口
	 */
	private IThDAO dao;

	/**
	 * 投诉业务接口
	 */
	private ITsBO tsBo;

	/**
	 * 订货合同业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 装箱指示书业务接口
	 */
	private IZxzsBO zsBo;

	@Override
	public void update(ThTp entity) {
		ThTp th = dao.get(entity.getId());
		th.setThri(entity.getThri());
		th.setBz(entity.getBz());
		dao.update(th);
	}

	@Override
	public String doCxth(String[] ids) {
		ThTp thTp = null;
		Zng2Tp zng2Tp = null;
		ZpngTp zpngTp = null;
		Integer zpzl = null;
		String dhno = null;
		TsTp tsTp = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String id : ids) {
			thTp = dao.get((Serializable) id);
			if (thTp == null) continue;
			tsTp = thTp.getTsTp();
			if (tsTp == null) continue;
			if (!ThStat.TH.stat.equals(thTp.getZt())) {
				throw new CocoException(-1, "制品No.为" + tsTp.getJbno()
						+ ",已做撤销退货操作");
			}
			dhno = tsTp.getDhno() + tsTp.getLine();
			if ((zpzl = map.get(dhno)) == null) {
				map.put(dhno, tsTp.getZpzl());
			}
			else {
				map.put(dhno, tsTp.getZpzl() + zpzl);
			}
			// 获取装箱指示书明细信息
			zng2Tp = zsBo.get(tsTp.getJbno(), tsTp.getShno());
			if (zng2Tp == null) continue;
			// 修改状态为退货
			zng2Tp.setStat(TsStat.TS.stat);
			// 更新记录
			zsBo.updateZng2(zng2Tp);
			// 修改制品在库信息
			zpngTp = zpBo.getZp(tsTp.getJbno());
			if (zpngTp == null) continue;
			zpngTp.setDuic(zng2Tp.getDcno());
			// 设置出货号
			zpngTp.setChno(tsTp.getShno());
			// 设置送货号
			zpngTp.setShno(tsTp.getShno());
			// 设置出货实绩登录年月
			zpngTp.setChsd(tsTp.getChri());
			// 设置删除标记
			zpngTp.setScbj(EScbj.YCH.key);
			// 设置设置出货指示年月日
			zpngTp.setChzs(tsTp.getChri());
			zpBo.update(zpngTp);
			// 修改投诉状态 为退货状态
			tsTp.setStat(TsStat.TS.stat);
			tsBo.update(tsTp);
			// 设置退货状态
			thTp.setZt(ThStat.CX.stat);
			dao.update(thTp);
		}
		DhuoTp dhuoTp = null;
		long thus = 0l;
		long chus = 0l;
		// 修改合同信息
		for (String key : map.keySet()) {
			// 获取订货合同
			dhuoTp = dhBo.get(new DhuoTpPk(key.substring(0, 7),
					key.substring(7)));
			if (dhuoTp == null) continue;
			thus = (dhuoTp.getThus() != null ? dhuoTp.getThus() : 0);
			chus = (dhuoTp.getChus() != null ? dhuoTp.getChus() : 0);
			// 修改退货实绩量
			dhuoTp.setThus(thus - map.get(key));
			dhuoTp.setChus(chus + map.get(key));
			// 修改数据库
			dhBo.update(dhuoTp);
		}
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<ThTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public ThTp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void setFpno(String[] ids, String fpno) {
		String fpcz = (fpno != null && !fpno.isEmpty() ? ChglConstants.TH_CZZT_YCZ
				: ChglConstants.TH_CZZT_WCZ);
		ThTp thTp = null;
		for (String id : ids) {
			thTp = dao.get(id);
			if (thTp == null) continue;
			thTp.setYfph(fpno);
			thTp.setFpcz(fpcz);
			dao.update(thTp);
		}
	}

	@Override
	public String doTh(String[] ids) {
		ThTp thTp = null;
		Zng2Tp zng2Tp = null;
		ZpngTp zpngTp = null;
		Integer zpzl = null;
		String dhno = null;
		Date thri = new Date();
		TsTp tsTp = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String id : ids) {
			tsTp = tsBo.get((Serializable) id);
			if (tsTp == null) continue;
			if (!TsStat.TS.stat.equals(tsTp.getStat())) {
				throw new CocoException(-1, "制品No." + tsTp.getJbno() + ",已做"
						+ TsStat.get(tsTp.getStat()).name + "操作");
			}
			dhno = tsTp.getDhno() + tsTp.getLine();
			if ((zpzl = map.get(dhno)) == null) {
				map.put(dhno, tsTp.getZpzl());
			}
			else {
				map.put(dhno, tsTp.getZpzl() + zpzl);
			}
			// 获取装箱指示书明细信息
			zng2Tp = zsBo.get(tsTp.getJbno(), tsTp.getShno());
			if (zng2Tp == null) continue;
			// 修改状态为退货
			zng2Tp.setStat(TsStat.TH.stat);
			// 更新记录
			zsBo.updateZng2(zng2Tp);
			// 修改制品在库信息
			zpngTp = zpBo.getZp(tsTp.getJbno());
			if (zpngTp == null) continue;
			zpngTp.setDuic(zng2Tp.getDcno());
			// 设置出货号为空
			zpngTp.setChno(null);
			// 设置送货号为空
			zpngTp.setShno(null);
			// 设置出货实绩登录年月为空
			zpngTp.setChsd(null);
			// 设置删除标记为空
			zpngTp.setScbj(EScbj.CS.key);
			// 设置设置出货指示年月日为空
			zpngTp.setChzs(null);
			zpBo.update(zpngTp);
			// 修改投诉状态 为退货状态
			tsTp.setStat(TsStat.TH.stat);
			tsTp.setThri(thri);
			tsBo.update(tsTp);
			thTp = new ThTp();
			thTp.setTsTp(tsTp);
			thTp.setThri(thri);
			// 设置发票处置状态为 未处置
			thTp.setFpcz(ChglConstants.TH_CZZT_WCZ);
			// 设置退货状态
			thTp.setZt(ThStat.TH.stat);
			thTp.setBz(tsTp.getBz());
			thTp.setId(null);
			dao.save(thTp);
		}
		DhuoTp dhuoTp = null;
		long thus = 0l;
		long chus = 0l;
		// 修改合同信息
		for (String key : map.keySet()) {
			// 获取订货合同
			dhuoTp = dhBo.get(new DhuoTpPk(key.substring(0, 7),
					key.substring(7)));
			if (dhuoTp == null) continue;
			thus = (dhuoTp.getThus() != null ? dhuoTp.getThus() : 0);
			chus = (dhuoTp.getChus() != null ? dhuoTp.getChus() : 0);
			// 修改退货实绩量
			dhuoTp.setThus(thus + map.get(key));
			dhuoTp.setChus(chus - map.get(key));
			// 修改数据库
			dhBo.update(dhuoTp);
		}
		return Result.SUCCESS;
	}

	public IThDAO getDao() {
		return dao;
	}

	public void setDao(IThDAO dao) {
		this.dao = dao;
	}

	public ITsBO getTsBo() {
		return tsBo;
	}

	public void setTsBo(ITsBO tsBo) {
		this.tsBo = tsBo;
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

	public IZxzsBO getZsBo() {
		return zsBo;
	}

	public void setZsBo(IZxzsBO zsBo) {
		this.zsBo = zsBo;
	}

}
