/**
 * 
 */
package com.quanta.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 */

public class FileRead {

	public static void main(String[] args) {
		String filePath = "D:\\zkfp\\fp\\index.jsp";
		File file = new File(filePath);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}
}