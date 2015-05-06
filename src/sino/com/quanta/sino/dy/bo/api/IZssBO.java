package com.quanta.sino.dy.bo.api;

import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.dy.vo.ZssVO;

/**
 * <p>
 * 指示书打印业务接口
 * </p>
 * <p>
 * create: 2011-1-14 上午09:37:31
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IZssBO {

	/**
	 * 根据指示书连号获取指示书信息
	 * 
	 * @param zsno
	 *            指示连号
	 * @param type
	 *            类型，仅ETL和SL有效
	 * @return ZssVO
	 */
	public ZssVO get(String zsno, ProductType type);
}
