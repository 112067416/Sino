package com.quanta.sino.ch.dao;

import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.ch.dao.api.IChuhDAO;
import com.quanta.sino.orm.ChuhLp;

/**
 * <p>
 * 出货实绩数据访问接口实绩类
 * </p>
 * <p>
 * create: 2011-1-23 下午02:44:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChuhDAO implements IChuhDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(ChuhLp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<ChuhLp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(ChuhLp entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public ChuhLp get(String id) {
		return dao.get(ChuhLp.class, id);
	}

	@Override
	public ChuhLp getChuhLp(String dhno, String line, Date chri) {
		return (ChuhLp) dao.getUnique("from ChuhLp where dhno=? and line=? and chri=?", dhno, line, chri);
	}

	@Override
	public void saveOrUpdate(ChuhLp entity) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			entity.setId(null);
			dao.save(entity);
		}
		else {
			dao.update(entity);
		}
	}

}
