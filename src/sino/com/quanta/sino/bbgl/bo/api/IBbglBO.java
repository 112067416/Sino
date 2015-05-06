package com.quanta.sino.bbgl.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.bbgl.vo.CpcktzVO;
import com.quanta.sino.bbgl.vo.YccktzVO;
import com.quanta.sino.orm.Tzbz;

/**
 * <p>
 * 原材料仓库台帐业务接口
 * </p>
 * <p>
 * create: 2011-8-31 上午11:31:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IBbglBO {
	/**
	 * <p>
	 * 查询原材料仓库台帐
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryYctz(QEntity<YccktzVO> qentity);

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param vos
	 */
	public void saveYctz(List<YccktzVO> vos);

	/**
	 * <p>
	 * 查询成品仓库台帐
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryCptz(QEntity<CpcktzVO> qentity);

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param vos
	 * @param cplb
	 */
	public void saveCptz(List<CpcktzVO> vos, String cplb);

	/**
	 * <p>
	 * 查询台帐备注
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryTzbz(QEntity<Tzbz> qentity);

	/**
	 * <p>
	 * 新增台帐备注
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String saveTzbz(Tzbz entity);

	/**
	 * <p>
	 * 更新台帐备注
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String updateTzbz(Tzbz entity);

	/**
	 * <p>
	 * 加载台帐备注
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public String loadTzbzForJS(Serializable id);

	/**
	 * <p>
	 * 删除台帐备注
	 * </p>
	 * 
	 * @param ids
	 */
	public void delsTzbz(String[] ids);

}
