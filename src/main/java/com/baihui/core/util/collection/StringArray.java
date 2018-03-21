package com.baihui.core.util.collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 处理String数组的工具类
 * @author changmeng.wang
 * @date 2016/12/12 16:00
 */
public class StringArray {

    /**
     * @Description:并集（set唯一性）
     * @param arr1 数组
     * @param arr2 数组
     * @return
     * @throws 
     * @author changmeng.wang
     * @date 2016/12/12 16:00
     */
    public static String[] union (String[] arr1, String[] arr2){
        Set<String> hs = new HashSet<String>();
        for(String str:arr1){
            hs.add(str);
        }
        for(String str:arr2){
            hs.add(str);
        }
        String[] result={};
        return hs.toArray(result);
    }

    /**
     * @Description: 交集(注意结果集中若使用LinkedList添加，则需要判断是否包含该元素，否则其中会包含重复的元素)
     * @param arr1 数组
     * @param arr2 数组
     * @return 
     * @throws 
     * @author changmeng.wang
     * @date 2016/12/12 16:01
     */
    public static String[] intersect(String[] arr1, String[] arr2){
        List<String> l = new LinkedList<String>();
        Set<String> common = new HashSet<String>();
        for(String str:arr1){
            if(!l.contains(str)){
                l.add(str);
            }
        }
        for(String str:arr2){
            if(l.contains(str)){
                common.add(str);
            }
        }
        String[] result={};
        return common.toArray(result);
    }
    /**
     * @Description: 求两个数组的差集
     * @param arr1 数组
     * @param arr2 数组
     * @return
     * @throws
     * @author changmeng.wang
     * @date 2016/12/12 16:01
     */
    public static String[] substract(String[] arr1, String[] arr2) {
        LinkedList<String> list = new LinkedList<String>();
        for (String str : arr1) {
            if(!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : arr2) {
            if (list.contains(str)) {
                list.remove(str);
            }
        }
        String[] result = {};
        return list.toArray(result);
    }
    /**
     * @Description:把list集合转变为string数组
     * @param list 集合
     * @return 
     * @throws 
     * @author changmeng.wang
     * @date 2016/12/12 17:02
     */
    public static String[] toArr(List<Object> list){
        int size = list.size();
        String[] arr = new String[size];
        for(int i=0; i<size; i++){
            arr[i]=list.get(i)==null?"":String.valueOf(list.get(i));
        }
        return arr;
    }
    /**
     * @Description:集合中的元素用逗号分割成字符串
     * @param <S> list 集合
     * @return 
     * @throws 
     * @author changmeng.wang
     * @date 2016/12/13 14:33
     */
    public static <S> String getStringSeparatedByComma(List<S> list){
        if(list!=null && list.size()>0){
            StringBuffer sb = new StringBuffer();
            for(int i=0,size=list.size(); i<size; i++){
                if(i>0)
                    sb.append(",");
                sb.append(list.get(i));
            }
            return sb.toString();
        }
        return "";
    }
}