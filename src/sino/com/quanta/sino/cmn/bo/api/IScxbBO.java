package com.quanta.sino.cmn.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.vo.ScxbpzFormVO;
import com.quanta.sino.orm.Scxbpz;

/**
 * <p>
 * 生产线别配置业务接口
 * </p>
 * <p>
 * create: 2011-1-6 上午09:53:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IScxbBO {

	/**
	 * <p>
	 * 保存生产线别配置
	 * </p>
	 * 
	 * @param vo
	 */
	public void save(ScxbpzFormVO vo);

	/**
	 * <p>
	 * 删除指定的生产线别
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询生产线别配置
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Scxbpz> qentity);

	/**
	 * <p>
	 * 根据部门获取线别
	 * </p>
	 * 
	 * @param dept
	 * @return Scxbpz
	 */
	public Scxbpz getByDept(String dept);

	/**
	 * <p>
	 * 根据代码获取线别
	 * </p>
	 * 
	 * @param type
	 * @param code
	 * @return Scxbpz
	 */
	public Scxbpz getByCode(ProductType type, String code);

	/**
	 * <p>
	 * 根据类型获取线别列表
	 * </p>
	 * 
	 * @param type
	 * @param code
	 * @return List<Scxbpz>
	 */
	public List<Scxbpz> findByType(ProductType type);
}
