package com.quanta.sino.yygl.bo.api;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.orm.Khyfdj;
import com.quanta.sino.yygl.vo.CkdVO;
import com.quanta.sino.yygl.vo.CondVO;
import com.quanta.sino.yygl.vo.CpckdVO;
import com.quanta.sino.yygl.vo.KhyfVO;
import com.quanta.sino.yygl.vo.RChtjVO;
import com.quanta.sino.yygl.vo.ZrchsjfVO;

/**
 * <p>
 * 客户运费业务层接口
 * </p>
 * <p>
 * create: 2011-2-13 下午07:19:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKhyfBO {

	/**
	 * 新增客户运费
	 * 
	 * @param entity
	 */
	public void save(Khyf entity);

	/**
	 * <p>
	 * 为客户出货数据，设置客户运费单价
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveYf(KhyfVO entity);

	/**
	 * 批量新增客户运费
	 * 
	 * @param entities
	 */
	public void saveAll(List<Khyf> entities);

	/**
	 * 更新客户运费
	 * 
	 * @param entity
	 */
	public void update(Khyf entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询客户运费
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZrchsjfVO> qentity);

	/**
	 * 获取客户运费
	 * 
	 * @param id
	 * @return Khyf
	 */
	public Khyf get(Serializable id);

	/**
	 * <p>
	 * 根据出货日期、运输行、用户名称、订货号、送货点和到达地点，找出客户运费
	 * </p>
	 * 
	 * @param chri
	 *            出货日期
	 * @param ysgs
	 *            运输行
	 * @param user
	 *            用户名称
	 * @param dhno
	 *            订货合同号
	 * @param line
	 *            订货合同行号
	 * @param shno
	 *            送货点
	 * @param addr
	 *            到达地点
	 * @return Khyf
	 */
	public Khyf getUnique(Date chri, String ysgs, String user, String dhno,
			String line, String shno, String addr);

	/**
	 * <p>
	 * 保存或修改客户运费
	 * </p>
	 * 
	 * @param khyf
	 */
	public void saveOrUpdate(Khyf khyf);

	/**
	 * <p>
	 * 加载客户运费信息
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 根据出货日期，统计出货件数和制品重量
	 * </p>
	 * 
	 * @param chriS
	 *            出货日期开始
	 * @param chriE
	 *            出货日期结束
	 * @param chriE
	 *            运输公司
	 * @return ChtjVO
	 */
	public ChtjVO tj(Date chriS, Date chriE, String ysgs);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @param ysgs
	 * @param ysfs
	 * @param djdw
	 * @param ld
	 * @return Khyfdj
	 */
	public Khyfdj getYfdj(String id, String ysgs, String ysfs, String djdw,
			String ld);

	/**
	 * <p>
	 * 日出货统计数据Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchRChtj(RChtjVO vo, OutputStream os);

	/**
	 * <p>
	 * 成品出库单统计数据Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchCpckd(CkdVO vo, OutputStream os);

	/**
	 * <p>
	 * 根据时间段查询成品出库单
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return List<CpckdVO>
	 */
	public List<CpckdVO> queryCpckds(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据时间段查询用途
	 * </p>
	 * 
	 * @param yearS
	 * @param yearE
	 * @param monthS
	 * @param monthE
	 * @return List<CondVO>
	 */
	public List<CondVO> queryConds(Integer yearS, Integer yearE,
			Integer monthS, Integer monthE);

	/**
	 * <p>
	 * 根据时间段查询用途
	 * </p>
	 * 
	 * @param yearS
	 * @param yearE
	 * @param monthS
	 * @param monthE
	 * @return CondVO
	 */
	public CondVO getCond(Integer yearS, Integer yearE, Integer monthS,
			Integer monthE);

	/**
	 * <p>
	 * 统计一个时间段出货的天数
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return long
	 */
	public long getNum(Date chriS, Date chriE);

	public void delete();
}
