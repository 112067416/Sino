package com.quanta.sino.dy.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.dbgl.bo.api.IDbglBO;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.bo.api.IZpkBO;
import com.quanta.sino.dy.vo.ZpkDataVO;
import com.quanta.sino.dy.vo.ZpkVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DbglTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 制品卡打印数据获取业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午09:58:24
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZpkBO implements IZpkBO {
	/**
	 * 端板制品业务接口
	 */
	private IDbglBO dbglBO;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 订货业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 代码业务接口
	 */
	private ICodeBO codeBo;

	/**
	 * 公共业务接口
	 */
	private ICmnBO cmnBO;

	/**
	 * 将prime换成grade-1
	 */
	private static String GRADE = "GRADE";

	/**
	 * SPCC规格打印等级
	 */
	private static String SPCC = "SPCC";

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"MMdd yyyy");

	/**
	 * js串生成忽略的字段
	 */
	private static final Set<String> IGNORE_FIELDS = new HashSet<String>();

	static {
		IGNORE_FIELDS.add("jbnos");
	}

	@Override
	public ZpkVO get(String jbno) {
		// 查询制品在库
		ZpngTp zpng = zpBo.getZp(jbno);
		if (zpng == null) {
			throw new CocoException(-1, "无法找到对应的制品信息，制品号：" + jbno);
		}
		ZpkVO vo = new ZpkVO();
		String htnoAndLine = zpng.getDhno();
		String dhno = null, dhline = null;
		if (htnoAndLine != null && !htnoAndLine.isEmpty()) {
			// 查询订货Db
			dhno = htnoAndLine.substring(0, 7);
			dhline = htnoAndLine.substring(7);
			vo.setDhno(dhno);
			vo.setDhline(dhline);
			vo.setYhlc(zpng.getAbbr());
			vo.setYhdm(zpng.getUser());
		}
		else {
			vo.setDhno(null);
			vo.setDhline(null);
			vo.setYhlc(null);
			vo.setYhdm(null);
		}
		// 制品No.
		vo.setZpno(StringUtils.concat(zpng.getPlqf(), zpng.getZzno(), "-", jbno));
		// 生成日期
		vo.setDate(SDF.format(zpng.getCrea()));
		String nwai = zpng.getNwai();
		if (!CodeNwx.nei.type.equals(nwai)) {
			// 出口包装No
			if (zpng.getCkno() != null && zpng.getCkno() > 0) {
				vo.setPackageNo(StringUtils.format(zpng.getCkno(), "%06d"));
				vo.setPackageNoBz("PACKAGE  NO  " + vo.getPackageNo());
			}
		}
		// 指示书No.
		vo.setZsno(zpng.getZsno());
		// 包装形式
		vo.setBzxs(zpng.getKbao());
		// 等级
		vo.setDj(zpng.getDeng());
		// 主缺陷
		vo.setZqx(zpng.getQqdm());
		// 采取Piler
		vo.setPiler(zpng.getCqpl());
		// 表面精加工（值）
		CodeItem code = codeBo.getCodeItem(CmnConstants.CODE_005, zpng.getFace());
		if (code != null) {
			vo.setFace(code.getValue());
		}
		vo.setFaceK(zpng.getFace());
		// 镀锡量
		vo.setDjl(cmnBO.getDhfz(zpng.getFudw(), zpng.getFuzm(), zpng.getFufm(), zpng.getChdx()));
		// 钢种类型和运用规格
		String gzlx = zpng.getGzlx();
		gzlx = gzlx != null ? gzlx : "";
		gzlx = StringUtils.fillChar(gzlx, 2, ' ');
		code = codeBo.getCodeItem(CmnConstants.CODE_019, zpng.getYblc());
		String yuny = zpng.getYuny();
		yuny = yuny != null ? yuny : "";
		vo.setTemper(StringUtils.concat(gzlx, " ", yuny));
		// 尺寸
		vo.setSize(SinoUtils.formatProductSize(zpng.getXpho(), zpng.getXpkn(), zpng.getXpcn()));
		vo.setXpho(zpng.getXpho() != null ? NumberUtils.formatNumber(zpng.getXpho(), 3)
				: "");
		vo.setXpkn(zpng.getXpkn() != null ? NumberUtils.formatNumber(zpng.getXpkn(), 2)
				: "");
		vo.setXpcn(zpng.getXpcn() != null ? NumberUtils.formatNumber(zpng.getXpcn(), 2)
				: "COIL");
		int phgs = zpng.getPhgs() != null ? zpng.getPhgs() : 0;
		vo.setPinhole(phgs > 0 ? phgs + "" : null);
		// 单量
		if (zpng.getBdan() != null && zpng.getBdan() != 0) {
			vo.setDl(NumberUtils.formatNumber(zpng.getBdan(), 3));
		}
		// 包装张数
		String pzno = zpng.getPzno();
		Code118 code118 = null;
		if (pzno != null && !pzno.isEmpty()) {
			code118 = Code118.get(pzno.substring(0, 1));
			Integer zpzl = zpng.getZpzl();
			Integer zshu = zpng.getZshu();
			Integer jscn = zpng.getJscn();
			// 卷成品
			if (Code118.coil.key.equals(code118.key)
					|| DC.C.name.equals(zpng.getDuic())) {
				vo.setBzzs(jscn != null ? jscn + Code118.coil.unit : null);
				vo.setDl(NumberUtils.formatNumber(zpng.getMdan(), 3));
			}
			else {
				// 板成品
				// 长(批量)
				vo.setBzzs(zshu != null ? zshu + "" : null);
				// 足垫木方向
				vo.setW(SinoUtils.getSkidFx(zpng.getXpkn(), zpng.getXpcn(), zpng.getYyan(), zpng.getDmfx()));
			}
			// 质量
			vo.setZl(zpzl != null ? zpzl.toString() : null);
		}
		// 重量
		if (zpng.getZpzl() != null && zpng.getZpzl() > 0) {
			vo.setZl(StringUtils.fillChar(zpng.getZpzl().toString(), 4, '0'));
		}
		// 检定员
		vo.setJdy(zpng.getJdyn());
		// 计数员
		vo.setJsy(zpng.getJsyn());
		// 卷板No.
		vo.setJbno(jbno);
		// 堆场
		vo.setDuic(zpng.getDuic());
		String chan = zpng.getChan();
		// 产量码
		vo.setClm(chan);
		// 等级（制品等级）
		if (chan != null && !chan.isEmpty()) {
			vo.setPzdj(GRADE + "-" + chan);
			if (ChanType.fc.key.equals(chan)) {
				vo.setPzdj(null);
			}
			else if (ChanType.fs.key.equals(chan)) {
				vo.setDjl(null);
				vo.setFace(null);
				vo.setTemper(null);
			}
			else if (ChanType.bhg.key.equals(chan)
					|| ChanType.ns.key.equals(chan)) {
			}
			else if (ChanType.hg.key.equals(chan)
					|| ChanType.bl.key.equals(chan)) {
			}
		}
		// SPCC不用打印等级
		if (SPCC.equals(zpng.getGgno())) {
			vo.setPzdj(null);
		}
		return vo;
	}

	@Override
	public ZpkVO getdb(String jbno) {
		// 查询制品在库
		DbglTp dbgl = dbglBO.get(jbno);
		if (dbgl == null) {
			throw new CocoException(-1, "无法找到对应的制品信息，制品号：" + jbno);
		}
		ZpkVO vo = new ZpkVO();

		// 制品No.
		vo.setZpno(StringUtils.concat("", "", "-", jbno));
		// 生成日期
		vo.setDate(SDF.format(dbgl.getCrea()));
		// 包装形式
		vo.setBzxs(dbgl.getKbao());

		// 重量
		if (dbgl.getZpzl() != null && dbgl.getZpzl() > 0) {
			vo.setZl(StringUtils.fillChar(dbgl.getZpzl().toString(), 4, '0'));
		}

		return vo;
	}

	@Override
	public String getForJs(ZpkDataVO vo) {
		if (vo == null || vo.getJbnos() == null || vo.getJbnos().length == 0) {
			throw new CocoException(-1, "至少给定一个制品号");
		}
		ZpkVO item = null;
		List<ZpkVO> items = new ArrayList<ZpkVO>();
		for (String jbno : vo.getJbnos()) {
			item = get(jbno);
			items.add(item);
		}
		vo.setDatas(items);
		return new Result(vo).toJsObject(null, IGNORE_FIELDS);
	}

	@Override
	public String getdbForJs(ZpkDataVO vo) {
		if (vo == null || vo.getJbnos() == null || vo.getJbnos().length == 0) {
			throw new CocoException(-1, "至少给定一个制品号");
		}
		ZpkVO item = null;
		List<ZpkVO> items = new ArrayList<ZpkVO>();
		for (String jbno : vo.getJbnos()) {
			item = getdb(jbno);
			items.add(item);
		}
		vo.setDatas(items);
		return new Result(vo).toJsObject(null, IGNORE_FIELDS);
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

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}

	public IDbglBO getDbglBO() {
		return dbglBO;
	}

	public void setDbglBO(IDbglBO dbglBO) {
		this.dbglBO = dbglBO;
	}

}
