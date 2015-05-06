package com.quanta.sino.zk.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.zk.dao.api.IEtlyygljlDAO;

/**
 * <p>
 * ETL药液管理记录
 * </p>
 * <p>
 * create: 2011-2-15 下午04:30:37
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlyygljlDAO implements IEtlyygljlDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Etlyygljl entity) {
		dao.save(entity);
	}

	@Override
	public void update(Etlyygljl entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<Etlyygljl> qentity) {
		dao.query(qentity);
	}

	@Override
	public Etlyygljl get(Serializable id) {
		return dao.get(Etlyygljl.class, id);
	}

	@Override
	public Etlyygljl getByJlsj(Date jlsj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(jlsj);
		Date begin = null, end = null;
		try {
			begin = sdf.parse(sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			end = sdf.parse(sdf.format(calendar.getTime()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		String ql = "from Etlyygljl where jlsj >=? and jlsj <= ?";
		return (Etlyygljl) dao.getUnique(ql, begin, end);
	}

	@Override
	public boolean isExisted(String id) {
		String ql = "from Etlyygljl where id=?";
		return dao.isExisted(ql, id);
	}

}
