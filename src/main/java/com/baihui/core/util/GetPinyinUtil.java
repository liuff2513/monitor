package com.baihui.core.util;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 
 * @author ziyu.zhang
 * @Description 汉字到拼音的处理类
 */
public class GetPinyinUtil {

    /**
     * 得到 全拼
     * 
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母
     * 
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
    
    /**
     * 验证是否包含中文
     */
    public static boolean containsChinese(String s) {
        if ((s == null) || ("".equals(s.trim())))
          return false;
        for (int i = 0; i < s.length(); ++i)
          if (isChinese(s.charAt(i)))
            return true;

        return false;
    }

	public static boolean isChinese(char a) {
	    int v = a;
	    return ((v >= 19968) && (v <= 171941));
	}

	/**
	 * 小于6个字符的字符串得到中文首字母 
	 */
	public static String getDocFieldValByPinyin(String str){
		return str;
		
		/*if(str.length()>6)
    		return str;
    	if(!containsChinese(str))
    		return str;
    	return str+" "+getPinYinHeadChar(str);*/
	}
	

    /**
     * 将字符串转移为ASCII码
     * 
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }
    public static String GetPyString(String chinese) throws BadHanyuPinyinOutputFormatCombination{
	    HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
	    String chineseTerm;
	    boolean isFirstChar = true;
	    if (isFirstChar) {
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < chinese.length(); ++i) {
	        String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), outputFormat);
	        if (array != null) { if (array.length == 0)
	            continue;
	
	          String s = array[0];
	          char c = s.charAt(0);
	
	          sb.append(c); }
	      }
	      chineseTerm = sb.toString();
	    } else {
	      chineseTerm = PinyinHelper.toHanyuPinyinString(chinese, outputFormat, "");
	    }
	    return chineseTerm;
   }

    public static void main(String[] args) {
        System.out.println("----------"+getPingYin("行业"));
    }

}