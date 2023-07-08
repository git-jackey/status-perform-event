package com.javayige.client;


import com.javayige.status.Condition;
import com.javayige.PerformAction;
import com.javayige.StateMachine;
import com.javayige.factory.StateMachineFactory;
import com.javayige.callback.AlertFailCallback;
import com.javayige.builder.StateMachineBuilder;
import com.javayige.factory.StateMachineBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author : orangeCy.奕
 * @Product : 猎豹养成产品源码
 * @Time : 2023/7/8
 * @Desc : StateMachineTest
 */
public class StateMachineTest {

    static String MACHINE_ID = "TestStateMachine";

    enum States {
        STATE1,
        STATE2,
        STATE3,
    }

    enum Events {
        EVENT1,
        EVENT2,
        EVENT3,
        INTERNAL_EVENT
    }

    static class Context {
        String operator = "java_yg";
        String entityId = "123";

        private String condition;

        public Context() {
        }

        public Context(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }
    }

    @Test
    public void testExternalNormal() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID);
        States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());
        Assert.assertEquals(States.STATE2, target);
    }

    @Test
    public void testFail() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        builder.setFailCallback(new AlertFailCallback<>());

        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID + "-testFail");
        Assert.assertThrows(RuntimeException.class,
                () -> stateMachine.fireEvent(States.STATE2, Events.EVENT1, new Context()));
    }


    @Test
    public void testInternalNormal() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(States.STATE1)
                .on(Events.INTERNAL_EVENT)
                .when(checkCondition())
                .perform(doAction());
        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID + "2");

        stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());
        States target = stateMachine.fireEvent(States.STATE1, Events.INTERNAL_EVENT, new Context());
        Assert.assertEquals(States.STATE1, target);
    }

    @Test
    public void testExternalInternalNormal() {
        StateMachine<States, Events, Context> stateMachine = buildStateMachine("testExternalInternalNormal");

        Context context = new Context();
        States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, context);
        Assert.assertEquals(States.STATE2, target);
        target = stateMachine.fireEvent(States.STATE2, Events.INTERNAL_EVENT, context);
        Assert.assertEquals(States.STATE2, target);
        target = stateMachine.fireEvent(States.STATE2, Events.EVENT2, context);
        Assert.assertEquals(States.STATE1, target);
        target = stateMachine.fireEvent(States.STATE1, Events.EVENT3, context);
        Assert.assertEquals(States.STATE3, target);
    }

    @Test
    public void testChoice() {
        StateMachineBuilder<StateMachineTest.States, StateMachineTest.Events, Context> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(StateMachineTest.States.STATE1)
                .on(StateMachineTest.Events.EVENT1)
                .when((ctx) -> "1".equals(ctx.getCondition()))
                .perform((from, to, event, ctx) -> System.out.println("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getCondition()));
        builder.externalTransition()
                .from(StateMachineTest.States.STATE1)
                .to(StateMachineTest.States.STATE2)
                .on(StateMachineTest.Events.EVENT1)
                .when((ctx) -> "2".equals(ctx.getCondition()))
                .perform((from, to, event, ctx) -> System.out.println("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getCondition()));
        builder.externalTransition()
                .from(StateMachineTest.States.STATE1)
                .to(StateMachineTest.States.STATE3)
                .on(StateMachineTest.Events.EVENT1)
                .when((ctx) -> "3".equals(ctx.getCondition()))
                .perform((from, to, event, ctx) -> System.out.println("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx.getCondition()));

        StateMachine<StateMachineTest.States, StateMachineTest.Events, Context> stateMachine = builder.build("ChoiceConditionMachine");
        StateMachineTest.States target1 = stateMachine.fireEvent(StateMachineTest.States.STATE1, StateMachineTest.Events.EVENT1, new Context("1"));
        Assert.assertEquals(StateMachineTest.States.STATE1, target1);
        StateMachineTest.States target2 = stateMachine.fireEvent(StateMachineTest.States.STATE1, StateMachineTest.Events.EVENT1, new Context("2"));
        Assert.assertEquals(StateMachineTest.States.STATE2, target2);
        StateMachineTest.States target3 = stateMachine.fireEvent(StateMachineTest.States.STATE1, StateMachineTest.Events.EVENT1, new Context("3"));
        Assert.assertEquals(StateMachineTest.States.STATE3, target3);
    }


    private StateMachine<States, Events, Context> buildStateMachine(String machineId) {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        builder.internalTransition()
                .within(States.STATE2)
                .on(Events.INTERNAL_EVENT)
                .when(checkCondition())
                .perform(doAction());

        builder.externalTransition()
                .from(States.STATE2)
                .to(States.STATE1)
                .on(Events.EVENT2)
                .when(checkCondition())
                .perform(doAction());

        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE3)
                .on(Events.EVENT3)
                .when(checkCondition())
                .perform(doAction());

        builder.build(machineId);

        return StateMachineFactory.get(machineId);
    }

    private Condition<Context> checkCondition() {
        return context -> {
            System.out.println("Check condition : " + context);
            return true;
        };
    }

    private PerformAction<States, Events, Context> doAction() {
        return (from, to, event, ctx) -> System.out.println(
                ctx.operator + " is operating " + ctx.entityId + " from:" + from + " to:" + to + " on:" + event);
    }

}
