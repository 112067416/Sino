package com.quanta.sino.yygl.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.quanta.sino.cmn.constants.EFace;
import com.quanta.sino.cmn.constants.SczsStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.SczsTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.yygl.bo.api.ISczsBO;
import com.quanta.sino.yygl.dao.api.ISczsDAO;
import com.quanta.sino.yygl.vo.SczsdDhVO;

/**
 * <p>
 * 生产指示单业务接口实现类
 * </p>
 * <p>
 * create: 2011-8-4 下午03:48:30
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class SczsBO implements ISczsBO {

	/**
	 * 
	 */
	private ISczsDAO dao;

	/**
	 * 
	 */
	private IDhBO dhBO;

	/**
	 * 
	 */
	private IYcaitpBO ycaitpBO;

	@Override
	public void save(SczsTp entity) {
		String sfjj = entity.getSfjj();
		entity.setSfjj(sfjj == null || sfjj.isEmpty() ? "N" : "Y");
		entity.setStat(SczsStat.newly.key);
		dao.save(entity);
	}

	@Override
	public void update(SczsTp entity) {
		SczsTp $entity = dao.get(entity.getId());
		String stat = $entity.getStat();
		ReflectUtils.copy($entity, entity, false);
		String sfjj = entity.getSfjj();
		$entity.setSfjj(sfjj == null || sfjj.isEmpty() ? "N" : "Y");
		$entity.setStat(stat);
		dao.update($entity);
	}

	@Override
	public void saveOrUpdate(SczsTp entity) {
		dao.update(entity);
	}

	@Override
	public String delete(String[] ids) {
		dao.delete(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<SczsTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public SczsTp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		SczsTp entity = dao.get(id);
		return new Result(entity).toJsObject();
	}

	@Override
	public String getDhInfo(String dhno, String line) {
		DhuoTp dhuoTp = dhBO.get(dhno, line, null);
		// 若获取的订货DB信息为空，这行号与订货no不对应
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		// 否则传递订货DB相关信息到界面中
		SczsdDhVO sczsdDh = new SczsdDhVO();
		sczsdDh.setAbbr(dhuoTp.getName());
		sczsdDh.setUser(dhuoTp.getUser());
		Long ybkc = 0l;
		if (line != null && !line.isEmpty()) {
			sczsdDh.setCang(dhuoTp.getCang());
			sczsdDh.setHouu(dhuoTp.getHouu());
			sczsdDh.setKuan(dhuoTp.getKuan());
			sczsdDh.setFudw(dhuoTp.getFudw());
			sczsdDh.setFuzm(dhuoTp.getFuzm());
			sczsdDh.setFufm(dhuoTp.getFufm());
			sczsdDh.setFace(dhuoTp.getFace());
			sczsdDh.setYuny(dhuoTp.getYuny());
			sczsdDh.setName(dhuoTp.getName());
			sczsdDh.setAbbr(dhuoTp.getAbbr());
			sczsdDh.setUser(dhuoTp.getUser());
			sczsdDh.setHtzl(dhuoTp.getHtzl());
			sczsdDh.setJhqi(dhuoTp.getJhqi());
			EFace eFace = EFace.get(dhuoTp.getFace());
			List<String> faces = eFace != null ? eFace.values : null;
			ybkc = ycaitpBO.getKc(dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getYuny(), faces);
		}
		else {
			sczsdDh.setCang(null);
			sczsdDh.setHouu(null);
			sczsdDh.setKuan(null);
			sczsdDh.setFudw(null);
			sczsdDh.setFuzm(null);
			sczsdDh.setFufm(null);
			sczsdDh.setFace(null);
			sczsdDh.setYuny(null);
			sczsdDh.setName(null);
			sczsdDh.setAbbr(null);
			sczsdDh.setUser(null);
			sczsdDh.setHtzl(null);
			sczsdDh.setJhqi(null);
		}
		if (ybkc != null) {
			sczsdDh.setYbkc(NumberUtils.format(ybkc / 1000d, 3));
		}
		return new Result(sczsdDh).toJsObject();
	}

	@Override
	public void updateStat(String stat, String[] ids) {
		dao.updateStat(stat, ids);
	}

	public ISczsDAO getDao() {
		return dao;
	}

	public void setDao(ISczsDAO dao) {
		this.dao = dao;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

}
