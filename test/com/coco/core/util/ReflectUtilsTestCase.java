package com.coco.core.util;

import org.junit.Test;

public class ReflectUtilsTestCase {

	@Test
	public void copy() {

		A a = new A();
		a.setA("a");
		a.setB("b");
		B b = new B();
		b.setB("c");
		ReflectUtils.copy(b, a, false);
		System.out.println(a.getA() + "," + a.getB());
		System.out.println(b.getA() + "," + b.getB());

	}

	public static class A {
		private String a;

		private String b;

		/**
		 * @return the a
		 */
		public String getA() {
			return a;
		}

		/**
		 * @param a
		 *            the a to set
		 */
		public void setA(String a) {
			this.a = a;
		}

		/**
		 * @return the b
		 */
		public String getB() {
			return b;
		}

		/**
		 * @param b
		 *            the b to set
		 */
		public void setB(String b) {
			this.b = b;
		}

	}

	public static class B {
		private String a;
		private String b;

		/**
		 * @return the a
		 */
		public String getA() {
			return a;
		}

		/**
		 * @param a
		 *            the a to set
		 */
		public void setA(String a) {
			this.a = a;
		}

		/**
		 * @return the b
		 */
		public String getB() {
			return b;
		}

		/**
		 * @param b
		 *            the b to set
		 */
		public void setB(String b) {
			this.b = b;
		}

	}

}
