/**
 * 
 */
package com.quanta.sino.ycai.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.orm.Ybcbmx;
import com.quanta.sino.orm.Ycaicb;
import com.quanta.sino.ycai.dao.api.IYcaicbDAO;

/**
 * <p>
 * 原板成本数据访问实现类
 * </p>
 * <p>
 * create: 2011-1-25 下午09:23:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YcaicbDAO implements IYcaicbDAO {

	private DAO dao;

	@Override
	public Ycaicb get(Serializable id) {
		return dao.get(Ycaicb.class, id);
	}

	@Override
	public void save(Ycaicb entity) {
		dao.save(entity);
	}

	@Override
	public boolean isExisted(String jbno) {
		return dao.isExisted("from Ycaicb where jbno=?", jbno);
	}

	@Override
	public void saveAll(List<Ycaicb> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(Ycaicb entity) {
		dao.update(entity);
	}

	@Override
	public void deleteByShip(String ship) {
		dao.executeUpdate("delete from Ycaicb where ship=?", ship);
	}

	@Override
	public Ybcbmx getMx(String ship) {
		return (Ybcbmx) dao.getUnique("from Ybcbmx where ship=?", ship);
	}

	@Override
	public void saveMx(Ybcbmx ybcbmx) {
		dao.save(ybcbmx);
	}

	@Override
	public void deleteMx(String ship) {
		dao.delete(Ybcbmx.class, ship);
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
