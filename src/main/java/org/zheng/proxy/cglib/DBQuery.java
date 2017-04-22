package org.zheng.proxy.cglib;

import java.util.Arrays;
import java.util.List;

/**
 * Create by zxb on 2017/4/22
 */
public class DBQuery {

    public String getElement(String id) {
        return id + "_CGLib";
    }

    public List<String> getAllElements() {
        return Arrays.asList("Hello_CGLib1", "Hello_CGLib2");
    }
}
