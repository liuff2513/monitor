package com.baihui.core.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName:     EncryptUtil.java
 * @Description:   TODO(加密工具) 
 * @All rights Reserved, Designed By PCstars
 * @Copyright:  Copyright(C) 2014-2015
 * @Company:	baihui
 * @author      ziyu.zhang
 * @version     V2.0  
 * @Date        2015年10月8日 下午6:04:31 
 */
public class EncryptUtil {

	// md5加密
	public static String md5(String inputText) {
		return encrypt(inputText, "md5","UTF-8");
	}
	
	// md5加密
	public static String md5(String inputText,String entype) {
		return encrypt(inputText, "md5",entype);
	}


	public static String encrypt(String inputText, String algorithmName,String entype) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes(entype));
			byte s[] = m.digest();
			// m.digest(inputText.getBytes("UTF8"));
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}
	
	// 返回十六进制字符串
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}
	
}