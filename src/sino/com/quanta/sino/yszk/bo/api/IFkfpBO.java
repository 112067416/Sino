package com.quanta.sino.yszk.bo.api;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.vo.FpVO;
import com.quanta.sino.yszk.vo.HjVO;
import com.quanta.sino.yszk.vo.KhfkVO;
import com.quanta.sino.yszk.vo.YszkQE;

/**
 * <p>
 * 应收账款业务层接口
 * </p>
 * <p>
 * create: 2011-7-1 下午05:53:58
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFkfpBO {
	/**
	 * <p>
	 * 新增发票
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Fkfp entity);

	/**
	 * <p>
	 * 更新发票
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Fkfp entity);

	/**
	 * <p>
	 * 根据主键删除对应的发票
	 * </p>
	 * 
	 * @param id
	 */
	public String delete(Serializable id);

	/**
	 * <p>
	 * 查询发票数据
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Fkfp> qentity);

	/**
	 * <p>
	 * 付款冲帐记录分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryCzgl(QEntity<Czgl> qentity);

	/**
	 * <p>
	 * 获取付款发票信息
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取付款发票信息
	 * </p>
	 * 
	 * @param id
	 * @return Fkfp
	 */
	public Fkfp get(Serializable id);

	/**
	 * <p>
	 * 保存编辑好的，应收帐款发票信息
	 * </p>
	 * 
	 * @param fpVO
	 * @param user
	 */
	public void saveFp(FpVO fpVO, User user);

	/**
	 * <p>
	 * 编辑付款发票信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void editFp(Fkfp entity);

	/**
	 * <p>
	 * 编辑红字发票
	 * </p>
	 * 
	 * @param entity
	 */
	public String editHzfp(Fkfp entity);

	/**
	 * <p>
	 * 根据发票生成红字发票
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String editHzfp(String[] ids);

	/**
	 * <p>
	 * 根据发票生成蓝字发票
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String editLzfp(String[] ids);

	/**
	 * <p>
	 * 获得客户付款金额
	 * </p>
	 * 
	 * @param id
	 * @return Khfk
	 */
	public Khfk getKhfk(String id);

	/**
	 * <p>
	 * 保存冲账相关操作
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String doCz(KhfkVO vo);

	/**
	 * <p>
	 * 获取付款发票
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param chri
	 *            出货日
	 * @param fppz
	 *            发货品种
	 * @return Fkfp
	 */
	public Fkfp get(String dhno, String line, Date chri, String fppz);

	/**
	 * <p>
	 * 保存或修改付款发票
	 * </p>
	 * 
	 * @param fkfp
	 */
	public void saveOrUpdate(Fkfp fkfp);

	/**
	 * <p>
	 * 未收货款
	 * </p>
	 * 
	 * @param user
	 * @return Double
	 */
	public Double getWsyk(String user);

	/**
	 * <p>
	 * 根据出货日期，生成应收帐款发票数据
	 * </p>
	 * 
	 * @param chqiS
	 * @param chqiE
	 */
	public void outFpDatas(Date chqiS, Date chqiE);

	/**
	 * <p>
	 * 统计出货重量和总价
	 * </p>
	 * 
	 * @param chqiS
	 * @param chqiE
	 * @param fpymc
	 * @param nwai
	 * @param deng
	 * @param pz
	 * @param stats
	 * @param houuS
	 * @param houuE
	 * @param fppz
	 * @param fpno
	 * @return HjVO
	 */
	public HjVO getHj(Date chqiS, Date chqiE, String fpymc, String nwai,
			String deng, String pz, String[] stats, Double houuS, Double houuE,
			String fppz, String fpno);

	/**
	 * <p>
	 * 获得客户付款记录对应的发票数
	 * </p>
	 * 
	 * @param khfkId
	 * @param size
	 * @return int
	 */
	public int getPageSize(String khfkId, Integer size);

	/**
	 * <p>
	 * 获取付款发票记录统计数据Excel流
	 * </p>
	 * 
	 * @param qentity
	 * @param os
	 */
	public void fetchFkfp(YszkQE qentity, OutputStream os);

	/**
	 * <p>
	 * 保存发票分解记录
	 * </p>
	 * 
	 * @param fkfp
	 * @return String
	 */
	public String saveDivied(Fkfp fkfp);

	/**
	 * <p>
	 * 设置发票号
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 */
	public void setFpno(List<String> ids, String fpno);

	/**
	 * <p>
	 * 设置发票公共参数
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 * @param fpymc
	 * @param kpdj
	 */
	public void setFpcs(String[] ids, String fpno, String fpymc, Double kpdj);

	/**
	 * <p>
	 * 获取发票订货信息内容
	 * </p>
	 * 
	 * @param dhno
	 *            订货no
	 * @param line
	 *            行号
	 * @return String
	 */
	public String getFpdhInfo(String dhno, String line);
}
