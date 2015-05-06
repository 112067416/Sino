package com.quanta.sino.dy.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.dy.bo.api.IJbqxbBO;
import com.quanta.sino.dy.vo.JbqxbDataVO;
import com.quanta.sino.dy.vo.JbqxbVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 卷板缺陷表打印数据业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午09:53:05
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class JbqxbBO implements IJbqxbBO {

	/**
	 * 原材业务接口
	 */
	private IYcaitpBO ycaiBo;

	/**
	 * 原材业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 指示业务接口
	 */
	private IZsBO zsBo;

	/**
	 * <p>
	 * 根据原材No列表获取打印数据
	 * </p>
	 * 
	 * @param nos
	 * @return List<JbqxbVO>
	 */
	private List<JbqxbVO> findByJbnos(List<String> nos) {
		List<JbqxbVO> datas = new ArrayList<JbqxbVO>();
		YcaiTp ycai = null;
		Map<String, ZsdhTp> zsdhs = new HashMap<String, ZsdhTp>();
		ZsdhTp zsdh = null;
		for (String no : nos) {
			ycai = ycaiBo.get(no);
			if (ycai == null) {
				continue;
			}
			zsdh = zsdhs.get(ycai.getZsno());
			if (zsdh == null) {
				zsdh = zsBo.getZsdhTp(ycai.getZsno());
				if (zsdh == null) {
					continue;
				}
				zsdhs.put(ycai.getZsno(), zsdh);
			}
			datas.add(fillYcai(ycai, zsdh));
		}
		return datas;
	}

	/**
	 * <p>
	 * 填充原材信息
	 * </p>
	 * 
	 * @param ycai
	 * @param zsdh
	 * @return JbqxbVO
	 */
	private JbqxbVO fillYcai(YcaiTp ycai, ZsdhTp zsdh) {
		JbqxbVO data = new JbqxbVO();
		data.setZsno(ycai.getZsno());
		data.setJbno(ycai.getZzsd() + "-" + ycai.getJbno());
		data.setYh(ycai.getAbbr());
		data.setBh(NumberUtils.formatNumber(ycai.getXpho(), 3));
		data.setBk(NumberUtils.formatNumber(ycai.getXpkn(), 2));
		data.setXfzl(zsdh.getFugm());
		data.setJjg(ycai.getFace());
		data.setJbzl(NumberUtils.formatNumberComma(ycai.getZpzl(), null));
		data.setJbc(NumberUtils.formatNumberComma(ycai.getJbcn(), null));
		return data;
	}

	/**
	 * <p>
	 * 根据制品No列表获取打印数据
	 * </p>
	 * 
	 * @param nos
	 * @return List<JbqxbVO>
	 */
	public List<JbqxbVO> findByZpnos(List<String> nos) {
		List<JbqxbVO> datas = new ArrayList<JbqxbVO>();
		ZpngTp zpng = null;
		Map<String, ZsdhTp> zsdhs = new HashMap<String, ZsdhTp>();
		ZsdhTp zsdh = null;
		for (String no : nos) {
			zpng = zpBo.getZp(no);
			if (zpng == null) {
				continue;
			}
			zsdh = zsdhs.get(zpng.getZsno());
			if (zsdh == null) {
				zsdh = zsBo.getZsdhTp(zpng.getZsno());
				if (zsdh == null) {
					continue;
				}
				zsdhs.put(zpng.getZsno(), zsdh);
			}
			datas.add(fillZp(zpng, zsdh));
		}
		return datas;
	}

	/**
	 * <p>
	 * 填充制品信息
	 * </p>
	 * 
	 * @param zpng
	 * @param zsdh
	 * @return JbqxbVO
	 */
	public JbqxbVO fillZp(ZpngTp zpng, ZsdhTp zsdh) {
		JbqxbVO data = new JbqxbVO();
		data.setZsno(zpng.getZsno());
		data.setJbno(zpng.getJbno());
		data.setYh(zpng.getUser());
		data.setBh(NumberUtils.formatNumber(zpng.getHouu(), 3));
		data.setBk(NumberUtils.formatNumber(zpng.getKuan(), 2));
		data.setXfzl(StringUtils.concat(NumberUtils.format(zsdh.getFuzm(), 1), "/", NumberUtils.format(zsdh.getFufm(), 1)));
		data.setJjg(zpng.getFace());
		data.setJbzl(NumberUtils.formatNumberComma(zpng.getSjzl(), null));
		data.setJbc(NumberUtils.formatNumberComma(zpng.getJbcn(), null));
		return data;
	}

	/**
	 * <p>
	 * 根据指示No列表获取打印数据
	 * </p>
	 * 
	 * @param nos
	 * @return List<JbqxbVO>
	 */
	public List<JbqxbVO> findByZsnos(List<String> nos) {
		List<JbqxbVO> datas = new ArrayList<JbqxbVO>();
		ZsdhTp zsdh = null;
		YcaiQE ycaiQe = new YcaiQE();
		ZpQE zpQe = new ZpQE();
		for (String no : nos) {
			zsdh = zsBo.getZsdhTp(no);
			if (zsdh == null) {
				continue;
			}
			// 若指示现品状况为素板则查原材，否知查制品
			if (EXpzk.SC.key.equals(zsdh.getYczk())) {
				ycaiQe.setZsno(no);
				ycaiBo.query(ycaiQe);
				for (YcaiTp ycai : ycaiQe.getDatas()) {
					datas.add(fillYcai(ycai, zsdh));
				}
			}
			else {
				zpQe.setZsno(no);
				zpBo.query(zpQe);
				for (ZpngTp zpng : zpQe.getDatas()) {
					datas.add(fillZp(zpng, zsdh));
				}
			}
		}
		return datas;
	}

	@Override
	public String getForJs(JbqxbDataVO vo) {
		// 判断是否是根据卷板No.获取数据，"1"为根据卷板No.，否则为根据指示No.
		boolean isJb = JbqxbDataVO.TYPE_JB.equals(vo.getType());
		// 判断是否是原材，位数为6或7的为原材，否则为制品
		boolean isYcai = false;
		List<String> nos = new ArrayList<String>();
		// 若指定是号码列表nos，则以号码列表为优先条件
		if (vo.getNos() != null && !vo.getNos().isEmpty()) {
			String[] noArr = vo.getNos().split(",");
			// 设置是否原材标志
			boolean flag = false;
			// 只取6-11位的数据
			for (String no : noArr) {
				if ((no = no.trim()).length() >= 6 && no.length() <= 11) {
					// 若没有设置是否原材标志，则先设置，若设置了原材标志，则卷板类型要一直
					if (!flag) {
						// 判断是否是原材，位数为6或7的为原材，否则为制品
						isYcai = no.length() == 6 || no.length() == 7;
					}
					else {
						// 若卷板类型不一致，则报错
						if (isYcai && !(no.length() == 6 || no.length() == 7)) {
							throw new CocoException(-1, "号码列表中不都是原材");
						}
						if (!isYcai && (no.length() == 6 || no.length() == 7)) {
							throw new CocoException(-1, "号码列表中不都是制品");
						}
					}
					nos.add(no);
				}
			}
		}
		// 若没有指定号码列表，若分析起止号码
		if (nos.isEmpty()) {
			String noBegin = vo.getNoBegin();
			if (noBegin == null || noBegin.isEmpty()) {
				throw new CocoException(-1, "必须指定一个卷板No");
			}
			// 判断是否是原材，位数为6或7的为原材，否则为制品
			isYcai = noBegin.length() == 6 || noBegin.length() == 7;
			String noEnd = vo.getNoEnd();
			if (noEnd != null && !noEnd.isEmpty()) {
				if (noEnd.length() != noBegin.length()) {
					throw new CocoException(-1, "起始号码和终止号码位数必须一致");
				}
				int begin = NumberUtils.parseInt(noBegin);
				int end = NumberUtils.parseInt(noEnd);
				if (begin == 0) {
					throw new CocoException(-1, "起始号码无效");
				}
				if (end == 0) {
					throw new CocoException(-1, "终止号码无效");
				}
				nos.add(noBegin);
				String no;
				int len = noBegin.length();
				for (int i = begin + 1; i <= end; i++) {
					no = String.valueOf(i);
					no = noBegin.substring(0, len - no.length()) + no;
					nos.add(no);
				}
			}
			else {
				nos.add(noBegin);
			}
		}
		if (isJb) {
			if (isYcai) {
				vo.setDatas(findByJbnos(nos));
			}
			else {
				vo.setDatas(findByZpnos(nos));
			}
		}
		else {
			vo.setDatas(findByZsnos(nos));
		}
		return new Result(vo).toJsObject();
	}

	/**
	 * @return the zsBo
	 */
	public IZsBO getZsBo() {
		return zsBo;
	}

	/**
	 * @param zsBo
	 *            the zsBo to set
	 */
	public void setZsBo(IZsBO zsBo) {
		this.zsBo = zsBo;
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

}
