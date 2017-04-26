package org.zheng.pattern.structural.decorator;

/**
 * Create by zxb on 2017/4/25
 */
public class DecoratorTest {
    public static void main(String[] args){
        ConcreteComponent component = new ConcreteComponent();
        component.doSomething();
        System.out.println();
        Decorator1 decorator1 = new Decorator1(component);
        decorator1.doSomething();
        System.out.println();
        Decorator2 decorator2 = new Decorator2(decorator1);
        decorator2.doSomething();
    }
}
