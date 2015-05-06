package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.quanta.sino.ch.dao.api.IZng1DAO;
import com.quanta.sino.ch.vo.CdnmVO;
import com.quanta.sino.ch.vo.ChntjVO;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.CjpVO;
import com.quanta.sino.ch.vo.CxZxzsVO;
import com.quanta.sino.ch.vo.QuarterVO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.Code119;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.yygl.vo.CondVO;

public class Zng1DAO implements IZng1DAO {

	private DAO dao;

	private JdbcTemplate jt;

	@Override
	public void save(Zng1Tp entity) {
		dao.save(entity);
	}

	@Override
	public void update(Zng1Tp entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<Zng1Tp> qentity) {
		dao.query(qentity);
	}

	@Override
	public Zng1Tp get(Serializable id) {
		return dao.get(Zng1Tp.class, id);
	}

	@Override
	public void updateKhno(String dhno, String line, String khno) {
		dao.executeUpdate("update Zng1Tp set khno=? where dhno=? and line=?", khno, dhno, line);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zng1Tp> find(String zxno) {
		return (List<Zng1Tp>) dao.query("from Zng1Tp where zxzsTp.zxno=? order by dhno, line", zxno);
	}

	@Override
	public ChtjVO getChtj(Date chriS, Date chriE) {
		String ql = "select sum(chsu), sum(chzl) from ZxzsTp where chri>=? and chri<? and stat<>?";
		Object[] objs = (Object[]) dao.getUnique(ql, chriS, chriE, ChStat.ZF.stat);
		ChtjVO chtjVO = new ChtjVO();
		if (objs == null || objs.length == 0) {
			return chtjVO;
		}
		chtjVO.setChsu((Long) objs[0]);
		chtjVO.setChzl((Double) objs[1]);
		return chtjVO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ChtjVO> findChtj(String chlldId) {
		String ql = "select zxno, chsu, chzl, stat from ZxzsTp where pid=? and stat<>?";
		List results = dao.query(ql, chlldId, ChStat.ZF.stat);
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		ChtjVO chtjVO = null;
		List<ChtjVO> vos = new ArrayList<ChtjVO>();
		Iterator iterator = results.iterator();
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			chtjVO = new ChtjVO();
			chtjVO.setZxno((String) row[0]);
			chtjVO.setChsu(row[1] != null ? Long.parseLong(row[1].toString())
					: null);
			chtjVO.setChzl((Double) row[2]);
			chtjVO.setStat((String) row[3]);
			vos.add(chtjVO);
		}
		return vos;
	}

	@Override
	public void queryZxzs(QEntity<CxZxzsVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public Zng1Tp getUnique(String zxno) {
		String ql = "from Zng1Tp where zxzsTp.zxno=?";
		return (Zng1Tp) dao.getUnique(ql, zxno);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<Date, List<ChsjVO>> queryChtjByPzno(Date chriS, Date chriE) {
		String ql = "select zxzsTp.chri,pz,spbh,sum(chzl) from Zng1Tp where zxzsTp.stat=? and zxzsTp.chri >= ? and zxzsTp.chri < ? group by zxzsTp.chri,pz,spbh";
		List results = dao.query(ql, ChStat.YFH.stat, chriS, chriE);
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		ChsjVO vo = null;
		List<ChsjVO> vos = null;
		Map<Date, List<ChsjVO>> chsjs = new HashMap<Date, List<ChsjVO>>();
		Date chri = null;
		Iterator iterator = results.iterator();
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			chri = (Date) row[0];
			if ((vos = chsjs.get(chri)) == null) {
				vos = new ArrayList<ChsjVO>();
				chsjs.put(chri, vos);
			}
			vo = new ChsjVO();
			vo.setChri(chri);
			vo.setPzno((String) row[1]);
			vo.setSpbh((String) row[2]);
			vo.setChzl((Double) row[3]);
			vos.add(vo);
		}
		return chsjs;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<Date, List<ChsjVO>> queryChtjByNwai(Date chriS, Date chriE) {
		String ql = "select zxzsTp.chri,nwai,pz,spbh,sum(chzl) from Zng1Tp where zxzsTp.stat=? and zxzsTp.chri >= ? and zxzsTp.chri < ? group by zxzsTp.chri,nwai,pz,spbh";
		List results = dao.query(ql, ChStat.YFH.stat, chriS, chriE);
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		ChsjVO vo = null;
		List<ChsjVO> vos = null;
		Map<Date, List<ChsjVO>> chsjs = new HashMap<Date, List<ChsjVO>>();
		Date chri = null;
		Iterator iterator = results.iterator();
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			chri = (Date) row[0];
			if ((vos = chsjs.get(chri)) == null) {
				vos = new ArrayList<ChsjVO>();
				chsjs.put(chri, vos);
			}
			vo = new ChsjVO();
			vo.setChri(chri);
			vo.setNwai((String) row[1]);
			vo.setPzno((String) row[2]);
			vo.setSpbh((String) row[3]);
			vo.setChzl((Double) row[4]);
			vos.add(vo);
		}
		return chsjs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Date, List<ChsjVO>> queryChtjByNwai1(Date chriS, Date chriE) {
		StringBuilder ql = new StringBuilder(
				"select t.CHRI_,t.NWAI_,t.PZ_,t.SPBH_,sum(t.CHZL_) from (select b.CHRI_, case when a.DHNO_ is not null and substring(a.DHNO_,1,1)='E' then '2' else '1' end as NWAI_,a.PZ_,a.SPBH_,a.CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_");
		ql.append(" where b.STAT_='").append(ChStat.YFH.stat).append("'").append(" and b.CHRI_ >= '").append(DateUtils.format(chriS, "yyyy-MM-dd")).append("' and b.CHRI_ < '").append(DateUtils.format(chriE, "yyyy-MM-dd")).append("'");
		ql.append(") t group by t.CHRI_,t.NWAI_,t.PZ_,t.SPBH_ order by t.CHRI_ asc,t.NWAI_ asc");
		return (Map<Date, List<ChsjVO>>) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				ChsjVO vo = null;
				List<ChsjVO> vos = null;
				Map<Date, List<ChsjVO>> chsjs = new HashMap<Date, List<ChsjVO>>();
				Date chri = null;
				while (rs.next()) {
					chri = rs.getDate(1);
					if ((vos = chsjs.get(chri)) == null) {
						vos = new ArrayList<ChsjVO>();
						chsjs.put(chri, vos);
					}
					vo = new ChsjVO();
					vo.setChri(chri);
					vo.setNwai(rs.getString(2));
					vo.setPzno(rs.getString(3));
					vo.setSpbh(rs.getString(4));
					vo.setChzl(rs.getDouble(5));
					vos.add(vo);
				}
				return chsjs;
			}

		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ChtjVO> queryChtj(Date chriS, Date chriE) {
		String ql = "select zxzsTp.chri, sum(chzl) from Zng1Tp where zxzsTp.stat=? and zxzsTp.chri >= ? and zxzsTp.chri < ? and dhno not like ? group by zxzsTp.chri order by zxzsTp.chri asc";
		List results = dao.query(ql, ChStat.YFH.stat, chriS, chriE, "W%");
		if (results.size() == 0) {
			return null;
		}
		Object[] row = null;
		ChtjVO vo = null;
		List<ChtjVO> vos = new ArrayList<ChtjVO>();
		Iterator iterator = results.iterator();
		while (iterator.hasNext()) {
			row = (Object[]) iterator.next();
			vo = new ChtjVO();
			vo.setChri((Date) row[0]);
			vo.setChzl((Double) row[1]);
			vos.add(vo);
		}
		return vos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CondVO> queryCond(Date chriS, Date chriE) {
		StringBuilder ql = new StringBuilder(
				"select YEAR_, MONTH_, CDNM_, sum(CHZL_) as CHZL_ from (select 1 as XH_, YEAR(b.CHRI_) as YEAR_, MONTH(b.CHRI_) as MONTH_, a.CDNM_, a.CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where");
		ql.append(" b.STAT_='").append(ChStat.YFH.stat).append("' and a.DENG_='").append(Code119.prime.key).append("' and b.CHRI_ >= '").append(DateUtils.format(chriS, "yyyy-MM-dd")).append("' and b.CHRI_ < '").append(DateUtils.format(DateUtils.add(chriE, Calendar.MONTH, 1), "yyyy-MM-dd")).append("'");
		ql.append(" union all ");
		ql.append("select 2 as XH_, null as YEAR_, null as MONTH_, a.CDNM_, sum(a.CHZL_) as CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where");
		ql.append(" b.STAT_='").append(ChStat.YFH.stat).append("' and a.DENG_='").append(Code119.prime.key).append("' and b.CHRI_ >= '").append(DateUtils.format(chriS, "yyyy-MM-dd")).append("' and b.CHRI_ < '").append(DateUtils.format(DateUtils.add(chriE, Calendar.MONTH, 1), "yyyy-MM-dd")).append("'");
		ql.append(" group by a.CDNM_");
		ql.append(") t  group by XH_, YEAR_, MONTH_, CDNM_ order by XH_ asc, YEAR_ asc, MONTH_ asc, CDNM_ asc");
		return (List<CondVO>) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<CondVO> vos = new ArrayList<CondVO>();
				CondVO vo = null;
				int year, month;
				List<String> list = new ArrayList<String>();
				while (rs.next()) {
					year = rs.getInt(1);
					month = rs.getInt(2);
					if (list.size() == 0 || !list.contains(year + "-" + month)) {
						list.add(year + "-" + month);
						vo = new CondVO();
						vo.setYear(year);
						vo.setMonth(month);
						vos.add(vo);
					}
					vo.setZl(rs.getDouble(4), rs.getString(3));
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ChntjVO> queryChnty(Date chriE, String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select YEAR_,sum(CHZL_) from (select YEAR(b.CHRI_) as YEAR_,b.CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where b.CHRI_<'").append(DateUtils.format(chriE, "yyyy-MM-dd")).append("' and b.STAT_='").append(ChStat.YFH.stat).append("' and b.USER_='").append(user).append("') t group by t.YEAR_ order by t.YEAR_ asc");
		return (List<ChntjVO>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<ChntjVO> vos = new ArrayList<ChntjVO>();
				ChntjVO vo = null;
				while (rs.next()) {
					vo = new ChntjVO();
					vo.setYear(rs.getInt(1));
					vo.setChzl(rs.getDouble(2));
					vos.add(vo);
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CdnmVO> queryCdnm(Date chriE, String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct YEAR(b.CHRI_) as YEAR_,a.HOUU_,a.KUAN_,a.YUNY_,a.CDNM_,a.FACE_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where b.CHRI_>='").append(DateUtils.format(chriE, "yyyy-MM-dd")).append("' and b.STAT_='").append(ChStat.YFH.stat).append("' and b.USER_='").append(user).append("' and a.YUNY_ is not null and len(a.YUNY_) > 0 order by a.YUNY_ asc,a.CDNM_ asc,a.FACE_ asc, a.HOUU_ asc,a.KUAN_ asc");
		return (List<CdnmVO>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<CdnmVO> vos = new ArrayList<CdnmVO>();
				CdnmVO vo = null;
				while (rs.next()) {
					vo = new CdnmVO();
					vo.setYear(rs.getInt(1));
					vo.setHouu(rs.getDouble(2));
					vo.setKuan(rs.getDouble(3));
					vo.setYuny(rs.getString(4));
					vo.setCdnm(rs.getString(5));
					vo.setFace(rs.getString(6));

					vos.add(vo);
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public QuarterVO getQuarter(final Integer year, String user) {
		StringBuilder sql = new StringBuilder(
				"select MONTH_,sum(CHZL_) from (select YEAR(b.CHRI_) AS YEAR_,MONTH(b.CHRI_) as MONTH_,a.CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where b.STAT_='").append(ChStat.YFH.stat).append("' and b.USER_='").append(user).append("') t where t.YEAR_=").append(year).append(" group by t.MONTH_");
		return (QuarterVO) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				QuarterVO vo = new QuarterVO();
				vo.setYear(year);
				while (rs.next()) {
					vo.setChzl(rs.getInt(1), rs.getDouble(2));
				}
				return vo;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public CondVO getCond(Date chriS, Date chriE) {
		StringBuilder ql = new StringBuilder(
				"select a.CDNM_, sum(a.CHZL_) as CHZL_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_ where");
		ql.append(" b.STAT_='").append(ChStat.YFH.stat).append("' and a.DENG_='").append(Code119.prime.key).append("' and b.CHRI_ >= '").append(DateUtils.format(chriS, "yyyy-MM-dd")).append("' and b.CHRI_ < '").append(DateUtils.format(DateUtils.add(chriE, Calendar.MONTH, 1), "yyyy-MM-dd")).append("'");
		ql.append(" group by a.CDNM_ order by a.CDNM_ asc");
		return (CondVO) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				CondVO vo = new CondVO();
				while (rs.next()) {
					vo.setZl(rs.getDouble(2), rs.getString(1));
				}
				return vo;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CjpVO> queryCjp() {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.ID_,b.CHRI_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_ = b.ID_ where b.CHRI_>='2011-07-06' and a.DHNO_ like 'W%' and b.STAT_='1' group by b.ID_,b.CHRI_");
		return (List<CjpVO>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<CjpVO> vos = new ArrayList<CjpVO>();
				CjpVO vo = null;
				while (rs.next()) {
					vo = new CjpVO();
					vo.setZxno(rs.getString(1));
					vo.setChri(rs.getDate(2));

					vos.add(vo);
				}
				return vos;
			}

		});
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

}
