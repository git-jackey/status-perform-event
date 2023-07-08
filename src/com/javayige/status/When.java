package com.javayige.status;

import com.javayige.PerformAction;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : When
 */
public interface When<S, E, C> {
    /**
     * WHEN
     *
     * @param action
     */
    void perform(PerformAction<S, E, C> action);
}
