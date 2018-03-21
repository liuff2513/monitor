package com.baihui.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZip压缩解压
 * @author ziyuzhang
 *
 */
public class GZipUtil {
	
	private static String encode = "utf-8";//"ISO-8859-1" 
    
    public String getEncode() { 
        return encode; 
    } 
   
    /*
     * 设置 编码，默认编码：UTF-8
     */ 
    public void setEncode(String encode) { 
    	GZipUtil.encode = encode; 
    } 
	
	/**
	 * 使用gzip进行压缩 Base64
	 * 使用gzip进行压缩 
	 */
	public static String gzipbase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new sun.misc.BASE64Encoder().encode(out.toByteArray());
	}

	/**
	 * 使用zip进行解压 Base64
	 * Description:使用gzip进行解压缩
	 * @param compressedStr
	 * @return
	 */
	public static String ungzipbase(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
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
}
