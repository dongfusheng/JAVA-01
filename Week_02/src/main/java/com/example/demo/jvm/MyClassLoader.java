package com.example.demo.jvm;

import java.util.Base64;

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            new MyClassLoader().findClass("jvm.Hello").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String byteStr = "sfnjdngloierj99821u39rgfkdsdkanfnlksndjfn;dsvdfjhtwyfsdvcndkfjnkjsalnldf dllksfml";
        byte[] decode = Base64.getDecoder().decode(byteStr);
        return defineClass(name,decode,0,decode.length);
    }

}
