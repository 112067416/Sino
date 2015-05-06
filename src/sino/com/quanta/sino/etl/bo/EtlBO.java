package com.quanta.sino.etl.bo;

import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.bean.Result;
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
import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.Ban;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.Czlx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EEtlStop;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.EPass;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.dao.api.IEtlDAO;
import com.quanta.sino.etl.dao.api.IRcrzDAO;
import com.quanta.sino.etl.vo.EtlBanTjVO;
import com.quanta.sino.etl.vo.EtlSbMainDataVO;
import com.quanta.sino.etl.vo.EtlSbStopDataVO;
import com.quanta.sino.etl.vo.EtlSbTjVO;
import com.quanta.sino.etl.vo.EtlStopMxVO;
import com.quanta.sino.etl.vo.EtlStopVO;
import com.quanta.sino.etl.vo.EtlSyAllSheetVO;
import com.quanta.sino.etl.vo.EtlSyBanSheetVO;
import com.quanta.sino.etl.vo.EtlSybmSheetVO;
import com.quanta.sino.etl.vo.EtlSydjDataVO;
import com.quanta.sino.etl.vo.EtlSydjVO;
import com.quanta.sino.etl.vo.EtlSylmSheetVO;
import com.quanta.sino.etl.vo.SBVO;
import com.quanta.sino.etl.vo.SBmxVO;
import com.quanta.sino.etl.vo.SjMainVO;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.etl.vo.SjlrVO;
import com.quanta.sino.etl.vo.XzVO;
import com.quanta.sino.etl.vo.YxRecordVO;
import com.quanta.sino.etl.vo.ZpSLVO;
import com.quanta.sino.etl.vo.ZsViewVO;
import com.quanta.sino.kb.constants.KbConstants;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ETLStopTp;
import com.quanta.sino.orm.ETLVariTp;
import com.quanta.sino.orm.ETLsbTp;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.orm.EtlsLp;
import com.quanta.sino.orm.PngzLp;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.RiziLpPk;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZkfpCzjl;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZscdTpPk;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZsdxTp;
import com.quanta.sino.orm.ZsdxTpPk;
import com.quanta.sino.orm.Zsfmt;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.orm.ZszrTpPk;
import com.quanta.sino.sj.bo.api.ISjBO;
import com.quanta.sino.sj.vo.SjVO;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 实绩录入业务层
 * </p>
 * <p>
 * create: 2011-1-21 下午12:06:40
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlBO implements IEtlBO {

	/**
	 * 
	 */
	private IEtlDAO dao;
	/**
	 * 订货指示接口
	 */
	private IZsBO zsBO;
	/**
	 * 原材原板接口
	 */
	private IYcaitpBO ycaitpBO;
	/**
	 * 余材化处理业务层接口
	 */
	private IYchBO ychBO;
	/**
	 * 订货管理接口
	 */
	private IDhBO dhBO;
	/**
	 * 公共业务接口
	 */
	private ICmnBO cmnBO;
	/**
	 * 制品业务接口
	 */
	private IZpBO zpBO;
	/**
	 * 代码管理业务层接口
	 */
	private ICodeBO codeBO;
	/**
	 * 分配和余材操作记录业务接口
	 */
	private ICzjlBO czjlBO;

	/**
	 * 实绩日志公共格式业务接口
	 */
	private ISjBO sjBO;

	/**
	 * 
	 */
	private IEtlpzglBO etlpzglBO;

	/**
	 * 检查步留明细路径
	 */
	private String sydjPath;
	/**
	 * ETL速报细路径
	 */
	private String sbdjPath;

	/**
	 * 分检查步留明细处理接口
	 */
	private ExcelbookDataExecuter<EtlSydjDataVO> sydjExec;
	/**
	 * ETL速报处理接口
	 */
	private ExcelbookDataExecuter<EtlSbMainDataVO> sbdjExec;
	/**
	 * 注入JDBC
	 */
	private JdbcTemplate jdbc;
	/**
	 * 班别接口
	 */
	private IBanBO banBO;

	/**
	 * PC6000工控系统数据库访问接口
	 */
	private IRcrzDAO rcrzDAO;

	@Override
	public void updateEtlLodyPrint(String jbno) {
		zpBO.updateEtlLodyPrint(jbno);
	}

	@Override
	public String get(String zsno, String jbno, String elin) {
		// 操作Mode
		String caoz;
		// 订货No.行号
		String dhnoline;
		// 交货重量内容
		String jhnr;
		// 查询指示DB(装入卷板)
		ZszrTp zszr = dao.getZszrTp(new ZszrTpPk(zsno, jbno));
		if (zszr == null) {
			throw new CocoException(-1, "指示书号和卷板号不存在！");
		}
		// 检查别的装入卷板是否中止或终了
		if (ycaitpBO.isZl(jbno, elin)) {
			throw new CocoException(-1, "同一个生产线，别的卷实绩录入未终了或中止！");
		}
		// 查询订货指示DB的操作Mode和 订货No.行号
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		// 检查指示书完了标志
		if (ZtConstants.DHZS_ZSBJ_YWC.equals(zsdh.getZsbj())) {
			throw new CocoException(-1, "指示书发行完了！");
		}
		// 检查指示书是否分派
		if (!ZtConstants.DHZS_STAT_YFP.equals(zsdh.getStat())) {
			throw new CocoException(-1, "指示书没有分派！");
		}
		// 查询原材原板
		YcaiTp ycai = ycaitpBO.get(jbno);
		if (ycai == null) {
			throw new CocoException(-1, "原板原材不存在！");
		}
		if ((YbStat.SJZL.stat.equals(ycai.getStat()))
				|| (YbStat.SJZZ.stat.equals(ycai.getStat()))) {
			throw new CocoException(-1, "指示书实绩终了或中止了！");
		}
		if (ycai.getZtbj() != null) {
			throw new CocoException(-1, "原板原材已经停止！");
		}
		// 订货NO和行号
		dhnoline = zsdh.getDhno();
		if (dhnoline == null || dhnoline.isEmpty()) {
			throw new CocoException(-1, "订货DB的订货NO不存在！");
		}
		// 操作
		caoz = zsdh.getCaoz();
		if (ZtConstants.DHZS_CAOZ_S.equals(caoz)) {
			return ZtConstants.DHZS_CAOZ_S;
		}
		if (ZtConstants.DHZS_CAOZ_C.equals(caoz)) {
			// 查询订货DB
			DhuoTp dhuo;
			DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
			dhuo = dhBO.get(dhpk);
			if (dhuo == null) {
				throw new CocoException(-1, "订货DB不存在！");
			}
			// 订货DB的重内
			jhnr = dhuo.getJhnr();
			if ((jhnr == null) || (jhnr.isEmpty())) {
				throw new CocoException(-1, "订货DB的重内不存在！");
			}
			return ZlnrCode.getForCaoz(jhnr);
		}
		else {
			throw new CocoException(-1, "订货指示DB的操作Mode不存在！");
		}

	}

	@Override
	public String get(String jbno) {
		// 操作Mode
		String caoz;
		// 订货No.行号
		String dhnoline;
		// 交货重量内容
		String jhnr;
		ZpngTp zp = zpBO.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		Date crea = zp.getCrea();
		if (crea == null) {
			throw new CocoException(-1, "作成时间为空！");
		}
		Date nowDate = new Date();
		if ((nowDate.getTime() - crea.getTime()) > 86400000) {
			throw new CocoException(-1, "实绩登录已超过24小时，不能订正！");
		}
		if (!ZpStat.CS.stat.equals(zp.getStat())) {
			throw new CocoException(-1, "制品不是初始，不能订正！");
		}
		if (zp.getZtbj() != null) {
			throw new CocoException(-1, "制品已经停止，不能订正！");
		}
		if (ZtConstants.ZPNG_JD_SLZS_YZS.equals(zp.getJdsz())) {
			throw new CocoException(-1, "SL指示中，不能订正！");
		}
		if (zp.getChno() != null) {
			throw new CocoException(-1, "已经生成出货指示，不能订正！");
		}
		// if (EScbj.KEY_YSJZL.equals(zp.getScbj())) {
		// throw new CocoException(-1, "该记录已不存在，不能进行订正！");
		// }
		String rczpno = zp.getRczpno();
		if (rczpno == null) {
			throw new CocoException(-1, "制品入侧端COIL/PILE不存在！");
		}
		// 查询原材原板
		YcaiTp ycai = ycaitpBO.get(rczpno);
		if (ycai == null) {
			throw new CocoException(-1, "制品对应的原板原材不存在！");
		}
		if (ycai.getZtbj() != null) {
			throw new CocoException(-1, "原板原材已经停止！");
		}
		String zsno = ycai.getZsno();
		if (zsno == null) {
			throw new CocoException(-1, "原板原材指示书NO不存在！");
		}
		// 查询订货指示DB的操作Mode和 订货No.行号
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		// if (ZtConstants.DHZS_ZSFX_YDY.equals(zsdh.getZsfx())) {
		// throw new CocoException(-1, "指示书发行完了！");
		// }
		// 订货NO和行号
		dhnoline = zsdh.getDhno();
		if (dhnoline == null || dhnoline.isEmpty()) {
			throw new CocoException(-1, "订货DB的订货NO不存在！");
		}
		// 操作
		caoz = zsdh.getCaoz();
		if (ZtConstants.DHZS_CAOZ_S.equals(caoz)) {
			return ZtConstants.DHZS_CAOZ_S;
		}
		if (ZtConstants.DHZS_CAOZ_C.equals(caoz)) {
			// 查询订货DB
			DhuoTp dhuo;
			DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
			dhuo = dhBO.get(dhpk);
			if (dhuo == null) {
				throw new CocoException(-1, "订货DB不存在！");
			}
			// 订货DB的重内
			jhnr = dhuo.getJhnr();
			if ((jhnr == null) || (jhnr.isEmpty())) {
				throw new CocoException(-1, "订货DB的重内不存在！");
			}
			return ZlnrCode.getForCaoz(jhnr);
		}
		else {
			throw new CocoException(-1, "订货指示DB的操作Mode不存在！");
		}

	}

	@Override
	public String getMode(String jbno) {
		// 操作Mode
		String caoz;
		// 订货No.行号
		String dhnoline;
		// 交货重量内容
		String jhnr;
		ZpngTp zp = zpBO.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		String rczpno = zp.getRczpno();
		if (rczpno == null) {
			throw new CocoException(-1, "制品入侧端COIL/PILE不存在！");
		}
		// 查询原材原板
		YcaiTp ycai = ycaitpBO.get(rczpno);
		if (ycai == null) {
			throw new CocoException(-1, "制品对应的原板原材不存在！");
		}
		String zsno = ycai.getZsno();
		if (zsno == null) {
			throw new CocoException(-1, "原板原材指示书NO不存在！");
		}
		// 查询订货指示DB的操作Mode和 订货No.行号
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		// 订货NO和行号
		dhnoline = zsdh.getDhno();
		if (dhnoline == null || dhnoline.isEmpty()) {
			throw new CocoException(-1, "订货DB的订货NO不存在！");
		}
		// 操作
		caoz = zsdh.getCaoz();
		if (ZtConstants.DHZS_CAOZ_S.equals(caoz)) {
			return ZtConstants.DHZS_CAOZ_S;
		}
		if (ZtConstants.DHZS_CAOZ_C.equals(caoz)) {
			// 查询订货DB
			DhuoTp dhuo;
			DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
			dhuo = dhBO.get(dhpk);
			if (dhuo == null) {
				throw new CocoException(-1, "订货DB不存在！");
			}
			// 订货DB的重内
			jhnr = dhuo.getJhnr();
			if ((jhnr == null) || (jhnr.isEmpty())) {
				throw new CocoException(-1, "订货DB的重内不存在！");
			}
			return ZlnrCode.getForCaoz(jhnr);
		}
		else {
			throw new CocoException(-1, "订货指示DB的操作Mode不存在！");
		}
	}

	@Override
	public SjlrVO getFuZi(String zsno, String jbno) {
		// 查询订货指示DB
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		// 界面录入VO
		SjlrVO sjlrVO = new SjlrVO();
		// 生成出端COIL No.
		String[] collNos = cmnBO.generateNo(jbno, NoGenType.end);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB为空！");
		}
		// 制品尺寸厚
		Double houu = zsdh.getHouu();
		// 制品尺寸宽
		Double kuan = zsdh.getKuan();
		// 订货NO和行号
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "订货指示DB的订货NO为空！");
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoAndLine);
		// 查询订货DB
		DhuoTp dhuo = dhBO.get(dhpk);
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB为空！");
		}
		String fuzm = NumberUtils.format(Integer.parseInt(dhuo.getFuzm()) * 1.12 / 10, 2).toString();
		sjlrVO.setFuzm(fuzm);
		String fufm = NumberUtils.format(Integer.parseInt(dhuo.getFufm()) * 1.12 / 10, 2).toString();
		sjlrVO.setFufm(fufm);
		sjlrVO.setHouu(houu);
		sjlrVO.setKuan(kuan);
		sjlrVO.setZsno(zsno);
		sjlrVO.setZrjb(jbno);
		sjlrVO.setDhno(dhnoAndLine);
		sjlrVO.setAbbr(dhuo.getAbbr());
		if (collNos.length > 0) {
			sjlrVO.setJbno(collNos[0]);
		}
		if (zsdh.getPzno() != null) {
			sjlrVO.setPzno(zsdh.getPzno().substring(0, 1));
		}
		return sjlrVO;
	}

	@Override
	public Integer getJbcn(Integer sjzl, String zsno) {
		sjzl = sjzl == null ? 0 : sjzl;
		// 查询订货指示DB
		Integer jbcn = 0;
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			return jbcn;
		}
		// m单重
		double mdan = zsdh.getMdan() != null ? zsdh.getMdan() : 0;
		// 卷取长＝实称重量（界面）/m单重
		jbcn = mdan > 0 ? NumberUtils.formatDouToInt(sjzl / mdan, 0, BigDecimal.ROUND_DOWN)
				: 0;
		return jbcn;

	}

	/**
	 * <p>
	 * 制品长
	 * </p>
	 * 
	 * @param jbcn
	 * @param cutc
	 * @param loss
	 * @return
	 */
	private Integer getZpc(Integer jbcn, Integer cutc, Integer losc,
			Integer losc2, String mode) {
		jbcn = jbcn == null ? 0 : jbcn;
		cutc = cutc == null ? 0 : cutc;
		losc = losc == null ? 0 : losc;
		losc2 = losc2 == null ? 0 : losc2;
		// 制品长
		Integer zpc = 0;
		// 卷重录入
		if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
			zpc = jbcn - cutc - losc - losc2;
		}
		// 卷长录入
		if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
			zpc = jbcn - cutc - losc - losc2;
		}
		return zpc;
	}

	@Override
	public Integer getZpzl(String zsno, Integer sjzl, Integer jbcn,
			Integer cutc, Integer losc, Integer losc2, String chan, String mode) {
		sjzl = sjzl == null ? 0 : sjzl;
		jbcn = jbcn == null ? 0 : jbcn;
		cutc = cutc == null ? 0 : cutc;
		losc = losc == null ? 0 : losc;
		losc2 = losc2 == null ? 0 : losc2;
		Integer zpzl = 0;
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			return 0;
		}
		// m单重
		double mdan = zsdh.getMdan() != null ? zsdh.getMdan() : 0;
		// 卷重录入(合格)
		if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
			// cutc重量＝cutc长*m单重
			// losc重量＝losc长*m单重
			// 出货重量=称重重量(界面)-cutc重量－losc重量
			Double lossZl = NumberUtils.multiply(losc, mdan).doubleValue();
			Double lossZl2 = NumberUtils.multiply(losc2, mdan).doubleValue();
			zpzl = NumberUtils.formatDouToInt(NumberUtils.subtract(sjzl, lossZl, lossZl2).doubleValue(), 0, BigDecimal.ROUND_DOWN);
			return zpzl;
		}
		// 卷长录入(合格)
		if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
			// 制品长＝卷取长（界面）－CUT长－LOSS长
			// 出货重量＝制品长*m单重
			Integer zpc = jbcn - cutc - losc - losc2;
			zpzl = NumberUtils.formatDouToInt(zpc * mdan, 0, BigDecimal.ROUND_DOWN);
			return zpzl;

		}
		return zpzl;
	}

	/**
	 * <p>
	 * 订正时计算界面的实称重量
	 * </p>
	 * 
	 * @param zsno
	 * @param sjzl
	 * @param loss
	 * @param mode
	 * @return
	 */
	// private Integer getSizlByDz(String zsno, Integer sjzl, Integer loss,
	// String mode) {
	// sjzl = sjzl == null ? 0 : sjzl;
	// loss = loss == null ? 0 : loss;
	// ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
	// if (zsdh == null) {
	// return 0;
	// }
	// // m单重
	// double mdan = zsdh.getMdan() != null ? zsdh.getMdan() : 0;
	// // 重量计算补正系数
	// double bzxs = 0;
	// CodeItem code = null;
	// // 取码表的补正系数0.996
	// code = codeBO.getCodeItem(CmnConstants.CODE_200, Code200.BZXS1.key);
	// if (code != null) {
	// String bzxsStr = "0." + code.getValue().substring(1);
	// bzxs = Double.parseDouble(bzxsStr);
	// }
	//
	// // 卷重录入
	// if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
	// // 出货重量=称重重量(界面)-loss重量
	// // loss重量＝loss长*单重*0.996
	// Integer lossZl = NumberUtils.formatDouToInt(loss * mdan * bzxs, 0,
	// BigDecimal.ROUND_DOWN);
	// sjzl = sjzl + lossZl;
	// return sjzl;
	// }
	// // 卷长录入
	// if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
	// return sjzl;
	// }
	// return sjzl;
	//
	// }

	// 毛重
	@Override
	public Integer getMazl(String dhno, Integer jinz) {
		jinz = jinz == null ? 0 : jinz;

		// 查询订货DB
		Integer mazl = 0;
		if (dhno == null || dhno.isEmpty()) {
			return jinz;
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhno);
		// 查询订货DB
		DhuoTp dhuo = dhBO.get(dhpk);
		if (dhuo == null) {
			return jinz;
		}
		// 重量内容
		// String jhnr = dhuo.getJhnr();
		// 垫木重量(Kg)
		Integer dmzl = 0;
		if (dhuo.getDmzl() != null) {
			dmzl = dhuo.getDmzl();
		}
		// 包装材料重量（Kg）
		Integer bzcl = 0;
		if (dhuo.getBzcl() != null) {
			bzcl = dhuo.getBzcl();
		}
		// if (ZlnrCode.nr2.key.equals(jhnr) || ZlnrCode.nr7.key.equals(jhnr)) {
		// mazl = jinz + bzcl;
		// }
		// if (ZlnrCode.nr3.key.equals(jhnr) || ZlnrCode.nr8.key.equals(jhnr)) {
		// mazl = jinz + bzcl + dmzl;
		// }
		// else {
		// mazl = jinz;
		// }
		mazl = jinz + bzcl + dmzl;
		return mazl;
	}

	// 净重
	private Integer getJinz(Integer jszl, Integer sjzl, String mode) {
		jszl = jszl == null ? 0 : jszl;
		sjzl = sjzl == null ? 0 : sjzl;
		// 净重
		Integer jinz = 0;
		// 卷重录入
		if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
			jinz = sjzl;
		}
		// 卷长录入
		if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
			jinz = jszl;
		}

		return jinz;
	}

	// 实际重量
	private Integer getSjzl(Integer zpzl, Integer sjzl, String mode) {
		zpzl = zpzl == null ? 0 : zpzl;
		sjzl = sjzl == null ? 0 : sjzl;
		// 卷重录入
		if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
			// 实称重量＝界面输入实称重量
			return sjzl;
		}
		// 卷长录入
		if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
			// 实际重量＝制品重量
			sjzl = zpzl;
		}
		return sjzl;
	}

	// 计算重量
	private Integer getJszl(Integer zpzl, String mode) {
		zpzl = zpzl == null ? 0 : zpzl;
		// 计算重量
		Integer jszl = 0;
		// 卷重录入
		if (ZtConstants.DHZS_CAOZ_C.equals(mode)) {
			// 计算重量＝制品重量
			jszl = zpzl;
		}
		// 卷长录入
		if (ZtConstants.DHZS_CAOZ_S.equals(mode)) {
			// 计算重量＝制品重量
			jszl = zpzl;
		}
		return jszl;
	}

	//

	@Override
	public void getZzsj(SjMainVO vo) {
		YcaiTp yc = ycaitpBO.getZzsj(YbStat.SJLR.stat);
		if (yc != null) {
			vo.setZsno(yc.getZsno());
			vo.setJbno(yc.getJbno());
			vo.setDhno(yc.getDhno());
			vo.setAbbr(yc.getAbbr());

		}
	}

	@Override
	public String getZpInfo(String jbno, String elin, String mode) {
		// 界面录入VO
		SjSaveVO sjSaveVO = new SjSaveVO();
		// 查询制品
		ZpngTp zp = zpBO.getZp(jbno);
		if (zp == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		if (!elin.equals(zp.getElin())) {
			throw new CocoException(-1, "制品生产线与用户生产线不同！");
		}
		String rczpno = zp.getRczpno();
		if (rczpno == null) {
			throw new CocoException(-1, "制品入侧端COIL/PILE不存在！");
		}
		// 查询原材原板
		YcaiTp ycai = ycaitpBO.get(rczpno);
		if (ycai == null) {
			throw new CocoException(-1, "制品对应的原板原材不存在！");
		}
		String zsno = ycai.getZsno();
		if (zsno == null) {
			throw new CocoException(-1, "原板原材指示书NO不存在！");
		}
		// 查询订货指示DB
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB为空！");
		}
		// 制品尺寸厚
		Double houu = zsdh.getHouu();
		// 制品尺寸宽
		Double kuan = zsdh.getKuan();
		// 订货NO和行号
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "订货指示DB的订货NO为空！");
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoAndLine);
		// 查询订货DB
		DhuoTp dhuo = dhBO.get(dhpk);
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB为空！");
		}
		// 界面实体赋值
		sjSaveVO.setHouu(houu);
		sjSaveVO.setKuan(kuan);
		sjSaveVO.setZsno(zsno);
		if (zsdh.getPzno() != null) {
			sjSaveVO.setPzno(zsdh.getPzno().substring(0, 1));
		}
		sjSaveVO.setZrjb(zp.getRczpno());
		sjSaveVO.setJbno(jbno);
		sjSaveVO.setDhno(dhnoAndLine);

		if (zp.getCutc() != null && zp.getCutc() != 0) {
			sjSaveVO.setCutc(zp.getCutc());
		}
		if (zp.getLosc() != null && zp.getLosc() != 0) {
			sjSaveVO.setLosc(zp.getLosc());
		}
		if (zp.getLosc2() != null && zp.getLosc2() != 0) {
			sjSaveVO.setLosc2(zp.getLosc2());
		}
		// Integer sjzl = getSizlByDz(zsno, zp.getSjzl(), zp.getLosc(), mode);
		sjSaveVO.setSjzl(zp.getSjzl());
		sjSaveVO.setLosq(zp.getLosq());
		sjSaveVO.setLosq2(zp.getLosq2());
		sjSaveVO.setChan(zp.getChan());
		sjSaveVO.setDeng(zp.getDeng());
		sjSaveVO.setCzdm(zp.getCzdm());
		sjSaveVO.setZpzl(zp.getZpzl());
		sjSaveVO.setJdyn(zp.getJdyn());
		sjSaveVO.setJsyn(zp.getJsyn());
		sjSaveVO.setDdno(zp.getDdno());
		sjSaveVO.setQqdm(zp.getQqdm());
		if (zp.getPhgs() != null && zp.getPhgs() != 0) {
			sjSaveVO.setPhgs(zp.getPhgs());
		}
		if (zp.getRjet() != null && zp.getRjet() != 0) {
			sjSaveVO.setRjet(zp.getRjet());
		}
		sjSaveVO.setBhbl(zp.getBhbl());
		sjSaveVO.setJsno(zp.getJsno());

		sjSaveVO.setBopg(zp.getBopg());

		sjSaveVO.setBopj(zp.getBopj());

		sjSaveVO.setBdrg(zp.getBdrg());

		sjSaveVO.setBdrj(zp.getBdrj());

		sjSaveVO.setZbog(zp.getZbog());

		sjSaveVO.setZboj(zp.getZboj());

		sjSaveVO.setJbcn(zp.getJbcn());
		sjSaveVO.setBan(zp.getBan());
		sjSaveVO.setZu(zp.getZu());
		sjSaveVO.setIsfp(zp.getIsfp());
		String fufm = NumberUtils.format(Integer.parseInt(zp.getFufm()) * 1.12 / 10, 2).toString();
		sjSaveVO.setFufm(fufm);
		String fuzm = NumberUtils.format(Integer.parseInt(zp.getFuzm()) * 1.12 / 10, 2).toString();
		sjSaveVO.setFuzm(fuzm);
		sjSaveVO.setSczm(zp.getSczm());
		sjSaveVO.setScfm(zp.getScfm());
		return new Result(sjSaveVO).toJsObject();
	}

	@Override
	public void updateSjLr(SjSaveVO sjVO) {
		if (sjVO == null) {
			return;
		}
		// 查询订货指示DB的操作Mode
		ZsdhTp zsdh = zsBO.getZsdhTp(sjVO.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		// 查询订货DB可更新实体
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "无效订货No.！");
		}
		DhuoTp dhuo = dhBO.getRef(SinoUtils.parseDhuoPk(dhnoAndLine));
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB不存在！");
		}
		// 校验录入数据
		validate(sjVO, zsdh, dhuo);
		// 查询制品可更新实体
		ZpngTp zpng = dao.getZpRef(sjVO.getJbno());

		if (zpng == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		String oldchan = zpng.getChan();
		Date date = new Date();
		// 更新订货DB的SL指示量(吨)、ETL合格(吨)
		updateDh(sjVO, dhuo, zpng);
		// 更新制品在库DB
		updateZP(sjVO, zpng, zsdh, date);
		// 分配/余材标记,产量=1、9时，设定为1
		ychBO.doScYch(zpng, oldchan, dhuo, date);
		// 更新指示对象DB
		// 操作
		String caoz = zsdh.getCaoz();
		// 操作为板材
		if (ZtConstants.DHZS_CAOZ_S.equals(caoz)) {
			updateZsdx(sjVO, zpng, zsdh, date);
		}
		// 新增实绩日志(现品情报共通格式),新增实绩日志（指示情报共通格式）
		SjVO sj = sjBO.saveForZp(sjVO.getJbno(), sjVO.getZsno(), ZtConstants.ZPNG_CLQ_MODY);
		// 新增ETL（镀锡）实绩日志
		saveEtlsLp(zpng, sj.getXpfmt(), sj.getZsfmt());
	}

	private void updateZsdx(SjSaveVO sjVO, ZpngTp zpng, ZsdhTp zsdh, Date upda) {
		ZsdxTp zsdx = new ZsdxTp();
		// 卷板No/PileNo
		zsdx.setJbno(sjVO.getJbno());
		// 分配No
		zsdx.setFpno(ZtConstants.ZSDX_FP_NO);
		// 超量分配标记
		zsdx.setClfp(zsdh.getClfp());
		// 作成时间
		zsdx.setCrea(new Date());
		// 订货No.行号
		zsdx.setDhno(zpng.getDhno());
		// 剪边宽变更标记
		zsdx.setJbkb(zpng.getZjbb());
		// Pile区分
		zsdx.setPlqf(zpng.getPlqf());
		// 强制标记
		zsdx.setQzbj(zsdh.getQzbj());
		// 状态
		zsdx.setStat(ZtConstants.ZSDH_STAT_WZS);
		// 余材状况
		zsdx.setYczk(EXpzk.ZJP.key);
		// 制品重量
		zsdx.setZpzl(sjVO.getZpzl());
		// 再选别标记
		zsdx.setZxbb(zsdh.getZxbb());
		// 原板制造商No
		zsdx.setZzno(zpng.getZzsd());
		zsdx.setCrea(upda);
		zsdx.setUpda(upda);
		// 合格品订正为合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				&& ChanType.hg.key.equals(sjVO.getChan())) {
			// 更新记录
			zsBO.updateZsdx(zsdx);
		}
		// 合格品订正为不合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				&& ChanType.bhg.key.equals(sjVO.getChan())) {
			// 删除记录
			zsBO.deleteZsdxTp(new ZsdxTpPk(zsdx.getFpno(), zsdx.getJbno()));
		}
		// 不合格品订正为合格品
		if (ChanType.bhg.key.equals(zpng.getChan())
				&& ChanType.hg.key.equals(sjVO.getChan())) {
			// 新增记录
			zsBO.saveZsdX(zsdx);
		}
	}

	/**
	 * <p>
	 * 更新订货DB的SL指示量(吨)、ETL合格(吨)
	 * </p>
	 * 
	 * @param sjVO
	 *            界面输入实体
	 * @param sjVO
	 *            订货DB实体
	 * @param zpng
	 *            制品库实体
	 */
	private void updateDh(SjSaveVO sjVO, DhuoTp dhuo, ZpngTp zpng) {
		// 原SL指示量(吨)
		Double zszl = 0.0;
		// 原ETL合格量(吨)
		Double etlh = 0.0;
		// 原制品重量
		Double zpzl1 = 0.0;
		// 订正后的制品重量
		Double zpzl2 = 0.0;
		if (dhuo.getSlzs() != null) {
			zszl = dhuo.getSlzs();
		}
		if (zpng.getZpzl() != null) {
			zpzl1 = zpng.getZpzl() * 0.001;
		}
		if (sjVO.getZpzl() != null) {
			zpzl2 = sjVO.getZpzl() * 0.001;
		}
		if (dhuo.getEtlh() != null) {
			etlh = dhuo.getEtlh();
		}
		String pzno = dhuo.getPzno();
		// 合格品订正为合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			if (ChanType.hg.key.equals(sjVO.getChan())
					|| ChanType.bl.key.equals(sjVO.getChan())) {
				zszl = zszl - zpzl1 + zpzl2;
				etlh = etlh - zpzl1 + zpzl2;
			}
			// dhuo.setEtlh(NumberUtils.format(etlh, 3));
		}
		// 合格品订正为不合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			if (!ChanType.hg.key.equals(sjVO.getChan())
					&& !ChanType.bl.key.equals(sjVO.getChan())) {
				zszl = zszl - zpzl1;
				etlh = etlh - zpzl1;
			}
			// dhuo.setEtlh(NumberUtils.format(etlh, 3));
		}
		// 不合格品订正为合格品
		if (!ChanType.hg.key.equals(zpng.getChan())
				&& !ChanType.bl.key.equals(zpng.getChan())) {
			if (ChanType.hg.key.equals(sjVO.getChan())
					|| ChanType.bl.key.equals(sjVO.getChan())) {
				zszl = zszl + zpzl2;
				etlh = etlh + zpzl2;
			}
			// dhuo.setEtlh(NumberUtils.format(etlh, 3));
		}

		if (Code118.sheet.key.equals(pzno.substring(0, 1))
				|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
			dhuo.setSlzs(NumberUtils.format(zszl, 3));
		}
		dhuo.setEtlh(NumberUtils.format(etlh, 3));
	}

	/**
	 * <p>
	 * 更新制品在库DB
	 * </p>
	 * 
	 * @param sjVO
	 *            界面输入实体
	 * @param zpng
	 *            制品库实体
	 */
	private void updateZP(SjSaveVO sjVO, ZpngTp zpng, ZsdhTp zsdh, Date upda) {
		// 班
		zpng.setIsfp(sjVO.getIsfp());
		// 班
		zpng.setBan(sjVO.getBan());
		// 组
		zpng.setZu(sjVO.getZu());
		// 附着量.正
		zpng.setSczm(sjVO.getSczm());
		// 附着量.反
		zpng.setScfm(sjVO.getScfm());
		// Cut长
		zpng.setCutc(sjVO.getCutc());
		// Loss长
		zpng.setLosc(sjVO.getLosc());
		// Loss长缺陷
		zpng.setLosq(sjVO.getLosq());
		// Loss长
		zpng.setLosc2(sjVO.getLosc2());
		// Loss长缺陷
		zpng.setLosq2(sjVO.getLosq2());
		// 产量
		zpng.setChan(sjVO.getChan());
		// 处置代码
		zpng.setCzdm(sjVO.getCzdm());
		// 等级
		zpng.setDeng(sjVO.getDeng());
		// 装入重量
		zpng.setZrzl(sjVO.getZpzl());
		// 检定员
		zpng.setJdyn(sjVO.getJdyn());
		// 计数员
		zpng.setJsyn(sjVO.getJsyn());
		// 卷取长
		zpng.setJbcn(sjVO.getJbcn());
		// 录入员
		zpng.setDdno(sjVO.getDdno());
		// P.H个数
		zpng.setPhgs(sjVO.getPhgs());
		// 缺陷代码
		zpng.setQqdm(sjVO.getQqdm());
		// 溶接个数.ETL
		zpng.setRjet(sjVO.getRjet());
		// 板厚不良
		zpng.setBhbl(sjVO.getBhbl());
		// 卷上TRNo
		zpng.setJsno(sjVO.getJsno());
		// 边波纹DR.高度

		zpng.setBdrg(sjVO.getBdrg());

		// 边波纹DR.间隔
		zpng.setBdrj(sjVO.getBdrj());
		// 边波纹OP.高度

		zpng.setBopg(sjVO.getBopg());

		// 边波纹OP.间隔
		zpng.setBopj(sjVO.getBopj());
		// 中波纹高度

		zpng.setZbog(sjVO.getZbog());

		// 中波纹间隔
		zpng.setZboj(sjVO.getZboj());

		// 品种代码
		String zspzno = zsdh.getPzno();
		// 堆场
		if (zspzno != null) {
			// 卷制品，外卖材
			if (Code118.coil.key.equals(zspzno.substring(0, 1))) {
				zpng.setDuic(DC.C.name);
				zpng.setKw(DC.C.name);
				// 现品状况
				zpng.setXpzk(EXpzk.JZP_KEY);
				zpng.setZsno(zsdh.getZsno());
				// 捆包形式
				if (ChanType.hg.key.equals(zpng.getChan())
						|| ChanType.bl.key.equals(zpng.getChan())) {
					zpng.setKbao(zsdh.getKbao());
				}
				else {
					zpng.setKbao("S11");
				}

			}
			else {
				if (!ChanType.hg.key.equals(zpng.getChan())
						&& !ChanType.bl.key.equals(zpng.getChan())) {
					zpng.setDuic(DC.C.name);
					zpng.setKw(DC.C.name);
					// 现品状况
					zpng.setXpzk(EXpzk.JZP_KEY);
				}
				else {
					zpng.setDuic(DC.D.name);
					zpng.setKw(DC.D.name);
					// 现品状况
					zpng.setXpzk(EXpzk.ZJP_KEY);
				}
				// 分配No
				zpng.setFpno(ZtConstants.ZSDX_FP_NO);
				// 捆包形式
				if (!ChanType.hg.key.equals(sjVO.getChan())
						&& !ChanType.bl.key.equals(sjVO.getChan())) {
					zpng.setKbao("S11");
					zpng.setZsno(zsdh.getZsno());
				}

			}

		}

		// 计算得出
		// 制品重量
		Integer zpzl = getZpzl(sjVO.getZsno(), sjVO.getSjzl(), sjVO.getJbcn(), sjVO.getCutc(), sjVO.getLosc(), sjVO.getLosc2(), sjVO.getChan(), sjVO.getMode());
		zpng.setZpzl(zpzl);
		// 实际重量
		Integer sjzl = getSjzl(zpzl, sjVO.getSjzl(), sjVO.getMode());
		zpng.setSjzl(sjzl);

		// 计算重量
		Integer jszl = getJszl(zpzl, sjVO.getMode());
		zpng.setJszl(jszl);
		// 净重
		Integer jinz = getJinz(jszl, sjzl, sjVO.getMode());
		zpng.setJnzl(jinz);
		// 毛重量
		Integer mazl = getMazl(sjVO.getDhno(), jinz);
		zpng.setMazl(mazl);
		// 制品长
		zpng.setJscn(getZpc(sjVO.getJbcn(), sjVO.getCutc(), sjVO.getLosc(), sjVO.getLosc2(), sjVO.getMode()));
		zpng.setUpda(upda);
		// ETL实绩登录年月日
		// zpng.setEtsd(upda);

	}

	@Override
	public void saveSjLr(SjSaveVO sjVO) {
		if (sjVO == null) {
			return;
		}
		// 查询订货指示DB
		ZsdhTp zsdh = zsBO.getRef(sjVO.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "无效订货No.！");
		}
		DhuoTp dhuo = dhBO.getRef(SinoUtils.parseDhuoPk(dhnoAndLine));
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB不存在！");
		}
		// 查询原材原板
		YcaiTp ycai = ycaitpBO.getRef(sjVO.getZrjb());
		if (ycai == null || ycai.getJbno() == null) {
			throw new CocoException(-1, "原板原材不存在！");
		}
		if (YbStat.SJZL.stat.equals(ycai.getStat())
				|| YbStat.SJZZ.stat.equals(ycai.getStat())) {
			throw new CocoException(-1, "原板原材已经终了或中止了！");
		}
		Integer stat1 = sjVO.getStat1();
		Integer stat2 = sjVO.getStat2();
		// 状态
		String stat = YbStat.SJLR.stat;
		if (stat1 == null && stat2 == null) {
			stat = YbStat.SJLR.stat;
		}
		else if (stat1 != null && stat2 == null) {
			stat = YbStat.SJZL.stat;
		}
		else if (stat1 == null && stat2 != null) {
			stat = YbStat.SJZZ.stat;
		}
		String[] collNos = cmnBO.generateNo(sjVO.getZrjb(), YbStat.SJZZ.stat.equals(stat) ? NoGenType.cut
				: NoGenType.end);
		// 出端COIL No
		if (collNos != null && collNos.length > 0) {
			sjVO.setJbno(collNos[0]);
		}
		// 新原材原板NO
		String newJbno = null;
		if (collNos != null && collNos.length > 1) {
			newJbno = collNos[1];
		}
		// 出口包装No.
		Integer ckno = null;
		if (CodeNwx.wai.key.equals(dhuo.getNwai())) {
			ckno = cmnBO.generatePackageNo(zsdh.getDhno());
		}
		// 指示书NO
		String zsno = sjVO.getZsno();
		// 装入卷板No/PileNo
		String zrjb = sjVO.getZrjb();
		// 删除标记为1
		String scbj = null;
		// 卷板确定标志
		String qdbj = null;
		// 指示完了标记
		String zsbj = ZtConstants.DHZS_ZSBJ_WWC;
		// 装入中止标记
		String zlzz = null;
		// 更新时间
		Date upda = new Date();
		// 输入终了或中止
		if (!YbStat.SJLR.stat.equals(stat)) {
			ycai.setSjsj(upda);
			ycai.setQdbj(ZtConstants.YCAI_QDBJ_ZL);
			ycai.setScbj(EScbj.YSJZL.key);
			ycai.setStat(stat);
			// 更新订货指示DB
			if (!ycaitpBO.isExistedYcai(sjVO.getZsno(), sjVO.getZrjb())) {
				zsbj = ZtConstants.DHZS_ZSBJ_YWC;
			}
			zsdh.setZsbj(zsbj);
			zsdh.setZsny(upda);
			if (YbStat.SJZZ.stat.equals(stat)) {
				zsdh.setZlzz(ZtConstants.DHZS_ZHRU_YZ);
				saveYcaiTp(sjVO, ycai, newJbno, dhuo, zsdh, upda);
			}
			// 更新制品在库DB的实绩附着量
			Double sczm = sjVO.getSczm() == null ? 0.00
					: NumberUtils.format(sjVO.getSczm(), 2);
			Double scfm = sjVO.getScfm() == null ? 0.00
					: NumberUtils.format(sjVO.getScfm(), 2);
			dao.updateAllZp(sjVO.getZrjb(), sczm, scfm);
			// 原板制品商代码
			sjVO.setZzno(ycai.getZzsd());
			sjVO.setAbbr(zsdh.getAbbr());
			sjVO.setUser(dhuo.getUser());
			// 新增实绩日志(现品情报共通格式),新增实绩日志（指示情报共通格式）
			SjVO sj = sjBO.saveForZpZZ(sjVO, null);
			// 新增ETL（镀锡）实绩日志
			EtlsLp etlslp = new EtlsLp();
			etlslp.setXpfmt(sj.getXpfmt());
			etlslp.setZsfmt(sj.getZsfmt());
			etlslp.setZrzz(stat);
			etlslp.setCrea(upda);
			etlslp.setBan(sjVO.getBan());
			etlslp.setZu(sjVO.getZu());
			etlslp.setJscn(sjVO.getJbcn());
			dao.saveEtlsLp(etlslp);
			return;
		}
		// 解析成制品对象 ，并新增制品在库DB
		ZpngTp zpng = parseZp(sjVO, dhuo, zsdh, ycai, ckno, upda);
		ychBO.doScYch(zpng, null, dhuo, upda);
		zpBO.save(zpng);

		// 更新原材卷板记录
		ycai.setQdbj(qdbj);
		ycai.setScbj(scbj);
		ycai.setStat(stat);
		// 更新订货指示DB
		zsdh.setZsbj(zsbj);
		zsdh.setZlzz(zlzz);
		zsdh.setZsny(upda);
		// 更新订货DB
		if (ChanType.hg.key.equals(sjVO.getChan())
				|| ChanType.bl.key.equals(sjVO.getChan())) {
			if (sjVO.getCzdm() == null) {
				// 界面输入出货重量(制品重量)
				Double zpzl = 0.0;
				if (sjVO.getZpzl() != null) {
					zpzl = sjVO.getZpzl() * 0.001;
				}
				// 订货DB的ETL合格量(吨)
				Double etlh = 0.0;
				if (dhuo.getEtlh() != null) {
					etlh = dhuo.getEtlh();
				}
				dhuo.setEtlh(NumberUtils.format(etlh + zpzl, 3));
				// 品种代码
				String pzno = dhuo.getPzno();
				// 订货DB的SL指示量(吨)
				Double slzs = 0.0;
				if (dhuo.getSlzs() != null) {
					slzs = dhuo.getSlzs();
				}
				if (Code118.sheet.key.equals(pzno.substring(0, 1))
						|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
					dhuo.setSlzs(NumberUtils.format(slzs + zpzl, 3));
				}
			}
		}

		// 更新指示DB(装入卷板)
		ZszrTp zszr = dao.getZszrTpRef(new ZszrTpPk(zsno, zrjb));
		zszr.setStat(ZtConstants.ZSZR_STAT_YSJ);
		zszr.setUpda(upda);
		// 新增指示DB(出端卷板/PILE)
		saveZscdTp(sjVO, ycai, upda);
		// 新增指示对象DB
		saveZsdxTp(sjVO, zsdh, ycai, dhuo, upda);
		// 新增日志
		saveRiziLp(sjVO);
		// 新增实绩日志(现品情报共通格式),新增实绩日志（指示情报共通格式）
		SjVO sj = sjBO.saveForZp(sjVO.getJbno(), sjVO.getZsno(), ZtConstants.ZPNG_CLQ_ADD);
		// 新增ETL（镀锡）实绩日志
		saveEtlsLp(zpng, sj.getXpfmt(), sj.getZsfmt());
	}

	// /**
	// * <p>
	// * 生成出端COIL No
	// * </p>
	// *
	// * @param sjVO
	// * 界面实体
	// * @return COIL No
	// */
	// private String[] getCollNo(SjSaveVO sjVO) {
	//
	// String[] collNos = null;
	// // 输入中止
	// if (sjVO.getStat1() == null && sjVO.getStat2() == null) {
	// // 取新的原板原材卷号
	// collNos = cmnBO.generateNo(sjVO.getZrjb(), NoGenType.end);
	// }
	// if (sjVO.getStat1() != null && sjVO.getStat2() == null) {
	// // 取新的原板原材卷号
	// collNos = cmnBO.generateNo(sjVO.getZrjb(), NoGenType.end);
	// }
	// if (sjVO.getStat1() == null && sjVO.getStat2() != null) {
	// // 取新的原板原材卷号
	// collNos = cmnBO.generateNo(sjVO.getZrjb(), NoGenType.cut);
	// }
	// return collNos;
	// }

	/**
	 * <p>
	 * 新增ETL（镀锡）实绩日志
	 * </p>
	 * 
	 * @param zpng
	 *            制品实体
	 * @param xpfmt
	 *            现品情报共通ID
	 * @param zsfmt
	 *            指示情报ID
	 */
	private void saveEtlsLp(ZpngTp zpng, Xpfmt xpfmt, Zsfmt zsfmt) {
		EtlsLp etlslp = new EtlsLp();
		// 边波纹DP-高度BDRG_
		etlslp.setBdrg(zpng.getBdrg());
		// 边波纹DP-间隔BDRJ_
		etlslp.setBdrj(zpng.getBdrj());
		// 边波纹OP-高度BOPG_
		etlslp.setBopg(zpng.getBopg());
		// 边波纹OP-间隔BOPJ_
		etlslp.setBopj(zpng.getBopj());
		// 中伸-高度zbog_
		etlslp.setZbog(zpng.getZbog());
		// 中伸-间隔zboj_
		etlslp.setZboj(zpng.getZboj());
		// 板厚有无不良BHBL_
		etlslp.setBhbl(zpng.getBhbl());
		// 作成时间CREA_
		etlslp.setCrea(zpng.getUpda());
		// CUT长CUTC_
		etlslp.setCutc(zpng.getCutc());
		// 记录识别FLAG_
		etlslp.setFlag(zpng.getFlag());
		// // 横向挠度-符号HNDF_
		// etlslp.setHndf(zpng.getHndf());
		// 横向挠度-值HNDZ_
		etlslp.setHndz(zpng.getHndz());
		// 检定员JDYN_
		etlslp.setJdyn(zpng.getJdyn());
		// 制品长JSCN_
		etlslp.setJscn(zpng.getJscn());
		// 卷上TRNoJSNO_
		etlslp.setJsno(zpng.getJsno());
		// 计数员JSYN_
		etlslp.setJsyn(zpng.getJsyn());
		// LOSS长LOSC_
		etlslp.setLosc(zpng.getLosc());
		// LOSS长缺陷LOSQ_
		etlslp.setLosq(zpng.getLosq());
		// LOSS长LOSC_
		etlslp.setLosc2(zpng.getLosc2());
		// LOSS长缺陷LOSQ_
		etlslp.setLosq2(zpng.getLosq2());
		// P-H个数PHGS_
		etlslp.setPhgs(zpng.getPhgs());
		// 更新程序名PROG_
		etlslp.setProg(zpng.getProg());
		// 溶接个数RJGS_
		etlslp.setRjgs(zpng.getRjet());
		// 状态STAT_
		etlslp.setStat(zpng.getStat());
		// 更新时间UPDA_
		etlslp.setUpda(zpng.getUpda());
		// 中波纹等级ZBWD_
		etlslp.setZbwd(zpng.getZbwd());
		// // 纵向挠度-符号ZNDF_
		// etlslp.setZndf(zpng.getZndf());
		// 纵向挠度-值ZNDZ_
		etlslp.setZndz(zpng.getZndz());
		// 班
		etlslp.setBan(zpng.getBan());
		// 组
		etlslp.setZu(zpng.getZu());
		// 现品共通情报-FK
		etlslp.setXpfmt(xpfmt);
		// 指示情报-FK
		etlslp.setZsfmt(zsfmt);

		dao.saveEtlsLp(etlslp);
	}

	/**
	 * <p>
	 * ETL实绩录入操作时，对原板做中止操作。
	 * </p>
	 * 
	 * @param sjVO
	 *            ETL实绩录入实体类
	 * @param $ycai
	 *            ETL镀锡原板
	 * @param jbno
	 *            中止操作生成的新原板卷的卷号
	 * @param dhuoTp
	 *            订货合同
	 * @param zsdhTp
	 *            ETL指示书
	 * @param date
	 *            中止时间
	 */
	private void saveYcaiTp(SjSaveVO sjVO, YcaiTp $ycai, String jbno,
			DhuoTp dhuoTp, ZsdhTp zsdhTp, Date date) {
		//
		YcaiTp ycai = new YcaiTp();
		ReflectUtils.copy(ycai, $ycai, false);
		// 内径代码
		String njnoCode = ycai.getNjno();
		if (njnoCode == null) {
			throw new CocoException(-1, "原板原材内径代码为空！");
		}
		// 内径值
		Double njnoValue = 0.0;
		// 取内径码表
		CodeItem codeItem = codeBO.getCodeItem(CmnConstants.CODE_020, njnoCode);
		if (codeItem != null) {
			njnoValue = Double.valueOf(codeItem.getValue());
		}
		// 装入钢卷宽
		Double kuan = ycai.getXpkn();
		// 钢卷截面面积
		Double area = SinoUtils.calculate(njnoValue, sjVO.getJhou());
		// 余材量.钢卷重量=钢卷截面面积*钢卷宽*7.85
		Double ycl = SinoUtils.calculate(area, kuan);
		Integer $ycl = NumberUtils.formatDouToInt(ycl, 0, BigDecimal.ROUND_DOWN);
		// 更新原材的装入量和卷长
		if ($ycai.getZrzl() < $ycl) {
			throw new CocoException(-1, "中止重量大于装入重量！");
		}
		Integer zrzl = $ycai.getZrzl() - $ycl;
		$ycai.setZrzl(zrzl);
		// 卷板长=钢卷重量/m单重
		Integer jbcn = SinoUtils.calculate(ycl, kuan, sjVO.getJhou(), zsdhTp.getMdan());
		// 新的原板原材卷号
		ycai.setJbno(jbno);
		// 原板原材卷板长
		ycai.setJbcn(jbcn);
		// 计算重量JSZL_
		// Integer jszl = NumberUtils.formatDouToInt(ycl, 0,
		// BigDecimal.ROUND_DOWN);
		ycai.setJszl($ycl);
		// 实际重量
		// Integer sjzl = NumberUtils.formatDouToInt(ycl, 0,
		// BigDecimal.ROUND_DOWN);
		ycai.setSjzl($ycl);
		// 净重
		// Integer jnzl = 0;
		// jnzl = SinoUtils.fetchJnzl(dhuo.getJhnr(), ycai.getSjzl(),
		// ycai.getJszl());
		ycai.setJnzl($ycl);
		// 毛重
		ycai.setMazl($ycl);
		// 制品重量
		ycai.setZpzl($ycl);
		// 制品装入重量
		ycai.setZrzl($ycl);
		// 用户代码
		ycai.setUser(null);
		// 用户略称
		ycai.setAbbr(null);
		// 分配NO
		ycai.setFpno(null);
		// 指示书NO
		ycai.setZsno(null);
		// 分配/余材标记
		ycai.setFpyc(EFpyc.KEY_YC);
		// ETL流水线代码（实绩）
		ycai.setElin(null);
		// 订货合同号
		ycai.setDhno(null);
		// 进度标记.ETL实绩
		ycai.setJdes(null);
		// 进度标记.ETL指示
		ycai.setJdez(null);
		// 确定标记0
		ycai.setQdbj(ZtConstants.YCAI_QDBJ_WL);
		// 删除标记
		ycai.setScbj(EScbj.CS.key);
		// 状态
		ycai.setStat(YbStat.YRK.stat);
		// 堆场
		ycai.setDuic(DC.B.name);
		// 库位
		ycai.setKw(DC.B.name);
		// 创建时间
		ycai.setCrea(date);
		// 修改时间
		ycai.setUpda(null);
		// 实际时间
		ycai.setSjsj(null);
		// 改变界面的卷板号为分割号
		sjVO.setJbno(jbno);
		// 改变界面的卷板长
		sjVO.setJbcn(jbcn);
		// 改变界面的计算重量
		sjVO.setJszl($ycl);
		// 改变界面的实际重量
		sjVO.setSjzl($ycl);
		// 改变界面的制品重量
		sjVO.setZpzl($ycl);
		// 新增一条原材记录
		ycaitpBO.saveEntity(ycai);

		// 更新订货DB的分配量
		Double fpln = dhuoTp.getFpln();
		dhuoTp.setFpln(NumberUtils.format(fpln - ycl * 0.001, 3));
		// 新增分配操作记录
		ZkfpCzjl czjl = new ZkfpCzjl();
		czjl.setJbno(ycai.getJbno());
		// 旧的订货NO.
		String dhno = ycai.getDhno();
		// 旧的分配NO
		String fpno = ycai.getFpno();
		czjl.setDhno(dhno);
		czjl.setFpno(fpno);
		czjl.setXpzk(EXpzk.SC_KEY);
		czjl.setSfpp(null);
		czjl.setCzlx(Czlx.key6.key);
		czjl.setDdno(dhuoTp.getDdno());
		czjl.setDdnm(null);
		czjl.setCrea(date);
		czjlBO.save(czjl);
	}

	//
	// /**
	// * <p>
	// * 批量更新制品库的附着量
	// * </p>
	// *
	// * @param sjVO
	// * 界面实体
	// */
	// private void updateAllZp(SjSaveVO sjVO) {
	// dao.updateAllZp(sjVO.getZrjb(), sjVO.getSczm(), sjVO.getScfm());
	// }

	/**
	 * <p>
	 * 新增日志
	 * </p>
	 * 
	 * @param sjVO
	 *            界面提交实体
	 */
	private void saveRiziLp(SjSaveVO sjVO) {
		RiziLp rl = new RiziLp();
		// 出端卷板No/PileNo
		rl.setCdjb(sjVO.getJbno());
		// 行号
		rl.setLine(sjVO.getJbno().substring(sjVO.getJbno().length() - 2));
		// 日志种类
		rl.setRzzl(EKpType.ETL.key);
		// 装入卷板No
		rl.setZrjb(sjVO.getZrjb());
		// 作成时间
		rl.setCrea(new Date());
		dao.saveRiziLp(rl);

	}

	/**
	 * <p>
	 * 新增指示对象DB
	 * </p>
	 * 
	 * @param sjVO
	 *            界面提交实体
	 * @param zsdh
	 *            订货指示DB实体
	 * @param ycai
	 *            原板原材实体
	 */
	private void saveZsdxTp(SjSaveVO sjVO, ZsdhTp zsdh, YcaiTp ycai,
			DhuoTp dhuo, Date date) {
		// 品种
		String pzno = dhuo.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return;
		}
		String $pzno = pzno.substring(0, 1);
		String chan = sjVO.getChan();
		if (!ChanType.hg.key.equals(chan)) {
			return;
		}
		if (Code118.sheet.key.equals($pzno) || Code118.scroll.key.equals($pzno)) {
			ZsdxTp zsdx = new ZsdxTp();
			// 卷板No/PileNo
			zsdx.setJbno(sjVO.getJbno());
			// 分配No
			zsdx.setFpno(ZtConstants.ZSDX_FP_NO);
			// 超量分配标记
			zsdx.setClfp(zsdh.getClfp());
			// 取指示量
			zsdx.setCqzs(zsdh.getCqzs());
			// 作成时间
			zsdx.setCrea(date);
			// 订货No.行号
			zsdx.setDhno(zsdh.getDhno());
			// 剪边宽变更标记
			zsdx.setJbkb(zsdh.getJbbj());
			// 强制标记
			zsdx.setQzbj(zsdh.getQzbj());
			// 状态
			zsdx.setStat(ZtConstants.ZSDX_STAT_WZS);
			// 余材状况
			zsdx.setYczk(EXpzk.ZJP_KEY);
			// 制品重量
			zsdx.setZpzl(sjVO.getZpzl());
			// 再选别标记
			zsdx.setZxbb(zsdh.getZxbb());
			// 原板制造商No
			zsdx.setZzno(ycai.getZzsd());
			dao.saveZsdxTp(zsdx);
		}
	}

	/**
	 * <p>
	 * 新增指示DB(出端卷板/PILE)
	 * </p>
	 * 
	 * @param sjVO
	 *            界面提交实体
	 * @param ycai
	 *            原板原材实体
	 */
	private void saveZscdTp(SjSaveVO sjVO, YcaiTp ycai, Date date) {
		ZscdTp zscd = new ZscdTp();
		// 出端卷板No/PileNo
		zscd.setCdjb(sjVO.getJbno());
		// 指示书No
		zscd.setZsno(sjVO.getZsno());
		// 作成时间
		zscd.setCrea(date);
		// 实绩流水线代码
		zscd.setLine(sjVO.getElin());
		// 装入中止
		if (sjVO.getStat1() == null && sjVO.getStat2() != null) {
			// 装入中止卷板标记
			zscd.setZrzz(sjVO.getStat2().toString());
		}
		// 原板制造商No
		zscd.setZzno(ycai.getZzsd());
		saveZscdTp(zscd);
	}

	@Override
	public void saveZscdTp(ZscdTp entity) {
		dao.saveZscdTp(entity);
	}

	/**
	 * <p>
	 * 新增制品在库
	 * </p>
	 * 
	 * @param sjVO
	 *            界面提交实体
	 * @param dhuo
	 *            订货DB实体
	 * @param zsdh
	 *            订货指示DB实体
	 * @param ycai
	 *            原材原板实体
	 * @param ckno
	 *            出口包装号
	 */
	private ZpngTp parseZp(SjSaveVO sjVO, DhuoTp dhuo, ZsdhTp zsdh,
			YcaiTp ycai, Integer ckno, Date upda) {
		ZpngTp zpng = new ZpngTp();
		// 捆包形式
		zpng.setKbao(zsdh.getKbao());
		// 垫木方向
		zpng.setDmfx(zsdh.getDmfx());
		// 是否是废品
		zpng.setIsfp(sjVO.getIsfp());

		// 卷板No/PlieNo= 出端COIL No
		zpng.setJbno(sjVO.getJbno());
		// 用户略称
		zpng.setAbbr(zsdh.getAbbr());
		// 用户代码
		zpng.setUser(dhuo.getUser());
		// m单重
		zpng.setMdan(zsdh.getMdan());
		// 薄板单重
		zpng.setBdan(zsdh.getBdan());
		// 板厚不良
		zpng.setBhbl(sjVO.getBhbl());
		// 边波纹DR.高度

		zpng.setBdrg(sjVO.getBdrg());

		// 边波纹DR.间隔
		zpng.setBdrj(sjVO.getBdrj());
		// BL-Date(装船日)
		zpng.setBlny(ycai.getBlny());
		// 边波纹OP.高度

		zpng.setBopg(sjVO.getBopg());

		// 边波纹OP.间隔
		zpng.setBopj(sjVO.getBopj());
		// 中波纹高度

		zpng.setZbog(sjVO.getZbog());

		// 中波纹间隔
		zpng.setZboj(sjVO.getZboj());
		// 包装材料重量（Kg）
		zpng.setBzcl(zsdh.getBzcl());
		// 垫木重量（Kg）
		zpng.setDmzl(zsdh.getDmzl());
		// 重量计算补正系数
		zpng.setBzxs(zsdh.getBzxs());
		// 包装张数
		zpng.setBzzs(zsdh.getBzzs());
		// 制品尺寸.长
		zpng.setCang(zsdh.getCang());
		// 采购单价
		zpng.setCgdj(ycai.getCgdj());
		// 产量
		zpng.setChan(sjVO.getChan());
		// 差厚镀锡标记
		zpng.setChdx(dhuo.getChdx());

		// // 尺寸允许范围.长mm上限.符号
		// zpng.setCsfu(zsdh.getCsfu());
		// 尺寸允许范围.长mm上限.值
		zpng.setCszi(zsdh.getCszi());

		// // 尺寸允许范围.长mm下限.符号
		// zpng.setCxfu(zsdh.getCxfu());
		// 尺寸允许范围.长mm下限.值
		zpng.setCxzi(zsdh.getCxzi());
		// 处置代码
		zpng.setCzdm(sjVO.getCzdm());
		// 等级
		zpng.setDeng(sjVO.getDeng());
		// 订货No.行号
		zpng.setDhno(zsdh.getDhno());

		// 表面精加工符号
		zpng.setFace(zsdh.getFace());
		// 附着量.单位
		zpng.setFudw(zsdh.getFudw());
		// 附着量.反面
		zpng.setFufm(dhuo.getFufm());
		// 附着量.正面
		zpng.setFuzm(dhuo.getFuzm());
		// 实绩附着量.正面
		zpng.setSczm(sjVO.getSczm());
		// 实绩附着量.反面
		zpng.setScfm(sjVO.getScfm());
		// 规格代码
		zpng.setGgno(zsdh.getGgno());
		// 钢种类型
		zpng.setGzlx(zsdh.getGzlx());
		// 换算尺寸.长
		zpng.setHngc(zsdh.getHngc());
		// 换算尺寸.厚
		zpng.setHngh(zsdh.getHngh());
		// 换算尺寸.宽
		zpng.setHngk(zsdh.getHngk());
		// 制品尺寸.厚
		zpng.setHouu(zsdh.getHouu());
		// // 尺寸允许范围.厚%上限.符号
		// zpng.setHsfu(zsdh.getHsfu());
		// 尺寸允许范围.厚%上限.值
		zpng.setHszi(zsdh.getHszi());
		// 化学处理方法
		zpng.setHxcl(zsdh.getHxcl());
		// // 尺寸允许范围.厚%下限.符号
		// zpng.setHxfu(zsdh.getHxfu());
		// 尺寸允许范围.厚%下限.值
		zpng.setHxzi(zsdh.getHxzi());
		// 卷取长
		zpng.setJbcn(sjVO.getJbcn());
		// 卷上TRNo
		zpng.setJsno(sjVO.getJsno());

		// 锯齿形式
		zpng.setJcxs(zsdh.getJczl());
		// 进度标记.ETL实绩
		zpng.setJdes(ZtConstants.ZPNG_JD_SLSJ_YSJ);
		// 进度标记.ETL指示
		zpng.setJdez(ZtConstants.ZPNG_JD_SLZS_YZS);
		// 检定员
		zpng.setJdyn(sjVO.getJdyn());
		// 交货重量内容
		zpng.setJhnr(dhuo.getJhnr());
		// 直角度
		zpng.setJiao(dhuo.getJiao());

		// 计数员
		zpng.setJsyn(sjVO.getJsyn());

		// 捆包指定重量.上限
		zpng.setKbzs(zsdh.getKbzs());
		// 捆包指定重量.下限
		zpng.setKbzx(zsdh.getKbzx());
		// 附页KpNo1.
		zpng.setKpn1(zsdh.getKpn1());
		// 附页KpNo2.
		zpng.setKpn2(zsdh.getKpn2());
		// 附页KpNo3.
		zpng.setKpn3(zsdh.getKpn3());
		// 附页KpNo4.
		zpng.setKpn4(zsdh.getKpn4());
		// 业联
		zpng.setYlno(ycai.getYlno());
		// // 尺寸允许范围.宽mm上限.符号
		// zpng.setKsfu(zsdh.getKsfu());
		// 尺寸允许范围.宽mm上限.值
		zpng.setKszi(zsdh.getKszi());
		// 制品尺寸.宽
		zpng.setKuan(zsdh.getKuan());
		// // 尺寸允许范围.宽mm下限.符号
		// zpng.setKxfu(zsdh.getKxfu());
		// 尺寸允许范围.宽mm下限.值
		zpng.setKxzi(zsdh.getKxzi());
		Integer losc = sjVO.getLosc();
		Integer losc2 = sjVO.getLosc2();
		// Loss长
		zpng.setLosc(losc);
		zpng.setLosc2(losc2);
		if ((losc != null && losc > 0) || (losc2 != null && losc2 > 0)) {
			zpng.setLody(ZtConstants.DHZS_ZSFX_WDY);
		}
		// Loss长缺陷
		zpng.setLosq(sjVO.getLosq());
		zpng.setLosq2(sjVO.getLosq2());
		// Cut长
		zpng.setCutc(sjVO.getCutc());

		// // 尺寸允许范围.厚mm上限.符号
		// zpng.setMsfu(zsdh.getMsfu());
		// 尺寸允许范围.厚mm上限.值
		zpng.setMszi(zsdh.getMszi());
		// // 尺寸允许范围.厚mm下限.符号
		// zpng.setMxfu(zsdh.getMxfu());
		// 尺寸允许范围.厚mm下限.值
		zpng.setMxzi(zsdh.getMxzi());
		// 内径保护筒标记
		zpng.setNjbh(zsdh.getNjbh());
		// 内径代码
		zpng.setNjno(zsdh.getNjno());
		// 内外销
		zpng.setNwai(dhuo.getNwai());
		// P.H个数
		zpng.setPhgs(sjVO.getPhgs());
		// K-Plate表示
		zpng.setPlat(dhuo.getPlat());
		// 保护标志
		zpng.setProt(zsdh.getProt());
		// 品种代码
		zpng.setPzno(zsdh.getPzno());
		// 确定标记
		zpng.setQdbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 缺陷代码
		zpng.setQqdm(sjVO.getQqdm());
		// 溶接个数.ETL
		zpng.setRjet(sjVO.getRjet());
		// 溶接个数.冷延
		zpng.setRjly(ycai.getRjly());
		// 溶接个数.酸洗
		zpng.setRjsx(ycai.getRjsx());
		// 删除标记
		zpng.setScbj(EScbj.CS.key);
		// 船名
		zpng.setShip(ycai.getShip());

		// 实绩品种等级
		zpng.setSpdj(cmnBO.getSjpzdj(ChanType.get(sjVO.getChan()), sjVO.getDeng()));
		// 商社代码
		zpng.setSsno(dhuo.getSsno());
		// 状态
		zpng.setStat(ZpStat.CS.stat);
		// 通货区分
		zpng.setThqf(dhuo.getThqf());
		// 现品尺寸.厚
		zpng.setXpho(ycai.getXpho());
		// 现品尺寸.宽
		zpng.setXpkn(ycai.getXpkn());
		// 现品尺寸.长
		zpng.setXpcn(NumberUtils.format(NumberUtils.parseDouble(ycai.getJbcn()), 2));
		// 销售W可(ETL)
		zpng.setXswk(zsdh.getXswf());
		// 原板规格略称
		zpng.setYblc(ycai.getYblc());
		// 原板采购No
		zpng.setYbno(ycai.getYbno());
		// 原板收货年月日
		zpng.setYsuo(ycai.getYsuo());
		// 涂油种类
		zpng.setYtyp(dhuo.getYtyp());
		// 运用规格
		zpng.setYuny(zsdh.getYuny());
		// 压延方向指定标记
		zpng.setYyan(zsdh.getYyan());
		// 再剪边标记
		zpng.setZjbb(zsdh.getJbbj());
		// 制造年月日
		zpng.setZzny(ycai.getZzny());
		// 制造商代码
		zpng.setZzsd(ycai.getZzsd());
		// 制造商卷板No
		zpng.setZzsj(ycai.getZzsj());
		// 是否紧急（捆包发货）
		zpng.setSfjj(ZtConstants.DHZS_SFJJ_BJJ);
		// 库位
		zpng.setKw(zpng.getDuic());
		// ETL流水线代码（实绩）
		zpng.setElin(sjVO.getElin());
		// 班
		zpng.setBan(sjVO.getBan());
		// 组
		zpng.setZu(sjVO.getZu());
		// 入侧端COIL/PILE
		zpng.setRczpno(sjVO.getZrjb());
		// 是否保税
		zpng.setSfbs(ycai.getSfbs());
		// 录入员
		zpng.setDdno(sjVO.getDdno());
		// 商品编号
		zpng.setSpbh(ycai.getSpbh());
		// 装入量
		zpng.setZrzl(sjVO.getZpzl());
		// 原材NO
		zpng.setYcno(sjVO.getZrjb());
		// 原板制造商No
		zpng.setZzno(ycai.getZzsd());
		// 堆场搬入年月日
		zpng.setDuib(upda);
		// 作成时间
		zpng.setCrea(upda);
		// 更新时间
		zpng.setUpda(upda);
		// ETL实绩登录年月日
		zpng.setEtsd(upda);

		// 品种代码
		String pzno = zsdh.getPzno();
		// 堆场
		if (pzno != null) {
			// 卷制品，外卖材
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				zpng.setDuic(DC.C.name);
				zpng.setKw(DC.C.name);
				// 现品状况
				zpng.setXpzk(EXpzk.JZP_KEY);

				// 出口包装No.
				zpng.setCkno(ckno);
				zpng.setZsno(zsdh.getZsno());
				// 捆包形式
				if (ChanType.hg.key.equals(zpng.getChan())
						|| ChanType.bl.key.equals(zpng.getChan())) {
					zpng.setKbao(zsdh.getKbao());
				}
				else {
					zpng.setKbao("S11");
				}
				// 是否紧急
				if (zsdh.getJinj() != null
						&& ZtConstants.DHZS_SFJJ_JJ.equals(zsdh.getJinj())) {
					zpng.setSfjj(KbConstants.ISJJ_YES);
				}
			}
			else {
				if (!ChanType.hg.key.equals(zpng.getChan())
						&& !ChanType.bl.key.equals(zpng.getChan())) {
					zpng.setDuic(DC.C.name);
					zpng.setKw(DC.C.name);
					// 现品状况
					zpng.setXpzk(EXpzk.JZP_KEY);
				}
				else {
					zpng.setDuic(DC.D.name);
					zpng.setKw(DC.D.name);
					// 现品状况
					zpng.setXpzk(EXpzk.ZJP_KEY);
				}
				// 分配No
				zpng.setFpno(ZtConstants.ZSDX_FP_NO);
				// 捆包形式
				if (!ChanType.hg.key.equals(sjVO.getChan())
						&& !ChanType.bl.key.equals(sjVO.getChan())) {
					zpng.setKbao("S11");
					zpng.setZsno(zsdh.getZsno());
				}

			}

		}

		// 计算得出

		// 制品重量
		Integer zpzl = getZpzl(sjVO.getZsno(), sjVO.getSjzl(), sjVO.getJbcn(), sjVO.getCutc(), sjVO.getLosc(), sjVO.getLosc2(), sjVO.getChan(), sjVO.getMode());
		zpng.setZpzl(zpzl);
		// 实际重量
		Integer sjzl = getSjzl(zpzl, sjVO.getSjzl(), sjVO.getMode());
		zpng.setSjzl(sjzl);

		// 计算重量
		Integer jszl = getJszl(zpzl, sjVO.getMode());
		zpng.setJszl(jszl);
		// 净重
		Integer jinz = getJinz(jszl, sjzl, sjVO.getMode());
		zpng.setJnzl(jinz);
		// 毛重量
		Integer mazl = getMazl(sjVO.getDhno(), jinz);
		zpng.setMazl(mazl);

		// 制品长
		zpng.setJscn(getZpc(sjVO.getJbcn(), sjVO.getCutc(), sjVO.getLosc(), sjVO.getLosc2(), sjVO.getMode()));
		// zpng.setZsno(zsdh.getZsno());
		// 硬度上限
		zpng.setYmax(dhuo.getYmax());
		// 硬度下限
		zpng.setYmin(dhuo.getYmin());
		// 硬度
		zpng.setYing(ycai.getYing());
		// 硬度
		zpng.setLlyd(ycai.getYing());
		return zpng;
	}

	@Override
	public void checkFu(DhuoTp dhuo, Double sczm, Double scfm) {
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB不存在！");
		}
		if (sczm != null && sczm != 0) {
			if (dhuo.getFuzx() != null && dhuo.getFuzx() > 0
					&& (sczm < dhuo.getFuzx() || sczm > dhuo.getFuzs())) {
				String msg = "附着量.正超过范围(" + dhuo.getFuzx().toString() + "-"
						+ dhuo.getFuzs().toString() + ")";
				throw new CocoException(-2, msg);
			}
		}
		if (scfm != null && scfm != 0) {
			if (dhuo.getFufs() != null && dhuo.getFufs() > 0
					&& (scfm < dhuo.getFufx() || scfm > dhuo.getFufs())) {
				String msg = "附着量.反超过范围(" + dhuo.getFufx().toString() + "-"
						+ dhuo.getFufs().toString() + ")";
				throw new CocoException(-3, msg);
			}
		}
	}

	@Override
	public void validate(SjSaveVO sjVO) {
		Boolean banbl = banBO.checkBan(sjVO.getBan());
		if (!banbl) {
			throw new CocoException(-23, "现在的时间不是这个班！");
		}
		// 检查产量代码
		if ("1".equals(sjVO.getIsfp())) {
			if (sjVO.getChan() != null && !sjVO.getChan().isEmpty()) {
				if (!ChanType.hg.key.equals(sjVO.getChan())
						&& !ChanType.bl.key.equals(sjVO.getChan())) {
					throw new CocoException(-11, "不是合格品要选择否！");
				}
			}

		}
		if ("0".equals(sjVO.getIsfp())) {
			if (sjVO.getChan() != null && !sjVO.getChan().isEmpty()) {
				if (ChanType.hg.key.equals(sjVO.getChan())
						|| ChanType.bl.key.equals(sjVO.getChan())) {
					throw new CocoException(-11, "不是合格品产量不能为1或9！");
				}
			}

		}
		// 查询订货指示DB
		ZsdhTp zsdh = zsBO.getRef(sjVO.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "订货指示DB不存在！");
		}
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "无效订货No.！");
		}
		DhuoTp dhuo = dhBO.getRef(SinoUtils.parseDhuoPk(dhnoAndLine));
		if (dhuo == null) {
			throw new CocoException(-1, "订货DB不存在！");
		}
		validate(sjVO, zsdh, dhuo);
	}

	/**
	 * <p>
	 * 检查界面输入。
	 * </p>
	 * 
	 * @param sjVO
	 *            界面输入实体
	 */
	private void validate(SjSaveVO sjVO, ZsdhTp zsdh, DhuoTp dhuo) {
		// 检查终了输入
		if (sjVO.getStat1() != null) {
			if (sjVO.getStat1() != 1) {
				throw new CocoException(-1, "终了输入不是1！");
			}
			if (sjVO.getSczm() == null) {
				throw new CocoException(-2, "附着量.正不能为空！");
			}
			if (sjVO.getScfm() == null) {
				throw new CocoException(-3, "附着量.反不能为空！");
			}
			// 检查附着量
			checkFu(dhuo, sjVO.getSczm(), sjVO.getScfm());
			// 检查合格品
			// 查询总装入量
			// 查询原材原板
			YcaiTp ycai = ycaitpBO.get(sjVO.getZrjb());
			if (ycai == null || ycai.getJbno() == null) {
				throw new CocoException(-1, "原板原材不存在！");
			}
			sjVO.setAllzrzl(ycai.getZrzl());
			// 查询总产出量(合格品)
			// List<ZpngTp> zps = zpBO.listZpzlByJbno(sjVO.getZrjb());
			// Integer allzpzl = 0;
			// for (ZpngTp zpz : zps) {
			// allzpzl = allzpzl + zpz.getZpzl();
			// }
			Integer allzpzl = zpBO.getCczl(sjVO.getZrjb());
			sjVO.setAllzpzl(allzpzl);
			Double hgzl = 0.0;
			if (sjVO.getAllzrzl() > 0) {
				hgzl = (allzpzl * 1.0) / sjVO.getAllzrzl() * 100;
			}
			if (hgzl < 98 || hgzl > 102) {
				String msg = "总装入量:" + sjVO.getAllzrzl().toString()
						+ ",总合格量不在范围（98%-102%）："
						+ NumberUtils.format(hgzl, 2).toString() + "%";
				throw new CocoException(-99, msg);

			}
			return;
		}
		// 检查中止输入
		if (sjVO.getStat2() != null) {
			if (sjVO.getStat2() != 1) {
				throw new CocoException(-4, "中止输入不是1！");
			}
			if (sjVO.getJhou() == null) {
				throw new CocoException(-5, "卷厚(mm)不能为空！");
			}
			if (sjVO.getSczm() == null) {
				throw new CocoException(-2, "附着量.正不能为空！");
			}
			if (sjVO.getScfm() == null) {
				throw new CocoException(-3, "附着量.反不能为空！");
			}
			// 检查附着量
			checkFu(dhuo, sjVO.getSczm(), sjVO.getScfm());
			return;
		}

		// 检查出端COIL No生成次数
		if (sjVO.getJbno() != null) {
			if (sjVO.getJbno().length() == 8) {
				if (ZtConstants.CODE_CHS9 == sjVO.getJbno().charAt(7)) {
					if (ZtConstants.CODE_CHS9 == sjVO.getJbno().charAt(6)) {
						if (sjVO.getStat1() == null) {
							throw new CocoException(-1, "终了输入不是1！");
						}
					}
					else {
						if (sjVO.getStat2() == null && sjVO.getStat1() == null) {
							throw new CocoException(-1, "终了或中止输入不是1！");
						}
					}

				}
			}
		}
		if (ZtConstants.DHZS_CAOZ_C.equals(sjVO.getMode())) {
			// 检查实称重量
			if (sjVO.getSjzl() == null) {
				throw new CocoException(-6, "实称重量不能为空！");
			}
		}
		if (ZtConstants.DHZS_CAOZ_S.equals(sjVO.getMode())) {
			// 检查卷取长
			if (sjVO.getJbcn() == null) {
				throw new CocoException(-6, "卷取长不能为空！");
			}
		}

		// 检查LOSS缺
		if (sjVO.getLosc() != null) {
			if (sjVO.getLosq() == null) {
				throw new CocoException(-9, "LOSS缺不能为空！");
			}
			CodeItem code = codeBO.getCodeItem(CmnConstants.CODE_QX, sjVO.getLosq());
			if (code == null) {
				throw new CocoException(-9, "LOSS缺陷在码表不存在！");
			}
		}
		// 检查缺陷代码
		if (sjVO.getQqdm() != null) {
			CodeItem code = codeBO.getCodeItem(CmnConstants.CODE_QX, sjVO.getQqdm());
			if (code == null) {
				throw new CocoException(-21, "缺陷在码表不存在！");
			}
		}
		// 检查产量代码
		if (sjVO.getChan() == null) {
			throw new CocoException(-11, "产量代码不能为空！");
		}
		// 检查产量代码
		if (sjVO.getChan() != null) {
			// 不合格品
			if ("0".equals(sjVO.getIsfp())) {
				if (ChanType.hg.key.equals(sjVO.getChan())
						|| ChanType.bl.key.equals(sjVO.getChan())) {
					throw new CocoException(-11, "不合品录入产量代码不能为1和9！");
				}
			}
			if (ChanType.get(sjVO.getChan()) == null) {
				throw new CocoException(-11, "产量代码输入错误！");
			}
		}
		// 检验产量与等级关系。
		cmnBO.checkChan(sjVO.getChan(), sjVO.getDeng(), zsdh.getFpdj());
		// 检查检定员和计数员
		if (sjVO.getJdyn() == null) {
			throw new CocoException(-15, "检定员不能为空！");
		}
		if (sjVO.getJsyn() == null) {
			throw new CocoException(-16, "计数员不能为空！");
		}
		// 品种
		String pzno = dhuo.getPzno();
		if (!pzno.isEmpty()) {
			// 品种编号第一位为2
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				if (sjVO.getJdyn() == null) {
					throw new CocoException(-15, "检定员不能为空！");
				}
				if (sjVO.getJsyn() == null) {
					throw new CocoException(-16, "计数员不能为空！");
				}
			}
		}
		// 检查出货重量
		if (!pzno.isEmpty()) {
			// 品种编号第一位为2
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				// 产量输入为1
				if (ChanType.hg.key.equals(sjVO.getChan())) {
					// 出货重量
					Integer zpzl = 0;
					if (sjVO.getZpzl() != null) {
						zpzl = sjVO.getZpzl();
					}
					// 捆包指定重量.上限
					Double kbzs = 0.0;
					if (dhuo.getKbzs() != null) {
						kbzs = dhuo.getKbzs();
					}
					// 捆包指定重量.下限
					Double kbzx = 0.0;
					if (dhuo.getKbzx() != null) {
						kbzx = dhuo.getKbzx();
					}
					// 捆包指定重量和出货重量比较，吨数比较
					if ((zpzl * 0.001 < kbzx) || (zpzl * 0.001 > kbzs)) {
						String msg = "出货重量不在指定的捆包重量范围(" + kbzx + "~" + kbzs
								+ ")内！";
						throw new CocoException(-14, msg);
					}
				}
			}
		}
		// 检查溶接个数
		if (!pzno.isEmpty()) {
			// 品种编号第一位为2
			if (Code118.coil.key.equals(pzno.substring(0, 1))) {
				// 产量输入为1
				if (ChanType.hg.key.equals(sjVO.getChan())) {
					if (dhuo.getRjie().equals(ZtConstants.DHUO_RJIE_B)) {
						if (sjVO.getRjet() != null) {
							throw new CocoException(-20, "溶接个数必须为空！");
						}
					}
				}
			}
		}
		// 卷取TRNo.
		if (sjVO.getJsno() == null) {
			throw new CocoException(-22, "卷取TRNo不能为空！");
		}
		if (!"1".equals(sjVO.getJsno()) && !"2".equals(sjVO.getJsno())) {
			throw new CocoException(-22, "卷取TRNo不是1或2！");
		}
	}

	@Override
	public void query(QEntity<ZpngTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void querySL(QEntity<ZpSLVO> qentity) {
		dao.querySL(qentity);
	}

	@Override
	public SjSaveVO getZpView(String jbno) {
		SjSaveVO sjvo = new SjSaveVO();
		// 查询制品
		ZpngTp zp = zpBO.getZp(jbno);
		if (zp != null) {
			sjvo.setBan(zp.getBan());
			sjvo.setZu(zp.getZu());
			sjvo.setJbno(zp.getJbno());
			sjvo.setJbcn(zp.getJbcn());
			sjvo.setHouu(zp.getHouu());
			sjvo.setKuan(zp.getKuan());
			sjvo.setSjzl(zp.getSjzl());
			sjvo.setCutc(zp.getCutc());
			sjvo.setLosc(zp.getLosc());
			sjvo.setLosq(zp.getLosq());
			sjvo.setChan(zp.getChan());
			sjvo.setDeng(zp.getDeng());
			sjvo.setZpzl(zp.getZpzl());
			sjvo.setJdyn(zp.getJdyn());
			sjvo.setJsyn(zp.getJsyn());
			sjvo.setQqdm(zp.getQqdm());
			sjvo.setPhgs(zp.getPhgs());
			sjvo.setRjet(zp.getRjet());
			sjvo.setBhbl(zp.getBhbl());
			sjvo.setJsno(zp.getJsno());
			sjvo.setBopg(zp.getBopg());
			sjvo.setBopj(zp.getBopj());
			sjvo.setBdrg(zp.getBdrg());
			sjvo.setBdrj(zp.getBdrj());
			sjvo.setZbog(zp.getZbog());
			sjvo.setZboj(zp.getZboj());
		}
		// 查询指示NO
		YcaiTp yc = ycaitpBO.get(zp.getRczpno());
		if (yc != null) {
			sjvo.setZsno(yc.getZsno());
		}
		return sjvo;
	}

	@Override
	public void delete(String jbno) {
		// 查询制品
		ZpngTp zpng = zpBO.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		YcaiTp ycai = ycaitpBO.getRef(zpng.getRczpno());
		if (ycai == null) {
			throw new CocoException(-1, "原材原板不存在！");
		}
		ZsdhTp zsdh = zsBO.getRef(ycai.getZsno());
		if (zsdh == null) {
			throw new CocoException(-1, "指示书不存在！");
		}
		// 检验删除
		validateDelete(zpng);
		Date date = new Date();
		// 更新订货DB
		updateDh(zpng);
		// 删除指示对象DB
		deleteZsdxTp(zpng);
		// 更新原材卷板
		updateYcaiTp(ycai, zpng);
		// 更新订货指示DB
		updateZsdhTp(zsdh);
		// 更新指示DB(装入卷板)
		updateZszrTp(zpng.getRczpno(), ycai.getZsno(), date);
		// 删除指示DB(出端卷板/PILE)
		dao.deleteZscdTp(new ZscdTpPk(ycai.getZsno(), jbno));
		// 删除日志
		sjBO.delete(new RiziLpPk(EKpType.ETL.key, zpng.getRczpno(), jbno,
				jbno.substring(jbno.length() - 2)));
		// 新增实绩日志(现品情报共通格式),新增实绩日志（指示情报共通格式）
		SjVO sj = sjBO.saveForZp(jbno, ycai.getZsno(), ZtConstants.ZPNG_CLQ_DEL);
		// 新增ETL（镀锡）实绩日志
		saveEtlsLp(zpng, sj.getXpfmt(), sj.getZsfmt());
		// 删除制品在库DB
		zpBO.delete(jbno);
	}

	@Override
	public void deleteAll(List<String> jbnos) {
		for (String jbno : jbnos) {
			delete(jbno);
		}
	}

	private void validateDelete(ZpngTp zpng) {
		// 品种代码
		String pzno = zpng.getPzno();
		// 外贩材（品种代码第一位为2）
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			if (!DC.C.name.equals(zpng.getDuic())) {
				throw new CocoException(-1, "制品堆场不为C！");
			}
			// if (!EScbj.KEY_CS.equals(zpng.getScbj())) {
			// throw new CocoException(-1, "制品删除标记不为0！");
			// }
			Date nowDate = new Date();
			if ((nowDate.getTime() - zpng.getCrea().getTime()) > 86400000) {
				throw new CocoException(-1, "实绩登录已超过24小时，不能删除！");
			}
			if (zpng.getChno() != null) {
				throw new CocoException(-1, "出货指示No不为空！");
			}
			if (zpng.getZtbj() != null) {
				throw new CocoException(-1, "作业停止标记不为空！");
			}
		}
		// 剪切材(品种第一位为1、3)
		if (Code118.sheet.key.equals(pzno.substring(0, 1))
				|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
			if (!DC.D.name.equals(zpng.getDuic())) {
				throw new CocoException(-1, "制品堆场不为D！");
			}
			if (zpng.getZtbj() != null) {
				throw new CocoException(-1, "作业停止标记不为空！");
			}
			// if (!EScbj.KEY_CS.equals(zpng.getScbj())) {
			// throw new CocoException(-1, "制品删除标记不为0！");
			// }
			Date nowDate = new Date();
			if ((nowDate.getTime() - zpng.getCrea().getTime()) > 86400000) {
				throw new CocoException(-1, "实绩登录已超过24小时，不能删除！");
			}
			// if (zpng.getZsno() != null) {
			// throw new CocoException(-1, "指示书NO不为空！");
			// }
			if (zpng.getJdsz() != null) {
				if (ZtConstants.ZPNG_JD_SLZS_WZS.equals(zpng.getJdsz())) {
					throw new CocoException(-1, "进度标记.SL指示不为空或0！");
				}
			}
			if (ZtConstants.ZPNG_JD_SLZS_YZS.equals(zpng.getJdsz())) {
				throw new CocoException(-1, "SL指示中，不能删除！");
			}
			if (zpng.getChno() != null) {
				throw new CocoException(-1, "已经生成出货指示，不能删除！");
			}

		}
	}

	/**
	 * <p>
	 * 更新指示DB(装入卷板)
	 * </p>
	 * 
	 * @param rczpno
	 *            入侧端COIL/PILE
	 * @param zsno
	 *            指示书号
	 */
	private void updateZszrTp(String rczpno, String zsno, Date date) {
		// 查询指示DB(装入卷板)引用
		ZszrTp zszr = zsBO.getZszrTpRef(new ZszrTpPk(zsno, rczpno));
		if (zszr == null) {
			throw new CocoException(-1, "指示DB(装入卷板)不存在！");
		}
		zszr.setStat(ZtConstants.ZSZR_STAT_WSCH);
		zszr.setUpda(date);
	}

	/**
	 * <p>
	 * 更新订货指示DB
	 * </p>
	 * 
	 * @param zsno
	 *            指示书号
	 */
	private void updateZsdhTp(ZsdhTp zsdh) {
		if (ycaitpBO.isExistedYcai(zsdh.getZsno(), "")) {
			String zsbj = ZtConstants.DHZS_ZSBJ_WWC;
			zsdh.setZsbj(zsbj);
		}
	}

	/**
	 * <p>
	 * 更新原材卷板
	 * </p>
	 * 
	 * @param ycai
	 *            原材卷板实体
	 * @param zpng
	 *            制品实体
	 */
	private void updateYcaiTp(YcaiTp ycai, ZpngTp zpng) {
		// 原材生成的制品全部删除
		if (!zpBO.isZp(zpng.getJbno(), zpng.getRczpno())) {
			// 确定标记为0
			String qdbj = ZtConstants.YCAI_QDBJ_WL;
			// 删除标记为0
			String scbj = EScbj.CS.key;
			// 状态为2-已分配
			String stat = YbStat.YFP.stat;
			ycai.setStat(stat);
			ycai.setQdbj(qdbj);
			ycai.setScbj(scbj);

		}
	}

	/**
	 * <p>
	 * 删除指示对象DB
	 * </p>
	 * 
	 * @param zpng
	 *            制品实体
	 */
	private void deleteZsdxTp(ZpngTp zpng) {
		// 品种代码
		String pzno = zpng.getPzno();
		// 产量代码为1，9,制品卷为合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			// 品种第一位为1、3
			if (Code118.sheet.key.equals(pzno.substring(0, 1))
					|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
				zsBO.deleteZsdxTp(new ZsdxTpPk(ZtConstants.ZSDX_FP_NO,
						zpng.getJbno()));
			}
		}
	}

	/**
	 * <p>
	 * 更新订货DB
	 * </p>
	 * 
	 * @param zpng
	 *            制品实体
	 */
	private void updateDh(ZpngTp zpng) {
		// 查询订货DB可更新实体
		String dhnoAndLine = zpng.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			return;
		}
		DhuoTp dhuo = dhBO.getRef(SinoUtils.parseDhuoPk(dhnoAndLine));
		if (dhuo == null) {
			return;
		}
		// 原SL指示量(吨)
		Double slzs = 0.0;
		// 原ETL合格量(吨)
		Double etlh = 0.0;
		// 原制品重量
		Integer zpzl = 0;
		if (zpng.getZpzl() != null) {
			zpzl = zpng.getZpzl();
		}
		if (dhuo.getEtlh() != null) {
			etlh = dhuo.getEtlh();
		}
		if (dhuo.getSlzs() != null) {
			slzs = dhuo.getSlzs();
		}
		// 品种代码
		String pzno = zpng.getPzno();
		// 产量代码为1，9,制品卷为合格品
		if (ChanType.hg.key.equals(zpng.getChan())
				|| ChanType.bl.key.equals(zpng.getChan())) {
			etlh = etlh - zpzl * 0.001;
			dhuo.setEtlh(NumberUtils.format(etlh, 3));
			// 品种第一位为1、3
			if (Code118.sheet.key.equals(pzno.substring(0, 1))
					|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
				slzs = slzs - zpzl * 0.001;
				dhuo.setSlzs(NumberUtils.format(slzs, 3));
			}
		}

	}

	@Override
	public String getForJs(Serializable id) {
		List<PngzLp> plps;
		plps = dao.getPngzLp(id);
		if (plps == null || plps.size() == 0) {
			throw new CocoException(-1, "没有品质管理记录！");
		}
		return new Result(plps.get(0)).toJsObject();
	}

	@Override
	public void savePngzLp(PngzLp entity) {
		dao.savePngzLp(entity);
	}

	@Override
	public ZsViewVO getZs(String jbno, String type) {

		ZsViewVO zs = new ZsViewVO();
		if (ProductType.etl.name.equals(type)) {

			YcaiTp ycai = ycaitpBO.get(jbno);
			if (ycai == null) {
				throw new CocoException(-1, "原板原材不存在！");
			}
			String dhnoline = ycai.getDhno();
			if (dhnoline == null || dhnoline.isEmpty()) {
				throw new CocoException(-1, "订货DB的订货NO不存在！");
			}
			// 查询订货DB
			DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
			DhuoTp dhuo = dhBO.get(dhpk);
			if (dhuo == null) {
				throw new CocoException(-1, "订货DB不存在！");
			}
			// 增加生产备注
			zs.setBz(dhuo.getBz1() + " " + dhuo.getBz3());
			// 增加附业KEY
			if (EKpType.ETL.key.equals(dhuo.getKpn1Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn1Flag())) {
				zs.getKpns().add(dhuo.getKpn1());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn2Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn2Flag())) {
				zs.getKpns().add(dhuo.getKpn2());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn3Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn3Flag())) {
				zs.getKpns().add(dhuo.getKpn3());
			}
			if (EKpType.ETL.key.equals(dhuo.getKpn4Flag())
					|| EKpType.BOTH.key.equals(dhuo.getKpn4Flag())) {
				zs.getKpns().add(dhuo.getKpn4());
			}
			// 增加业联
			String ylnNo = ycai.getYlno();
			if (ylnNo != null) {
				String[] ylns1 = ylnNo.split(",");
				String[] ylns2;
				for (String yln : ylns1) {
					ylns2 = yln.split("-");
					if (ylns2.length > 1) {
						if (EKpType.ETL.key.equals(ylns2[0])
								|| EKpType.BOTH.key.equals(ylns2[0])) {
							zs.getYlns().add(ylns2[1]);
						}
					}
				}
			}
		}
		if (ProductType.sl.name.equals(type)) {
			ZpngTp zp = zpBO.getZp(jbno);
			if (zp != null) {
				String dhnoline = zp.getDhno();
				if (dhnoline == null || dhnoline.isEmpty()) {
					throw new CocoException(-1, "订货DB的订货NO不存在！");
				}
				// 查询订货DB
				DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoline);
				DhuoTp dhuo = dhBO.get(dhpk);
				if (dhuo == null) {
					throw new CocoException(-1, "订货DB不存在！");
				}
				// 增加生产备注
				zs.setBz(dhuo.getBz1() + " " + dhuo.getBz3());
				// 增加附业KEY
				if (EKpType.SL.key.equals(dhuo.getKpn1Flag())
						|| EKpType.BOTH.key.equals(dhuo.getKpn1Flag())) {
					zs.getKpns().add(dhuo.getKpn1());
				}
				if (EKpType.SL.key.equals(dhuo.getKpn2Flag())
						|| EKpType.BOTH.key.equals(dhuo.getKpn2Flag())) {
					zs.getKpns().add(dhuo.getKpn2());
				}
				if (EKpType.SL.key.equals(dhuo.getKpn3Flag())
						|| EKpType.BOTH.key.equals(dhuo.getKpn3Flag())) {
					zs.getKpns().add(dhuo.getKpn3());
				}
				if (EKpType.SL.key.equals(dhuo.getKpn4Flag())
						|| EKpType.BOTH.key.equals(dhuo.getKpn4Flag())) {
					zs.getKpns().add(dhuo.getKpn4());
				}
				// 增加业联
				String ylnNo = zp.getYlno();
				if (ylnNo != null) {
					String[] ylns1 = ylnNo.split(",");
					String[] ylns2;
					for (String yln : ylns1) {
						ylns2 = yln.split("-");
						if (ylns2.length > 1) {
							if (EKpType.ETL.key.equals(ylns2[0])
									|| EKpType.BOTH.key.equals(ylns2[0])) {
								zs.getYlns().add(ylns2[1]);
							}
						}
					}
				}
			}

		}

		return zs;
	}

	@Override
	public void fetchEtlsy(EtlSydjVO vo, OutputStream os) {
		EtlSydjDataVO etlsy = new EtlSydjDataVO();
		// 一个月的时间段
		Date begin = DateUtils.getFirstDayOfMonth(vo.getEtsd());
		Date end = DateUtils.getLastDayOfNextMonth(vo.getEtsd());
		String betweenStr = "b.Crea_ >='"
				+ DateUtils.format(begin, "yyyy-MM-dd")
				+ " 8:00:00' and b.Crea_<='"
				+ DateUtils.format(end, "yyyy-MM-dd") + " 8:00:00'";
		// 第1个sheet
		EtlSybmSheetVO sheet0 = new EtlSybmSheetVO();
		EtlSybmSheetVO.Row row0 = null;
		String sql = getBmSql(betweenStr);
		List<Map<String, Object>> datas = jdbc.queryForList(sql);
		for (Map<String, Object> map : datas) {
			row0 = new EtlSybmSheetVO.Row();
			row0.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
			row0.setFuzm(Double.valueOf(map.get("mbfuzm1_").toString()));
			row0.setSjsczm(Double.valueOf(map.get("sjfuzm1_").toString()));
			row0.setJcha1(Double.valueOf(map.get("cha1_").toString()));
			row0.setMbfuzm(Double.valueOf(map.get("mbfuzm2_").toString()));
			row0.setSjfuzm(Double.valueOf(map.get("sjfuzm2_").toString()));
			row0.setJcha2(Double.valueOf(map.get("cha2_").toString()));
			row0.setSjyl(Double.valueOf(map.get("sjyl_").toString()));
			row0.setMbyl(Double.valueOf(map.get("mbyl_").toString()));
			sheet0.add(row0);
		}
		etlsy.setBm(sheet0);
		// 第2个Sheet
		EtlSylmSheetVO sheet1 = new EtlSylmSheetVO();
		EtlSylmSheetVO.Row row1 = null;
		sql = getLmSql(betweenStr);
		datas = jdbc.queryForList(sql);
		for (Map<String, Object> map : datas) {
			row1 = new EtlSylmSheetVO.Row();
			row1.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
			row1.setFufm(Double.valueOf(map.get("mbfufm1_").toString()));
			row1.setSjscfm(Double.valueOf(map.get("sjfufm1_").toString()));
			row1.setJcha1(Double.valueOf(map.get("cha1_").toString()));
			row1.setMbfufm(Double.valueOf(map.get("mbfufm2_").toString()));
			row1.setSjfufm(Double.valueOf(map.get("sjfufm2_").toString()));
			row1.setJcha2(Double.valueOf(map.get("cha2_").toString()));
			row1.setSjyl(Double.valueOf(map.get("sjyl_").toString()));
			row1.setMbyl(Double.valueOf(map.get("mbyl_").toString()));
			sheet1.add(row1);

		}
		etlsy.setLm(sheet1);
		// 第3个Sheet
		EtlSyAllSheetVO sheet2 = new EtlSyAllSheetVO();
		EtlSyAllSheetVO.Row row2 = null;
		sql = getAllSql(betweenStr);
		datas = jdbc.queryForList(sql);
		for (Map<String, Object> map : datas) {
			row2 = new EtlSyAllSheetVO.Row();
			row2.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
			row2.setMbfu(Double.valueOf(map.get("mb_").toString()));
			row2.setSjfu(Double.valueOf(map.get("sj_").toString()));
			row2.setJcha(Double.valueOf(map.get("cha_").toString()));
			sheet2.add(row2);
		}
		etlsy.setAll(sheet2);
		// 第4个Sheet
		EtlSyBanSheetVO sheet3 = new EtlSyBanSheetVO();
		EtlSyBanSheetVO.Row row3 = null;
		sql = getBanSql(betweenStr);
		datas = jdbc.queryForList(sql);
		List<Map<String, Object>> qudatas = null;
		for (Map<String, Object> mapban : datas) {
			// 表面统计数据
			row3 = new EtlSyBanSheetVO.Row();
			sql = getBmSql(betweenStr, mapban.get("zu_").toString());
			qudatas = jdbc.queryForList(sql);
			for (Map<String, Object> map : qudatas) {
				row0 = new EtlSybmSheetVO.Row();
				row0.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
				row0.setFuzm(Double.valueOf(map.get("mbfuzm1_").toString()));
				row0.setSjsczm(Double.valueOf(map.get("sjfuzm1_").toString()));
				row0.setJcha1(Double.valueOf(map.get("cha1_").toString()));
				row0.setMbfuzm(Double.valueOf(map.get("mbfuzm2_").toString()));
				row0.setSjfuzm(Double.valueOf(map.get("sjfuzm2_").toString()));
				row0.setJcha2(Double.valueOf(map.get("cha2_").toString()));
				// row0.setSjyl(Double.valueOf(map.get("sjyl").toString()));
				// row0.setMbyl(Double.valueOf(map.get("mbyl").toString()));
				row3.getBms().add(row0);

			}
			// 里面统计数据
			sql = getLmSql(betweenStr, mapban.get("zu_").toString());
			qudatas = jdbc.queryForList(sql);
			for (Map<String, Object> map : qudatas) {
				row1 = new EtlSylmSheetVO.Row();
				row1.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
				row1.setFufm(Double.valueOf(map.get("mbfufm1_").toString()));
				row1.setSjscfm(Double.valueOf(map.get("sjfufm1_").toString()));
				row1.setJcha1(Double.valueOf(map.get("cha1_").toString()));
				row1.setMbfufm(Double.valueOf(map.get("mbfufm2_").toString()));
				row1.setSjfufm(Double.valueOf(map.get("sjfufm2_").toString()));
				row1.setJcha2(Double.valueOf(map.get("cha2_").toString()));
				// row1.setSjyl(Double.valueOf(map.get("sjyl").toString()));
				// row1.setMbyl(Double.valueOf(map.get("mbyl").toString()));
				row3.getLms().add(row1);

			}
			// 总统计数据
			sql = getAllSql(betweenStr, mapban.get("zu_").toString());
			qudatas = jdbc.queryForList(sql);
			for (Map<String, Object> map : qudatas) {
				row2 = new EtlSyAllSheetVO.Row();
				row2.setZrzlAll(Double.valueOf(map.get("zrzlAll_").toString()));
				row2.setMbfu(Double.valueOf(map.get("mb_").toString()));
				row2.setSjfu(Double.valueOf(map.get("sj_").toString()));
				row2.setJcha(Double.valueOf(map.get("cha_").toString()));
				row3.getAlls().add(row2);
			}
			row3.setBan(mapban.get("zu_").toString());
			row3.setBanname(mapban.get("value_").toString());
			sheet3.add(row3);
		}
		etlsy.setBan(sheet3);
		// 填充excel
		ExcelUtils.fillData(sydjPath, etlsy, sydjExec, os);
	}

	@Override
	public List<EtlStopMxVO> findEtlStopDatas(Date date, String ban) {
		List<EtlStopMxVO> mxs = new ArrayList<EtlStopMxVO>();
		List<ETLStopTp> stoptps = dao.finEtlStops(date, ban);

		EtlStopMxVO mx;
		// String stop = null;
		if (stoptps.size() == 0) {
			// addStopList(mx., date, ban);
			outEtlStopData(mxs, date, ban);
		}
		else {
			for (ETLStopTp etlStopTp : stoptps) {
				mx = new EtlStopMxVO();
				mx.setId(etlStopTp.getId());
				mx.setRemk(etlStopTp.getRemk());
				mx.setStop(etlStopTp.getStop());
				mx.setXzda(etlStopTp.getXzda());
				mx.setXzdj(etlStopTp.getXzdj());
				mx.setXzjx(etlStopTp.getXzjx());
				mx.setXzzy(etlStopTp.getXzzy());
				mxs.add(mx);

				// stop = etlStopTp.getStop();
			}
			// int hour = 0, minute = 0;
			// if (stop != null && !stop.isEmpty()) {
			// String[] arrs = stop.split("-");
			// if (arrs.length == 2) {
			// String[] times = arrs[1].split(":");
			// if (times.length == 2) {
			// hour = Integer.parseInt(times[0]);
			// minute = Integer.parseInt(times[1]);
			//
			// date = DateUtils.formatDate(date, hour, minute, 59);
			// }
			// }
			// }
			// Ban $ban = Ban.get(ban);
			// if ($ban.end == hour || (Ban.two.key.equals(ban) && hour == 0))
			// return mxs;
			// outEtlStopData(mxs, date, ban);
			// addStopList(mxs, date, ban);
		}
		return mxs;
	}

	/**
	 * <p>
	 * 根据PC6000的停线记录表，生成对的ETL停线信息
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param sbstops
	 * @param date
	 * @param ban
	 */
	private void outEtlStopData(List<EtlStopMxVO> mxs, Date date, String ban) {
		EtlStopMxVO mx;
		Ban $ban = Ban.get(ban);
		if ($ban == null) return;
		Date nextDay = DateUtils.add(date, Calendar.DAY_OF_MONTH, 1);

		// 三班的工作时间为明天的早上零晨零点到早上八点；一班为当天的早上八点到下午四午；二班为下午四点到明天早上零晨零点
		Date $date = Ban.three.key.equals(ban) ? nextDay : date;

		// mxs大于0时：表示当前日期的停线记录有一部份已经从PC6000的数据库登录到生产系统中了。现在要将另一部份PC6000中的停线记录写到生产系统中
		Date begin = mxs.size() > 0 ? $date
				: DateUtils.formatDate($date, $ban.begin, 0, 0);
		Date end = DateUtils.formatDate($date, $ban.end, 0, 0);

		Date startD, stopD = DateUtils.formatDate($date, $ban.begin, 0, 0);

		List<YxRecordVO> vos = rcrzDAO.queryYxRecords(begin, end);

		if (vos.size() == 0 && mxs.size() == 0) {
			YxRecordVO yxRecordVO = rcrzDAO.getYxRecord(stopD);
			if (yxRecordVO != null
					&& EEtlStop.stop.name.equals(yxRecordVO.getVarValue())) {
				mx = new EtlStopMxVO();
				mx.setStop($ban.time);
				mxs.add(mx);
			}
			return;
		}
		if (vos.size() == 0) return;
		// 记录当前机器状态和上一个机器状态
		String stop = EEtlStop.stop.name;
		for (YxRecordVO vo : vos) {
			if (EEtlStop.start.name.equals(vo.getVarValue())
					&& EEtlStop.stop.name.equals(stop)) {
				startD = DateUtils.parse(vo.getSystime(), DateUtils.YMD_HMS);
				mx = new EtlStopMxVO();
				mx.setStop(DateUtils.format(stopD, DateUtils.HH_MM) + "-"
						+ DateUtils.format(startD, DateUtils.HH_MM));
				mxs.add(mx);
			}
			stop = vo.getVarValue();
			stopD = DateUtils.parse(vo.getSystime(), DateUtils.YMD_HMS);
		}
		if (EEtlStop.stop.name.equals(stop)) {
			mx = new EtlStopMxVO();
			mx.setStop(DateUtils.format(stopD, DateUtils.HH_MM) + "-"
					+ DateUtils.format(end, DateUtils.HH_MM));
			mxs.add(mx);
		}
	}

	@Override
	public List<SBmxVO> findSbDatas(Date dasr) {
		Date first = DateUtils.getFirstDayOfMonth(dasr);
		Date last = DateUtils.getLastDayOfMonth(dasr);
		List<SBmxVO> sbVos = new ArrayList<SBmxVO>();
		SBmxVO sbVo;
		if (!dao.isExistedEtlsb(dasr)) {
			while (first.compareTo(last) <= 0) {
				sbVo = new SBmxVO();
				sbVo.setDasr(first);
				sbVos.add(sbVo);

				first = DateUtils.add(first, Calendar.DAY_OF_MONTH, 1);
			}
			return sbVos;
		}
		List<ETLsbTp> etlsbTps = dao.findETLsbTps(first, last);
		for (ETLsbTp etLsbTp : etlsbTps) {
			sbVo = new SBmxVO();
			sbVo.setDasr(etLsbTp.getDasr());
			sbVo.setGzda(etLsbTp.getGzda());
			sbVo.setJszl(etLsbTp.getJszl());

			sbVos.add(sbVo);
		}
		return sbVos;
	}

	/**
	 * <p>
	 * 设置ETL停线特记信息
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param eTLVariTp
	 * @param vo
	 */
	private void setETLVariTp(ETLVariTp eTLVariTp, EtlStopVO vo) {
		eTLVariTp.setDasr(vo.getDasr());
		eTLVariTp.setVari1(vo.getVari1());
		eTLVariTp.setVari2(vo.getVari2());
		eTLVariTp.setVari3(vo.getVari3());
		eTLVariTp.setVari4(vo.getVari4());
		eTLVariTp.setVari5(vo.getVari5());
	}

	/**
	 * <p>
	 * 判断停线时间的格式是否正确。注：正确格式 HH:mm-HH:mm
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param stop
	 * @param begin
	 * @param end
	 * @param ban
	 * @return boolean
	 */
	private boolean checkEtlStop(String stop, int begin, int end, String ban) {
		if (stop == null || stop.isEmpty()) return true;
		String[] arrs, starts, ends;
		int hour, minute;
		arrs = stop.split("-");
		if (arrs.length != 2) {
			return false;
		}
		starts = arrs[0].split(":");
		if (starts.length != 2) {
			return false;
		}
		hour = Integer.parseInt(starts[0]);
		if (hour < begin || hour > end) {
			return false;
		}
		minute = Integer.parseInt(starts[1]);
		if (minute < 0 || minute > 60) {
			return false;
		}
		ends = arrs[1].split(":");
		if (starts.length != 2) {
			return false;
		}
		hour = Integer.parseInt(ends[0]);
		hour = Ban.two.key.equals(ban) && hour == 0 ? 24 : hour;
		if (hour < begin || hour > end) {
			return false;
		}
		minute = Integer.parseInt(ends[1]);
		if (minute < 0 || minute > 60) {
			return false;
		}
		return true;
	}

	@Override
	public void saveEtlStop(EtlStopVO vo) {
		ETLStopTp entity;
		String id, stop;
		Date date = new Date();
		Ban ban = Ban.get(vo.getBan());
		Date dasr = vo.getDasr();

		Date _dasr = dasr;
		if (Ban.three.key.equals(ban.key)) {
			_dasr = DateUtils.add(dasr, Calendar.DAY_OF_MONTH, 1);
		}
		Date $dasr = DateUtils.formatDate(_dasr, ban.end, 0, 0);
		if (date.before($dasr)) {
			throw new CocoException(-1, "ETL停线记录只能在下班后登录。" + ban.name
					+ "的下班时间在" + ban.end + "点后");
		}
		// 保存特记
		ETLVariTp vari = dao.getETLVariTp(dasr, vo.getBan());
		if (vari == null) {
			vari = new ETLVariTp();
			vari.setBan(vo.getBan());
			vari.setCrea(date);
			setETLVariTp(vari, vo);
			dao.saveETLVariTp(vari);
		}
		else {
			vari.setUpda(date);
			setETLVariTp(vari, vo);
			dao.updateETLVariTp(vari);
		}
		EtlStopMxVO[] items = vo.getItems();
		if (items == null || items.length == 0) return;
		for (EtlStopMxVO item : items) {
			id = item.getId();
			stop = item.getStop();
			// 判断停线时间的格式是否正确。
			if (!checkEtlStop(stop, ban.begin, ban.end, ban.key)) {
				throw new CocoException(-1, "时间(" + stop + ")输入不正确。" + ban.name
						+ "的时间在" + ban.begin + "点~" + ban.end + "点之间");
			}
			if ((id == null || id.isEmpty()) && stop != null && !stop.isEmpty()) {
				entity = new ETLStopTp();
				entity.setDasr(dasr);
				entity.setCrea(date);
				entity.setBan(vo.getBan());
				setETLStopTp(entity, item);
				dao.saveETLStopTp(entity);
				continue;
			}
			entity = dao.getETLStopTp(id);
			if (entity == null) return;
			if (stop == null || stop.isEmpty()) {
				dao.deleteETLStopTp(id);
				continue;
			}
			entity.setUpda(date);
			entity.setBan(vo.getBan());
			setETLStopTp(entity, item);
			dao.updateETLStopTp(entity);
		}
	}

	/**
	 * <p>
	 * 设置ETL停线明细信息
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param eTLStopTp
	 * @param item
	 */
	private void setETLStopTp(ETLStopTp eTLStopTp, EtlStopMxVO item) {
		eTLStopTp.setRemk(item.getRemk());
		eTLStopTp.setStop(item.getStop());
		eTLStopTp.setXzda(item.getXzda());
		eTLStopTp.setXzdj(item.getXzdj());
		eTLStopTp.setXzjx(item.getXzjx());
		eTLStopTp.setXzzy(item.getXzzy());
	}

	@Override
	public ETLVariTp getETLVariTp(Date dasr, String ban) {
		return dao.getETLVariTp(dasr, ban);
	}

	@Override
	public void saveEtlsb(SBVO vo) {
		Date date = new Date();
		ETLsbTp sbtp;
		for (SBmxVO sbvo : vo.getItems()) {
			sbtp = dao.getETLsbTp(sbvo.getDasr());
			if (sbtp == null) {
				sbtp = new ETLsbTp();
				sbtp.setDasr(sbvo.getDasr());
				sbtp.setCrea(date);
				sbtp.setGzda(sbvo.getGzda());
				sbtp.setJszl(sbvo.getJszl());

				dao.saveETLsbTp(sbtp);
				continue;
			}
			sbtp.setUpda(new Date());
			sbtp.setGzda(sbvo.getGzda());
			sbtp.setJszl(sbvo.getJszl());
			dao.updateETLsbTp(sbtp);
		}
		// 从PC6000读数据更新ETL速报实动时间
		Date firstDay = DateUtils.getFirstDayOfMonth(date);
		Date lastDay = DateUtils.getLastDayOfMonth(date);
		updateEtlsbTp(firstDay, lastDay);
	}

	// /**
	// * /**
	// * <p>
	// * 构造ETL速报查询
	// * </p>
	// *
	// * @param betweenStr
	// * @return
	// */
	// private String getETlsbSql(String betweenStr) {
	// StringBuilder bm = new StringBuilder();
	//
	// bm.append("select a.zu_,allZpzl_=cast(isnull(a.allZpzl_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("allZrzl_=cast(isnull(a.allZrzl_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("Zrzl1_=cast(isnull(a.Zrzl1_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("Zrzl2_=cast(isnull(a.Zrzl2_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("Zrzl3_=cast(isnull(a.Zrzl3_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C1_=cast(isnull(a.C1_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C2_=cast(isnull(a.C2_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C11_=cast(isnull(a.C1_,0)*0.001/(isnull(a.Zrzl1_,1)*0.001)*100 as numeric(10,1)), \r\n");
	// bm.append("C21_=cast(isnull(a.C2_,0)*0.001/(isnull(a.Zrzl2_,1)*0.001)*100 as numeric(10,1)), \r\n");
	// bm.append("C3_=cast(isnull(a.C3_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C4_=cast(isnull(a.C4_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C5_=cast(isnull(a.C5_,0)*0.001 as numeric(10,3)), \r\n");
	// bm.append("C6_=cast(isnull(a.C6_,0)*0.001 as numeric(10,3)) \r\n");
	// bm.append("from  \r\n");
	// bm.append("( \r\n");
	// bm.append("select a.zu_,allZpzl_=sum(a.allZpzl_),allZrzl_=sum(a.allZrzl_),Zrzl1_=sum(a.zrzl1_),Zrzl2_=sum(a.zrzl2_),Zrzl3_=sum(a.zrzl3_),C1_=sum(C1_),C2_=sum(C2_),C3_=sum(C3_),C4_=sum(C4_),C5_=sum(C5_),C6_=sum(C6_) from \r\n");
	// bm.append("( \r\n");
	// bm.append("select dhno_=(select dhno_ from sino_ycaitp b where b.jbno_=a.jbno_),a.zu_, \r\n");
	// bm.append("zrzl1_=(select sum(ZRZL_) from sino_ycaitp b,sino_dhuotp c where substring(c.pzno_,1,1)='1' and b.jbno_=a.jbno_ and c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2) and substring(b.dhno_,1,1)<>'T'), \r\n");
	// bm.append("zrzl2_=(select sum(ZRZL_) from sino_ycaitp b,sino_dhuotp c where substring(c.pzno_,1,1)='2' and b.jbno_=a.jbno_ and c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2) and substring(b.dhno_,1,1)<>'T'), \r\n");
	// bm.append("zrzl3_=(select sum(ZRZL_) from sino_ycaitp b where b.jbno_=a.jbno_ and substring(b.dhno_,1,1)='T'),  \r\n");
	// bm.append("allZrzl_=(select ZRZL_ from sino_ycaitp b where b.jbno_=a.jbno_), \r\n");
	// bm.append("allZpzl_=(select sum(b.zpzl_) from sino_zpngtp b where elin_='N' and b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C1_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('1','9') and substring(b.pzno_,1,1)='1' AND b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C2_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('1','9') and substring(b.pzno_,1,1)='2' AND b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C3_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('4') and substring(b.pzno_,1,1)='1' AND b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C4_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('4') and substring(b.pzno_,1,1)='2' AND b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C5_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('S') and substring(b.pzno_,1,1)='1' AND b.RCZPNO_=a.jbno_), \r\n");
	// bm.append(" C6_=(SELECT SUM(ZPZL_) FROM SINO_ZPNGTP b WHERE elin_='N' and b.CHAN_ in('S') and substring(b.pzno_,1,1)='2' AND b.RCZPNO_=a.jbno_) \r\n");
	// bm.append("from \r\n");
	// bm.append("( \r\n");
	// bm.append("select distinct c.Zrj_ as jbno_,b.zu_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
	// bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and \r\n ");
	// bm.append("" + betweenStr + " \r\n");
	// bm.append(")a   \r\n");
	// bm.append(") a group by a.zu_ \r\n");
	// bm.append(")a \r\n");
	//
	// return bm.toString();
	// }

	/**
	 * <p>
	 * 查询一个时间段的停线时间总和
	 * </p>
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 * @return long
	 */
	private long getEtlStopTime(Date scsjBegin, Date scsjEnd) {
		long stopMilliseconds = 0l;
		List<YxRecordVO> vos = rcrzDAO.queryYxRecords(scsjBegin, scsjEnd);
		if (vos == null || vos.size() == 0) return stopMilliseconds;
		String stop = EEtlStop.stop.name;
		Date stopDate = scsjBegin;
		for (YxRecordVO vo : vos) {
			if (EEtlStop.start.name.equals(vo.getVarValue())
					&& EEtlStop.stop.name.equals(stop)) {
				scsjBegin = DateUtils.parse(vo.getSystime(), DateUtils.YMD_HMS);
				stopMilliseconds += scsjBegin.getTime() - stopDate.getTime();
			}
			stop = vo.getVarValue();
			stopDate = DateUtils.parse(vo.getSystime(), DateUtils.YMD_HMS);
		}
		if (EEtlStop.stop.name.equals(stop)) {
			stopMilliseconds += scsjEnd.getTime() - stopDate.getTime();
		}
		return stopMilliseconds;
	}

	/**
	 * <p>
	 * 根据ETL品质管理记录，修改对应的ETL速度信息。从入到切为一个停线时间区间
	 * </p>
	 * <ul>
	 * <li>入：
	 * <li>切：
	 * <p>
	 * author:张良
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 */
	private void updateEtlsbTp(Date scsjBegin, Date scsjEnd) {
		List<ETLsbTp> etlsbs = dao.findETLsbTps(scsjBegin, scsjEnd);
		if (etlsbs.size() == 0) return;
		Date sotpDate, scsj, dasr, nextDate, current = DateUtils.parse(new Date(), DateUtils.YMD);
		long passMinute = 0l, duraMilliseconds, stopMilliseconds = 0l;
		String $pass, pass;
		List<EtlpzGl> etlpzGls;
		for (ETLsbTp entity : etlsbs) {
			if (entity.getGzda() == null || entity.getGzda().doubleValue() == 0) continue;
			dasr = DateUtils.formatDate(entity.getDasr(), 8, 0, 0);
			if (dasr.compareTo(current) > 0) continue;
			nextDate = DateUtils.formatDate(dasr, 32, 0, 0);
			sotpDate = dasr;
			// 查询一天的ETL生产卷记录（一天的时间是从今天早上的8点到明天早上8点）
			etlpzGls = etlpzglBO.query(dasr, nextDate);
			passMinute = 0l;
			$pass = EPass.Q.key;
			for (EtlpzGl etlpzGl : etlpzGls) {
				if ((pass = etlpzGl.getPass()) == null || pass.isEmpty()) continue;
				if (EPass.R.key.equals(pass) && EPass.Q.key.equals($pass)) {
					sotpDate = DateUtils.parse(etlpzGl.getScsj(), "yyyy-MM-dd HH:mm:ss");
					$pass = EPass.R.key;
					continue;
				}
				if (EPass.Q.key.equals(pass) && EPass.R.key.equals($pass)) {
					scsj = DateUtils.parse(etlpzGl.getScsj(), "yyyy-MM-dd HH:mm:ss");
					duraMilliseconds = scsj.getTime() - sotpDate.getTime();
					// 停线时间
					stopMilliseconds = getEtlStopTime(sotpDate, scsj);
					passMinute += DateUtils.millisecondsTominute(duraMilliseconds
							- stopMilliseconds);
					$pass = EPass.Q.key;
				}
			}
			if (EPass.R.key.equals($pass)) {
				duraMilliseconds = nextDate.getTime() - sotpDate.getTime();
				// 停线时间
				stopMilliseconds = getEtlStopTime(sotpDate, nextDate);
				passMinute += DateUtils.millisecondsTominute(duraMilliseconds
						- stopMilliseconds);
			}
			entity.setPass(DateUtils.minuterToHour(passMinute));
			entity.setIfdj("1");
			dao.updateETLsbTp(entity);
		}
	}

	@Override
	public void fetchEtlsb(EtlSydjVO vo, OutputStream os) {
		EtlSbMainDataVO etlsy = new EtlSbMainDataVO();
		Date etsd = vo.getEtsd();
		// 一天的时间段
		Date currentDay = DateUtils.add(etsd, Calendar.HOUR_OF_DAY, 8);
		Date nextDay = DateUtils.add(currentDay, Calendar.DAY_OF_MONTH, 1);
		Date firstDay = DateUtils.getFirstDayOfMonth(etsd);
		Date $firstDay = DateUtils.add(DateUtils.getFirstDayOfMonth(etsd), Calendar.HOUR_OF_DAY, 8);
		Double allzrzl1 = 0d, allzrzl2 = 0d, allzrzl3 = 0d, sjzl = 0d, sjzlAll = 0d;
		List<EtlBanTjVO> vos = new ArrayList<EtlBanTjVO>();
		// 统计当天某班
		EtlBanTjVO tjVO = null;
		// 统计当天
		EtlBanTjVO $tjVO = new EtlBanTjVO();
		Map<String, EtlBanTjVO> tjMaps = dao.findBanTjVO(currentDay, nextDay);
		for (Ban ban : Ban.values()) {
			if ((tjVO = tjMaps.get(ban.key)) == null) {
				tjVO = new EtlBanTjVO();
			}
			vos.add(tjVO);

			if (Ban.one.key.equals(ban.key)) {
				allzrzl1 = tjVO.getZrzlAll();
			}
			else if (Ban.two.key.equals(ban.key)) {
				allzrzl2 = tjVO.getZrzlAll();
			}
			else if (Ban.three.key.equals(ban.key)) {
				allzrzl3 = tjVO.getZrzlAll();
			}

			$tjVO.setZrzlOR($tjVO.getZrzlOR() + tjVO.getZrzlOR());
			$tjVO.setZrzlS($tjVO.getZrzlS() + tjVO.getZrzlS());
			$tjVO.setZrzlC($tjVO.getZrzlC() + tjVO.getZrzlC());
			$tjVO.setZrzlAll($tjVO.getZrzlAll() + tjVO.getZrzlAll());
			$tjVO.setHgzpzlS($tjVO.getHgzpzlS() + tjVO.getHgzpzlS());
			$tjVO.setHgzpzlC($tjVO.getHgzpzlC() + tjVO.getHgzpzlC());
			$tjVO.setFszpzlS($tjVO.getFszpzlS() + tjVO.getFszpzlS());
			$tjVO.setFszpzlC($tjVO.getFszpzlC() + tjVO.getFszpzlC());
			$tjVO.setFczpzlS($tjVO.getFczpzlS() + tjVO.getFczpzlS());
			$tjVO.setFczpzlC($tjVO.getFczpzlC() + tjVO.getFczpzlC());
			$tjVO.setZpzl($tjVO.getZpzl() + tjVO.getZpzl());
		}
		vos.add($tjVO);
		sjzl = $tjVO.getZrzlAll();

		// 统计这个月的第一天到当前的生产量
		EtlBanTjVO $$tjVO = dao.getTjVO($firstDay, nextDay);
		vos.add($$tjVO);
		etlsy.setVos(vos);
		sjzlAll = $$tjVO.getZrzlAll();

		ETLsbTp sbtp = dao.getETLsbTp(etsd);
		double $sdsj = 8.0, sdsj = 0d;
		double jszl = 0.0;
		double pass = 0.0;
		double sdsj1 = 0d, sdsj2 = 0d, sdsj3 = 0d, $sdsj1 = 0d, $sdsj2 = 0d, $sdsj3 = 0d;
		double gzda = sbtp != null && sbtp.getGzda() != null ? sbtp.getGzda()
				: 0d;
		if (gzda > 0) {
			sdsj1 = dao.getStopTime(etsd, Ban.one.key);
			sdsj2 = dao.getStopTime(etsd, Ban.two.key);
			sdsj3 = dao.getStopTime(etsd, Ban.three.key);
			// 操业时间
			etlsy.setZysj1($sdsj);
			etlsy.setZysj2($sdsj);
			etlsy.setZysj3($sdsj);
			// 实动时间
			$sdsj1 = $sdsj - sdsj1 / 60.0;
			etlsy.setSdsj1(NumberUtils.format($sdsj1, 2));
			$sdsj2 = $sdsj - sdsj2 / 60.0;
			etlsy.setSdsj2(NumberUtils.format($sdsj2, 2));
			$sdsj3 = $sdsj - sdsj3 / 60.0;
			etlsy.setSdsj3(NumberUtils.format($sdsj3, 2));
			// 实动率
			etlsy.setSdli1(NumberUtils.format(etlsy.getSdsj1() / $sdsj * 100.0, 1));
			etlsy.setSdli2(NumberUtils.format(etlsy.getSdsj2() / $sdsj * 100.0, 1));
			etlsy.setSdli3(NumberUtils.format(etlsy.getSdsj3() / $sdsj * 100.0, 1));
			// 能率
			if ($sdsj1 > 0) {
				etlsy.setNli1(NumberUtils.format(allzrzl1 / $sdsj1, 3));
			}
			if ($sdsj2 > 0) {
				etlsy.setNli2(NumberUtils.format(allzrzl2 / $sdsj2, 3));
			}
			if ($sdsj3 > 0) {
				etlsy.setNli3(NumberUtils.format(allzrzl3 / $sdsj3, 3));
			}
			// 当日计划
			jszl = sbtp.getJszl() != null ? sbtp.getJszl() : 0d;
			etlsy.setJszl(jszl);

			// 当日较差
			etlsy.setChazl(NumberUtils.format(sjzl - jszl, 2));
			// 当日操业时间
			etlsy.setZysj(gzda);
			// 当日实动时间
			sdsj = NumberUtils.format($sdsj1 + $sdsj2 + $sdsj3, 2);
			etlsy.setSdsj(sdsj);
			// 当日pass时间
			pass = sbtp.getPass() != null ? sbtp.getPass() : 0d;
			etlsy.setPass(NumberUtils.format(pass, 2));
			// 当日实动率
			// 当日能率
			if (sdsj > 0) {
				etlsy.setSdli(NumberUtils.format(sdsj / gzda * 100.0, 1));
				etlsy.setNli(NumberUtils.format(sjzl / sdsj, 3));
				etlsy.setPassli(NumberUtils.format(pass / sdsj * 100, 2));
			}
		}
		// 当日实绩
		etlsy.setSjzl(sjzl);
		// 查询ETL速报表总数据
		EtlSbTjVO sbTjVO = dao.getSbTjVO(firstDay, etsd);
		// 总计划
		double jszlAll = sbTjVO.getJszl() != null ? sbTjVO.getJszl() : 0d;
		etlsy.setJszlAll(jszlAll);
		double xzAll = dao.getETLStopTpbydasr(firstDay, etsd);
		double xzdaAll = dao.getETLStopXzdaTpbydasr(firstDay, etsd);
		double _gzda = sbTjVO.getGzda() != null ? sbTjVO.getGzda() : 0d;
		// 总操业
		double zyAll = _gzda - xzdaAll / 60.0;
		etlsy.setZyAll(NumberUtils.format(zyAll, 2));
		// 总实动
		double sdsjAll = _gzda - xzAll / 60.0;
		etlsy.setSdsjAll(NumberUtils.format(sdsjAll, 2));
		// 总Pass时间
		double passAll = sbTjVO.getPass() != null ? sbTjVO.getPass() : 0d;
		etlsy.setPassAll(NumberUtils.format(passAll, 2));
		if (sdsjAll > 0) {
			etlsy.setPassAllli(NumberUtils.format(passAll / sdsjAll * 100, 2));
		}
		// 总实绩
		etlsy.setSjzlAll(sjzlAll);
		// 总较差
		etlsy.setChazlAll(NumberUtils.format(sjzlAll - jszlAll, 2));
		// 总经历
		long dayNum = DateUtils.millisecondsToday(etsd.getTime()
				- firstDay.getTime());
		double jlAll = (dayNum + 1) * 24d;
		etlsy.setJlAll(jlAll);
		// 总实动率
		if (jlAll > 0) {
			etlsy.setSdAll(NumberUtils.format(sdsjAll / jlAll * 100.0, 2));
		}
		// 总修正率
		if (zyAll > 0) {
			etlsy.setXzAll(NumberUtils.format(sdsjAll / zyAll * 100.0, 2));
		}
		// 总能率
		if (sdsjAll > 0) {
			etlsy.setNliAll(NumberUtils.format(sjzlAll / sdsjAll, 3));
		}
		etlsy.setCrea(vo.getEtsd());
		// 查询停止时间
		List<ETLStopTp> stops = dao.findETLStopTps(etsd);
		List<EtlSbStopDataVO> sbVos = null;
		if (stops.size() > 0) {
			sbVos = new ArrayList<EtlSbStopDataVO>();
			EtlSbStopDataVO sbVo = new EtlSbStopDataVO();

			// 统计当日休止
			double xzda = 0d, xzzy = 0d, xzjx = 0d, xzdj = 0d;
			for (ETLStopTp stop : stops) {
				xzda += stop.getXzda() != null ? stop.getXzda() : 0d;
				xzzy += stop.getXzzy() != null ? stop.getXzzy() : 0d;
				xzjx += stop.getXzjx() != null ? stop.getXzjx() : 0d;
				xzdj += stop.getXzdj() != null ? stop.getXzdj() : 0d;

				sbVo = new EtlSbStopDataVO();
				sbVo.setStop(stop.getStop());
				sbVo.setXzda(stop.getXzda());
				sbVo.setXzdj(stop.getXzdj());
				sbVo.setXzjx(stop.getXzjx());
				sbVo.setXzzy(stop.getXzzy());
				sbVo.setRemk(stop.getRemk());
				sbVo.setVari(stop.getVari());
				sbVos.add(sbVo);
			}
			etlsy.setStopbms(sbVos);
			// 当日休止I
			etlsy.setXz1(xzda);
			// 当日休止II
			etlsy.setXz2(xzzy);
			// 当日休止II
			etlsy.setXz3(xzjx);
			// 当日休止II
			etlsy.setXz4(xzdj);
		}
		// 统计到目前为止休止
		XzVO xzVO = dao.getXzVO(firstDay, etsd);
		// 当日休止I
		etlsy.setXzAll1(xzVO.getXzda());
		etlsy.setXzAllhour1(DateUtils.minuterToHour(etlsy.getXzAll1()));
		// 当日休止II
		etlsy.setXzAll2(xzVO.getXzzy());
		etlsy.setXzAllhour2(DateUtils.minuterToHour(etlsy.getXzAll2()));
		// 当日休止II
		etlsy.setXzAll3(xzVO.getXzjx());
		etlsy.setXzAllhour3(DateUtils.minuterToHour(etlsy.getXzAll3()));
		// 当日休止II
		etlsy.setXzAll4(xzVO.getXzdj());
		etlsy.setXzAllhour4(DateUtils.minuterToHour(etlsy.getXzAll4()));
		// 查询特记v
		ETLVariTp etlVariTp = dao.getETLVariTp(etsd, Ban.one.key);
		if (etlVariTp == null) etlVariTp = new ETLVariTp();
		etlsy.setVari1(etlVariTp);

		etlVariTp = dao.getETLVariTp(etsd, Ban.two.key);
		if (etlVariTp == null) etlVariTp = new ETLVariTp();
		etlsy.setVari2(etlVariTp);

		etlVariTp = dao.getETLVariTp(etsd, Ban.three.key);
		if (etlVariTp == null) etlVariTp = new ETLVariTp();
		etlsy.setVari3(etlVariTp);
		// 填充excel
		ExcelUtils.fillData(sbdjPath, etlsy, sbdjExec, os);
	}

	/**
	 * <p>
	 * 构造总锡原按班查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 * @return
	 */
	private String getAllSql(String betweenStr, String zu) {
		StringBuilder bm = new StringBuilder();
		bm.append(" --总查询 \r\n");
		bm.append(" select a.*,cha_=a.sj_-a.mb_ \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select zrzlAll_=sum(a.zrzl2_),mb_=cast((sum(a.mbfuzmAll_)+sum(a.mbfufmAll_))/sum(a.zrzl2_) as numeric(10,2)), \r\n");
		bm.append(" sj_=cast((sum(a.sjfuzmAll_)+sum(a.sjfufmAll_))/sum(a.zrzl2_) as numeric(10,2)) \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append(" bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append(" lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2)) \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)), \r\n");
		bm.append(" sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2)) \r\n");
		bm.append("  from  \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*, \r\n");
		bm.append(" mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append(" mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append(" sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_), \r\n");
		bm.append(" sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_) \r\n");
		bm.append("  from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select jbno_,zrzl_=ZrZL_*0.001, \r\n");
		bm.append(" etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append(" c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001, \r\n");
		bm.append(" fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append(" (select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append(" fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append(" (select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append(" ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append(" zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_) \r\n");
		bm.append(" from sino_ycaitp b ,  \r\n");
		bm.append(" ( \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and  \r\n");
		bm.append("" + betweenStr + " and b.zu_='" + zu + "' \r\n");
		bm.append(" ) a where b.jbno_=a.RCZPNO_   \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a \r\n");
		return bm.toString();
	}

	/**
	 * <p>
	 * 构造总锡原查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 * @return
	 */
	private String getAllSql(String betweenStr) {
		StringBuilder bm = new StringBuilder();
		bm.append(" --总查询 \r\n");
		bm.append(" select a.*,cha_=a.sj_-a.mb_ \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select zrzlAll_=sum(a.zrzl2_),mb_=cast((sum(a.mbfuzmAll_)+sum(a.mbfufmAll_))/sum(a.zrzl2_) as numeric(10,2)), \r\n");
		bm.append(" sj_=cast((sum(a.sjfuzmAll_)+sum(a.sjfufmAll_))/sum(a.zrzl2_) as numeric(10,2)) \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append(" bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append(" lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2)) \r\n");
		bm.append(" from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)), \r\n");
		bm.append(" sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2)) \r\n");
		bm.append("  from  \r\n");
		bm.append(" ( \r\n");
		bm.append(" select a.*, \r\n");
		bm.append(" mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append(" mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append(" sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_), \r\n");
		bm.append(" sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_) \r\n");
		bm.append("  from \r\n");
		bm.append(" ( \r\n");
		bm.append(" select jbno_,zrzl_=ZrZL_*0.001, \r\n");
		bm.append(" etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append(" c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001, \r\n");
		bm.append(" fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append(" (select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append(" fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append(" (select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append(" ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append(" zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_) \r\n");
		bm.append(" from sino_ycaitp b ,  \r\n");
		bm.append(" ( \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and  \r\n");
		bm.append("" + betweenStr + " \r\n");
		bm.append(" ) a where b.jbno_=a.RCZPNO_   \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a  \r\n");
		bm.append(" ) a \r\n");
		return bm.toString();
	}

	/**
	 * <p>
	 * 构造表面锡原按班查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 * @param ban
	 * @return
	 */
	private String getBmSql(String betweenStr, String zu) {
		StringBuilder bm = new StringBuilder();

		bm.append("  --总查询 \r\n");
		bm.append("select a.zrzlAll_,a.mbfuzm1_,a.sjfuzm1_,cha1_=a.mbfuzm1_-a.sjfuzm1_,a.mbfuzm2_,a.sjfuzm2_,cha2_=a.sjfuzm2_-a.mbfuzm2_ \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.zrzlAll_,mbfuzm1_=a.mbfuzm_,sjfuzm1_=cast((a.sjfuzmAll_/a.mjAll_)*1000 as numeric(10,2)),mbfuzm2_=cast(a.mbfuzmAll_/a.zrzlAll_ as numeric(10,2)), \r\n");
		bm.append("sjfuzm2_=cast(a.sjfuzmAll_/a.zrzlAll_ as numeric(10,2)) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select zrzlAll_=sum(a.zrzl2_),a.mbfuzm_,mjAll_=sum(a.xpkn_*a.jbcn_),mbfuzmAll_=sum(a.mbfuzmAll_),sjfuzmAll_=sum(a.sjfuzmAll_) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append("bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append("lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2)) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)), \r\n");
		bm.append("sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2)) \r\n");
		bm.append(" from  \r\n");
		bm.append("( \r\n");
		bm.append("select a.*, \r\n");
		bm.append("mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append("mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append("sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_), \r\n");
		bm.append("sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_) \r\n");
		bm.append(" from \r\n");
		bm.append("( \r\n");
		bm.append("select jbno_,zrzl_=ZrZL_*0.001, \r\n");
		bm.append("etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append("c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001, \r\n");
		bm.append("fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append("(select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append("fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append("(select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append("ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_),\r\n");
		bm.append("zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_) \r\n");
		bm.append("from sino_ycaitp b , \r\n");
		bm.append("(\r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and  \r\n");
		bm.append("" + betweenStr + " and b.zu_='" + zu + "' \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_  \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");
		bm.append(") a group by a.mbfuzm_ \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");
		return bm.toString();
	}

	/**
	 * <p>
	 * 构造一个月生产的班查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 * @return
	 */
	private String getBanSql(String betweenStr) {
		StringBuilder bm = new StringBuilder();
		bm.append("select a.zu_,b.value_ from \r\n");
		bm.append("( \r\n");
		bm.append("select distinct b.zu_  from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and \r\n");
		bm.append("" + betweenStr + "  \r\n");
		bm.append(") a  left join COCO_CODE_ITEM b on a.zu_=b.key_ and b.code_='SINO_ZU' \r\n");
		return bm.toString();
	}

	/**
	 * <p>
	 * 构造表面锡原查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 *            一个月的时间段
	 * @return
	 */
	private String getBmSql(String betweenStr) {

		StringBuilder bm = new StringBuilder();
		bm.append(" --总装入量  \r\n");
		bm.append("declare @AllZrzl_ numeric(10,2) \r\n");
		bm.append("select @AllZrzl_=sum(ZRZL_) \r\n");
		bm.append("from sino_ycaitp b , \r\n");
		bm.append("( \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') \r\n");
		bm.append(" and " + betweenStr + "  \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_  \r\n");
		bm.append("--总查询 \r\n");
		bm.append("select a.zrzlAll_,a.mbfuzm1_,a.sjfuzm1_,cha1_=a.mbfuzm1_-a.sjfuzm1_,a.mbfuzm2_,a.sjfuzm2_,cha2_=a.sjfuzm2_-a.mbfuzm2_,a.sjyl_,a.mbyl_ \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.zrzlAll_,mbfuzm1_=a.mbfuzm_,sjfuzm1_=cast((a.sjfuzmAll_/a.mjAll_)*1000 as numeric(10,2)),mbfuzm2_=cast(a.mbfuzmAll_/a.zrzlAll_ as numeric(10,2)), \r\n");
		bm.append("sjfuzm2_=cast(a.sjfuzmAll_/a.zrzlAll_ as numeric(10,2)),sjyl_=cast(a.sjfuzmAll_/@AllZrzl_ as numeric(10,2)),mbyl_=cast(a.mbfuzmAll_/@AllZrzl_ as numeric(10,2)) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select zrzlAll_=sum(a.zrzl2_),a.mbfuzm_,mjAll_=sum(a.xpkn_*a.jbcn_),mbfuzmAll_=sum(a.mbfuzmAll_),sjfuzmAll_=sum(a.sjfuzmAll_) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append("bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)), \r\n");
		bm.append("lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2)) \r\n");
		bm.append("from \r\n");
		bm.append("( \r\n");
		bm.append("select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)), \r\n");
		bm.append("sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2)) \r\n");
		bm.append(" from  \r\n");
		bm.append("( \r\n");
		bm.append("select a.*, \r\n");
		bm.append("mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append("mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)), \r\n");
		bm.append("sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_), \r\n");
		bm.append("sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_) \r\n");
		bm.append(" from \r\n");
		bm.append("( \r\n");
		bm.append("select jbno_,zrzl_=ZrZL_*0.001, \r\n");
		bm.append("etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_), \r\n");
		bm.append("c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001, \r\n");
		bm.append("fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append("(select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append("fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)), \r\n");
		bm.append("(select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))), \r\n");
		bm.append("ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_),\r\n");
		bm.append("zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_) \r\n");
		bm.append("from sino_ycaitp b , \r\n");
		bm.append("(\r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') \r\n");
		bm.append(" and " + betweenStr + "  \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_  \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");
		bm.append(") a group by a.mbfuzm_ \r\n");
		bm.append(") a \r\n");
		bm.append(") a \r\n");

		return bm.toString();
	}

	/**
	 * <p>
	 * 构造里面锡原查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 *            一个月的时间段
	 * @return
	 */
	private String getLmSql(String betweenStr, String zu) {

		StringBuilder bm = new StringBuilder();
		bm.append(" --总查询  \r\n");
		bm.append("select a.zrzlAll_,a.mbfufm1_,a.sjfufm1_,cha1_=a.mbfufm1_-a.sjfufm1_,a.mbfufm2_,a.sjfufm2_,cha2_=a.sjfufm2_-a.mbfufm2_  \r\n");
		bm.append("from  \r\n");
		bm.append("(  \r\n");
		bm.append("select a.zrzlAll_,mbfufm1_=a.mbfufm_,sjfufm1_=cast((a.sjfufmAll_/a.mjAll_)*1000 as numeric(10,2)),mbfufm2_=cast(a.mbfufmAll_/a.zrzlAll_ as numeric(10,2)),  \r\n");
		bm.append("sjfufm2_=cast(a.sjfufmAll_/a.zrzlAll_ as numeric(10,2))  \r\n");
		bm.append("from   \r\n");
		bm.append("(  \r\n");
		bm.append("select zrzlAll_=sum(a.zrzl2_),a.mbfufm_,mjAll_=sum(a.xpkn_*a.jbcn_),mbfufmAll_=sum(a.mbfufmAll_),sjfufmAll_=sum(a.sjfufmAll_)  \r\n");
		bm.append("from   \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)),  \r\n");
		bm.append("bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)),  \r\n");
		bm.append("lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2))  \r\n");
		bm.append("from  \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)),  \r\n");
		bm.append("sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2))  \r\n");
		bm.append(" from   \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,  \r\n");
		bm.append("mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)),  \r\n");
		bm.append("mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)),  \r\n");
		bm.append("sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_),  \r\n");
		bm.append("sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_)  \r\n");
		bm.append(" from  \r\n");
		bm.append("(  \r\n");
		bm.append("select jbno_,zrzl_=ZrZL_*0.001,  \r\n");
		bm.append("etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_),  \r\n");
		bm.append("c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001,  \r\n");
		bm.append("fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)),  \r\n");
		bm.append("(select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))),  \r\n");
		bm.append("fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)),  \r\n");
		bm.append("(select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))),  \r\n");
		bm.append("ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_),  \r\n");
		bm.append("zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_)  \r\n");
		bm.append("from sino_ycaitp b ,   \r\n");
		bm.append("(  \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and  \r\n");
		bm.append("" + betweenStr + " and b.zu_='" + zu + "' \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_    \r\n");
		bm.append(") a   \r\n");
		bm.append(") a   \r\n");
		bm.append(") a   \r\n");
		bm.append(") a group by a.mbfufm_  \r\n");
		bm.append(") a  \r\n");
		bm.append(") a  \r\n");

		return bm.toString();
	}

	/**
	 * <p>
	 * 构造里面锡原查询语句
	 * </p>
	 * 
	 * @param betweenStr
	 *            一个月的时间段
	 * @return
	 */
	private String getLmSql(String betweenStr) {

		StringBuilder bm = new StringBuilder();
		bm.append(" --总装入量  \r\n");
		bm.append("declare @AllZrzl_ numeric(10,2)   \r\n");
		bm.append("select @AllZrzl_=sum(ZRZL_)  \r\n");
		bm.append("from sino_ycaitp b ,   \r\n");
		bm.append("(  \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') \r\n");
		bm.append(" and " + betweenStr + "  \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_   \r\n");
		bm.append("--总查询  \r\n");
		bm.append("select a.zrzlAll_,a.mbfufm1_,a.sjfufm1_,cha1_=a.mbfufm1_-a.sjfufm1_,a.mbfufm2_,a.sjfufm2_,cha2_=a.sjfufm2_-a.mbfufm2_,a.sjyl_,a.mbyl_  \r\n");
		bm.append("from  \r\n");
		bm.append("(  \r\n");
		bm.append("select a.zrzlAll_,mbfufm1_=a.mbfufm_,sjfufm1_=cast((a.sjfufmAll_/a.mjAll_)*1000 as numeric(10,2)),mbfufm2_=cast(a.mbfufmAll_/a.zrzlAll_ as numeric(10,2)),  \r\n");
		bm.append("sjfufm2_=cast(a.sjfufmAll_/a.zrzlAll_ as numeric(10,2)),sjyl_=cast(a.sjfufmAll_/@AllZrzl_ as numeric(10,2)),mbyl_=cast(a.mbfufmAll_/@AllZrzl_ as numeric(10,2))  \r\n");
		bm.append("from   \r\n");
		bm.append("(  \r\n");
		bm.append("select zrzlAll_=sum(a.zrzl2_),a.mbfufm_,mjAll_=sum(a.xpkn_*a.jbcn_),mbfufmAll_=sum(a.mbfufmAll_),sjfufmAll_=sum(a.sjfufmAll_)  \r\n");
		bm.append("from   \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,blmbAll_=cast((a.mbfuzmAll_+a.mbfufmAll_)/a.zrzl2_ as numeric(10,2)),blsjAll_=cast((a.sjfuzmAll_+a.sjfufmAll_)/a.zrzl2_ as numeric(10,2)),  \r\n");
		bm.append("bmmb_=cast(a.mbfuzmAll_/a.zrzl2_ as numeric(10,2)),bmsj_=cast(a.sjfuzmAll_/a.zrzl2_ as numeric(10,2)),  \r\n");
		bm.append("lmmb_=cast(a.mbfufmAll_/a.zrzl2_ as numeric(10,2)),lmsj_=cast(a.sjfufmAll_/a.zrzl2_ as numeric(10,2))  \r\n");
		bm.append("from  \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,mbfuzmAll_=cast(a.XPKN_*a.jbcn_*a.mbfuzm_/1000 as numeric(10,2)),mbfufmAll_=cast(a.XPKN_*a.jbcn_*a.mbfufm_/1000 as numeric(10,2)),  \r\n");
		bm.append("sjfuzmAll_=cast(a.XPKN_*a.jbcn_*a.sjfuzm_/1000 as numeric(10,2)),sjfufmAll_=cast(a.XPKN_*a.jbcn_*a.sjfufm_/1000 as numeric(10,2))  \r\n");
		bm.append(" from   \r\n");
		bm.append("(  \r\n");
		bm.append("select a.*,  \r\n");
		bm.append("mbfuzm_=cast(cast(a.fuzm_ as numeric(3,0))*1.12/10 as numeric(10,2)),  \r\n");
		bm.append("mbfufm_=cast(cast(a.fufm_ as numeric(3,0))*1.12/10 as numeric(10,2)),  \r\n");
		bm.append("sjfuzm_=(select top 1 sczm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_),  \r\n");
		bm.append("sjfufm_=(select top 1 scfm_ from  sino_zpngtp d where d.RCZPNO_=a.jbno_)  \r\n");
		bm.append(" from  \r\n");
		bm.append("(  \r\n");
		bm.append("select jbno_,zrzl_=ZrZL_*0.001,  \r\n");
		bm.append("etsd_=(select max(d.ETSD_) from sino_zpngtp d where d.RCZPNO_=b.jbno_),  \r\n");
		bm.append("c_='',no_='',baos_='',zrzl1_=ZRZL_*0.001,  \r\n");
		bm.append("fuzm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)),  \r\n");
		bm.append("(select c.fuzm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))),  \r\n");
		bm.append("fufm_=dbo.GET_FU((select c.fudw_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2)),  \r\n");
		bm.append("(select c.fufm_ from sino_dhuotp c where c.dhno_=substring(b.dhno_,1,7) and c.line_=substring(b.dhno_,8,2))),  \r\n");
		bm.append("ban_=(select top 1 d.ban_ from sino_zpngtp d where d.RCZPNO_=b.jbno_),  \r\n");
		bm.append("zrzl2_=ZRZL_,XPHO_,XPKN_,jbcn_=(select sum(d.jbcn_) from sino_zpngtp d where d.RCZPNO_=b.jbno_)  \r\n");
		bm.append("from sino_ycaitp b ,   \r\n");
		bm.append("(  \r\n");
		bm.append("select distinct c.Zrj_ as RCZPNO_ from sino_zpngtp a,SINO_ETLSLP b,SINO_ZSFMT c  \r\n");
		bm.append("where a.RCZPNO_=c.Zrj_ and b.Zsfmt_=c.id_ and  b.Zrzz_ in('4','5') and  \r\n");
		bm.append("" + betweenStr + " \r\n");
		bm.append(") a where b.jbno_=a.RCZPNO_    \r\n");
		bm.append(") a   \r\n");
		bm.append(") a   \r\n");
		bm.append(") a   \r\n");
		bm.append(") a group by a.mbfufm_  \r\n");
		bm.append(") a  \r\n");
		bm.append(") a  \r\n");

		return bm.toString();
	}

	@Override
	public void updateZu(String jbno, String zu) {
		ZpngTp zpngTp = zpBO.getRef(jbno);
		if (zpngTp == null) {
			throw new CocoException(-1, "Coil No." + jbno + "不存在");
		}
		zpngTp.setZu(zu);
		zpBO.update(zpngTp);
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
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

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public ISjBO getSjBO() {
		return sjBO;
	}

	public void setSjBO(ISjBO sjBO) {
		this.sjBO = sjBO;
	}

	public String getSydjPath() {
		return sydjPath;
	}

	public void setSydjPath(String sydjPath) {
		this.sydjPath = sydjPath;
	}

	public ExcelbookDataExecuter<EtlSydjDataVO> getSydjExec() {
		return sydjExec;
	}

	public void setSydjExec(ExcelbookDataExecuter<EtlSydjDataVO> sydjExec) {
		this.sydjExec = sydjExec;
	}

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
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

	public IBanBO getBanBO() {
		return banBO;
	}

	public void setBanBO(IBanBO banBO) {
		this.banBO = banBO;
	}

	public String getSbdjPath() {
		return sbdjPath;
	}

	public void setSbdjPath(String sbdjPath) {
		this.sbdjPath = sbdjPath;
	}

	public ExcelbookDataExecuter<EtlSbMainDataVO> getSbdjExec() {
		return sbdjExec;
	}

	public void setSbdjExec(ExcelbookDataExecuter<EtlSbMainDataVO> sbdjExec) {
		this.sbdjExec = sbdjExec;
	}

	public IEtlDAO getDao() {
		return dao;
	}

	public void setDao(IEtlDAO dao) {
		this.dao = dao;
	}

	public IRcrzDAO getRcrzDAO() {
		return rcrzDAO;
	}

	public void setRcrzDAO(IRcrzDAO rcrzDAO) {
		this.rcrzDAO = rcrzDAO;
	}

	public IEtlpzglBO getEtlpzglBO() {
		return etlpzglBO;
	}

	public void setEtlpzglBO(IEtlpzglBO etlpzglBO) {
		this.etlpzglBO = etlpzglBO;
	}

}
