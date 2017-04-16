package org.zheng.jvmcmd;

/**
 * Create by zxb on 2017/4/16
 */
class SuperClass {

    public String method() {
        return "from SuperClass...";
    }

    public void otherMethod() {
        System.out.println("In SuperClass otherMethod()...");
        System.out.println("SuperClass otherMethod() calls method(): " + method());   //invokevirtual编译时确定
    }
}

class SubClass extends SuperClass {

    public String method() {
        return "from SubClass...";
    }

    public void subMethod() {
        System.out.println("SubClass calls super.method(): " + super.method());   //invokespecail
        privateMethod();  //invokespeical
    }

    private void privateMethod() {
        System.out.println("This is private Method From SubClass");
    }
}

interface ITest {

    void print();
}

class TestImpl implements ITest {

    public void print() {
        System.out.println("print in TestImpl...");
    }
}

public class InvokeTest {

    public static void staticMethod() {
        System.out.println("I am static method..");
    }

    public static void main(String args[]) {
        staticMethod();  //invokestatic

        SubClass b = new SubClass(); //invokespecial 初始化
        SuperClass supper = b;              //向上转型引用
        System.out.println(supper.method());//invokevirtual，当前引用的对象是b

        b.subMethod();  //invokevirtual
        b.otherMethod();  //invokevirtual

        ITest iTest = new TestImpl();  //invokespcial 初始化
        iTest.print();   //invokeinterface
    }
}
