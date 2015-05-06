package com.quanta.sino.cmn.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.api.IBaseDAO;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.vo.ScxbpzFormVO;
import com.quanta.sino.orm.Scxbpz;

/**
 * <p>
 * 生产线别配置业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:09:43
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ScxbBO implements IScxbBO {

	private IBaseDAO dao;

	@Override
	public void save(ScxbpzFormVO vo) {
		if (vo == null) {
			throw new CocoException(-1, "缺少参数");
		}
		Scxbpz[] entities = vo.getScxbs();
		ProductType type = ProductType.get(vo.getType());
		if (type == null) {
			throw new CocoException(-1, "没有指定生产类别，或指定的生产类别无效");
		}
		if (entities == null || entities.length == 0) {
			dao.executeUpdate("delete from Scxbpz where type=?", type.code);
			return;
		}
		List<String> ids = new ArrayList<String>();
		Set<String> codes = new HashSet<String>();
		boolean isAdd;
		for (Scxbpz entity : entities) {
			isAdd = entity.getId() == null || entity.getId().isEmpty();
			// 不允许修改
			if (!isAdd) {
				ids.add(entity.getId());
				continue;
			}
			else {
				entity.setId(null);
			}
			if (entity.getCode() == null || entity.getCode().isEmpty()
					|| entity.getName() == null || entity.getName().isEmpty()
					|| entity.getDept() == null || entity.getDept().isEmpty()
					|| codes.contains(entity.getCode())) {
				continue;
			}
			codes.add(entity.getCode());
			entity.setType(type.code);
			dao.save(entity);
			ids.add(entity.getId());
		}
		if (ids.isEmpty()) {
			dao.executeUpdate("delete from Scxbpz where type=?", type.code);
		}
		else {
			dao.executeForValues("delete from Scxbpz where id not in (?) and type=?", ids, type.code);
		}
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Scxbpz.class, id);
	}

	@Override
	public void query(QEntity<Scxbpz> qentity) {
		dao.query(qentity);
	}

	@Override
	public Scxbpz getByDept(String dept) {
		return (Scxbpz) dao.getUnique("from Scxbpz where dept=?", dept);
	}

	@Override
	public Scxbpz getByCode(ProductType type, String code) {
		if (type == null) {
			throw new CocoException(-1, "没有指定生产类别，或指定的生产类别无效");
		}
		return (Scxbpz) dao.getUnique("from Scxbpz where type=? and code=?", type.code, code);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Scxbpz> findByType(ProductType type) {
		return (List<Scxbpz>) dao.query("from Scxbpz where type=? order by code", type.code);
	}

	/**
	 * @return the dao
	 */
	public IBaseDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(IBaseDAO dao) {
		this.dao = dao;
	}

}
