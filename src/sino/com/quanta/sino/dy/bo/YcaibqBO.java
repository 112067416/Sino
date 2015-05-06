package com.quanta.sino.dy.bo;

import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.dy.bo.api.IYcaibqBO;
import com.quanta.sino.dy.vo.YcaibqVO;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

/**
 * <p>
 * 原材打印数据获取业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午09:56:17
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class YcaibqBO implements IYcaibqBO {

	/**
	 * 原材业务接口
	 */
	private IYcaitpBO bo;

	/**
	 * 代码业务接口
	 */
	private ICodeBO codeBo;

	public IYcaitpBO getBo() {
		return bo;
	}

	public void setBo(IYcaitpBO bo) {
		this.bo = bo;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	@Override
	public YcaibqVO get(String jbno) {
		YcaiTp tp = bo.get(jbno);
		if (tp == null) {
			return null;
		}
		YcaibqVO vo = new YcaibqVO();
		// 等级
		vo.setDeng(tp.getDeng());
		// 制造商代码
		// 卷板No
		vo.setJbno(tp.getZzsd() + "-" + tp.getJbno());
		CodeItem item = codeBo.getCodeItem(CmnConstants.CODE_020, tp.getNjno());
		// 卷板内径
		vo.setNjno(item == null ? "" : item.getValue());
		// 制造商规格略称
		vo.setYblc(tp.getYblc());
		// 现品尺寸.厚
		vo.setXpho(tp.getXpho());
		// 现品尺寸.宽
		vo.setXpkn(tp.getXpkn());
		// 制造商生产年月
		vo.setZzny(tp.getZzny());
		// 合同NO
		vo.setYbno(tp.getYbno());
		//
		String zzsj = tp.getZzsj();
		// 制造商卷板NO。去掉制造商卷板No.前缀
		vo.setZzsj(zzsj.substring(4));
		// 卷板重量
		vo.setZpzl(tp.getZpzl());
		// 表面精加工符号
		vo.setFace(tp.getFace());
		// 船名
		vo.setShip(tp.getShip());
		// 合同行号
		vo.setLine(tp.getLine());
		return vo;
	}

	@Override
	public String findByShip(String ship) {
		List<YcaiTp> vos = bo.getByShip(ship);
		if (vos.isEmpty()) {
			throw new CocoException(-1, "未找到相应船名的数据！");
		}
		StringBuilder sb = new StringBuilder();
		for (YcaiTp vo : vos) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("\"").append(vo.getJbno()).append("\"");
		}
		return "[" + sb + "]";
	}

}
