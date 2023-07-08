package com.javayige.status;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : Condition
 */
public interface Condition<C> {
    /**
     * 条件匹配
     *
     * @param context
     * @return
     */
    boolean isMatch(C context);
}