package com.quanta.sino.kcgl.bo.api;

import com.quanta.sino.ycai.vo.DrVO;

/**
 * <p>
 * 库存管理接口
 * </p>
 * <p>
 * create: 2011-2-18 上午10:38:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKcglBO {
	/**
	 * <p>
	 * 制品入库
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>jbno</td>
	 * <td>制品NO</td>
	 * <td style="text-align: left;">验证制品NO是否存在和不能为空。</td>
	 * </tr>
	 * <tr>
	 * <td>kw</td>
	 * <td>库位</td>
	 * <td style="text-align: left;">验证库位和不能为空。库位标签以G开头，长度为4</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">制品表中，以下字段的获取方式：
	 * <ul>
	 * <li>堆场：G。
	 * <li>库位：文件库位值。
	 * <li>入库时间：当前时间。
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	public String importZprk(DrVO entity);

	/**
	 * <p>
	 * 制品盘点
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>jbno</td>
	 * <td>制品NO</td>
	 * <td style="text-align: left;">验证制品NO是否存在和不能为空。</td>
	 * </tr>
	 * <tr>
	 * <td>kw</td>
	 * <td>库位</td>
	 * <td style="text-align: left;">验证库位和不能为空。库位标签以G开头，长度为4</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">根据实物与数据库中进行比较，
	 * <ul>
	 * <li>判断实物是否在数据库中存在;
	 * <li>如果实物在数据库中存在，则判断实物堆场和数据库中对应的堆场是否一致;
	 * <li>
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	public String zppd(DrVO entity, String[] duics);

	/**
	 * <p>
	 * 原板盘点
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	public String scpd(DrVO entity, String[] duics);
}
