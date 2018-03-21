package com.baihui.core.util.bean;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * Created by ziyu.zhang on 2016/4/1.
 * Description 运行时类修改器
 */
public class ClassPoolUtil {

    /**
     * 修改 类注解信息
     * @throws NotFoundException
     */
    public static void UpdateClassAnn(String tableName){
        try {
            ClassPool pool = ClassPool.getDefault();
            //获取需要修改的类
            CtClass ct = pool.get(tableName);
            ct.defrost();
            ClassFile classFile = ct.getClassFile();
            ConstPool constPool = classFile.getConstPool();
            // 获取运行时注解属性
            AnnotationsAttribute attribute = (AnnotationsAttribute)classFile.getAttribute(AnnotationsAttribute.visibleTag);
            Annotation tableAnnotation = new Annotation("org.springframework.data.mongodb.core.mapping.Document", constPool);
            tableAnnotation.addMemberValue("collection", new StringMemberValue(tableName, constPool));
            attribute.setAnnotation(tableAnnotation);
            //classFile.addAttribute(attribute);
            //classFile.setVersionToJava5();
            //classFile.prune();
            //ct.writeFile();

            //获取注解信息
           /* AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation("javax.persistence.PersistenceContext", cp);

            //修改名称为unitName的注解
            annotation.addMemberValue("unitName", new StringMemberValue("basic-entity", cp));
            attribute2.setAnnotation(annotation);
            minInfo.addAttribute(attribute2);*/



            //打印修改后方法
            /*AnnotationsAttribute attribute2 = (AnnotationsAttribute)classFile.getAttribute(AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation("org.springframework.data.mongodb.core.mapping.Document", constPool);
            Annotation annotation2 = attribute2.getAnnotation("org.springframework.data.mongodb.core.mapping.Document");
            String text = ((StringMemberValue)annotation2.getMemberValue("collection")).getValue();
            System.out.println("修改后的注解名称===" + text);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 当前ClassLoader中必须尚未加载该实体。（同一个ClassLoader加载同一个类只会加载一次）
        //c = clazz.toClass();
        //EntityClassLoader loader = new EntityClassLoader(ClassPoolUtils.class.getClassLoader());
        //c = clazz.toClass(loader , null);
       /* //获取注解信息*/


    }

    /**
     * 修改 属性注解信息
     * @throws NotFoundException
     */
    /*public static void UpdateFieldAnn() throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        //获取需要修改的类
        CtClass ct = pool.get(LoggerBean.class.getName());

        //获取类里的所有方法
        CtMethod[] cms = ct.getDeclaredMethods();
        CtMethod cm = cms[0];
        System.out.println("方法名称====" + cm.getName());

        MethodInfo minInfo = cm.getMethodInfo();
        //获取类里的em属性
        CtField cf = ct.getField("em");
        FieldInfo fieldInfo = cf.getFieldInfo();
        System.out.println("属性名称===" + cf.getName());

        ConstPool cp = fieldInfo.getConstPool();
        //获取注解信息
        AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("javax.persistence.PersistenceContext", cp);

        //修改名称为unitName的注解
        annotation.addMemberValue("unitName", new StringMemberValue("basic-entity", cp));
        attribute2.setAnnotation(annotation);
        minInfo.addAttribute(attribute2);

        //打印修改后方法
        Annotation annotation2 = attribute2.getAnnotation("javax.persistence.PersistenceContext");
        String text = ((StringMemberValue)annotation2.getMemberValue("unitName")).getValue();

        System.out.println("修改后的注解名称===" + text);
    }*/

    public static void main(String[] args) throws Exception {
       // UpdateClassAnn("201603");
        UpdateClassAnn("s_role");
    }

}
