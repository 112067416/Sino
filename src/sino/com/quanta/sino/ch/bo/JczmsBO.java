package com.quanta.sino.ch.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.dao.api.IJczmsDAO;
import com.quanta.sino.ch.vo.JczmsVO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.ZpnoCd;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.orm.Jczms;
import com.quanta.sino.orm.JczmsItem;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

public class JczmsBO implements IJczmsBO {

	/**
	 * 检查证明书数据访问接口
	 */
	private IJczmsDAO dao;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBO;

	/**
	 * 原板业务接口
	 */
	private IYcaitpBO ycaitpBO;

	/**
	 * 装箱指示书业务接口
	 */
	private IZxzsBO zxzsBO;

	/**
	 * 订货管理业务接口
	 */
	private IDhBO dhBO;

	/**
	 * 用户管理业务接口
	 */
	private IYongBO yongBO;

	@Override
	public void outJczms(String zxno, Date chri, List<JczmsVO> jczmsVos,
			DhuoTp dhuoTp, User user) {
		// 订货合同no
		// 制品检查证明书做成（当订货合同唯一时做制品检查证明书）
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String pzno = dhuoTp.getPzno();
		String jcno = sdf.format(chri) + "-" + pzno + "-"
				+ zxno.replaceAll("^0+", "");
		Code118 code118 = Code118.get(pzno.substring(0, 1));
		JczmsItem jczmsItem = null;
		Jczms jczms = null;
		Map<String, JczmsItem> maps = new HashMap<String, JczmsItem>();
		String rczpno = null;
		Integer jszl = 0;
		Integer jssu = 0;
		Integer bzzs = 0;
		Integer jbcn = 0;
		List<String> zznos = new ArrayList<String>();
		for (JczmsVO jczmsVO : jczmsVos) {
			rczpno = jczmsVO.getRczpno();
			if (!zznos.contains(jczmsVO.getZzno())) {
				zznos.add(jczmsVO.getZzno());
			}
			if ((jczmsItem = maps.get(rczpno)) == null) {
				jszl = 0;
				jssu = 0;
				bzzs = 0;
				jbcn = 0;
				jczmsItem = parseJczmsItem(jczmsVO, rczpno);
				maps.put(rczpno, jczmsItem);
			}
			jszl += jczmsVO.getZpzl();
			jssu += 1;
			if (Code118.coil.key.equals(code118.key)) {
				jbcn += (jczmsVO.getJscn() != null ? jczmsVO.getJscn() : 0);
			}
			else {
				bzzs += (jczmsVO.getZshu() != null ? jczmsVO.getZshu() : 0);
			}
			// 制品重量
			jczmsItem.setJszl(jszl);
			// 制品数量
			jczmsItem.setJssu(jssu);
			// 包装张数
			jczmsItem.setBzzs(bzzs);
			// 卷板长度
			jczmsItem.setJbcn(jbcn);
		}
		String ywbz = null, zwbz = null;
		List<CodeItem> citems = null;
		if (zznos.size() > 0) {
			citems = codeBO.findItems(CmnConstants.SINO_JCZMS_BZ, zznos.get(0));
			if (citems.size() > 0) {
				zwbz = citems.get(0).getRemark();
				ywbz = citems.size() > 1 ? citems.get(1).getRemark() : null;
			}
		}
		List<JczmsItem> jczmsItems = new ArrayList<JczmsItem>();
		Iterator<String> iter = maps.keySet().iterator();
		while (iter.hasNext()) {
			jczmsItems.add(maps.get(iter.next()));
		}
		Integer size = jczmsItems.size();
		// 制品检查证明书页数
		int sum = size / ChglConstants.JCZMS_SIZE;
		if (size % ChglConstants.JCZMS_SIZE != 0) {
			sum++;
		}
		int chsu;
		double chzl;
		int ying;
		ZpngTp zpngTp = null;
		List<JczmsItem> items = null;
		for (int pageSize = 0; pageSize < sum; pageSize++) {
			chsu = 0;
			chzl = 0;
			bzzs = 0;
			jbcn = 0;
			jczms = parseJczms(dhuoTp, zxno, chri, user);
			jczms.setJcno(jcno + "-" + String.format("%04d", pageSize + 1));
			items = new ArrayList<JczmsItem>();
			for (int index = pageSize * ChglConstants.JCZMS_SIZE; index < (pageSize + 1)
					* ChglConstants.JCZMS_SIZE; index++) {
				if (index >= size) {
					break;
				}
				jczmsItem = jczmsItems.get(index);
				rczpno = jczmsItem.getZpno();
				if ((zpngTp = zpBO.getByRczpno(rczpno)) == null) continue;
				ying = zpngTp.getYing() != null ? zpngTp.getYing() : 0;
				jczmsItem.setYing((NumberUtils.formatDouToInt(ying / 10d, 0)));
				if (rczpno.length() == ZpnoCd.six.len
						|| rczpno.length() == ZpnoCd.eight.len) {
					jczmsItem.setZpno(rczpno + "0");
				}
				jczmsItem.setJczms(jczms);
				items.add(jczmsItem);
				chsu += jczmsItem.getJssu();
				chzl += (jczmsItem.getJszl() / 1000d);
				bzzs += (jczmsItem.getBzzs() != null ? jczmsItem.getBzzs() : 0);
				jbcn += (jczmsItem.getJbcn() != null ? jczmsItem.getJbcn() : 0);
			}
			jczms.setChsu(chsu);

			jczms.setChzl(NumberUtils.format(chzl, 3));
			jczms.setBzzs(bzzs);
			jczms.setJbcn(jbcn);
			jczms.setYwbz(ywbz != null && !ywbz.isEmpty() ? ywbz : null);
			jczms.setZwbz(zwbz != null && !zwbz.isEmpty() ? zwbz : null);
			dao.save(jczms);
			// 保存检查证明书明细表
			dao.saveAll(items);
		}
	}

	private JczmsItem parseJczmsItem(JczmsVO jczmsVO, String rczpno) {
		JczmsItem jczmsItem = new JczmsItem();
		jczmsItem.setZpno(rczpno);
		jczmsItem.setCpcn(jczmsVO.getCang());
		jczmsItem.setCpkn(jczmsVO.getKuan());
		jczmsItem.setCpho(jczmsVO.getHouu());
		jczmsItem.setJsmz(jczmsVO.getMazl());
		double sczm = jczmsVO.getSczm() != null ? jczmsVO.getSczm() : 0d;
		double scfm = jczmsVO.getScfm() != null ? jczmsVO.getScfm() : 0d;
		jczmsItem.setSczm(NumberUtils.format(sczm, 1));
		jczmsItem.setScfm(NumberUtils.format(scfm, 1));
		int ying = jczmsVO.getYing() != null ? jczmsVO.getYing() : 0;
		jczmsItem.setYing(NumberUtils.formatDouToInt(ying / 10d, 0));
		JchaTp jchaTp = ycaitpBO.getJchatp(rczpno.substring(0, 6));
		if (jchaTp != null) {
			jczmsItem.setChui(jchaTp.getChui());
			jczmsItem.setCfcc(jchaTp.getCfcc());
			jczmsItem.setCfmn(jchaTp.getCfmn());
			jczmsItem.setCfpp(jchaTp.getCfpp());
			jczmsItem.setCfsi(jchaTp.getCfsi());
			jczmsItem.setCfss(jchaTp.getCfss());
		}
		return jczmsItem;
	}

	/**
	 * <p>
	 * 写检查证明书主表
	 * </p>
	 * 
	 * @param dhuoTp
	 * @param zxno
	 * @param date
	 * @param user
	 * @return Jczms
	 */
	private Jczms parseJczms(DhuoTp dhuoTp, String zxno, Date date, User user) {
		Jczms jczms = new Jczms();
		jczms.setDhno(dhuoTp.getDhno());
		jczms.setLine(dhuoTp.getLine());
		jczms.setZxno(zxno);
		YongMp yongMp = yongBO.get(dhuoTp.getUser());
		Integer jcha = yongMp != null ? yongMp.getJcha() : 1;
		if (jcha == null || jcha.intValue() == 0) {
			jcha = 1;
		}
		jczms.setJcha(jcha);
		if (yongMp != null) {
			jczms.setUser(yongMp.getUser());
			jczms.setAbbr(yongMp.getAbbr());
			jczms.setName(yongMp.getRsv1());
		}
		else {
			jczms.setUser(dhuoTp.getUser());
			jczms.setAbbr(dhuoTp.getAbbr());
			jczms.setName(dhuoTp.getName());
		}
		jczms.setSsno(dhuoTp.getSsno());
		jczms.setSsmc(dhuoTp.getSsnm());
		CodeItem item = null;
		String pz1 = "", pz2 = "", face = "", pzno = dhuoTp.getPzno();
		if (pzno != null
				&& (item = codeBO.getCodeItem(CmnConstants.CODE_118, pzno.substring(0, 1))) != null) {
			pz1 = item.getValue();
		}
		if (pzno != null
				&& (item = codeBO.getCodeItem(CmnConstants.CODE_119, pzno.substring(1, 2))) != null) {
			pz2 = item.getValue();
		}
		if ((item = codeBO.getCodeItem(CmnConstants.CODE_005, dhuoTp.getFace())) != null) {
			face = item.getValue();
		}
		// 品名由订货DB表中的品种第一位（品种）、品种第二位（等级）和表面精整组合表示
		jczms.setPm(pz1 + "  " + pz2 + "  " + face);
		jczms.setGgnm(dhuoTp.getGgnm());
		jczms.setZzri(date);
		jczms.setFudw(dhuoTp.getFudw());
		jczms.setFuzm(dhuoTp.getFuzm());
		jczms.setFufm(dhuoTp.getFufm());
		jczms.setDdno(user.getNo());
		jczms.setYmin(dhuoTp.getYmin());
		jczms.setYmax(dhuoTp.getYmax());
		jczms.setFuzs(dhuoTp.getFuzs());
		jczms.setFuzx(dhuoTp.getFuzx());
		jczms.setFufs(dhuoTp.getFufs());
		jczms.setFufx(dhuoTp.getFufx());
		jczms.setJcts(dhuoTp.getJcts());
		return jczms;
	}

	@Override
	public List<Jczms> find(String zxno) {
		return dao.query(zxno);
	}

	@Override
	public List<Jczms> find(String[] zxnos) {
		return dao.query(Arrays.asList(zxnos));
	}

	@Override
	public List<JczmsItem> findMx(String refId) {
		return dao.findMx(refId);
	}

	@Override
	public void save(Jczms jczms) {
		dao.save(jczms);
	}

	@Override
	public void query(QEntity<JczmsItem> qentity) {
		dao.queryJczms(qentity);
		List<JczmsItem> jczmsItems = qentity.getDatas();
		for (JczmsItem entity : jczmsItems) {
			entity.getJczms().getJcno();
			entity.getJczms().getZxno();
			entity.getJczms().getAbbr();
			entity.getJczms().getZzri();
		}
	}

	@Override
	public void update(JczmsItem mx) {
		// 获取原检查证明书明细
		JczmsItem jczmsItem = dao.getItem(mx.getId());
		// 将修改过的拷贝到原来的数据中
		ReflectUtils.copy(jczmsItem, mx, true);
		dao.update(jczmsItem);
	}

	@Override
	public void updateAll(List<JczmsItem> mxs) {
		JczmsItem jczmsItem = null;
		int ying = 0;
		for (JczmsItem item : mxs) {
			ying = item.getYing() != null ? item.getYing() : 0;
			jczmsItem = dao.getItem(item.getId());
			if (jczmsItem == null) continue;
			jczmsItem.setCfcc(item.getCfcc());
			jczmsItem.setCfsi(item.getCfsi());
			jczmsItem.setCfmn(item.getCfmn());
			jczmsItem.setCfpp(item.getCfpp());
			jczmsItem.setCfss(item.getCfss());
			jczmsItem.setSczm(item.getSczm());
			jczmsItem.setScfm(item.getScfm());
			// 是否解除硬度判定
			jczmsItem.setCfy1(item.getCfy1());
			jczmsItem.setYing(ying);
			dao.update(jczmsItem);
		}
	}

	@Override
	public Jczms get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public int getPageSize(String id, Integer size) {
		long count = dao.count(id);
		int pageSize = (int) count / size;
		if (count % size != 0) {
			pageSize++;
		}
		return pageSize;
	}

	@Override
	public void zzJczms(String[] zxnos, User user) {
		// List<Zng1Tp> zng1Tps = null;
		// List<Zng2Tp> zng2Tps = null;
		// DhuoTp dhuoTp = null;
		// for (String zxno : zxnos) {
		// dao.deleteItems(zxno);
		// dao.delete(zxno);
		// zng1Tps = zxzsBO.find(zxno);
		// if (zng1Tps == null || zng1Tps.size() == 0) continue;
		// for (Zng1Tp zng1Tp : zng1Tps) {
		// zng2Tps = zxzsBO.findZxzsMx(zxno, zng1Tp.getDhno(),
		// zng1Tp.getLine());
		// if (zng2Tps == null || zng2Tps.size() == 0) continue;
		// dhuoTp = dhBO.get(new DhuoTpPk(zng1Tp.getDhno(),
		// zng1Tp.getLine()));
		// if (dhuoTp == null) continue;
		// outJczms(zng1Tp, zng2Tps, dhuoTp, user);
		// }
		// }
		List<String> $zxnos = Arrays.asList(zxnos);
		dao.deleteItems($zxnos);
		dao.delete($zxnos);
		List<Zng1Tp> zng1Tps = null;
		List<JczmsVO> jczmsVos = null;
		DhuoTp dhuoTp = null;
		for (String zxno : zxnos) {
			zng1Tps = zxzsBO.find(zxno);
			if (zng1Tps == null || zng1Tps.size() == 0) continue;
			for (Zng1Tp zng1Tp : zng1Tps) {
				dhuoTp = dhBO.get(new DhuoTpPk(zng1Tp.getDhno(),
						zng1Tp.getLine()));
				if (dhuoTp == null) continue;
				jczmsVos = dao.findJczmsVos(zxno, zng1Tp.getDhno(), zng1Tp.getLine(), dhuoTp.getPzno());
				if (jczmsVos == null || jczmsVos.size() == 0) continue;
				outJczms(zxno, zng1Tp.getZxzsTp().getChri(), jczmsVos, dhuoTp, user);
			}
		}
	}

	@Override
	public String checkZzJczms(List<String> zxnos) {
		String errorZxno = dao.checkZzJczms(zxnos);
		if (errorZxno != null && !errorZxno.isEmpty()) {
			dao.deleteItems(zxnos);
			dao.delete(zxnos);
			return new Result(-1, "装箱指示书No." + errorZxno + "制作失败,请重新制作。").toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public String checkJczms(String[] zxnos) {
		StringBuilder errors = new StringBuilder();
		for (String zxno : zxnos) {
			if (dao.isExist(zxno)) continue;
			if (errors.length() == 0) {
				errors.append(zxno);
			}
			else {
				errors.append("、").append(zxno);
			}
		}
		if (errors.length() > 0) {
			return new Result(-1, "装箱指示书No." + errors.toString()
					+ "对应的制品检查证明书还未制作").toString();
		}
		return Result.SUCCESS;
	}

	public IJczmsDAO getDao() {
		return dao;
	}

	public void setDao(IJczmsDAO dao) {
		this.dao = dao;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

	public IZxzsBO getZxzsBO() {
		return zxzsBO;
	}

	public void setZxzsBO(IZxzsBO zxzsBO) {
		this.zxzsBO = zxzsBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

}
