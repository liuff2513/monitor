package com.baihui.core.util.file;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


/**
 * @ClassName: Base64FileUtil.java
 * @Description: (Base64字符串到图片之间的转换)
 * @All rights Reserved, Designed By PCstars
 * @Copyright: Copyright(C) 2014-2015
 * @Company: baihui
 * @author ziyu.zhang
 * @version V2.0
 * @Date 2015年11月25日 下午5:37:02
 */
public class Base64ImageUtil {

	public static void main(String[] args) {
		String filePath = "D:\\Documents\\Pictures\\壁纸\\251468.jpg";
		// File file = new File("d:\\123.gif");
		// System.out.println(file.getName());
		String strImg = GetImageStr(filePath);
		System.out.println(strImg);
		GenerateImage(strImg, "D:\\Documents\\Pictures\\壁纸\\251468.jpg");

	}

	// 图片转化成base64字符串
	public static String GetImageStr(String filePath) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(filePath);
			data = new byte[in.available()];//实际可读字节数，也就是总大小
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr, String fileUrl) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(fileUrl);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
