package com.quanta.sino.ycai.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;

/**
 * <p>
 * 原板管理数据接口实现
 * </p>
 * <p>
 * create time : 2010-12-9 上午10:46:04
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YcaitpDAO implements IYcaitpDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(YcaiTp entity) {
		dao.save(entity);
	}

	@Override
	public boolean isExisted(String zzsj) {
		String ql = "from YcaiTp where zzsj=?";
		return dao.isExisted(ql, zzsj);
	}

	@Override
	public boolean isExistedShip(String ship) {
		String ql = "from YcaiTp where ship=?";
		return dao.isExisted(ql, ship);
	}

	@Override
	public boolean isExistedYcai(String zsno, String jbno) {
		String ql = "from YcaiTp where zsno=? and jbno<>? and stat not in ('"
				+ YbStat.SJZL.stat + "','" + YbStat.SJZZ.stat + "')";
		return dao.isExisted(ql, zsno, jbno);
	}

	@Override
	public boolean isExistedYcai(String zsno) {
		String ql = "from YcaiTp where zsno=? and stat in ('"
				+ YbStat.SJLR.stat + "','" + YbStat.SJZL.stat + "','"
				+ YbStat.SJZZ.stat + "')";
		return dao.isExisted(ql, zsno);
	}

	@Override
	public boolean isExistedYcaiSjsj(String zsno) {
		String ql = "from YcaiTp where zsno=? and stat in ('"
				+ YbStat.SJLR.stat + "')";
		return dao.isExisted(ql, zsno);
	}

	@Override
	public boolean isExistedWwhttp(String ybno, String line) {
		String ql = "from YcaiTp where ybno=? and line=? ";
		return dao.isExisted(ql, ybno, line);
	}

	@Override
	public YcaiTp get(Serializable id) {
		return dao.get(YcaiTp.class, id);
	}

	@Override
	public YcaiTp getByZzsj(String zzsj) {
		String ql = "from YcaiTp where zzsj=? order by zzny desc";
		return (YcaiTp) dao.getUnique(ql, zzsj);
	}

	@Override
	public YcaiTp getZzsj(String stat) {
		String ql = "from YcaiTp where stat=?";
		return (YcaiTp) dao.getUnique(ql, stat);
	}

	@Override
	public List<YcaiTp> findByZsno(String zsno) {
		return dao.find(YcaiTp.class, "zsno=?", zsno);
	}

	@Override
	public void query(QEntity<YcaiTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void update(YcaiTp entity) {
		dao.update(entity);
	}

	@Override
	public void deletes(List<String> jbnos) {
		String ql = "delete from YcaiTp where jbno in(?)";
		dao.executeForValues(ql, jbnos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YcaiTp> findByShip(String ship) {
		String ql = "from YcaiTp where ship=? order by jbno asc";
		return (List<YcaiTp>) dao.query(ql, ship);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YcaiTp> find(List<String> jbnos) {
		return (List<YcaiTp>) dao.findForValues("from "
				+ YcaiTp.class.getSimpleName() + " where jbno in (?)", jbnos);
	}

	@Override
	public YcaiTp getRef(Serializable id) {
		return dao.getRef(YcaiTp.class, id);
	}

	@Override
	public void setRksj(String ship, Date duib, String[] stats) {
		StringBuilder ql = new StringBuilder(
				"update YcaiTp set duib=? where ship=? and stat in ('");
		ql.append(StringUtils.join(stats, "','")).append("')");
		dao.executeUpdate(ql.toString(), duib, ship);
	}

	@Override
	public Long totalZpzl(String ship) {
		String ql = "select sum(zpzl) from YcaiTp where ship=?";
		return (Long) dao.getUnique(ql, ship);
	}

	@Override
	public boolean isZl(String jbno, String elin) {
		String ql = "from YcaiTp a,ZpngTp b  where a.jbno=b.rczpno and a.stat=? and a.jbno<>? and b.elin=?";
		return dao.isExisted(ql, YbStat.SJLR.stat, jbno, elin);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(YcaiTp.class, id);
	}

	@Override
	public void setYlno(List<String> jbnos, String ylno) {
		dao.executeForValues("update YcaiTp set ylno=? where jbno in (?)", jbnos, ylno);
	}

	@Override
	public boolean isExistedbysczt(String sczt) {
		String ql = "from YcaiTp where sczt=?";
		return dao.isExisted(ql, sczt);
	}

	@Override
	public boolean isExisted(String jbno, String sfqr) {
		String ql = "from YcaiTp where jbno=? and sfqr=?";
		return dao.isExisted(ql, jbno, sfqr);
	}

	@Override
	public void updateSczt(String id, String stat) {
		dao.executeUpdate("update YcaiTp set sczt=?  where jbno=?", stat, id);
	}

	@Override
	public YcaiTp getYcaiBySczt(String sczt) {
		return (YcaiTp) dao.getUnique("from YcaiTp where sczt=?", sczt);
	}

	@Override
	public void updateSfqr(String id, String stat) {
		dao.executeUpdate("update YcaiTp set sfqr=?  where jbno=?", stat, id);
	}

	@Override
	public void updateScztAndSfqr(String id, String sczt, String sfqr) {
		dao.executeUpdate("update YcaiTp set sczt=?, sfqr=? where jbno=?", sczt, sfqr, id);
	}

	@Override
	public boolean isExistedYcaiByJbno(String jbno) {
		String ql = "from YcaiTp where jbno=?";
		return dao.isExisted(ql, jbno);
	}

	@Override
	public boolean isExistedByJbnoAndSczt(String jbno, String sczt) {
		String ql = "from YcaiTp where jbno=? and sczt=?";
		return dao.isExisted(ql, jbno, sczt);
	}

	@Override
	public boolean isExistedBySczt(String sczt) {
		String ql = "from YcaiTp where sczt=?";
		return dao.isExisted(ql, sczt);
	}

	@Override
	public void updateYd(String jbno, Integer ying, String jdyn, Date ydsj) {
		dao.executeUpdate("update YcaiTp set ying=?, yjdr=?, ydsj=? where jbno like ?", ying, jdyn, ydsj, jbno);
	}

	@Override
	public Long getKc(Double xpho, Double xpkn, String yuny, List<String> faces) {
		StringBuilder ql = new StringBuilder(
				"select sum(zpzl) from YcaiTp where");
		ql.append(" fpyc in ('").append(EFpyc.CS.key).append("','").append(EFpyc.YC.key).append("')");
		ql.append(" and stat in ('").append(YbStat.CS.stat).append("','").append(YbStat.YRK.stat).append("')");
		if (faces != null && faces.size() > 0) {
			ql.append(" and face in ('").append(StringUtils.join(faces, "','")).append("')");
		}
		ql.append(" and scbj=? and xpho=? and xpkn=? and yuny=?");
		return (Long) dao.getUnique(ql.toString(), EScbj.CS.key, xpho, xpkn, yuny);
	}

	@Override
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno) {
		dao.executeUpdate("update YcaiTp set ylno=? where jbno >= ? and jbno <= ? and stat <> ? and scbj = ?", ylno, jbnoStart, jbnoEnd, YbStat.SJLR.stat, EScbj.CS.key);
	}
}
