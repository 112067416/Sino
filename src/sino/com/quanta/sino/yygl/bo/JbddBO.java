package com.quanta.sino.yygl.bo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.EYN;
import com.quanta.sino.cmn.constants.JbddItemStat;
import com.quanta.sino.cmn.constants.JbddStat;
import com.quanta.sino.cmn.constants.Zjzt;
import com.quanta.sino.orm.Jbdd;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.orm.JbddItemLog;
import com.quanta.sino.yygl.bo.api.IJbddBO;
import com.quanta.sino.yygl.dao.api.IJbddDAO;
import com.quanta.sino.yygl.vo.JbddItemQE;
import com.quanta.sino.yygl.vo.JbddItemVO;
import com.quanta.sino.yygl.vo.JbddVO;
import com.quanta.sino.yygl.vo.JbddtjVO;

/**
 * <p>
 * 基板订单明细业务实现类
 * </p>
 * <p>
 * create: 2010-12-21 下午05:59:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddBO implements IJbddBO {

	/**
	 * 基板订购单数据访问接口
	 */
	private IJbddDAO dao;

	/**
	 * 调用序列号模块的bo接口
	 */
	private ISeqBO sqBo;

	/**
	 * 基板订单路径
	 */
	private String jbddListPath;

	/**
	 * 基板订单处理接口
	 */
	private ExcelbookDataExecuter<JbddVO> jbddListExec;

	private static final Set<String> EXCLUDE_FIELDS = new HashSet<String>();

	static {
		EXCLUDE_FIELDS.add("ddno");
		EXCLUDE_FIELDS.add("ddnm");
		EXCLUDE_FIELDS.add("stat");
		EXCLUDE_FIELDS.add("crea");
		EXCLUDE_FIELDS.add("id");
		EXCLUDE_FIELDS.add("jbdd");
		EXCLUDE_FIELDS.add("item");
		EXCLUDE_FIELDS.add("lock");
		// EXCLUDE_FIELDS.add("calc");
		// EXCLUDE_FIELDS.add("conf");
	}

	@Override
	public void saveItem(JbddItem entity) {
		entity.setId(null);
		entity.setStat(JbddItemStat.CS.stat);
		entity.setCrea(new Date());
		if (entity.getDhsl() != null && entity.getDhsl() > 0) {
			entity.setNwai(CodeNwx.NX);
			entity.setTotal(entity.getDhsl());
		}
		else if (entity.getCksl() != null && entity.getCksl() > 0) {
			entity.setNwai(CodeNwx.CK);
			entity.setTotal(entity.getCksl());
		}
		else {
			entity.setNwai(CodeNwx.NX);
			entity.setTotal(0);
		}
		entity.setZjzt(Zjzt.no.key);
		entity.setLock(EYN.N.key);
		dao.saveItem(entity);
	}

	@Override
	public String updateItem(JbddItem entity, String flag) {
		JbddItem item = dao.getItem(entity.getId());
		if (item == null) {
			return new Result(-1, "该记录不存在,请联系管理员").toString();
		}
		if (EYN.Y.key.equals(flag) && EYN.Y.key.equals(item.getLock())) {
			return new Result(-1, "该记录已锁,不允许做修改操作").toString();
		}
		if (entity.getDhsl() != null && entity.getDhsl() > 0) {
			entity.setNwai(CodeNwx.NX);
			entity.setTotal(entity.getDhsl());
		}
		else if (entity.getCksl() != null && entity.getCksl() > 0) {
			entity.setNwai(CodeNwx.CK);
			entity.setTotal(entity.getCksl());
		}
		else {
			entity.setNwai(CodeNwx.NX);
			entity.setTotal(0);
		}

		ReflectUtils.copy(item, entity, null, EXCLUDE_FIELDS, false);
		dao.updateItem(item);
		return Result.SUCCESS;
	}

	@Override
	public String deleteItem(String[] ids) {
		List<String> $ids = Arrays.asList(ids);
		dao.executeForValues("delete from JbddItem where id in(?)", $ids);
		dao.executeForValues("delete from JbddItemLog where jbddItem in (?)", $ids);
		return Result.SUCCESS;
	}

	@Override
	public String updateLock(String[] ids, String lock) {
		List<String> $ids = Arrays.asList(ids);
		dao.executeForValues("update JbddItem set lock=? where id in(?)", $ids, lock);
		return Result.SUCCESS;
	}

	@Override
	public void copyItem(String[] ids) {
		JbddItem entity = null;
		JbddItem $entity = null;
		Date date = new Date();
		for (String id : ids) {
			entity = getItem(id);
			if (entity == null) continue;
			$entity = new JbddItem();
			ReflectUtils.copy($entity, entity, false);
			$entity.setId(null);
			$entity.setCrea(date);
			$entity.setStat(JbddItemStat.CS.stat);
			$entity.setJbdd(null);
			// $entity.setCalc(null);
			$entity.setConf(null);
			$entity.setZjzt(Zjzt.no.key);
			$entity.setLock(EYN.N.key);
			$entity.setItem(null);
			dao.saveItem($entity);
		}
	}

	@Override
	public String getForJs(String abbr, String face, String yuny, Double houa,
			Double houb, Double width, String ddno) {
		String ql = "from JbddItem where abbr=? and face=? and yuny=? and houa=? and houb=? and width=? and ddno=? order by crea desc";
		JbddItem entity = dao.getUnique(ql, abbr, face, yuny, houa, houb, width, ddno);
		if (entity == null) {
			return new Result(-1, "该基板订购单信息不存在").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public String getForJs(Serializable id) {
		JbddItem entity = dao.getItem(id);
		if (entity == null) {
			return new Result(-1, "该基板订购单信息不存在").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void queryItem(QEntity<JbddItem> qentity) {
		dao.queryItem(qentity);
		if (qentity.getDatas().size() > 0) {
			for (JbddItem item : qentity.getDatas()) {
				if (item.getJbdd() == null) continue;
				item.getJbdd().getJbno();
			}
		}
	}

	@Override
	public JbddItem getItem(Serializable id) {
		return dao.getItem(id);
	}

	@Override
	public Jbdd getJbdd(Serializable id) {
		return dao.getJdbb(id);
	}

	@Override
	public void queryJbdd(QEntity<Jbdd> qentity) {
		dao.queryJdbb(qentity);
	}

	@Override
	public String build(String[] ids, User user, String jbno) {
		if (dao.isExistedJbno(jbno)) {
			return new Result(-1, "基板订单号" + jbno + "已存在").toString();
		}
		Jbdd entity = new Jbdd();
		entity.setCrea(new Date());
		entity.setDdno(user.getNo());
		entity.setDdnm(user.getName());
		entity.setJbno(jbno);
		entity.setStat(JbddStat.WFS.stat);
		dao.saveJdbb(entity);
		JbddItem item = null;
		for (String id : ids) {
			item = dao.getItem(id);
			if (item == null) continue;
			item.setJbdd(entity);
			item.setStat(JbddItemStat.YZZ.stat);
			dao.updateItem(item);
		}
		return Result.SUCCESS;
	}

	@Override
	public void zjJbdd(String[] ids, String jbdd) {
		JbddItemLog log = null;
		Jbdd entity = dao.getJdbb(jbdd);
		if (entity == null) return;
		JbddItem item = null;
		int index = dao.getMax(entity.getId()) + 1;
		Date date = DateUtils.parse(new Date(), "yyyy-MM-dd");
		for (String id : ids) {
			item = dao.getItem(id);
			if (item == null) continue;
			item.setJbdd(entity);
			item.setStat(JbddItemStat.YZZ.stat);
			item.setZjzt(Zjzt.yes.key);
			item.setZjrq(date);
			if (JbddStat.YFS.stat.equals(entity.getStat())) {

				item.setItem(index++);
				item.setLock(EYN.Y.key);

				log = new JbddItemLog();
				ReflectUtils.copy(log, item, null, null, false);
				log.setJbddItem(item.getId());
				log.setId(null);
				dao.saveLog(log);
			}
			dao.updateItem(item);
		}
	}

	@Override
	public void bjJbdd(String[] ids, String jbdd) {
		JbddItemLog log = null;
		Jbdd entity = dao.getJdbb(jbdd);
		if (entity == null) return;
		JbddItem item = null;
		int index = dao.getMax(entity.getId()) + 1;
		for (String id : ids) {
			item = dao.getItem(id);
			if (item == null) continue;
			item.setJbdd(entity);
			item.setStat(JbddItemStat.YZZ.stat);
			item.setZjzt(Zjzt.no.key);
			item.setZjrq(null);
			if (JbddStat.YFS.stat.equals(entity.getStat())) {

				item.setItem(index++);
				item.setLock(EYN.Y.key);

				log = new JbddItemLog();
				ReflectUtils.copy(log, item, null, null, false);
				log.setJbddItem(item.getId());
				log.setId(null);
				dao.saveLog(log);
			}
			dao.updateItem(item);
		}
	}

	@Override
	public void ycJbdd(String[] ids) {
		JbddItem item = null;
		for (String id : ids) {
			item = dao.getItem(id);
			if (item == null) continue;
			item.setJbdd(null);
			item.setStat(JbddItemStat.CS.stat);
			item.setZjzt(Zjzt.no.key);
			item.setZjrq(null);
			item.setItem(null);
			dao.updateItem(item);
			dao.deleteLog(item.getId());
		}
	}

	@Override
	public void deleteJbdd(String pid) {
		JbddItemQE page = new JbddItemQE();
		page.setPid(pid);
		page.setSize(-1);
		dao.queryItem(page);
		List<JbddItem> items = page.getDatas();
		if (items.size() > 0) {
			for (JbddItem item : items) {
				item.setStat(JbddItemStat.CS.stat);
				item.setJbdd(null);
				dao.updateItem(item);
				dao.deleteLog(item.getId());
			}
		}
		dao.deleteJbdd(pid);
	}

	@Override
	public JbddtjVO getTj(String pid, String stat, String ddno, String abbr) {
		return dao.getTj(pid, stat, ddno, abbr);
	}

	@Override
	public String updateJbno(String id, String jbno) {
		if (dao.isExistedJbno(jbno)) {
			return new Result(-1, "基板订单号" + jbno + "已存在").toString();
		}
		Jbdd entity = dao.getJdbb(id);
		if (entity == null) {
			return new Result(-1, "基板订单" + jbno + "不存在").toString();
		}
		entity.setJbno(jbno);
		dao.updateJdbb(entity);
		return Result.SUCCESS;

	}

	@Override
	public void fetchJbdd(JbddVO vo, OutputStream os) {
		String jbno = vo.getJbno();
		List<JbddItemVO> itemVOS = null;
		JbddItemVO itemVO = null;
		Jbdd jbdd = dao.getJdbbByJbno(jbno);
		if (jbdd == null) return;
		String stat = jbdd.getStat();
		vo.setCrea(jbdd.getCrea());
		vo.setStat(stat);

		// 国内
		List<JbddItem> items = dao.findJbddItems(jbno, CodeNwx.NX, Zjzt.no.key);
		if (items.size() > 0) {
			itemVOS = new ArrayList<JbddItemVO>();
			for (JbddItem item : items) {
				itemVO = new JbddItemVO();
				itemVO.setItem(item);
				itemVO.setLog(dao.getLog(item.getId()));
				itemVOS.add(itemVO);
			}
			vo.setNxItems(itemVOS);
		}

		// 国外
		items = dao.findJbddItems(jbno, CodeNwx.CK, Zjzt.no.key);
		if (items.size() > 0) {
			itemVOS = new ArrayList<JbddItemVO>();
			for (JbddItem item : items) {
				itemVO = new JbddItemVO();
				itemVO.setItem(item);
				itemVO.setLog(dao.getLog(item.getId()));
				itemVOS.add(itemVO);
			}
			vo.setWxItems(itemVOS);
		}

		// 追加
		items = dao.findJbddItems(jbno, null, Zjzt.yes.key);
		if (items.size() > 0) {
			itemVOS = new ArrayList<JbddItemVO>();
			for (JbddItem item : items) {
				itemVO = new JbddItemVO();
				itemVO.setItem(item);
				itemVO.setLog(dao.getLog(item.getId()));
				itemVOS.add(itemVO);
			}
			vo.setZjItems(itemVOS);
		}

		ExcelUtils.fillData(jbddListPath, vo, jbddListExec, os);
	}

	@Override
	public void updateJdbbStat(String[] ids, String stat) {
		Jbdd entity = null;
		List<JbddItem> items = null;
		JbddItemLog log = null;
		for (String id : ids) {
			entity = dao.getJdbb(id);
			if (entity == null) continue;
			entity.setStat(stat);
			dao.updateJdbb(entity);
			items = dao.findItems(id);
			if (items == null || items.size() == 0) continue;
			if (JbddStat.WFS.stat.equals(stat)) {
				for (JbddItem item : items) {
					item.setItem(null);
					dao.updateItem(item);
					dao.deleteLog(item.getId());
				}
			}
			else {
				int index = 1;
				for (JbddItem item : items) {
					log = new JbddItemLog();
					ReflectUtils.copy(log, item, null, null, false);
					log.setJbddItem(item.getId());
					log.setId(null);
					dao.saveLog(log);
					item.setItem(index++);
					item.setLock(EYN.Y.key);
					dao.updateItem(item);
				}
			}
		}
	}

	public IJbddDAO getDao() {
		return dao;
	}

	public void setDao(IJbddDAO dao) {
		this.dao = dao;
	}

	public ISeqBO getSqBo() {
		return sqBo;
	}

	public void setSqBo(ISeqBO sqBo) {
		this.sqBo = sqBo;
	}

	public String getJbddListPath() {
		return jbddListPath;
	}

	public void setJbddListPath(String jbddListPath) {
		this.jbddListPath = jbddListPath;
	}

	public ExcelbookDataExecuter<JbddVO> getJbddListExec() {
		return jbddListExec;
	}

	public void setJbddListExec(ExcelbookDataExecuter<JbddVO> jbddListExec) {
		this.jbddListExec = jbddListExec;
	}

}
