package com.quanta.sino.ch.bo.api;

import java.util.List;

import com.quanta.sino.dy.vo.PbzqdVO;

/**
 * <p>
 * 包装清单操作业务接口
 * </p>
 * <p>
 * create: 2011-1-18 上午09:21:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IBzqdBO {

	/**
	 * <p>
	 * 根据装箱指示书，查询包装清单
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param zxno
	 * @return String
	 */
	public String get(String dhno, String line, String zxno);

	/**
	 * <p>
	 * 获取包装清单打印数据
	 * </p>
	 * 
	 * @param jbnos
	 * @param zxno
	 * @return List[PbzqdVO]
	 */
	public List<PbzqdVO> findBzqdVos(List<String> jbnos, String zxno);

}
