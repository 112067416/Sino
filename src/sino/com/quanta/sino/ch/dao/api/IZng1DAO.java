package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.vo.CdnmVO;
import com.quanta.sino.ch.vo.ChntjVO;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.CjpVO;
import com.quanta.sino.ch.vo.CxZxzsVO;
import com.quanta.sino.ch.vo.QuarterVO;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.yygl.vo.CondVO;

/**
 * <p>
 * 装箱指示书1数据访问接口
 * </p>
 * <p>
 * create: 2010-12-30 上午11:28:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IZng1DAO {

	/**
	 * <p>
	 * 新增装箱指示书Zng1Tp
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Zng1Tp entity);

	/**
	 * <p>
	 * 更新装箱批示书
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Zng1Tp entity);

	/**
	 * <p>
	 * 查询装箱批示书
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Zng1Tp> qentity);

	/**
	 * <p>
	 * 获取装箱批示书
	 * </p>
	 * 
	 * @param id
	 * @return Zng1Tp
	 */
	public Zng1Tp get(Serializable id);

	/**
	 * <p>
	 * 修改客户订货单号
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param khno
	 */
	public void updateKhno(String dhno, String line, String khno);

	/**
	 * <p>
	 * 获取装箱指示书列表
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Zng1Tp]
	 */
	public List<Zng1Tp> find(String zxno);

	/**
	 * <p>
	 * 根据出货日期，统计出货件数和制品重量
	 * </p>
	 * 
	 * @param chriS
	 *            出货日期开始
	 * @param chriE
	 *            出货日期结束
	 * @return ChtjVO
	 */
	public ChtjVO getChtj(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货联络单ID，查询实际出货情况
	 * </p>
	 * <p>
	 * author：张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param chlldId
	 *            出货联络单ID
	 * @return ChtjVO
	 */
	public List<ChtjVO> findChtj(String chlldId);

	/**
	 * <p>
	 * 装箱指示书查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZxzs(QEntity<CxZxzsVO> qentity);

	/**
	 * <p>
	 * 获取装箱指示书主表
	 * </p>
	 * 
	 * @param zxno
	 * @return Zng1Tp
	 */
	public Zng1Tp getUnique(String zxno);

	/**
	 * <p>
	 * 根据出货时间段和品种，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByPzno(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货时间段和内外销，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByNwai(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货时间段和内外销，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByNwai1(Date chriS, Date chriE);

	/**
	 * <p>
	 * 按天统计，出货重量
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return List
	 */
	public List<ChtjVO> queryChtj(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货实绩统计加工作途
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return List<CondVO>
	 */
	public List<CondVO> queryCond(Date chriS, Date chriE);

	/**
	 * <p>
	 * 按年统计出货量
	 * </p>
	 * 
	 * @param chriE
	 * @return List<ChntjVO>
	 */
	public List<ChntjVO> queryChnty(Date chriE, String user);

	/**
	 * <p>
	 * 按规格、尺寸、用途统计
	 * </p>
	 * 
	 * @param chriE
	 * @return List<CdnmVO>
	 */
	public List<CdnmVO> queryCdnm(Date chriE, String user);

	/**
	 * <p>
	 * 按年统计出货量
	 * </p>
	 * 
	 * @param year
	 * @return QuarterVO
	 */
	public QuarterVO getQuarter(Integer year, String user);

	/**
	 * <p>
	 * 根据出货实绩统计加工作途
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return CondVO
	 */
	public CondVO getCond(Date chriS, Date chriE);

	public List<CjpVO> queryCjp();

}
