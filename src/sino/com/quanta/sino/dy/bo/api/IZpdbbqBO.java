package com.quanta.sino.dy.bo.api;

import com.quanta.sino.dy.vo.ZpdbbqVO;

/**
 * <p>
 * 制品打印数据获取业务接口
 * </p>
 * <p>
 * create: 2011-2-12 上午09:57:11
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IZpdbbqBO {

	/**
	 * 根据制品No获取制品标签信息
	 * 
	 * @param jbno
	 *            制品号
	 * @return ZpbqVO
	 */
	public ZpdbbqVO get(String jbno);

}
