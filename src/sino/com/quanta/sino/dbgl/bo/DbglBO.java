package com.quanta.sino.dbgl.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EDbzt;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dbgl.bo.api.IDbglBO;
import com.quanta.sino.dbgl.dao.api.IDbglDAO;
import com.quanta.sino.orm.DbglLp;
import com.quanta.sino.orm.DbglTp;

/**
 * <p>
 * 业务实现
 * </p>
 * <p>
 * create: 2011-5-12 上午09:53:00
 * </p>
 * 
 * @author 张红国[c_x_pvc@163.com]
 * @version 1.0
 */
public class DbglBO implements IDbglBO {

	/**
	 * 
	 */
	private IDbglDAO dao;
	/**
	 * 调用序列号模块的bo接口
	 */
	private ISeqBO sqBo;

	@Override
	public void save(DbglTp entity) {
		// 再取一次卷板no
		String jbno = sqBo.sequence(Seq.dbjbno.name);
		entity.setJbno(jbno);
		Date date = new Date();
		entity.setCrea(date);
		entity.setUpda(date);
		entity.setDuic(DC.F.name);
		entity.setKw(DC.F.name);
		entity.setZt(EDbzt.CS.key);
		dao.save(entity);
		// 写日志
		DbglLp dbgllp = parseDbglLp(entity, ZtConstants.ZPNG_CLQ_ADD);
		dao.save(dbgllp);
	}

	@Override
	public String getJbno() {
		String jbno = sqBo.sequenceNoUpdate(Seq.dbjbno.name);
		return jbno;
	}

	@Override
	public void query(QEntity<DbglTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public DbglTp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void delete(String jbno) {
		// 写日志
		check(jbno);
		DbglTp entity = dao.get(jbno);
		DbglLp dbgllp = parseDbglLp(entity, ZtConstants.ZPNG_CLQ_DEL);
		dao.save(dbgllp);

		dao.delete(jbno);
	}

	@Override
	public void delAll(String[] jbnos) {
		for (String jbno : jbnos) {
			check(jbno);
			// 写日志
			DbglTp entity = dao.get(jbno);
			// 写日志
			DbglLp dbgllp = parseDbglLp(entity, ZtConstants.ZPNG_CLQ_DEL);
			dao.save(dbgllp);
			dao.delete(jbno);
		}

	}

	private void check(String jbno) {
		DbglTp dbgl = dao.get(jbno);
		if (dbgl == null) {
			throw new CocoException(-1, "制品不存在！");
		}
		if (DC.G.name.equals(dbgl.getDuic())) {
			throw new CocoException(-1, "制品已经捆包不能订正或删除！");
		}
	}

	@Override
	public void update(DbglTp entity) {
		check(entity.getJbno());
		DbglTp dbgl = dao.get(entity.getJbno());
		dbgl.setSlin(entity.getSlin());
		dbgl.setZpzl(entity.getZpzl());
		dbgl.setDmfx(entity.getDmfx());
		dbgl.setKbao(entity.getKbao());
		dbgl.setUpda(new Date());
		dao.update(dbgl);
		// 写日志
		DbglLp dbgllp = parseDbglLp(entity, ZtConstants.ZPNG_CLQ_MODY);
		dao.save(dbgllp);
	}

	private DbglLp parseDbglLp(DbglTp entity, String clq) {
		// 写日志
		DbglLp dbgllp = new DbglLp();
		dbgllp.setClq(clq);
		dbgllp.setCrea(new Date());
		dbgllp.setDmfx(entity.getDmfx());
		dbgllp.setJbno(entity.getJbno());
		dbgllp.setKbao(entity.getKbao());
		dbgllp.setSlin(entity.getSlin());
		dbgllp.setUpda(new Date());
		dbgllp.setZpzl(entity.getZpzl());
		return dbgllp;
	}

	@Override
	public void doFh(List<String> jbnos) {
		dao.updateZt(jbnos, EDbzt.YCH.key);
	}

	@Override
	public void doCxfh(String jbno) {
		DbglTp dbglTp = dao.get(jbno);
		if (dbglTp != null) {
			dbglTp.setZt(EDbzt.CS.key);
			dao.update(dbglTp);
		}
	}

	public IDbglDAO getDao() {
		return dao;
	}

	public void setDao(IDbglDAO dao) {
		this.dao = dao;
	}

	public ISeqBO getSqBo() {
		return sqBo;
	}

	public void setSqBo(ISeqBO sqBo) {
		this.sqBo = sqBo;
	}

}
