package com.baihui.core.util.str;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *ClassName: ValidateUtil 
 * @Description: 校验工具类
 * @author 智圣
 * @date 2015年8月30日 下午2:54:13
 */
/**
 *ClassName: ValidateUtil 
 * @Description: TODO
 * @author 智圣
 * @date 2015年9月1日 下午7:35:37
 */
public class StringUtil {


	public StringUtil() {

	}

	/**
	 * @Description: 首字母大写
	 * @param @param name
	 * @param @return   
	 * @return String
	 * @throws
	 * @author 智圣
	 * @date 2015年9月1日 下午7:35:42
	 */
	public static String captureName(String name) {
		//     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//		        return  name;
		char[] cs=name.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);

	}

	/**
	 * @Description: FIELD_NAME转POJO_NAME
	 * @param str
	 * @return
	 * @return String
	 * @throws
	 * @author feifei.liu
	 * @date 2016年8月24日 下午3:24:28
	 */
	public static String captureUnderlineName(String str) {
		if(str==null||"".equals(str)) return str;
		char[] cs = str.toLowerCase().toCharArray();
		for(int i=0; i< cs.length; i++) {
			if(cs[i]=='_'&&i<cs.length-1) {
				if(!Character.isDigit(cs[i+1])) cs[i+1]-=32;
			}
		}
		return String.valueOf(cs).replace("_", "");
	}



	/**
	 * 按某分隔符切分字符串 并返回字符数组
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public static final String[] split(String str, String delims) {
		StringTokenizer st = new StringTokenizer(str, delims);
		ArrayList list = new ArrayList();
		for (; st.hasMoreTokens(); list.add(st.nextToken()))
			;
		return (String[]) list.toArray(new String[list.size()]);
	}
	/**
	 * 按某分隔符切分字符串 并返回字符集
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public static final List splitReList(String str, String delims) {
		StringTokenizer st = new StringTokenizer(str, delims);
		ArrayList list = new ArrayList();
		for (; st.hasMoreTokens(); list.add(st.nextToken()));
		return list;
	}

	/**
	 * 按长度得到字符
	 * @param str
	 * @param length
	 * @return
	 */
	public static String substring(String str, int length) {
		if (str == null)
			return null;

		if (str.length() > length)
			return (str.substring(0, length - 2) + "...");
		else
			return str;
	}

	/**
	 * 获取本机IP
	 */
	public static String getIp(){
		InetAddress addr = null;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//获得本机IP
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	/**
	 * 获取访问者IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 得到字符串中某个字符的个数
	 * @param str
	 * @param c
	 * @return
	 */
	public static final int getCharnum(String str,char c){
		int num = 0;
		char[] chars = str.toCharArray();
		for(int i = 0; i < chars.length; i++)
		{
			if(c == chars[i])
			{
				num++;
			}
		}
		return num;
	}

	/**
	 * 按长度分割字符串
	 *
	 * @param content
	 * @param len
	 * @return
	 */
	public static String[] split(String content, int len) {
		if (content == null || content.equals("")) {
			return null;
		}
		int len2 = content.length();
		if (len2 <= len) {
			return new String[] { content };
		} else {
			int i = len2 / len + 1;
			System.out.println("i:" + i);
			String[] strA = new String[i];
			int j = 0;
			int begin = 0;
			int end = 0;
			while (j < i) {
				begin = j * len;
				end = (j + 1) * len;
				if (end > len2)
					end = len2;
				strA[j] = content.substring(begin, end);
				// System.out.println(strA[j]+"<br/>");
				j = j + 1;
			}
			return strA;
		}
	}

	public static boolean emailFormat(String email)
	{
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}


	/**
	 * 验证是不是EMAIL
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean retval = false;
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		retval = matcher.matches();
		return retval;
	}
	/**
	 *
	 * @Title:  isMobilePhone
	 * @Description: (验证手机号是否正确)
	 * @author: ziyu.zhang
	 * @date:   2015年10月12日 下午3:59:48 
	 * @update: 2015年10月12日 下午3:59:48
	 * @param mobilePhone
	 * @return
	 * @return: boolean
	 * @throws:
	 * Why & What is modified: <修改原因描述>
	 */
	public static boolean isMobilePhone(String mobilePhone){
		boolean retval = false;
		String check = "^0?(13[0-9]|15[012356789]|18[0123456789]|17[67]|14[57])[0-9]{8}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(mobilePhone);
		retval = matcher.matches();
		return retval;
	}

	/**
	 * 验证汉字为true 
	 * @param s
	 * @return
	 */
	public static boolean isLetterorDigit(String s) {
		if (s.equals("") || s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				// if (!Character.isLetter(s.charAt(i))){
				return false;
			}
		}
		// Character.isJavaLetter()
		return true;
	}
	/**
	 * 判断某字符串是否为null，如果长度大于256，则返回256长度的子字符串，反之返回原串
	 * @param str
	 * @return
	 */
	public static String checkStr(String str){
		if(str==null){
			return "";
		}else if(str.length()>256){
			return str.substring(256);
		}else{
			return str;
		}
	}

	/**
	 * 验证是不是Int
	 * validate a int string   
	 * the Integer string.
	 * @return true if the str is invalid otherwise false.
	 */
	public static boolean validateInt(String strs) {
		String str = "";
		if(strs.contains("-")){
			str = strs.substring(1, strs.length());
		}else{
			str = strs;
		}
		if (str == null || str.trim().equals(""))
			return false;

		char c;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				return false;
		}

		return true;
	}
	/**
	 * 字节码转换成16进制字符串 内部调试使用
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 字节码转换成自定义字符串 内部调试使用
	 *
	 * @param b
	 * @return
	 */
	public static String byte2string(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	public static byte[] string2byte(String hs) {
		byte[] b = new byte[hs.length() / 2];
		for (int i = 0; i < hs.length(); i = i + 2) {
			String sub = hs.substring(i, i + 2);
			byte bb = new Integer(Integer.parseInt(sub, 16)).byteValue();
			b[i / 2] = bb;
		}
		return b;
	}

	/**
	 * 验证字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean empty(String param) {
		return  null == param || param.trim().length() < 1 || "" == param || "".equals(param) || "null".equals(param)||"undefined".equals(param);
	}

	/**
	 * 验证英文字母或数据
	 * @param str
	 * @return
	 */
	public static boolean isLetOrDigit(String str) {
		Pattern p = null; // 正则表达式
		Matcher m = null; // 操作的字符串
		boolean value = true;
		try {
			p = Pattern.compile("[^0-9A-Za-z]");
			m = p.matcher(str);
			if (m.find()) {

				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 验证是否是小写字符串
	 */
	@SuppressWarnings("unused")
	private static boolean isLowerLetter(String str) {
		Pattern p = null; // 正则表达式
		Matcher m = null; // 操作的字符串
		boolean value = true;
		try {
			p = Pattern.compile("[^a-z]");
			m = p.matcher(str);
			if (m.find()) {
				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 判断字符串是否为int或为double
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit3(String strNum){
		boolean sd = false;
		if(null==strNum || "".equals(strNum)){
			return sd;
		}
		if(strNum.length()>0 && ".".equals(strNum.substring(0,1))){
			return sd;
		}
		if(strNum.contains(".") && !strNum.contains("..")){
			Pattern pattern = Pattern.compile("[0-9]{1,}");
			for(int i=0;i<strNum.length();i++){
				if(!".".equals(String.valueOf(strNum.charAt(i)))){
					Matcher matcher = pattern.matcher((CharSequence) String.valueOf(strNum.charAt(i)));
					sd = matcher.matches();
					if(sd==false){
						return sd;
					}
				}
			}
		}

		if(!strNum.contains(".")){
			if("0".equals(String.valueOf(strNum.charAt(0))) && strNum.length()>1){
				return sd;
			}else{
				Pattern pattern = Pattern.compile("[0-9]{1,}");
				Matcher matcher = pattern.matcher((CharSequence) strNum);
				sd = matcher.matches();
			}
		}

		return sd;
	}

	/**
	 * 截取数字
	 * @param content
	 * @return
	 */
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	/**
	 * 截取数字
	 * @param content
	 * @return
	 */
	public static Set<String> getNumsList(String content,Set<String> lists) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			lists.add(matcher.group());
		}
		return lists;
	}


	/**
	 * 截取英文字母
	 * @param content
	 * @return
	 */
	public static Set<String> getEnsList(String content,Set<String> lists) {
		Pattern pattern = Pattern.compile("[A-Za-z]+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			lists.add(matcher.group());
			lists.add(matcher.group().toLowerCase());
		}
		return lists;
	}

	/**
	 * 根据英文字符获取相似项
	 * @param content
	 * @return
	 */
	public static List<String> getEnsBysin(String sin,String content) {
		Pattern p = Pattern.compile("(?i)"+sin);
		Matcher m = p.matcher(content);
		List<String> lists = new ArrayList<>();
		while (m.find()){
			if(!lists.contains(m.group())){
				lists.add(m.group());
			}
		}
		return lists;
	}


	/**
	 * 截取中文
	 * @param content
	 * @return
	 */
	public static Set<String> getChsList(String content,Set<String> lists) {
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			lists.add(matcher.group());
		}
		return lists;
	}
	/**
	 * 验证字符串中是否包含中文
	 * @param content
	 * @return
	 */
	public static Boolean isContainsCh(String content) {
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
		Matcher matcher = pattern.matcher(content);
		return matcher.find();
	}

	/**
	 * 截取符号
	 * @param content
	 * @return
	 */
	public static Set<String> getSinList(String content,Set<String> lists) {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9\\u4e00-\\u9fa5]");
		Matcher matcher = pattern.matcher(content);
		//String specials = "+–&|!(){}[]^”~*?;':\"\"";
		String specials = "+–&|!(){}[]^”~*?;':";
		while (matcher.find()) {
			if(!lists.contains(matcher.group())){
				if(specials.indexOf(matcher.group())==-1){
					if(null!=matcher.group() && !" ".equals(matcher.group())){
						lists.add(matcher.group());
					}
				}
			}
		}
		return lists;
	}

	/**
	 * 截取符号
	 * @param content
	 * @return
	 */
	public static String getSinStr(String content) {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9\\u4e00-\\u9fa5]");
		Matcher matcher = pattern.matcher(content);
		//String specials = "+–&|!(){}[]^”~*?;':\"\"";
		String specials = "+–&|!(){}[]^”~*?;':";
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}


	/**
	 * 截取font标签//⊕(.*?)⊙
	 * @param content
	 * @return
	 */
	public static List<String> splitFont(String content) {
		Pattern p = Pattern.compile("<font id='solr_hl' .*?>(.*?)</font>");
		//Pattern p = Pattern.compile("⊕(.*?)⊙");
		Matcher m = p.matcher(content);
		List<String> lists = new ArrayList<>();
		while (m.find()){
			lists.add(m.group());
		}
		return lists;
	}


	/**
	 * 判断一个字符串是否都为数字
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

	/**
	 * 判断一个字符串是否都为中文
	 * @param strNum
	 * @return
	 */
	public static boolean isCh(String strNum) {
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}
	/**
	 * 判断一个字符串是否都为英文
	 * @param strNum
	 * @return
	 */
	public static boolean isEn(String strNum) {
		Pattern pattern = Pattern.compile("[A-Za-z]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}


	/**
	 * 截取非数字
	 * @param content
	 * @return
	 */
	public static String splitNotNumber(String content) {
		Pattern pattern = Pattern.compile("\\D+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	/**
	 * matcher [20160516171633_206.] 之前的任何字符
	 * 如:matcher xxxxx20160516171633_206.jpg 中的 xxxxx
	 * @param content
	 * @return
	 */
	public static String matcherFileName(String content) {
		Pattern p = Pattern.compile(".+\\w+(?=20[0-9]+_[0-9]+\\.)");
		Matcher m = p.matcher(content);
		while(m.find())
			return m.group(0);
		return "";
	}

	public static String encode(String str, String code) {
		try {
			return URLEncoder.encode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	public static String decode(String str, String code) {
		try {
			return URLDecoder.decode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	public static String nvl(String param) {
		return param != null ? param.trim() : "";
	}

	public static int parseInt(String param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return i;
	}

	public static int parseInt(String param) {
		return parseInt(param, 0);
	}

	public static long parseLong(String param) {
		long l = 0L;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
		}
		return l;
	}

	public static double parseDouble(String param) {
		return parseDouble(param, 1.0);
	}

	public static double parseDouble(String param, double t) {
		double tmp = 0.0;
		try {
			tmp = Double.parseDouble(param.trim());
		} catch (Exception e) {
			tmp = t;
		}
		return tmp;
	}

	public static boolean parseBoolean(String param) {
		if (empty(param))
			return false;
		switch (param.charAt(0)) {
			case 49: // '1'
			case 84: // 'T'
			case 89: // 'Y'
			case 116: // 't'
			case 121: // 'y'
				return true;

		}
		return false;
	}
	
	 /* public static String replace(String mainString, String oldString, String newString) 
	  { 
		  if(mainString == null) 
			  return null;
		  int i = mainString.lastIndexOf(oldString);
		  if(i < 0) 
			  return mainString;
		  StringBuffer mainSb = new StringBuffer(mainString); 
		  for(; i >= 0; i = mainString.lastIndexOf(oldString, i - 1)){mainSb.replace(i, i +oldString.length(), newString)};
	  
		  return mainSb.toString(); 
	  }*/


	public static boolean validateDouble(String str) throws RuntimeException {
		if (str == null)
			// throw new RuntimeException("null input");
			return false;
		char c;
		int k = 0;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				if (!(i == 0 && (c == '-' || c == '+')))
					if (!(c == '.' && i < l - 1 && k < 1))
						// throw new RuntimeException("invalid number");
						return false;
					else
						k++;
		}
		return true;
	}

	public static boolean validateMobile(String str, boolean includeUnicom) {
		if (str == null || str.trim().equals(""))
			return false;
		str = str.trim();

		if (str.length() != 11 || !validateInt(str))
			return false;

		if (includeUnicom
				&& (str.startsWith("130") || str.startsWith("133") || str.startsWith("131")))
			return true;

		if (!(str.startsWith("139") || str.startsWith("138") || str.startsWith("137")
				|| str.startsWith("136") || str.startsWith("135")))
			return false;
		return true;
	}

	public static boolean validateMobile(String str) {
		return validateMobile(str, false);
	}

	/**
	 * delete file
	 *
	 * @param fileName
	 * @return -1 exception,1 success,0 false,2 there is no one directory of the
	 *         same name in system
	 */
	public static int deleteFile(String fileName) {
		File file = null;
		int returnValue = -1;
		try {
			file = new File(fileName);
			if (file.exists())
				if (file.delete())
					returnValue = 1;
				else
					returnValue = 0;
			else
				returnValue = 2;

		} catch (Exception e) {
			System.out.println("Exception:e=" + e.getMessage());
		} finally {
			file = null;
			// return returnValue;
		}
		return returnValue;
	}

	public static String gbToIso(String s) throws UnsupportedEncodingException {
		return covertCode(s, "GB2312", "ISO8859-1");
	}

	public static String covertCode(String s, String code1, String code2)
			throws UnsupportedEncodingException {
		if (s == null)
			return null;
		else if (s.trim().equals(""))
			return "";
		else
			return new String(s.getBytes(code1), code2);
	}

	public static String transCode(String s0) throws UnsupportedEncodingException {
		if (s0 == null || s0.trim().equals(""))
			return null;
		else {
			s0 = s0.trim();
			return new String(s0.getBytes("GBK"), "ISO8859-1");
		}
	}
	/**
	 * 编码转换
	 * @param s
	 * @return
	 */
	public static String GBToUTF8(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str);
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str1.length(); j < 2; j++) {
					out.append("0");
				}

				out.append(str1);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	public static final String[] replaceAll(String[] obj, String oldString, String newString) {
		if (obj == null) {
			return null;
		}
		int length = obj.length;
		String[] returnStr = new String[length];
		String str = null;
		for (int i = 0; i < length; i++) {
			returnStr[i] = replaceAll(obj[i], oldString, newString);
		}
		return returnStr;
	}
	/**
	 * 字符串全文替换
	 * @param s0
	 * @param oldstr
	 * @param newstr
	 * @return
	 */
	public static String replaceAll(String s0, String oldstr, String newstr) {
		if (s0 == null || s0.trim().equals(""))
			return null;
		StringBuffer sb = new StringBuffer(s0);
		int begin = 0;
		// int from = 0;
		begin = s0.indexOf(oldstr);
		while (begin > -1) {
			sb = sb.replace(begin, begin + oldstr.length(), newstr);
			s0 = sb.toString();
			begin = s0.indexOf(oldstr, begin + newstr.length());
		}
		return s0;
	}

	public static String toHtml(String str) {
		String html = str;
		if (str == null || str.length() == 0) {
			return str;
		}
		html = replaceAll(html, "&", "&amp;");
		html = replaceAll(html, "<", "&lt;");
		html = replaceAll(html, ">", "&gt;");
		html = replaceAll(html, "\r\n", "\n");
		html = replaceAll(html, "\n", "<br>\n");
		html = replaceAll(html, "\t", "         ");
		html = replaceAll(html, " ", "&nbsp;");
		return html;
	}

	/**
	 * 字符串替换
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replace(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static final String replaceIgnoreCase(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 标签转义
	 * @param input
	 * @return
	 */
	public static final String escapeHTMLTags(String input) {
		// Check if the string is null or zero length -- if so, return
		// what was sent in.
		if (input == null || input.length() == 0) {
			return input;
		}
		// Use a StringBuffer in lieu of String concatenation -- it is
		// much more efficient this way.
		StringBuffer buf = new StringBuffer(input.length());
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.
	 * <p>
	 *
	 * For every character in the returned String, there is an equal chance that
	 * it will be a letter or number. If a letter, there is an equal chance that
	 * it will be lower or upper case.
	 * <p>
	 *
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 *
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */

	private static Random randGen = null;
	private static Object initLock = new Object();
	private static char[] numbersAndLetters = null;

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 固定长度字符串截取
	 * @param content
	 * @param i
	 * @return
	 */
	public static String getSubstring(String content, int i) {
		int varsize = 10;
		String strContent = content;
		if (strContent.length() < varsize + 1) {
			return strContent;
		} else {
			int max = (int) Math.ceil((double) strContent.length() / varsize);
			if (i < max - 1) {
				return strContent.substring(i * varsize, (i + 1) * varsize);
			} else {
				return strContent.substring(i * varsize);
			}
		}
	}

	/**
	 * 日期转String
	 * @param pattern 格式
	 * @return
	 */
	public static String now(String pattern) {
		return dateToString(new Date(), pattern);
	}

	public static String dateToString(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	public static synchronized String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * String转Date
	 * @param strDate 字符串日期
	 * @param pattern 格式
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String strDate, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(strDate);
		long lngTime = date.getTime();
		return new Date(lngTime);
	}

	public static Date stringToUtilDate(String strDate, String pattern)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(strDate);
	}

	public static String formatHTMLOutput(String s) {
		if (s == null)
			return null;

		if (s.trim().equals(""))
			return "";

		String formatStr;
		formatStr = replaceAll(s, " ", "&nbsp;");
		formatStr = replaceAll(formatStr, "\n", "<br />");

		return formatStr;
	}

	/*
	 * 4舍5入 @param num 要调整的数 @param x 要保留的小数位
	 */
	public static double myround(double num, int x) {
		BigDecimal b = new BigDecimal(num);
		return b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*
	 * 4舍5入  @param num 要调整的数 @param x 要保留的小数位
	 */
	public static String myround1(double num, int x){
		BigDecimal b = new BigDecimal(num);
		double f1 = b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(f1);
	}
	

	/*
	 * public static String getSubstring(String content,int i){ int varsize=10;
	 * String strContent=content; if(strContent.length()<varsize+1){ return
	 * strContent; }else{ int
	 * max=(int)Math.ceil((double)strContent.length()/varsize); if(i<max-1){
	 * return strContent.substring(i*varsize,(i+1)*varsize); }else{ return
	 * strContent.substring(i*varsize); } } }
	 */

	/**
	 * liuqs
	 *
	 * @param param
	 * @param d
	 * @return
	 */
	public static int parseLongInt(Long param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(String.valueOf(param));
		} catch (Exception e) {
		}
		return i;
	}

	public static int parseLongInt(Long param) {
		return parseLongInt(param, 0);
	}
	public static String returnString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 修改敏感字符编码
	 * @param value
	 * @return
	 */
	public static String htmlEncode(String value){
		String re[][] = {{"<","&lt;"},
				{">","&gt;"},
				{"\"","&quot;"},
				{"\\′","&acute;"},
				{"&","&amp;"}
		};

		for(int i=0; i<4; i++){
			value = value.replaceAll(re[i][0], re[i][1]);
		}
		return value;
	}
	/**
	 * 防SQL注入
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) {
		if(!empty(str)){
			String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
			String inj_stra[] = inj_str.split("\\|");
			for (int i=0 ; i < inj_stra.length ; i++ )
			{
				if (str.indexOf(inj_stra[i])>=0)
				{
					return true;
				}
			}
		}
		return false;
	}



	static String[] glcontext={"select","union","update","delete","insert",
			"into","'","creat","and","where","1=1","1=2","/*",
			"//","\\","///","/","\\\\","drop","lock table","grant","ascii",
			"count","chr","mid","master","truncate","%",";","or","+"};
	/**
	 *
	 * @Description: 防SQL注入    强行过滤 
	 * @param context
	 * @return
	 * @return String
	 * @throws
	 * @author gengxin.huang
	 * @date 2015年11月17日 下午4:53:53
	 */
	public static String getContext(String context){
		if(context==null)
		{
			return "";
		}
		context = context.trim();
		for(int i=0;i<glcontext.length;i++){
			if(context.indexOf(glcontext[i])!=-1){
				context = context.replace(glcontext[i],"");
			}
		}

		return context;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String test(float f){
		DecimalFormat df=new DecimalFormat("#0.000000000000");
		String str=df.format(f).toString();
		if(str.split("\\.")[1].equals("00")){
			str=str.split("\\.")[0];
		}
		return str;
	}


	/**
	 * 根据传入字符串判断小数，float，整数（未对传入字符进行校验，遗留默认传入格式为1233或者123.33 或者12321.11f等）
	 * @author Cloudy
	 *
	 */
	public static String test(String src){
		DecimalFormat df=new DecimalFormat("#0.00");
		//如果是小数
		if(Pattern.compile("[0-9]*(\\.)[0-9]*").matcher(src).find()){
			//判断为float类型
			if(Pattern.compile("\\.*[a-zA-Z]+\\.*").matcher(src).find()){
				float float_str=Float.valueOf(src);
				String str=df.format(float_str).toString();
				if(str.split("\\.")[1].equals("00")){
					str=str.split("\\.")[0];
				}
				return str;
			}
			else{
				double d_str=Double.parseDouble(src);
				String str_1=df.format(d_str).toString();
				if(str_1.split("\\.")[1].equals("00")){
					str_1=str_1.split("\\.")[0];
				}
				return str_1;
			}

		}

		else{
			float f_strr=Float.valueOf(src);
			String str_3=df.format(f_strr).toString();
			if(str_3.split("\\.")[1].equals("00")){
				str_3=str_3.split("\\.")[0];
			}
			return str_3;
		}
	}
	/**
	 * 提取SQL中的 ID 条件项
	 * @param sqlstr
	 * @return
	 */
	public static List<String> getFacSql(String sqlstr){
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("\\(\\d+(,\\d+)*\\)");
		Matcher m = p.matcher(sqlstr);
		while(m.find())
			list.add(m.group());
		return list.size()>0 ? list : null;
	}

	public static String getPercent(int x,int total){
		String result="";//接受百分比的值
		double x_double=x*1.0;
		double tempresult=x/total;
		//NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法
		//nf.setMinimumFractionDigits( 2 );        保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
		//result=nf.format(tempresult);
		result= df1.format(tempresult);
		return result;
	}

	//保留位数
	public static String accuracy1(double num,int scale){
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		//可以设置精确几位小数
		df.setMaximumFractionDigits(scale);
		//模式 例如四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(num);
	}

	//设置千分位 不设置小数位
	public static String accuracy1(double num){
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		//可以设置精确几位小数
		//df.setMaximumFractionDigits(scale);
		//模式 例如四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(num);
	}

	//计算概率
	public static String accuracy(double num, double total, int scale){
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		//可以设置精确几位小数
		df.setMaximumFractionDigits(scale);
		//模式 例如四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		double accuracy_num = num / total * 100;
		return df.format(accuracy_num);
	}

	//计算概率
	public static String accuracy(double num1, long num2, int scale){
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		//可以设置精确几位小数
		df.setMaximumFractionDigits(scale);
		//模式 例如四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		double accuracy_num = num1 / num2;
		return df.format(accuracy_num);
	}


	//Double转String
	public static String douToStr(Double s){
		if(s<=0){
			return "0.00";
		}
		DecimalFormat format = new DecimalFormat("#.00");
		String sMoney = format.format(s);
		return sMoney;
	}

	//获取随机数
	public static int code(int size){
		String[] arrays = {"0","1","2","3","4","5","6","7","8","9"};
		Random ran = new Random();
		int count = ran.nextInt(arrays.length),i = 0;
		String str="";
		while(i < size) {
			String tmp = arrays[ran.nextInt(arrays.length)];
			str+=tmp;
			i++;
		}
		return Integer.parseInt(str);
	}

	//获取两数中间的所有数 以字符串list返回
	public static List<String> getStrNumbetPara(String para1,String para2){
		List<String> strs = new ArrayList<String>();
		if(para1.equals(para2)){
			strs.add(para1);
			return strs;
		}else{
			for(int i=0;i<Integer.parseInt(para2)-Integer.parseInt(para1);i++){
				if((Integer.parseInt(para1)+i) <= Integer.parseInt(para2)){
					strs.add((Integer.parseInt(para1)+i)+"");
				}
			}
			strs.add(para2);
		}
		return strs;
	}

	public static String getIds(List<String> list){
		String ids = "";
		if(null!=list && list.size()>0){
			StringBuffer sb_ = new StringBuffer();
			for(String b : list)
				sb_.append("'"+b+"',");
			if(sb_.toString().length()>0)
				ids = sb_.toString().substring(0,sb_.toString().length()-1);
		}
		return ids;
	}

	private final static double PI = 3.14159265358979323; // 圆周率
	private final static double R = 6371229; // 地球的半径
	private final static double TR = 6378137.0; // 取WGS84标准参考椭球中的地球长半径(单位:m)

	/**
	 * 根据2坐标 计算距离
	 * Created by ziyu.zhang on 2016/4/28 15:29
	 * @Param
	 * @return  distance 单位米
	 */
	public static double getDistance(double longt1, double lat1, double longt2,double lat2) {
		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance;
	}
	public static double getDistance1(double lng1,double lat1,double lng2,double lat2) {
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(lng1) - Math.toRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
				* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * TR;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 根据经纬度 及距离计算 经度范围，维度范围
	 * Created by ziyu.zhang on 2016/4/28 15:26
	 * @Param lat 维度
	 * @Param lon 经度
	 * @Param raidus 距离 单位米
	 * @return double[]经纬度最大最小 范围
	 */
	public static double[] getAround(double lon,double lat,int raidus){

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901*1609)/360.0;
		double raidusMile = raidus;

		Double dpmLat = 1/degree;
		Double radiusLat = dpmLat*raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree*Math.cos(latitude * (PI/180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng*raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
		return new double[]{minLat,minLng,maxLat,maxLng};
	}


	/**
	 *
	 * @Description: 将查询动作 , 和值传入 ， 返回SQL条件
	 * @param searchOper
	 * @return
	 * @return String
	 * @throws
	 * @author gengxin.huang
	 * @date 2015年11月18日 下午2:33:27
	 */
	public static String getSearchSql(String searchOper , String searchName ,String searchValue){
		String searchParamSql="";
		switch(searchOper){
			case "LIKE":
				searchParamSql=" LIKE '%"+searchValue+"%' ";
				break;
			case "UNLIKE":
				searchParamSql=" NOT LIKE '%"+searchValue+"%' ";
				break;
			case "EQ":
				searchParamSql=" = '"+searchValue+"' ";
				break;
			case "UNEQ":
				searchParamSql=" <> '"+searchValue+"' ";
				break;
			case "NULL":
				searchParamSql="  AND ( t."+searchName+ " IS NULL OR t."+searchName+" = '' AND  t."+searchName+" != '0'  ) ";
				break;
			case "UNNULL":
				searchParamSql="  AND ( t."+searchName+" IS NOT NULL AND t."+searchName+" <> '') ";
				break;
			case "STARTEQ":
				searchParamSql=" LIKE '"+searchValue+"%' ";
				break;
			case "ENDEQ":
				searchParamSql=" LIKE '%"+searchValue+"' ";
				break;
			case "LT":
				searchParamSql=" < '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "LTE":
				searchParamSql=" <= '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "GT":
				searchParamSql=" > '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "GTE":
				searchParamSql=" >= '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "BEFORE":
				searchParamSql=" < '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "":
				searchParamSql=" < '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			case "AFTER":
				searchParamSql=" > '"+searchValue+"' AND t."+searchName+" <> '' ";
				break;
			default:
				searchParamSql="";
				break;
		}

		return searchParamSql;

	}
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	private static final String regEx_color = "(?:&#|#)\\d+";//定义css颜色代码



	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

		Pattern p_color = Pattern.compile(regEx_color, Pattern.CASE_INSENSITIVE);
		Matcher m_color = p_color.matcher(htmlStr);
		htmlStr = m_color.replaceAll(""); // 过滤空格回车标签


		return htmlStr.trim(); // 返回文本字符串
	}
	public static String getTextFromHtml(String htmlStr){
		htmlStr = delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp|$|&", "");
		return htmlStr;
	}
	/**
	 * Solr 检索特殊字符处理
	 * Created by ziyu.zhang on 2016/5/19 19:27
	 * @Param
	 * @return
	 */
	public static String escapeQueryChars(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
					|| c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
					|| c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
					|| Character.isWhitespace(c)) {
				sb.append('\\');
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 将数字转换为字符串(当不足位数时高位补0)
	 * @param number：当前数值；digit：位数
	 * @return
	 */
	public static String getSerialNumber(long number, int digit){
		String result = "" ;
		//大于位数时直接转换成字符串返回
		if((number+"").length() > digit){
			return result = number+"";
		}else{//根据位数的不同前边补不同的0
			int  length = (number +"").length();
			for(int i = (digit-length); i > 0; i--){
				result=result+"0";
			}
			return result + number;
		}
	}

	/**
	 * 科学计数转 数字字符串
	 * Created by ziyu.zhang on 2016/7/6 13:39
	 * @Param
	 * @return
	 */
	public static String plainString(String digit){
		BigDecimal bd = null;
		try {
			bd = new BigDecimal(digit);
		} catch (Exception e) {
		}
		if(bd==null){
			return "";
		}
		return bd.toPlainString();
	}

	/**
	 * 排除重复数据
	 * @param datas
	 * @return
	 */
	public static String excludeRepeatData(String[] datas){
		String dataString = "";
		Set<String> set = new HashSet<String>();
		for(String str:datas){
			if(str==null||"".equals(str)){
				continue;
			}
			set.add(str);
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			dataString += it.next()+",";
		}
		dataString = dataString.substring(0, dataString.length()-1);
		return dataString;
	}

	/**
	 * @param a String[]
	 * @return String[]
	 */
	public static String[] getSortOfChinese(String[] a) {
		Comparator cmp = Collator.getInstance(Locale.CHINA);
		Arrays.sort(a, cmp);
		return a;
	}

	/**
	 * 截取指定特殊字符之间的字符串，包含2端特殊字符
	 * @param content
	 * @return
	 */
	public static Set<String> matchStr(String content,Set<String> lists) {
		Pattern pattern = Pattern.compile("★☆⊙[\\s\\S]*?⊙☆★");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			lists.add(matcher.group());
		}
		return lists;
	}


	public static void main(String[] args) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code","200");
		jsonObject.put("message","成功！");
		Map<String,List<String>> maps = new HashMap<>();
		List<String> ids = new ArrayList<>();
		ids.add("明细ID1");
		ids.add("明细ID2");
		List<String> ids1 = new ArrayList<>();
		ids1.add("明细ID1");
		ids1.add("明细ID2");
		maps.put("消费单ID1",ids);
		maps.put("消费单ID2",ids1);

		JSONArray jsonArray1 = new JSONArray();
		JSONObject jsonObject0 = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();

		jsonObject1.put("id","123123");
		jsonObject1.put("argF2002","111");
		jsonObject1.put("argF1002","222");

		jsonObject2.put("id","123123");
		jsonObject2.put("argF2002","111");
		jsonObject2.put("argF1002","222");

		jsonArray1.add(jsonObject1);
		jsonArray1.add(jsonObject2);

		List<Object> list = new ArrayList<>();
		jsonObject0.put("id","消费单ID");
		jsonObject0.put("name","订单号");
		jsonObject0.put("argF2001","1222");
		jsonObject0.put("detail",jsonArray1);
		list.add(jsonObject0);

		jsonObject.put("data",list);
		System.out.println(jsonObject);

	}

}
