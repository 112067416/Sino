package com.quanta.sino.cmn.bo;

import java.io.Serializable;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.dao.api.IYongDAO;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.YongSize;

/**
 * <p>
 * 用户主文件业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:09:22
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class YongBO implements IYongBO {

	private IYongDAO dao;

	@Override
	public void save(YongMp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void deletes(List<String> ids) {
		for (String id : ids) {
			dao.deleteShdz(id);
			dao.delete(id);
		}
	}

	@Override
	public void setParams(List<String> users, String region, String valid,
			String ddnm) {
		dao.setParams(users, region, valid, ddnm);
	}

	@Override
	public void query(QEntity<YongMp> qentity) {
		dao.query(qentity);
	}

	@Override
	public YongMp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		YongMp entity = this.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该文件信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void update(YongMp entity) {
		dao.update(entity);
	}

	@Override
	public String saveShdz(String user, List<YongShdz> shdzes) {
		if (shdzes == null || shdzes.size() == 0) {
			return new Result(-1, "未添加送货地址").toString();
		}
		YongMp entity = new YongMp();
		entity.setUser(user);
		for (YongShdz shdz : shdzes) {
			if (shdz.getAddr() == null || shdz.getAddr().isEmpty()) {
				return new Result(-1, "送货地址为必填项").toString();
			}
			shdz.setId(null);
			shdz.setYong(entity);
		}
		dao.deleteShdz(user);
		dao.saveShdz(shdzes);
		return Result.SUCCESS;
	}

	@Override
	public String saveSize(String user, List<YongSize> sizes) {
		if (sizes == null || sizes.size() == 0) {
			return new Result(-1, "未添加用户尺寸和用途").toString();
		}
		YongMp entity = new YongMp();
		entity.setUser(user);
		for (YongSize size : sizes) {
			size.setId(null);
			size.setYong(entity);
		}
		dao.deleteSize(user);
		dao.saveSize(sizes);
		return Result.SUCCESS;
	}

	@Override
	public List<YongShdz> findShdz(String user) {
		return dao.findShdz(user);
	}

	@Override
	public List<YongSize> findSize(String user) {
		return dao.findSize(user);
	}

	@Override
	public boolean isWithinCredit(String user, Double number) {
		if (number == null || number <= 0) {
			return true;
		}
		YongMp entity = dao.get(user);
		if (entity == null) {
			return false;
		}
		return entity.getXyed() == null || entity.getXyed() <= 0
				|| entity.getXyed().doubleValue() > number;
	}

	@Override
	public List<YongMp> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean isExistUser(String user) {
		return dao.isExistUser(user);
	}

	@Override
	public boolean isExistUserAndDzdm(String user, String dzdm) {
		return dao.isExistUserAndDzdm(user, dzdm);
	}

	@Override
	public YongShdz getByDzdm(String dzdm) {
		return dao.getByDzdm(dzdm);
	}

	@Override
	public List<String> findFpkhs() {
		return dao.findFpkhs();
	}

	public IYongDAO getDao() {
		return dao;
	}

	public void setDao(IYongDAO dao) {
		this.dao = dao;
	}

}
