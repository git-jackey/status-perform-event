package com.javayige.context;

import com.javayige.StateMachine;
import com.javayige.Transition;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateContext
 */
public interface StateContext<S, E, C> {
    /**
     * 获取流转进态
     *
     * @return
     */
    Transition<S, E, C> getTransition();

    /**
     * 上下文fetch 状态机
     *
     * @return
     */
    StateMachine<S, E, C> getStateMachine();
}
