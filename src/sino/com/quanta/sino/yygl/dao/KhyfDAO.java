package com.quanta.sino.yygl.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.yygl.dao.api.IKhyfDAO;

/**
 * <p>
 * 客户运输数据访问层实现类
 * </p>
 * <p>
 * create: 2011-2-13 下午07:24:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfDAO implements IKhyfDAO {

	private DAO dao;

	@Override
	public void save(Khyf entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<Khyf> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(Khyf entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Khyf.class, id);
	}

	@Override
	public Khyf get(Serializable id) {
		return dao.get(Khyf.class, id);
	}

	@Override
	public Khyf getUnique(Date chri, String ysgs, String user, String dhno,
			String line, String shno, String addr) {
		String ql = "from Khyf where chri=? and ysgs=? and user=? and dhno=? and line=? and shno=? and addr=?";
		return (Khyf) dao.getUnique(ql, chri, ysgs, user, dhno, line, shno, addr);
	}

	@Override
	public ChtjVO tj(Date chriS, Date chriE, String ysgs) {
		StringBuilder ql = new StringBuilder(
				"select sum(chsu), sum(chzl) from Khyf where chri>=? and chri<?");
		if (ysgs != null && !ysgs.isEmpty()) {
			ql.append(" and ysgs='").append(ysgs).append("'");
		}
		ql = ql.append(" and dhno not like ?");
		Object[] objs = (Object[]) dao.getUnique(ql.toString(), chriS, chriE, "W%");
		ChtjVO chtjVO = new ChtjVO();
		if (objs == null || objs.length == 0) {
			return chtjVO;
		}
		chtjVO.setChsu((Long) objs[0]);
		chtjVO.setChzl((Double) objs[1]);
		return chtjVO;
	}

	@Override
	public void delete() {
		dao.executeUpdate("delete from Khyf where dhno like ?", "W%");
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
