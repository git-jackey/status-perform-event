package com.javayige.impl;

import com.javayige.State;
import com.javayige.Transition;
import com.javayige.transtions.EventTransitions;

import java.util.List;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateImpl
 */
public class StateImpl<S, E, C> implements State<S, E, C> {

    protected final S stateId;

    private EventTransitions eventTransitions = new EventTransitions();

    public StateImpl(S stateId) {
        this.stateId = stateId;
    }

    @Override
    public List<Transition<S, E, C>> getEventTransitions(E event) {
        return eventTransitions.get(event);
    }

    @Override
    public S getId() {
        return stateId;
    }

    @Override
    public Transition<S, E, C> addTransition(E event, State<S, E, C> target) {
        Transition<S, E, C> newTransition = new TransitionImpl<>();
        newTransition.setSource(this);
        newTransition.setTarget(target);
        newTransition.setEvent(event);
        eventTransitions.put(event, newTransition);
        return newTransition;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof State) {
            State other = (State) anObject;
            if (this.stateId.equals(other.getId()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return stateId.toString();
    }
}
