package com.javayige.status;


/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : To
 */
public interface To<S, E, C> {
    /**
     * TO-ON
     *
     * @param event
     * @return
     */
    On<S, E, C> on(E event);
}
