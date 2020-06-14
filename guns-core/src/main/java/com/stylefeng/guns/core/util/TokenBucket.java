package com.stylefeng.guns.core.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenBucket {

    private static int bucketNums=100;
    private static int rate=1;
    private static int nowTokens;
    private static long timestamp=getNowTime();

    private static long getNowTime(){
        return System.currentTimeMillis();
    }

    private static int min(int tokens){
        if (bucketNums>tokens){
            return tokens;
        }else {
            return bucketNums;
        }
    }

    public static boolean getToken(){
        long nowTime=getNowTime();
        nowTokens=nowTokens+(int)((nowTime-timestamp)*rate);
        nowTokens=min(nowTokens);
        log.info("当前令牌数量:"+nowTokens);
        timestamp=nowTime;
        if (nowTokens<1){
            return false;
        }else {
            nowTokens-=1;
            return true;
        }
    }
}
