package com.quanta.sino.ss.bo;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.coco.core.excel.api.ExcelDataExecuter;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.IBanBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code104;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IMgBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.Hbbjl;
import com.quanta.sino.orm.PlscItemLp;
import com.quanta.sino.orm.PlscLp;
import com.quanta.sino.orm.PlxmLp;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.sj.bo.api.ISjBO;
import com.quanta.sino.ss.bo.api.ISsBO;
import com.quanta.sino.ss.dao.api.ISsDAO;
import com.quanta.sino.ss.vo.PlscLpQE;
import com.quanta.sino.ss.vo.SlsjVO;
import com.quanta.sino.ss.vo.SsBlVO;
import com.quanta.sino.ss.vo.SsItemVO;
import com.quanta.sino.ss.vo.SsRzVO;
import com.quanta.sino.ss.vo.SsVO;
import com.quanta.sino.ss.vo.SsXmVO;
import com.quanta.sino.ss.vo.SsxpVO;
import com.quanta.sino.ss.vo.ssXmItemVO;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * SS分选业务实现
 * </p>
 * <p>
 * create: 2011-1-20 下午07:47:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsBO implements ISsBO {

	private ISsDAO dao;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 公共业务接口
	 */
	private ICmnBO cmnBo;

	/**
	 * 订货业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 订货指示业务接口
	 */
	private IZsBO zsBo;

	/**
	 * 生产线别业务接口
	 */
	private IScxbBO scxbBo;

	/**
	 * 公共实绩业务接口
	 */
	private ISjBO sjBo;

	/**
	 * 代码业务接口
	 */
	private ICodeBO codeBo;

	/**
	 * 分选日志路径
	 */
	private String rizhiPath;

	/**
	 * 余材化操作
	 */
	private IYchBO ychBO;

	/**
	 * 分析日志处理接口
	 */
	private ExcelbookDataExecuter<SsRzVO> rizhiExec;

	/**
	 * 检查步留统计路径
	 */
	private String bltjPath;

	/**
	 * 分检查步留统计处理接口
	 */
	private ExcelDataExecuter<SsBlVO> bltjExec;

	/**
	 * 检查步留明细路径
	 */
	private String blmxPath;

	/**
	 * 分检查步留明细处理接口
	 */
	private ExcelDataExecuter<SsBlVO> blmxExec;
	/**
	 * 班别接口
	 */
	private IBanBO banBO;
	/**
	 * 执木库存接口
	 */
	private IMgBO dmBO;

	@Override
	public SsItemVO fetchItem(String jbno, SsItemVO header) {
		if (jbno == null || jbno.length() < 8 || jbno.length() > 11) {
			throw new CocoException(-1, "无效的包号");
		}
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-2, "无法获取制品信息");
		}

		// 选别条件：
		// 1. 删除标记 SCBJ_ ：0（0-初始；1-已实绩终了；2-已消灭；3-已出货；4-异常处理删除）
		// 2. 堆场 DHUC_：E（A-G）
		// 3. 作业停止 ZTBJ_：为空
		// 4. 出货指示NO CHNO_：为空
		// 删除标记 SCBJ_ :0-初始
		if (!ZtConstants.ZPNG_SC_CSZ.equals(zpng.getScbj())) {
			throw new CocoException(-3, "该制品的删除标记不允许生成新制品");
		}
		// 余材品
		// if (EFpyc.YC.key.equals(zpng.getFpyc())) {
		// throw new CocoException(-3, "该制品为余材品不允许生成新制品");
		// }
		// 仅E堆场
		if (!DC.E.name.equals(zpng.getDuic())) {
			throw new CocoException(-3, "没有在指定的E堆场里");
		}
		// 作业停止 ZTBJ_：为空
		if (zpng.getZtbj() != null && !zpng.getZtbj().isEmpty()) {
			throw new CocoException(-3, "该制品已经作业停止");
		}
		// 出货指示NO CHNO_：为空
		if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
			throw new CocoException(-3, "该制品已经出货");
		}
		// 若已存在头行条件，则比较是否与头行数据一致
		if (header != null) {
			// 订货行号是否一致
			if (zpng.getDhno() != null) {
				if (!zpng.getDhno().equals(header.getDhno())) {
					throw new CocoException(-3, "订货No.不一致");
				}
			}
			else if (header.getDhno() != null) {
				throw new CocoException(-3, "订货No.不一致");
			}

			// if ((zpng.getDhno() == null)
			// || (zpng.getDhno() != null &&
			// !zpng.getDhno().equals(header.getDhno()))) {
			// // 客户是否一致
			// if (zpng.getUser() == null
			// || !zpng.getUser().equals(header.getUser())) {
			// throw new CocoException(-3, "客户信息不一致");
			// }
			// // 尺寸是否一致
			// if (zpng.getHouu() != null && header.getHouu() == null
			// || zpng.getHouu() == null && header.getHouu() != null
			// || zpng.getHouu().doubleValue() != header.getHouu()
			// || zpng.getKuan() != null && header.getKuan() == null
			// || zpng.getKuan() == null && header.getKuan() != null
			// || zpng.getKuan().doubleValue() != header.getKuan()
			// || zpng.getCang() != null && header.getCang() == null
			// || zpng.getCang() == null && header.getCang() != null
			// || zpng.getCang().doubleValue() != header.getCang()) {
			// throw new CocoException(-3, "制品尺寸信息不一致");
			// }
			//
			// // 规格是否一致
			// if (zpng.getGgno() == null
			// || !zpng.getGgno().equals(header.getGgno())) {
			// throw new CocoException(-3, "规格不一致");
			// }
			// if (zpng.getYuny() == null
			// || !zpng.getYuny().equals(header.getYuny())) {
			// throw new CocoException(-3, "用途不一致");
			// }
			// // 镀锡量是否一致
			// if (zpng.getFuzm() == null
			// || !zpng.getFuzm().equals(header.getFuzm())
			// || zpng.getFufm() == null
			// || !zpng.getFufm().equals(header.getFufm())) {
			// throw new CocoException(-3, "镀锡量信息不一致");
			// }
			// }
		}

		// 设置分析项目信息
		SsItemVO vo = new SsItemVO();
		vo.setJbno(jbno);
		vo.setDhno(zpng.getDhno());
		vo.setUser(zpng.getUser());
		vo.setFuzm(zpng.getFuzm());
		vo.setFufm(zpng.getFufm());
		vo.setGgno(zpng.getGgno());
		vo.setYuny(zpng.getYuny());
		vo.setDeng(zpng.getDeng());
		vo.setAbbr(zpng.getAbbr());
		vo.setHouu(zpng.getHouu());
		vo.setKuan(zpng.getKuan());
		vo.setCang(zpng.getCang());
		vo.setZshu(zpng.getZshu());
		// 附业kpns
		if (zpng.getKpn1() != null) {
			vo.getKpns().add(zpng.getKpn1());
		}
		if (zpng.getKpn2() != null) {
			vo.getKpns().add(zpng.getKpn2());
		}
		if (zpng.getKpn3() != null) {
			vo.getKpns().add(zpng.getKpn3());
		}
		if (zpng.getKpn4() != null) {
			vo.getKpns().add(zpng.getKpn4());
		}
		String ylno = zpng.getYlno();
		if (ylno != null && !ylno.isEmpty()) {
			String type;
			for (String yln : ylno.split(",")) {
				type = yln.substring(0, 1);
				if (EKpType.SL.key.equals(type)
						|| EKpType.BOTH.key.equals(type)) {
					vo.getYlns().add(yln.substring(2));
				}
			}
		}
		vo.setSjzl(zpng.getSjzl());
		vo.setPlqf(zpng.getPlqf());
		vo.setQqdm(zpng.getQqdm());
		vo.setQqd2(zpng.getQqd2());
		vo.setQqd3(zpng.getQqd3());
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.quanta.sino.ss.bo.api.ISsBO#build(com.quanta.sino.ss.vo.SsVO)
	 */
	@Override
	public void build(SsVO vo) {
		if (vo.getItems() == null || vo.getItems().size() == 0) {
			throw new CocoException(-1, "至少要填写一个中间制品");
		}
		SsItemVO header = null;
		// 有效的项目，并给定头行信息
		List<SsItemVO> items = new ArrayList<SsItemVO>();
		for (SsItemVO itemVo : vo.getItems()) {
			if (itemVo.getJbno() == null || itemVo.getJbno().isEmpty()) {
				continue;
			}
			if (header == null) {
				header = itemVo;
			}
			items.add(itemVo);
		}
		if (header.getFuzm() == null
				|| !header.getFuzm().equals(header.getFufm())) {
			vo.setDiffDxl(true);
		}
		// 根据头行信息设置分选信息
		vo.setItems(items);
		String parentNo = cmnBo.parentNo(header.getJbno());
		String[] nos = cmnBo.generateNo(parentNo, NoGenType.end);
		vo.setJbno(nos[0]);
		vo.setHouu(header.getHouu());
		vo.setKuan(header.getKuan());
		vo.setCang(header.getCang());
		vo.setDhno(header.getDhno());
	}

	/*
	 * (non-Javadoc)
	 * @see com.quanta.sino.ss.bo.api.ISsBO#save(com.quanta.sino.ss.vo.SsVO)
	 */
	@Override
	public void save(SsVO vo) {
		// 获取复制源制品信息
		ZpngTp header = zpBo.getZp(vo.getItems().get(0).getJbno());
		if (header == null) {
			throw new CocoException(-1, "无法获取制品信息");
		}

		// 获取订货信息
		DhuoTp dhuo = null;
		// 取中间制品
		ZpngTp zjzpng = zpBo.getZp(header.getJbno());
		String zsno = null;
		if (zjzpng != null) {
			// 取入端制品
			ZpngTp rcZp = zpBo.getZp(zjzpng.getRczpno());
			if (rcZp != null) {
				zsno = rcZp.getZsno();
				// 取指示书
				ZsdhTp zss = zsBo.getZsdhTp(rcZp.getZsno());
				if (zss != null) {
					// 获取订货信息

					dhuo = dhBo.getRef(SinoUtils.parseDhuoPk(zss.getDhno()));
					// 更新垫木库存
					dmBO.updateDmkz(zss.getKuan(), zss.getCang(), zss.getDmfx(), zss.getKbao(), ZtConstants.MG_KW, "分选", 1);
				}

			}
		}
		// 参数检查
		validate(vo, dhuo, header.getBdan());

		// 考虑到多个操作员操作异步的情况，重新生成包号，和其他信息
		build(vo);

		// 正反附着量不一样时，要求翻面
		if (ChanType.hg.key.equals(vo.getChan())
				&& !header.getFuzm().equals(header.getFufm())
				&& !ZtConstants.ZPNG_SFFM_FM.equals(vo.getSffz())) {
			throw new CocoException(-1, "正反附着量不一样，需求值是否翻面为"
					+ ZtConstants.ZPNG_SFFM_FM);
		}

		// if (dhuo == null) {
		// throw new CocoException(-1, "无法获取制品的订货信息");
		// }
		// 当前时间
		Date date = new Date();

		// ---------写入制品信息-------
		ZpngTp zpng = new ZpngTp();
		ReflectUtils.copy(zpng, header, false);
		zpng.setJbno(vo.getJbno());
		// 张数
		zpng.setZshu(vo.getZshu());
		// 实际重量
		zpng.setSjzl(vo.getSjzl());
		// 计算重量 JSZL_
		Integer jszl = 0;
		if (zpng.getBdan() != null) {
			jszl = NumberUtils.formatDouToInt((vo.getZshu() * zpng.getBdan()), 0, BigDecimal.ROUND_HALF_UP);
		}
		zpng.setJszl(jszl);
		// 净重量 JNZL_
		zpng.setJnzl(SinoUtils.fetchJnzl(zpng.getJhnr(), zpng.getSjzl(), zpng.getJszl(), zpng.getChan()));
		// 毛重量 MAZL_
		zpng.setMazl(SinoUtils.fetchMazl(zpng.getJhnr(), zpng.getJnzl(), zpng.getBzcl(), zpng.getDmzl()));
		// 制品重量 ZPZL_
		zpng.setZpzl(SinoUtils.fetchZpzl(zpng));

		// 产量代码
		zpng.setChan(vo.getChan());
		// 等级
		zpng.setDeng(vo.getDeng());
		// 堆场，改为F
		zpng.setDuic(DC.F.name);
		// 库位
		zpng.setKw(DC.F.name);
		// 删除指示号
		zpng.setZsno(null);
		// 是否翻面
		zpng.setFmby(vo.getSffz());
		// 设定班组
		zpng.setBan(vo.getBan());
		zpng.setZu(vo.getZu());

		// 缺陷代码
		zpng.setQqdm(vo.getQqdm());
		zpng.setJdyn(vo.getJdyn());
		zpng.setJsyn(vo.getJsyn());
		zpng.setJdjs(ZtConstants.ZPNG_JD_JDJS);
		zpng.setJdjz(ZtConstants.ZPNG_JD_JDJZ);
		// Pile生成实绩登录年月日
		zpng.setPssd(date);
		// 创建时间
		zpng.setCrea(date);
		// 删除标志
		zpng.setScbj(EScbj.CS.key);
		// 状态 STAT_
		zpng.setStat(ZpStat.CS.stat);
		zpng.setBan(vo.getBan());
		zpng.setZu(vo.getZu());
		// ========运算得出类==============
		// 若为外销，生成出口包装号
		if (CodeNwx.wai == CodeNwx.get(zpng.getNwai())) {
			zpng.setCkno(cmnBo.generatePackageNo(zpng.getDhno()));
		}

		// Pile区分
		// 以第一个中间制品的“PILE区分”作判定依据。即为第一个中间制品的SL生产线上所生产出的合格品的“PILE区分”。
		Scxbpz scxb = scxbBo.getByCode(ProductType.sl, zpng.getSlin());
		if (scxb == null) {
			throw new CocoException(-1, "无法获取制品的SL流水线号");
		}
		zpng.setPlqf(scxb.getQualified());
		// 捆包形式 KBAO_
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			if (dhuo != null) {
				zpng.setKbao(dhuo.getKbao());
			}

		}
		else {
			Double kuan = 0.0;
			if (zpng.getKuan() != null) {
				kuan = zpng.getKuan();
			}
			Double cang = 0.0;
			if (zpng.getCang() != null) {
				cang = zpng.getCang();
			}
			if (kuan.doubleValue() > 850 || cang > 850) {
				zpng.setKbao("B31");
			}
			else {
				zpng.setKbao("B21");
			}
		}
		ychBO.doScYch(zpng, null, dhuo, date);
		zpng.setZsno(zsno);
		zpng.setZtbj(null);
		zpBo.save(zpng);

		// ---------修改订货DB记录-------
		// 累计SL合格重量
		Integer zpzl = SinoUtils.fetchZpzl(zpng);
		if (zpzl == null || zpzl <= 0) {
			throw new CocoException(-1, "无法获取制品的制品种类");
		}
		// 合格的累计
		if (dhuo != null) {
			if (ChanType.hg.key.equals(vo.getChan())
					|| ChanType.bl.key.equals(vo.getChan())) {

				Double slhg = dhuo.getSlhg();
				slhg = slhg + zpzl * 0.001;
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
			}

		}

		// ---------写入实绩日志（指示情报共通格式、现品情报共通格式）---------
		Xpfmt xpfmt = sjBo.saveXpfmt(zpng, ZtConstants.ZPNG_CLQ_ADD);
		// SjVO sjVo = sjBo.saveForZp(zpng.getJbno());

		// ---------写入SS生产实绩日志---------

		PlscLp entity = parsePlscLp(zpng, xpfmt, date);
		dao.save(entity);

		// ---------写入合并包记录，修改原制品张数---------
		Hbbjl hbb = null;
		PlscItemLp plitem = null;
		int index = 1;
		ZpngTp zpitem;
		for (SsItemVO item : vo.getItems()) {
			// 新增合并包记录
			hbb = new Hbbjl();
			hbb.setPlsc(entity.getId());
			hbb.setXbh(zpng.getJbno());
			hbb.setZjbh(item.getJbno());
			hbb.setXh(index++);
			hbb.setZs(item.getZshu());
			hbb.setPlqf(item.getPlqf());
			dao.save(hbb);
			// 修改原制品精整实绩标志
			zpitem = zpBo.getRef(item.getJbno());
			// int zshu = zpitem.getZshu() - item.getZshu();
			// zpitem.setZshu(zshu > 0 ? zshu : 0);
			zpitem.setJdjs(ZtConstants.ZPNG_JD_JDJS);
			// 计算重量、实际重量、制品重量，净重等是否要改动？
			// ----------写中间包日志--------------
			plitem = new PlscItemLp();
			plitem.setJbn(item.getJbno());
			plitem.setPlq(item.getPlqf());
			plitem.setZsu(item.getZshu());
			plitem.setCrea(date);
			plitem.setUpda(date);
			plitem.setPlscLp(entity);
			dao.savePlscItem(plitem);
		}

	}

	private PlscLp parsePlscLp(ZpngTp zpng, Xpfmt xpfmt, Date date) {
		PlscLp entity = new PlscLp();
		entity.setCrea(date);
		// entity.setZsfmt(null);
		entity.setXpfmt(xpfmt);
		entity.setJdyn(zpng.getJdyn());
		entity.setJsyn(zpng.getJsyn());
		entity.setZshu(zpng.getZshu());
		entity.setFmby(null);
		entity.setBan(zpng.getBan());
		entity.setZu(zpng.getZu());
		entity.setDhno(zpng.getDhno());
		entity.setAbbr(zpng.getAbbr());
		entity.setFace(zpng.getFace());
		entity.setHouu(zpng.getHouu());
		entity.setKuan(zpng.getKuan());
		entity.setCang(zpng.getCang());
		entity.setYuny(zpng.getYuny());
		entity.setFudw(zpng.getFudw());
		entity.setFuzm(zpng.getFuzm());
		entity.setFufm(zpng.getFufm());
		entity.setZzsd(zpng.getZzsd());
		entity.setKpn1Flag(zpng.getKpn1Flag());
		entity.setKpn1(zpng.getKpn1());
		entity.setKpn2Flag(zpng.getKpn2Flag());
		entity.setKpn2(zpng.getKpn2());
		entity.setKpn3Flag(zpng.getKpn3Flag());
		entity.setKpn3(zpng.getKpn3());
		entity.setKpn4Flag(zpng.getKpn4Flag());
		entity.setKpn4(zpng.getKpn4());
		entity.setYlno(zpng.getYlno());
		return entity;
	}

	@Override
	public void query(QEntity<SlsjVO> page) {
		dao.query(page);
	}

	/**
	 * <p>
	 * 让正和删除验证
	 * </p>
	 */
	private void delUpdaValidate(String jbno) {
		if (jbno == null || jbno.isEmpty()) {
			throw new CocoException(-1, "异常：获取板材号为空");
		}
		// 取制品信息
		ZpngTp zp = zpBo.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "异常：无法获取制品信息");
		}
		// 删除标志不为0
		if (!EScbj.CS.key.equals(zp.getScbj())) {
			throw new CocoException(-1, "删除标志不是初始，不能删除或让正");
		}

		// 已经消灭的不能删除
		// if (dao.isExisted(jbno)) {
		// throw new CocoException(-1, "已经消灭，不能删除或让正");
		// }
		// 未超过实绩录入24小时
		// Date nowDate = new Date();
		// if (zp.getCrea() != null
		// && (nowDate.getTime() - zp.getCrea().getTime()) > 86400000) {
		// throw new CocoException(-1, "实绩登录已超过24小时，不能删除或让正！");
		// }
		// 不是F堆场
		if (!DC.F.name.equals(zp.getDuic())) {
			throw new CocoException(-1, "不是F堆场，不能删除或让正！");
		}
	}

	@Override
	public void delete(String jbno) {
		// 检查
		delUpdaValidate(jbno);
		// 取制品信息
		ZpngTp zp = zpBo.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "异常：无法获取制品信息");
		}
		Date date = new Date();
		// ========更新订货合格量==============
		// 获取订货信息
		DhuoTp dhuo = dhBo.getRef(SinoUtils.parseDhuoPk(zp.getDhno()));
		// 新生成的包合格
		if (ChanType.hg.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			if (dhuo != null) {
				// 累计SL合格重量
				Double slhg = 0.0;
				Integer zpzl = zp.getZpzl();
				slhg = slhg - zpzl / 1000.0;
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
			}
		}
		// ---------写入实绩日志（指示情报共通格式、现品情报共通格式）---------
		Xpfmt xpfmt = sjBo.saveXpfmt(zp, ZtConstants.ZPNG_CLQ_DEL);
		// SjVO sjVo = sjBo.saveForZp(zpng.getJbno());

		// ---------写入SS生产实绩日志---------
		PlscLp entity = parsePlscLp(zp, xpfmt, date);
		dao.save(entity);
		// ========更新中间品的张数==============
		// 更新中间品
		List<Hbbjl> sstiemold = dao.findHbbjl(jbno);
		// 删除旧的中间包
		dao.deletebyxbh(jbno);
		// 保存界面的中间包
		// Integer zshu = 0;
		ZpngTp orig;
		for (Hbbjl hb : sstiemold) {
			// 修改原制品张数为原张数+合并掉张数
			// zshu = hb.getZs();
			orig = zpBo.getRef(hb.getZjbh());
			// int zshuAll = orig.getZshu() + zshu;
			// orig.setZshu(zshuAll > 0 ? zshuAll : 0);
			// 置删除标志为初始
			orig.setScbj(ZtConstants.ZPNG_SC_CSZ);
			orig.setJdjs(ZtConstants.ZPNG_SC_CSZ);
			// ----------写中间包日志--------------
			PlscItemLp plitem = new PlscItemLp();
			plitem.setJbn(hb.getZjbh());
			plitem.setPlq(hb.getPlqf());
			plitem.setZsu(hb.getZs());
			plitem.setCrea(date);
			plitem.setUpda(date);
			plitem.setPlscLp(entity);
			dao.savePlscItem(plitem);
		}
		// ========删除新包==============
		// 取指示书
		ZsdhTp zss = zsBo.getZsdhTp(zp.getZsno());
		if (zss != null) {
			// 更新垫木库存
			dmBO.updateDmkz(zss.getKuan(), zss.getCang(), zss.getDmfx(), zss.getKbao(), ZtConstants.MG_KW, "分选", -1);
		}
		zpBo.delete(jbno);

	}

	@Override
	public void update(SsVO vo) {
		ZpngTp zpng = zpBo.getRef(vo.getJbno());

		String chanold = zpng.getChan();
		// 旧的合格量
		Integer zpzlold = SinoUtils.fetchZpzl(zpng);
		// 中间包jbno
		String zjjbno = null;
		DhuoTp dhuo = null;
		for (SsItemVO item : vo.getItems()) {
			zjjbno = item.getJbno();
		}
		Date date = new Date();
		// 取中间制品
		ZpngTp zjzpng = zpBo.getZp(zjjbno);
		if (zjzpng != null) {
			// 取入端制品
			ZpngTp rcZp = zpBo.getZp(zjzpng.getRczpno());
			if (rcZp != null) {
				// 取指示书
				ZsdhTp zss = zsBo.getZsdhTp(rcZp.getZsno());
				if (zss != null) {
					// 获取订货信息
					dhuo = dhBo.getRef(SinoUtils.parseDhuoPk(zss.getDhno()));
				}

			}
		}
		// 参数检查
		validate(vo, dhuo, zpng.getBdan());
		zpng.setJbno(vo.getJbno());
		// 张数
		zpng.setZshu(vo.getZshu());

		// 实际重量
		zpng.setSjzl(vo.getSjzl());
		// 计算重量 JSZL_
		Integer jszl = 0;
		if (zpng.getBdan() != null) {
			jszl = NumberUtils.formatDouToInt((vo.getZshu() * zpng.getBdan()), 0, BigDecimal.ROUND_HALF_UP);
		}
		zpng.setJszl(jszl);
		// 净重量 JNZL_
		zpng.setJnzl(SinoUtils.fetchJnzl(zpng.getJhnr(), zpng.getSjzl(), zpng.getJszl(), vo.getChan()));
		// 毛重量 MAZL_
		zpng.setMazl(SinoUtils.fetchMazl(zpng.getJhnr(), zpng.getJnzl(), zpng.getBzcl(), zpng.getDmzl()));
		// 制品重量 ZPZL_
		zpng.setZpzl(SinoUtils.fetchZpzl(zpng));

		// 产量代码
		zpng.setChan(vo.getChan());
		// 等级
		zpng.setDeng(vo.getDeng());
		// 是否翻面
		zpng.setFmby(vo.getSffz());
		// 设定班组
		zpng.setBan(vo.getBan());
		zpng.setZu(vo.getZu());
		// 捆包形式 KBAO_
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			if (dhuo != null) {
				zpng.setKbao(dhuo.getKbao());
			}

		}
		else {
			Double kuan = 0.0;
			if (zpng.getKuan() != null) {
				kuan = zpng.getKuan();
			}
			Double cang = 0.0;
			if (zpng.getCang() != null) {
				cang = zpng.getCang();
			}
			if (kuan.doubleValue() > 850 || cang > 850) {
				zpng.setKbao("B31");
			}
			else {
				zpng.setKbao("B21");
			}
		}
		// 缺陷代码
		zpng.setQqdm(vo.getQqdm());
		zpng.setJdyn(vo.getJdyn());
		zpng.setJsyn(vo.getJsyn());
		// Pile生成实绩登录年月日
		// zpng.setPssd(new Date());
		// Pile生成实绩登录年月日
		zpng.setUpda(date);
		// ---------修改订货DB记录-------
		// 累计新的SL合格重量
		Integer zpzl = SinoUtils.fetchZpzl(zpng);
		if (zpzl == null || zpzl <= 0) {
			throw new CocoException(-1, "无法获取制品的制品重量");
		}
		// 合格的累计
		Double slhg = 0.0;
		if (dhuo != null) {
			if (dhuo.getSlhg() != null) {
				slhg = dhuo.getSlhg();
			}
			// 合格变为合格或不合格
			if (ChanType.hg.key.equals(chanold)
					|| ChanType.bl.key.equals(chanold)) {
				// 变为合格
				if (ChanType.hg.key.equals(vo.getChan())
						|| ChanType.bl.key.equals(vo.getChan())) {
					slhg = slhg - zpzlold * 0.001 + zpzl * 0.001;
					dhuo.setSlhg(NumberUtils.format(slhg, 3));
				}
				// 变为不合格
				if (!ChanType.hg.key.equals(vo.getChan())
						&& !ChanType.bl.key.equals(vo.getChan())) {
					slhg = slhg - zpzlold * 0.001;
					dhuo.setSlhg(NumberUtils.format(slhg, 3));
				}
			}
			// 不合格变为合格
			if (!ChanType.hg.key.equals(chanold)
					&& !ChanType.bl.key.equals(chanold)) {
				// 变为合格
				if (ChanType.hg.key.equals(vo.getChan())
						|| ChanType.bl.key.equals(vo.getChan())) {
					slhg = slhg + zpzl * 0.001;
					dhuo.setSlhg(NumberUtils.format(slhg, 3));
				}
			}
		}
		ychBO.doScYch(zpng, chanold, dhuo, date);
		// ---------写入实绩日志（指示情报共通格式、现品情报共通格式）---------
		Xpfmt xpfmt = sjBo.saveXpfmt(zpng, ZtConstants.ZPNG_CLQ_MODY);
		// SjVO sjVo = sjBo.saveForZp(zpng.getJbno());

		// ---------写入SS生产实绩日志---------
		PlscLp entity = parsePlscLp(zpng, xpfmt, date);
		dao.save(entity);
		// ---------删除原合并包记录，写入合并包记录，修改原制品张数---------
		Hbbjl hbb = null;
		int index = 1;

		List<Hbbjl> sstiemold = dao.findHbbjl(vo.getJbno());
		// 更新界面删除的中间包张数
		updateNoZpng(sstiemold, vo.getItems());
		// 删除旧的中间包
		dao.deletebyxbh(vo.getJbno());
		// 保存界面的中间包
		for (SsItemVO item : vo.getItems()) {
			// 新增合并包记录
			// zshuold = 0;
			hbb = new Hbbjl();
			hbb.setPlqf(entity.getId());
			hbb.setXbh(zpng.getJbno());
			hbb.setZjbh(item.getJbno());
			hbb.setXh(index++);
			hbb.setZs(item.getZshu());
			hbb.setPlqf(item.getPlqf());
			// 修改原制品张数为原张数减去合并掉张数
			// zshuold = getZshu(sstiemold, hbb.getZjbh());
			// orig = zpBo.getRef(item.getJbno());
			// int zshu = orig.getZshu() + zshuold - item.getZshu();
			// orig.setZshu(zshu > 0 ? zshu : 0);
			dao.save(hbb);
			// ----------写中间包日志--------------
			PlscItemLp plitem = new PlscItemLp();
			plitem.setJbn(item.getJbno());
			plitem.setPlq(item.getPlqf());
			plitem.setZsu(item.getZshu());
			plitem.setCrea(date);
			plitem.setUpda(date);
			plitem.setPlscLp(entity);
			dao.savePlscItem(plitem);
		}

	}

	// 更新界面删除的中间品张数
	private void updateNoZpng(List<Hbbjl> sstiemold, List<SsItemVO> items) {
		Boolean isExit = true;
		ZpngTp orig = null;
		int zshu = 0;
		for (Hbbjl hb : sstiemold) {

			for (SsItemVO item : items) {
				if (hb.getZjbh().equals(item.getJbno())) {
					isExit = false;
				}
			}
			if (isExit) {
				orig = zpBo.getRef(hb.getZjbh());
				zshu = orig.getZshu() + hb.getZs();
				orig.setZshu(zshu > 0 ? zshu : 0);
			}
			isExit = true;
			zshu = 0;
		}
	}

	// // 取旧的张数
	// private Integer getZshu(List<Hbbjl> sstiemold, String jbno) {
	// Integer zshu = 0;
	// for (Hbbjl hb : sstiemold) {
	// if (hb.getZjbh().equals(jbno)) {
	// zshu = hb.getZs();
	// }
	//
	// }
	// return zshu;
	// }

	/**
	 * <p>
	 * 检验参数
	 * </p>
	 * 
	 * @param vo
	 */
	private void validate(SsVO vo, DhuoTp dhuo, Double bdan) {
		Boolean banbl = banBO.checkBan(vo.getBan());
		if (!banbl) {
			throw new CocoException(-23, "现在的时间不是这个班！");
		}
		if (vo.getSjzl() == null) {
			throw new CocoException(-1, "实际重量不能为空");
		}
		// 产量代码是否有效
		if (ChanType.get(vo.getChan()) == null) {
			throw new CocoException(-1, "无效的产量代码：" + vo.getChan());
		}
		// 等级代码检查
		// 产量代码4
		if (ChanType.fs.key.equals(vo.getChan())) {
			if (vo.getDeng() == null) {
				throw new CocoException(-1, "等级不能为空");
			}
			if (vo.getDeng().length() != 2
					|| !Code104.D.key.equals(vo.getDeng().substring(0, 1))
					|| !Code104.D.key.equals(vo.getDeng().substring(1, 2))) {
				throw new CocoException(-1, "等级不是DD");
			}

		}
		else {
			if (vo.getQqdm() == null) {
				throw new CocoException(-1, "缺陷不能为空");
			}
			if (vo.getDeng() == null
					|| vo.getDeng().length() != 3
					|| !codeBo.isExisted(CmnConstants.CODE_103, vo.getDeng().substring(0, 1))
					|| !codeBo.isExisted(CmnConstants.CODE_104, vo.getDeng().substring(1, 2))
					|| !codeBo.isExisted(CmnConstants.CODE_105, vo.getDeng().substring(2, 3))) {
				throw new CocoException(-1, "无效的等级:" + vo.getDeng());
			}
		}
		if (ChanType.hg.key.equals(vo.getChan())) {
			// 实际重量检查
			// 计算重量 JSZL_
			Integer jszl = 0;
			if (bdan != null) {
				jszl = NumberUtils.formatDouToInt((vo.getZshu() * bdan), 0, BigDecimal.ROUND_HALF_UP);
			}
			Integer zl = vo.getSjzl() - jszl;
			Double js = 0.0;
			if (jszl != null && jszl != 0) {
				js = zl * 1.0 / jszl;
			}
			if (js > 0.05 || js < -0.05) {
				throw new CocoException(-4, "实际重量和计算重量相差超出5%");
			}
			// 枚数检查
			Integer zshu = vo.getZshu() != null ? vo.getZshu() : 0;
			if (dhuo != null) {
				// throw new CocoException(-1, "订货DB为空,不能指定包装张数！");
				// 包装张数
				Integer kbsz = dhuo.getKbsz() != null ? Integer.parseInt(dhuo.getKbsz())
						: 0;
				// 枚数大于包装数
				if (zshu.intValue() > kbsz.doubleValue()) {
					throw new CocoException(-1, "张数大于包装张数" + kbsz + "");
				}
				// 零头下限
				Integer ltzi = dhuo.getLtzi() != null ? dhuo.getLtzi() : 0;
				// 判断是否符合包装张数和零头下限
				// 包装张数.值的校验
				List<CodeItem> codeItems = codeBo.findItems(CmnConstants.SINO_KBSZ);
				if (codeItems != null && codeItems.size() > 0) {
					String v;
					int bcs;
					boolean isExisted = false;
					for (CodeItem codeItem : codeItems) {
						if ((v = codeItem.getValue()) == null || v.isEmpty()) continue;
						try {
							bcs = Integer.parseInt(v);
						}
						catch (NumberFormatException e) {
							continue;
						}
						if (zshu % bcs == 0) {
							isExisted = true;
							break;
						}
					}
					if (!isExisted) {
						throw new CocoException(-1, "张数不是50、100或112的倍数！");
					}
				}
				// if (zshu % DhConstants.ZS_50 != 0
				// && zshu % DhConstants.ZS_100 != 0
				// && zshu % DhConstants.ZS_112 != 0) {
				// throw new CocoException(-1, "张数不是50、100或112的倍数！");
				// }
				if (zshu < ltzi) {
					throw new CocoException(-1, "张数小于零头下限！");
				}
				// if (kbsz % 112 == 0) {
				// if (zshu % 112 != 0 && zshu % 100 != 0) {
				// throw new CocoException(-1, "张数不是112或100的倍数！");
				// }
				// if (zshu < ltzi) {
				// throw new CocoException(-1, "张数小于零头下限！");
				// }
				// }
				// else {
				// if (zshu % 100 != 0) {
				// throw new CocoException(-1, "张数不是100的倍数！");
				// }
				// if (zshu < ltzi) {
				// throw new CocoException(-1, "张数小于零头下限！");
				// }
				// }
			}
		}

	}

	@Override
	public SsVO dzCheck(String jbno) {
		// 检查
		delUpdaValidate(jbno);
		SsVO vo = new SsVO();
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "不存在制品信息");
		}
		// if (EFpyc.YC.key.equals(zpng.getFpyc())) {
		// throw new CocoException(-1, "该制品为余材品，不能订正");
		// }
		vo.setJbno(zpng.getJbno());
		vo.setDhno(zpng.getDhno());
		// 要求厚度
		vo.setHouu(zpng.getHouu());
		// 要求宽度
		vo.setKuan(zpng.getKuan());
		// 要求长度
		vo.setCang(zpng.getCang());
		// 张数
		vo.setZshu(zpng.getZshu());
		// 实际重量
		vo.setSjzl(zpng.getSjzl());
		// 计算重量
		vo.setJszz(zpng.getJszl());

		// 产量代码
		vo.setChan(zpng.getChan());
		// 等级
		vo.setDeng(zpng.getDeng());
		// 是否翻面
		vo.setSffz(zpng.getFmby());
		// 设定班组
		vo.setBan(zpng.getBan());
		vo.setZu(zpng.getZu());

		// 缺陷代码
		vo.setQqdm(zpng.getQqdm());
		vo.setJdyn(zpng.getJdyn());
		vo.setJsyn(zpng.getJsyn());
		// 制品尺寸
		// vo.setZpcc(SinoUtils.formatProductSize(zpng.getHouu(),
		// zpng.getKuan(), zpng.getCang()));
		List<Hbbjl> hbs = dao.findHbbjl(jbno);
		SsItemVO ssItem = null;
		ZpngTp zpnginfo = null;
		for (Hbbjl hb : hbs) {
			ssItem = new SsItemVO();
			ssItem.setJbno(hb.getZjbh());
			ssItem.setZshu(hb.getZs());
			zpnginfo = zpBo.getZp(hb.getZjbh());
			if (zpnginfo != null) {
				ssItem.setDhno(zpnginfo.getDhno());
				ssItem.setUser(zpnginfo.getUser());
				ssItem.setFuzm(zpnginfo.getFuzm());
				ssItem.setFufm(zpnginfo.getFufm());
				ssItem.setGgno(zpnginfo.getGgno());
				ssItem.setYuny(zpnginfo.getYuny());
				ssItem.setAbbr(zpnginfo.getAbbr());
				ssItem.setDeng(zpnginfo.getDeng());
				ssItem.setZpcc(SinoUtils.formatProductSize(zpnginfo.getHouu(), zpnginfo.getKuan(), zpnginfo.getCang()));

				ssItem.setQqdm(zpnginfo.getQqdm());
				ssItem.setQqd2(zpnginfo.getQqd2());
				ssItem.setQqd3(zpnginfo.getQqd3());
				ssItem.setHouu(zpnginfo.getHouu());
				ssItem.setKuan(zpnginfo.getKuan());
				ssItem.setCang(zpnginfo.getCang());
				ssItem.setSjzl(zpnginfo.getSjzl());
				ssItem.setPlqf(zpnginfo.getPlqf());

				// 附业kpns
				if (zpng.getKpn1() != null) {
					ssItem.getKpns().add(zpng.getKpn1());
				}
				if (zpng.getKpn2() != null) {
					ssItem.getKpns().add(zpng.getKpn2());
				}
				if (zpng.getKpn3() != null) {
					ssItem.getKpns().add(zpng.getKpn3());
				}
				if (zpng.getKpn4() != null) {
					ssItem.getKpns().add(zpng.getKpn4());
				}
				String ylno = zpng.getYlno();
				if (ylno != null && !ylno.isEmpty()) {
					String type;
					for (String yln : ylno.split(",")) {
						type = yln.substring(0, 1);
						if (EKpType.SL.key.equals(type)
								|| EKpType.BOTH.key.equals(type)) {
							ssItem.getYlns().add(yln.substring(2));
						}
					}
				}
			}
			vo.getItems().add(ssItem);
		}
		return vo;
	}

	@Override
	public SsXmVO fetchXmVO(String jbno) {
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "无法获取制品信息");
		}
		if (!(ZtConstants.ZPNG_SC_YSJ.equals(zpng.getScbj()) || ZtConstants.ZPNG_SC_CSZ.equals(zpng.getScbj()))) {
			throw new CocoException(-1, "该制品处于的删除标志状态不允许消灭，标志："
					+ zpng.getScbj());
		}
		SsXmVO vo = new SsXmVO();
		vo.setJbno(jbno);
		vo.setHouu(zpng.getHouu());
		vo.setKuan(zpng.getKuan());
		vo.setCang(zpng.getCang());
		vo.setDeng(zpng.getDeng());
		vo.setZshu(zpng.getZshu());
		return vo;
	}

	@Override
	public void destroyvalidate(ssXmItemVO[] vos) {
		for (ssXmItemVO vo : vos) {
			if (vo.getJbno() != null && !vo.getJbno().isEmpty()) {
				// 检查缺陷代码
				checkQx(vo);
			}
		}
	}

	@Override
	public void destroy(ssXmItemVO[] vos) {
		Date date = new Date();
		ZpngTp rcZp = null;
		for (ssXmItemVO vo : vos) {
			if (vo.getJbno() != null && !vo.getJbno().isEmpty()) {
				// 检查缺陷代码
				checkQx(vo);
				ZpngTp zpng = zpBo.getZp(vo.getJbno());
				if (zpng == null) {
					throw new CocoException(-1, "无法获取" + vo.getJbno() + "制品信息");
				}
				if (!(ZtConstants.ZPNG_SC_YSJ.equals(zpng.getScbj()) || ZtConstants.ZPNG_SC_CSZ.equals(zpng.getScbj()))) {
					throw new CocoException(-1, "制品" + vo.getJbno()
							+ "已经消灭，消灭状态为：" + zpng.getScbj());
				}
				if (!DC.E.name.equals(zpng.getDuic())) {
					throw new CocoException(-1, "制品" + vo.getJbno()
							+ "不是E堆场，不能消灭");
				}
				// -----------更新制品信息--------------
				// 消灭日期
				zpng.setPxsd(date);
				// 缺陷等级
				zpng.setQxd1(vo.getDeng1());
				zpng.setQxd2(vo.getDeng2());
				zpng.setQxd3(vo.getDeng3());
				zpng.setQxd4(vo.getDeng4());
				zpng.setQxd5(vo.getDeng5());
				zpng.setQxd6(vo.getDeng6());
				zpng.setQxd7(vo.getDeng7());
				zpng.setQxd8(vo.getDeng8());
				// 缺陷代码
				zpng.setQxn1(vo.getQxdm1());
				zpng.setQxn2(vo.getQxdm2());
				zpng.setQxn3(vo.getQxdm3());
				zpng.setQxn4(vo.getQxdm4());
				zpng.setQxn5(vo.getQxdm5());
				zpng.setQxn6(vo.getQxdm6());
				zpng.setQxn7(vo.getQxdm7());
				zpng.setQxn8(vo.getQxdm8());
				// 缺陷枚数
				zpng.setQxz1(vo.getZshu1());
				zpng.setQxz2(vo.getZshu2());
				zpng.setQxz3(vo.getZshu3());
				zpng.setQxz4(vo.getZshu4());
				zpng.setQxz5(vo.getZshu5());
				zpng.setQxz6(vo.getZshu6());
				zpng.setQxz7(vo.getZshu7());
				zpng.setQxz8(vo.getZshu8());

				// 置删除标志
				zpng.setScbj(ZtConstants.ZPNG_SC_YXM);

				zpBo.update(zpng);
				// 取中间制品

				// 取入端制品
				rcZp = zpBo.getZp(zpng.getRczpno());
				if (rcZp != null) {
					// 取指示书
					ZsdhTp zss = zsBo.getZsdhTp(rcZp.getZsno());
					if (zss != null) {
						// 更新垫木库存
						dmBO.updateDmkz(zss.getKuan(), zss.getCang(), zss.getDmfx(), zss.getKbao(), ZtConstants.MG_KW, "分选", -1);
					}

				}

				// ---------写入实绩日志（指示情报共通格式、现品情报共通格式）---------
				// SjVO sjVo = sjBo.saveForZp(zpng.getJbno());
				Xpfmt xpfmt = sjBo.saveXpfmt(zpng, ZtConstants.ZPNG_CLQ_DEL);

				// -----------写入Pile（分选打包）消灭实绩日志--------------
				PlxmLp plxm = new PlxmLp();
				// plxm.setZsfmt(null);
				plxm.setXpfmt(xpfmt);
				// 做成时间
				plxm.setCrea(date);
				// 缺陷等级
				plxm.setQxd1(zpng.getQxd1());
				plxm.setQxd2(zpng.getQxd2());
				plxm.setQxd3(zpng.getQxd3());
				plxm.setQxd4(zpng.getQxd4());
				plxm.setQxd5(zpng.getQxd5());
				plxm.setQxd6(zpng.getQxd6());
				plxm.setQxd7(zpng.getQxd7());
				plxm.setQxd8(zpng.getQxd8());
				// 缺陷代码
				plxm.setQxn1(zpng.getQxn1());
				plxm.setQxn2(zpng.getQxn2());
				plxm.setQxn3(zpng.getQxn3());
				plxm.setQxn4(zpng.getQxn4());
				plxm.setQxn5(zpng.getQxn5());
				plxm.setQxn6(zpng.getQxn6());
				plxm.setQxn7(zpng.getQxn7());
				plxm.setQxn8(zpng.getQxn8());
				// 缺陷枚数
				plxm.setQxz1(zpng.getQxz1());
				plxm.setQxz2(zpng.getQxz2());
				plxm.setQxz3(zpng.getQxz3());
				plxm.setQxz4(zpng.getQxz4());
				plxm.setQxz5(zpng.getQxz5());
				plxm.setQxz6(zpng.getQxz6());
				plxm.setQxz7(zpng.getQxz7());
				plxm.setQxz8(zpng.getQxz8());
				// 用户略称
				plxm.setAbbr(zpng.getAbbr());

				dao.save(plxm);
			}
		}

	}

	/**
	 * <p>
	 * 检查缺陷
	 * </p>
	 */
	private void checkQx(ssXmItemVO vo) {
		CodeItem code = null;
		if (vo.getQxdm1() != null && !vo.getQxdm1().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm1());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm1() + "在码表不存在！");
			}
		}
		if (vo.getQxdm2() != null && !vo.getQxdm2().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm2());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm2() + "在码表不存在！");
			}
		}
		if (vo.getQxdm3() != null && !vo.getQxdm3().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm3());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm3() + "在码表不存在！");
			}
		}
		if (vo.getQxdm4() != null && !vo.getQxdm4().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm4());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm4() + "在码表不存在！");
			}
		}
		if (vo.getQxdm5() != null && !vo.getQxdm5().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm5());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm5() + "在码表不存在！");
			}
		}
		if (vo.getQxdm6() != null && !vo.getQxdm6().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm6());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm6() + "在码表不存在！");
			}
		}
		if (vo.getQxdm7() != null && !vo.getQxdm7().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm7());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm7() + "在码表不存在！");
			}
		}
		if (vo.getQxdm8() != null && !vo.getQxdm8().isEmpty()) {
			code = codeBo.getCodeItem(CmnConstants.CODE_QX, vo.getQxdm8());
			if (code == null) {
				throw new CocoException(-9, "制品号：" + vo.getJbno() + "缺陷"
						+ vo.getQxdm8() + "在码表不存在！");
			}
		}
	}

	@Override
	public void fetchRz(SsRzVO vo, OutputStream os) {
		// 根据条件(检查日期，班，组)读取合并包记录
		PlscLpQE qentity = new PlscLpQE();
		qentity.setSize(0);
		// 进度标记.精整实绩为选别过的
		// qentity.setJdjs(ZtConstants.ZPNG_JD_JDJS);
		// 必须是F堆场的
		// qentity.setDuic(DC.F.name);

		// if (vo.getCrea_begin() != null) {
		// Date pssd_begin = DateUtils.formatDate(vo.getCrea_begin(), 8, 0);
		// qentity.setCreaBegin(pssd_begin);
		// if (vo.getCrea_end() == null) {
		// qentity.setCreaEnd(DateUtils.add(pssd_begin, Calendar.DAY_OF_MONTH,
		// 1));
		// }
		// else {
		// Date pssd_end = DateUtils.formatDate(vo.getCrea_end(), 8, 0);
		// qentity.setCreaEnd(DateUtils.add(pssd_end, Calendar.DAY_OF_MONTH,
		// 1));
		// }
		//
		// }
		// else {
		// Date pssd_begin = DateUtils.parse("1900-01-01", "yyyy-MM-dd");
		// vo.setCrea_begin(pssd_begin);
		// qentity.setCreaBegin(pssd_begin);
		// if (vo.getCrea_end() == null) {
		// Date pssd_end = new Date();
		// qentity.setCreaEnd(DateUtils.add(pssd_end, Calendar.DAY_OF_MONTH,
		// 1));
		// vo.setCrea_end(pssd_end);
		// }
		// else {
		// Date pssd_end = DateUtils.formatDate(vo.getCrea_end(), 8, 0);
		// qentity.setCreaEnd(DateUtils.add(pssd_end, Calendar.DAY_OF_MONTH,
		// 1));
		// vo.setCrea_end(pssd_end);
		// }
		//
		// }
		String pileNo = vo.getPileNo();
		boolean isExisted = true;
		if (pileNo != null && !pileNo.isEmpty()) {
			List<String> plscs = dao.findPlsc(pileNo);
			if (plscs == null || plscs.size() == 0) {
				isExisted = false;
			}
			qentity.setIds(plscs);
		}
		Date creaBegin = vo.getCrea_begin();
		Date creaEnd = vo.getCrea_end();
		qentity.setCreaBegin(creaBegin);
		if (creaEnd == null) {
			qentity.setCreaEnd(creaBegin);
		}
		qentity.setBan(vo.getBan());
		qentity.setZu(vo.getZu());
		qentity.setJbno(vo.getJbno());
		qentity.setOrderBys(" a.crea asc");
		if (isExisted) {
			dao.queryRZ(qentity);
		}
		// 填充数据
		List<SsVO> ssVos = new ArrayList<SsVO>();

		SsVO ssVo;
		SsItemVO itemVo;
		List<PlscItemLp> plscItemLps;
		for (SsxpVO ssxp : qentity.getDatas()) {
			ssVo = new SsVO();
			ReflectUtils.copy(ssVo, ssxp, true);
			ssVo.setJdcn(ssxp.getCang());
			ssVo.setFuzm(cmnBo.parseFzl2Gm(ssxp.getFudw(), ssxp.getFuzm()).toString());
			ssVo.setFufm(cmnBo.parseFzl2Gm(ssxp.getFudw(), ssxp.getFufm()).toString());
			// 附业kpns
			if (ssxp.getKpn1() != null) {
				ssVo.getKpns().add(ssxp.getKpn1Flag() + " " + ssxp.getKpn1());
			}
			if (ssxp.getKpn2() != null) {
				ssVo.getKpns().add(ssxp.getKpn2Flag() + " " + ssxp.getKpn2());
			}
			if (ssxp.getKpn3() != null) {
				ssVo.getKpns().add(ssxp.getKpn3Flag() + " " + ssxp.getKpn3());
			}
			if (ssxp.getKpn4() != null) {
				ssVo.getKpns().add(ssxp.getKpn4Flag() + " " + ssxp.getKpn4());
			}
			String ylno = ssxp.getYlno();
			if (ylno != null && !ylno.isEmpty()) {
				String type;
				for (String yln : ylno.split(",")) {
					type = yln.substring(0, 1);
					if (EKpType.SL.key.equals(type)
							|| EKpType.BOTH.key.equals(type)) {
						ssVo.getYlns().add(yln);
					}
				}
			}
			plscItemLps = dao.findPlscItemLp(ssxp.getId());
			if (plscItemLps != null) {
				for (PlscItemLp plitem : plscItemLps) {
					itemVo = new SsItemVO();
					itemVo.setJbno(plitem.getJbn());
					itemVo.setZshu(plitem.getZsu());
					itemVo.setPlqf(plitem.getPlq());
					ssVo.getItems().add(itemVo);
				}
			}
			ssVos.add(ssVo);
		}
		vo.setDatas(ssVos);
		// 填充Excel数据
		ExcelUtils.fillData(rizhiPath, vo, rizhiExec, os);
	}

	@Override
	public void fetchBltj(SsBlVO vo, OutputStream os) {
		// 查询已消灭的包，本来想从消灭实绩日志获取，但是消灭实绩日志数据信息不够（缺少单重等），故从制品在库表查询
		// 重置查询条件

		// 消灭时间起始、终止条件
		// 本人觉得限制只统计一个月为好，这里限制起止篇幅为60天
		if (vo.getPxsdBegin() != null && vo.getPxsdEnd() != null) {
			int dayOffset = DateUtils.getDayOffset(vo.getPxsdBegin(), vo.getPxsdEnd());
			if (dayOffset < 0) {
				// 若终止时间早于开始时间，则查开始当日
				vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH, 1));
			}
			else if (dayOffset > 60) {
				// 若终止时间早于开始时间60天以上，则只查60天
				vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH, 60));
			}
			else {
				vo.setPxsdEnd(DateUtils.add(vo.getPxsdEnd(), Calendar.DAY_OF_MONTH, 1));
			}
		}
		else if (vo.getPxsdBegin() != null) {
			// 若没有指定终止时间，则查指定开始时间当日
			vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH, 1));
		}
		else if (vo.getPxsdEnd() != null) {
			// 若没有指定开始时间，则查当月月初至终止时间
			vo.setPxsdBegin(DateUtils.getFirstDayOfMonth(vo.getPxsdBegin()));
			vo.setPxsdEnd(DateUtils.add(vo.getPxsdEnd(), Calendar.DAY_OF_MONTH, 1));
		}
		else {
			// 若没有指定开始时间和终止时间，则默认查当天
			vo.setPxsdBegin(DateUtils.getCurrentDay());
			vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH, 1));
		}
		vo.setCount(true);
		ZpQE qentity = new ZpQE();
		qentity.setPxsdBegin(vo.getPxsdBegin());
		qentity.setPxsdEnd(vo.getPxsdEnd());
		qentity.setScbj(ZtConstants.ZPNG_SC_YXM);
		qentity.setSize(-1);
		zpBo.query(qentity);

		for (ZpngTp zpng : qentity.getDatas()) {
			vo.putCount(zpng.getPxsd(), zpng.getBdan(), new SsBlVO.Quex(
					zpng.getQxn1(), zpng.getQxz1()), new SsBlVO.Quex(
					zpng.getQxn2(), zpng.getQxz2()), new SsBlVO.Quex(
					zpng.getQxn3(), zpng.getQxz3()), new SsBlVO.Quex(
					zpng.getQxn4(), zpng.getQxz4()), new SsBlVO.Quex(
					zpng.getQxn5(), zpng.getQxz5()), new SsBlVO.Quex(
					zpng.getQxn6(), zpng.getQxz6()), new SsBlVO.Quex(
					zpng.getQxn7(), zpng.getQxz7()), new SsBlVO.Quex(
					zpng.getQxn8(), zpng.getQxz8()));
		}

		// 填充Excel数据
		ExcelUtils.fillData(bltjPath, vo, bltjExec, os);

	}

	@Override
	public void fetchBlmx(SsBlVO vo, OutputStream os) {
		// 查询已消灭的包，本来想从消灭实绩日志获取，但是消灭实绩日志数据信息不够（缺少单重等），故从制品在库表查询
		// 重置查询条件
		// 消灭时间起始、终止条件
		// 只能按天来查，即只指定开始时间，若没有指定开始时间，则默认查当日。
		// if (vo.getPxsdBegin() != null) {
		// vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH,
		// 1));
		// }
		// else {
		// // 若没有指定开始时间，则默认查当天
		// vo.setPxsdBegin(DateUtils.getCurrentDay());
		// vo.setPxsdEnd(DateUtils.add(vo.getPxsdBegin(), Calendar.DAY_OF_MONTH,
		// 1));
		// }
		if (vo.getPxsdBegin() == null) {
			vo.setPxsdBegin(DateUtils.getCurrentDay());
		}
		if (vo.getPxsdEnd() == null) {
			vo.setPxsdEnd(DateUtils.getCurrentDay());
		}

		vo.setCount(false);

		ZpQE qentity = new ZpQE();
		qentity.setPxsdBegin(vo.getPxsdBegin());
		qentity.setPxsdEnd(vo.getPxsdEnd());
		qentity.setScbj(ZtConstants.ZPNG_SC_YXM);
		qentity.setSize(-1);
		zpBo.query(qentity);

		for (ZpngTp zpng : qentity.getDatas()) {
			vo.putDetail(zpng.getJbno(), zpng.getPxsd(), zpng.getBdan(), new SsBlVO.Quex(
					zpng.getQxn1(), zpng.getQxz1()), new SsBlVO.Quex(
					zpng.getQxn2(), zpng.getQxz2()), new SsBlVO.Quex(
					zpng.getQxn3(), zpng.getQxz3()), new SsBlVO.Quex(
					zpng.getQxn4(), zpng.getQxz4()), new SsBlVO.Quex(
					zpng.getQxn5(), zpng.getQxz5()), new SsBlVO.Quex(
					zpng.getQxn6(), zpng.getQxz6()), new SsBlVO.Quex(
					zpng.getQxn7(), zpng.getQxz7()), new SsBlVO.Quex(
					zpng.getQxn8(), zpng.getQxz8()));
		}

		ExcelUtils.fillData(blmxPath, vo, blmxExec, os);
	}

	/**
	 * @return the zpBo
	 */
	public IZpBO getZpBo() {
		return zpBo;
	}

	/**
	 * @param zpBo
	 *            the zpBo to set
	 */
	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	/**
	 * @return the cmnBo
	 */
	public ICmnBO getCmnBo() {
		return cmnBo;
	}

	/**
	 * @param cmnBo
	 *            the cmnBo to set
	 */
	public void setCmnBo(ICmnBO cmnBo) {
		this.cmnBo = cmnBo;
	}

	/**
	 * @return the sjBo
	 */
	public ISjBO getSjBo() {
		return sjBo;
	}

	/**
	 * @param sjBo
	 *            the sjBo to set
	 */
	public void setSjBo(ISjBO sjBo) {
		this.sjBo = sjBo;
	}

	/**
	 * @return the dao
	 */
	public ISsDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(ISsDAO dao) {
		this.dao = dao;
	}

	/**
	 * @return the dhBo
	 */
	public IDhBO getDhBo() {
		return dhBo;
	}

	/**
	 * @param dhBo
	 *            the dhBo to set
	 */
	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	/**
	 * @return the zsBo
	 */
	public IZsBO getZsBo() {
		return zsBo;
	}

	/**
	 * @param zsBo
	 *            the zsBo to set
	 */
	public void setZsBo(IZsBO zsBo) {
		this.zsBo = zsBo;
	}

	/**
	 * @return the scxbBo
	 */
	public IScxbBO getScxbBo() {
		return scxbBo;
	}

	/**
	 * @param scxbBo
	 *            the scxbBo to set
	 */
	public void setScxbBo(IScxbBO scxbBo) {
		this.scxbBo = scxbBo;
	}

	/**
	 * @return the codeBo
	 */
	public ICodeBO getCodeBo() {
		return codeBo;
	}

	/**
	 * @param codeBo
	 *            the codeBo to set
	 */
	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public String getRizhiPath() {
		return rizhiPath;
	}

	public void setRizhiPath(String rizhiPath) {
		this.rizhiPath = rizhiPath;
	}

	public ExcelbookDataExecuter<SsRzVO> getRizhiExec() {
		return rizhiExec;
	}

	public void setRizhiExec(ExcelbookDataExecuter<SsRzVO> rizhiExec) {
		this.rizhiExec = rizhiExec;
	}

	public String getBltjPath() {
		return bltjPath;
	}

	public void setBltjPath(String bltjPath) {
		this.bltjPath = bltjPath;
	}

	public ExcelDataExecuter<SsBlVO> getBltjExec() {
		return bltjExec;
	}

	public void setBltjExec(ExcelDataExecuter<SsBlVO> bltjExec) {
		this.bltjExec = bltjExec;
	}

	public String getBlmxPath() {
		return blmxPath;
	}

	public void setBlmxPath(String blmxPath) {
		this.blmxPath = blmxPath;
	}

	public ExcelDataExecuter<SsBlVO> getBlmxExec() {
		return blmxExec;
	}

	public void setBlmxExec(ExcelDataExecuter<SsBlVO> blmxExec) {
		this.blmxExec = blmxExec;
	}

	public IYchBO getYchBO() {
		return ychBO;
	}

	public void setYchBO(IYchBO ychBO) {
		this.ychBO = ychBO;
	}

	public IBanBO getBanBO() {
		return banBO;
	}

	public void setBanBO(IBanBO banBO) {
		this.banBO = banBO;
	}

	public IMgBO getDmBO() {
		return dmBO;
	}

	public void setDmBO(IMgBO dmBO) {
		this.dmBO = dmBO;
	}

}
