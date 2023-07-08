package com.javayige.impl;


import com.javayige.builder.ExternalTransitionBuilder;
import com.javayige.builder.InternalTransitionBuilder;
import com.javayige.status.Condition;
import com.javayige.PerformAction;
import com.javayige.State;
import com.javayige.Transition;
import com.javayige.util.StateUtils;
import com.javayige.status.From;
import com.javayige.status.On;
import com.javayige.status.To;
import com.javayige.status.When;

import java.util.Map;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : TransitionBuilderImpl
 */
class TransitionBuilderImpl<S, E, C> implements ExternalTransitionBuilder<S, E, C>, InternalTransitionBuilder<S, E, C>, From<S, E, C>, On<S, E, C>, To<S, E, C> {

    final Map<S, State<S, E, C>> stateMap;

    private State<S, E, C> source;

    protected State<S, E, C> target;

    private Transition<S, E, C> transition;

    public TransitionBuilderImpl(Map<S, State<S, E, C>> stateMap) {
        this.stateMap = stateMap;
    }

    @Override
    public From<S, E, C> from(S stateId) {
        source = StateUtils.getState(stateMap, stateId);
        return this;
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public To<S, E, C> to(S stateId) {
        target = StateUtils.getState(stateMap, stateId);
        return this;
    }

    @Override
    public To<S, E, C> within(S stateId) {
        source = target = StateUtils.getState(stateMap, stateId);
        return this;
    }

    @Override
    public When<S, E, C> when(Condition<C> condition) {
        transition.setCondition(condition);
        return this;
    }

    @Override
    public On<S, E, C> on(E event) {
        transition = source.addTransition(event, target);
        return this;
    }

    @Override
    public void perform(PerformAction<S, E, C> action) {
        transition.setAction(action);
    }
}
