package com.baihui.core.util.bean;


import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Method;

/**
 * ClassName: BeanUtil
 * Description: //TODO
 * Created by feifei.liu on 2017/7/6 10:49
 **/
public class BeanUtil extends BeanUtils {
    /**
     * @Description: 根据实体类获取对应表名
     * @param clazz
     * @return
     * @return String
     * @throws
     * @author feifei.liu
     * @date 2015年9月12日 下午2:19:44
     */
    public static String getTableName(Class<?> clazz){
        return clazz.getAnnotation(Table.class).name();
    }

    /**
     * @Description: 根据实体类和字段名获取对应表字段名
     * @param clazz
     * @param fieldName
     * @return
     * @return String
     * @throws
     * @author feifei.liu
     * @date 2016年2月27日 下午5:37:04
     */
    public static String getDbFieldName(Class<?> clazz, String fieldName) {
        try {
            Method method = clazz.getMethod("get"+(fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1)));
            Column column = method.getAnnotation(Column.class);
            if(column!=null&&column.name()!=null) return column.name();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
