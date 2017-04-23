package org.zheng.proxy.define;

/**
 * Create by zxb on 2017/4/23
 */
interface Subject {

    void request();
}

class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("RealSubject do something!");
    }
}

class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        subject.request();
    }
}

public class ProxyExample {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        proxy.request();
    }
}
