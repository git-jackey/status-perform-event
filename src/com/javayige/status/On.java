package com.javayige.status;


/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : On
 */
public interface On<S, E, C> extends When<S, E, C> {
    /**
     * ON-WHEN
     *
     * @param condition
     * @return
     */
    When<S, E, C> when(Condition<C> condition);
}
