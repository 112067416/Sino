package com.quanta.sino.yygl.bo;

import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.excel.api.ExcelDataExecuter;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.CodeSpbh;
import com.quanta.sino.cmn.constants.CodeYfdw;
import com.quanta.sino.cmn.constants.CodeYsfs;
import com.quanta.sino.cmn.constants.ESection;
import com.quanta.sino.cmn.constants.KhyfStat;
import com.quanta.sino.cmn.constants.YfdjStat;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.orm.Khyfdj;
import com.quanta.sino.yygl.bo.api.IKhyfBO;
import com.quanta.sino.yygl.bo.api.IKhyfdjBO;
import com.quanta.sino.yygl.dao.api.IKhyfDAO;
import com.quanta.sino.yygl.vo.CkdVO;
import com.quanta.sino.yygl.vo.CondVO;
import com.quanta.sino.yygl.vo.CpckdVO;
import com.quanta.sino.yygl.vo.KhyfVO;
import com.quanta.sino.yygl.vo.RChtjVO;
import com.quanta.sino.yygl.vo.ZrchsjfVO;

/**
 * <p>
 * 客户运费业务层实现类
 * </p>
 * <p>
 * create: 2011-2-13 下午07:24:14
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfBO implements IKhyfBO {

	/**
	 * 
	 */
	private IKhyfDAO khyfDAO;

	/**
	 * 
	 */
	private IKhyfdjBO khyfdjBO;

	/**
	 * 日出货统计记录路径
	 */
	private String rchtjListPath;

	/**
	 * 日出货统计处理接口
	 */
	private ExcelDataExecuter<RChtjVO> rchtjListExec;

	/**
	 * 成品出库单路径
	 */
	private String cpckdListPath;

	/**
	 * 成品出库单处理接口
	 */
	private ExcelbookDataExecuter<CkdVO> cpckdListExec;

	/**
	 * 装箱指示书业务接口
	 */
	private IZxzsBO zxzsBO;

	/**
	 * 原生SQL查询接口
	 */
	private ISqlDAO sqlDAO;

	@Override
	public void save(Khyf entity) {
		khyfDAO.save(entity);
	}

	@Override
	public void saveYf(KhyfVO vo) {
		String id = null;
		if ((id = vo.getId()) == null || id.isEmpty()) {
			throw new CocoException(-1, "运输费用主键不存在");
		}
		Khyf khyf = khyfDAO.get(id);
		if (khyf == null) {
			throw new CocoException(-1, "运费费用不存在");
		}
		Date date = new Date();
		String ysfs = vo.getYsfs();
		khyf.setYsfs(ysfs);
		khyf.setYsfm(vo.getYsfm());
		khyf.setSzsj(date);
		khyf.setStat(KhyfStat.WC.stat);
		clearYs(khyf);
		// 货柜海运
		if (CodeYsfs.HGHY.key.equals(ysfs)) {
			saveHghy(khyf, vo);
			return;
		}
		// 散货海运
		if (CodeYsfs.SHHY.key.equals(ysfs)) {
			saveShhy(khyf, vo);
			return;
		}
		// 公路运输
		if (CodeYsfs.GLYS.key.equals(ysfs)) {
			saveGlys(khyf, vo);
			return;
		}
		// 铁路运输
		if (CodeYsfs.TLYS.key.equals(ysfs)) {
			saveTlys(khyf, vo);
			return;
		}
		// 出口
		if (CodeYsfs.CK.key.equals(ysfs)) {
			saveCk(khyf, vo);
			return;
		}
		// 自提
		if (CodeYsfs.ZT.key.equals(ysfs)) {
			saveZt(khyf, vo);
		}
		try {
			khyfDAO.update(khyf);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * <p>
	 * 清空运输费用
	 * </p>
	 * 
	 * @param khyf
	 */
	private void clearYs(Khyf khyf) {
		// 出口运输方式。
		// 中日达起始港
		khyf.setQgckdj(null);
		khyf.setQgckno(null);
		khyf.setQgcknm(null);
		// 堆场
		khyf.setDcckdj(null);
		khyf.setDcck(null);
		khyf.setDcckno(null);
		khyf.setDccknm(null);
		// 船运
		khyf.setCyckdj(null);
		khyf.setCyck(null);
		khyf.setCyckno(null);
		khyf.setCycknm(null);

		khyf.setCyckdj1(null);
		khyf.setCyck1(null);
		khyf.setCyckno1(null);
		khyf.setCycknm1(null);
		// 目的港到客户
		khyf.setKhckdj(null);
		khyf.setMdck(null);
		khyf.setKhckno(null);
		khyf.setKhcknm(null);

		// 铁路运输方式。
		// 中日达到终点站
		khyf.setZdtldj(null);
		khyf.setZdtlno(null);
		khyf.setZdtlnm(null);
		// 终点站到客户
		khyf.setKhtldj(null);
		khyf.setKhtlno(null);
		khyf.setKhtlnm(null);
		// 公路运输方式
		// 中日达到客户
		khyf.setKhgldj(null);
		khyf.setKhglno(null);
		khyf.setKhglnm(null);
		// 散货海运
		// 中日达到目的港
		khyf.setQgshdj(null);
		khyf.setQgshno(null);
		khyf.setQgshnm(null);
		// 目的港到客户
		khyf.setKhshdj(null);
		khyf.setKhshno(null);
		khyf.setKhshnm(null);
		// 货柜海运
		// 中日达到起始港
		khyf.setQghhdj(null);
		khyf.setQghhno(null);
		khyf.setQghhnm(null);
		// 堆场
		khyf.setDchy(null);
		khyf.setDchhdj(null);
		khyf.setDchhno(null);
		khyf.setDchhnm(null);

		// 船运到客户
		khyf.setCyhy1(null);
		khyf.setCyhhdj1(null);
		khyf.setCyhhno1(null);
		khyf.setCyhhnm1(null);

		// 船运
		khyf.setCyhy(null);
		khyf.setCyhhdj(null);
		khyf.setCyhhno(null);
		khyf.setCyhhnm(null);
		// 目的港岛客户（RMB/吨）
		khyf.setKhhhdj(null);
		khyf.setKhhhno(null);
		khyf.setKhhhnm(null);
		// 目的港岛客户（RMB/柜）
		khyf.setKhhh1(null);
		khyf.setKhhhdj1(null);
		khyf.setKhhhno1(null);
		khyf.setKhhhnm1(null);
		// 自提单价
		khyf.setZtdj(null);

	}

	/**
	 * <p>
	 * 出口。出口运输方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到起始港
	 * <li>堆场
	 * <li>船运
	 * <li>目的港岛客户
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveCk(Khyf khyf, KhyfVO vo) {
		Khyfdj khyfdj = null;
		// 中日达到起始港（出口）
		Double qgckdj = vo.getQgckdj();
		String qgckno = vo.getQgckno();
		if (qgckno != null && !qgckno.isEmpty()) {
			if (qgckdj == null) {
				throw new CocoException(-1, "中日达到起始港单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getQgcknm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.ck1.value);
			if (qgckdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != qgckdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, qgckdj, qgckno, vo.getQgcknm(), CodeYfdw.rmbD.name, ESection.ck1.value, ESection.ck1.key);
				khyfdjBO.save(khyfdj);
			}
		}
		// 堆场（出口）
		Double dcckdj = vo.getDcckdj();
		Double dcck = vo.getDcck();
		String dcckno = vo.getDcckno();
		if (dcckno != null && !dcckno.isEmpty()) {
			if (dcckdj == null) {
				throw new CocoException(-1, "堆场单价为空");
			}
			if (dcck == null) {
				throw new CocoException(-1, "堆场存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getDccknm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.ck2.value);
			if (dcckdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != dcckdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, dcckdj, dcckno, vo.getDccknm(), CodeYfdw.rmbG.name, ESection.ck2.value, ESection.ck2.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 船运出口USD
		Double cyckdj = vo.getCyckdj();
		Double cyck = vo.getCyck();
		String cyckno = vo.getCyckno();
		if (cyckno != null && !cyckno.isEmpty()) {
			if (cyckdj == null) {
				throw new CocoException(-1, "船运单价为空");
			}
			if (cyck == null) {
				throw new CocoException(-1, "船运存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getCycknm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.usdG.name, ESection.ck3.value);
			if (cyckdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != cyckdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, cyckdj, cyckno, vo.getCycknm(), CodeYfdw.usdG.name, ESection.ck3.value, ESection.ck3.key);
				khyfdjBO.save(khyfdj);
			}
		}
		// 船运出口RMB
		Double cyckdj1 = vo.getCyckdj1();
		Double cyck1 = vo.getCyck1();
		String cyckno1 = vo.getCyckno1();
		if (cyckno1 != null && !cyckno1.isEmpty()) {
			if (cyckdj1 == null) {
				throw new CocoException(-1, "船运单价为空");
			}
			if (cyck1 == null) {
				throw new CocoException(-1, "船运存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getCycknm1(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.ck3.value);
			if (cyckdj1 > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != cyckdj1.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, cyckdj1, cyckno1, vo.getCycknm1(), CodeYfdw.rmbG.name, ESection.ck3.value, ESection.ck3.key);
				khyfdjBO.save(khyfdj);
			}
		}

		khyf.setQgckdj(qgckdj);
		khyf.setQgckno(qgckno);
		khyf.setQgcknm(vo.getQgcknm());

		khyf.setDcckdj(dcckdj);
		khyf.setDcck(dcck);
		khyf.setDcckno(dcckno);
		khyf.setDccknm(vo.getDccknm());

		khyf.setCyckdj(cyckdj);
		khyf.setCyck(cyck);
		khyf.setCyckno(cyckno);
		khyf.setCycknm(vo.getCycknm());

		khyf.setCyckdj1(cyckdj1);
		khyf.setCyck1(cyck1);
		khyf.setCyckno1(cyckno1);
		khyf.setCycknm1(vo.getCycknm1());

		// khyf.setKhckdj(khckdj);
		// khyf.setMdck(mdck);
		// khyf.setKhckno(khckno);
		khyf.setKhcknm(vo.getKhcknm());
	}

	/**
	 * <p>
	 * 铁路运输。铁路运输方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到终点站。
	 * <li>终点站到客户。
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveTlys(Khyf khyf, KhyfVO vo) {
		Khyfdj khyfdj = null;
		// 中日达到终点站（铁路）
		Double zdtldj = vo.getZdtldj();
		String zdtlno = vo.getZdtlno();
		if (zdtlno != null && !zdtlno.isEmpty()) {
			if (zdtldj == null) {
				throw new CocoException(-1, "中日达到终点站单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getZdtlnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.tlys1.value);
			if (zdtldj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != zdtldj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, zdtldj, zdtlno, vo.getZdtlnm(), CodeYfdw.rmbD.name, ESection.tlys1.value, ESection.tlys1.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 终点站到客户（铁路）
		Double khtldj = vo.getKhtldj();
		String khtlno = vo.getKhtlno();
		if (khtlno != null && !khtlno.isEmpty()) {
			if (khtldj == null) {
				throw new CocoException(-1, "终点站到客户单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getKhtlnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.tlys2.value);
			if (khtldj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != khtldj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, khtldj, khtlno, vo.getKhtlnm(), CodeYfdw.rmbD.name, ESection.tlys2.value, ESection.tlys2.key);
				khyfdjBO.save(khyfdj);
			}
		}

		khyf.setZdtldj(zdtldj);
		khyf.setZdtlno(zdtlno);
		khyf.setZdtlnm(vo.getZdtlnm());

		khyf.setKhtldj(khtldj);
		khyf.setKhtlno(khtlno);
		khyf.setKhtlnm(vo.getKhtlnm());
	}

	/**
	 * <p>
	 * 公路运输。公路运输方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到客户。
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveGlys(Khyf khyf, KhyfVO vo) {
		Khyfdj khyfdj = null;
		// 中日达到客户（公路）
		Double khgldj = vo.getKhgldj();
		String khglno = vo.getKhglno();
		if (khglno != null && !khglno.isEmpty()) {
			if (khgldj == null) {
				throw new CocoException(-1, "中日达到客户单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getKhglnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.glys1.value);
			if (khgldj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != khgldj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, khgldj, khglno, vo.getKhglnm(), CodeYfdw.rmbD.name, ESection.glys1.value, ESection.glys1.key);
				khyfdjBO.save(khyfdj);
			}
		}
		khyf.setKhgldj(khgldj);
		khyf.setKhglno(khglno);
		khyf.setKhglnm(vo.getKhglnm());
	}

	/**
	 * <p>
	 * 自提运输。公路运输方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到客户。
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveZt(Khyf khyf, KhyfVO vo) {
		// 中日达到客户（公路）
		Double ztdj = vo.getZtdj();
		Khyfdj khyfdj = khyfdjBO.getUnique(CodeYsfs.ZT.name, khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, ESection.zt1.value);
		if (ztdj != null
				&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != ztdj.doubleValue())) {
			khyfdj = parseKhyfdj(khyf, ztdj, CodeYsfs.ZT.key, CodeYsfs.ZT.name, CodeYfdw.rmbG.name, ESection.zt1.value, ESection.zt1.key);
			khyfdjBO.save(khyfdj);
		}
		khyf.setZtdj(ztdj);
	}

	/**
	 * <p>
	 * 散货海运。散货海运方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到目的港。
	 * <li>目的港岛客户。
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveShhy(Khyf khyf, KhyfVO vo) {
		Khyfdj khyfdj = null;
		// 中日达到目的港
		Double qgshdj = vo.getQgshdj();
		String qgshno = vo.getQgshno();
		if (qgshno != null && !qgshno.isEmpty()) {
			if (qgshdj == null) {
				throw new CocoException(-1, "中日达到目的港单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getQgshnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.shhy1.value);
			if (qgshdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != qgshdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, qgshdj, qgshno, vo.getQgshnm(), CodeYfdw.rmbD.name, ESection.shhy1.value, ESection.shhy1.key);
				khyfdjBO.save(khyfdj);
			}
		}
		// 目的港到客户
		Double khshdj = vo.getKhshdj();
		String khshno = vo.getKhshno();
		if (khshno != null && !khshno.isEmpty()) {
			if (khshdj == null) {
				throw new CocoException(-1, "目的港到客户单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getKhshnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.shhy2.value);
			if (khshdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != khshdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, khshdj, khshno, vo.getKhshnm(), CodeYfdw.rmbD.name, ESection.shhy2.value, ESection.shhy2.key);
				khyfdjBO.save(khyfdj);
			}
		}
		khyf.setQgshdj(qgshdj);
		khyf.setQgshno(qgshno);
		khyf.setQgshnm(vo.getQgshnm());

		khyf.setKhshdj(khshdj);
		khyf.setKhshno(khshno);
		khyf.setKhshnm(vo.getKhshnm());
	}

	/**
	 * <p>
	 * 货柜海运。货柜海运方式包含以下费用：
	 * </p>
	 * <ul>
	 * <li>中日达到起始港。
	 * <li>堆场。
	 * <li>船运。
	 * <li>目的港岛客户。
	 * </ul>
	 * 
	 * @param khyf
	 * @param vo
	 */
	private void saveHghy(Khyf khyf, KhyfVO vo) {
		Khyfdj khyfdj = null;
		// 中日达到起始港口（货柜海运）(RMB/吨)
		Double qghhdj = vo.getQghhdj();
		String qghhno = vo.getQghhno();
		if (qghhno != null && !qghhno.isEmpty()) {
			if (qghhdj == null) {
				throw new CocoException(-1, "中日达到起始港口单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getQghhnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.hghy1.value);
			if (qghhdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != qghhdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, qghhdj, qghhno, vo.getQghhnm(), CodeYfdw.rmbD.name, ESection.hghy1.value, ESection.hghy1.key);
				khyfdjBO.save(khyfdj);
			}
		}
		// 堆场
		Double dchhdj = vo.getDchhdj();
		Double dchy = vo.getDchy();
		String dchhno = vo.getDchhno();
		if (dchhno != null && !dchhno.isEmpty()) {
			if (dchhdj == null) {
				throw new CocoException(-1, "堆场单价为空");
			}
			if (dchy == null) {
				throw new CocoException(-1, "堆场存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getDchhnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.hghy2.value);
			if (dchhdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != dchhdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, dchhdj, dchhno, vo.getDchhnm(), CodeYfdw.rmbG.name, ESection.hghy2.value, ESection.hghy2.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 船运到客户（RMB/柜）
		Double cyhhdj1 = vo.getCyhhdj1();
		Double cyhy1 = vo.getCyhy1();
		String cyhhno1 = vo.getCyhhno1();
		if (cyhhno1 != null && !cyhhno1.isEmpty()) {
			if (cyhhdj1 == null) {
				throw new CocoException(-1, "船运单价为空");
			}
			if (cyhy1 == null) {
				throw new CocoException(-1, "船运存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getCyhhnm1(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.hghy3.value);
			if (cyhhdj1 > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != cyhhdj1.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, cyhhdj1, cyhhno1, vo.getCyhhnm1(), CodeYfdw.rmbG.name, ESection.hghy3.value, ESection.hghy3.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 船运
		Double cyhhdj = vo.getCyhhdj();
		Double cyhy = vo.getCyhy();
		String cyhhno = vo.getCyhhno();
		if (cyhhno != null && !cyhhno.isEmpty()) {
			if (cyhhdj == null) {
				throw new CocoException(-1, "船运单价为空");
			}
			if (cyhy == null) {
				throw new CocoException(-1, "船运存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getCyhhnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.hghy4.value);
			if (cyhhdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != cyhhdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, cyhhdj, cyhhno, vo.getCyhhnm(), CodeYfdw.rmbG.name, ESection.hghy4.value, ESection.hghy4.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 目的港到客户
		Double khhhdj = vo.getKhhhdj();
		String khhhno = vo.getKhhhno();
		if (khhhno != null && !khhhno.isEmpty()) {
			if (khhhdj == null) {
				throw new CocoException(-1, "目的港到客户单价为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getKhhhnm(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbD.name, ESection.hghy5.value);
			if (khhhdj > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != khhhdj.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, khhhdj, khhhno, vo.getKhhhnm(), CodeYfdw.rmbD.name, ESection.hghy5.value, ESection.hghy5.key);
				khyfdjBO.save(khyfdj);
			}
		}

		// 目的港到客户（RMB/柜）
		Double khhhdj1 = vo.getKhhhdj1();
		Double khhh1 = vo.getKhhh1();
		String khhhno1 = vo.getKhhhno1();
		if (khhhno1 != null && !khhhno1.isEmpty()) {
			if (khhhdj1 == null) {
				throw new CocoException(-1, "目的港到客户单价为空");
			}
			if (khhh1 == null) {
				throw new CocoException(-1, "船运存放柜数为空");
			}
			khyfdj = khyfdjBO.getUnique(vo.getKhhhnm1(), khyf.getUser(), khyf.getShno(), khyf.getAddr(), khyf.getYsfs(), YfdjStat.YX.stat, CodeYfdw.rmbG.name, ESection.hghy5.value);
			if (khhhdj1 > 0
					&& (khyfdj == null || khyfdj.getYfdj().doubleValue() != khhhdj1.doubleValue())) {
				khyfdj = parseKhyfdj(khyf, khhhdj1, khhhno1, vo.getKhhhnm1(), CodeYfdw.rmbG.name, ESection.hghy5.value, ESection.hghy5.key);
				khyfdjBO.save(khyfdj);
			}
		}

		khyf.setQghhdj(qghhdj);
		khyf.setQghhno(qghhno);
		khyf.setQghhnm(vo.getQghhnm());

		khyf.setDchy(dchy);
		khyf.setDchhdj(dchhdj);
		khyf.setDchhno(dchhno);
		khyf.setDchhnm(vo.getDchhnm());

		khyf.setCyhy1(cyhy1);
		khyf.setCyhhdj1(cyhhdj1);
		khyf.setCyhhno1(cyhhno1);
		khyf.setCyhhnm1(vo.getCyhhnm1());

		khyf.setCyhy(cyhy);
		khyf.setCyhhdj(cyhhdj);
		khyf.setCyhhno(cyhhno);
		khyf.setCyhhnm(vo.getCyhhnm());

		khyf.setKhhhdj(khhhdj);
		khyf.setKhhhno(khhhno);
		khyf.setKhhhnm(vo.getKhhhnm());

		khyf.setKhhh1(khhh1);
		khyf.setKhhhdj1(khhhdj1);
		khyf.setKhhhno1(khhhno1);
		khyf.setKhhhnm1(vo.getKhhhnm1());
	}

	/**
	 * <p>
	 * 设置客户运费单价
	 * </p>
	 * 
	 * @param khyf
	 * @param yfdj
	 * @param ysgs
	 * @param ysnm
	 * @param djdw
	 * @param ld
	 * @param ldcn
	 * @return Khyfdj
	 */
	private Khyfdj parseKhyfdj(Khyf khyf, Double yfdj, String ysgs,
			String ysnm, String djdw, String ld, String ldcn) {
		Khyfdj khyfdj = new Khyfdj();
		khyfdj.setUser(khyf.getUser());
		khyfdj.setName(khyf.getName());
		khyfdj.setAbbr(khyf.getAbbr());
		khyfdj.setYsfs(khyf.getYsfs());
		khyfdj.setYsfm(khyf.getYsfm());
		khyfdj.setShno(khyf.getShno());
		khyfdj.setShnm(khyf.getShnm());
		khyfdj.setAddr(khyf.getAddr());
		khyfdj.setYsgs(ysgs);
		khyfdj.setYsnm(ysnm);
		khyfdj.setDjdw(djdw);
		khyfdj.setYfdj(yfdj);
		khyfdj.setCrea(khyf.getSzsj());
		khyfdj.setStat(YfdjStat.YX.stat);
		khyfdj.setLd(ld);
		khyfdj.setLdcn(ldcn);
		return khyfdj;
	}

	@Override
	public void saveAll(List<Khyf> entities) {
		khyfDAO.saveAll(entities);
	}

	@Override
	public void update(Khyf entity) {
		khyfDAO.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		khyfDAO.delete(id);
	}

	@Override
	public void query(QEntity<ZrchsjfVO> qentity) {
		sqlDAO.query(qentity);
	}

	@Override
	public Khyf get(Serializable id) {
		return khyfDAO.get(id);
	}

	@Override
	public Khyf getUnique(Date chri, String ysgs, String user, String dhno,
			String line, String shno, String addr) {
		return khyfDAO.getUnique(chri, ysgs, user, dhno, line, shno, addr);
	}

	@Override
	public void saveOrUpdate(Khyf khyf) {
		if (khyf.getId() == null || khyf.getId().isEmpty()) {
			khyf.setId(null);
			khyfDAO.save(khyf);
		}
		else {
			khyfDAO.update(khyf);
		}
	}

	@Override
	public String getForJs(Serializable id) {
		Khyf khyf = khyfDAO.get(id);
		return new Result(khyf).toJsObject();
	}

	@Override
	public ChtjVO tj(Date chriS, Date chriE, String ysgs) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(chriE);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		chriE = calendar.getTime();
		return khyfDAO.tj(chriS, chriE, ysgs);
	}

	@Override
	public Khyfdj getYfdj(String id, String ysgs, String ysfs, String djdw,
			String ld) {
		Khyf khyf = khyfDAO.get(id);
		if (khyf == null) {
			return null;
		}
		return khyfdjBO.getUnique(ysgs, khyf.getUser(), khyf.getShno(), khyf.getAddr(), ysfs, YfdjStat.YX.stat, djdw, ld);
	}

	@Override
	public void fetchRChtj(RChtjVO vo, OutputStream os) {
		Date chriS = vo.getChriS();
		Date chriE = vo.getChriE();
		if (chriE == null) {
			chriE = chriS;
		}
		vo.setChtjs(zxzsBO.queryChtj(chriS, DateUtils.add(chriE, Calendar.DAY_OF_MONTH, 1)));

		ExcelUtils.fillData(rchtjListPath, vo, rchtjListExec, os);

	}

	@Override
	public void fetchCpckd(CkdVO vo, OutputStream os) {
		Date chriS = vo.getChriS();
		Date chriE = vo.getChriE();
		if (chriE == null) {
			chriE = chriS;
		}
		vo.setVos(queryCpckds(chriS, DateUtils.add(chriE, Calendar.DAY_OF_MONTH, 1)));
		ExcelUtils.fillData(cpckdListPath, vo, cpckdListExec, os);
	}

	@Override
	public List<CpckdVO> queryCpckds(Date chriS, Date chriE) {
		Map<Date, List<ChsjVO>> vos1 = zxzsBO.queryChtjByPzno(chriS, chriE);
		Map<Date, List<ChsjVO>> vos2 = zxzsBO.queryChtjByNwai1(chriS, chriE);
		if (vos2 == null || vos2.size() == 0) {
			return null;
		}
		List<CpckdVO> vos = new ArrayList<CpckdVO>();
		Date start = chriS;
		List<ChsjVO> v1 = null;
		List<ChsjVO> v2 = null;
		while (start.before(chriE)) {
			v1 = vos1.get(start);
			v2 = vos2.get(start);
			if (v1 == null || v1.size() == 0) {
				start = DateUtils.add(start, Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			vos.add(parseCpckdVO(start, v1, v2));
			start = DateUtils.add(start, Calendar.DAY_OF_MONTH, 1);
		}
		return vos;
	}

	@Override
	public List<CondVO> queryConds(Integer yearS, Integer yearE,
			Integer monthS, Integer monthE) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date chriS = null, chriE = null;
		try {
			calendar.set(yearS, monthS - 1, 1);
			chriS = sdf.parse(sdf.format(calendar.getTime()));
			calendar.set(yearE, monthE - 1, 1);
			chriE = sdf.parse(sdf.format(calendar.getTime()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return zxzsBO.queryCond(chriS, chriE);
	}

	@Override
	public CondVO getCond(Integer yearS, Integer yearE, Integer monthS,
			Integer monthE) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date chriS = null, chriE = null;
		try {
			calendar.set(yearS, monthS - 1, 1);
			chriS = sdf.parse(sdf.format(calendar.getTime()));
			calendar.set(yearE, monthE - 1, 1);
			chriE = sdf.parse(sdf.format(calendar.getTime()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return zxzsBO.getCond(chriS, chriE);
	}

	/**
	 * <p>
	 * 根据出货汇总信息，设置成品出库单记录
	 * </p>
	 * 
	 * @param chqi
	 * @param v1
	 * @param v2
	 * @return CpckdVO
	 */
	private CpckdVO parseCpckdVO(Date chqi, List<ChsjVO> v1, List<ChsjVO> v2) {
		CpckdVO vo = new CpckdVO();
		vo.setChqi(chqi);
		vo.setShnos(zxzsBO.getZxnos(chqi));
		double hj1 = 0, hj2 = 0;
		for (ChsjVO chsjVO : v1) {
			hj1 += chsjVO.getChzl();
			if (Code118.sheet.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh1.key.equals(chsjVO.getSpbh())) {
				vo.setBsl1(chsjVO.getChzl());
			}
			else if (Code118.sheet.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh2.key.equals(chsjVO.getSpbh())) {
				vo.setBsl2(chsjVO.getChzl());
			}
			else if (Code118.sheet.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh3.key.equals(chsjVO.getSpbh())) {
				vo.setBsl3(chsjVO.getChzl());
			}
			else if (Code118.coil.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh1.key.equals(chsjVO.getSpbh())) {
				vo.setJsl1(chsjVO.getChzl());
			}
			else if (Code118.coil.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh2.key.equals(chsjVO.getSpbh())) {
				vo.setJsl2(chsjVO.getChzl());
			}
			else if (Code118.coil.key.equals(chsjVO.getPzno())
					&& CodeSpbh.spbh3.key.equals(chsjVO.getSpbh())) {
				vo.setJsl3(chsjVO.getChzl());
			}
		}
		vo.setHj(NumberUtils.format(hj1, 3));
		if (v2 != null && v2.size() > 0) {
			for (ChsjVO chsjVO : v2) {
				if (CodeNwx.wai.key.equals(chsjVO.getNwai())) {
					hj2 += chsjVO.getChzl();
					if (Code118.sheet.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh1.key.equals(chsjVO.getSpbh())) {
						vo.setCbsl1(chsjVO.getChzl());
					}
					else if (Code118.sheet.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh2.key.equals(chsjVO.getSpbh())) {
						vo.setCbsl2(chsjVO.getChzl());
					}
					else if (Code118.sheet.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh3.key.equals(chsjVO.getSpbh())) {
						vo.setCbsl3(chsjVO.getChzl());
					}
					else if (Code118.coil.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh1.key.equals(chsjVO.getSpbh())) {
						vo.setCjsl1(chsjVO.getChzl());
					}
					else if (Code118.coil.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh2.key.equals(chsjVO.getSpbh())) {
						vo.setCjsl2(chsjVO.getChzl());
					}
					else if (Code118.coil.key.equals(chsjVO.getPzno())
							&& CodeSpbh.spbh3.key.equals(chsjVO.getSpbh())) {
						vo.setCjsl3(chsjVO.getChzl());
					}
				}
			}
		}
		vo.setNhj(NumberUtils.format(hj1 - hj2, 3));
		return vo;
	}

	@Override
	public long getNum(Date chriS, Date chriE) {
		return zxzsBO.getNum(chriS, chriE);
	}

	@Override
	public void delete() {
		khyfDAO.delete();
	}

	/**
	 * @return the khyfDAO
	 */
	public IKhyfDAO getKhyfDAO() {
		return khyfDAO;
	}

	/**
	 * @param khyfDAO
	 *            the khyfDAO to set
	 */
	public void setKhyfDAO(IKhyfDAO khyfDAO) {
		this.khyfDAO = khyfDAO;
	}

	/**
	 * @return the khyfdjBO
	 */
	public IKhyfdjBO getKhyfdjBO() {
		return khyfdjBO;
	}

	/**
	 * @param khyfdjBO
	 *            the khyfdjBO to set
	 */
	public void setKhyfdjBO(IKhyfdjBO khyfdjBO) {
		this.khyfdjBO = khyfdjBO;
	}

	public String getRchtjListPath() {
		return rchtjListPath;
	}

	public void setRchtjListPath(String rchtjListPath) {
		this.rchtjListPath = rchtjListPath;
	}

	public ExcelDataExecuter<RChtjVO> getRchtjListExec() {
		return rchtjListExec;
	}

	public void setRchtjListExec(ExcelDataExecuter<RChtjVO> rchtjListExec) {
		this.rchtjListExec = rchtjListExec;
	}

	public IZxzsBO getZxzsBO() {
		return zxzsBO;
	}

	public void setZxzsBO(IZxzsBO zxzsBO) {
		this.zxzsBO = zxzsBO;
	}

	public ISqlDAO getSqlDAO() {
		return sqlDAO;
	}

	public void setSqlDAO(ISqlDAO sqlDAO) {
		this.sqlDAO = sqlDAO;
	}

	public String getCpckdListPath() {
		return cpckdListPath;
	}

	public void setCpckdListPath(String cpckdListPath) {
		this.cpckdListPath = cpckdListPath;
	}

	public ExcelbookDataExecuter<CkdVO> getCpckdListExec() {
		return cpckdListExec;
	}

	public void setCpckdListExec(ExcelbookDataExecuter<CkdVO> cpckdListExec) {
		this.cpckdListExec = cpckdListExec;
	}

}
