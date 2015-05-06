package com.quanta.sino.ss.bo.api;

import java.io.OutputStream;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ss.vo.SlsjVO;
import com.quanta.sino.ss.vo.SsBlVO;
import com.quanta.sino.ss.vo.SsItemVO;
import com.quanta.sino.ss.vo.SsRzVO;
import com.quanta.sino.ss.vo.SsVO;
import com.quanta.sino.ss.vo.SsXmVO;
import com.quanta.sino.ss.vo.ssXmItemVO;

/**
 * <p>
 * SS分选业务接口
 * </p>
 * <p>
 * create: 2011-1-20 下午07:48:02
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISsBO {
	/**
	 * <p>
	 * 消灭验证
	 * </p>
	 * 
	 * @param vos
	 */
	public void destroyvalidate(ssXmItemVO[] vos);

	/**
	 * <p>
	 * 获取合并包制品信息
	 * </p>
	 * 
	 * @param jbno
	 * @param header
	 * @return SsItemVO
	 */
	public SsItemVO fetchItem(String jbno, SsItemVO header);

	/**
	 * <p>
	 * 生成新的制品数据
	 * </p>
	 * 
	 * @param vo
	 */
	public void build(SsVO vo);

	/**
	 * <p>
	 * 保存生成新的制品数据。
	 * <ul>
	 * <li>1. 合成新包要求客户、尺寸、材质、镀锡量等一致才能合成一个新的PILE，新合成的PILE会由系统根据第一个PILE
	 * No所对应的卷生成新的PILE No，同时打印新的制品卡。</li>
	 * <li>2. 如果生成的新制品的实绩正反附着量不一致，界面数据 提交保存时必须确认翻转（界面翻转字段填1）。</li>
	 * <li>3. 合成制品PILE时，中间品PILE的张数没有限制（即使分去合包的总张数超过了它在系统中记录的张数）,合成的制品张数也没有限制。</li>
	 * <li>4. 新生成的制品的
	 * “PILE区分”是按照合成时的第一个制品来判定的。新制品的“PILE区分”为第一个制品对应合格品的“PILE区分”。如第一个制品的“PILE区分
	 * ”为T时，新制品的“PILE区分”为C。</li>
	 * <li>5. 对于出口合同，实绩作成时，根据订货合同号按顺序生成“出口包装NO”,捆包打印标签时候会在标签上打印</li>
	 * <li>6. PILE生成实绩界面中，用户输入第一个中间品PILE No.时，系统调出与之关联的业联信息（暂定显示业联号，可链接）。</li>
	 * <li>7. 重量计算方法，计算重量＝7.85×宽×厚×长×张数 其中宽、厚、长由毫米转换成米</li>
	 * </ul>
	 * </p>
	 * 
	 * @param vo
	 *            生成制品数据
	 */
	public void save(SsVO vo);

	/**
	 * <p>
	 * 保存修改的制品数据。
	 * <ul>
	 * <li>1. 合成新包要求客户、尺寸、材质、镀锡量等一致才能合成一个新的PILE，新合成的PILE会由系统根据第一个PILE
	 * No所对应的卷生成新的PILE No，同时打印新的制品卡。</li>
	 * <li>2. 如果生成的新制品的实绩正反附着量不一致，界面数据 提交保存时必须确认翻转（界面翻转字段填1）。</li>
	 * <li>3. 合成制品PILE时，中间品PILE的张数没有限制（即使分去合包的总张数超过了它在系统中记录的张数）,合成的制品张数也没有限制。</li>
	 * <li>4. 新生成的制品的
	 * “PILE区分”是按照合成时的第一个制品来判定的。新制品的“PILE区分”为第一个制品对应合格品的“PILE区分”。如第一个制品的“PILE区分
	 * ”为T时，新制品的“PILE区分”为C。</li>
	 * <li>5. 对于出口合同，实绩作成时，根据订货合同号按顺序生成“出口包装NO”,捆包打印标签时候会在标签上打印</li>
	 * <li>6. PILE生成实绩界面中，用户输入第一个中间品PILE No.时，系统调出与之关联的业联信息（暂定显示业联号，可链接）。</li>
	 * <li>7. 重量计算方法，计算重量＝7.85×宽×厚×长×张数 其中宽、厚、长由毫米转换成米</li>
	 * </ul>
	 * </p>
	 * 
	 * @param vo
	 *            生成制品数据
	 */
	public void update(SsVO vo);

	/**
	 * <p>
	 * 查询已经生成的新包
	 * </p>
	 * 
	 * @param page
	 */
	public void query(QEntity<SlsjVO> page);

	/**
	 * <p>
	 * 获取可消灭的制品，删除标志为1（已实绩）的情况
	 * </p>
	 * 
	 * @param jbno
	 * @return SsXmVO
	 */
	public SsXmVO fetchXmVO(String jbno);

	/**
	 * <p>
	 * 消灭制品数据，置删除标志为2(已消灭)
	 * <ul>
	 * <li>1. PILE的消灭：
	 * <p>
	 * a. PILE消灭时，需要填写该PILE的缺陷（原系统中最多为6个，新系统需要考虑扩充，现在设置为8个）。
	 * </p>
	 * <p>
	 * b. 当中间品PILE合成制品PILE后，需要对中间品PILE进行消灭。消灭的PILE张数也不作限制（即使张数不等于零）。
	 * 消灭后的中间品PILE不能再次进行合包操作。
	 * </p>
	 * </li>
	 * <li>2. PILE消灭界面中，缺陷默认为8个。</li>
	 * </ul>
	 * </p>
	 * 
	 * @param vo
	 *            消灭制品数据
	 */
	public void destroy(ssXmItemVO[] vos);

	/**
	 * <p>
	 * 订正检查
	 * </p>
	 * 
	 * @param jbno
	 *            制品号
	 * @return
	 */
	public SsVO dzCheck(String jbno);

	/**
	 * <p>
	 * 输出日志Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchRz(SsRzVO vo, OutputStream os);

	/**
	 * <p>
	 * 获取分析步留统计数据Excel流
	 * </p>
	 * <p>
	 * 本人觉得限制只统计一个月为好，这里限制起止篇幅为60天。查询条件重设：
	 * <ul>
	 * <li>若终止时间早于开始时间，则查开始当日。</li>
	 * <li>若终止时间早于开始时间60天以上，则只查60天。</li>
	 * <li>若没有指定终止时间，则查指定开始时间当日。</li>
	 * <li>若没有指定开始时间，则查当月月初至终止时间。</li>
	 * <li>若没有指定开始时间和终止时间，则默认查当天。</li>
	 * </ul>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchBltj(SsBlVO vo, OutputStream os);

	/**
	 * <p>
	 * 获取分析步留统计数据Excel流
	 * </p>
	 * <p>
	 * 只能按天来查，即只指定开始时间，若没有指定开始时间，则默认查当日。
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchBlmx(SsBlVO vo, OutputStream os);

	/**
	 * <p>
	 * 删除新生成的包
	 * </p>
	 * 
	 * @param jbno
	 */
	public void delete(String jbno);

}
