package com.quanta.sino.yygl.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jbdd;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.orm.JbddItemLog;
import com.quanta.sino.yygl.dao.api.IJbddDAO;
import com.quanta.sino.yygl.vo.JbddtjVO;

/**
 * <p>
 * 基板订单数据访问实现类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:24:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddDAO implements IJbddDAO {

	private DAO dao;

	@Override
	public void saveItem(JbddItem entity) {
		dao.save(entity);
	}

	@Override
	public void updateItem(JbddItem entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(JbddItem.class, id);
	}

	@Override
	public void deleteJbdd(Serializable id) {
		dao.delete(Jbdd.class, id);
	}

	@Override
	public void queryItem(QEntity<JbddItem> qentity) {
		dao.query(qentity);
	}

	@Override
	public JbddItem getItem(Serializable id) {
		return dao.get(JbddItem.class, id);
	}

	@Override
	public JbddItem getUnique(String ql, Object... values) {
		return (JbddItem) dao.getUnique(ql, values);
	}

	@Override
	public void executeForValues(String ql, Collection<?> values,
			Object... params) {
		dao.executeForValues(ql, values, params);
	}

	@Override
	public void executeUpdate(String ql, Object... params) {
		dao.executeUpdate(ql, params);
	}

	@Override
	public void saveAll(List<JbddItem> entitys) {
		dao.saveAll(entitys);
	}

	@Override
	public void saveJdbb(Jbdd entity) {
		dao.save(entity);
	}

	@Override
	public void updateJdbb(Jbdd entity) {
		dao.update(entity);
	}

	@Override
	public void queryJdbb(QEntity<Jbdd> qentity) {
		dao.query(qentity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JbddItem> findItems(String jbdd) {
		return (List<JbddItem>) dao.query("from JbddItem where jbdd.id=? order by zjzt asc,zjrq asc,nwai asc,ajb asc,code desc,cond desc,abbr asc,houa asc,id asc", jbdd);
	}

	@Override
	public Jbdd getJdbb(Serializable id) {
		return dao.get(Jbdd.class, id);
	}

	@Override
	public Jbdd getJdbbByJbno(String jbno) {
		return dao.getUnique(Jbdd.class, "jbno=?", jbno);
	}

	@Override
	public JbddItem getRef(Serializable id) {
		return dao.getRef(JbddItem.class, id);
	}

	@Override
	public JbddtjVO getTj(String pid, String stat, String ddno, String abbr) {
		StringBuilder ql = new StringBuilder(
				"select sum(a.total),sum(a.conf) from JbddItem a where");
		if (pid != null && !pid.isEmpty()) {
			ql.append(" a.jbdd.id='").append(pid).append("' and");
		}
		if (stat != null && !stat.isEmpty()) {
			ql.append(" a.stat='").append(stat).append("' and");
		}
		if (ddno != null && !ddno.isEmpty()) {
			ql.append(" a.ddno='").append(ddno).append("' and");
		}
		if (abbr != null && !abbr.isEmpty()) {
			ql.append(" a.abbr='").append(abbr).append("' and");
		}
		Object[] objs = (Object[]) dao.getUnique(ql.toString().replaceAll("(where|and)$", ""));
		JbddtjVO tjVO = new JbddtjVO();
		if (objs == null || objs.length == 0) {
			return tjVO;
		}
		tjVO.setDhsl((Long) objs[0]);
		tjVO.setConf((Long) objs[1]);
		return tjVO;
	}

	@Override
	public JbddItemLog getLog(String jbddItem) {
		return (JbddItemLog) dao.getUnique("from JbddItemLog where jbddItem=?", jbddItem);
	}

	@Override
	public void deleteLog(String jbddItem) {
		dao.executeUpdate("delete from JbddItemLog where jbddItem=?", jbddItem);
	}

	@Override
	public void saveLog(JbddItemLog log) {
		dao.save(log);
	}

	@Override
	public boolean isExistedJbno(String jbno) {
		return dao.isExisted("from Jbdd where jbno=?", jbno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JbddItem> findJbddItems(String jbno, String nwai, String zjzt) {
		StringBuilder ql = new StringBuilder();
		ql.append("from JbddItem where");
		ql.append(" jbdd.jbno='").append(jbno).append("' and").append(" zjzt='").append(zjzt).append("'");
		if (nwai != null && !nwai.isEmpty()) {
			ql.append(" and nwai='").append(nwai).append("'");
		}
		// if (JbddStat.WFS.stat.equals(stat)) {
		ql.append(" order by item asc,zjrq asc,nwai asc,ajb asc,code desc,cond desc,abbr asc,houa asc,id asc");
		// }
		// else {
		// ql.append(" order by item asc");
		// }
		return (List<JbddItem>) dao.query(ql.toString());
	}

	@Override
	public Integer getMax(Serializable jbddId) {
		String ql = "select max(item) from JbddItem where jbdd.id=?";
		Object obj = (Object) dao.getUnique(ql, jbddId);
		if (obj == null) {
			return 0;
		}
		return (Integer) obj;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
