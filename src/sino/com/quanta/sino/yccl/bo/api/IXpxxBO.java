package com.quanta.sino.yccl.bo.api;

import com.coco.core.bean.User;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.yccl.vo.YldlVO;
import com.quanta.sino.yccl.vo.ZpVO;
import com.quanta.sino.yccl.vo.ZpkDataVO;
import com.quanta.sino.yccl.vo.ZyblQE;
import com.quanta.sino.yccl.vo.ZyztQE;

/**
 * <p>
 * 现品信息
 * </p>
 * <p>
 * create: 2011-1-18 下午05:09:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IXpxxBO {

	/**
	 * <p>
	 * 通过现品情报作成功能，生成新的制品
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @param newJbno
	 */
	public void saveZpngTp(ZpngTp entity, User user, String newJbno);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entity
	 * @param user
	 * @param newJbno
	 */
	public void saveYcaiTp(YcaiTp ycaiTp, ZpngTp entity, User user,
			String newJbno);

	/**
	 * <p>
	 * 修改制品在库DB
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateZpngTp(ZpngTp entity, User user);

	/**
	 * <p>
	 * 更新原材卷板信息。可以更新字段如下：
	 * </p>
	 * <table width="300" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <tr>
	 * <td>名称</td>
	 * <td>字段名</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td>现品尺寸.厚</td>
	 * <td>xpho</td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>现品尺寸.宽</td>
	 * <td>xpkn</td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>卷板长</td>
	 * <td>jbcn</td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>制品重量</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>等级</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>制造商代码</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>钢种类型</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>内径代码</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>表面精加工</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>品种代码</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>规格代码</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * <tr>
	 * <td>业连No</td>
	 * <td></td>
	 * <td></td>
	 * </tr>
	 * </table>
	 * 
	 * @param entity
	 */
	public void updateYcaiTp(YcaiTp entity, User user);

	/**
	 * 根据卷板号获取制品在库表信息或者原材卷板信息
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public String getForJs(String jbno);

	/**
	 * <p>
	 * 用sql语句联合查询制品在库表和原材卷板表
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(ZyztQE qentity);

	/**
	 * <p>
	 * 用sql语句联合查询制品在库表和原材卷板表
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryBl(ZyblQE qentity);

	/**
	 * <p>
	 * 删除制品在库信息
	 * </p>
	 * 
	 * @param jbno
	 */
	public void deleteZpngTp(String jbno, User user);

	/**
	 * <p>
	 * 现品情报删除原材卷板信息
	 * </p>
	 * 
	 * @param jbno
	 * @param user
	 */
	public void deleteYcaiTp(String jbno, User user);

	/**
	 * <p>
	 * 判断现品情报删除
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String getCheckDelXp(String jbno);

	/**
	 * <p>
	 * 修改原版或者制品在库DB作业停止标记。注：对做过已实绩终了、已消灭、已出货（包含制作装箱指示书）和异常处理删除的现品是不能设定作业停止标记;
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param ztbj
	 *            作业停止标记
	 * @param czr
	 *            操作人
	 * @param zyly
	 *            处置
	 * @param cz
	 *            操作人
	 * @param user
	 */
	public void updateZytz(String[] ids, String ztbj, String czr, String zyly,
			String cz, User user);

	/**
	 * <p>
	 * 修改原版或者制品在库DB作业停止对应的处置理由
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param zyly
	 *            处置
	 * @param cz
	 *            操作人
	 * @param user
	 */
	public void updateCz(String[] ids, String zyly, String cz, User user);

	/**
	 * <p>
	 * 修改原版或者制品在库DB作业停止标记。注：对做过已实绩终了、已消灭、已出货（包含制作装箱指示书）和异常处理删除的现品是不能设定作业停止标记;
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param ztbj
	 *            作业停止标记
	 * @param czr
	 *            操作人
	 * @param zyly
	 *            处置
	 * @param cz
	 *            操作人
	 * @param user
	 */
	public void updateXpbl(String[] ids, String blbj, String czr, String zyly,
			String cz, User user);

	/**
	 * <p>
	 * 修改原版或者制品在库DB 业联NO
	 * </p>
	 * 
	 * @param vo
	 * @param user
	 */
	public void updateYlno(YldlVO vo, User user);

	/**
	 * <p>
	 * 批量设置业务No
	 * </p>
	 * 
	 * @param xpzk
	 * @param jbnoStart
	 * @param jbnoEnd
	 * @param ylno
	 */
	public void updateYlno(String xpzk, String jbnoStart, String jbnoEnd,
			String ylno);

	/**
	 * <p>
	 * 修改原版或者制品在库DB 堆场
	 * </p>
	 * 
	 * @param jbnos
	 * @param kws
	 * @param dc
	 */
	public void updateDc(String[] jbnos, String[] kws, User user);

	/**
	 * <p>
	 * 堆场转换规则判断
	 * </p>
	 * 
	 * @param jbnos
	 * @param kws
	 * @return String
	 */
	public String getCheckDcgz(String[] jbnos, String[] kws);

	/**
	 * <p>
	 * 获得制品信息
	 * </p>
	 * 
	 * @param zpngTp
	 * @param ycaiTp
	 * @param flag
	 * @return String
	 */
	public String getZpForJS(ZpngTp zpngTp, YcaiTp ycaiTp, boolean flag);

	/**
	 * <p>
	 * 判断修正后的现品信息
	 * </p>
	 * 
	 * @param zpVO
	 * @param flag
	 * @return String
	 */
	public String getCheckXpxx(ZpVO zpVO, boolean flag);

	/**
	 * <p>
	 * 获得制品信息
	 * </p>
	 * 
	 * @param zpngTp
	 * @param ycaiTp
	 * @param flag
	 * @return String
	 */
	public String getZyztForJS(ZpkDataVO vo);

	/**
	 * <p>
	 * 获得制品信息
	 * </p>
	 * 
	 * @param zpngTp
	 * @param ycaiTp
	 * @param flag
	 * @return String
	 */
	public String getZyblForJS(ZpkDataVO vo);

	/**
	 * <p>
	 * 检查制品是否已设置作业停止标记
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String getCheckZytz(String[] ids);

	/**
	 * <p>
	 * 检查制品是否已设置保留标记
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String getCheckXpbl(String[] ids);

}
