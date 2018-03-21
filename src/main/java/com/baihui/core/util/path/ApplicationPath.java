package com.baihui.core.util.path;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * ClassName: ApplicationPath
 * @Description: 项目路径工具类
 * @author feifei.liu
 * @date 2015年9月9日 上午11:43:05
 */
public class ApplicationPath {
	public static String getApplicationPath( Class<?> cls) {
		String path = null;
		URI uri = getClassLocationURI(cls);
		if (uri != null) {
			path = uri.getPath();
//			if(path.startsWith("/")) path = path.substring(1);
//			path = path.replace("/", "\\");
			if(path.startsWith("\\")) path = path.substring(1);
			path = path.replace("\\", "/");
		}
		return path;

	}

	private static URI getClassLocationURI(Class<?> cls) {
		if (cls == null) {
			throw new IllegalArgumentException("Null Input:class");
		}
		URI uri = null;
		try {
			uri=cls.getResource("").toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri;
	}

	public static String getAppliactionPath() {
		return getRootPath() + "WEB-INF" + File.separator + "classes";
	}

	public static String getRootPath() {
		String path = getApplicationPath(ApplicationPath.class);
		int index = path.indexOf("WEB-INF");
		int index2 = path.indexOf("classes");
		path = path.substring(0, index2-1);
		return path;
	}

	public static String getProjectRootPath() {
		File rootPath = new File(getRootPath());
		return rootPath.getParentFile().getAbsolutePath();
	}
	public static void main(String[] args) {
		System.out.println(getRootPath());
	}
}
