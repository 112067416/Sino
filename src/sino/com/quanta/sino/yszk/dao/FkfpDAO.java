package com.quanta.sino.yszk.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.yszk.dao.api.IFkfpDAO;
import com.quanta.sino.yszk.vo.ChsjVO;
import com.quanta.sino.yszk.vo.HjVO;

/**
 * <p>
 * 付款发票数据访问层实现类
 * </p>
 * <p>
 * create: 2011-6-27 下午08:40:31
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FkfpDAO implements IFkfpDAO {

	private DAO dao;

	private JdbcTemplate jt;

	@Override
	public void save(Fkfp entity) {
		dao.save(entity);
	}

	@Override
	public void update(Fkfp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Fkfp.class, id);
	}

	@Override
	public void query(QEntity<Fkfp> qentity) {
		dao.query(qentity);
	}

	@Override
	public Fkfp get(Serializable id) {
		return dao.get(Fkfp.class, id);
	}

	@Override
	public void saveAll(List<Fkfp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public Fkfp getRef(Serializable id) {
		return dao.getRef(Fkfp.class, id);
	}

	@Override
	public Fkfp get(String dhno, String line, Date chri, String fppz) {
		return (Fkfp) dao.getUnique("from Fkfp where dhno=? and line=? and chri=? and fppz=?", dhno, line, chri, fppz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fkfp> find(String[] ids) {
		StringBuilder ql = new StringBuilder("from Fkfp where id in ('");
		ql.append(StringUtils.join(ids, "','")).append("') order by chri asc, dhno asc, line asc, id desc");
		return (List<Fkfp>) dao.query(ql.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fkfp> find(String[] ids, String[] stats) {
		StringBuilder ql = new StringBuilder("from Fkfp where id in ('");
		ql.append(StringUtils.join(ids, "','")).append("') and stat in ('").append(StringUtils.join(stats, "','")).append("') order by chri asc, dhno asc, line asc, id desc");
		List<Fkfp> fkfps = (List<Fkfp>) dao.query(ql.toString());
		List<Fkfp> $fkfps = new ArrayList<Fkfp>();
		if (fkfps == null || fkfps.size() == 0) return $fkfps;
		for (String id : ids) {
			for (Fkfp fkfp : fkfps) {
				if (!id.equals(fkfp.getId())) continue;
				$fkfps.add(fkfp);
				break;
			}
		}
		return $fkfps;
	}

	@Override
	public Double getWsyk(String user) {
		String ql = "select sum(wsyk) from Fkfp where stat<>? and fpdm=?";
		Double wsyk = (Double) dao.getUnique(ql, FpStat.YJS.key, user);
		return wsyk;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ChsjVO> findFpVOS(Date chqiS, Date chqiE) {
		StringBuilder ql = new StringBuilder(
				"select b.CHRI_,a.DHNO_,a.LINE_,a.GGNO_,a.HOUU_,a.KUAN_,a.CANG_,a.FUDW_,a.FUZM_,a.FUFM_,b.USER_,b.ABBR_,b.NAME_,sum(a.CHZL_),a.SPBH_,a.NWAI_,a.DENG_,a.PZ_ from SINO_ZNG1TP a left join SINO_ZXZS b on a.ZXZSTP_=b.ID_");
		ql.append(" where b.CHRI_ >= '").append(DateUtils.format(chqiS, "yyyy-MM-dd")).append("' and b.CHRI_ < '").append(DateUtils.format(chqiE, "yyyy-MM-dd")).append("' and b.STAT_='").append(ChStat.YFH.stat).append("'");
		ql.append(" group by b.CHRI_,a.DHNO_,a.LINE_,a.GGNO_,a.HOUU_,a.KUAN_,a.CANG_,a.FUDW_,a.FUZM_,a.FUFM_,b.USER_,b.ABBR_,b.NAME_,a.SPBH_,a.NWAI_,a.DENG_,a.PZ_");
		return (List<ChsjVO>) jt.query(ql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<ChsjVO> vos = new ArrayList<ChsjVO>();
				ChsjVO vo = null;
				while (rs.next()) {
					vo = new ChsjVO();

					vo.setChri(rs.getDate(1));
					vo.setDhno(rs.getString(2));
					vo.setLine(rs.getString(3));
					vo.setGgno(rs.getString(4));
					vo.setHouu(rs.getDouble(5));
					vo.setKuan(rs.getDouble(6));
					vo.setCang(rs.getDouble(7));
					vo.setFudw(rs.getString(8));
					vo.setFuzm(rs.getString(9));
					vo.setFufm(rs.getString(10));
					vo.setUser(rs.getString(11));
					vo.setAbbr(rs.getString(12));
					vo.setName(rs.getString(13));
					vo.setChzl(rs.getDouble(14));
					vo.setSpbh(rs.getString(15));
					vo.setNwai(rs.getString(16));
					vo.setDeng(rs.getString(17));
					vo.setPz(rs.getString(18));

					vos.add(vo);
				}
				return vos;
			}

		});
	}

	@Override
	public void delete(Date chqiS, Date chqiE) {
		String ql = "delete from Fkfp where chri >= ? and chri < ?";
		dao.executeUpdate(ql, chqiS, chqiE);
	}

	@Override
	public HjVO getHj(Date chqiS, Date chqiE, String fpymc, String nwai,
			String deng, String pz, String stats, Double houuS, Double houuE,
			String fppz, String fpno) {
		StringBuilder ql = new StringBuilder(
				"select sum(kfzl),sum(fpje),sum(fkje),sum(wsyk) from Fkfp where");
		if (chqiS != null) {
			ql.append(" chri >= '").append(DateUtils.format(chqiS, "yyyy-MM-dd")).append("' and");
		}
		if (chqiE != null) {
			ql.append(" chri < '").append(DateUtils.format(chqiE, "yyyy-MM-dd")).append("' and");
		}
		if (fpymc != null && !fpymc.isEmpty()) {
			ql.append(" fpymc='").append(fpymc).append("' and");
		}
		if (nwai != null && CodeNwx.jeck.key.equals(nwai)) {
			ql.append(" nwai='").append(CodeNwx.NX).append("'").append(" and dhno like '").append("E%' and");
		}
		else if (nwai != null && CodeNwx.nei.key.equals(nwai)) {
			ql.append(" nwai='").append(CodeNwx.NX).append("'").append(" and dhno not like '").append("E%' and");
		}
		else if (nwai != null && !nwai.isEmpty()) {
			ql.append(" nwai='").append(nwai).append("' and");
		}
		if (deng != null && !deng.isEmpty()) {
			ql.append(" deng='").append(deng).append("' and");
		}
		if (pz != null && !pz.isEmpty()) {
			ql.append(" pz='").append(pz).append("' and");
		}
		if (stats != null && !stats.isEmpty()) {
			ql.append(" stat in (").append(stats).append(") and");
		}
		if (houuE != null) {
			ql.append(" houu <= ").append(houuE).append(" and");
		}
		if (houuS != null) {
			ql.append(" houu > ").append(houuS).append(" and");
		}
		if (fppz != null && !fppz.isEmpty()) {
			ql.append(" fppz='").append(fppz).append("' and");
		}
		if (fpno != null && !fpno.isEmpty()) {
			ql.append(" fpno='").append(fpno).append("'");
		}
		Object[] objs = (Object[]) dao.getUnique(ql.toString().replaceAll("(where|and)$", ""));
		HjVO hjVO = new HjVO();
		if (objs == null || objs.length == 0) {
			return hjVO;
		}
		hjVO.setChzl((Double) objs[0]);
		hjVO.setFpje((Double) objs[1]);
		hjVO.setFkje((Double) objs[2]);
		hjVO.setWsyk((Double) objs[3]);

		return hjVO;
	}

	@Override
	public void setFpno(List<String> ids, String fpno) {
		String ql = "update Fkfp set fpno=? where id in (?)";
		dao.executeForValues(ql, ids, fpno);
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
