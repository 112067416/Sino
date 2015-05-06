package com.quanta.sino.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.EDm;
import com.quanta.sino.cmn.constants.EKpType;
import com.quanta.sino.cmn.constants.EYlType;
import com.quanta.sino.cmn.constants.EYy;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.dh.constants.DhYy;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;

/**
 * <p>
 * 中日达工具类
 * </p>
 * <p>
 * create: 2010-12-13 上午11:14:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SinoUtils {

	/**
	 * 密度(单位：g/cm^2，即7.85×10^3kg/m ^3， 即7.85×10^-3g/mm^3)
	 */
	public static final double DENSITY = 7.85;

	/**
	 * <p>
	 * 根据宽、厚和重量获取板长
	 * </p>
	 * 
	 * @param kuan
	 *            宽
	 * @param hou
	 *            厚
	 * @param zl
	 *            重量
	 * @return int 计算结果（kg）
	 */
	public static int calculate(Double kuan, Double hou, Double zl) {
		if (kuan == null || kuan <= 0 || hou == null || hou <= 0 || zl == null
				|| zl <= 0) {
			return 0;
		}
		return (int) Math.round((zl * 1000000) / (hou * kuan * DENSITY));
	}

	/**
	 * <p>
	 * 根据内径值、厚获取钢卷截面面积
	 * </p>
	 * 
	 * @param nj
	 *            内径值
	 * @param hou
	 *            厚(mm)
	 * @return 计算结果（kg）
	 */
	public static Double calculate(Double nj, Integer hou) {
		return Math.PI * Math.pow((nj * 0.5 + hou), 2) - Math.PI
				* Math.pow((nj * 0.5), 2);
	}

	/**
	 * <p>
	 * 根据钢卷截面面积和钢卷宽获取钢卷重量
	 * </p>
	 * 
	 * @param cutR
	 *            钢卷截面面积
	 * @param kuan
	 *            钢卷宽(mm)
	 * @return Double 计算结果（kg）
	 */
	public static Double calculate(Double cutR, Double kuan) {
		if (cutR == null || cutR <= 0 || kuan == null || kuan <= 0) {
			return 0.0;
		}
		return cutR * kuan * 0.000001 * DENSITY;
	}

	/**
	 * <p>
	 * 根据钢卷重量、钢卷宽、钢卷厚、m单重获取卷板长
	 * </p>
	 * 
	 * @param yclh
	 *            钢卷重量
	 * @param kuan
	 *            钢卷宽(mm)
	 * @param hou
	 *            钢卷厚(mm)
	 * @param mdan
	 *            m单重
	 * @return Double 计算结果（kg）
	 */
	public static int calculate(Double yclh, Double kuan, Integer hou,
			Double mdan) {
		if (yclh == null || yclh <= 0 || kuan == null || kuan <= 0
				|| hou == null || hou <= 0 || mdan == null || mdan <= 0) {
			return 0;
		}
		// return (int) Math.round((yclh / mdan) * kuan * 0.001 * hou * 0.001
		// * DENSITY);
		return (int) Math.round(yclh / mdan);
	}

	/**
	 * <p>
	 * 格式化制品尺寸【厚*宽*长】
	 * </p>
	 * 
	 * @param h
	 *            厚
	 * @param k
	 *            宽
	 * @param c
	 *            长
	 * @return String 厚*宽*长
	 */
	public static String formatProductSize(Double h, Double k, Double c) {
		if (h == 0 && k == 0) {
			return "";
		}
		String hou = h != null ? NumberUtils.formatNumber(h, 3) : "";
		String kuan = k != null ? NumberUtils.formatNumber(k, 2) : "";
		String chang = (c == null || c <= 0 ? "COIL"
				: NumberUtils.formatNumber(c, 2));
		return StringUtils.concat(hou, "*", kuan, "*", chang);
	}

	/**
	 * <p>
	 * 获得足垫木的方向
	 * </p>
	 * 
	 * @param k
	 * @param c
	 * @param yyan
	 * @param dmfx
	 * @return String
	 */
	public static String getSkidFx(Double k, Double c, String yyan, String dmfx) {
		if (k == null || k.doubleValue() == 0 || c == null
				|| c.doubleValue() == 0) {
			return "";
		}
		if (k.doubleValue() != c.doubleValue()) {
			if (DhYy.YY_DB.key.equals(yyan) && EDm.L.key.equals(dmfx)) {
				return "W";
			}
			else if (DhYy.YY_CB.key.equals(yyan) && EDm.C.key.equals(dmfx)) {
				return "W";
			}
		}
		else {
			if (EDm.C.key.equals(dmfx)) {
				return "W";
			}
		}
		return "";
	}

	/**
	 * <p>
	 * 根据厚、宽、长、品种代码、压延方向取尺寸的W表示(张良注：该方法已作废。客户提出没有换算尺寸)
	 * </p>
	 * 
	 * @param h
	 *            厚
	 * @param k
	 *            宽
	 * @param c
	 *            长
	 * @param pzno
	 *            品种代码
	 * @param yyan
	 *            压延方向
	 * @return String
	 */
	@Deprecated
	public static String formatProductSize(Double h, Double k, Double c,
			String pzno, String yyan) {
		// 非空检查
		if (pzno == null || pzno.isEmpty() || yyan == null || yyan.isEmpty()) {
			return formatProductSize(h, k, c);
		}
		// 合法性校验
		String pz = pzno.substring(0, 1);
		Code118 epz = Code118.get(pz);
		EYy eyy = EYy.get(yyan);
		if (epz == null) {
			return formatProductSize(h, k, c);
		}
		// 尺寸
		String cc = null;
		// 品种为剪切板或锯齿板时
		if (Code118.sheet.equals(epz) || Code118.scroll.equals(epz)) {
			if (eyy == null) {
				return formatProductSize(h, k, c);
			}
			// 短边压压延方向
			if (EYy.YY_1.equals(eyy)) {
				// 宽>长
				if (k > c) {
					cc = h + "*" + k + "W*" + c;
				}
				// 长>宽
				else if (c > k) {
					cc = h + "*" + k + "*" + c + "W";
				}
				// 其它
				else {
					cc = h + "*" + k + "*" + c;
				}
			}
			// 长边压压延方向
			else if (EYy.YY_2.equals(eyy)) {
				// 宽>长
				if (k > c) {
					cc = h + "*" + k + "*" + c + "W";
				}
				// 长>宽
				else if (c > k) {
					cc = h + "*" + k + "W*" + c;
				}
				// 其它
				else {
					cc = h + "*" + k + "*" + c;
				}
			}
		}
		// 品种为钢卷时
		else if (Code118.mothor.equals(epz) || Code118.coil.equals(epz)) {
			cc = h + "*" + k + "*" + "COIL";
		}
		return cc;
	}

	/**
	 * <p>
	 * 解析订货No为订货主键
	 * </p>
	 * 
	 * @param dhno
	 * @return DhuoTpPk
	 */
	public static DhuoTpPk parseDhuoPk(String dhno) {
		if (dhno == null || dhno.length() != 9) {
			return null;
		}
		return new DhuoTpPk(dhno.substring(0, 7), dhno.substring(7));
	}

	/**
	 * <p>
	 * 计算包重量
	 * </p>
	 * 
	 * @param houu
	 *            厚（mm）
	 * @param kuan
	 *            宽（mm）
	 * @param cang
	 *            长（mm）
	 * @param zshu
	 *            张数
	 * @return double 计算结果（kg）
	 */
	public static Integer calculate(Double houu, Double kuan, Double cang,
			Integer zshu) {
		if (houu == null || kuan == null || cang == null || zshu == null) {
			return 0;
		}
		Double jszlTemp = DENSITY * houu * kuan * cang * zshu * 0.000001;
		Integer jszl = NumberUtils.formatDouToInt(jszlTemp, 0, BigDecimal.ROUND_DOWN);
		return jszl;

	}

	/**
	 * <p>
	 * 计算薄板单重
	 * </p>
	 * <p>
	 * <b>计算公式： </b><br />
	 * W1=7.85*换算尺寸•厚；<br />
	 * W2=换算尺寸•宽*换算尺寸•长*10-6；<br />
	 * 板材单重（薄板单重）=W1*W2。
	 * </p>
	 * 
	 * @param hngh
	 *            订货尺寸·厚（mm）
	 * @param hngk
	 *            订货尺寸·宽（mm）
	 * @param hngc
	 *            订货尺寸·长（mm）
	 * @return double 计算结果（kg）
	 */
	public static Double calculateBdan(Double houu, Double kuan, Double cang) {
		if (houu == null || kuan == null || cang == null) {
			return 0.0;
		}
		return NumberUtils.format(DENSITY * houu * kuan * cang * 0.000001, 3, BigDecimal.ROUND_DOWN);
	}

	/**
	 * <p>
	 * M单重，即单位长度的薄板重量 ，一般针对卷材<br />
	 * <p>
	 * <b>计算公式： </b><br />
	 * M单重 = 换算尺寸·厚 * 换算尺寸·宽 * 密度 / 1000
	 * </p>
	 * 
	 * @param hngh
	 *            换算尺寸·厚
	 * @param hngk
	 *            换算尺寸·宽
	 * @return Double：M单重，单位：吨
	 */
	public static Double calculateMdan(Double hngh, Double hngk) {
		if (hngh == null || hngk == null) {
			return 0.0;
		}
		return NumberUtils.format(SinoUtils.DENSITY * hngh * hngk * 0.001, 3, BigDecimal.ROUND_DOWN);
	}

	/**
	 * <p>
	 * 获取制品重量，制品重量（制品重量也叫出货重量，制品卡打印时取该重量）的获取方法为：
	 * <ul>
	 * <li>品种编号第一位为１、３（剪切板、波形板）时，取净重。</li>
	 * <li>品种编号第一位为２（马口铁钢卷）时，根据重量内容获取：
	 * <p>
	 * A. 重量内容为１，６时　，将“净重”作为制品重量。
	 * </p>
	 * <p>
	 * B. 重量内容为２，３，７，８时，将“毛重”作为制品重量。
	 * </p>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @see com.quanta.sino.cmn.constants.Code118 品种代码
	 * @see com.quanta.sino.cmn.constants.ZlnrCode 重量内容代码
	 * @param zpng
	 *            制品信息
	 * @return int 制品重量(Kg)，返回0表示无效信息
	 */
	public static int fetchZpzl(ZpngTp zpng) {
		if (zpng == null || zpng.getPzno() == null || zpng.getPzno().isEmpty()) {
			return 0;
		}
		String pzdm = zpng.getPzno().substring(0, 1);
		Code118 code118 = Code118.get(pzdm);
		if (code118 == null) {
			return 0;
		}
		// 品种编号第一位为１、３（剪切板、波形板）时，取净重。
		if (code118 == Code118.sheet || code118 == Code118.scroll) {
			return zpng.getJnzl();
		}
		// 品种编号第一位为２（马口铁钢卷）时，根据重量内容获取
		if (code118 == Code118.coil) {
			ZlnrCode nr = ZlnrCode.get(zpng.getJhnr());
			if (nr == null) {
				return 0;
			}
			// 重量内容为１，６时　，将“净重”作为制品重量
			if (nr == ZlnrCode.nr1 || nr == ZlnrCode.nr6) {
				return zpng.getJnzl();
			}
			// 否则（即重量内容为２，３，７，８时），将“毛重”作为制品重量
			return zpng.getMazl();
		}
		return 0;
	}

	/**
	 * <p>
	 * 获取净重
	 * <ul>
	 * <li>当重量内容为１、２、３　时　以“实际重量”　作为净重</li>
	 * <li>否则（即重量内容为６、７、８时），以“计算重量”　作为净重</li>
	 * </ul>
	 * </p>
	 * 
	 * @param zlnr
	 *            重量内容
	 * @param sjzl
	 *            实际重量
	 * @param jszl
	 *            计算重量
	 * @return int 净重
	 */
	public static int fetchJnzl(String zlnr, Integer sjzl, Integer jszl,
			String chan) {

		ZlnrCode nr = ZlnrCode.get(zlnr);
		if (nr == null) {
			return 0;
		}
		// 当重量内容为１、２、３　时　以“实际重量”　作为净重
		if (nr == ZlnrCode.nr1 || nr == ZlnrCode.nr2 || nr == ZlnrCode.nr3) {
			return sjzl;
		}
		// 不合格品
		if (!ChanType.hg.key.equals(chan) && !ChanType.bl.key.equals(chan)) {
			return sjzl;
		}
		// 否则（即重量内容为６、７、８时），以“计算重量”　作为净重
		return jszl;
	}

	/**
	 * <p>
	 * 获取毛重
	 * </p>
	 * 
	 * @param zlnr
	 *            重量内容
	 * @param jnzl
	 *            净重量
	 * @param bzcl
	 *            包装材料重量（Kg）
	 * @param dmzl
	 *            垫木重量
	 * @return
	 */
	public static int fetchMazl(String zlnr, Integer jnzl, Integer bzcl,
			Integer dmzl) {
		// ZlnrCode nr = ZlnrCode.get(zlnr);
		// if (nr == null) {
		// return jnzl;
		// }
		// if (ZlnrCode.nr2 == nr || ZlnrCode.nr7 == nr) {
		// return jnzl + bzcl;
		// }
		// if (ZlnrCode.nr3 == nr || ZlnrCode.nr8 == nr) {
		// return jnzl + bzcl + dmzl;
		// }
		jnzl = jnzl == null ? 0 : jnzl;
		bzcl = bzcl == null ? 0 : bzcl;
		dmzl = dmzl == null ? 0 : dmzl;
		return jnzl + bzcl + dmzl;
		// return jnzl;
	}

	/**
	 * <p>
	 * 解析业连号<br />
	 * <b>业连说明：</b>
	 * <ul>
	 * <li>业连对应的每个现品，形如'1-000001,2-000002,3-000003'</li>
	 * <li>业连号：'1-000001'。前一位为标识位，后一位为真正的业连号码，与资料室中所上传的业连资料名称对应</li>
	 * </ul>
	 * </p>
	 * <p>
	 * author: 方元龙
	 * </p>
	 * 
	 * @param ylno
	 *            业连号
	 * @param type
	 *            {@link EYlType} 业连类型，默认为ETL
	 * @return List<String> 业连号的泛型集合
	 */
	public static List<String> parseYl(String ylno, EYlType type) {
		if (ylno == null || ylno.isEmpty()) {
			return null;
		}
		if (type == null) {
			type = EYlType.ETL;
		}
		// 分解业连号
		List<String> listYl = null;
		String[] item = null;
		String[] items = ylno.split(CmnConstants.YLNO_SPLIT_PART);
		if (items != null && items.length > 0) {
			listYl = new ArrayList<String>();
			for (String no : items) {
				if (no == null || (no = no.trim()).isEmpty()) {
					continue;
				}
				item = no.split(CmnConstants.YLNO_SPLIT_BREAK);
				// 业连标识
				String flag = item[0] != null ? item[0].trim() : null;
				EYlType t = EYlType.get(flag);
				if (flag == null) {
					continue;
				}
				// 号码
				String yl = item[1] != null ? item[1].trim() : null;
				if (yl == null) {
					continue;
				}
				// 类型为非BOTH时，加入对应值
				if (type != EYlType.BOTH) {
					if (type == t) {
						listYl.add(yl);
					}
				}
				// 任何情况，都需要加BOTH值
				if (t == EYlType.BOTH) {
					listYl.add(yl);
				}
			}
		}
		return listYl;
	}

	/**
	 * <p>
	 * 解析附业号<br />
	 * <b>附页说明：</b>
	 * <ul>
	 * <li>附页针对每个订货合同；</li>
	 * <li>这里取指示书中的附页，亦即订货合同中的附页；</li>
	 * <li>完整的附页是两个字段组成的键值对:flag/kpno。前一位即标识位，后一位为附页号码，与资料室中所上传的业连资料名称对应</li>
	 * </ul>
	 * </p>
	 * <p>
	 * author: 方元龙
	 * </p>
	 * 
	 * @param zsdh
	 *            指示书
	 * @param type
	 *            {@link EKpType} 附页类型，默认为ETL
	 * @return List<String> 附页号的泛型集合
	 */
	public static List<String> parseKp(ZsdhTp zsdh, EKpType type) {
		if (zsdh == null) {
			return null;
		}
		if (type == null) {
			type = EKpType.ETL;
		}
		List<String> listKp = new ArrayList<String>();
		EKpType flag1 = EKpType.get(zsdh.getKpn1Flag());
		EKpType flag2 = EKpType.get(zsdh.getKpn2Flag());
		EKpType flag3 = EKpType.get(zsdh.getKpn3Flag());
		EKpType flag4 = EKpType.get(zsdh.getKpn4Flag());
		if (flag1 != null && (type == flag1 || flag1 == EKpType.BOTH)) {
			listKp.add(zsdh.getKpn1());
		}
		if (flag2 != null && (type == flag2 || flag2 == EKpType.BOTH)) {
			listKp.add(zsdh.getKpn2());
		}

		if (flag3 != null && (type == flag3 || flag3 == EKpType.BOTH)) {
			listKp.add(zsdh.getKpn3());
		}
		if (flag4 != null && (type == flag4 || flag4 == EKpType.BOTH)) {
			listKp.add(zsdh.getKpn4());
		}
		return listKp;
	}

	/**
	 * <p>
	 * 产量的比较（合格与非合格的转换比较）
	 * </p>
	 * 
	 * @param chan1
	 *            原产量
	 * @param chan2
	 *            产量
	 * @return int:0-异常；1-非合格转合格；2- 合格品转为合格品； -1 - 合格转非合格（降级）；-2 - 非合格品转为非合格品；
	 */
	public static int compare(ChanType ycl, ChanType cl) {
		if (ycl == null || cl == null) {
			return 0;
		}
		if (ycl == ChanType.hg || ycl == ChanType.bl) {
			// 合格品订正为合格品
			if (cl == ChanType.hg || cl == ChanType.bl) {
				return 2;
			}
			// 合格品订正为不合格品
			else {
				return -1;
			}
		}
		else {
			// 不合格品订正为合格品
			if (cl == ChanType.hg || cl == ChanType.bl) {
				return 1;
			}
			// 不合格品订正为不合格品
			else {
				return -2;
			}
		}
	}

}
