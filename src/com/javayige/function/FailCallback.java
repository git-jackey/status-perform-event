package com.javayige.function;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : FailCallback
 */
@FunctionalInterface
public interface FailCallback<S, E, C> {
    /**
     * 失败处理
     *
     * @param sourceState
     * @param event
     * @param context
     */
    void onFail(S sourceState, E event, C context);
}
