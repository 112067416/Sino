package com.quanta.sino.ch.bo.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.vo.JczmsVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.JczmsItem;

/**
 * <p>
 * 检查证明书管理业务接口
 * </p>
 * <p>
 * create: 2011-1-18 上午09:21:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IJczmsBO {

	/**
	 * <p>
	 * 根据装箱指示书，订货合同生成制品检查证明书
	 * <ul>
	 * <li>当订货合同唯一，也就是全为一级品的时候才做制品检查证明书
	 * <li>制品检查证明书的明细是更具钢卷号来做的，也就是同一个钢卷号下的所有制品只生成一条记录
	 * </p>
	 * 
	 * @param zxno
	 * @param chri
	 * @param jczmsVos
	 * @param dhuoTp
	 * @param user
	 */
	public void outJczms(String zxno, Date chri, List<JczmsVO> jczmsVos,
			DhuoTp dhuoTp, User user);

	/**
	 * <p>
	 * 查询检查证明书明细记录
	 * </p>
	 * 
	 * @param refId
	 * @return List[JczmsItem]
	 */
	public List<JczmsItem> findMx(String refId);

	/**
	 * <p>
	 * 保存检查证明书
	 * </p>
	 * 
	 * @param jczms
	 */
	public void save(Jczms jczms);

	/**
	 * <p>
	 * 查询检查证明书
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Jczms]
	 */
	public List<Jczms> find(String zxno);

	/**
	 * <p>
	 * 查询检查证明书
	 * </p>
	 * 
	 * @param zxnos
	 * @return List[Jczms]
	 */
	public List<Jczms> find(String[] zxnos);

	/**
	 * <p>
	 * 查询制品检查证明书明细
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<JczmsItem> qentity);

	/**
	 * <p>
	 * 修改制品检查证明书
	 * <ul>
	 * <li>修改检查证明书只能修改明细内容
	 * </ul>
	 * </p>
	 * 
	 * @param mx
	 */
	public void update(JczmsItem mx);

	/**
	 * <p>
	 * 批量修改检查证明书记录
	 * </p>
	 * 
	 * @param mxs
	 */
	public void updateAll(List<JczmsItem> mxs);

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
	 * 获取页数
	 * </p>
	 * 
	 * @param id
	 * @param size
	 * @return int
	 */
	public int getPageSize(String id, Integer size);

	/**
	 * <p>
	 * 制作生成检查证明书
	 * </p>
	 * 
	 * @param zxnos
	 * @param user
	 */
	public void zzJczms(String[] zxnos, User user);

	/**
	 * <p>
	 * 判断制品检查证明书，是否存在
	 * </p>
	 * 
	 * @param zxnos
	 * @return String
	 */
	public String checkJczms(String[] zxnos);

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
