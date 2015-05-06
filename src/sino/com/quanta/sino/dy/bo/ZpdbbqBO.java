package com.quanta.sino.dy.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.ICodeBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.dbgl.bo.api.IDbglBO;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dy.bo.api.IZpdbbqBO;
import com.quanta.sino.dy.vo.ZpdbbqVO;
import com.quanta.sino.orm.DbglTp;

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
public class ZpdbbqBO implements IZpdbbqBO {

	/**
	 * 端板业务接口
	 */
	private IDbglBO dbBo;

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

	@Override
	public ZpdbbqVO get(String jbno) {
		// 查询制品在库
		DbglTp dbTp = dbBo.get(jbno);
		if (dbTp == null) {
			throw new CocoException(-1, "无法找到对应的端板信息，制品号：" + jbno);
		}
		ZpdbbqVO zpdbVO = new ZpdbbqVO();
		zpdbVO.setDate(SDF.format(dbTp.getCrea()));
		zpdbVO.setZpno(jbno);
		zpdbVO.setZl(dbTp.getZpzl().toString());
		zpdbVO.setKbao(dbTp.getKbao());
		// 更新F堆场为G堆场
		dbTp.setDuic(DC.G.name);
		dbTp.setKw(DC.G.name);
		dbTp.setKbsj(new Date());
		return zpdbVO;
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

	public IDbglBO getDbBo() {
		return dbBo;
	}

	public void setDbBo(IDbglBO dbBo) {
		this.dbBo = dbBo;
	}

}
