package org.zheng.proxy.cglib.singlecallback;

import org.springframework.cglib.proxy.Enhancer;
import org.zheng.proxy.cglib.DBQuery;
import org.zheng.proxy.cglib.DBQueryProxy;

/**
 * 1）使用CGLib代理的类不能是final修饰的，因为代理类需要继承主题类；
 * 2）final修饰的方法不会被切入；
 * 3）如果主题类的构造函数不是默认空参数的，那么在使用Enhancer类create的时候
 * ，选择create(java.lang.Class[] argumentTypes, java.lang.Object[] arguments) 方法
 * Create by zxb on 2017/4/22
 */
public class TestCGLibProxy {

    public static void main(String[] args) {
        DBQueryProxy dbQueryProxy = new DBQueryProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DBQuery.class);
        enhancer.setCallback(dbQueryProxy);
//        DBQuery dbQuery = (DBQuery)enhancer.create(new Class[]{Integer.class}, new Object[]{1});
        DBQuery dbQuery = (DBQuery) enhancer.create();
        System.out.println(dbQuery.getElement("Hello"));
        System.out.println();
        System.out.println(dbQuery.getAllElements());
        System.out.println();
        System.out.println(dbQuery.sayHello());
    }
}
