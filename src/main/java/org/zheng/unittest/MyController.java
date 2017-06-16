package org.zheng.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Create by zxb on 2017/6/16
 */
@Controller
public class MyController {

    @Autowired
    private MyService myService;

    public void doSomething() {
        this.myService.doSomething();
    }

    public Model findById(Long id) {
        return this.myService.findById(id);
    }
}
