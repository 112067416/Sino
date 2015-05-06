/**
 * 
 */
package com.quanta.sino.zkfp.bo;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.exception.CocoException;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.Code103;
import com.quanta.sino.cmn.constants.Code104;
import com.quanta.sino.cmn.constants.Code105;
import com.quanta.sino.cmn.constants.Code109;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Code119;
import com.quanta.sino.cmn.constants.CodeJian;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.Czlx;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.EFace;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.SczsStat;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.Sfpp;
import com.quanta.sino.cmn.constants.YbScStat;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhConstants;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.SczsTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZkfpCwjl;
import com.quanta.sino.orm.ZkfpCzjl;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.yygl.bo.api.ISczsBO;
import com.quanta.sino.zkfp.bo.api.IFpBO;
import com.quanta.sino.zkfp.constants.ZkfpConstance;
import com.quanta.sino.zkfp.dao.api.ICwjlDAO;
import com.quanta.sino.zkfp.vo.CError;
import com.quanta.sino.zkfp.vo.FpVO;
import com.quanta.sino.zkfp.vo.FpckItemVO;
import com.quanta.sino.zkfp.vo.FpckVO;
import com.quanta.sino.zkfp.vo.PError;
import com.quanta.sino.zkfp.vo.ZkfpErrorVO;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.vo.ZsdxQE;

/**
 * <p>
 * 在库分配业务层实现类
 * </p>
 * <p>
 * create: 2011-1-14 下午01:51:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpBO implements IFpBO {

	/**
	 * 订货合同业务层操作接口
	 */
	private IDhBO dhBO;

	/**
	 * 进货管理业务层操作接口
	 */
	private IYcaitpBO ycaitpBO;

	/**
	 * 制品管理业务层操作接口
	 */
	private IZpBO zpBO;

	/**
	 * 分配操作数据访问层接口
	 */
	private ICzjlBO czjlBO;

	/**
	 * 分配错误操作数据访问层接口
	 */
	private ICwjlDAO cwjlDAO;

	/**
	 * 序号接口
	 */
	private ISeqBO seqBo;

	/**
	 * 指示对象业务层接口
	 */
	private IZsBO zsBO;

	/**
	 * 生产指示单业务接口
	 */
	private ISczsBO sczsBO;

	/**
	 * 在库分配错误记录路径
	 */
	private String zkfpErrorPath;

	/**
	 * 在库分配错误记录处理接口
	 */
	private ExcelbookDataExecuter<ZkfpErrorVO> zkfpErrorExec;

	/**
	 * 分配参考LIST记录路径
	 */
	private String fpckListPath;

	/**
	 * 分配参考LIST记录处理接口
	 */
	private ExcelbookDataExecuter<FpckVO> fpckListExec;

	@Override
	public String doFpqx(List<String> jbnos, String fpno, String dhno,
			String line, String yczk, User user) {
		if (ZtConstants.ZSDX_FP_NO.equals(fpno)) {
			return new Result(-1, "不能对该分配No.下的现品做取消操作").toString();
		}
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			return new Result(-1, "订货No." + dhno + "-" + line + "不存在").toString();
		}
		ZsdxQE qentity = new ZsdxQE();
		qentity.setFpno(fpno);
		qentity.setJbnos(jbnos);
		zsBO.queryZsdxtp(qentity);
		List<ZsdxTp> zsdxtps = qentity.getDatas();
		if (zsdxtps.size() == 0) {
			return new Result(-1, "分配No." + fpno + "不存在").toString();
		}
		// 判断指示对象状态是否已下指示。如果已下指示，则不能做取消分配操作
		for (ZsdxTp zsdxtp : zsdxtps) {
			if (!ZtConstants.ZSDX_STAT_WZS.equals(zsdxtp.getStat())) {
				return new Result(-1, "分配No." + fpno + "已下指示，不允许做取消分配操作").toString();
			}
		}
		if (EXpzk.SC.key.equals(yczk)) {
			// 取消分配素材
			return fpqxSc(jbnos, dhuoTp, user, zsdxtps.get(0));
		}
		if (EXpzk.BZP.key.equals(yczk)) {
			// 取消分配剪切板制品
			return fpqxBzp(jbnos, dhuoTp, user, zsdxtps.get(0));
		}
		if (EXpzk.JZP.key.equals(yczk)) {
			// 取消分配卷制品
			return fpqxJzp(jbnos, dhuoTp, user, zsdxtps.get(0));

		}
		// 取消分配中间品
		return fpqxZjp(jbnos, dhuoTp, user, zsdxtps.get(0));
	}

	/**
	 * <p>
	 * 取消素材分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的素材对象。
	 * <li>修改素材信息。
	 * <li>为取消分配素材，生成分配操作记录对象
	 * <li>修改订货合同的分配量
	 * <li>修改取消分配的指示对象状态为：取消分配。同时，判断订货合同是否还有分配的记录。如果没有，则修改订货合同的状态为：仕样确认；
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 * 
	 * @param jbnos
	 * @param dhuoTp
	 * @param user
	 * @param zsdxTp
	 * @return String
	 */
	private String fpqxSc(List<String> jbnos, DhuoTp dhuoTp, User user,
			ZsdxTp zsdxTp) {
		List<YcaiTp> ycaiTps = ycaitpBO.find(jbnos);
		if (ycaiTps.size() == 0) {
			return new Result(-1, "未选择制品").toString();
		}
		// 分配取消操作时间
		Date date = new Date();
		// 统计取消分配制品的重量
		Integer qxzl = 0;
		// 分配NO
		String fpno = zsdxTp.getFpno();
		// 余材状况(素材、中间品、剪切板制品或卷制品)
		String yczk = zsdxTp.getYczk();
		// 分配No对应的分配重量
		Integer cqzs = zsdxTp != null ? zsdxTp.getCqzs() : 0;
		// 取消分配操作记录
		ZkfpCzjl czjl = null;
		for (YcaiTp ycaiTp : ycaiTps) {
			ycaiTp.setFpno(null);
			ycaiTp.setFpyc(EFpyc.YC.key);
			ycaiTp.setStat(YbStat.YRK.stat);
			ycaiTp.setDhno(null);
			// 修改素材信息
			ycaitpBO.updateEntity(ycaiTp);
			// 统计取消分配量
			qxzl += ycaiTp.getZpzl();
			// 为取消分配素材，生成一个取消分配操作记录对象
			czjl = parseCzjl(ycaiTp.getJbno(), dhuoTp.getDhno(), dhuoTp.getLine(), yczk, Czlx.key2.key, fpno, date, user, null);
			// 保存操作记录
			czjlBO.save(czjl);
		}
		// 取消指示对象
		zsBO.doQxfp(jbnos, fpno);
		if (!zsBO.isFpForDh(dhuoTp.getDhno() + dhuoTp.getLine())) {
			dhuoTp.setStat(DhStat.confirm.key);
		}
		double fpln = dhuoTp.getFpln() != null ? dhuoTp.getFpln() : 0;
		dhuoTp.setFpln(fpln - qxzl / 1000d);
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 修改指示对象的分配量和修改日期
		zsBO.updateZsdx(fpno, cqzs - qxzl, date);
		// 更新页面上的未分配量和分配指示量
		FpVO fpVO = parseFpVO(zsdxTp, dhuoTp);
		fpVO.setCqzs((cqzs - qxzl) / 1000d);
		return new Result(fpVO).toJsObject();
	}

	/**
	 * <p>
	 * 取消剪切板制品分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的剪切板制品对象。
	 * <li>修改剪切板制品信息。
	 * <li>为取消分配剪切板制品，生成分配操作记录对象
	 * <li>修改订货合同的SL指示量和SL合格量
	 * <li>修改取消分配的指示对象状态为：取消分配。同时，判断订货合同是否还有分配的记录。如果没有，则修改订货合同的状态为：仕样确认；
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 * 
	 * @param jbnos
	 * @param dhuoTp
	 * @param user
	 * @param zsdxTp
	 * @return String
	 */
	private String fpqxBzp(List<String> jbnos, DhuoTp dhuoTp, User user,
			ZsdxTp zsdxTp) {
		List<ZpngTp> zpngTps = zpBO.listZp(jbnos);
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择制品").toString();
		}
		// 分配取消操作时间
		Date date = new Date();
		// 统计取消分配制品的重量
		Integer qxzl = 0;
		// 分配NO
		String fpno = zsdxTp.getFpno();
		// 余材状况(素材、中间品、剪切板制品或卷制品)
		String yczk = zsdxTp.getYczk();
		// 分配No对应的分配重量
		Integer cqzs = zsdxTp != null ? zsdxTp.getCqzs() : 0;
		// 取消分配操作记录
		ZkfpCzjl czjl = null;
		// 合格品的重量
		double hgl = 0;
		for (ZpngTp zpngTp : zpngTps) {
			zpngTp.setFpno(null);
			zpngTp.setFpyc(EFpyc.YC.key);
			zpngTp.setDhno(null);
			zpngTp.setAbbr(null);
			zpngTp.setUpda(date);
			// 修改制品信息
			zpBO.update(zpngTp);
			// 统计取消分配量
			qxzl += zpngTp.getZpzl();
			// 为取消分配制品，生成一个取消分配操作记录对象
			czjl = parseCzjl(zpngTp.getJbno(), dhuoTp.getDhno(), dhuoTp.getLine(), yczk, Czlx.key2.key, fpno, date, user, null);
			// 保存操作记录
			czjlBO.save(czjl);
			// 记录余材制品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 取消指示对象
		zsBO.doQxfp(jbnos, fpno);
		if (!zsBO.isFpForDh(dhuoTp.getDhno() + dhuoTp.getLine())) {
			dhuoTp.setStat(DhStat.confirm.key);
		}
		// 修改订货合同的SL指示量
		double slzs = dhuoTp.getSlzs() != null ? dhuoTp.getSlzs() : 0;
		dhuoTp.setSlzs(slzs - qxzl / 1000d);
		// 当制品为合格品（产量为1或9，处置为空）时，要修改制品对应订货合同的SL合格量
		double slhg = dhuoTp.getSlhg() != null ? dhuoTp.getSlhg() : 0;
		dhuoTp.setSlhg(slhg - hgl);
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 修改指示对象的分配量和修改日期
		zsBO.updateZsdx(fpno, cqzs - qxzl, date);
		// 更新页面上的未分配量和分配指示量
		FpVO fpVO = parseFpVO(zsdxTp, dhuoTp);
		fpVO.setCqzs((cqzs - qxzl) / 1000d);
		return new Result(fpVO).toJsObject();
	}

	/**
	 * <p>
	 * 取消卷制品分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的卷制品对象。
	 * <li>修改卷制品信息。
	 * <li>为取消分配卷制品，生成分配操作记录对象
	 * <li>修改订货合同的ETL指示量和ETL合格量
	 * <li>修改取消分配的指示对象状态为：取消分配。同时，判断订货合同是否还有分配的记录。如果没有，则修改订货合同的状态为：仕样确认；
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 * 
	 * @param jbnos
	 * @param dhuoTp
	 * @param user
	 * @param zsdxTp
	 * @return String
	 */
	private String fpqxJzp(List<String> jbnos, DhuoTp dhuoTp, User user,
			ZsdxTp zsdxTp) {
		List<ZpngTp> zpngTps = zpBO.listZp(jbnos);
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择制品").toString();
		}
		// 分配取消操作时间
		Date date = new Date();
		// 统计取消分配中间品的重量
		Integer qxzl = 0;
		// 分配NO
		String fpno = zsdxTp.getFpno();
		// 余材状况(素材、中间品、剪切板制品或卷制品)
		String yczk = zsdxTp.getYczk();
		// 分配No对应的分配重量
		Integer cqzs = zsdxTp != null ? zsdxTp.getCqzs() : 0;
		// 取消分配操作记录
		ZkfpCzjl czjl = null;
		// 合格品的重量
		double hgl = 0;
		for (ZpngTp zpngTp : zpngTps) {
			zpngTp.setFpno(null);
			zpngTp.setFpyc(EFpyc.YC.key);
			zpngTp.setDhno(null);
			zpngTp.setAbbr(null);
			zpngTp.setUpda(date);
			// 修改中间品信息
			zpBO.update(zpngTp);
			// 统计取消分配量
			qxzl += zpngTp.getZpzl();
			// 为取消分配中间品，生成一个取消分配操作记录对象
			czjl = parseCzjl(zpngTp.getJbno(), dhuoTp.getDhno(), dhuoTp.getLine(), yczk, Czlx.key2.key, fpno, date, user, null);
			// 保存操作记录
			czjlBO.save(czjl);
			// 记录余材中间品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 取消指示对象
		zsBO.doQxfp(jbnos, fpno);
		if (!zsBO.isFpForDh(dhuoTp.getDhno() + dhuoTp.getLine())) {
			dhuoTp.setStat(DhStat.confirm.key);
		}
		// 修改订货合同的ETL指示量
		double etlz = dhuoTp.getEtlz() != null ? dhuoTp.getEtlz() : 0;
		dhuoTp.setEtlz(etlz - qxzl / 1000d);
		// 当中间品为合格品（产量为1或9，处置为空）时，要修改中间品对应订货合同的ETL合格量
		double etlh = dhuoTp.getEtlh() != null ? dhuoTp.getEtlh() : 0;
		dhuoTp.setEtlh(etlh - hgl);
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 修改指示对象的分配量和修改日期
		zsBO.updateZsdx(fpno, cqzs - qxzl, date);
		// 更新页面上的未分配量和分配指示量
		FpVO fpVO = parseFpVO(zsdxTp, dhuoTp);
		fpVO.setCqzs((cqzs - qxzl) / 1000d);
		return new Result(fpVO).toJsObject();
	}

	/**
	 * <p>
	 * 取消中间品分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的素材对象。
	 * <li>修改中间品信息。
	 * <li>为取消分配素材，生成分配操作记录对象
	 * <li>修改订货合同的ETL指示量和ETL合格量
	 * <li>修改取消分配的指示对象状态为：取消分配。同时，判断订货合同是否还有分配的记录。如果没有，则修改订货合同的状态为：仕样确认；
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 * 
	 * @param jbnos
	 * @param dhuoTp
	 * @param user
	 * @param zsdxTp
	 * @return String
	 */
	private String fpqxZjp(List<String> jbnos, DhuoTp dhuoTp, User user,
			ZsdxTp zsdxTp) {
		List<ZpngTp> zpngTps = zpBO.listZp(jbnos);
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择制品").toString();
		}
		// 分配取消操作时间
		Date date = new Date();
		// 统计取消分配中间品的重量
		Integer qxzl = 0;
		// 分配NO
		String fpno = zsdxTp.getFpno();
		// 余材状况(素材、中间品、剪切板制品或卷制品)
		String yczk = zsdxTp.getYczk();
		// 分配No对应的分配重量
		Integer cqzs = zsdxTp != null ? zsdxTp.getCqzs() : 0;
		// 取消分配操作记录
		ZkfpCzjl czjl = null;
		// 合格品的重量
		double hgl = 0;
		for (ZpngTp zpngTp : zpngTps) {
			zpngTp.setFpno(null);
			zpngTp.setFpyc(EFpyc.YC.key);
			zpngTp.setDhno(null);
			zpngTp.setAbbr(null);
			zpngTp.setUpda(date);
			// 修改中间品信息
			zpBO.update(zpngTp);
			// 统计取消分配量
			qxzl += zpngTp.getZpzl();
			// 为取消分配中间品，生成一个取消分配操作记录对象
			czjl = parseCzjl(zpngTp.getJbno(), dhuoTp.getDhno(), dhuoTp.getLine(), yczk, Czlx.key2.key, fpno, date, user, null);
			// 保存操作记录
			czjlBO.save(czjl);
			// 记录余材中间品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 取消指示对象
		zsBO.doQxfp(jbnos, fpno);
		if (!zsBO.isFpForDh(dhuoTp.getDhno() + dhuoTp.getLine())) {
			dhuoTp.setStat(DhStat.confirm.key);
		}
		// 修改订货合同的ETL指示量
		double etlz = dhuoTp.getEtlz() != null ? dhuoTp.getEtlz() : 0;
		dhuoTp.setEtlz(etlz - qxzl / 1000d);
		// 当中间品为合格品（产量为1或9，处置为空）时，要修改中间品对应订货合同的ETL合格量
		double etlh = dhuoTp.getEtlh() != null ? dhuoTp.getEtlh() : 0;
		dhuoTp.setEtlh(etlh - hgl);
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 修改指示对象的分配量和修改日期
		zsBO.updateZsdx(fpno, cqzs - qxzl, date);
		// 更新页面上的未分配量和分配指示量
		FpVO fpVO = parseFpVO(zsdxTp, dhuoTp);
		fpVO.setCqzs((cqzs - qxzl) / 1000d);
		return new Result(fpVO).toJsObject();
	}

	@Override
	public String doFp(FpVO vo, User user) {
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(vo.getDhno(), vo.getLine()));
		if (dhuoTp == null) {
			return new Result(-1, "订货合同不存在").toString();
		}
		// 订货合同状态为仁样确认和已分配的才能做分配操作
		if (!(DhStat.assign.key.equals(dhuoTp.getStat()) || DhStat.confirm.key.equals(dhuoTp.getStat()))) {
			return new Result(-1, "订货合同状态为"
					+ DhStat.get(dhuoTp.getStat()).name() + "。不能对其做分配操作").toString();
		}
		if (!DhConstants.JSTJ_DL.equals(dhuoTp.getJstj())) {
			return new Result(-1, "该订货合同未设置结算条件").toString();
		}
		// 品种代码
		String pzno = dhuoTp.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-1, "该订货合同信息有误,没有设置品种代码").toString();
		}
		Code118 code118 = Code118.get(pzno.substring(0, 1));
		if (code118 == null) {
			return new Result(-1, "该订货合同信息有误,品种代码在码表中不存在").toString();
		}
		//
		if (Code118.coil.key.equals(code118.key)
				&& EXpzk.BZP.key.equals(vo.getXpzk())) {
			return new Result(-1, "不能将剪切板制品分配给钢卷类型的订货合同").toString();
		}
		if (!EXpzk.BZP.key.equals(vo.getXpzk())
				&& ZkfpConstance.ZKFP_ZXBB_1.equals(vo.getZxbb())) {
			return new Result(-1, "只有剪切板制品才能去做分选操作").toString();
		}
		if (vo.getWfpl() < vo.getCqzs()
				&& !ZkfpConstance.ZKFP_CLFP_1.equals(vo.getClfp())) {
			return new Result(-1, "分配指示量大于该订货合同的未分配量,要允许超出才能分配").toString();
		}
		if (EXpzk.SC.key.equals(vo.getXpzk())) {
			// 分配素板
			return fpSc(vo, user, dhuoTp);
		}
		if (EXpzk.ZJP.key.equals(vo.getXpzk())) {
			// 分配中间品
			return fpZjp(vo, user, dhuoTp);
		}
		if (EXpzk.JZP.key.equals(vo.getXpzk())) {
			// 分配卷制品
			return fpJzp(vo, user, dhuoTp);
		}
		// 分配剪切板制品
		return fpBzp(vo, user, dhuoTp);
	}

	/**
	 * <p>
	 * 分配素板。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的素材对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断素材是否可以用来做分配操作。
	 * <li>判断素材与订货合同的信息是否配匹。
	 * <li>根据素材信息，生成指示对象。
	 * <li>根据素材信息，生成分配操作记录对象。
	 * <li>如果素材信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>设置素材的分配信息。
	 * <li>修改订货合同的分配量。
	 * </ul>
	 * 
	 * @param vo
	 * @param user
	 * @param dhuoTp
	 * @return String
	 */
	private String fpSc(FpVO vo, User user, DhuoTp dhuoTp) {
		// 素板分配
		List<YcaiTp> ycaiTps = ycaitpBO.find(vo.getJbnos());
		if (ycaiTps.size() == 0) {
			return new Result(-1, "未选择要做分配操作的素板").toString();
		}
		// 生成分配号
		String fpno = seqBo.sequence(Seq.fpno.name);
		if (fpno == null || fpno.isEmpty()) {
			return new Result(-1, "系统生成在库分配No失败").toString();
		}
		// 分配操作时间
		Date date = new Date();
		// 分配号
		fpno = vo.getXpzk() + fpno;
		// 指示对象
		ZsdxTp zsdxTp = null;
		// 分配操作记录
		ZkfpCzjl czjl = null;
		// 分配操作错误记录
		ZkfpCwjl cwjl = null;
		// 素材与订货合同是否配匹标识
		Sfpp sfpp = null;
		for (YcaiTp ycaiTp : ycaiTps) {
			// 判断该素材是否为符合做分配指示
			this.checkYcai(ycaiTp);
			// 判断该素材与订货合同是否配匹
			sfpp = this.getSfpp(ycaiTp, null, dhuoTp, vo.getXpzk(), vo.getJbkb());
			if (!(Sfpp.match.key.equals(sfpp.key) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
				throw new CocoException(-1, ycaiTp.getJbno() + "素材与订货合同信息不匹配");
			}
			// 记录指示对象
			zsdxTp = parseZsdx(ycaiTp, null, vo, fpno, date, sfpp);
			zsBO.saveZsdX(zsdxTp);
			// 为分配素材生成分配操作记录
			czjl = parseCzjl(ycaiTp.getJbno(), vo.getDhno(), vo.getLine(), vo.getXpzk(), Czlx.key1.key, fpno, date, user, sfpp.key);
			czjlBO.save(czjl);
			if (!Sfpp.match.equals(sfpp.key)) {
				// 如果现品信息与订货合同信息不匹配。要分配错误表中新增一条信息
				cwjl = parseCwjl(ycaiTp, null, czjl);
				cwjlDAO.save(cwjl);
			}
			ycaiTp.setFpno(fpno);
			ycaiTp.setFpyc(EFpyc.FP.key);
			ycaiTp.setDhno(vo.getDhno() + vo.getLine());
			ycaiTp.setAbbr(dhuoTp.getAbbr());
			ycaiTp.setSczt(YbScStat.stat0.stat);
			ycaiTp.setStat(YbStat.YFP.stat);
			ycaiTp.setUpda(date);
			// 修改素板信息
			ycaitpBO.updateEntity(ycaiTp);
		}
		// 修改订货合同的分配量
		double fpln = dhuoTp.getFpln() != null ? dhuoTp.getFpln() : 0;
		dhuoTp.setFpln(fpln + vo.getCqzs());
		// 修改订货合同的分配积累量
		double fpjl = dhuoTp.getFpjl() != null ? dhuoTp.getFpjl() : 0;
		dhuoTp.setFpjl(fpjl + vo.getCqzs());
		dhuoTp.setStat(DhStat.assign.key);
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 设置生产指示单的分配量
		setSczs(vo.getSczsId(), vo.getCqzs());
		return new Result(1, fpno).toString();
	}

	/**
	 * <p>
	 * 设置生产指示单的分配量
	 * </p>
	 * 
	 * @param id
	 * @param fpln
	 */
	private void setSczs(String id, double fpln) {
		if (id == null || id.isEmpty()) return;
		SczsTp entity = sczsBO.get(id);
		if (entity == null) return;
		double $fpln = entity.getFpln() != null ? entity.getFpln() : 0d;
		entity.setFpln($fpln + fpln);
		entity.setStat(SczsStat.assign.key);
		sczsBO.saveOrUpdate(entity);
	}

	/**
	 * <p>
	 * 分配中间品。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的中间品对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断中间品是否可以用来做分配操作。
	 * <li>判断中间品与订货合同的信息是否配匹。
	 * <li>根据中间品信息，生成分配操作记录对象。
	 * <li>如果中间品信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>将中间品分配给剪切板合同时写指示对象。
	 * <li>设置中间品的分配信息。
	 * <li>修改订货合同的ETL指示量和ETL分配量。
	 * </ul>
	 * 
	 * @param vo
	 * @param user
	 * @param dhuoTp
	 * @return String
	 */
	private String fpZjp(FpVO vo, User user, DhuoTp dhuoTp) {
		// 中间品分配
		List<ZpngTp> zpngTps = zpBO.listZp(vo.getJbnos());
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择要做分配操作的中间品").toString();
		}
		// 生成分配号
		String fpno = seqBo.sequence(Seq.fpno.name);
		if (fpno == null || fpno.isEmpty()) {
			return new Result(-1, "系统生成在库分配No失败").toString();
		}
		// 分配操作时间
		Date date = new Date();
		// 分配号
		fpno = vo.getXpzk() + fpno;
		// 合格品的重量
		double hgl = 0;
		// 分配操作记录
		ZkfpCzjl czjl = null;
		// 分配操作错误记录
		ZkfpCwjl cwjl = null;
		// 中间品与订货合同是否匹配标识
		Sfpp sfpp;
		// 指示对象
		ZsdxTp zsdxTp = null;
		// 要判断该订货合同是剪切板合同还是钢卷合同。如果是剪切板合同，则会存在中间品转为该订货合同的中间品（堆场不变）、外销卷成品转为该订货合同中间品（该分配就是将外销卷成品用来做剪切操作，堆场由C变为D）。
		// 反之，是钢卷合同，则会存在中间品转为该订货合同外销卷成品(将原本是用做剪切的卷变为卷成品，堆场由D变为C)、外销卷成品转成该订货合同外销卷成品(堆场不变)
		for (ZpngTp zpngTp : zpngTps) {
			// 判断该中间品是否为符合做分配指示
			this.checkZp(zpngTp);
			// 判断该中间品与订货合同是否配匹
			sfpp = this.getSfpp(null, zpngTp, dhuoTp, vo.getXpzk(), vo.getJbkb());
			if (!(Sfpp.match.key.equals(sfpp.key) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
				throw new CocoException(-1, zpngTp.getJbno() + "中间品与订货合同信息不匹配");
			}
			// 为分配中间品生成分配操作记录
			czjl = parseCzjl(zpngTp.getJbno(), vo.getDhno(), vo.getLine(), vo.getXpzk(), Czlx.key1.key, fpno, date, user, sfpp.key);
			// 保存新增的在库分配操作记录
			czjlBO.save(czjl);
			if (!Sfpp.match.equals(sfpp.key)) {
				// 如果中间品信息与订货合同信息不匹配。要分配错误表中新增一条信息
				cwjl = parseCwjl(null, zpngTp, czjl);
				cwjlDAO.save(cwjl);
			}
			// 中间品分配给剪切板合同时写指示对象
			if (!Code118.coil.key.equals(dhuoTp.getPzno().substring(0, 1))) {
				zsdxTp = parseZsdx(null, zpngTp, vo, fpno, date, sfpp);
				zsBO.saveZsdX(zsdxTp);
			}
			else {
				// 中间品分配给钢卷订货合同时。修改现品状况为钢卷制品
				zpngTp.setXpzk(EXpzk.JZP.key);
			}
			parseZp(zpngTp, dhuoTp, fpno, EFpyc.FP.key, vo.getJbkb());
			// 修改中间品信息
			zpBO.update(zpngTp);
			// 记录中间品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 修改订货合同的ETL指示量
		double etlz = dhuoTp.getEtlz() != null ? dhuoTp.getEtlz() : 0;
		dhuoTp.setEtlz(etlz + vo.getCqzs());
		// 当中间品为合格品（产量为1或9，处置为空）时，要修改中间品对应订货合同的ETL合格量
		double etlh = dhuoTp.getEtlh() != null ? dhuoTp.getEtlh() : 0;
		dhuoTp.setEtlh(etlh + hgl);
		dhuoTp.setStat(DhStat.assign.key);
		// 修改订货合同的分配积累量
		double fpjl = dhuoTp.getFpjl() != null ? dhuoTp.getFpjl() : 0;
		dhuoTp.setFpjl(fpjl + vo.getCqzs());
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 设置生产指示单的分配量
		setSczs(vo.getSczsId(), vo.getCqzs());
		return new Result(1, fpno).toString();
	}

	/**
	 * <p>
	 * 分配卷制品。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的制品对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断制品是否可以用来做分配操作。
	 * <li>判断制品与订货合同的信息是否配匹。
	 * <li>根据制品信息，生成指示对象。
	 * <li>如果是钢卷成品分配给剪切板的合同，则要生成指示对象。
	 * <li>根据制品信息，生成分配操作记录对象。
	 * <li>如果制品信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>设置制品的分配信息。
	 * <li>对钢卷成品做分配操作时，要修改订货合同的ETL指示量和ETL分配量；对剪切剪切板制品做分配操作是，要修改订货合同的SL指示量和SL分配量。
	 * </ul>
	 * 
	 * @param vo
	 * @param user
	 * @param dhuoTp
	 * @return String
	 */
	private String fpJzp(FpVO vo, User user, DhuoTp dhuoTp) {
		// 制品分配
		List<ZpngTp> zpngTps = zpBO.listZp(vo.getJbnos());
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择要做分配操作的制品").toString();
		}
		// 生成分配号
		String fpno = seqBo.sequence(Seq.fpno.name);
		if (fpno == null || fpno.isEmpty()) {
			return new Result(-1, "系统生成在库分配No失败").toString();
		}
		// 分配操作时间
		Date date = new Date();
		// 分配号
		fpno = vo.getXpzk() + fpno;
		// 分配操作记录
		ZkfpCzjl czjl = null;
		// 分配操作错误记录
		ZkfpCwjl cwjl = null;
		// 制品与订货合同是否匹配标识
		Sfpp sfpp = null;
		// 指示对象
		ZsdxTp zsdxTp = null;
		// 合格品的重量
		double hgl = 0;
		for (ZpngTp zpngTp : zpngTps) {
			// 判断该制品是否为符合做分配指示
			this.checkZp(zpngTp);
			// 判断该制品与订货合同是否配匹
			sfpp = this.getSfpp(null, zpngTp, dhuoTp, vo.getXpzk(), vo.getJbkb());
			if (!(Sfpp.match.key.equals(sfpp.key) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
				throw new CocoException(-1, zpngTp.getJbno() + "制品与订货合同信息不匹配");
			}
			// 为分配制品生成分配操作记录
			czjl = parseCzjl(zpngTp.getJbno(), vo.getDhno(), vo.getLine(), vo.getXpzk(), Czlx.key1.key, fpno, date, user, sfpp.key);
			// 保存新增的在库分配操作记录
			czjlBO.save(czjl);
			if (!Sfpp.match.equals(sfpp.key)) {
				// 如果制品信息与订货合同信息不匹配。要分配错误表中新增一条信息
				cwjl = parseCwjl(null, zpngTp, czjl);
				cwjlDAO.save(cwjl);
			}
			parseZp(zpngTp, dhuoTp, fpno, EFpyc.FP.key, vo.getJbkb());
			if (!Code118.coil.key.equals(dhuoTp.getPzno().substring(0, 1))) {
				// 剪切板订货合同
				// if (EXpzk.JZP.key.equals(zpngTp.getXpzk())) {
				// 将外销卷成品转为中间品去做剪切板
				vo.setXpzk(EXpzk.ZJP.key);
				zsdxTp = parseZsdx(null, zpngTp, vo, fpno, date, sfpp);
				zsBO.saveZsdX(zsdxTp);
				zpngTp.setXpzk(EXpzk.ZJP.key);
				// }
				// zpngTp.setDuic(DC.D.name);
				// zpngTp.setKw(DC.D.name);
			}
			// else {
			// 钢卷订货合同
			// zpngTp.setXpzk(EXpzk.JZP.key);
			// zpngTp.setDuic(DC.C.name);
			// zpngTp.setKw(DC.C.name);
			// }
			// 修改制品信息
			zpBO.update(zpngTp);
			// 记录制品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 修改订货合同的ETL指示量
		double elzs = dhuoTp.getSlzs() != null ? dhuoTp.getSlzs() : 0;
		dhuoTp.setEtlz(elzs + vo.getCqzs());
		// 当制品为合格品（产量为1或9，处置为空）时，要修改制品对应订货合同的ETL合格量
		double elhg = dhuoTp.getSlhg() != null ? dhuoTp.getSlhg() : 0;
		dhuoTp.setEtlh(elhg + hgl);
		dhuoTp.setStat(DhStat.assign.key);
		// 修改订货合同的分配积累量
		double fpjl = dhuoTp.getFpjl() != null ? dhuoTp.getFpjl() : 0;
		dhuoTp.setFpjl(fpjl + vo.getCqzs());
		// 修改订货合同
		dhBO.update(dhuoTp);
		// 设置生产指示单的分配量
		setSczs(vo.getSczsId(), vo.getCqzs());
		return new Result(1, fpno).toString();
	}

	/**
	 * <p>
	 * 分配剪切板制品。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的制品对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断制品是否可以用来做分配操作。
	 * <li>判断制品与订货合同的信息是否配匹。
	 * <li>根据制品信息，生成指示对象。
	 * <li>如果是钢卷成品分配给剪切板的合同，则要生成指示对象。
	 * <li>根据制品信息，生成分配操作记录对象。
	 * <li>如果制品信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>设置制品的分配信息。
	 * <li>对剪切剪切板制品做分配操作是，要修改订货合同的SL指示量和SL分配量。
	 * </ul>
	 * 
	 * @param vo
	 * @param user
	 * @param dhuoTp
	 * @return String
	 */
	private String fpBzp(FpVO vo, User user, DhuoTp dhuoTp) {
		// 制品分配
		List<ZpngTp> zpngTps = zpBO.listZp(vo.getJbnos());
		if (zpngTps.size() == 0) {
			return new Result(-1, "未选择要做分配操作的制品").toString();
		}
		// 生成分配号
		String fpno = seqBo.sequence(Seq.fpno.name);
		if (fpno == null || fpno.isEmpty()) {
			return new Result(-1, "系统生成在库分配No失败").toString();
		}
		// 分配操作时间
		Date date = new Date();
		// 分配号
		fpno = vo.getXpzk() + fpno;
		// 分配操作记录
		ZkfpCzjl czjl = null;
		// 分配操作错误记录
		ZkfpCwjl cwjl = null;
		// 制品与订货合同是否匹配标识
		Sfpp sfpp = null;
		// 合格品的重量
		double hgl = 0;
		for (ZpngTp zpngTp : zpngTps) {
			// 判断该制品是否为符合做分配指示
			this.checkZp(zpngTp);
			// 判断该制品与订货合同是否配匹
			sfpp = this.getSfpp(null, zpngTp, dhuoTp, vo.getXpzk(), vo.getJbkb());
			if (!(Sfpp.match.key.equals(sfpp.key) || ZkfpConstance.ZKFP_QZBJ_1.equals(vo.getQzbj()))) {
				throw new CocoException(-1, zpngTp.getJbno() + "制品与订货合同信息不匹配");
			}
			// 为分配制品生成分配操作记录
			czjl = parseCzjl(zpngTp.getJbno(), vo.getDhno(), vo.getLine(), vo.getXpzk(), Czlx.key1.key, fpno, date, user, sfpp.key);
			// 保存新增的在库分配操作记录
			czjlBO.save(czjl);
			if (!Sfpp.match.equals(sfpp.key)) {
				// 如果制品信息与订货合同信息不匹配。要分配错误表中新增一条信息
				cwjl = parseCwjl(null, zpngTp, czjl);
				cwjlDAO.save(cwjl);
			}
			parseZp(zpngTp, dhuoTp, fpno, EFpyc.FP.key, vo.getJbkb());
			// 修改制品信息
			zpBO.update(zpngTp);
			// 记录制品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(zpngTp.getChan()) || ChanType.bl.key.equals(zpngTp.getChan()))
					&& (zpngTp.getCzdm() == null || zpngTp.getCzdm().isEmpty())) {
				hgl += (zpngTp.getZpzl() / 1000d);
			}
		}
		// 修改订货合同的SL指示量
		double slzs = dhuoTp.getSlzs() != null ? dhuoTp.getSlzs() : 0;
		dhuoTp.setSlzs(slzs + vo.getCqzs());
		// 当制品为合格品（产量为1或9，处置为空）时，要修改制品对应订货合同的SL合格量
		double slhg = dhuoTp.getSlhg() != null ? dhuoTp.getSlhg() : 0;
		dhuoTp.setSlhg(slhg + hgl);
		dhuoTp.setStat(DhStat.assign.key);
		// 修改订货合同的分配积累量
		double fpjl = dhuoTp.getFpjl() != null ? dhuoTp.getFpjl() : 0;
		dhuoTp.setFpjl(fpjl + vo.getCqzs());
		// 修改订货合同
		dhBO.update(dhuoTp);
		return new Result(1, fpno).toString();
	}

	@Override
	public Sfpp getSfpp(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk, String jbkb) {
		try {
			if (Code119.prime.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为一级品时
				return comparePrime(ycaiTp, zpngTp, dhuoTp, xpzk, jbkb);
			}
			else if (Code119.grade2.key.equals(dhuoTp.getPzno().substring(1))
					|| Code119.grade3.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为O/R1级或O/R2级时
				return compareOr(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			else if (Code119.grade4.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为发生品时
				return compareFs(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			else if (Code119.grade5.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为端板时
				return compareNs(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
		}
		catch (Exception e) {
			return Sfpp.nmatch;
		}
		return Sfpp.match;
	}

	@Override
	public List<PError> findCompareErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk) {
		List<CError> errors = null;
		if (Code119.prime.key.equals(dhuoTp.getPzno().substring(1))) {
			// 当订货合同为一级品时
			errors = findComparePrimeErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		}
		else if (Code119.grade2.key.equals(dhuoTp.getPzno().substring(1))
				|| Code119.grade3.key.equals(dhuoTp.getPzno().substring(1))) {
			// 当订货合同为O/R1级或O/R2级时
			errors = findCompareOrErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		}
		else if (Code119.grade4.key.equals(dhuoTp.getPzno().substring(1))) {
			// 当订货合同为发生品时
			errors = findCompareFsErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		}
		else {
			// 当订货合同为端板时
			errors = findCompareNsErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		}
		int size = errors.size();
		if (size == 0) {
			return new ArrayList<PError>();
		}
		List<PError> perrors = new ArrayList<PError>();
		PError perror = null;
		int i = 0;
		CError error = null;
		while (i < size && (error = errors.get(i++)) != null) {
			if (i % ZkfpConstance.PAGE_SIZE == 1) {
				perror = new PError();
				perror.getErrors().add(error);
				perrors.add(perror);
				continue;
			}
			perror.getErrors().add(error);
		}
		return perrors;
	}

	/**
	 * <p>
	 * 当订货合同为一级品时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="6">素材</td>
	 * <td>运用规格</td>
	 * <td align="left">素材的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">素材的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">素材的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，素材的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">素材的钢种种类必等于订货合同的品种第一位</td>
	 * </tr>
	 * <tr>
	 * <td>是否保税</td>
	 * <td align="left">素材的是否保税必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="31">剪切板制品</td>
	 * <td>品种第一位</td>
	 * <td align="left">制品的品种第一位必等于订货合同的品种第一位</td>
	 * </tr>
	 * <tr>
	 * <td>内外销</td>
	 * <td align="left">制品的内外销必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">制品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，制品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，制品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，制品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽-长</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>制品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>直角度</td>
	 * <td align="left">当制品的直角度&gt;订货合同的直角度时，匹配错误</td>
	 * </tr>
	 * <tr>
	 * <td>再剪边标记</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的再剪边标记为"T"，制品的再剪边标记为空时，匹配错误
	 * <li>订货合同的再剪边标记为"N"，制品的再剪边标记不为空时，匹配错误
	 * <li>订货的再剪边标记为空时，不检查 </div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">制品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>压延方向标记</td>
	 * <td align="left">制品的压延方向标记必等于订货合同的压延方向标记</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">制品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">制品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>保护标记</td>
	 * <td align="left">制品的保护标记必等于订货合同的保护标记（除制品的保护标记为非空，而订货合同的保护标记为空时）</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">制品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>交付重量内容</td>
	 * <td align="left">制品的交付重量内容必等于订货合同的交付重量内容</td>
	 * </tr>
	 * <tr>
	 * <td>板材枚数</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的零头下限单为N时，订货合同的捆包指定张数-值不等于制品的张数（包装单位），则配匹错误
	 * <li>订货合同的零头下限单位是P时，订货合同的零头下限值&gt;制品的捆包指定张数，则配匹错误
	 * <li>订货合同的零头下限单位为B时，订货合同的零头下限值&gt;制品的捆包指定张数，则配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">制品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>垫木方向</td>
	 * <td align="left">制品的垫木方向必等于订货合同的垫木方向</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">制品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">制品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">制品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">制品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">制品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，制品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>制品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>制品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>制品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>硬度</td>
	 * <td align="left">当订货合同的硬度.下限&lt;=制品的实绩硬度&lt;=订货合同的硬度.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="27">钢卷成品</td>
	 * <td>品种第一位</td>
	 * <td align="left">订货合同的品种第一位必为2即为钢卷合同</td>
	 * </tr>
	 * <tr>
	 * <td>内外销</td>
	 * <td align="left">钢卷成品的内外销必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">钢卷成品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，钢卷成品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，钢卷成品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，钢卷成品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>钢卷成品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td> <tdalign="left"钢卷成品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>内径保护筒标记</td>
	 * <td align="left">钢卷成品的内径保护筒标记必等于订货合同的内径保护筒标记</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">钢卷成品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">钢卷成品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">钢卷成品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">钢卷成品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>交付重量内容</td>
	 * <td align="left">钢卷成品的交付重量内容必等于订货合同的交付重量内容</td>
	 * </tr>
	 * <tr>
	 * <td>捆包重量</td>
	 * <td align="left">订货合同的捆包重量指定.下限&lt;=钢卷成品的制品重量&lt;=订货合同的捆包重量指定.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>溶接部有无(ETL)</td>
	 * <td align="left">钢卷成品的溶接个数.ETL&gt;订货合同的外W时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>内径</td>
	 * <td align="left">钢卷成品的内径必等于订货合同的内径</td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">钢卷成品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">钢卷成品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">钢卷成品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">钢卷成品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">钢卷成品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">钢卷成品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">钢卷成品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，钢卷成品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>硬度</td>
	 * <td align="left">订货合同的硬度.下限&lt;=钢卷成品的实绩硬度&lt;=订货合同的硬度.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="20">中间品</td>
	 * <td>品种第一位</td>
	 * <td align="left">订货合同的品种第一位2即为钢卷合同是，不配匹</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">中间品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，中间品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，中间品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，中间品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">中间品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">中间品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>中间品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">制品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">中间品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">中间品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">中间品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">中间品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">中间品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">中间品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">中间品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">中间品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">中间品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当中间品剪切材时，中间品的现品尺寸.宽不等于订货合同的SL装入宽时，配匹错误（如果订货合同的SL装入宽为空时
	 * ，用订货合同的订货尺寸.宽来检查）</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，中间品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>中间品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>中间品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>中间品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return Sfpp
	 */
	private Sfpp comparePrime(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk, String jbkb) {
		// 规格代码
		String ggno = dhuoTp.getGgno();
		// 品种
		String pzno = dhuoTp.getPzno();
		// 比较装入宽时，如果订货合同的装入宽为空时，则用订货合同的尺寸.宽
		double zrkn = dhuoTp.getZrkn() != null && dhuoTp.getZrkn() > 0 ? dhuoTp.getZrkn()
				: dhuoTp.getKuan();
		// 以下是素板是否配匹比较的项目
		if (EXpzk.SC.key.equals(xpzk)) {
			// 运用规格
			if (!dhuoTp.getYuny().equals(ycaiTp.getYuny())) {
				return Sfpp.nmatch;
			}
			// 尺寸-厚
			if (dhuoTp.getHouu().doubleValue() != ycaiTp.getXpho().doubleValue()) {
				return Sfpp.nmatch;
			}
			double xpkn = ycaiTp.getXpkn() != null ? ycaiTp.getXpkn() : 0;
			if (ZkfpConstance.ZKFP_JBKB_0.equals(jbkb)
					&& !CodeJian.T.key.equals(dhuoTp.getJian())) {
				if (xpkn != zrkn) {
					return Sfpp.nmatch;
				}
			}
			else {
				if ((zrkn + ZkfpConstance.zrknS) > xpkn
						|| (zrkn + ZkfpConstance.zrknX) < xpkn) {
					return Sfpp.nmatch;
				}
			}
			// // 尺寸-宽
			// if (dhuoTp.getKuan().doubleValue() !=
			// ycaiTp.getXpkn().doubleValue()) {
			// return Sfpp.nmatch;
			// }
			// // SL装入宽
			// if (zrkn != ycaiTp.getXpkn().doubleValue()) {
			// return Sfpp.nmatch;
			// }
			// 钢种种类
			if (!ycaiTp.getGzlx().equals(ggno.substring(0, 1))) {
				return Sfpp.nmatch;
			}
			// 是否保税
			// CodeNwx codeNwx;
			// if ((codeNwx = CodeNwx.get(dhuoTp.getNwai())) != null
			// && !codeNwx.type.equals(ycaiTp.getSfbs())) {
			// return Sfpp.nmatch;
			// }
			EFace eFace = EFace.get(dhuoTp.getFace());
			if (eFace != null && !eFace.contain(ycaiTp.getFace())) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 以下是剪切板制品、钢卷制品和中间品是否配匹比较项目
		// 运用规格
		if (!dhuoTp.getYuny().equals(zpngTp.getYuny())) {
			return Sfpp.nmatch;
		}
		// 订货合同分配等级
		String fpdj = dhuoTp.getFpdj();
		// 制品等级
		String deng = zpngTp.getDeng();
		// 分配等级(第一位)(发生品的等级长度为两位)
		if (deng.length() != 3
				|| ((Code103.G.key.equals(fpdj.substring(0, 1)) || Code103.P.key.equals(fpdj.substring(0, 1))) && !fpdj.substring(0, 1).equals(deng.substring(0, 1)))) {
			return Sfpp.nmatch;
		}
		// 分配等级(第二位和第三位)
		if (deng.length() != 3
				|| ((Code104.get(fpdj.substring(1, 2)).value < Code104.get(deng.substring(1, 2)).value) || (Code105.get(fpdj.substring(2)).value < Code105.get(deng.substring(2)).value))) {
			return Sfpp.nmatch;
		}
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸允许公差厚.上限
		if (dhuoTp.getHszi() != null
				&& zpngTp.getHszi() != null
				&& zpngTp.getHszi().doubleValue() < dhuoTp.getHszi().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸允许公差厚.下限
		if (dhuoTp.getHxzi() != null
				&& zpngTp.getHxzi() != null
				&& zpngTp.getHxzi().doubleValue() > dhuoTp.getHxzi().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸允许公差宽.上限
		if (dhuoTp.getKszi() != null
				&& zpngTp.getKszi() != null
				&& zpngTp.getKszi().doubleValue() < dhuoTp.getKszi().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸允许公差宽.下限
		if (dhuoTp.getKxzi() != null
				&& zpngTp.getKxzi() != null
				&& zpngTp.getKxzi().doubleValue() > dhuoTp.getKxzi().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 涂油种类
		if (dhuoTp.getYtyp() != null
				&& !dhuoTp.getYtyp().equals(zpngTp.getYtyp())) {
			return Sfpp.nmatch;
		}
		// 化学处理方法
		if (dhuoTp.getHuac() != null
				&& !dhuoTp.getHuac().equals(zpngTp.getHxcl())) {
			return Sfpp.nmatch;
		}
		// K-Plate表示
		if (dhuoTp.getPlat() != null
				&& !dhuoTp.getPlat().equals(zpngTp.getPlat())) {
			return Sfpp.nmatch;
		}
		// 差厚镀锡标记
		if (dhuoTp.getChdx() != null
				&& !dhuoTp.getChdx().equals(zpngTp.getChdx())) {
			return Sfpp.nmatch;
		}
		// 附页KPNO1
		if (dhuoTp.getKpn1Flag() != null
				&& !dhuoTp.getKpn1Flag().equals(zpngTp.getKpn1Flag())
				&& dhuoTp.getKpn1() != null
				&& !dhuoTp.getKpn1().equals(zpngTp.getKpn1())) {
			return Sfpp.nmatch;
		}
		// 附页KPNO2
		if (dhuoTp.getKpn2Flag() != null
				&& !dhuoTp.getKpn2Flag().equals(zpngTp.getKpn2Flag())
				&& dhuoTp.getKpn2() != null
				&& !dhuoTp.getKpn2().equals(zpngTp.getKpn2())) {
			return Sfpp.nmatch;
		}
		// 附页KPNO3
		if (dhuoTp.getKpn3Flag() != null
				&& !dhuoTp.getKpn3Flag().equals(zpngTp.getKpn3Flag())
				&& dhuoTp.getKpn3() != null
				&& !dhuoTp.getKpn3().equals(zpngTp.getKpn3())) {
			return Sfpp.nmatch;
		}
		// 附页KPNO4
		if (dhuoTp.getKpn4Flag() != null
				&& !dhuoTp.getKpn4Flag().equals(zpngTp.getKpn4Flag())
				&& dhuoTp.getKpn4() != null
				&& !dhuoTp.getKpn4().equals(zpngTp.getKpn4())) {
			return Sfpp.nmatch;
		}
		// 产量区分
		if (!ChanType.hg.key.equals(zpngTp.getChan())) {
			return Sfpp.nmatch;
		}
		// 品种第二位
		if (!pzno.substring(1).equals(zpngTp.getPzno().substring(1))) {
			return Sfpp.nmatch;
		}
		// 目标附着量-单位
		if (dhuoTp.getFudw() != null
				&& !dhuoTp.getFudw().equals(zpngTp.getFudw())) {
			return Sfpp.nmatch;
		}
		// 目标附着量-正面
		if (dhuoTp.getFuzm() != null
				&& !dhuoTp.getFuzm().equals(zpngTp.getFuzm())) {
			return Sfpp.nmatch;
		}
		// 目标附着量-反面
		if (dhuoTp.getFufm() != null
				&& !dhuoTp.getFufm().equals(zpngTp.getFufm())) {
			return Sfpp.nmatch;
		}
		// 表面精整
		if (dhuoTp.getFace() != null
				&& !dhuoTp.getFace().equals(zpngTp.getFace())) {
			return Sfpp.nmatch;
		}
		// 钢种种类
		if (Code109.L.key.equals(ggno.substring(0, 1))
				&& (Code109.D.key.equals(zpngTp.getGzlx()) || Code109.M.key.equals(zpngTp.getGzlx()))) {
			return Sfpp.nmatch;
		}
		// 实绩附着量.正面
		if (zpngTp.getSczm() != null
				&& ((dhuoTp.getFuzs() != null && dhuoTp.getFuzs().doubleValue() < zpngTp.getSczm().doubleValue()) || (dhuoTp.getFuzx() != null && dhuoTp.getFuzx().doubleValue() > zpngTp.getSczm().doubleValue()))) {
			return Sfpp.nmatch;
		}
		// 实绩附着量.反面
		if (zpngTp.getScfm() != null
				&& ((dhuoTp.getFufs() != null && dhuoTp.getFufs().doubleValue() < zpngTp.getScfm().doubleValue()) || (dhuoTp.getFufx() != null && dhuoTp.getFufx().doubleValue() > zpngTp.getScfm().doubleValue()))) {
			return Sfpp.nmatch;
		}
		// 以下是剪切板制品是否配匹比较项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 品种第一位
			if (!pzno.substring(0, 1).equals(zpngTp.getPzno().substring(0, 1))) {
				return Sfpp.nmatch;
			}
			// 内外销
			if (!dhuoTp.getNwai().equals(zpngTp.getNwai())) {
				return Sfpp.nmatch;
			}
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				return Sfpp.nmatch;
			}
			// 尺寸允许公差长.上限
			if (dhuoTp.getCszi() != null
					&& zpngTp.getCszi() != null
					&& zpngTp.getCszi().doubleValue() < dhuoTp.getCszi().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 尺寸允许公差长.下限
			if (dhuoTp.getCxzi() != null
					&& zpngTp.getCxzi() != null
					&& zpngTp.getCxzi().doubleValue() > dhuoTp.getCxzi().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 直角度
			if (dhuoTp.getJiao() != null && zpngTp.getJiao() != null
					&& zpngTp.getJiao() > dhuoTp.getJiao()) {
				return Sfpp.nmatch;
			}
			// 再剪边标记
			if (CodeJian.T.key.equals(dhuoTp.getJian())
					&& (zpngTp.getZjbb() == null || zpngTp.getZjbb().isEmpty())) {
				return Sfpp.nmatch;
			}
			if (CodeJian.N.key.equals(dhuoTp.getJian())
					&& zpngTp.getZjbb() != null && !zpngTp.getZjbb().isEmpty()) {
				return Sfpp.nmatch;
			}
			// 压延方向标记
			if (dhuoTp.getYyan() != null
					&& !dhuoTp.getYyan().equals(zpngTp.getYyan())) {
				return Sfpp.nmatch;
			}
			// 保护标记
			if (dhuoTp.getProt() != null
					&& !dhuoTp.getProt().equals(zpngTp.getProt())) {
				return Sfpp.nmatch;
			}
			// 交付重量内容
			if (dhuoTp.getJhnr() != null
					&& !dhuoTp.getJhnr().equals(zpngTp.getJhnr())) {
				return Sfpp.nmatch;
			}
			// // 板材枚数
			// if (CodeLtdw.N.key.equals(dhuoTp.getLtdw())
			// && zpngTp.getKbzs() != dhuoTp.getKbzs()) {
			// return Sfpp.nmatch;
			// }
			// if ((CodeLtdw.P.key.equals(dhuoTp.getLtdw()) ||
			// CodeLtdw.B.key.equals(dhuoTp.getLtdw()))
			// && dhuoTp.getLtzi().intValue() > zpngTp.getKbzs().intValue()) {
			// return Sfpp.nmatch;
			// }
			// 包装形式
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				return Sfpp.nmatch;
			}
			// 垫木方向
			if (dhuoTp.getDmfx() != null
					&& !dhuoTp.getDmfx().equals(zpngTp.getDmfx())) {
				return Sfpp.nmatch;
			}
			Integer llyd = zpngTp.getLlyd();
			// 硬度
			if (llyd != null
					&& ((dhuoTp.getYmax() != null && Double.parseDouble(dhuoTp.getYmax()) < (llyd / 10d)) || (dhuoTp.getYmin() != null && Double.parseDouble(dhuoTp.getYmin()) > (llyd / 10d)))) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 钢卷制品。配匹比较项目
		if (EXpzk.JZP.key.equals(xpzk)) {
			// 品种第一位
			if (!Code118.coil.key.equals(pzno.substring(0, 1))) {
				return Sfpp.nmatch;
			}
			// 内外销(当为钢卷成品时，要配匹)
			if (!dhuoTp.getNwai().equals(zpngTp.getNwai())) {
				return Sfpp.nmatch;
			}
			// 内径保护筒标记(钢卷成品才有内径保护筒标记)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				return Sfpp.nmatch;
			}
			// 交付重量内容(当为钢卷成品时，要配匹)
			if (dhuoTp.getJhnr() != null
					&& !dhuoTp.getJhnr().equals(zpngTp.getJhnr())) {
				return Sfpp.nmatch;
			}
			int zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
			// 捆包重量(钢卷成品才有捆包重量)
			if (dhuoTp.getKbzs() != null
					&& dhuoTp.getKbzx() != null
					&& zpngTp.getKbzs() != null
					&& zpngTp.getKbzx() != null
					&& (dhuoTp.getKbzs().doubleValue() > (zpzl / 1000d) || dhuoTp.getKbzx().doubleValue() < (zpzl / 1000d))) {
				return Sfpp.nmatch;
			}
			// 溶接部有无（ETL）(钢卷成品才有溶接部有无)
			if (dhuoTp.getRjie() != null
					&& zpngTp.getRjet() != null
					&& zpngTp.getRjet().intValue() > Integer.parseInt(dhuoTp.getRjie())) {
				return Sfpp.nmatch;
			}
			// 内径(钢卷成品才有内径)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				return Sfpp.nmatch;
			}
			// 包装形式(当为钢卷成品时，要配匹)
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				return Sfpp.nmatch;
			}
			Integer llyd = zpngTp.getLlyd();
			// 硬度
			if (llyd != null
					&& ((dhuoTp.getYmax() != null && Double.parseDouble(dhuoTp.getYmax()) < (llyd / 10d)) || (dhuoTp.getYmin() != null && Double.parseDouble(dhuoTp.getYmin()) > (llyd / 10d)))) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 中间品。配匹比较项目
		// 品种第一位。
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			return Sfpp.nmatch;
		}
		// SL装入宽(当中间品为剪切材时，要配匹)
		if (zrkn != zpngTp.getXpkn().doubleValue()) {
			return Sfpp.nmatch;
		}
		return Sfpp.match;
	}

	/**
	 * <p>
	 * 当订货合同为一级品时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="6">素材</td>
	 * <td>运用规格</td>
	 * <td align="left">素材的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">素材的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">素材的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，素材的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">素材的钢种种类必等于订货合同的品种第一位</td>
	 * </tr>
	 * <tr>
	 * <td>是否保税</td>
	 * <td align="left">素材的是否保税必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="31">剪切板制品</td>
	 * <td>品种第一位</td>
	 * <td align="left">制品的品种第一位必等于订货合同的品种第一位</td>
	 * </tr>
	 * <tr>
	 * <td>内外销</td>
	 * <td align="left">制品的内外销必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">制品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，制品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，制品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，制品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽-长</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>制品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>直角度</td>
	 * <td align="left">当制品的直角度&gt;订货合同的直角度时，匹配错误</td>
	 * </tr>
	 * <tr>
	 * <td>再剪边标记</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的再剪边标记为"T"，制品的再剪边标记为空时，匹配错误
	 * <li>订货合同的再剪边标记为"N"，制品的再剪边标记不为空时，匹配错误
	 * <li>订货的再剪边标记为空时，不检查 </div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">制品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>压延方向标记</td>
	 * <td align="left">制品的压延方向标记必等于订货合同的压延方向标记</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">制品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">制品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>保护标记</td>
	 * <td align="left">制品的保护标记必等于订货合同的保护标记（除制品的保护标记为非空，而订货合同的保护标记为空时）</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">制品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>交付重量内容</td>
	 * <td align="left">制品的交付重量内容必等于订货合同的交付重量内容</td>
	 * </tr>
	 * <tr>
	 * <td>板材枚数</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的零头下限单为N时，订货合同的捆包指定张数-值不等于制品的张数（包装单位），则配匹错误
	 * <li>订货合同的零头下限单位是P时，订货合同的零头下限值&gt;制品的捆包指定张数，则配匹错误
	 * <li>订货合同的零头下限单位为B时，订货合同的零头下限值&gt;制品的捆包指定张数，则配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">制品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>垫木方向</td>
	 * <td align="left">制品的垫木方向必等于订货合同的垫木方向</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">制品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">制品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">制品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">制品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">制品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，制品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>制品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>制品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>制品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>硬度</td>
	 * <td align="left">当订货合同的硬度.下限&lt;=制品的实绩硬度&lt;=订货合同的硬度.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="27">钢卷成品</td>
	 * <td>品种第一位</td>
	 * <td align="left">订货合同的品种第一位必为2即为钢卷合同</td>
	 * </tr>
	 * <tr>
	 * <td>内外销</td>
	 * <td align="left">钢卷成品的内外销必等于订货合同的内外销</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">钢卷成品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，钢卷成品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，钢卷成品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，钢卷成品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>钢卷成品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td> <tdalign="left"钢卷成品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>内径保护筒标记</td>
	 * <td align="left">钢卷成品的内径保护筒标记必等于订货合同的内径保护筒标记</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">钢卷成品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">钢卷成品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">钢卷成品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">钢卷成品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>交付重量内容</td>
	 * <td align="left">钢卷成品的交付重量内容必等于订货合同的交付重量内容</td>
	 * </tr>
	 * <tr>
	 * <td>捆包重量</td>
	 * <td align="left">订货合同的捆包重量指定.下限&lt;=钢卷成品的制品重量&lt;=订货合同的捆包重量指定.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>溶接部有无(ETL)</td>
	 * <td align="left">钢卷成品的溶接个数.ETL&gt;订货合同的外W时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>内径</td>
	 * <td align="left">钢卷成品的内径必等于订货合同的内径</td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">钢卷成品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">钢卷成品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">钢卷成品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">钢卷成品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">钢卷成品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">钢卷成品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">钢卷成品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，钢卷成品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>硬度</td>
	 * <td align="left">订货合同的硬度.下限&lt;=钢卷成品的实绩硬度&lt;=订货合同的硬度.上限以外时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="20">中间品</td>
	 * <td>品种第一位</td>
	 * <td align="left">订货合同的品种第一位2即为钢卷合同是，不配匹</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">中间品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>分配等级</td>
	 * <td align="left"><div>第一位检查：
	 * <ul>
	 * <li>订货合同的分配等级第一位要求为G时，中间品等级第一位必须为G。
	 * <li>订货合同的分配等级第一位要求为P时，中间品等级第一位必须为P。
	 * <li>订货合同的分配等级第一位要求为K时，中间品等级第一位可以为G、P、K。
	 * </ul>
	 * 除以上情况，均匹配错误<br>
	 * 第二、三位检查： <br>
	 * 订货要求的级别值 &lt; 现品的级别值时，匹配错误（级别值为：1-S；2-A；3-B；4-C；5-D）</div></td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">中间品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">中间品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸允许公差厚-宽</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的尺寸允许差•下限 &gt;订货合同的尺寸允许差•下限
	 * <li>中间品的尺寸允许差•上限 &lt;订货合同的尺寸允许差•上限
	 * </ul>
	 * ，匹配错误</div></td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>化学处理方法</td>
	 * <td align="left">制品的化学处理方法必等于订货合同的化学处理方法</td>
	 * </tr>
	 * <tr>
	 * <td>K-Plate表示</td>
	 * <td align="left">中间品的K-Plate表示必等于订货合同的K-Plate表示</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">中间品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>附页KPNO</td>
	 * <td align="left">中间品的附页KPNO必等于订货合同的附页KPNO</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">中间品的产量代码必等于1</td>
	 * </tr>
	 * <tr>
	 * <td>品种第二位</td>
	 * <td align="left">中间品的品种第二位必等于订货合同的品种第二位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.单位</td>
	 * <td align="left">中间品的目标附着量.单位必等于订货合同的目标附着量.单位</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.正面</td>
	 * <td align="left">中间品的目标附着量.正面必等于订货合同的目标附着量.正面</td>
	 * </tr>
	 * <tr>
	 * <td>目标附着量.反面</td>
	 * <td align="left">中间品的目标附着量.反面必等于订货合同的目标附着量.反面</td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">中间品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当中间品剪切材时，中间品的现品尺寸.宽不等于订货合同的SL装入宽时，配匹错误（如果订货合同的SL装入宽为空时
	 * ，用订货合同的订货尺寸.宽来检查）</td>
	 * </tr>
	 * <tr>
	 * <td>钢种种类</td>
	 * <td align="left">订货合同的规格代码（第一个字符）是'L'，中间品的钢种种类是'D'或'M'时，配匹错误</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>中间品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>中间品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>中间品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return List<CError>
	 */
	private List<CError> findComparePrimeErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk) {
		// 规格代码
		String ggno = dhuoTp.getGgno();
		// 品种
		String pzno = dhuoTp.getPzno();
		// 比较装入宽时，如果订货合同的装入宽为空时，则用订货合同的尺寸.宽
		double zrkn = dhuoTp.getZrkn() != null && dhuoTp.getZrkn() > 0 ? dhuoTp.getZrkn()
				: dhuoTp.getKuan();
		// 记录不配匹的项目
		List<CError> errors = new ArrayList<CError>();
		// 以下是素板是否配匹比较的项目
		if (EXpzk.SC.key.equals(xpzk)) {

			// 运用规格
			if (!dhuoTp.getYuny().equals(ycaiTp.getYuny())) {
				CError error = new CError();
				error.setName("运用规格");
				error.setField1(ycaiTp.getYuny());
				error.setField2(dhuoTp.getYuny());
				errors.add(error);
			}
			// 尺寸-厚
			if (dhuoTp.getHouu().doubleValue() != ycaiTp.getXpho().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.厚");
				error.setField1(ycaiTp.getXpho().toString());
				error.setField2(dhuoTp.getHouu().toString());
				errors.add(error);
			}
			// 尺寸-宽
			if (dhuoTp.getKuan().doubleValue() != ycaiTp.getXpkn().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.宽");
				error.setField1(ycaiTp.getXpkn().toString());
				error.setField2(dhuoTp.getKuan().toString());
				errors.add(error);
			}
			// SL装入宽
			if (zrkn != ycaiTp.getXpkn().doubleValue()) {
				CError error = new CError();
				error.setName("装入宽");
				error.setField1(ycaiTp.getXpkn().toString());
				error.setField2(String.valueOf(zrkn));
				errors.add(error);
			}
			// 钢种种类
			if (!ycaiTp.getGzlx().equals(ggno.substring(0, 1))) {
				CError error = new CError();
				error.setName("钢种种类");
				error.setField1(ycaiTp.getGzlx());
				error.setField2(ggno.substring(0, 1));
				errors.add(error);
			}
			// 是否保税
			CodeNwx codeNwx;
			if ((codeNwx = CodeNwx.get(dhuoTp.getNwai())) != null
					&& !codeNwx.type.equals(ycaiTp.getSfbs())) {
				CError error = new CError();
				error.setName("是否保税");
				error.setField1(ycaiTp.getSfbs());
				error.setField2(dhuoTp.getNwai());
				errors.add(error);
			}
			EFace eFace = EFace.get(dhuoTp.getFace());
			if (eFace != null && !eFace.contain(ycaiTp.getFace())) {
				CError error = new CError();
				error.setName("表面");
				error.setField1(ycaiTp.getFace());
				error.setField2(dhuoTp.getFace());
				errors.add(error);
			}
			return errors;
		}
		// 以下是剪切板制品、钢卷制品和中间品是否配匹比较项目
		// 运用规格
		if (!dhuoTp.getYuny().equals(zpngTp.getYuny())) {
			CError error = new CError();
			error.setName("运用规格");
			error.setField1(zpngTp.getYuny());
			error.setField2(dhuoTp.getYuny());
			errors.add(error);
		}
		// 订货合同分配等级
		String fpdj = dhuoTp.getFpdj();
		// 制品等级
		String deng = zpngTp.getDeng();
		boolean flag = false;
		// 分配等级(第一位)
		if (deng.length() != 3
				|| ((Code103.G.key.equals(fpdj.substring(0, 1)) || Code103.P.key.equals(fpdj.substring(0, 1))) && !fpdj.substring(0, 1).equals(deng.substring(0, 1)))) {
			flag = true;
		}
		// 分配等级(第二位和第三位)
		if (deng.length() != 3
				|| ((Code104.get(fpdj.substring(1, 2)).value < Code104.get(deng.substring(1, 2)).value) || (Code105.get(fpdj.substring(2)).value < Code105.get(deng.substring(2)).value))) {
			flag = true;
		}
		if (flag) {
			CError error = new CError();
			error.setName("分配等级");
			error.setField1(deng);
			error.setField2(fpdj);
			errors.add(error);
		}
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.厚");
			error.setField1(zpngTp.getXpho().toString());
			error.setField2(dhuoTp.getHouu().toString());
			errors.add(error);
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.宽");
			error.setField1(zpngTp.getXpkn().toString());
			error.setField2(dhuoTp.getKuan().toString());
			errors.add(error);
		}
		// 尺寸允许公差厚.上限
		if (dhuoTp.getHszi() != null
				&& zpngTp.getHszi() != null
				&& zpngTp.getHszi().doubleValue() < dhuoTp.getHszi().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸允许公差厚.上限");
			error.setField1(zpngTp.getHszi().toString());
			error.setField2(dhuoTp.getHszi().toString());
			errors.add(error);
		}
		// 尺寸允许公差厚.下限
		if (dhuoTp.getHxzi() != null
				&& zpngTp.getHxzi() != null
				&& zpngTp.getHxzi().doubleValue() > dhuoTp.getHxzi().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸允许公差厚.下限");
			error.setField1(zpngTp.getHxzi().toString());
			error.setField2(dhuoTp.getHxzi().toString());
			errors.add(error);
		}
		// 尺寸允许公差宽.上限
		if (dhuoTp.getKszi() != null
				&& zpngTp.getKszi() != null
				&& zpngTp.getKszi().doubleValue() < dhuoTp.getKszi().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸允许公差宽.上限");
			error.setField1(zpngTp.getKszi().toString());
			error.setField2(dhuoTp.getKszi().toString());
			errors.add(error);
		}
		// 尺寸允许公差宽.下限
		if (dhuoTp.getKxzi() != null
				&& zpngTp.getKxzi() != null
				&& zpngTp.getKxzi().doubleValue() > dhuoTp.getKxzi().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸允许公差宽.下限");
			error.setField1(zpngTp.getKxzi().toString());
			error.setField2(dhuoTp.getKxzi().toString());
			errors.add(error);
		}
		// 涂油种类
		if (dhuoTp.getYtyp() != null
				&& !dhuoTp.getYtyp().equals(zpngTp.getYtyp())) {
			CError error = new CError();
			error.setName("涂油种类");
			error.setField1(zpngTp.getYtyp());
			error.setField2(dhuoTp.getYtyp());
			errors.add(error);
		}
		// 化学处理方法
		if (dhuoTp.getHuac() != null
				&& !dhuoTp.getHuac().equals(zpngTp.getHxcl())) {
			CError error = new CError();
			error.setName("化学处理方法");
			error.setField1(zpngTp.getHxcl());
			error.setField2(dhuoTp.getHuac());
			errors.add(error);
		}
		// K-Plate表示
		if (dhuoTp.getPlat() != null
				&& !dhuoTp.getPlat().equals(zpngTp.getPlat())) {
			CError error = new CError();
			error.setName("K-Plate表示");
			error.setField1(zpngTp.getPlat());
			error.setField2(dhuoTp.getPlat());
			errors.add(error);
		}
		// 差厚镀锡标记
		if (dhuoTp.getChdx() != null
				&& !dhuoTp.getChdx().equals(zpngTp.getChdx())) {
			CError error = new CError();
			error.setName("差厚");
			error.setField1(zpngTp.getChdx());
			error.setField2(dhuoTp.getChdx());
			errors.add(error);
		}
		// 附页KPNO1
		if (dhuoTp.getKpn1Flag() != null
				&& !dhuoTp.getKpn1Flag().equals(zpngTp.getKpn1Flag())
				&& dhuoTp.getKpn1() != null
				&& !dhuoTp.getKpn1().equals(zpngTp.getKpn1())) {
			CError error = new CError();
			error.setName("附页KPNO1");
			error.setField1(zpngTp.getKpn1());
			error.setField2(dhuoTp.getKpn1());
			errors.add(error);
		}
		// 附页KPNO2
		if (dhuoTp.getKpn2Flag() != null
				&& !dhuoTp.getKpn2Flag().equals(zpngTp.getKpn2Flag())
				&& dhuoTp.getKpn2() != null
				&& !dhuoTp.getKpn2().equals(zpngTp.getKpn2())) {
			CError error = new CError();
			error.setName("附页KPNO2");
			error.setField1(zpngTp.getKpn2());
			error.setField2(dhuoTp.getKpn2());
			errors.add(error);
		}
		// 附页KPNO3
		if (dhuoTp.getKpn3Flag() != null
				&& !dhuoTp.getKpn3Flag().equals(zpngTp.getKpn3Flag())
				&& dhuoTp.getKpn3() != null
				&& !dhuoTp.getKpn3().equals(zpngTp.getKpn3())) {
			CError error = new CError();
			error.setName("附页KPNO3");
			error.setField1(zpngTp.getKpn3());
			error.setField2(dhuoTp.getKpn3());
			errors.add(error);
		}
		// 附页KPNO4
		if (dhuoTp.getKpn4Flag() != null
				&& !dhuoTp.getKpn4Flag().equals(zpngTp.getKpn4Flag())
				&& dhuoTp.getKpn4() != null
				&& !dhuoTp.getKpn4().equals(zpngTp.getKpn4())) {
			CError error = new CError();
			error.setName("附页KPNO4");
			error.setField1(zpngTp.getKpn4());
			error.setField2(dhuoTp.getKpn4());
			errors.add(error);
		}
		// 产量区分
		if (!ChanType.hg.key.equals(zpngTp.getChan())) {
			CError error = new CError();
			error.setName("产量区分");
			error.setField1(zpngTp.getChan());
			error.setField2(ChanType.hg.key);
			errors.add(error);
		}
		// 品种第二位
		if (!pzno.substring(1).equals(zpngTp.getPzno().substring(1))) {
			CError error = new CError();
			error.setName("品种第二位");
			error.setField1(zpngTp.getPzno().substring(1));
			error.setField2(pzno.substring(1));
			errors.add(error);
		}
		// 目标附着量.单位
		if (dhuoTp.getFudw() != null
				&& !dhuoTp.getFudw().equals(zpngTp.getFudw())) {
			CError error = new CError();
			error.setName("目标附着量.单位");
			error.setField1(zpngTp.getFudw());
			error.setField2(dhuoTp.getFudw());
			errors.add(error);
		}
		// 目标附着量.正面
		if (dhuoTp.getFuzm() != null
				&& !dhuoTp.getFuzm().equals(zpngTp.getFuzm())) {
			CError error = new CError();
			error.setName("目标附着量.正面");
			error.setField1(zpngTp.getFuzm());
			error.setField2(dhuoTp.getFuzm());
			errors.add(error);
		}
		// 目标附着量.反面
		if (dhuoTp.getFufm() != null
				&& !dhuoTp.getFufm().equals(zpngTp.getFufm())) {
			CError error = new CError();
			error.setName("目标附着量.反面");
			error.setField1(zpngTp.getFufm());
			error.setField2(dhuoTp.getFufm());
			errors.add(error);
		}
		// 表面精整
		if (dhuoTp.getFace() != null
				&& !dhuoTp.getFace().equals(zpngTp.getFace())) {
			CError error = new CError();
			error.setName("表面精整");
			error.setField1(zpngTp.getFace());
			error.setField2(dhuoTp.getFace());
			errors.add(error);
		}
		// 钢种种类
		if (Code109.L.key.equals(ggno.substring(0, 1))
				&& (Code109.D.key.equals(zpngTp.getGzlx()) || Code109.M.key.equals(zpngTp.getGzlx()))) {
			CError error = new CError();
			error.setName("钢种种类");
			error.setField1(zpngTp.getGzlx());
			error.setField2(ggno.substring(0, 1));
			errors.add(error);
		}
		// 实绩附着量.正面
		if (zpngTp.getSczm() != null
				&& ((dhuoTp.getFuzs() != null && dhuoTp.getFuzs().doubleValue() < zpngTp.getSczm().doubleValue()) || (dhuoTp.getFuzx() != null && dhuoTp.getFuzx().doubleValue() > zpngTp.getSczm().doubleValue()))) {
			CError error = new CError();
			error.setName("实绩附着量.正面");
			error.setField1(zpngTp.getSczm().toString());
			error.setField2(dhuoTp.getFuzx() + "~" + dhuoTp.getFuzs());
			errors.add(error);
		}
		// 实绩附着量.反面
		if (zpngTp.getScfm() != null
				&& ((dhuoTp.getFufs() != null && dhuoTp.getFufs().doubleValue() < zpngTp.getScfm().doubleValue()) || (dhuoTp.getFufx() != null && dhuoTp.getFufx().doubleValue() > zpngTp.getScfm().doubleValue()))) {
			CError error = new CError();
			error.setName("实绩附着量.反面");
			error.setField1(zpngTp.getScfm().toString());
			error.setField2(dhuoTp.getFufx() + "~" + dhuoTp.getFufs());
			errors.add(error);
		}
		// 以下是剪切板制品是否配匹比较项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 品种第一位
			if (!pzno.substring(0, 1).equals(zpngTp.getPzno().substring(0, 1))) {
				CError error = new CError();
				error.setName("品种第一位");
				error.setField1(zpngTp.getPzno().substring(0, 1));
				error.setField2(pzno.substring(0, 1));
				errors.add(error);
			}
			// 内外销
			if (!dhuoTp.getNwai().equals(zpngTp.getNwai())) {
				CError error = new CError();
				error.setName("内外销");
				error.setField1(zpngTp.getNwai());
				error.setField2(dhuoTp.getNwai());
				errors.add(error);
			}
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.长");
				error.setField1(zpngTp.getXpcn().toString());
				error.setField2(dhuoTp.getCang().toString());
				errors.add(error);
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				CError error = new CError();
				error.setName("锯齿形式");
				error.setField1(zpngTp.getJcxs());
				error.setField2(dhuoTp.getJcxs());
				errors.add(error);
			}
			// 尺寸允许公差长.上限
			if (dhuoTp.getCszi() != null
					&& zpngTp.getCszi() != null
					&& zpngTp.getCszi().doubleValue() < dhuoTp.getCszi().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸允许公差长.上限");
				error.setField1(zpngTp.getCszi().toString());
				error.setField2(dhuoTp.getCszi().toString());
				errors.add(error);
			}
			// 尺寸允许公差长.下限
			if (dhuoTp.getCxzi() != null
					&& zpngTp.getCxzi() != null
					&& zpngTp.getCxzi().doubleValue() > dhuoTp.getCxzi().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸允许公差长.下限");
				error.setField1(zpngTp.getCxzi().toString());
				error.setField2(dhuoTp.getCxzi().toString());
				errors.add(error);
			}
			// 直角度
			if (dhuoTp.getJiao() != null && zpngTp.getJiao() != null
					&& zpngTp.getJiao() > dhuoTp.getJiao()) {
				CError error = new CError();
				error.setName("直角度");
				error.setField1(zpngTp.getJiao().toString());
				error.setField2(dhuoTp.getJiao().toString());
				errors.add(error);
			}
			// 再剪边标记
			if (CodeJian.T.key.equals(dhuoTp.getJian())
					&& (zpngTp.getZjbb() == null || zpngTp.getZjbb().isEmpty())) {
				CError error = new CError();
				error.setName("再剪边标记");
				error.setField1(zpngTp.getZjbb());
				error.setField2(dhuoTp.getJian());
				errors.add(error);
			}
			if (CodeJian.N.key.equals(dhuoTp.getJian())
					&& zpngTp.getZjbb() != null && !zpngTp.getZjbb().isEmpty()) {
				CError error = new CError();
				error.setName("再剪边标记");
				error.setField1(zpngTp.getZjbb());
				error.setField2(dhuoTp.getJian());
				errors.add(error);
			}
			// 压延方向标记
			if (dhuoTp.getYyan() != null
					&& !dhuoTp.getYyan().equals(zpngTp.getYyan())) {
				CError error = new CError();
				error.setName("压延方向");
				error.setField1(zpngTp.getYyan());
				error.setField2(dhuoTp.getYyan());
				errors.add(error);
			}
			// 保护标记
			if (dhuoTp.getProt() != null
					&& !dhuoTp.getProt().equals(zpngTp.getProt())) {
				CError error = new CError();
				error.setName("保护标记");
				error.setField1(zpngTp.getProt());
				error.setField2(dhuoTp.getProt());
				errors.add(error);
			}
			// 交付重量内容
			if (dhuoTp.getJhnr() != null
					&& !dhuoTp.getJhnr().equals(zpngTp.getJhnr())) {
				CError error = new CError();
				error.setName("重内");
				error.setField1(zpngTp.getJhnr());
				error.setField2(dhuoTp.getJhnr());
				errors.add(error);
			}
			// // 板材枚数
			// if (CodeLtdw.N.key.equals(dhuoTp.getLtdw())
			// && zpngTp.getKbzs() != dhuoTp.getKbzs()) {
			// CError error = new CError();
			// error.setName("枚数");
			// error.setField1(zpngTp.getKbzs().toString());
			// error.setField2(dhuoTp.getKbzs().toString());
			// errors.add(error);
			// }
			// if ((CodeLtdw.P.key.equals(dhuoTp.getLtdw()) ||
			// CodeLtdw.B.key.equals(dhuoTp.getLtdw()))
			// && dhuoTp.getLtzi().intValue() > zpngTp.getKbzs().intValue()) {
			// CError error = new CError();
			// error.setName("枚数");
			// error.setField1(zpngTp.getKbzs().toString());
			// error.setField2(dhuoTp.getLtzi().toString());
			// errors.add(error);
			// }
			// 包装形式
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				CError error = new CError();
				error.setName("包装形式");
				error.setField1(zpngTp.getKbao());
				error.setField2(dhuoTp.getKbao());
				errors.add(error);
			}
			// 垫木方向
			if (dhuoTp.getDmfx() != null
					&& !dhuoTp.getDmfx().equals(zpngTp.getDmfx())) {
				CError error = new CError();
				error.setName("垫木方向");
				error.setField1(zpngTp.getDmfx());
				error.setField2(dhuoTp.getDmfx());
				errors.add(error);
			}
			Integer llyd = zpngTp.getLlyd();
			// 硬度
			if (llyd != null
					&& ((dhuoTp.getYmax() != null && Double.parseDouble(dhuoTp.getYmax()) < (llyd / 10d)) || (dhuoTp.getYmin() != null && Double.parseDouble(dhuoTp.getYmin()) > (llyd / 10d)))) {
				CError error = new CError();
				error.setName("硬度");
				error.setField1(llyd.toString());
				error.setField2(dhuoTp.getYmin() + "~" + dhuoTp.getYmax());
				errors.add(error);
			}
			return errors;
		}
		// 钢卷制品。配匹比较项目
		if (EXpzk.JZP.key.equals(xpzk)) {
			// 品种第一位
			if (!Code118.coil.key.equals(pzno.substring(0, 1))) {
				CError error = new CError();
				error.setName("品种第一位");
				error.setField1(zpngTp.getPzno().substring(0, 1));
				error.setField2(pzno.substring(0, 1));
				errors.add(error);
			}
			// 内外销(当为钢卷成品时，要配匹)
			if (!dhuoTp.getNwai().equals(zpngTp.getNwai())) {
				CError error = new CError();
				error.setName("内外销");
				error.setField1(zpngTp.getNwai());
				error.setField2(dhuoTp.getNwai());
				errors.add(error);
			}
			// 内径保护筒标记(钢卷成品才有内径保护筒标记)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				CError error = new CError();
				error.setName("内径保护筒");
				error.setField1(zpngTp.getNjbh());
				error.setField2(dhuoTp.getNjbh());
				errors.add(error);
			}
			// 交付重量内容(当为钢卷成品时，要配匹)
			if (dhuoTp.getJhnr() != null
					&& !dhuoTp.getJhnr().equals(zpngTp.getJhnr())) {
				CError error = new CError();
				error.setName("重内");
				error.setField1(zpngTp.getJhnr());
				error.setField2(dhuoTp.getJhnr());
				errors.add(error);
			}
			int zpzl = zpngTp.getZpzl() != null ? zpngTp.getZpzl() : 0;
			// 捆包重量(钢卷成品才有捆包重量)
			if (dhuoTp.getKbzs() != null
					&& dhuoTp.getKbzx() != null
					&& zpngTp.getKbzs() != null
					&& zpngTp.getKbzx() != null
					&& (dhuoTp.getKbzs().doubleValue() > (zpzl / 1000d) || dhuoTp.getKbzx().doubleValue() < (zpzl / 1000d))) {
				CError error = new CError();
				error.setName("捆包重量");
				error.setField1((NumberUtils.format(zpzl / 1000d, 3)) + "");
				error.setField2(dhuoTp.getKbzs() + "~" + dhuoTp.getKbzx());
				errors.add(error);
			}
			// 溶接部有无（ETL）(钢卷成品才有溶接部有无)
			if (dhuoTp.getRjie() != null
					&& zpngTp.getRjet() != null
					&& zpngTp.getRjet().intValue() > Integer.parseInt(dhuoTp.getRjie())) {
				CError error = new CError();
				error.setName("溶接");
				error.setField1(zpngTp.getRjet().toString());
				error.setField2(dhuoTp.getRjie());
				errors.add(error);
			}
			// 内径(钢卷成品才有内径)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				CError error = new CError();
				error.setName("内径");
				error.setField1(zpngTp.getNjbh());
				error.setField2(dhuoTp.getNjbh());
				errors.add(error);
			}
			// 包装形式(当为钢卷成品时，要配匹)
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				CError error = new CError();
				error.setName("包装形式");
				error.setField1(zpngTp.getKbao());
				error.setField2(dhuoTp.getKbao());
				errors.add(error);
			}
			Integer llyd = zpngTp.getLlyd();
			// 硬度
			if (llyd != null
					&& ((dhuoTp.getYmax() != null && Double.parseDouble(dhuoTp.getYmax()) < (llyd / 10d)) || (dhuoTp.getYmin() != null && Double.parseDouble(dhuoTp.getYmin()) > (llyd / 10d)))) {
				CError error = new CError();
				error.setName("硬度");
				error.setField1(llyd.toString());
				error.setField2(dhuoTp.getYmin() + "~" + dhuoTp.getYmax());
				errors.add(error);
			}
			return errors;
		}
		// 中间品。配匹比较项目
		// 品种第一位。
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			CError error = new CError();
			error.setName("品种第一位");
			error.setField1(pzno.substring(0, 1));
			error.setField2(Code118.coil.key);
			errors.add(error);
		}
		// SL装入宽(当中间品为剪切材时，要配匹)
		if (zrkn != zpngTp.getXpkn().doubleValue()) {
			CError error = new CError();
			error.setName("装入宽");
			error.setField1(zpngTp.getXpkn().toString());
			error.setField2(String.valueOf(zrkn));
			errors.add(error);
		}
		return errors;
	}

	/**
	 * <p>
	 * 当订货合同为 O/R1或 O/R2级时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="4">素材</td>
	 * <td>运用规格</td>
	 * <td align="left">素材的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">素材的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">素材的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，素材的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="12">剪切板制品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">制品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>制品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>制品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>制品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">制品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于3</td>
	 * </tr>
	 * <tr>
	 * <td>再剪边标记</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的再剪边标记为"T"，制品的再剪边标记为空时，匹配错误
	 * <li>订货合同的再剪边标记为"N"，制品的再剪边标记不为空时，匹配错误
	 * <li>订货的再剪边标记为空时，不检查 </div></td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">制品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">制品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="10">钢卷成品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">钢卷成品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">钢卷成品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">钢卷成品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">当钢卷成品为钢卷成品时，钢卷成品的产量代码必等于3</td>
	 * </tr>
	 * <tr>
	 * <td>内径</td>
	 * <td align="left">当钢卷成品为钢卷成品时，钢卷成品的内径必等于订货合同的内径</td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">钢卷成品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">钢卷成品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="8">中间品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">中间品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">中间品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">中间品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>中间品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>中间品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>中间品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">中间品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">中间品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，中间品（剪切材）的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">中间品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return Sfpp
	 */
	private Sfpp compareOr(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk) {
		// 比较装入宽时，如果订货合同的装入宽为空时，则用订货合同的尺寸.宽
		double zrkn = dhuoTp.getZrkn() != null && dhuoTp.getZrkn() > 0 ? dhuoTp.getZrkn()
				: dhuoTp.getKuan();
		// 以下是素板是否配匹比较的项目
		if (EXpzk.SC.key.equals(xpzk)) {
			// 运用规格
			if (!dhuoTp.getYuny().equals(ycaiTp.getYuny())) {
				return Sfpp.nmatch;
			}
			// 尺寸-厚
			if (dhuoTp.getHouu().doubleValue() != ycaiTp.getXpho().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 尺寸-宽
			if (dhuoTp.getKuan().doubleValue() != ycaiTp.getXpkn().doubleValue()) {
				return Sfpp.nmatch;
			}
			// SL装入宽
			if (zrkn != ycaiTp.getXpkn().doubleValue()) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 以下是剪切板制品、钢卷制品和中间品是否配匹比较项目
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 运用规格
		if (!dhuoTp.getYuny().equals(zpngTp.getYuny())) {
			return Sfpp.nmatch;
		}
		// 实绩附着量.正面
		if (zpngTp.getSczm() != null
				&& ((dhuoTp.getFuzs() != null && dhuoTp.getFuzs().doubleValue() < zpngTp.getSczm().doubleValue()) || (dhuoTp.getFuzx() != null && dhuoTp.getFuzx().doubleValue() > zpngTp.getSczm().doubleValue()))) {
			return Sfpp.nmatch;
		}
		// 实绩附着量.反面
		if (zpngTp.getScfm() != null
				&& ((dhuoTp.getFufs() != null && dhuoTp.getFufs().doubleValue() < zpngTp.getScfm().doubleValue()) || (dhuoTp.getFufx() != null && dhuoTp.getFufx().doubleValue() > zpngTp.getScfm().doubleValue()))) {
			return Sfpp.nmatch;
		}
		// 表面精整
		if (dhuoTp.getFace() != null
				&& !dhuoTp.getFace().equals(zpngTp.getFace())) {
			return Sfpp.nmatch;
		}
		// 涂油种类
		if (dhuoTp.getYtyp() != null
				&& !dhuoTp.getYtyp().equals(zpngTp.getYtyp())) {
			return Sfpp.nmatch;
		}
		// 差厚镀锡标记
		if (dhuoTp.getChdx() != null
				&& !dhuoTp.getChdx().equals(zpngTp.getChdx())) {
			return Sfpp.nmatch;
		}
		// 剪切板制品。配匹比较项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 产量区分
			if (!ChanType.bhg.key.equals(zpngTp.getChan())) {
				return Sfpp.nmatch;
			}
			// 再剪边标记
			if (CodeJian.T.key.equals(dhuoTp.getJian())
					&& (zpngTp.getZjbb() == null || zpngTp.getZjbb().isEmpty())) {
				return Sfpp.nmatch;
			}
			if (CodeJian.N.key.equals(dhuoTp.getJian())
					&& zpngTp.getZjbb() != null && !zpngTp.getZjbb().isEmpty()) {
				return Sfpp.nmatch;
			}
			// 包装形式
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				return Sfpp.nmatch;
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 钢卷成品。配匹比较项目
		if (EXpzk.JZP.key.equals(xpzk)) {
			// 产量区分
			if (!ChanType.bhg.key.equals(zpngTp.getChan())) {
				return Sfpp.nmatch;
			}
			// 内径(钢卷成品才有内径)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				return Sfpp.nmatch;
			}
			// 包装形式(当中间品为剪切材时，要配匹)
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		// 中间品。配匹比较项目
		// SL装入宽(当中间品为剪切材时，要配匹)
		if (zrkn != zpngTp.getXpkn().doubleValue()) {
			return Sfpp.nmatch;
		}
		return Sfpp.match;
	}

	/**
	 * <p>
	 * 当订货合同为 O/R1或 O/R2级时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="4">素材</td>
	 * <td>运用规格</td>
	 * <td align="left">素材的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.厚</td>
	 * <td align="left">素材的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">素材的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，素材的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="12">剪切板制品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">制品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>制品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>制品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>制品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>制品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">制品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">制品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于3</td>
	 * </tr>
	 * <tr>
	 * <td>再剪边标记</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>订货合同的再剪边标记为"T"，制品的再剪边标记为空时，匹配错误
	 * <li>订货合同的再剪边标记为"N"，制品的再剪边标记不为空时，匹配错误
	 * <li>订货的再剪边标记为空时，不检查 </div></td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">制品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">制品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="10">钢卷成品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">钢卷成品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>钢卷成品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>钢卷成品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">钢卷成品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">钢卷成品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">当钢卷成品为钢卷成品时，钢卷成品的产量代码必等于3</td>
	 * </tr>
	 * <tr>
	 * <td>内径</td>
	 * <td align="left">当钢卷成品为钢卷成品时，钢卷成品的内径必等于订货合同的内径</td>
	 * </tr>
	 * <tr>
	 * <td>包装形式</td>
	 * <td align="left">钢卷成品的包装形式必等于订货合同的包装形式</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">钢卷成品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="8">中间品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">中间品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">中间品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>运用规格</td>
	 * <td align="left">中间品的运用规格必等于订货合同的运用规格</td>
	 * </tr>
	 * <tr>
	 * <td>锡付着量</td>
	 * <td align="left"><div>
	 * <ul>
	 * <li>中间品的实绩附着量.正面 &lt;订货合同的锡附着量.正面-下限时，配匹错误
	 * <li>中间品的实绩附着量.正面 &gt;订货合同的锡附着量.正面-上限时，配匹错误
	 * <li>中间品的实绩附着量.反面&lt;订货合同的锡附着量.反面-下限时，配匹错误
	 * <li>中间品的实绩附着量.反面&gt;订货合同的锡附着量.反面-上限时，配匹错误
	 * </ul>
	 * </div></td>
	 * </tr>
	 * <tr>
	 * <td>表面精整</td>
	 * <td align="left">中间品的表面精整必等于订货合同的表面精整</td>
	 * </tr>
	 * <tr>
	 * <td>涂油种类</td>
	 * <td align="left">中间品的涂油种类必等于订货合同的涂油种类</td>
	 * </tr>
	 * <tr>
	 * <td>SL装入宽</td>
	 * <td align="left">当订货合同的SL装入宽不为空时，中间品（剪切材）的尺寸.宽必等于订货合同的SL装入宽</td>
	 * </tr>
	 * <tr>
	 * <td>差厚镀锡标记</td>
	 * <td align="left">中间品的差厚镀锡标记必等于订货合同的差厚镀锡标记</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return List<CError>
	 */
	private List<CError> findCompareOrErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk) {

		// 比较装入宽时，如果订货合同的装入宽为空时，则用订货合同的尺寸.宽
		double zrkn = dhuoTp.getZrkn() != null && dhuoTp.getZrkn() > 0 ? dhuoTp.getZrkn()
				: dhuoTp.getKuan();
		// 记录不配匹的项目
		List<CError> errors = new ArrayList<CError>();
		// 以下是素板是否配匹比较的项目
		if (EXpzk.SC.key.equals(xpzk)) {
			// 运用规格
			if (!dhuoTp.getYuny().equals(ycaiTp.getYuny())) {
				CError error = new CError();
				error.setName("运用规格");
				error.setField1(ycaiTp.getYuny());
				error.setField2(dhuoTp.getYuny());
				errors.add(error);
			}
			// 尺寸.厚
			if (dhuoTp.getHouu().doubleValue() != ycaiTp.getXpho().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.厚");
				error.setField1(ycaiTp.getXpho().toString());
				error.setField2(dhuoTp.getHouu().toString());
				errors.add(error);
			}
			// 尺寸.宽
			if (dhuoTp.getKuan().doubleValue() != ycaiTp.getXpkn().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.宽");
				error.setField1(ycaiTp.getXpkn().toString());
				error.setField2(dhuoTp.getKuan().toString());
				errors.add(error);
			}
			// SL装入宽
			if (zrkn != ycaiTp.getXpkn().doubleValue()) {
				CError error = new CError();
				error.setName("装入宽");
				error.setField1(ycaiTp.getXpkn().toString());
				error.setField2(String.valueOf(zrkn));
				errors.add(error);
			}
			return errors;
		}
		// 以下是剪切板制品、钢卷制品和中间品是否配匹比较项目
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.厚");
			error.setField1(zpngTp.getXpho().toString());
			error.setField2(dhuoTp.getHouu().toString());
			errors.add(error);
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.宽");
			error.setField1(zpngTp.getXpkn().toString());
			error.setField2(dhuoTp.getKuan().toString());
			errors.add(error);
		}
		// 运用规格
		if (!dhuoTp.getYuny().equals(zpngTp.getYuny())) {
			CError error = new CError();
			error.setName("运用规格");
			error.setField1(zpngTp.getYuny());
			error.setField2(dhuoTp.getYuny());
			errors.add(error);
		}
		// 实绩附着量.正面
		if (zpngTp.getSczm() != null
				&& ((dhuoTp.getFuzs() != null && dhuoTp.getFuzs().doubleValue() < zpngTp.getSczm().doubleValue()) || (dhuoTp.getFuzx() != null && dhuoTp.getFuzx().doubleValue() > zpngTp.getSczm().doubleValue()))) {
			CError error = new CError();
			error.setName("实绩附着量.正面");
			error.setField1(zpngTp.getSczm().toString());
			error.setField2(dhuoTp.getFuzx() + "~" + dhuoTp.getFuzs());
			errors.add(error);
		}
		// 实绩附着量.反面
		if (zpngTp.getScfm() != null
				&& ((dhuoTp.getFufs() != null && dhuoTp.getFufs().doubleValue() < zpngTp.getScfm().doubleValue()) || (dhuoTp.getFufx() != null && dhuoTp.getFufx().doubleValue() > zpngTp.getScfm().doubleValue()))) {
			CError error = new CError();
			error.setName("实绩附着量.反面");
			error.setField1(zpngTp.getScfm().toString());
			error.setField2(dhuoTp.getFuzx() + "~" + dhuoTp.getFuzs());
			errors.add(error);
		}
		// 表面精整
		if (dhuoTp.getFace() != null
				&& !dhuoTp.getFace().equals(zpngTp.getFace())) {
			CError error = new CError();
			error.setName("表面精整");
			error.setField1(zpngTp.getFace());
			error.setField2(dhuoTp.getFace());
			errors.add(error);
		}
		// 涂油种类
		if (dhuoTp.getYtyp() != null
				&& !dhuoTp.getYtyp().equals(zpngTp.getYtyp())) {
			CError error = new CError();
			error.setName("涂油种类");
			error.setField1(zpngTp.getYtyp());
			error.setField2(dhuoTp.getYtyp());
			errors.add(error);
		}
		// 差厚镀锡标记
		if (dhuoTp.getChdx() != null
				&& !dhuoTp.getChdx().equals(zpngTp.getChdx())) {
			CError error = new CError();
			error.setName("差厚");
			error.setField1(zpngTp.getChdx());
			error.setField2(dhuoTp.getChdx());
			errors.add(error);
		}
		// 剪切板制品。配匹比较项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.长");
				error.setField1(zpngTp.getXpcn().toString());
				error.setField2(dhuoTp.getCang().toString());
				errors.add(error);
			}
			// 产量区分
			if (!ChanType.bhg.key.equals(zpngTp.getChan())) {
				CError error = new CError();
				error.setName("产量区分");
				error.setField1(zpngTp.getChan());
				error.setField2(ChanType.bhg.key);
				errors.add(error);
			}
			// 再剪边标记
			if (CodeJian.T.key.equals(dhuoTp.getJian())
					&& (zpngTp.getZjbb() == null || zpngTp.getZjbb().isEmpty())) {
				CError error = new CError();
				error.setName("再剪边标记");
				error.setField1(null);
				error.setField2(CodeJian.T.key);
				errors.add(error);
			}
			if (CodeJian.N.key.equals(dhuoTp.getJian())
					&& zpngTp.getZjbb() != null && !zpngTp.getZjbb().isEmpty()) {
				CError error = new CError();
				error.setName("再剪边标记");
				error.setField1(zpngTp.getZjbb());
				error.setField2(CodeJian.N.key);
				errors.add(error);
			}
			// 包装形式
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				CError error = new CError();
				error.setName("包装形式");
				error.setField1(zpngTp.getKbao());
				error.setField2(dhuoTp.getKbao());
				errors.add(error);
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				CError error = new CError();
				error.setName("锯齿形式");
				error.setField1(zpngTp.getJcxs());
				error.setField2(dhuoTp.getJcxs());
				errors.add(error);
			}
			return errors;
		}
		// 钢卷成品。配匹比较项目
		if (EXpzk.JZP.key.equals(xpzk)) {
			// 产量区分
			if (!ChanType.bhg.key.equals(zpngTp.getChan())) {
				CError error = new CError();
				error.setName("产量区分");
				error.setField1(zpngTp.getChan());
				error.setField2(ChanType.bhg.key);
				errors.add(error);
			}
			// 内径(钢卷成品才有内径)
			if (dhuoTp.getNjbh() != null && zpngTp.getNjbh() != null
					&& !dhuoTp.getNjbh().equals(zpngTp.getNjbh())) {
				CError error = new CError();
				error.setName("内径");
				error.setField1(zpngTp.getNjbh());
				error.setField2(dhuoTp.getNjbh());
				errors.add(error);
			}
			// 包装形式(当中间品为剪切材时，要配匹)
			if (dhuoTp.getKbao() != null
					&& !dhuoTp.getKbao().equals(zpngTp.getKbao())) {
				CError error = new CError();
				error.setName("包装形式");
				error.setField1(zpngTp.getKbao());
				error.setField2(dhuoTp.getKbao());
				errors.add(error);
			}
			return errors;
		}
		// 中间品。配匹比较项目
		// SL装入宽(当中间品为剪切材时，要配匹)
		if (zrkn != zpngTp.getXpkn().doubleValue()) {
			CError error = new CError();
			error.setName("装入宽");
			error.setField1(zpngTp.getXpkn().toString());
			error.setField2(String.valueOf(zrkn));
			errors.add(error);
		}

		return errors;
	}

	/**
	 * <p>
	 * 当订货合同为 发生品时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td>素材</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="5">制品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于4</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="3">钢卷成品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货中间合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">钢卷成品的产量代码必等于4</td>
	 * </tr>
	 * <tr>
	 * <td>中间品</td>
	 * <td>无</td>
	 * <td align="left">&nbsp;</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return Sfpp
	 */
	private Sfpp compareFs(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk) {
		// 当现品状况为素板和中间品时，不用配匹
		if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)) {
			return Sfpp.match;
		}
		// 以下是剪切板制品和钢卷制品是否配匹比较项目
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			return Sfpp.nmatch;
		}
		// 产量区分
		if (!ChanType.fs.key.equals(zpngTp.getChan())) {
			return Sfpp.nmatch;
		}
		// 剪切板制品。配匹要比较的项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				return Sfpp.nmatch;
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		return Sfpp.match;
	}

	/**
	 * <p>
	 * 当订货合同为 发生品时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td>素材</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="5">制品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">制品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">制品的尺寸.宽必等于订货合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.长</td>
	 * <td align="left">制品的尺寸.长必等于订货合同的订货尺寸.长</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于4</td>
	 * </tr>
	 * <tr>
	 * <td>锯齿形式</td>
	 * <td align="left">制品的锯齿形式必等于订货合同的锯齿形式</td>
	 * </tr>
	 * <tr>
	 * <td rowspan="3">钢卷成品</td>
	 * <td>尺寸.厚</td>
	 * <td align="left">钢卷成品的尺寸.厚必等于订货合同的订货尺寸.厚</td>
	 * </tr>
	 * <tr>
	 * <td>尺寸.宽</td>
	 * <td align="left">钢卷成品的尺寸.宽必等于订货中间合同的订货尺寸.宽</td>
	 * </tr>
	 * <tr>
	 * <td>产量区分</td>
	 * <td align="left">钢卷成品的产量代码必等于4</td>
	 * </tr>
	 * <tr>
	 * <td>中间品</td>
	 * <td>无</td>
	 * <td align="left">&nbsp;</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return List<CError>
	 */
	private List<CError> findCompareFsErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk) {
		// 记录不配匹的项目
		List<CError> errors = new ArrayList<CError>();
		// 当现品状况为素板和中间品时，不用配匹
		if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)) {
			return errors;
		}
		// 以下是剪切板制品和钢卷制品是否配匹比较项目
		// 尺寸.厚
		if (dhuoTp.getHouu().doubleValue() != zpngTp.getXpho().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.厚");
			error.setField1(zpngTp.getXpho().toString());
			error.setField2(dhuoTp.getHouu().toString());
			errors.add(error);
		}
		// 尺寸.宽
		if (dhuoTp.getKuan().doubleValue() != zpngTp.getXpkn().doubleValue()) {
			CError error = new CError();
			error.setName("尺寸.宽");
			error.setField1(zpngTp.getXpkn().toString());
			error.setField2(dhuoTp.getKuan().toString());
			errors.add(error);
		}
		// 产量区分
		if (!ChanType.fs.key.equals(zpngTp.getChan())) {
			CError error = new CError();
			error.setName("产量区分");
			error.setField1(zpngTp.getChan());
			error.setField2(ChanType.fs.key);
			errors.add(error);
		}
		// 剪切板制品。配匹要比较的项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 尺寸.长
			if (dhuoTp.getCang().doubleValue() != zpngTp.getXpcn().doubleValue()) {
				CError error = new CError();
				error.setName("尺寸.长");
				error.setField1(zpngTp.getXpcn().toString());
				error.setField2(dhuoTp.getCang().toString());
				errors.add(error);
			}
			// 锯齿形式
			if (dhuoTp.getJcxs() != null
					&& !dhuoTp.getJcxs().equals(zpngTp.getJcxs())) {
				CError error = new CError();
				error.setName("锯齿形式");
				error.setField1(zpngTp.getJcxs());
				error.setField2(dhuoTp.getJcxs());
				errors.add(error);
			}
			return errors;
		}
		return errors;
	}

	/**
	 * <p>
	 * 当订货合同为端板时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td>素材</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td>制品</td>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于7</td>
	 * </tr>
	 * <tr>
	 * <td>钢卷成品</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td>中间品</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return Sfpp
	 */
	private Sfpp compareNs(YcaiTp ycaiTp, ZpngTp zpngTp, DhuoTp dhuoTp,
			String xpzk) {
		// 当现品状况为素板、钢卷制品和中间品时，不用匹配
		if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)
				|| EXpzk.JZP.key.equals(xpzk)) {
			return Sfpp.match;
		}
		// 剪切板制品。配匹比较的项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 产量区分
			if (!ChanType.ns.key.equals(zpngTp.getChan())) {
				return Sfpp.nmatch;
			}
			return Sfpp.match;
		}
		return Sfpp.match;
	}

	/**
	 * <p>
	 * 当订货合同为端板时。对其做分配操作时，要配匹的项目如下：
	 * </p>
	 * <table width="800" bordercolor="#000000" border="1" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="15%" /><col width="15%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <td>余材状况</td>
	 * <td>字段</td>
	 * <td>说明</td>
	 * </tr>
	 * <tr>
	 * <td>素材</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td>制品</td>
	 * <td>产量区分</td>
	 * <td align="left">制品的产量代码必等于7</td>
	 * </tr>
	 * <tr>
	 * <td>钢卷成品</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * <tr>
	 * <td>中间品</td>
	 * <td>无</td>
	 * <td align="left">无</td>
	 * </tr>
	 * </table>
	 * 
	 * @param ycaiTp
	 * @param zpngTp
	 * @param dhuoTp
	 * @param xpzk
	 * @return List<CError>
	 */
	private List<CError> findCompareNsErrors(YcaiTp ycaiTp, ZpngTp zpngTp,
			DhuoTp dhuoTp, String xpzk) {
		// 记录不配匹的项目
		List<CError> errors = new ArrayList<CError>();
		// 当现品状况为素板、钢卷制品和中间品时，不用匹配
		if (EXpzk.SC.key.equals(xpzk) || EXpzk.ZJP.key.equals(xpzk)
				|| EXpzk.JZP.key.equals(xpzk)) {
			return errors;
		}
		// 剪切板制品。配匹比较的项目
		if (EXpzk.BZP.key.equals(xpzk)) {
			// 产量区分
			if (!ChanType.ns.key.equals(zpngTp.getChan())) {
				CError error = new CError();
				error.setName("产量区分");
				error.setField1(zpngTp.getChan());
				error.setField2(ChanType.ns.key);
				errors.add(error);
			}
			return errors;
		}
		return errors;
	}

	/**
	 * <p>
	 * 判断素材是否符合做分配指示。要求如下：
	 * </p>
	 * <ul>
	 * <li>删除标识为：0（初始）。
	 * <li>分配/余材为：0（初始）或2（余材）
	 * <li>状态为：1（已入库）。
	 * </ul>
	 * 
	 * @param ycaiTp
	 */
	private void checkYcai(YcaiTp ycaiTp) {
		if (!EScbj.CS.key.equals(ycaiTp.getScbj())) {
			throw new CocoException(-1, "原材卷板No为" + ycaiTp.getJbno()
					+ "的素材,删除标记为" + EScbj.get(ycaiTp.getScbj()).value
					+ ",不能用来做分配指示");
		}
		if (!(EFpyc.CS.key.equals(ycaiTp.getFpyc()) || EFpyc.YC.key.equals(ycaiTp.getFpyc()))) {
			throw new CocoException(-1, "原材卷板No为" + ycaiTp.getJbno()
					+ "的素材,不为余材,不能用来做分配指示");
		}
		if (!(YbStat.CS.stat.equals(ycaiTp.getStat()) || YbStat.YRK.stat.equals(ycaiTp.getStat()))) {
			throw new CocoException(-1, "原材卷板No为" + ycaiTp.getJbno()
					+ "的素材,状态为" + YbStat.get(ycaiTp.getStat()).name
					+ ",不能用来做分配指示");
		}
		if (ycaiTp.getZtbj() != null && !ycaiTp.getZtbj().trim().isEmpty()) {
			throw new CocoException(-1, "原材卷板" + ycaiTp.getJbno() + "作业停止标记为"
					+ ycaiTp.getZtbj() + ",不能做分配指示");
		}
	}

	/**
	 * <p>
	 * 判断中间品、剪切板制品或卷制品是否符合做分配指示。要求如下：
	 * </p>
	 * <ul>
	 * <li>删除标识为：0（初始）。
	 * <li>分配/余材为：0（初始）或2（余材）
	 * <li>状态为：1（初始）。
	 * </ul>
	 * 
	 * @param zpngTp
	 */
	private void checkZp(ZpngTp zpngTp) {
		if (!EScbj.CS.key.equals(zpngTp.getScbj())) {
			throw new CocoException(-1, EXpzk.getValue(zpngTp.getXpzk())
					+ "现品No为" + zpngTp.getJbno() + ",删除标记为"
					+ EScbj.get(zpngTp.getScbj()).value + ",不能用来做分配指示");
		}
		if (!(EFpyc.CS.key.equals(zpngTp.getFpyc()) || EFpyc.YC.key.equals(zpngTp.getFpyc()))) {
			throw new CocoException(-1, EXpzk.getValue(zpngTp.getXpzk())
					+ "现品No为" + zpngTp.getJbno() + ",不为余材,不能用来做分配指示");
		}
		if (!ZpStat.CS.stat.equals(zpngTp.getStat())) {
			throw new CocoException(-1, EXpzk.getValue(zpngTp.getXpzk())
					+ "现品No为" + zpngTp.getJbno() + ",状态为"
					+ ZpStat.get(zpngTp.getStat()).name + ",不能用来做分配指示");
		}
		if (EXpzk.ZJP.key.equals(zpngTp.getXpzk()) && zpngTp.getZtbj() != null
				&& !zpngTp.getZtbj().trim().isEmpty()) {
			throw new CocoException(-1, "现品" + zpngTp.getJbno() + "作业停止标记为"
					+ zpngTp.getZtbj() + ",不能做分配指示");
		}
	}

	/**
	 * <p>
	 * 根据分配的素材或中间品，生成指示对象
	 * </p>
	 * 
	 * @param ycaiTp
	 *            素材
	 * @param zpngTp
	 *            中间品（只能是中间品。因为制品分配是不用着生产，因此是不会写指示对象）
	 * @param vo
	 *            分配信息
	 * @param fpno
	 *            分配号
	 * @param date
	 *            分配时间
	 * @param sfpp
	 *            是否配匹
	 * @return ZsdxTp
	 */
	private ZsdxTp parseZsdx(YcaiTp ycaiTp, ZpngTp zpngTp, FpVO vo,
			String fpno, Date date, Sfpp sfpp) {
		ZsdxTp zsdxTp = null;
		if (ycaiTp == null && zpngTp == null) {
			return zsdxTp;
		}
		zsdxTp = new ZsdxTp();
		// 分配No.
		zsdxTp.setFpno(fpno);
		// Coil/PileNo
		zsdxTp.setJbno(ycaiTp == null ? zpngTp.getJbno() : ycaiTp.getJbno());
		// 原剪切板制品厂家（制造商代码）
		zsdxTp.setZzno(ycaiTp == null ? zpngTp.getZzsd() : ycaiTp.getZzsd());
		// 订货No和行号
		zsdxTp.setDhno(vo.getDhno() + vo.getLine());
		// 余材状况
		zsdxTp.setYczk(vo.getXpzk());
		// 剪边
		zsdxTp.setJbkb(vo.getJbkb());
		// 再选别标记
		zsdxTp.setZxbb(vo.getZxbb());
		// 强制标识
		zsdxTp.setQzbj(vo.getQzbj());
		// 采取指示量
		zsdxTp.setCqzs(NumberUtils.formatDouToInt(vo.getCqzs() * 1000, 0));
		// 超过分配指示量
		zsdxTp.setClfp(vo.getClfp());
		// 制品重量
		zsdxTp.setZpzl(ycaiTp == null ? zpngTp.getZpzl() : ycaiTp.getZpzl());
		// 状态
		zsdxTp.setStat(ZtConstants.ZSDX_STAT_WZS);
		// Pile区分
		zsdxTp.setPlqf(ycaiTp == null ? zpngTp.getPlqf() : null);
		// 作成时间
		zsdxTp.setCrea(date);
		// 是否配匹
		zsdxTp.setSfpp(sfpp.key);
		// 原板客户
		zsdxTp.setYcbr(ycaiTp != null ? ycaiTp.getYcbr() : null);

		return zsdxTp;
	}

	/**
	 * <p>
	 * 对制品设置订货合同信息
	 * </p>
	 * 
	 * @param zpngTp
	 *            制品
	 * @param dhuoTp
	 *            订货合同
	 * @param fpno
	 *            分配No.
	 * @param fpyc
	 *            分配余才
	 * @param zjbb
	 *            剪边标记
	 */
	private void parseZp(ZpngTp zpngTp, DhuoTp dhuoTp, String fpno,
			String fpyc, String zjbb) {
		// 分配号
		zpngTp.setFpno(fpno);
		// 分配/余材标记
		zpngTp.setFpyc(fpyc);
		// 订货No.行号
		zpngTp.setDhno(dhuoTp.getDhno() + dhuoTp.getLine());
		// 客户略称
		zpngTp.setAbbr(dhuoTp.getAbbr());
		// 客户代码
		zpngTp.setUser(dhuoTp.getUser());
		// 品种代码
		zpngTp.setPzno(dhuoTp.getPzno());
		zpngTp.setHouu(dhuoTp.getHouu());
		zpngTp.setKuan(dhuoTp.getKuan());
		zpngTp.setCang(dhuoTp.getCang());
		zpngTp.setNwai(dhuoTp.getNwai());
		// // 标签换帖标记置1-需换贴标签
		// zpngTp.setBqht(ZkfpConstance.BQHT);
		// 薄板单重
		zpngTp.setBdan(SinoUtils.calculateBdan(dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getCang()));
		// // 包装材料重量
		// zpngTp.setBzcl(dhuoTp.getBzcl());
		// // 包装张数
		// zpngTp.setBzzs(NumberUtils.formatDouToInt(dhuoTp.getKbzs(), 0));
		// // 制品尺寸.长
		// zpngTp.setCang(dhuoTp.getCang());
		// // 差厚镀锡标记
		// zpngTp.setChdx(dhuoTp.getChdx());
		// // 尺寸允许范围.长mm上限.符号
		// // zpngTp.setCsfu(dhuoTp.getCsfu());
		// // 尺寸允许范围.长mm上限.值
		// zpngTp.setCszi(dhuoTp.getCszi());
		// // 尺寸允许范围.长mm下限.符号
		// // zpngTp.setCxfu(dhuoTp.getCxfu());
		// // 尺寸允许范围.长mm下限.值
		// zpngTp.setCxzi(dhuoTp.getCxzi());
		// // 垫木方向
		// zpngTp.setDmfx(dhuoTp.getDmfx());
		// // 垫木重量
		// zpngTp.setDmzl(dhuoTp.getDmzl());
		// // 表面精加工符号
		// zpngTp.setFace(dhuoTp.getFace());
		// // 规格代码
		// zpngTp.setGgno(dhuoTp.getGgno());
		// // 换算尺寸.长
		// zpngTp.setHngc(dhuoTp.getHngc());
		// // 换算尺寸.厚
		// zpngTp.setHngh(dhuoTp.getHngh());
		// // 换算尺寸.宽
		// zpngTp.setHngk(dhuoTp.getHngk());
		// // 制品尺寸.厚
		// zpngTp.setHouu(dhuoTp.getHouu());
		// // 尺寸允许范围.厚%上限.符号
		// // zpngTp.setHsfu(dhuoTp.getHsfu());
		// // 尺寸允许范围.厚%上限.值
		// zpngTp.setHszi(dhuoTp.getHszi());
		// // 化学处理方法
		// zpngTp.setHxcl(dhuoTp.getHuac());
		// // 尺寸允许范围.厚%下限.符号
		// // zpngTp.setHxfu(dhuoTp.getHxfu());
		// // 尺寸允许范围.厚%下限.值
		// zpngTp.setHxzi(dhuoTp.getHxzi());
		// // 锯齿形式
		// zpngTp.setJcxs(dhuoTp.getJcxs());
		// // 交货重量内容
		// zpngTp.setJhnr(dhuoTp.getJhnr());
		// // 直角度
		// zpngTp.setJiao(dhuoTp.getJiao());
		// // 捆包形式
		// zpngTp.setKbao(dhuoTp.getKbao());
		// // 捆包指定重量.上限
		// zpngTp.setKbzs(dhuoTp.getKbzs());
		// // 捆包指定重量.下限
		// zpngTp.setKbzx(dhuoTp.getKbzx());
		// // 附面KPNO.1标识位
		// zpngTp.setKpn1Flag(dhuoTp.getKpn1Flag());
		// // 附页KPNO.1
		// zpngTp.setKpn1(dhuoTp.getKpn1());
		// // 附面KPNO.2标识位
		// zpngTp.setKpn2Flag(dhuoTp.getKpn2Flag());
		// // 附面KPNO.2
		// zpngTp.setKpn2(dhuoTp.getKpn2());
		// // 附面KPNO.3标识位
		// zpngTp.setKpn3Flag(dhuoTp.getKpn3Flag());
		// // 附面KPNO.3
		// zpngTp.setKpn3(dhuoTp.getKpn3());
		// // 附面KPNO.4标识位
		// zpngTp.setKpn4Flag(dhuoTp.getKpn4Flag());
		// // 附面KPNO.4
		// zpngTp.setKpn4(dhuoTp.getKpn4());
		// // 尺寸允许范围.宽mm上限.符号
		// // zpngTp.setKsfu(dhuoTp.getKsfu());
		// // 尺寸允许范围.宽mm上限.值
		// zpngTp.setKszi(dhuoTp.getKszi());
		// // 制品尺寸.宽
		// zpngTp.setKuan(dhuoTp.getKuan());
		// // 尺寸允许范围.宽mm下限.符号
		// // zpngTp.setKxfu(dhuoTp.getKxfu());
		// // 尺寸允许范围.宽mm下限.值
		// zpngTp.setKxzi(dhuoTp.getKxzi());
		// // 尺寸允许范围.厚mm上限.符号
		// // zpngTp.setMxfu(dhuoTp.getHsfu());
		// // 尺寸允许范围.厚mm上限.值
		// zpngTp.setMszi(dhuoTp.getHszi());
		// // 尺寸允许范围.厚mm下限.符号
		// // zpngTp.setMxfu(dhuoTp.getHxfu());
		// // 尺寸允许范围.厚mm下限.值
		// zpngTp.setMxzi(dhuoTp.getHxzi());
		// // 内径保护筒标记
		// zpngTp.setNjbh(dhuoTp.getNjbh());
		// // 内径代码
		// zpngTp.setNjno(dhuoTp.getNeij());
		// // 内外销
		// zpngTp.setNwai(dhuoTp.getNwai());
		// // K-Plate表示
		// zpngTp.setPlat(dhuoTp.getPlat());
		// // 通货区分
		// zpngTp.setThqf(dhuoTp.getThqf());
		// // 涂油种类
		// zpngTp.setYtyp(dhuoTp.getYtyp());
		// // 运用规格
		// zpngTp.setYuny(dhuoTp.getYuny());
		// // 压延方向指定
		// zpngTp.setYyan(dhuoTp.getYyan());
		// // 再剪边标记
		// zpngTp.setZjbb(zjbb);
		// // SL装入宽
		// zpngTp.setZrkn(dhuoTp.getZrkn());
	}

	/**
	 * <p>
	 * 对分配制品，设置分配操作记录
	 * </p>
	 * 
	 * @param jbno
	 *            卷板No/PileNo
	 * @param dhno
	 *            订货No.
	 * @param line
	 *            行号
	 * @param xpzk
	 *            现品状况
	 * @param czlx
	 *            操作类型
	 * @param fpno
	 *            分配No.
	 * @param date
	 *            操作时间
	 * @param user
	 *            担当者
	 * @param sfpp
	 *            是否匹配
	 * @return ZkfpCzjl
	 */
	private ZkfpCzjl parseCzjl(String jbno, String dhno, String line,
			String xpzk, String czlx, String fpno, Date date, User user,
			String sfpp) {
		ZkfpCzjl zkfpCzjl = null;
		zkfpCzjl = new ZkfpCzjl();
		// 卷板No/PileNo
		zkfpCzjl.setJbno(jbno);
		// 订货No.和行号
		zkfpCzjl.setDhno(dhno + line);
		// 分配No.
		zkfpCzjl.setFpno(fpno);
		// 现品状况
		zkfpCzjl.setXpzk(xpzk);
		// 是否匹配
		zkfpCzjl.setSfpp(sfpp);
		// 操作类型
		zkfpCzjl.setCzlx(czlx);
		// 操作时间
		zkfpCzjl.setCrea(date);
		// 担当者代码
		zkfpCzjl.setDdno(user.getNo());
		// 担当者
		zkfpCzjl.setDdnm(user.getName());
		return zkfpCzjl;
	}

	/**
	 * <p>
	 * 现品信息与订货合同信息不匹配。要分配错误表中新增一条信息
	 * </p>
	 * 
	 * @param ycaiTp
	 *            素材
	 * @param zpngTp
	 *            中间品、剪切板制品或卷制品
	 * @param czjl
	 *            在库分配记录
	 * @return ZkfpCwjl
	 */
	public ZkfpCwjl parseCwjl(YcaiTp ycaiTp, ZpngTp zpngTp, ZkfpCzjl czjl) {
		ZkfpCwjl cwjl = null;
		if (ycaiTp == null && zpngTp == null) {
			return cwjl;
		}
		cwjl = new ZkfpCwjl();
		cwjl.setCzid(czjl.getId());
		if (ycaiTp != null) {
			cwjl.setPzno(ycaiTp.getPzno());
			cwjl.setNwai(null);
			cwjl.setYuny(ycaiTp.getYuny());
			cwjl.setFpdj(null);
			cwjl.setXpkn(ycaiTp.getXpkn());
			cwjl.setXpho(ycaiTp.getXpho());
			cwjl.setXpcn(null);
			cwjl.setJcxs(null);
			cwjl.setJiao(null);
			cwjl.setZjbb(null);
			cwjl.setYtyp(null);
			cwjl.setNjbh(null);
			cwjl.setHxcl(null);
			cwjl.setYyan(null);
			cwjl.setPlat(null);
			cwjl.setChdx(null);
			cwjl.setProt(null);
			cwjl.setKpn1Flag(null);
			cwjl.setKpn1(null);
			cwjl.setKpn2Flag(null);
			cwjl.setKpn2(null);
			cwjl.setKpn3Flag(null);
			cwjl.setKpn3(null);
			cwjl.setKpn4Flag(null);
			cwjl.setKpn4(null);
			cwjl.setZpzl(ycaiTp.getZpzl());
			cwjl.setZshu(null);
			cwjl.setRjet(null);
			cwjl.setNjno(ycaiTp.getNjno());
			cwjl.setKbao(null);
			cwjl.setDmfx(null);
			cwjl.setChan(null);
			cwjl.setZrkn(null);
			cwjl.setGzlx(ycaiTp.getGzlx());
			cwjl.setScfm(null);
			cwjl.setSczm(null);
			cwjl.setYing(null);
			cwjl.setZzsd(ycaiTp.getZzsd());
		}
		else {
			cwjl.setPzno(zpngTp.getPzno());
			cwjl.setNwai(zpngTp.getNwai());
			cwjl.setYuny(zpngTp.getYuny());
			cwjl.setFpdj(zpngTp.getDeng());
			cwjl.setXpkn(zpngTp.getXpkn());
			cwjl.setXpho(zpngTp.getXpho());
			cwjl.setXpcn(zpngTp.getXpcn());
			cwjl.setJcxs(zpngTp.getJcxs());
			cwjl.setJiao(zpngTp.getJiao());
			cwjl.setZjbb(zpngTp.getZjbb());
			cwjl.setYtyp(zpngTp.getYtyp());
			cwjl.setNjbh(zpngTp.getNjbh());
			cwjl.setHxcl(zpngTp.getHxcl());
			cwjl.setYyan(zpngTp.getYyan());
			cwjl.setPlat(zpngTp.getPlat());
			cwjl.setChdx(zpngTp.getChdx());
			cwjl.setProt(zpngTp.getProt());
			cwjl.setKpn1Flag(zpngTp.getKpn1Flag());
			cwjl.setKpn1(zpngTp.getKpn1());
			cwjl.setKpn2Flag(zpngTp.getKpn2Flag());
			cwjl.setKpn2(zpngTp.getKpn2());
			cwjl.setKpn3Flag(zpngTp.getKpn3Flag());
			cwjl.setKpn3(zpngTp.getKpn3());
			cwjl.setKpn4Flag(zpngTp.getKpn4Flag());
			cwjl.setKpn4(zpngTp.getKpn4());
			cwjl.setZpzl(zpngTp.getZpzl());
			cwjl.setZshu(zpngTp.getZshu());
			cwjl.setRjet(zpngTp.getRjet());
			cwjl.setNjno(zpngTp.getNjno());
			cwjl.setKbao(zpngTp.getKbao());
			cwjl.setDmfx(zpngTp.getDmfx());
			cwjl.setChan(zpngTp.getChan());
			cwjl.setZrkn(zpngTp.getZrkn());
			cwjl.setGzlx(zpngTp.getGzlx());
			cwjl.setScfm(zpngTp.getScfm());
			cwjl.setSczm(zpngTp.getSczm());
			cwjl.setYing(zpngTp.getYing());
			cwjl.setZzsd(zpngTp.getZzsd());
		}
		return cwjl;
	}

	@Override
	public String getFpzs(String dhno, String line) {
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			return new Result(-1, "该订货No.对应的订货合同不存在").toString();
		}
		if (!(DhStat.confirm.key.equals(dhuoTp.getStat()) || DhStat.assign.key.equals(dhuoTp.getStat()))) {
			StringBuilder error = new StringBuilder();
			error.append("该订货合同状态为").append(DhStat.get(dhuoTp.getStat()).name).append("不能做分配操作");
			return new Result(-1, error.toString()).toString();
		}
		FpVO entity = parseFpVO(null, dhuoTp);
		entity.setXpzk(EXpzk.SC.key);
		return new Result(entity).toJsObject();
	}

	@Override
	public String getFpqx(String fpno) {
		ZsdxTp zsdxTp = zsBO.getUniqueZsdx(fpno);
		if (zsdxTp == null) {
			return new Result(-1, "待取消分配No.不存在").toString();
		}
		String dhno = zsdxTp.getDhno();
		if (dhno == null || dhno.isEmpty()) {
			return new Result(-1, "待取消分配No.对应的指示对象信息有误").toString();
		}
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno.substring(0, 7),
				dhno.substring(7)));
		if (dhuoTp == null) {
			return new Result(-1, "分配No.对应的订货No." + dhno + "不存在").toString();
		}
		return new Result(parseFpVO(zsdxTp, dhuoTp)).toJsObject();
	}

	/**
	 * <p>
	 * 分配指示VO
	 * </p>
	 * 
	 * @param zsdxTp
	 * @param dhuoTp
	 * @return FpVO
	 */
	private FpVO parseFpVO(ZsdxTp zsdxTp, DhuoTp dhuoTp) {
		FpVO entity = new FpVO();
		// 计算订货合同的未分配量。未分配量=合同量-分配量
		// 合同量
		double htzl = dhuoTp.getHtzl() != null ? dhuoTp.getHtzl() : 0;
		// 分配量
		double fpln = dhuoTp.getFpln() != null ? dhuoTp.getFpln() : 0;
		// 未分配量
		double wfpl = NumberUtils.format(htzl - fpln, 3);
		entity.setWfpl(wfpl);
		if (dhuoTp != null) {
			entity.setFace(dhuoTp.getFace());
			entity.setAbbr(dhuoTp.getAbbr());
			entity.setDhno(dhuoTp.getDhno());
			entity.setLine(dhuoTp.getLine());
			entity.setGgno(dhuoTp.getGgno());
			entity.setFudw(dhuoTp.getFudw());
			entity.setFuzm(dhuoTp.getFuzm());
			entity.setFufm(dhuoTp.getFufm());
			entity.setCang(dhuoTp.getCang());
			entity.setHouu(dhuoTp.getHouu());
			entity.setKuan(dhuoTp.getKuan());
		}
		if (zsdxTp != null) {
			entity.setXpzk(zsdxTp.getYczk());
			entity.setZxbb(zsdxTp.getZxbb());
			entity.setJbkb(zsdxTp.getJbkb());
			entity.setClfp(zsdxTp.getClfp());
			entity.setQzbj(zsdxTp.getQzbj());
			entity.setCqzs(zsdxTp.getCqzs() != null ? NumberUtils.format(zsdxTp.getCqzs() / 1000d, 3)
					: 0);
			entity.setFpno(zsdxTp.getFpno());
		}
		return entity;
	}

	@Override
	public void fetchZkfpError(ZkfpErrorVO vo, OutputStream os) {
		ZsdxQE qentity = new ZsdxQE();
		qentity.setCreaB(vo.getFpBegin());
		qentity.setCreaE(vo.getFpEnd());
		qentity.setNeFpno(ZtConstants.ZSDX_FP_NO);
		qentity.setDhno(vo.getDhnoAndLine());
		qentity.setJbno(vo.getJbno());
		qentity.setSfpp(Sfpp.nmatch.key);
		qentity.setSize(-1);
		zsBO.queryZsdxtp(qentity);

		ZpngTp zpngTp = null;
		YcaiTp ycaiTp = null;
		List<CError> errors = null;
		DhuoTp dhuoTp = null;
		String dhno;
		String jbno;
		String xpzk;
		for (ZsdxTp zsdxTp : qentity.getDatas()) {
			dhno = zsdxTp.getDhno();
			jbno = zsdxTp.getJbno();
			xpzk = zsdxTp.getYczk();
			if (dhno == null || jbno == null || xpzk == null) continue;
			dhuoTp = dhBO.get(new DhuoTpPk(dhno.substring(0, 7),
					dhno.substring(7)));
			if (dhuoTp == null) continue;
			if (EXpzk.SC.key.equals(zsdxTp.getYczk())) {
				ycaiTp = ycaitpBO.get(jbno);
			}
			else {
				zpngTp = zpBO.getZp(jbno);
			}
			if (Code119.prime.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为一级品时
				errors = findComparePrimeErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			else if (Code119.grade2.key.equals(dhuoTp.getPzno().substring(1))
					|| Code119.grade3.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为O/R1级或O/R2级时
				errors = findCompareOrErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			else if (Code119.grade4.key.equals(dhuoTp.getPzno().substring(1))) {
				// 当订货合同为发生品时
				errors = findCompareFsErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			else {
				// 当订货合同为端板时
				errors = findCompareNsErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
			}
			vo.getCwjls().put(zsdxTp, errors);
		}
		ExcelUtils.fillData(zkfpErrorPath, vo, zkfpErrorExec, os);
	}

	private FpckItemVO parseFpckItemVO(ZpngTp zpngTp, YcaiTp ycaiTp) {
		FpckItemVO fpckItemVO = new FpckItemVO();
		if (zpngTp != null) {
			fpckItemVO.setYuny(zpngTp.getYuny());
			fpckItemVO.setXpho(zpngTp.getXpho());
			fpckItemVO.setXpkn(zpngTp.getXpkn());
			fpckItemVO.setXpcn(zpngTp.getXpcn());
			fpckItemVO.setFace(zpngTp.getFace());
			fpckItemVO.setXpzk(zpngTp.getXpzk());
			fpckItemVO.setZtbj(zpngTp.getZtbj());
			fpckItemVO.setPlqf(zpngTp.getPlqf());
			fpckItemVO.setZzsd(zpngTp.getZzsd());
			fpckItemVO.setJbno(zpngTp.getJbno());
			fpckItemVO.setDuic(zpngTp.getKw());
			fpckItemVO.setYyan(zpngTp.getYyan());
			fpckItemVO.setDmfx(zpngTp.getDmfx());
			fpckItemVO.setZpzl(zpngTp.getZpzl());
			fpckItemVO.setChan(zpngTp.getChan());
			fpckItemVO.setDeng(zpngTp.getDeng());
			fpckItemVO.setYtyp(zpngTp.getYtyp());
			fpckItemVO.setSczm(zpngTp.getSczm());
			fpckItemVO.setScfm(zpngTp.getScfm());
			fpckItemVO.setYsuo(zpngTp.getYsuo());
			fpckItemVO.setRczpno(zpngTp.getRczpno());
			fpckItemVO.setAbbr(zpngTp.getAbbr());
			return fpckItemVO;
		}
		fpckItemVO.setYuny(ycaiTp.getYuny());
		fpckItemVO.setXpho(ycaiTp.getXpho());
		fpckItemVO.setXpkn(ycaiTp.getXpkn());
		fpckItemVO.setXpcn(null);
		fpckItemVO.setFace(ycaiTp.getFace());
		fpckItemVO.setXpzk(ycaiTp.getXpzk());
		fpckItemVO.setZtbj(ycaiTp.getZtbj());
		fpckItemVO.setPlqf(null);
		fpckItemVO.setZzsd(ycaiTp.getZzsd());
		fpckItemVO.setJbno(ycaiTp.getJbno());
		fpckItemVO.setDuic(ycaiTp.getKw());
		fpckItemVO.setYyan(ycaiTp.getYyan());
		fpckItemVO.setDmfx(null);
		fpckItemVO.setChan(ycaiTp.getChan());
		fpckItemVO.setDeng(ycaiTp.getDeng());
		fpckItemVO.setYtyp(null);
		fpckItemVO.setSczm(null);
		fpckItemVO.setScfm(null);
		fpckItemVO.setYsuo(ycaiTp.getYsuo());
		fpckItemVO.setRczpno(null);
		fpckItemVO.setAbbr(ycaiTp.getYcbr());
		fpckItemVO.setZzsj(ycaiTp.getYzzsj());
		double cgdj = ycaiTp.getCgdj() != null ? ycaiTp.getCgdj() : 0;
		int zpzl = ycaiTp.getZpzl() != null ? ycaiTp.getZpzl() : 0;
		double ybcb = ycaiTp.getYbcb() != null ? ycaiTp.getYbcb() : 0;
		fpckItemVO.setCgdj(cgdj);
		fpckItemVO.setZpzl(zpzl);
		fpckItemVO.setCbfy(NumberUtils.format(cgdj * zpzl / 1000.0 + ybcb, 3));
		fpckItemVO.setYsuo(ycaiTp.getBlny());
		fpckItemVO.setRczpno(ycaiTp.getYbno() + ycaiTp.getLine());
		return fpckItemVO;
	}

	@Override
	public void fetchFpckList(FpckVO vo, OutputStream os) {
		List<FpckItemVO> items = new ArrayList<FpckItemVO>();
		String[] fpycs = { EFpyc.CS.key, EFpyc.YC.key };
		String[] stats = { YbStat.CS.stat, YbStat.YRK.stat };
		if (EXpzk.SC.key.equals(vo.getChan())) {
			YcaiQE ycaiQE = new YcaiQE();
			ycaiQE.setYuny(vo.getYuny());
			ycaiQE.setFace(vo.getFace());
			if (vo.getJbnoS() != null && vo.getJbnoE() == null) {
				ycaiQE.setCoilNo(vo.getJbnoS());
			}
			else {
				ycaiQE.setCoilNoS(vo.getJbnoS());
				ycaiQE.setCoilNoE(vo.getJbnoE());
			}
			if (vo.getXphoS() != null && vo.getXphoE() == null) {
				ycaiQE.setHouu(vo.getXphoS());
			}
			else {
				ycaiQE.setHouuS(vo.getXphoS());
				ycaiQE.setHouuE(vo.getXphoE());
			}
			if (vo.getXpknS() != null && vo.getXpknE() == null) {
				ycaiQE.setKuan(vo.getXpknS());
			}
			else {
				ycaiQE.setKuanS(vo.getXpknS());
				ycaiQE.setKuanE(vo.getXpknE());
			}
			ycaiQE.setYcbr(vo.getYcbr());
			ycaiQE.setScbj(EScbj.CS.key);
			ycaiQE.setStats(stats);
			ycaiQE.setFpyc(fpycs);
			ycaiQE.setOrderBys("yuny asc, xpho asc, xpkn asc, face asc, jbno asc");
			ycaiQE.setSize(-1);
			ycaitpBO.query(ycaiQE);
			if (ycaiQE.getDatas().size() > 0) {
				for (YcaiTp ycaiTp : ycaiQE.getDatas()) {
					items.add(parseFpckItemVO(null, ycaiTp));
				}
			}
		}
		else {
			ZpQE zpQE = new ZpQE();
			zpQE.setYuny(vo.getYuny());
			zpQE.setChan(vo.getChan());
			zpQE.setFace(vo.getFace());
			if (vo.getJbnoS() != null && vo.getJbnoE() == null) {
				zpQE.setJbno(vo.getJbnoS());
			}
			else {
				zpQE.setJbnoStart(vo.getJbnoS());
				zpQE.setJbnoEnd(vo.getJbnoE());
			}
			if (vo.getXphoS() != null && vo.getXphoE() == null) {
				zpQE.setXpho(vo.getXphoS());
			}
			else {
				zpQE.setXphoS(vo.getXphoS());
				zpQE.setXphoE(vo.getXphoE());
			}
			if (vo.getXpknS() != null && vo.getXpknE() == null) {
				zpQE.setXpkn(vo.getXpknS());
			}
			else {
				zpQE.setXpknS(vo.getXpknS());
				zpQE.setXpknE(vo.getXpknE());
			}
			if (vo.getXpcnS() != null && vo.getXpcnE() == null) {
				zpQE.setXpcn(vo.getXpcnS());
			}
			else {
				zpQE.setXpcnS(vo.getXpcnS());
				zpQE.setXpcnE(vo.getXpcnE());
			}
			if (Code118.sheet.key.equals(vo.getPz())) {
				String[] xpzks = { EXpzk.BZP_KEY, EXpzk.ZJP_KEY };
				zpQE.setXpzks(xpzks);
			}
			else if (Code118.coil.key.equals(vo.getPz())) {
				zpQE.setXpzk(EXpzk.JZP_KEY);
			}
			zpQE.setScbj(EScbj.CS.key);
			zpQE.setStat(ZpStat.CS.stat);
			zpQE.setFpycs(fpycs);
			zpQE.setSize(-1);
			zpQE.setOrderBys("yuny asc, xpho asc, xpkn asc, xpcn asc, face asc, jbno asc");
			zpBO.query(zpQE);
			if (zpQE.getDatas().size() > 0) {
				for (ZpngTp zpngTp : zpQE.getDatas()) {
					items.add(parseFpckItemVO(zpngTp, null));
				}
			}
		}

		vo.setMxs(items);
		ExcelUtils.fillData(fpckListPath, vo, fpckListExec, os);
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	public ICzjlBO getCzjlBO() {
		return czjlBO;
	}

	public void setCzjlBO(ICzjlBO czjlBO) {
		this.czjlBO = czjlBO;
	}

	public ICwjlDAO getCwjlDAO() {
		return cwjlDAO;
	}

	public void setCwjlDAO(ICwjlDAO cwjlDAO) {
		this.cwjlDAO = cwjlDAO;
	}

	public ISeqBO getSeqBo() {
		return seqBo;
	}

	public void setSeqBo(ISeqBO seqBo) {
		this.seqBo = seqBo;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public String getZkfpErrorPath() {
		return zkfpErrorPath;
	}

	public void setZkfpErrorPath(String zkfpErrorPath) {
		this.zkfpErrorPath = zkfpErrorPath;
	}

	public ExcelbookDataExecuter<ZkfpErrorVO> getZkfpErrorExec() {
		return zkfpErrorExec;
	}

	public void setZkfpErrorExec(
			ExcelbookDataExecuter<ZkfpErrorVO> zkfpErrorExec) {
		this.zkfpErrorExec = zkfpErrorExec;
	}

	public String getFpckListPath() {
		return fpckListPath;
	}

	public void setFpckListPath(String fpckListPath) {
		this.fpckListPath = fpckListPath;
	}

	public ExcelbookDataExecuter<FpckVO> getFpckListExec() {
		return fpckListExec;
	}

	public void setFpckListExec(ExcelbookDataExecuter<FpckVO> fpckListExec) {
		this.fpckListExec = fpckListExec;
	}

	public ISczsBO getSczsBO() {
		return sczsBO;
	}

	public void setSczsBO(ISczsBO sczsBO) {
		this.sczsBO = sczsBO;
	}

}
