package com.quanta.sino.bbgl.dao.api;

import com.quanta.sino.bbgl.vo.CpcktzVO;

/**
 * <p>
 * 成品仓库台帐数据访问接口
 * </p>
 * <p>
 * create: 2011-8-31 上午11:02:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICpcktzDAO {

	/**
	 * <p>
	 * 修改成品仓库台帐数据
	 * </p>
	 * 
	 * @param vo
	 * @param cplb
	 */
	public void update(CpcktzVO vo, String cplb);

}
