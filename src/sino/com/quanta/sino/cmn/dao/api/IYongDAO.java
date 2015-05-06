package com.quanta.sino.cmn.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.YongSize;

/**
 * <p>
 * 通用主文件数据访问接口
 * </p>
 * <p>
 * create: 2011-2-12 上午10:04:00
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IYongDAO {

	/**
	 * 新增通用主文件
	 * 
	 * @param entity
	 */
	public void save(YongMp entity);

	/**
	 * 更新通用主文件
	 * 
	 * @param entity
	 */
	public void update(YongMp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(List<String> ids);

	/**
	 * <p>
	 * 批量设置参数值
	 * </p>
	 * 
	 * @param users
	 * @param region
	 * @param valid
	 * @param ddnm
	 */
	public void setParams(List<String> users, String region, String valid,
			String ddnm);

	/**
	 * <p>
	 * </p>
	 * 
	 * @return List<YongMp>
	 */
	public List<YongMp> findAll();

	/**
	 * 查询通用主文件
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YongMp> qentity);

	/**
	 * 获取通用主文件
	 * 
	 * @param id
	 * @return
	 */
	public YongMp get(Serializable id);

	/**
	 * 查询用户送货地址
	 * 
	 * @param user
	 * @return
	 */
	public List<YongShdz> findShdz(String user);

	/**
	 * <p>
	 * 查询客户尺寸和用途
	 * </p>
	 * 
	 * @param user
	 * @return List<YongSize>
	 */
	public List<YongSize> findSize(String user);

	/**
	 * 保存用户送货地点
	 * 
	 * @param shdds
	 */
	public void saveShdz(List<YongShdz> shdzes);

	/**
	 * 保存用户尺寸和用途
	 * 
	 * @param shdds
	 */
	public void saveSize(List<YongSize> sizes);

	/**
	 * <p>
	 * 判断地址代码是否存在。注：每个地址对应唯一的地址代码
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param dzdm
	 *            地址代码
	 * @return boolean
	 */
	public boolean isExistDzdm(String dzdm);

	/**
	 * <p>
	 * 判断用户代码对应的用户是否存在
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param user
	 *            用户代码
	 * @return boolean
	 */
	public boolean isExistUser(String user);

	/**
	 * <p>
	 * 判断地址代码是否属于用户
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param user
	 *            用户代码
	 * @param dzdm
	 *            地址代码
	 * @return boolean
	 */
	public boolean isExistUserAndDzdm(String user, String dzdm);

	/**
	 * <p>
	 * 根据地址代码，获得地址
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param dzdm
	 *            地址代码
	 * @return YongShdz
	 */
	public YongShdz getByDzdm(String dzdm);

	/**
	 * <p>
	 * 删除用户地址
	 * </p>
	 * 
	 * @param user
	 */
	public void deleteShdz(String user);

	/**
	 * <p>
	 * 删除用户尺寸和用途
	 * </p>
	 * 
	 * @param user
	 */
	public void deleteSize(String user);

	/**
	 * <p>
	 * 查询发票客户名称
	 * </p>
	 * 
	 * @return List<String>
	 */
	public List<String> findFpkhs();

}
