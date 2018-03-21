package com.baihui.core.util.validate;

import com.baihui.core.util.date.DateUtil;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: RegexValidateUtil
 * 
 * @Description: 数据正则验证工具类
 * @author feifei.liu
 * @date 2016年3月28日 下午2:01:03
 */
public class RegexValidateUtil {
	public static void main(String[] args) {
//		System.out.println(checkEmail("liuff2513@163.cn"));
		System.out.println(checkDecimal("0.123143"));
//		System.out.println(checkNumber("11111111111111111111111111111111111111111111111111"));
//		System.out.println(checkURL("http://1.2.3"));
//		System.out.println(checkDateTime("2015-05-21 12:12:12:0000"));
	//	System.out.println(RegexValidateUtil.checkMobilePhone("15311978870"));
//		System.out.println(RegexValidateUtil.checkQQ("12345"));
//		System.out.println(RegexValidateUtil.checkPercent(""));
	}
	static boolean flag = false;

	public static boolean check(String str, String regex) {
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * @Description: 数值验证
	 * @param number
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午4:54:26
	 */
	
	
	public static boolean checkNumber(String number) {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	/**
	 * @Description: 百分数验证
	 * @param percent
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午4:41:38
	 */
	public static boolean checkPercent(String percent) {
		///^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]|[0-9]{2}))?$/
//		String regex = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
		String regex = "^[0-9]{1,16}+(\\.[0-9]{1,2})?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(percent);
		return matcher.matches();
	}
	
	/**
	 * @Description: 验证百分比(针对表单)
	 * @param percent
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author yong.lou
	 * @date 2017年4月19日 下午1:31:44
	 */
	public static boolean checkPercentForWebForm(String percent) {
		String regex = "^[0-9]+\\.{0,1}[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(percent);
		return matcher.matches();
	}
	
	/**
	 * @Description: 小数验证
	 * @param decimal
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午4:32:36
	 */
	public static boolean checkDecimal(String decimal) {
		String regex = "^(-)?[0-9]+\\.{0,1}[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(decimal);
		return matcher.matches();
	}
	
	
	/**
	 * @Description: 长整形验证
	 * @param longnumber
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author yong.lou
	 * @date 2017年4月19日 上午11:25:21
	 */
	public static boolean checkLongnumber(String longnumber){
		String regex = "^[0-9]*[1-9][0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(longnumber);
		return matcher.matches();
	}
	
	/**
	 * @Description: 货币验证
	 * @param currency
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author yong.lou
	 * @date 2017年4月19日 上午11:20:01
	 */
	public static boolean checkCurrency(String currency){
		String regex = "^[\\-\\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\\.]([0-9]+))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher =  pattern.matcher(currency);
		return matcher.matches();
	}
	
	/**
	 * @Description: 验证日期或时间(针对web表单)
	 * @param date
	 * @param format
	 * @return   
	 * @return String  
	 * @throws
	 * @author yong.lou
	 * @date 2017年4月19日 上午11:57:44
	 */
	public static String checkDateOrTimeAndReturn(String date,String format){
		try{
			final SimpleDateFormat df = new SimpleDateFormat(format);
			final ParsePosition parserP = new ParsePosition(0);
			final Date result = df.parse(date,parserP);
			if(parserP.getIndex()!=0 && result != null){
				return date;
			}
		}catch(Exception e){
		}
		return "";
	}

	/**
	 * @Description: 邮箱验证
	 * @param email
	 * @return
	 * @return boolean
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午2:00:41
	 */
	public static boolean checkEmail(String email) {
		String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * @Description: URL验证
	 * @param URL
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年4月8日 下午2:11:27
	 */
	public static boolean checkURL(String URL) {
//		String regex = "/^((https?|ftp):\\/\\/)?(((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";
		String regex  = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(URL);
		return matcher.matches();
	}
	
	/**
	 * @Description: 手机号验证
	 * @param mobilePhone
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午2:03:38
	 */
	public static boolean checkMobilePhone(String mobilePhone) {
		//移动：134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|147|1705   --147上网卡、1705虚拟运营商专属号码
		//联通：130|131|132|155|156|185|186|176|145|1709    --145上网卡、1709虚拟运营商专属号码
		//电信：133|153|180|181|189|173|177|1700|1349   --1700虚拟运营商专属号码、1349卫星通信
		String regex = "^(134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|147|1705|130|131|132|155|156|185|186|176|145|1709|133|153|180|181|189|173|177|1700|1349)[0-9]{8}$";
//		String regex = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobilePhone);
		return matcher.matches();
	}
	
	/**
	 * @Description: 电话号码验证
	 * @param phone
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年7月13日 下午6:05:28
	 */
	public static boolean checkPhone(String phone) {
		String regex = "^([\\+][0-9]{1,3}[\\.\\-])?([\\(]{1}[0-9]{2,6}[\\)])?([0-9 \\.\\-\\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	/**
	 * @Description: 日期/日期时间验证
	 * @param dateValue
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年4月9日 下午1:55:06
	 */
	public static boolean checkDateTime(String dateValue) {
		try {//捕获转换异常（出现异常则说明不符合格式，返回false）
			final String localDateFormats[] = DateUtil.getDatePatterns();
			String v = dateValue;
			if (v.length() > 1 && v.startsWith("'") && v.endsWith("'")) {
				v = v.substring (1, v.length() - 1);
			}
			for (final String dateFormat : localDateFormats) {
				final SimpleDateFormat dateParser = new SimpleDateFormat(dateFormat);
				final ParsePosition pos = new ParsePosition(0);
				final Date result = dateParser.parse(v, pos);
				if (pos.getIndex() != 0&&result!=null) {
					return true;
				}
			}
		} catch (Exception e) {
			 return false;
		}
        return false;
	}
	
	/**
	 * @Description: 身份证号验证
	 * @param idnumber
	 * @return   
	 * @return boolean  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月28日 下午4:20:09
	 */
	public static boolean checkIdnumber(String idnumber) {
		// 15位和18位身份证号码的正则表达式
		String regex = "^[0-9]{15}|[0-9]{18}$";
		// 如果通过该验证，说明身份证格式正确，但准确性还需计算
		if (Pattern.matches(regex, idnumber)) {
			if (idnumber.length() == 18) {
				int[] idCardWi = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
						9, 10, 5, 8, 4, 2 };// 将前17位加权因子保存在数组里
				int[] idCardY = new int[] { 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };// 这是除以11后，可能产生的11位余数、验证码，也保存成数组
				int idCardWiSum = 0;// 用来保存前17位各自乖以加权因子后的总和
				for (int i = 0; i < 17; i++) {
					idCardWiSum += (Integer.valueOf(idnumber.substring(i, i + 1)) * idCardWi[i]);
				}
				int idCardMod = idCardWiSum % 11;// 计算出校验码所在数组的位置
				String idCardLast = idnumber.substring(17); // 得到最后一位身份证号码
				// 如果等于2，则说明校验码是10，身份证号码最后一位应该是X
				if (idCardMod == 2) {
					if ("X".equals(idCardLast) || "x".equals(idCardLast))
						return true;
					return false;
				} else {
					// 用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
					if (String.valueOf(idCardY[idCardMod]).equals(idCardLast))
						return true;
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * 验证非空
	 * 
	 * @param notEmputy
	 * @return
	 */
	public static boolean checkNotEmputy(String notEmputy) {
		 String regex = "^\\s*$";
		 return check(notEmputy, regex) ? false : true;
    }

	/**
	 * 验证手机号码
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145 电信号码段:133、153、180、189
	 * 
	 * @param cellphone
	 * @return
	 */
	public static boolean checkCellphone(String cellphone) {
		 String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$"; 
		 return check(cellphone, regex);
    }

	/**
	 * 验证固话号码
	 * 
	 * @param telephone
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		 String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		 return  check(telephone, regex);
    }

	/**
	 * 验证传真号码
	 * 
	 * @param fax
	 * @return
	 */
	public static boolean checkFax(String fax) {
		 String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$"; 
		 return check(fax, regex);
    }

	/**
	 * 验证QQ号码
	 * 
	 * @param QQ
	 * @return
	 */
	public static boolean checkQQ(String QQ) {
		String regex = "^[1-9][0-9]{3,10}$";
		return check(QQ, regex);
	}
}
