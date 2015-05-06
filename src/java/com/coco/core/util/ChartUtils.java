package com.coco.core.util;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * <p>
 * 图形报表工具
 * </p>
 * <p>
 * create: 2011-7-7 下午05:06:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChartUtils {

	/**
	 * <p>
	 * 创建3D图形报表
	 * </p>
	 * 
	 * @param title
	 * @param dataset
	 * @return byte[]
	 */
	public static byte[] createPie3D(String title, DefaultPieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, true);
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.BOLD, 20));
		// 设置饼图图例字体的大小
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 14));
		// 获得饼图plot的对象
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 设置饼图旁边的中文字体
		plot.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		ByteArrayOutputStream bos = null;
		byte[] bytes = null;
		try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos, 1.0f, chart, 730, 440, null);
			bytes = bos.toByteArray();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				bos.close();
			}
			catch (IOException e) {

				e.printStackTrace();
			}
		}
		return bytes;
	}

	public static byte[] createPie(String title, DefaultPieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, true);
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.BOLD, 20));
		// 设置饼图图例字体的大小
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 14));
		// 获得饼图plot的对象
		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置饼图旁边的中文字体
		plot.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				("{0}={1}({2})"), NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		ByteArrayOutputStream bos = null;
		byte[] bytes = null;
		try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos, 1.0f, chart, 730, 440, null);
			bytes = bos.toByteArray();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				bos.close();
			}
			catch (IOException e) {

				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * <p>
	 * 创建柱状图报表
	 * </p>
	 * 
	 * @param title
	 * @param xTitle
	 * @param yTitle
	 * @param dataset
	 * @return byte[]
	 */
	public static byte[] createBar(String title, String xTitle, String yTitle,
			DefaultCategoryDataset dataset) {
		/*
		 * createBarChart3D创建柱图，第一个参数是图标的标题，第二个是X轴标题，第三个是Y轴标题，第四个是数据集合，
		 * PlotOrientation.HORIZONTAL , 图表方向：水平PlotOrientation.VERTICAL ,
		 * 图表方向：垂直 false, 是否显示图例(对于简单的柱状图必须是false),生成在图片的最下方，该例中表示什么颜色代表什么地方。
		 * true, 是否生成工具 true 是否生成URL链接
		 */
		JFreeChart chart = ChartFactory.createBarChart3D(title, xTitle, yTitle, dataset, PlotOrientation.VERTICAL, true, false, false);
		// 获取柱状图的plot的对象
		CategoryPlot plot = chart.getCategoryPlot();
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		// 取得纵轴
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		// PLAIN字体样式为普通， BOLD字体样式为粗体
		textTitle.setFont(new Font("黑体", Font.BOLD, 20));
		// 取得横轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置柱状图整体距离y轴左侧10%
		domainAxis.setLowerMargin(0.1);
		// 设置柱状图整体距离y轴右侧10%
		domainAxis.setUpperMargin(0.1);
		// 设置X轴与X轴标签之间的距离为1个像素 setCategoryMargin设置距离百分比
		domainAxis.setCategoryLabelPositionOffset(1);
		// 设置横轴标尺值字体
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 10));
		// 设置横轴显示标签的字体
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 10));
		// 设置纵轴标尺值字体
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		// 设置纵轴显示标签字体
		numberaxis.setLabelFont(new Font("黑体", Font.BOLD, 12));
		// char.getLegend 取得表的第一个图例，设置图例的字体
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));
		// 文件输出流，传进来一个输出地址作为参数
		ByteArrayOutputStream bos = null;
		byte[] bytes = null;
		try {
			bos = new ByteArrayOutputStream();
			/*
			 * 第一个参数是输出流对象，第二个是分辨率 介于0.1F到1.0F之间 第三个是chart对象（图像作为输出流进行输出）
			 * 第四个第五个是图片大小的设置。
			 */
			ChartUtilities.writeChartAsJPEG(bos, 1.0f, chart, 730, 440);
			bytes = bos.toByteArray();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				bos.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}
