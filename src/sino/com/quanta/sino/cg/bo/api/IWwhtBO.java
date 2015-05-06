package com.quanta.sino.cg.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cg.vo.BaseVO;
import com.quanta.sino.cg.vo.DrVO;
import com.quanta.sino.cg.vo.WwhtSaveVO;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.YbCgdj;

/**
 * <p>
 * 供应商合同业务访问接口
 * </p>
 * <p>
 * create: 2010-12-22 上午10:27:34
 * </p>
 * 
 * @author 张良[jimsnhappy@126.com]
 * @version 1.0
 */
public interface IWwhtBO {

	/**
	 * <p>
	 * 新增未完合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(WwhtTp entity);

	/**
	 * <p>
	 * 批量增加供应商合同
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<WwhtTp> entities);

	/**
	 * <p>
	 * 更新未完合同
	 * </p>
	 * 
	 * @param wsvo
	 * @param $htno
	 * @param $line
	 */
	public void update(WwhtSaveVO wsvo, String $htno, String $line);

	/**
	 * <p>
	 * 批量删除合同
	 * </p>
	 * 
	 * @param htnoAndLines
	 *            合同No.和行号组合数组(no-line)
	 */
	public void delete(List<String> htnoAndLines);

	/**
	 * <p>
	 * 查询合同
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<WwhtTp> qentity);

	/**
	 * <p>
	 * 将获取的对象转化成JS
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 以合同号获取合同
	 * </p>
	 * 
	 * @param id
	 *            合同组合主键（合同NO-行号）
	 * @return WwhtTp 供应商合同实体
	 */
	public WwhtTp get(Serializable id);

	/**
	 * <p>
	 * 修改到货数量
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param id
	 *            供应商合同主键
	 * @param zpzl
	 *            原材卷板重量
	 */
	public void updateDhsl(Serializable id, Double zpzl);

	/**
	 * <p>
	 * 供应商合同导入。文件包含内容如下：
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
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
	 * <td>htno</td>
	 * <td>供应商合同-行号</td>
	 * <td>文件中该列是将数据库表的供应商合同号和行号组合起来的。两者之间用'-'隔开。要验证供应商合同号的长度为7，并且行号为3。正确格式为：
	 * XXXXXXX-XXX</td>
	 * </tr>
	 * <tr>
	 * <td>zzsd</td>
	 * <td>制造商</td>
	 * <td>文件中该列存的是制造商代码。要验证制造商代码不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称。对应的码表为：012</td>
	 * </tr>
	 * <tr>
	 * <td>face</td>
	 * <td>表面</td>
	 * <td>验证表面不能为空和在码表中是否存在。对应的码表为：005</td>
	 * </tr>
	 * <tr>
	 * <td>houu</td>
	 * <td>尺寸.厚</td>
	 * <td>验证尺寸.厚不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>kuan</td>
	 * <td>尺寸.宽</td>
	 * <td>验证尺寸.宽不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>htzl</td>
	 * <td>重量</td>
	 * <td>验证合同重量不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>htdj</td>
	 * <td>单价</td>
	 * <td>验证合同单价不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>zzgg</td>
	 * <td>制造商规格略称</td>
	 * <td>验证制造商规格略称不能为空和在码表中是否存在,同时根据制造商规格略称获得中日达规格略称。对应的码表为：017</td>
	 * </tr>
	 * <tr>
	 * <td>abbr</td>
	 * <td>用户略称</td>
	 * <td>不用验证</td>
	 * </tr>
	 * <tr>
	 * <td>pzno</td>
	 * <td>品种</td>
	 * <td>验证品种不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表。对应的码表为：118和119</td>
	 * </tr>
	 * <tr>
	 * <td>neij</td>
	 * <td>内径</td>
	 * <td>验证内径不能为空和在码表中是否存在</td>
	 * </tr>
	 * <tr>
	 * <td>fpdj</td>
	 * <td>等级</td>
	 * <td>验证不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表。对应的码表为：103、104和105</td>
	 * </tr>
	 * </table>
	 */
	public void importHt(DrVO vo);

	/**
	 * <p>
	 * 查询原板采购单价
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryCgdj(QEntity<YbCgdj> qentity);

	/**
	 * <p>
	 * 保存原板采购单价
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String saveCgdj(YbCgdj entity);

	/**
	 * <p>
	 * 删除采购单价
	 * </p>
	 * 
	 * @param ids
	 */
	public void deleteCgdj(String[] ids);

	/**
	 * <p>
	 * 计算原板月采购单价
	 * </p>
	 * 
	 * @param vo
	 */
	public void calculateCgdj(BaseVO vo);

	/**
	 * <p>
	 * 加载采购单价信息
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String loadCgdj(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @return
	 */
	public String getBase();

}
