/**
 * 
 */
package com.quanta.sino.sl.bo.api;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.sl.vo.SLSjSaveVO;
import com.quanta.sino.sl.vo.SjzkVO;
import com.quanta.sino.sl.vo.SjzsVO;
import com.quanta.sino.sl.vo.SllrVO;
import com.quanta.sino.sl.vo.SlsjVO;

/**
 * <p>
 * SL实绩业务接口
 * </p>
 * <p>
 * create: 2011-1-15 下午12:30:05
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface ISlBO {
	/**
	 * <p>
	 * 计算重量
	 * </p>
	 * 
	 * @param zsno
	 * @param zshu
	 * @return
	 */
	public Integer getJszl(String zsno, Integer zshu);

	/**
	 * <p>
	 * 检查实测宽和实测剪断长
	 * </p>
	 * 
	 * @param sckn
	 * @param jdcn
	 * @param zsno
	 */
	public void checkKuanCang(Double sckn, Double jdcn, String zsno);

	/**
	 * <p>
	 * 保存成功取制品信息
	 * </p>
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	public SLSjSaveVO getcheck(SllrVO vo, User user);

	/**
	 * <p>
	 * 实绩保存
	 * </p>
	 * 
	 * @param sjsave
	 * @param vo
	 * @param user
	 */
	public void sjlrSave(SLSjSaveVO sjsave, SjzkVO vo, User user);

	/**
	 * <p>
	 * 界面检查
	 * </p>
	 * 
	 * @param zp
	 * @param vo
	 * @param user
	 */
	public void validateCheck(SLSjSaveVO sjsave, SjzkVO vo, User user,
			String clq);

	/**
	 * <p>
	 * 初始当前用户所在生产线中正在实绩的卷
	 * </p>
	 * 
	 * @param user
	 *            登录用户
	 * @return
	 */
	public SllrVO lead(User user);

	/**
	 * <p>
	 * 检验用户输入[生产线、指示书号、入端卷号]
	 * </p>
	 * 
	 * @param vo
	 * @return String 出端制品
	 */
	public SLSjSaveVO check(SllrVO vo, User user);

	/**
	 * <p>
	 * 查看实绩指示
	 * </p>
	 * 
	 * @param rcjbno
	 *            入端卷号
	 * @return
	 */
	public SjzsVO viewSjzs(String rcjbno);

	/**
	 * <p>
	 * 订正检查
	 * </p>
	 * 
	 * @param jbno
	 *            制品号
	 * @return
	 */
	public Result dzCheck(String jbno, User user);

	/**
	 * <p>
	 * 实绩订正【限于实绩订正】
	 * </p>
	 * 
	 * @param dzzp
	 *            界面订正实绩制品
	 * @param user
	 *            当前操作用户
	 */
	public void sjdz(ZpngTp dzzp, User user);

	/**
	 * <p>
	 * 实绩制品分页查询
	 * </p>
	 * 
	 * @param page
	 *            查询体
	 */
	public void query(QEntity<SlsjVO> page);

	/**
	 * <p>
	 * 查看制品信息
	 * </p>
	 * 
	 * @param jbno
	 *            卷板NO
	 * @return
	 */
	public ZpngTp view(String jbno);

	/**
	 * <p>
	 * 删除制品
	 * </p>
	 * 
	 * @param jbno
	 *            板材号
	 * @param user
	 *            用户
	 */
	public void delete(String jbno, User user);
}
