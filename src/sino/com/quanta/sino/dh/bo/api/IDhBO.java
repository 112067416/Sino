/**
 * 
 */
package com.quanta.sino.dh.bo.api;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dh.vo.DefaultDhVO;
import com.quanta.sino.dh.vo.DhVO;
import com.quanta.sino.dh.vo.DhjdVO;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.FspHtVO;
import com.quanta.sino.dh.vo.FspVO;
import com.quanta.sino.dh.vo.GxhtVO;
import com.quanta.sino.dh.vo.JstjVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.GxhtTp;

/**
 * <p>
 * 订货单业务层高级接口
 * </p>
 * <p>
 * create: 2011-1-4 上午10:47:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IDhBO {

	/**
	 * <p>
	 * 新增订货单
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @return String
	 */
	public String save(DhuoTp entity, User user);

	/**
	 * <p>
	 * 更新订货单
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @param ydhno
	 * @return String
	 */
	public String update(DhuoTp entity, User user, String ydhno);

	/**
	 * <p>
	 * 根据中文件编号删除对应的信息
	 * </p>
	 * 
	 * @param ids
	 */
	public String delete(String[] ids);

	/**
	 * <p>
	 * 查询订货单
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<DhuoTp> qentity);

	/**
	 * <p>
	 * 获取订货单（同时验证是否存在）
	 * </p>
	 * 
	 * @param dhbo
	 *            订货no
	 * @param line
	 *            行号
	 * @param user
	 *            用户代码
	 * @return DhuoTp
	 */
	public DhuoTp get(String dhno, String line, String user);

	/**
	 * <p>
	 * 通过复合主键获取订货信息
	 * </p>
	 * 
	 * @param id
	 * @return DhuoTp
	 */
	public DhuoTp get(Serializable id);

	/**
	 * <p>
	 * 判断是否存在此订货No.
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param id
	 *            订货No.
	 * @return boolean
	 */
	public boolean isExisted(Serializable id);

	/**
	 * <p>
	 * 判断订货合同号是否存在
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @return boolean
	 */
	public boolean isExisted(String dhno, String line);

	/**
	 * <p>
	 * 判断订货合同号是否存在
	 * </p>
	 * 
	 * @param dhno
	 * @return boolean
	 */
	public boolean isExistedByDhno(String dhno);

	/**
	 * <p>
	 * 获取制造仕样信息
	 * </p>
	 * 
	 * @param dhuoTp
	 * @return String
	 */
	public String getZzsy(DhuoTp dhuoTp);

	/**
	 * <p>
	 * 获取捆包计算结果
	 * </p>
	 * 
	 * @param kbjs
	 *            捆包计算
	 * @param pzno
	 *            品种
	 * @param kuan
	 *            宽
	 * @param cang
	 *            长
	 * @return String
	 */
	public String getKbjs(String kbjs, String pzno, Double kuan, Double cang);

	/**
	 * <p>
	 * 加载订货DB信息
	 * </p>
	 * 
	 * @param id
	 * @param flag
	 *            标识 区分修改与其他操作的加载
	 * @return String
	 */
	public String getForJs(Serializable id, String flag);

	/**
	 * <p>
	 * 保存结算条件设定信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveJstj(JstjVO jstjVO);

	/**
	 * <p>
	 * 获取打印订货确认表集合信息
	 * </p>
	 * 
	 * @param ids
	 * @return List<DhuoTp>
	 */
	public List<DhuoTp> findDhqrPrints(String[] ids);

	/**
	 * <p>
	 * 获取仕样未确认打印信息集合并修改对应信息为未上锁
	 * </p>
	 * 
	 * @param ids
	 * @return List<DhuoTp>
	 */
	public List<DhuoTp> findSyPrints(String[] ids, User user);

	/**
	 * <p>
	 * 通过订货No.列表获取对应的订货合同信息
	 * </p>
	 * 
	 * @param ids
	 * @return List<DhuoTp>
	 */
	public List<DhuoTp> findByIds(List<String> ids);

	/**
	 * <p>
	 * 通过订货号，获取该订货号默认的订货信息
	 * </p>
	 * 
	 * @param dhno
	 *            订货号
	 * @return DefaultDhVO
	 */
	public DefaultDhVO getDefaultDhInfo(String dhno);

	/**
	 * <p>
	 * 订货合同仕样确认操作
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @return String
	 */
	public String doSyqr(DhuoTp entity, User user);

	/**
	 * <p>
	 * 批量操作订货合同仕样确认
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 */
	public void doSyqr(String[] ids, User user);

	/**
	 * <p>
	 * 订货合同仕样保存操作
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @return String
	 */
	public String doSybc(DhuoTp entity, User user);

	/**
	 * <p>
	 * 仕样上锁操作
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @return String
	 */
	public String lock(String[] ids, User user);

	/**
	 * <p>
	 * 仕样解锁操作
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @return String
	 */
	public String deLock(String[] ids, User user);

	/**
	 * <p>
	 * 订货合同完成操作
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @return String
	 */
	public String finish(String[] ids, User user);

	/**
	 * <p>
	 * 取消订货合同完成操作
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @return String
	 */
	public String cancelFinish(String[] ids, User user);

	/**
	 * <p>
	 * 修改订货合同
	 * </p>
	 * 
	 * @param tp
	 */
	public void update(DhuoTp tp);

	/**
	 * <p>
	 * 通过复合主键获取订货信息,该实体信息由容器托管，其属性更改将同步到数据库
	 * </p>
	 * 
	 * @param id
	 * @return DhuoTp
	 */
	public DhuoTp getRef(Serializable id);

	/**
	 * <p>
	 * 订货进度统计数据Excel流
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchDhjdmx(DhjdVO vo, OutputStream os);

	/**
	 * <p>
	 * 检验订货合同信息
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 * @param flag
	 * @return Result
	 */
	public Result checkDhInfo(DhuoTp entity, boolean flag);

	/**
	 * <p>
	 * 校验仕样信息
	 * </p>
	 * 
	 * @param entity
	 * @return Result
	 */
	public Result checkZzsy(DhuoTp entity);

	/**
	 * <p>
	 * 获得订货合同表中，当前最新的订货合同
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return
	 */
	public DhuoTp getLatest();

	/**
	 * <p>
	 * 取消仕样确认
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 * @return String
	 */
	public String cancelSyqr(String[] ids, User user);

	/**
	 * <p>
	 * 批量修改订货合同信息
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param dhuoTps
	 */
	public void updates(List<DhuoTp> dhuoTps);

	/**
	 * <p>
	 * 获得订货合同记录对应的页数
	 * </p>
	 * 
	 * @param dhno
	 * @param size
	 * @return int
	 */
	public int getPageSize(String dhno, Integer size);

	/**
	 * <p>
	 * 修改订货合同是否打印
	 * </p>
	 * 
	 * @param dhnos
	 * @param lines
	 * @param sfdy
	 */
	public void updateSfdy(List<String> dhnos, List<String> lines, String sfdy);

	/**
	 * <p>
	 * 查询打印ID
	 * </p>
	 * 
	 * @param sfdy
	 * @return List<String>
	 */
	public List<String> queryIds(String sfdy);

	/**
	 * <p>
	 * 查询发生品
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryFsp(QEntity<FspVO> qentity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryGxht(QEntity<GxhtVO> qentity);

	/**
	 * <p>
	 * 制作发生品订货合同
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String doCjpHt(FspHtVO vo, User user);

	/**
	 * <p>
	 * 删除W开头的订货合同
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String delCjpHt(String[] ids);

	/**
	 * <p>
	 * 查询订货进度
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryDhjd(QEntity<DhVO> qentity);

	/**
	 * <p>
	 * 获取购销合同
	 * </p>
	 * 
	 * @param id
	 * @return GxhtTp
	 */
	public GxhtTp getGxht(Serializable id);

	/**
	 * <p>
	 * 保存购销合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveGxht(GxhtTp entity);

	/**
	 * <p>
	 * 修改购销合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateGxht(GxhtTp entity);

	/**
	 * <p>
	 * 判断购销合同是否存在
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExistedGxht(String id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getGxhtForJs(String id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param user
	 * @return GxhtTp
	 */
	public GxhtTp getByUser(String user);

	/**
	 * <p>
	 * 设置合同日期
	 * </p>
	 * 
	 * @param ids
	 * @param htqi
	 */
	public void doHtqi(String[] ids, String htqi);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @param khno
	 */
	public void updateKhno(String[] ids, String khno);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchDhuoChDatas(DhuoChVO vo, OutputStream os);

	/**
	 * <p>
	 * 修改订货合同状态
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param stat
	 */
	public void updateStat(String dhno, String line, String stat);

}
