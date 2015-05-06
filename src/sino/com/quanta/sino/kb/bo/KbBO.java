package com.quanta.sino.kb.bo;

import java.util.Date;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.kb.bo.api.IKbBO;
import com.quanta.sino.kb.constants.KbConstants;
import com.quanta.sino.kb.dao.api.IKbDAO;
import com.quanta.sino.orm.Kbrzb;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 捆包生产业务操作层实现类
 * </p>
 * <p>
 * create: 2011-1-12 上午11:46:28
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KbBO implements IKbBO {

	/**
	 * 捆包数据访问接口
	 */
	private IKbDAO dao;

	/**
	 * 引入制品业务层bo
	 */
	private IZpBO zpBo;

	/**
	 * 引入订货管理业务层bo
	 */
	private IDhBO dhBo;

	@Override
	public void saveKbsj(String[] jbnos, String bz, User user, String duic) {
		ZpngTp tp = null;
		Date date = new Date();
		for (String jbno : jbnos) {
			Kbrzb kbrzb = new Kbrzb();
			// 修改制品在库表
			tp = zpBo.getZp(jbno);
			if (tp == null) {
				throw new CocoException(-1, "制品No." + jbno + "不存在");
			}
			// 把堆场改为G
			tp.setDuic(DC.G.name);
			// 库位也修改为G
			tp.setKw(DC.G.name);
			// 记录捆包日期
			tp.setKbsd(date);
			// 记录打印标签时间
			tp.setBjdy(date);
			// 把堆场回退为F
			zpBo.update(tp);
			// 保存捆包日志表
			kbrzb.setJbno(jbno);
			kbrzb.setBanz(bz);
			kbrzb.setIskb(KbConstants.ISKB_YK);
			kbrzb.setDdno(user.getNo());
			kbrzb.setDdnm(user.getName());
			kbrzb.setCrea(date);
			dao.save(kbrzb);
		}
	}

	@Override
	public void rollBack(String[] jbnos, User user, String xpzk) {
		ZpngTp tp = null;
		Date date = new Date();
		for (String jbno : jbnos) {
			Kbrzb kbrzb = new Kbrzb();
			// 修改制品在库表
			tp = zpBo.getZp(jbno);
			if (tp == null) {
				throw new CocoException(-1, "制品No." + jbno + "不存在");
			}
			if (tp.getChno() != null && !tp.getChno().isEmpty()) {
				throw new CocoException(-1, "制品No." + jbno + "已制作装箱指示书,不能做回退操作");
			}
			if (EXpzk.BZP.key.equals(xpzk)) {
				tp.setDuic(DC.F.name);
				// 库位也修改为F
				tp.setKw(DC.F.name);
			}
			else {
				tp.setDuic(DC.C.name);
				// 库位也修改为F
				tp.setKw(DC.C.name);
			}
			tp.setKbsd(null);
			tp.setDuib(date);
			// 把堆场回退为F
			zpBo.update(tp);
			// 保存捆包日志表
			kbrzb.setJbno(jbno);
			kbrzb.setBanz(user.getGroup());
			kbrzb.setIskb(KbConstants.ISKB_WK);
			kbrzb.setDdno(user.getNo());
			kbrzb.setDdnm(user.getName());
			kbrzb.setCrea(date);
			dao.save(kbrzb);
		}
	}

	@Override
	public void updateSfjj(String[] jbnos, String sfjj) {
		ZpngTp tp = null;
		for (String jbno : jbnos) {
			tp = zpBo.getZp(jbno);
			tp.setSfjj(sfjj);
			zpBo.update(tp);
		}
	}

	@Override
	public void save(Kbrzb entity) {
		dao.save(entity);
	}

	@Override
	public String getKb(String jbno, String duic) {
		ZpngTp tp = zpBo.getZp(jbno);
		if (tp == null) {
			return new Result(-1, "制品No." + jbno + "不存在").toString();
		}
		if (!EScbj.CS.key.equals(tp.getScbj())) {
			return new Result(-1, "该制品已做删除标识,不能进行捆包操作").toString();
		}
		if (!duic.equals(tp.getDuic())) {
			return new Result(-1, "该制品不在" + duic + "堆场,不能做捆包操作").toString();
		}
		return new Result(tp).toJsObject();
	}

	@Override
	public void saveKw(String kw, String[] jbnos) {
		ZpngTp zpng = null;
		String dc = kw.substring(0, 1);
		for (String jbno : jbnos) {
			zpng = zpBo.getZp(jbno);
			if (zpng == null) {
				throw new CocoException(-1, "制品No." + jbno + "不存在");
			}
			if (ZpStat.SJLR.stat.equals(zpng.getStat())) {
				throw new CocoException(-1, "制品" + jbno + "状态为"
						+ ZpStat.SJLR.name + ",不能做修改堆场操作");
			}
			if (zpng.getChno() != null && !zpng.getChno().isEmpty()) {
				throw new CocoException(-1, "制品" + jbno + "已制作装箱指示书,不能做修改堆场操作");
			}
			if (!EScbj.CS.key.equals(zpng.getScbj())) {
				throw new CocoException(-1, "制品" + jbno + "删除标记为"
						+ EScbj.get(zpng.getScbj()).value + ",不能做修改堆场操作");
			}
			zpng.setDuic(dc);
			zpng.setKw(kw);
			zpBo.update(zpng);
		}
	}

	public IKbDAO getDao() {
		return dao;
	}

	public void setDao(IKbDAO dao) {
		this.dao = dao;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

}
