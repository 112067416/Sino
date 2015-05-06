package com.coco.core.env;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.bean.Property;

/**
 * <p>
 * 系统初始化
 * </p>
 * <p>
 * create: 2010-12-21 上午09:35:34
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Init {

	private List<Property> modules;

	private JdbcTemplate jt;

	/**
	 * <p>
	 * 初始化指定的文件列表
	 * </p>
	 * 
	 * @param sqlfiles
	 */
	public void init(String[] sqlfiles) {
		if (sqlfiles != null) {
			InputStream is = null;
			String content;
			ByteArrayOutputStream bos = null;
			int len;
			byte[] bs = new byte[8096];
			String[] sqlArr = null;
			List<List<String>> sqls = new ArrayList<List<String>>();
			List<String> qs = null;
			for (String sqlfile : sqlfiles) {
				try {
					is = this.getClass().getClassLoader().getResourceAsStream(sqlfile);
					bos = new ByteArrayOutputStream();
					while ((len = is.read(bs)) > 0) {
						bos.write(bs, 0, len);
					}
					content = bos.toString("UTF-8");
					sqlArr = content.split("\n");
					for (String sql : sqlArr) {
						if (!(sql = sql.trim()).isEmpty()
								&& !sql.startsWith("--")) {
							Logger.getLogger(Init.class.getName()).log(Level.INFO, "[init sql] "
									+ sql);
							if (qs == null || qs.size() == 500) {
								qs = new ArrayList<String>();
								sqls.add(qs);
							}
							qs.add(sql);
						}
					}
				}
				catch (Exception ex) {
					Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
				}
				finally {
					if (is != null) {
						try {
							is.close();
						}
						catch (IOException ex) {
							Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
					if (bos != null) {
						try {
							bos.close();
						}
						catch (IOException ex) {
							Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
			if (!sqls.isEmpty()) {
				for (List<String> items : sqls) {
					jt.batchUpdate(items.toArray(new String[0]));
				}
			}
		}
	}

	public List<Property> getModules() {
		return modules;
	}

	public void setModules(List<Property> modules) {
		this.modules = modules;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	public static void main(String[] args) {
		Helper.getBean(Init.class).init(new String[] { "init/COCO_CODE.sql" });
	}
}
