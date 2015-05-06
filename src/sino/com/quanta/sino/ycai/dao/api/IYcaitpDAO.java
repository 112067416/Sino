package com.quanta.sino.ycai.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YcaiTp;

/**
 * <p>
 * 原板数据接口
 * </p>
 * <p>
 * create time : 2010-12-9 上午10:05:50
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYcaitpDAO {
	/**
	 * <p>
	 * 判断是原材是否在实绩录入
	 * </p>
	 * 
	 * @param zsno
	 * @return
	 */
	public boolean isExistedYcaiSjsj(String zsno);

	/**
	 * <p>
	 * 获取原材信息
	 * </p>
	 * <p>
	 * create time : 2010-12-10 上午09:36:09
	 * </p>
	 * 
	 * @param id
	 * @return YcaiTp
	 */
	public YcaiTp get(Serializable id);

	/**
	 * <p>
	 * 根据制造商钢卷号，查询原材卷板记录
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zzsj
	 * @return YcaiTp
	 */
	public YcaiTp getByZzsj(String zzsj);

	/**
	 * <p>
	 * 查询正在实绩录入的原材卷板记录
	 * </p>
	 * 
	 * @param stat
	 * @return
	 */
	public YcaiTp getZzsj(String stat);

	/**
	 * <p>
	 * 保存原材信息
	 * </p>
	 * <p>
	 * create time : 2010-12-10 上午09:36:05
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(YcaiTp entity);

	/**
	 * <p>
	 * 判断是否存在此制造商卷No
	 * </p>
	 * <p>
	 * create time : 2010-12-10 上午09:34:33
	 * </p>
	 * 
	 * @param zzsj
	 *            制造商卷No
	 * @return boolean
	 */
	public boolean isExisted(String zzsj);

	/**
	 * <p>
	 * 判断船名是否存在
	 * </p>
	 * 
	 * @param ship
	 * @return boolean
	 */
	public boolean isExistedShip(String ship);

	/**
	 * <p>
	 * 是否存在未终了或未中止的原材
	 * </p>
	 * 
	 * @param zsno
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExistedYcai(String zsno, String jbno);

	/**
	 * <p>
	 * 是否存在原材
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExistedYcaiByJbno(String jbno);

	/**
	 * <p>
	 * 是否存在终了、中止或实绩录入的原材
	 * </p>
	 * 
	 * @param zsno
	 * @return boolean
	 */
	public boolean isExistedYcai(String zsno);

	/**
	 * <p>
	 * 是否存在供应商合同
	 * </p>
	 * 
	 * @param ybno
	 *            供应商合同NO
	 * @param line
	 *            供应商合同行号
	 * @return boolean
	 */
	public boolean isExistedWwhttp(String ybno, String line);

	/**
	 * <p>
	 * 原板信息查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YcaiTp> qentity);

	/**
	 * <p>
	 * 修改原板信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(YcaiTp entity);

	/**
	 * <p>
	 * 删除原板信息
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void deletes(List<String> jbnos);

	/**
	 * <p>
	 * 单个删除原板信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 根据船名获取原板信息
	 * </p>
	 * 
	 * @param ship
	 * @return List[YcaiTp]
	 */
	public List<YcaiTp> findByShip(String ship);

	/**
	 * <p>
	 * 根据指示书获取原板信息
	 * </p>
	 * 
	 * @param zsno
	 *            指示号
	 * @return
	 */
	public List<YcaiTp> findByZsno(String zsno);

	/**
	 * <p>
	 * 根据原材卷板号，查询原板信息
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbnos
	 *            原板卷板号
	 * @return List<YcaiTp>
	 */
	public List<YcaiTp> find(List<String> jbnos);

	/**
	 * <p>
	 * 获取原材信息引用
	 * </p>
	 * 
	 * @param id
	 * @return YcaiTp
	 */
	public YcaiTp getRef(Serializable id);

	/**
	 * <p>
	 * 设置原板入库时间
	 * </p>
	 * 
	 * @param ship
	 * @param duib
	 * @param stats
	 */
	public void setRksj(String ship, Date duib, String[] stats);

	/**
	 * <p>
	 * 统计整船原板重量
	 * </p>
	 * 
	 * @param ship
	 * @return Long
	 */
	public Long totalZpzl(String ship);

	/**
	 * <p>
	 * 是否存在别的实绩录入，状态为未终了或中止
	 * </p>
	 * 
	 * @param jbno
	 *            卷板NO
	 * @param elin
	 *            生产线
	 * @return True 为存在
	 */
	public boolean isZl(String jbno, String elin);

	/**
	 * <p>
	 * 设置业联no
	 * </p>
	 * 
	 * @param jbnos
	 * @param ylno
	 */
	public void setYlno(List<String> jbnos, String ylno);

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
	 * 根据生产状态查询原材
	 * </p>
	 * 
	 * @param sczt
	 * @return YcaiTp
	 */
	public YcaiTp getYcaiBySczt(String sczt);

	/**
	 * <p>
	 * 判断是否有备用卷
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public boolean isExistedbysczt(String sczt);

	/**
	 * 修改备卷是否向pc600设备发送过数据
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateSfqr(String id, String stat);

	/**
	 * <p>
	 * 修改制定原材卷板的生产状态 当设定卷生产完成的时候 （生产状态变为3） 设定卷的生产完成时间
	 * </p>
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateSczt(String id, String stat);

	/**
	 * <p>
	 * 修改生产状态和是否确认标识
	 * </p>
	 * 
	 * @param id
	 * @param sczt
	 * @param sfqr
	 */
	public void updateScztAndSfqr(String id, String sczt, String sfqr);

	/**
	 * <p>
	 * 判断原材是否生产确认
	 * </p>
	 * 
	 * @param jbno
	 * @param sfqr
	 * @return boolean
	 */
	public boolean isExistedByJbnoAndSczt(String jbno, String sczt);

	/**
	 * <p>
	 * 判断是否有备用卷
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public boolean isExistedBySczt(String sczt);

	/**
	 * <p>
	 * 修改原材硬度
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 * @param jdyn
	 * @param ydsj
	 */
	public void updateYd(String jbno, Integer ying, String jdyn, Date ydsj);

	/**
	 * <p>
	 * 查询库存
	 * </p>
	 * 
	 * @param xpho
	 * @param xpkn
	 * @param yuny
	 * @param faces
	 * @return Long
	 */
	public Long getKc(Double xpho, Double xpkn, String yuny, List<String> faces);

	/**
	 * <p>
	 * 批量设置业务No
	 * </p>
	 * 
	 * @param jbnoStart
	 * @param jbnoEnd
	 * @param ylno
	 */
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno);

}
