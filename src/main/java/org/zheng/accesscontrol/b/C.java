package org.zheng.accesscontrol.b;

import org.zheng.accesscontrol.a.A;

/**
 * Create by zxb on 2017/4/16
 */
public class C extends A {
    public void H(){
        this.aa = "";  //正常访问
//        this.bb = "";  //编译报错
    }
    public static void main(String[] args){
        A a = new A();
//        a.aa = "1";  //编译报错
//        a.bb = "1";  //编译报错
    }
}
