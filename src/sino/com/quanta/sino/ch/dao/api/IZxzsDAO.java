package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZxzsTp;

/**
 * <p>
 * 装箱指示书数据访问接口
 * </p>
 * <p>
 * create: 2011-2-23 上午11:30:33
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IZxzsDAO {

	/**
	 * <p>
	 * 新增装箱指示书ZxzsTp
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(ZxzsTp entity);

	/**
	 * <p>
	 * 更新装箱指示书
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(ZxzsTp entity);

	/**
	 * <p>
	 * 查询装箱指示书
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZxzsTp> qentity);

	/**
	 * <p>
	 * 获取装箱指示书
	 * </p>
	 * 
	 * @param id
	 * @return ZxzsTp
	 */
	public ZxzsTp get(Serializable id);

	/**
	 * <p>
	 * 获取装箱指示书最大的号码
	 * </p>
	 * 
	 * @return String
	 */
	public String getMaxNo();

	/**
	 * <p>
	 * 判断装箱指示书是否存在
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExist(Serializable id);

	/**
	 * <p>
	 * 统计次日出货联络单，对应的打单数量
	 * </p>
	 * 
	 * @param pid
	 * @return Double
	 */
	public Double getTj(String pid);

	/**
	 * <p>
	 * 统计次日出货联络单，对应的打单数量
	 * </p>
	 * 
	 * @param chri
	 * @return Double
	 */
	public Double getTj(Date chri);

	/**
	 * <p>
	 * 判断出货联络单是否有对应的装箱指示书
	 * </p>
	 * 
	 * @param pid
	 * @return boolean
	 */
	public boolean isExistByChlld(String pid);

	/**
	 * <p>
	 * 统计指定日期的送货单No.
	 * </p>
	 * 
	 * @param chri
	 * @return List<String>
	 */
	public List<String> queryZxnos(Date chri);

	/**
	 * <p>
	 * 统计一个时间段出货的天数
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return long
	 */
	public int getNum(Date chriS, Date chriE);

}
