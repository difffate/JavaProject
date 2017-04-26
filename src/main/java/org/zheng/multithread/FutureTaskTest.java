package org.zheng.multithread;

import java.util.concurrent.Callable;

/**
 * Create by zxb on 2017/4/26
 */
class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 666;
    }
}
public class FutureTaskTest {
    public static void main(String[] args){
//        Executors.newCachedThreadPool()
    }
}
