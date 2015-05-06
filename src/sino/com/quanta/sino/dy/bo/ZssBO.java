package com.quanta.sino.dy.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.util.DateUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.bo.api.IZssBO;
import com.quanta.sino.dy.vo.JbVO;
import com.quanta.sino.dy.vo.ZssVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 指示书打印数据获取业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午09:59:45
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZssBO implements IZssBO {

	/**
	 * 指示业务接口
	 */
	private IZsBO bo;

	/**
	 * 原材业务接口
	 */
	private IYcaitpBO ycaiBo;

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 订货业务接口
	 */
	private IDhBO dhBo;

	/**
	 * 
	 */
	private ICmnBO cmnBO;

	@Override
	public ZssVO get(String zsno, ProductType type) {
		if (type == null) {
			throw new CocoException(-1, "打印类型无效");
		}
		ZsdhTp zsdh = bo.getZsdhTp(zsno);
		if (zsdh == null) {
			String msg = "订货指示书" + zsno + "不存在";
			throw new CocoException(-1, msg);
		}
		String htnoAndLine = zsdh.getDhno();
		if (htnoAndLine == null || htnoAndLine.length() != 9) {
			throw new CocoException(-1, "订货指示书中无效的订货合同No，指示连号：" + zsno);
		}
		// 查询订货Db
		DhuoTp dhuo = dhBo.get(SinoUtils.parseDhuoPk(htnoAndLine));
		if (dhuo == null) {
			throw new CocoException(-1, "订货指示书对应的订货合同不存在，指示连号：" + zsno);
		}
		ZssVO vo = new ZssVO();
		vo.setCond(zsdh.getCond());
		vo.setType(type.code);
		// 指示连号
		vo.setZsno(zsno);

		// 操作方法
		vo.setCzff(zsdh.getCaoz());
		// 装入宽
		vo.setZrk(NumberUtils.formatNumberComma(zsdh.getZrkn(), 2));
		// 剪断长
		if (zsdh.getJdcn() != null) {
			vo.setJdc(NumberUtils.formatNumberComma(zsdh.getJdcn(), 2));
		}
		if (zsdh.getJbbj() != null) {
			if (!"0".equals(zsdh.getJbbj())) {
				// 剪边标志
				vo.setTrim(zsdh.getJbbj());

			}
		}

		// trim后宽
		if (zsdh.getJbhk() != null) {
			vo.setTrimHk(NumberUtils.formatNumberComma(zsdh.getJbhk(), 2));
		}

		// 运用规格
		vo.setYygg(zsdh.getYuny());

		// 锡附着量（上/下）
		// vo.setXfzl(zsdh.getFugm());
		vo.setXfzl(cmnBO.getFugm(dhuo.getFudw(), dhuo.getFuzm(), dhuo.getFufm(), dhuo.getChdx()));

		// 附着量.正.上限
		vo.setFzsx(NumberUtils.formatNumber(zsdh.getFuzs(), 2));

		// 附着量.正.下限
		vo.setFzxx(NumberUtils.formatNumber(zsdh.getFuzx(), 2));

		// 附着量.反.上限
		vo.setFfsx(NumberUtils.formatNumber(zsdh.getFufs(), 2));

		// 附着量.反.下限
		vo.setFfxx(NumberUtils.formatNumber(zsdh.getFufx(), 2));

		// reflow：是|否
		vo.setReflow(zsdh.getFlow());

		// 表面精加工
		vo.setBmjg(zsdh.getFace());

		// 合金层
		vo.setHjc(zsdh.getHjin());

		// k-plate洗净强化
		vo.setKplate(zsdh.getXdqh());

		// 化学处理方法
		if (zsdh.getHxcl() != null && "300".equals(zsdh.getHxcl())) {
			vo.setHxcl(zsdh.getHxcl());
		}

		// 涂油种类
		if (zsdh.getYtyp() != null) {
			vo.setTyzl(zsdh.getYtyp());
		}

		// 涂油量
		vo.setTyl(zsdh.getYqty());

		// 用户略称
		vo.setYhlc(zsdh.getAbbr());

		// 合同号
		vo.setDhno(dhuo.getDhno());

		// 合同行号
		vo.setDhline(dhuo.getLine());

		// 销售W否
		vo.setW(zsdh.getXswf());

		// 分配等级
		vo.setFpdj(zsdh.getFpdj());

		// 钢种
		vo.setGz(zsdh.getGzlx());

		// 直角度
		if (zsdh.getJiao() != null) {
			vo.setZjd(NumberUtils.formatNumber(zsdh.getJiao(), 1));
		}
		// SHEET单重
		if (zsdh.getBdan() != null) {
			vo.setSdz(NumberUtils.formatNumber(zsdh.getBdan(), 3));
		}

		// m单重
		if (zsdh.getMdan() != null) {
			vo.setMdz(NumberUtils.formatNumber(zsdh.getMdan(), 3));
		}

		// 112张单重
		if (zsdh.getD112() != null) {
			vo.setDz112(NumberUtils.formatNumber(zsdh.getD112(), 3));
		}
		// 交货区分
		vo.setJhqf(zsdh.getJhdd());
		if (ZtConstants.DHZS_CAOZ_C.equals(zsdh.getCaoz())) {
			// 内径代码
			vo.setNeij(dhuo.getNeij());
		}

		// 保护筒 SLEEVE
		vo.setSleeve(zsdh.getNjbh());

		// PROTEXCT
		vo.setProtect(zsdh.getProt());

		// SCROLL可否
		vo.setSckf(zsdh.getJckf());

		// SCROLL种类
		vo.setSczl(zsdh.getJczl());

		// 制品尺寸
		vo.setSize(SinoUtils.formatProductSize(zsdh.getHouu(), zsdh.getKuan(), zsdh.getCang()));

		// 订货规格（为订货DB的运用规格）
		vo.setDhgg(dhuo.getYuny());

		// 订货附着量略称
		vo.setDhdxl(zsdh.getDhfz());

		// 尺寸允许范围-板厚%-下限
		vo.setBhxx(NumberUtils.formatNumber(zsdh.getHxzi(), 1));

		// 尺寸允许范围-板厚%-上限
		vo.setBhsx(NumberUtils.formatNumber(zsdh.getHszi(), 1));

		// 尺寸允许范围-板厚mm-下限
		vo.setBhmxx(NumberUtils.formatNumber(zsdh.getMxzi(), 3));

		// 尺寸允许范围-板厚mm-上限
		vo.setBhmsx(NumberUtils.formatNumber(zsdh.getMszi(), 3));

		// 尺寸允许范围-板宽mm-下限
		vo.setBkmxx(NumberUtils.formatNumber(zsdh.getKxzi(), 2));

		// 尺寸允许范围-板宽mm-上限
		vo.setBkmsx(NumberUtils.formatNumber(zsdh.getKszi(), 2));

		// 尺寸允许范围-板长mm-下限
		vo.setBcmxx(NumberUtils.formatNumber(zsdh.getCxzi(), 2));

		// 尺寸允许范围-板长mm-上限
		vo.setBcmsx(NumberUtils.formatNumber(zsdh.getCszi(), 2));

		// 包装张数
		if (zsdh.getBzzs() != null) {
			vo.setBzzs(NumberUtils.formatNumberComma(zsdh.getBzzs(), 0));
		}

		// Lot重量
		if (zsdh.getBdan() != null && zsdh.getBzzs() != null) {
			vo.setLotZl(NumberUtils.formatNumber(zsdh.getBdan()
					* zsdh.getBzzs() * 0.001, 3));
		}

		// B/B重量
		if (zsdh.getBbz() != null) {
			vo.setBbZl(NumberUtils.formatNumber(zsdh.getBbz(), 3));
		}

		// 零头可否
		vo.setLing(zsdh.getLtkf());

		if (zsdh.getLtxx() != null) {
			vo.setLtxx(zsdh.getLtxx().toString());
		}

		// Tin补正系数
		if (zsdh.getBzxs() != null) {
			vo.setTin(NumberUtils.formatNumber(zsdh.getBzxs(), 3));
		}

		// 垫木方向
		vo.setDmfx(zsdh.getDmfx());

		// 垫木重量
		if (zsdh.getDmzl() != null) {
			vo.setDmzl(zsdh.getDmzl().toString());
		}

		// 包装材料重量
		if (zsdh.getBzcl() != null) {
			vo.setBzclZl(zsdh.getBzcl().toString());
		}

		// 捆包指定-下限
		if (zsdh.getKbzx() != null) {
			vo.setKbxx(NumberUtils.formatNumber(zsdh.getKbzx(), 3));
		}

		// 捆包指定-上限
		if (zsdh.getKbzs() != null) {
			vo.setKbsx(NumberUtils.formatNumber(zsdh.getKbzs(), 3));
		}

		// 捆包形式
		vo.setKbxs(zsdh.getKbao());

		// 规格
		vo.setGgnm(zsdh.getSpec());

		// 货标
		vo.setHuob("");

		// Package No.
		vo.setPackageNo("");

		// 用户名称
		vo.setUser(zsdh.getName());

		// 订货纳期
		vo.setDhnq(DateUtils.format(zsdh.getJhqi(), "yyyy-MM-dd"));

		// 备注
		if (EKpType.ETL.key.equals(type.code)) {
			// 木工所No.
			vo.setMgsn(dhuo.getMgsn());
			vo.setBz1(dhuo.getBz1());
			vo.setBz3(dhuo.getBz3());
		}
		else {
			vo.setBz2(dhuo.getBz2());
			vo.setBz3(dhuo.getBz3());
		}
		// 附页kpn1Flag 标示位
		String kpn1Flag = dhuo.getKpn1Flag();
		String kpn1 = dhuo.getKpn1();
		// 附页kpn2Flag 标示位
		String kpn2Flag = dhuo.getKpn2Flag();
		String kpn2 = dhuo.getKpn2();
		// 附页kpn3Flag 标示位
		String kpn3Flag = dhuo.getKpn3Flag();
		String kpn3 = dhuo.getKpn3();
		// 附页kpn4Flag 标示位
		String kpn4Flag = dhuo.getKpn4Flag();
		String kpn4 = dhuo.getKpn4();
		// 获取卷板列表
		List<JbVO> jbs = new ArrayList<JbVO>();
		JbVO jb;
		int jbsIndex = 0;
		Integer zpzlall = 0;
		if (EKpType.ETL.key.equals(type.code)) {
			// 备注
			YcaiQE ycaiQe = new YcaiQE();
			ycaiQe.setZsno(zsno);
			ycaiBo.query(ycaiQe);
			for (YcaiTp ycai : ycaiQe.getDatas()) {
				jb = new JbVO();
				// 卷板No.
				jb.setJbno(ycai.getJbno());
				// 重量
				jb.setZl(NumberUtils.formatNumberComma(ycai.getZpzl(), 0));
				zpzlall = zpzlall + ycai.getZpzl();
				// 卷板长
				jb.setJbc(NumberUtils.formatNumberComma(ycai.getJbcn(), 0));
				// W个数
				jb.setWgs(StringUtils.concat(ycai.getRjly(), ",", ycai.getRjsx()).replaceAll("^,{1}$", ""));
				// 厚不良有无
				jb.setHbl("");
				// 别纸KpNo.(当别纸的标示位为etl和ss是，则在打印etl指示书的时候要显示对应的别纸)
				if ((EKpType.ETL.key.equals(kpn1Flag) || EKpType.BOTH.key.equals(kpn1Flag))
						&& kpn1 != null && !kpn1.isEmpty()) {
					jb.setBzkpNo1(kpn1);
				}
				if ((EKpType.ETL.key.equals(kpn2Flag) || EKpType.BOTH.key.equals(kpn2Flag))
						&& kpn2 != null && !kpn2.isEmpty()) {
					jb.setBzkpNo2(kpn2);
				}
				if ((EKpType.ETL.key.equals(kpn3Flag) || EKpType.BOTH.key.equals(kpn3Flag))
						&& kpn3 != null && !kpn3.isEmpty()) {
					jb.setBzkpNo3(kpn3);
				}
				if ((EKpType.ETL.key.equals(kpn4Flag) || EKpType.BOTH.key.equals(kpn4Flag))
						&& kpn4 != null && !kpn4.isEmpty()) {
					jb.setBzkpNo4(kpn4);
				}

				// String ylnNo = ycai.getYlno();
				// String ylntm = "";
				// if (ylnNo != null) {
				// String[] ylns1 = ylnNo.split(",");
				// String ylty;
				// String ylns2;
				// for (String yln : ylns1) {
				// ylty = yln.substring(0, 1);
				// ylns2 = yln.substring(2);
				// if (EKpType.ETL.key.equals(ylty)
				// || EKpType.BOTH.key.equals(ylty)) {
				// ylntm = ylntm + "," + ylns2;
				//
				// }
				//
				// }
				// }
				// if (ylntm.length() > 1) {
				// jb.setYwll(ylntm.substring(1));
				// }
				// 业务联络单
				jb.setYwll(getYlno(ycai.getYlno(), type));
				// 库位代码
				jb.setKw(ycai.getKw());
				// 制造商代码
				jb.setZzsd(ycai.getZzsd());
				jbs.add(jb);
				jbsIndex++;
			}
		}
		else if (ProductType.sl.code.equals(type.code)) {
			// 备注
			ZpQE zpQe = new ZpQE();
			zpQe.setZsno(zsno);
			zpQe.setDuic(DC.D.name);
			zpBo.query(zpQe);
			for (ZpngTp zpng : zpQe.getDatas()) {
				jb = new JbVO();
				// 卷板No.
				jb.setJbno(zpng.getJbno());
				// 重量
				jb.setZl(NumberUtils.formatNumberComma(zpng.getSjzl(), 0));
				zpzlall = zpzlall + zpng.getSjzl();
				// 卷板长
				jb.setJbc(NumberUtils.formatNumberComma(zpng.getJbcn(), 0));
				// W个数
				jb.setWgs(StringUtils.concat(zpng.getRjly(), ",", zpng.getRjsx()).replaceAll("^,{1}$", ""));
				// 厚不良有无
				jb.setHbl(zpng.getBhbl());
				// 别纸KpNo.(当别纸的标示位为sl和ss是，则在打印SL指示书的时候要显示对应的别纸)
				if ((EKpType.SL.key.equals(kpn1Flag) || EKpType.BOTH.key.equals(kpn1Flag))
						&& kpn1 != null && !kpn1.isEmpty()) {
					jb.setBzkpNo1(kpn1);
				}
				if ((EKpType.SL.key.equals(kpn2Flag) || EKpType.BOTH.key.equals(kpn2Flag))
						&& kpn2 != null && !kpn2.isEmpty()) {
					jb.setBzkpNo2(kpn2);
				}
				if ((EKpType.SL.key.equals(kpn3Flag) || EKpType.BOTH.key.equals(kpn3Flag))
						&& kpn3 != null && !kpn3.isEmpty()) {
					jb.setBzkpNo3(kpn3);
				}
				if ((EKpType.SL.key.equals(kpn4Flag) || EKpType.BOTH.key.equals(kpn4Flag))
						&& kpn4 != null && !kpn4.isEmpty()) {
					jb.setBzkpNo4(kpn4);
				}
				// String ylnNo = zpng.getYlno();
				// String ylntm = "";
				// if (ylnNo != null) {
				// String[] ylns1 = ylnNo.split(",");
				// String ylns2;
				// for (String yln : ylns1) {
				// ylns2 = yln.substring(2);
				// ylntm = ylntm + "," + ylns2;
				//
				// }
				// }
				// if (ylntm.length() > 1) {
				// jb.setYwll(ylntm.substring(1));
				// }
				// 业务联络单
				jb.setYwll(getYlno(zpng.getYlno(), type));
				// 制造商代码
				jb.setZzsd(zpng.getZzsd());
				jbs.add(jb);
				jbsIndex++;
			}
		}
		// Box数
		Double dmsz1 = 0.0;
		Integer dmsz2 = 0;
		if (vo.getLotZl() != null) {
			Double lotzl = Double.parseDouble(vo.getLotZl());
			dmsz1 = zpzlall * 0.001 / lotzl;
			if (dmsz1 >= (NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 0.3)) {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN) + 1;
			}
			else {
				dmsz2 = NumberUtils.formatDouToInt(dmsz1, 0, BigDecimal.ROUND_DOWN);
			}
			vo.setBoxs(dmsz2.toString());
		}
		for (int i = jbsIndex; i < 5; i++) {
			jbs.add(new JbVO());
		}
		vo.setJbs(jbs);
		return vo;
	}

	private String getYlno(String ylno, ProductType type) {
		if (ylno == null || ylno.isEmpty()) {
			return null;
		}
		String[] arrs = ylno.split(",");
		String prefix;
		String no = null;
		if (ProductType.sl.code.equals(type.code)) {
			for (String s : arrs) {
				prefix = s.substring(0, 1);
				if (EKpType.ETL.key.equals(prefix)) continue;
				if (no == null) {
					no = s.substring(2);
					continue;
				}
				no = no + "," + s.substring(2);
			}
		}
		else if (ProductType.etl.code.equals(type.code)) {
			for (String s : arrs) {
				prefix = s.substring(0, 1);
				if (EKpType.SL.key.equals(prefix)) continue;
				if (no == null) {
					no = s.substring(2);
					continue;
				}
				no = no + "," + s.substring(2);
			}
		}
		return no;
	}

	/**
	 * @return the bo
	 */
	public IZsBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZsBO bo) {
		this.bo = bo;
	}

	/**
	 * @return the ycaiBo
	 */
	public IYcaitpBO getYcaiBo() {
		return ycaiBo;
	}

	/**
	 * @param ycaiBo
	 *            the ycaiBo to set
	 */
	public void setYcaiBo(IYcaitpBO ycaiBo) {
		this.ycaiBo = ycaiBo;
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

	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}
}
