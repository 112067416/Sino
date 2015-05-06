package com.quanta.sino.zs.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code005;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Code200;
import com.quanta.sino.cmn.constants.CodeTyzl;
import com.quanta.sino.cmn.constants.Czlx;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.ECaoz;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.EYlType;
import com.quanta.sino.cmn.constants.EZsdm;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.Sfpp;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.QxbgTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZkfpCzjl;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZsdxTpPk;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.orm.ZszrTpPk;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.constants.ESlzssType;
import com.quanta.sino.zs.dao.api.IZsDAO;
import com.quanta.sino.zs.vo.JqcVO;
import com.quanta.sino.zs.vo.ZsVO;
import com.quanta.sino.zs.vo.ZsdhVO;
import com.quanta.sino.zs.vo.ZsdhVVO;
import com.quanta.sino.zs.vo.ZsdhViewVO;
import com.quanta.sino.zs.vo.ZsdxVO;
import com.quanta.sino.zs.vo.ZssXpVO;

/**
 * <p>
 * 指示书业务接口实现
 * </p>
 * <p>
 * create: 2010-12-22 上午10:23:38
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class ZsBO implements IZsBO {

	private IZsDAO dao;

	/**
	 * 序号访问接口
	 */
	private ISeqBO seqBo;

	/**
	 * 订货模块业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 代码业务层
	 */
	private ICodeBO codeBo;

	/**
	 * 原材业务接口
	 */
	private IYcaitpBO ycaiBo;

	/**
	 * 制品访问接口
	 */
	private IZpBO zpBo;

	/**
	 * 分配和余材操作记录业务接口
	 */
	private ICzjlBO czjlBO;

	/**
	 * 公共业务接口
	 */
	private ICmnBO cmnBo;

	@Override
	public void queryZsdx(QEntity<ZsdxVO> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryZsdhTp(QEntity<ZsdhTp> qentity) {
		dao.queryZsdhTp(qentity);
	}

	@Override
	public void queryDscZsdhTp(QEntity<ZsdhVO> qentity) {
		dao.queryDscZsdhTp(qentity);
	}

	@Override
	public void queryZsdxtp(QEntity<ZsdxTp> qentity) {
		dao.queryZsdxtp(qentity);
	}

	@Override
	public void updateZsdh(ZsdhTp zss) {
		dao.updateZsdh(zss);
	}

	@Override
	public void doQxfp(List<String> jbnos, String fpno) {
		dao.doQxfp(jbnos, fpno);
	}

	@Override
	public boolean isFpForDh(String dhno) {
		return dao.isFpForDh(dhno);
	}

	@Override
	public void updateZsdx(String fpno, Integer cqzs, Date upda) {
		dao.updateZsdx(fpno, cqzs, upda);
	}

	@Override
	public void saveZsdxTps(List<ZsdxTp> entities) {
		dao.saveZsdxTps(entities);
	}

	@Override
	public ZszrTp getZszrTpRef(Serializable id) {
		return dao.getZszrTpRef(id);
	}

	@Override
	public ZszrTp getUniqueZszrTp(String zrjb) {
		return dao.getUniqueZszrTp(zrjb);
	}

	@Override
	public boolean isExistedZszrTp(String zrjb) {
		return dao.isExistedZszrTp(zrjb);
	}

	private List<List<String[]>> parseNosSL(List<String> jbnos,
			List<String> nozsnos) {
		List<List<String[]>> zses = new ArrayList<List<String[]>>();
		List<String[]> items = null;
		String[] params;
		String[] lastParams = null;
		for (String jbno : jbnos) {
			params = jbno.split("#");
			if (params.length < 10) {
				continue;
			}
			// 停止标志不为空
			if (params.length > 10 && params[0] != null
					&& !params[10].isEmpty()) {
				nozsnos.add(params[0]);
				continue;
			}
			if (items == null
					|| items.size() == ZtConstants.MAX_COIL_IN_SL
					|| (lastParams != null && !(lastParams[2].equalsIgnoreCase(params[2])
							&& lastParams[3].equals(params[3])
							&& lastParams[4].equals(params[4])
							&& lastParams[5].equals(params[5])
							&& lastParams[8].equals(params[8]) && lastParams[9].equals(params[9])))) {
				items = new ArrayList<String[]>();
				zses.add(items);
			}
			items.add(params);
			lastParams = params;
		}
		return zses;
	}

	private List<List<String[]>> parseNos(List<String> jbnos,
			List<String> nozsnos) {
		List<List<String[]>> zses = new ArrayList<List<String[]>>();
		List<String[]> items = null;
		String[] params;
		String[] lastParams = null;
		for (String jbno : jbnos) {
			params = jbno.split("#");
			if (params.length < 8) {
				continue;
			}
			// 停止标志不为空
			if (params.length > 8 && params[8] != null && !params[8].isEmpty()) {
				nozsnos.add(params[0]);
				continue;
			}
			if (items == null
					|| items.size() == ZtConstants.MAX_COIL_IN_ETL
					|| (lastParams != null && !(lastParams[2].equalsIgnoreCase(params[2])
							&& lastParams[3].equals(params[3])
							&& lastParams[4].equals(params[4])
							&& lastParams[5].equals(params[5])
							&& lastParams[6].equals(params[6]) && lastParams[7].equals(params[7])))) {
				items = new ArrayList<String[]>();
				zses.add(items);
			}
			items.add(params);
			lastParams = params;
		}
		return zses;
	}

	@Override
	public List<String> zc(List<String> jbnos, List<String> nozsnos,
			boolean isPrint) {
		List<String> zsnos = new ArrayList<String>();

		List<List<String[]>> zses = parseNos(jbnos, nozsnos);
		if (zses.isEmpty()) {
			if (nozsnos.size() > 0) {
				throw new CocoException(-1, "选择的卷已经停止");
			}
			else {
				throw new CocoException(-1, "没有能生成指示书的卷");
			}

		}
		List<String> jbnoList;
		String dhno;
		for (List<String[]> items : zses) {
			jbnoList = new ArrayList<String>();
			dhno = null;
			for (String[] params : items) {
				// 拼接卷板，0元素-卷板No，1元素-分配NO-2元素剪边标志jbkb
				jbnoList.add(params[0] + "-" + params[1] + "-" + params[3]);
				if (dhno == null) {
					// 订货No.
					dhno = params[2];
				}
			}
			zsnos.add(doEtlZss(jbnoList, dhno, isPrint));
		}
		if (zsnos.isEmpty()) {
			throw new CocoException(-1, "不能生成任何指示书");
		}
		return zsnos;
	}

	/**
	 * <p>
	 * 更新指示对象DB，新增订货指示DB，更新原材卷DB, 新增指示DB(装入卷板)，新增卷板缺陷表保管DB
	 * </p>
	 * 
	 * @param jbnolist
	 *            卷板NO和分配号组合集合
	 * @param dhnoline
	 *            订货NO行号
	 * @param isPrint
	 *            打印标志
	 * @param zsnos
	 *            生成的指示号列表引用
	 * @return String 指示书号
	 */
	private String doEtlZss(List<String> jbnolist, String dhnoline,
			boolean isPrint) {
		// 根据订货NO和行号取订货DB详细信息
		DhuoTp dh = null;
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
		dh = dhBo.getRef(dhpk);
		if (dh == null) {
			throw new CocoException(-1, "订货DB的NO." + dhnoline + "不存在！");
		}
		// 更新订货DB的ETL指示量(吨)
		String[] arr;
		String zrjb;
		YcaiTp ycai;
		Double etlz = 0.0;
		Double xpkn = 0.0;
		Double xpho = 0.0;
		String jbkb = null;
		ZsdxTpPk zsdxTpPk = null;
		for (String jbno : jbnolist) {
			arr = jbno.split("-");
			zrjb = arr[0];
			if (arr[2] != null && !arr[2].isEmpty()) {
				jbkb = arr[2];
			}
			ycai = ycaiBo.get(zrjb);
			etlz = ycai.getZpzl() + etlz;
			xpkn = ycai.getXpkn();
			xpho = ycai.getXpho();

			// 更新指示对象DB
			zsdxTpPk = new ZsdxTpPk(arr[1], arr[0]);
			dao.updateStat(zsdxTpPk, ZtConstants.DHZS_STAT_YFP);
		}
		if (dh.getEtlz() == null) {
			dh.setEtlz(NumberUtils.format(etlz * 0.001, 3));
			dh.setUpda(new Date());
		}
		else {
			dh.setEtlz(NumberUtils.format(dh.getEtlz() + etlz * 0.001, 3));
			dh.setUpda(new Date());
		}
		// 新增订货指示DB
		String zsno = saveZSDHTP(jbnolist, dh, xpkn, xpho, jbkb, isPrint);
		// 更新原材卷DB
		updateYcaiTp(jbnolist, zsno, ZtConstants.ZPNG_JD_ETLZS_YZS);
		// 新增指示DB(装入卷板)
		saveZszrTP(jbnolist, zsno, dh);
		// 新增卷板缺陷表保管DB
		saveQxbgTP(jbnolist, zsno, dh);
		return zsno;
	}

	@Override
	public void updateZsdx(ZsdxTp entity) {
		dao.updateZsdx(entity);
	}

	@Override
	public void saveZsdX(ZsdxTp entity) {
		dao.saveZsdX(entity);
	}

	/**
	 * <p>
	 * 新增订货指示DB
	 * </p>
	 * 
	 * @param dhnoline
	 *            订货NO和行号
	 * @param isprint
	 *            打印标志
	 * @return String 指示书NO
	 */
	public String saveZSDHTP(List<String> jbnolist, DhuoTp dhVO, Double xpkn,
			Double xpho, String jbkb, boolean isPrint) {

		// 实例化订货指示DB实体。
		ZsdhTp zsdhVO = new ZsdhTp();
		// 取流水号
		String zsno = seqBo.sequence(Seq.dhzsno.name);
		if ((zsno == null) || (zsno.isEmpty())) {
			throw new CocoException(-1, "订货指示NO流水号为空！");
		}
		// 指示书No
		zsdhVO.setZsno(zsno);
		CodeItem code = null;
		String bzxs = "";
		double w = 0;
		if (dhVO != null) {

			// 用户略称
			zsdhVO.setAbbr(dhVO.getAbbr());

			// 包装材料重量
			zsdhVO.setBzcl(dhVO.getBzcl());
			ZlnrCode znr = ZlnrCode.get(dhVO.getJhnr());
			if (znr == null) {
				throw new CocoException(-1, "无效的重内代码！");
			}
			// 重量计算补正系数
			if (ZlnrCode.nr1 == znr || ZlnrCode.nr2 == znr
					|| ZlnrCode.nr3 == znr) {
				// 取码表的补正系数0.998
				code = codeBo.getCodeItem(CmnConstants.CODE_200, Code200.BZXS7.key);
				if (code != null) {
					bzxs = "0." + code.getValue().substring(1);
					zsdhVO.setBzxs(Double.parseDouble(bzxs));
				}
			}
			else {
				// 取码表的补正系数0.996
				code = codeBo.getCodeItem(CmnConstants.CODE_200, Code200.BZXS1.key);
				if (code != null) {
					bzxs = "0." + code.getValue().substring(1);
					zsdhVO.setBzxs(Double.parseDouble(bzxs));
				}
			}
			// 包装张数
			if (dhVO.getPzno() != null && !dhVO.getPzno().isEmpty()) {
				if (Code118.sheet.key.equals(dhVO.getPzno().substring(0, 1))
						|| Code118.scroll.key.equals(dhVO.getPzno().substring(0, 1))) {
					zsdhVO.setBzzs(Integer.parseInt(dhVO.getKbsz()));
				}
			}

			// 操作Mode
			if (dhVO.getPzno() != null && !dhVO.getPzno().isEmpty()) {
				if (Code118.coil.key.equals(dhVO.getPzno().substring(0, 1))) {
					zsdhVO.setCaoz(ZtConstants.DHZS_CAOZ_C);
				}
				else {
					zsdhVO.setCaoz(ZtConstants.DHZS_CAOZ_S);
				}
			}
			// 超量分配标记
			zsdhVO.setClfp(ZtConstants.DHZS_CLFP_WZL);
			// 加工用途条件
			zsdhVO.setCond(dhVO.getCond());
			// 采取指示量
			zsdhVO.setCqzs(0);
			// 作成时间（年月日时分秒）
			zsdhVO.setCrea(new Date());
			// 尺寸允许范围.剪断长mm上限.符号
			// zsdhVO.setCsfu(dhVO.getCsfu());
			// 尺寸允许范围.剪断长mm上限.值
			zsdhVO.setCszi(dhVO.getCszi());
			// 尺寸允许范围.剪断长mm下限.符号
			// zsdhVO.setCxfu(dhVO.getCsfu());
			// 尺寸允许范围.剪断长mm下限.值
			zsdhVO.setCxzi(dhVO.getCxzi());
			// 112张单重
			zsdhVO.setD112(NumberUtils.format(w * 112, 1).doubleValue());

			// 订货规格
			// zsdhVO.setDhgg(dhVO.getGgno());
			// 订货No.行号
			zsdhVO.setDhno(dhVO.getDhno() + dhVO.getLine());
			// 订货区分

			// 垫木方向
			zsdhVO.setDmfx(SinoUtils.getSkidFx(dhVO.getKuan(), dhVO.getCang(), dhVO.getYyan(), dhVO.getDmfx()));
			// 垫木重量
			zsdhVO.setDmzl(dhVO.getDmzl());
			// 订货表面精加工符号
			zsdhVO.setFace(dhVO.getFace());
			// reflow要否
			if (dhVO.getFace() != null && !dhVO.getFace().isEmpty()) {
				if (Code005.M.key.equals(dhVO.getFace())
						|| Code005.BN.key.equals(dhVO.getFace())) {
					zsdhVO.setFlow(ZtConstants.CODE_FLOW);
				}
			}
			// 分配等级
			zsdhVO.setFpdj(dhVO.getFpdj());
			// 附着量.单位
			zsdhVO.setFudw(dhVO.getFudw());
			// 附着量.反面(g/m2)
			zsdhVO.setFufm(cmnBo.parseFzl2Gm(dhVO.getFudw(), dhVO.getFufm()));
			// 附着量.反面.上限值
			zsdhVO.setFufs(dhVO.getFufs());
			// 附着量.反面.下限值
			zsdhVO.setFufx(dhVO.getFufx());
			// 附着量（g/m2)
			zsdhVO.setFugm(cmnBo.getFugm(dhVO.getFudw(), dhVO.getFuzm(), dhVO.getFufm(), dhVO.getChdx()));
			// 附着量.正面(g/m2)
			zsdhVO.setFuzm(cmnBo.parseFzl2Gm(dhVO.getFudw(), dhVO.getFuzm()));
			// 附着量.正面.上限值
			zsdhVO.setFuzs(dhVO.getFuzs());
			// 附着量.正面.下限值
			zsdhVO.setFuzx(dhVO.getFuzx());
			// 订货附着量略称
			zsdhVO.setDhfz(cmnBo.getDhfz(dhVO.getFudw(), dhVO.getFuzm(), dhVO.getFufm(), dhVO.getChdx()));
			// 规格代码
			zsdhVO.setGgno(dhVO.getGgno());
			// 钢种类型
			if (dhVO.getGgno() != null && !dhVO.getGgno().isEmpty()) {
				code = codeBo.getCodeItem(CmnConstants.CODE_109, dhVO.getGgno().substring(0, 1));
			}

			if (code != null) {
				zsdhVO.setGzlx(code.getValue());
			}
			// 合金层
			zsdhVO.setHjin(dhVO.getHjin());
			// 换算尺寸.长
			zsdhVO.setHngc(dhVO.getHngc());
			// 换算尺寸.厚
			zsdhVO.setHngh(dhVO.getHngh());
			// 换算尺寸.宽
			zsdhVO.setHngk(dhVO.getHngk());
			// 制品尺寸.长
			zsdhVO.setCang(dhVO.getCang());
			// 制品尺寸.厚
			zsdhVO.setHouu(dhVO.getHouu());
			// 制品尺寸.宽
			zsdhVO.setKuan(dhVO.getKuan());
			// 尺寸允许范围.厚%上限.符号
			// zsdhVO.setHsfu(dhVO.getHsfu());
			// 尺寸允许范围.厚%上限.值
			zsdhVO.setHszi(dhVO.getHszi());
			// 尺寸允许范围.厚%下限.符号
			// zsdhVO.setHxfu(dhVO.getHxfu());
			// 尺寸允许范围.厚%下限.值
			zsdhVO.setHxzi(dhVO.getHxzi());
			// 化学处理方法
			zsdhVO.setHxcl(dhVO.getHuac());
			// 剪边标记
			zsdhVO.setJbbj(jbkb);
			// 剪边后宽
			if (zsdhVO.getJbbj() != null && !zsdhVO.getJbbj().isEmpty()) {
				if (ZtConstants.DHZS_JBBJ_JB.equals(zsdhVO.getJbbj())) {
					zsdhVO.setJbhk(zsdhVO.getHngk());
				}
			}
			// 锯齿种类(锯齿形式)
			zsdhVO.setJczl(dhVO.getJcxs());
			// 锯齿可否
			if ((dhVO.getJcxs() != null) && (!dhVO.getJcxs().isEmpty())) {
				zsdhVO.setJckf(ZtConstants.DHZS_JCKF_YJC);
			}
			// 剪切长
			if (dhVO.getPzno() != null && !dhVO.getPzno().isEmpty()) {
				if (Code118.sheet.key.equals(dhVO.getPzno().substring(0, 1))
						|| Code118.scroll.key.equals(dhVO.getPzno().substring(0, 1))) {
					zsdhVO.setJdcn(dhVO.getHngc());

				}
			}
			// 交货地点(交货区分)
			if (ZlnrCode.nr1 == znr || ZlnrCode.nr2 == znr
					|| ZlnrCode.nr3 == znr) {
				zsdhVO.setJhdd(ZtConstants.DHZS_Jhdd_1);
			}
			else {
				zsdhVO.setJhdd(ZtConstants.DHZS_Jhdd_2);
			}
			// 交货期
			zsdhVO.setJhqi(dhVO.getJhqi());
			// 直角度
			zsdhVO.setJiao(dhVO.getJiao());
			// 捆包形式
			zsdhVO.setKbao(dhVO.getKbao());
			// 捆包指定重量.上限
			zsdhVO.setKbzs(dhVO.getKbzs());
			// 捆包指定重量.下限
			zsdhVO.setKbzx(dhVO.getKbzx());
			// 附页KpNo1.标示流水线
			zsdhVO.setKpn1Flag(dhVO.getKpn1Flag());
			// 附页KpNo1.
			zsdhVO.setKpn1(dhVO.getKpn1());
			// 附页KpNo2.标示流水线
			zsdhVO.setKpn2Flag(dhVO.getKpn2Flag());
			// 附页KpNo2.
			zsdhVO.setKpn2(dhVO.getKpn2());
			// 附页KpNo3.标示流水线
			zsdhVO.setKpn3Flag(dhVO.getKpn3Flag());
			// 附页KpNo3
			zsdhVO.setKpn3(dhVO.getKpn3());
			// 附页KpNo4.标示流水线
			zsdhVO.setKpn4Flag(dhVO.getKpn4Flag());
			// 附页KpNo4.
			zsdhVO.setKpn4(dhVO.getKpn4());
			// 尺寸允许范围.宽mm上限.符号
			// zsdhVO.setKsfu(dhVO.getKsfu());
			// 尺寸允许范围.宽mm上限.值
			zsdhVO.setKszi(dhVO.getKszi());

			// 尺寸允许范围.宽mm下限.符号
			// zsdhVO.setKxfu(dhVO.getKxfu());
			// 尺寸允许范围.宽mm下限.值
			zsdhVO.setKxzi(dhVO.getKxzi());
			// 零头可否(零头下限.单位)
			zsdhVO.setLtkf(dhVO.getLtdw());
			// 零头下限
			zsdhVO.setLtxx(dhVO.getLtzi());
			// m单重
			zsdhVO.setMdan(SinoUtils.calculateMdan(xpho, xpkn));
			// 薄板单重
			zsdhVO.setBdan(SinoUtils.calculateBdan(dhVO.getHouu(), dhVO.getKuan(), dhVO.getCang()));
			// 尺寸允许范围.厚mm上限.符号
			// zsdhVO.setMsfu(dhVO.getHsfu());
			// 尺寸允许范围.厚mm上限.值
			double hszi = dhVO.getHszi() == null ? 0.0
					: dhVO.getHszi().doubleValue();
			zsdhVO.setMszi(NumberUtils.format(dhVO.getHngh() * hszi / 100, 3));
			// 尺寸允许范围.厚mm下限.符号
			// zsdhVO.setMxfu(dhVO.getHxfu());
			// 尺寸允许范围.厚mm下限.值
			double hxzi = dhVO.getHxzi() == null ? 0.0
					: dhVO.getHxzi().doubleValue();
			zsdhVO.setMxzi(NumberUtils.format(dhVO.getHngh() * hxzi / 100, 3));
			// 用户名称
			zsdhVO.setName(dhVO.getName());
			// 内径保护筒
			zsdhVO.setNjbh(dhVO.getNjbh());
			// 内径代码
			if (dhVO.getNeij() != null && !dhVO.getNeij().isEmpty()) {
				code = codeBo.getUniqueItemByValue(CmnConstants.CODE_020, dhVO.getNeij());
				if (code != null) {
					zsdhVO.setNjno(code.getKey());
				}
			}

			// 批重量
			if (dhVO.getPzno() != null && !dhVO.getPzno().isEmpty()) {
				if (Code118.sheet.key.equals(dhVO.getPzno().substring(0, 1))
						|| Code118.scroll.key.equals(dhVO.getPzno().substring(0, 1))) {
					zsdhVO.setPizl(NumberUtils.format(zsdhVO.getBdan()
							* zsdhVO.getBzzs(), 3));
				}
			}
			// 保护标记
			zsdhVO.setProt(dhVO.getProt());
			// 品种代码
			zsdhVO.setPzno(dhVO.getPzno());
			// 强制标记
			zsdhVO.setQzbj(getQzbj(jbnolist));
			// Specification规格略称
			zsdhVO.setSpec(dhVO.getGgnm());
			// 状态设为0，未分派
			zsdhVO.setStat(ZtConstants.DHZS_STAT_WFP);
			// 更新时间（年月日时分秒）,原字段TOUPDA
			zsdhVO.setUpda(new Date());
			// K-Plate洗涤强化
			zsdhVO.setXdqh(dhVO.getPlat());
			// 箱数

			// 销售W否
			zsdhVO.setXswf(dhVO.getRjie());
			// 余材状况
			zsdhVO.setYczk(EXpzk.SC_KEY);
			// 目标涂油量
			zsdhVO.setYqty(dhVO.getYqty());
			// 目标涂油种类
			CodeTyzl codeTyzl = CodeTyzl.get(dhVO.getYtyp());
			if (codeTyzl != null) {
				zsdhVO.setYtyp(codeTyzl.name);
			}
			// 运用规格
			zsdhVO.setYuny(dhVO.getYuny());
			// 压延方向指定
			zsdhVO.setYyan(dhVO.getYyan());
			// 制品尺寸（W表示编辑完）
			zsdhVO.setZpcc(SinoUtils.formatProductSize(dhVO.getHouu(), dhVO.getKuan(), dhVO.getCang()));
			// 制品尺寸（W表示编辑完）
			// if (dhVO.getPzno() != null) {
			// if (Code118.sheet.key.equals(dhVO.getPzno().substring(0, 1))
			// || Code118.scroll.key.equals(dhVO.getPzno().substring(0, 1))) {
			// // 短边压压延方向
			// if (dhVO.getYyan() != null) {
			// if (EYy.YY_1.key.equals(dhVO.getYyan())) {
			// if (dhVO.getKuan() > dhVO.getCang()) {
			// // 宽>长
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "W*"
			// + dhVO.getCang());zs
			// }
			// else if (dhVO.getCang() > dhVO.getKuan()) {
			// // 长>宽
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "*" + dhVO.getCang()
			// + "W");
			// }
			// else {
			// // 其它
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "*" + dhVO.getCang());
			// }
			// }
			// }
			// // 长边压压延方向
			// if (dhVO.getYyan() != null) {
			// if (EYy.YY_2.key.equals(dhVO.getYyan())) {
			// if (dhVO.getKuan() > dhVO.getCang()) {
			// // 宽>长
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "*" + dhVO.getCang()
			// + "W");
			// }
			// else if (dhVO.getCang() > dhVO.getKuan()) {
			// // 长>宽
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "W*"
			// + dhVO.getCang());
			// }
			// else {
			// // 其它
			// zsdhVO.setZpcc(dhVO.getHouu() + "*"
			// + dhVO.getKuan() + "*" + dhVO.getCang());
			// }
			// }
			// }
			//
			// }
			// else if (Code118.coil.key.equals(dhVO.getPzno().substring(0, 1)))
			// {
			// zsdhVO.setZpcc(dhVO.getHouu() + "*" + dhVO.getKuan() + "*"
			// + "COIL");
			// }
			// }
			// 装入宽
			zsdhVO.setZrkn(xpkn);
			// 指示完了标记
			zsdhVO.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
			// 指示书发行完标记
			if (isPrint) {
				zsdhVO.setZsfx(ZtConstants.DHZS_ZSFX_YDY);
			}
			else {
				zsdhVO.setZsfx(ZtConstants.DHZS_ZSFX_WDY);
			}
			// 指示书缺陷打印标志为0
			zsdhVO.setZsqx(ZtConstants.DHZS_ZSFX_WDY);
			zsdhVO.setSfdm(ZtConstants.DHZS_ZSFX_WDY);

		}

		saveZsdh(zsdhVO);
		return zsno;
	}

	@Override
	public void updateZsPrint(List<String> zss) {
		dao.updateZsPrint(zss);
	}

	@Override
	public void updateZsQxPrint(List<String> zss) {
		dao.updateZsQxPrint(zss);
	}

	/**
	 * <p>
	 * 更新原材卷板
	 * </p>
	 * 
	 * @param jbnolist
	 * @param zsno
	 */
	public void updateYcaiTp(List<String> jbnolist, String zsno, String jdez) {
		String[] arr;
		for (String jbno : jbnolist) {
			arr = jbno.split("-");
			dao.updateYcaiTp(arr[0], zsno, jdez);
		}
	}

	/**
	 * <p>
	 * 取指示对象DB的强制标记
	 * </p>
	 * 
	 * @param jbnolist
	 * @return String
	 */
	public String getQzbj(List<String> jbnolist) {

		String[] arr;
		ZsdxTp entity;
		String relqzbj = "0";
		for (String jbno : jbnolist) {
			arr = jbno.split("-");
			ZsdxTpPk idpk = new ZsdxTpPk(arr[1], arr[0]);
			entity = dao.get(idpk);
			if (entity != null) {
				if ((entity.getQzbj() != null) && (!entity.getQzbj().isEmpty())) {
					if (entity.getQzbj().equals("1")) {
						relqzbj = entity.getQzbj();
					}

				}

			}
		}

		return relqzbj;
	}

	/**
	 * <p>
	 * 取指示对象DB的剪边标志
	 * </p>
	 * 
	 * @param jbnolist
	 * @return String
	 */
	public String getJbkb(List<String> jbnolist) {

		String[] arr;
		ZsdxTp entity;
		String reljbkb = "0";
		for (String jbno : jbnolist) {
			arr = jbno.split("-");
			ZsdxTpPk idpk = new ZsdxTpPk(arr[1], arr[0]);
			entity = dao.get(idpk);
			if (entity != null) {
				if ((entity.getJbkb() != null) && (!entity.getJbkb().isEmpty())) {
					if (entity.getJbkb().equals("1")) {
						reljbkb = entity.getJbkb();
					}

				}

			}
		}

		return reljbkb;
	}

	/**
	 * <p>
	 * 新增指示DB(装入卷板)
	 * </p>
	 * 
	 * @param jbnolist
	 *            卷板No和分配号
	 * @param zsno
	 *            指示书号
	 * @param dhnoline
	 *            订货NO
	 */
	public void saveZszrTP(List<String> jbnolist, String zsno, DhuoTp dhVO) {

		ZszrTp entity;
		YcaiTp ycai = new YcaiTp();
		List<ZszrTp> list = new ArrayList<ZszrTp>();
		String[] arr;
		String zrjb;
		ZszrTp zszr = null;
		String zrzsno = null;
		Date crea = new Date();
		for (String jbno : jbnolist) {
			entity = new ZszrTp();
			arr = jbno.split("-");
			zrjb = arr[0];
			// 检查jbno是否已经做成指示书
			zszr = dao.getUniqueZszrTp(zrjb);
			if (zszr != null) {
				zrzsno = zszr.getZsno();
				if (zrzsno != null) {
					if (EZsdm.ETL.key.equals(zrzsno.substring(0, 1))) {
						throw new CocoException(-1, "错误:Coil NO:" + zrjb
								+ "已经作成指示书,不能再生成！");
					}
				}
			}
			// 取原材卷板
			ycai = ycaiBo.get(zrjb);
			// 装入卷板No/PileNo
			entity.setZrjb(zrjb);
			// 指示书No
			entity.setZsno(zsno);
			// 作成时间
			entity.setCrea(crea);
			// 等级(现品)
			entity.setDeng(ycai.getDeng());
			// 表面精加工符号(现品)
			entity.setFace(ycai.getFace());
			// 卷板长
			entity.setJbcn(ycai.getJbcn());
			// 前工程作业年月日
			entity.setQgcn(ycai.getDuib());
			// 溶接个数.冷延
			entity.setRjly(ycai.getRjly());
			// 溶接个数.酸洗
			entity.setRjsx(ycai.getRjsx());
			// 状态
			entity.setStat(ZtConstants.ZSDH_STAT_WZS);
			// 更新时间
			entity.setUpda(crea);
			// 现品尺寸.厚
			entity.setXpho(ycai.getXpho());
			// 现品尺寸.宽
			entity.setXpkn(ycai.getXpkn());
			// 业连No
			entity.setYlno(ycai.getYlno());
			if (dhVO != null) {
				// 涂油量(现品)
				entity.setYqty(dhVO.getYqty());
				// 涂油种类(现品)
				CodeTyzl codeTyzl = CodeTyzl.get(dhVO.getYtyp());
				if (codeTyzl != null) {
					entity.setYtyp(codeTyzl.name);
				}

			}
			// 运用规格(现品)
			entity.setYuny(ycai.getYuny());
			// 制品重量
			entity.setZpzl(ycai.getZpzl());
			// 原板制造商No
			entity.setZzno(ycai.getZzsd());

			// 实体加到集合
			list.add(entity);

		}
		dao.saveZszrTPs(list);
	}

	/**
	 * <p>
	 * 新增卷板缺陷表保管DB
	 * </p>
	 * 
	 * @param jbnolist
	 *            卷板No和分配号
	 * @param zsno
	 *            指示书No
	 * @param dhnoline
	 *            订货NO
	 */
	public void saveQxbgTP(List<String> jbnolist, String zsno, DhuoTp dhVO) {
		QxbgTp entity;
		YcaiTp ycai = new YcaiTp();
		List<QxbgTp> list = new ArrayList<QxbgTp>();
		String[] arr;
		String jbnonew;
		Date crea = new Date();
		for (String jbno : jbnolist) {
			entity = new QxbgTp();
			arr = jbno.split("-");
			jbnonew = arr[0];
			// 取原材卷板
			ycai = ycaiBo.get(jbnonew);
			// 卷板No
			entity.setJbno(jbnonew);
			// 担当者略称
			entity.setAbbr(ycai.getAbbr());
			// 作成时间
			entity.setCrea(crea);
			// 表面精加工符号(现品)
			entity.setFace(ycai.getFace());
			// 附着量
			if (dhVO != null) {
				entity.setFugm(dhVO.getFuzm() + "/" + dhVO.getFufm());
			}
			// 现品尺寸.厚
			entity.setHouu(ycai.getXpho());
			// 现品尺寸.宽
			entity.setKuan(ycai.getXpkn());
			// 卷板长
			entity.setJbcn(ycai.getJbcn());
			// 卷板重量(Kg)
			entity.setJbzl(ycai.getZpzl());
			// 指示书No
			entity.setZsno(zsno);

			// 原板制造商No
			entity.setZzno(ycai.getZzsd());

			// 实体加到集合
			list.add(entity);

		}
		dao.saveQxbgTPs(list);
	}

	@Override
	public String getForJs(Serializable id) {
		ZsdhTp entity = dao.getZsdhTp(id);
		if (entity == null) {
			return new Result(-1, "无法获取该条信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void updateZsdhTp(String[] ids, String jinj, String shch, String remk) {
		dao.updateZsdhTp(ids, jinj, shch, remk);
	}

	@Override
	public void updateSLZsJinj(String[] ids, String jinj, String remk) {
		dao.updateSLZsJinj(ids, jinj, remk);
	}

	@Override
	public void updateREMK(String zsno, String remk) {
		dao.updateREMK(zsno, remk);
	}

	@Override
	public void updateSLZsDM(String[] ids, String sfdm, String type) {
		dao.updateSLZsDM(ids, sfdm, type);
	}

	@Override
	public void deleteFP(String[] ids) {
		for (String id : ids) {
			if (ycaiBo.isExistedYcaiSjsj(id.toString())) {
				throw new CocoException(-1, "指示书" + id + "正在实绩录入，不能取消分派！");
			}
			dao.deleteFP(id);
		}
	}

	@Override
	public void deleteSLFP(String[] ids) {
		dao.deleteSLFP(ids);
	}

	@Override
	public void deleteSLDM(String[] ids, String type) {
		dao.deleteSLDM(ids, type);
	}

	@Override
	public void saveZsdh(ZsdhTp entity) {
		dao.savezsdh(entity);
	}

	@Override
	public void deleteZsdh(String zsno) {
		// 查询订货指示DB的操作Mode和 订货No.行号
		ZsdhTp zsdh = dao.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		if (ycaiBo.isExistedYcai(zsno)) {
			throw new CocoException(-1, "该指示书已经实绩录入，不能撤消！");
		}
		// List<String> jbnoAndfpnolist = new ArrayList<String>();
		Map<String, List<String>> fpnos = new HashMap<String, List<String>>();
		List<String> jbnos = null;
		List<String> jbnolist = new ArrayList<String>();
		// 卷重
		Integer zpzl = 0;
		YcaiQE ycaiqe = new YcaiQE();
		ycaiqe.setZsno(zsno);
		ycaiBo.query(ycaiqe);
		if (ycaiqe != null) {
			if (ycaiqe.getDatas() != null) {
				for (YcaiTp yctp : ycaiqe.getDatas()) {
					jbnolist.add(yctp.getJbno());
					if (yctp.getZpzl() != null) {
						zpzl = zpzl + yctp.getZpzl();
					}
					if ((jbnos = fpnos.get(yctp.getFpno())) == null) {
						jbnos = new ArrayList<String>();
						jbnos.add(yctp.getJbno());
						fpnos.put(yctp.getFpno(), jbnos);
						continue;
					}
					jbnos.add(yctp.getJbno());
				}
			}
		}
		// 撤消指示对象DB
		deleteZsdxTps(fpnos);

		// 删除订货指示DB
		dao.deleteZsdhTp(zsno);
		// 撤消原材卷DB
		cancelYcaiTp(jbnolist);
		// 删除指示DB(装入卷板)
		deleteZszrTPs(jbnolist, zsno);
		// 删除卷板缺陷表保管DB
		deleteQxbgTPs(jbnolist);
		// 更新订货DB
		updateDhuoTp(zsdh.getDhno(), zpzl);
	}

	/**
	 * <p>
	 * 更新订货DB
	 * </p>
	 * 
	 * @param dhno
	 *            订货NO和行号
	 * @param zpzl
	 *            卷重
	 */
	public void updateDhuoTp(String dhno, Integer zpzl) {
		if (dhno != null && !dhno.isEmpty()) {
			String dhnonew = dhno.substring(0, 7);
			String line = dhno.substring(7);
			dao.updateDhuoTp(new DhuoTpPk(dhnonew, line), zpzl);
		}
	}

	/**
	 * <p>
	 * 撤消原材卷板
	 * </p>
	 * 
	 * @param id
	 *            原材原板NO
	 * @param jdez
	 *            进度标记.ETL指示
	 * @param fpyc
	 *            分配/余材标记
	 */
	public void cancelYcaiTp(List<String> jbnolist) {
		for (String jbno : jbnolist) {
			dao.cancelYcaiTp(jbno, ZtConstants.ZPNG_JD_ETLZS_WZS, EFpyc.KEY_YC, YbStat.YRK.stat);
		}

	}

	/**
	 * <p>
	 * 删除指示DB
	 * </p>
	 * 
	 * @param jbnoAndfpnolist
	 *            卷板No和分配号
	 */
	public void deleteZsdxTps(Map<String, List<String>> fpnos) {
		if (!fpnos.isEmpty()) {
			for (String fpno : fpnos.keySet()) {
				doQxfp(fpnos.get(fpno), fpno);
			}
		}
	}

	/**
	 * <p>
	 * 删除指示DB(装入卷板)
	 * </p>
	 * 
	 * @param jbnolist
	 *            原材原板NO
	 * @param zsno
	 *            指示书
	 */
	public void deleteZszrTPs(List<String> jbnolist, String zsno) {
		for (String jbno : jbnolist) {
			ZszrTpPk idpk = new ZszrTpPk(zsno, jbno);
			dao.deleteZszrTP(idpk);
		}
	}

	/**
	 * <p>
	 * 删除卷板缺陷表保管DB
	 * </p>
	 * 
	 * @param jbnolist
	 *            原材原板NO
	 * @param zsno
	 *            指示书
	 */
	public void deleteQxbgTPs(List<String> jbnolist) {
		for (String jbno : jbnolist) {

			dao.deleteQxbgTp(jbno);
		}
	}

	@Override
	public void deleteZsdxTp(Serializable id) {
		dao.deleteZsdxTp(id);
	}

	@Override
	public void deleteZszrTp(Serializable id) {
		dao.deleteZszrTP(id);
	}

	@Override
	public ZsdhTp getZsdhTp(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.getZsdhTp(id);
	}

	@Override
	public ZsdhViewVO getZsdhTpVO(Serializable id, String type) {
		if (id == null) {
			return null;
		}
		ZsdhTp zsdh = dao.getZsdhTp(id);
		if (zsdh == null) {
			throw new CocoException(-1, "指示书不存在！");
		}
		ZsdhViewVO zsdhVO = new ZsdhViewVO();
		zsdhVO.setZsno(zsdh.getZsno());
		zsdhVO.setCaoz(zsdh.getCaoz());
		zsdhVO.setZpcc(zsdh.getZpcc());
		zsdhVO.setAbbr(zsdh.getAbbr());
		zsdhVO.setZrkn(zsdh.getZrkn());
		zsdhVO.setJdcn(zsdh.getJdcn());
		zsdhVO.setYuny(zsdh.getYuny());
		zsdhVO.setNjno(zsdh.getNjno());
		zsdhVO.setFpdj(zsdh.getFpdj());
		zsdhVO.setFugm(zsdh.getFugm());
		zsdhVO.setFuzx(zsdh.getFuzx());
		zsdhVO.setFuzs(zsdh.getFuzs());
		zsdhVO.setFufx(zsdh.getFufx());
		zsdhVO.setFufs(zsdh.getFufs());
		zsdhVO.setFace(zsdh.getFace());
		zsdhVO.setJhdd(zsdh.getJhdd());
		zsdhVO.setDhno(zsdh.getDhno());
		zsdhVO.setMdan(zsdh.getMdan());
		zsdhVO.setBdan(zsdh.getBdan());
		zsdhVO.setMszi(zsdh.getMszi());
		zsdhVO.setMxzi(zsdh.getMxzi());
		zsdhVO.setKszi(zsdh.getKszi());
		zsdhVO.setKxzi(zsdh.getKxzi());
		zsdhVO.setCszi(zsdh.getCszi());
		zsdhVO.setCxzi(zsdh.getCxzi());
		zsdhVO.setPzno(zsdh.getPzno());
		// 查询订货DB
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(zsdh.getDhno());
		DhuoTp dhuo = dhBo.get(dhpk);
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB不存在！");
		}
		if (ProductType.etl.name.equals(type)) {
			// 增加附业KEY
			if (EKpType.ETL.key.equals(dhuo.getKpn1Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn1Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn1());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn2Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn2Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn2());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn3Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn3Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn3());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn4Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn4Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn4());
			}
		}
		if (ProductType.sl.name.equals(type)) {
			// 增加附业KEY
			if (EKpType.SL.key.equals(dhuo.getKpn1Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn1Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn1());
			}
			if (EKpType.SL.key.equals(dhuo.getKpn2Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn2Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn2());
			}
			if (EKpType.SL.key.equals(dhuo.getKpn3Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn3Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn3());
			}
			if (EKpType.SL.key.equals(dhuo.getKpn4Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn4Flag())) {
				zsdhVO.getKpns().add(dhuo.getKpn4());
			}
		}
		// 备注1
		zsdhVO.setBz1(dhuo.getBz1());
		// 备注2
		zsdhVO.setBz3(dhuo.getBz3());
		// 木工所业联
		zsdhVO.setMgsn(dhuo.getMgsn());

		zsdhVO.setYmax(dhuo.getYmax());
		zsdhVO.setYmin(dhuo.getYmin());
		zsdhVO.setKbzx(dhuo.getKbzx());
		zsdhVO.setKbzs(dhuo.getKbzs());
		zsdhVO.setYtyp(dhuo.getYtyp());
		zsdhVO.setYqty(dhuo.getYqty());
		zsdhVO.setNjbh(dhuo.getNjbh());
		zsdhVO.setKbsz(dhuo.getKbsz());
		zsdhVO.setKbao(dhuo.getKbao());
		zsdhVO.setDmfx(zsdh.getDmfx());
		zsdhVO.setLotz(dhuo.getFuzm() + "/" + dhuo.getFufm());
		return zsdhVO;
	}

	@Override
	public List<ZsdhVVO> getYcaiByZsno(String zsno) {
		List<ZsdhVVO> zsdhs = new ArrayList<ZsdhVVO>();
		ZsdhVVO zsV;
		String ylnNo;
		List<YcaiTp> ycais = ycaiBo.findByZsno(zsno);
		for (YcaiTp yc : ycais) {
			zsV = new ZsdhVVO();
			zsV.setJbno(yc.getJbno());
			zsV.setDhno(yc.getDhno());
			zsV.setPzno(yc.getPzno());
			zsV.setRjsx(yc.getRjsx());
			zsV.setRjly(yc.getRjly());
			zsV.setZpzl(yc.getZpzl());
			zsV.setYcbr(yc.getYcbr());
			zsV.setZzsd(yc.getZzsd());
			// 增加业联
			ylnNo = yc.getYlno();
			if (ylnNo != null) {
				String[] ylns1 = ylnNo.split(",");
				String ylty;
				String ylns2;
				for (String yln : ylns1) {
					ylty = yln.substring(0, 1);
					ylns2 = yln.substring(2);

					if (EKpType.ETL.key.equals(ylty)
							|| EKpType.BOTH.key.equals(ylty)) {
						zsV.getYlns().add(ylns2);
					}

				}
			}
			zsV.setStat(yc.getStat());
			zsdhs.add(zsV);
		}
		return zsdhs;
	}

	@Override
	public ZsdxTp get(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.get(id);
	}

	@Override
	public ZsdxTp getUniqueZsdx(String fpno) {
		return dao.getUniqueZsdx(fpno);
	}

	@Override
	public void saveDyZzs(List<String> idsList) {

		String[] zsnoAndSort;
		Double sort = null;
		for (String id : idsList) {
			zsnoAndSort = id.split("-");
			if (zsnoAndSort.length > 1) {
				sort = Double.parseDouble(zsnoAndSort[1]);
			}
			dao.saveDyZzs(zsnoAndSort[0], sort);
			sort = null;
		}
	}

	/*
	 * =====================SL指示=====================
	 */

	@Override
	public void queryJqc(QEntity<JqcVO> qentity) {
		dao.queryJqc(qentity);
	}

	@Override
	public List<String> slzc(List<String> jbnos, List<String> nozsnos,
			User user, boolean isPrint) {
		List<String> zsnos = new ArrayList<String>();

		List<List<String[]>> zses = parseNosSL(jbnos, nozsnos);
		if (zses.isEmpty()) {
			if (nozsnos.size() > 0) {
				throw new CocoException(-1, "选择的制品已经停止");
			}
			else {
				throw new CocoException(-1, "没有能生成指示书的制品");
			}

		}
		List<String> jbnoList;
		for (List<String[]> items : zses) {
			jbnoList = new ArrayList<String>();
			for (String[] params : items) {
				// 制品NO-订货No.-剪边标志-现品宽-现品厚
				if (params[6] == null || params[6].isEmpty()) {
					throw new CocoException(-1, "镀锡量为空");
				}
				if (params[7] == null || params[7].isEmpty()) {
					throw new CocoException(-1, "镀锡量为空");
				}
				jbnoList.add(params[0] + "-" + params[2] + "-" + params[3]
						+ "-" + params[4] + "-" + params[5] + "-" + params[6]
						+ "-" + params[7]);

			}
			zsnos.add(doSlZss(jbnoList, isPrint));
		}
		if (zsnos.isEmpty()) {
			throw new CocoException(-1, "不能生成任何指示书");
		}
		return zsnos;
	}

	/**
	 * <p>
	 * SL指示书作成
	 * </p>
	 * 
	 * @param jbnolist
	 *            界面
	 * @param dhnoline
	 * @param isPrint
	 * @return
	 */
	private String doSlZss(List<String> jbnolist, boolean isPrint) {
		String[] arr;
		// 制品NO列表
		List<String> jbos = new ArrayList<String>();
		// 现品宽
		Double xpkn = 0.0;
		// 现品厚
		// Double xpho = 0.0;
		// 实绩附着量.正
		Double sczm = 0.0;
		// 实绩附着量.反
		Double scfm = 0.0;
		// 剪边宽变更标记
		String jbkb = null;
		// 订货NO
		String dhnoline = null;
		for (String jbno : jbnolist) {
			arr = jbno.split("-");
			jbos.add(arr[0]);
			dhnoline = arr[1];
			jbkb = arr[2];
			xpkn = NumberUtils.format(Double.parseDouble(arr[3]), 2);
			// xpho = NumberUtils.format(Double.parseDouble(arr[4]), 3);
			sczm = NumberUtils.format(Double.parseDouble(arr[5]), 2);
			scfm = NumberUtils.format(Double.parseDouble(arr[6]), 2);
		}
		// 根据订货NO和行号取订货DB详细信息
		DhuoTp dh = null;
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
		dh = dhBo.getRef(dhpk);
		if (dh == null) {
			throw new CocoException(-1, "订货DB的NO." + dhnoline + "不存在！");
		}
		// 取界面选取的制品
		List<ZpngTp> zpngTps = zpBo.listZp(jbos);
		// 取强制标记
		String qzbj = getQzbj4Slzszc(zpngTps);
		ESlzssType flag = null;
		// 需要剪边(剪边宽变更标记不为空且1即剪边):类型为V
		if (jbkb != null && ZtConstants.JBBJ_JB.equals(jbkb)) {
			flag = ESlzssType.V;
		}
		// 装入宽不等于制品尺寸.宽
		else if ((dh.getKuan() == null && xpkn > 0)
				|| (dh.getKuan() != null && xpkn.doubleValue() != dh.getKuan().doubleValue())) {
			flag = ESlzssType.V;
		}
		// 不剪边，且为剪切板：类型为W
		else {
			flag = ESlzssType.W;
		}
		// 写指示书（作成指示书）
		String zsno = saveZsdh4Slzszc(dh, flag, qzbj, xpkn, sczm, scfm, isPrint);
		ZsdxTpPk zsdxTpPk = null;
		ZszrTp zszrTp = null;
		String zrjb = null;
		for (ZpngTp zpngTp : zpngTps) {
			zrjb = zpngTp.getJbno();
			if (dao.isExistedZszrTp(zrjb)) {
				throw new CocoException(-1, "错误:Coil NO:" + zrjb
						+ "已经作成指示书,不能再生成");
			}
			// 更新多个制品记录
			zpngTp.setJdsz(ZtConstants.ZPNG_JD_SLZS_YZS);
			zpngTp.setZsno(zsno);
			zpBo.save(zpngTp);

			// 新增多个指示DB(装入卷板)
			zszrTp = parseSlZszrTp(zpngTp, zsno);
			dao.saveZszrTp(zszrTp);
			// 更新多个指示对象DB
			zsdxTpPk = new ZsdxTpPk(zpngTp.getFpno(), zrjb);
			dao.updateStat(zsdxTpPk, ZtConstants.ZSDX_STAT_YZS);
		}
		return zsno;
	}

	/**
	 * <p>
	 * SL指示书作成时，获取其下的制品中是否有强制的分配（查指示对象），如有存在至少一个为强制分配时，返回‘强制分配’
	 * </p>
	 * 
	 * @param zpngTps
	 * @return String
	 */
	private String getQzbj4Slzszc(List<ZpngTp> zpngTps) {
		String qzbj = ZtConstants.ZSDX_QZBJ_FQZ;
		if (zpngTps == null || zpngTps.size() == 0) {
			return qzbj;
		}
		ZsdxTp zsdxTp = null;
		for (ZpngTp zpngTp : zpngTps) {
			zsdxTp = dao.get(new ZsdxTpPk(zpngTp.getFpno(), zpngTp.getJbno()));
			if (zsdxTp == null) continue;
			if (ZtConstants.ZSDX_QZBJ_QZ.equals(zsdxTp.getQzbj())) {
				qzbj = ZtConstants.ZSDX_QZBJ_QZ;
				break;
			}
		}
		return qzbj;
	}

	/**
	 * <p>
	 * 作成SL指示书时，新增指示书
	 * </p>
	 * 
	 * @param dhno
	 *            订货号
	 * @param type
	 *            {@link ESlzssType}
	 * @param qzbj
	 *            强制标记
	 * @return String 指示书号（由逗号隔开的多个指示书号组合而成）
	 */
	private String saveZsdh4Slzszc(DhuoTp dh, ESlzssType type, String qzbj,
			Double xpkn, Double sczm, Double scfm, boolean isPrint) {
		Date date = new Date();
		// 取流水号
		String zsls = seqBo.sequence(Seq.slzsno.name);
		if ((zsls == null) || (zsls.isEmpty())) {
			throw new CocoException(-1, "SL指示书流水号生成失败");
		}
		// 实例化订货指示DB实体。
		ZsdhTp zss = new ZsdhTp();
		// 指示书No
		zss.setZsno(type.flag + zsls);
		// 用户略称
		zss.setAbbr(dh.getAbbr());
		// 换算尺寸.厚
		Double hngh = dh.getHngh() != null ? dh.getHngh() : 0.0;
		zss.setHngh(hngh);
		// 换算尺寸.宽
		Double hngk = dh.getHngk() != null ? dh.getHngk() : 0.0;
		zss.setHngk(hngk);
		// 换算尺寸.长
		Double hngc = dh.getHngc() != null ? dh.getHngc() : 0.0;
		zss.setHngc(hngc);

		// 包装材料重量
		zss.setBzcl(dh.getBzcl());
		// 重量计算补正系数
		ZlnrCode zlnr = ZlnrCode.get(dh.getJhnr());
		if (zlnr == null) {
			throw new CocoException(-1, "无效的重内代码！");
		}
		zss.setBzxs(cmnBo.fetchZsbz(zlnr));
		// 订货的捆包张数·值
		int dbsz = Integer.parseInt(dh.getKbsz());
		// 包装张数
		Code118 pz = Code118.get(dh.getPzno().substring(0, 1));
		ECaoz caoz = ECaoz.getByPz(pz);
		// 为板材时才存在包装张数
		if (caoz == ECaoz.SHEET) {
			zss.setBzzs(dbsz);
		}
		// 制品尺寸.长
		zss.setCang(NumberUtils.format(dh.getCang(), 2));
		// 操作Mode
		zss.setCaoz(caoz != null ? caoz.key : null);
		// 超量分配标记(该字段弃用)
		zss.setClfp(null);
		// 加工用途条件
		zss.setCond(dh.getCond());
		// 采取指示量
		zss.setCqzs(0);
		// 作成时间（年月日时分秒）
		zss.setCrea(date);
		// 尺寸允许范围.剪断长mm上限.符号
		// zss.setCsfu(dh.getCsfu());
		// 尺寸允许范围.剪断长mm上限.值
		zss.setCszi(dh.getCszi());
		// 尺寸允许范围.剪断长mm下限.符号
		// zss.setCxfu(dh.getCsfu());
		// 尺寸允许范围.剪断长mm下限.值
		zss.setCxzi(dh.getCxzi());

		// 订货附着量略称
		zss.setDhfz(cmnBo.getDhfz(dh.getFudw(), dh.getFuzm(), dh.getFufm(), dh.getChdx()));
		// 订货规格(取订货的运用规格)
		zss.setDhgg(dh.getYuny());
		// 订货No.行号
		zss.setDhno(dh.getDhno() + dh.getLine());
		// 订货区分（暂设为空）
		// 垫木方向
		zss.setDmfx(SinoUtils.getSkidFx(dh.getKuan(), dh.getCang(), dh.getYyan(), dh.getDmfx()));
		// 垫木重量
		zss.setDmzl(dh.getDmzl());
		// 订货表面精加工符号
		zss.setFace(dh.getFace());
		// reflow要否[软溶要否]
		if (dh.getFace() != null) {
			if (Code005.M.key.equals(dh.getFace())
					|| Code005.BN.key.equals(dh.getFace())) {
				zss.setFlow(ZtConstants.CODE_FLOW);
			}
		}
		// 分配等级
		zss.setFpdj(dh.getFpdj());
		// 附着量.单位
		zss.setFudw(dh.getFudw());
		// 附着量.反面(g/m2)
		zss.setFufm(cmnBo.parseFzl2Gm(dh.getFudw(), dh.getFufm()));
		// 附着量.反面.上限值
		zss.setFufs(dh.getFufs());
		// 附着量.反面.下限值
		zss.setFufx(dh.getFufx());
		// 附着量（g/m2)
		zss.setFugm(sczm.toString() + "/" + scfm.toString());
		// 附着量.正面(g/m2)
		zss.setFuzm(cmnBo.parseFzl2Gm(dh.getFudw(), dh.getFuzm()));
		// 附着量.正面.上限值
		zss.setFuzs(dh.getFuzs());
		// 附着量.正面.下限值
		zss.setFuzx(dh.getFuzx());
		// 规格代码
		zss.setGgno(dh.getGgno());
		// 钢种类型
		if (dh.getGgno() != null) {
			CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_109, dh.getGgno().substring(0, 1));
			if (codeItem != null) {
				zss.setGzlx(codeItem.getValue());
			}
		}
		// 合金层
		zss.setHjin(dh.getHjin());
		// 制品尺寸.厚
		zss.setHouu(dh.getHouu());
		// 尺寸允许范围.厚%上限.符号
		// zss.setHsfu(dh.getHsfu());
		// 尺寸允许范围.厚%上限.值
		zss.setHszi(dh.getHszi());
		// 尺寸允许范围.厚%下限.符号
		// zss.setHxfu(dh.getHxfu());
		// 尺寸允许范围.厚%下限.值
		zss.setHxzi(dh.getHxzi());
		// 化学处理方法
		zss.setHxcl(dh.getHuac());

		// 锯齿种类(锯齿形式)
		zss.setJczl(dh.getJcxs());
		// 锯齿可否
		if ((dh.getJcxs() != null) && (!dh.getJcxs().isEmpty())) {
			zss.setJckf(ZtConstants.DHZS_JCKF_YJC);
		}
		// 剪切长
		if (ECaoz.getByPz(pz) == ECaoz.SHEET) {
			zss.setJdcn(hngc);
		}
		// 交货区分
		if (dh.getJhnr() != null) {
			if (ZlnrCode.nr1 == zlnr || ZlnrCode.nr2 == zlnr
					|| ZlnrCode.nr3 == zlnr) {
				zss.setJhdd("1");
			}
			else {
				zss.setJhdd("2");
			}
		}
		// 交货期
		zss.setJhqi(dh.getJhqi());
		// 直角度
		zss.setJiao(NumberUtils.format(dh.getJiao(), 1));
		// 捆包形式
		zss.setKbao(dh.getKbao());
		// 捆包指定重量.上限
		zss.setKbzs(dh.getKbzs());
		// 捆包指定重量.下限
		zss.setKbzx(dh.getKbzx());
		// 附页KpNo1.标示流水线
		zss.setKpn1Flag(dh.getKpn1Flag());
		// 附页KpNo1.
		zss.setKpn1(dh.getKpn1());
		// 附页KpNo2.标示流水线
		zss.setKpn2Flag(dh.getKpn2Flag());
		// 附页KpNo2.
		zss.setKpn2(dh.getKpn2());
		// 附页KpNo3.标示流水线
		zss.setKpn3Flag(dh.getKpn3Flag());
		// 附页KpNo3
		zss.setKpn3(dh.getKpn3());
		// 附页KpNo4.标示流水线
		zss.setKpn4Flag(dh.getKpn4Flag());
		// 附页KpNo4.
		zss.setKpn4(dh.getKpn4());
		// 尺寸允许范围.宽mm上限.符号
		// zss.setKsfu(dh.getKsfu());
		// 尺寸允许范围.宽mm上限.值
		zss.setKszi(dh.getKszi());
		// 制品尺寸.宽
		zss.setKuan(dh.getKuan());
		// 尺寸允许范围.宽mm下限.符号
		// zss.setKxfu(dh.getKxfu());
		// 尺寸允许范围.宽mm下限.值
		zss.setKxzi(dh.getKxzi());
		// 零头可否(零头下限.单位)
		zss.setLtkf(dh.getLtdw());
		// 零头下限
		zss.setLtxx(dh.getLtzi());
		// m单重
		zss.setMdan(SinoUtils.calculateMdan(dh.getHouu(), dh.getKuan()));
		// 薄板单重
		zss.setBdan(SinoUtils.calculateBdan(dh.getHouu(), dh.getKuan(), dh.getCang()));
		// 112张单重(薄板单重 * 112)
		zss.setD112(NumberUtils.format(zss.getBdan() * 112, 1));
		// 尺寸允许范围.厚mm上限.符号
		// zss.setMsfu(dh.getHsfu());
		// 尺寸允许范围.厚mm上限.值
		Double hszi = dh.getHszi() != null ? dh.getHszi() : 0.0;
		zss.setMszi(NumberUtils.format(hngh * hszi / 100, 3));

		// 尺寸允许范围.厚mm下限.符号
		// zss.setMxfu(dh.getHxfu());
		// 尺寸允许范围.厚mm下限.值
		Double hxzi = dh.getHxzi() != null ? dh.getHxzi() : 0.0;
		zss.setMxzi(NumberUtils.format(hngh * hxzi / 100, 3));
		// 用户名称
		zss.setName(dh.getName());
		// 内径保护筒
		zss.setNjbh(dh.getNjbh());
		// 内径代码
		if (dh.getNeij() != null && !dh.getNeij().isEmpty()) {
			CodeItem code = codeBo.getUniqueItemByValue(CmnConstants.CODE_020, dh.getNeij());
			if (code != null) {
				zss.setNjno(code.getKey());
			}
		}
		// 批重量(单位：吨)
		if (ECaoz.SHEET == caoz) {
			zss.setPizl(NumberUtils.format(zss.getBdan() * zss.getBzzs(), 3));
		}

		// 保护标记
		zss.setProt(dh.getProt());
		// 品种代码
		zss.setPzno(dh.getPzno());
		// 强制标记
		zss.setQzbj(qzbj);
		// Specification规格略称
		zss.setSpec(dh.getGgnm());
		// 状态【这里直接分派】
		zss.setStat(ZtConstants.DHZS_STAT_YFP);
		// 更新时间（年月日时分秒）,原字段TOUPDA
		zss.setUpda(date);
		// K-Plate洗涤强化
		zss.setXdqh(dh.getPlat());
		// 箱数(捆包枚数为100或112的倍数)
		int box = dbsz % 112 != 0 ? dbsz / 100 : dbsz / 112;
		zss.setXshu(box);
		// 销售W否
		zss.setXswf(dh.getRjie());
		// 余材状况
		zss.setYczk(EXpzk.ZJP_KEY);
		// 目标涂油量
		zss.setYqty(dh.getYqty());
		// 目标涂油种类
		CodeTyzl codeTyzl = CodeTyzl.get(dh.getYtyp());
		if (codeTyzl != null) {
			zss.setYtyp(codeTyzl.name);
		}
		// 运用规格
		zss.setYuny(dh.getYuny());
		// 压延方向指定
		zss.setYyan(dh.getYyan());
		// 制品尺寸（W表示编辑完）
		// zss.setZpcc(SinoUtils.formatProductSize(dh.getHouu(), dh.getCang(),
		// dh.getKuan(), dh.getPzno(), dh.getYyan()));
		zss.setZpcc(SinoUtils.formatProductSize(dh.getHouu(), dh.getKuan(), dh.getCang()));
		// 装入宽
		// zss.setZrkn(NumberUtils.format(dh.getZrkn(), 2));
		zss.setZrkn(xpkn);
		// 指示书完成标记
		zss.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 指示书打印标记
		// zss.setZsfx(ZtConstants.DHZS_ZSFX_WDY);
		if (isPrint) {
			zss.setZsfx(ZtConstants.DHZS_ZSFX_YDY);
		}
		else {
			zss.setZsfx(ZtConstants.DHZS_ZSFX_WDY);
		}
		// 如果被分配的制品的宽与当前订货DB的订货尺寸宽KUAN_不一样，则订货指示DB的再剪边标记ZJBB_(JBB_)
		if (xpkn != null && !xpkn.equals(dh.getKuan())) {
			zss.setJbbj(ZtConstants.DHZS_JBBJ_JB);
			zss.setJbhk(hngk);
		}
		zss.setSfdm(ZtConstants.DHZS_ZSFX_WDY);
		saveZsdh(zss);
		return zss.getZsno();
	}

	private ZszrTp parseSlZszrTp(ZpngTp zpngTp, String zsno) {
		ZszrTp entity = new ZszrTp();
		// 装入卷板No/PileNo
		entity.setZrjb(zpngTp.getJbno());
		// 指示书No
		entity.setZsno(zsno);
		// 板厚不量有无
		entity.setBhbl(zpngTp.getBhbl());
		// 作成时间
		entity.setCrea(new Date());
		// 等级(现品)
		entity.setDeng(zpngTp.getDeng());
		// 表面精加工符号(现品)
		entity.setFace(zpngTp.getFace());
		// 记录识别,原字段TOFLAG
		entity.setFlag(null);
		// 附着量.单位(现品)
		entity.setFudw(zpngTp.getFudw());
		// 附着量.反面(现品)
		entity.setFufm(zpngTp.getFufm());
		// 附着量.正面(现品)
		entity.setFuzm(zpngTp.getFuzm());
		// 卷板长
		entity.setJbcn(zpngTp.getJbcn());
		// Pile区分
		entity.setPlqf(zpngTp.getPlqf());
		// 更新程序名
		entity.setProg(null);
		// 前工程作业年月日
		entity.setQgcn(null);
		// 溶接个数.冷延
		entity.setRjly(zpngTp.getRjly());
		// 溶接个数.酸洗
		entity.setRjsx(zpngTp.getRjsx());
		// 预备1
		entity.setRsv1(null);
		// 预备2
		entity.setRsv2(null);
		// 状态
		entity.setStat(null);
		// 更新时间
		entity.setUpda(null);
		// 现品尺寸.长
		entity.setXpcn(zpngTp.getXpcn());
		// 现品尺寸.厚
		entity.setXpho(zpngTp.getXpho());
		// 现品尺寸.宽
		entity.setXpkn(zpngTp.getXpkn());
		// 业连No
		entity.setYlno(zpngTp.getYlno());
		// 取订货合同
		DhuoTp dh = dhBo.get(SinoUtils.parseDhuoPk(zpngTp.getDhno()));
		// 涂油量(现品)
		entity.setYqty(dh.getYqty());
		// 涂油种类(现品)
		CodeTyzl codeTyzl = CodeTyzl.get(dh.getYtyp());
		if (codeTyzl != null) {
			entity.setYtyp(codeTyzl.name);
		}

		// 运用规格(现品)
		entity.setYuny(zpngTp.getYuny());
		// 制品重量
		entity.setZpzl(zpngTp.getZpzl());
		// 原板制造商No
		entity.setZzno(zpngTp.getZzno());
		return entity;
	}

	// /**
	// * <p>
	// * 作成SL指示书时，新增SL装入卷板
	// * </p>
	// * <p>
	// * author: 方元龙
	// * </p>
	// */
	// private void saveSlzr4Slzszc(List<ZpngTp> listZp) {
	// List<ZszrTp> listZrtp = new ArrayList<ZszrTp>();
	// ZszrTp entity = null;
	// ZszrTp zszr = null;
	// String zsno = null;
	// for (ZpngTp zp : listZp) {
	// entity = new ZszrTp();
	// // 检查jbno是否已经做成指示书
	// zszr = dao.getUniqueZszrTp(zp.getJbno());
	// if (zszr != null) {
	// zsno = zszr.getZsno();
	// if (zsno != null) {
	// if (EZsdm.SLW.key.equals(zsno.substring(0, 1))
	// || EZsdm.SLV.key.equals(zsno.substring(0, 1))) {
	// throw new CocoException(-1, "错误:Coil NO:"
	// + zp.getJbno() + "已经作成指示书,不能再生成！");
	// }
	// }
	// }
	// // 装入卷板No/PileNo
	// entity.setZrjb(zp.getJbno());
	// // 指示书No
	// entity.setZsno(zp.getZsno());
	// // 板厚不量有无
	// entity.setBhbl(zp.getBhbl());
	// // 作成时间
	// entity.setCrea(new Date());
	// // 等级(现品)
	// entity.setDeng(zp.getDeng());
	// // 表面精加工符号(现品)
	// entity.setFace(zp.getFace());
	// // 记录识别,原字段TOFLAG
	// entity.setFlag(null);
	// // 附着量.单位(现品)
	// entity.setFudw(zp.getFudw());
	// // 附着量.反面(现品)
	// entity.setFufm(zp.getFufm());
	// // 附着量.正面(现品)
	// entity.setFuzm(zp.getFuzm());
	// // 卷板长
	// entity.setJbcn(zp.getJbcn());
	// // Pile区分
	// entity.setPlqf(zp.getPlqf());
	// // 更新程序名
	// entity.setProg(null);
	// // 前工程作业年月日
	// entity.setQgcn(null);
	// // 溶接个数.冷延
	// entity.setRjly(zp.getRjly());
	// // 溶接个数.酸洗
	// entity.setRjsx(zp.getRjsx());
	// // 预备1
	// entity.setRsv1(null);
	// // 预备2
	// entity.setRsv2(null);
	// // 状态
	// entity.setStat(null);
	// // 更新时间
	// entity.setUpda(null);
	// // 现品尺寸.长
	// entity.setXpcn(zp.getXpcn());
	// // 现品尺寸.厚
	// entity.setXpho(zp.getXpho());
	// // 现品尺寸.宽
	// entity.setXpkn(zp.getXpkn());
	// // 业连No
	// entity.setYlno(zp.getYlno());
	// // 取订货合同
	// DhuoTp dh = dhBo.get(SinoUtils.parseDhuoPk(zp.getDhno()));
	// // 涂油量(现品)
	// entity.setYqty(dh.getYqty());
	// // 涂油种类(现品)
	// CodeTyzl codeTyzl = CodeTyzl.get(dh.getYtyp());
	// if (codeTyzl != null) {
	// entity.setYtyp(codeTyzl.name);
	// }
	//
	// // 运用规格(现品)
	// entity.setYuny(zp.getYuny());
	// // 制品重量
	// entity.setZpzl(zp.getZpzl());
	// // 原板制造商No
	// entity.setZzno(zp.getZzno());
	// listZrtp.add(entity);
	// }
	// dao.saveZszrTPs(listZrtp);
	// }

	@Override
	public void queryWwc(QEntity<ZsVO> qentity) {
		dao.queryWwc(qentity);
	}

	@Override
	public String getZsno(String jbno, String type) {
		if (ProductType.etl.name.equals(type)) {
			YcaiTp yc = ycaiBo.get(jbno);
			if (yc != null) {
				if (yc.getZsno() != null && !yc.getZsno().isEmpty()) {
					return yc.getZsno();
				}
			}
		}
		if (ProductType.sl.name.equals(type)) {
			ZpngTp zp = zpBo.getZp(jbno);
			if (zp != null) {
				if (zp.getZsno() != null && !zp.getZsno().isEmpty()) {
					return zp.getZsno();
				}
			}
		}
		return "000000";
	}

	@Override
	public void doSlZssCx(String zsno, User user) {
		// 查询指示书
		ZsdhTp zsdh = dao.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "异常：获取指示书失败");
		}
		// 如果指示书中已有卷实绩，则不能撤消
		if (ZtConstants.DHZS_SFSJ_YSJ.equals(zsdh.getSfsj())) {
			throw new CocoException(-1, "异常：指示书中已有卷实绩，不能撤消");
		}
		long $zpzl = 0; // 总制品重量
		List<ZpngTp> zpngTps = zpBo.listZpByZs(zsno);
		if (zpngTps.size() == 0) {
			throw new CocoException(-1, "异常：指示书中内容为空");
		}
		ZkfpCzjl czjl = null;
		Date date = new Date();
		int zpzl;
		for (ZpngTp zpngTp : zpngTps) {
			zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
			$zpzl += zpzl;
			// 删除装入卷板表
			dao.deleteZszrTP(new ZszrTpPk(zsno, zpngTp.getJbno()));
			// 删除指示对象
			dao.deleteZsdxTp(new ZsdxTpPk(zpngTp.getFpno(), zpngTp.getJbno()));

			// 更新制品表
			zpngTp.setAbbr(null);
			zpngTp.setDhno(null);
			zpngTp.setZsno(null);
			zpngTp.setJdsz(null);
			zpngTp.setFpyc(EFpyc.KEY_YC);
			zpngTp.setFpno(null);
			zpngTp.setAbbr(null);
			zpngTp.setUser(null);
			zpngTp.setUpda(date);
			zpBo.update(zpngTp);

			// 新增分配操作记录
			czjl = new ZkfpCzjl();
			czjl.setJbno(zpngTp.getJbno());
			czjl.setDhno(zpngTp.getDhno());
			czjl.setFpno(zpngTp.getFpno());
			czjl.setXpzk(EXpzk.ZJP_KEY);
			czjl.setSfpp(Sfpp.ndis.key);
			czjl.setCzlx(Czlx.key5.key);
			czjl.setDdno(null);
			czjl.setDdnm(null);
			czjl.setCrea(date);
			czjlBO.save(czjl);
		}
		// 删除指示订货
		dao.deleteZsdhTp(zsno);
		// 更新订货DB
		if (!dao.isFpForDh(zsdh.getDhno())) {
			String dhno = zsdh.getDhno();
			dhBo.updateStat(dhno.substring(0, 7), dhno.substring(7), DhStat.assign.key);
		}
	}

	@Override
	public ZsdhTp getRef(Serializable id) {
		return dao.getRef(id);
	}

	@Override
	public List<ZssXpVO> listZssXp(ZsdhTp zss) {
		if (zss == null) {
			return null;
		}
		// EXpzk type = EXpzk.get(zss.getYczk());
		String yczk = zss.getYczk();
		if (yczk == null || yczk.isEmpty()) {
			return null;
		}
		// 取订货合同
		List<ZssXpVO> listXp = new ArrayList<ZssXpVO>();
		ZssXpVO vo = null;
		List<String> listKp = null;
		// ETL
		if (EXpzk.SC.key.equals(yczk)) {
			// 取附页
			listKp = SinoUtils.parseKp(zss, EKpType.ETL);
			// 取原材列表
			YcaiQE qentity = new YcaiQE();
			qentity.setZsno(zss.getZsno());
			ycaiBo.query(qentity);
			List<YcaiTp> listYc = qentity.getDatas();
			for (YcaiTp yc : listYc) {
				vo = new ZssXpVO();
				vo.setJbno(yc.getJbno());
				vo.setDhno(yc.getDhno());
				vo.setPzno(yc.getPzno());
				vo.setRjly(yc.getRjly());
				vo.setRjsx(yc.getRjsx());
				vo.setZpzl(yc.getZpzl());
				vo.setZzsd(yc.getZzsd());
				vo.setYlnos(SinoUtils.parseYl(yc.getYlno(), EYlType.ETL));
				vo.setKpnos(listKp);
				YbStat stat = YbStat.get(yc.getStat());
				vo.setStat(stat != null ? stat.name : "");
				listXp.add(vo);
			}
		}
		// SL
		if (EXpzk.ZJP.key.equals(yczk)) {
			// 取附页
			listKp = SinoUtils.parseKp(zss, EKpType.SL);
			// 取剪切材列表
			List<ZpngTp> listZp = zpBo.listZssmx(zss.getZsno());
			String ylnNo;
			for (ZpngTp zp : listZp) {
				vo = new ZssXpVO();
				vo.setJbno(zp.getJbno());
				vo.setDhno(zp.getDhno());
				vo.setPzno(zp.getPzno());
				vo.setRjly(zp.getRjly());
				vo.setRjsx(zp.getRjsx());
				vo.setZpzl(zp.getZpzl());
				vo.setZzsd(zp.getZzsd());
				vo.setJbcn(zp.getJbcn());
				// 增加业联
				ylnNo = zp.getYlno();
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
				// vo.setKpnos(listKp);
				ZpStat stat = ZpStat.get(zp.getStat());
				vo.setStat(stat != null ? stat.name : "");
				listXp.add(vo);
			}
		}
		return listXp;
	}

	public ICzjlBO getCzjlBO() {
		return czjlBO;
	}

	public void setCzjlBO(ICzjlBO czjlBO) {
		this.czjlBO = czjlBO;
	}

	public ICmnBO getCmnBo() {
		return cmnBo;
	}

	public void setCmnBo(ICmnBO cmnBo) {
		this.cmnBo = cmnBo;
	}

	public IZsDAO getDao() {
		return dao;
	}

	public void setDao(IZsDAO dao) {
		this.dao = dao;
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

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IYcaitpBO getYcaiBo() {
		return ycaiBo;
	}

	public void setYcaiBo(IYcaitpBO ycaiBo) {
		this.ycaiBo = ycaiBo;
	}

}
