package com.quanta.sino.dy.bo.api;

import com.quanta.sino.dy.vo.YcaibqVO;

/**
 * <p>
 * 原材打印数据获取业务接口
 * </p>
 * <p>
 * create: 2011-2-12 上午09:55:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IYcaibqBO {

	/**
	 * <p>
	 * 根据卷板No.获取原材卷板标签信息
	 * </p>
	 * 
	 * @param jbno
	 * @return YcaibqVO
	 */
	public YcaibqVO get(String jbno);

	/**
	 * <p>
	 * 根据船名查询原材No.列表并转化成js串
	 * </p>
	 * 
	 * @param ship
	 * @return String
	 */
	public String findByShip(String ship);

}
