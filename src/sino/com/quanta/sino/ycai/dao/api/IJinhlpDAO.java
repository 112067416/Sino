package com.quanta.sino.ycai.dao.api;

import java.io.Serializable;

import com.quanta.sino.orm.JinhLp;

/**
 * <p>
 * 原材卷板进货日志表数据访问接口
 * </p>
 * <p>
 * create: 2011-1-12 下午01:56:16
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IJinhlpDAO {
	/**
	 * <p>
	 * 读取进货日志
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public JinhLp get(Serializable id);

	/**
	 * <p>
	 * 保存进货日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(JinhLp entity);

	/**
	 * <p>
	 * 修改进货日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(JinhLp entity);

}
