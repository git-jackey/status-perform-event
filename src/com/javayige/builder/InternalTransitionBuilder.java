package com.javayige.builder;

import com.javayige.status.To;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : InternalTransitionBuilder
 */
public interface InternalTransitionBuilder<S, E, C> extends TransitionBuilder {
    /**
     * within
     *
     * @param stateId
     * @return
     */
    To<S, E, C> within(S stateId);

    @Override
    String name();
}
