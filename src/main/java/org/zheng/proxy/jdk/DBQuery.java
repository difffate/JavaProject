package org.zheng.proxy.jdk;

/**
 * Create by zxb on 2017/4/22
 */
public class DBQuery implements IDBQuery {

    public String getElement(String id) {
        return id + "_JDKProxy";
    }
}
