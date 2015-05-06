/**
 * 
 */
package com.quanta.sino.dh.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.dao.api.IDhDAO;
import com.quanta.sino.dh.vo.Dhjdmx;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.DhuoChmxVO;
import com.quanta.sino.dh.vo.DhuoVO;
import com.quanta.sino.dh.vo.FspZlVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;

/**
 * <p>
 * 订货单数据访问实现类
 * </p>
 * <p>
 * create: 2011-1-4 上午10:51:00
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhDAO implements IDhDAO {

	private DAO dao;

	private JdbcTemplate jt;

	private static String FUDW1 = "WB";

	private static String FUDW2 = "GM";

	@Override
	public void save(DhuoTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<DhuoTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(DhuoTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(DhuoTp.class, id);
	}

	@Override
	public void deletes(List<DhuoTpPk> ids) {
		for (DhuoTpPk id : ids) {
			dao.delete(DhuoTp.class, id);
		}
		// dao.executeForValues("delete from DhuoTp where id in (?)", ids);
	}

	@Override
	public void query(QEntity<DhuoTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public DhuoTp get(String dhno, String line, String user) {
		StringBuilder ql = new StringBuilder();
		ql.append("from DhuoTp where dhno=?");
		int arg = 0;
		if (line != null && !line.isEmpty()) {
			ql.append(" and line=?");
			arg |= 1;
		}
		if (user != null && !user.isEmpty()) {
			ql.append(" and user=?");
			arg |= 2;
		}
		ql.append(" order by line desc");
		switch (arg) {
		case 0:
			return (DhuoTp) dao.getUnique(ql.toString(), dhno);
		case 1:
			return (DhuoTp) dao.getUnique(ql.toString(), dhno, line);
		case 2:
			return (DhuoTp) dao.getUnique(ql.toString(), dhno, user);
		case 3:
			return (DhuoTp) dao.getUnique(ql.toString(), dhno, line, user);
		}
		return null;
	}

	@Override
	public DhuoTp get(String dhno, String orders) {
		String ql = "from DhuoTp where dhno=? order by line asc";
		return (DhuoTp) dao.getUnique(ql, dhno);
	}

	@Override
	public DhuoTp get(Serializable id) {
		return dao.get(DhuoTp.class, id);
	}

	@Override
	public boolean isExisted(Serializable id) {
		return dao.isExisted(DhuoTp.class, id);
	}

	@Override
	public boolean isExisted(String dhno, String line) {
		int arg = 0;
		if (dhno != null && !dhno.isEmpty()) {
			arg |= 1;
		}
		if (line != null && !line.isEmpty()) {
			arg |= 2;
		}
		if (arg == 1) {
			return dao.isExisted("from DhuoTp where dhno=?", dhno);
		}
		else if (arg == 2) {
			return dao.isExisted("from DhuoTp where line=?", line);
		}
		else if (arg == 3) {
			return dao.isExisted("from DhuoTp where dhno=? and line=?", dhno, line);
		}
		return false;
	}

	@Override
	public boolean isExistedByDhno(String dhno) {
		return dao.isExisted("from DhuoTp where dhno=?", dhno);
	}

	@Override
	public DhuoTp getRef(Serializable id) {
		return dao.getRef(DhuoTp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DhuoTp> findByIds(List<String> ids) {
		return (List<DhuoTp>) dao.findForValues(" from DhuoTp where dhno+line in (?)  order by dhqr asc, dhno asc, line asc", ids);
	}

	@Override
	public DhuoTp getLatest() {
		return (DhuoTp) dao.getUnique("from DhuoTp order by crea desc");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Dhjdmx> fetchDhjdmxs(String dhnoStart, String dhnoEnd) {
		StringBuilder sql = new StringBuilder(
				"select * from (select a.FPNO_,b.ZSNO_,b.ZTBJ_,null as BLBJ_,b.CHAN_,b.JBNO_,b.ZPZL_,b.KW_,null as PLQF_,a.DHNO_ from SINO_ZSDXTP a left join SINO_YCAITP b on b.JBNO_=a.JBNO_ where a.FPNO_<>'").append(ZtConstants.ZSDX_FP_NO).append("' and a.STAT_<>'").append(ZtConstants.ZSDX_STAT_QX).append("' and a.YCZK_='").append(EXpzk.SC.key).append("' and b.SCBJ_='").append(EScbj.CS.key).append("' and a.DHNO_>='").append(dhnoStart).append("' and a.DHNO_<='").append(dhnoEnd).append("'");
		sql.append(" union all ");
		sql.append("select null as FPNO_,a.ZSNO_,a.ZTBJ_,a.BLBJ_,a.CHAN_,a.JBNO_,a.ZPZL_,a.KW_,a.PLQF_,a.DHNO_ from SINO_ZPNGTP a where a.FPYC_<>'").append(EFpyc.YC.key).append("' and a.SCBJ_='").append(EScbj.CS.key).append("' and a.DHNO_>='").append(dhnoStart).append("' and a.DHNO_<='").append(dhnoEnd).append("') c order by DHNO_ asc, JBNO_ asc, KW_ asc");
		return (List<Dhjdmx>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Dhjdmx> vos = new ArrayList<Dhjdmx>();
				Dhjdmx mx = null;
				while (rs.next()) {
					mx = new Dhjdmx();
					mx.setFpno(rs.getString(1));
					mx.setZsno(rs.getString(2));
					mx.setStop(rs.getString(3));
					mx.setBlbj(rs.getString(4));
					mx.setChan(rs.getString(5));
					mx.setJbno(rs.getString(6));
					mx.setZpzl(rs.getInt(7));
					mx.setKw(rs.getString(8));
					mx.setPlqf(rs.getString(9));
					mx.setDhno(rs.getString(10));

					vos.add(mx);
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DhuoVO getDhjd(String dhno, String line) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select a.USER_,a.ABBR_,a.JHQI_,a.DHNO_,a.LINE_,a.PZNO_,a.GGNO_,a.YUNY_,a.FUDW_,a.FUZM_,a.FUFM_,a.HOUU_,a.KUAN_,a.CANG_,a.FACE_"
				+ ",a.YYAN_,a.DMFX_,a.HTZL_,a.JHQF_,a.JHFZ_,a.JHZZ_,a.FPLN_,a.ETLH_,a.SLHG_,a.CHUS_,a.KBUS_,a.THUS_ from SINO_V_DHUO_SCTJ a");
		sql.append(" where a.DHNO_='").append(dhno).append("' and a.LINE_='").append(line).append("'");
		return (DhuoVO) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				DhuoVO dhuoVO = null;
				while (rs.next()) {
					dhuoVO = new DhuoVO();
					dhuoVO.setUser(rs.getString(1));
					dhuoVO.setAbbr(rs.getString(2));
					dhuoVO.setJhqi(rs.getDate(3));
					dhuoVO.setDhno(rs.getString(4));
					dhuoVO.setLine(rs.getString(5));
					dhuoVO.setPzno(rs.getString(6));
					dhuoVO.setGgno(rs.getString(7));
					dhuoVO.setYuny(rs.getString(8));
					dhuoVO.setFudw(rs.getString(9));
					dhuoVO.setFuzm(rs.getString(10));
					dhuoVO.setFufm(rs.getString(11));
					dhuoVO.setHouu(rs.getDouble(12));
					dhuoVO.setKuan(rs.getDouble(13));
					dhuoVO.setCang(rs.getDouble(14));
					dhuoVO.setFace(rs.getString(15));
					dhuoVO.setYyan(rs.getString(16));
					dhuoVO.setDmfx(rs.getString(17));
					dhuoVO.setHtzl(rs.getDouble(18));
					dhuoVO.setJhqf(rs.getString(19));
					dhuoVO.setJhfz(rs.getInt(20));
					dhuoVO.setJhzz(rs.getInt(21));
					dhuoVO.setFpln(rs.getDouble(22));
					dhuoVO.setEtlh(rs.getDouble(23));
					dhuoVO.setSlhg(rs.getDouble(24));
					dhuoVO.setChus(rs.getDouble(25));
					dhuoVO.setKbus(rs.getDouble(26));
					dhuoVO.setThus(rs.getDouble(27));
					break;
				}
				return dhuoVO;
			}

		});
	}

	@Override
	public Long count(String dhno) {
		Long count = (Long) dao.getUnique("select count(*) from DhuoTp where dhno = ?", dhno);
		return count;
	}

	@Override
	public void updateSfdy(List<String> dhnos, List<String> lines, String sfdy) {
		StringBuilder ql = new StringBuilder();
		ql.append("update DhuoTp set sfdy=? where dhno in ('").append(StringUtils.join(dhnos, "','")).append("')").append(" and line in ('").append(StringUtils.join(lines, "','")).append("')");
		dao.executeUpdate(ql.toString(), sfdy);
	}

	@Override
	public void updateStat(String dhno, String line, String stat) {
		dao.executeUpdate("update DhuoTp set stat=? where dhno=? and line=?", stat, dhno, line);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> queryIds(String sfdy) {
		StringBuilder sql = new StringBuilder(
				"select DHNO_+'-'+LINE_+'-'+CASE WHEN DHQR_ is NULL or len(DHQR_) = 0THEN '' ELSE DHQR_ END from SINO_DHUOTP where SFDY_='");
		sql.append(sfdy).append("' order by DHQR_ asc, DHNO_ asc, LINE_ asc");
		return (List<String>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<String> vos = new ArrayList<String>();
				while (rs.next()) {
					vos.add(rs.getString(1));
				}
				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<FspZlVO> queryFsp(String yuny, Double xpho, Double xpkn,
			Double xpcn, String face, String xpzk, String chan,
			final Map<String, String> dxls) {
		StringBuilder sql = new StringBuilder();
		String[] fpycs = { EFpyc.CS.key, EFpyc.YC.key };
		sql.append("select JBNO_,ZPZL_,FUDW_,FUZM_,FUFM_ from SINO_ZPNGTP where YUNY_='").append(yuny).append("' and XPHO_=").append(xpho).append(" and XPKN_=").append(xpkn);
		if (EXpzk.BZP.key.equals(xpzk)) {
			sql.append(" and XPCN_=").append(xpcn);
		}
		sql.append(" and FACE_='").append(face).append("' and XPZK_='").append(xpzk).append("'");
		sql.append(" and SCBJ_='").append(EScbj.CS.key).append("' and STAT_='").append(ZpStat.CS.stat).append("' and DUIC_ <> 'E'").append(" and CHAN_='").append(chan).append("' and FPYC_ in ('").append(StringUtils.join(fpycs, "','")).append("')");
		sql.append(" order by FUDW_ asc,FUZM_ asc,FUFM_ asc");
		return (List<FspZlVO>) jt.query(sql.toString(), new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<FspZlVO> vos = new ArrayList<FspZlVO>();
				int zpzl;
				String fudw, fuzm, fufm;
				List<String> list = new ArrayList<String>();
				String dxl = null, jbno = null;
				FspZlVO vo = null;
				while (rs.next()) {
					jbno = rs.getString(1);
					zpzl = rs.getInt(2);
					fudw = rs.getString(3);
					fuzm = rs.getString(4);
					fufm = rs.getString(5);

					if (fudw != null && FUDW2.equals(fudw)) {
						fuzm = dxls.get(FUDW2 + fuzm);
						fufm = dxls.get(FUDW2 + fufm);
					}
					dxl = fuzm + fufm;
					if (list.size() == 0 || !list.contains(dxl)) {
						list.add(dxl);
						vo = new FspZlVO();
						vo.setFudw(FUDW1);
						vo.setFuzm(fuzm);
						vo.setFufm(fufm);

						vos.add(vo);
					}
					vo.setZpzl(zpzl + (vo.getZpzl() != null ? vo.getZpzl() : 0));
					vo.getJbnos().add(jbno);
				}
				return vos;
			}
		});
	}

	@Override
	public void fpCjp(List<String> jbnos, String dhnoAndLine, String abbr,
			String pzno) {
		dao.executeForValues("update ZpngTp set fpyc=?,dhno=?,abbr=?,pzno=?,nwai=? where jbno in (?)", jbnos, EFpyc.FP.key, dhnoAndLine, abbr, pzno, CodeNwx.NX);
	}

	@Override
	public List<DhuoChmxVO> queryDhuoChVO(DhuoChVO vo) {
		Date jhqiB = vo.getJhqiB();
		Date jhqiE = vo.getJhqiE();
		Date chqiB = vo.getChqiB();
		Date chqiE = vo.getChqiE();
		StringBuilder sql = new StringBuilder();
		sql.append("select top 3000 a.NAME_,a.DHNO_,a.LINE_,a.PZNO_,a.CDNM_,a.GGNO_,a.YUNY_,a.FUDW_,a.FUZM_,a.FUFM_,a.HOUU_,a.KUAN_,a.CANG_,a.QIXN_,a.DDNM_,a.FACE_,a.HTDJ_,a.HTZL_,isnull(d.CHZL_,0) as CHZL_,HTZL_- isnull(d.CHZL_,0) as HTC_ from SINO_V_DHHTB a left join (select b.DHNO_,b.LINE_,sum(b.CHZL_) as CHZL_ from sino_zng1tp b left join SINO_ZXZS c on b.ZXZSTP_=c.ID_ where c.STAT_='1'");
		if (chqiB != null) {
			sql.append(" and c.CHRI_ >= '").append(DateUtils.format(chqiB, DateUtils.YMD_HMS)).append("'");
		}
		if (chqiE != null) {
			sql.append(" and c.CHRI_ <= '").append(DateUtils.format(chqiE, DateUtils.YMD_HMS)).append("'");
		}
		sql.append(" group by b.DHNO_,b.LINE_) d on a.DHNO_=d.DHNO_ and a.LINE_=d.LINE_ where (a.DHNO_ like 'D%' or a.DHNO_ like 'E%') ");
		if (jhqiB != null) {
			sql.append(" and a.JHQI_ >= '").append(DateUtils.format(jhqiB, DateUtils.YMD_HMS)).append("'");
		}
		if (jhqiE != null) {
			sql.append(" and a.JHQI_ <= '").append(DateUtils.format(jhqiE, DateUtils.YMD_HMS)).append("'");
		}
		sql.append(" order by a.NAME_ asc,a.DHNO_ asc,a.LINE_ asc");
		System.out.println("sql:\n" + sql.toString());
		return jt.query(sql.toString(), new RowMapper<DhuoChmxVO>() {

			@Override
			public DhuoChmxVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DhuoChmxVO vo = new DhuoChmxVO();
				vo.setName(rs.getString(1));
				vo.setDhno(rs.getString(2));
				vo.setLine(rs.getString(3));
				vo.setPzno(rs.getString(4));
				vo.setCdnm(rs.getString(5));
				vo.setGgno(rs.getString(6));
				vo.setYuny(rs.getString(7));
				vo.setFudw(rs.getString(8));
				vo.setFuzm(rs.getString(9));
				vo.setFufm(rs.getString(10));
				vo.setHouu(rs.getDouble(11));
				vo.setKuan(rs.getDouble(12));
				vo.setCang(rs.getDouble(13));
				vo.setQixn(rs.getInt(14));
				vo.setDdnm(rs.getString(15));
				vo.setFace(rs.getString(16));
				vo.setHtdj(rs.getDouble(17));
				vo.setHtzl(rs.getDouble(18));
				vo.setChzl(rs.getDouble(19));
				vo.setCxzl(rs.getDouble(20));
				return vo;
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
