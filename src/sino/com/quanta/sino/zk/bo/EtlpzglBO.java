package com.quanta.sino.zk.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeTyzl;
import com.quanta.sino.cmn.constants.EEtlpz;
import com.quanta.sino.fxs.vo.JCr;
import com.quanta.sino.fxs.vo.JTyl;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zk.dao.api.IEtlpzglDAO;
import com.quanta.sino.zk.vo.EtlpzGlVO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * ETL品质管理业务实现类
 * </p>
 * <p>
 * create: 2011-5-9 下午09:35:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlpzglBO implements IEtlpzglBO {

	/**
	 * ETL品质管理数据访问接口
	 */
	private IEtlpzglDAO dao;

	/**
	 * 指示书业务接口
	 */
	private IZsBO zsBO;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;

	/**
	 * 原生SQL查询接口
	 */
	private ISqlDAO sqlDAO;

	@Override
	public void update(EtlpzGl entity) {
		dao.update(entity);
	}

	@Override
	public String updateEtlpzGl(EtlpzGl entity) {
		EtlpzGl $entity = dao.get(entity.getId());
		if ($entity == null) {
			return new Result(-1, "该生产记录不存在").toString();
		}
		// Date scsj = $entity.getScsj();
		// if (scsj != null
		// && DateUtils.add(scsj, Calendar.DAY_OF_MONTH, 1).before(new Date()))
		// {
		// return new Result(-1, "该卷的生产时间为"
		// + DateUtils.format(scsj, "yyyy-MM-dd HH:mm")
		// + "已超过修改时限,不能修改该卷生产记录").toString();
		// }
		$entity.setBan(entity.getBan());
		$entity.setZu(entity.getZu());
		$entity.setPass(entity.getPass());
		$entity.setCheDlmdb(entity.getCheDlmdb());
		$entity.setCheDlmdt(entity.getCheDlmdt());
		$entity.setSczm(entity.getSczm());
		$entity.setScfm(entity.getScfm());
		$entity.setTj(entity.getTj());
		$entity.setTyjms(entity.getTyjms());
		$entity.setOilerBl(entity.getOilerBl());
		$entity.setUpda(new Date());
		dao.update($entity);
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<EtlpzGl> qentity) {
		dao.query(qentity);
	}

	@Override
	public void queryDq(QEntity<EtlpzGlVO> qentity) {
		sqlDAO.query(qentity);
	}

	@Override
	public void delete(String[] ids) {
		dao.delete(Arrays.asList(ids));
	}

	@Override
	public void save(EtlpzGl entity) {
		dao.save(entity);
	}

	@Override
	public EtlpzGl getByJbno(String jbno) {
		return dao.getByJbno(jbno);
	}

	@Override
	public void outEtlpzGl(String jbno) {
		if (jbno == null || jbno.isEmpty()) return;
		dao.updateStatDqjToYwc();
		EtlpzGl etlpzGl = getByJbno(jbno);
		if (etlpzGl == null) {
			etlpzGl = new EtlpzGl();
		}
		etlpzGl.setJbno(jbno);
		ZszrTp zszrTp = zsBO.getUniqueZszrTp(jbno);
		if (zszrTp != null) {
			ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
			etlpzGl.setFugm(zsdhTp.getFugm());
			String yqty = zsdhTp.getYqty();
			String ytyp = zsdhTp.getYtyp();
			etlpzGl.setYqty(yqty);
			etlpzGl.setYtyp(ytyp);
			etlpzGl.setFuzs(zsdhTp.getFuzs());
			etlpzGl.setFuzx(zsdhTp.getFuzx());
			etlpzGl.setFufs(zsdhTp.getFufs());
			etlpzGl.setFufx(zsdhTp.getFufx());
			JCr cr = JCr.get(zsdhTp.getHxcl());
			if (cr != null) {
				etlpzGl.setCrs(cr.max);
				etlpzGl.setCrx(cr.min);
			}
			// 合金
			String hjin = zsdhTp.getHjin();
			if (hjin != null && !hjin.isEmpty()) {
				CodeItem codeItem = codeBO.getCodeItem(CmnConstants.SINO_HJIN, hjin);
				Double $hjin = null;
				if (codeItem != null) {
					try {
						$hjin = Double.valueOf(codeItem.getValue());
					}
					catch (NumberFormatException e) {
					}
				}
				etlpzGl.setHjin($hjin);
			}
			CodeTyzl codeTyzl;
			if (ytyp != null && !ytyp.isEmpty() && yqty != null
					&& !yqty.isEmpty()
					&& (codeTyzl = CodeTyzl.getByName(ytyp)) != null) {
				JTyl tyl = JTyl.get(yqty);
				if (tyl != null && JTyl.D80.type.equals(codeTyzl.key)
						&& JTyl.D80.name.equals(yqty)
						&& (hjin == null || hjin.isEmpty())) {
					tyl = JTyl.$D80;
				}
				if (tyl != null) {
					etlpzGl.setTyx(tyl.min);
					etlpzGl.setTys(tyl.max);
				}
			}
			etlpzGl.setZzsd(zszrTp.getZzno());
		}

		etlpzGl.setScsj(new Date());
		etlpzGl.setStat(EEtlpz.dqj.key);
		if (etlpzGl.getId() == null || etlpzGl.getId().isEmpty()) {
			save(etlpzGl);
		}
		else {
			update(etlpzGl);
		}
	}

	@Override
	public String addEtlpzGl(String jbno, Date scsj) {
		EtlpzGl etlpzGl = new EtlpzGl();
		etlpzGl.setJbno(jbno);
		ZszrTp zszrTp = zsBO.getUniqueZszrTp(jbno);
		if (zszrTp == null) {
			return new Result(-1, "COIL NO.为" + jbno
					+ "的原板还未制作ETL生产指示书,因此不能对其做新增操作").toString();
		}
		ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
		etlpzGl.setFugm(zsdhTp.getFugm());
		String yqty = zsdhTp.getYqty();
		String ytyp = zsdhTp.getYtyp();
		etlpzGl.setYqty(yqty);
		etlpzGl.setYtyp(ytyp);
		etlpzGl.setFuzs(zsdhTp.getFuzs());
		etlpzGl.setFuzx(zsdhTp.getFuzx());
		etlpzGl.setFufs(zsdhTp.getFufs());
		etlpzGl.setFufx(zsdhTp.getFufx());
		JCr cr = JCr.get(zsdhTp.getHxcl());
		if (cr != null) {
			etlpzGl.setCrs(cr.max);
			etlpzGl.setCrx(cr.min);
		}
		// 合金
		String hjin = zsdhTp.getHjin();
		if (hjin != null && !hjin.isEmpty()) {
			CodeItem codeItem = codeBO.getCodeItem(CmnConstants.SINO_HJIN, hjin);
			Double $hjin = null;
			if (codeItem != null) {
				try {
					$hjin = Double.valueOf(codeItem.getValue());
				}
				catch (NumberFormatException e) {
				}
			}
			etlpzGl.setHjin($hjin);
		}
		CodeTyzl codeTyzl;
		if (ytyp != null && !ytyp.isEmpty() && yqty != null && !yqty.isEmpty()
				&& (codeTyzl = CodeTyzl.getByName(ytyp)) != null) {
			JTyl tyl = JTyl.get(yqty);
			if (tyl != null && JTyl.D80.type.equals(codeTyzl.key)
					&& JTyl.D80.name.equals(yqty)
					&& (hjin == null || hjin.isEmpty())) {
				tyl = JTyl.$D80;
			}
			if (tyl != null) {
				etlpzGl.setTyx(tyl.min);
				etlpzGl.setTys(tyl.max);
			}
		}
		etlpzGl.setZzsd(zszrTp.getZzno());

		etlpzGl.setScsj(scsj);
		etlpzGl.setStat(EEtlpz.ywc.key);
		dao.save(etlpzGl);
		return Result.SUCCESS;
	}

	@Override
	public void finish(String jbno) {
		if (jbno == null || jbno.isEmpty()) return;
		EtlpzGl etlpzGl = getByJbno(jbno);
		if (etlpzGl == null) {
			etlpzGl = new EtlpzGl();
			// etlpzGl.setJbno(jbno);
			// ZszrTp zszrTp = zsBO.getUniqueZszrTp(jbno);
			// if (zszrTp != null) {
			// ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
			// etlpzGl.setFugm(zsdhTp != null ? zsdhTp.getFugm() : null);
			// etlpzGl.setZzsd(zszrTp.getZzno());
			// }
		}
		etlpzGl.setJbno(jbno);
		ZszrTp zszrTp = zsBO.getUniqueZszrTp(jbno);
		if (zszrTp != null) {
			ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
			etlpzGl.setFugm(zsdhTp.getFugm());
			String yqty = zsdhTp.getYqty();
			String ytyp = zsdhTp.getYtyp();
			etlpzGl.setYqty(yqty);
			etlpzGl.setYtyp(ytyp);
			etlpzGl.setFuzs(zsdhTp.getFuzs());
			etlpzGl.setFuzx(zsdhTp.getFuzx());
			etlpzGl.setFufs(zsdhTp.getFufs());
			etlpzGl.setFufx(zsdhTp.getFufx());
			JCr cr = JCr.get(zsdhTp.getHxcl());
			if (cr != null) {
				etlpzGl.setCrs(cr.max);
				etlpzGl.setCrx(cr.min);
			}
			// 合金
			String hjin = zsdhTp.getHjin();
			if (hjin != null && !hjin.isEmpty()) {
				CodeItem codeItem = codeBO.getCodeItem(CmnConstants.SINO_HJIN, hjin);
				Double $hjin = null;
				if (codeItem != null) {
					try {
						$hjin = Double.valueOf(codeItem.getValue());
					}
					catch (NumberFormatException e) {
					}
				}
				etlpzGl.setHjin($hjin);
			}
			CodeTyzl codeTyzl;
			if (ytyp != null && !ytyp.isEmpty() && yqty != null
					&& !yqty.isEmpty()
					&& (codeTyzl = CodeTyzl.getByName(ytyp)) != null) {
				JTyl tyl = JTyl.get(yqty);
				if (tyl != null && JTyl.D80.type.equals(codeTyzl.key)
						&& JTyl.D80.name.equals(yqty)
						&& (hjin == null || hjin.isEmpty())) {
					tyl = JTyl.$D80;
				}
				if (tyl != null) {
					etlpzGl.setTyx(tyl.min);
					etlpzGl.setTys(tyl.max);
				}
			}
			etlpzGl.setZzsd(zszrTp.getZzno());
		}
		if (etlpzGl.getScsj() == null) {
			etlpzGl.setScsj(new Date());
		}
		etlpzGl.setStat(EEtlpz.ywc.key);
		if (etlpzGl.getId() == null || etlpzGl.getId().isEmpty()) {
			save(etlpzGl);
		}
		else {
			update(etlpzGl);
		}
	}

	@Override
	public void delete(String jbno) {
		dao.delete(jbno);
	}

	@Override
	public EtlpzGl get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void read(Serializable id, boolean readed) {
		dao.read(id, readed);
	}

	@Override
	public EtlpzGl getLatest(Date scsj) {
		return dao.getLatest(scsj);
	}

	@Override
	public boolean isExistedNew(Date scsjBegin, Date scsjEnd) {
		return dao.isExistedNew(scsjBegin, scsjEnd);
	}

	@Override
	public boolean isExistedNew() {
		return dao.isExistedNew();
	}

	@Override
	public EtlpzGl getByStat(String stat) {
		return dao.getByStat(stat);
	}

	@Override
	public int getPageSize(Date scsj, Integer size) {
		Long count = dao.count(scsj);
		if (count == null || count.longValue() == 0) {
			return 0;
		}
		int pageSize = (int) count.intValue() / size;
		if (count % size != 0) {
			pageSize++;
		}
		return pageSize;
	}

	@Override
	public List<EtlpzGl> query(Date scsjBegin, Date scsjEnd) {
		return dao.query(scsjBegin, scsjEnd);
	}

	public IEtlpzglDAO getDao() {
		return dao;
	}

	public void setDao(IEtlpzglDAO dao) {
		this.dao = dao;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public ISqlDAO getSqlDAO() {
		return sqlDAO;
	}

	public void setSqlDAO(ISqlDAO sqlDAO) {
		this.sqlDAO = sqlDAO;
	}

	@Override
	public void updateStatDqjToYwc() {
		dao.updateStatDqjToYwc();
	}

}
