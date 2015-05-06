package com.quanta.sino.etl.dao.api;

import java.util.Date;
import java.util.List;

import com.quanta.sino.etl.vo.YxRecordVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Rcrz;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdhTp;

/**
 * <p>
 * 入侧日志
 * </p>
 * <p>
 * create: 2011-1-26 下午06:38:49
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public interface IRcrzDAO {

	/**
	 * <p>
	 * 第三方接口判断是否要加备用卷
	 * </p>
	 * 
	 * @return
	 */
	public boolean getByStat(Integer varId);

	/**
	 * <p>
	 * 新增业连日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Rcrz entity);

	/**
	 * <p>
	 * 通过第三方接口写中间表,成功返回True,失败返回false;
	 * </p>
	 * 
	 * @param jbno
	 */
	public boolean sendSave(String jbno, YcaiTp ycai, ZsdhTp zsdh, DhuoTp dhuo);

	/**
	 * <p>
	 * 通过第三方接口删除中间表,成功返回True,失败返回false;
	 * </p>
	 * 
	 * @param jbno
	 */
	public boolean sendDelete(String jbno);

	/**
	 * <p>
	 * 通过第三方接口查询备用卷状态，，1为备用,2为当前,3为已完成
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public String getSczt(String jbno);

	/**
	 * <p>
	 * 通过第三方接口判断是否存在备用卷,1为不存在，-1为存在
	 * </p>
	 * 
	 * @return
	 */
	public boolean isExitBy();

	/**
	 * <p>
	 * 查询PC6000数据库的机器停机记录
	 * </p>
	 * 
	 * @param begin
	 * @param end
	 * @return List<YxRecordVO>
	 */
	public List<YxRecordVO> queryYxRecords(Date begin, Date end);

	/**
	 * <p>
	 * 查询PC6000数据库的机器停机记录
	 * </p>
	 * 
	 * @param begin
	 * @param end
	 * @param orderbys
	 * @return List<YxRecordVO>
	 */
	public List<YxRecordVO> queryYxRecords(Date begin, Date end, String orderbys);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param systime
	 * @param varValue
	 * @return YxRecordVO
	 */
	public YxRecordVO getYxRecord(Date systime, String varValue);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param systime
	 * @return YxRecordVO
	 */
	public YxRecordVO getYxRecord(Date systime);
}
