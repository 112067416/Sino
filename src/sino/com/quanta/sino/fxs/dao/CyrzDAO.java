package com.quanta.sino.fxs.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.MktfxStat;
import com.quanta.sino.fxs.dao.api.ICyrzDAO;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.CyrzSlxb;

/**
 * <p>
 * 采样日志数据访问实现类
 * </p>
 * <p>
 * create: 2011-4-17 下午05:37:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CyrzDAO implements ICyrzDAO {

	private DAO dao;

	@Override
	public void save(Cyrz entity) {
		dao.save(entity);
	}

	@Override
	public void update(Cyrz entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Cyrz.class, id);
	}

	@Override
	public void query(QEntity<Cyrz> qentity) {
		dao.query(qentity);
	}

	@Override
	public Cyrz get(Serializable id) {
		return dao.get(Cyrz.class, id);
	}

	@Override
	public Cyrz getRef(Serializable id) {
		return dao.getRef(Cyrz.class, id);
	}

	@Override
	public boolean isExisted(String zsno, String jbno) {
		String ql = "from Cyrz where deleted=?";
		if (zsno != null && !zsno.isEmpty()) {
			ql = ql + " and zsno='" + zsno + "'";
		}
		if (jbno != null && !jbno.isEmpty()) {
			ql = ql + " and jbno='" + jbno + "'";
		}
		return dao.isExisted(ql, false);
	}

	@Override
	public Cyrz getForSl(String xb) {
		return (Cyrz) dao.getUnique("from Cyrz a where a.deleted=? and a.slsd is null and (select count(b) from CyrzSlxb b where b.id=a.id and b.line=?) = 0", false, xb);
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.join(new String[] { ECyfxxm.SL,
				ECyfxxm.SL_FXS }, "','"));
	}

	@Override
	public Cyrz getForSl() {
		StringBuilder fxxms = new StringBuilder();
		fxxms.append("'").append(ECyfxxm.SL).append("','").append(ECyfxxm.SL_FXS).append("'");
		return (Cyrz) dao.getUnique("from Cyrz a where a.deleted=? and slZt=? and slsd is null and jsxb in ("
				+ fxxms + ")", false, false);
	}

	@Override
	public Cyrz getForFxs() {
		StringBuilder fxxms = new StringBuilder();
		fxxms.append("'").append(ECyfxxm.FXS).append("','").append(ECyfxxm.SL_FXS).append("'");
		return (Cyrz) dao.getUnique("from Cyrz where deleted=? and fxsdZt=? and fxsyZt=? and jsxb in ("
				+ fxxms + ")", false, false, false);
	}

	@Override
	public CyrzSlxb getCyrzSlxb(Serializable id) {
		return dao.get(CyrzSlxb.class, id);
	}

	@Override
	public void saveCyrzSlxb(CyrzSlxb entity) {
		dao.save(entity);
	}

	@Override
	public Integer getJycs(String jbno) {
		String ql = "select count(*) from Cyrz where jbno=? and stat<>? and (fxxm like ? or fxxm like ? or fxxm like ?)";
		Object obj = (Object) dao.getUnique(ql, jbno, MktfxStat.CS.stat, "%"
				+ ECyfxxm.xfzl.name + "%", "%" + ECyfxxm.tyl.name + "%", "%"
				+ ECyfxxm.cr.name + "%");
		if (obj == null) {
			return 1;
		}
		return Integer.valueOf(obj.toString());
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
