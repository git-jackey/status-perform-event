package com.javayige.impl;

import com.javayige.function.FailCallback;
import com.javayige.State;
import com.javayige.StateMachine;
import com.javayige.builder.ExternalTransitionBuilder;
import com.javayige.builder.InternalTransitionBuilder;
import com.javayige.builder.StateMachineBuilder;
import com.javayige.factory.StateMachineFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateMachineBuilderImpl
 */
public class StateMachineBuilderImpl<S, E, C> implements StateMachineBuilder<S, E, C> {

    private final Map<S, State<S, E, C>> stateMap = new ConcurrentHashMap<>();
    private final StateMachineImpl<S, E, C> stateMachine = new StateMachineImpl<>(stateMap);
    private FailCallback<S, E, C> failCallback = (sourceState, event, context) -> {

    };

    @Override
    public ExternalTransitionBuilder<S, E, C> externalTransition() {
        return new TransitionBuilderImpl<>(stateMap);
    }

    @Override
    public InternalTransitionBuilder<S, E, C> internalTransition() {
        return new TransitionBuilderImpl<>(stateMap);
    }

    @Override
    public void setFailCallback(FailCallback<S, E, C> callback) {
        this.failCallback = callback;
    }

    @Override
    public StateMachine<S, E, C> build(String machineId) {
        stateMachine.setMachineId(machineId);
        stateMachine.setReady(true);
        stateMachine.setFailCallback(failCallback);
        StateMachineFactory.register(stateMachine);
        return stateMachine;
    }
}
