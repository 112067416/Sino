package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.orm.Zng2Tp;

/**
 * <p>
 * 装箱指示书2数据访问接口
 * </p>
 * <p>
 * create: 2010-12-30 上午11:28:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IZng2DAO {

	/**
	 * <p>
	 * 新增装箱指示书Zng2Tp
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Zng2Tp entity);

	/**
	 * <p>
	 * 批量新增装箱指示书Zng2Tp
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<Zng2Tp> entities);

	/**
	 * <p>
	 * 更新装箱批示书
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Zng2Tp entity);

	/**
	 * <p>
	 * 查询装箱批示书
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Zng2Tp> qentity);

	/**
	 * <p>
	 * 获取装箱批示书明细
	 * </p>
	 * 
	 * @param id
	 * @return Zng2Tp
	 */
	public Zng2Tp get(Serializable id);

	/**
	 * <p>
	 * 获取装箱批示书明细
	 * </p>
	 * 
	 * @param jbno
	 * @param zxno
	 * @return Zng2Tp
	 */
	public Zng2Tp get(String jbno, String zxno);

	/**
	 * <p>
	 * 查询装箱指示明细
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Zng2Tp]
	 */
	public List<Zng2Tp> findZxzsMx(String zxno);

	/**
	 * <p>
	 * 查询装箱指示明细
	 * </p>
	 * 
	 * @param zxno
	 * @param dhno
	 * @param line
	 * @return List<Zng2Tp>
	 */
	public List<Zng2Tp> findZxzsMx(String zxno, String dhno, String line);

	/**
	 * <p>
	 * 获取包装清单打印数据
	 * </p>
	 * 
	 * @param jbnos
	 * @param zxno
	 * @return List[PbzqdVO]
	 */
	public List<PbzqdVO> findBzqdVos(List<String> jbnos, String zxno);

	/**
	 * <p>
	 * 根据订货号和制品号，查询对应的装箱指示书NO.
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param jbno
	 * @return List<String>
	 */
	public List<String> queryZxnos(String dhno, String line, String jbno);

	/**
	 * <p>
	 * 设置车牌号
	 * </p>
	 * 
	 * @param cpno
	 * @param jbnos
	 */
	public void setCpno(String cpno, List<String> jbnos);

	/**
	 * <p>
	 * 获得送货单的总张数
	 * </p>
	 * 
	 * @param zxno
	 * @return long
	 */
	public long getHjzs(String zxno);

}
