package com.quanta.sino.yszk.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khfk;

/**
 * <p>
 * 客户付款数据访问层接口
 * </p>
 * <p>
 * create: 2011-6-30 下午12:47:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKhfkDAO {
	/**
	 * <p>
	 * 新增客户付款
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Khfk entity);

	/**
	 * <p>
	 * 更新客户付款
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Khfk entity);

	/**
	 * <p>
	 * 根据主键删除对应的客户付款
	 * </p>
	 * 
	 * @param ids
	 */
	public void delete(List<String> ids);

	/**
	 * <p>
	 * 查询客户付款
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Khfk> qentity);

	/**
	 * <p>
	 * 查询客户付款记录
	 * </p>
	 * 
	 * @param ids
	 * @return List<Khfk>
	 */
	public List<Khfk> find(List<String> ids);

	/**
	 * <p>
	 * 获取客户付款
	 * </p>
	 * 
	 * @param id
	 * @return Khfk
	 */
	public Khfk get(Serializable id);

	/**
	 * <p>
	 * 获取客户付款信息，该实体信息由容器托管，其属性更改将同步到数据库
	 * </p>
	 * 
	 * @param id
	 * @return Khfk
	 */
	public Khfk getRef(Serializable id);

	// /**
	// * <p>
	// * 根据客户代码和付款日期，获取客户付款信息
	// * </p>
	// *
	// * @param user
	// * 用户代码
	// * @param crea
	// * 付款日期
	// * @return Khfk
	// */
	// public Khfk get(String user, Date crea);

	// /**
	// * <p>
	// * 根据客户代码和状态，获取客户付款信息
	// * </p>
	// *
	// * @param user
	// * 用户代码
	// * @param crea
	// * 付款日期
	// * @param stats
	// * 状态
	// * @return Khfk
	// */
	// public Khfk get(String user, Date crea, String[] stats);

	/**
	 * <p>
	 * 查询客户付款记录
	 * </p>
	 * 
	 * @param fpymc
	 * @param stats
	 * @return List<Khfk>
	 */
	public List<Khfk> find(String fpymc, List<String> stats);

	/**
	 * <p>
	 * 判断客户是否付款
	 * </p>
	 * 
	 * @param fpymc
	 * @param stats
	 * @return boolean
	 */
	public boolean isExisted(String fpymc, List<String> stats);

	/**
	 * <p>
	 * 获取客户付款余额
	 * </p>
	 * <p>
	 * author:陈小攀
	 * </p>
	 * 
	 * @param fpymc
	 * @return Double
	 */
	public Double getFkye(String fpymc);

}
