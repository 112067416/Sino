package com.quanta.sino.ch.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.dao.api.IZng1DAO;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.ch.dao.api.IZxzsDAO;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.ch.vo.KhchtjVO;
import com.quanta.sino.ch.vo.XgZxzsVO;
import com.quanta.sino.ch.vo.ZxzsVO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.ChlldStat;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.CodeSpbh;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.TsStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZxzsTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.vo.CondVO;

/**
 * <p>
 * 装箱指示业务接口实现类
 * </p>
 * <p>
 * create: 2010-12-30 上午10:12:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxzsBO implements IZxzsBO {

	/**
	 * 装箱指示书1数据接口
	 */
	private IZng1DAO zng1DAO;
	/**
	 * 装箱指示书2明细数据接口
	 */
	private IZng2DAO zng2DAO;

	/**
	 * 检查证明书业务接口
	 */
	private IJczmsBO jczmsBO;
	/**
	 * 序号接口
	 */
	private ISeqBO seqBo;

	/**
	 * 订货单业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 出货联络单业务处理接口
	 */
	private IChlldBO cbo;
	/**
	 * 码表接口
	 */
	private ICodeBO codeBo;

	/**
	 * 制品在库业务接口
	 */
	private IZpBO zpbo;

	/**
	 * 用户主文件业务接口
	 */
	private IYongBO yongBo;

	/**
	 * 装箱指示书数据访问接口
	 */
	private IZxzsDAO zxzsDAO;

	/**
	 * 客户付款业务接口
	 */
	private IKhfkBO khfkBo;

	/**
	 * 付款发票业务接口
	 */
	private IFkfpBO fkfpBO;

	/**
	 * 
	 */
	private ICmnBO cmnBO;

	@Override
	public void save(ZxzsVO vo, User user) {
		// 制品列表
		List<ZpngTp> zpngtps = zpbo.listZp(Arrays.asList(vo.getJbnos()));
		if (zpngtps.size() == 0) {
			throw new CocoException(-1, "请选择制作装箱指示书的制品");
		}
		// 生成装箱指示书No
		String zxno = vo.getZxno();
		if (zxzsDAO.isExist(vo.getZxno())) {
			throw new CocoException(-1, "装箱指示书No为" + vo.getZxno() + "的装箱指示书已存在");
		}
		// 做成时间
		Date date = new Date();
		// 订货合同为key,订货合同对应的装箱指示书明细列表为value
		Map<String, List<Zng2Tp>> maps = new HashMap<String, List<Zng2Tp>>();
		// 制品顺序
		int zpss = 1;
		// 订货号行号
		String dhno = null;
		// 装箱指示书明细列表
		List<Zng2Tp> zng2Tps = null;
		// 合计重量
		Double hjzl = 0.0;
		// 循环所选制品
		for (ZpngTp zpngTp : zpngtps) {
			if ((dhno = zpngTp.getDhno()) == null) continue;
			if (zpngTp.getZtbj() != null && !zpngTp.getZtbj().isEmpty()) {
				throw new CocoException(-1, "制品No.为" + zpngTp.getJbno()
						+ "已做停止标记" + zpngTp.getZtbj() + ",不能对其制作装箱指示书");
			}
			if (ChanType.bl.key.equals(zpngTp.getChan())) {
				throw new CocoException(-1, "制品No.为" + zpngTp.getJbno()
						+ "是保留品,不能对其制作装箱指示书");
			}
			// 如果该map的订货号，即key不存在
			if (maps.get(dhno) == null) {
				zpss = 1;
				maps.put(dhno, new ArrayList<Zng2Tp>());
			}
			// 合计重量为吨
			hjzl = NumberUtils.add(hjzl, (zpngTp.getZpzl() / 1000d)).doubleValue();
			zng2Tps = maps.get(dhno);
			zng2Tps.add(parseZng2Tp(zpngTp, date, zpss));
			// 为制品信息设置出货指示书No
			zpngTp.setChno(zxno);
			// 为制品信息设置出货指示年月日
			zpngTp.setChzs(date);
			// 修改制品信息
			zpbo.update(zpngTp);
			// 序号增1
			zpss++;
		}
		// 生成装箱指示对象
		ZxzsTp zxzsTp = parseZxzsTp(vo, date, zxno);
		zxzsTp.setChsu(zpngtps.size());
		zxzsTp.setChzl(NumberUtils.format(hjzl, 3));
		zxzsDAO.save(zxzsTp);
		Zng1Tp zng1Tp = null;
		DhuoTp dhuoTp = null;
		Double chzl = 0d;
		Double chma = 0d;
		Integer zpzl = 0;
		Integer mazl = 0;
		// 货款金额
		// Double hkje = 0d;
		Map<String, String> checkDhno = new HashMap<String, String>();
		Iterator<String> iterator = maps.keySet().iterator();
		// 循环map
		while (iterator.hasNext()) {
			dhno = iterator.next();
			if ((zng2Tps = maps.get(dhno)) == null) {
				continue;
			}
			if (checkDhno.size() > 0
					&& checkDhno.get(dhno.substring(0, 7)) == null) {
				throw new CocoException(-1, "制作装箱指示书的制品不属于相同的订货合同号");
			}
			checkDhno.put(dhno.substring(0, 7), dhno.substring(0, 7));
			// 获取订货合同
			dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(dhno));
			if (dhuoTp == null) {
				throw new CocoException(-1, "订货No." + dhno + "不存在");
			}
			// 装箱指示书做成
			zng1Tp = parseZng1Tp(dhuoTp);
			for (Zng2Tp zng2Tp : zng2Tps) {
				zpzl = zng2Tp.getZpzl();
				mazl = zng2Tp.getMazl();
				chzl = NumberUtils.add(chzl, (zpzl != null ? zpzl / 1000d : 0)).doubleValue();
				chma = NumberUtils.add(chma, (mazl != null ? mazl / 1000d : 0)).doubleValue();
				zng2Tp.setZng1Tp(zng1Tp);
			}

			zng1Tp.setZxzsTp(zxzsTp);
			// 设置出货重量
			zng1Tp.setChzl(NumberUtils.format(chzl, 3));
			// 设置出货毛重量
			zng1Tp.setChma(NumberUtils.format(chma, 3));
			// 设置出货数量
			zng1Tp.setChsu(zng2Tps.size());
			// hkje += chzl * dhuoTp.getHtdj();
			// 保存装箱指示书主表信息
			zng1DAO.save(zng1Tp);
			// 保存装箱指示书明细
			zng2DAO.saveAll(zng2Tps);
			// 生成检查证明书信息，并保存
			// jczmsBO.outJczms(zng1Tp, zng2Tps, dhuoTp, user);
			chzl = 0d;
		}
		// // 判断信用额度
		// if (!checkXyed(hkje, dhuoTp.getUser())) {
		// throw new CocoException(-1, "该用户未付的货款已超过信用额度,不能对其发货");
		// }
		// 如果次日出货联络单不为空 则修改次日出货联络单状态为已打单
		if (vo.getPid() != null && !vo.getPid().isEmpty()) {
			Chlld chlld = cbo.get(vo.getPid());
			// 设置状态为已打单
			chlld.setStat(ChlldStat.YDD.key);
			cbo.update(chlld);
		}
		// 生成装箱指示书No
		seqBo.sequence(Seq.zxzsno.name);
	}

	/**
	 * <p>
	 * 写装箱指示书主表信息
	 * </p>
	 * 
	 * @param vo
	 * @param date
	 * @param zxno
	 * @return Zng1Tp
	 */
	private ZxzsTp parseZxzsTp(ZxzsVO vo, Date date, String zxno) {
		// 写装箱指示书主表信息
		ZxzsTp zxzsTp = new ZxzsTp();
		zxzsTp.setAbbr(vo.getAbbr());
		zxzsTp.setAddr(vo.getAddr());
		zxzsTp.setUser(vo.getUser());
		zxzsTp.setPid(vo.getPid());
		zxzsTp.setName(vo.getName());
		zxzsTp.setChri(vo.getChqi());
		zxzsTp.setCrea(vo.getChqi());
		zxzsTp.setName(vo.getName());
		zxzsTp.setShnm(vo.getShnm());
		zxzsTp.setShno(vo.getShno());
		zxzsTp.setStat(ChStat.WFH.stat);
		zxzsTp.setYsgs(vo.getYsgs());
		zxzsTp.setYsnm(vo.getYsnm());
		zxzsTp.setZxno(zxno);
		return zxzsTp;
	}

	/**
	 * <p>
	 * 写装箱指示书1表信息
	 * </p>
	 * 
	 * @param dhuoTp
	 * @return Zng1Tp
	 */
	private Zng1Tp parseZng1Tp(DhuoTp dhuoTp) {
		// 写装箱指示书主表信息
		Zng1Tp zng1Tp = new Zng1Tp();
		zng1Tp.setFudw(dhuoTp.getFudw());
		zng1Tp.setFufm(dhuoTp.getFufm());
		zng1Tp.setFuzm(dhuoTp.getFuzm());
		zng1Tp.setGgnm(dhuoTp.getGgnm());
		zng1Tp.setGgno(dhuoTp.getGgno());
		zng1Tp.setCang(dhuoTp.getCang());
		zng1Tp.setHouu(dhuoTp.getHouu());
		zng1Tp.setKuan(dhuoTp.getKuan());
		zng1Tp.setDhno(dhuoTp.getDhno());
		zng1Tp.setLine(dhuoTp.getLine());
		String pzno = dhuoTp.getPzno();
		zng1Tp.setPzno(pzno);
		zng1Tp.setYuny(dhuoTp.getYuny());
		zng1Tp.setSpbh(CodeSpbh.getByHouu(dhuoTp.getHouu()));
		zng1Tp.setNwai(dhuoTp.getNwai());
		zng1Tp.setPz(pzno.substring(0, 1));
		zng1Tp.setDeng(pzno.substring(1));
		zng1Tp.setYmin(dhuoTp.getYmin());
		zng1Tp.setYmax(dhuoTp.getYmax());
		zng1Tp.setCond(dhuoTp.getCond());
		zng1Tp.setCdnm(dhuoTp.getCdnm());
		zng1Tp.setKhno(dhuoTp.getKhno());
		zng1Tp.setFace(dhuoTp.getFace());
		return zng1Tp;
	}

	/**
	 * <p>
	 * 写装箱指示书从表信息
	 * </p>
	 * 
	 * @param zpngTp
	 * @param date
	 * @param zpss
	 * @return Zng2Tp
	 */
	private Zng2Tp parseZng2Tp(ZpngTp zpngTp, Date date, Integer zpss) {
		// 写装箱指示书从表信息
		Zng2Tp zng2 = new Zng2Tp();
		zng2.setCrea(date);
		zng2.setJbno(zpngTp.getJbno());
		zng2.setDcno(zpngTp.getKw());
		zng2.setZpss(zpss);
		// 设置制造年月
		zng2.setZzny(zpngTp.getCrea());
		zng2.setChan(zpngTp.getChan());
		zng2.setPlqf(zpngTp.getPlqf());
		zng2.setZzno(zpngTp.getZzno());
		int zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
		int dmzl = zpngTp.getDmzl() != null ? zpngTp.getDmzl() : 0;
		int bzcl = zpngTp.getBzcl() != null ? zpngTp.getBzcl() : 0;
		zng2.setZpzl(zpzl);
		zng2.setJszl(zpngTp.getJszl());
		zng2.setJnzl(zpngTp.getJnzl());
		String pzno = zpngTp.getPzno();
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			zng2.setZshu(null);
			zng2.setJscn(zpngTp.getJscn());
		}
		else {
			zng2.setZshu(zpngTp.getZshu());
			zng2.setJscn(null);
		}
		zng2.setStat(TsStat.CS.stat);
		zng2.setCkno(zpngTp.getCkno());

		zng2.setSjzl(zpngTp.getSjzl());
		zng2.setMazl(zpzl + dmzl + bzcl);
		zng2.setLlyd(zpngTp.getYing());
		zng2.setSczm(zpngTp.getSczm());
		zng2.setScfm(zpngTp.getScfm());
		zng2.setRczpno(zpngTp.getRczpno());
		return zng2;
	}

	@Override
	public String getCheck(String jbnos) {
		if (jbnos == null || jbnos.isEmpty()) {
			return new Result(-1, "请选择制作装箱指示书的制品").toString();
		}
		if (zpbo.isExistedBlbj(jbnos.replaceAll(",", "','"))) {
			return new Result(-2, "制品有现品保留，是否强制出货?").toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public String update(XgZxzsVO vo) {
		ZxzsTp zxzsTp = zxzsDAO.get(vo.getZxno());
		if (zxzsTp == null) {
			return new Result(-1, "该装箱指示书不存在").toString();
		}
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			return new Result(-1, "该装箱指示书状态为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能做作废操作").toString();
		}
		zxzsTp.setChri(vo.getChri());
		zxzsTp.setYsgs(vo.getYsgs());
		zxzsTp.setYsnm(vo.getYsnm());
		zxzsTp.setShnm(vo.getShnm());
		zxzsTp.setShno(vo.getShno());
		zxzsTp.setUpda(new Date());
		zxzsDAO.update(zxzsTp);
		return Result.SUCCESS;
	}

	@Override
	public ZxzsTp get(Serializable id) {
		return zxzsDAO.get(id);
	}

	@Override
	public List<Zng2Tp> findZxzsMx(String zsno) {
		List<Zng2Tp> zng2Tps = zng2DAO.findZxzsMx(zsno);
		if (zng2Tps.size() > 0) {
			for (Zng2Tp entity : zng2Tps) {
				entity.getZng1Tp().getDhno();
				entity.getZng1Tp().getLine();
				entity.getZng1Tp().getZxzsTp().getZxno();
			}
		}
		return zng2Tps;
	}

	@Override
	public List<Zng2Tp> findZxzsMx(String zxno, String dhno, String line) {
		return zng2DAO.findZxzsMx(zxno, dhno, line);
	}

	@Override
	public String delete(String zxno) {
		ZxzsTp zxzsTp = zxzsDAO.get(zxno);
		if (zxzsTp == null) {
			return new Result(-1, "该装箱指示书不存在").toString();
		}
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			return new Result(-1, "该装箱指示书状态为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能做作废操作").toString();
		}
		// 将装箱指示书的状态改为 作废状态
		zxzsTp.setStat(ChStat.ZF.stat);
		zxzsDAO.update(zxzsTp);
		zpbo.deleteZxzs(zxno);
		// List<Zng2Tp> zng2Tps = zng2DAO.findZxzsMx(zxno);
		// if (zng2Tps == null || zng2Tps.size() == 0) {
		// return new Result(-1, "该装箱指示书为空").toString();
		// }
		// ZpngTp zpngTp = null;
		// for (Zng2Tp zng2Tp : zng2Tps) {
		// zpngTp = zpbo.getZp(zng2Tp.getJbno());
		// if (zpngTp == null) {
		// continue;
		// }
		// // 设置值的出货号为空
		// zpngTp.setChno(null);
		// // 设置出货指示年月日为空
		// zpngTp.setChzs(null);
		// zpbo.update(zpngTp);
		// }
		return Result.SUCCESS;
	}

	@Override
	public void queryZxzs(QEntity<ZxzsTp> qentity) {
		zxzsDAO.query(qentity);
	}

	@Override
	public String getForJs(String zxno) {
		ZxzsTp zxzsTp = zxzsDAO.get(zxno);
		if (zxzsTp == null) {
			return new Result(-1, "该装箱指示书不存在").toString();
		}
		if (!ChStat.WFH.stat.equals(zxzsTp.getStat())) {
			return new Result(-1, "该装箱指示书状态为"
					+ ChStat.get(zxzsTp.getStat()).name + ",不能做装箱对照操作").toString();
		}
		return new Result(zxzsTp).toJsObject();
	}

	@Override
	public String getNwai(Serializable id) {
		DhuoTp dhuoTp = dhBo.get(id);
		if (dhuoTp == null) {
			return null;
		}
		return dhuoTp.getNwai();
	}

	@Override
	public int getPageSize(String zxno, Integer size) {
		ZxzsTp zxzsTp = zxzsDAO.get(zxno);
		if (zxzsTp == null) {
			return 0;
		}
		int chsu = zxzsTp.getChsu();
		int pageSize = (chsu % size != 0 ? (chsu / size + 1) : (chsu / size));
		return pageSize;
	}

	@Override
	public void queryZxzsMx(QEntity<Zng2Tp> qentity) {
		zng2DAO.query(qentity);
		List<Zng2Tp> zng2Tps = qentity.getDatas();
		for (Zng2Tp entity : zng2Tps) {
			entity.getZng1Tp().getZxzsTp().getPid();
			entity.getZng1Tp().getDhno();
			entity.getZng1Tp().getLine();
			entity.getZng1Tp().getGgnm();
			entity.getZng1Tp().getGgno();
			entity.getZng1Tp().getFudw();
			entity.getZng1Tp().getFufm();
			entity.getZng1Tp().getFuzm();
			entity.getZng1Tp().getHouu();
			entity.getZng1Tp().getKuan();
			entity.getZng1Tp().getCang();
		}
	}

	@Override
	public void updateZng2(Zng2Tp tp) {
		zng2DAO.update(tp);
	}

	@Override
	public Zng1Tp getUnique(String zxno) {
		return zng1DAO.getUnique(zxno);
	}

	@Override
	public Zng2Tp get(String jbno, String zxno) {
		return zng2DAO.get(jbno, zxno);
	}

	@Override
	public String getMaxNo() {
		return zxzsDAO.getMaxNo();
	}

	@Override
	public Double getTj(String pid) {
		return zxzsDAO.getTj(pid);
	}

	@Override
	public Double getTj(Date chri) {
		return zxzsDAO.getTj(chri);
	}

	@Override
	public List<Zng1Tp> find(String zxno) {
		return zng1DAO.find(zxno);
	}

	@Override
	public List<ChtjVO> queryChtj(Date chriS, Date chriE) {
		return zng1DAO.queryChtj(chriS, chriE);
	}

	@Override
	public Map<Date, List<ChsjVO>> queryChtjByPzno(Date chriS, Date chriE) {
		return zng1DAO.queryChtjByPzno(chriS, chriE);
	}

	@Override
	public Map<Date, List<ChsjVO>> queryChtjByNwai(Date chriS, Date chriE) {
		return zng1DAO.queryChtjByNwai(chriS, chriE);
	}

	@Override
	public Map<Date, List<ChsjVO>> queryChtjByNwai1(Date chriS, Date chriE) {
		return zng1DAO.queryChtjByNwai1(chriS, chriE);
	}

	@Override
	public String getZxnos(Date chri) {
		List<String> zxnos = zxzsDAO.queryZxnos(chri);
		if (zxnos == null || zxnos.size() == 0) {
			return null;
		}
		return StringUtils.join(zxnos, ",");
	}

	@Override
	public int getNum(Date chriS, Date chriE) {
		return zxzsDAO.getNum(chriS, chriE);
	}

	@Override
	public List<CondVO> queryCond(Date chriS, Date chriE) {
		return zng1DAO.queryCond(chriS, chriE);
	}

	@Override
	public CondVO getCond(Date chriS, Date chriE) {
		return zng1DAO.getCond(chriS, chriE);
	}

	@Override
	public boolean isExistByChlld(String pid) {
		return zxzsDAO.isExistByChlld(pid);
	}

	@Override
	public void updateKhno(String dhno, String line, String khno) {
		zng1DAO.updateKhno(dhno, line, khno);
	}

	@Override
	public List<String> queryZxnos(String dhno, String line, String jbno) {
		return zng2DAO.queryZxnos(dhno, line, jbno);
	}

	@Override
	public KhchtjVO getKhchtj(Date current, String user) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);

		KhchtjVO vo = new KhchtjVO();
		vo.setSizeVOs(yongBo.findSize(user));
		vo.setChntjVOs(zng1DAO.queryChnty(cal.getTime(), user));
		return vo;
	}

	@Override
	public long getHjzs(String zxno) {
		return zng2DAO.getHjzs(zxno);
	}

	public IZng1DAO getZng1DAO() {
		return zng1DAO;
	}

	public void setZng1DAO(IZng1DAO zng1dao) {
		zng1DAO = zng1dao;
	}

	public IZng2DAO getZng2DAO() {
		return zng2DAO;
	}

	public void setZng2DAO(IZng2DAO zng2dao) {
		zng2DAO = zng2dao;
	}

	public IJczmsBO getJczmsBO() {
		return jczmsBO;
	}

	public void setJczmsBO(IJczmsBO jczmsBO) {
		this.jczmsBO = jczmsBO;
	}

	public ISeqBO getSeqBo() {
		return seqBo;
	}

	public void setSeqBo(ISeqBO seqBo) {
		this.seqBo = seqBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IChlldBO getCbo() {
		return cbo;
	}

	public void setCbo(IChlldBO cbo) {
		this.cbo = cbo;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IZpBO getZpbo() {
		return zpbo;
	}

	public void setZpbo(IZpBO zpbo) {
		this.zpbo = zpbo;
	}

	public IYongBO getYongBo() {
		return yongBo;
	}

	public void setYongBo(IYongBO yongBo) {
		this.yongBo = yongBo;
	}

	public IZxzsDAO getZxzsDAO() {
		return zxzsDAO;
	}

	public void setZxzsDAO(IZxzsDAO zxzsDAO) {
		this.zxzsDAO = zxzsDAO;
	}

	public IKhfkBO getKhfkBo() {
		return khfkBo;
	}

	public void setKhfkBo(IKhfkBO khfkBo) {
		this.khfkBo = khfkBo;
	}

	public IFkfpBO getFkfpBO() {
		return fkfpBO;
	}

	public void setFkfpBO(IFkfpBO fkfpBO) {
		this.fkfpBO = fkfpBO;
	}

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}

}
