package com.javayige.impl;

import com.javayige.status.Condition;
import com.javayige.PerformAction;
import com.javayige.State;
import com.javayige.Transition;
import com.javayige.status.On;
import com.javayige.status.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : TransitionsBuilderImpl
 */
public class TransitionsBuilderImpl<S, E, C> extends TransitionBuilderImpl<S, E, C> {

    private List<State<S, E, C>> sources = new ArrayList<>();

    private List<Transition<S, E, C>> transitions = new ArrayList<>();

    public TransitionsBuilderImpl(Map<S, State<S, E, C>> stateMap) {
        super(stateMap);
    }


    @Override
    public On<S, E, C> on(E event) {
        for (State source : sources) {
            Transition transition = source.addTransition(event, super.target);
            transitions.add(transition);
        }
        return this;
    }

    @Override
    public When<S, E, C> when(Condition<C> condition) {
        for (Transition transition : transitions) {
            transition.setCondition(condition);
        }
        return this;
    }

    @Override
    public void perform(PerformAction<S, E, C> action) {
        for (Transition transition : transitions) {
            transition.setAction(action);
        }
    }
}
