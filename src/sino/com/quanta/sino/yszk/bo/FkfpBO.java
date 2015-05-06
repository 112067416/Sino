package com.quanta.sino.yszk.bo;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.CodeSpbh;
import com.quanta.sino.cmn.constants.EFppz;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yszk.dao.api.ICzglDAO;
import com.quanta.sino.yszk.dao.api.IFkfpDAO;
import com.quanta.sino.yszk.vo.ChsjVO;
import com.quanta.sino.yszk.vo.FpVO;
import com.quanta.sino.yszk.vo.FpdhVO;
import com.quanta.sino.yszk.vo.FpmxVO;
import com.quanta.sino.yszk.vo.HjVO;
import com.quanta.sino.yszk.vo.KhfkVO;
import com.quanta.sino.yszk.vo.YszkQE;

/**
 * <p>
 * 应收账款业务层接口实现类
 * </p>
 * <p>
 * create: 2011-7-3 下午02:36:16
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FkfpBO implements IFkfpBO {

	/**
	 * 付款发票数据访问层接口
	 */
	private IFkfpDAO dao;

	/**
	 * 付款发票冲帐数据访问层接口
	 */
	private ICzglDAO czglDAO;

	/**
	 * 引入代码业务层bo
	 */
	private ICodeBO codeBo;

	/**
	 * 引入订货管理业务层bo
	 */
	private IDhBO dhBo;

	/**
	 * 引入用户主文件业务层bo
	 */
	private IYongBO yongBo;

	/**
	 * 引入客户付款业务层bo
	 */
	private IKhfkBO khBo;

	/**
	 * 付款发票记录路径
	 */
	private String fkfpPath;

	/**
	 * 付款发票记录处理接口
	 */
	private ExcelbookDataExecuter<YszkQE> fkfpExec;

	private static String E = "E";

	@Override
	public void save(Fkfp entity) {
		dao.save(entity);
	}

	@Override
	public void update(Fkfp entity) {
		dao.update(entity);
	}

	@Override
	public String delete(Serializable id) {
		Fkfp fkfp = dao.get(id);
		if (fkfp == null) {
			return new Result(-1, "删除的付款发票信息不存在").toString();
		}
		if (!FpStat.CS.key.equals(fkfp.getStat())) {
			return new Result(-1, "付款发票状态为" + FpStat.get(fkfp.getStat()).name
					+ "，不能进行删除操作").toString();
		}
		dao.delete(id);
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<Fkfp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryCzgl(QEntity<Czgl> qentity) {
		czglDAO.query(qentity);
		if (qentity.getDatas().size() > 0) {
			for (Czgl czgl : qentity.getDatas()) {
				czgl.getFkfp().getFpymc();
				czgl.getFkfp().getDhno();
				czgl.getFkfp().getLine();
				czgl.getFkfp().getGgno();
				czgl.getFkfp().getFudw();
				czgl.getFkfp().getFufm();
				czgl.getFkfp().getFuzm();
				czgl.getFkfp().getHouu();
				czgl.getFkfp().getKuan();
				czgl.getFkfp().getCang();
				czgl.getFkfp().getKfzl();
				czgl.getFkfp().getKpdj();
				czgl.getFkfp().getZlzr();
				czgl.getFkfp().getLxzr();
				czgl.getFkfp().getTzje();
				czgl.getFkfp().getFpje();
				czgl.getFkfp().getFpno();
				czgl.getFkfp().getFpri();
				czgl.getFkfp().getSkri();
				czgl.getFkfp().getFkje();
				czgl.getFkfp().getWsyk();
			}
		}
	}

	@Override
	public String getForJs(Serializable id) {
		Fkfp entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该信息").toString();
		}
		if (!FpStat.CS.key.equals(entity.getStat())) {
			return new Result(-1, "付款发票状态为" + FpStat.get(entity.getStat()).name
					+ ",不能进行修改操作").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public Fkfp get(Serializable id) {
		Fkfp entity = dao.get(id);
		return entity;
	}

	@Override
	public void saveFp(FpVO fpVO, User user) {
		Fkfp fkfp = null;
		Date date = new Date();
		double fpje = 0d;
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		double kfzl = 0d;
		double kpdj = 0d;
		double lxzr = 0d;
		double zlzr = 0d;
		for (FpmxVO vo : fpVO.getItems()) {
			fkfp = dao.get(vo.getId());
			if (fkfp == null) continue;
			if (!FpStat.CS.key.equals(fkfp.getStat())) continue;
			// 修改时间
			fkfp.setUpda(date);
			// 吨数
			kfzl = fkfp.getKfzl() != null ? fkfp.getKfzl() : 0d;
			// 发票单价
			kpdj = vo.getKpdj() != null ? vo.getKpdj() : 0d;
			fkfp.setKpdj(kpdj);
			// 利息折扣
			lxzr = vo.getLxzr() != null ? vo.getLxzr() : 0d;
			fkfp.setLxzr(lxzr);
			// 质量折扣
			zlzr = vo.getZlzr() != null ? vo.getZlzr() : 0d;
			fkfp.setZlzr(zlzr);
			// 计算发票金额（算出总价）
			// BigDecimal $kfzl = new BigDecimal(String.valueOf(kfzl));
			// BigDecimal $kpdj = new BigDecimal(String.valueOf(kpdj));
			fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
			fkfp.setFpje(fpje);
			fkje = fkfp.getFkje() != null ? fkfp.getFkje() : 0d;
			tzje = fkfp.getTzje() != null ? fkfp.getTzje() : 0d;
			// 计算未收余款为：发票金额-收款金额-调整金额
			wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
			fkfp.setWsyk(wsyk);
			// 发票号
			fkfp.setFpno(vo.getFpno());
			// 担当者代码
			fkfp.setDdno(user.getNo());
			// 担当者名称
			fkfp.setDdnm(user.getName());
			dao.update(fkfp);
		}
	}

	@Override
	public void editFp(Fkfp entity) {
		double kfzl = entity.getKfzl() != null ? entity.getKfzl() : 0d;
		double kpdj = entity.getKpdj() != null ? entity.getKpdj() : 0d;
		double lxzr = entity.getLxzr() != null ? entity.getLxzr() : 0d;
		double zlzr = entity.getZlzr() != null ? entity.getZlzr() : 0d;
		double fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		String fpymc = entity.getFpymc();
		// 修改发票信息
		Fkfp $entity = dao.get(entity.getId());
		if ($entity == null) {
			throw new CocoException(-1, "付款发票信息不存在,请联系管理员");
		}
		$entity.setFpri(entity.getFpri());
		$entity.setFpymc(fpymc);
		$entity.setKfzl(kfzl);
		$entity.setFpno(entity.getFpno());
		$entity.setKpdj(kpdj);
		$entity.setLxzr(lxzr);
		$entity.setZlzr(zlzr);
		$entity.setFpje(fpje);
		fkje = $entity.getFkje() != null ? $entity.getFkje() : 0d;
		tzje = $entity.getTzje() != null ? $entity.getTzje() : 0d;
		// 计算未收余款为：发票金额-收款金额-调整金额
		wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
		$entity.setWsyk(wsyk);
		$entity.setThqf(entity.getThqf());
		dao.update($entity);
	}

	@Override
	public String editHzfp(Fkfp entity) {
		String id = entity.getId();
		double kfzl = entity.getKfzl() != null ? entity.getKfzl() : 0d;
		double kpdj = entity.getKpdj() != null ? entity.getKpdj() : 0d;
		double lxzr = entity.getLxzr() != null ? entity.getLxzr() : 0d;
		double zlzr = entity.getZlzr() != null ? entity.getZlzr() : 0d;
		double fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		String fpymc = entity.getFpymc();
		Fkfp $entity = null;
		if (id != null && !id.isEmpty()) {
			// 修改发票信息
			$entity = dao.get(id);
			if ($entity == null) {
				return new Result(-1, "付款发票信息不存在,请联系管理员").toString();
			}
			$entity.setCond(entity.getCond());
			$entity.setCdnm(entity.getCdnm());
			$entity.setChri(entity.getChri());
			$entity.setFpri(entity.getFpri());
			$entity.setFpymc(fpymc);
			$entity.setKfzl(kfzl);
			$entity.setFpno(entity.getFpno());
			$entity.setKpdj(kpdj);
			$entity.setLxzr(lxzr);
			$entity.setZlzr(zlzr);
			$entity.setFpje(fpje);
			fkje = $entity.getFkje() != null ? $entity.getFkje() : 0d;
			tzje = $entity.getTzje() != null ? $entity.getTzje() : 0d;
			// 计算未收余款为：发票金额-收款金额-调整金额
			wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
			$entity.setWsyk(wsyk);
			$entity.setThqf(entity.getThqf());
			dao.update($entity);
		}
		else {
			CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_BFHL, "1");
			entity.setBxfl(codeItem != null ? Double.valueOf(codeItem.getValue())
					: null);
			entity.setFpje(fpje);
			entity.setFkje(0d);
			// 计算未收余款为：发票金额-收款金额-调整金额
			wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
			entity.setWsyk(wsyk);

			entity.setFppz(EFppz.hz.key);
			entity.setStat(FpStat.CS.key);
			dao.save(entity);
		}
		return Result.SUCCESS;
	}

	@Override
	public String editHzfp(String[] ids) {
		Fkfp entity = null;
		Fkfp $entity = null;
		double fpje = 0d;
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		double kfzl = 0d;
		double kpdj = 0d;
		double lxzr = 0d;
		double zlzr = 0d;
		for (String id : ids) {
			entity = dao.get(id);
			if (entity == null) continue;
			$entity = new Fkfp();
			ReflectUtils.copy($entity, entity, false);
			$entity.setId(null);
			$entity.setFppz(EFppz.hz.key);
			$entity.setStat(FpStat.CS.key);

			// 吨数
			kfzl = $entity.getKfzl() != null ? -$entity.getKfzl() : 0d;
			$entity.setKfzl(kfzl);
			// 发票单价
			kpdj = $entity.getKpdj() != null ? $entity.getKpdj() : 0d;
			// 利息折扣
			lxzr = 0d;
			$entity.setLxzr(lxzr);
			// 质量折扣
			zlzr = 0d;
			$entity.setZlzr(zlzr);
			// 计算发票金额（算出总价）
			fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
			$entity.setFpje(fpje);
			fkje = 0d;
			$entity.setFkje(fkje);
			tzje = 0d;
			$entity.setTzje(tzje);
			// 计算未收余款为：发票金额-收款金额-调整金额
			wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
			$entity.setWsyk(wsyk);

			dao.save($entity);
		}
		return Result.SUCCESS;
	}

	@Override
	public String editLzfp(String[] ids) {
		Fkfp entity = null;
		Fkfp $entity = null;
		double fpje = 0d;
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		double kfzl = 0d;
		double kpdj = 0d;
		double lxzr = 0d;
		double zlzr = 0d;
		for (String id : ids) {
			entity = dao.get(id);
			if (entity == null) continue;
			$entity = new Fkfp();
			ReflectUtils.copy($entity, entity, false);
			$entity.setId(null);
			$entity.setFppz(EFppz.hz.key);
			$entity.setStat(FpStat.CS.key);

			// 吨数
			kfzl = $entity.getKfzl() != null ? $entity.getKfzl() : 0d;
			$entity.setKfzl(kfzl);
			// 发票单价
			kpdj = $entity.getKpdj() != null ? $entity.getKpdj() : 0d;
			// 利息折扣
			lxzr = 0d;
			$entity.setLxzr(lxzr);
			// 质量折扣
			zlzr = 0d;
			$entity.setZlzr(zlzr);
			// 计算发票金额（算出总价）
			fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
			$entity.setFpje(fpje);
			fkje = 0d;
			$entity.setFkje(fkje);
			tzje = 0d;
			$entity.setTzje(tzje);
			// 计算未收余款为：发票金额-收款金额-调整金额
			wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
			$entity.setWsyk(wsyk);

			dao.save($entity);
		}
		return Result.SUCCESS;
	}

	@Override
	public Khfk getKhfk(String id) {
		return khBo.get(id);
	}

	@Override
	public String doCz(KhfkVO vo) {
		Khfk khfk = null;
		List<Fkfp> fkfps = null;
		String[] stats = { FpStat.CS.key, FpStat.WJS.key };
		Czgl czgl = null;
		Date date = new Date();
		for (String pid : vo.getKhfkIds()) {
			khfk = khBo.get(pid);
			if (khfk == null) {
				throw new CocoException(-1, "该客户付款信息已丢失,请联系管理员");
			}
			if (FpStat.YJS.key.equals(khfk.getStat())) {
				throw new CocoException(-1, "该客户付款信息已冲帐,请联系管理员");
			}
			// 冲帐金额（注：客户冲帐金额必须小于或等于客户的付款余额）
			double fkye = khfk.getFkye();
			double $fkye = khfk.getFkye();

			fkfps = dao.find(vo.getIds(), stats);
			if (fkfps == null || fkfps.size() == 0) break;

			//
			double fkje = 0d, wsyk = 0d;
			double hkje = 0d;
			double $hkje = khfk.getHkje() != null ? khfk.getHkje() : 0d;
			for (Fkfp fkfp : fkfps) {
				// 放在for里面是以便前一次统计付款余额后再对剩下的付款余额进行判断
				if (fkye == 0.0) break;
				// 发票的收款金额
				fkje = fkfp.getFkje() != null ? fkfp.getFkje() : 0d;
				// 发票的未收余款
				wsyk = fkfp.getWsyk() != null ? fkfp.getWsyk() : 0d;
				// 定义冲账关联表
				czgl = new Czgl();
				// 获取付款发票信息
				czgl.setFkfp(fkfp);
				// 获取客户付款信息
				czgl.setKhfk(khfk);
				// 记录每次的付款日期
				czgl.setSkri(date);

				// 若未收余款大于或等于发票金额
				if (Math.abs(wsyk) <= Math.abs(fkye)) {
					// 则收款金额就是未收余款（初始时是等于发票金额）还需要加上原有的收款金额
					fkfp.setFkje(NumberUtils.format(NumberUtils.add(fkje, wsyk).doubleValue(), 2));
					// 获取剩下的付款金额，以便用剩下的付款金额去对下一个发票的冲账
					fkye = NumberUtils.format(NumberUtils.subtract(fkye, wsyk).doubleValue(), 2);

					// 未收余款为零
					fkfp.setWsyk(0.0);
					// 状态为冲账结束
					fkfp.setStat(FpStat.YJS.key);
					// 记录冲账日期
					fkfp.setCzri(date);
					// 记录每次收款金额
					czgl.setFkje(wsyk);
					// 久了每次剩下的未收余额
					czgl.setWsyk(0.0);
					hkje = NumberUtils.add(hkje, wsyk).doubleValue();
				}
				// 否则未收余款大于付款金额
				else {
					// 未收余款为原未收余款减去付款金额
					fkfp.setWsyk(NumberUtils.format(NumberUtils.subtract(wsyk, fkye).doubleValue(), 2));
					// 收款金额为原收款金额加上付款金额（或者说剩下的付款金额）
					fkfp.setFkje(NumberUtils.format(NumberUtils.add(fkje, fkye).doubleValue(), 2));
					// 状态为冲账未结束
					fkfp.setStat(FpStat.WJS.key);
					// 记录每次收款金额
					czgl.setFkje(fkye);
					// 记录每次剩下的未收余额
					czgl.setWsyk(NumberUtils.format(NumberUtils.subtract(wsyk, fkye).doubleValue(), 2));
					hkje = NumberUtils.add(hkje, fkye).doubleValue();
					fkye = 0.0;
				}
				// 记录收款日期
				fkfp.setSkri(date);
				// 记录客户id
				fkfp.setPid(pid);
				dao.update(fkfp);
				// 保存冲账关联表
				czglDAO.save(czgl);
			}
			// 获取货款余额
			khfk.setHkje(NumberUtils.format(NumberUtils.add($hkje, hkje).doubleValue(), 2));
			// 付款余额为付款金额-货款金额
			khfk.setFkye(NumberUtils.format(NumberUtils.subtract($fkye, hkje).doubleValue(), 2));
			if (khfk.getFkye() == 0.0) {
				// 若付款余额为零，则状态为冲账结束
				khfk.setStat(FpStat.YJS.key);
			}
			else {
				// 若付款余额大于零，则状态为冲账未结束
				khfk.setStat(FpStat.WJS.key);
			}
			khBo.updateByCz(khfk);
		}
		return Result.SUCCESS;
	}

	@Override
	public Fkfp get(String dhno, String line, Date chri, String fppz) {
		return dao.get(dhno, line, chri, fppz);
	}

	@Override
	public void saveOrUpdate(Fkfp fkfp) {
		if (fkfp.getId() == null || fkfp.getId().isEmpty()) {
			fkfp.setId(null);
			dao.save(fkfp);
		}
		else {
			dao.update(fkfp);
		}
	}

	@Override
	public Double getWsyk(String user) {
		return dao.getWsyk(user);
	}

	@Override
	public void outFpDatas(Date chqiS, Date chqiE) {
		dao.delete(chqiS, chqiE);
		List<Fkfp> fkfps = null;
		Fkfp fkfp = null;
		CodeItem codeItem = null;
		DhuoTp dhuoTp = null;
		String dhno = null, line = null;
		Double chzl = null, htdj = null, fpje = null;
		List<ChsjVO> vos = null;
		while (chqiS.before(chqiE)) {
			vos = dao.findFpVOS(chqiS, DateUtils.add(chqiS, Calendar.DAY_OF_MONTH, 1));
			chqiS = DateUtils.add(chqiS, Calendar.DAY_OF_MONTH, 1);
			if (vos.size() == 0) continue;
			fkfps = new ArrayList<Fkfp>();
			for (ChsjVO vo : vos) {
				fkfp = new Fkfp();
				dhno = vo.getDhno();
				line = vo.getLine();
				if ((dhuoTp = dhBo.get(new DhuoTpPk(dhno, line))) == null) continue;
				chzl = vo.getChzl() != null ? vo.getChzl() : 0;
				htdj = dhuoTp.getHtdj() != null ? dhuoTp.getHtdj() : 0;
				fpje = NumberUtils.format(chzl * htdj, 2);

				fkfp.setChri(vo.getChri());
				fkfp.setDhno(dhno);
				fkfp.setLine(line);
				fkfp.setGgno(vo.getGgno());
				fkfp.setHouu(vo.getHouu());
				fkfp.setKuan(vo.getKuan());
				fkfp.setCang(vo.getCang());
				fkfp.setFudw(vo.getFudw());
				fkfp.setFuzm(vo.getFuzm());
				fkfp.setFufm(vo.getFufm());
				fkfp.setUser(vo.getUser());
				fkfp.setAbbr(vo.getAbbr());
				fkfp.setName(vo.getName());
				// fkfp.setFpdm(vo.getUser());
				// fkfp.setFpnm(vo.getAbbr());
				fkfp.setFpymc(vo.getName());
				fkfp.setKfzl(chzl);
				fkfp.setKpdj(htdj);
				fkfp.setFpje(fpje);
				fkfp.setFkje(0d);
				fkfp.setWsyk(fpje);
				fkfp.setHtdj(htdj);

				fkfp.setSpbh(vo.getSpbh());
				fkfp.setNwai(vo.getNwai());
				fkfp.setDeng(vo.getDeng());
				fkfp.setPz(vo.getPz());
				fkfp.setStat(FpStat.CS.key);
				codeItem = codeBo.getCodeItem(CmnConstants.CODE_BFHL, "1");
				fkfp.setBxfl(Double.valueOf(codeItem.getValue()));

				fkfps.add(fkfp);
			}
			dao.saveAll(fkfps);
			fkfps = null;
			fkfp = null;
			codeItem = null;
			dhuoTp = null;
			dhno = null;
			line = null;
			chzl = null;
			htdj = null;
			fpje = null;
			vos = null;
		}
	}

	@Override
	public HjVO getHj(Date chqiS, Date chqiE, String fpymc, String nwai,
			String deng, String pz, String[] stats, Double houuS, Double houuE,
			String fppz, String fpno) {
		String $stats = null;
		if (stats != null && stats.length > 0) {
			$stats = "'" + StringUtils.join(stats, "','") + "'";
		}
		return dao.getHj(chqiS, chqiE, fpymc, nwai, deng, pz, $stats, houuS, houuE, fppz, fpno);
	}

	@Override
	public int getPageSize(String khfkId, Integer size) {
		Long count = czglDAO.countByKhfk(khfkId);
		if (count == null || count.longValue() == 0) {
			return 0;
		}
		int pageSize = (int) count.intValue() / size;
		if (count % size != 0) {
			pageSize++;
		}
		return pageSize;
	}

	@Override
	public void fetchFkfp(YszkQE qentity, OutputStream os) {
		String[] fppzs = { EFppz.zp.key, EFppz.hz.key };
		String fpymc = qentity.getFpymc();
		if (fpymc != null) {
			try {
				fpymc = java.net.URLDecoder.decode(fpymc, "UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				fpymc = null;
			}
			qentity.setFpymc(fpymc);
		}
		qentity.setSize(-1);
		qentity.setFppzs(fppzs);
		qentity.setFpje(0d);
		qentity.setOrderBys("chri asc, fpymc asc, fpno asc, dhno asc, line asc, id desc");
		if (qentity.getNwai() != null
				&& CodeNwx.jeck.key.equals(qentity.getNwai())) {
			qentity.setNwai(CodeNwx.NX);
			qentity.setDhno("E");
		}
		else if (qentity.getNwai() != null
				&& CodeNwx.nei.key.equals(qentity.getNwai())) {
			qentity.setNwai(CodeNwx.NX);
			qentity.setNotLikeDhno("E");
		}
		dao.query(qentity);
		ExcelUtils.fillData(fkfpPath, qentity, fkfpExec, os);
	}

	@Override
	public String saveDivied(Fkfp fkfp) {
		Date date = new Date();
		double fpje = 0d;
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		double kfzl = 0d;
		double kpdj = 0d;
		double lxzr = 0d;
		double zlzr = 0d;
		Fkfp $fkfp = dao.get(fkfp.getId());
		if (!FpStat.CS.key.equals($fkfp.getStat())) {
			return new Result(-1, "付款发票状态为" + FpStat.get($fkfp.getStat()).name
					+ ",不能进行分解操作").toString();
		}
		String fpymc = fkfp.getFpymc();
		// 吨数
		kfzl = $fkfp.getKfzl() != null ? $fkfp.getKfzl() : 0d;
		double $kfzl = fkfp.getKfzl() != null ? fkfp.getKfzl() : 0d;
		if ($kfzl >= kfzl) {
			return new Result(-1, "分解发票重量不能大于原发票重量").toString();
		}
		kfzl = NumberUtils.subtract(kfzl, $kfzl).doubleValue();
		// 发票单价
		kpdj = $fkfp.getKpdj() != null ? $fkfp.getKpdj() : 0d;
		// 利息折扣
		lxzr = $fkfp.getLxzr() != null ? $fkfp.getLxzr() : 0d;
		// 质量折扣
		zlzr = $fkfp.getZlzr() != null ? $fkfp.getZlzr() : 0d;
		// 计算发票金额（算出总价）
		// BigDecimal $$kfzl = new BigDecimal(String.valueOf(kfzl));
		// BigDecimal $$kpdj = new BigDecimal(String.valueOf(kpdj));
		// fpje = NumberUtils.format($$kfzl.multiply($$kpdj).doubleValue() -
		// lxzr
		// - zlzr, 2);
		fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
		// fpje = NumberUtils.format(kfzl * kpdj - lxzr - zlzr, 2);
		fkje = $fkfp.getFkje() != null ? $fkfp.getFkje() : 0d;
		tzje = $fkfp.getTzje() != null ? $fkfp.getTzje() : 0d;
		// 计算未收余款为：发票金额-收款金额-调整金额
		wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
		$fkfp.setKfzl(NumberUtils.format(kfzl, 3));
		$fkfp.setKpdj(kpdj);
		$fkfp.setLxzr(lxzr);
		$fkfp.setZlzr(zlzr);
		$fkfp.setFpje(fpje);
		$fkfp.setWsyk(wsyk);
		$fkfp.setUpda(date);
		dao.update($fkfp);

		// 发票单价
		kpdj = fkfp.getKpdj() != null ? fkfp.getKpdj() : 0d;
		// 利息折扣
		lxzr = fkfp.getLxzr() != null ? fkfp.getLxzr() : 0d;
		// 质量折扣
		zlzr = fkfp.getZlzr() != null ? fkfp.getZlzr() : 0d;
		// 计算发票金额（算出总价）
		// BigDecimal $$$kfzl = new BigDecimal(String.valueOf($kfzl));
		// fpje = NumberUtils.format($$$kfzl.multiply($$kpdj).doubleValue() -
		// lxzr
		// - zlzr, 2);
		fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply($kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
		fkje = fkfp.getFkje() != null ? fkfp.getFkje() : 0d;
		tzje = fkfp.getTzje() != null ? fkfp.getTzje() : 0d;
		// 计算未收余款为：发票金额-收款金额-调整金额
		wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
		String fpno = fkfp.getFpno();
		ReflectUtils.copy(fkfp, $fkfp, false);
		fkfp.setId(null);
		fkfp.setKfzl($kfzl);
		fkfp.setKpdj(kpdj);
		fkfp.setLxzr(lxzr);
		fkfp.setZlzr(zlzr);
		fkfp.setFpje(fpje);
		fkfp.setWsyk(wsyk);
		fkfp.setFpno(fpno);
		fkfp.setFpymc(fpymc);
		fkfp.setThqf($fkfp.getThqf());
		dao.save(fkfp);
		return Result.SUCCESS;
	}

	@Override
	public void setFpno(List<String> ids, String fpno) {
		dao.setFpno(ids, fpno);
	}

	@Override
	public void setFpcs(String[] ids, String fpno, String fpymc, Double kpdj) {
		Fkfp fkfp = null;
		double fpje = 0d;
		double fkje = 0d;
		double tzje = 0d;
		double wsyk = 0d;
		double kfzl = 0d;
		double lxzr = 0d;
		double zlzr = 0d;
		for (String id : ids) {
			fkfp = dao.get(id);
			if (fkfp == null || !FpStat.CS.key.equals(fkfp.getStat())) continue;
			if (fpno != null && !fpno.isEmpty()) {
				fkfp.setFpno(fpno);
			}
			if (fpymc != null && !fpymc.isEmpty()) {
				fkfp.setFpymc(fpymc);
			}
			if (kpdj != null && kpdj.doubleValue() > 0) {
				// 吨数
				kfzl = fkfp.getKfzl() != null ? fkfp.getKfzl() : 0d;
				// 利息折扣
				lxzr = fkfp.getLxzr() != null ? fkfp.getLxzr() : 0d;
				// 质量折扣
				zlzr = fkfp.getZlzr() != null ? fkfp.getZlzr() : 0d;
				// 计算发票金额（算出总价）
				// BigDecimal $kfzl = new BigDecimal(String.valueOf(kfzl));
				// BigDecimal $kpdj = new BigDecimal(String.valueOf(kpdj));
				// fpje = NumberUtils.format($kfzl.multiply($kpdj).doubleValue()
				// - lxzr - zlzr, 2);
				fpje = NumberUtils.format(NumberUtils.subtract(NumberUtils.multiply(kfzl, kpdj), lxzr, zlzr).doubleValue(), 2);
				fkfp.setFpje(fpje);
				fkje = fkfp.getFkje() != null ? fkfp.getFkje() : 0d;
				tzje = fkfp.getTzje() != null ? fkfp.getTzje() : 0d;
				// 计算未收余款为：发票金额-收款金额-调整金额
				wsyk = NumberUtils.format(NumberUtils.subtract(fpje, fkje, tzje).doubleValue(), 2);
				fkfp.setWsyk(wsyk);
				fkfp.setKpdj(kpdj);
			}
			dao.update(fkfp);
		}
	}

	@Override
	public String getFpdhInfo(String dhno, String line) {
		DhuoTp dhuoTp = dhBo.get(dhno, line, null);
		// 若获取的订货DB信息为空，这行号与订货no不对应
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		// 否则传递订货DB相关信息到界面中
		FpdhVO fpdhVO = new FpdhVO();

		fpdhVO.setAbbr(dhuoTp.getAbbr());
		fpdhVO.setUser(dhuoTp.getUser());
		fpdhVO.setName(dhuoTp.getName());
		fpdhVO.setFpymc(dhuoTp.getName());

		fpdhVO.setCang(dhuoTp.getCang());
		fpdhVO.setHouu(dhuoTp.getHouu());
		fpdhVO.setKuan(dhuoTp.getKuan());
		fpdhVO.setFudw(dhuoTp.getFudw());
		fpdhVO.setFuzm(dhuoTp.getFuzm());
		fpdhVO.setFufm(dhuoTp.getFufm());
		fpdhVO.setYuny(dhuoTp.getYuny());
		fpdhVO.setGgno(dhuoTp.getGgno());

		fpdhVO.setJhqi(dhuoTp.getJhqi());
		fpdhVO.setHtdj(dhuoTp.getHtdj());
		fpdhVO.setKpdj(dhuoTp.getHtdj());
		fpdhVO.setCond(dhuoTp.getCond());
		fpdhVO.setCdnm(dhuoTp.getCdnm());
		fpdhVO.setThqf(E.equalsIgnoreCase(dhuoTp.getDhno().substring(0, 1)) ? Code013.usd.key
				: Code013.rmb.key);

		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_BFHL, "1");
		fpdhVO.setBxfl(codeItem != null ? Double.valueOf(codeItem.getValue())
				: null);
		fpdhVO.setSpbh(CodeSpbh.getByHouu(dhuoTp.getHouu()));
		fpdhVO.setNwai(dhuoTp.getNwai());
		String pzno = dhuoTp.getPzno();
		if (pzno != null && !pzno.isEmpty()) {
			fpdhVO.setPz(pzno.substring(0, 1));
			fpdhVO.setDeng(pzno.substring(1));
		}

		YongMp yongMp = yongBo.get(dhuoTp.getUser());
		if (yongMp != null) {
			fpdhVO.setFpymc(yongMp.getFpkh());
		}

		return new Result(fpdhVO).toJsObject();
	}

	public IFkfpDAO getDao() {
		return dao;
	}

	public void setDao(IFkfpDAO dao) {
		this.dao = dao;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public IYongBO getYongBo() {
		return yongBo;
	}

	public void setYongBo(IYongBO yongBo) {
		this.yongBo = yongBo;
	}

	public IKhfkBO getKhBo() {
		return khBo;
	}

	public void setKhBo(IKhfkBO khBo) {
		this.khBo = khBo;
	}

	public ICzglDAO getCzglDAO() {
		return czglDAO;
	}

	public void setCzglDAO(ICzglDAO czglDAO) {
		this.czglDAO = czglDAO;
	}

	public String getFkfpPath() {
		return fkfpPath;
	}

	public void setFkfpPath(String fkfpPath) {
		this.fkfpPath = fkfpPath;
	}

	public ExcelbookDataExecuter<YszkQE> getFkfpExec() {
		return fkfpExec;
	}

	public void setFkfpExec(ExcelbookDataExecuter<YszkQE> fkfpExec) {
		this.fkfpExec = fkfpExec;
	}

}
