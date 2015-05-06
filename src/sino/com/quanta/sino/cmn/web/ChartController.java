package com.quanta.sino.cmn.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coco.core.util.ChartUtils;
import com.quanta.sino.cmn.constants.Chart;
import com.quanta.sino.yygl.bo.api.IKhyfBO;
import com.quanta.sino.yygl.vo.CondVO;

/**
 * <p>
 * 图形报表控制器
 * </p>
 * <p>
 * create: 2011-7-7 下午05:15:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/chart")
public class ChartController {

	/**
	 * 客户运费业务接口
	 */
	@Autowired
	private IKhyfBO khyfBO;

	/**
	 * <p>
	 * 生成用途报表
	 * </p>
	 * 
	 * @param yearS
	 * @param yearE
	 * @param monthS
	 * @param monthE
	 * @param chart
	 * @param response
	 */
	@RequestMapping("/createCondChart")
	public void createCondChart(Integer yearS, Integer yearE, Integer monthS,
			Integer monthE, String chart, HttpServletResponse response) {
		CondVO vo = khyfBO.getCond(yearS, yearE, monthS, monthE);

		chart = chart == null || chart.isEmpty() ? Chart.pie.key : chart;
		DefaultPieDataset pieDataset = null;
		DefaultCategoryDataset barDataset = null;
		if (Chart.pie.key.equals(chart)) {
			pieDataset = new DefaultPieDataset();
			pieDataset.setValue(CondVO.Cdnm.yl.name, vo.getYlzl() != null ? vo.getYlzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.zg.name, vo.getZgzl() != null ? vo.getZgzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.sp.name, vo.getSpzl() != null ? vo.getSpzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.dc.name, vo.getDczl() != null ? vo.getDczl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.pw.name, vo.getPwzl() != null ? vo.getPwzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.dzbj.name, vo.getDzbjzl() != null ? vo.getDzbjzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.hg.name, vo.getHgzl() != null ? vo.getHgzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.hgg.name, vo.getHggzl() != null ? vo.getHggzl()
					: 0d);
			pieDataset.setValue(CondVO.Cdnm.nf.name, vo.getNfzl() != null ? vo.getNfzl()
					: 0d);
		}
		else if (Chart.bar.key.equals(chart)) {
			barDataset = new DefaultCategoryDataset();
			barDataset.addValue(vo.getYlzl() != null ? vo.getYlzl() : 0d, CondVO.Cdnm.yl.name, CondVO.Cdnm.yl.name);
			barDataset.addValue(vo.getZgzl() != null ? vo.getZgzl() : 0d, CondVO.Cdnm.zg.name, CondVO.Cdnm.zg.name);
			barDataset.addValue(vo.getSpzl() != null ? vo.getSpzl() : 0d, CondVO.Cdnm.sp.name, CondVO.Cdnm.sp.name);
			barDataset.addValue(vo.getDczl() != null ? vo.getDczl() : 0d, CondVO.Cdnm.dc.name, CondVO.Cdnm.dc.name);
			barDataset.addValue(vo.getPwzl() != null ? vo.getPwzl() : 0d, CondVO.Cdnm.pw.name, CondVO.Cdnm.pw.name);
			barDataset.addValue(vo.getDzbjzl() != null ? vo.getDzbjzl() : 0d, CondVO.Cdnm.dzbj.name, CondVO.Cdnm.dzbj.name);
			barDataset.addValue(vo.getHgzl() != null ? vo.getHgzl() : 0d, CondVO.Cdnm.hg.name, CondVO.Cdnm.hg.name);
			barDataset.addValue(vo.getHggzl() != null ? vo.getHggzl() : 0d, CondVO.Cdnm.hgg.name, CondVO.Cdnm.hgg.name);
			barDataset.addValue(vo.getNfzl() != null ? vo.getNfzl() : 0d, CondVO.Cdnm.nf.name, CondVO.Cdnm.nf.name);
		}
		response.setContentType("application/octet-stream");
		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("CondChart", "UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
		}
		try {
			if (Chart.pie.key.equals(chart)) {
				response.getOutputStream().write(ChartUtils.createPie("用途销售量分析", pieDataset));
			}
			else if (Chart.bar.key.equals(chart)) {
				response.getOutputStream().write(ChartUtils.createBar("用途销售量分析", "加工用途", "出货量", barDataset));
			}
		}
		catch (IOException e) {
		}
	}
}
