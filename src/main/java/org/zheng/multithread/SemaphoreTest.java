package org.zheng.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Create by zxb on 2017/4/26
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 5;

    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("name="+Thread.currentThread().getName());
                        s.tryAcquire(1, TimeUnit.SECONDS);   //获得一个许可证
                        System.out.println("availablePermits="+s.availablePermits());
                        System.out.println("save data");
                        s.release();   //释放许可证
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        threadPool.shutdown();
    }
}
