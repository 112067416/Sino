package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.vo.JczmsVO;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.JczmsItem;

/**
 * <p>
 * 检查证明书数据访问接口
 * </p>
 * <p>
 * create: 2011-1-12 上午11:59:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IJczmsDAO {

	/**
	 * <p>
	 * 保存检查证明书主表记录
	 * </p>
	 * 
	 * @param jczms
	 */
	public void save(Jczms jczms);

	/**
	 * <p>
	 * 保存检查证明书明细表记录
	 * </p>
	 * 
	 * @param mx
	 */
	public void saveAll(List<JczmsItem> mx);

	/**
	 * <p>
	 * 修改检查证明书主表记录
	 * </p>
	 * 
	 * @param jczms
	 *            检查证明书主表记录 author zhangliang, jimsonhappy@126.com create time:
	 *            2010-11-15 上午10:34:41
	 */
	public void update(Jczms jczms);

	/**
	 * <p>
	 * 修改检查证明书明细表记录
	 * </p>
	 * 
	 * @param mx
	 */
	public void update(JczmsItem mx);

	/**
	 * <p>
	 * 获取制品检查证明书
	 * </p>
	 * 
	 * @param id
	 * @return Jczms
	 */
	public Jczms get(Serializable id);

	/**
	 * <p>
	 * 查询检查证明书明细信息
	 * </p>
	 * 
	 * @param id
	 * @return List[JczmsItem]
	 */
	public List<JczmsItem> findMx(String id);

	/**
	 * <p>
	 * 获取制品检查证明书明细
	 * </p>
	 * 
	 * @param id
	 * @return JczmsItem
	 */
	public JczmsItem getItem(Serializable id);

	/**
	 * <p>
	 * 查询检查证明书
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Jczms]
	 */
	public List<Jczms> query(String zxno);

	/**
	 * <p>
	 * 查询检查证明书
	 * </p>
	 * 
	 * @param zxnos
	 * @return List[Jczms]
	 */
	public List<Jczms> query(List<String> zxnos);

	/**
	 * <p>
	 * 查询制品检查证明书明细
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryJczms(QEntity<JczmsItem> qentity);

	/**
	 * <p>
	 * 统计检查证明书的记录数
	 * </p>
	 * 
	 * @param id
	 * @return Long
	 */
	public Long count(Serializable id);

	/**
	 * <p>
	 * 删除检查证明书明细
	 * </p>
	 * 
	 * @param zxno
	 */
	public void deleteItems(List<String> zxno);

	/**
	 * <p>
	 * 删除检查证明书
	 * </p>
	 * 
	 * @param zxno
	 */
	public void delete(List<String> zxno);

	/**
	 * <p>
	 * 判断装箱指示书对应的制品检查证明书是否存在
	 * </p>
	 * 
	 * @param zxno
	 * @return boolean
	 */
	public boolean isExist(String zxno);

	/**
	 * <p>
	 * 获得制作检查证明书数据
	 * </p>
	 * 
	 * @param zxno
	 * @param dhnoAndLine
	 * @param pzno
	 * @return List<JczmsVO>
	 */
	public List<JczmsVO> findJczmsVos(String zxno, String dhno, String line,
			String pzno);

	/**
	 * <p>
	 * 判断制作后的检查证明书
	 * </p>
	 * 
	 * @param zxnos
	 * @return String
	 */
	public String checkZzJczms(List<String> zxnos);

}
