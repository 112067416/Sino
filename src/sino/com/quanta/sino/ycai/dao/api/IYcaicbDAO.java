package com.quanta.sino.ycai.dao.api;

import java.io.Serializable;
import java.util.List;

import com.quanta.sino.orm.Ybcbmx;
import com.quanta.sino.orm.Ycaicb;

/**
 * <p>
 * 原板成本数据接口
 * </p>
 * <p>
 * create: 2011-1-25 下午09:21:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYcaicbDAO {
	/**
	 * <p>
	 * 读取原板成本
	 * </p>
	 * 
	 * @param id
	 * @return Ycaicb
	 */
	public Ycaicb get(Serializable id);

	/**
	 * <p>
	 * 判断原板成本是否存在
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExisted(String jbno);

	/**
	 * <p>
	 * 保存原板成本
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Ycaicb entity);

	/**
	 * <p>
	 * 批量保存原板成本
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<Ycaicb> entities);

	/**
	 * <p>
	 * 修改原板成本
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Ycaicb entity);

	/**
	 * <p>
	 * 按照船名，删除原板成本费用
	 * </p>
	 * 
	 * @param ship
	 */
	public void deleteByShip(String ship);

	/**
	 * <p>
	 * 按照船名，查询原板成本费用
	 * </p>
	 * 
	 * @param ship
	 * @return Ycaicb
	 */
	public Ybcbmx getMx(String ship);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ybcbmx
	 */
	public void saveMx(Ybcbmx ybcbmx);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ship
	 */
	public void deleteMx(String ship);

}
