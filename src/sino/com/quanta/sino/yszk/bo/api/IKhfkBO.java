package com.quanta.sino.yszk.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.vo.KhfkVO;

/**
 * <p>
 * 客户付款业务层接口
 * </p>
 * <p>
 * create: 2011-6-30 下午12:47:24
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKhfkBO {
	/**
	 * <p>
	 * 新增客户付款
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String save(Khfk entity, User user);

	/**
	 * <p>
	 * 更新客户付款（本模块调用）
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String update(Khfk entity);

	/**
	 * <p>
	 * 通过冲账付款后更新客户付款信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateByCz(Khfk entity);

	/**
	 * <p>
	 * 根据主键删除对应的客户付款
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String delete(String[] ids);

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
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取客户付款信息
	 * </p>
	 * 
	 * @param id
	 * @return Khfk
	 */
	public Khfk get(Serializable id);

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
	 * 获取调整金额
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getTzje(String id);

	/**
	 * <p>
	 * 保持调整金额
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String saveTzje(KhfkVO vo);

	/**
	 * <p>
	 * 撤销冲账需要修改的关联信息（包括表有：付款发票、客户付款、冲账关联）
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String doCxcz(String id);

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
