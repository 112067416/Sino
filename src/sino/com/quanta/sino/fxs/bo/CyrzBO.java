package com.quanta.sino.fxs.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.JsUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.MktfxStat;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.fxs.bo.api.ICyrzBO;
import com.quanta.sino.fxs.dao.api.ICyrzDAO;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.CyrzSlxb;
import com.quanta.sino.orm.CyrzSlxbPk;

/**
 * <p>
 * 采样日志业务实现
 * </p>
 * <p>
 * create: 2011-1-7 上午09:27:18
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CyrzBO implements ICyrzBO {

	/**
	 * 采样日志数据访问接口
	 */
	private ICyrzDAO cyrzDAO;

	/**
	 * 序号业务接口
	 */
	private ISeqBO seqBo;

	/**
	 * 获取JS忽略字段
	 */
	private static final Set<String> JS_IGNORES = new HashSet<String>();

	/**
	 * 获取JS附加表达式
	 */
	private static final Set<String> JS_EXPRS = new HashSet<String>();

	static {
		JS_IGNORES.add("fxxm");
		JS_IGNORES.add("cywz");
		JS_EXPRS.add("fxxms");
		JS_EXPRS.add("cywzs");
	}

	@Override
	public void cydEtl(Cyrz entity) {
		entity.setId(seqBo.sequence(Seq.cyrzno.name));
		if (entity.getId() == null) {
			throw new CocoException(-1, "序号【" + Seq.cyrzno.description
					+ "】还没有配置");
		}
		String[] fxxms = entity.getFxxms();
		if (fxxms != null && fxxms.length > 0) {
			ECyfxxm eCyfxxm = null;
			String jsxb = ECyfxxm.SL;
			for (String fxxm : fxxms) {
				if ((eCyfxxm = ECyfxxm.getByName(fxxm)) == null) continue;
				if (!ECyfxxm.SL.equals(eCyfxxm.jsxb)) jsxb = eCyfxxm.jsxb;
			}
			entity.setJsxb(jsxb);
		}
		entity.setDate(new Date());
		entity.setStat(ECyfxxm.SL.equals(entity.getJsxb()) ? null
				: MktfxStat.CS.stat);
		entity.setFsxb(ECyfxxm.ETL);
		cyrzDAO.save(entity);
	}

	@Override
	public void cydSl(Cyrz entity) {
		entity.setId(seqBo.sequence(Seq.cyrzno.name));
		if (entity.getId() == null) {
			throw new CocoException(-1, "序号【" + Seq.cyrzno.description
					+ "】还没有配置");
		}
		Date date = new Date();
		entity.setSlsd(date);
		entity.setSlsy(date);
		entity.setDate(date);
		entity.setStat(MktfxStat.CS.stat);
		entity.setJsxb(ECyfxxm.SL_FXS);
		entity.setFsxb(ECyfxxm.SL);
		cyrzDAO.save(entity);
	}

	@Override
	public void update(Cyrz entity, UpdateType type) {
		Cyrz ref = cyrzDAO.getRef(entity.getId());
		if (ref == null) {
			throw new CocoException(-1, "数据已丢失");
		}
		Date date = new Date();
		if (type == UpdateType.slsd) {
			ref.setSlxb(entity.getSlxb());
			ref.setSlsd(date);
		}
		else if (type == UpdateType.slsy) {
			ref.setSlsy(date);
			ref.setSlBz(entity.getSlBz());
		}
		else if (type == UpdateType.fxsd) {
			ref.setFxsd(date);
			ref.setFxsdZt(true);
		}
		else if (type == UpdateType.fxsy) {
			if (ref.getFxsd() == null) {
				ref.setFxsd(date);
				ref.setFxsdZt(true);
			}
			ref.setFxsy(date);
			ref.setFxsyZt(true);
		}
		else if (type == UpdateType.fx) {
			ref.setFxsj(date);
			ref.setFxBz(entity.getFxBz());
			ref.setEnd(true);
		}
		else {
			ref.setBan(entity.getBan());
			ref.setBz(entity.getBz());
			ref.setCywz(entity.getCywz());
			ref.setDxl(entity.getDxl());
			ref.setFxxm(entity.getFxxm());
			ref.setJbno(entity.getJbno());
			ref.setTyl(entity.getTyl());
			ref.setZsno(entity.getZsno());
			ref.setZu(entity.getZu());
		}
	}

	@Override
	public void delete(Serializable id, String fsxb) {
		Cyrz ref = cyrzDAO.getRef(id);
		if (ref == null) {
			throw new CocoException(-1, "该记录已丢失,请联系管理员");
		}
		if (!fsxb.equals(ref.getFsxb())) {
			throw new CocoException(-1, "该采样单记录不是该线发送,不允许做删除操作");
		}
		ref.setDeleted(true);
	}

	@Override
	public Cyrz get(Serializable id) {
		return cyrzDAO.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		return JsUtils.toSimpleObject(cyrzDAO.get(id), JS_IGNORES, JS_EXPRS);
	}

	@Override
	public void query(QEntity<Cyrz> qentity) {
		cyrzDAO.query(qentity);
	}

	@Override
	public Cyrz getForSl(String xb) {
		return cyrzDAO.getForSl(xb);
	}

	@Override
	public Cyrz getForSl() {
		return cyrzDAO.getForSl();
	}

	@Override
	public Cyrz getForFxs() {
		return cyrzDAO.getForFxs();
	}

	@Override
	public void updateState(String id, String xb, UpdateType type) {
		Cyrz entity = cyrzDAO.getRef(id);
		if (entity == null) {
			throw new CocoException(-1, "数据已丢失");
		}
		if (type == UpdateType.slsd) {
			CyrzSlxbPk pk = new CyrzSlxbPk(id, xb);
			CyrzSlxb slxb = cyrzDAO.getCyrzSlxb(pk);
			if (slxb == null) {
				slxb = new CyrzSlxb();
				slxb.setId(id);
				slxb.setLine(xb);
				cyrzDAO.saveCyrzSlxb(slxb);
			}
		}
		else if (type == UpdateType.fxsd) {
			entity.setFxsdZt(true);
		}
		else if (type == UpdateType.fxsy) {
			entity.setFxsyZt(true);
		}
	}

	@Override
	public void updateState(String id, UpdateType type) {
		Cyrz entity = cyrzDAO.getRef(id);
		if (entity == null) {
			throw new CocoException(-1, "数据已丢失");
		}
		if (type == UpdateType.slsd) {
			entity.setSlZt(true);
		}
		else if (type == UpdateType.fxsd) {
			entity.setFxsdZt(true);
		}
		else if (type == UpdateType.fxsy) {
			entity.setFxsyZt(true);
		}
	}

	@Override
	public boolean isExisted(String zsno, String jbno) {
		return cyrzDAO.isExisted(zsno, jbno);
	}

	public ISeqBO getSeqBo() {
		return seqBo;
	}

	public void setSeqBo(ISeqBO seqBo) {
		this.seqBo = seqBo;
	}

	public ICyrzDAO getCyrzDAO() {
		return cyrzDAO;
	}

	public void setCyrzDAO(ICyrzDAO cyrzDAO) {
		this.cyrzDAO = cyrzDAO;
	}

}
