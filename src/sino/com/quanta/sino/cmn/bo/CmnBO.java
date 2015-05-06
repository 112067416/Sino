package com.quanta.sino.cmn.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.api.IBaseDAO;
import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.util.NumberUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code103;
import com.quanta.sino.cmn.constants.Code104;
import com.quanta.sino.cmn.constants.Code105;
import com.quanta.sino.cmn.constants.Code200;
import com.quanta.sino.cmn.constants.CodeChdxZl;
import com.quanta.sino.cmn.constants.EYlType;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.SjDeng;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.cmn.vo.XpVo;
import com.quanta.sino.dh.constants.DhCh;
import com.quanta.sino.dh.constants.DhFzlDw;
import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 中日达生产公共业务实现
 * </p>
 * <p>
 * create: 2011-1-5 下午04:34:15
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CmnBO implements ICmnBO {

	private IBaseDAO dao;

	private JdbcTemplate jt;

	private ICodeBO codeBO;

	private ISeqBO seqBO;

	/**
	 * 原材卷板初始号码
	 */
	private static final String JBNO = "000001";

	@Override
	public XpVo fetchXpxx(String xpNo) {
		return null;
	}

	@Deprecated
	@Override
	public String getSpbh(Double xpho) {
		if (xpho == null) {
			return null;
		}
		List<CodeItem> items = codeBO.findItems(CmnConstants.CODE_SPBH);
		if (items == null || items.size() == 0) {
			return null;
		}
		String[] s = null;
		for (CodeItem item : items) {
			s = item.getValue().split("-");
			if (s.length != 2) {
				continue;
			}
			if (Double.parseDouble(s[0]) <= xpho
					&& Double.parseDouble(s[1]) > xpho) {
				return item.getKey();
			}
		}
		return null;
	}

	@Override
	public double getGssl(Double xpho) {
		if (xpho == null) {
			return 0d;
		}
		List<CodeItem> items = codeBO.findItems(CmnConstants.CODE_SPBH);
		if (items == null || items.size() == 0) {
			return 0d;
		}
		String[] s = null;
		for (CodeItem item : items) {
			s = item.getValue().split("-");
			if (s.length != 2) {
				continue;
			}
			if (Double.parseDouble(s[0]) <= xpho
					&& Double.parseDouble(s[1]) > xpho) {
				BigDecimal number = item.getNumber();
				if (number == null) {
					return 0d;
				}
				return number.doubleValue();
			}
		}
		return 0d;
	}

	@Override
	public String[] generateNo(String jbno, NoGenType type) {
		if (jbno == null || (jbno = jbno.trim()).isEmpty() || jbno.length() < 6
				|| jbno.length() > 9) {
			return null;
		}
		// ETL制品
		boolean etl = jbno.length() < 8;
		// 原始卷板
		boolean base = jbno.length() == 6 || jbno.length() == 8;
		// 基本号码
		String baseNo = base ? jbno : jbno.substring(0, jbno.length() - 1);
		String preNo = base ? (jbno + "0") : jbno;
		// 获取制品表中最大的号码
		String maxZpno = jt.queryForObject("select max(JBNO_) from SINO_ZPNGTP where JBNO_ like ?", String.class, preNo
				+ (etl ? "_" : "__"));
		// 生成的制品号
		String $jbno = null;
		// 如果没有制品号则在原卷后加（卷：1；板：01）
		if (maxZpno == null) {
			$jbno = preNo + (etl ? "1" : "01");
		}
		else {
			int num = -1;
			// 若该卷已经生成过则取出当前生成过的最大值
			try {
				num = Integer.parseInt(maxZpno.replace(preNo, ""));
			}
			catch (NumberFormatException e) {
			}
			num = num <= 0 ? 1 : num + 1;
			if (etl) {
				if (num == 10) {
					throw new CocoException(-2, "生成的号码已经超出范围");
				}
				else if (num > 10) {
					throw new CocoException(-2, "该卷板No无效，尾号小于中止数");
				}
				$jbno = preNo + num;
			}
			else {
				if (num == 100) {
					throw new CocoException(-2, "生成的号码已经超出范围");
				}
				else if (num > 100) {
					throw new CocoException(-2, "该卷板No无效，尾号小于中止数");
				}
				$jbno = preNo + (num < 10 ? "0" + num : num);
			}
		}
		// 若终了则只生成新的制品号
		if (NoGenType.end.equals(type)) {
			return new String[] { $jbno };
		}

		String $cutNo = null;
		if (etl) {
			// 获取制品表中最大的号码
			String maxYbno = jt.queryForObject("select max(JBNO_) from SINO_YCAITP where JBNO_ like ?", String.class, baseNo
					+ "_");
			if (maxYbno == null) {
				$cutNo = baseNo + 1;
			}
			else {
				int num = -1;
				try {
					num = Integer.parseInt(maxYbno.replace(baseNo, ""));
				}
				catch (NumberFormatException e) {
				}
				$cutNo = baseNo + (num <= 0 ? 1 : num + 1);
			}
		}
		else {
			// 获取制品表中最大的号码
			maxZpno = jt.queryForObject("select max(JBNO_) from SINO_ZPNGTP where JBNO_ like ?", String.class, baseNo
					+ "_");
			if (maxZpno == null) {
				$cutNo = baseNo + 1;
			}
			else {
				int num = -1;
				try {
					num = Integer.parseInt(maxZpno.replace(baseNo, ""));
				}
				catch (NumberFormatException e) {
				}
				$cutNo = baseNo + (num <= 0 ? 1 : num + 1);
			}
		}
		return new String[] { $jbno, $cutNo };
	}

	@Override
	public String generateYcaiTpNo() {
		String jbno = jt.queryForObject("select max(JBNO_) from SINO_YCAITP", String.class);
		if (jbno == null) {
			return JBNO;
		}
		long maxNo = 0;
		try {
			maxNo = Long.parseLong(jbno);
		}
		catch (NumberFormatException e) {
		}
		maxNo += 1;
		com.coco.sys.orm.Seq seq = seqBO.get(Seq.ybno.name);
		if (seq != null && seq.getNumber() > maxNo) {
			maxNo = seq.getNumber() + 1;
		}
		return String.format("%06d", maxNo);
	}

	@Override
	public String parentNo(String jbno) {
		if (jbno == null || (jbno = jbno.trim()).length() < 7) {
			return null;
		}
		int no;
		if (jbno.length() == 11) {
			return jbno.charAt(8) == '0' ? jbno.substring(0, 8)
					: jbno.substring(0, 9);
		}
		if (jbno.length() == 9) {
			no = jbno.charAt(8) - 48;
			if (no < 1 || no > 9) {
				return null;
			}
			if (no == 1) {
				return jbno.substring(0, 8);
			}
			return jbno.substring(0, 8) + (no - 1);
		}
		if (jbno.length() == 8) {
			return jbno.charAt(6) == '0' ? jbno.substring(0, 6)
					: jbno.substring(0, 7);
		}
		if (jbno.length() == 7) {
			no = jbno.charAt(6) - 48;
			if (no < 1 || no > 9) {
				return null;
			}
			if (no == 1) {
				return jbno.substring(0, 6);
			}
			return jbno.substring(0, 6) + (no - 1);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.quanta.sino.cmn.bo.api.ICmnBO#noTree(java.lang.String)
	 */
	@Override
	public Document treeNo(String jbno) {
		if (jbno == null || (jbno = jbno.trim()).length() < 6
				|| jbno.length() > 11) {
			return null;
		}
		String baseNo = null;
		boolean notNo = false;
		if (jbno.length() == 7) {
			baseNo = jbno.substring(0, 6);
			notNo = true;
		}
		else if (jbno.length() == 9) {
			baseNo = jbno.substring(0, 8);
			notNo = true;
		}
		else {
			baseNo = jbno;
		}

		String nolike = baseNo + "%";
		List<String> list = null;
		if (jbno.length() < 8) {
			list = jt.queryForList("(select JBNO_ from SINO_YCAITP where JBNO_ like ? union select JBNO_ from SINO_ZPNGTP where JBNO_ like ? order by JBNO_", String.class, nolike, nolike);
		}
		else {
			list = jt.queryForList("select JBNO_ from SINO_ZPNGTP where JBNO_ like ? order by JBNO_", String.class, nolike);
		}
		Set<String> set = new HashSet<String>();
		int len;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		Element item;
		for (String no : list) {
			len = no.length();
			if (len < jbno.length() || set.contains(no) || notNo
					&& len == jbno.length()
					&& no.charAt(len - 1) < jbno.charAt(len - 1)) {
				continue;
			}
			set.add(no);
			item = root.addElement("item");
			item.addAttribute("id", no);
			item.addAttribute("label", no);
			if (no.equals(jbno)) {
				item.addAttribute("pid", "0");
			}
			else {
				item.addAttribute("pid", parentNo(no));
			}
			item.addAttribute("leaf", no.length() == 11 ? "1" : "0");
		}
		return doc;
	}

	@Override
	public List<String> fetchYls(String ylno, EYlType type) {
		if (ylno == null || (ylno = ylno.trim()).length() == 0) {
			return null;
		}
		if (type == null) {
			return null;
		}
		// 将业连号组合串分开成多个业连号
		List<String> listYl = null;
		String[] yls = ylno.split(CmnConstants.YLNO_SPLIT_PART);
		if (yls != null && yls.length > 0) {
			listYl = new ArrayList<String>();
			for (String yl : yls) {
				if (yl == null || (yl = yl.trim()).isEmpty()) {
					continue;
				}
				String[] syl = yl.split(CmnConstants.YLNO_SPLIT_BREAK);
				String pa = syl[0];
				String pb = syl[1];
				if (pa != null
						&& (type.key.equalsIgnoreCase(pa) || EYlType.BOTH.key.equalsIgnoreCase(pa))) {
					listYl.add(pb);
				}
			}
		}
		return listYl;
	}

	@Override
	public void checkDeng(String deng) {
		if (deng == null || deng.isEmpty()) {
			return;
		}
		// 等级代码长度为1时，异常
		if (deng.length() == 1) {
			throw new CocoException(-12, "等级异常");
		}
		// 等级代码长度为2时，只校验等级第二、三位
		if (deng.length() == 2) {
			String d2 = deng.substring(0, 1);
			if (!d2.isEmpty() && Code104.get(d2) == null) {
				throw new CocoException(-12, "表面现状等级异常");
			}
			String d3 = deng.substring(1, 2);
			if (!d3.isEmpty() && Code105.get(d3) == null) {
				throw new CocoException(-12, "性状等级异常");
			}
		}
		// 等级代码长度为3时，校验等级第一、二、三位
		if (deng.length() == 3) {
			String d1 = deng.substring(0, 1);
			if (!d1.isEmpty() && Code103.get(d1) == null) {
				throw new CocoException(-12, "等级第一位异常");
			}
			String d2 = deng.substring(1, 2);
			if (!d2.isEmpty() && Code104.get(d2) == null) {
				throw new CocoException(-12, "等级第二位异常");
			}
			String d3 = deng.substring(2, 3);
			if (!d3.isEmpty() && Code105.get(d3) == null) {
				throw new CocoException(-12, "等级第三位异常");
			}
		}
	}

	@Override
	public void checkChan(String chan, String deng, String fpdj) {
		// 产量
		ChanType cl = ChanType.get(chan);
		if (cl == null) {
			throw new CocoException(-11, "产量代码异常：产量当且仅当1、3、4、7、9、S时有效");
		}
		// 等级校验
		checkDeng(deng);
		// 分配等级
		Code103 code103 = null;
		// 表面性状
		Code104 code104 = null;
		// 性状
		Code105 code105 = null;
		// 当产量为1,合格
		if (cl == ChanType.hg || cl == ChanType.bl) {
			// 等级合法检查
			if (deng == null || (deng = deng.trim()).length() != 3) {
				throw new CocoException(-12, "产量与等级匹配异常：等级必须非空且长度为三位");
			}
			// 分配等级合法检查
			if (fpdj != null && fpdj.length() == 3) {
				// throw new CocoException(-1, "产量与等级匹配异常：分配等级必须非空且长度为三位");
				// 等级第一位验证
				code103 = Code103.get(deng.substring(0, 1));
				if (code103 == null
						|| code103 != Code103.get(fpdj.substring(0, 1))) {
					throw new CocoException(-12, "产量与等级匹配异常：等级与指示分配等级第一位不相符！");
				}
				// 等级第二位验证
				code104 = Code104.get(fpdj.substring(1, 2));
				if (code104 == null || !code104.contains(deng.substring(1, 2))) {
					throw new CocoException(-12, "产量与等级匹配异常：等级与指示分配等级第二位不相符！");
				}
				// 等级第三位验证
				code105 = Code105.get(fpdj.substring(2, 3));
				if (code105 == null || !code105.contains(deng.substring(2, 3))) {
					throw new CocoException(-12, "产量与等级匹配异常：等级与指示分配等级第三位不相符！");
				}
			}
		}
		// 当产量为3,不合格
		if (cl == ChanType.bhg) {
			// 等级合法检查
			if (deng == null || (deng = deng.trim()).length() < 2) {
				throw new CocoException(-12, "产量与等级匹配异常：等级必须非空且长度至少为二位");
			}
			// 等级第一位验证
			code103 = Code103.get(deng.substring(0, 1));
			if (code103 == null || code103 != Code103.K) {
				throw new CocoException(-12, "产量与等级匹配异常：等级第一位错误！");
			}
			// 等级第二位验证
			if (!Code104.C.contains(deng.substring(1, 2))) {
				throw new CocoException(-12, "产量与等级匹配异常：等级第二位错误！");
			}
		}
		// 当产量为4,发生
		if (cl == ChanType.fs) {
			// 等级合法检查
			if (deng == null || (deng = deng.trim()).length() < 1) {
				throw new CocoException(-12, "产量与等级匹配异常");
			}
			// 等级第二位验证
			if (!Code104.D.contains(deng.substring(0, 1))) {
				throw new CocoException(-12, "产量与等级匹配异常");
			}
		}
		// 当产量为7、S
		if (cl == ChanType.ns || cl == ChanType.fc) {
			// 等级合法检查
			if (deng != null) {
				throw new CocoException(-12, "等级必须为空");
			}
		}
	}

	@Override
	public Result getCheck(String chan, String deng, String fpdj) {
		try {
			checkChan(chan, deng, fpdj);
		}
		catch (CocoException e) {
			int cdoe = e.getCode() == -11 ? -19 : -18;
			return new Result(cdoe, e.getMessage());
		}
		return new Result(1, "OK");
	}

	@Override
	public Integer generatePackageNo(String dhno) {
		String sql = "select max(CKNO_) from SINO_ZPNGTP where DHNO_=?";
		Integer ckno = jt.queryForObject(sql, Integer.class, dhno);
		if (ckno == null) {
			return 1;
		}
		return ckno + 1;
	}

	@Override
	public String getSjpzdj(ChanType cl, String dj) {
		if (cl == null) {
			return null;
		}
		// 产量=1或9时，设定1
		if (ChanType.hg.equals(cl) || ChanType.bl.equals(cl)) {
			return SjDeng.SJD1.key;
		}
		// 产量=2时
		if (ChanType.bhg.equals(cl)) {
			if (dj == null || (dj = dj.trim()).isEmpty() || dj.length() != 3) {
				return null;
			}
			// 等级代码的第一、二、三位进行验证
			char[] djs = dj.toCharArray();
			if (Code103.get(String.valueOf(djs[0])) == null
					|| Code104.get(String.valueOf(djs[1])) == null
					|| Code105.get(String.valueOf(djs[2])) == null) {
				return null;
			}
			// 等级=KBB以上，设定2
			if (Code104.get(String.valueOf(djs[1])).contains(djs[1])
					&& Code105.get(String.valueOf(djs[1])).contains(djs[2])) {
				return SjDeng.SJD2.key;
			}
			// 等级=KBB未满，设定3
			else {
				return SjDeng.SJD3.key;
			}
		}
		// 产量=4时，设定4
		if (ChanType.fs.equals(cl)) {
			return SjDeng.SJD4.key;
		}
		// 产量=7或S时，设定5
		if (ChanType.ns.equals(cl) || ChanType.fc.equals(cl)) {
			return SjDeng.SJD5.key;
		}
		return null;
	}

	@Override
	public Double fetchZsbz(ZlnrCode zlnr) {
		if (zlnr == null) {
			return 0.0;
		}
		CodeItem codeItem = null;
		// 取码表的实际重量补正系数0.998
		if (ZlnrCode.nr1 == zlnr || ZlnrCode.nr2 == zlnr
				|| ZlnrCode.nr3 == zlnr) {
			codeItem = codeBO.getCodeItem(CmnConstants.CODE_200, Code200.BZXS7.key);
		}
		// 取码表的计算重量补正系数0.996
		else {
			codeItem = codeBO.getCodeItem(CmnConstants.CODE_200, Code200.BZXS1.key);
		}
		if (codeItem != null) {
			return NumberUtils.format(Double.parseDouble(codeItem.getValue()) * 0.001);
		}
		return null;
	}

	@Override
	public String getDhfz(String fudw, String fuzm, String fufm, String chdx) {
		// String f = "";
		// String z = "";
		// if (chdx != null && !chdx.isEmpty() && DhCh.get(chdx) != null) {
		// f = chdx.substring(1).equals(DhCh.F) ? chdx.substring(0, 1) : "";
		// z = chdx.substring(1).equals(DhCh.Z) ? chdx.substring(0, 1) : "";
		// }
		String f = "";
		String z = "";
		String mark = null;
		if (chdx != null && !chdx.isEmpty() && DhCh.get(chdx) != null) {
			mark = chdx.substring(0, 1);
			if (CodeChdxZl.paniya.key.equalsIgnoreCase(mark)) {
				f = "";
				z = mark;
			}
			else {
				if (DhCh.B.equals(chdx.substring(1))) {
					if (fuzm.compareTo(fufm) > 0) {
						f = mark;
						z = "";
					}
					else {
						f = "";
						z = mark;
					}
				}
				else {
					if (fuzm.compareTo(fufm) > 0) {
						f = "";
						z = mark;
					}
					else {
						f = mark;
						z = "";
					}
				}
			}
		}
		fuzm = fuzm.replaceAll("^0+", "");
		fufm = fufm.replaceAll("^0+", "");
		if (DhFzlDw.FZLDW_GM.stat.equals(fudw)) {
			fuzm = NumberUtils.formatNumber(Double.parseDouble(fuzm) * 0.1, 1)
					+ z;
			fufm = NumberUtils.formatNumber(Double.parseDouble(fufm) * 0.1, 1)
					+ f;
			return fuzm + "/" + fufm;

		}
		return fuzm + z + "/" + fufm + f;
	}

	@Override
	public Double parseFzl2Gm(String dw, String fzl) {
		if (dw == null || dw.isEmpty() || fzl == null || fzl.isEmpty()) {
			return 0.0;
		}
		DhFzlDw edw = DhFzlDw.get(dw);
		if (edw != null) {
			if (edw == DhFzlDw.FZLDW_GM) {
				fzl = fzl.replaceAll("^0+", "");

				return NumberUtils.format(Double.parseDouble(fzl) * 0.1, 2);
			}
			if (edw == DhFzlDw.FZLDW_WB) {
				CodeItem item = codeBO.getCodeItem(CmnConstants.CODE_024, dw
						+ fzl);
				if (item != null && item.getNumber() != null) {
					return NumberUtils.format(item.getNumber().doubleValue(), 2);
				}
			}
		}
		return 0.0;
	}

	@Override
	public String getFugm(String fudw, String fuzm, String fufm, String chdx) {
		String f = "";
		String z = "";
		String mark = null;
		if (chdx != null && !chdx.isEmpty() && DhCh.get(chdx) != null) {
			mark = chdx.substring(0, 1);
			if (CodeChdxZl.paniya.key.equalsIgnoreCase(mark)) {
				f = "";
				z = mark;
			}
			else {
				if (DhCh.B.equals(chdx.substring(1))) {
					if (fuzm.compareTo(fufm) > 0) {
						f = mark;
						z = "";
					}
					else {
						f = "";
						z = mark;
					}
				}
				else {
					if (fuzm.compareTo(fufm) > 0) {
						f = "";
						z = mark;
					}
					else {
						f = mark;
						z = "";
					}
				}
			}
		}
		// 单位是GM
		if (DhFzlDw.FZLDW_GM.stat.equals(fudw)) {
			fuzm = fuzm.replaceAll("^0+", "");
			fufm = fufm.replaceAll("^0+", "");
			fuzm = NumberUtils.formatNumberComma(Double.parseDouble(fuzm) * 0.1, 2)
					+ z;
			fufm = NumberUtils.formatNumberComma(Double.parseDouble(fufm) * 0.1, 2)
					+ f;
			return fuzm + "/" + fufm;
		}
		// 单位是WB
		CodeItem code = null;
		code = codeBO.getCodeItem(CmnConstants.CODE_024, fudw + fuzm);
		if (code != null) {
			fuzm = NumberUtils.formatNumberComma(code.getNumber().doubleValue(), 2)
					+ z;
		}
		code = codeBO.getCodeItem(CmnConstants.CODE_024, fudw + fufm);
		if (code != null) {
			fufm = NumberUtils.formatNumberComma(code.getNumber().doubleValue(), 2)
					+ f;
		}
		return fuzm + "/" + fufm;

	}

	@Override
	public int getZzzl(Integer zzjh, String njno, Double xpkn) {
		// 卷厚
		int jh = zzjh == null ? 0 : zzjh.intValue();
		// 内径值
		Double nj = 0.0;
		// 取内径码表
		CodeItem codeItem = codeBO.getCodeItem(CmnConstants.CODE_020, njno);
		if (codeItem != null) {
			nj = Double.valueOf(codeItem.getValue());
		}
		// 钢卷截面面积
		Double cutR = SinoUtils.calculate(nj, jh);
		// 钢卷重量(钢卷截面面积*钢卷宽*7.85)
		Double gjzl = NumberUtils.format(SinoUtils.calculate(cutR, xpkn), 3);
		return NumberUtils.formatDouToInt(gjzl, 0, BigDecimal.ROUND_HALF_UP);
	}

	public IBaseDAO getDao() {
		return dao;
	}

	public void setDao(IBaseDAO dao) {
		this.dao = dao;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

	public ISeqBO getSeqBO() {
		return seqBO;
	}

	public void setSeqBO(ISeqBO seqBO) {
		this.seqBO = seqBO;
	}

}
