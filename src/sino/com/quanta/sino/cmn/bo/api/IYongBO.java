package com.quanta.sino.cmn.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.YongSize;

/**
 * 用户主文件业务接口
 * 
 * @author 许德建[xudejian@126.com]
 */
public interface IYongBO {

	/**
	 * 新增用户主文件
	 * 
	 * @param entity
	 */
	public void save(YongMp entity);

	/**
	 * 修改用户主文件
	 * 
	 * @param entity
	 */
	public void update(YongMp entity);

	/**
	 * 根据用户主文件编号删除对应的信息
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
	 * @return
	 */
	public List<YongMp> findAll();

	/**
	 * 查询用户主文件
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YongMp> qentity);

	/**
	 * 获取用户主文件
	 * 
	 * @param id
	 * @return
	 */
	public YongMp get(Serializable id);

	/**
	 * 获取用户主文件Js串
	 * 
	 * @param id
	 * @return
	 */
	public String getForJs(Serializable id);

	/**
	 * 查询用户送货地点
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
	 * <p>
	 * 保存用户送货地点
	 * </p>
	 * 
	 * @param user
	 * @param shdzes
	 * @return String
	 */
	public String saveShdz(String user, List<YongShdz> shdzes);

	/**
	 * <p>
	 * 维护客户尺寸和用途
	 * </p>
	 * 
	 * @param user
	 * @param sizes
	 * @return String
	 */
	public String saveSize(String user, List<YongSize> sizes);

	/**
	 * 检查金额是否在信用额度之内，true-是，false-否
	 * 
	 * @param user
	 * @param number
	 * @return
	 */
	public boolean isWithinCredit(String user, Double number);

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
	 * 查询发票客户名称
	 * </p>
	 * 
	 * @return List<String>
	 */
	public List<String> findFpkhs();
}
