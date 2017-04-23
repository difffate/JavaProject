package org.zheng.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * Create by zxb on 2017/4/22
 */
public class TestJDKProxy {

    public static void main(String[] args) {
        //设置变量，可在将代理类保存在项目根目录
        //System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        DBQuery dbQuery = new DBQuery();
        DBQueryProxy dbQueryProxy = new DBQueryProxy(dbQuery);
        IDBQuery query = (IDBQuery) Proxy
                .newProxyInstance(dbQuery.getClass().getClassLoader(), dbQuery.getClass().getInterfaces(),
                        dbQueryProxy);
        System.out.println(query.getElement("Hello"));
    }
}
