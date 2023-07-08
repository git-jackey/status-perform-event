package com.javayige;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : PerformAction
 */
public interface PerformAction<S, E, C> {
    /**
     * @param from    source status
     * @param to      target status
     * @param event   事件
     * @param context 上下文
     */
    void execute(S from, S to, E event, C context);
}
