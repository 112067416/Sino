/**
 * 
 */
package com.quanta.sino.sl.dao;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.orm.SlsjLp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.sl.constants.SlConstants;
import com.quanta.sino.sl.dao.api.ISlDAO;
import com.quanta.sino.sl.vo.SlsjVO;

/**
 * <p>
 * SL实绩数据访问接口层
 * </p>
 * <p>
 * create: 2011-1-17 上午09:05:59
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SlDAO implements ISlDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public ZpngTp getCurr(String scxb) {
		// ZpngTp lastZp = getLastCdByScx(scxb);
		// if(lastZp == null) {
		// return null;
		// }
		return dao.getUnique(ZpngTp.class, "slin=? and stat=? and xpzk=?", scxb, ZpStat.SJLR.stat, EXpzk.ZJP_KEY);
	}

	@Override
	public ZpngTp getLastCdByScx(String scxb) {
		return (ZpngTp) dao.getUnique("from ZpngTp where slin=? and xpzk=? order by crea desc", scxb, EXpzk.BZP_KEY);
	}

	@Override
	public Long getDs(String jbno) {
		Object obj = dao.getUnique("select count(*) from ZpngTp where dbbj=? and rczpno=? ", SlConstants.ZPNG_DBBJ_DB, jbno);
		return obj != null ? (Long) obj : 0;
	}

	@Override
	public void saveSlsj(SlsjLp entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<SlsjVO> page) {
		dao.query(page);
	}

}
