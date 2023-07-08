package com.javayige.factory;

import com.javayige.builder.StateMachineBuilder;
import com.javayige.impl.StateMachineBuilderImpl;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateMachineBuilderFactory
 */
public class StateMachineBuilderFactory {
    public static <S, E, C> StateMachineBuilder<S, E, C> create() {
        return new StateMachineBuilderImpl<>();
    }
}
