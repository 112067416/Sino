package com.quanta.sino.yygl.bo.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.SczsTp;

/**
 * <p>
 * * 生产指示单业务处理接口
 * </p>
 * <p>
 * create: 2011-8-4 下午03:48:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ISczsBO {

	/**
	 * 新增生产指示单
	 * 
	 * @param entity
	 */
	public void save(SczsTp entity);

	/**
	 * <p>
	 * 更新生产指示单
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(SczsTp entity);

	/**
	 * <p>
	 * 更新生产指示单
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(SczsTp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public String delete(String[] ids);

	/**
	 * 查询生产指示单
	 * 
	 * @param qentity
	 */
	public void query(QEntity<SczsTp> qentity);

	/**
	 * 获取生产指示单
	 * 
	 * @param id
	 * @return SczsTp
	 */
	public SczsTp get(Serializable id);

	/**
	 * 将获取的对象转化成JS
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取订货DB信息内容
	 * </p>
	 * 
	 * @param dhno
	 *            订货no
	 * @param line
	 *            行号
	 * @return String
	 */
	public String getDhInfo(String dhno, String line);

	/**
	 * <p>
	 * 修改状态
	 * </p>
	 * 
	 * @param stat
	 * @param ids
	 */
	public void updateStat(String stat, String[] ids);

}
