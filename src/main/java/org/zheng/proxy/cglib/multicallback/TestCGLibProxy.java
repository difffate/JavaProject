package org.zheng.proxy.cglib.multicallback;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.zheng.proxy.cglib.DBQuery;
import org.zheng.proxy.cglib.DBQueryProxy;

import java.lang.reflect.Method;

/**
 * Create by zxb on 2017/4/22
 */
public class TestCGLibProxy {

    public static void main(String[] args) {
        DBQueryProxy dbQueryProxy = new DBQueryProxy();
        DBQueryProxy2 dbQueryProxy2 = new DBQueryProxy2();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DBQuery.class);
        enhancer.setCallbacks(new Callback[]{dbQueryProxy, dbQueryProxy2});
        enhancer.setCallbackFilter(new CallbackFilter() {

            public int accept(Method method) {
                if (method.getName().equals("getElement")) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        DBQuery dbQuery = (DBQuery) enhancer.create();
        System.out.println(dbQuery.getElement("Hello"));
        System.out.println(dbQuery.getAllElements());
    }
}
