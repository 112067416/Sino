package com.quanta.sino.etl.bo.api;

import java.util.List;

import com.quanta.sino.etl.vo.RcSaveVO;
import com.quanta.sino.etl.vo.RcViewVO;
import com.quanta.sino.etl.vo.RcmxVO;

/**
 * <p>
 * ETL入侧业务接口
 * </p>
 * <p>
 * create: 2011-1-18 上午08:48:04
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface IRcBO {

	/**
	 * <p>
	 * 撤消
	 * </p>
	 * 
	 * @param jbno
	 */
	public void deleteBY(String jbno);

	/**
	 * <p>
	 * 强制移除
	 * </p>
	 * 
	 * @param dqjbno
	 * @param byjbno
	 */
	public void updateMove(String dqjbno);

	/**
	 * <p>
	 * 备用卷确认
	 * </p>
	 * 
	 * @param jbno
	 */
	public void updateBySfqr(String jbno);

	/**
	 * <p>
	 * 判断原材是否生产确认
	 * </p>
	 * 
	 * @param jbno
	 * @param sfqr
	 * @return
	 */
	public boolean isExisted(String jbno, String sfqr);

	/**
	 * <p>
	 * 查询备用和当前卷
	 * </p>
	 * 
	 * @return
	 */
	public List<RcmxVO> findByStates();

	/**
	 * <p>
	 * 判读是否有备用卷，无备用卷提交备用卷
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(RcSaveVO entity);

	/**
	 * <p>
	 * 通过第三方接口更新本地备用卷,-1为没有更新备用卷，1为已更新备用卷
	 * </p>
	 */
	public Integer updateYCBY();

	/**
	 * <p>
	 * 通过第三方接口更新本地备用卷信息,1为不能加备用卷，2为请加备用卷，-1为不显示
	 * </p>
	 */
	public Integer getbygif();

	/**
	 * <p>
	 * 查询入侧信息
	 * </p>
	 * 
	 * @return
	 */
	public RcViewVO findRc();
}
