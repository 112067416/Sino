package com.quanta.sino.yygl;

import java.awt.Font;
import java.io.FileOutputStream;
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

public class ChartDemo {

	public static void main(String[] args) {
		ChartDemo chartD = new ChartDemo();
		// chartD.createBarDemo("c:\\bar.jpg");
		// chartD.createPie3DDemo("c:\\pie3d.jpg");
		chartD.createPieDemo("c:\\pie.jpg");

	}

	// 创建柱状图
	public void createBarDemo(String jpgname) {
		// 创建数据的集合
		DefaultCategoryDataset dataset = getBarDataset();
		/*
		 * createBarChart3D创建柱图，第一个参数是图标的标题，第二个是X轴标题，第三个是Y轴标题，第四个是数据集合，
		 * PlotOrientation.HORIZONTAL , 图表方向：水平PlotOrientation.VERTICAL ,
		 * 图表方向：垂直 false, 是否显示图例(对于简单的柱状图必须是false),生成在图片的最下方，该例中表示什么颜色代表什么地方。
		 * true, 是否生成工具 true 是否生成URL链接
		 */
		JFreeChart chart = ChartFactory.createBarChart3D("水果产量图", "水果", "产量", dataset, PlotOrientation.VERTICAL, true, false, false);
		// 获取柱状图的plot的对象
		CategoryPlot plot = chart.getCategoryPlot();
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		// 取得纵轴
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		// PLAIN字体样式为普通， BOLD字体样式为粗体
		textTitle.setFont(new Font("黑体", Font.BOLD, 30));
		// 取得横轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置柱状图整体距离y轴左侧10%
		domainAxis.setLowerMargin(0.1);
		// 设置柱状图整体距离y轴右侧10%
		domainAxis.setUpperMargin(0.1);
		// 设置X轴与X轴标签之间的距离为1个像素 setCategoryMargin设置距离百分比
		domainAxis.setCategoryLabelPositionOffset(1);
		// 设置横轴标尺值字体
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 22));
		// 设置横轴显示标签的字体
		domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));
		// 设置纵轴标尺值字体
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 22));
		// 设置纵轴显示标签字体
		numberaxis.setLabelFont(new Font("黑体", Font.BOLD, 18));
		// char.getLegend 取得表的第一个图例，设置图例的字体
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 22));
		// 文件输出流，传进来一个输出地址作为参数
		FileOutputStream jpg = null;
		try {
			jpg = new FileOutputStream(jpgname);
			/*
			 * 第一个参数是输出流对象，第二个是分辨率 介于0.1F到1.0F之间 第三个是chart对象（图像作为输出流进行输出）
			 * 第四个第五个是图片大小的设置。
			 */
			ChartUtilities.writeChartAsJPEG(jpg, 1.0f, chart, 900, 700);

		}
		catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		finally {
			try {
				jpg.close();
			}
			catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	// 获取柱状图数据
	private DefaultCategoryDataset getBarDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 往dataset中加入数据，第一个参数是y轴坐标，第三个参数是x轴坐标，第二个参数是每个参量
		dataset.addValue(33, "北京", "苹果");
		dataset.addValue(55, "上海", "梨子");
		dataset.addValue(77, "南昌", "葡萄");
		dataset.addValue(13, "海南", "香蕉");
		dataset.addValue(28, "北京", "荔枝");
		dataset.addValue(65, "上海", "荔枝");
		return dataset;
	}

	// 创建饼图
	public void createPie3DDemo(String jpgname) {
		DefaultPieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createPieChart3D("水果产量", dataset, true, true, true);
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.BOLD, 50));
		// 设置饼图图例字体的大小
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 22));
		// 获得饼图plot的对象
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 设置饼图旁边的中文字体
		plot.setLabelFont(new Font("黑体", Font.PLAIN, 22));
		FileOutputStream jpg = null;
		// ByteArrayOutputStream bos = null;
		try {
			jpg = new FileOutputStream(jpgname);
			// bos = new ByteArrayOutputStream();

			ChartUtilities.writeChartAsJPEG(jpg, 1.0f, chart, 900, 700, null);

		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				jpg.close();
			}
			catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	// 创建饼图
	public void createPieDemo(String jpgname) {
		DefaultPieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createPieChart("水果产量", dataset, true, true, false);
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.BOLD, 50));
		// 设置饼图图例字体的大小
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 22));
		// 获得饼图plot的对象
		PiePlot pieplot = (PiePlot) chart.getPlot();
		// 背景色　透明度
		pieplot.setBackgroundAlpha(0.5f);
		// 前景色　透明度
		pieplot.setForegroundAlpha(0.5f);
		// 设置饼图旁边的中文字体
		pieplot.setLabelFont(new Font("黑体", Font.PLAIN, 22));
		// pieplot.setExplodePercent(5, 0.1d);
		// pieplot.setToolTipGenerator(new PieToolTipGenerator() {
		//
		// @Override
		// public String generateToolTip(PieDataset dataset, Comparable compare)
		// {
		// System.out.println("====================enter into====================");
		// int count = dataset.getItemCount();
		// for (int index = 0; index < count; index++) {
		// System.out.println("key================="
		// + dataset.getKey(index).toString());
		// }
		// return null;
		// }
		//
		// });
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				("{0}: ({1}，{2})"), NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 其中new StandardPieSectionLabelGenerator(
		// ("{0}: ({1}，{2})"), NumberFormat.getNumberInstance(),
		// new DecimalFormat("0.00%"))

		FileOutputStream jpg = null;
		// ByteArrayOutputStream bos = null;
		try {
			jpg = new FileOutputStream(jpgname);
			// bos = new ByteArrayOutputStream();

			ChartUtilities.writeChartAsJPEG(jpg, 1.0f, chart, 900, 700, null);
			// bos.toByteArray();

		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				jpg.close();
			}
			catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加饼状图数据，setValue只有两个参数第一个是变量的名字，第二个是变量的值
	 */
	private DefaultPieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("苹果", 100);
		dataset.setValue("梨子", 200);
		dataset.setValue("葡萄", 300);
		dataset.setValue("荔枝", 400);
		dataset.setValue("香蕉", 500);
		dataset.setValue("枣子", 600);
		return dataset;
	}

}
