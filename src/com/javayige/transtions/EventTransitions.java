package com.javayige.transtions;


import com.javayige.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : EventTransitions
 */
public class EventTransitions<S, E, C> {
    private HashMap<E, List<Transition<S, E, C>>> eventTransitions;

    public EventTransitions() {
        eventTransitions = new HashMap<>();
    }

    public void put(E event, Transition<S, E, C> transition) {
        if (eventTransitions.get(event) == null) {
            List<Transition<S, E, C>> transitions = new ArrayList<>();
            transitions.add(transition);
            eventTransitions.put(event, transitions);
        } else {
            List existingTransitions = eventTransitions.get(event);
            verify(existingTransitions, transition);
            existingTransitions.add(transition);
        }
    }

    private void verify(List<Transition<S, E, C>> existingTransitions, Transition<S, E, C> newTransition) {
        for (Transition transition : existingTransitions) {
            if (transition.equals(newTransition)) {
                throw new RuntimeException(transition + " 已经存在");
            }
        }
    }

    public List<Transition<S, E, C>> get(E event) {
        return eventTransitions.get(event);
    }
}
