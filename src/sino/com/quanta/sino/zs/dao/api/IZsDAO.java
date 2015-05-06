package com.quanta.sino.zs.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.QxbgTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.zs.vo.JqcVO;
import com.quanta.sino.zs.vo.ZsVO;
import com.quanta.sino.zs.vo.ZsdhVO;
import com.quanta.sino.zs.vo.ZsdxVO;

/**
 * <p>
 * 指示书数据访问接口
 * </p>
 * <p>
 * create: 2010-12-22 上午10:39:34
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public interface IZsDAO {
	/**
	 * <p>
	 * 保存指示书备注
	 * </p>
	 * 
	 * @param zsno
	 * @param remk
	 */
	public void updateREMK(String zsno, String remk);

	/**
	 * <p>
	 * 取消垫木设置
	 * </p>
	 * 
	 * @param ids
	 */
	public void deleteSLDM(String[] ids, String type);

	/**
	 * <p>
	 * 更新SL指示书垫木状态
	 * </p>
	 * 
	 * @param ids
	 * @param sfdm
	 */
	public void updateSLZsDM(String[] ids, String sfdm, String type);

	/**
	 * <p>
	 * 更新SL指示书紧急
	 * </p>
	 * 
	 * @param ids
	 * @param jinj
	 * @param sfcl
	 */
	public void updateSLZsJinj(String[] ids, String jinj, String sfcl);

	/**
	 * <p>
	 * 查询指示对象DB
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZsdxVO> qentity);

	/**
	 * <p>
	 * 取订货指示引用
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public ZsdhTp getRefZsdhTp(Serializable id);

	/**
	 * <p>
	 * 更新订货指示打印标志
	 * </p>
	 * 
	 * @param zss
	 */
	public void updateZsPrint(List<String> zss);

	/**
	 * <p>
	 * 更新指示对象的缺陷打印标志
	 * </p>
	 * 
	 * @param zss
	 */
	public void updateZsQxPrint(List<String> zss);

	/**
	 * <p>
	 * 查询订货指示对象DB
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZsdhTp(QEntity<ZsdhTp> qentity);

	/**
	 * <p>
	 * 查询待生产订货指示对象DB
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryDscZsdhTp(QEntity<ZsdhVO> qentity);

	/**
	 * <p>
	 * 更新指示对象DB的状态
	 * </p>
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateStat(Serializable id, String stat);

	/**
	 * <p>
	 * 新增订货指示DB
	 * </p>
	 * 
	 * @param entity
	 */
	public void savezsdh(ZsdhTp entity);

	/**
	 * <p>
	 * 新增指示DB
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZsdX(ZsdxTp entity);

	/**
	 * <p>
	 * 更新原材卷板
	 * </p>
	 * 
	 * @param id
	 *            原材原板NO
	 * @param zsno
	 *            指示书NO
	 * @param jdez
	 *            进度标记.ETL指示
	 */
	public void updateYcaiTp(Serializable id, String zsno, String jdez);

	/**
	 * <p>
	 * 更新订货DB
	 * </p>
	 * 
	 * @param id
	 *            订货NO和行号
	 * @param etlz
	 *            卷重
	 */
	public void updateDhuoTp(Serializable id, Integer etlz);

	/**
	 * <p>
	 * 撤消原材卷板
	 * </p>
	 * 
	 * @param id
	 *            原材原板NO
	 * @param jdez
	 *            进度标记.ETL指示
	 * @param fpyc
	 *            分配/余材标记
	 */
	public void cancelYcaiTp(Serializable id, String jdez, String fpyc,
			String stat);

	/**
	 * <p>
	 * 查询单个指示对象DB
	 * </p>
	 * 
	 * @param id
	 *            卷板No.和分配号组合主键
	 * @return ZsdxTp
	 */
	public ZsdxTp get(Serializable id);

	/**
	 * <p>
	 * 根据分配No.，获得单个指示对象
	 * </p>
	 * 
	 * @param fpno
	 *            分配No
	 * @return ZsdxTp
	 */
	public ZsdxTp getUniqueZsdx(String fpno);

	/**
	 * <p>
	 * 批量新增指示DB(装入卷板)
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveZszrTPs(List<ZszrTp> entities);

	/**
	 * <p>
	 * 新增指示DB(装入卷板)
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZszrTp(ZszrTp entity);

	/**
	 * <p>
	 * 批量新增卷板缺陷表保管DB
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveQxbgTPs(List<QxbgTp> entities);

	/**
	 * <p>
	 * 查询单个订货指示书DB
	 * </p>
	 * 
	 * @param id
	 *            指示书no
	 * @return ZsdhTp
	 */
	public ZsdhTp getZsdhTp(Serializable id);

	/**
	 * <p>
	 * 分派订货指示对象DB
	 * </p>
	 * 
	 * @param id
	 * @param jinj
	 *            紧急程度
	 * @param shch
	 *            生产线
	 */
	public void updateZsdhTp(String[] ids, String jinj, String shch, String remk);

	/**
	 * <p>
	 * 更新指示订货对象
	 * </p>
	 * 
	 * @param entity
	 *            指示订货对象
	 */
	public void updateZsdh(ZsdhTp entity);

	/**
	 * <p>
	 * 更新指示对象
	 * </p>
	 * 
	 * @param entity
	 *            指示对象实体
	 */
	public void updateZsdx(ZsdxTp entity);

	/**
	 * <p>
	 * 取消分派
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteFP(Serializable id);

	/**
	 * <p>
	 * 删除指示对象DB
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZsdxTp(Serializable id);

	/**
	 * <p>
	 * 删除订货指示对象DB
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZsdhTp(Serializable id);

	/**
	 * <p>
	 * 删除指示DB(装入卷板)
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZszrTP(Serializable id);

	/**
	 * <p>
	 * 获取指示DB(装入卷板)引用
	 * </p>
	 * 
	 * @param id
	 *            卷板No/PlieNo
	 */
	public ZszrTp getZszrTpRef(Serializable id);

	/**
	 * <p>
	 * 根据装入卷板No/PileNo，查询装入卷板
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zrjb
	 * @return ZszrTp
	 */
	public ZszrTp getUniqueZszrTp(String zrjb);

	/**
	 * <p>
	 * 判断是否有该装入卷
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zrjb
	 * @return boolean
	 */
	public boolean isExistedZszrTp(String zrjb);

	/**
	 * <p>
	 * 删除卷板缺陷表保管DB
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteQxbgTp(Serializable id);

	/**
	 * <p>
	 * 保存指示书排序
	 * </p>
	 * 
	 * @param idsList
	 *            指示书号
	 * @param sort
	 *            序号
	 */
	public void saveDyZzs(Serializable id, Double sort);

	/**
	 * <p>
	 * 查询剪切材
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryJqc(QEntity<JqcVO> qentity);

	/**
	 * <p>
	 * 查询剪切材
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryWwc(QEntity<ZsVO> qentity);

	/**
	 * <p>
	 * 查询指示对象
	 * </p>
	 * <p>
	 * author：张良
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZsdxtp(QEntity<ZsdxTp> qentity);

	/**
	 * <p>
	 * 取指示书中分配的现品的完成情况
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @param type
	 *            类型：1-未完成个数；2-已完成个数; 0/其他-总个数
	 * @return Long 个数。注：-1时表示异常
	 */
	public long getZssWcqk(String zsno, int type);

	/**
	 * <p>
	 * 根据分配号和现品号，取消指示对象
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbnos
	 *            现品号
	 * @param fpno
	 *            分配号
	 */
	public void doQxfp(List<String> jbnos, String fpno);

	/**
	 * <p>
	 * 根据分配号修改指示对象的分配量和修改时间
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param fpno
	 *            分配号
	 * @param cqzs
	 *            分配重量
	 * @param upda
	 *            修改时间
	 */
	public void updateZsdx(String fpno, Integer cqzs, Date upda);

	/**
	 * <p>
	 * 批量新增指示对象
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveZsdxTps(List<ZsdxTp> entities);

	/**
	 * <p>
	 * 判断订货合同是否已经做分配
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param dhno
	 * @return boolean
	 */
	public boolean isFpForDh(String dhno);

	/**
	 * <p>
	 * 通过复合主键获取订货指示信息,该实体信息由容器托管，其属性更改将同步到数据库
	 * </p>
	 * 
	 * @param id
	 * @return DhuoTp
	 */
	public ZsdhTp getRef(Serializable id);

	/**
	 * <p>
	 * 取消分派
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteSLFP(String[] ids);

}
