package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.quanta.sino.ch.dao.api.IJczmsDAO;
import com.quanta.sino.ch.vo.JczmsVO;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.JczmsItem;

public class JczmsDAO implements IJczmsDAO {

	private DAO dao;

	private JdbcTemplate jt;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Jczms jczms) {
		dao.save(jczms);
	}

	@Override
	public void saveAll(List<JczmsItem> mx) {
		dao.saveAll(mx);
	}

	@Override
	public void update(Jczms jczms) {
		dao.update(jczms);
	}

	@Override
	public void update(JczmsItem mx) {
		dao.update(mx);
	}

	@Override
	public Jczms get(Serializable id) {
		return dao.get(Jczms.class, id);
	}

	@Override
	public List<JczmsItem> findMx(String id) {
		return dao.find(JczmsItem.class, "jczms.id=?", id);
	}

	@Override
	public List<Jczms> query(String zxno) {
		return dao.find(Jczms.class, "zxno=?", zxno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jczms> query(List<String> zxnos) {
		return (List<Jczms>) dao.findForValues("from Jczms where zxno in (?)", zxnos);
	}

	@Override
	public void queryJczms(QEntity<JczmsItem> qentity) {
		dao.query(qentity);
	}

	@Override
	public JczmsItem getItem(Serializable id) {
		return dao.get(JczmsItem.class, id);
	}

	@Override
	public Long count(Serializable id) {
		Long count = (Long) dao.getUnique("select count(*) from JczmsItem where jczms.id=?", id);
		return count;
	}

	@Override
	public void deleteItems(List<String> zxno) {
		dao.executeForValues("delete from JczmsItem  where jczms.id in (select b.id from Jczms b where b.zxno in (?))", zxno);
	}

	@Override
	public void delete(List<String> zxno) {
		dao.executeForValues("delete from Jczms where zxno in (?)", zxno);
	}

	@Override
	public boolean isExist(String zxno) {
		return dao.isExisted("from Jczms where zxno=?", zxno);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<JczmsVO> findJczmsVos(String zxno, String dhno, String line,
			final String pzno) {
		StringBuilder ql = new StringBuilder(
				"select b.jbno_, b.rczpno_, b.zpzl_, b.jscn_, b.zshu_, a.houu_, a.kuan_, a.cang_, b.sczm_, b.scfm_, b.llyd_, b.zzno_, b.mazl_ from SINO_ZNG1TP a left join SINO_ZNG2TP b on a.ID_=b.PID_ where a.ZXZSTP_='");
		ql.append(zxno).append("' and a.dhno_='").append(dhno).append("' and a.line_='").append(line).append("' order by b.jbno_ asc");
		return (List<JczmsVO>) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<JczmsVO> vos = new ArrayList<JczmsVO>();
				JczmsVO vo = null;
				while (rs.next()) {
					vo = new JczmsVO();
					vo.setJbno(rs.getString(1));
					vo.setRczpno(rs.getString(2));
					vo.setZpzl(rs.getInt(3));
					if (Code118.coil.key.equals(pzno.substring(0, 1))) {
						vo.setJscn(rs.getInt(4));
						vo.setZshu(null);
					}
					else {
						vo.setJscn(null);
						vo.setZshu(rs.getInt(5));
					}
					vo.setHouu(rs.getDouble(6));
					vo.setKuan(rs.getDouble(7));
					vo.setCang(rs.getDouble(8));
					vo.setSczm(rs.getDouble(9));
					vo.setScfm(rs.getDouble(10));
					vo.setYing(rs.getInt(11));
					vo.setZzno(rs.getString(12));
					vo.setMazl(rs.getInt(13));

					vos.add(vo);
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String checkZzJczms(List<String> zxnos) {
		StringBuilder ql = new StringBuilder(
				"select a.ZXNO_ from (select c.ZXNO_,sum(c.CHZL_) as CHZL_ from SINO_JCZMS c group by c.ZXNO_) a left join SINO_ZXZS b on a.ZXNO_=b.ID_ where a.CHZL_<>b.CHZL_ and a.ZXNO_ in ('");
		ql.append(StringUtils.join(zxnos, "','")).append("')");
		ql.append(" union ");
		ql.append(" select a.ZXNO_ from SINO_JCZMS a , (select c.JCZMS_,SUM(c.JSZL_/1000.0) AS JSZL_ from SINO_JCZMS_ITEM c  group by c.JCZMS_) b WHERE a.ID_=b.JCZMS_ and a.CHZL_<> b.JSZL_ and a.ZXNO_ in ('");
		ql.append(StringUtils.join(zxnos, "','")).append("')");
		return (String) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				StringBuilder zxnos = new StringBuilder();
				while (rs.next()) {
					if (zxnos.length() == 0) {
						zxnos.append(rs.getString(1));
					}
					else {
						zxnos.append(",").append(rs.getString(1));
					}
				}
				return zxnos.toString();
			}

		});
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

}
