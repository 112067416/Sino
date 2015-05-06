package com.quanta.sino.etl.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IMgBO;
import com.quanta.sino.etl.dao.api.IMgDAO;
import com.quanta.sino.etl.vo.MgVO;
import com.quanta.sino.orm.DmLp;
import com.quanta.sino.orm.Dmgs;

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
public class MgBO implements IMgBO {
	private IMgDAO dao;
	/**
	 * 调用序列号模块的bo接口
	 */
	private ISeqBO sqBo;

	@Override
	public void update(Dmgs entity) {
		Dmgs dmsave = dao.get(entity.getId());
		dmsave.setCang(entity.getCang());
		dmsave.setKuan(entity.getKuan());
		dmsave.setDmfx(entity.getDmfx());
		dmsave.setKbao(entity.getKbao());
		dmsave.setKw(entity.getKw());
		dmsave.setUpda(new Date());
		dao.update(dmsave);
	}

	@Override
	public Dmgs getUnique(Double kuan, Double cang, String dmfx, String kbao,
			String kw) {
		return dao.getUnique(kuan, cang, dmfx, kbao, kw);
	}

	@Override
	public void save(MgVO mgVo) {
		// 再取一次
		Dmgs dm = dao.getUnique(mgVo.getKuan(), mgVo.getCang(), mgVo.getDmfx(), mgVo.getKbao(), mgVo.getKw());
		if (dm == null) {
			// 新增库存
			dm = new Dmgs();
			dm.setCrea(new Date());
			dm.setDann(mgVo.getDann());
			dm.setDmgs(mgVo.getAdddmgs());
			dm.setKuan(mgVo.getKuan());
			dm.setCang(mgVo.getCang());
			dm.setDmfx(mgVo.getDmfx());
			dm.setKbao(mgVo.getKbao());
			dm.setKw(mgVo.getKw());
			dm.setBan(mgVo.getBan());
			dm.setZu(mgVo.getZu());
			dao.save(dm);
			// 写日志
			DmLp dmlp = parseDmLpLp(dm, null, null, mgVo.getKw(), mgVo.getAdddmgs(), mgVo.getBan(), mgVo.getZu(), mgVo.getDann(), ZtConstants.ZPNG_CLQ_ADD);
			dao.saveDmlp(dmlp);
		}
		else {
			// 更新库存
			Integer dmgsAll = 0;
			if (dm.getDmgs() != null) {
				dmgsAll = dm.getDmgs();
			}
			if (mgVo.getAdddmgs() != null) {
				dmgsAll = dmgsAll + mgVo.getAdddmgs();
			}
			dm.setDmgs(dmgsAll);
			dm.setUpda(new Date());
			dao.update(dm);
			// 如果出库库存为0，删除记录
			if (dmgsAll.intValue() == 0) {
				dao.delete(dm.getId());
			}
			// 写日志
			DmLp dmlp = parseDmLpLp(dm, null, null, mgVo.getKw(), mgVo.getAdddmgs(), mgVo.getBan(), mgVo.getZu(), mgVo.getDann(), ZtConstants.ZPNG_CLQ_MODY);
			dao.saveDmlp(dmlp);
		}

	}

	private DmLp parseDmLpLp(Dmgs dm, String oukw, Integer ougs, String inkw,
			Integer ings, String ban, String zu, String dann, String clq) {
		// 写日志
		DmLp dmlp = new DmLp();
		dmlp.setClq(clq);
		dmlp.setCrea(new Date());
		dmlp.setDann(dann);
		dmlp.setOukw(oukw);
		dmlp.setOugs(ougs);
		dmlp.setInkw(inkw);
		dmlp.setIngs(ings);
		dmlp.setKuan(dm.getKuan());
		dmlp.setCang(dm.getCang());
		dmlp.setDmfx(dm.getDmfx());
		dmlp.setKbao(dm.getKbao());
		dmlp.setBan(ban);
		dmlp.setZu(zu);
		return dmlp;
	}

	@Override
	public void updateDmkz(Double kuan, Double cang, String dmfx, String kbao,
			String kw, String slin, Integer gs) {
		// 再取一次
		gs = gs == null ? 0 : gs;
		Dmgs dm = dao.getUnique(kuan, cang, dmfx, kbao, kw);
		if (dm == null) {
			// 新增库存
			dm = new Dmgs();
			dm.setCrea(new Date());
			dm.setDmgs(0 - gs);
			dm.setKuan(kuan);
			dm.setCang(cang);
			dm.setDmfx(dmfx);
			dm.setKbao(kbao);
			dm.setKw(kw);
			dm.setDann(slin);
			dao.save(dm);
			// 写日志
			DmLp dmlp = parseDmLpLp(dm, kw, gs, null, null, null, null, slin, ZtConstants.ZPNG_CLQ_ADD);
			dao.saveDmlp(dmlp);
		}
		else {
			// 更新库存
			Integer dmgsAll = 0;
			if (dm.getDmgs() != null) {
				dmgsAll = dm.getDmgs();
			}
			dmgsAll = dmgsAll - gs;
			dm.setDmgs(dmgsAll);
			dao.update(dm);
			// 如果出库库存为0，删除记录
			if (dmgsAll.intValue() == 0) {
				dao.delete(dm.getId());
			}
		}
	}

	@Override
	public void saveMove(List<String> idsList, String ban, String zu,
			String dann) {

		String[] ids;
		String gid = null;
		Integer ougs = null;
		String inkw = null;
		Dmgs outdm = null;
		Dmgs indm = null;
		Dmgs dm = null;
		Integer dmgsAll = 0;
		DmLp dmlp = null;
		for (String id : idsList) {
			ids = id.split("#");
			if (ids.length > 2) {
				gid = ids[0];
				ougs = Integer.parseInt(ids[1]);
				inkw = ids[2];

				outdm = dao.get(gid);
				if (outdm == null) {
					throw new CocoException(-1, "移出库位不存在！");
				}
				indm = dao.getUnique(outdm.getKuan(), outdm.getCang(), outdm.getDmfx(), outdm.getKbao(), inkw);
				if (indm == null) {
					// 新增进库
					dm = new Dmgs();
					dm.setCrea(new Date());
					dm.setDann(outdm.getDann());
					dm.setDmgs(ougs);
					dm.setKuan(outdm.getKuan());
					dm.setCang(outdm.getCang());
					dm.setDmfx(outdm.getDmfx());
					dm.setKbao(outdm.getKbao());
					dm.setKw(inkw);
					dm.setBan(ban);
					dm.setZu(zu);
					dm.setDann(dann);
					dao.save(dm);
				}
				else {
					// 更新进库
					dmgsAll = 0;
					if (indm.getDmgs() != null) {
						dmgsAll = indm.getDmgs();
					}
					dmgsAll = dmgsAll + ougs;
					indm.setDmgs(dmgsAll);
					indm.setUpda(new Date());
					dao.update(indm);
				}
				// 修改出库
				dmgsAll = 0;
				if (outdm.getDmgs() != null) {
					dmgsAll = outdm.getDmgs();
				}
				dmgsAll = dmgsAll - ougs;
				outdm.setDmgs(dmgsAll);
				outdm.setUpda(new Date());
				dao.update(outdm);
				// 如果出库库存为0，删除记录
				if (dmgsAll.intValue() == 0) {
					dao.delete(gid);
				}
				// 写日志
				dmlp = parseDmLpLp(outdm, outdm.getKw(), ougs, inkw, ougs, ban, zu, dann, ZtConstants.ZPNG_CLQ_DEL);
				dao.saveDmlp(dmlp);
			}
		}
	}

	@Override
	public void query(QEntity<Dmgs> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Dmgs get(Serializable id) {
		return dao.get(id);
	}

	public ISeqBO getSqBo() {
		return sqBo;
	}

	public void setSqBo(ISeqBO sqBo) {
		this.sqBo = sqBo;
	}

	public IMgDAO getDao() {
		return dao;
	}

	public void setDao(IMgDAO dao) {
		this.dao = dao;
	}

}
