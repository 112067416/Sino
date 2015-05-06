package com.quanta.sino.ycai.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JchaTp;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 下午05:59:14
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public interface IJchatpDAO {
	/**
	 * 新增原板检查证明DB
	 * 
	 * @param entity
	 */
	public void save(JchaTp entity);

	/**
	 * 查询原板检查证明DB
	 * 
	 * @param qentity
	 */

	public void query(QEntity<JchaTp> qentity);

	/**
	 * 删除原板检查证明DB
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 批量删除原板检查证明DB
	 * </p>
	 * 
	 * @param ids
	 */
	public void deleteAll(List<String> ids);

	/**
	 * 获取原板检查证明DB
	 * 
	 * @param id
	 * @return
	 */
	public JchaTp get(Serializable id);

	/**
	 * <p>
	 * 根据制造商卷板NO，获得原板查询证明书对象
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zzsj
	 *            制造商卷板NO
	 * @return JchaTp
	 */
	public JchaTp getByZzsj(String zzsj);

	/**
	 * 更新原板检查证明DB
	 * 
	 * @param id
	 * @return
	 */
	public void update(JchaTp entity);

	/**
	 * <p>
	 * 判断是否存在原材卷板No
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExisted(String jbno);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param zzsj
	 * @return boolean
	 */
	public boolean isExistedByZzsj(String zzsj);

	/**
	 * <p>
	 * 批量保存原板检查证明书信息
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entities
	 * @return
	 */
	public void saveAll(List<JchaTp> entities);

}
