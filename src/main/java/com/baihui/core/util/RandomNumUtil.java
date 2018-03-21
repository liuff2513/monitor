package com.baihui.core.util;

import java.util.Random;

	/**
 * 
 * ClassName: RandomNumUtil
 * @Description: 随机产生一个自定义长度的字符串
 * @author xiaopeng.ma
 * @date 2016年1月14日 下午8:51:27
 */
public class RandomNumUtil {

	private static Random randGen = null;
	private static char[] numbersAndLetters = null;

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("1234567890").toCharArray();
/*			numbersAndLetters = ("23456789abcdefghijkmnpqrstuvwxyz" +
					"23456789ABCDEFGHJKLMNPQRSTUVWXYZ").toCharArray();
*/			
		}
		char [] randBuffer = new char[length];
		for (int i=0; i<randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}
	public static void main(String[] args) {
		String randomString = randomString(6);
		System.out.println(randomString);
	}
}
