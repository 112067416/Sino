package com.quanta.sino.cmn.bo.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dh.vo.ZzsyVO;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 通用主文件业务接口
 * </p>
 * <p>
 * create: 2011-1-5 上午11:29:30
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IZzsyBO {

	/**
	 * 新增通用主文件
	 * 
	 * @param entity
	 * @return String
	 */
	public String save(ZzsyMp entity);

	/**
	 * 修改通用主文件
	 * 
	 * @param entity
	 * @return String
	 */
	public String update(ZzsyMp entity);

	/**
	 * <p>
	 * 检验数据
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String validate(ZzsyMp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询通用主文件
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZzsyMp> qentity);

	/**
	 * 获取通用主文件
	 * 
	 * @param id
	 * @return ZzsyMp
	 */
	public ZzsyMp get(Serializable id);

	/**
	 * <p>
	 * 根据ID获取对象
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 根据试样Key1(填写厚宽长上下限的情况)和Key2获取匹配的一个试样主文件
	 * </p>
	 * 
	 * @param ggno
	 *            规格代码
	 * @param fudw
	 *            附着量.单位
	 * @param fuzm
	 *            附着量.正面
	 * @param fufm
	 *            附着量.反面
	 * @param pzno
	 *            品种代码
	 * @param chdx
	 *            差厚镀锡
	 * @param nwai
	 *            内外销
	 * @param hmax
	 *            尺寸.厚上限值
	 * @param hmin
	 *            尺寸.厚下限值
	 * @param kmax
	 *            尺寸.宽上限值
	 * @param kmin
	 *            尺寸.宽下限值
	 * @param cmax
	 *            尺寸.长上限值
	 * @param cmin
	 *            尺寸.长下限值
	 * @param houu
	 *            尺寸.厚
	 * @param kuan
	 *            尺寸.宽
	 * @param cang
	 *            尺寸.长
	 * @param cond
	 *            加工用途条件
	 * @param user
	 *            用户代码
	 * @return ZzsyMp
	 */
	public ZzsyMp getByKey(String ggno, String fudw, String fuzm, String fufm,
			String pzno, String chdx, String nwai, Double hmax, Double hmin,
			Double kmax, Double kmin, Double cmax, Double cmin, Double houu,
			Double kuan, Double cang, String cond, String user);

	public ZzsyMp getByKey(String ql);

	/**
	 * <p>
	 * 根据试样Key1(填写厚宽长指定值的情况)和Key2获取匹配的一个试样主文件
	 * </p>
	 * 
	 * @param ggno
	 *            规格代码
	 * @param fudw
	 *            附着量.单位
	 * @param fuzm
	 *            附着量.正面
	 * @param fufm
	 *            附着量.反面
	 * @param pzno
	 *            品种代码
	 * @param chdx
	 *            差厚镀锡
	 * @param nwai
	 *            内外销
	 * @param houu
	 *            尺寸.厚
	 * @param kuan
	 *            尺寸.宽
	 * @param cang
	 *            尺寸.长
	 * @param cond
	 *            加工用途条件
	 * @param user
	 *            用户代码
	 * @return ZzsyMp
	 */
	public ZzsyMp getByKey(String ggno, String fudw, String fuzm, String fufm,
			String pzno, String chdx, String nwai, Double houu, Double kuan,
			Double cang, String cond, String user);

	/**
	 * <p>
	 * 获取仕样主文件新生成的号码
	 * </p>
	 */
	public String getNewNo();

	/**
	 * <p>
	 * 根据 规格代码、附着量.单位、附着量 .正面和附着量 .反面，取默认的制造仕样信息
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ggno
	 *            规格代码
	 * @param fudw
	 *            附着量.单位
	 * @param fuzm
	 *            附着量 .正面
	 * @param fufm
	 *            附着量 .反面
	 * @param pzno
	 *            品种代码
	 * @return ZzsyVO
	 */
	public ZzsyVO getDefaultZzsy(String ggno, String fudw, String fuzm,
			String fufm, String pzno);

	/**
	 * <p>
	 * </p>
	 */
	public void doRefresh();
}
