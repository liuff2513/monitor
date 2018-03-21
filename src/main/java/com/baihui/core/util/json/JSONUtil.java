package com.baihui.core.util.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

/**
 * ClassName: JSONUtil
 * @Description: JSON工具类
 * @author feifei.liu
 * @date 2015年9月24日 下午6:55:52
 */
public class JSONUtil {
	private static JsonConfig jsonConfig;
	
	/**
	 * @Description: 转换为JSONObject对象时过滤
	 * @param object
	 * @param excludes
	 * @return   
	 * @return Object  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月24日 下午6:55:48
	 */
	public static JSONObject excludeObject(Object object, String[] excludes){
		if(jsonConfig==null) jsonConfig=new JsonConfig();
		if(excludes!=null&&excludes.length>0) jsonConfig.setExcludes(excludes);
		return JSONObject.fromObject(object, jsonConfig);
	}
	
	/**
	 * @Description: 转换为JSONObject对象时过滤
	 * @param object
	 * @param excludes
	 * @return   
	 * @return Object  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月24日 下午6:55:30
	 */
	public static JSONObject excludeObject(Object object, String excludes){
		if(jsonConfig==null) jsonConfig=new JsonConfig();
		String[] excludesArr=new String[]{};
		if(excludes!=null&&!"".equals(excludes)){
			if(excludes.indexOf(",")!=-1) excludesArr=excludes.split(",");
			else{ excludesArr=new String[1]; excludesArr[0]=excludes;}
		}
		jsonConfig.setExcludes(excludesArr);
		return JSONObject.fromObject(object, jsonConfig);
	}
	
	/**
	 * @Description: 转换为JSONOArray对象时过滤
	 * @param object
	 * @param excludes
	 * @return   
	 * @return JSONArray  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:28:34
	 */
	public static JSONArray excludeArray(Object object, String[] excludes){
		if(jsonConfig==null) jsonConfig=new JsonConfig();
		if(excludes!=null&&excludes.length>0) jsonConfig.setExcludes(excludes);
		return JSONArray.fromObject(object, jsonConfig);
	}
	
	/**
	 * @Description: 转换为JSONOArray对象时过滤
	 * @param object
	 * @param excludes
	 * @return   
	 * @return JSONArray  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:29:32
	 */
	public static JSONArray excludeArray(Object object, String excludes){
		if(jsonConfig==null) jsonConfig=new JsonConfig();
		String[] excludesArr=new String[]{};
		if(excludes!=null&&!"".equals(excludes)){
			if(excludes.indexOf(",")!=-1) excludesArr=excludes.split(",");
			else{ excludesArr=new String[1]; excludesArr[0]=excludes;}
		}
		jsonConfig.setExcludes(excludesArr);
		return JSONArray.fromObject(object, jsonConfig);
	}
	
	
	/**
	 * @Description: JSONObject对象转换为Bean对象
	 * @param jsonObject
	 * @param clazz
	 * @return   
	 * @return Object  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:15:32
	 */
	public static Object toBean(JSONObject jsonObject, Class<?> clazz){
		return JSONObject.toBean(jsonObject, clazz);
	}
	
	/**
	 * @Description: Object对象转换为Bean对象
	 * @param object
	 * @param clazz
	 * @return   
	 * @return Object  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:14:23
	 */
	public static Object toBean(Object object, Class<?> clazz){
		JSONObject jsonObject=JSONObject.fromObject(object);
		return toBean(jsonObject, clazz);
	}
	
	/**
	 * @Description: JSONOArray对象转换为集合对象
	 * @param jsonArray
	 * @param clazz
	 * @return   
	 * @return List<?>  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:23:15
	 */
	public static List<?> toList(JSONArray jsonArray, Class<?> clazz){
		return (List<?>) JSONArray.toCollection(jsonArray, clazz);
	}
	/**
	 * @Description: Object对象转换为集合对象
	 * @param object
	 * @param clazz
	 * @return   
	 * @return List<?>  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年9月25日 下午6:23:15
	 */
	public static List<?> toList(Object object, Class<?> clazz){
		JSONArray jsonArray=JSONArray.fromObject(object);
		return toList(jsonArray, clazz);
	}
}
