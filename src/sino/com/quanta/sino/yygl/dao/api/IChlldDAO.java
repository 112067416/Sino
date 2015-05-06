package com.quanta.sino.yygl.dao.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Chlld;

/**
 * <p>
 * 出货联络单业务处理接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:38:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IChlldDAO {

	/**
	 * 新增出货联络单
	 * 
	 * @param entity
	 */
	public void save(Chlld entity);

	/**
	 * 更新出货联络单
	 * 
	 * @param entity
	 */
	public void update(Chlld entity);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delete(List<String> ids);

	/**
	 * 查询出货联络单
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Chlld> qentity);

	/**
	 * 获取出货联络单
	 * 
	 * @param id
	 * @return Chlld
	 */
	public Chlld get(Serializable id);

	/**
	 * <p>
	 * 修改次日出货联络单信息
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @param params
	 */
	public void executeForValues(String ql, Collection<?> values,
			Object... params);

	/**
	 * <p>
	 * 设置天气
	 * </p>
	 * 
	 * @param chqi
	 * @param weather
	 * @return void
	 */
	public void setWeather(Date chqi, String weather);

	/**
	 * <p>
	 * 获取天气
	 * </p>
	 * 
	 * @param chqi
	 * @param weather
	 * @return void
	 */
	public String getWeather(Date chqi);

}
