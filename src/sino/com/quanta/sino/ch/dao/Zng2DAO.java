package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.orm.Zng2Tp;

public class Zng2DAO implements IZng2DAO {

	private DAO dao;

	@Override
	public void save(Zng2Tp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<Zng2Tp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(Zng2Tp entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<Zng2Tp> qentity) {
		dao.query(qentity);
	}

	@Override
	public Zng2Tp get(Serializable id) {
		return dao.get(Zng2Tp.class, id);
	}

	@Override
	public Zng2Tp get(String jbno, String zxno) {
		String ql = "from Zng2Tp where jbno=? and zng1Tp.zxzsTp.zxno=?";
		return (Zng2Tp) dao.getUnique(ql, jbno, zxno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zng2Tp> findZxzsMx(String zxno) {
		return (List<Zng2Tp>) dao.query("select a from Zng2Tp a,Zng1Tp b where a.zng1Tp.id=b.id and b.zxzsTp.zxno=?", zxno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zng2Tp> findZxzsMx(String zxno, String dhno, String line) {
		return (List<Zng2Tp>) dao.query("select a from Zng2Tp a,Zng1Tp b where a.zng1Tp.id=b.id and b.zxzsTp.zxno=? and b.dhno=? and b.line=?", zxno, dhno, line);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<PbzqdVO> findBzqdVos(List<String> jbnos, String zxno) {
		String ql = "select a.ckno,a.jbno,a.zshu,a.jscn,a.zpzl,a.mazl,b.dhno,b.line,b.pzno from Zng2Tp a,Zng1Tp b where a.zng1Tp.id=b.id and b.zxzsTp.zxno=? and a.jbno in (?) order by a.ckno, a.jbno";
		List results = dao.findForValues(ql, jbnos, zxno);
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		PbzqdVO vo = null;
		String pzno;
		List<PbzqdVO> vos = new ArrayList<PbzqdVO>();
		Iterator iterator = results.iterator();
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			vo = new PbzqdVO();
			vo.setCkno((Integer) row[0]);
			vo.setJbno((String) row[1]);
			pzno = (String) row[8];
			if (pzno == null) continue;
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				vo.setJscn((Integer) row[3]);
				vo.setZshu(null);
			}
			else {
				vo.setJscn(null);
				vo.setZshu((Integer) row[2]);
			}
			vo.setZpzl((Integer) row[4]);
			vo.setMazl((Integer) row[5]);
			vo.setDhno((String) row[6] + row[7]);
			vos.add(vo);
		}
		return vos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryZxnos(String dhno, String line, String jbno) {
		StringBuilder ql = new StringBuilder();
		ql.append("select distinct b.zxzsTp.zxno from Zng2Tp a,Zng1Tp b where a.zng1Tp.id=b.id");
		if (dhno != null && !dhno.isEmpty()) {
			ql.append(" and b.dhno='").append(dhno).append("'");
		}
		if (line != null && !line.isEmpty()) {
			ql.append(" and b.line='").append(line).append("'");
		}
		if (jbno != null && !jbno.isEmpty()) {
			ql.append(" and a.jbno='").append(jbno).append("'");
		}
		return (List<String>) dao.query(ql.toString());
	}

	@Override
	public void setCpno(String cpno, List<String> jbnos) {
		dao.executeForValues("update Zng2Tp set cpno=? where jbno in (?)", jbnos, cpno);
	}

	@Override
	public long getHjzs(String zxno) {
		Object obj = dao.getUnique("select sum(a.zshu) from Zng2Tp a,Zng1Tp b where a.zng1Tp.id=b.id and b.zxzsTp.zxno=?", zxno);
		if (obj == null) {
			return 0l;
		}
		return (Long) obj;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
