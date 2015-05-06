package com.coco.query.quartz;

import java.io.File;
import java.util.logging.Logger;

import com.coco.core.env.Helper;

public class DeleteTempFile {

	private static final Logger LOGGER = Logger.getLogger(DeleteTempFile.class.getName());

	private String path;

	public void doJob() {
		String realPath = Helper.SERVLET_CONTEXT != null ? Helper.SERVLET_CONTEXT.getRealPath(path)
				: null;
		if (realPath != null) {
			File dir = new File(realPath);
			if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isFile()
							&& file.lastModified() < System.currentTimeMillis() - 3600000) {
						LOGGER.info("Delete file : " + file.getPath());
						file.delete();
					}
				}
			}
		}
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
