package com.quanta.sino.zk.bo;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.bo.api.IYyfxjgjlBO;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.orm.EtlyygljlItem;
import com.quanta.sino.zk.bo.api.IEtlyyglBO;
import com.quanta.sino.zk.dao.api.IEtlyygljlDAO;
import com.quanta.sino.zk.dao.api.IEtlyygljlItemDAO;

/**
 * <p>
 * Etl药液品质管理记录
 * </p>
 * <p>
 * create: 2011-2-15 下午04:31:51
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlyyglBO implements IEtlyyglBO {

	private IEtlyygljlDAO dao;

	private IEtlyygljlItemDAO itemDao;

	private IYyfxjgjlBO yyfxBo;

	@Override
	public void query(QEntity<EtlyygljlItem> qentity) {
		itemDao.query(qentity);
		if (qentity.getDatas().size() > 0) {
			for (EtlyygljlItem item : qentity.getDatas()) {
				if (item.getYyfxid() == null) continue;
				item.getYyfxid().getJjyFreeCon();
				item.getYyfxid().getJjyTotalCon();
				item.getYyfxid().getJdjyFreeCon();
				item.getYyfxid().getJdjyTotalCon();
				item.getYyfxid().getSdjyHsoCon();
				item.getYyfxid().getSdjyQt();
				item.getYyfxid().getXddySntCon();
				item.getYyfxid().getXddyEnsa();
				item.getYyfxid().getXddyAcidtCon();
				item.getYyfxid().getTcySnCon();
				item.getYyfxid().getTcyAcidCon();
				item.getYyfxid().getHxclyCrCon();
				item.getYyfxid().getHxclyPh();
				item.getYyfxid().getRq();
			}
		}
	}

	@Override
	public void save(Etlyygljl entity) {
		dao.save(entity);
	}

	@Override
	public void update(Etlyygljl entity) {
		dao.update(entity);
	}

	@Override
	public void saveItem(EtlyygljlItem entity) {
		itemDao.save(entity);
	}

	@Override
	public void updateYyglItem(EtlyygljlItem entity) {
		EtlyygljlItem item = itemDao.get(entity.getId());
		item.setJjbgs(entity.getJjbgs());
		item.setJdjbgs(entity.getJdjbgs());
		item.setSdjbgs(entity.getSdjbgs());
		item.setJjbgj(entity.getJjbgj());
		item.setJdjbgj(entity.getJdjbgj());
		item.setSdjbgLs(entity.getSdjbgLs());
		item.setQyypr201(entity.getQyypr201());
		item.setHxbgSh(entity.getHxbgSh());
		item.setDdbgEnsa(entity.getDdbgEnsa());
		item.setQyypr403(entity.getQyypr403());
		item.setHxbgGsg(entity.getHxbgGsg());
		item.setDdbgPsa(entity.getDdbgPsa());
		item.setHxbgZgsn(entity.getHxbgZgsn());
		item.setNewed(entity.isNewed());
		item.setRq(entity.getRq());
		itemDao.update(item);
	}

	@Override
	public Etlyygljl getByJlsj(Date jlsj) {
		return dao.getByJlsj(jlsj);
	}

	@Override
	public void deleteByYyfxid(Serializable id) {
		itemDao.deleteByYyfxid(id);
	}

	@Override
	public EtlyygljlItem getItem(Serializable id) {
		EtlyygljlItem item = itemDao.get(id);
		if (item != null && item.getYyfxid() != null) {
			item.getYyfxid().getJjyFreeCon();
			item.getYyfxid().getJjyTotalCon();
			item.getYyfxid().getJdjyFreeCon();
			item.getYyfxid().getJdjyTotalCon();
			item.getYyfxid().getSdjyHsoCon();
			item.getYyfxid().getSdjyQt();
			item.getYyfxid().getXddySntCon();
			item.getYyfxid().getXddyEnsa();
			item.getYyfxid().getXddyAcidtCon();
			item.getYyfxid().getTcySnCon();
			item.getYyfxid().getTcyAcidCon();
			item.getYyfxid().getHxclyCrCon();
			item.getYyfxid().getHxclyPh();
			item.getYyfxid().getRq();
		}
		return item;
	}

	@Override
	public EtlyygljlItem getByYyfxid(Serializable id) {
		return itemDao.getByYyfxid(id);
	}

	@Override
	public void read(Serializable id, boolean readed) {
		itemDao.read(id, readed);
	}

	@Override
	public boolean isExistedNew(Date rqBegin, Date rqEnd) {
		return itemDao.isExistedNew(rqBegin, rqEnd);
	}

	public IEtlyygljlDAO getDao() {
		return dao;
	}

	public void setDao(IEtlyygljlDAO dao) {
		this.dao = dao;
	}

	public IEtlyygljlItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(IEtlyygljlItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public IYyfxjgjlBO getYyfxBo() {
		return yyfxBo;
	}

	public void setYyfxBo(IYyfxjgjlBO yyfxBo) {
		this.yyfxBo = yyfxBo;
	}

}
