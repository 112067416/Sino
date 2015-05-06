package com.quanta.sino.fxs.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.fxs.bo.api.IFxzyrzBO;
import com.quanta.sino.fxs.dao.api.IFxzyRzYcsxDAO;
import com.quanta.sino.fxs.dao.api.IFxzyrzDAO;
import com.quanta.sino.fxs.vo.Dhxx;
import com.quanta.sino.fxs.vo.YcsxVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.FxzyRz;
import com.quanta.sino.orm.FxzyRzYcsx;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 分析作业日志表
 * </p>
 * <p>
 * create: 2011-1-7 上午11:27:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FxzyrzBO implements IFxzyrzBO {

	/**
	 * 分析作业日志数据访问接口
	 */
	private IFxzyrzDAO dao;

	/**
	 * 分析作业日志异常事项数据访问接口
	 */
	private IFxzyRzYcsxDAO fxzyRzYcsxDAO;

	/**
	 * 指示书业务接口
	 */
	private IZsBO zsBO;

	/**
	 * 
	 */
	private IDhBO dhBO;

	/**
	 * 
	 */
	private static final Set<String> EXCLUDE_FIELDS = new HashSet<String>();

	static {
		EXCLUDE_FIELDS.add("cjno");
		EXCLUDE_FIELDS.add("cjnm");
		EXCLUDE_FIELDS.add("cjsj");
		EXCLUDE_FIELDS.add("ycsx");
	}

	@Override
	public void save(FxzyRz entity, User user) {
		entity.setCjno(user.getNo());
		entity.setCjnm(user.getName());
		entity.setCjsj(new Date());
		dao.save(entity);
	}

	@Override
	public void update(FxzyRz entity, User user) {
		FxzyRz fxzyRz = dao.get(entity.getId());
		ReflectUtils.copy(fxzyRz, entity, null, EXCLUDE_FIELDS, false);
		fxzyRz.setXgno(user.getNo());
		fxzyRz.setXgnm(user.getName());
		fxzyRz.setXgsj(new Date());
		dao.update(fxzyRz);
	}

	@Override
	public void query(QEntity<FxzyRz> qentity) {
		dao.query(qentity);
	}

	@Override
	public FxzyRz get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void delete(String[] ids) {
		fxzyRzYcsxDAO.delete(Arrays.asList(ids));
		dao.delete(Arrays.asList(ids));
	}

	@Override
	public List<FxzyRz> findFxzyPrints(String[] ids) {
		return dao.find(Arrays.asList(ids));
	}

	@Override
	public List<FxzyRzYcsx> find(String pid) {
		return fxzyRzYcsxDAO.find(pid);
	}

	@Override
	public String getDhForJs(String jbno) {
		ZszrTp zszrTp = zsBO.getUniqueZszrTp(jbno);
		if (zszrTp == null) {
			return new Result(-1, "Coil No." + jbno + "还未下生产指示").toString();
		}
		ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
		if (zsdhTp == null) {
			return new Result(-1, "Coil No." + jbno + "还未下生产指示").toString();
		}
		DhuoTp dhuoTp = dhBO.get(SinoUtils.parseDhuoPk(zsdhTp.getDhno()));
		if (dhuoTp == null) {
			return new Result(-1, "订货No." + zsdhTp.getDhno() + "不存在").toString();
		}
		Dhxx dhxx = new Dhxx();
		dhxx.setHouu(dhuoTp.getHouu());
		dhxx.setKuan(dhuoTp.getKuan());
		dhxx.setCang(dhuoTp.getCang());
		dhxx.setDhno(zsdhTp.getDhno());
		dhxx.setAbbr(dhuoTp.getAbbr());
		dhxx.setYuny(dhuoTp.getYuny());
		dhxx.setCond(dhuoTp.getCond());
		dhxx.setFace(zsdhTp.getFace());
		dhxx.setDhfz(zsdhTp.getDhfz());
		dhxx.setYqty(zsdhTp.getYqty());
		return new Result(dhxx).toJsObject();
	}

	@Override
	public String saveYcsx(YcsxVO vo) {
		// if (vo.getItems() == null || vo.getItems().length == 0) {
		// return new Result(-1, "还未增加异常事项").toString();
		// }
		String pid = vo.getPid();
		ZszrTp zszrTp;
		ZsdhTp zsdhTp;
		String jbno;
		List<FxzyRzYcsx> ycsxs1 = null;
		List<FxzyRzYcsx> ycsxs2 = null;
		if (vo.getItems() != null && vo.getItems().length > 0) {
			ycsxs1 = new ArrayList<FxzyRzYcsx>();
			ycsxs2 = new ArrayList<FxzyRzYcsx>();
			for (FxzyRzYcsx ycsx : vo.getItems()) {
				ycsx.setPid(pid);
				jbno = ycsx.getJbno();
				if (ycsx.getId() == null || ycsx.getId().isEmpty()) {
					ycsxs2.add(ycsx);
				}
				else {
					ycsxs1.add(ycsx);
				}
				zszrTp = zsBO.getUniqueZszrTp(jbno);
				if (zszrTp == null) {
					return new Result(-1, "Coil No." + jbno + "还未下生产指示").toString();
				}
				zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
				if (zsdhTp == null) {
					return new Result(-1, "Coil No." + jbno + "还未下生产指示").toString();
				}
				DhuoTp dhuoTp = dhBO.get(SinoUtils.parseDhuoPk(zsdhTp.getDhno()));
				if (dhuoTp == null) {
					return new Result(-1, "订货No." + zsdhTp.getDhno() + "不存在").toString();
				}
				ycsx.setHouu(dhuoTp.getHouu());
				ycsx.setKuan(dhuoTp.getKuan());
				ycsx.setCang(dhuoTp.getCang());
				ycsx.setDhno(zsdhTp.getDhno());
				ycsx.setAbbr(dhuoTp.getAbbr());
				ycsx.setYuny(dhuoTp.getYuny());
				ycsx.setCond(dhuoTp.getCond());
				ycsx.setFace(dhuoTp.getFace());
				String dhfz = zsdhTp.getDhfz();
				if (dhfz != null && !dhfz.isEmpty()) {
					String[] dhfzs = dhfz.split("/");
					ycsx.setFuzm(dhfzs[0].replaceAll("\\D", ""));
					ycsx.setFufm(dhfzs.length > 0 ? dhfzs[1].replaceAll("\\D", "")
							: dhfzs[0].replaceAll("\\D", ""));
				}
				ycsx.setYqty(zsdhTp.getYqty());
			}
		}
		String ycsx = vo.getYcsx();
		if (ycsx != null && !ycsx.isEmpty()) {
			FxzyRz fxzyRz = dao.get(pid);
			fxzyRz.setYcsx(ycsx);
			dao.update(fxzyRz);
		}

		fxzyRzYcsxDAO.deleteByPid(vo.getPid());
		if (ycsxs1 != null && ycsxs1.size() > 0) {
			fxzyRzYcsxDAO.updateAll(ycsxs1);
		}
		if (ycsxs2 != null && ycsxs2.size() > 0) {
			fxzyRzYcsxDAO.saveAll(ycsxs2);
		}
		return Result.SUCCESS;
	}

	@Override
	public boolean isFxzyRzYcsx(String pid) {
		return fxzyRzYcsxDAO.isFxzyRzYcsx(pid);
	}

	public IFxzyrzDAO getDao() {
		return dao;
	}

	public void setDao(IFxzyrzDAO dao) {
		this.dao = dao;
	}

	public IFxzyRzYcsxDAO getFxzyRzYcsxDAO() {
		return fxzyRzYcsxDAO;
	}

	public void setFxzyRzYcsxDAO(IFxzyRzYcsxDAO fxzyRzYcsxDAO) {
		this.fxzyRzYcsxDAO = fxzyRzYcsxDAO;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

}
