package com.quanta.sino.ch.bo.api;

import java.util.Date;
import java.util.List;

import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.CpdrVO;
import com.quanta.sino.ch.vo.ZxdzVO;

/**
 * <p>
 * 装箱对照业务接口
 * </p>
 * <p>
 * create: 2011-1-23 下午02:31:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IZxdzBO {

	/**
	 * <p>
	 * 装箱对照
	 * <ul>
	 * <li>判断输入的制品和对应的订货合同是否是装箱指示书中的
	 * <li>设置装箱指示书状态为已发货状态
	 * <li>修改相应的制品的送货单号，设置出货实绩登录年月，设置删除标记为已出货
	 * <li>写出货日志表
	 * <li>写出付款发票表
	 * <li>修改订货合同的出货数量，出货数量为原出货数+本次出货数量
	 * <li>写客户运费表
	 * </ul>
	 * </p>
	 * 
	 * @param vos
	 */
	public void zxdz(ZxdzVO vos);

	/**
	 * <p>
	 * 次级品装箱对照
	 * </p>
	 * 
	 * @param vos
	 * @param swList
	 */
	public void cjpZxdz(ZxdzVO vos, List<String> swList);

	/**
	 * <p>
	 * 设置已发货制品车牌号
	 * </p>
	 * 
	 * @param cpno
	 * @param jbnos
	 */
	public void setCpno(String cpno, String[] jbnos);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param vo
	 */
	public String importCpno(CpdrVO vo);

	/**
	 * <p>
	 * 根据出货日期，统计出货件数和制品重量
	 * </p>
	 * <p>
	 * author：张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param chri
	 *            出货日
	 * @return ChtjVO
	 */
	public ChtjVO getChtj(Date chri);

	/**
	 * <p>
	 * 根据出货联络单ID，查询实际出货情况
	 * </p>
	 * <p>
	 * author：张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param chlldId
	 *            出货联络单ID
	 * @return ChtjVO
	 */
	public List<ChtjVO> findChtj(String chlldId);

	/**
	 * <p>
	 * 判断装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String getCheck(ZxdzVO vo);

	/**
	 * <p>
	 * 判断次级品装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @param swList
	 * @return String
	 */
	public String getCjpCheck(ZxdzVO vo, List<String> swList);
}
