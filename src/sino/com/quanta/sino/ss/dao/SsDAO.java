package com.quanta.sino.ss.dao;

import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.orm.Hbbjl;
import com.quanta.sino.orm.PlscItemLp;
import com.quanta.sino.orm.PlscLp;
import com.quanta.sino.orm.PlxmLp;
import com.quanta.sino.ss.dao.api.ISsDAO;
import com.quanta.sino.ss.vo.SlsjVO;
import com.quanta.sino.ss.vo.SsxpVO;

/**
 * <p>
 * SS分选数据访问实现
 * </p>
 * <p>
 * create: 2011-1-20 下午07:43:19
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsDAO implements ISsDAO {

	private DAO dao;

	@Override
	public void queryRZ(QEntity<SsxpVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public void save(PlscLp entity) {
		dao.save(entity);
	}

	@Override
	public void save(Hbbjl entity) {
		dao.save(entity);
	}

	@Override
	public void savePlscItem(PlscItemLp entity) {
		dao.save(entity);
	}

	@Override
	public void save(PlxmLp entity) {
		dao.save(entity);
	}

	@Override
	public List<Hbbjl> findHbbjl(String jbno) {
		return dao.find(Hbbjl.class, "xbh=?", jbno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findPlsc(String jbno) {
		return (List<String>) dao.query("select plscLp.id from PlscItemLp where jbn=?", jbno);
	}

	@Override
	public List<PlscItemLp> findPlscItemLp(String id) {
		return dao.find(PlscItemLp.class, "plscLp.id=?", id);
	}

	@Override
	public void deletebyxbh(String jbno) {
		String ql = "Delete from Hbbjl where xbh=?";
		dao.executeUpdate(ql, jbno);
	}

	@Override
	public void query(QEntity<SlsjVO> page) {
		dao.query(page);
	}

	@Override
	public Hbbjl getUnique(String xbh, String zjbh) {
		return dao.getUnique(Hbbjl.class, "xbh = ? and zjbh=?", xbh, zjbh);
	}

	@Override
	public void deleteHbbjl(String id) {
		dao.delete(Hbbjl.class, id);
	}

	@Override
	public boolean isExisted(String jbno) {
		String ql = "from ZpngTp where scbj=? and jbno in (select zjbh from Hbbjl where xbh=?)";
		return dao.isExisted(ql, ZtConstants.ZPNG_SC_YXM, jbno);
	}

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
