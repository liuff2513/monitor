/**  
 * @Title: DoubleUtil.java
 * @Package com.baihui.crm.utils
 * @Description: TODO
 * @author zhibin.zhu
 * @date 2016年5月20日
 */
package com.baihui.core.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ClassName: DoubleUtil 
 * @Description: TODO
 * @author zhibin.zhu
 * @date 2016年5月20日
 */
public class DoubleUtil {

	private static final int DEF_DIV_SCALE = 10;
	
	/** 
	* * 两个Double数相加 * 
	*  
	* @param v1 * 
	* @param v2 * 
	* @return Double 
	*/  
	public static Double add(Double v1, Double v2) {  
	   BigDecimal b1 = new BigDecimal(v1.toString());  
	   BigDecimal b2 = new BigDecimal(v2.toString());  
	   return new Double(b1.add(b2).doubleValue());  
	}  
	  
	/** 
	* * 两个Double数相减 * 
	*  
	* @param v1 * 
	* @param v2 * 
	* @return Double 
	*/  
	public static Double sub(Double v1, Double v2) {  
	   BigDecimal b1 = new BigDecimal(v1.toString());  
	   BigDecimal b2 = new BigDecimal(v2.toString());  
	   return new Double(b1.subtract(b2).doubleValue());  
	}  
	  
	/** 
	* * 两个Double数相乘 * 
	*  
	* @param v1 * 
	* @param v2 * 
	* @return Double 
	*/  
	public static Double mul(Double v1, Double v2) {  
	   BigDecimal b1 = new BigDecimal(v1.toString());  
	   BigDecimal b2 = new BigDecimal(v2.toString());  
	   return new Double(b1.multiply(b2).doubleValue());  
	}  
	  
	/** 
	* * 两个Double数相除 * 
	*  
	* @param v1 * 
	* @param v2 * 
	* @return Double 
	*/  
	public static Double div(Double v1, Double v2) {  
	   BigDecimal b1 = new BigDecimal(v1.toString());  
	   BigDecimal b2 = new BigDecimal(v2.toString());  
	   return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)  
	     .doubleValue());  
	}  
	  
	/** 
	* * 两个Double数相除，并保留scale位小数 * 
	*  
	* @param v1 * 
	* @param v2 * 
	* @param scale * 
	* @return Double 
	*/  
	public static Double div(Double v1, Double v2, int scale) {  
	   if (scale < 0) {  
	    throw new IllegalArgumentException(  
	      "The scale must be a positive integer or zero");  
	   }  
	   BigDecimal b1 = new BigDecimal(v1.toString());  
	   BigDecimal b2 = new BigDecimal(v2.toString());  
	   return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());  
	}  
	
	/**
	 * @Description: NULL转成0.0
	 * @param doule
	 * @return   
	 * @return Double  
	 * @throws
	 */
	public static Double nullToZero(Double doule){
		
		if(doule==null) doule = new Double(0.0);
		return doule;
	}
	/**
	 * @Description: double数值保留num位小数
	 * @param value double数值
	 * @param num 保留num位小数
	 * @return
	 * @throws
	 * @author changmeng.wang
	 * @date 2017/4/27 16:10
	 */
	public static String scaleNum(double value, int num) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(num, RoundingMode.HALF_UP);
		return bd.toString();
	}
}
