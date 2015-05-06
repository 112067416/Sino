package com.quanta.sino.dy.bo;

import java.text.SimpleDateFormat;

import com.coco.core.exception.CocoException;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.bo.api.IZpbqBO;
import com.quanta.sino.dy.vo.ZpbqVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 制品打印数据获取业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午09:57:39
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZpbqBO implements IZpbqBO {

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 订货业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 用户业务接口
	 */
	private IYongBO yongBo;

	/**
	 * 码表接口
	 */
	private ICodeBO codeBo;
	/**
	 * 公共业务接口
	 */
	private ICmnBO cmnBO;

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 
	 */
	private static String GRADE = "GRADE";

	/**
	 * SPCC规格打印等级
	 */
	private static String SPCC = "SPCC";

	@Override
	public ZpbqVO get(String jbno) {
		// 查询制品在库
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "无法找到对应的制品信息，制品号：" + jbno);
		}
		String chan = zpng.getChan();
		if (ChanType.bl.key.equals(chan)) {
			throw new CocoException(-1, "制品号：" + jbno + "为保留品,不能打印制品标签");
		}
		String htnoAndLine = zpng.getDhno();
		DhuoTp dhuo = null;
		if (htnoAndLine != null) {
			dhuo = dhBo.get(SinoUtils.parseDhuoPk(htnoAndLine));
		}
		CodeItem code = null;
		ZpbqVO vo = new ZpbqVO();
		if (dhuo == null) {
			vo.setUser(null);
			vo.setAbbr(null);
			vo.setDhno(null);
			vo.setDhline(null);
		}
		else {
			YongMp yongMp = yongBo.get(dhuo.getUser());
			if (yongMp != null) {
				vo.setUser(yongMp.getRsv1());
				vo.setAbbr(yongMp.getAbbr());
			}
			else {
				vo.setUser(dhuo.getName());
				vo.setAbbr(dhuo.getAbbr());
			}
			vo.setDhno(dhuo.getDhno());
			vo.setDhline(dhuo.getLine());
		}
		String pzno = zpng.getPzno();
		Code118 code118 = null;
		if (pzno != null && !pzno.isEmpty()) {
			code118 = Code118.get(pzno.substring(0, 1));
			if (code118 != null) {
				// 商品名
				vo.setSpm(code118.ename);
			}
			Integer zpzl = zpng.getZpzl();
			Integer zshu = zpng.getZshu();
			Integer jscn = zpng.getJscn();
			// 卷成品
			if (Code118.coil.key.equals(code118.key)
					|| DC.C.name.equals(zpng.getDuic())) {
				vo.setCang(jscn != null ? jscn + Code118.coil.unit : null);
			}
			else {
				// 板成品
				// 长(批量)
				vo.setCang(zshu != null ? zshu + code118.unit : null);
			}
			// 质量
			vo.setZl(zpzl != null ? zpzl.toString() : null);
		}
		// Package No.
		String nwai = zpng.getNwai();
		if (!CodeNwx.nei.type.equals(nwai)) {
			// 出口包装No
			if (zpng.getCkno() != null && zpng.getCkno() > 0) {
				vo.setPackageNo(StringUtils.fillChar("" + zpng.getCkno(), 6, '0'));
			}
		}
		if (zpng.getGgno() != null) {
			code = codeBo.getUniqueItemByValue(CmnConstants.CODE_018, zpng.getGgno());
			if (code != null) {
				code = codeBo.getCodeItem(CmnConstants.CODE_017, code.getKey());
				// 规格名称
				vo.setGgnm(code != null ? code.getValue() : null);
			}
		}
		// 镀锡量
		vo.setDxl(cmnBO.getDhfz(zpng.getFudw(), zpng.getFuzm(), zpng.getFufm(), zpng.getChdx()));
		// 表面精加工（值）
		code = codeBo.getCodeItem(CmnConstants.CODE_005, zpng.getFace());
		vo.setFace(code != null ? code.getValue() : null);
		vo.setFaceK(zpng.getFace());
		// 等级（制品等级）
		if (chan != null && !chan.isEmpty()) {
			vo.setPzdj(GRADE + "-" + chan);
			if (ChanType.fc.key.equals(chan)) {
				vo.setPzdj(null);
				vo.setSpm(null);
			}
			else if (ChanType.fs.key.equals(chan)) {
				vo.setDxl(null);
				if (code118 != null && Code118.coil.key.equals(code118.key)) {
					vo.setSpm(null);
				}
				vo.setFace(null);
				vo.setFaceK(null);
				vo.setGgnm(null);
			}
			else if (ChanType.bhg.key.equals(chan)
					|| ChanType.ns.key.equals(chan)) {
				vo.setSpm(null);
			}
			else if (ChanType.hg.key.equals(chan)
					|| ChanType.bl.key.equals(chan)) {
			}
		}
		// SPCC不用打印等级
		if (SPCC.equals(zpng.getGgno())) {
			vo.setPzdj(null);
		}
		// 尺寸
		vo.setSize(SinoUtils.formatProductSize(zpng.getXpho(), zpng.getXpkn(), zpng.getXpcn()));
		vo.setXpho(zpng.getXpho());
		vo.setXpkn(zpng.getXpkn());
		vo.setXpcn(zpng.getXpcn());
		// G/W
		if (zpng.getBzcl() != null && zpng.getBzcl() > 0) {
			vo.setGw(zpng.getBzcl().toString());
		}
		// 制造年月日
		vo.setDate(SDF.format(zpng.getCrea()));
		// 制品号
		vo.setZpno(jbno);
		// 捆包形式
		vo.setKbao(zpng.getKbao());
		return vo;
	}

	/**
	 * @return the zpBo
	 */
	public IZpBO getZpBo() {
		return zpBo;
	}

	/**
	 * @param zpBo
	 *            the zpBo to set
	 */
	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	/**
	 * @return the yongBo
	 */
	public IYongBO getYongBo() {
		return yongBo;
	}

	/**
	 * @param yongBo
	 *            the yongBo to set
	 */
	public void setYongBo(IYongBO yongBo) {
		this.yongBo = yongBo;
	}

	/**
	 * @return the codeBo
	 */
	public ICodeBO getCodeBo() {
		return codeBo;
	}

	/**
	 * @param codeBo
	 *            the codeBo to set
	 */
	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	/**
	 * @return the dhBo
	 */
	public IDhBO getDhBo() {
		return dhBo;
	}

	/**
	 * @param dhBo
	 *            the dhBo to set
	 */
	public void setDhBo(IDhBO dhBo) {
		this.dhBo = dhBo;
	}

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}

}
