package com.javayige.status;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : From
 */
public interface From<S, E, C> {
    /**
     * FROM - TO
     *
     * @param stateId
     * @return
     */
    To<S, E, C> to(S stateId);
}
