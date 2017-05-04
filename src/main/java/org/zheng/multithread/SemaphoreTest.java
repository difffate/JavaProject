package org.zheng.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * Create by zxb on 2017/4/26
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 5;

    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(3);

    public static void testTryAcquire1() throws InterruptedException {
        if (s.tryAcquire()) {  //尝试获取一个许可
            System.out.println(Thread.currentThread().getName() + " 获得许可");
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + " 操作完毕");
            s.release();   //释放许可证
        } else {
            System.out.println(Thread.currentThread().getName() + "未获得许可");
        }
    }

    public static void testTryAcquire2() throws InterruptedException {
        if (s.tryAcquire(1)) {  //尝试获取一个许可
            System.out.println(Thread.currentThread().getName() + " 获得许可");
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + " 操作完毕");
            s.release();   //释放许可证
        } else {
            System.out.println(Thread.currentThread().getName() + "未获得许可");
        }
    }

    public static void testTryAcquire3() throws InterruptedException {
        if (s.tryAcquire(1, TimeUnit.SECONDS)) {   //尝试获取一个许可，超时时间1s
            System.out.println(Thread.currentThread().getName() + " 获得许可");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " 操作完毕");
            s.release();   //释放许可证
        } else {
            System.out.println(Thread.currentThread().getName() + "未获得许可");
        }
    }

    public static void testAcquire() throws InterruptedException {
        s.acquire();
        System.out.println(Thread.currentThread().getName() + " 获得许可");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " 操作完毕");
        s.release();   //释放许可证
    }

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
//                        testTryAcquire1();
//                        testTryAcquire2();
//                        testTryAcquire3();
                        testAcquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}