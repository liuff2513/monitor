package com.baihui.core.util.file;


import com.baihui.core.util.GZipUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @ClassName:     Base64.java
 * @Description:   TODO(Base64、Gzip压缩) 
 * @All rights Reserved, Designed By PCstars
 * @Copyright:  Copyright(C) 2014-2015
 * @Company:	baihui
 * @author      ziyu.zhang
 * @version     V2.0  
 * @Date        2015年10月8日 下午6:07:42 
 */
public class Base64
{
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55,
			56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
			21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
			47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

	public Base64()
	{
	}

	public static String encode(byte[] data)
	{
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;

		while (i < len)
		{
			b1 = data[i++] & 0xff;
			if (i == len)
			{
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len)
			{
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}

	public static byte[] decode(String str)
	{
		byte[] data = str.getBytes();
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len)
		{

			/* b1 */
			do
			{
				b1 = base64DecodeChars[data[i++]];
			}
			while (i < len && b1 == -1);
			if (b1 == -1)
			{
				break;
			}

			/* b2 */
			do
			{
				b2 = base64DecodeChars[data[i++]];
			}
			while (i < len && b2 == -1);
			if (b2 == -1)
			{
				break;
			}
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do
			{
				b3 = data[i++];
				if (b3 == 61)
				{
					return buf.toByteArray();
				}
				b3 = base64DecodeChars[b3];
			}
			while (i < len && b3 == -1);
			if (b3 == -1)
			{
				break;
			}
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do
			{
				b4 = data[i++];
				if (b4 == 61)
				{
					return buf.toByteArray();
				}
				b4 = base64DecodeChars[b4];
			}
			while (i < len && b4 == -1);
			if (b4 == -1)
			{
				break;
			}
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		return buf.toByteArray();
	}
	public static byte[] unGZip(byte[] bContent)  
	{  

	    byte[] data = new byte[bContent.length];  
	    try  
	    {  
	        ByteArrayInputStream in = new ByteArrayInputStream(bContent);  
	        GZIPInputStream pIn = new GZIPInputStream(in);  
	        DataInputStream objIn = new DataInputStream(pIn);  

	        int len = 0;  
	        int count = 0;  
	        while ((count = objIn.read(data)) != -1)  
	        {  
	            len = len + count;  
	        }  

	        byte[] trueData = new byte[len];  
	        System.arraycopy(data, 0, trueData, 0, len);  

	        objIn.close();  
	        pIn.close();  
	        in.close();  

	        return trueData;  

	    }  
	    catch (Exception e)  
	    {  
	        e.printStackTrace();  
	    }
	    return null;
	}  
	
	/**
	 * 使用zip进行解压 Base64
	 * Description:使用gzip进行解压缩
	 * @param compressedStr
	 * @return
	 */
	public static String ungzipbase(byte[] compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = compressedStr;
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		return decompressed;
		
	}
	
	
	public static void main(String[] args)
	{
		
		/*long s = System.currentTimeMillis();
		System.out.println(s);
		List<Customer> list = new LinkedList<Customer>();
		long e = System.currentTimeMillis();
		System.out.println("总耗时：========"+(e-s));
		JSONObject json = new JSONObject();
		json.put("data", list);
		String encodeStr = GZipUtil.gzipbase(json.toString().replaceAll(" ", "+"));
		
		
		byte[] decodeStr = decode(encodeStr);
		
		··
		String unGZip = ungzipbase(decodeStr);
		
		System.out.println("解密结果:"+unGZip);
		JSONObject obj = JSONObject.fromObject(unGZip);
		System.out.println("data---------------------:"+obj.get("data"));*/
		
		
		
		String res = GZipUtil.ungzipbase("H4sIAAAAAAAAAAEkANv/55Sz6K+3T0HlpLHotKUo5pyq6L+e5o6l5YiwT0HmnI3liqEpVLrD4CQAAAA=");
		System.out.println("解密结果:"+res);
		
	}
}
