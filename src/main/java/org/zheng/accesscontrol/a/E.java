package org.zheng.accesscontrol.a;

/**
 * Create by zxb on 2017/4/16
 */
public class E {

    public static void main(String[] args) {
        A a = new A();
        a.aa = "1";  //正常访问
        a.bb = "1"; //正常访问
    }
}
