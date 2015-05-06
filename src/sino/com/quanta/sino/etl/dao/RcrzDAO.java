package com.quanta.sino.etl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.coco.core.persistence.api.DAO;
import com.coco.core.util.DateUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.ESczt;
import com.quanta.sino.dh.constants.DhFzlDw;
import com.quanta.sino.etl.dao.api.IRcrzDAO;
import com.quanta.sino.etl.vo.YxRecordVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Rcrz;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdhTp;

/**
 * <p>
 * 入侧日志
 * </p>
 * <p>
 * create: 2011-1-26 下午06:38:27
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class RcrzDAO implements IRcrzDAO {

	private DAO dao;

	private JdbcTemplate pc6000Jt;

	private ICodeBO codeBO;

	@Override
	public void save(Rcrz entity) {
		dao.save(entity);
	}

	@Override
	public boolean sendSave(String jbno, YcaiTp ycai, ZsdhTp zsdh, DhuoTp dhuo) {

		try {
			// jdbc = (JdbcTemplate) Helper.getBean("pc6000JdbcTemplate");
			// 判断中间库是否存在备用卷
			String sql = "select * from jcb where  process_flag=0";
			List<Map<String, Object>> datas = pc6000Jt.queryForList(sql);
			if (datas.size() > 0) {
				// 删除备用卷
				sql = "delete from jcb where process_flag=0";
				pc6000Jt.execute(sql);
			}
			// 插入备用卷
			StringBuilder bm = new StringBuilder();
			bm.append("Insert jcb(");
			bm.append("CAOZ_ ,DHFZ_ ,FACE_ ,FLOW_ ,FUZM_ ,FUFM_ ,HXCL_ ,JBCN_ ,JBNO_ ,KBZS_ ,KUAN_");
			bm.append(",MDAN_ ,NJNO_ ,XPHO_ ,XPKN_ ,YQTY_ ,ZRKN_ ,ZZSD_ ,finished_flag ,process_flag");
			bm.append(",finished_time ,process_time ,sys_time,trim,YUNY_)values(");
			// 操作
			if (zsdh.getCaoz() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + zsdh.getCaoz() + "',");
			}
			// 订货附着量略称
			if (zsdh.getDhfz() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + zsdh.getDhfz() + "',");
			}
			// 表面精度
			if (zsdh.getFace() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + zsdh.getFace() + "',");
			}
			// 是否软溶
			if (zsdh.getFlow() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + zsdh.getFlow() + "',");
			}
			// 订货DB附着量正
			CodeItem codeItem = null;
			DhFzlDw edw = DhFzlDw.get(dhuo.getFudw());
			if (dhuo.getFuzm() == null) {
				bm.append("null,");
			}
			else {
				if (edw != null && DhFzlDw.FZLDW_GM.stat.equals(edw.stat)) {
					codeItem = codeBO.getCodeItemByFudw(CmnConstants.CODE_024, edw.stat
							+ dhuo.getFuzm(), DhFzlDw.FZLDW_WB.stat);
					if (codeItem != null) {
						bm.append("'").append(codeItem.getKey().replaceFirst(DhFzlDw.FZLDW_WB.stat, "")).append("',");
					}
				}
				else {
					bm.append("'").append(dhuo.getFuzm()).append("',");
				}
			}
			// 订货DB附着量反
			if (dhuo.getFufm() == null) {
				bm.append("null,");
			}
			else {
				if (edw != null && DhFzlDw.FZLDW_GM.stat.equals(edw.stat)) {
					codeItem = codeBO.getCodeItemByFudw(CmnConstants.CODE_024, edw.stat
							+ dhuo.getFufm(), DhFzlDw.FZLDW_WB.stat);
					if (codeItem != null) {
						bm.append("'").append(codeItem.getKey().replaceFirst(DhFzlDw.FZLDW_WB.stat, "")).append("',");
					}
				}
				else {
					bm.append("'").append(dhuo.getFufm()).append("',");
				}

			}
			// 化学处理
			if (dhuo.getHuac() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + dhuo.getHuac() + "',");
			}
			// 卷板长
			if (ycai.getJbcn() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + ycai.getJbcn() + ",");
			}
			// 卷板号
			if (ycai.getJbno() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + ycai.getJbno() + "',");
			}
			// 捆包上限
			if (dhuo.getKbzs() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + dhuo.getKbzs() + ",");
			}
			// 制品.宽
			if (dhuo.getKuan() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + dhuo.getKuan() + ",");
			}
			// M单重
			if (zsdh.getMdan() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + zsdh.getMdan() + ",");
			}
			// 内径
			if (ycai.getNjno() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + ycai.getNjno() + "',");
			}
			// 现品.厚
			if (ycai.getXpho() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + ycai.getXpho() + ",");
			}
			// 现品.长
			if (ycai.getXpkn() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + ycai.getXpkn() + ",");
			}
			// 目标涂油量
			if (zsdh.getYqty() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + zsdh.getYqty() + "',");
			}
			// 装入宽
			if (zsdh.getZrkn() == null) {
				bm.append("null,");
			}
			else {
				bm.append("" + zsdh.getZrkn() + ",");
			}
			// 制品商代码
			if (ycai.getZzsd() == null) {
				bm.append("null,");
			}
			else {
				bm.append("'" + ycai.getZzsd() + "',");
			}

			bm.append("0,");
			bm.append("0,");
			bm.append("null,");
			bm.append("null,");
			bm.append("getdate(),");
			// 剪边标志
			if (zsdh.getJbbj() == null) {
				bm.append("0,");
			}
			else {
				bm.append("" + zsdh.getJbbj() + ",");
			}
			// 运用规格
			if (ycai.getYuny() == null) {
				bm.append("null)");
			}
			else {
				bm.append("'" + ycai.getYuny() + "')");
			}
			pc6000Jt.execute(bm.toString());
			return true;

		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}

	}

	@Override
	public boolean sendDelete(String jbno) {

		try {
			// jdbc = (JdbcTemplate) Helper.getBean("zjjdbcTemplate");
			String sql = "delete from jcb where jbno_='" + jbno + "'";
			pc6000Jt.execute(sql);
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean getByStat(Integer varId) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select var_value from var_table where var_id= " + varId);
			return (Boolean) pc6000Jt.query(sql.toString(), new ResultSetExtractor() {
				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					while (rs.next()) {
						boolean pf = rs.getBoolean(1);
						return pf;
					}
					return true;
				}

			});

		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getSczt(String jbno) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select process_flag from jcb where JBNO_='");
			sql.append(jbno).append("'");
			return (String) pc6000Jt.query(sql.toString(), new ResultSetExtractor() {

				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					String sczt = ESczt.KEY_WCJ;
					while (rs.next()) {
						boolean pf = rs.getBoolean(1);
						if (pf) {
							sczt = ESczt.KEY_DQJ;
						}
						else {
							sczt = ESczt.KEY_BYJ;
						}
					}
					return sczt;
				}

			});
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ESczt.KEY_BYJ;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean isExitBy() {
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("select process_flag from jcb ");
			return (Boolean) pc6000Jt.query(sql.toString(), new ResultSetExtractor() {
				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					boolean rel = true;
					int i = 0;
					while (rs.next()) {
						boolean pf = rs.getBoolean(1);
						if (!pf) {
							rel = pf;
						}
						i++;
					}
					if (i > 0) {
						return rel;
					}
					else {
						return true;
					}

				}

			});

		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Override
	public List<YxRecordVO> queryYxRecords(Date begin, Date end) {
		return pc6000Jt.query("select * from yx_record where systime > ? and systime < ? order by var_id asc", new Object[] {
				begin, end }, new RowMapper<YxRecordVO>() {

			@Override
			public YxRecordVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				YxRecordVO vo = new YxRecordVO();
				vo.setVarId(rs.getLong(1));
				vo.setVarName(rs.getString(2));
				vo.setVarValue(rs.getString(3));
				vo.setSystime(DateUtils.parse(rs.getTimestamp(4), DateUtils.YMD_HMS));
				return vo;
			}

		});
	}

	@Override
	public List<YxRecordVO> queryYxRecords(Date begin, Date end, String orderbys) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from yx_record where systime > ? and systime < ? ");
		if (orderbys != null && !orderbys.isEmpty()) {
			sql.append(orderbys);
		}
		return pc6000Jt.query(sql.toString(), new Object[] { begin, end }, new RowMapper<YxRecordVO>() {

			@Override
			public YxRecordVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				YxRecordVO vo = new YxRecordVO();
				vo.setVarId(rs.getLong(1));
				vo.setVarName(rs.getString(2));
				vo.setVarValue(rs.getString(3));
				vo.setSystime(DateUtils.parse(rs.getTimestamp(4), DateUtils.YMD_HMS));
				return vo;
			}

		});
	}

	@Override
	public YxRecordVO getYxRecord(Date systime, String varValue) {

		return pc6000Jt.queryForObject("select top 1 * from yx_record where systime < ? and var_value=? order by systime desc", new Object[] {
				systime, varValue }, new RowMapper<YxRecordVO>() {

			@Override
			public YxRecordVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				YxRecordVO vo = new YxRecordVO();
				vo.setVarId(rs.getLong(1));
				vo.setVarName(rs.getString(2));
				vo.setVarValue(rs.getString(3));
				vo.setSystime(rs.getDate(4));
				return vo;
			}

		});
	}

	@Override
	public YxRecordVO getYxRecord(Date systime) {

		return pc6000Jt.queryForObject("select top 1 * from yx_record where systime < ? order by systime desc", new Object[] { systime }, new RowMapper<YxRecordVO>() {

			@Override
			public YxRecordVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				YxRecordVO vo = new YxRecordVO();
				vo.setVarId(rs.getLong(1));
				vo.setVarName(rs.getString(2));
				vo.setVarValue(rs.getString(3));
				vo.setSystime(rs.getDate(4));
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

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public JdbcTemplate getPc6000Jt() {
		return pc6000Jt;
	}

	public void setPc6000Jt(JdbcTemplate pc6000Jt) {
		this.pc6000Jt = pc6000Jt;
	}

}
