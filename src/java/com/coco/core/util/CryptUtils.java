package com.coco.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * 加密工具类
 * </p>
 * <p>
 * create: 2010-12-21 上午09:59:49
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CryptUtils {

	/**
	 * <p>
	 * 加密密鈅
	 * </p>
	 */
	private static final String DES_CRYPT_KEY = "XuAndLuo";

	/**
	 * <p>
	 * 加密方式
	 * </p>
	 */
	private static final String ALGORITHM = "DES";

	/**
	 * 默认密码
	 */
	private static final String PASSWORD_DEFAULT = "88888888";

	/**
	 * <p>
	 * 加密，若password为空则默认{@link #PASSWORD_DEFAULT}
	 * </p>
	 * 
	 * @param password
	 * @return String
	 */
	public static String cryptPswd(String password) {
		if (password == null || password.isEmpty()) {
			password = PASSWORD_DEFAULT;
		}
		try {
			SecretKey deskey = new SecretKeySpec(DES_CRYPT_KEY.getBytes(),
					ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, new java.security.SecureRandom());
			byte[] encPswd = cipher.doFinal(password.getBytes());
			return new String(Base64.encode(encPswd));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return password;
	}

	/**
	 * <p>
	 * 解密，若为空则返回默认密码{@link #PASSWORD_DEFAULT}
	 * </p>
	 * 
	 * @param password
	 * @return String
	 */
	public static String decryptPswd(String password) {
		if (password == null || password.isEmpty()) {
			password = PASSWORD_DEFAULT;
		}
		try {
			SecretKey deskey = new SecretKeySpec(DES_CRYPT_KEY.getBytes(),
					ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			byte[] encPswd = cipher.doFinal(Base64.decode(password.toCharArray()));
			return new String(encPswd).trim();
		}
		catch (Exception ex) {
		}
		return password;
	}
}
