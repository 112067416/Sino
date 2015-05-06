package com.quanta.sino.fxs.bo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeTyzl;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.EEtlpz;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhFzlDw;
import com.quanta.sino.fxs.bo.api.IMktfxBO;
import com.quanta.sino.fxs.dao.api.IChpdbDAO;
import com.quanta.sino.fxs.dao.api.ICyrzDAO;
import com.quanta.sino.fxs.dao.api.IMktfxDAO;
import com.quanta.sino.fxs.vo.JCr;
import com.quanta.sino.fxs.vo.JTcv;
import com.quanta.sino.fxs.vo.JTyl;
import com.quanta.sino.fxs.vo.MktfjVO;
import com.quanta.sino.fxs.vo.MktfxVO;
import com.quanta.sino.orm.Chpdb;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.orm.MktfxshJl;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.ZszrTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 马口铁检测数据维护
 * </p>
 * <p>
 * create: 2011-4-19 下午10:06:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class MktfxBO implements IMktfxBO {

	/**
	 * 马口铁数据访问接口
	 */
	private IMktfxDAO dao;

	/**
	 * 原板管理业务接口
	 */
	private IYcaitpBO ycaiBo;

	/**
	 * 订货管理业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 采样日志数据访问接口
	 */
	private ICyrzDAO cyrzDAO;

	/**
	 * ETL品质管理业务接口
	 */
	private IEtlpzglBO etlpzglBO;

	/**
	 * 指示书业务接口
	 */
	private IZsBO zsBO;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;

	/**
	 * 出荷判定数据访问接口
	 */
	private IChpdbDAO chpdbDAO;

	/**
	 * 
	 */
	private static final Set<String> EXCLUDE_FIELDS = new HashSet<String>();

	static {
		EXCLUDE_FIELDS.add("cjno");
		EXCLUDE_FIELDS.add("cjnm");
		EXCLUDE_FIELDS.add("cjsj");
	}

	@Override
	public void save(MktfxshJl entity, User user) {
		updateCyrz(entity);
		entity.setCjno(user.getNo());
		entity.setCjnm(user.getName());
		entity.setCjsj(new Date());
		dao.save(entity);
		EtlpzGl etlpzGl = parseEtlpzGl(entity, etlpzglBO.getByJbno(entity.getJbno()));
		if (etlpzGl.getId() == null || etlpzGl.getId().isEmpty()) {
			etlpzglBO.save(etlpzGl);
		}
		else {
			etlpzglBO.update(etlpzGl);
		}
		dao.update(entity);
	}

	/**
	 * <p>
	 * 根据马口铁分析数据，修改对应的采样单日志信息
	 * </p>
	 * 
	 * @param entity
	 */
	private void updateCyrz(MktfxshJl entity) {
		// if (MktfxStat.CS.stat.equals(entity.getStat())) {
		// entity.setFxr(null);
		// return;
		// }
		Cyrz cyrz = cyrzDAO.get(entity.getCyrzId());
		cyrz.setFxsj(entity.getFxr());
		cyrz.setFxBz(entity.getBz());
		cyrz.setEnd(true);
		cyrz.setStat(entity.getStat());
		cyrzDAO.update(cyrz);
	}

	/**
	 * <p>
	 * 根据马口铁分析数据，修改对应的品质管理记录信息
	 * </p>
	 * 
	 * @param mktfxshJl
	 * @param etlpzGl
	 * @return EtlpzGl
	 */
	private EtlpzGl parseEtlpzGl(MktfxshJl mktfxshJl, EtlpzGl etlpzGl) {
		// 统计一个卷的检验次数
		int jycs = 0;
		if (etlpzGl == null) {
			etlpzGl = new EtlpzGl();
			etlpzGl.setStat(EEtlpz.dqj.key);
			ZszrTp zszrTp = zsBO.getUniqueZszrTp(mktfxshJl.getJbno());
			if (zszrTp != null) {
				ZsdhTp zsdhTp = zsBO.getZsdhTp(zszrTp.getZsno());
				etlpzGl.setFugm(zsdhTp.getFugm());
				etlpzGl.setFuzs(zsdhTp.getFuzs());
				etlpzGl.setFuzx(zsdhTp.getFuzx());
				etlpzGl.setFufs(zsdhTp.getFufs());
				etlpzGl.setFufx(zsdhTp.getFufx());
				String yqty = zsdhTp.getYqty();
				String ytyp = zsdhTp.getYtyp();
				etlpzGl.setYqty(yqty);
				etlpzGl.setYtyp(ytyp);
				mktfxshJl.setHuac(zsdhTp.getHxcl());
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
				// etlpzGl.setJbno(mktfxshJl.getJbno());
				// etlpzGl.setZzsd(mktfxshJl.getZzsd());
			}
			jycs = 1;
		}
		etlpzGl.setJbno(mktfxshJl.getJbno());
		etlpzGl.setZzsd(mktfxshJl.getZzsd());
		// 检验次数
		jycs = jycs > 0 ? jycs : cyrzDAO.getJycs(mktfxshJl.getJbno());
		etlpzGl.setJycs(jycs);
		Cyrz cyrz = cyrzDAO.get(mktfxshJl.getCyrzId());
		MktfjVO mktfjVO = getMktfjVO(mktfxshJl.getJbno());
		String fxxm;
		if (cyrz != null && (fxxm = cyrz.getFxxm()) != null && !fxxm.isEmpty()) {
			List<String> fxxms = Arrays.asList(fxxm.split(","));
			// 镀锡量
			if (fxxms.contains(ECyfxxm.xfzl.name)) {
				etlpzGl.setNewed(true);
			}
			// 涂油量
			if (fxxms.contains(ECyfxxm.tyl.name)) {
				etlpzGl.setNewed(true);
			}
			// 铬量
			if (fxxms.contains(ECyfxxm.cr.name)) {
				etlpzGl.setNewed(true);
			}
		}
		// 镀锡量
		etlpzGl.setXfzlCmb(mktfjVO.getXfzlCmb());
		etlpzGl.setXfzlCmt(mktfjVO.getXfzlCmt());
		etlpzGl.setXfzlSmb(mktfjVO.getXfzlSmb());
		etlpzGl.setXfzlSmt(mktfjVO.getXfzlSmt());
		etlpzGl.setXfzlNmb(mktfjVO.getXfzlNmb());
		etlpzGl.setXfzlNmt(mktfjVO.getXfzlNmt());
		etlpzGl.setXfzlCab(mktfjVO.getXfzlCab());
		etlpzGl.setXfzlCat(mktfjVO.getXfzlCat());
		etlpzGl.setXfzlSab(mktfjVO.getXfzlSab());
		etlpzGl.setXfzlSat(mktfjVO.getXfzlSat());
		etlpzGl.setXfzlNab(mktfjVO.getXfzlNab());
		etlpzGl.setXfzlNat(mktfjVO.getXfzlNat());
		// 涂油量
		etlpzGl.setTylSt(mktfjVO.getTylSt());
		etlpzGl.setTylSb(mktfjVO.getTylSb());
		etlpzGl.setTylCt(mktfjVO.getTylCt());
		etlpzGl.setTylCb(mktfjVO.getTylCb());
		etlpzGl.setTylNt(mktfjVO.getTylNt());
		etlpzGl.setTylNb(mktfjVO.getTylNb());
		// 铬量
		etlpzGl.setCrSb(mktfjVO.getCrSb());
		etlpzGl.setCrSt(mktfjVO.getCrSt());
		etlpzGl.setCrCb(mktfjVO.getCrCb());
		etlpzGl.setCrCt(mktfjVO.getCrCt());
		etlpzGl.setCrNb(mktfjVO.getCrNb());
		etlpzGl.setCrNt(mktfjVO.getCrNt());

		return etlpzGl;
	}

	@Override
	public void update(MktfxshJl entity, User user) {
		updateCyrz(entity);
		MktfxshJl mktfxshJl = dao.get(entity.getId());
		ReflectUtils.copy(mktfxshJl, entity, null, EXCLUDE_FIELDS, false);
		mktfxshJl.setXgno(user.getNo());
		mktfxshJl.setXgnm(user.getName());
		mktfxshJl.setXgsj(new Date());
		dao.update(mktfxshJl);
		EtlpzGl etlpzGl = parseEtlpzGl(mktfxshJl, etlpzglBO.getByJbno(entity.getJbno()));
		etlpzglBO.update(etlpzGl);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<MktfxshJl> qentity) {
		dao.query(qentity);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public MktfxshJl get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public MktfxVO getMubiao(String cyrzId, String jbno) {
		YcaiTp entity = ycaiBo.get(jbno);
		if (entity == null) {
			return null;
		}
		if (!cyrzDAO.isExisted(null, jbno)) {
			return null;
		}
		String dhno = null;
		if ((dhno = entity.getDhno()) == null || dhno.isEmpty()) {
			return null;
		}
		DhuoTp dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(dhno));
		if (dhuoTp == null) {
			return null;
		}
		MktfxVO mktfxVO = new MktfxVO();
		// 锡附着量正.反上下限范围
		mktfxVO.setFuzx(dhuoTp.getFuzx());
		mktfxVO.setFuzs(dhuoTp.getFuzs());
		mktfxVO.setZxbz(dhuoTp.getFuzx() + " <= x <= " + dhuoTp.getFuzs());
		mktfxVO.setFufx(dhuoTp.getFufx());
		mktfxVO.setFufs(dhuoTp.getFufs());
		mktfxVO.setFxbz(dhuoTp.getFufx() + " <= x <= " + dhuoTp.getFufs());
		// 铬上下限范围
		JCr cr = JCr.get(dhuoTp.getHuac());
		if (cr != null) {
			mktfxVO.setCrs(cr.max);
			mktfxVO.setCrx(cr.min);
			mktfxVO.setCrbz(cr.description);
		}

		// 合金范围
		String hjin = dhuoTp.getHjin();
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
			mktfxVO.setHjin($hjin);
			mktfxVO.setHjbz($hjin + "以上");
		}
		// 涂油量上下限范围
		String ytyp = dhuoTp.getYtyp();
		String yqty = dhuoTp.getYqty();
		CodeTyzl codeTyzl;
		if (ytyp != null && !ytyp.isEmpty() && yqty != null && !yqty.isEmpty()
				&& (codeTyzl = CodeTyzl.get(ytyp)) != null) {
			JTyl tyl = JTyl.get(yqty);
			if (tyl != null && JTyl.D80.type.equals(codeTyzl.key)
					&& JTyl.D80.name.equals(yqty)
					&& (hjin == null || hjin.isEmpty())) {
				tyl = JTyl.$D80;
			}
			if (tyl != null) {
				mktfxVO.setTyx(tyl.min);
				mktfxVO.setTys(tyl.max);
				mktfxVO.setTybz(tyl.description);
			}
		}
		String fuzm = null, fufm = null;
		if (DhFzlDw.FZLDW_WB.stat.equals(dhuoTp.getFudw())) {
			fuzm = dhuoTp.getFuzm();
			fufm = dhuoTp.getFufm();
			mktfxVO.setFuzm(fuzm);
			mktfxVO.setFufm(fufm);
		}
		else {
			CodeItem codeItem = codeBO.getCodeItemByFudw(CmnConstants.CODE_024, dhuoTp.getFudw()
					+ dhuoTp.getFuzm(), DhFzlDw.FZLDW_WB.stat);
			String key = null;
			if (codeItem != null && (key = codeItem.getKey()) != null
					&& !key.isEmpty()) {
				fuzm = key.replaceFirst(DhFzlDw.FZLDW_WB.stat, "");
				mktfxVO.setFuzm(fuzm);
			}
			codeItem = codeBO.getCodeItemByFudw(CmnConstants.CODE_024, dhuoTp.getFudw()
					+ dhuoTp.getFufm(), DhFzlDw.FZLDW_WB.stat);
			if (codeItem != null && (key = codeItem.getKey()) != null
					&& !key.isEmpty()) {
				fufm = key.replaceFirst(DhFzlDw.FZLDW_WB.stat, "");
				mktfxVO.setFufm(fufm);
			}
		}
		// TCV上下限范围
		JTcv tcv = JTcv.get(fuzm);
		if (tcv != null) {
			mktfxVO.setTcvzx(tcv.min);
			mktfxVO.setTcvzs(tcv.max);
			mktfxVO.setTcvzbz(tcv.description);
		}
		tcv = JTcv.get(fufm);
		if (tcv != null) {
			mktfxVO.setTcvfx(tcv.min);
			mktfxVO.setTcvfs(tcv.max);
			mktfxVO.setTcvfbz(tcv.description);
		}
		mktfxVO.setYtyp(ytyp);
		mktfxVO.setYqty(yqty);
		mktfxVO.setZzsd(entity.getZzsd());
		mktfxVO.setCyrzId(cyrzId);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mktfxVO.setFxr(sdf.parse(sdf.format(date)));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		mktfxVO.setJbno(jbno);
		return mktfxVO;
	}

	@Override
	public void deletes(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
	}

	@Override
	public MktfxshJl getByCyrzId(String cyrzId) {
		return dao.getByCyrzId(cyrzId);
	}

	public IMktfxDAO getDao() {
		return dao;
	}

	public void setDao(IMktfxDAO dao) {
		this.dao = dao;
	}

	public IYcaitpBO getYcaiBo() {
		return ycaiBo;
	}

	public void setYcaiBo(IYcaitpBO ycaiBo) {
		this.ycaiBo = ycaiBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public ICyrzDAO getCyrzDAO() {
		return cyrzDAO;
	}

	public void setCyrzDAO(ICyrzDAO cyrzDAO) {
		this.cyrzDAO = cyrzDAO;
	}

	public IEtlpzglBO getEtlpzglBO() {
		return etlpzglBO;
	}

	public void setEtlpzglBO(IEtlpzglBO etlpzglBO) {
		this.etlpzglBO = etlpzglBO;
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

	@Override
	public MktfjVO getMktfjVO(String jbno) {
		MktfjVO vo = new MktfjVO();
		List<MktfxshJl> mktfxshJls = dao.findMktfxshJls(jbno);
		for (MktfxshJl mktfxshJl : mktfxshJls) {
			// 镀锡量
			Double xfzlCmb = mktfxshJl.getXfzlCmb();
			xfzlCmb = (xfzlCmb != null && xfzlCmb > 0 ? xfzlCmb
					: vo.getXfzlCmb());
			vo.setXfzlCmb(xfzlCmb);
			Double xfzlCmt = mktfxshJl.getXfzlCmt();
			xfzlCmt = (xfzlCmt != null && xfzlCmt > 0 ? xfzlCmt
					: vo.getXfzlCmt());
			vo.setXfzlCmt(xfzlCmt);
			Double xfzlSmb = mktfxshJl.getXfzlSmb();
			xfzlSmb = (xfzlSmb != null && xfzlSmb > 0 ? xfzlSmb
					: vo.getXfzlSmb());
			vo.setXfzlSmb(xfzlSmb);
			Double xfzlSmt = mktfxshJl.getXfzlSmt();
			xfzlSmt = (xfzlSmt != null && xfzlSmt > 0 ? xfzlSmt
					: vo.getXfzlSmt());
			vo.setXfzlSmt(xfzlSmt);
			Double xfzlNmb = mktfxshJl.getXfzlNmb();
			xfzlNmb = (xfzlNmb != null && xfzlNmb > 0 ? xfzlNmb
					: vo.getXfzlNmb());
			vo.setXfzlNmb(xfzlNmb);
			Double xfzlNmt = mktfxshJl.getXfzlNmt();
			xfzlNmt = (xfzlNmt != null && xfzlNmt > 0 ? xfzlNmt
					: vo.getXfzlNmt());
			vo.setXfzlNmt(xfzlNmt);

			Double xfzlCab = mktfxshJl.getXfzlCab();
			xfzlCab = (xfzlCab != null && xfzlCab > 0 ? xfzlCab
					: vo.getXfzlCab());
			vo.setXfzlCab(xfzlCab);
			Double xfzlCat = mktfxshJl.getXfzlCat();
			xfzlCat = (xfzlCat != null && xfzlCat > 0 ? xfzlCat
					: vo.getXfzlCat());
			vo.setXfzlCat(xfzlCat);
			Double xfzlSab = mktfxshJl.getXfzlSab();
			xfzlSab = (xfzlSab != null && xfzlSab > 0 ? xfzlSab
					: vo.getXfzlSab());
			vo.setXfzlSab(xfzlSab);
			Double xfzlSat = mktfxshJl.getXfzlSat();
			xfzlSat = (xfzlSat != null && xfzlSat > 0 ? xfzlSat
					: vo.getXfzlSat());
			vo.setXfzlSat(xfzlSat);
			Double xfzlNab = mktfxshJl.getXfzlNab();
			xfzlNab = (xfzlNab != null && xfzlNab > 0 ? xfzlNab
					: vo.getXfzlNab());
			vo.setXfzlNab(xfzlNab);
			Double xfzlNat = mktfxshJl.getXfzlNat();
			xfzlNat = (xfzlNat != null && xfzlNat > 0 ? xfzlNat
					: vo.getXfzlNat());
			vo.setXfzlNat(xfzlNat);
			// 涂油量
			Double tylSt = mktfxshJl.getTylSt();
			tylSt = (tylSt != null && tylSt > 0 ? tylSt : vo.getTylSt());
			vo.setTylSt(tylSt);
			Double tylSb = mktfxshJl.getTylSb();
			tylSb = (tylSb != null && tylSb > 0 ? tylSb : vo.getTylSb());
			vo.setTylSb(tylSb);
			Double tylCt = mktfxshJl.getTylCt();
			tylCt = (tylCt != null && tylCt > 0 ? tylCt : vo.getTylCt());
			vo.setTylCt(tylCt);
			Double tylCb = mktfxshJl.getTylCb();
			tylCb = (tylCb != null && tylCb > 0 ? tylCb : vo.getTylCb());
			vo.setTylCb(tylCb);
			Double tylNt = mktfxshJl.getTylNt();
			tylNt = (tylNt != null && tylNt > 0 ? tylNt : vo.getTylNt());
			vo.setTylNt(tylNt);
			Double tylNb = mktfxshJl.getTylNb();
			tylNb = (tylNb != null && tylNb > 0 ? tylNb : vo.getTylNb());
			vo.setTylNb(tylNb);
			// 铬量
			Double crSb = mktfxshJl.getCrSb();
			crSb = (crSb != null && crSb > 0 ? crSb : vo.getCrSb());
			vo.setCrSb(crSb);
			Double crSt = mktfxshJl.getCrSt();
			crSt = (crSt != null && crSt > 0 ? crSt : vo.getCrSt());
			vo.setCrSt(crSt);
			Double crCb = mktfxshJl.getCrCb();
			crCb = (crCb != null && crCb > 0 ? crCb : vo.getCrCb());
			vo.setCrCb(crCb);
			Double crCt = mktfxshJl.getCrCt();
			crCt = (crCt != null && crCt > 0 ? crCt : vo.getCrCt());
			vo.setCrCt(crCt);
			Double crNb = mktfxshJl.getCrNb();
			crNb = (crNb != null && crNb > 0 ? crNb : vo.getCrNb());
			vo.setCrNb(crNb);
			Double crNt = mktfxshJl.getCrNt();
			crNt = (crNt != null && crNt > 0 ? crNt : vo.getCrNt());
			vo.setCrNt(crNt);
		}
		return vo;
	}

	@Override
	public void queryChpdb(QEntity<Chpdb> qentity) {
		chpdbDAO.query(qentity);
	}

	@Override
	public void saveChpdb(Chpdb entity) {
		if (chpdbDAO.isExisted(entity.getJbno())) {
			chpdbDAO.update(entity);
			return;
		}
		chpdbDAO.save(entity);
	}

	@Override
	public String getChpdbForJS(String jbno) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "Coil No.为空").toString();
		}
		YcaiTp entity = ycaiBo.get(jbno);
		if (entity == null) {
			return new Result(-1, "Coil No." + jbno + "不存在").toString();
		}
		String dhno = null;
		if ((dhno = entity.getDhno()) == null || dhno.isEmpty()) {
			return new Result(-1, "Coil No." + jbno + "还未生产").toString();
		}
		DhuoTp dhuoTp = dhBo.get(SinoUtils.parseDhuoPk(dhno));
		if (dhuoTp == null) {
			return new Result(-1, "订货No." + dhno + "不存在").toString();
		}
		Chpdb chpdb = new Chpdb();
		chpdb.setZzsd(entity.getZzsd());
		chpdb.setJbno(jbno);
		chpdb.setDhno(dhno);
		chpdb.setAbbr(dhuoTp.getAbbr());
		chpdb.setYuny(dhuoTp.getYuny());
		chpdb.setFace(dhuoTp.getFace());
		chpdb.setFudw(dhuoTp.getFudw());
		String fuzm = dhuoTp.getFuzm();
		String fufm = dhuoTp.getFufm();
		chpdb.setFuzm(fuzm);
		chpdb.setFufm(fufm);
		chpdb.setHouu(dhuoTp.getHouu());
		chpdb.setKuan(dhuoTp.getKuan());
		chpdb.setCang(dhuoTp.getCang());
		chpdb.setCond(dhuoTp.getCond());
		List<MktfxshJl> mktfxshJls = dao.findMktfxshJls(jbno);
		for (MktfxshJl mktfxshJl : mktfxshJls) {
			// 镀锡量
			Double xfzlSmb = mktfxshJl.getXfzlSmb();
			xfzlSmb = (xfzlSmb != null && xfzlSmb >= 0 ? xfzlSmb
					: chpdb.getXfzlMb());
			chpdb.setXfzlMb(xfzlSmb);
			Double xfzlSmt = mktfxshJl.getXfzlSmt();
			xfzlSmt = (xfzlSmt != null && xfzlSmt >= 0 ? xfzlSmt
					: chpdb.getXfzlMt());
			chpdb.setXfzlMt(xfzlSmt);
			Double xfzlNmb = mktfxshJl.getXfzlNmb();
			xfzlNmb = (xfzlNmb != null && xfzlNmb >= 0 ? xfzlNmb
					: chpdb.getXfzlMb());
			chpdb.setXfzlMb(xfzlNmb);
			Double xfzlNmt = mktfxshJl.getXfzlNmt();
			xfzlNmt = (xfzlNmt != null && xfzlNmt >= 0 ? xfzlNmt
					: chpdb.getXfzlMt());
			chpdb.setXfzlMt(xfzlNmt);
			
			Double xfzlCmb = mktfxshJl.getXfzlCmb();
			xfzlCmb = (xfzlCmb != null && xfzlCmb >= 0 ? xfzlCmb
					: chpdb.getXfzlMb());
			chpdb.setXfzlMb(xfzlCmb);
			Double xfzlCmt = mktfxshJl.getXfzlCmt();
			xfzlCmt = (xfzlCmt != null && xfzlCmt >= 0 ? xfzlCmt
					: chpdb.getXfzlMt());
			chpdb.setXfzlMt(xfzlCmt);

			Double xfzlSab = mktfxshJl.getXfzlSab();
			xfzlSab = (xfzlSab != null && xfzlSab >= 0 ? xfzlSab
					: chpdb.getXfzlAb());
			chpdb.setXfzlAb(xfzlSab);
			Double xfzlSat = mktfxshJl.getXfzlSat();
			xfzlSat = (xfzlSat != null && xfzlSat >= 0 ? xfzlSat
					: chpdb.getXfzlAt());
			chpdb.setXfzlAt(xfzlSat);
			Double xfzlNab = mktfxshJl.getXfzlNab();
			xfzlNab = (xfzlNab != null && xfzlNab >= 0 ? xfzlNab
					: chpdb.getXfzlAb());
			chpdb.setXfzlAb(xfzlNab);
			Double xfzlNat = mktfxshJl.getXfzlNat();
			xfzlNat = (xfzlNat != null && xfzlNat >= 0 ? xfzlNat
					: chpdb.getXfzlAt());
			chpdb.setXfzlAt(xfzlNat);
			Double xfzlCab = mktfxshJl.getXfzlCab();
			xfzlCab = (xfzlCab != null && xfzlCab >= 0 ? xfzlCab
					: chpdb.getXfzlAb());
			chpdb.setXfzlAb(xfzlCab);
			Double xfzlCat = mktfxshJl.getXfzlCat();
			xfzlCat = (xfzlCat != null && xfzlCat >= 0 ? xfzlCat
					: chpdb.getXfzlAt());
			chpdb.setXfzlAt(xfzlCat);

			Double xfzlSsb = mktfxshJl.getXfzlSsb();
			xfzlSsb = (xfzlSsb != null && xfzlSsb >= 0 ? xfzlSsb
					: chpdb.getXfzlSb());
			chpdb.setXfzlSb(xfzlSsb);
			Double xfzlSst = mktfxshJl.getXfzlSst();
			xfzlSst = (xfzlSst != null && xfzlSst >= 0 ? xfzlSst
					: chpdb.getXfzlSt());
			chpdb.setXfzlSt(xfzlSst);
			Double xfzlNsb = mktfxshJl.getXfzlNsb();
			xfzlNsb = (xfzlNsb != null && xfzlNsb >= 0 ? xfzlNsb
					: chpdb.getXfzlSb());
			chpdb.setXfzlSb(xfzlNsb);
			Double xfzlNst = mktfxshJl.getXfzlNst();
			xfzlNst = (xfzlNst != null && xfzlNst >= 0 ? xfzlNst
					: chpdb.getXfzlSt());
			chpdb.setXfzlSt(xfzlNst);
			Double xfzlCsb = mktfxshJl.getXfzlCsb();
			xfzlCsb = (xfzlCsb != null && xfzlCsb >= 0 ? xfzlCsb
					: chpdb.getXfzlSb());
			chpdb.setXfzlSb(xfzlCsb);
			Double xfzlCst = mktfxshJl.getXfzlCst();
			xfzlCst = (xfzlCst != null && xfzlCst >= 0 ? xfzlCst
					: chpdb.getXfzlSt());
			chpdb.setXfzlSt(xfzlCst);

			// 铬量
			Double crSb = mktfxshJl.getCrSb();
			crSb = (crSb != null && crSb >= 0 ? crSb : chpdb.getCrB());
			chpdb.setCrB(crSb);
			Double crSt = mktfxshJl.getCrSt();
			crSt = (crSt != null && crSt >= 0 ? crSt : chpdb.getCrT());
			chpdb.setCrT(crSt);
			Double crNb = mktfxshJl.getCrNb();
			crNb = (crNb != null && crNb >= 0 ? crNb : chpdb.getCrB());
			chpdb.setCrB(crNb);
			Double crNt = mktfxshJl.getCrNt();
			crNt = (crNt != null && crNt >= 0 ? crNt : chpdb.getCrT());
			chpdb.setCrT(crNt);
			Double crCb = mktfxshJl.getCrCb();
			crCb = (crCb != null && crCb >= 0 ? crCb : chpdb.getCrB());
			chpdb.setCrB(crCb);
			Double crCt = mktfxshJl.getCrCt();
			crCt = (crCt != null && crCt >= 0 ? crCt : chpdb.getCrT());
			chpdb.setCrT(crCt);

			Double isvZt = mktfxshJl.getIsvZt();
			isvZt = (isvZt != null && isvZt >= 0 ? isvZt : chpdb.getIsv());
			chpdb.setIsv(isvZt);

			Double isvZb = mktfxshJl.getIsvZb();
			isvZb = (isvZb != null && isvZb >= 0 ? isvZb : chpdb.getIsv());
			chpdb.setIsv(isvZb);

			Double tcsPdt = mktfxshJl.getTcsPdt();
			tcsPdt = (tcsPdt != null && tcsPdt >= 0 ? tcsPdt : chpdb.getTcs());
			chpdb.setTcs(tcsPdt);

			Double tcsPdb = mktfxshJl.getTcsPdb();
			tcsPdb = (tcsPdb != null && tcsPdb >= 0 ? tcsPdb : chpdb.getTcs());
			chpdb.setTcs(tcsPdb);

			Double atcZt = mktfxshJl.getAtcZt();
			atcZt = (atcZt != null && atcZt >= 0 ? atcZt : chpdb.getAtc());
			chpdb.setAtc(atcZt);

			Double atcZb = mktfxshJl.getAtcZb();
			atcZb = (atcZb != null && atcZb >= 0 ? atcZb : chpdb.getAtc());
			chpdb.setAtc(atcZb);

			Integer porePdt = mktfxshJl.getPorePdt();
			porePdt = (porePdt != null && porePdt >= 0 ? porePdt
					: chpdb.getPore());
			chpdb.setPore(porePdt);

			Integer porePdb = mktfxshJl.getPorePdb();
			porePdb = (porePdb != null && porePdb >= 0 ? porePdb
					: chpdb.getPore());
			chpdb.setPore(porePdb);

			if (fuzm != null && !fuzm.isEmpty() && fuzm.compareTo(fufm) > 0) {
				Double tcvZt = mktfxshJl.getTcvZt();
				tcvZt = (tcvZt != null && tcvZt >= 0 ? tcvZt : chpdb.getTcv());
				chpdb.setTcv(tcvZt);
			}
			else if (fuzm != null && !fuzm.isEmpty()
					&& fuzm.compareTo(fufm) < 0) {
				Double tcvZb = mktfxshJl.getTcvZb();
				tcvZb = (tcvZb != null && tcvZb >= 0 ? tcvZb : chpdb.getTcv());
				chpdb.setTcv(tcvZb);
			}
			else {
				Double tcvZt = mktfxshJl.getTcvZt();
				tcvZt = (tcvZt != null && tcvZt >= 0 ? tcvZt : chpdb.getTcv());
				chpdb.setTcv(tcvZt);

				Double tcvZb = mktfxshJl.getTcvZb();
				tcvZb = (tcvZb != null && tcvZb >= 0 ? tcvZb : chpdb.getTcv());
				chpdb.setTcv(tcvZb);
			}
			Double pltPd = mktfxshJl.getPltPd();
			pltPd = (pltPd != null && pltPd >= 0 ? pltPd : chpdb.getPltPd());
			chpdb.setPltPd(pltPd);
		}
		return new Result(chpdb).toJsObject();
	}

	@Override
	public Chpdb getChpdb(String jbno) {
		return chpdbDAO.get(jbno);
	}

	@Override
	public void deleteChpdb(String[] ids) {
		chpdbDAO.deletes(ids);
	}

	public IChpdbDAO getChpdbDAO() {
		return chpdbDAO;
	}

	public void setChpdbDAO(IChpdbDAO chpdbDAO) {
		this.chpdbDAO = chpdbDAO;
	}

}