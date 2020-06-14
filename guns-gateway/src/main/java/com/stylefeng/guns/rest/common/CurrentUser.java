package com.stylefeng.guns.rest.common;

public class CurrentUser {

    //线程绑定的存储空间,InheritableThreadLocal:即使线程切换也可以取出
    private static final InheritableThreadLocal<String> threadLocal=new InheritableThreadLocal<>();

    //将用户id放入存储空间
    public static void saveUserInfo(String userId){
        threadLocal.set(userId);
    }

    //将用户id从存储空间取出
    public static String getCurrentUser(){
        return threadLocal.get();
    }
}
