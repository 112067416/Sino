package com.quanta.sino.cmn.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.dao.api.IYongDAO;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.YongSize;

/**
 * <p>
 * 通用主文件数据访问实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:04:20
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class YongDAO implements IYongDAO {

	private DAO dao;

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(YongMp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(YongMp.class, id);
	}

	@Override
	public void deletes(List<String> ids) {
		dao.executeForValues("delete from YongMp where user in (?)", ids);
	}

	@Override
	public void setParams(List<String> users, String region, String valid,
			String ddnm) {
		StringBuilder ql = new StringBuilder("update YongMp set valid='").append(valid).append("'");
		if (region != null && !region.isEmpty()) {
			ql.append(",").append("region='").append(region).append("'");
		}
		if (ddnm != null && !ddnm.isEmpty()) {
			ql.append(",").append("ddnm='").append(ddnm).append("'");
		}
		ql.append(" where user in (?)");
		dao.executeForValues(ql.toString(), users);
	}

	@Override
	public void query(QEntity<YongMp> qentity) {
		dao.query(qentity);
	}

	@Override
	public YongMp get(Serializable id) {
		return dao.get(YongMp.class, id);
	}

	@Override
	public void update(YongMp entity) {
		dao.update(entity);
	}

	@Override
	public void saveShdz(List<YongShdz> shdzes) {
		dao.saveAll(shdzes);
	}

	@Override
	public void saveSize(List<YongSize> sizes) {
		dao.saveAll(sizes);
	}

	@Override
	public List<YongShdz> findShdz(String user) {
		return dao.find(YongShdz.class, "yong.user=? order by abbr", user);
	}

	@Override
	public List<YongSize> findSize(String user) {
		return dao.find(YongSize.class, "yong.user=? order by yuny asc,cdnm asc,houu asc,kuan asc", user);
	}

	@Override
	public List<YongMp> findAll() {
		return dao.findAll(YongMp.class);
	}

	@Override
	public boolean isExistDzdm(String dzdm) {
		return dao.isExisted("from YongShdz where dzdm=?", dzdm);
	}

	@Override
	public boolean isExistUser(String user) {
		return dao.isExisted("from YongMp where user=?", user);
	}

	@Override
	public boolean isExistUserAndDzdm(String user, String dzdm) {
		return dao.isExisted("from YongShdz where dzdm=? and yong.user=?", dzdm, user);
	}

	@Override
	public YongShdz getByDzdm(String dzdm) {
		return (YongShdz) dao.getUnique("from YongShdz where dzdm=?", dzdm);
	}

	@Override
	public void deleteShdz(String user) {
		dao.executeUpdate("delete from YongShdz where yong.user=?", user);
	}

	@Override
	public void deleteSize(String user) {
		dao.executeUpdate("delete from YongSize where yong.user=?", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findFpkhs() {
		return (List<String>) dao.query("select distinct fpkh from YongMp order by fpkh asc");
	}

}
