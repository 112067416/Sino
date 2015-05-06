package com.quanta.sino.ycai.bo.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.Ybcbmx;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.Ycaicb;
import com.quanta.sino.ycai.vo.DrVO;
import com.quanta.sino.ycai.vo.JchatpQE;

/**
 * <p>
 * 原板业务接口
 * </p>
 * <p>
 * create time : 2010-12-10 上午09:13:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYcaitpBO {
	/**
	 * <p>
	 * 判断是原材是否在实绩录入
	 * </p>
	 * 
	 * @param zsno
	 * @return
	 */
	public boolean isExistedYcaiSjsj(String zsno);

	/**
	 * <p>
	 * 保存原板信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveEntity(YcaiTp entity);

	/**
	 * <p>
	 * 获取原板信息
	 * </p>
	 * 
	 * @param id
	 * @return YcaiTp
	 */
	public YcaiTp getRef(Serializable id);

	/**
	 * <p>
	 * 更新原板信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateEntity(YcaiTp entity);

	/**
	 * <p>
	 * 保存原板信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(YcaiTp entity);

	/**
	 * <p>
	 * 转换为JS对象
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取原板信息
	 * </p>
	 * 
	 * @param id
	 *            主键
	 * @return YcaiTp
	 */
	public YcaiTp get(Serializable id);

	/**
	 * <p>
	 * 获取供应商合同信息
	 * </p>
	 * 
	 * @param id
	 *            主键
	 * @return WwhtTp
	 */
	public WwhtTp getWwht(Serializable id);

	/**
	 * <p>
	 * 原板信息查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YcaiTp> qentity);

	/**
	 * <p>
	 * 修改原板信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(YcaiTp entity);

	/**
	 * <p>
	 * 删除原板信息
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void deletes(String[] jbnos);

	/**
	 * <p>
	 * 删除单个原板信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取修改原板信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public YcaiTp getForUpdate(String jbno);

	/**
	 * <p>
	 * 查询正在实绩录入的原材卷板记录
	 * </p>
	 * 
	 * @param stat
	 * @return
	 */
	public YcaiTp getZzsj(String stat);

	/**
	 * <p>
	 * 根据船名获取原板信息
	 * </p>
	 * 
	 * @param ship
	 * @return List[YcaiTp]
	 */
	public List<YcaiTp> getByShip(String ship);

	/**
	 * <p>
	 * 原板清单导入
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
	 * <td>ybno</td>
	 * <td>供应商合同</td>
	 * <td style="text-align: left;">
	 * 文件中该列是将数据库表的供应商合同号和行号组合起来的。两者之间用'-'隔开。要验证供应商合同号的长度为7，并且行号为3。正确格式为：
	 * 原板表中的供应商合NO、 行号、原板客户、通货区分、制造商规格略称、中日达规格略称、制造商代码、商社代码、品种、等级、采购单价和制造年月日，
	 * 是通过供应商合同号到供应商合同表中查询得到。</td>
	 * </tr>
	 * <tr>
	 * <td>xpho</td>
	 * <td>厚</td>
	 * <td style="text-align: left;">判断供应商合同的尺寸.厚同原板清单的现品厚是否一致</td>
	 * </tr>
	 * <tr>
	 * <td>xpkn</td>
	 * <td>宽</td>
	 * <td style="text-align: left;">判断供应商合同的尺寸.宽同原板清单的现品宽是否一致</td>
	 * </tr>
	 * <tr>
	 * <td>zzsj</td>
	 * <td>制造商卷板NO</td>
	 * <td style="text-align: left;">验证制造商卷板号唯一和不能为空</td>
	 * </tr>
	 * <tr>
	 * <td>zzzp</td>
	 * <td>制造商制品NO</td>
	 * <td style="text-align: left;">不用验证</td>
	 * </tr>
	 * <tr>
	 * <td>tun</td>
	 * <td>重量</td>
	 * <td style="text-align: left;">验证原材卷板重量不能为空和大于0。同时根据原材卷板重量计算原板长度</td>
	 * </tr>
	 * <tr>
	 * <td>face</td>
	 * <td>表面</td>
	 * <td style="text-align: left;">判断供应商合同的表面同原板清单的表面是否一致</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">原板表中，以下字段的获取方式：
	 * <ul>
	 * <li>规格代码。根据制造商规格略称和码表ID:018，在码表中查询得到，取值。
	 * <li>运用规格。根据制造商规格略称和码表ID:019，在码表中查询得到，取值。
	 * <li>内径代码。根据内径值和码表ID:020，在码表中查询得到，取键。
	 * <li>是否保税。根据制造商代码和码表ID:012，在码表中查询得到，取备注。注：1：非保税，2：保税
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	public String importYbqd(DrVO entity);

	/**
	 * <p>
	 * 原板入库
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
	 * <td>operate</td>
	 * <td>操作类别</td>
	 * <td style="text-align: left;">验证操作类别是否存在和不能为空。（21：原板入库)和（31：制品入库）</td>
	 * </tr>
	 * <tr>
	 * <td>jbno</td>
	 * <td>原材卷板NO</td>
	 * <td style="text-align: left;">验证原材卷板NO是否存在和不能为空。</td>
	 * </tr>
	 * <tr>
	 * <td>kw</td>
	 * <td>库位</td>
	 * <td style="text-align: left;">验证库位和不能为空。库位标签以A开头，长度为4</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">原板表中，以下字段的获取方式：
	 * <ul>
	 * <li>堆场：A或B。
	 * <li>库位：文件库位值。
	 * <li>入库时间：当前时间。
	 * <li>原板状态：已入库
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	public String importYbrk(DrVO entity);

	/**
	 * <p>
	 * 原板检查证明书导入
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
	 * <td>zzsj</td>
	 * <td>制造商卷板NO</td>
	 * <td style="text-align: left;">
	 * 验证制造商卷板NO不能为空。同时，判断该制造商卷板NO在原板表中是否存在。只有状态为初始或已入库的原板，才能设置商品检查书信息</td>
	 * </tr>
	 * <tr>
	 * <td>cfcc</td>
	 * <td>C</td>
	 * <td style="text-align: left;">验证成分值C为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfsi</td>
	 * <td>Si</td>
	 * <td style="text-align: left;">验证成分值SI为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfmn</td>
	 * <td>Mn</td>
	 * <td style="text-align: left;">验证成分值MN为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfpp</td>
	 * <td>P</td>
	 * <td style="text-align: left;">验证成分值P为数据类型和不能为空。在文件传入值的基础上乘以1000</td>
	 * </tr>
	 * <tr>
	 * <td>cfss</td>
	 * <td>S</td>
	 * <td style="text-align: left;">验证成分值S为数据类型和不能为空。在文件传入值的基础上乘以1000</td>
	 * </tr>
	 * <tr>
	 * <td>ying</td>
	 * <td>硬度</td>
	 * <td style="text-align: left;">验证硬度为数据类型和不能为空</td>
	 * </tr>
	 * <tr>
	 * <td>chui</td>
	 * <td>吹练NO</td>
	 * <td style="text-align: left;">验证吹练NO不能为空</td>
	 * </tr>
	 * </table>
	 */
	public String importJczms(DrVO entity);

	/**
	 * <p>
	 * 根据原材卷板号，查询原板信息
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param jbnos
	 *            原板卷板号
	 * @return List<YcaiTp>
	 */
	public List<YcaiTp> find(List<String> jbnos);

	/**
	 * <p>
	 * 根据指示书获取原板信息
	 * </p>
	 * 
	 * @param zsno
	 *            指示号
	 * @return
	 */
	public List<YcaiTp> findByZsno(String zsno);

	/**
	 * <p>
	 * 设置原板入库时间
	 * </p>
	 * 
	 * @param ship
	 * @param date
	 */
	public void setRksj(String ship, Date date);

	/**
	 * <p>
	 * 判断船名是否存在
	 * </p>
	 * 
	 * @param ship
	 * @return boolean
	 */
	public boolean isExistedShip(String ship);

	/**
	 * <p>
	 * 计算原板成本
	 * </p>
	 * 
	 * @param vo
	 * @param flag
	 * @param user
	 * @return String
	 */
	public String saveYbcb(Ybcbmx ybcbmx, String flag, User user);

	/**
	 * <p>
	 * 加载原板成本费用
	 * </p>
	 * 
	 * @param ship
	 * @return String
	 */
	public String loadYbcb(String ship);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @return Ycaicb
	 */
	public Ycaicb getYbcb(String jbno);

	/**
	 * <p>
	 * 修改海关加价
	 * </p>
	 * 
	 * @param jbno
	 * @param hgjj
	 * @return String
	 */
	public String updateHgjj(String jbno, Double hgjj);

	/**
	 * <p>
	 * 查询商品检查书
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryJchatp(JchatpQE qentity);

	/**
	 * <p>
	 * 保存商品检查书
	 * </p>
	 * 
	 * @param entity
	 */
	public String saveJchatp(JchaTp entity);

	/**
	 * <p>
	 * 更新商品检查书
	 * </p>
	 * 
	 * @param entity
	 */
	public String updateJchatp(JchaTp entity);

	/**
	 * <p>
	 * 获取商品检查书
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public JchaTp getJchatp(Serializable id);

	/**
	 * <p>
	 * 批量删除商品检查书
	 * </p>
	 * 
	 * @param ids
	 */
	public void deleteJchatps(String[] ids);

	/**
	 * <p>
	 * 删除商品检查书
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteJchatp(Serializable id);

	/**
	 * <p>
	 * 获取商品检查书js
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String getJchatpForJs(String jbno);

	/**
	 * <p>
	 * 是否存在别的实绩录入，状态为未终了或中止
	 * </p>
	 * 
	 * @param jbno
	 *            卷板NO
	 * @param elin
	 *            生产线
	 * @return True 为存在
	 */
	public boolean isZl(String jbno, String elin);

	/**
	 * <p>
	 * 是否存在未终了或未中止的原材
	 * </p>
	 * 
	 * @param zsno
	 * @param jbno
	 * @return
	 */
	public boolean isExistedYcai(String zsno, String jbno);

	/**
	 * <p>
	 * 是否存在供应商合同
	 * </p>
	 * 
	 * @param ybno
	 *            供应商合同NO
	 * @param line
	 *            供应商合同行号
	 * @return
	 */
	public boolean isExistedWwhttp(String ybno, String line);

	/**
	 * <p>
	 * 根据制造商卷板NO,获得原材卷板
	 * </p>
	 * 
	 * @param zzsj
	 *            制造商卷板NO
	 * @return YcaiTp
	 */
	public YcaiTp getByZzsj(String zzsj);

	/**
	 * <p>
	 * 是否存在终了、中止或实绩录入的原材
	 * </p>
	 * 
	 * @param zsno
	 * @return
	 */
	public boolean isExistedYcai(String zsno);

	/**
	 * <p>
	 * 判断原材卷板是否有对应的商品检查证明书
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExistedJchatp(String jbno);

	/**
	 * <p>
	 * 设置业联no
	 * </p>
	 * 
	 * @param jbnos
	 * @param ylno
	 */
	public void setYlno(List<String> jbnos, String ylno);

	/**
	 * <p>
	 * 判断原材是否生产确认
	 * </p>
	 * 
	 * @param jbno
	 * @param sfqr
	 * @return
	 */
	public boolean isExisted(String jbno, String sfqr);

	/**
	 * <p>
	 * 判断原材是否生产确认
	 * </p>
	 * 
	 * @param jbno
	 * @param sfqr
	 * @return boolean
	 */
	public boolean isExistedByJbnoAndSczt(String jbno, String sczt);

	/**
	 * <p>
	 * 根据生产状态查询原材
	 * </p>
	 * 
	 * @param sczt
	 * @return YcaiTp
	 */
	public YcaiTp getYcaiBySczt(String sczt);

	/**
	 * <p>
	 * 判断是否有备用卷
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public boolean isExistedBySczt(String sczt);

	/**
	 * 修改备卷是否向pc600设备发送过数据
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateSfqr(String id, String stat);

	/**
	 * <p>
	 * 修改制定原材卷板的生产状态 当设定卷生产完成的时候 （生产状态变为3） 设定卷的生产完成时间
	 * </p>
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateSczt(String id, String stat);

	/**
	 * <p>
	 * 修改生产状态和是否确认标识
	 * </p>
	 * 
	 * @param id
	 * @param sczt
	 * @param sfqr
	 */
	public void updateScztAndSfqr(String id, String sczt, String sfqr);

	/**
	 * <p>
	 * 是否存在原材
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExistedYcaiByJbno(String jbno);

	/**
	 * <p>
	 * 修改原材硬度
	 * </p>
	 * 
	 * @param jbno
	 * @param ying
	 * @param jdyn
	 * @param ydsj
	 */
	public void updateYd(String jbno, Integer ying, String jdyn, Date ydsj);

	/**
	 * <p>
	 * 查询库存
	 * </p>
	 * 
	 * @param xpho
	 * @param xpkn
	 * @param yuny
	 * @param faces
	 * @return Long
	 */
	public Long getKc(Double xpho, Double xpkn, String yuny, List<String> faces);

	/**
	 * <p>
	 * 批量设置业务No
	 * </p>
	 * 
	 * @param jbnoStart
	 * @param jbnoEnd
	 * @param ylno
	 */
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno);
}
