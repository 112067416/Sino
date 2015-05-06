package com.quanta.sino.etl.bo.api;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.etl.vo.EtlStopMxVO;
import com.quanta.sino.etl.vo.EtlStopVO;
import com.quanta.sino.etl.vo.EtlSydjVO;
import com.quanta.sino.etl.vo.SBVO;
import com.quanta.sino.etl.vo.SBmxVO;
import com.quanta.sino.etl.vo.SjMainVO;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.etl.vo.SjlrVO;
import com.quanta.sino.etl.vo.ZpSLVO;
import com.quanta.sino.etl.vo.ZsViewVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.ETLVariTp;
import com.quanta.sino.orm.PngzLp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;

/**
 * <p>
 * 实绩录入业务层
 * </p>
 * <p>
 * create: 2011-1-14 上午09:43:26
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public interface IEtlBO {
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
	 * 保存ETL停线信息
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param vo
	 */
	public void saveEtlStop(EtlStopVO vo);

	/**
	 * <p>
	 * ETL停线记录
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public List<EtlStopMxVO> findEtlStopDatas(Date date, String ban);

	/**
	 * <p>
	 * 取速报数据
	 * </p>
	 * 
	 * @author 张良
	 * @return List<SBSjlrVO>
	 */
	public List<SBmxVO> findSbDatas(Date dasr);

	/**
	 * <p>
	 * 保存速报
	 * </p>
	 * 
	 * @author 张良
	 * @param vo
	 */
	public void saveEtlsb(SBVO vo);

	/**
	 * <p>
	 * ETL速报
	 * </p>
	 * <p>
	 * 描述：张良重做
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchEtlsb(EtlSydjVO vo, OutputStream os);

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
	 * 检查附着量
	 * </p>
	 * 
	 * @param dhno
	 */
	public void checkFu(DhuoTp dhuo, Double sczm, Double scfm);

	/**
	 * <p>
	 * 查询制品信息
	 * </p>
	 * 
	 * @return
	 */
	public SjSaveVO getZpView(String jbno);

	/**
	 * <p>
	 * 检查界面输入。
	 * </p>
	 * 
	 * @param sjVO
	 *            界面输入实体
	 */
	public void validate(SjSaveVO sjVO);

	/**
	 * <p>
	 * 判断存在指示书和原材原板的操作
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @param jbno
	 *            原材原板NO
	 * @param elin
	 *            用户生产线
	 * @return C为卷材，S为板材，NULL为不存在
	 */
	public String get(String zsno, String jbno, String elin);

	/**
	 * <p>
	 * 判断制品的操作
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PlieNo
	 * @return C为卷材，S为板材，NULL为不存在
	 */
	public String get(String jbno);

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
	 * 计算毛重
	 * </p>
	 * 
	 * @param zsno
	 * @param jinz
	 * @return
	 */
	public Integer getMazl(String zsno, Integer jinz);

	/**
	 * <p>
	 * 计算出货重量
	 * </p>
	 * 
	 * @param zsno
	 *            指示书NO
	 * @param sjzl
	 *            实称重量
	 * @param jbcn
	 *            卷取长
	 * @param cutc
	 *            CUT长
	 * @param loss
	 *            LOSS长
	 * @param loss2
	 *            LOSS2长
	 * @return Integer
	 */
	public Integer getZpzl(String zsno, Integer sjzl, Integer jbcn,
			Integer cutc, Integer loss, Integer loss2, String chan, String mode);

	/**
	 * <p>
	 * 计算卷取长
	 * </p>
	 * 
	 * @param sjzl
	 *            实称重量
	 * @param zsno
	 *            指示NO
	 * @return
	 */
	public Integer getJbcn(Integer sjzl, String zsno);

	/**
	 * <p>
	 * 判断制品的操作
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No /PlieNo
	 * @return C为卷材，S为板材，NULL为不存在
	 */
	public String getMode(String jbno);

	/**
	 * <p>
	 * 查询正在实绩录入的卷板号
	 * </p>
	 * 
	 * @param SjMainVO
	 *            界面实体
	 * @return
	 */
	public void getZzsj(SjMainVO vo);

	/**
	 * <p>
	 * 查询订货DB的附着量和制品尺寸
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 * @param jbno
	 *            原材原板NO
	 * @return String
	 */
	public SjlrVO getFuZi(String zsno, String jbno);

	/**
	 * <p>
	 * 查询制品库信息
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PlieNo
	 * @param elin
	 *            用户生产线
	 * @return String
	 */
	public String getZpInfo(String jbno, String elin, String mode);

	/**
	 * <p>
	 * 实绩录入( 新增制品在库DB,更新(新增)原材卷板DB,更新订货指示DB,
	 * 更新订货DB,更新示DB(装入卷板)，新增指示DB(出端卷板/PILE)，
	 * 新增指示对象DB，新增日志DB，新增实绩日志DB，新增实绩日志DB,新增ETL（镀锡）实绩日志DB)
	 * </p>
	 * 
	 * @param sjVO
	 *            实绩录入界面生成的VO
	 */
	public void saveSjLr(SjSaveVO sjVO);

	/**
	 * <p>
	 * 保存实绩录入(更新制品在库DB，更新订货DB，更新指示对象，新增实绩日志DB，新增实绩日志DB,新增ETL（镀锡）实绩日志DB)
	 * </p>
	 * 
	 * @param sjVO
	 *            实绩录入界面生成的VO
	 */
	public void updateSjLr(SjSaveVO sjVO);

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
	 * 查询制品
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZpngTp> qentity);

	/**
	 * <p>
	 * 删除实绩
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PlieNo
	 */
	public void delete(String jbno);

	/**
	 * <p>
	 * 批量删除实绩
	 * </p>
	 * 
	 * @param jbnos
	 *            卷板No/PlieNo
	 */
	public void deleteAll(List<String> jbnos);

	/**
	 * <p>
	 * 将获取的对象转化成JS
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

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
	 * 取得指示书信息
	 * </p>
	 * 
	 * @param jbno
	 *            卷板NO
	 */
	public ZsViewVO getZs(String jbno, String type);

	/**
	 * <p>
	 * 获取分析ETL锡原单位统计数据Excel流
	 * </p>
	 * <p>
	 * 只能按月来查，即只指定时间，若没有指定时间，则默认查当月。
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchEtlsy(EtlSydjVO vo, OutputStream os);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @param zu
	 */
	public void updateZu(String jbno, String zu);

}
