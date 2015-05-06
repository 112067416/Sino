package com.quanta.sino.yszk.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yszk.dao.api.ICzglDAO;
import com.quanta.sino.yszk.dao.api.IKhfkDAO;
import com.quanta.sino.yszk.vo.KhfkVO;

/**
 * <p>
 * 客户付款管理业务接口实现类
 * </p>
 * <p>
 * create: 2011-6-30 上午10:04:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhfkBO implements IKhfkBO {

	/**
	 * 客户付款数据访问接口
	 */
	private IKhfkDAO dao;

	/**
	 * 应收帐款业务接口
	 */
	private IFkfpBO fpBo;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBo;

	/**
	 * 付款发票冲帐数据访问层接口
	 */
	private ICzglDAO czglDAO;

	@Override
	public String save(Khfk entity, User user) {
		entity.setId(null);
		entity.setDdno(user.getNo());
		entity.setDdnm(user.getName());
		entity.setStat(FpStat.CS.key);
		// 初始时 付款余额默认为付款金额
		entity.setFkye(entity.getFkje());
		dao.save(entity);
		return Result.SUCCESS;
	}

	@Override
	public String update(Khfk entity) {
		Khfk $khfk = dao.get(entity.getId());
		if ($khfk == null) {
			return new Result(-1, "该信息不存在,请联系管理员").toString();
		}
		Date crea1 = entity.getCrea();
		Date crea2 = $khfk.getCrea();
		if (!FpStat.CS.key.equals($khfk.getStat())) {
			return new Result(-1, "客户" + $khfk.getName() + "在"
					+ DateUtils.format(crea2, "yyyy-MM-dd")
					+ "号的付款记录已有冲帐,不能修改客户付款信息").toString();
		}
		double fkje1 = entity.getFkje() != null ? entity.getFkje() : 0d;
		double fkye = $khfk.getFkye() != null ? $khfk.getFkye() : 0d;
		double fkje2 = $khfk.getFkje() != null ? $khfk.getFkje() : 0d;
		$khfk.setUpda(new Date());
		$khfk.setFkje(fkje1);
		$khfk.setName(entity.getName());
		$khfk.setCrea(crea1);
		$khfk.setFkye(NumberUtils.format(NumberUtils.subtract(NumberUtils.add(fkye, fkje1).doubleValue(), fkje2).doubleValue(), 2));
		dao.update($khfk);
		return Result.SUCCESS;
	}

	@Override
	public String delete(String[] ids) {
		Khfk entity = null;
		for (int i = 0; i < ids.length; i++) {
			entity = dao.get(ids[i]);
			if (entity == null || FpStat.CS.key.equals(entity.getStat())) continue;
			return new Result(-1, "客户" + entity.getName() + "在"
					+ DateUtils.format(entity.getCrea(), "yyyy-MM-dd")
					+ "号的付款记录已有冲帐,不能删除该笔付款记录").toString();
		}
		dao.delete(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<Khfk> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<Khfk> find(List<String> ids) {
		return dao.find(ids);
	}

	@Override
	public String getForJs(Serializable id) {
		Khfk entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该客户付款信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public String getTzje(String id) {
		Khfk khfk = dao.get(id);
		if (khfk == null) {
			return new Result(-1, "该信息不存在,请联系管理员").toString();
		}
		if (FpStat.CS.key.equals(khfk.getStat())) {
			return new Result(-1, "该笔付款记录还未冲帐,不能设置调整金额").toString();
		}
		// 获取未收余款合计
		Czgl czgl = czglDAO.getByKhfk(id);
		if (czgl == null || czgl.getWsyk() == null || czgl.getWsyk() <= 0) {
			return new Result(-1, "该笔付款记录的未收余款为零,不用设置调整金额").toString();
		}
		czgl = czglDAO.getByKhfp(czgl.getFkfp().getId());
		if (czgl != null && !id.equals(czgl.getKhfk().getId())) {
			return new Result(-1, "该笔付款记录的未收余款为零,不用设置调整金额").toString();
		}
		KhfkVO khvo = new KhfkVO();
		khvo.setId(id);
		khvo.setTzye(czgl.getWsyk());
		return new Result(khvo).toJsObject();
	}

	@Override
	public String saveTzje(KhfkVO vo) {
		String id = vo.getId();
		Khfk khfk = dao.get(id);
		if (khfk == null) {
			return new Result(-1, "该信息不存在,请联系管理员").toString();
		}
		Czgl cgzl = czglDAO.getByKhfk(id);
		double tzye = vo.getTzye();
		Fkfp fkfp = cgzl.getFkfp();
		fkfp.setWsyk(0.0);
		fkfp.setTzje(tzye);
		fkfp.setStat(FpStat.YJS.key);
		fpBo.update(fkfp);
		khfk.setTzye(tzye);
		dao.update(khfk);
		return Result.SUCCESS;
	}

	@Override
	public String doCxcz(String id) {
		Khfk khfk = dao.get(id);
		if (khfk == null) {
			return new Result(-1, "该客户付款信息已丢失,请联系管理员").toString();
		}
		if (FpStat.CS.key.equals(khfk.getStat())) {
			return new Result(-1, "该笔付款记录还未冲账，不能做撤销冲账操作").toString();
		}
		List<Czgl> czgls = czglDAO.find(id);
		if (czgls.size() == 0) {
			return new Result(-1, "该笔付款记录还未冲账，不能做撤销冲账操作").toString();
		}
		// 付款金额
		double fkje = 0d;
		// 总计付款金额
		double $fkje = 0d;
		// 未收余额
		double wsyk = 0d;
		Date date = new Date();
		Fkfp fkfp = null;
		for (Czgl czgl : czgls) {
			if ((fkfp = czgl.getFkfp()) == null) continue;
			// 累加每次冲账时的付款金额
			fkje = czgl.getFkje() != null ? czgl.getFkje() : 0d;
			wsyk = fkfp.getWsyk() != null ? fkfp.getWsyk() : 0d;
			$fkje = NumberUtils.add($fkje, fkje).doubleValue();
			if (czglDAO.countByFkfp(fkfp.getId()) == 1) {
				fkfp.setStat(FpStat.CS.key);
				fkfp.setFkje(0.0);
				fkfp.setWsyk(NumberUtils.format(NumberUtils.add(fkje, wsyk).doubleValue(), 2));
				fkfp.setSkri(date);
			}
			else {
				fkfp.setStat(FpStat.WJS.key);
				fkfp.setFkje(NumberUtils.format(NumberUtils.subtract(fkfp.getFkje() != null ? fkfp.getFkje()
						: 0, fkje).doubleValue(), 2));
				fkfp.setWsyk(NumberUtils.format(NumberUtils.add(wsyk, fkje).doubleValue(), 2));
			}
			// 修改保存以上值
			fpBo.update(fkfp);
			// 删除掉对应的冲账记录
			czglDAO.delete(czgl.getId());
		}
		double fkye = khfk.getFkye() != null ? khfk.getFkye() : 0d;
		khfk.setFkye(NumberUtils.format(NumberUtils.add(fkye, $fkje).doubleValue(), 2));
		double hkje = khfk.getHkje() != null ? khfk.getHkje() : 0d;
		khfk.setHkje(NumberUtils.format(NumberUtils.subtract(hkje, $fkje).doubleValue(), 2));
		if (khfk.getHkje() == null || khfk.getHkje().doubleValue() == 0d) {
			khfk.setStat(FpStat.CS.key);
		}
		else {
			khfk.setStat(FpStat.WJS.key);
		}
		dao.update(khfk);
		return Result.SUCCESS;
	}

	@Override
	public Double getFkye(String fpymc) {
		return dao.getFkye(fpymc);
	}

	@Override
	public List<Khfk> find(String fpymc, List<String> stats) {
		return dao.find(fpymc, stats);
	}

	@Override
	public boolean isExisted(String fpymc, List<String> stats) {
		return dao.isExisted(fpymc, stats);
	}

	@Override
	public Khfk get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void updateByCz(Khfk entity) {
		dao.update(entity);
	}

	public IFkfpBO getFpBo() {
		return fpBo;
	}

	public void setFpBo(IFkfpBO fpBo) {
		this.fpBo = fpBo;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IKhfkDAO getDao() {
		return dao;
	}

	public void setDao(IKhfkDAO dao) {
		this.dao = dao;
	}

	public ICzglDAO getCzglDAO() {
		return czglDAO;
	}

	public void setCzglDAO(ICzglDAO czglDAO) {
		this.czglDAO = czglDAO;
	}

}
