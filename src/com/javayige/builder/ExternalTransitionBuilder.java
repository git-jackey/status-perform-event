package com.javayige.builder;

import com.javayige.status.From;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : ExternalTransitionBuilder
 */
public interface ExternalTransitionBuilder<S, E, C> extends TransitionBuilder {
    /**
     * from
     *
     * @param stateId
     * @return
     */
    From<S, E, C> from(S stateId);

    @Override
    String name();
}
