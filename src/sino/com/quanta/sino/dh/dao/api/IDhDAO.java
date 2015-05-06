/**
 * 
 */
package com.quanta.sino.dh.dao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dh.vo.Dhjdmx;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.DhuoChmxVO;
import com.quanta.sino.dh.vo.DhuoVO;
import com.quanta.sino.dh.vo.FspZlVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;

/**
 * <p>
 * 订货单数据访问接口
 * </p>
 * <p>
 * create: 2011-2-2 上午10:49:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IDhDAO {
	/**
	 * <p>
	 * 新增订货单
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(DhuoTp entity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<DhuoTp> entities);

	/**
	 * <p>
	 * 更新订货单
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(DhuoTp entity);

	/**
	 * <p>
	 * 根据中文件编号删除对应的信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 批量删除订货合同
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(List<DhuoTpPk> ids);

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
	 * 获取订货NO.与行号或订货NO.与用户代码获取订货合同信息
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
	 * </p>
	 * 
	 * @param dhno
	 * @param orders
	 * @return DhuoTp
	 */
	public DhuoTp get(String dhno, String orders);

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
	 * 通过订货No.列表获取对应的订货合同信息
	 * </p>
	 * 
	 * @param ids
	 * @return List<DhuoTp>
	 */
	public List<DhuoTp> findByIds(List<String> ids);

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
	 * 根据合同查询订货进度明细
	 * </p>
	 * 
	 * @param dhnoStart
	 * @param dhnoEnd
	 * @return List<Dhjdmx>
	 */
	public List<Dhjdmx> fetchDhjdmxs(String dhnoStart, String dhnoEnd);

	/**
	 * <p>
	 * 查询订货进度信息
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @return DhuoVO
	 */
	public DhuoVO getDhjd(String dhno, String line);

	/**
	 * <p>
	 * 获得品质管理指定日期的生产卷数
	 * </p>
	 * 
	 * @param khfkId
	 * @return Long
	 */
	public Long count(String dhno);

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
	 * 修改订货合同状态
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param stat
	 */
	public void updateStat(String dhno, String line, String stat);

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
	 * @param yuny
	 *            运用规格
	 * @param xpho
	 *            尺寸.厚
	 * @param xpkn
	 *            尺寸.宽
	 * @param xpcn
	 *            尺寸.长
	 * @param face
	 *            表面
	 * @param xpzk
	 *            现 品状况
	 * @param chan
	 *            产量代码
	 * @param dxls
	 * @return List<FspZlVO>
	 */
	public List<FspZlVO> queryFsp(String yuny, Double xpho, Double xpkn,
			Double xpcn, String face, String xpzk, String chan,
			Map<String, String> dxls);

	/**
	 * <p>
	 * 自动分配次级品
	 * </p>
	 * 
	 * @param jbnos
	 * @param dhnoAndLine
	 * @param abbr
	 * @param pzno
	 */
	public void fpCjp(List<String> jbnos, String dhnoAndLine, String abbr,
			String pzno);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jhqiB
	 * @param jhqiE
	 * @param chqiB
	 * @param chqiE
	 * @return List<DhuoChVO>
	 */
	public List<DhuoChmxVO> queryDhuoChVO(DhuoChVO vo);

}
