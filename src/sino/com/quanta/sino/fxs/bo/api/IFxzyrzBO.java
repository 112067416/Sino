package com.quanta.sino.fxs.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.vo.YcsxVO;
import com.quanta.sino.orm.FxzyRz;
import com.quanta.sino.orm.FxzyRzYcsx;

/**
 * <p>
 * 分析作业日志表
 * </p>
 * <p>
 * create: 2011-2-15 下午04:26:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFxzyrzBO {

	/**
	 * <p>
	 * 新增分析作业日志表
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 */
	public void save(FxzyRz entity, User user);

	/**
	 * <p>
	 * 更新分析作业日志表
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 */
	public void update(FxzyRz entity, User user);

	/**
	 * <p>
	 * 查询分析作业日志表
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<FxzyRz> qentity);

	/**
	 * <p>
	 * 获取分析作业日志表
	 * </p>
	 * 
	 * @param id
	 * @return FxzyRz
	 */
	public FxzyRz get(Serializable id);

	/**
	 * <p>
	 * 批量删除 分析作业日志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(String[] ids);

	/**
	 * <p>
	 * 根据分析作业日志表的id 获取打印列表
	 * </p>
	 * 
	 * @param ids
	 * @return List<FxzyRz>
	 */
	public List<FxzyRz> findFxzyPrints(String[] ids);

	/**
	 * <p>
	 * 查询分析作业日志的异常事项
	 * </p>
	 * 
	 * @param pid
	 *            分析作业日志的主键
	 * @return List<FxzyRzYcsx>
	 */
	public List<FxzyRzYcsx> find(String pid);

	/**
	 * <p>
	 * 获得卷板对应的订货信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String getDhForJs(String jbno);

	/**
	 * <p>
	 * 保存异常事项
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String saveYcsx(YcsxVO vo);

	/**
	 * <p>
	 * 判断分析作业日志是否有异常事项
	 * </p>
	 * 
	 * @param pid
	 * @return boolean
	 */
	public boolean isFxzyRzYcsx(String pid);

}
