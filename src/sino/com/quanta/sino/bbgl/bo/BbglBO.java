package com.quanta.sino.bbgl.bo;

import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.bbgl.bo.api.IBbglBO;
import com.quanta.sino.bbgl.dao.api.ICpcktzDAO;
import com.quanta.sino.bbgl.dao.api.ITzbzDAO;
import com.quanta.sino.bbgl.dao.api.IYccktzDAO;
import com.quanta.sino.bbgl.vo.CpcktzVO;
import com.quanta.sino.bbgl.vo.YccktzVO;
import com.quanta.sino.orm.Tzbz;

/**
 * <p>
 * 原材料仓库台帐业务接口实现类
 * </p>
 * <p>
 * create: 2011-8-31 上午11:32:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class BbglBO implements IBbglBO {

	/**
	 * 原生SQL查询接口
	 */
	private ISqlDAO sqlDAO;

	/**
	 * 原材台帐数据访问接口
	 */
	private IYccktzDAO yccktzDAO;

	/**
	 * 成品出库台帐数据访问接口
	 */
	private ICpcktzDAO cpcktzDAO;

	/**
	 * 台帐备注数据访问接口
	 */
	private ITzbzDAO tzbzDAO;

	@Override
	public void queryYctz(QEntity<YccktzVO> qentity) {
		sqlDAO.query(qentity);
	}

	@Override
	public void saveYctz(List<YccktzVO> vos) {
		for (YccktzVO item : vos) {
			yccktzDAO.update(item);
		}
	}

	@Override
	public void queryCptz(QEntity<CpcktzVO> qentity) {
		sqlDAO.query(qentity);

	}

	@Override
	public void saveCptz(List<CpcktzVO> vos, String cplb) {
		for (CpcktzVO item : vos) {
			cpcktzDAO.update(item, cplb);
		}
	}

	@Override
	public void queryTzbz(QEntity<Tzbz> qentity) {
		tzbzDAO.query(qentity);
	}

	@Override
	public String saveTzbz(Tzbz entity) {
		Tzbz $entity = tzbzDAO.get(entity.getXpzk(), entity.getSpbh(), entity.getNy(), entity.getBbm());
		if ($entity != null) {
			String spbh = entity.getSpbh();
			return new Result(
					-1,
					spbh == null || spbh.isEmpty() ? "数据库中已存在相同报表名、现品状况和年月的记录,请重新设定"
							: "数据库中已存在相同报表名、现品状况、年月和商品编号的记录,请重新设定").toString();
		}
		tzbzDAO.save(entity);
		return Result.SUCCESS;
	}

	@Override
	public String updateTzbz(Tzbz entity) {
		Tzbz $entity = tzbzDAO.get(entity.getXpzk(), entity.getSpbh(), entity.getNy(), entity.getBbm());
		if ($entity != null && !$entity.getId().equals(entity.getId())) {
			String spbh = entity.getSpbh();
			return new Result(
					-1,
					spbh == null || spbh.isEmpty() ? "数据库中已存在相同报表名、现品状况和年月的记录,请重新设定"
							: "数据库中已存在相同报表名、现品状况、年月和商品编号的记录,请重新设定").toString();
		}
		tzbzDAO.update(entity);
		return Result.SUCCESS;
	}

	@Override
	public String loadTzbzForJS(Serializable id) {
		Tzbz entity = tzbzDAO.get(id);
		if (entity == null) {
			return new Result(-1, "数据不存在,请联系管理员").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void delsTzbz(String[] ids) {
		for (String id : ids) {
			tzbzDAO.delete(id);
		}
	}

	public ISqlDAO getSqlDAO() {
		return sqlDAO;
	}

	public void setSqlDAO(ISqlDAO sqlDAO) {
		this.sqlDAO = sqlDAO;
	}

	public IYccktzDAO getYccktzDAO() {
		return yccktzDAO;
	}

	public void setYccktzDAO(IYccktzDAO yccktzDAO) {
		this.yccktzDAO = yccktzDAO;
	}

	public ITzbzDAO getTzbzDAO() {
		return tzbzDAO;
	}

	public void setTzbzDAO(ITzbzDAO tzbzDAO) {
		this.tzbzDAO = tzbzDAO;
	}

	public ICpcktzDAO getCpcktzDAO() {
		return cpcktzDAO;
	}

	public void setCpcktzDAO(ICpcktzDAO cpcktzDAO) {
		this.cpcktzDAO = cpcktzDAO;
	}

}
