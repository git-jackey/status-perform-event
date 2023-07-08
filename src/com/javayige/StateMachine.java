package com.javayige;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateMachine
 */
public interface StateMachine<S, E, C> {

    /**
     * 入口 StateMachine 启动Event
     *
     * @param sourceState
     * @param event
     * @param ctx
     * @return
     */
    S fireEvent(S sourceState, E event, C ctx);

    /**
     * id
     *
     * @return
     */
    String getMachineId();
}
