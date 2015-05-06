package com.quanta.sino.yygl.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.yygl.dao.api.IChlldDAO;

/**
 * <p>
 * 出货联络单数据访问实现类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:23:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChlldDAO implements IChlldDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Chlld entity) {
		dao.save(entity);

	}

	@Override
	public void update(Chlld entity) {
		dao.update(entity);
	}

	@Override
	public void delete(List<String> ids) {
		dao.executeForValues("delete from Chlld where id in (?)", ids);
	}

	@Override
	public void query(QEntity<Chlld> qentity) {
		dao.query(qentity);
	}

	@Override
	public Chlld get(Serializable id) {
		return dao.get(Chlld.class, id);
	}

	@Override
	public void executeForValues(String ql, Collection<?> values,
			Object... params) {
		dao.executeForValues(ql, values, params);
	}

	@Override
	public void setWeather(Date chqi, String weather) {
		String ql = "update Chlld set weather=? where chqi>=? and chqi<?";
		dao.executeUpdate(ql, weather, chqi, DateUtils.add(chqi, Calendar.DAY_OF_MONTH, 1));
	}

	@Override
	public String getWeather(Date chqi) {
		String ql = "select weather from Chlld where chqi>=? and chqi<?";
		return (String) dao.getUnique(ql, chqi, DateUtils.add(chqi, Calendar.DAY_OF_MONTH, 1));
	}

}
