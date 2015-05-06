package com.quanta.sino.etl.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.etl.dao.api.IEtlDAO;
import com.quanta.sino.etl.vo.EtlBanTjVO;
import com.quanta.sino.etl.vo.EtlSbTjVO;
import com.quanta.sino.etl.vo.XzVO;
import com.quanta.sino.etl.vo.ZpSLVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ETLStopTp;
import com.quanta.sino.orm.ETLVariTp;
import com.quanta.sino.orm.ETLsbTp;
import com.quanta.sino.orm.EtlsLp;
import com.quanta.sino.orm.PngzLp;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZszrTp;

/**
 * <p>
 * 实绩录入数据访问层
 * </p>
 * <p>
 * create: 2011-1-21 下午12:07:13
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlDAO implements IEtlDAO {

	private DAO dao;

	private JdbcTemplate jt;

	@Override
	public void saveETLStopTp(ETLStopTp entity) {
		dao.save(entity);
	}

	@Override
	public void updateETLStopTp(ETLStopTp entity) {
		dao.update(entity);
	}

	@Override
	public void saveETLVariTp(ETLVariTp entity) {
		dao.save(entity);
	}

	@Override
	public void updateETLVariTp(ETLVariTp entity) {
		dao.update(entity);
	}

	@Override
	public void saveETLsbTp(ETLsbTp entity) {
		dao.save(entity);
	}

	@Override
	public Double getStopTime(Date dasr, String ban) {
		String ql = "select isnull(sum(xzda),0)+isnull(sum(xzdj),0)+isnull(sum(xzjx),0)+isnull(sum(xzzy),0) from ETLStopTp where dasr=? and ban=?";
		Object obj = (Object) dao.getUnique(ql, dasr, ban);
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;
	}

	@Override
	public Double getETLStopTpbydasr(Date dasr) {
		String ql = "select isnull(sum(xzda),0)+isnull(sum(xzdj),0)+isnull(sum(xzjx),0)+isnull(sum(xzzy),0) from ETLStopTp where dasr=?";
		Object obj = (Object) dao.getUnique(ql, dasr);
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;
	}

	@Override
	public Double getETLStopTpbydasr(Date firstdate, Date enddate) {
		String ql = "select isnull(sum(xzda),0)+isnull(sum(xzdj),0)+isnull(sum(xzjx),0)+isnull(sum(xzzy),0) from ETLStopTp where dasr>=? and dasr<=?";
		Object obj = (Object) dao.getUnique(ql, firstdate, enddate);
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;
	}

	@Override
	public Double getETLStopXzdaTpbydasr(Date firstdate, Date enddate) {
		String ql = "select isnull(sum(xzda),0) from ETLStopTp where dasr>=? and dasr<=?";
		Object obj = (Object) dao.getUnique(ql, firstdate, enddate);
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;
	}

	@Override
	public ETLStopTp getETLStopTp(Date dasr, String stop) {
		String ql = "from ETLStopTp where dasr=? and stop=?";
		return (ETLStopTp) dao.getUnique(ql, dasr, stop);
	}

	@Override
	public ETLVariTp getETLVariTp(Date dasr, String ban) {
		return (ETLVariTp) dao.getUnique("from ETLVariTp where dasr=? and ban=?", dasr, ban);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ETLVariTp> findETLVariTps(Date dasr) {
		return (List<ETLVariTp>) dao.query("from ETLVariTp where dasr >= ? and dasr < ? order by ban asc", dasr, DateUtils.add(dasr, Calendar.DAY_OF_MONTH, 1));
	}

	@Override
	public ETLStopTp getETLStopTp(Serializable id) {
		return dao.get(ETLStopTp.class, id);
	}

	@Override
	public void deleteETLStopTp(Serializable id) {
		dao.delete(ETLStopTp.class, id);
	}

	@Override
	public ETLsbTp getETLsbTp(Serializable id) {
		return dao.get(ETLsbTp.class, id);
	}

	@Override
	public EtlSbTjVO getSbTjVO(Date begin, Date end) {
		StringBuilder ql = new StringBuilder(
				"select sum(jszl),sum(gzda),sum(sdsj),sum(xzda),sum(pass) from ETLsbTp where dasr >= ? and dasr <= ?");
		Object[] objs = (Object[]) dao.getUnique(ql.toString(), begin, end);
		EtlSbTjVO sbTjVO = new EtlSbTjVO();
		if (objs == null || objs.length == 0) {
			return sbTjVO;
		}
		sbTjVO.setJszl((Double) objs[0]);
		sbTjVO.setGzda((Double) objs[1]);
		sbTjVO.setSdsj((Double) objs[2]);
		sbTjVO.setXzda((Double) objs[3]);
		sbTjVO.setPass((Double) objs[4]);
		return sbTjVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ETLsbTp> findETLsbTps(Date begin, Date end) {
		return (List<ETLsbTp>) dao.query("from ETLsbTp where dasr>=? and dasr<=? order by dasr asc", begin, end);
		// return dao.find(ETLsbTp.class, " dasr>=? and dasr<=?", begin, end);
	}

	@Override
	public boolean isExistedEtlsb(Date dasr) {
		return dao.isExisted("from ETLsbTp where dasr=?", dasr);
	}

	@Override
	public List<ETLStopTp> finEtlStops(Date date, String ban) {
		return dao.find(ETLStopTp.class, "dasr=? and ban=? order by crea asc", date, ban);
	}

	@Override
	public XzVO getXzVO(Date begin, Date end) {
		StringBuilder ql = new StringBuilder(
				"select sum(xzda),sum(xzdj),sum(xzjx),sum(xzzy) from ETLStopTp where dasr >= ? and dasr <= ?");
		Object[] objs = (Object[]) dao.getUnique(ql.toString(), begin, end);
		XzVO xzVO = new XzVO();
		if (objs == null || objs.length == 0) {
			return xzVO;
		}
		xzVO.setXzda((Double) objs[0]);
		xzVO.setXzdj((Double) objs[1]);
		xzVO.setXzjx((Double) objs[2]);
		xzVO.setXzzy((Double) objs[3]);
		return xzVO;
	}

	@Override
	public List<ETLStopTp> findETLStopTps(Date date) {
		return dao.find(ETLStopTp.class, "dasr=? order by crea asc", date);
	}

	@Override
	public void updateETLsbTp(ETLsbTp entity) {
		dao.update(entity);
	}

	@Override
	public ZszrTp getZszrTp(Serializable id) {
		return dao.get(ZszrTp.class, id);
	}

	@Override
	public void saveEtlsLp(EtlsLp entity) {
		dao.save(entity);
	}

	// @Override
	// public void updateYcaiTp(Serializable id, String qdbj, String scbj,
	// String stat) {
	// YcaiTp ycai = dao.getRef(YcaiTp.class, id);
	// ycai.setQdbj(qdbj);
	// ycai.setScbj(scbj);
	// ycai.setStat(stat);
	// }

	@Override
	public void updateZsdhTp(Serializable id, String zsbj, String zlzz,
			Date zsny) {
		ZsdhTp zsdh = dao.getRef(ZsdhTp.class, id);
		zsdh.setZsbj(zsbj);
		zsdh.setZlzz(zlzz);
		zsdh.setZsny(zsny);
	}

	@Override
	public void updateDhuoTp(String dhno, String line, Double etlh) {
		DhuoTp dhuo = dao.getRef(DhuoTp.class, new DhuoTpPk(dhno, line));
		Double dhuoEtlh = dhuo.getEtlh();
		dhuoEtlh = etlh + dhuoEtlh;
		dhuo.setEtlh(dhuoEtlh);
		dhuo.setUpda(new Date());
	}

	@Override
	public void updateDhFp(String dhno, String line, Double yclh) {
		DhuoTp dhuo = dao.getRef(DhuoTp.class, new DhuoTpPk(dhno, line));
		Double dhuoFpln = dhuo.getFpln();
		dhuoFpln = dhuoFpln - yclh;
		dhuo.setFpln(dhuoFpln);
	}

	@Override
	public ZszrTp getZszrTpRef(Serializable id) {
		return dao.getRef(ZszrTp.class, id);
	}

	@Override
	public ZpngTp getZpRef(Serializable id) {
		return dao.getRef(ZpngTp.class, id);
	}

	@Override
	public void saveZscdTp(ZscdTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveZsdxTp(ZsdxTp entity) {
		dao.save(entity);
	}

	@Override
	public void deleteZscdTp(Serializable id) {
		dao.delete(ZscdTp.class, id);
	}

	@Override
	public void deleteRiziLp(Serializable id) {
		dao.delete(RiziLp.class, id);
	}

	@Override
	public void saveRiziLp(RiziLp entity) {
		dao.save(entity);
	}

	@Override
	public Integer getMaxCkno(String dhnoAndLine) {
		// String ql = "select max(ckno) from " + ZpngTp.class.getSimpleName()
		// + " where dhno=?";
		Integer i = (Integer) dao.getUnique("select max(ckno) from ZpngTp where dhno=?", dhnoAndLine);
		if (i == null) {
			return 0;
		}
		return i;
	}

	@Override
	public void updateAllZp(String rczpno, Double sczm, Double scfm) {
		// String ql = "update " + ZpngTp.class.getSimpleName()
		// + " set sczm=?,scfm=?  where rczpno=?";
		dao.executeUpdate("update ZpngTp set sczm=?,scfm=? where rczpno=?", sczm, scfm, rczpno);
	}

	@Override
	public void query(QEntity<ZpngTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void querySL(QEntity<ZpSLVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<PngzLp> getPngzLp(Serializable id) {
		return dao.find(PngzLp.class, "zrjb = ?", id);
	}

	@Override
	public void savePngzLp(PngzLp entity) {
		dao.save(entity);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, EtlBanTjVO> findBanTjVO(Date scsjBegin, Date scsjEnd) {
		StringBuilder ql = new StringBuilder(
				"select d.BAN_,d.ZU_,d.RCZPNO_,d.CHAN_,d.XPZK_,d.LX_,d.ZRZL_,d.ZPZL_,d.PZNO_ from (select b.BAN_,b.ZU_,b.RCZPNO_,b.CHAN_,b.XPZK_,c.LX_,c.ZRZL_,sum(b.ZPZL_) as ZPZL_,c.PZNO_ from (select a.JBNO_,a.ZRZL_,SUBSTRING(a.DHNO_,1,1) as LX_,e.PZNO_ from SINO_YCAITP a left join SINO_DHUOTP e on a.DHNO_=e.DHNO_+e.LINE_ where a.SJSJ_>=? and a.SJSJ_<?) c left join SINO_ZPNGTP b on b.RCZPNO_= c.JBNO_ group by b.BAN_,b.ZU_,c.PZNO_,b.RCZPNO_,b.CHAN_,b.XPZK_,c.LX_,c.ZRZL_) d order by d.RCZPNO_ asc,d.BAN_ desc");
		return (Map<String, EtlBanTjVO>) jt.query(ql.toString(), new Object[] {
				scsjBegin, scsjEnd }, new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				EtlBanTjVO vo = null;
				Map<String, EtlBanTjVO> vos = new HashMap<String, EtlBanTjVO>();
				String ban = null, zu = null, rczpno = null, lx = null, chan = null, $ban = null, pzno = null;
				boolean isCoil;
				Integer zrzl, zpzl;
				double $zrzl, $zpzl;
				while (rs.next()) {
					ban = rs.getString(1);
					zu = rs.getString(2);
					chan = rs.getString(4);
					// xpzk = rs.getString(5);
					lx = rs.getString(6);
					zrzl = rs.getInt(7);
					zpzl = rs.getInt(8);
					pzno = rs.getString(9);
					isCoil = Code118.coil.key.equals(pzno.substring(0, 1));

					$zrzl = NumberUtils.format(zrzl / 1000d, 3);
					$zpzl = NumberUtils.format(zpzl / 1000d, 3);

					if ((vo = vos.get(ban)) == null) {
						vo = new EtlBanTjVO();
						vo.setZu(zu);
						vos.put(ban, vo);
					}
					// 当在两个班在交接班的时候，生产线上还有一个卷。则这个卷的装入量和产出量，只能归于一个班
					if (rczpno == null || rczpno.isEmpty()
							|| !rczpno.equals(rs.getString(3))) {
						if ("T".equals(lx)) {
							vo.setZrzlOR(NumberUtils.format(vo.getZrzlOR()
									+ $zrzl, 3));
						}
						else if (!isCoil) {
							// else if (EXpzk.ZJP_KEY.equals(xpzk)) {
							vo.setZrzlS(NumberUtils.format(vo.getZrzlS()
									+ $zrzl, 3));
						}
						// else if (EXpzk.JZP_KEY.equals(xpzk)) {
						else if (isCoil) {
							vo.setZrzlC(NumberUtils.format(vo.getZrzlC()
									+ $zrzl, 3));
						}
						vo.setZrzlAll(NumberUtils.format(vo.getZrzlAll()
								+ $zrzl, 3));
					}
					else {
						vo = vos.get($ban);
					}
					if ((ChanType.hg.key.equals(chan) || ChanType.bl.key.equals(chan))
							&& !isCoil) {
						// && EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setHgzpzlS(NumberUtils.format(vo.getHgzpzlS()
								+ $zpzl, 3));
					}
					else if ((ChanType.hg.key.equals(chan) || ChanType.bl.key.equals(chan))
							&& isCoil) {
						// && EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setHgzpzlC(NumberUtils.format(vo.getHgzpzlC()
								+ $zpzl, 3));
					}
					else if (ChanType.fs.key.equals(chan) && !isCoil) {
						// && EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setFszpzlS(NumberUtils.format(vo.getFszpzlS()
								+ $zpzl, 3));
					}
					else if (ChanType.fs.key.equals(chan) && isCoil) {
						// && EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setFszpzlC(NumberUtils.format(vo.getFszpzlC()
								+ $zpzl, 3));
					}
					else if (ChanType.fc.key.equals(chan) && !isCoil) {
						// && EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setFczpzlS(NumberUtils.format(vo.getFczpzlS()
								+ $zpzl, 3));
					}
					else if (ChanType.fc.key.equals(chan) && isCoil) {
						// && EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setFczpzlC(NumberUtils.format(vo.getFczpzlC()
								+ $zpzl, 3));
					}
					vo.setZpzl(NumberUtils.format(vo.getZpzl() + $zpzl, 3));
					rczpno = rs.getString(3);
					$ban = ban;
				}

				return vos;
			}

		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EtlBanTjVO getTjVO(Date scsjBegin, Date scsjEnd) {
		StringBuilder ql = new StringBuilder(
				"select d.RCZPNO_,d.CHAN_,d.XPZK_,d.LX_,d.ZRZL_,d.ZPZL_ from (select b.RCZPNO_,b.CHAN_,b.XPZK_,c.LX_,c.ZRZL_,sum(b.ZPZL_) as ZPZL_ from (select a.JBNO_,a.ZRZL_,SUBSTRING(a.DHNO_,1,1) as LX_ from SINO_YCAITP a where a.SJSJ_>=? and a.SJSJ_<?) c left join SINO_ZPNGTP b on b.RCZPNO_= c.JBNO_ group by b.RCZPNO_,b.CHAN_,b.XPZK_,c.LX_,c.ZRZL_) d order by d.RCZPNO_ asc");
		return (EtlBanTjVO) jt.query(ql.toString(), new Object[] { scsjBegin,
				scsjEnd }, new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				EtlBanTjVO vo = new EtlBanTjVO();
				String rczpno = null, xpzk = null, lx = null, chan = null;
				Integer zrzl, zpzl;
				double $zrzl, $zpzl;
				while (rs.next()) {
					chan = rs.getString(2);
					xpzk = rs.getString(3);
					lx = rs.getString(4);
					zrzl = rs.getInt(5);
					zpzl = rs.getInt(6);
					$zrzl = NumberUtils.format(zrzl / 1000d, 3);
					$zpzl = NumberUtils.format(zpzl / 1000d, 3);

					if (rczpno == null || rczpno.isEmpty()
							|| !rczpno.equals(rs.getString(1))) {
						if ("T".equals(lx)) {
							vo.setZrzlOR(vo.getZrzlOR() + $zrzl);
						}
						else if (EXpzk.ZJP_KEY.equals(xpzk)) {
							vo.setZrzlS(vo.getZrzlS() + $zrzl);
						}
						else if (EXpzk.JZP_KEY.equals(xpzk)) {
							vo.setZrzlC(vo.getZrzlC() + $zrzl);
						}
						vo.setZrzlAll(vo.getZrzlAll() + $zrzl);
					}
					if ((ChanType.hg.key.equals(chan) || ChanType.bl.key.equals(chan))
							&& EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setHgzpzlS(vo.getHgzpzlS() + $zpzl);
					}
					else if ((ChanType.hg.key.equals(chan) || ChanType.bl.key.equals(chan))
							&& EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setHgzpzlC(vo.getHgzpzlC() + $zpzl);
					}
					else if (ChanType.fs.key.equals(chan)
							&& EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setFszpzlS(vo.getFszpzlS() + $zpzl);
					}
					else if (ChanType.fs.key.equals(chan)
							&& EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setFszpzlC(vo.getFszpzlC() + $zpzl);
					}
					else if (ChanType.fc.key.equals(chan)
							&& EXpzk.ZJP_KEY.equals(xpzk)) {
						vo.setFczpzlS(vo.getFczpzlS() + $zpzl);
					}
					else if (ChanType.fc.key.equals(chan)
							&& EXpzk.JZP_KEY.equals(xpzk)) {
						vo.setFczpzlC(vo.getFczpzlC() + $zpzl);
					}
					vo.setZpzl(vo.getZpzl() + $zpzl);
					rczpno = rs.getString(1);
				}

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
