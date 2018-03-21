package com.baihui.core.util.bean;

import java.io.*;

/**
 * ClassName: SerializableUtil
 * @Description: 序列化工具
 * @author feifei.liu
 * @date 2015年9月9日 下午12:36:10
 */
public class SerializableUtil {
	/**
	 * 序列化
	 * 
	 * @param object
	 *            要序列化的对象
	 * @return 序列化byte数组
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				baos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 *            反序列化所需byte数组
	 * @return 对象
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
