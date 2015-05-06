package com.quanta.sino.sj.bo;

import java.io.Serializable;

import com.coco.core.exception.CocoException;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.Zsfmt;
import com.quanta.sino.sj.bo.api.ISjBO;
import com.quanta.sino.sj.dao.api.ISjDAO;
import com.quanta.sino.sj.vo.SjVO;
import com.quanta.sino.sl.constants.SlConstants;
import com.quanta.sino.sl.vo.SjzkVO;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 实绩日志公共格式业务实现
 * </p>
 * <p>
 * create: 2011-1-20 下午07:38:53
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SjBO implements ISjBO {

	private ISjDAO dao;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 订货业务接口
	 */
	private IDhBO dhBo;

	/**
	 * etl业务接口
	 */
	private IEtlBO etlBO;

	public IEtlBO getEtlBO() {
		return etlBO;
	}

	public void setEtlBO(IEtlBO etlBO) {
		this.etlBO = etlBO;
	}

	/**
	 * 指示书业务接口
	 */
	private IZsBO zsBo;

	@Override
	public void save(Zsfmt entity) {
		// 张红国改：不用判断
		// if (dao.getZs(entity.getZsn()) == null) {
		dao.save(entity);
		// }
	}

	@Override
	public void save(Xpfmt entity) {
		// 黄美改：Xpfmt主键换了，此判断需要修改或者去掉if语句（id是自增的不会存在重复）
		// 张红国改：不用判断
		// if (dao.getXp(entity.getId()) == null) {
		dao.save(entity);
		// }
	}

	@Override
	public SjVO saveForSlZpZZ(ZpngTp zp, ZsdhTp zss, SjzkVO sjvo, Integer yin,
			String clq) {
		SjVO vo = new SjVO();
		// 保存解析成实绩日志（指示情报共通格式）
		Zsfmt zsfmt = new Zsfmt();
		zsfmt.setZsn(zss.getZsno());
		// 装入卷板No/PileNo
		zsfmt.setZrj(zp.getRczpno());
		// 原板制品商代码
		zsfmt.setZzn(zp.getZzsd());
		// 生产线
		zsfmt.setEli(zp.getSlin());
		// 用户略称
		zsfmt.setAbb(zp.getAbbr());
		// 用户代码
		zsfmt.setUsr(zp.getUser());
		dao.save(zsfmt);
		// 保存解析成实绩日志（现品情报共通格式）
		Xpfmt xpfmt = new Xpfmt();
		// 状态中止
		if (SlConstants.SJ_ZZ.equals(sjvo.getZz())) {
			// 分割卷板号
			xpfmt.setJbn(zp.getJbno());
			// 修改区分
			// xpfmt.setClq(clq);
		}
		// 终了
		if (SlConstants.SJ_ZL.equals(sjvo.getZl())) {
			// 分割卷板号
			xpfmt.setJbn(zp.getRczpno());
		}
		// 硬度
		xpfmt.setYin(yin);
		dao.save(xpfmt);
		vo.setZsfmt(zsfmt);
		vo.setXpfmt(xpfmt);
		return vo;
	}

	@Override
	public SjVO saveForZpZZ(SjSaveVO sjVO, String clq) {
		SjVO vo = new SjVO();
		// 保存解析成实绩日志（指示情报共通格式）
		Zsfmt zsfmt = new Zsfmt();
		zsfmt.setZsn(sjVO.getZsno());
		// 装入卷板No/PileNo
		zsfmt.setZrj(sjVO.getZrjb());
		// 原板制品商代码
		zsfmt.setZzn(sjVO.getZzno());
		// 生产线
		zsfmt.setEli(sjVO.getElin());
		// 用户略称
		zsfmt.setAbb(sjVO.getAbbr());
		// 用户代码
		zsfmt.setUsr(sjVO.getUser());
		dao.save(zsfmt);

		// 保存解析成实绩日志（现品情报共通格式）
		Xpfmt xpfmt = new Xpfmt();
		// 状态中止
		if (sjVO.getStat1() == null && sjVO.getStat2() != null) {
			// 分割卷板号
			xpfmt.setJbn(sjVO.getJbno());
			// 计算重量
			xpfmt.setJsz(sjVO.getJszl());
			// 实际重量
			xpfmt.setSjz(sjVO.getSjzl());
			// 制品重量
			xpfmt.setZpz(sjVO.getZpzl());
			// 净重量
			xpfmt.setJnz(sjVO.getSjzl());
			// 卷板长
			xpfmt.setJbc(sjVO.getJbcn());
			// 制品长
			xpfmt.setJbc(sjVO.getJbcn());
			// 实绩附着量.正
			xpfmt.setScz(sjVO.getSczm());
			// 实绩附着量.反
			xpfmt.setScf(sjVO.getScfm());
			// 修改区分
			// xpfmt.setClq(clq);
		}
		// 状态终了
		if (sjVO.getStat1() != null && sjVO.getStat2() == null) {
			// 分割卷板号
			xpfmt.setJbn(sjVO.getZrjb());
			// 实绩附着量.正
			xpfmt.setScz(sjVO.getSczm());
			// 实绩附着量.反
			xpfmt.setScf(sjVO.getScfm());
		}
		dao.save(xpfmt);

		vo.setZsfmt(zsfmt);
		vo.setXpfmt(xpfmt);
		return vo;
	}

	@Override
	public SjVO saveForZp(String jbno, String clq) {
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "无法获取对应的制品信息");
		}
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpng.getDhno()));
		Zsfmt zsfmt = null;
		Xpfmt xpfmt = null;
		SjVO vo = new SjVO();
		ZsdhTp zsdh = zsBo.getZsdhTp(zpng.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "无法获取对应的指示信息");
		}
		if (zsfmt == null) {
			// 保存解析成实绩日志（指示情报共通格式）
			zsfmt = parseZsfmt(zpng, dhuo, zsdh);
			dao.save(zsfmt);
		}
		if (xpfmt == null) {
			// 保存解析成实绩日志（现品情报共通格式）
			xpfmt = parseXpfmt(zpng, clq);
			dao.save(xpfmt);
		}
		vo.setZsfmt(zsfmt);
		vo.setXpfmt(xpfmt);
		return vo;
	}

	@Override
	public SjVO saveForZp(String jbno, String zsno, String clq) {
		Zsfmt zsfmt = null;
		Xpfmt xpfmt = null;
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "无法获取对应的制品信息");
		}
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpng.getDhno()));
		SjVO vo = new SjVO();
		ZsdhTp zsdh = zsBo.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "无法获取对应的指示信息");
		}
		if (zsfmt == null) {
			// 保存解析成实绩日志（指示情报共通格式）
			zsfmt = parseZsfmt(zpng, dhuo, zsdh);
			dao.save(zsfmt);
		}
		if (xpfmt == null) {
			// 保存解析成实绩日志（现品情报共通格式）
			xpfmt = parseXpfmt(zpng, clq);
			dao.save(xpfmt);
		}
		vo.setZsfmt(zsfmt);
		vo.setXpfmt(xpfmt);
		return vo;
	}

	/**
	 * <p>
	 * 解析成实绩日志（指示情报共通格式）
	 * </p>
	 * 
	 * @param zpng
	 *            制品信息
	 * @param dhuo
	 *            订货信息
	 * @param zsdh
	 *            指示订货信息
	 * @return Zsfmt
	 */
	private Zsfmt parseZsfmt(ZpngTp zpng, DhuoTp dhuo, ZsdhTp zsdh) {
		Zsfmt zsfmt = new Zsfmt();
		// 指示书NO
		zsfmt.setZsn(zsdh.getZsno());
		// 操作MODE
		zsfmt.setCao(zsdh.getCaoz());
		// 装入宽
		zsfmt.setZrk(zpng.getZrkn());
		// 剪断长
		zsfmt.setJdc(zsdh.getJdcn());
		// 运用规格
		zsfmt.setYun(zsdh.getYuny());
		// 附着量(g/m2)
		zsfmt.setFgm(zsdh.getFugm());
		// 附着量-正面-下限值
		zsfmt.setFzx(zsdh.getFuzx());
		// 附着量-正面-上限值
		zsfmt.setFzs(zsdh.getFuzs());
		// 附着量-反面-下限值
		zsfmt.setFfx(zsdh.getFufx());
		// 附着量-反面-上限值
		zsfmt.setFfs(zsdh.getFufs());
		// REFLOW要否
		zsfmt.setFlo(zsdh.getFlow());
		// 订货表面精加工符号
		zsfmt.setFac(zsdh.getFace());
		// 合金层
		zsfmt.setHji(zsdh.getHjin());
		// K-PLATE洗涤强化
		zsfmt.setHdq(zpng.getPlat());
		// 化学处理方法
		zsfmt.setHxc(zsdh.getHxcl());
		// 目标涂油种类
		zsfmt.setYty(zsdh.getYtyp());
		// 目标涂油量
		zsfmt.setYqt(zsdh.getYqty());
		// 用户略称
		zsfmt.setAbb(zsdh.getAbbr());
		// 用户代码
		if (dhuo != null) {
			zsfmt.setUsr(dhuo.getUser());
		}

		// 订货NO行号
		zsfmt.setDhn(zsdh.getDhno());
		// 销售W否
		zsfmt.setXsw(zpng.getXswk());
		// 分配等级
		zsfmt.setFpd(zsdh.getFpdj());
		// 钢种类型
		zsfmt.setGzl(zsdh.getGzlx());
		// 直角度
		zsfmt.setJia(zsdh.getJiao());
		// m单重
		zsfmt.setMda(zsdh.getMdan());
		// 薄板单重
		zsfmt.setBda(zsdh.getBdan());
		// 112张重量
		zsfmt.setZ112(zsdh.getD112());
		// 交货区分
		zsfmt.setJhd(zsdh.getJhdd());
		// 内径代码
		zsfmt.setNjn(zsdh.getNjno());
		// 内径保护筒
		zsfmt.setNjb(zsdh.getNjbh());
		// 保护标记
		zsfmt.setPro(zsdh.getProt());
		// 锯齿可否
		zsfmt.setJck(zsdh.getJckf());
		// 锯齿种类
		zsfmt.setJcz(zsdh.getJczl());
		// 制品尺寸(W表示编辑完)
		zsfmt.setZpc(zsdh.getZpcc());
		// 订货规格
		zsfmt.setDhg(zsdh.getDhgg());
		// 订货附着量略称
		zsfmt.setDhf(zsdh.getDhfz());
		// 尺寸允许范围-厚%上限-符号
		// zsfmt.setHsf(zsdh.getHsfu());
		// 尺寸允许范围-厚%上限-值
		zsfmt.setHsz(zsdh.getHszi());
		// 尺寸允许范围-厚%下限-符号
		// zsfmt.setHxf(zsdh.getHxfu());
		// 尺寸允许范围-厚%下限-值
		zsfmt.setHxz(zsdh.getHxzi());
		// 尺寸允许范围-厚mm上限-符号
		// zsfmt.setMsf(zsdh.getMsfu());
		// 尺寸允许范围-厚mm上限-值
		zsfmt.setMsz(zsdh.getMszi());
		// 尺寸允许范围-厚mm下限-符号
		// zsfmt.setMxf(zsdh.getMxfu());
		// 尺寸允许范围-厚mm下限-值
		zsfmt.setMxz(zsdh.getMxzi());
		// 尺寸允许范围-宽mm上限-符号
		// zsfmt.setKsf(zsdh.getKsfu());
		// 尺寸允许范围-宽mm上限-值
		zsfmt.setKsz(zsdh.getKszi());
		// 尺寸允许范围-宽mm下限-符号
		// zsfmt.setKxf(zsdh.getKxfu());
		// 尺寸允许范围-宽mm下限-值
		zsfmt.setKxz(zsdh.getKxzi());
		// 尺寸允许范围-剪断长mm上限-符号
		// zsfmt.setCsf(zsdh.getCsfu());
		// 尺寸允许范围-剪断长mm上限-值
		zsfmt.setCsz(zsdh.getCszi());
		// 尺寸允许范围-剪断长mm下限-符号
		// zsfmt.setCxf(zsdh.getCxfu());
		// 尺寸允许范围-剪断长mm下限-值
		zsfmt.setCxz(zsdh.getCxzi());
		// 包装张数
		zsfmt.setBzz(zsdh.getBzzs());
		// 批重量
		zsfmt.setPiz(zsdh.getPizl());
		// 箱数
		zsfmt.setXsh(zsdh.getXshu());
		// B/B重量
		zsfmt.setBbz(zsdh.getBbz());
		// 零头可否
		zsfmt.setLtk(zsdh.getLtkf());
		// 零头下限
		zsfmt.setLtx(zsdh.getLtxx());
		// 重量计算补正系数
		zsfmt.setBzx(zsdh.getBzxs());
		// 垫木方向
		zsfmt.setDmf(zsdh.getDmfx());
		// 垫木重量
		zsfmt.setDmz(zsdh.getDmzl());
		// 包装材料重量(Kg)
		zsfmt.setBzc(zsdh.getBzcl());
		// 捆包指定重量-上限
		zsfmt.setKbs(zsdh.getKbzs());
		// 捆包指定重量-下限
		zsfmt.setKbx(zsdh.getKbzx());
		// 捆包形式
		zsfmt.setKba(zsdh.getKbao());
		// SPECIFICATION[规格]
		zsfmt.setSpe(zsdh.getSpec());
		// 用户名称
		zsfmt.setNam(zsdh.getName());
		// 附页KpNo.1标示流水线
		zsfmt.setKl1(zsdh.getKpn1Flag());
		// 附页KpNo.1
		zsfmt.setKn1(zsdh.getKpn1());
		// 附页KpNo.2标示流水线
		zsfmt.setKl2(zsdh.getKpn2Flag());
		// 附页KpNo.2
		zsfmt.setKn2(zsdh.getKpn2());
		// 附页KpNo.3标示流水线
		zsfmt.setKl3(zsdh.getKpn3Flag());
		// 附页KpNo.3
		zsfmt.setKn3(zsdh.getKpn3());
		// 附页KpNo.4标示流水线
		zsfmt.setKl4(zsdh.getKpn4Flag());
		// 附页KpNo.4
		zsfmt.setKn4(zsdh.getKpn4());
		// 制品尺寸-厚
		zsfmt.setHou(zsdh.getHouu());
		// 制品尺寸-宽
		zsfmt.setKua(zsdh.getKuan());
		// 制品尺寸-长
		zsfmt.setCan(zsdh.getCang());
		// 压延方向指定
		zsfmt.setYya(zsdh.getYyan());
		// 换算尺寸-厚
		zsfmt.setHsh(zsdh.getHngh());
		// 换算尺寸-宽
		zsfmt.setHsk(zsdh.getHngk());
		// 换算尺寸-长
		zsfmt.setHsc(zsdh.getHngc());
		// 订货区分
		zsfmt.setDhq(zsdh.getDhqf());
		// 品种代码
		zsfmt.setPzn(zsdh.getPzno());
		// 加工用途条件
		zsfmt.setCon(zsdh.getCond());
		// 规格代码
		zsfmt.setGgn(zsdh.getGgno());
		// 交货期
		zsfmt.setJhq(zsdh.getJhqi());
		// 采取指示量
		zsfmt.setCqz(zsdh.getCqzs());
		// 装入卷板No/PileNo
		zsfmt.setZrj(zpng.getRczpno());
		// 原板制造商No
		zsfmt.setZzn(zpng.getZzsd());
		// PILE区分
		zsfmt.setPlq(zpng.getPlqf());
		// 制品重量
		zsfmt.setZpz(zpng.getZpzl());
		// 卷板长(ZXFMT)
		zsfmt.setJbc(zpng.getJbcn());
		// 溶接个数-酸洗
		zsfmt.setRjs(zpng.getRjsx());
		// 溶接个数-冷延
		zsfmt.setRjl(zpng.getRjly());
		// 板厚有无不良
		zsfmt.setBhb(zpng.getBhbl());
		// 业连NO
		zsfmt.setYlno(zpng.getYlno());
		// 前工程作业年月日
		zsfmt.setQgc(zpng.getCrea());
		// 现品尺寸-厚
		zsfmt.setXph(zpng.getXpho());
		// 现品尺寸-宽
		zsfmt.setXpk(zpng.getXpkn());
		// 现品尺寸-长
		zsfmt.setXpc(zpng.getXpcn());
		// 生产线代码
		zsfmt.setEli(zpng.getElin());
		// 若被分配的制品的宽与当前订货DB的订货尺寸宽KUAN_不一样的话，
		// 则实绩日志（指示情报共通格式）剪边标记JBB_都应该设置为‘1’,指示情报共通表的剪边后宽JBK_＝订货DB的KUAN_
		if (dhuo != null && !zpng.getXpkn().equals(dhuo.getKuan())) {
			zsfmt.setJbb(ZtConstants.DHZS_JBBJ_JB);
			zsfmt.setJbk(dhuo.getKuan());
		}
		else {
			// 剪边标记
			zsfmt.setJbb(zsdh.getJbbj());
			// 剪边后宽
			zsfmt.setJbk(zsdh.getJbhk());
		}
		return zsfmt;
	}

	/**
	 * <p>
	 * 解析成实绩日志（现品情报共通格式）
	 * </p>
	 * 
	 * @param zpng
	 *            制品信息
	 * @return Xpfmt
	 */
	private Xpfmt parseXpfmt(ZpngTp zpng, String clq) {
		Xpfmt xpfmt = new Xpfmt();
		// 处理区分
		// xpfmt.setClq(zpng.getClqf());
		// 卷板NO/PILENO
		xpfmt.setJbn(zpng.getJbno());
		// 原板制造商NO
		xpfmt.setZzn(zpng.getZzsd());
		// PILE区分
		xpfmt.setPlq(zpng.getPlqf());
		// 采取Piler CQPL_
		xpfmt.setCqp(zpng.getCqpl());
		// 出口包装No
		xpfmt.setCkn(zpng.getCkno());
		// 计算重量
		xpfmt.setJsz(zpng.getJszl());
		// 实际重量
		xpfmt.setSjz(zpng.getSjzl());
		// 净重量
		xpfmt.setJnz(zpng.getJnzl());
		// 毛重量
		xpfmt.setMaz(zpng.getMazl());
		// 制品重量
		xpfmt.setZpz(zpng.getZpzl());
		// 现品尺寸-厚
		xpfmt.setXph(zpng.getHouu());
		// 现品尺寸-宽
		xpfmt.setXpk(zpng.getKuan());
		// 现品尺寸-长
		xpfmt.setXpc(zpng.getCang());
		// 卷板长
		xpfmt.setJbc(zpng.getJbcn());
		// 产量
		xpfmt.setCha(zpng.getChan());
		// 等级
		xpfmt.setDen(zpng.getDeng());
		// 处置代码
		xpfmt.setCzd(zpng.getCzdm());
		// 毛边下
		xpfmt.setMax(zpng.getMaox());
		// 毛边上
		xpfmt.setMas(zpng.getMaos());
		// 修边毛边OP
		xpfmt.setMaop(zpng.getMaop());
		// 修边毛边DR
		xpfmt.setMadr(zpng.getMadr());
		// 缺陷2
		xpfmt.setQq2(zpng.getQqd2());
		// 缺陷3
		xpfmt.setQq3(zpng.getQqd3());
		// 缺陷代码
		xpfmt.setQqd(zpng.getQqdm());
		// 实绩附着量-正面
		xpfmt.setScz(zpng.getSczm());
		// 实绩附着量-反面
		xpfmt.setScf(zpng.getScfm());
		// 硬度
		xpfmt.setYin(zpng.getYing());
		// 业连NO
		xpfmt.setYlno(zpng.getYlno());
		// 实绩品种等级
		xpfmt.setSpd(zpng.getSpdj());
		// 处理区分
		xpfmt.setClq(clq);
		return xpfmt;
	}

	@Override
	public ZscdTp getZscd(Serializable id) {
		return dao.getZscd(id);
	}

	@Override
	public void saveZscd(ZscdTp entity) {
		dao.saveZscd(entity);
	}

	@Override
	public void deleteZscd(Serializable id) {
		dao.deleteZscd(id);
	}

	/**
	 * @return the dao
	 */
	public ISjDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(ISjDAO dao) {
		this.dao = dao;
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

	@Override
	public void save(RiziLp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Zsfmt saveZsfmt(ZpngTp zpng, ZsdhTp zsdh) {
		// 取订货合同
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(zpng.getDhno()));
		Zsfmt zsfmt = this.parseZsfmt(zpng, dhuo, zsdh);
		this.save(zsfmt);
		return zsfmt;
	}

	@Override
	public Xpfmt saveXpfmt(ZpngTp zpng, String clq) {
		Xpfmt xpfmt = this.parseXpfmt(zpng, clq);
		dao.save(xpfmt);
		return xpfmt;
	}

}
