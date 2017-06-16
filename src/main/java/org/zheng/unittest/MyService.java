package org.zheng.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zxb on 2017/6/16
 */
@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public void doSomething() {
        this.myRepository.doSomething();
    }

    public Model findById(Long id) {
        return this.myRepository.findById(id);
    }
}
