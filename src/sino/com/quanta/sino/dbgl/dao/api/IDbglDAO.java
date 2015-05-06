package com.quanta.sino.dbgl.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DbglLp;
import com.quanta.sino.orm.DbglTp;

/**
 * <p>
 * 端板数据访问接口
 * </p>
 * <p>
 * create: 2011-1-20 下午05:35:37
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IDbglDAO {
	/**
	 * <p>
	 * 保存日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(DbglLp entity);

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(DbglTp entity);

	/**
	 * 查询
	 * <p>
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
	 * @param id
	 */
	public void delete(Serializable id);

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
	 * 设置基板状态
	 * </p>
	 * 
	 * @param jbnos
	 * @param zt
	 */
	public void updateZt(List<String> jbnos, String zt);

}
