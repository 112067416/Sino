/**
 * 
 */
package com.quanta.sino.sl.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Config;
import com.coco.core.env.Helper;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.IBanBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeChdxZl;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.Czlx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.ECzdm;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.ERzlx;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.Sfpp;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.bo.api.IMgBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.RiziLpPk;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.SlsjLp;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZkfpCzjl;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZscdTpPk;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.Zsfmt;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.orm.ZszrTpPk;
import com.quanta.sino.sj.bo.api.ISjBO;
import com.quanta.sino.sj.vo.SjVO;
import com.quanta.sino.sl.bo.api.ISlBO;
import com.quanta.sino.sl.constants.EPiler;
import com.quanta.sino.sl.constants.ESlRzzt;
import com.quanta.sino.sl.constants.SlConstants;
import com.quanta.sino.sl.dao.api.ISlDAO;
import com.quanta.sino.sl.vo.SLSjSaveVO;
import com.quanta.sino.sl.vo.SjzkVO;
import com.quanta.sino.sl.vo.SjzsVO;
import com.quanta.sino.sl.vo.SllrVO;
import com.quanta.sino.sl.vo.SlsjVO;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.yd.bo.api.IYdBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * SL实绩业务实现
 * </p>
 * <p>
 * create: 2011-1-15 下午12:30:46
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SlBO implements ISlBO {

	private ISlDAO dao;

	private IScxbBO scxbBo;

	private IZpBO zpBo;

	private IZsBO zsBo;

	private IDhBO dhBo;

	private ICmnBO cmnBo;

	private IEtlBO etlBo;

	private ICodeBO codeBo;

	private ICzjlBO czjlBO;

	private IYchBO ychBO;

	private IYdBO ydBO;
	/**
	 * 班别接口
	 */
	private IBanBO banBO;
	/**
	 * 执木库存接口
	 */
	private IMgBO dmBO;

	@Override
	public SllrVO lead(User user) {
		// 担当者登录检查
		if (user == null) {
			throw new CocoException(-1, "异常：担当者登录超时");
		}
		// 生产线担当者检查
		Scxbpz scx = scxbBo.getByDept(user.getDeptId());
		if (scx == null || !ProductType.sl.code.equalsIgnoreCase(scx.getType())) {
			throw new CocoException(-2, "异常：当前担当者不能操作此项功能");
		}
		// VO容器
		SllrVO vo = new SllrVO();
		vo.setSlin(scx.getCode());
		// 系统查找正在实绩中的卷
		ZpngTp currZp = dao.getCurr(scx.getCode());
		if (currZp != null) {
			vo.setRczpno(currZp.getJbno());
			vo.setZsno(currZp.getZsno());
		}
		return vo;
	}

	@Override
	public Integer getJszl(String zsno, Integer zshu) {
		// 判断从界面获取的指示书必须存在
		ZsdhTp zsdh = zsBo.getZsdhTp(zsno);
		if (zsdh == null) {
			return 0;
		}
		Double bdan = 0.0;
		Integer jszl = 0;
		if (zsdh.getBdan() != null) {
			bdan = zsdh.getBdan();
		}
		// 计算重量 = 张数 * 单张重
		jszl = NumberUtils.formatDouToInt(zshu * bdan, 0, BigDecimal.ROUND_HALF_UP);
		return jszl;

	}

	/**
	 * <p>
	 * 检验担当者输入[生产线、指示书号、入端卷号]
	 * </p>
	 * 
	 * @param vo
	 */
	@Override
	public SLSjSaveVO getcheck(SllrVO vo, User user) {

		// 判断从界面获取的指示书必须存在
		ZsdhTp zsdh = zsBo.getZsdhTp(vo.getZsno());
		if (zsdh == null) {
			throw new CocoException(-5, "异常：当前指示书不存在");
		}

		// 从界面获取的卷号必须存在
		ZpngTp lrZp = zpBo.getZp(vo.getRczpno());
		if (lrZp == null) {
			throw new CocoException(-8, "异常：入端卷不存在");
		}

		// 新生成的出端制品对象
		SLSjSaveVO cdZp = new SLSjSaveVO();
		// 生成新的出端制品号
		String[] cdnos = cmnBo.generateNo(vo.getRczpno(), null);
		String cdno;
		if (cdnos == null || cdnos.length == 0 || (cdno = cdnos[0]) == null
				|| cdno.trim().isEmpty()) {
			throw new CocoException(-1, "异常：无法生成制品号");
		}
		cdZp.setJbno(cdno);
		// 设入端卷号
		cdZp.setRczpno(lrZp.getJbno());
		// 设入生产线
		cdZp.setSlin(vo.getSlin());
		// 设入指示书号
		cdZp.setZsno(zsdh.getZsno());
		// 取制造商代码
		cdZp.setZzno(lrZp.getZzsd());
		// 设入制品尺寸·厚
		cdZp.setHouu(zsdh.getHouu());
		// 设入制品尺寸·宽
		cdZp.setKuan(zsdh.getKuan());
		// 设入制品尺寸·长
		cdZp.setCang(zsdh.getCang());
		// 设入薄板单重
		cdZp.setBdan(zsdh.getBdan());
		// 设入班
		cdZp.setBan(user.getShift());
		// 设入组
		cdZp.setZu(user.getGroup());
		// 实绩附着量正
		cdZp.setSczm(lrZp.getSczm());
		// 实绩附着量正反
		cdZp.setScfm(lrZp.getScfm());
		// 取制造商代码
		cdZp.setZzsd(lrZp.getZzsd());
		return cdZp;
	}

	/**
	 * <p>
	 * 检验担当者输入[生产线、指示书号、入端卷号]
	 * </p>
	 * 
	 * @param vo
	 */
	@Override
	public SLSjSaveVO check(SllrVO vo, User user) {
		if (user == null) {
			throw new CocoException(-1, "异常：当前担当者登陆超时");
		}
		// 界面录入的对象获取为空[对象包括的数据有：生产线别；指示书号；入端卷号]
		if (vo == null) {
			throw new CocoException(-1, "异常：获取值对象为空");
		}
		// 从界面获取的生产线别不能为空
		if (vo.getSlin() == null || vo.getSlin().trim().isEmpty()) {
			throw new CocoException(-2, "异常：生产线别不能为空");
		}
		// 从界面获取的指示书号不能为空
		if (vo.getZsno() == null || vo.getZsno().trim().isEmpty()) {
			throw new CocoException(-2, "异常：指示书号不能为空");
		}
		// 从界面获取的卷板号不能为空
		if (vo.getRczpno() == null || vo.getRczpno().trim().isEmpty()) {
			throw new CocoException(-3, "异常：入端卷号不能为空");
		}
		// 从界面获取的生产线别必须为SL生产线别
		Scxbpz scxb = scxbBo.getByCode(ProductType.sl, vo.getSlin());
		if (scxb == null) {
			throw new CocoException(-4, "异常：当前担当者非剪切线担当者");
		}
		// 如有卷实绩未完成，不能录入其他卷
		ZpngTp wwZp = dao.getCurr(vo.getSlin());
		if (wwZp != null && !vo.getRczpno().equals(wwZp.getJbno())) {
			throw new CocoException(-4, "异常：有卷号为 " + wwZp.getJbno()
					+ " 的剪切卷未实绩完成，不能对该卷作实绩录入");
		}

		// 判断从界面获取的指示书必须存在
		ZsdhTp zsdh = zsBo.getZsdhTp(vo.getZsno());
		if (zsdh == null) {
			throw new CocoException(-5, "异常：当前指示书不存在");
		}
		// 从界面获取的指示书必须已经分派
		// if (!ZtConstants.DHZS_STAT_YFP.equalsIgnoreCase(zsdh.getStat())) {
		// throw new CocoException(-6, "异常：该指示书未分派");
		// }
		// 从界面获取的指示书必须为未完成状态
		if (!ZtConstants.DHZS_ZSBJ_WWC.equalsIgnoreCase(zsdh.getZsbj())) {
			throw new CocoException(-7, "异常：该指示书中的卷均已实绩完成");
		}
		// 从界面获取的卷号必须存在
		ZpngTp lrZp = zpBo.getZp(vo.getRczpno());
		if (lrZp == null) {
			throw new CocoException(-8, "异常：入端卷不存在");
		}
		// 作业停止标志判断
		if (lrZp.getZtbj() != null) {
			throw new CocoException(-8, "异常：入端卷已经作业停止");
		}
		// 从界面获取的卷的订货号必须存在
		if (lrZp.getDhno() == null || lrZp.getDhno().isEmpty()) {
			throw new CocoException(-8, "异常：入端卷没有订货号");
		}
		// 卷堆场必须为D
		if (!DC.D.name.equalsIgnoreCase(lrZp.getDuic())) {
			throw new CocoException(-8, "异常：入端卷堆场不为D");
		}
		// 卷必须为中间品
		if (!EXpzk.ZJP.key.equalsIgnoreCase(lrZp.getXpzk())) {
			throw new CocoException(-8, "异常：入端卷不是中间品，不能剪切");
		}
		// 从界面获取的卷必须为未生产完成
		// if (ZtConstants.ZPNG_QDBJ_QD.equals(lrZp.getQdbj())) {
		// throw new CocoException(-9, "异常：入端卷已经生产完成");
		// }
		// 从界面获取的卷必须为未删除
		if (!EScbj.CS.key.equals(lrZp.getScbj())) {
			throw new CocoException(-10, "异常：入端卷已经删除");
		}
		// 从界面获取的卷当前卷状态不为初始或实绩录入中
		if (!ZpStat.CS.stat.equals(lrZp.getStat())
				&& !ZpStat.SJLR.stat.equalsIgnoreCase(lrZp.getStat())) {
			throw new CocoException(-11, "异常：当前卷状态不为初始或实绩录入中");
		}
		// 判断从界面获取的指示书号与卷的指示书号是否一致
		if (!vo.getZsno().equalsIgnoreCase(lrZp.getZsno())) {
			throw new CocoException(-10, "异常：当前卷号与指示书不匹配");
		}
		// 新生成的出端制品对象
		SLSjSaveVO cdZp = new SLSjSaveVO();
		// 生成新的出端制品号
		ICmnBO cmnBo = Helper.getBean(ICmnBO.class);
		String[] cdnos = cmnBo.generateNo(vo.getRczpno(), null);
		String cdno;
		if (cdnos == null || cdnos.length == 0 || (cdno = cdnos[0]) == null
				|| cdno.trim().isEmpty()) {
			throw new CocoException(-1, "异常：无法生成制品号");
		}
		cdZp.setJbno(cdno);
		// 设入端卷号
		cdZp.setRczpno(lrZp.getJbno());
		// 设硬度
		Integer ying = ydBO.getYing(vo.getRczpno());
		cdZp.setYing(ying);
		// 设入生产线
		cdZp.setSlin(vo.getSlin());
		// 设入指示书号
		cdZp.setZsno(zsdh.getZsno());
		// 取制造商代码
		cdZp.setZzno(lrZp.getZzsd());
		// 设入制品尺寸·厚
		cdZp.setHouu(zsdh.getHouu());
		// 设入制品尺寸·宽
		cdZp.setKuan(zsdh.getKuan());
		// 设入制品尺寸·长
		cdZp.setCang(zsdh.getCang());
		// 设入薄板单重
		cdZp.setBdan(zsdh.getBdan());
		// 设入班
		cdZp.setBan(user.getShift());
		// 设入组
		cdZp.setZu(user.getGroup());
		// 实绩附着量正
		cdZp.setSczm(lrZp.getSczm());
		// 实绩附着量正反
		cdZp.setScfm(lrZp.getScfm());
		// 取制造商代码
		cdZp.setZzsd(lrZp.getZzsd());
		return cdZp;
	}

	@Override
	public SjzsVO viewSjzs(String jbno) {
		if (jbno == null) {
			throw new CocoException(-1, "异常：无法获取入端卷号");
		}
		SjzsVO vo = new SjzsVO();
		// 取制品对象
		ZpngTp zp = zpBo.getZp(jbno);
		if (zp == null || zp.getZsno() == null || zp.getZsno().isEmpty()) {
			throw new CocoException(-1, "异常：入端卷不存在");
		}
		// 增加业联
		String ylnNo = zp.getYlno();
		if (ylnNo != null) {
			String[] ylns1 = ylnNo.split(",");
			String ylty;
			String ylns2;
			for (String yln : ylns1) {
				ylty = yln.substring(0, 1);
				ylns2 = yln.substring(2);

				if (EKpType.SL.key.equals(ylty)
						|| EKpType.BOTH.key.equals(ylty)) {
					vo.getYlnos().add(ylns2);
				}

			}
		}
		// vo.setYlnos(SinoUtils.parseYl(zp.getYlno(), EYlType.SL));
		// 取指示书
		ZsdhTp zsdh = zsBo.getZsdhTp(zp.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "异常：入端卷对应的指示书不存在");
		}
		// vo.setKpnos(SinoUtils.parseKp(zsdh, EKpType.SL));
		// 取订货对象
		DhuoTp dh = dhBo.get(SinoUtils.parseDhuoPk(zp.getDhno()));
		if (dh == null) {
			throw new CocoException(-1, "异常：入端卷对应的订货合同不存在");
		}
		// 增加附业KEY
		if (EKpType.ETL.key.equals(dh.getKpn1Flag())
				|| EKpType.BOTH.key.equals(dh.getKpn1Flag())) {
			vo.getKpnos().add(dh.getKpn1());
		}
		if (EKpType.ETL.key.equals(dh.getKpn2Flag())
				|| EKpType.BOTH.key.equals(dh.getKpn2Flag())) {
			vo.getKpnos().add(dh.getKpn2());
		}
		if (EKpType.ETL.key.equals(dh.getKpn3Flag())
				|| EKpType.BOTH.key.equals(dh.getKpn3Flag())) {
			vo.getKpnos().add(dh.getKpn3());
		}
		if (EKpType.ETL.key.equals(dh.getKpn4Flag())
				|| EKpType.BOTH.key.equals(dh.getKpn4Flag())) {
			vo.getKpnos().add(dh.getKpn4());
		}
		// 取etl、木工所和sl备注
		if (dh.getBz1() != null) {
			vo.setBz1(dh.getBz1());
		}
		// 取SL备注【订货】
		if (dh.getBz2() != null) {
			vo.setBz2(dh.getBz2());
		}
		// 取etl、木工所和sl备注【订货】
		if (dh.getBz3() != null) {
			vo.setBz3(dh.getBz3());
		}
		return vo;
	}

	@Override
	public void validateCheck(SLSjSaveVO sjsave, SjzkVO vo, User user,
			String clq) {
		ZpngTp zp = pasreZp(sjsave);
		validate(zp, vo, user, clq);
	}

	@Override
	public void checkKuanCang(Double sckn, Double jdcn, String zsno) {
		// 取指示书
		ZsdhTp zss = zsBo.getZsdhTp(zsno);
		if (zss == null) {
			throw new CocoException(-1, "取指示书信息为空");
		}
		// 取订货DB
		String dhnoline = zss.getDhno();
		if (dhnoline == null || dhnoline.isEmpty()) {
			throw new CocoException(-1, "订货DB的订货NO不存在！");
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
		DhuoTp dhuo = dhBo.get(dhpk);
		if (dhuo == null) {
			throw new CocoException(-1, "取订货DB信息为空");
		}
		if (sckn != null) {
			// 订货尺寸宽
			Double kuan = 0.0;
			// 公差下限
			Double kxzi = 0.0;
			// 公差上限
			Double kszi = 0.0;
			// 实测宽
			if (dhuo.getKuan() != null) {
				kuan = dhuo.getKuan();
			}
			if (dhuo.getKxzi() != null) {
				kxzi = dhuo.getKxzi();
			}
			if (dhuo.getKszi() != null) {
				kszi = dhuo.getKszi();
			}
			sckn = sckn * 0.01;
			if (sckn < (kuan + kxzi)) {
				throw new CocoException(-17, "异常：实测宽小于公差下限");
			}
			if (sckn > (kuan + kszi)) {
				throw new CocoException(-17, "异常：实测宽大于公差上限");
			}

		}
		// }
		// 实测剪断长
		// if (zp.getJdcn() == null || zp.getJdcn() <= 0) {
		// throw new CocoException(-18, "异常：实测剪断长为必填项");
		// }
		if (jdcn != null) {
			// 订货尺寸宽
			Double cang = 0.0;
			// 公差下限
			Double cxzi = 0.0;
			// 公差上限
			Double cszi = 0.0;

			if (dhuo.getCang() != null) {
				cang = dhuo.getCang();
			}
			if (dhuo.getCxzi() != null) {
				cxzi = dhuo.getCxzi();
			}
			if (dhuo.getCszi() != null) {
				cszi = dhuo.getCszi();
			}
			jdcn = jdcn * 0.01;
			if (jdcn < (cang + cxzi)) {
				throw new CocoException(-18, "异常：实测剪断长小于公差下限");
			}
			if (jdcn > (cang + cszi)) {
				throw new CocoException(-18, "异常：实测剪断长大于公差上限");
			}
		}
	}

	private void validate(ZpngTp zp, SjzkVO vo, User user, String clq) {

		if (user == null) {
			throw new CocoException(-1, "异常：担当者没有登录");
		}
		if (zp == null) {
			throw new CocoException(-1, "异常：取界面实绩数据为空");
		}
		if (ChanType.hg.key.equals(zp.getChan())
				&& EPiler.P1.piler.equals(zp.getCqpl())) {
			throw new CocoException(-8888, "PILE为1,产量代码为1。是否继续登录?");
		}
		zp.getCqpl();
		Boolean banbl = banBO.checkBan(zp.getBan());
		if (!banbl) {
			throw new CocoException(-43, "现在的时间不是这个班！");
		}
		// 取入端制品
		ZpngTp rczp = zpBo.getRef(zp.getRczpno());
		if (rczp == null) {
			throw new CocoException(-1, "异常：取入侧制品号为空");
		}

		// 取指示书
		ZsdhTp zss = zsBo.getRef(rczp.getZsno());
		if (zss == null) {
			throw new CocoException(-1, "取指示书信息为空");
		}
		// 取订货DB
		String dhnoline = zss.getDhno();
		if (dhnoline == null || dhnoline.isEmpty()) {
			throw new CocoException(-1, "订货DB的订货NO不存在！");
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
		DhuoTp dhuo = dhBo.get(dhpk);
		if (dhuo == null) {
			throw new CocoException(-1, "取订货DB信息为空");
		}
		// 实绩状况检查
		checkSjzk(vo, dhuo, rczp.getZpzl());
		// 不是终了和中止
		if (vo.getZl() == null && vo.getZz() == null) {
			// 界面元素检验
			checkSjlr(zp, zss, dhuo, clq);
		}
	}

	private ZpngTp pasreZp(SLSjSaveVO sjsave) {
		ZpngTp zp = new ZpngTp();
		// 出端COIL No.
		zp.setJbno(sjsave.getJbno());
		// 硬度
		zp.setYing(sjsave.getYing());
		// 指示书No
		zp.setZsno(sjsave.getZsno());
		// 入端NO
		zp.setRczpno(sjsave.getRczpno());
		// sl流水线代码（实绩）
		zp.setSlin(sjsave.getSlin());
		// 制造商代码
		zp.setZzno(sjsave.getZzno());
		// 制品尺寸?厚
		zp.setHouu(sjsave.getHouu());
		// 制品尺寸宽
		zp.setKuan(sjsave.getKuan());
		// 制品尺寸?长
		zp.setCang(sjsave.getCang());
		// 薄板单重
		zp.setBdan(sjsave.getBdan());
		// 班
		zp.setBan(sjsave.getBan());
		// 组
		zp.setZu(sjsave.getZu());
		// 实绩附着量正
		zp.setSczm(sjsave.getSczm());
		// 实绩附着量正反
		zp.setScfm(sjsave.getScfm());
		// 端板标记
		if (sjsave.getDbbj() != null) {
			zp.setDbbj(sjsave.getDbbj().toString());
		}
		// 计算重量
		zp.setJszl(sjsave.getJszl());
		// 实际重量
		zp.setSjzl(sjsave.getSjzl());
		// 产量代码
		zp.setChan(sjsave.getChan());
		// 等级
		zp.setDeng(sjsave.getDeng());
		// 处置
		zp.setCzdm(sjsave.getCzdm());
		// 主缺
		zp.setQqdm(sjsave.getQqdm());
		// 缺陷2
		zp.setQqd2(sjsave.getQqd2());
		// 缺陷3
		zp.setQqd3(sjsave.getQqd3());
		// 检定员
		zp.setJdyn(sjsave.getJdyn());
		// 计数员
		zp.setJsyn(sjsave.getJsyn());
		// 枚数
		zp.setZshu(sjsave.getZshu());
		// D(A)-MARK
		zp.setDmrk(sjsave.getDmrk());
		// PILER
		zp.setCqpl(sjsave.getCqpl());
		// 录入员
		zp.setDdno(sjsave.getDdno());
		// 实测宽

		zp.setSckn(sjsave.getSckn());

		// 实测剪断长

		zp.setJdcn(sjsave.getJdcn());

		// LINE-SPEED
		zp.setLnsd(sjsave.getLnsd());
		// 长分布1
		zp.setCm05(sjsave.getCm05());
		// 长分布2
		zp.setCp00(sjsave.getCp00());
		// 长分布3
		zp.setCp05(sjsave.getCp05());
		// 长分布4
		zp.setCp10(sjsave.getCp10());
		// 长分布5
		zp.setCp15(sjsave.getCp15());
		// 耳波op高度
		if (sjsave.getBopg() != null) {
			zp.setBopg(sjsave.getBopg());
		}
		// 耳波op间隔
		zp.setBopj(sjsave.getBopj());
		// 耳波Dr高度
		if (sjsave.getBdrg() != null) {
			zp.setBdrg(sjsave.getBdrg());
		}
		// 耳波Dr间隔
		zp.setBdrj(sjsave.getBdrj());
		// 耳波等级
		zp.setBdji(sjsave.getBdji());
		// 直角Op
		if (sjsave.getZopz() != null) {
			zp.setZopz(sjsave.getZopz());
		}
		// 直角Dr
		if (sjsave.getZdrz() != null) {
			zp.setZdrz(sjsave.getZdrz());
		}

		// L反
		if (sjsave.getZndz() != null) {
			zp.setZndz(sjsave.getZndz());
		}

		// C反
		if (sjsave.getHndz() != null) {
			zp.setHndz(sjsave.getHndz());
		}
		// 中伸高度
		if (sjsave.getZbog() != null) {
			zp.setZbog(sjsave.getZbog());
		}
		// 中伸间隔
		zp.setZboj(sjsave.getZboj());
		// 翘度
		zp.setQduz(sjsave.getQduz());
		// 毛边上
		zp.setMaos(sjsave.getMaos());
		// 毛边下
		zp.setMaox(sjsave.getMaox());
		// 修边毛边DR
		zp.setMadr(sjsave.getMadr());
		// 修边毛边OP
		zp.setMaop(sjsave.getMaop());
		// 矢切量
		zp.setSiql(sjsave.getSiql());
		// 镜面检查
		zp.setJmjc(sjsave.getJmjc());
		// 垫足确认
		zp.setDmqr(sjsave.getDmqr());
		// 针孔确认
		zp.setZkqr(sjsave.getZkqr());

		// 特记
		zp.setVari(sjsave.getVari());
		return zp;
	}

	@Override
	public void sjlrSave(SLSjSaveVO sjsave, SjzkVO vo, User user) {
		ZpngTp zp = pasreZp(sjsave);
		sjlr(zp, vo, user);
	}

	private void sjlr(ZpngTp zp, SjzkVO vo, User user) {
		if (user == null) {
			throw new CocoException(-1, "异常：担当者没有登录");
		}
		if (zp == null) {
			throw new CocoException(-1, "异常：取界面实绩数据为空");
		}
		// 取入端制品
		ZpngTp rczp = zpBo.getRef(zp.getRczpno());
		if (rczp == null) {
			throw new CocoException(-1, "异常：取入侧制品号为空");
		}

		// 取指示书
		ZsdhTp zss = zsBo.getRef(rczp.getZsno());
		if (zss == null) {
			throw new CocoException(-1, "取指示书信息为空");
		}
		// 取订货合同
		DhuoTp dhuo = dhBo.getRef(SinoUtils.parseDhuoPk(zss.getDhno()));
		if (dhuo == null) {
			throw new CocoException(-1, "异常：取订货合同为空");
		}
		// 实绩状况检查
		// checkSjzk(vo);
		// 不是终了和中止
		// if (vo.getZl() == null && vo.getZz() == null) {
		// // 界面元素检验
		// checkSjlr(zp, zss);
		// }

		// 装入终了或中止时，完成以下库表更新
		int zzzl = 0;
		Date date = new Date();
		if (vo.getZl() != null || vo.getZz() != null) {
			String stat = null;
			// 终了
			if (SlConstants.SJ_ZL.equals(vo.getZl())) {
				stat = ZpStat.SJZL.stat;
				// 所有卷都已完成时，更新“指示完成标记”
				if (!zpBo.isExistedZpng(zss.getZsno(), zp.getRczpno())) {
					zss.setZsbj(ZtConstants.DHZS_ZSBJ_YWC);
					zss.setZsny(date);
				}
				// 终了时，更新状态、确定标记、删除标记
				if (SlConstants.SJ_ZL.equals(vo.getZl())) {
					rczp.setStat(ZpStat.SJZL.stat);
					rczp.setQdbj(ZtConstants.ZPNG_QDBJ_QD);
					rczp.setScbj(EScbj.YSJZL.key);
					rczp.setSjsj(date);
				}
			}
			// 中止
			if (SlConstants.SJ_ZZ.equals(vo.getZz())) {
				stat = ZpStat.SJZZ.stat;
				// 计算中止余材的钢卷重量
				// zzzl = fetchZzzl4Zrzz(vo.getJh(), rczp.getNjno(),
				// rczp.getXpkn());
				zzzl = cmnBo.getZzzl(vo.getJh(), rczp.getNjno(), rczp.getXpkn());

				// 新增中止后的余材制品,包括更新订货合同的指示量[与更新入端卷顺序不能颠倒]
				saveZzzp4Zrzz(rczp, dhuo, zzzl, zp, user);

				// 更新订货合同
				saveDhuo4Zrzz(dhuo, zzzl);
				// 写分配操作记录
				saveFpcz4Zrzz(rczp, user);
				// 更新入端制品
				rczp.setStat(ZpStat.SJZZ.stat);
				rczp.setQdbj(ZtConstants.ZPNG_QDBJ_QD);
				rczp.setScbj(EScbj.YSJZL.key);
				rczp.setZrzl(rczp.getZpzl() - zzzl);
				rczp.setSjsj(date);
				// 更新订货指示书
				// 所有卷都已完成时，更新“指示完成标记”
				if (!zpBo.isExistedZpng(zss.getZsno(), zp.getRczpno())) {
					zss.setZsbj(ZtConstants.DHZS_ZSBJ_YWC);
					zss.setZsny(date);
				}
				// 更新“装入中止”
				zss.setZlzz(SlConstants.SJ_ZZ);
			}
			// 用户略称
			zp.setAbbr(rczp.getAbbr());
			// 制品商代码
			zp.setZzsd(rczp.getZzsd());
			// 用户代码
			zp.setUser(rczp.getUser());
			// 更新硬度
			// GxVO gx = new GxVO();
			// gx.setJbno(zp.getJbno());
			// gx.setYing(zp.getYing());
			// gx.setJdyn(zp.getJdyn());
			ydBO.updateYd(zp.getJbno(), zp.getYing());
			/*
			 * 保存公共日志：现品情报共通格式/指示情报共通格式
			 */
			SjVO rzVo = sjBo.saveForSlZpZZ(zp, zss, vo, zp.getYing(), null);
			// 保存SL实绩日志
			SlsjLp slsj = new SlsjLp();
			slsj.setXpfmt(rzVo.getXpfmt());
			slsj.setZsfmt(rzVo.getZsfmt());
			slsj.setStat(stat);
			slsj.setCrea(date);
			slsj.setRczpno(zp.getRczpno());
			slsj.setBan(zp.getBan());
			slsj.setZu(zp.getZu());
			slsj.setZrzz(stat);
			dao.saveSlsj(slsj);
			return;
		}
		/*
		 * 新增出端制品
		 */
		saveCdzp4Sjlr(zp, rczp, zss, dhuo, date);
		/*
		 * 更新入端卷
		 */
		updateRdzp4Sjlr(rczp, vo, zzzl, date, zp.getSlin());
		/*
		 * 更新指示书（要在更新入端卷之后，为了统计指示书下的入端卷确定标记）
		 */
		updateZss4Sjlr(zss, vo.getZz(), date);
		/*
		 * 更新订货合同
		 */
		updateDhuo4Sjlr(zp, dhuo);
		/*
		 * 新增指示出端记录
		 */
		saveZscd4Sjlr(zp, vo.getZz(), zss.getZsno(), date);
		/*
		 * 保存日志
		 */
		saveRz(zp, date);
		/*
		 * 保存公共日志：现品情报共通格式/指示情报共通格式
		 */
		SjVO rzVo = sjBo.saveForZp(zp.getJbno(), zss.getZsno(), ZtConstants.ZPNG_CLQ_ADD);
		/*
		 * 保存SL实绩日志
		 */
		saveSlsjrz(zp, vo.getZz(), rzVo.getXpfmt(), rzVo.getZsfmt(), ESlRzzt.LR, date);
		// 更新垫木库存
		dmBO.updateDmkz(zss.getKuan(), zss.getCang(), zss.getDmfx(), zss.getKbao(), ZtConstants.MG_KW, zp.getSlin(), 1);
	}

	/**
	 * <p>
	 * 检查入侧卷的终了、终止状态
	 * </p>
	 */
	private void checkSjzk(SjzkVO vo, DhuoTp dhuo, Integer zrzl) {
		// 检查母卷完成对象
		if (vo == null) return;
		// 终了
		String zl = vo.getZl();
		// 中止
		String zz = vo.getZz();
		// 卷厚
		Integer jh = vo.getJh();
		// 硬度
		Integer ying = vo.getYing();
		// 终了录入合法性检查
		if (zl != null) {
			if (!SlConstants.SJ_ZL.equals(zl)) {
				throw new CocoException(-1, "母卷终了 不能填1以外的值");
			}
			if (dhuo.getDhno() != null
					&& !"T".equals(dhuo.getDhno().substring(0, 1))) {
				if (ying == null) {
					throw new CocoException(-42, "硬度不能为空！");
				}
				if (dhuo.getYmin() != null) {
					if (ying * 0.1 < Integer.parseInt(dhuo.getYmin())) {
						throw new CocoException(-99, "硬度范围" + dhuo.getYmin()
								+ "-" + dhuo.getYmax());
					}
				}
				if (dhuo.getYmax() != null) {
					if (ying * 0.1 > Integer.parseInt(dhuo.getYmax())) {
						throw new CocoException(-99, "硬度范围" + dhuo.getYmin()
								+ "-" + dhuo.getYmax());
					}
				}
			}

		}
		// 中止检查
		if (zz != null) {
			// 合法性检查
			if (!SlConstants.SJ_ZZ.equals(zz)) {
				throw new CocoException(-2, "母卷中止 不能填1以外的值");
			}
			// 中止时，卷厚必须填写
			if (jh == null || jh <= 0) {
				throw new CocoException(-3, "中止时必须填写卷厚");
			}
			if (dhuo.getDhno() != null
					&& !"T".equals(dhuo.getDhno().substring(0, 1))) {
				if (ying == null) {
					throw new CocoException(-42, "硬度不能为空！");
				}
				if (dhuo.getYmin() != null) {
					if (ying * 0.1 < Integer.parseInt(dhuo.getYmin())) {
						throw new CocoException(-99, "硬度范围" + dhuo.getYmin()
								+ "-" + dhuo.getYmax());
					}
				}
				if (dhuo.getYmax() != null) {
					if (ying * 0.1 > Integer.parseInt(dhuo.getYmax())) {
						throw new CocoException(-99, "硬度范围" + dhuo.getYmin()
								+ "-" + dhuo.getYmax());
					}
				}
			}

		}
		if (zl != null && zz != null) {
			throw new CocoException(-1, "异常：母卷终了与实绩中止不能同时填写");
		}
	}

	/**
	 * <p>
	 * 判断选别品号是否大于生成号
	 * </p>
	 * 
	 * @param psjbno
	 * @param jbno
	 * @return
	 */
	private boolean ispsjbo(String psjbno, String jbno) {
		if (psjbno != null && !psjbno.isEmpty() && psjbno.length() > 8
				&& jbno != null && !jbno.isEmpty() && jbno.length() > 8) {
			if (Integer.parseInt(psjbno.substring(8)) >= Integer.parseInt(jbno.substring(8))) {
				return true;
			}
			else {
				return false;
			}

		}
		return false;
	}

	/**
	 * <p>
	 * 对担当者界面录入的元素检查是否合法
	 * </p>
	 * 
	 * @return
	 */
	private void checkSjlr(ZpngTp zp, ZsdhTp zss, DhuoTp dhuo, String clq) {

		// 检查界面制品对象
		if (zp == null) {
			throw new CocoException(-80, "无法获取界面制品信息");
		}
		// 新增时检查是否有生成选别品,订正不检查
		if (ZtConstants.ZPNG_CLQ_ADD.equals(clq)) {
			List<ZpngTp> pszps = zpBo.listPszp(zp.getRczpno());
			String psjbno = "";
			if (pszps != null && pszps.size() > 0) {
				for (ZpngTp pszp : pszps) {
					if (ispsjbo(pszp.getJbno(), zp.getJbno())) {
						psjbno = psjbno + "," + pszp.getJbno();
					}

				}
				if (!psjbno.isEmpty()) {
					throw new CocoException(-98, "制品号：" + psjbno.substring(1)
							+ "是选别品");
				}
			}
		}

		// 实际重量
		if (zp.getSjzl() == null) {
			throw new CocoException(-4, "实际重量不能为空");
		}
		if (zp.getSjzl() <= 0) {
			throw new CocoException(-4, "实际重量不合规范");
		}
		// 产量代码
		if (zp.getChan() == null || zp.getChan().isEmpty()) {
			throw new CocoException(-5, "产量代码不能为空");
		}
		// 等级
		if (zp.getDeng() == null) {
			throw new CocoException(-12, "等级不能为空");
		}
		// 检定员
		if (zp.getJdyn() == null) {
			throw new CocoException(-11, "检定员不能为空");
		}
		// 计数员
		if (zp.getJsyn() == null) {
			throw new CocoException(-6, "计数员不能为空");
		}
		// 枚数
		if (zp.getZshu() == null) {
			throw new CocoException(-13, "枚数不能为空");
		}
		// PILER
		if (zp.getCqpl() == null) {
			throw new CocoException(-15, "PILER不能为空");
		}
		// 录入员
		// if (zp.getDdno() == null) {
		// throw new CocoException(-16, "录入员不能为空");
		// }

		// LINE-SPEED
		if (zp.getLnsd() == null) {
			throw new CocoException(-19, "LINE-SPEED不能为空");
		}
		// 镜面检查
		if (zp.getJmjc() == null) {
			throw new CocoException(-38, "镜面检查不能为空");
		}
		// 垫足确认
		if (zp.getDmqr() == null) {
			throw new CocoException(-39, "垫足确认不能为空");
		}
		// 针孔确认
		if (zp.getZkqr() == null) {
			throw new CocoException(-40, "针孔确认不能为空");
		}
		/*
		 * String pzno = zss.getPzno(); if (!pzno.isEmpty()) { // 品种编号第一位为2 if
		 * (Code118.coil.key.equals(pzno.substring(0, 1))) { if (zp.getSjzl() <
		 * zss.getKbzx()) { throw new CocoException(-1, "实际重量低于订货合同中的捆包指定重量下限");
		 * } if (zp.getSjzl() > zss.getKbzs()) { throw new CocoException(-1,
		 * "实际重量高于于订货合同中的捆包指定重量上限"); } } }
		 */

		// 产量代码与处置代码间的判断
		// if (ChanType.hg.key.equals(zp.getChan())
		// || ChanType.bl.key.equals(zp.getChan())) {
		// if (zp.getCzdm() != null) {
		// throw new CocoException(-5, "产量代码为1 或9时，处置代码必须为空");
		// }
		// }
		ChanType cl = ChanType.get(zp.getChan());
		if (cl == null) {
			throw new CocoException(-5, "产量代码不合规范");
		}
		// 等级
		String dj = zp.getDeng();
		if (dj == null || dj.isEmpty()) {
			throw new CocoException(-12, "等级不能为空");
		}
		// 产量、等级与分配等级的检查
		cmnBo.checkChan(cl.key, dj, zss.getFpdj());
		// 主缺陷，缺陷2，缺陷3
		// 检查主缺陷
		if (zp.getQqdm() != null) {
			CodeItem code = codeBo.getCodeItem(CmnConstants.CODE_QX, zp.getQqdm());
			if (code == null) {
				throw new CocoException(-8, "主缺陷在码表不存在！");
			}
		}
		// 检查缺陷2
		if (zp.getQqd2() != null) {
			CodeItem code = codeBo.getCodeItem(CmnConstants.CODE_QX, zp.getQqd2());
			if (code == null) {
				throw new CocoException(-9, "缺陷2在码表不存在！");
			}
		}
		// 检查缺陷3
		if (zp.getQqd3() != null) {
			CodeItem code = codeBo.getCodeItem(CmnConstants.CODE_QX, zp.getQqd3());
			if (code == null) {
				throw new CocoException(-10, "缺陷3在码表不存在！");
			}
		}
		// 检定员
		if (zp.getJdyn() == null || zp.getJdyn().trim().isEmpty()) {
			throw new CocoException(-11, "异常：检定员为必填项");
		}
		// 计数员
		if (zp.getJsyn() == null || zp.getJsyn().trim().isEmpty()) {
			throw new CocoException(-6, "异常：计数员为必填项");
		}
		// 枚数
		Integer zshu = zp.getZshu();
		if (zshu == null) {
			throw new CocoException(-13, "异常：枚数为必填项");
		}
		if (zshu <= 0) {
			throw new CocoException(-13, "异常：枚数为必须为正数");
		}
		// 包装张数
		Integer bzzs = zss.getBzzs();
		if (zp.getCzdm() == null && !ChanType.fs.key.equals(zp.getChan())) {
			if ((cl == ChanType.hg || cl == ChanType.bl)) {
				if (bzzs == null || bzzs.intValue() != zshu.intValue()) {
					throw new CocoException(-13, "异常：合格品枚数必须为指示要求的包装张数要求："
							+ (bzzs == null ? "包装张数没有指定" : bzzs.toString()));
				}
			}
			// 非合格品的时候
			else {
				if (zss.getLtkf() == null
						|| SlConstants.ZPNG_LTKF_K.equals(zss.getLtkf())) {
					if (zshu % SlConstants.LTZS != 0) {
						throw new CocoException(-13,
								"异常：非合格品订货合同有零头的时候必须是112的倍数");
					}
				}
				else {
					if (zshu % SlConstants.WLTZS != 0) {
						throw new CocoException(-13,
								"异常：非合格品订货合同无零头的时候必须是50的倍数");
					}
				}
			}
		}
		// 端板标记
		String dbbj = zp.getDbbj();

		// 处置不为空时，检查合法性
		if (ChanType.hg.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			if (zp.getCzdm() != null) {
				ECzdm cz = ECzdm.get(zp.getCzdm());
				if (cz == null) {
					throw new CocoException(-7, "异常：处置代码填写不合法，只能为空、1、2、3、4");
				}
				if (ECzdm.CZ_4.key.equals(zp.getCzdm())) {
					throw new CocoException(-7, "异常：当产量为1时，处置只能为空或1、2、3");
				}
			}
		}
		if (ChanType.fs.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			if (zp.getCzdm() != null) {
				if (!ECzdm.CZ_4.key.equals(zp.getCzdm())) {
					throw new CocoException(-7, "异常：当产量为4时，处置只能为4或空");
				}
			}
		}
		// 处置为空时，检查张数
		if (zp.getCzdm() == null) {
			// 合格品时检查端板
			if (cl == ChanType.hg || cl == ChanType.bl) {
				// // 不是端板
				if ((dbbj == null || dbbj.isEmpty())) {
					// 必须满足要求张数
					if ((cl == ChanType.hg || cl == ChanType.bl)
							&& bzzs.intValue() != zshu.intValue()) {
						throw new CocoException(-60,
								"异常：非零头的合格品枚数必须为指示要求的包装张数要求：" + bzzs);
					}
				}
				// 是端板：端板标记为1
				else if (SlConstants.ZPNG_DBBJ_DB.equals(dbbj)) {
					// 零头不允许时错误
					if (zss.getLtkf() == null
							|| !SlConstants.ZPNG_LTKF_K.equals(zss.getLtkf())) {
						throw new CocoException(-60, "异常：订货合同不允许有零头");
					}
					// 不能超过零头上限个数
					Long ds = dao.getDs(zp.getRczpno());
					if (ds != null && ds > SlConstants.MAX_ZPDB) {
						throw new CocoException(-60,
								"异常：入端卷生成的端板个数已达到限定个数，不能再录入端板");
					}
					// 零头不能超过指示要求的张数
					if (zshu > bzzs) {
						throw new CocoException(-60, "异常：零头不能超过指示要求的张数：" + bzzs);
					}
					// 个数下限检查
					Integer ltxx = zss.getLtxx();
					if (ltxx != null && zshu < ltxx) {
						throw new CocoException(-41, "异常：端板的枚数不能小于指示书的零头下限："
								+ ltxx);
					}
				}
				// 其他情况：端板标记录入不合法
				else {
					throw new CocoException(-41, "异常：端板标记录入不合法，只能录入空或1");
				}
			}
		}
		// D(A)-MARK
		if (zp.getDmrk() != null && zp.getDmrk().isEmpty()) {
			if (!ZtConstants.DMARK_B.equals(zp.getDmrk())
					&& !ZtConstants.DMARK_L.equals(zp.getDmrk())) {
				throw new CocoException(-41, "异常：D(A)-MARK输入不合法");
			}
		}
		// PILER
		String cqpl = zp.getCqpl();
		if (cqpl == null || cqpl.isEmpty()) {
			throw new CocoException(-15, "异常：PILER为必填项");
		}
		EPiler ecqpl = EPiler.get(cqpl);
		if (ecqpl == null) {
			throw new CocoException(-15, "异常：PILER输入不合法");
		}
		// 检查实测宽和实测长
		// Double sckn = zp.getSckn() == null ? null
		// : Double.parseDouble(zp.getSckn());
		// Double jdcn = zp.getJdcn() == null ? null
		// : Double.parseDouble(zp.getJdcn());
		// checkKuanCang(sckn, jdcn, zss.getZsno());

		// 长分布，耳波Op，耳波Dr，耳波等级，直角Op，直角Dr，L反，C反，中伸，翘度，毛边上，毛边下，矢切量不作检查
		// 镜面检查
		if (zp.getJmjc() == null || zp.getJmjc().isEmpty()
				|| !SlConstants.SJ_JMJC.equals(zp.getJmjc())) {
			throw new CocoException(-38, "异常：镜面检查必须确认");
		}
		// 垫足确认
		if (zp.getDmqr() == null || zp.getDmqr().isEmpty()
				|| !SlConstants.SJ_DMQR.equals(zp.getDmqr())) {
			throw new CocoException(-39, "异常：垫足确认必须确认");
		}
		// 针孔确认
		if (zp.getZkqr() == null || zp.getZkqr().isEmpty()
				|| !SlConstants.SJ_ZKQR.equals(zp.getZkqr())) {
			throw new CocoException(-40, "异常：针孔确认必须确认");
		}
		// 实际重量检查
		Integer zl = zp.getSjzl() - zp.getJszl();
		Double js = 0.0;
		if (zp.getJszl() != null && zp.getJszl() != 0) {
			js = zl * 1.0 / zp.getJszl();
		}
		if (js > 0.05 || js < -0.05) {
			throw new CocoException(-4, "实际重量和计算重量相差超出5%");
		}
		if (zss != null) {
			String dhfz = zss.getDhfz();
			if (dhfz != null) {
				String[] params = dhfz.split("/");
				if (params.length > 1) {
					if (CodeChdxZl.chemical.key.equals(params[0].substring(params[0].length() - 1))
							|| CodeChdxZl.paniya.key.equals(params[0].substring(params[0].length() - 1))) {
						if (!"1".equals(zp.getDmrk())) {
							throw new CocoException(-14, "D(A)-MARK是1，输入不正确!");
						}
					}
					if (CodeChdxZl.chemical.key.equals(params[1].substring(params[1].length() - 1))
							|| CodeChdxZl.paniya.key.equals(params[1].substring(params[1].length() - 1))) {
						if (!"2".equals(zp.getDmrk())) {
							throw new CocoException(-14, "D(A)-MARK是2，输入不正确!");
						}
					}
					if (!CodeChdxZl.chemical.key.equals(params[0].substring(params[0].length() - 1))
							&& !CodeChdxZl.paniya.key.equals(params[0].substring(params[0].length() - 1))
							&& !CodeChdxZl.chemical.key.equals(params[1].substring(params[1].length() - 1))
							&& !CodeChdxZl.paniya.key.equals(params[1].substring(params[1].length() - 1))) {
						if (zp.getDmrk() != null) {
							throw new CocoException(-14, "D(A)-MARK是空，输入不正确!");
						}
					}
				}
			}
		}

	}

	/**
	 * <p>
	 * 保存实绩录入
	 * </p>
	 * 
	 * @param zp
	 */
	private void saveCdzp4Sjlr(ZpngTp zp, ZpngTp rczp, ZsdhTp zss, DhuoTp dhuo,
			Date date) {
		// 生产线别
		Scxbpz scx = scxbBo.getByCode(ProductType.sl, zp.getSlin());
		if (scx == null) {
			throw new CocoException(-1, "保存出端制品时异常：无法获取生产线");
		}
		// 用户略称 ABBR_
		zp.setAbbr(zss.getAbbr());
		// 薄板单重 BDAN_
		zp.setBdan(zss.getBdan());
		// m单重 MDAN_
		zp.setMdan(zss.getMdan());
		// 板厚不量有无 BHBL_
		zp.setBhbl(rczp.getBhbl());
		// BL-Date(装船日) BLNY_
		zp.setBlny(rczp.getBlny());
		// 标签换帖标记 BQHT_
		zp.setBqht(null);
		// 包装材料重量（Kg） BZCL_
		zp.setBzcl(zss.getBzcl());
		// 重量计算补正系数 BZXS_
		zp.setBzxs(zss.getBzxs());
		// 包装张数 BZZS_
		zp.setBzzs(zss.getBzzs());
		// 制品尺寸.长 CANG_
		zp.setCang(zss.getCang());
		// 采购单价 CGDJ_
		zp.setCgdj(rczp.getCgdj());
		// 产量 CHAN_
		// 差厚镀锡标记 CHDX_
		zp.setChdx(dhuo.getChdx());
		// 出货指示No CHNO_
		zp.setChno(null);
		// 出货实绩登录年月日 CHSD_
		zp.setChsd(null);
		// 出货指示年月日 CHZS_
		zp.setChzs(null);
		// 出口包装No. CKNO_
		// 出口包装No.
		Integer ckno = null;
		if (CodeNwx.wai.key.equals(dhuo.getNwai())) {
			ckno = cmnBo.generatePackageNo(zss.getDhno());
			zp.setCkno(ckno);
		}
		// 作成时间 CREA_
		zp.setCrea(date);
		// 尺寸允许范围.长mm上限.值 CSZI_
		zp.setCszi(zss.getCszi());
		// 尺寸允许范围.长mm下限.值 CXZI_
		zp.setCxzi(zss.getCxzi());
		// 处置代码 CZDM_
		ECzdm cz = ECzdm.get(zp.getCzdm());
		// 订货No.行号 DHNO_
		zp.setDhno(zss.getDhno());
		// 垫木方向 DMFX_
		zp.setDmfx(zss.getDmfx());
		// 垫木重量 DMZL_
		zp.setDmzl(zss.getDmzl());
		// 堆场搬入年月日 DUIB_
		zp.setDuib(new Date());
		// ETL实绩登录年月日 ETSD_
		zp.setEtsd(rczp.getEtsd());
		// 表面精加工符号 FACE_
		zp.setFace(zss.getFace());
		// 方向 FANG_
		zp.setFang(null);
		// 分配No FPNO_
		zp.setFpno(null);
		// 分配/余材标记 FPYC_
		// 若产量代码为1（合格）或9（保留），则标识为已分配
		if (ChanType.hg.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			zp.setFpyc(EFpyc.KEY_FP);
		}
		// 否则都为余材
		else {
			zp.setFpyc(EFpyc.KEY_YC);
		}
		// 附着量.单位 FUDW_
		zp.setFudw(zss.getFudw());
		// 附着量.反面 FUFM_
		zp.setFufm(dhuo.getFufm());
		// 附着量.正面 FUZM_
		zp.setFuzm(dhuo.getFuzm());
		// 规格代码 GGNO_
		zp.setGgno(zss.getGgno());
		// 钢种类型 GZLX_
		zp.setGzlx(zss.getGzlx());
		// 后Mark HMRK_
		zp.setHmrk(null);
		// 横向挠度.等级 HNDD_
		zp.setHndd(null);
		// 横向挠度.符号 HNDF_
		// zp.setHndf(null);
		// 横向挠度.值 HNDZ_
		// 换算尺寸.长 HNGC_
		zp.setHngc(zss.getHngc());
		// 换算尺寸.厚 HNGH_
		zp.setHngh(zss.getHngh());
		// 换算尺寸.宽 HNGK_
		zp.setHngk(zss.getHngk());
		// 制品尺寸.厚 HOUU_
		zp.setHouu(zss.getHouu());
		// 尺寸允许范围.厚%上限.值 HSZI_
		zp.setHszi(zss.getHszi());
		// 化学处理方法 HXCL_
		zp.setHxcl(zss.getHxcl());
		// 尺寸允许范围.厚%下限.值 HXZI_
		zp.setHxzi(zss.getHxzi());
		// 锯齿形式 JCXS_
		zp.setJcxs(dhuo.getJcxs());
		// 进度标记.ETL实绩 JDES_
		zp.setJdes(ZtConstants.ZPNG_JD_ETLSJ_YSJ);
		// 进度标记.ETL指示 JDEZ_
		zp.setJdez(ZtConstants.ZPNG_JD_ETLZS_YZS);
		// 进度标记.精整实绩 JDJS_
		zp.setJdjs(null);
		// 进度标记.精整指示 JDJZ_
		zp.setJdjz(null);
		// 进度标记.SL实绩 JDSS_
		zp.setJdss(ZtConstants.ZPNG_JD_SLSJ_YSJ);
		// 进度标记.SL指示 JDSZ_
		zp.setJdsz(ZtConstants.ZPNG_JD_SLZS_YZS);
		// 交货重量内容 JHNR_
		zp.setJhnr(dhuo.getJhnr());

		// 捆包指定重量.上限 KBZS_
		zp.setKbzs(zss.getKbzs());
		// 捆包指定重量.下限 KBZX_
		zp.setKbzx(zss.getKbzx());
		// 附页KpNo.表示流水线 KPL1_
		zp.setKpn1Flag(dhuo.getKpn1Flag());
		// 附页KpNo. KPN1_
		zp.setKpn1(dhuo.getKpn1());
		// 附页KpNo.表示流水线 KPL2_
		zp.setKpn2Flag(dhuo.getKpn2Flag());
		// 附页KpNo. KPN2_
		zp.setKpn2(dhuo.getKpn2());
		// 附页KpNo.表示流水线 KPL3_
		zp.setKpn3Flag(dhuo.getKpn3Flag());
		// 附页KpNo. KPN3_
		zp.setKpn3(dhuo.getKpn3());
		// 附页KpNo.表示流水线 KPL4_
		zp.setKpn4Flag(dhuo.getKpn4Flag());
		// 附页KpNo. KPN4_
		zp.setKpn4(dhuo.getKpn4());
		// 尺寸允许范围.宽mm上限.值 KSZI_
		zp.setKszi(dhuo.getKszi());
		// 制品尺寸.宽 KUAN_
		zp.setKuan(zss.getKuan());
		// 尺寸允许范围.宽mm下限.值 KXZI_
		zp.setKxzi(zss.getKxzi());
		// 尺寸允许范围.厚mm上限.值 MSZI_
		zp.setMszi(zss.getMszi());
		// 目的 MUDI_
		zp.setMudi(null);
		// 尺寸允许范围.厚mm下限.值 MXZI_
		zp.setMxzi(zss.getMxzi());
		// 内径保护筒标记 NJBH_
		zp.setNjbh(zss.getNjbh());
		// 内径代码 NJNO_
		zp.setNjno(zss.getNjno());
		// 内外销 NWAI_
		zp.setNwai(dhuo.getNwai());
		// K-Plate表示 PLAT_
		zp.setPlat(dhuo.getPlat());
		// Pile区分 PLQF_和堆场 DUIC_
		if (cz != null) {
			zp.setPlqf(scx.getUnqualified());
			zp.setDuic(DC.E.name);
		}
		else {
			zp.setPlqf(scx.getQualified());
			zp.setDuic(DC.F.name);
		}
		// 保护标志 PROT_
		zp.setProt(zss.getProt());
		// Pile生成实绩登录年月日 PSSD_
		zp.setPssd(null);
		// Pile消灭实绩登录年月日 PXSD_
		zp.setPxsd(null);
		// 品种代码 PZNO_
		zp.setPzno(zss.getPzno());
		// 确定标记 QDBJ_
		zp.setQdbj(ZtConstants.ZPNG_QDBJ_CS);
		// 溶接个数.冷延 RJLY_
		zp.setRjly(rczp.getRjly());
		// 溶接个数.酸洗 RJSX_
		zp.setRjsx(rczp.getRjsx());
		// 删除标记 SCBJ_
		zp.setScbj(EScbj.CS.key);
		// 实绩附着量.反面 SCFM_
		zp.setScfm(rczp.getScfm());
		// 实测宽 SCKN_
		zp.setSckn(zp.getSckn());
		// 实绩附着量.正面 SCZM_
		zp.setSczm(rczp.getSczm());
		// 船名 SHIP_
		zp.setShip(rczp.getShip());
		// SL实绩登录年月日 SLSD_
		zp.setSlsd(new Date());
		// 实绩品种等级 SPDJ_
		zp.setSpdj(cmnBo.getSjpzdj(ChanType.get(zp.getChan()), zp.getDeng()));
		// 商社代码 SSNO_
		zp.setSsno(rczp.getSsno());
		// 状态 STAT_
		zp.setStat(ZpStat.CS.stat);
		// 通货区分 THQF_
		zp.setThqf(dhuo.getThqf());
		// 担当者代码 USER_
		zp.setUser(dhuo.getUser());
		// 现品尺寸.长 XPCN_
		zp.setXpcn(zss.getCang());
		// 现品尺寸.厚 XPHO_
		zp.setXpho(zss.getHouu());
		// 现品尺寸.宽 XPKN_
		zp.setXpkn(zss.getKuan());
		// 现品状况 XPZK_
		zp.setXpzk(EXpzk.BZP_KEY);
		// 销售W可(ETL) XSWK_
		zp.setXswk(dhuo.getRjie());
		// 原板规格略称 YBLC_
		zp.setYblc(rczp.getYblc());
		// 原板采购No YBNO_
		zp.setYbno(rczp.getYbno());
		// 业连No YLN1_
		zp.setYlno(rczp.getYlno());
		// 原板收货年月日 YSUO_
		zp.setYsuo(rczp.getYsuo());
		// 涂油种类 YTYP_
		zp.setYtyp(dhuo.getYtyp());
		// 运用规格 YUNY_
		zp.setYuny(zss.getYuny());
		// 压延方向指定标记 YYAN_
		zp.setYyan(zss.getYyan());
		// 中波纹等级 ZBWD_
		zp.setZbwd(rczp.getZbwd());
		// 再剪边标记 ZJBB_
		zp.setZjbb(zss.getJbbj());
		// 纵向挠度.等级 ZNDD_
		zp.setZndd(rczp.getZndd());
		// SL装入宽 ZRKN_
		zp.setZrkn(zss.getZrkn());
		// 原板制造商No ZZNO_
		zp.setZzno(rczp.getZzno());
		// 制造年月日 ZZNY_
		zp.setZzny(rczp.getZzny());
		// 制造商代码 ZZSD_
		zp.setZzsd(rczp.getZzsd());
		// 制造商卷板No ZZSJ_
		zp.setZzsj(rczp.getZzsj());
		// 库位 KW_
		zp.setKw(zp.getDuic());
		// ETL流水线代码（实绩） ELIN_
		zp.setElin(zp.getSlin());
		// 是否保税
		zp.setSfbs(rczp.getSfbs());
		// 商品编号
		zp.setSpbh(rczp.getSpbh());

		// 硬度上限
		zp.setYmax(dhuo.getYmax());
		// 硬度下限
		zp.setYmin(dhuo.getYmin());
		// 原材号码
		zp.setYcno(rczp.getYcno());

		// 计算重量 JSZL_
		if (zp.getZshu() != null && zss.getBdan() != null) {
			zp.setJszl(NumberUtils.formatDouToInt((zp.getZshu() * zss.getBdan()), 0, BigDecimal.ROUND_HALF_UP));
		}
		// 实际重量 SJZL_，界面输入的，不在改变
		// 净重量 JNZL_
		zp.setJnzl(SinoUtils.fetchJnzl(dhuo.getJhnr(), zp.getSjzl(), zp.getJszl(), zp.getChan()));
		// 制品长 JSCN_
		zp.setJscn(rczp.getJscn());
		// 毛重量 MAZL_
		zp.setMazl(SinoUtils.fetchMazl(dhuo.getJhnr(), zp.getJnzl(), zss.getBzcl(), zss.getDmzl()));
		// 制品重量 ZPZL_
		zp.setZpzl(SinoUtils.fetchZpzl(zp));

		// 装入重量
		zp.setZrzl(rczp.getZrzl());

		// 硬度 信息
		zp.setYing(rczp.getYing());
		zp.setLlyd(rczp.getLlyd());
		zp.setYdsj(rczp.getYdsj());

		// 捆包形式 KBAO_
		if (ChanType.hg.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			zp.setKbao(zss.getKbao());
		}
		else {
			Double kuan = 0.0;
			if (zp.getKuan() != null) {
				kuan = zp.getKuan();
			}
			Double cang = 0.0;
			if (zp.getCang() != null) {
				cang = zp.getCang();
			}
			if (kuan.doubleValue() > 850 || cang > 850) {
				zp.setKbao("B31");
			}
			else {
				zp.setKbao("B21");
			}
		}

		// 如果现品的产量不为1或9时，要对其做余材化操作（张良加）
		ychBO.doScYch(zp, null, dhuo, date);

		zpBo.save(zp);
	}

	// /**
	// * <p>
	// * 获取中止重量（由界面录入的卷厚计算得出）
	// * </p>
	// *
	// * @param zzjh
	// * 装入卷厚（界面录入）
	// * @param njno
	// * 内径代码（中间品）
	// * @param xpkn
	// * 现品尺寸·宽（中间品）
	// * @return int：装入中止的钢卷重量，即制品重量
	// */
	// private int fetchZzzl4Zrzz(Integer zzjh, String njno, Double xpkn) {
	// // 卷厚
	// int jh = zzjh == null ? 0 : zzjh.intValue();
	// // 内径值
	// Double nj = 0.0;
	// // 取内径码表
	// CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_020, njno);
	// if (codeItem != null) {
	// nj = Double.valueOf(codeItem.getValue());
	// }
	// // 钢卷截面面积
	// Double cutR = SinoUtils.calculate(nj, jh);
	// // 钢卷重量(钢卷截面面积*钢卷宽*7.85)
	// Double gjzl = NumberUtils.format(SinoUtils.calculate(cutR, xpkn), 3);
	// return NumberUtils.formatDouToInt(gjzl, 0, BigDecimal.ROUND_HALF_UP);
	// }

	/**
	 * <p>
	 * 装入中止时，新增中止制品
	 * </p>
	 * 
	 * @param rczp
	 *            入端制品
	 * @param dhuo
	 *            订货合同
	 * @param zzzl
	 *            中止制品重量
	 * @param user
	 *            当前担当者
	 */
	private void saveZzzp4Zrzz(ZpngTp rczp, DhuoTp dhuo, int zzzl, ZpngTp czp,
			User user) {
		// 取中止后的余材制品号
		// 此处传递jdno修改为入端制品的jbno
		String[] cdnos = cmnBo.generateNo(rczp.getJbno(), NoGenType.cut);
		if (cdnos == null || cdnos.length == 0) {
			throw new CocoException(-1, "异常：无法生成制品号");
		}
		// 实例化中止制品（中间品）
		ZpngTp zzzp = new ZpngTp();
		// 拷入端卷信息
		ReflectUtils.copy(zzzp, rczp, false);
		// 更换卷板号
		zzzp.setJbno(cdnos[1]);
		czp.setJbno(cdnos[1]);
		// 计算卷板长
		Integer jbcn = etlBo.getJbcn(zzzl, rczp.getZsno());
		// 卷板长
		zzzp.setJbcn(jbcn);
		// 订货合同号
		zzzp.setDhno(null);
		// 客户略称
		zzzp.setAbbr(null);
		// 指示书号
		zzzp.setZsno(null);
		// 分配号
		zzzp.setFpno(null);
		// 分配余材
		zzzp.setFpyc(EFpyc.KEY_YC);
		// SL生产线别
		zzzp.setSlin(null);
		// 进度·SL指示
		zzzp.setJdsz(null);
		// 进度·SL实绩
		zzzp.setJdss(null);
		// 删除标记
		zzzp.setScbj(EScbj.CS.key);
		// 状态
		zzzp.setStat(ZpStat.CS.stat);
		// 计算重量 JSZL_
		// Integer jszl = etlBo.getJszl(rczp.getZsno(), jbcn, rczp.getCutc(),
		// rczp.getLosc());
		zzzp.setJszl(zzzl);
		czp.setJszl(zzzl);
		// 实际重量 SJZL_
		zzzp.setSjzl(zzzl);
		czp.setSjzl(zzzl);
		// 净重量 JNZL_
		zzzp.setJnzl(zzzl);
		czp.setJnzl(zzzl);
		// 毛重量 MAZL_
		// Integer mazl = etlBo.getMazl(zzzp.getDhno(), zzzl);
		zzzp.setMazl(zzzl);
		// 制品重量
		zzzp.setZpzl(zzzl);
		czp.setZpzl(zzzl);
		// 装入重量
		zzzp.setZrzl(zzzp.getZpzl());
		czp.setZrzl(zzzp.getZpzl());
		// 创建时间为当前日期
		zzzp.setCrea(new Date());
		// 修改时间
		zzzp.setUpda(null);
		/*
		 * 保存中止制品
		 */
		zpBo.save(zzzp);
	}

	/**
	 * <p>
	 * 更新订货合同
	 * </p>
	 * 
	 * @param dhuo
	 *            订货合同号
	 * @param zzzl
	 *            中止重量
	 */
	private void saveDhuo4Zrzz(DhuoTp dhuo, int zzzl) {
		double fpln = dhuo.getFpln() != null ? dhuo.getFpln() : 0.0;
		dhuo.setFpln(NumberUtils.format(fpln - zzzl * 0.001, 3));
		// dhBo.update(dhuo);
	}

	/**
	 * <p>
	 * 写分配操作记录
	 * </p>
	 * 
	 * @param rczp
	 *            入端制品
	 * @param user
	 *            当前担当者
	 */
	private void saveFpcz4Zrzz(ZpngTp rczp, User user) {
		ZkfpCzjl czjl = new ZkfpCzjl();
		// 卷板No/PileNo
		czjl.setJbno(rczp.getJbno());
		// 订货No.行号
		czjl.setDhno(rczp.getDhno());
		// 分配No
		czjl.setFpno(rczp.getFpno());
		// 现品状况
		czjl.setXpzk(EXpzk.ZJP_KEY);
		// 是否匹配
		czjl.setSfpp(Sfpp.ndis.key);
		// 操作类型
		czjl.setCzlx(Czlx.key6.key);
		// 操作时间
		czjl.setCrea(new Date());
		// 担当者代码
		czjl.setDdno(user.getId());
		// 担当者
		czjl.setDdnm(user.getName());
		czjlBO.save(czjl);
	}

	/**
	 * <p>
	 * 更新入侧制品
	 * </p>
	 * 
	 * @param rczp
	 *            入侧制品号(中间品)
	 * @param vo
	 *            实绩状态值对象
	 * @param zzzl
	 *            中止制品重量
	 */
	private void updateRdzp4Sjlr(ZpngTp rczp, SjzkVO vo, int zzzl, Date date,
			String slin) {

		// 加更新日期
		rczp.setUpda(date);
		// 更新状态
		rczp.setStat(ZpStat.SJLR.stat);
		// 更新进度·Sl实绩
		rczp.setJdss(ZtConstants.ZPNG_JD_SLSJ_YSJ);
		// 更新生产线
		rczp.setSlin(slin);
		// zpBo.update(rczp);
	}

	/**
	 * <p>
	 * 更新指示书(更新指示书的完成状态)
	 * </p>
	 * 
	 * @param zss
	 *            指示书
	 * @param zrzz
	 *            装入终止
	 */
	private void updateZss4Sjlr(ZsdhTp zss, String zrzz, Date date) {

		zss.setSfsj(ZtConstants.DHZS_SFSJ_YSJ);
		zss.setUpda(date);
		// zsBo.updateZsdh(zss);
	}

	/**
	 * <p>
	 * 更新订货合同
	 * </p>
	 * 
	 * @param zp
	 *            实绩录入时的制品
	 */
	private void updateDhuo4Sjlr(ZpngTp zp, DhuoTp dhuo) {
		if (DC.F.name.equals(zp.getDuic())) {
			if (ChanType.hg.key.equals(zp.getChan())
					|| ChanType.bl.key.equals(zp.getChan())) {
				// 更新"SL合格量(吨)"
				double slhg = dhuo.getSlhg() == null ? 0.0 : dhuo.getSlhg();
				dhuo.setSlhg(NumberUtils.format(slhg + zp.getZpzl() * 0.001, 3));
				// dhBo.update(dhuo);
			}
		}
	}

	/**
	 * <p>
	 * 保存指示出端卷板[前提：实绩制品已保存]
	 * </p>
	 * 
	 * @param zp
	 *            出端制品
	 * @param zrzz
	 *            装入中止
	 * @param zsno
	 *            指示书号
	 */
	private void saveZscd4Sjlr(ZpngTp zp, String zrzz, String zsno, Date date) {

		ZscdTp zscd = new ZscdTp();
		// 出端卷板NO
		zscd.setCdjb(zp.getJbno());
		// 指示书号
		zscd.setZsno(zsno);
		// 作成日期
		zscd.setCrea(date);
		// 制品商代码
		zscd.setZzno(zp.getZzno());
		// PILE区分
		zscd.setPlqf(zp.getPlqf());
		// 生产线代码
		zscd.setLine(zp.getSlin());
		// 装入中止
		zscd.setZrzz(zrzz);
		sjBo.saveZscd(zscd);
	}

	/**
	 * <p>
	 * 保存日志[前提：实绩制品已保存]
	 * </p>
	 * 
	 * @param zp
	 */
	private void saveRz(ZpngTp zp, Date date) {
		RiziLp rz = new RiziLp();
		// 装入卷板No
		rz.setZrjb(zp.getRczpno());
		// 出端卷板No/PileNo
		rz.setCdjb(zp.getJbno());
		// 日志种类(1-ETL实绩；2-SL实绩；3-SS实绩；4-异常处理现品作成)
		rz.setRzzl(ERzlx.SL.key);
		// 行号
		rz.setLine(zp.getJbno().substring(zp.getJbno().length() - 2));
		// 作成时间
		rz.setCrea(date);
		sjBo.save(rz);
	}

	/**
	 * <p>
	 * 保存SL实绩日志[前提：实绩制品已保存]
	 * </p>
	 */
	private void saveSlsjrz(ZpngTp zp, String zrzz, Xpfmt xpfmt, Zsfmt zsfmt,
			ESlRzzt zt, Date date) {

		SlsjLp slsj = new SlsjLp();
		// 作成时间
		slsj.setCrea(date);
		// 状态
		slsj.setStat(zt.key);
		// 现品共通情报-FK
		slsj.setXpfmt(xpfmt);
		// 指示情报-FK
		slsj.setZsfmt(zsfmt);
		// 装入中止卷板标记
		slsj.setZrzz(ZpStat.SJLR.stat);
		// 实绩流水线代码
		slsj.setLine(zp.getSlin());
		// 检定员
		slsj.setJdyn(zp.getJdyn());
		// 计数员
		slsj.setJsyn(zp.getJsyn());
		// 张数
		slsj.setZshu(zp.getZshu());
		// D-MARK标记
		slsj.setDmrk(zp.getDmrk());
		// 采取PILER
		slsj.setCqpl(zp.getCqpl());
		// 长度分布(+1.5)
		slsj.setCp15(zp.getCp15());
		// 长度分布(+1.0)
		slsj.setCp10(zp.getCp10());
		// 长度分布(+0.5) cp05;
		slsj.setCp05(zp.getCp05());
		// 长度分布(+0.0)cp00;
		slsj.setCp00(zp.getCp00());
		// 长度分布(-0.5)
		slsj.setCm05(zp.getCm05());
		// 实测宽
		slsj.setSckn(zp.getSckn());
		// 实测剪断长
		slsj.setJdcn(zp.getJdcn());
		// 流水线速度
		slsj.setLnsd(zp.getLnsd());
		// 边波纹OP-高度
		slsj.setBopg(zp.getBopg());
		// 边波纹OP-间隔
		slsj.setBopj(zp.getBopj());
		// 边波纹DP-高度
		slsj.setBdrg(zp.getBdrg());
		// 边波纹DP-间隔
		slsj.setBdrj(zp.getBdrj());
		// 边波纹等级
		slsj.setBdji(zp.getBdji());
		// 直角度OP-符号
		// slsj.setZopf(zp.getZopf());
		// 直角度OP-值
		slsj.setZopz(zp.getZopz());
		// 直角度DR-符号
		// slsj.setZdrf(zp.getZdrf());
		// 直角度DR-值
		slsj.setZdrz(zp.getZdrz());
		// 纵向挠度-符号
		// slsj.setZndf(zp.getZndf());
		// L反
		slsj.setZndz(zp.getZndz());
		// C反
		slsj.setHndz(zp.getHndz());
		slsj.setZndd(zp.getZndd());
		// 横向挠度-符号
		// slsj.setHndf(zp.getHndf());

		// 横向挠度-等级
		slsj.setDndd(zp.getHndd());
		// 中波纹等级
		slsj.setZbwd(zp.getZbwd());
		// 翘度符号
		// slsj.setQduf(zp.getQduf());
		// 翘度值
		slsj.setQduz(zp.getQduz());
		// VARIABLE
		slsj.setVari(zp.getVari());
		// 端板标记 DBBJ_
		slsj.setDbbj(zp.getDbbj());
		// 班 BAN_
		slsj.setBan(zp.getBan());
		// 组 ZU_
		slsj.setZu(zp.getZu());
		// 缺陷2 QQD2_
		slsj.setQqd2(zp.getQqd2());
		// 缺陷3 QQD3_
		slsj.setQqd3(zp.getQqd3());
		// 失切量 SIQL_
		slsj.setSiql(zp.getSiql());
		// 镜面检查测试 JMJC_
		slsj.setJmjc(zp.getJmjc());
		// 毛边上 MAOS_
		slsj.setMaos(zp.getMaos());
		// 毛边下 MAOX_
		slsj.setMaox(zp.getMaox());
		// 入侧端COIL/PILE RCZPNO_
		slsj.setRczpno(zp.getRczpno());
		// 垫木方向确认 DMQR_
		slsj.setDmqr(zp.getDmqr());
		// 针孔确认 ZKQR_
		slsj.setZkqr(zp.getZkqr());
		// 库位 KW_
		slsj.setKw(zp.getKw());
		// 担当者代码 DDNO_
		slsj.setDdno(zp.getDdno());
		// 中波纹高度 ZBOG_
		slsj.setZbog(zp.getZbog());
		// 中波纹间隔 ZBOJ_
		slsj.setZboj(zp.getZboj());
		// 特记
		slsj.setVari(zp.getVari());
		dao.saveSlsj(slsj);
	}

	/**
	 * <p>
	 * 制品更新或删除处理时，对制品进行检查: <br />
	 * 如非空、未删除、未停止、未捆包、未精整
	 * </p>
	 * 
	 * @param zp
	 *            制品对象
	 * @param user
	 *            当前担当者
	 * @return Result: 返回结果
	 */
	private void zpCheck(ZpngTp zp, User user) {
		// 制品非空
		if (zp == null) {
			throw new CocoException(-1, "制品不存在");
		}
		// 生产线担当者检查
		Scxbpz scx = scxbBo.getByDept(user.getDeptId());
		if (!Config.isAdmin(user.getId())) {
			if (scx == null) {
				throw new CocoException(-1, "异常：当前担当者不是管理员，且不是生产线担当者");
			}
			if (!scx.getCode().equalsIgnoreCase(zp.getSlin())) {
				throw new CocoException(-1, "异常：制品的订正或删除只能由最近更新该制品的生产线人员操作");
			}
		}
		// 未超过实绩录入24小时
		Date nowDate = new Date();
		if (zp.getCrea() != null
				&& (nowDate.getTime() - zp.getCrea().getTime()) > 86400000) {
			throw new CocoException(-1, "实绩登录已超过24小时，不能订正或删除！");
		}
		// 制品未删除
		EScbj scbj = EScbj.get(zp.getScbj());
		if (scbj == null || !EScbj.CS.equals(scbj)) {
			throw new CocoException(-1, "制品已经删除，不能再实绩订正或删除");
		}
		// 制品实绩完成
		if (ZtConstants.ZPNG_QDBJ_QD.equalsIgnoreCase(zp.getQdbj())) {
			throw new CocoException(-1, "制品已实绩完成，不能实绩订正或删除");
		}
		// 制品停止
		if (zp.getZtbj() != null && !zp.getZtbj().isEmpty()) {
			throw new CocoException(-1, "制品已经停止，不能实绩订正或删除");
		}
		// 捆包进度
		if (DC.G.name.equalsIgnoreCase(zp.getDuic())) {
			throw new CocoException(-1, "该制品已经捆包，不能实绩订正或删除");
		}
		// 精整实绩进度
		if (zp.getJdjs() != null && !zp.getJdjs().trim().isEmpty()) {
			throw new CocoException(-1, "该制品已经过精整实绩，不能实绩订正或删除");
		}

	}

	@Override
	public Result dzCheck(String jbno, User user) {
		if (jbno == null) {
			return new Result(-1, "无法获取订正界面的制品号");
		}
		if (user == null) {
			throw new CocoException(-1, "异常：当前担当者没有登录");
		}
		// 取制品
		ZpngTp zp = zpBo.getZp(jbno);
		// 检查订正
		zpCheck(zp, user);

		SLSjSaveVO sjsave = new SLSjSaveVO();
		// 出端COIL No.
		sjsave.setJbno(zp.getJbno());
		// 指示书No
		sjsave.setZsno(zp.getZsno());
		// 入端NO
		sjsave.setRczpno(zp.getRczpno());
		// sl流水线代码（实绩）
		sjsave.setSlin(zp.getSlin());
		// 制造商代码
		sjsave.setZzno(zp.getZzno());
		// 制造商代码
		sjsave.setZzsd(zp.getZzsd());
		// 制品尺寸?厚
		sjsave.setHouu(zp.getHouu());
		// 制品尺寸宽
		sjsave.setKuan(zp.getKuan());
		// 制品尺寸?长
		sjsave.setCang(zp.getCang());
		// 薄板单重
		sjsave.setBdan(zp.getBdan());
		// 班
		sjsave.setBan(zp.getBan());
		// 组
		sjsave.setZu(zp.getZu());
		// 实绩附着量正
		sjsave.setSczm(zp.getSczm());
		// 实绩附着量正反
		sjsave.setScfm(zp.getScfm());
		// 端板标记
		if (zp.getDbbj() != null && !zp.getDbbj().isEmpty()) {
			sjsave.setDbbj(Integer.parseInt(zp.getDbbj()));
		}
		// 计算重量
		sjsave.setJszl(zp.getJszl());
		// 实际重量
		sjsave.setSjzl(zp.getSjzl());
		// 产量代码
		sjsave.setChan(zp.getChan());
		// 等级
		sjsave.setDeng(zp.getDeng());
		// 处置
		sjsave.setCzdm(zp.getCzdm());
		// 主缺
		sjsave.setQqdm(zp.getQqdm());
		// 缺陷2
		sjsave.setQqd2(zp.getQqd2());
		// 缺陷3
		sjsave.setQqd3(zp.getQqd3());
		// 检定员
		sjsave.setJdyn(zp.getJdyn());
		// 计数员
		sjsave.setJsyn(zp.getJsyn());
		// 枚数
		sjsave.setZshu(zp.getZshu());
		// D(A)-MARK
		sjsave.setDmrk(zp.getDmrk());
		// PILER
		sjsave.setCqpl(zp.getCqpl());
		// 录入员
		sjsave.setDdno(zp.getDdno());
		// 实测宽

		sjsave.setSckn(zp.getSckn());

		// 实测剪断长
		sjsave.setJdcn(zp.getJdcn());

		// LINE-SPEED
		sjsave.setLnsd(zp.getLnsd());
		// 长分布1

		sjsave.setCm05(zp.getCm05());

		// 长分布2

		sjsave.setCp00(zp.getCp00());

		// 长分布3

		sjsave.setCp05(zp.getCp05());

		// 长分布4

		sjsave.setCp10(zp.getCp10());

		// 长分布5

		sjsave.setCp15(zp.getCp15());

		// 耳波op高度

		sjsave.setBopg(zp.getBopg());

		// 耳波op间隔

		sjsave.setBopj(zp.getBopj());

		// 耳波Dr高度

		sjsave.setBdrg(zp.getBdrg());

		// 耳波Dr间隔

		sjsave.setBdrj(zp.getBdrj());

		// 耳波等级
		sjsave.setBdji(zp.getBdji());
		// 直角Op

		sjsave.setZopz(zp.getZopz());

		// 直角Dr

		sjsave.setZdrz(zp.getZdrz());

		// L反

		sjsave.setZndz(zp.getZndz());

		// sjsave.setZndz(zp.getZndz());

		// C反

		sjsave.setHndz(zp.getHndz());

		// sjsave.setHndz(zp.getHndz());

		// 中伸高度

		sjsave.setZbog(zp.getZbog());

		// 中伸间隔
		if (zp.getZboj() != null) {
			sjsave.setZboj(zp.getZboj());
		}

		// 翘度

		sjsave.setQduz(zp.getQduz());

		// 毛边上
		sjsave.setMaos(zp.getMaos());
		// 毛边下
		sjsave.setMaox(zp.getMaox());
		// 修边毛边DR
		sjsave.setMadr(zp.getMadr());
		// 修边毛边OP
		sjsave.setMaop(zp.getMaop());
		// 矢切量
		sjsave.setSiql(zp.getSiql());
		// 镜面检查
		sjsave.setJmjc(zp.getJmjc());
		// 垫足确认
		sjsave.setDmqr(zp.getDmqr());
		// 针孔确认
		sjsave.setZkqr(zp.getZkqr());
		// 删除标志
		sjsave.setScbj(zp.getScbj());
		// 确认标志
		sjsave.setQdbj(zp.getQdbj());
		// 特记
		sjsave.setVari(zp.getVari());
		return new Result(sjsave);
	}

	@Override
	public void sjdz(ZpngTp dzzp, User user) {
		if (dzzp == null || dzzp.getJbno() == null || dzzp.getJbno().isEmpty()) {
			throw new CocoException(-1, "异常：无法获取订正界面的制品信息");
		}
		// 生产线别
		Scxbpz scx = scxbBo.getByDept(user.getDeptId());
		if (scx == null) {
			throw new CocoException(-1, "异常：实绩订正时出错——无法获取当前担当者的生产线别");
		}
		// 取原制品
		ZpngTp zp = zpBo.getZp(dzzp.getJbno());
		if (zp == null) {
			throw new CocoException(-1, "异常：取制品信息为空");
		}
		String oldchan = zp.getChan();
		// 取入端制品
		ZpngTp rcZp = zpBo.getZp(zp.getRczpno());
		if (rcZp == null) {
			throw new CocoException(-1, "异常：取入侧制品信息为空");
		}
		// 取指示书
		ZsdhTp zss = zsBo.getZsdhTp(rcZp.getZsno());
		if (zss == null) {
			throw new CocoException(-1, "异常：取指示书信息为空");
		}
		// 界面实绩检查
		// checkSjlr(dzzp, zss);
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zss.getDhno()));
		if (dhuo == null) {
			throw new CocoException(-1, "异常：取订货合同为空");
		}
		Date date = new Date();
		// 原制品重量
		Integer yzpzl = zp.getZpzl();
		// 解析订正制品信息
		parseDzzp(zp, dzzp, scx, zss, date);
		// 订正后的制品重量
		Integer zpzl = zp.getZpzl();
		if (DC.F.name.equals(zp.getDuic())) {
			// SL合格量
			double slhg = dhuo.getSlhg() != null ? dhuo.getSlhg() : 0;
			// 等级订正
			int flag = SinoUtils.compare(ChanType.get(zp.getChan()), ChanType.get(dzzp.getChan()));
			switch (flag) {
			// 合格转非合格（降级）
			case -1:
				slhg = slhg - yzpzl * 0.001;
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
				break;
			// 非合格转合格
			case 1:
				slhg = slhg + zpzl * 0.001;
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
				break;
			// 合格品转为合格品
			case 2:
				slhg = slhg - yzpzl * 0.001 + zpzl * 0.001;
				dhuo.setSlhg(NumberUtils.format(slhg, 3));
				break;
			// 非合格品转为非合格品
			case -2:
				// 其他
			default:
				break;
			}
		}
		/*
		 * 更新订货
		 */
		dhBo.update(dhuo);

		// 如果现品的产量不为1或9时，要对其做余材化操作（张良加）
		ychBO.doScYch(zp, oldchan, dhuo, date);
		/*
		 * 更新订正的制品
		 */
		zpBo.update(zp);

		/*
		 * 保存公共日志：现品情报共通格式/指示情报共通格式
		 */
		SjVO rzVo = sjBo.saveForZp(zp.getJbno(), zss.getZsno(), ZtConstants.ZPNG_CLQ_MODY);
		/*
		 * 保存SL实绩日志
		 */
		saveSlsjrz(zp, null, rzVo.getXpfmt(), rzVo.getZsfmt(), ESlRzzt.LR, date);
	}

	/**
	 * <p>
	 * 解析订正制品信息【将订正界面的数据替换到原制品信息】
	 * </p>
	 * 
	 * @param yzp
	 *            原制品
	 * @param zp
	 *            订正界面的制品
	 */
	private void parseDzzp(ZpngTp zp, ZpngTp dzzp, Scxbpz scx, ZsdhTp zss,
			Date date) {

		// 边波纹等级[耳波] BDJI_
		zp.setBdji(dzzp.getBdji());
		// 边波纹DR.高度 BDRG_
		zp.setBdrg(dzzp.getBdrg());
		// 边波纹DR.间隔 BDRJ_
		zp.setBdrj(dzzp.getBdrj());
		// 边波纹OP.高度 BOPG_
		zp.setBopg(dzzp.getBopg());
		// 边波纹OP.间隔 BOPJ_
		zp.setBopj(dzzp.getBopj());
		// 产量 CHAN_
		zp.setChan(dzzp.getChan());
		// 长度分布(-0.5) CM05_
		zp.setCm05(dzzp.getCm05());
		// 长度分布(+-0) CP00_
		zp.setCp00(dzzp.getCp00());
		// 长度分布(+0.5) CP05_
		zp.setCp05(dzzp.getCp05());
		// 长度分布(+1.0) CP10_
		zp.setCp10(dzzp.getCp10());
		// 长度分布(+1.5) CP15_
		zp.setCp15(dzzp.getCp15());
		// 采取Piler CQPL_
		zp.setCqpl(dzzp.getCqpl());
		// 处置代码 CZDM_
		zp.setCzdm(dzzp.getCzdm());
		// 等级 DENG_
		zp.setDeng(dzzp.getDeng());
		// D-Mark标记 DMRK_
		zp.setDmrk(dzzp.getDmrk());
		// 堆场
		ECzdm cz = ECzdm.get(zp.getCzdm());
		if (cz != null) {
			zp.setDuic(DC.E.name);
		}
		else {
			zp.setDuic(DC.F.name);
		}
		// 卷板长 JBCN_
		// 实测剪断长 JDCN_
		zp.setJdcn(dzzp.getJdcn());
		// 检定员 JDYN_
		zp.setJdyn(dzzp.getJdyn());

		// zp.setMazl(zp.getJszl());
		// Pile区分 PLQF_
		if (cz != null) {
			zp.setPlqf(scx.getUnqualified());
		}
		else {
			zp.setPlqf(scx.getQualified());
		}
		// 翘度值 QDUZ_
		zp.setQduz(dzzp.getQduz());
		// 缺陷代码 QQDM_
		zp.setQqdm(dzzp.getQqdm());
		// 缺陷代码 2
		zp.setQqd2(dzzp.getQqd2());
		// 缺陷代码3
		zp.setQqd3(dzzp.getQqd3());
		// 实测宽 SCKN_
		zp.setSckn(dzzp.getSckn());
		// SL流水线代码（实绩） SLIN_
		zp.setSlin(scx.getCode());
		// SL实绩登录年月日 SLSD_
		// zp.setSlsd(new Date());
		// 实绩品种等级 SPDJ_
		zp.setSpdj(cmnBo.getSjpzdj(ChanType.get(dzzp.getChan()), dzzp.getDeng()));
		// 更新时间 UPDA_
		zp.setUpda(date);
		// 现品状况 XPZK_
		zp.setXpzk(EXpzk.BZP_KEY);
		// 计数员 JSYN_
		zp.setYsuo(dzzp.getYsuo());
		// 流水线速度 LNSD_
		zp.setLnsd(dzzp.getLnsd());
		// 直角度Dr.值 ZDRZ_
		zp.setZdrz(dzzp.getZdrz());
		// L反
		zp.setZndz(dzzp.getZndz());
		// C反
		zp.setHndz(dzzp.getHndz());
		// 直角度Op.值 ZOPZ_
		zp.setZopz(dzzp.getZopz());
		// PILER
		zp.setCqpl(dzzp.getCqpl());
		// 张数（包装单位） ZSHU_
		zp.setZshu(dzzp.getZshu());
		// 缺陷2 QQD2_
		zp.setQqd2(dzzp.getQqd2());
		// 缺陷3 QQD3_
		zp.setQqd3(dzzp.getQqd3());
		// 失切量 SIQL_
		zp.setSiql(dzzp.getSiql());
		// 镜面检查测试 JMJC_
		zp.setJmjc(dzzp.getJmjc());
		// 毛边上 MAOS_
		zp.setMaos(dzzp.getMaos());
		// 毛边下 MAOX_
		zp.setMaox(dzzp.getMaox());
		// 修边毛边DR
		zp.setMadr(dzzp.getMadr());
		// 修边毛边OP
		zp.setMaop(dzzp.getMaop());
		// 垫木方向确认 DMQR_
		zp.setDmqr(dzzp.getDmqr());
		// 针孔确认 ZKQR_
		zp.setZkqr(dzzp.getZkqr());
		// 是否紧急（捆包发货） SFJJ_
		zp.setSfjj(dzzp.getSfjj());
		// 中波纹高度 ZBOG_
		zp.setZbog(dzzp.getZbog());
		// 中波纹间隔 ZBOJ_
		zp.setZboj(dzzp.getZboj());
		// 端板标记 DBBJ_
		zp.setDbbj(dzzp.getDbbj());
		// 特记
		zp.setVari(dzzp.getVari());
		// 库位 KW_
		zp.setKw(zp.getDuic());
		// 组别 ZU_
		zp.setZu(dzzp.getZu());
		// 捆包形式 KBAO_
		if (ChanType.hg.key.equals(zp.getChan())
				|| ChanType.bl.key.equals(zp.getChan())) {
			zp.setKbao(zss.getKbao());
		}
		else {
			Double kuan = 0.0;
			if (zp.getKuan() != null) {
				kuan = zp.getKuan();
			}
			Double cang = 0.0;
			if (zp.getCang() != null) {
				cang = zp.getCang();
			}
			if (kuan.doubleValue() > 850 || cang > 850) {
				zp.setKbao("B31");
			}
			else {
				zp.setKbao("B21");
			}
		}

		// 实际重量
		zp.setSjzl(dzzp.getSjzl());
		// 计算重量 JSZL_
		if (zp.getBdan() != null) {
			zp.setJszl(NumberUtils.formatDouToInt((dzzp.getZshu() * zp.getBdan()), 0, BigDecimal.ROUND_HALF_UP));
		}
		// 净重量 JNZL_
		zp.setJnzl(SinoUtils.fetchJnzl(zp.getJhnr(), zp.getSjzl(), zp.getJszl(), zp.getChan()));
		// 毛重量 MAZL_
		zp.setMazl(SinoUtils.fetchMazl(zp.getJhnr(), zp.getJnzl(), zp.getBzcl(), zp.getDmzl()));
		// 制品重量 ZPZL_
		zp.setZpzl(SinoUtils.fetchZpzl(zp));
	}

	@Override
	public void query(QEntity<SlsjVO> page) {
		dao.query(page);
	}

	@Override
	public ZpngTp view(String jbno) {
		return zpBo.getZp(jbno);
	}

	@Override
	public void delete(String jbno, User user) {
		if (jbno == null || jbno.isEmpty()) {
			throw new CocoException(-1, "异常：获取板材号为空");
		}
		if (user == null) {
			throw new CocoException(-1, "异常：当前担当者没有登录");
		}
		// 取制品信息
		ZpngTp zp = zpBo.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "异常：无法获取制品信息");
		}
		// 制品删除检查
		zpCheck(zp, user);
		Date date = new Date();
		/*
		 * 更新订货DB（前提：删除的制品为合格品）
		 */
		if (DC.F.name.equals(zp.getDuic())) {
			ChanType cl = ChanType.get(zp.getChan());
			if (cl != null && (ChanType.hg == cl || ChanType.bl == cl)) {
				// 取订货合同
				DhuoTp dh = dhBo.getRef(SinoUtils.parseDhuoPk(zp.getDhno()));
				if (dh != null) {
					// throw new CocoException(-1, "异常：订货合同不存在");
					double slhg = dh.getSlhg() == null ? 0.0
							: dh.getSlhg().doubleValue();
					int zpzl = zp.getZpzl();
					dh.setSlhg(NumberUtils.format(slhg - zpzl * 0.001, 3));
				}

				// dhBo.update(dh);
			}
		}
		/*
		 * 如果删除的制品为入端卷所生成的最后一个卷时(全部删除制品)，更新如下数据库
		 */
		ZpngTp rczp = zpBo.getRef(zp.getRczpno());
		ZsdhTp zss = zsBo.getRef(rczp.getZsno());
		if (zpBo.listZpByRc(rczp.getJbno()).size() == 1) {
			/*
			 * 更新原入侧制品状态
			 */
			rczp.setStat(ZpStat.CS.stat);
			rczp.setQdbj(ZtConstants.ZPNG_QDBJ_CS);
			rczp.setScbj(EScbj.CS.key);
			rczp.setUpda(date);
			rczp.setJdss(ZtConstants.ZPNG_JD_SLSJ_WSJ);
			rczp.setSjsj(null);
			// zpBo.save(rczp);

			/*
			 * 更新订货指示[指示书]
			 */
			boolean flag = false;
			List<ZpngTp> listZp = zpBo.listZpByZs(rczp.getZsno());
			if (listZp.size() > 0) {
				for (ZpngTp z : listZp) {
					if (z == null) {
						continue;
					}
					// 记录指示书中的卷是否存在有已实绩的卷
					if (z.getQdbj().equals(ZtConstants.ZPNG_JD_ETLSJ_YSJ)
							|| z.getQdbj().equals(ZtConstants.ZPNG_QDBJ_CS)) {
						flag = true;
					}
				}
			}
			/*
			 * 指示书中的所有卷均未实绩时，更新指示书状态
			 */

			if (flag) {

				// 是否实绩
				zss.setSfsj(ZtConstants.DHZS_SFSJ_WSJ);
				// 指示完了标记
				zss.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
				// 指示完了时间（年月日时分秒）
				zss.setZsny(null);
				// 装入中止标记
				zss.setZlzz(ZtConstants.DHZS_ZHRU_WZ);
				// 更新时间
				zss.setUpda(date);

			}
			/*
			 * 更新指示DB(装入卷板)的状态
			 */
			ZszrTp zszr = zsBo.getZszrTpRef(new ZszrTpPk(rczp.getZsno(),
					rczp.getJbno()));
			zszr.setStat(ZtConstants.ZSZR_STAT_WSCH);
			zszr.setUpda(date);
		}
		/*
		 * 删除日志
		 */
		sjBo.delete(new RiziLpPk(EKpType.SL.key, zp.getRczpno(), zp.getJbno(),
				zp.getJbno().substring(jbno.length() - 2)));
		/*
		 * 删除指示出端
		 */
		sjBo.deleteZscd(new ZscdTpPk(rczp.getZsno(), zp.getJbno()));
		/*
		 * 保存公共日志：现品情报共通格式/指示情报共通格式
		 */
		SjVO rzVo = sjBo.saveForZp(zp.getJbno(), rczp.getZsno(), ZtConstants.ZPNG_CLQ_DEL);
		/*
		 * 保存SL实绩日志
		 */
		saveSlsjrz(zp, null, rzVo.getXpfmt(), rzVo.getZsfmt(), ESlRzzt.LR, date);
		/*
		 * 删除制品
		 */
		zpBo.delete(zp.getJbno());
		// 更新垫木库存
		dmBO.updateDmkz(zss.getKuan(), zss.getCang(), zss.getDmfx(), zss.getKbao(), ZtConstants.MG_KW, zp.getSlin(), -1);

	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	private ISjBO sjBo;

	public ISjBO getSjBo() {
		return sjBo;
	}

	public void setSjBo(ISjBO sjBo) {
		this.sjBo = sjBo;
	}

	public IEtlBO getEtlBo() {
		return etlBo;
	}

	public void setEtlBo(IEtlBO etlBo) {
		this.etlBo = etlBo;
	}

	public ICmnBO getCmnBo() {
		return cmnBo;
	}

	public void setCmnBo(ICmnBO cmnBo) {
		this.cmnBo = cmnBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IZsBO getZsBo() {
		return zsBo;
	}

	public void setZsBo(IZsBO zsBo) {
		this.zsBo = zsBo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public ISlDAO getDao() {
		return dao;
	}

	public void setDao(ISlDAO dao) {
		this.dao = dao;
	}

	public IScxbBO getScxbBo() {
		return scxbBo;
	}

	public void setScxbBo(IScxbBO scxbBo) {
		this.scxbBo = scxbBo;
	}

	public ICzjlBO getCzjlBO() {
		return czjlBO;
	}

	public void setCzjlBO(ICzjlBO czjlBO) {
		this.czjlBO = czjlBO;
	}

	public IYchBO getYchBO() {
		return ychBO;
	}

	public void setYchBO(IYchBO ychBO) {
		this.ychBO = ychBO;
	}

	public IYdBO getYdBO() {
		return ydBO;
	}

	public void setYdBO(IYdBO ydBO) {
		this.ydBO = ydBO;
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
