package com.quanta.sino.yygl.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.orm.Khyf;

/**
 * <p>
 * 客户运费数据访问接口
 * </p>
 * <p>
 * create: 2011-2-13 下午07:19:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKhyfDAO {

	/**
	 * 新增客户运费
	 * 
	 * @param entity
	 */
	public void save(Khyf entity);

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

	public void delete();
}
