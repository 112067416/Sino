package com.quanta.sino.dy.bo.api;

import com.quanta.sino.dy.vo.JbqxbDataVO;

/**
 * <p>
 * 卷板缺陷表打印数据业务接口
 * </p>
 * <p>
 * create: 2011-2-12 上午09:53:18
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IJbqxbBO {

	/**
	 * <p>
	 * 根据卷标No获取卷板缺陷明细Js对象串
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String getForJs(JbqxbDataVO vo);
}
