package com.quanta.sino.etl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.etl.dao.api.IZpDAO;
import com.quanta.sino.etl.vo.ZptjVO;
import com.quanta.sino.kcgl.vo.ZppdVO;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdxTp;

/**
 * <p>
 * 制品在库记录的数据访问层
 * </p>
 * 
 * @author 方元龙
 */
/**
 * <p>
 * </p>
 * <p>
 * create: 2011-1-20 上午09:50:34
 * </p>
 * 
 * @author 方元龙[gisgali@126.com]
 * @version 1.0
 */
public class ZpDAO implements IZpDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public ZpngTp getZp(Serializable jbno) {
		return dao.get(ZpngTp.class, jbno);
	}

	@Override
	public ZpngTp getByRczpno(String rczpno) {
		String ql = "from ZpngTp where rczpno=?";
		return (ZpngTp) dao.getUnique(ql, rczpno);
	}

	@Override
	public ZpngTp getRef(Serializable id) {
		return dao.getRef(ZpngTp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZpngTp> listZp(Collection<String> jbnos) {
		if (jbnos == null || jbnos.size() <= 0) {
			return null;
		}
		return (List<ZpngTp>) dao.findForValues("from "
				+ ZpngTp.class.getName() + " where jbno in (?) order by dhno", jbnos);
	}

	@Override
	public void updateEtlLodyPrint(String jbno) {
		String ql = "update ZpngTp set lody=? where jbno in(?)";
		dao.executeUpdate(ql, ZtConstants.DHZS_ZSFX_YDY, jbno);
	}

	@Override
	public void save(ZpngTp entity) {
		dao.save(entity);
	}

	@Override
	public void update(ZpngTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable jbno) {
		dao.delete(ZpngTp.class, jbno);
	}

	@Override
	public List<ZpngTp> listZpByDh(String dhno, String... xpzks) {
		if (xpzks == null || xpzks.length == 0) {
			return dao.find(ZpngTp.class, "dhno = ?", dhno);
		}
		String clause = "dhno=? and xpzk in ('" + xpzks[0] + "'";
		for (int i = 1; i < xpzks.length; i++) {
			clause += ",'" + xpzks[i] + "'";
		}
		clause += ")";
		return dao.find(ZpngTp.class, clause, dhno);
	}

	@Override
	public List<ZpngTp> listZpByFp(String fpno) {
		return dao.find(ZpngTp.class, "fpno = ?", fpno);
	}

	@Override
	public List<ZpngTp> listZpByZs(String zsno) {
		return dao.find(ZpngTp.class, "zsno = ?", zsno);
	}

	@Override
	public List<ZpngTp> listZssmx(String zsno) {
		return dao.find(ZpngTp.class, "zsno = ? and duic=?", zsno, DC.D.name);
	}

	@Override
	public List<ZpngTp> listPszp(String rczpno) {
		return dao.find(ZpngTp.class, "rczpno = ? and pssd is not null", rczpno);
	}

	@Override
	public List<ZpngTp> listZpByChno(String chno) {
		return dao.find(ZpngTp.class, "chno = ?", chno);
	}

	@Override
	public List<ZpngTp> listZpByJbno(String jbno) {
		return dao.find(ZpngTp.class, "rczpno= ?", jbno);
	}

	@Override
	public List<ZpngTp> listZpzlByJbno(String jbno) {
		return dao.find(ZpngTp.class, "rczpno= ? and chan  in (?,?)", jbno, ChanType.hg.key, ChanType.bl.key);
	}

	@Override
	public int getCczl(String jbno) {
		String ql = "select sum(zpzl) from ZpngTp where rczpno=?";
		Object obj = (Object) dao.getUnique(ql, jbno);
		if (obj == null) {
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}

	@Override
	public void query(QEntity<ZpngTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryZppd(QEntity<ZppdVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public boolean isExistedZpng(String zsno, String jbno) {
		String ql = "from ZpngTp where zsno=? and jbno<>? and duic='"
				+ DC.D.name + "' and  stat not in ('" + ZpStat.SJZL.stat
				+ "','" + ZpStat.SJZZ.stat + "')";
		return dao.isExisted(ql, zsno, jbno);
	}

	@Override
	public boolean isExistedByDhno(String dhno) {
		return dao.isExisted("from ZpngTp where dhno=?", dhno);
	}

	@Override
	public boolean isExistedBlbj(String jbnos) {
		StringBuilder ql = new StringBuilder();
		ql.append("from ZpngTp where blbj is not null and jbno in ('").append(jbnos).append("')");
		return dao.isExisted(ql.toString());
	}

	@Override
	public ZpngTp getUnique(String dhno) {
		return dao.getUnique(ZpngTp.class, "dhno = ?", dhno);
	}

	@Override
	public List<ZpngTp> listZpByRc(String rczpno) {
		return dao.find(ZpngTp.class, "rczpno = ?", rczpno);
	}

	@Override
	public void saveAll(List<ZpngTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public boolean isZp(String jbno, String rczpno) {
		String ql = "from ZpngTp  where jbno<>? and rczpno=?";
		return dao.isExisted(ql, jbno, rczpno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZpngTp> listZpByZsDh(Collection<String> dhnos) {
		if (dhnos == null || dhnos.size() <= 0) {
			return null;
		}
		return (List<ZpngTp>) dao.findForValues("from "
				+ ZpngTp.class.getName() + " where jbno in (select jbno from "
				+ ZsdxTp.class.getName() + " where dhno in(?)) ", dhnos);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<PbzqdVO> findBzqdVos(List<String> jbnos) {
		String ql = "select ckno,jbno,zshu,jscn,zpzl,mazl,dhno,pzno,bzcl,dmzl from ZpngTp where jbno in (?) order by ckno, jbno";
		List results = dao.findForValues(ql, jbnos);
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		PbzqdVO vo = null;
		List<PbzqdVO> vos = new ArrayList<PbzqdVO>();
		Iterator iterator = results.iterator();
		String pzno;
		int zpzl, bzcl, dmzl;
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			vo = new PbzqdVO();
			vo.setCkno(row[0] != null ? (Integer) row[0] : null);
			vo.setJbno((String) row[1]);
			pzno = (row[7] != null ? row[7].toString() : null);
			if (pzno == null) continue;
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				vo.setJscn((Integer) row[3]);
				vo.setZshu(null);
			}
			else {
				vo.setJscn(null);
				vo.setZshu((Integer) row[2]);
			}
			zpzl = row[4] != null ? (Integer) row[4] : 0;
			vo.setZpzl(zpzl);
			bzcl = row[8] != null ? (Integer) row[8] : 0;
			dmzl = row[9] != null ? (Integer) row[9] : 0;
			vo.setMazl(zpzl + bzcl + dmzl);
			vo.setDhno((String) row[6]);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public boolean isExisted(String jbno) {
		return dao.isExisted("from ZpngTp where jbno=?", jbno);
	}

	@Override
	public ZptjVO getKbtj(Date rqS, Date rqE, String xpzk) {
		StringBuilder ql = new StringBuilder(
				"select count(*), sum(zpzl) from ZpngTp where xpzk=?");
		int index = 0;
		if (rqS != null) {
			index |= 1;
			ql.append(" and kbsd>=?");
		}
		if (rqE != null) {
			index |= 2;
			ql.append(" and kbsd<?");
		}
		Object[] objs = null;
		if (index == 0) {
			objs = (Object[]) dao.getUnique(ql.toString(), xpzk);
		}
		else if (index == 1) {
			objs = (Object[]) dao.getUnique(ql.toString(), xpzk, rqS);
		}
		else if (index == 2) {
			objs = (Object[]) dao.getUnique(ql.toString(), xpzk, rqE);
		}
		else if (index == 3) {
			objs = (Object[]) dao.getUnique(ql.toString(), xpzk, rqS, rqE);
		}
		ZptjVO zptjVO = new ZptjVO();
		if (objs == null || objs.length == 0) {
			return zptjVO;
		}
		zptjVO.setSu((Long) objs[0]);
		zptjVO.setZl((Long) objs[1]);
		return zptjVO;
	}

	@Override
	public void updateYd(String jbno, Integer ying, Integer llyd, Date ydsj) {
		dao.executeUpdate("update ZpngTp set ying=?, llyd=?, ydsj=? where jbno like ?", ying, llyd, ydsj, jbno);
	}

	@Override
	public void updateYd(String jbno, Integer ying) {
		dao.executeUpdate("update ZpngTp set ying=?, llyd=? where jbno like ?", ying, ying, jbno);
	}

	@Override
	public void updateYing(String jbno, Integer ying) {
		dao.executeUpdate("update ZpngTp set ying=? where jbno like ?", ying, jbno);
	}

	@Override
	public void updateJinj(String[] ids, String jinj) {
		ZpngTp zp = null;
		for (String id : ids) {
			zp = dao.getRef(ZpngTp.class, id);
			zp.setJinj(jinj);
		}

	}

	@Override
	public Long getZlByDuic(String dhno, String duic) {
		String ql = "select sum(zpzl) from ZpngTp where dhno=? and duic=? and scbj=?";
		Object obj = (Object) dao.getUnique(ql, dhno, duic, EScbj.CS.key);
		if (obj == null) {
			return 0l;
		}
		return (Long) obj;
	}

	@Override
	public void deleteZxzs(String zxno) {
		dao.executeUpdate("update ZpngTp set chno=null,chzs=null where chno=?", zxno);
	}

	@Override
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno) {
		dao.executeUpdate("update ZpngTp set ylno=? where jbno >= ? and jbno <= ? and stat <> ? and scbj = ?", ylno, jbnoStart, jbnoEnd, YbStat.SJLR.stat, EScbj.CS.key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getZtbjJbnos(List<String> jbnos) {
		List<String> $jbnos = (List<String>) dao.findForValues("select jbno from ZpngTp where jbno in (?) and ztbj is not null", jbnos);
		if ($jbnos.size() == 0) {
			return null;
		}
		return StringUtils.join($jbnos, "、");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getXpblJbnos(List<String> jbnos) {
		List<String> $jbnos = (List<String>) dao.findForValues("select jbno from ZpngTp where jbno in (?) and blbj is not null", jbnos);
		if ($jbnos.size() == 0) {
			return null;
		}
		return StringUtils.join($jbnos, "、");
	}

	@Override
	public void updateZpForSlZss(String zsno, List<String> jbnos) {
		dao.executeForValues("update ZpngTp set jdsz=? where zsno=? and jbno in (?)", jbnos, ZtConstants.ZPNG_JD_SLZS_YZS, zsno);
	}
}
