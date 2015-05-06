/**
 * 
 */
package com.quanta.sino.sl.dao.api;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.SlsjLp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.sl.vo.SlsjVO;

/**
 * <p>
 * SL实绩数据访问实现
 * </p>
 * <p>
 * create: 2011-1-17 上午09:05:31
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface ISlDAO {

	/**
	 * <p>
	 * 获取当前实绩未完的入端卷记录（剪切材）
	 * </p>
	 * 
	 * @param scxb
	 * @return
	 */
	public ZpngTp getCurr(String scxb);

	/**
	 * <p>
	 * 根据生产线取最新的一条新生成的制品記錄
	 * </p>
	 * 
	 * @param scxb
	 *            生产线别
	 * @return
	 */
	public ZpngTp getLastCdByScx(String scxb);

	/**
	 * <p>
	 * 获取入端卷生成的制品中有多少个端板
	 * </p>
	 * 
	 * @param jbno
	 *            入端卷号【中间品】
	 * @return
	 */
	public Long getDs(String jbno);

	/**
	 * <p>
	 * 保存SL实绩日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveSlsj(SlsjLp entity);

	/**
	 * <p>
	 * 实绩制品分页查询
	 * </p>
	 * 
	 * @param page
	 *            查询体
	 */
	public void query(QEntity<SlsjVO> page);

}
