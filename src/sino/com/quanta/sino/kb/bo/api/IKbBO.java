package com.quanta.sino.kb.bo.api;

import com.coco.core.bean.User;
import com.quanta.sino.orm.Kbrzb;

/**
 * <p>
 * 捆包生产业务处理层接口
 * </p>
 * <p>
 * create: 2011-1-12 上午09:42:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKbBO {

	/**
	 * <p>
	 * 保存捆包操作
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param bz
	 * @param user
	 * @param duic
	 */
	public void saveKbsj(String[] ids, String bz, User user, String duic);

	/**
	 * <p>
	 * 回退捆包操作
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @param xpzk
	 */
	public void rollBack(String[] ids, User user, String xpzk);

	/**
	 * <p>
	 * 设定或取消紧急材
	 * </p>
	 * 
	 * @param jbnos
	 * @param flag
	 */
	public void updateSfjj(String[] jbnos, String sfjj);

	/**
	 * <p>
	 * 保存捆包日记表记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Kbrzb entity);

	/**
	 * <p>
	 * 验证输入的制品号信息是否正确
	 * </p>
	 * 
	 * @param jbno
	 * @param duic
	 * @return String
	 */
	public String getKb(String jbno, String duic);

	/**
	 * <p>
	 * 为制品设定库位
	 * </p>
	 * 
	 * @param kw
	 * @param jbnos
	 */
	public void saveKw(String kw, String[] jbnos);

}
