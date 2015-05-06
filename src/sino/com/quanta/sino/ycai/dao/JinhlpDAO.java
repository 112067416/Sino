package com.quanta.sino.ycai.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.orm.JinhLp;
import com.quanta.sino.ycai.dao.api.IJinhlpDAO;

/**
 * <p>
 * 原材卷板进货日志表数据访问实现类
 * </p>
 * <p>
 * create: 2011-1-12 下午01:50:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JinhlpDAO implements IJinhlpDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public JinhLp get(Serializable id) {
		return dao.get(JinhLp.class, id);
	}

	@Override
	public void save(JinhLp entity) {
		dao.save(entity);
	}

	@Override
	public void update(JinhLp entity) {
		dao.update(entity);
	}

}
