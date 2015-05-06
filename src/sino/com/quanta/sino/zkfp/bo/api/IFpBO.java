package com.quanta.sino.zkfp.bo.api;

import java.io.OutputStream;
import java.util.List;

import com.coco.core.bean.User;
import com.quanta.sino.cmn.constants.Sfpp;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.zkfp.vo.FpVO;
import com.quanta.sino.zkfp.vo.FpckVO;
import com.quanta.sino.zkfp.vo.PError;
import com.quanta.sino.zkfp.vo.ZkfpErrorVO;

/**
 * <p>
 * 在库分配业务层接口
 * </p>
 * <p>
 * create: 2011-1-14 下午01:52:06
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFpBO {

	/**
	 * <p>
	 * 现品分配操作。根据不同的现品状况，调用相应的分配方法。具体下如下：
	 * </p>
	 * <ul>
	 * <li>素材时，调用fpSc方法。具体操作细节，可以查看该方法的注释。
	 * <li>中间品时，调用fpZjp方法。具体操作细节，可以查看该方法的注释。
	 * <li>制品时，调用fpZp方法。具体操作细节，可以查看该方法的注释。
	 * </ul>
	 * 
	 * @param vo
	 *            分配对象
	 * @param user
	 *            分配操作人
	 * @return String
	 */
	public String doFp(FpVO vo, User user);

	/**
	 * <p>
	 * 判断订货合同与现品信息是否匹配。根据订货合同的等级不同，调用相应的配匹方法。具体如下：
	 * </p>
	 * <ul>
	 * <li>一级品（合格品）时，调用comparePrime方法。具体配匹字段，可以查看该方法的注释。
	 * <li>O/R1或 O/R2级时，调用compareOr方法。具体配匹字段，可以查看该方法的注释。
	 * <li>发生品时，调用compareFs方法。具体配匹字段，可以查看该方法的注释。
	 * <li>端板时，调用compareNs方法。具体配匹字段，可以查看该方法的注释。
	 * </ul>
	 * 
	 * @param ycaiTp
	 *            素材
	 * @param zpngTp
	 *            中间品或制品
	 * @param dhuoTp
	 *            订货合同
	 * @param xpzk
	 *            现品状况
	 * @param jbkb
	 *            是否剪边
	 * @return Sfpp
	 */
	public Sfpp getSfpp(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk, String jbkb);

	/**
	 * <p>
	 * 分配取消操作。根据余材状况，可以对素材、中间品或制品进行分配取消。不同的余材状况，调用相应的分配取消方法。具体如下：
	 * </p>
	 * <ul>
	 * <li>素材时，调用fpqxSc方法。具体操作细节，可以查看该方法的注释。
	 * <li>中间品时，调用fpqxZjp方法。具体操作细节，可以查看该方法的注释。
	 * <li>制品时，调用fpqxZp方法。具体操作细节，可以查看该方法的注释。
	 * </ul>
	 * 
	 * @param jbnos
	 *            取消分配的现品No
	 * @param fpno
	 *            分配No.
	 * @param yczk
	 *            余材状况
	 * @return String
	 */
	public String doFpqx(List<String> jbnos, String fpno, String dhno,
			String line, String yczk, User user);

	/**
	 * <p>
	 * 根据订货合同号和行号，获得分配信息（订货合同信息和分配指示信息）
	 * </p>
	 * 
	 * @param dhno
	 *            订货号
	 * @param line
	 *            订货行号
	 * @return String
	 */
	public String getFpzs(String dhno, String line);

	/**
	 * <p>
	 * 根据分配NO，获得分配信息（订货合同信息和分配指示信息）
	 * </p>
	 * 
	 * @param fpno
	 *            分配No.
	 * @return String
	 */
	public String getFpqx(String fpno);

	/**
	 * <p>
	 * 记录断订货合同与现品不匹配的信息。根据订货合同的等级不同，调用相应的记录不配匹信息的方法。具体如下：
	 * </p>
	 * <ul>
	 * <li>一级品（合格品）时，调用findComparePrimeErrors方法。具体配匹字段，可以查看该方法的注释。
	 * <li>O/R1或 O/R2级时，调用findCompareOrErrors方法。具体配匹字段，可以查看该方法的注释。
	 * <li>发生品时，调用findCompareFsErrors方法。具体配匹字段，可以查看该方法的注释。
	 * <li>端板时，调用findCompareNsErrors方法。具体配匹字段，可以查看该方法的注释。
	 * </ul>
	 * 
	 * @param ycaiTp
	 *            素材
	 * @param zpngTp
	 *            中间品或制品
	 * @param dhuoTp
	 *            订货合同
	 * @param xpzk
	 *            现品状况
	 * @return List<PError>
	 */
	public List<PError> findCompareErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk);

	/**
	 * <p>
	 * 获取在库分配错误记录统计数据Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchZkfpError(ZkfpErrorVO vo, OutputStream os);

	/**
	 * <p>
	 * 获取分配参考LIST记录统计数据Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchFpckList(FpckVO vo, OutputStream os);

}
