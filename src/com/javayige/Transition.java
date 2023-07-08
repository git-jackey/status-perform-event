package com.javayige;


import com.javayige.status.Condition;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : Transition
 */
public interface Transition<S, E, C> {
    /**
     * 获取Source
     *
     * @return
     */
    State<S, E, C> getSource();

    void setSource(State<S, E, C> state);

    E getEvent();

    void setEvent(E event);

    /**
     * 获取Target
     *
     * @return
     */
    State<S, E, C> getTarget();

    void setTarget(State<S, E, C> state);

    Condition<C> getCondition();

    void setCondition(Condition<C> condition);

    PerformAction<S, E, C> getAction();

    void setAction(PerformAction<S, E, C> action);

    State<S, E, C> transit(C ctx, boolean checkCondition);
}
