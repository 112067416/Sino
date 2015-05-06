package com.quanta.sino.dbgl.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DbglTp;

/**
 * <p>
 * 端板业务接口
 * </p>
 * <p>
 * create: 2011-1-20 下午07:48:02
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
public interface IDbglBO {

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(DbglTp entity);

	/**
	 * <p>
	 * 取生成的卷板NO
	 * </p>
	 * 
	 * @return
	 */
	public String getJbno();

	/**
	 * <p>
	 * 查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<DbglTp> qentity);

	/**
	 * <p>
	 * 取端板
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public DbglTp get(Serializable id);

	/**
	 * <p>
	 * 删除
	 * </p>
	 * 
	 * @param jbno
	 */
	public void delete(String jbno);

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void delAll(String[] jbnos);

	/**
	 * <p>
	 * 更新
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(DbglTp entity);

	/**
	 * <p>
	 * 设置基板出货
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void doFh(List<String> jbnos);

	/**
	 * <p>
	 * 撤消发货
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void doCxfh(String jbno);

}
