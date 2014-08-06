package com.gpzuestc.fundamentals.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
  
public class ReflectionUtils {   
    /**  
     * 得到某个对象的公共属性  
     *  
     * @param owner, fieldName  
     * @return 该属性对象  
     * @throws Exception  
     *  
     */  
    public static Object getProperty(Object owner, String fieldName) throws Exception {   
        Class ownerClass = owner.getClass();   
        Field field = ownerClass.getField(fieldName);   
        Object property = field.get(owner);   
        return property;   
    }   
    /**  
     * 得到某类的静态公共属性  
     *  
     * @param className   类名  
     * @param fieldName   属性名  
     * @return 该属性对象  
     * @throws Exception  
     */  
    public static Object getStaticProperty(String className, String fieldName)   
            throws Exception {   
        Class ownerClass = Class.forName(className);   
        Field field = ownerClass.getField(fieldName);   
        Object property = field.get(ownerClass);   
        return property;   
    }   
  
    /**  
     * 执行某对象方法  
     *  
     * @param owner  
     *            对象  
     * @param methodName  
     *            方法名  
     * @param args  
     *            参数  
     * @return 方法返回值  
     * @throws Exception  
     */  
    public static Object invokeMethod(Object owner, String methodName, Object[] args)   
            throws Exception {   
        Class ownerClass = owner.getClass();   
        Class[] argsClass = new Class[args.length];   
        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();   
        }   
        Method method = ownerClass.getMethod(methodName, argsClass);   
        return method.invoke(owner, args);   
    }   
  
      /**  
     * 执行某类的静态方法  
     *  
     * @param className  
     *            类名  
     * @param methodName  
     *            方法名  
     * @param args  
     *            参数数组  
     * @return 执行方法返回的结果  
     * @throws Exception  
     */  
    public static Object invokeStaticMethod(String className, String methodName,   
            Object[] args) throws Exception {   
        Class ownerClass = Class.forName(className);   
        Class[] argsClass = new Class[args.length];   
        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();   
        }   
        Method method = ownerClass.getMethod(methodName, argsClass);   
        return method.invoke(null, args);   
    }   
    /**  
     * 新建实例  
     *  
     * @param className  
     *            类名  
     * @param args  
     *            构造函数的参数  
     * @return 新建的实例  
     * @throws Exception  
     */  
    public static Object newInstance(String className, Object[] args) throws Exception {   
        Class newoneClass = Class.forName(className);   
        Class[] argsClass = new Class[args.length];   
        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();   
        }   
        Constructor cons = newoneClass.getConstructor(argsClass);   
        return cons.newInstance(args);   
    }   
  
       
    /**  
     * 是不是某个类的实例  
     * @param obj 实例  
     * @param cls 类  
     * @return 如果 obj 是此类的实例，则返回 true  
     */  
    public static boolean isInstance(Object obj, Class cls) {   
        return cls.isInstance(obj);   
    }   
       
    /**  
     * 得到数组中的某个元素  
     * @param array 数组  
     * @param index 索引  
     * @return 返回指定数组对象中索引组件的值  
     */  
    public static Object getByArray(Object array, int index) {   
        return Array.get(array,index);   
    }   
    
    /* 
     * 获取方法参数名 
     */  
    public static String[] getMethodVariableNames(String targetClass, String targetMethodName) {  
        Class<?> clazz = null;  
        try {  
            clazz = Class.forName(targetClass);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
        ClassPool pool = ClassPool.getDefault();  
        pool.insertClassPath(new ClassClassPath(clazz));  
        CtClass cc;  
        CtMethod cm = null;  
        try {  
            cc = pool.get(clazz.getName());  
            cm = cc.getDeclaredMethod(targetMethodName);  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
      
        // 使用javaassist的反射方法获取方法的参数名  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
        String[] variableNames = new String[0];  
        try {  
            variableNames = new String[cm.getParameterTypes().length];  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
        int staticIndex = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < variableNames.length; i++)  
            variableNames[i] = attr.variableName(i + staticIndex);  
        return variableNames;  
    }  
}  
