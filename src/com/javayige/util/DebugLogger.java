package com.javayige.util;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : Debugger
 */
public class DebugLogger {

    private static boolean isDebugOn = false;

    //调试
    public static void debug(String message) {
        if (isDebugOn) {
            System.out.println(message);
        }
    }

    public static void enableDebug() {
        isDebugOn = true;
    }
}
