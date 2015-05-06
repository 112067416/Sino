package com.coco.tag.assist;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 上午10:35:28
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class TreeOption {

	public String id;
	public String pid;
	public String value;
	public String text;
	public String view;
	public String leaf;
	public String orders;
	public List<TreeOption> children;

	public void addTreeOption(TreeOption option) {
		if (this.children == null) {
			this.children = new ArrayList<TreeOption>();
		}
		this.children.add(option);
	}
}
