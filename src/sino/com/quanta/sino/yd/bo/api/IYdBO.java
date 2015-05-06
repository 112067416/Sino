/**
 * 
 */
package com.quanta.sino.yd.bo.api;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.yd.vo.GxVO;
import com.quanta.sino.yd.vo.YdVO;

/**
 * <p>
 * 硬度维护业务接口
 * </p>
 * <p>
 * create: 2011-1-28 下午03:18:31
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface IYdBO {

	/**
	 * <p>
	 * 硬度模块现品分页查询
	 * </p>
	 * 
	 * @param page
	 */
	public void query(QEntity<YdVO> page);

	/**
	 * <p>
	 * 硬度校验
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String checkYd(GxVO vo);

	/**
	 * <p>
	 * 硬度更新
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String updateYd(GxVO vo);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 * @return String
	 */
	public String updateYd(String jbno, Integer ying);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String getYdgxjlForJS(String jbno);

	/**
	 * <p>
	 * 获得母卷对应的硬度值
	 * </p>
	 * 
	 * @param jbno
	 * @return Integer
	 */
	public Integer getYing(String jbno);

}
