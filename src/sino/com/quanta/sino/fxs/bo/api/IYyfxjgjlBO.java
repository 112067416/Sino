package com.quanta.sino.fxs.bo.api;

import java.io.Serializable;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YyfxjgJl;

/**
 * <p>
 * 药液分析结果记录表
 * </p>
 * <p>
 * create: 2011-2-15 下午04:27:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYyfxjgjlBO {

	/**
	 * <p>
	 * 新增药液分析结果记录表
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 */
	public void save(YyfxjgJl entity, User user);

	/**
	 * <p>
	 * 更新药液分析结果记录表
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 */
	public void update(YyfxjgJl entity, User user);

	/**
	 * 根据ID删除药液分析结果记录表
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询药液分析结果记录表
	 * 在查询界面中碱浸液（Free）、碱浸液（Total）、碱电解液（Free）、碱电解液（Total）、碱电解液（H2SO4）、
	 * 锡电镀液（Sn）、锡电镀液（Acid）、拖出液（Sn）、拖出液（Acid），取的分别为查看详情里面对应的平均值
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YyfxjgJl> qentity);

	/**
	 * 获取药液分析结果记录表
	 * 
	 * @param id
	 * @return YyfxjgJl
	 */
	public YyfxjgJl get(Serializable id);

	/**
	 * <p>
	 * 删除 药液分析结果记录表
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(String[] ids);
}
