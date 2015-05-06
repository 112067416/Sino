package com.quanta.sino.zs.bo.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.zs.vo.JqcVO;
import com.quanta.sino.zs.vo.ZsVO;
import com.quanta.sino.zs.vo.ZsdhVO;
import com.quanta.sino.zs.vo.ZsdhVVO;
import com.quanta.sino.zs.vo.ZsdhViewVO;
import com.quanta.sino.zs.vo.ZsdxVO;
import com.quanta.sino.zs.vo.ZssXpVO;

public interface IZsBO {
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
	 * 根据卷板NO查询指示书NO
	 * </p>
	 * 
	 * @param jbno
	 */
	public String getZsno(String jbno, String type);

	/**
	 * <p>
	 * 更新指示对象
	 * </p>
	 * 
	 * @param zss
	 *            指示书
	 */
	public void updateZsdh(ZsdhTp zss);

	/**
	 * <p>
	 * 更新指示对象的打印标志
	 * </p>
	 * 
	 * @param zss
	 *            指示书NO
	 */
	public void updateZsPrint(List<String> zss);

	/**
	 * <p>
	 * 更新指示对象的缺陷打印标志
	 * </p>
	 * 
	 * @param zss
	 *            指示书NO
	 */
	public void updateZsQxPrint(List<String> zss);

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
	 * 删除指示对象
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZsdxTp(Serializable id);

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
	 * 删除指示DB(装入卷板)
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZszrTp(Serializable id);

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
	 * 新增指示DB
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZsdX(ZsdxTp entity);

	/**
	 * <p>
	 * 查询指示DB
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZsdx(QEntity<ZsdxVO> qentity);

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
	 * 查询单个订货指示书DB
	 * </p>
	 * 
	 * @param id
	 *            指示书no
	 * @return ZsdhTp
	 */
	public ZsdhViewVO getZsdhTpVO(Serializable id, String type);

	/**
	 * <p>
	 * 根据指示书no查询多个原材
	 * </p>
	 * 
	 * @param zsno
	 *            指示书no
	 * @return List
	 */
	public List<ZsdhVVO> getYcaiByZsno(String zsno);

	/**
	 * <p>
	 * 撤消指示书
	 * </p>
	 * 
	 * @param zsno
	 *            指示书No.
	 * @param dhno
	 *            订货NO.
	 */
	public void deleteZsdh(String zsno);

	/**
	 * <p>
	 * 查询订货指示书DB
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
	 * 指示书生成
	 * </p>
	 * 
	 * @param jbnos
	 *            卷板No.和分配No组合数组(JBNO-FPNO)
	 * @param nozsnos
	 *            不能生成的指示书卷板号
	 * @param isPrint
	 *            打印标志
	 * @return List<String> 指示书号列表
	 */
	public List<String> zc(List<String> jbnos, List<String> nozsnos,
			boolean isPrint);

	/**
	 * <p>
	 * 订货指示书查询
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 新增订货指示DB
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZsdh(ZsdhTp entity);

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
	 * 取消分派
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteFP(String[] ids);

	/**
	 * <p>
	 * 保存指示书排序
	 * </p>
	 * 
	 * @param idsList
	 *            指示书号数组
	 */
	public void saveDyZzs(List<String> idsList);

	/**
	 * <p>
	 * 查询剪切材
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param qEntity
	 *            查询QE
	 */
	public void queryJqc(QEntity<JqcVO> qEntity);

	/**
	 * <p>
	 * 指示书作成：
	 * </p>
	 * <p>
	 * <b>数据库更新</b>
	 * <ul>
	 * <li>新增指示订货DB(指示书)</li>
	 * <li>更新制品表的指示书号与SL指示进度(更新为1)</li>
	 * <li>更新指示对象的状态为‘已指示’</li>
	 * <li>新增装入指示记录</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Author 张红国
	 * </p>
	 * 
	 * @param nos
	 *            卷板号
	 * @param user
	 *            当前登陆用户
	 * @return String 作成的所有指示书字符串组合
	 */
	public List<String> slzc(List<String> jbnos, List<String> nozsnos,
			User user, boolean isPrint);

	/**
	 * <p>
	 * 查询未完成的指示书
	 * </p>
	 * <p>
	 * <b>默认查询条件：</b>
	 * <ul>
	 * <li>状态：1-已分派)</li>
	 * <li>指示完了标记：0-未完成</li>
	 * <li>余材状况：2-中间品</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Author 方元龙
	 * </p>
	 * 
	 * @param qEntity
	 *            查询QE
	 */
	public void queryWwc(QEntity<ZsVO> qEntity);

	/**
	 * <p>
	 * SL指示书撤消(原指示书中的制品变为余材)
	 * </p>
	 * <p>
	 * <b>数据库更新</b>
	 * <ul>
	 * <li>删除指示订货DB(指示书)</li>
	 * <li>更新制品表的用户略称、订货号、指示书号、SL进度指示、分配号为空，分配余材为2-余材</li>
	 * <li>删除指示对象的状态为‘未指示’</li>
	 * <li>删除装入指示记录</li>
	 * <li>新增分配操作记录</li>
	 * <li>更新订货合同的分配量、SL指示量</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Author 张良
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @param user
	 *            当前登陆用户
	 */
	public void doSlZssCx(String zsno, User user);

	/**
	 * <p>
	 * 指示书中所包含的现品集合(自动判定指示书的类型)
	 * </p>
	 * 
	 * @param zss
	 *            指示书对象
	 * @return List[ZssXpVO] 指示书中的现品列表
	 */
	public List<ZssXpVO> listZssXp(ZsdhTp zss);

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
