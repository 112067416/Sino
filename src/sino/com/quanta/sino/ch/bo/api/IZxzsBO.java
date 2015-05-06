package com.quanta.sino.ch.bo.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.KhchtjVO;
import com.quanta.sino.ch.vo.XgZxzsVO;
import com.quanta.sino.ch.vo.ZxzsVO;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZxzsTp;
import com.quanta.sino.yygl.vo.CondVO;

/**
 * <p>
 * 装箱指示业务接口
 * </p>
 * <p>
 * create: 2010-12-30 上午11:28:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IZxzsBO {

	/**
	 * <p>
	 * 制作装箱指示书
	 * <ul>
	 * <li>1.判断用户的信用额度，方法（当前货款金额-付款情况金额）与当前客户的信用额度比较，若信用额度不够则不能做装箱指示书
	 * <li>2.自动生成装箱指示书号，作为装箱指示书标识
	 * <li>3.根据所选择的制品写ZxzsTp写Zng1Tp写装箱指示书明细（Zng2Tp）
	 * <li>4.修改制品信息--填写出货号，出货指示年月
	 * <li>5.生成制品检查证明书
	 * <li>6.修改次日出货联络单的状态为已打单如果没有次日出货联络单则不修改状态
	 * </ul>
	 * </p>
	 * 
	 * @param vo
	 * @param user
	 */
	public void save(ZxzsVO vo, User user);

	/**
	 * <p>
	 * 判断制作装箱指示书
	 * </p>
	 * 
	 * @param jbnos
	 * @return String
	 */
	public String getCheck(String jbnos);

	/**
	 * <p>
	 * 修改装箱指示书
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	public String update(XgZxzsVO vo);

	/**
	 * <p>
	 * 查询装箱指示书
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZxzs(QEntity<ZxzsTp> qentity);

	/**
	 * <p>
	 * 获取装箱指示书
	 * </p>
	 * 
	 * @param id
	 * @return ZxzsTp
	 */
	public ZxzsTp get(Serializable id);

	/**
	 * <p>
	 * 装箱指示书作废
	 * <ul>
	 * <li>将装箱指示书状态改为作废状态
	 * <li>将装箱指示书相应的制品的出货号和出货指示年月设为空
	 * </ul>
	 * </p>
	 * 
	 * @param zxno
	 * @return String
	 */
	public String delete(String zxno);

	/**
	 * <p>
	 * 获取装箱指示书信息
	 * </p>
	 * 
	 * @param zxno
	 * @return String
	 */
	public String getForJs(String zxno);

	/**
	 * <p>
	 * 获取订货合同中的内外销字段
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getNwai(Serializable id);

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
	 * 查询出货明细
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryZxzsMx(QEntity<Zng2Tp> qentity);

	/**
	 * <p>
	 * 修改装箱指示书明细
	 * </p>
	 * 
	 * @param tp
	 */
	public void updateZng2(Zng2Tp tp);

	/**
	 * <p>
	 * 获取装箱指示书列表
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Zng1Tp]
	 */
	public List<Zng1Tp> find(String zxno);

	/**
	 * <p>
	 * 获取装箱指示书明细
	 * </p>
	 * 
	 * @param zxno
	 * @return List[Zng2Tp]
	 */
	public List<Zng2Tp> findZxzsMx(String zxno);

	/**
	 * <p>
	 * 查询装箱指示明细
	 * </p>
	 * 
	 * @param zxno
	 * @param dhno
	 * @param line
	 * @return List<Zng2Tp>
	 */
	public List<Zng2Tp> findZxzsMx(String zxno, String dhno, String line);

	/**
	 * <p>
	 * 获取装箱指示书主表
	 * </p>
	 * 
	 * @param zxno
	 * @return Zng1Tp
	 */
	public Zng1Tp getUnique(String zxno);

	/**
	 * <p>
	 * 获取装箱批示书明细
	 * </p>
	 * 
	 * @param jbno
	 * @param zxno
	 * @return Zng2Tp
	 */
	public Zng2Tp get(String jbno, String zxno);

	/**
	 * <p>
	 * 获取装箱指示书最大的号码
	 * </p>
	 */
	public String getMaxNo();

	/**
	 * <p>
	 * 统计次日出货联络单，对应的打单数量
	 * </p>
	 * 
	 * @param pid
	 * @return Double
	 */
	public Double getTj(String pid);

	/**
	 * <p>
	 * 统计次日出货联络单，对应的打单数量
	 * </p>
	 * 
	 * @param chri
	 * @return Double
	 */
	public Double getTj(Date chri);

	/**
	 * <p>
	 * 按天统计，出货重量
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return List
	 */
	public List<ChtjVO> queryChtj(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货时间段和品种，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByPzno(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货时间段和内外销，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByNwai(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货时间段和内外销，统计出货实绩
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return Map<Date, List<ChsjVO>>
	 */
	public Map<Date, List<ChsjVO>> queryChtjByNwai1(Date chriS, Date chriE);

	/**
	 * <p>
	 * 统计指定日期的送货单No.
	 * </p>
	 * 
	 * @param chri
	 * @return String
	 */
	public String getZxnos(Date chri);

	/**
	 * <p>
	 * 统计一个时间段出货的天数
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return int
	 */
	public int getNum(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货实绩统计加工作途
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return List<CondVO>
	 */
	public List<CondVO> queryCond(Date chriS, Date chriE);

	/**
	 * <p>
	 * 根据出货实绩统计加工作途
	 * </p>
	 * 
	 * @param chriS
	 * @param chriE
	 * @return CondVO
	 */
	public CondVO getCond(Date chriS, Date chriE);

	/**
	 * <p>
	 * 判断出货联络单是否有对应的装箱指示书
	 * </p>
	 * 
	 * @param pid
	 * @return boolean
	 */
	public boolean isExistByChlld(String pid);

	/**
	 * <p>
	 * 修改客户订货单号
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param khno
	 */
	public void updateKhno(String dhno, String line, String khno);

	/**
	 * <p>
	 * 根据订货号和制品号，查询对应的装箱指示书NO.
	 * </p>
	 * 
	 * @param dhno
	 * @param line
	 * @param jbno
	 * @return List<String>
	 */
	public List<String> queryZxnos(String dhno, String line, String jbno);

	/**
	 * <p>
	 * 获得客户当前出货量
	 * </p>
	 * 
	 * @param current
	 * @return KhchtjVO
	 */
	public KhchtjVO getKhchtj(Date current, String user);

	/**
	 * <p>
	 * 获得送货单的总张数
	 * </p>
	 * 
	 * @param zxno
	 * @return long
	 */
	public long getHjzs(String zxno);

}
