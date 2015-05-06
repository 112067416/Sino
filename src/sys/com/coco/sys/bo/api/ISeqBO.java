package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Seq;
import com.coco.sys.orm.SeqRule;

/**
 * <p>
 * 序号业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:42:45
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISeqBO {

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Seq entity);

	/**
	 * <p>
	 * 更新
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Seq entity);

	/**
	 * <p>
	 * 保存序号规则
	 * </p>
	 * 
	 * @param id
	 * @param rules
	 */
	public void saveRules(String id, List<SeqRule> rules);

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
	 * 获取序号
	 * </p>
	 * 
	 * @param id
	 * @return Seq
	 */
	public Seq get(Serializable id);

	/**
	 * <p>
	 * 查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Seq> qentity);

	/**
	 * <p>
	 * 获取序号对象js串，不包含规则
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 根据序号Id获取其规则
	 * </p>
	 * 
	 * @param id
	 * @return List<SeqRule>
	 */
	public List<SeqRule> findRules(String id);

	/**
	 * <p>
	 * 生成序号，根据其规则生成
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String sequence(String id);

	/**
	 * <p>
	 * 生成序号，根据其规则生成,不更新序号表。
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public String sequenceNoUpdate(String id);

}
