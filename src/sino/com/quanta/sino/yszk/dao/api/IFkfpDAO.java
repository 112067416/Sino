package com.quanta.sino.yszk.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.yszk.vo.ChsjVO;
import com.quanta.sino.yszk.vo.HjVO;

/**
 * <p>
 * 付款发票数据访问层接口
 * </p>
 * <p>
 * create: 2011-6-27 下午08:41:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFkfpDAO {
	/**
	 * <p>
	 * 新增发票
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Fkfp entity);

	/**
	 * <p>
	 * 更新发票
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Fkfp entity);

	/**
	 * <p>
	 * 根据主键删除对应的发票
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询发票
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Fkfp> qentity);

	/**
	 * <p>
	 * 获取发票
	 * </p>
	 * 
	 * @param id
	 * @return Fkfp
	 */
	public Fkfp get(Serializable id);

	/**
	 * <p>
	 * 批量保存付款发票信息
	 * </p>
	 * 
	 * @param entitys
	 */
	public void saveAll(List<Fkfp> entities);

	/**
	 * <p>
	 * 获取同步更新的发票信息
	 * </p>
	 * 
	 * @param id
	 * @return Fkfp
	 */
	public Fkfp getRef(Serializable id);

	/**
	 * <p>
	 * 获取付款发票
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param chri
	 *            出货日
	 * @param fppz
	 *            发货品种
	 * @return Fkfp
	 */
	public Fkfp get(String dhno, String line, Date chri, String fppz);

	/**
	 * <p>
	 * 获得付款发票信息
	 * </p>
	 * 
	 * @param ids
	 * @return List<Fkfp>
	 */
	public List<Fkfp> find(String[] ids);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @param stats
	 * @return List<Fkfp>
	 */
	public List<Fkfp> find(String[] ids, String[] stats);

	/**
	 * <p>
	 * 未收货款
	 * </p>
	 * 
	 * @param user
	 * @return Double
	 */
	public Double getWsyk(String user);

	/**
	 * <p>
	 * 根据出货实绩记录，生成应收帐款发票数据
	 * </p>
	 * 
	 * @param chqiS
	 * @param chqiE
	 * @return FpVO
	 */
	public List<ChsjVO> findFpVOS(Date chqiS, Date chqiE);

	/**
	 * <p>
	 * 删除指定时间的发票数据
	 * </p>
	 * 
	 * @param chqiS
	 * @param chqiE
	 */
	public void delete(Date chqiS, Date chqiE);

	/**
	 * <p>
	 * 统计出货重量和总价
	 * </p>
	 * 
	 * @param chqiS
	 * @param chqiE
	 * @param fpymc
	 * @param nwai
	 * @param deng
	 * @param pz
	 * @param stats
	 * @param houuS
	 * @param houuE
	 * @param fppz
	 * @param fpno
	 * @return HjVO
	 */
	public HjVO getHj(Date chqiS, Date chqiE, String fpymc, String nwai,
			String deng, String pz, String stats, Double houuS, Double houuE,
			String fppz, String fpno);

	/**
	 * <p>
	 * 设置发票号
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 */
	public void setFpno(List<String> ids, String fpno);

}
