package com.coco.core.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * <p>
 * 图片工具
 * </p>
 * <p>
 * create: 2010-12-21 上午10:00:44
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ImageUtils {

	public static final String FORMAT_PNG = "png";

	public static final String FORMAT_JPG = "jpeg";

	public static final String FORMAT_WBMG = "wbmp";

	public static final String FORMAT_BMG = "bmp";

	public static final String FORMAT_GIF = "gif";

	/**
	 * <p>
	 * 格式化扩展名
	 * </p>
	 * 
	 * @param name
	 * @return String
	 */
	private static String formatName(String name) {
		if (name == null || name.length() == 0) {
			name = FORMAT_PNG;
		}
		String fn = name.toLowerCase();
		if (fn.indexOf("jpeg") != -1 || fn.indexOf("jpg") != -1) {
			name = FORMAT_JPG;
		}
		else if (fn.indexOf("wbmp") != -1) {
			name = FORMAT_WBMG;
		}
		else if (fn.indexOf("bmp") != -1) {
			name = FORMAT_BMG;
		}
		else if (fn.indexOf("gif") != -1) {
			name = FORMAT_GIF;
		}
		else {
			name = FORMAT_PNG;
		}
		return name;
	}

	/**
	 * <p>
	 * 缩放拷贝
	 * </p>
	 * 
	 * @param originalImage
	 *            元素图片
	 * @param scaledWidth
	 *            指定宽度
	 * @param scaledHeight
	 *            指定长度
	 * @return BufferedImage
	 */
	public static BufferedImage createResizedCopy(Image originalImage,
			int scaledWidth, int scaledHeight) {
		int width = originalImage.getWidth(null);
		int height = originalImage.getHeight(null);
		if (scaledWidth <= 0) {
			scaledWidth = width;
		}
		if (scaledHeight <= 0) {
			scaledHeight = height;
		}
		if (originalImage instanceof BufferedImage) {
			if (width == scaledWidth && height == scaledHeight) {
				return (BufferedImage) originalImage;
			}
			AffineTransform atf = AffineTransform.getScaleInstance((double) scaledWidth
					/ width, (double) scaledHeight / height);
			AffineTransformOp op = new AffineTransformOp(atf, null);
			return op.filter((BufferedImage) originalImage, null);
		}
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledBI.createGraphics();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		return scaledBI;
	}

	/**
	 * <p>
	 * 缩放拷贝
	 * </p>
	 * 
	 * @param is
	 *            输入流
	 * @param out
	 *            输出流
	 * @param scaleWidth
	 *            指定宽度
	 * @param scaledHeight
	 *            指定长度
	 * @param formatName
	 *            图片格式
	 */
	public static void createResizedCopy(InputStream is, OutputStream out,
			int scaleWidth, int scaledHeight, String formatName) {
		try {
			BufferedImage bufferedImage = ImageIO.read(is);
			if (scaleWidth <= 0 && scaledHeight <= 0) {
				ImageIO.write(bufferedImage, formatName(formatName), out);
			}
			else {
				if (scaleWidth > 0 && scaledHeight <= 0) {
					scaledHeight = scaleWidth * bufferedImage.getHeight()
							/ bufferedImage.getWidth();
				}
				if (scaleWidth <= 0 && scaledHeight > 0) {
					scaleWidth = scaledHeight * bufferedImage.getWidth()
							/ bufferedImage.getHeight();
				}
				BufferedImage formatImage = createResizedCopy(bufferedImage, scaleWidth, scaledHeight);
				ImageIO.write(formatImage, formatName(formatName), out);
			}
		}
		catch (Exception e) {
		}
	}

	/**
	 * <p>
	 * 缩放拷贝
	 * </p>
	 * 
	 * @param is
	 *            输入流
	 * @param scaleWidth
	 *            指定宽度
	 * @param scaledHeight
	 *            指定长度
	 * @param formatName
	 *            图片格式
	 * @return byte[]
	 */
	public static byte[] createResizedCopy(InputStream is, int scaleWidth,
			int scaledHeight, String formatName) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			createResizedCopy(is, baos, scaleWidth, scaledHeight, formatName);
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * 缩放拷贝
	 * </p>
	 * 
	 * @param is
	 *            输入流
	 * @param out
	 *            输出流
	 * @param scale
	 *            缩放比例
	 * @param formatName
	 *            图片格式
	 */
	public static void createResizedCopy(InputStream is, OutputStream out,
			float scale, String formatName) {
		try {
			BufferedImage bufferedImage = ImageIO.read(is);
			if (scale <= 0) {
				ImageIO.write(bufferedImage, formatName(formatName), out);
			}
			else {
				int scaleWidth = (int) (bufferedImage.getWidth() * scale);
				int scaledHeight = (int) (bufferedImage.getHeight() * scale);
				BufferedImage formatImage = createResizedCopy(bufferedImage, scaleWidth, scaledHeight);
				ImageIO.write(formatImage, formatName(formatName), out);
			}
		}
		catch (Exception e) {
		}
	}

	/**
	 * <p>
	 * 缩放拷贝
	 * </p>
	 * 
	 * @param is
	 *            输入流
	 * @param scale
	 *            缩放比例
	 * @param formatName
	 *            图片格式
	 * @return byte[]
	 */
	public static byte[] createResizedCopy(InputStream is, float scale,
			String formatName) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			createResizedCopy(is, baos, scale, formatName);
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		}
		catch (Exception e) {
			return null;
		}
	}

}
