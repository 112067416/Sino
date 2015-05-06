package com.quanta.sino.yygl.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChlldStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.dao.api.IChlldDAO;
import com.quanta.sino.yygl.vo.ChlldDhVO;
import com.quanta.sino.yygl.vo.ChlldVO;

/**
 * <p>
 * 出货联络单业务接口
 * </p>
 * <p>
 * create: 2010-12-21 下午06:23:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChlldBO implements IChlldBO {

	/**
	 * 次日出货联络单数据访问接口
	 */
	private IChlldDAO dao;

	/**
	 * 订货合同管理业务接口
	 */
	private IDhBO bo;

	/**
	 * 
	 */
	private IYongBO yongBo;

	/**
	 * 
	 */
	private IFkfpBO fkfpBO;

	/**
	 * 
	 */
	private IKhfkBO khfkBo;

	@Override
	public void save(Chlld entity, User user) {
		entity.setId(null);
		// 作成时间默认为系统的当前时间
		entity.setCrea(new Date());
		// 初始状态默认为未上锁
		entity.setStat(ChlldStat.WS.key);
		entity.setDdno(user.getNo());
		entity.setDdnm(user.getName());
		entity.setYwy(user.getNo());
		entity.setYwnm(user.getName());
		dao.save(entity);
	}

	/**
	 * <p>
	 * 判断用户信用额度
	 * </p>
	 * 
	 * @param hkje
	 *            当前货款金额
	 * @param user
	 *            用户代码
	 * @return boolean
	 */
	private boolean checkXyed(Double hkje, String user) {
		YongMp yongMp = yongBo.get(user);
		if (yongMp == null) {
			return false;
		}
		// 信用额度
		Double xyed = yongMp.getXyed();
		// 若没有信用额度，可以做装箱指示书
		if (xyed == null || xyed.doubleValue() == 0) {
			return true;
		}
		// 未收余款
		Double wsyk = fkfpBO.getWsyk(user);
		wsyk = (wsyk != null ? wsyk : 0);
		// 付款余额
		Double fkye = khfkBo.getFkye(user);
		fkye = (fkye != null ? fkye : 0);
		// 若存在信用额度，且信用额度小于(货款余额-付款余额)则不能做装箱指示书
		if (xyed < (hkje + wsyk - fkye)) {
			return false;
		}
		return true;
	}

	@Override
	public void save(Chlld entity) {
		dao.save(entity);
	}

	@Override
	public void update(Chlld entity) {
		dao.update(entity);
	}

	@Override
	public String delete(String[] ids) {
		Chlld entity = null;
		for (String id : ids) {
			entity = dao.get(id);
			if (entity == null) {
				return new Result(-1, "该次日出货联络单已做删除操作").toString();
			}
			if (!ChlldStat.WS.key.equals(entity.getStat())) {
				return new Result(-1, "该次日出货联络单状态为"
						+ ChlldStat.get(entity.getStat()).value + ",不能再做删除操作").toString();
			}
		}
		dao.delete(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<Chlld> qentity) {
		dao.query(qentity);
	}

	@Override
	public Chlld get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		Chlld entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该次日出货联络单信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void updateStat(String[] ids, String stat) {
		String ql = "update Chlld set stat=? where id in(?)";
		dao.executeForValues(ql, Arrays.asList(ids), stat);
	}

	@Override
	public void doSetting(ChlldVO vo) {
		String ql = "update Chlld set ysgs=?, ysnm=?, shno=?, shnm=?, bhzk=?, chzl=? where id in (?)";
		dao.executeForValues(ql, Arrays.asList(vo.getIds()), vo.getYsgs(), vo.getYsnm(), vo.getShno(), vo.getShnm(), vo.getBhzk(), vo.getChzl());
	}

	@Override
	public String doDivide(Chlld entity) {
		Chlld chlld = dao.get(entity.getId());
		if (chlld == null) {
			return new Result(-1, "次日出货联络单不存在").toString();
		}
		double chzl = chlld.getChzl() != null ? chlld.getChzl() : 0;
		double $chzl = entity.getChzl() != null ? entity.getChzl() : 0;
		if ($chzl >= chzl) {
			return new Result(-1, "分解的重量不能大于或等于原联络单重量").toString();
		}
		// 修改原联络单重量为 原重量减去分解的重量值
		chlld.setChzl(chzl - $chzl);
		update(chlld);
		// 新定义空联络单实体用于克隆，直接用获取的实体因为事务处理的关系无法重新生成信息信息，必须通过处理，此处为克隆处理
		Chlld $chlld = new Chlld();
		// 克隆处理
		ReflectUtils.copy($chlld, chlld, false);
		$chlld.setId(null);
		$chlld.setYsgs(entity.getYsgs());
		$chlld.setYsnm(entity.getYsnm());
		$chlld.setShno(entity.getShno());
		$chlld.setShnm(entity.getShnm());
		$chlld.setChzl($chzl);
		// 新分解信息更新日期默认为空
		$chlld.setUpda(null);
		save($chlld);
		return Result.SUCCESS;
	}

	@Override
	public String getDhInfo(String dhno, String line) {
		DhuoTp dhuoTp = bo.get(dhno, line, null);
		// 若获取的订货DB信息为空，这行号与订货no不对应
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		// 否则传递订货DB相关信息到界面中
		ChlldDhVO chlldDh = new ChlldDhVO();
		chlldDh.setAbbr(dhuoTp.getName());
		chlldDh.setUser(dhuoTp.getUser());
		if (line != null && !line.isEmpty()) {
			chlldDh.setCang(dhuoTp.getCang());
			chlldDh.setHouu(dhuoTp.getHouu());
			chlldDh.setKuan(dhuoTp.getKuan());
			chlldDh.setFudw(dhuoTp.getFudw());
			chlldDh.setFuzm(dhuoTp.getFuzm());
			chlldDh.setFufm(dhuoTp.getFufm());
			chlldDh.setFace(dhuoTp.getFace());
			chlldDh.setYuny(dhuoTp.getYuny());
		}
		else {
			chlldDh.setCang(null);
			chlldDh.setHouu(null);
			chlldDh.setKuan(null);
			chlldDh.setFudw(null);
			chlldDh.setFuzm(null);
			chlldDh.setFufm(null);
			chlldDh.setFace(null);
			chlldDh.setYuny(null);
		}
		return new Result(chlldDh).toJsObject();
	}

	@Override
	public String getCheck(Chlld entity) {
		String dhno = entity.getDhno();
		String line = entity.getLine();
		DhuoTp dhuoTp = bo.get(dhno, line, null);
		// 若获取的订货DB信息为空，这行号与订货no不对应
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		String name = entity.getName();
		String user = entity.getUser();
		if (user != null && !user.equals(dhuoTp.getUser())) {
			return new Result(-1, "该订货No.,不属于用户" + name).toString();
		}
		double hkje = dhuoTp.getHtdj() != null ? NumberUtils.format(entity.getChzl()
				* dhuoTp.getHtdj(), 3)
				: 0;
		// 判断信用额度
		if (dhuoTp != null && !checkXyed(hkje, dhuoTp.getUser())) {
			return new Result(-1, "该用户未付的货款已超过信用额度,不能对其发货").toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public void setWeather(Date chqi, String weather) {
		dao.setWeather(chqi, weather);
	}

	@Override
	public String getWeather(Date chqi) {
		return dao.getWeather(chqi);
	}

	public IChlldDAO getDao() {
		return dao;
	}

	public void setDao(IChlldDAO dao) {
		this.dao = dao;
	}

	public IDhBO getBo() {
		return bo;
	}

	public void setBo(IDhBO bo) {
		this.bo = bo;
	}

	public IYongBO getYongBo() {
		return yongBo;
	}

	public void setYongBo(IYongBO yongBo) {
		this.yongBo = yongBo;
	}

	public IFkfpBO getFkfpBO() {
		return fkfpBO;
	}

	public void setFkfpBO(IFkfpBO fkfpBO) {
		this.fkfpBO = fkfpBO;
	}

	public IKhfkBO getKhfkBo() {
		return khfkBo;
	}

	public void setKhfkBo(IKhfkBO khfkBo) {
		this.khfkBo = khfkBo;
	}

}
