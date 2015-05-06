package com.quanta.sino.yygl.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.SczsTp;
import com.quanta.sino.yygl.dao.api.ISczsDAO;

/**
 * <p>
 * 生产指示单数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-7-17 下午05:15:04
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class SczsDAO implements ISczsDAO {

	private DAO dao;

	@Override
	public void save(SczsTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<SczsTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(SczsTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void delete(List<String> ids) {
		dao.executeForValues("delete from SczsTp where id in (?)", ids);
	}

	@Override
	public void query(QEntity<SczsTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public SczsTp get(Serializable id) {
		return dao.get(SczsTp.class, id);
	}

	@Override
	public void updateStat(String stat, String[] ids) {
		dao.executeForValues("update SczsTp set stat=? where id in (?)", Arrays.asList(ids), stat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SczsTp> find(List<String> ids) {
		return (List<SczsTp>) dao.findForValues("from SczsTp where id in (?)", ids);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
