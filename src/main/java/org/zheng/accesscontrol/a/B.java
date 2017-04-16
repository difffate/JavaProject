package org.zheng.accesscontrol.a;

/**
 * Create by zxb on 2017/4/16
 */
public class B extends A {
    public void H(){
        this.aa = "";  //正常访问
        this.bb = ""; //正常访问
    }
    public static void main(String[] args){
        A a = new A();
        a.aa = "1"; //正常访问
        a.bb = "1"; //正常访问
    }
}
