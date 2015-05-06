/**
 * 
 */
package com.quanta.sino.cmn.bo;

import java.util.Date;

import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Czlx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.cmn.dao.api.ICzjlDAO;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ZkfpCzjl;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品余材化处理业务层实现类
 * </p>
 * <p>
 * create: 2011-1-14 下午03:21:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YchBO implements IYchBO {

	/**
	 * 制品操作业务层接口
	 */
	private IZpBO zpBO;

	/**
	 * 订货操作业务层接口
	 */
	private IDhBO dhBO;

	/**
	 * 分配操作记录数据访问接口
	 */
	private ICzjlDAO czjlDAO;

	@Override
	public void doZpYch(String[] jbnos, User user) {
		ZpQE qentity = new ZpQE();
		qentity.setSize(-1);
		qentity.setJbnos(jbnos);
		zpBO.query(qentity);
		if (qentity.getDatas().size() == 0) {
			throw new CocoException(-1, "请选择制品");
		}
		// 制品所属订货合同
		String dhno = null;
		// 制品重量
		double zpzl = 0;
		// 合格品的重量
		double hgl = 0;
		// 记录制品做余材操作
		ZkfpCzjl czjl = null;
		// 操作时间
		Date date = new Date();
		for (ZpngTp entity : qentity.getDatas()) {
			if ((dhno = entity.getDhno()) == null) {
				throw new CocoException(-1, "制品信息有误,缺少订货合同");
			}
			if (entity.getZpzl() == null) {
				throw new CocoException(-1, "制品信息有误,重量为空");
			}
			// 已做装箱指示书的制品是不能做余材操作
			if (entity.getChno() != null && !entity.getChno().isEmpty()) {
				throw new CocoException(-1, entity.getJbno()
						+ "制品已做装箱指示书,不能做余材操作");
			}
			// 以下是判断制品号对应的制品信息是否正确。如堆场是否在C、F和G中，现品在库是否为制品，分配/余材标记是否为分配品，删除标识是否为初始，状态是否为初始
			// 验证制品堆场是否在C、F和G中
			if (!(DC.C.name.equals(entity.getDuic())
					|| DC.F.name.equals(entity.getDuic()) || DC.G.name.equals(entity.getDuic()))) {
				throw new CocoException(-1, entity.getJbno() + "制品堆场为"
						+ entity.getDuic() + "不能做余材操作");
			}
			// 验证现品在库是否为制品
			if (!(EXpzk.JZP.key.equals(entity.getXpzk()) || EXpzk.BZP.key.equals(entity.getXpzk()))) {
				throw new CocoException(-1, entity.getJbno() + "制品现品状况为"
						+ EXpzk.getValue(entity.getXpzk()) + "不能做余材操作");
			}
			// 验证分配/余材标记是否为分配品
			if (!EFpyc.FP.key.equals(entity.getFpyc())) {
				throw new CocoException(-1, entity.getJbno() + "制品分配/余材标记为"
						+ EFpyc.get(entity.getFpyc()).value + "不能做余材操作");
			}
			// 验证删除标识是否为初始
			if (!EScbj.CS.key.equals(entity.getScbj())) {
				throw new CocoException(-1, entity.getJbno() + "制品删除标识为"
						+ EScbj.get(entity.getScbj()).value + "不能做余材操作");
			}
			// 验证状态是否为初始
			if (!ZpStat.CS.stat.equals(entity.getStat())) {
				throw new CocoException(-1, entity.getJbno() + "制品状态为"
						+ ZpStat.get(entity.getStat()).name + "不能做余材操作");
			}
			// 修改制品做余材化操作后的信息
			entity.setFpno(null);
			entity.setFpyc(EFpyc.YC.key);
			entity.setDhno(null);
			entity.setAbbr(null);
			entity.setUser(null);
			entity.setUpda(date);
			// 修改制品信息
			zpBO.update(entity);
			zpzl += entity.getZpzl() / 1000d;
			// 记录余材制品的合格量。合格品的(产量为1或9、处置为空)
			if ((ChanType.hg.key.equals(entity.getChan()) || ChanType.bl.key.equals(entity.getChan()))
					&& (entity.getCzdm() == null || entity.getCzdm().isEmpty())) {
				hgl += entity.getZpzl() / 1000d;
			}
			// 记录制品余材操作
			czjl = new ZkfpCzjl();
			czjl.setJbno(entity.getJbno());
			czjl.setDhno(entity.getDhno());
			czjl.setFpno(entity.getFpno());
			czjl.setXpzk(entity.getXpzk());
			czjl.setSfpp(null);
			czjl.setCzlx(Czlx.key3.key);
			czjl.setDdno(user.getNo());
			czjl.setDdnm(user.getName());
			czjl.setCrea(date);
			// 保存制品做余材操作记录
			czjlDAO.save(czjl);
		}
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno.substring(0, 7),
				dhno.substring(7)));
		if (dhuoTp == null) {
			throw new CocoException(-1, "制品对应的订货合同不存在");
		}
		// 订货合同对应的品种
		String pzno = null;
		if ((pzno = dhuoTp.getPzno()) == null || pzno.isEmpty()) {
			throw new CocoException(-1, "订货合同对应的品种不存在");
		}
		// 修改订货合同的分配量。
		double fpln = dhuoTp.getFpln() != null ? dhuoTp.getFpln() : 0;
		dhuoTp.setFpln(NumberUtils.format(fpln - zpzl, 3));
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			// 钢卷制品的订货合同
			// 修改订货合同的ETL指示量
			double etlz = dhuoTp.getEtlz() != null ? dhuoTp.getEtlz() : 0;
			dhuoTp.setEtlz(NumberUtils.format(etlz - zpzl, 3));
			// 当制品为合格品（产量为1或9，处置为空）时，要修改制品对应订货合同的ETL合格量
			double etlh = dhuoTp.getEtlh() != null ? dhuoTp.getEtlh() : 0;
			dhuoTp.setEtlh(NumberUtils.format(etlh - hgl, 3));
		}
		else {
			// 剪切板的订货合同
			// 修改订货合同的SL指示量
			double slzs = dhuoTp.getSlzs() != null ? dhuoTp.getSlzs() : 0;
			dhuoTp.setSlzs(NumberUtils.format(slzs - zpzl, 3));
			// 当制品为合格品（产量为1或9，处置为空）时，要修改制品对应订货合同的SL合格量
			double slhg = dhuoTp.getSlhg() != null ? dhuoTp.getSlhg() : 0;
			dhuoTp.setSlhg(NumberUtils.format(slhg - hgl, 3));
		}
		// 保存订货合同修改的信息
		dhBO.update(dhuoTp);
	}

	@Override
	public void doScYch(ZpngTp zpngTp, String oldchan, DhuoTp dhuo, Date date) {

		// 合格(空)变不合格,余材化
		if (oldchan == null || ChanType.hg.key.equals(oldchan)
				|| ChanType.bl.key.equals(oldchan)) {
			if (!ChanType.hg.key.equals(zpngTp.getChan())
					&& !ChanType.bl.key.equals(zpngTp.getChan())) {

				// 修改制品做余材化操作后的信息
				zpngTp.setFpno(null);
				zpngTp.setFpyc(EFpyc.YC.key);
				zpngTp.setDhno(null);
				zpngTp.setAbbr(null);
				zpngTp.setUser(null);
				zpngTp.setUpda(date);

				// 记录制品余材操作
				ZkfpCzjl czjl = new ZkfpCzjl();
				czjl.setJbno(zpngTp.getJbno());
				czjl.setDhno(zpngTp.getDhno());
				czjl.setFpno(zpngTp.getFpno());
				czjl.setXpzk(zpngTp.getXpzk());
				czjl.setSfpp(null);
				czjl.setCzlx(Czlx.key3.key);
				czjl.setDdno(zpngTp.getDdno());
				czjl.setDdnm(null);
				czjl.setCrea(date);
				// 保存制品做余材操作记录
				czjlDAO.save(czjl);
			}
		}
		// 不合格(空)变合格,还原余材化
		if (oldchan == null
				|| (!ChanType.hg.key.equals(oldchan) && !ChanType.bl.key.equals(oldchan))) {
			if (ChanType.hg.key.equals(zpngTp.getChan())
					|| ChanType.bl.key.equals(zpngTp.getChan())) {

				// 修改制品做余材化操作后的信息
				// 品种代码
				String pzno = zpngTp.getPzno();
				if (pzno != null) {
					// 剪切板
					if (Code118.sheet.key.equals(pzno.substring(0, 1))
							|| Code118.scroll.key.equals(pzno.substring(0, 1))) {
						zpngTp.setFpno(ZtConstants.ZSDX_FP_NO);
					}
				}
				zpngTp.setFpyc(EFpyc.FP.key);
				if (dhuo != null) {
					zpngTp.setDhno(dhuo.getDhno() + dhuo.getLine());
					zpngTp.setAbbr(dhuo.getAbbr());
					zpngTp.setUser(dhuo.getUser());
				}
				zpngTp.setUpda(date);

				// 记录制品余材操作
				ZkfpCzjl czjl = new ZkfpCzjl();
				czjl.setJbno(zpngTp.getJbno());
				czjl.setDhno(zpngTp.getDhno());
				czjl.setFpno(zpngTp.getFpno());
				czjl.setXpzk(zpngTp.getXpzk());
				czjl.setSfpp(null);
				czjl.setCzlx(Czlx.key1.key);
				czjl.setDdno(zpngTp.getDdno());
				czjl.setDdnm(null);
				czjl.setCrea(date);
				// 保存制品做余材操作记录
				czjlDAO.save(czjl);
			}

		}

		// zpBO.save(zpngTp);
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public ICzjlDAO getCzjlDAO() {
		return czjlDAO;
	}

	public void setCzjlDAO(ICzjlDAO czjlDAO) {
		this.czjlDAO = czjlDAO;
	}

}
