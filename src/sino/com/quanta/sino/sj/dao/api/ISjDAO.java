package com.quanta.sino.sj.dao.api;

import java.io.Serializable;

import com.quanta.sino.cmn.constants.ERzlx;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.Zsfmt;

/**
 * <p>
 * 实绩日志公共格式数据访问接口
 * </p>
 * <p>
 * create: 2011-1-20 下午06:02:30
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISjDAO {

	/**
	 * <p>
	 * 保存实绩日志（指示情报共通格式）
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Zsfmt entity);

	/**
	 * <p>
	 * 保存实绩日志(现品情报共通格式)
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Xpfmt entity);

	/**
	 * <p>
	 * 修改实绩日志(现品情报共通格式)
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Xpfmt entity);

	/**
	 * <p>
	 * 保存日志（日志类型：{@link ERzlx}）
	 * </p>
	 * 
	 * @param entity
	 *            日志实体
	 */
	public void save(RiziLp entity);

	/**
	 * <p>
	 * 删除日志（日志类型：{@link ERzlx}）
	 * </p>
	 * 
	 * @param entity
	 *            日志实体
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取实绩日志（指示情报共通格式）
	 * </p>
	 * 
	 * @param id
	 * @return Zsfmt
	 */
	public Zsfmt getZs(Serializable id);

	/**
	 * <p>
	 * 获取实绩日志(现品情报共通格式)
	 * </p>
	 * 
	 * @param id
	 * @return Xpfmt
	 */
	public Xpfmt getXp(Serializable id);

	/**
	 * <p>
	 * 获取指示出端记录
	 * </p>
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public ZscdTp getZscd(Serializable id);

	/**
	 * <p>
	 * 保存指示出端记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZscd(ZscdTp entity);

	/**
	 * <p>
	 * 删除指示出端
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZscd(Serializable id);
}
