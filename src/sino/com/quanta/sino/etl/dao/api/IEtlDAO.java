package com.quanta.sino.etl.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.etl.vo.EtlBanTjVO;
import com.quanta.sino.etl.vo.EtlSbTjVO;
import com.quanta.sino.etl.vo.XzVO;
import com.quanta.sino.etl.vo.ZpSLVO;
import com.quanta.sino.orm.ETLStopTp;
import com.quanta.sino.orm.ETLVariTp;
import com.quanta.sino.orm.ETLsbTp;
import com.quanta.sino.orm.EtlsLp;
import com.quanta.sino.orm.PngzLp;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZszrTp;

/**
 * <p>
 * 实绩录入数据访问层
 * </p>
 * <p>
 * create: 2011-1-14 上午10:08:22
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public interface IEtlDAO {
	/**
	 * <p>
	 * 删除停止登录
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteETLStopTp(Serializable id);

	/**
	 * <p>
	 * 保存特记
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveETLVariTp(ETLVariTp entity);

	/**
	 * <p>
	 * 修改特记
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateETLVariTp(ETLVariTp entity);

	/**
	 * <p>
	 * 查询特记
	 * </p>
	 * 
	 * @param dasr
	 * @param ban
	 * @param vari
	 * @return
	 */
	public ETLVariTp getETLVariTp(Date dasr, String ban);

	/**
	 * <p>
	 * 查询一天的特记
	 * </p>
	 * 
	 * @param dasr
	 * @return List<ETLVariTp>
	 */
	public List<ETLVariTp> findETLVariTps(Date dasr);

	/**
	 * <p>
	 * 取停止数据
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public ETLStopTp getETLStopTp(Serializable id);

	/**
	 * <p>
	 * 按日期查询休止I停线时间
	 * </p>
	 * 
	 * @param firstdate
	 * @param enddate
	 * @return
	 */
	public Double getETLStopXzdaTpbydasr(Date firstdate, Date enddate);

	/**
	 * <p>
	 * 按日期查询停线时间
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public List<ETLStopTp> findETLStopTps(Date date);

	/**
	 * <p>
	 * 按日期查询停线时间
	 * </p>
	 * 
	 * @param firstdate
	 * @param enddate
	 * @return
	 */
	public Double getETLStopTpbydasr(Date firstdate, Date enddate);

	/**
	 * <p>
	 * 按日期查询停线时间
	 * </p>
	 * 
	 * @param dasr
	 * @return
	 */
	public Double getETLStopTpbydasr(Date dasr);

	/**
	 * <p>
	 * 按日期班查询停线时间
	 * </p>
	 * 
	 * @param dasr
	 * @param ban
	 * @return
	 */
	public Double getStopTime(Date dasr, String ban);

	/**
	 * <p>
	 * 保存停线数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveETLStopTp(ETLStopTp entity);

	/**
	 * <p>
	 * 修改停线数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateETLStopTp(ETLStopTp entity);

	/**
	 * <p>
	 * 查询停线
	 * </p>
	 * 
	 * @param dasr
	 * @param stop
	 * @return
	 */
	public ETLStopTp getETLStopTp(Date dasr, String stop);

	/**
	 * <p>
	 * 查询停线列表
	 * </p>
	 * 
	 * @param date
	 * @param ban
	 * @return
	 */
	public List<ETLStopTp> finEtlStops(Date date, String ban);

	/**
	 * <p>
	 * 统计休止
	 * </p>
	 * 
	 * @param begin
	 * @param end
	 * @return XzVO
	 */
	public XzVO getXzVO(Date begin, Date end);

	/**
	 * <p>
	 * 更新速报
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateETLsbTp(ETLsbTp entity);

	/**
	 * <p>
	 * 查询 速报列表
	 * </p>
	 * 
	 * @param begin
	 * @param end
	 * @return List<ETLsbTp>
	 */
	public List<ETLsbTp> findETLsbTps(Date begin, Date end);

	/**
	 * <p>
	 * 保存速报数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveETLsbTp(ETLsbTp entity);

	/**
	 * <p>
	 * 取速报数据
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public ETLsbTp getETLsbTp(Serializable id);

	/**
	 * <p>
	 * 统计休止
	 * </p>
	 * 
	 * @param begin
	 * @param end
	 * @return XzVO
	 */
	public EtlSbTjVO getSbTjVO(Date begin, Date end);

	/**
	 * <p>
	 * 判断指定日期的速报是否存在
	 * </p>
	 * 
	 * @param dasr
	 * @return boolean
	 */
	public boolean isExistedEtlsb(Date dasr);

	/**
	 * <p>
	 * 待生产的切板制品
	 * </p>
	 * 
	 * @param qentity
	 */
	public void querySL(QEntity<ZpSLVO> qentity);

	/**
	 * <p>
	 * 查询单个指示DB(装入卷板)
	 * </p>
	 * 
	 * @param id
	 *            指示书No.和卷板号组合主键
	 * @return
	 */
	public ZszrTp getZszrTp(Serializable id);

	/**
	 * <p>
	 * 更新订货指示DB
	 * </p>
	 * 
	 * @param id
	 *            指示书No
	 * @param ckzl
	 *            出口包装No.终了No.
	 * @param ckzx
	 *            出口包装No.最新终了No.
	 * @param zsbj
	 *            指示完了标记
	 * @param zlzz
	 *            装入中止标记
	 * @param zsny
	 *            指示完了时间
	 */
	public void updateZsdhTp(Serializable id, String zsbj, String zlzz,
			Date zsny);

	/**
	 * <p>
	 * 更新订货DB的ETL合格量
	 * </p>
	 * 
	 * @param dhno
	 *            订货NO
	 * @param line
	 *            行号
	 * @param etlh
	 *            ETL合格量(吨)
	 */
	public void updateDhuoTp(String dhno, String line, Double etlh);

	/**
	 * <p>
	 * 保存指示DB(出端卷板/PILE)
	 * </p>
	 * 
	 * @param entity
	 *            实体
	 */
	public void saveZscdTp(ZscdTp entity);

	/**
	 * <p>
	 * 删除指示DB(出端卷板/PILE)
	 * </p>
	 * 
	 * @param id
	 *            出端卷板no
	 */
	public void deleteZscdTp(Serializable id);

	/**
	 * <p>
	 * 删除日志
	 * </p>
	 * 
	 * @param id
	 *            出端卷板no
	 */
	public void deleteRiziLp(Serializable id);

	/**
	 * <p>
	 * 保存指示对象DB
	 * </p>
	 * 
	 * @param entity
	 *            实体
	 */
	public void saveZsdxTp(ZsdxTp entity);

	/**
	 * <p>
	 * 保存日志
	 * </p>
	 * 
	 * @param entity
	 *            实体
	 */
	public void saveRiziLp(RiziLp entity);

	/**
	 * <p>
	 * 根据订货NO取制品库的最大出口包装No
	 * </p>
	 * 
	 * @param dhnoAndLine
	 *            订货NO和行号
	 * @return Integer 最大出口包装No
	 */
	public Integer getMaxCkno(String dhnoAndLine);

	/**
	 * <p>
	 * 批量更新制品库的附着量
	 * </p>
	 * 
	 * @param rczpno
	 *            卷板NO
	 * @param sczm
	 *            实绩附着量.正
	 * @param scfm
	 *            实绩附着量.反
	 */
	public void updateAllZp(String rczpno, Double sczm, Double scfm);

	/**
	 * <p>
	 * 更新订货DB的分配量(吨)
	 * </p>
	 * 
	 * @param dhno
	 *            订货NO
	 * @param line
	 *            行号
	 * @param yclh
	 *            余材量(吨)
	 */
	public void updateDhFp(String dhno, String line, Double yclh);

	/**
	 * <p>
	 * ETL（镀锡）实绩日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveEtlsLp(EtlsLp entity);

	/**
	 * <p>
	 * 获取制品库引用
	 * </p>
	 * 
	 * @param id
	 *            卷板No/PlieNo
	 * @return ZpngTp 实体
	 */
	public ZpngTp getZpRef(Serializable id);

	/**
	 * <p>
	 * 查询制品
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZpngTp> qentity);

	/**
	 * <p>
	 * 获取ETL品质管理记录日志
	 * </p>
	 * 
	 * @param id
	 *            装入卷板NO
	 * @return list 实体
	 */
	public List<PngzLp> getPngzLp(Serializable id);

	/**
	 * <p>
	 * 保存ETL品质管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void savePngzLp(PngzLp entity);

	/**
	 * <p>
	 * 取装入卷板实体引用
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public ZszrTp getZszrTpRef(Serializable id);

	/**
	 * <p>
	 * 按班统计ETL产量数据
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 * @return Map<String,EtlZuTjVO>
	 */
	public Map<String, EtlBanTjVO> findBanTjVO(Date scsjBegin, Date scsjEnd);

	/**
	 * <p>
	 * 统计一个时间段的ETL产量数据
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 * @return EtlZuTjVO
	 */
	public EtlBanTjVO getTjVO(Date scsjBegin, Date scsjEnd);
}
