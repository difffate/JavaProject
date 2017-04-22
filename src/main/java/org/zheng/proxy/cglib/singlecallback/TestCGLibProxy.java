package org.zheng.proxy.cglib.singlecallback;

import org.springframework.cglib.proxy.Enhancer;
import org.zheng.proxy.cglib.DBQuery;
import org.zheng.proxy.cglib.DBQueryProxy;

/**
 * Create by zxb on 2017/4/22
 */
public class TestCGLibProxy {
    public static void main(String[] args){
        DBQueryProxy dbQueryProxy = new DBQueryProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DBQuery.class);
        enhancer.setCallback(dbQueryProxy);
        DBQuery dbQuery = (DBQuery)enhancer.create();
        System.out.println(dbQuery.getElement("Hello"));
        System.out.println(dbQuery.getAllElements());
    }
}
