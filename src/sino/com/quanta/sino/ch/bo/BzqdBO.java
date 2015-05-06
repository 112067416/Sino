package com.quanta.sino.ch.bo;

import java.util.List;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.ch.bo.api.IBzqdBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.ch.vo.BzqdVO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Zng1Tp;

/**
 * <p>
 * 包装清单操作业务实现类
 * </p>
 * <p>
 * create: 2011-2-23 上午09:20:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class BzqdBO implements IBzqdBO {

	/**
	 * 订货管理业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 制作装箱指示业务接口
	 */
	private IZxzsBO zsBo;

	/**
	 * 码表业务接口
	 */
	private ICodeBO codeBO;
	/**
	 * 装箱指示书2数据接口
	 */
	private IZng2DAO zng2DAO;

	@Override
	public String get(String dhno, String line, String zxno) {
		if ((dhno == null || dhno.isEmpty() || line == null || line.isEmpty())
				&& (zxno == null || zxno.isEmpty())) {
			return new Result(-1, "请输入订货No.或装箱指示书No.").toString();
		}
		DhuoTp dhuoTp = null;
		Zng1Tp zng1Tp = null;
		if (zxno != null && !zxno.isEmpty()) {
			zng1Tp = zsBo.getUnique(zxno);
			if (zng1Tp == null) {
				return new Result(-1, "该装箱指示书No.不存在").toString();
			}
			dhno = zng1Tp.getDhno();
			line = zng1Tp.getLine();
		}
		dhuoTp = dhBo.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			return new Result(-1, "订货No." + dhno + "-" + line + ",不存在").toString();
		}
		if (CodeNwx.nei.key.equals(dhuoTp.getNwai())) {
			return new Result(-1, "该制品不是外销制品,不能制作包装清单").toString();
		}
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
		String pm = pz1 + "  " + pz2 + "  " + face;
		BzqdVO vo = new BzqdVO();
		vo.setDhno(dhno);
		vo.setLine(line);
		vo.setAbbr(dhuoTp.getAbbr());
		vo.setZxno(zxno);
		if (zng1Tp != null) {
			vo.setChri(zng1Tp.getZxzsTp().getChri());
		}
		CodeItem codeItem = codeBO.getCodeItem(CmnConstants.CODE_NWX, dhuoTp.getNwai());
		if (codeItem != null) {
			vo.setNwai(codeItem.getValue());
		}
		vo.setPm(pm);
		return new Result(vo).toJsObject();
	}

	@Override
	public List<PbzqdVO> findBzqdVos(List<String> jbnos, String zxno) {
		return zng2DAO.findBzqdVos(jbnos, zxno);
	}

	public IZxzsBO getZsBo() {
		return zsBo;
	}

	public void setZsBo(IZxzsBO zsBo) {
		this.zsBo = zsBo;
	}

	public IDhBO getDhBo() {
		return dhBo;
	}

	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public IZng2DAO getZng2DAO() {
		return zng2DAO;
	}

	public void setZng2DAO(IZng2DAO zng2dao) {
		zng2DAO = zng2dao;
	}

}
