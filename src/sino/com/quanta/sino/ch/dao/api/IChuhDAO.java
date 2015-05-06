package com.quanta.sino.ch.dao.api;

import java.util.Date;
import java.util.List;

import com.quanta.sino.orm.ChuhLp;

/**
 * <p>
 * 出货实绩数据访问接口
 * </p>
 * <p>
 * create: 2011-1-23 下午02:21:18
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IChuhDAO {

	/**
	 * <p>
	 * 新增出货实绩
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(ChuhLp entity);

	/**
	 * <p>
	 * 新增出货实绩
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<ChuhLp> entities);

	/**
	 * <p>
	 * 更新出货实绩
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(ChuhLp entity);

	/**
	 * <p>
	 * 根据出货实绩主键删除对应的信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * <p>
	 * 获取出货实绩
	 * </p>
	 * 
	 * @param id
	 * @return ChuhLp
	 */
	public ChuhLp get(String id);

	/**
	 * <p>
	 * 获取出货日志
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param today
	 * @return ChuhLp
	 */
	public ChuhLp getChuhLp(String dhno, String line, Date today);

	/**
	 * <p>
	 * 修改或保存出货日志
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(ChuhLp entity);

}
