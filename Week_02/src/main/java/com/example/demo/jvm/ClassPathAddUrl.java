package com.example.demo.jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathAddUrl {

    public static void main(String[] args) throws NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        URLClassLoader classLoader = (URLClassLoader) ClassPathAddUrl.class.getClassLoader();
        Method addUrl = URLClassLoader.class.getDeclaredMethod("addUrl");
        addUrl.setAccessible(true);
        URL url = new URL("C:\\dong\\hello.class");
        addUrl.invoke(classLoader,url);
        Class.forName("jvm.Hello");
    }
}
