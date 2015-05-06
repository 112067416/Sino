package com.quanta.sino.dy.bo.api;

import com.quanta.sino.dy.vo.ZpkDataVO;
import com.quanta.sino.dy.vo.ZpkVO;

/**
 * <p>
 * 制品卡打印数据获取业务接口
 * </p>
 * <p>
 * create: 2011-2-12 上午09:57:59
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IZpkBO {
	/**
	 * <p>
	 * 查询端板
	 * </p>
	 * 
	 * @param vo
	 * @return
	 */
	public String getdbForJs(ZpkDataVO vo);

	/**
	 * 根据制品No获取制品卡信息
	 * 
	 * @param jbno
	 *            制品No.
	 * @return ZpkVO
	 */
	public ZpkVO get(String jbno);

	/**
	 * 根据制品No获取端板制品卡信息
	 * 
	 * @param jbno
	 *            制品No.
	 * @return ZpkVO
	 */
	public ZpkVO getdb(String jbno);

	/**
	 * 根据制品No获取制品卡信息js对象串<ZpkVO>
	 * 
	 * @param vo
	 *            页面数据对象
	 * @return String
	 */
	public String getForJs(ZpkDataVO vo);

}
