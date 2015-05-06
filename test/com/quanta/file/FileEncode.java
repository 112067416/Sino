package com.quanta.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * <p>
 * 文件转换
 * </p>
 * <p>
 * create: 2011-1-18 上午09:08:58
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class FileEncode {

	/**
	 * <p>
	 * </p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean flag = false;
		File file = new File("E:\\proj\\sino\\DOC\\设计文档");
		if (file.isDirectory()) {
			System.out.println(file.list().length);
		}
		try {
			byte[] buf = FileUtils.readFileToByteArray(file);
			String filecCntent = FileUtils.readFileToString(file, "UTF-8");
			if (filecCntent.equals(new String(buf, "UTF-8"))) {
				flag = true;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		if (flag) {
			System.out.println("=============TRUE===============");
		}
		else {
			System.out.println("=============FALSE===============");
		}
	}
}
