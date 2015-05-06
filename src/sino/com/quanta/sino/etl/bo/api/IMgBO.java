package com.quanta.sino.etl.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.etl.vo.MgVO;
import com.quanta.sino.orm.Dmgs;

/**
 * <p>
 * 木工业务接口
 * </p>
 * <p>
 * create: 2011-1-20 下午07:48:02
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
public interface IMgBO {
	/**
	 * <p>
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Dmgs entity);

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
	 * 更新垫木库存
	 * </p>
	 * 
	 * @param kuan
	 * @param cang
	 * @param dmgs
	 */
	public void updateDmkz(Double kuan, Double cang, String dmfx, String kbao,
			String kw, String slin, Integer dmgs);

	/**
	 * <p>
	 * 取垫木库存
	 * </p>
	 * 
	 * @param kuan
	 * @param cang
	 * @return
	 */
	public Dmgs getUnique(Double kuan, Double cang, String dmfx, String kbao,
			String kw);

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(MgVO entity);

	/**
	 * <p>
	 * 查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Dmgs> qentity);

	/**
	 * <p>
	 * 取端板
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Dmgs get(Serializable id);

	/**
	 * <p>
	 * 保存移动
	 * </p>
	 * 
	 * @param idsList
	 */
	public void saveMove(List<String> idsList, String ban, String zu,
			String dann);

}
