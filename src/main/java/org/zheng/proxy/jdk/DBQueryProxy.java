package org.zheng.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Create by zxb on 2017/4/22
 */
public class DBQueryProxy implements InvocationHandler {

    private DBQuery dbQuery;

    public DBQueryProxy(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(dbQuery, objects);
    }
}
