package com.quanta.sino.etl.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.dao.api.IZpDAO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.etl.vo.ZptjVO;
import com.quanta.sino.kcgl.vo.ZppdVO;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品业务实现
 * </p>
 * <p>
 * create: 2011-1-18 上午09:02:34
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class ZpBO implements IZpBO {

	private IZpDAO dao;

	public IZpDAO getDao() {
		return dao;
	}

	public void setDao(IZpDAO dao) {
		this.dao = dao;
	}

	@Override
	public void updateEtlLodyPrint(String jbno) {
		dao.updateEtlLodyPrint(jbno);
	}

	@Override
	public List<ZpngTp> listPszp(String rczpno) {
		return dao.listPszp(rczpno);
	}

	@Override
	public ZpngTp getZp(Serializable jbno) {
		return dao.getZp(jbno);
	}

	@Override
	public ZpngTp getByRczpno(String rczpno) {
		return dao.getByRczpno(rczpno);
	}

	@Override
	public void updateJinj(String[] ids, String jinj) {
		dao.updateJinj(ids, jinj);
	}

	@Override
	public ZpngTp getRef(Serializable jbno) {
		return dao.getRef(jbno);
	}

	@Override
	public List<ZpngTp> listZp(Collection<String> jbnos) {
		return dao.listZp(jbnos);
	}

	@Override
	public void update(ZpngTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable jbno) {
		dao.delete(jbno);
	}

	@Override
	public boolean isZp(String jbno, String rczpno) {
		return dao.isZp(jbno, rczpno);
	}

	@Override
	public void save(ZpngTp entity) {
		dao.save(entity);
	}

	@Override
	public void updateAll(List<ZpngTp> entities) {
		for (ZpngTp zpngTp : entities) {
			dao.update(zpngTp);
		}
	}

	@Override
	public List<ZpngTp> listZpByDh(String dhno, String... xpzks) {
		return dao.listZpByDh(dhno, xpzks);
	}

	@Override
	public List<ZpngTp> listZpByFp(String fpno) {
		return dao.listZpByFp(fpno);
	}

	@Override
	public List<ZpngTp> listZpByRc(String rczpno) {
		return dao.listZpByRc(rczpno);
	}

	@Override
	public boolean isExistedZpng(String zsno, String jbno) {
		return dao.isExistedZpng(zsno, jbno);
	}

	@Override
	public boolean isExistedByDhno(String dhno) {
		return dao.isExistedByDhno(dhno);
	}

	@Override
	public boolean isExistedBlbj(String jbnos) {
		return dao.isExistedBlbj(jbnos);
	}

	@Override
	public void query(QEntity<ZpngTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryZppd(QEntity<ZppdVO> qentity) {
		dao.queryZppd(qentity);
	}

	@Override
	public List<ZpngTp> listZpByZs(String zsno) {
		return dao.listZpByZs(zsno);
	}

	@Override
	public List<ZpngTp> listZssmx(String zsno) {
		return dao.listZssmx(zsno);
	}

	@Override
	public List<ZpngTp> listZpByChno(String chno) {
		return dao.listZpByChno(chno);
	}

	@Override
	public List<ZpngTp> listZpByJbno(String jbno) {
		return dao.listZpByJbno(jbno);
	}

	@Override
	public List<ZpngTp> listZpzlByJbno(String jbno) {
		return dao.listZpzlByJbno(jbno);
	}

	@Override
	public int getCczl(String jbno) {
		return dao.getCczl(jbno);
	}

	@Override
	public ZpngTp getUnique(String dhno) {
		return dao.getUnique(dhno);
	}

	@Override
	public List<ZpngTp> listZpByZsDh(Collection<String> dhnos) {
		return dao.listZpByZsDh(dhnos);
	}

	@Override
	public List<PbzqdVO> findBzqdVos(List<String> jbnos) {
		return dao.findBzqdVos(jbnos);
	}

	@Override
	public boolean isExisted(String jbno) {
		return dao.isExisted(jbno);
	}

	@Override
	public ZptjVO getKbtj(Date rqS, Date rqE, String xpzk) {
		return dao.getKbtj(rqS, rqE, xpzk);
	}

	@Override
	public void updateYd(String jbno, Integer ying, Integer llyd, Date ydsj) {
		dao.updateYd(jbno, ying, llyd, ydsj);
	}

	@Override
	public void updateYing(String jbno, Integer ying) {
		dao.updateYing(jbno, ying);
	}

	@Override
	public void updateYd(String jbno, Integer ying) {
		dao.updateYd(jbno, ying);
	}

	@Override
	public Long getZlByDuic(String dhno, String duic) {
		return dao.getZlByDuic(dhno, duic);
	}

	@Override
	public void deleteZxzs(String zxno) {
		dao.deleteZxzs(zxno);
	}

	@Override
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno) {
		ZpQE qentity = new ZpQE();
		qentity.setJbnoStart(jbnoStart);
		qentity.setJbnoEnd(jbnoEnd);
		qentity.setNeStat(YbStat.SJLR.stat);
		qentity.setScbj(EScbj.CS.key);
		List<ZpngTp> zpngTps = qentity.getDatas();
		if (zpngTps.size() > 0) {
			String $ylno;
			for (ZpngTp zpngTp : zpngTps) {
				$ylno = zpngTp.getYlno();
				if ($ylno == null || $ylno.isEmpty()) {
					zpngTp.setYlno(ylno);
				}
				else {
					zpngTp.setYlno($ylno + "," + ylno);
				}
				dao.update(zpngTp);
			}
		}
		// dao.updateYlno(jbnoStart, jbnoEnd, ylno);
	}

	@Override
	public String getZtbjJbnos(List<String> jbnos) {
		return dao.getZtbjJbnos(jbnos);
	}

	@Override
	public String getXpblJbnos(List<String> jbnos) {
		return dao.getXpblJbnos(jbnos);
	}

	@Override
	public void updateZpForSlZss(String zsno, List<String> jbnos) {
		dao.updateZpForSlZss(zsno, jbnos);
	}

}
