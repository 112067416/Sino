package com.quanta.sino.cmn.bo.api;

import java.util.List;

import org.dom4j.Document;

import com.coco.core.bean.Result;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.EYlType;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.cmn.vo.XpVo;

/**
 * <p>
 * 中日达生产公共业务接口
 * </p>
 * <p>
 * create: 2011-1-5 下午04:31:47
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ICmnBO {
	/**
	 * <p>
	 * 转换成附着量略称
	 * </p>
	 * 
	 * @param fudw
	 *            附着量单位
	 * @param fuzm
	 *            附着量.正
	 * @param fufm
	 *            附着量.反
	 * @param chdx
	 *            差厚
	 * @return
	 */
	public String getDhfz(String fudw, String fuzm, String fufm, String chdx);

	/**
	 * <p>
	 * 取现品信息(综合母材卷板DB\制品在库DB)
	 * </p>
	 * <p>
	 * author 方元龙
	 * </p>
	 * 
	 * @param xpNo
	 * @return XpVo
	 */
	public XpVo fetchXpxx(String xpNo);

	/**
	 * <p>
	 * 生成制品号，根据卷板No判断制品是ETL（6、7位）还是SL（8、9位），ETL生成的制品号为8位，SL的生成的制品号为11位。
	 * 若传入参数不符合规则，则返回null。
	 * </p>
	 * <p>
	 * author 许德建[xudejian@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @param type
	 * @return String[] 若终了则返回的数值为一个元素（制品号），否则中止返回的数值为两个元素（0-制品号，1-新原板卷材号）
	 * @see NoGenType
	 */
	public String[] generateNo(String jbno, NoGenType type);

	/**
	 * <p>
	 * 生成原板卷板号
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return String
	 */
	public String generateYcaiTpNo();

	/**
	 * <p>
	 * 制品号的上级号码
	 * </p>
	 * <p>
	 * author 许德建[xudejian@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @return String 上级号码
	 */
	public String parentNo(String jbno);

	/**
	 * <p>
	 * 制品号的结构树
	 * </p>
	 * <p>
	 * author 许德建[xudejian@126.com]
	 * </p>
	 * 
	 * @param jbno
	 * @return Document 树型文档
	 */
	public Document treeNo(String jbno);

	/**
	 * <p>
	 * 根据业连类型取获取业连号。注：ETL业连取1、3；SL业连取2、3。
	 * </p>
	 * <p>
	 * author 方元龙
	 * </p>
	 * 
	 * @param ylno
	 * @param type
	 *            业连类型：1-ETL；2-SL。
	 * @return List[String] 业连号（不带前缀）
	 */
	public List<String> fetchYls(String ylno, EYlType type);

	/**
	 * <p>
	 * 等级各位的合法性校验
	 * </p>
	 * <p>
	 * <b>形如：</b>
	 * <ul>
	 * <li>三位：GAA。每一位都校验</li>
	 * <li>二位：DD。作为等级的第二位与第三位校验</li>
	 * </ul>
	 * </p>
	 * 
	 * @param deng
	 *            等级代码
	 */
	public void checkDeng(String deng);

	/**
	 * <p>
	 * 实绩时，产量代码、等级与指示书的分配等级之间的校验<br />
	 * 注：如不匹配时，直接抛出异常
	 * </p>
	 * <p>
	 * author 方元龙
	 * </p>
	 * 
	 * @param chan
	 *            产量代码
	 * @param deng
	 *            等级
	 * @param fpdj
	 *            指示书的分配等级
	 */
	public void checkChan(String chan, String deng, String fpdj);

	/**
	 * <p>
	 * 实绩时，产量代码、等级与指示书的分配等级之间的校验<br />
	 * 注：如不匹配时，抛出不匹配原因
	 * </p>
	 * <p>
	 * author 方元龙
	 * </p>
	 * 
	 * @param chan
	 *            产量代码
	 * @param deng
	 *            等级
	 * @param fpdj
	 *            指示书的分配等级
	 * @return Result
	 */
	public Result getCheck(String chan, String deng, String fpdj);

	/**
	 * <p>
	 * 根据订货No.生成出口包装No.。生成机制：在制品库中同订货No.的最大出口包装号No.加1
	 * </p>
	 * 
	 * @param dhno
	 * @return Integer
	 */
	public Integer generatePackageNo(String dhno);

	/**
	 * <p>
	 * 根据产量代码获取实绩品种等级
	 * </p>
	 * <p>
	 * author 方元龙
	 * </p>
	 * 
	 * @param cl
	 *            产量代码【{@link com.quanta.sino.cmn.constants.ChanType}】
	 * @param dj
	 *            产量代码【{@link com.quanta.sino.cmn.constants.DengCode1}】； 【
	 *            {@link com.quanta.sino.cmn.constants.DengCode1}】
	 * @return String 為空時即发生异常
	 */
	public String getSjpzdj(ChanType cl, String dj);

	/**
	 * <p>
	 * 根据现品厚获得商品编号
	 * </p>
	 * 
	 * @param xpho
	 *            现品厚
	 * @return String 商品编号
	 */
	@Deprecated
	public String getSpbh(Double xpho);

	/**
	 * <p>
	 * 根据厚度，获得海关关税
	 * </p>
	 * 
	 * @param xpho
	 * @return double
	 */
	public double getGssl(Double xpho);

	/**
	 * <p>
	 * 根据重量内容取补正系统<br />
	 * 根据重量内容确定采用哪一个补正系数，当重量内容为１、
	 * ２、３时，采用实际重量补正系数0.998,当重量内容为６、７、８时，采用计算重量补正系数0.996 (补正系数主文件ID=200)
	 * </p>
	 * 
	 * @param zlnr
	 * @return
	 */
	public Double fetchZsbz(ZlnrCode zlnr);

	/**
	 * <p>
	 * 转换为GM单位的附着量
	 * </p>
	 * 
	 * @param dw
	 *            单位：GM/WB
	 * @param fzl
	 *            附着量(形如：010，011)
	 * @return Double：GM附着量(两位小数)
	 */
	public Double parseFzl2Gm(String dw, String fzl);

	/**
	 * <p>
	 * 转换成附着量（g/m2)
	 * </p>
	 * 
	 * @param fudw
	 *            附着量单位
	 * @param fuzm
	 *            附着量.正
	 * @param fufm
	 *            附着量.反
	 * @param chdx
	 *            差厚
	 * @return String
	 */
	public String getFugm(String fudw, String fuzm, String fufm, String chdx);

	/**
	 * <p>
	 * 计算办切中止卷的重量
	 * </p>
	 * 
	 * @param zzjh
	 *            装入卷厚
	 * @param njno
	 *            内径代码
	 * @param xpkn
	 *            现品尺寸 ·宽
	 * @return int 装入中止的钢卷重量，即制品重量
	 */
	public int getZzzl(Integer zzjh, String njno, Double xpkn);
}
