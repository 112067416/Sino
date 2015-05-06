/**
 * 
 */
package com.quanta.sino.yd.bo;

import java.util.Calendar;
import java.util.Date;

import com.coco.core.bean.Result;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.yd.bo.api.IYdBO;
import com.quanta.sino.yd.vo.GxVO;
import com.quanta.sino.yd.vo.YdVO;

/**
 * <p>
 * 硬度维护业务实现
 * </p>
 * <p>
 * create: 2011-4-14 下午10:41:17
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YdBO implements IYdBO {

	/**
	 * 制品业务接口
	 */
	private IZpBO zpBo;

	/**
	 * 原生SQL查询接口
	 */
	private ISqlDAO sqlDAO;

	/**
	 * 原材卷板管理业务接口
	 */
	private IYcaitpBO ycaitpBO;

	@Override
	public void query(QEntity<YdVO> page) {
		sqlDAO.query(page);
	}

	@Override
	public String checkYd(GxVO vo) {
		if (vo == null) {
			return new Result(-1, "获取对象为空").toString();
		}
		// 卷板号
		String jbno = vo.getJbno();
		if (jbno == null || (jbno = jbno.trim()).isEmpty()) {
			return new Result(-1, "卷板号 为空").toString();
		}
		// 硬度值
		int ying = vo.getYing() == null ? 0 : vo.getYing().intValue();
		if (ying <= 0) {
			return new Result(-1, "硬度值 不能为空").toString();
		}
		// 检定员
		String jdyn = vo.getJdyn();
		if (jdyn == null || (jdyn = jdyn.trim()).isEmpty()) {
			return new Result(-1, "检定员 不能为空").toString();
		}
		int ymax = 0, ymin = 0;
		if (vo.getYmax() != null && !vo.getYmax().isEmpty()) {
			try {
				ymax = Integer.parseInt(vo.getYmax());
			}
			catch (Exception e) {
			}
			if (ymax > 0 && ying / 10d > ymax) {
				return new Result(2, "警告：硬度值已超出硬度上限值").toString();
			}
		}
		if (vo.getYmin() != null && !vo.getYmin().isEmpty()) {
			try {
				ymin = Integer.parseInt(vo.getYmin());
			}
			catch (Exception e) {
			}
			if (ymin > 0 && ying / 10d < ymin) {
				return new Result(2, "警告：硬度值已低于硬度下限值").toString();
			}
		}
		YcaiTp ycaiTp = ycaitpBO.get(jbno);
		Date ydsj = ycaiTp != null ? ycaiTp.getYdsj() : null;
		if (ydsj != null
				&& DateUtils.add(ydsj, Calendar.DAY_OF_MONTH, 1).before(new Date())) {
			return new Result(-1, "该卷的硬度录入时间为"
					+ DateUtils.format(ydsj, "yyyy-MM-dd HH:mm")
					+ "已超过修改时限,不能修改该卷硬度").toString();
		}
		return Result.SUCCESS;
	}

	@Override
	public String updateYd(GxVO vo) {
		String jbno = vo.getJbno();
		String $jbno = null;
		if (jbno == null || ($jbno = jbno.trim()).length() < 6) {
			return new Result(-1, "现品No." + jbno + "有误").toString();
		}
		// 取母卷号
		$jbno = $jbno.substring(0, 6);
		Date date = new Date();
		zpBo.updateYd($jbno + "%", vo.getYing(), vo.getYing(), date);
		ycaitpBO.updateYd($jbno + "%", vo.getYing(), vo.getJdyn(), date);
		return Result.SUCCESS;
	}

	@Override
	public String updateYd(String jbno, Integer ying) {
		String $jbno = null;
		if (jbno == null || ($jbno = jbno.trim()).length() < 6) {
			return new Result(-1, "现品No." + jbno + "有误").toString();
		}
		// 取母卷号
		$jbno = $jbno.substring(0, 6);
		Date date = new Date();
		zpBo.updateYd($jbno + "%", ying);
		ycaitpBO.updateYd($jbno + "%", ying, null, date);
		return Result.SUCCESS;
	}

	@Override
	public String getYdgxjlForJS(String jbno) {
		String $jbno = null;
		if (jbno == null || ($jbno = jbno.trim()).length() < 6) {
			return new Result(-1, "现品No." + jbno + "有误").toString();
		}
		// 取母卷号
		$jbno = $jbno.substring(0, 6);
		YcaiTp ycaiTp = ycaitpBO.get($jbno);
		// 取制品
		if (ycaiTp == null) {
			return new Result(-1, "现品No." + jbno + "不存在").toString();
		}
		GxVO gxVO = new GxVO();
		gxVO.setYing(ycaiTp.getYing());
		gxVO.setJdyn(ycaiTp.getJdyn());
		gxVO.setJbno($jbno);
		return new Result(gxVO).toJsObject();
	}

	@Override
	public Integer getYing(String jbno) {
		String $jbno = null;
		if (jbno == null || ($jbno = jbno.trim()).length() < 6) {
			return null;
		}
		// 取母卷号
		$jbno = $jbno.substring(0, 6);
		YcaiTp ycaiTp = ycaitpBO.get($jbno);
		// 取制品
		if (ycaiTp == null) {
			return null;
		}
		return ycaiTp.getYing();
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public ISqlDAO getSqlDAO() {
		return sqlDAO;
	}

	public void setSqlDAO(ISqlDAO sqlDAO) {
		this.sqlDAO = sqlDAO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}
}
