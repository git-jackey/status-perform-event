package com.javayige;

import java.util.List;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : State
 */
public interface State<S, E, C> {

    /**
     * 状态标识id
     *
     * @return
     */
    S getId();

    /**
     * 添加状态流转 on Event
     *
     * @param event
     * @param target
     * @return
     */
    Transition<S, E, C> addTransition(E event, State<S, E, C> target);

    List<Transition<S, E, C>> getEventTransitions(E event);
}
