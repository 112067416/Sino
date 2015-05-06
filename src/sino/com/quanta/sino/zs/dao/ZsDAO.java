package com.quanta.sino.zs.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.dao.api.IZpDAO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.QxbgTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;
import com.quanta.sino.zs.dao.api.IZsDAO;
import com.quanta.sino.zs.vo.JqcVO;
import com.quanta.sino.zs.vo.ZsVO;
import com.quanta.sino.zs.vo.ZsdhVO;
import com.quanta.sino.zs.vo.ZsdxVO;

public class ZsDAO implements IZsDAO {

	private DAO dao;
	private IYcaitpDAO ycdao;
	private IZpDAO zpdao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void query(QEntity<ZsdxVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public ZsdhTp getRef(Serializable id) {
		return dao.getRef(ZsdhTp.class, id);
	}

	@Override
	public ZsdhTp getRefZsdhTp(Serializable id) {
		return dao.getRef(ZsdhTp.class, id);
	}

	@Override
	public void updateZsPrint(List<String> zss) {
		String ql = "update ZsdhTp set zsfx=? where zsno in(?)";
		dao.executeForValues(ql, zss, ZtConstants.DHZS_ZSFX_YDY);
	}

	@Override
	public void updateZsQxPrint(List<String> zss) {
		String ql = "update ZsdhTp set zsqx=? where zsno in(?)";
		dao.executeForValues(ql, zss, ZtConstants.DHZS_ZSFX_YDY);
	}

	@Override
	public void queryDscZsdhTp(QEntity<ZsdhVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryZsdhTp(QEntity<ZsdhTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void updateStat(Serializable id, String stat) {
		ZsdxTp zsdx = dao.getRef(ZsdxTp.class, id);
		zsdx.setStat(stat);
		zsdx.setUpda(new Date());

	}

	@Override
	public void updateYcaiTp(Serializable id, String zsno, String jdez) {
		YcaiTp ycaitp = dao.getRef(YcaiTp.class, id);
		ycaitp.setZsno(zsno);
		ycaitp.setJdez(jdez);
		ycaitp.setUpda(new Date());
	}

	@Override
	public void cancelYcaiTp(Serializable id, String jdez, String fpyc,
			String stat) {
		YcaiTp ycaitp = dao.getRef(YcaiTp.class, id);
		ycaitp.setZsno(null);
		ycaitp.setJdez(jdez);
		ycaitp.setFpyc(fpyc);
		ycaitp.setStat(stat);
		ycaitp.setFpno(null);
		ycaitp.setDhno(null);
		ycaitp.setUpda(new Date());
	}

	@Override
	public void updateDhuoTp(Serializable id, Integer zplz) {
		DhuoTp dhuo = dao.getRef(DhuoTp.class, id);
		// 订货DB的ETL指示量(吨)
		Double etlz = 0.0;
		if (dhuo.getEtlz() != null) {
			etlz = dhuo.getEtlz();
		}
		dhuo.setEtlz(NumberUtils.format(etlz - zplz * 0.001, 3));
		// 订货DB的分配量(吨)
		Double fpln = 0.0;
		if (dhuo.getFpln() != null) {
			fpln = dhuo.getFpln();
		}
		fpln = fpln - zplz * 0.001;
		// 分配量为0更新订货为确认
		if (fpln == 0) {
			dhuo.setStat(DhStat.confirm.key);
		}
		dhuo.setFpln(NumberUtils.format(fpln, 3));
		dhuo.setUpda(new Date());
	}

	@Override
	public void updateZsdhTp(String[] ids, String jinj, String shch, String remk) {
		ZsdhTp zsdh = null;
		List<YcaiTp> ycais = null;
		for (String id : ids) {
			zsdh = dao.getRef(ZsdhTp.class, id);
			zsdh.setJinj(jinj);
			if (shch != null && !shch.isEmpty()) {
				zsdh.setShch(shch);
			}
			zsdh.setRemk(remk);
			zsdh.setStat(ZtConstants.DHZS_STAT_YFP);
			zsdh.setUpda(new Date());
			ycais = ycdao.findByZsno(zsdh.getZsno());
			for (YcaiTp yc : ycais) {
				yc.setJinj(jinj);
			}
		}

	}

	@Override
	public void updateSLZsJinj(String[] ids, String jinj, String remk) {
		ZsdhTp zsdh = null;
		List<ZpngTp> zps = null;
		YcaiTp ycai = null;
		for (String id : ids) {
			zsdh = dao.getRef(ZsdhTp.class, id);
			zsdh.setJinj(jinj);
			zsdh.setRemk(remk);
			zsdh.setStat(ZtConstants.DHZS_STAT_YFP);
			zsdh.setUpda(new Date());
			zps = zpdao.listZssmx(zsdh.getZsno());
			for (ZpngTp zp : zps) {
				ycai = ycdao.get(zp.getRczpno());
				ycai.setJinj(jinj);
			}
		}

	}

	@Override
	public void updateREMK(String zsno, String remk) {
		ZsdhTp zsdh = null;
		zsdh = dao.getRef(ZsdhTp.class, zsno);
		if (zsdh != null) {
			zsdh.setRemk(remk);
		}

	}

	@Override
	public void updateSLZsDM(String[] ids, String sfdm, String type) {
		ZsdhTp zsdh = null;
		List<YcaiTp> ycais = null;
		List<ZpngTp> zps = null;
		YcaiTp ycai = null;
		for (String id : ids) {
			zsdh = dao.getRef(ZsdhTp.class, id);
			zsdh.setSfdm(sfdm);
			if (ProductType.etl.name.equals(type)) {
				ycais = ycdao.findByZsno(zsdh.getZsno());
				for (YcaiTp yc : ycais) {
					yc.setSfdm(sfdm);
				}
			}
			if (ProductType.sl.name.equals(type)) {
				zps = zpdao.listZssmx(zsdh.getZsno());
				for (ZpngTp zp : zps) {
					ycai = ycdao.get(zp.getRczpno());
					ycai.setSfdm(sfdm);
				}
			}

		}
	}

	@Override
	public void updateZsdh(ZsdhTp entity) {
		dao.update(entity);
	}

	@Override
	public void updateZsdx(ZsdxTp entity) {
		dao.update(entity);
	}

	@Override
	public void saveZsdX(ZsdxTp entity) {
		dao.save(entity);
	}

	@Override
	public void deleteFP(Serializable id) {
		ZsdhTp zsdhvo = dao.getRef(ZsdhTp.class, id);
		zsdhvo.setJinj(null);
		zsdhvo.setShch(null);
		zsdhvo.setRemk(null);
		zsdhvo.setStat(ZtConstants.DHZS_STAT_WFP);
		List<YcaiTp> ycais = null;
		ycais = ycdao.findByZsno(zsdhvo.getZsno());
		for (YcaiTp yc : ycais) {
			yc.setJinj(null);
		}
	}

	@Override
	public void deleteSLFP(String[] ids) {
		List<ZpngTp> zps = null;
		YcaiTp ycai = null;
		for (String id : ids) {
			ZsdhTp zsdhvo = dao.getRef(ZsdhTp.class, id);
			zsdhvo.setJinj(null);
			zsdhvo.setRemk(null);
			zps = zpdao.listZssmx(zsdhvo.getZsno());
			for (ZpngTp zp : zps) {
				ycai = ycdao.get(zp.getRczpno());
				ycai.setJinj(null);
			}
		}

	}

	@Override
	public void deleteSLDM(String[] ids, String type) {
		List<YcaiTp> ycais = null;
		List<ZpngTp> zps = null;
		YcaiTp ycai = null;
		for (String id : ids) {
			ZsdhTp zsdhvo = dao.getRef(ZsdhTp.class, id);
			zsdhvo.setSfdm(ZtConstants.ZSDH_STAT_WZS);
			if (ProductType.etl.name.equals(type)) {
				ycais = ycdao.findByZsno(zsdhvo.getZsno());
				for (YcaiTp yc : ycais) {
					yc.setSfdm(ZtConstants.ZSDH_STAT_WZS);
				}
			}
			if (ProductType.sl.name.equals(type)) {
				zps = zpdao.listZssmx(zsdhvo.getZsno());
				for (ZpngTp zp : zps) {
					ycai = ycdao.get(zp.getRczpno());
					ycai.setSfdm(ZtConstants.ZSDH_STAT_WZS);
				}
			}

		}
	}

	@Override
	public void saveDyZzs(Serializable id, Double sort) {
		// ZsdhTp zsdhvo = dao.getRef(ZsdhTp.class, id);
		// zsdhvo.setSort(sort);
		dao.executeUpdate("update ZsdhTp set sort=? where id=?", sort, id);
	}

	@Override
	public void deleteZsdxTp(Serializable id) {
		dao.delete(ZsdxTp.class, id);
	}

	@Override
	public void deleteZsdhTp(Serializable id) {
		dao.delete(ZsdhTp.class, id);
	}

	@Override
	public void deleteZszrTP(Serializable id) {
		dao.delete(ZszrTp.class, id);
	}

	@Override
	public ZszrTp getZszrTpRef(Serializable id) {
		return dao.getRef(ZszrTp.class, id);
	}

	@Override
	public ZszrTp getUniqueZszrTp(String zrjb) {
		String ql = "from ZszrTp where zrjb=?";
		return (ZszrTp) dao.getUnique(ql, zrjb);
	}

	@Override
	public boolean isExistedZszrTp(String zrjb) {
		return dao.isExisted("from ZszrTp where zrjb=?", zrjb);
	}

	@Override
	public void deleteQxbgTp(Serializable id) {
		dao.delete(QxbgTp.class, id);
	}

	@Override
	public void savezsdh(ZsdhTp entity) {

		dao.save(entity);
	}

	@Override
	public ZsdxTp get(Serializable id) {
		return dao.get(ZsdxTp.class, id);
	}

	@Override
	public ZsdxTp getUniqueZsdx(String fpno) {
		String ql = "from ZsdxTp where fpno=?";
		return (ZsdxTp) dao.getUnique(ql, fpno);
	}

	@Override
	public ZsdhTp getZsdhTp(Serializable id) {
		return dao.get(ZsdhTp.class, id);
	}

	@Override
	public void saveZszrTPs(List<ZszrTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void saveZszrTp(ZszrTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveQxbgTPs(List<QxbgTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void queryJqc(QEntity<JqcVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryWwc(QEntity<ZsVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryZsdxtp(QEntity<ZsdxTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public long getZssWcqk(String zsno, int type) {
		// 取指示书信息
		ZsdhTp zss = getZsdhTp(zsno);
		if (zss == null) {
			return -1;
		}
		// 取指示的余材类型
		EXpzk xpzk = EXpzk.get(zss.getYczk());
		if (xpzk == null) {
			return -1;
		}
		// 组合SQL
		StringBuffer ql = new StringBuffer("select count(*) from ");
		if (EXpzk.SC_KEY.equals(xpzk.key)) {
			ql.append(YcaiTp.class.getName());
		}
		else if (EXpzk.ZJP_KEY.equals(xpzk.key)) {
			ql.append(ZpngTp.class.getName());
		}
		else {
			return -1;
		}
		// 加上堆场为D的条件是为了除去出端制品的信息（他们在E或F堆场）
		ql.append(" where zsno=? and duic=? ");
		// 按类型组合
		switch (type) {
		// 未完成个数
		case 1:
			ql.append(" and qdbj=" + ZtConstants.ZPNG_QDBJ_CS);
			break;
		// 已完成个数
		case 2:
			ql.append(" and qdbj=" + ZtConstants.ZPNG_QDBJ_QD);
			// 总数
		default:
			break;
		}
		Object o = dao.getUnique(ql.toString(), zsno, DC.D.name);
		return o != null ? (Long) o : -1L;
	}

	@Override
	public void doQxfp(List<String> jbnos, String fpno) {
		String ql = "update ZsdxTp set stat=? where jbno in (?) and fpno=? ";
		dao.executeForValues(ql, jbnos, ZtConstants.ZSDX_STAT_QX, fpno);
	}

	@Override
	public void updateZsdx(String fpno, Integer cqzs, Date upda) {
		String ql = "update ZsdxTp set cqzs=?, upda=? where fpno=?";
		dao.executeUpdate(ql, cqzs, upda, fpno);
	}

	@Override
	public void saveZsdxTps(List<ZsdxTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public boolean isFpForDh(String dhno) {
		return dao.isExisted("from ZsdxTp where dhno=? and stat<>?", dhno, ZtConstants.ZSDX_STAT_QX);
	}

	public IYcaitpDAO getYcdao() {
		return ycdao;
	}

	public void setYcdao(IYcaitpDAO ycdao) {
		this.ycdao = ycdao;
	}

	public IZpDAO getZpdao() {
		return zpdao;
	}

	public void setZpdao(IZpDAO zpdao) {
		this.zpdao = zpdao;
	}

}
