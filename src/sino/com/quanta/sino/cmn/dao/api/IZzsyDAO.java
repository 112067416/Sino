package com.quanta.sino.cmn.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 仕样主文件数据访问接口
 * </p>
 * <p>
 * create: 2011-2-12 上午10:05:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IZzsyDAO {

	/**
	 * 新增通用主文件
	 * 
	 * @param entity
	 */
	public void save(ZzsyMp entity);

	/**
	 * 更新通用主文件
	 * 
	 * @param entity
	 */
	public void update(ZzsyMp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询通用主文件
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZzsyMp> qentity);

	/**
	 * 获取通用主文件
	 * 
	 * @param id
	 * @return
	 */
	public ZzsyMp get(Serializable id);

	/**
	 * <p>
	 * 根据Key获取匹配的使用主文件
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @return ZzsyMp
	 */
	public ZzsyMp getByKey(String ql, Object... values);

	/**
	 * <p>
	 * 根据Key获取匹配的使用主文件
	 * </p>
	 * 
	 * @param ql
	 * @return ZzsyMp
	 */
	public ZzsyMp getByKey(String ql);

	/**
	 * <p>
	 * 获取仕样主文件最大的号码
	 * </p>
	 */
	public String getMaxNo();

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ql
	 * @param params
	 */
	public void executeUpdate(String ql, Object... params);

}
