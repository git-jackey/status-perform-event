package com.javayige.builder;


import com.javayige.function.FailCallback;
import com.javayige.StateMachine;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateMachineBuilder
 */
public interface StateMachineBuilder<S, E, C> {
    /**
     * 外部流转
     *
     * @return
     */
    ExternalTransitionBuilder<S, E, C> externalTransition();

    /**
     * 内联流转
     *
     * @return
     */
    InternalTransitionBuilder<S, E, C> internalTransition();

    /**
     * 异常回调
     *
     * @param callback
     */
    void setFailCallback(FailCallback<S, E, C> callback);

    /**
     * 构建状态机
     *
     * @param machineId
     * @return
     */
    StateMachine<S, E, C> build(String machineId);

}
