package com.quanta.sino.etl.dao.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.etl.vo.ZptjVO;
import com.quanta.sino.kcgl.vo.ZppdVO;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品在库记录的数据访问层接口
 * </p>
 * <p>
 * create: 2011-1-18 上午08:51:20
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface IZpDAO {
	/**
	 * <p>
	 * 根据装入卷查询选别制品
	 * </p>
	 * 
	 * @param rczpno
	 * @return
	 */
	public List<ZpngTp> listPszp(String rczpno);

	/**
	 * <p>
	 * 判断是否存在未完成的制品
	 * </p>
	 * 
	 * @param zsno
	 * @param jbno
	 * @return
	 */
	public boolean isExistedZpng(String zsno, String jbno);

	/**
	 * <p>
	 * 判断订货合同是否存在制品
	 * </p>
	 * 
	 * @param dhno
	 * @return boolean
	 */
	public boolean isExistedByDhno(String dhno);

	/**
	 * <p>
	 * 判断制品是否存在保留标记
	 * </p>
	 * 
	 * @param jbnos
	 * @return boolean
	 */
	public boolean isExistedBlbj(String jbnos);

	/***
	 * <p>
	 * 不良扣除联络单打印更新
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PlieNo
	 */
	public void updateEtlLodyPrint(String jbno);

	/**
	 * <p>
	 * 获取单条制品记录
	 * </p>
	 * 
	 * @param jbno
	 *            卷板号
	 * @return ZpngTp
	 */
	public ZpngTp getZp(Serializable jbno);

	/**
	 * <p>
	 * 根据入侧制品No，获得制品记录
	 * </p>
	 * 
	 * @param rczpno
	 * @return ZpngTp
	 */
	public ZpngTp getByRczpno(String rczpno);

	/**
	 * <p>
	 * 获取单条制品记录引用
	 * </p>
	 * <p>
	 * author:许德建[xudejian@126.com]
	 * </p>
	 * 
	 * @param id
	 *            卷板号
	 * @return ZpngTp
	 */
	public ZpngTp getRef(Serializable id);

	/**
	 * <p>
	 * 根据制品号组合字符串查询制品集合,并按照订货合同号升序排序
	 * </p>
	 * 
	 * @param jbnos
	 * @return List<ZpngTp>
	 */
	public List<ZpngTp> listZp(Collection<String> jbnos);

	/**
	 * <p>
	 * 根据订货No.组合字符串指示对象对应的制品号集合，并根据制品号集合获取制品信息集合
	 * </p>
	 * <p>
	 * author:黄美[mei.huang.miss@gmail.com]
	 * </p>
	 * 
	 * @param dhnos
	 *            订货No.
	 * @return List<ZpngTp>
	 */
	public List<ZpngTp> listZpByZsDh(Collection<String> dhnos);

	/**
	 * <p>
	 * 根据订货号取制品对象集合
	 * </p>
	 * 
	 * @param dhno
	 *            订货号
	 * @param xpzks
	 *            现品状况
	 * @return List<ZpngTp>
	 */
	public List<ZpngTp> listZpByDh(String dhno, String... xpzks);

	/**
	 * <p>
	 * 根据分配号取制品对象集合
	 * </p>
	 * 
	 * @param fpno
	 *            分配号
	 * @return List<ZpngTp>
	 */
	public List<ZpngTp> listZpByFp(String fpno);

	/**
	 * <p>
	 * 根据入侧卷号取其生成的所有制品对象
	 * </p>
	 * 
	 * @param rczpno
	 *            入侧卷板号
	 * @return
	 */
	public List<ZpngTp> listZpByRc(String rczpno);

	/**
	 * <p>
	 * 保存制品记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(ZpngTp entity);

	/**
	 * <p>
	 * 保存多个制品记录
	 * </p>
	 * 
	 * @param entitys
	 */
	public void saveAll(List<ZpngTp> entities);

	/**
	 * <p>
	 * 更新制品记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(ZpngTp entity);

	/**
	 * <p>
	 * 删除制品记录
	 * </p>
	 * 
	 * @param jbno
	 */
	public void delete(Serializable jbno);

	/**
	 * <p>
	 * 制品分页查询
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZpngTp> qentity);

	/**
	 * <p>
	 * 查询制品盘点记录
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZppd(QEntity<ZppdVO> qentity);

	/**
	 * <p>
	 * 根据指示书号取制品对象集合
	 * </p>
	 * <p>
	 * author: 陈小攀
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @return List[ZpngTp]
	 */
	public List<ZpngTp> listZpByZs(String zsno);

	/**
	 * <p>
	 * 查询SL指示书明细
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param zsno
	 * @return
	 */
	public List<ZpngTp> listZssmx(String zsno);

	/**
	 * <p>
	 * 根据出货号取制品对象集合
	 * </p>
	 * <p>
	 * authro:陈小攀
	 * </p>
	 * 
	 * @param chno
	 *            出货号
	 * @return List<ZpngTp>
	 */
	public List<ZpngTp> listZpByChno(String chno);

	/**
	 * <p>
	 * 根据原材号取制品对象集合
	 * </p>
	 * <p>
	 * author: 张红国
	 * </p>
	 * 
	 * @param jbno
	 *            原材号
	 * @return List[ZpngTp]
	 */
	public List<ZpngTp> listZpByJbno(String jbno);

	/**
	 * <p>
	 * 根据原材号取制品对象集合
	 * </p>
	 * 
	 * @param jbno
	 * @param chan
	 * @return
	 */
	public List<ZpngTp> listZpzlByJbno(String jbno);

	/**
	 * <p>
	 * 获得装入卷的产出重量
	 * </p>
	 * 
	 * @param int
	 * @return long
	 */
	public int getCczl(String jbno);

	/**
	 * <p>
	 * 获取唯一对象
	 * </p>
	 * <p>
	 * author: 陈小攀
	 * </p>
	 * 
	 * @param dhno
	 * @return ZpngTp
	 */
	public ZpngTp getUnique(String dhno);

	/**
	 * <p>
	 * 是否存在同一入端的别的制品
	 * </p>
	 * <p>
	 * author: 张红国
	 * </p>
	 * 
	 * @param jbno
	 *            制品NO
	 * @param rczpno
	 *            入端no
	 * @return boolean
	 */
	public boolean isZp(String jbno, String rczpno);

	/**
	 * <p>
	 * 获取包装清单打印数据
	 * </p>
	 * 
	 * @param jbnos
	 * @return List[PbzqdVO]
	 */
	public List<PbzqdVO> findBzqdVos(List<String> jbnos);

	/**
	 * <p>
	 * 判断制品号是否存在
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExisted(String jbno);

	/**
	 * <p>
	 * 统计时间段的制品数量和件数
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param rqS
	 * @param rqE
	 * @param xpzk
	 * @return ZptjVO
	 */
	public ZptjVO getKbtj(Date rqS, Date rqE, String xpzk);

	/**
	 * <p>
	 * 设定制品硬度
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 * @param llyd
	 * @param ydsj
	 */
	public void updateYd(String jbno, Integer ying, Integer llyd, Date ydsj);

	/**
	 * <p>
	 * 设定制品硬度
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 */
	public void updateYd(String jbno, Integer ying);

	/**
	 * <p>
	 * 设定制品硬度
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 */
	public void updateYing(String jbno, Integer ying);

	/**
	 * <p>
	 * 更新紧急
	 * </p>
	 * 
	 * @param ids
	 * @param jinj
	 */
	public void updateJinj(String[] ids, String jinj);

	/**
	 * <p>
	 * 根据堆场统计重量
	 * </p>
	 * 
	 * @param dhno
	 * @param duic
	 * @return Long
	 */
	public Long getZlByDuic(String dhno, String duic);

	/**
	 * <p>
	 * 作废装箱指示书
	 * </p>
	 * 
	 * @param zxno
	 */
	public void deleteZxzs(String zxno);

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

	/**
	 * <p>
	 * 判断制品是否有作业停止标记
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	public String getZtbjJbnos(List<String> jbnos);

	/**
	 * <p>
	 * 判断制品是否有现品保留标记
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	public String getXpblJbnos(List<String> jbnos);

	/**
	 * <p>
	 * 修改SL指示书所属制品的进度标记
	 * </p>
	 * 
	 * @param zsno
	 * @param jbnos
	 */
	public void updateZpForSlZss(String zsno, List<String> jbnos);
}
