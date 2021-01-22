package com.example.demo.jvm;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;

public class PrintClassLoadPath {

    //-XX:+TranceClassLoading 或者 -verbose 查看类加载顺序
    //-Dsun.boot.class.path 自定义加载那个jar包
    //-Djava.ext.dirs 自定义扩展类加载的东西

    public static void main(String[] args) {

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载工具");
        for(URL ur: urLs){
            System.out.println("启动类加载工具===="+ur.toExternalForm());
        }
        //扩展类加载工具
        ClassLoader parent = PrintClassLoadPath.class.getClassLoader().getParent();
        printClassLoader("扩展类加载工具",parent);
        //应用类加载工具
        ClassLoader classLoader = PrintClassLoadPath.class.getClassLoader();
        printClassLoader("应用类加载工具",classLoader);
    }

    public static void printClassLoader(String name,ClassLoader cl){
        if(Objects.nonNull(cl)){
            System.out.println(name+"================>"+cl.toString());
            printClassLoaderPath(cl);
        }else{
            System.out.println(name+"================>null");
            printClassLoaderPath(cl);
        }
    }

    public static void printClassLoaderPath(ClassLoader cl){
        Object ucp = getField(cl,"ucp");
        Object path = getField(ucp, "path");
        ArrayList pathArr = (ArrayList) path;
        for(Object pat : pathArr){
            System.out.println("===================>"+pat);
        }
    }

    public static Object getField(Object obj,String field){
        Field f = null;
        if(obj instanceof URLClassLoader){
            try {
                f = URLClassLoader.class.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }else{
            try {
                f =obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        f.setAccessible(true);
        try {
            return f.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
