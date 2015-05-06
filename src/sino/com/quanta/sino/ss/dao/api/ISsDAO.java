package com.quanta.sino.ss.dao.api;

import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Hbbjl;
import com.quanta.sino.orm.PlscItemLp;
import com.quanta.sino.orm.PlscLp;
import com.quanta.sino.orm.PlxmLp;
import com.quanta.sino.ss.vo.SlsjVO;
import com.quanta.sino.ss.vo.SsxpVO;

/**
 * <p>
 * SS分选数据访问接口
 * </p>
 * <p>
 * create: 2011-1-20 下午05:35:37
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISsDAO {
	/**
	 * <p>
	 * 查询日志
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryRZ(QEntity<SsxpVO> qentity);

	/**
	 * <p>
	 * 保存中间包日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void savePlscItem(PlscItemLp entity);

	/**
	 * <p>
	 * 根据分选日志查询中间包日志
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public List<PlscItemLp> findPlscItemLp(String id);

	/**
	 * <p>
	 * 是否存在消灭的中间包
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public boolean isExisted(String jbno);

	/**
	 * <p>
	 * 保存SS生产实绩
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(PlscLp entity);

	/**
	 * <p>
	 * 保存合并包记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Hbbjl entity);

	/**
	 * <p>
	 * 保存SS消灭实绩
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(PlxmLp entity);

	/**
	 * <p>
	 * 根据新包号获取来源包列表
	 * </p>
	 * 
	 * @param jbno
	 * @return List<Hbbjl>
	 */
	public List<Hbbjl> findHbbjl(String jbno);

	/**
	 * <p>
	 * 查询一个包的合包记录
	 * </p>
	 * 
	 * @param jbno
	 * @return List<String>
	 */
	public List<String> findPlsc(String jbno);

	/**
	 * <p>
	 * 根据新包号和中间包号查询中间包号
	 * </p>
	 * 
	 * @param xbh
	 * @param zjbh
	 * @return
	 */
	public Hbbjl getUnique(String xbh, String zjbh);

	/**
	 * <p>
	 * 删除中间包号
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteHbbjl(String id);

	/**
	 * <p>
	 * 查询新包
	 * </p>
	 * 
	 * @param page
	 */
	public void query(QEntity<SlsjVO> page);

	/**
	 * <p>
	 * 根据新包删除中间包
	 * </p>
	 * 
	 * @param jbno
	 */
	public void deletebyxbh(String jbno);

}
