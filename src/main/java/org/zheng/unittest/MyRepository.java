package org.zheng.unittest;


import org.springframework.stereotype.Repository;

/**
 * Create by zxb on 2017/6/16
 */
@Repository
public class MyRepository {

    public void doSomething() {
        System.out.println("here's dosomething");
    }

    public Model findById(Long id) {
        return new Model(id, "Real Repository");
    }
}
