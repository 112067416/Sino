package com.quanta.sino.yygl.bo.api;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.yygl.vo.ChlldVO;

/**
 * <p>
 * 出货联络单业务处理接口
 * </p>
 * <p>
 * create: 2010-12-21 下午06:22:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IChlldBO {

	/**
	 * 新增出货联络单
	 * 
	 * @param entity
	 */
	public void save(Chlld entity, User user);

	/**
	 * 新增出货联络单
	 * 
	 * @param entity
	 */
	public void save(Chlld entity);

	/**
	 * <p>
	 * 更新出货联络单
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Chlld entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public String delete(String[] ids);

	/**
	 * 查询出货联络单
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Chlld> qentity);

	/**
	 * 获取出货联络单
	 * 
	 * @param id
	 * @return Chlld
	 */
	public Chlld get(Serializable id);

	/**
	 * 将获取的对象转化成JS
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 次日出货联络单上锁及解锁
	 * </p>
	 * 
	 * @param ids
	 *            选择的信息id集合
	 * @param stat
	 *            上锁或解锁的标识
	 */
	public void updateStat(String[] ids, String stat);

	/**
	 * <p>
	 * 获取订货DB信息内容
	 * </p>
	 * 
	 * @param dhno
	 *            订货no
	 * @param line
	 *            行号
	 * @return String
	 */
	public String getDhInfo(String dhno, String line);

	/**
	 * <p>
	 * 保存联络单设置的信息内容
	 * </p>
	 * 
	 * @param vo
	 */
	public void doSetting(ChlldVO vo);

	/**
	 * <p>
	 * 保存联络单分解的信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String doDivide(Chlld entity);

	/**
	 * <p>
	 * 验证次日出货联络单的信息
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String getCheck(Chlld entity);

	/**
	 * <p>
	 * 设置天气
	 * </p>
	 * 
	 * @param chqi
	 * @param weather
	 * @return void
	 */
	public void setWeather(Date chqi, String weather);

	/**
	 * <p>
	 * 获取天气
	 * </p>
	 * 
	 * @param chqi
	 * @param weather
	 * @return void
	 */
	public String getWeather(Date chqi);

}
