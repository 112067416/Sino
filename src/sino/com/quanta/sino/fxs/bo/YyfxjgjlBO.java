package com.quanta.sino.fxs.bo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.quanta.sino.fxs.bo.api.IYyfxjgjlBO;
import com.quanta.sino.fxs.dao.api.IYyfxjgjlDAO;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.orm.EtlyygljlItem;
import com.quanta.sino.orm.YyfxjgJl;
import com.quanta.sino.zk.bo.api.IEtlyyglBO;

/**
 * <p>
 * 药液分析结果记录表
 * </p>
 * <p>
 * create: 2011-2-15 下午04:27:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YyfxjgjlBO implements IYyfxjgjlBO {

	private IYyfxjgjlDAO dao;

	private IEtlyyglBO etlyyglBO;
	/**
	 * 
	 */
	private static final Set<String> EXCLUDE_FIELDS = new HashSet<String>();

	static {
		EXCLUDE_FIELDS.add("cjno");
		EXCLUDE_FIELDS.add("cjnm");
		EXCLUDE_FIELDS.add("cjsj");
	}

	private Etlyygljl parseEtlyygljl(YyfxjgJl entity, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date rq = null;

		try {
			rq = sdf.parse(sdf.format(date));
			c.setTime(rq);
			c.add(Calendar.HOUR_OF_DAY, 7);
			c.add(Calendar.MINUTE, 30);
			Date $rq = sdf.parse(sdf.format(c.getTime()));
			if ($rq.after(date)) {
				c.add(Calendar.DAY_OF_MONTH, -1);
				rq = sdf.parse(sdf.format(c.getTime()));
			}
		}
		catch (ParseException e) {
		}
		Etlyygljl etlyygljl = etlyyglBO.getByJlsj(rq);
		if (etlyygljl == null) {
			etlyygljl = new Etlyygljl();
			etlyygljl.setJlsj(rq);
		}
		Double cdwDd = etlyygljl.getCdwDd();
		if (cdwDd == null || entity.getXddySludge() != null) {
			etlyygljl.setCdwDd(entity.getXddySludge() != null ? entity.getXddySludge()
					: null);
		}
		Double cdwHx = etlyygljl.getCdwHx();
		if (cdwHx == null || entity.getHxclySludge() != null) {
			etlyygljl.setCdwHx(entity.getHxclySludge() != null ? entity.getHxclySludge()
					: null);
		}
		Double ddtnd = etlyygljl.getDdtnd();
		if (ddtnd == null || entity.getXddyFe() != null) {
			etlyygljl.setDdtnd(entity.getXddyFe() != null ? entity.getXddyFe()
					: null);
		}
		if (etlyygljl.getId() == null || etlyygljl.getId().isEmpty()) {
			etlyyglBO.save(etlyygljl);
		}
		else {
			etlyyglBO.update(etlyygljl);
		}
		return etlyygljl;
	}

	@Override
	public void save(YyfxjgJl entity, User user) {
		Date date = new Date();
		entity.setCjno(user.getNo());
		entity.setCjnm(user.getName());
		entity.setCjsj(date);
		dao.save(entity);

		Etlyygljl etlyygljl = parseEtlyygljl(entity, date);
		EtlyygljlItem etlyygljlItem = new EtlyygljlItem();
		etlyygljlItem.setYyfxid(entity);
		etlyygljlItem.setZbid(etlyygljl.getId());
		etlyygljlItem.setNewed(true);
		etlyygljlItem.setRq(entity.getRq());
		etlyyglBO.saveItem(etlyygljlItem);
	}

	@Override
	public void update(YyfxjgJl entity, User user) {
		Date date = new Date();
		YyfxjgJl yyfxjgJl = dao.get(entity.getId());
		ReflectUtils.copy(yyfxjgJl, entity, null, EXCLUDE_FIELDS, false);
		yyfxjgJl.setXgno(user.getNo());
		yyfxjgJl.setXgnm(user.getName());
		yyfxjgJl.setXgsj(date);
		dao.update(yyfxjgJl);
		parseEtlyygljl(yyfxjgJl, date);
		EtlyygljlItem etlyygljlItem = etlyyglBO.getByYyfxid(yyfxjgJl.getId());
		if (etlyygljlItem != null) {
			etlyygljlItem.setNewed(true);
			etlyygljlItem.setRq(yyfxjgJl.getRq());
			etlyyglBO.updateYyglItem(etlyygljlItem);
		}
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
		etlyyglBO.deleteByYyfxid(id);
	}

	@Override
	public void query(QEntity<YyfxjgJl> qentity) {
		dao.query(qentity);
	}

	@Override
	public YyfxjgJl get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void deletes(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
			etlyyglBO.deleteByYyfxid(id);
		}
	}

	public IYyfxjgjlDAO getDao() {
		return dao;
	}

	public void setDao(IYyfxjgjlDAO dao) {
		this.dao = dao;
	}

	public IEtlyyglBO getEtlyyglBO() {
		return etlyyglBO;
	}

	public void setEtlyyglBO(IEtlyyglBO etlyyglBO) {
		this.etlyyglBO = etlyyglBO;
	}

}
