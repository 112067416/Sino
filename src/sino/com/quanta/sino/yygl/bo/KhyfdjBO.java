package com.quanta.sino.yygl.bo;

import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khyfdj;
import com.quanta.sino.yygl.bo.api.IKhyfdjBO;
import com.quanta.sino.yygl.dao.api.IKhyfdjDAO;

/**
 * <p>
 * 客户运费单价业务层实现类
 * </p>
 * <p>
 * create: 2011-2-13 下午07:24:14
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfdjBO implements IKhyfdjBO {

	private IKhyfdjDAO khyfdjDAO;

	@Override
	public void save(Khyfdj entity) {
		khyfdjDAO.save(entity);
	}

	@Override
	public void saveAll(List<Khyfdj> entities) {
		khyfdjDAO.saveAll(entities);
	}

	@Override
	public void update(Khyfdj entity) {
		khyfdjDAO.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		khyfdjDAO.delete(id);
	}

	@Override
	public void query(QEntity<Khyfdj> qentity) {
		khyfdjDAO.query(qentity);
	}

	@Override
	public Khyfdj get(Serializable id) {
		return khyfdjDAO.get(id);
	}

	@Override
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String ld) {
		return khyfdjDAO.getUnique(ysnm, user, shno, addr, ysfs, stat, ld);
	}

	@Override
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String djdw, String ld) {
		return khyfdjDAO.getUnique(ysnm, user, shno, addr, ysfs, stat, djdw, ld);
	}

	@Override
	public String getForJs(Serializable id) {
		Khyfdj khyfdj = khyfdjDAO.get(id);
		return new Result(khyfdj).toJsObject();
	}

	@Override
	public void updateYfdj(Serializable id, Double yfdj) {
		Khyfdj khyfdj = khyfdjDAO.get(id);
		if (khyfdj != null) {
			khyfdj.setYfdj(yfdj);
			khyfdjDAO.update(khyfdj);
		}
	}

	@Override
	public void updateStat(List<String> ids, String stat) {
		khyfdjDAO.updateStat(ids, stat);
	}

	/**
	 * @return the khyfdjDAO
	 */
	public IKhyfdjDAO getKhyfdjDAO() {
		return khyfdjDAO;
	}

	/**
	 * @param khyfdjDAO
	 *            the khyfdjDAO to set
	 */
	public void setKhyfdjDAO(IKhyfdjDAO khyfdjDAO) {
		this.khyfdjDAO = khyfdjDAO;
	}

}
