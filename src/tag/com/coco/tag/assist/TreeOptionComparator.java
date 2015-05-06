package com.coco.tag.assist;

import java.util.Comparator;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 上午10:35:32
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class TreeOptionComparator implements Comparator<TreeOption> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TreeOption option1, TreeOption option2) {
		if (option1 == null || option1.orders == null) {
			return -1;
		}
		if (option2 == null || option2.orders == null) {
			return 1;
		}
		if (option1.orders.length() < option2.orders.length()) {
			return -1;
		}
		else if (option1.orders.length() > option2.orders.length()) {
			return 1;
		}
		return option1.orders.compareTo(option2.orders);
	}
	//
	// public static void main(String[] args) {
	// List<TreeOption> os = new ArrayList<TreeOption>();
	// TreeOption o = null;
	// o = new TreeOption();
	// o.orders = "1";
	// os.add(o);
	// o = new TreeOption();
	// o.orders = "02";
	// os.add(o);
	// o = new TreeOption();
	// o.orders = "11";
	// os.add(o);
	// o = new TreeOption();
	// o.orders = "011";
	// os.add(o);
	// Collections.sort(os, new TreeOptionComparator());
	// for(TreeOption c : os)
	// System.out.println(c.orders);
	// }

}
