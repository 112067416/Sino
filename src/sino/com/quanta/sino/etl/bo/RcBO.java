package com.quanta.sino.etl.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.exception.CocoException;
import com.quanta.sino.cmn.bo.api.IBanBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.ECaoz;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.ESczt;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IRcBO;
import com.quanta.sino.etl.constants.ESfqr;
import com.quanta.sino.etl.dao.api.IRcrzDAO;
import com.quanta.sino.etl.vo.RcSaveVO;
import com.quanta.sino.etl.vo.RcViewVO;
import com.quanta.sino.etl.vo.RcmxVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Rcrz;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * ETL入侧
 * </p>
 * <p>
 * create: 2011-2-15 下午04:45:33
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class RcBO implements IRcBO {

	/**
	 * 
	 */
	private IRcrzDAO dao;

	/**
	 * 
	 */
	private IYcaitpBO yaiBo;

	/**
	 * 订货指示接口
	 */
	private IZsBO zsBO;
	/**
	 * 订货管理接口
	 */
	private IDhBO dhBO;

	/**
	 * ETL品质管理业务接口
	 */
	private IEtlpzglBO etlpzglBO;
	/**
	 * 班别接口
	 */
	private IBanBO banBO;

	@Override
	public void save(RcSaveVO entity) {
		Boolean banbl = banBO.checkBan(entity.getBan());
		if (!banbl) {
			throw new CocoException(-23, "现在的时间不是这个班,请选择另一个班！");
		}
		if (yaiBo.isExistedBySczt(ESczt.KEY_BYJ)) {
			throw new CocoException(-1, "备用卷已经存在,不能添加新的备用卷");
		}
		if (yaiBo.isExistedByJbnoAndSczt(entity.getJbno(), ESczt.KEY_DQJ)) {
			throw new CocoException(-1, "该卷已经是当前卷");
		}
		YcaiTp ycai = yaiBo.get(entity.getJbno());
		if (ycai == null) {
			throw new CocoException(-1, "COIL No.不存在！");
		}
		String zsno = null;
		if ((zsno = ycai.getZsno()) == null || zsno.isEmpty()) {
			throw new CocoException(-1, "该卷还未制作指示书");
		}
		if (!zsno.equals(entity.getZsno())) {
			throw new CocoException(-1, "该卷对应的指示书No.为" + zsno + ",不属于指示书No."
					+ entity.getZsno());
		}

		// 卷的状态为终了或中止了
		if (YbStat.SJZL.stat.equals(ycai.getStat())
				|| YbStat.SJZZ.stat.equals(ycai.getStat())) {
			throw new CocoException(-1, "该卷状态为终了或中止了,不能将其增加为备用卷");
		}

		// 您输入的卷板号没有对应到B堆场或A堆场
		if (ycai.getDuic() == null
				|| (!ycai.getDuic().equals(DC.B.toString()) && !ycai.getDuic().equals(DC.A.toString()))) {
			throw new CocoException(-1, "您输入的卷板号没有对应到B堆场或A堆场！");
		}
		if (ycai.getZtbj() != null && !ycai.getZtbj().isEmpty()) {
			throw new CocoException(-1, "您输入的卷板号已经作业停止！");
		}
		// 分配号不为1
		if (ycai.getFpyc() == null || !EFpyc.KEY_FP.equals(ycai.getFpyc())) {
			throw new CocoException(-1, "您输入的卷板号为未分配！");
		}
		// 进度标记.ETL指示 =1
		if (ycai.getJdez() == null
				|| !ycai.getJdez().equals(ZtConstants.ZPNG_JD_ETLZS_YZS)) {
			throw new CocoException(-1, "您输入的卷板号为未进行ETL指示！");
		}
		// 获取系统当前用户
		User user = Context.currentUser();
		// 用户登录检查
		if (user == null) {
			throw new CocoException(-1, "异常：当前用户没有登录");
		}
		// 第三方接口查询，判断是否能加备用卷
		if (!dao.getByStat(1)) {
			throw new CocoException(-1, "电镀没有完成，不能加备用卷");
		}
		// -------- 更新以前的备用卷为初始----------
		// 查询以前备用卷
		YcaiTp ycaiTp = yaiBo.getYcaiBySczt(ESczt.KEY_BYJ);
		String byJbno = ycaiTp != null ? ycaiTp.getJbno() : null;
		// 设置以前的备用卷为初始状态,确认状态为未确认
		yaiBo.updateScztAndSfqr(byJbno, ESczt.KEY_CS, ESfqr.KEY_WQR);
		// 判断备用卷在pc6000是否是当前，如是变为当前
		if (dao.getSczt(entity.getJbno()).equals(ESczt.KEY_DQJ)) {
			// 设置备用卷为当前卷
			yaiBo.updateSczt(entity.getJbno(), ESczt.KEY_DQJ);
			etlpzglBO.outEtlpzGl(entity.getJbno());
			return;
		}

		// ---------更新添加的卷为备用卷
		// 更新生产状态为备用卷
		yaiBo.updateSczt(entity.getJbno(), ESczt.KEY_BYJ);
		// 写入侧日志
		Rcrz rcrz = new Rcrz();
		rcrz.setBz(entity.getBz());
		rcrz.setBan(entity.getBan());
		rcrz.setCzsj(new Date());
		rcrz.setCzy(entity.getCzy());
		rcrz.setJbno(entity.getJbno());
		rcrz.setSlin(entity.getSlin());
		rcrz.setXtyh(user.getNo());
		dao.save(rcrz);
		// 给第三方接口写备用卷信息

		ZsdhTp zsdh = zsBO.getZsdhTp(entity.getZsno());
		// 订货NO和行号
		String dhnoAndLine = zsdh.getDhno();
		if (dhnoAndLine == null || dhnoAndLine.isEmpty()) {
			throw new CocoException(-1, "订货指示DB的订货NO为空！");
		}
		DhuoTpPk dhpk = SinoUtils.parseDhuoPk(dhnoAndLine);
		// 查询订货DB
		DhuoTp dhuo = dhBO.get(dhpk);
		boolean relbl = dao.sendSave(entity.getJbno(), ycai, zsdh, dhuo);
		if (!relbl) {
			throw new CocoException(-1, "增加中间表备用卷失败！");
		}

	}

	private RcmxVO parseRcmxVO(YcaiTp ycai) {
		RcmxVO rcmxVO = new RcmxVO();
		if (ycai == null) {
			return rcmxVO;
		}
		rcmxVO.setJbno(ycai.getJbno());
		rcmxVO.setStat(ycai.getSczt());
		rcmxVO.setZsno(ycai.getZsno());
		rcmxVO.setZpzl(ycai.getZpzl());
		rcmxVO.setSfqr(ycai.getSfqr());
		// 获取业连号
		String ylno = ycai.getYlno();
		if (ylno != null && !ylno.isEmpty()) {
			String type;
			for (String yln : ylno.split(",")) {
				type = yln.substring(0, 1);
				if (EKpType.ETL.key.equals(type)
						|| EKpType.BOTH.key.equals(type)) {
					rcmxVO.getYlns().add(yln.substring(2));
				}
			}
		}
		// 订货指示
		ZsdhTp zsdhtp = zsBO.getZsdhTp(ycai.getZsno());
		if (zsdhtp != null) {
			ECaoz caoz = ECaoz.get(zsdhtp.getCaoz());
			rcmxVO.setCaoz(caoz != null ? caoz.name : null);
			rcmxVO.setFace(zsdhtp.getFace());
			rcmxVO.setFlow(zsdhtp.getFlow());
			rcmxVO.setFpdj(zsdhtp.getFpdj());
			rcmxVO.setHjin(zsdhtp.getHjin());
			rcmxVO.setHouu(zsdhtp.getHouu());
			rcmxVO.setKuan(zsdhtp.getKuan());
			rcmxVO.setCang(zsdhtp.getCang());
			rcmxVO.setSize(SinoUtils.formatProductSize(zsdhtp.getHouu(), zsdhtp.getKuan(), zsdhtp.getCang()));
			rcmxVO.setHxcl(zsdhtp.getHxcl());
			rcmxVO.setNjno(zsdhtp.getNjno());
			rcmxVO.setXfuz(zsdhtp.getFugm());
			rcmxVO.setYqty(zsdhtp.getYqty());
			rcmxVO.setYuny(zsdhtp.getYuny());
			if (EKpType.ETL.key.equals(zsdhtp.getKpn1Flag())
					|| EKpType.BOTH.key.equals(zsdhtp.getKpn1Flag())) {
				rcmxVO.getKpns().add(zsdhtp.getKpn1());
			}
			if (EKpType.ETL.key.equals(zsdhtp.getKpn2Flag())
					|| EKpType.BOTH.key.equals(zsdhtp.getKpn2Flag())) {
				rcmxVO.getKpns().add(zsdhtp.getKpn2());
			}
			if (EKpType.ETL.key.equals(zsdhtp.getKpn3Flag())
					|| EKpType.BOTH.key.equals(zsdhtp.getKpn3Flag())) {
				rcmxVO.getKpns().add(zsdhtp.getKpn3());
			}
			if (EKpType.ETL.key.equals(zsdhtp.getKpn4Flag())
					|| EKpType.BOTH.key.equals(zsdhtp.getKpn4Flag())) {
				rcmxVO.getKpns().add(zsdhtp.getKpn4());
			}
		}
		return rcmxVO;
	}

	@Override
	public RcViewVO findRc() {
		YcaiTp ycaitp = yaiBo.getYcaiBySczt(ESczt.KEY_DQJ);
		RcViewVO rc = new RcViewVO();
		// 加当前卷
		if (ycaitp != null) {
			rc.setDqjbno(ycaitp.getJbno());
		}
		ycaitp = yaiBo.getYcaiBySczt(ESczt.KEY_BYJ);
		// 加备用卷
		if (ycaitp != null) {
			rc.setByjbno(ycaitp.getJbno());
		}

		return rc;
	}

	@Override
	public List<RcmxVO> findByStates() {
		YcaiTp ycaitp = yaiBo.getYcaiBySczt(ESczt.KEY_DQJ);
		List<RcmxVO> ycaiVos = new ArrayList<RcmxVO>();
		// 加当前卷
		if (ycaitp == null) {
			RcmxVO ycai1 = new RcmxVO();
			ycai1.setStat(ESczt.KEY_DQJ);
			ycaiVos.add(ycai1);
		}
		else {
			ycaiVos.add(parseRcmxVO(ycaitp));
		}
		ycaitp = yaiBo.getYcaiBySczt(ESczt.KEY_BYJ);
		// 加备用卷
		if (ycaitp == null) {
			RcmxVO ycai1 = new RcmxVO();
			ycai1.setStat(ESczt.KEY_BYJ);
			ycai1.setSfqr(ESfqr.KEY_WQR);
			ycaiVos.add(ycai1);
		}
		else {
			ycaiVos.add(parseRcmxVO(ycaitp));
		}
		return ycaiVos;
	}

	@Override
	public void updateBySfqr(String jbno) {
		yaiBo.updateSfqr(jbno, ESfqr.KEY_YQR);
	}

	@Override
	public Integer getbygif() {
		if (dao.getByStat(1)) {
			if (dao.isExitBy()) {
				return 2;
			}
			else {
				return -1;
			}
		}
		else {
			return 1;
		}
	}

	@Override
	public Integer updateYCBY() {
		// 查询备用卷
		YcaiTp ycaiTp1 = yaiBo.getYcaiBySczt(ESczt.KEY_BYJ);
		String byJbno = ycaiTp1 != null ? ycaiTp1.getJbno() : null;
		// 查询当前卷
		YcaiTp ycaiTp2 = yaiBo.getYcaiBySczt(ESczt.KEY_DQJ);
		String dqjbno = ycaiTp2 != null ? ycaiTp2.getJbno() : null;
		Date date = new Date();
		int flag = -1;
		// 判断第三方接口的备用卷状态,true为当前卷
		// 中间库备用卷状态
		if (byJbno != null && !byJbno.isEmpty()) {
			String byStat = dao.getSczt(byJbno);
			// 备用卷变备用卷
			if (ESczt.KEY_BYJ.equals(byStat)) {
				flag = -1;
			}
			// 备用卷变当前卷
			if (ESczt.KEY_DQJ.equals(byStat)) {
				// 设置备用卷为当前卷
				ycaiTp1.setDxks(date);
				yaiBo.updateEntity(ycaiTp1);
				yaiBo.updateSczt(byJbno, ESczt.KEY_DQJ);
				etlpzglBO.outEtlpzGl(byJbno);
				flag = 1;

			}
			// 备用卷变已经完成卷
			if (ESczt.KEY_WCJ.equals(byStat)) {
				// 设置备用卷为完成卷
				ycaiTp1.setDxjs(date);
				yaiBo.updateEntity(ycaiTp1);
				yaiBo.updateScztAndSfqr(byJbno, ESczt.KEY_WCJ, ESfqr.KEY_YQR);
				etlpzglBO.finish(byJbno);
				flag = 1;

			}
		}
		if (dqjbno != null && !dqjbno.isEmpty()) {
			String dqStat = dao.getSczt(dqjbno);
			// 当前变当前
			if (ESczt.KEY_DQJ.equals(dqStat)) {
				flag = flag > 0 ? 1 : -1;
			}
			// 当前变已完成
			if (ESczt.KEY_WCJ.equals(dqStat) && dqjbno != null) {
				// 设置当前卷为已完成状态,确认状态为已确认
				ycaiTp2.setDxjs(date);
				yaiBo.updateEntity(ycaiTp2);
				yaiBo.updateScztAndSfqr(dqjbno, ESczt.KEY_WCJ, ESfqr.KEY_YQR);
				etlpzglBO.finish(dqjbno);
				flag = 1;

			}
		}
		return flag;
	}

	@Override
	public void updateMove(String dqjbno) {
		// 强制移行
		if (dqjbno != null && !dqjbno.isEmpty()) {
			// 设置当前卷为初始状态,确认状态为未确认
			yaiBo.updateScztAndSfqr(dqjbno, ESczt.KEY_CS, ESfqr.KEY_WQR);
		}

		// etlpzglBO.outEtlpzGl(byjbno);
		etlpzglBO.delete(dqjbno);
	}

	@Override
	public void deleteBY(String jbno) {
		// 调第三方接口删除中间表备用卷
		boolean rebl = dao.sendDelete(jbno);
		if (rebl) {
			// 设置备用卷为初始状态,确认状态为未确认
			yaiBo.updateScztAndSfqr(jbno, ESczt.KEY_CS, ESfqr.KEY_WQR);
		}
		else {
			throw new CocoException(-1, "异常：删除中间表失败！");
		}

	}

	@Override
	public boolean isExisted(String jbno, String sfqr) {
		return yaiBo.isExisted(jbno, sfqr);
	}

	public IRcrzDAO getDao() {
		return dao;
	}

	public void setDao(IRcrzDAO dao) {
		this.dao = dao;
	}

	public IYcaitpBO getYaiBo() {
		return yaiBo;
	}

	public void setYaiBo(IYcaitpBO yaiBo) {
		this.yaiBo = yaiBo;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public IEtlpzglBO getEtlpzglBO() {
		return etlpzglBO;
	}

	public void setEtlpzglBO(IEtlpzglBO etlpzglBO) {
		this.etlpzglBO = etlpzglBO;
	}

	public IDhBO getDhBO() {
		return dhBO;
	}

	public void setDhBO(IDhBO dhBO) {
		this.dhBO = dhBO;
	}

	public IBanBO getBanBO() {
		return banBO;
	}

	public void setBanBO(IBanBO banBO) {
		this.banBO = banBO;
	}

}
