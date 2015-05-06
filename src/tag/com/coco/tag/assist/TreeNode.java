package com.coco.tag.assist;

import java.util.List;

public class TreeNode {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 父ID
	 */
	private String pid;

	/**
	 * 树形深度
	 */
	private int deep;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 对象
	 */
	private Object obj;

	/**
	 * 子对象列表
	 */
	private List<TreeNode> children;

	/**
	 * 父对象
	 */
	private TreeNode parent;

	/**
	 * 状态
	 */
	private int state;

	private int count;

	private int leafCount;

	private int left;

	private int top;

	private int width;

	private int height;

	private int offsetLeft;

	private int childrenMaxHeight;

	public TreeNode() {

	}

	public TreeNode(String id, String pid) {
		this.id = id;
		this.pid = pid;
	}

	public TreeNode(String id, String pid, String name) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLeafCount() {
		return leafCount;
	}

	public void setLeafCount(int leafCount) {
		this.leafCount = leafCount;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOffsetLeft() {
		return offsetLeft;
	}

	public void setOffsetLeft(int offsetLeft) {
		this.offsetLeft = offsetLeft;
	}

	public int getChildrenMaxHeight() {
		return childrenMaxHeight;
	}

	public void setChildrenMaxHeight(int childrenMaxHeight) {
		this.childrenMaxHeight = childrenMaxHeight;
	}

}
