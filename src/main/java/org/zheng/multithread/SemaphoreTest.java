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

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(3);

    /**
     * tryAcquire()
     * 尝试获取permits个许可，成功则返回true，失败则返回false
     *
     * @throws InterruptedException
     */
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

    /**
     * tryAcquire(int permits)
     * 尝试获取permits个许可，成功则返回true，失败则返回false
     *
     * @throws InterruptedException
     */
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

    /**
     * tryAcquire(long timeout, TimeUnit unit)
     * 设置超时时间内能获取到1个许可都会返回true，否则返回false
     *
     * @throws InterruptedException
     */
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

    /**
     * 不断尝试，直到都获取到信号量执行任务
     *
     * @throws InterruptedException
     */
    public static void testTryAcquire4() throws InterruptedException {
        while (true) {
            if (s.tryAcquire(1, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获得许可");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 操作完毕");
                s.release();   //释放许可证
                break;
            }
        }
    }

    /**
     * 常规用法，先获取，获取不到则阻塞线程执行，获取到执行完，释放信号量
     *
     * @throws InterruptedException
     */
    public static void testAcquire() throws InterruptedException {
        s.acquire();    //获取许可
        System.out.println(Thread.currentThread().getName() + " 获得许可");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " 操作完毕");
        s.release();   //释放许可，只需要释放一次，释放多次会让信号量多增加
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
//                        testTryAcquire4();
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