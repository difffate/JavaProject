package org.zheng.pattern.structural.decorator;

/**
 * Create by zxb on 2017/4/25
 */
public class Decorator1 extends Component {

    private  Component component;

    public Decorator1(Component component){
        this.component = component;
    }

    public void doSomething() {
        this.component.doSomething();
        System.out.println("Here's in Decorator1 ! ");
    }
}
