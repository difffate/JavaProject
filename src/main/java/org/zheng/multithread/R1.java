package org.zheng.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Create by zxb on 2017/8/24
 */
class R2 implements Runnable {
    Runnable r;

    public R2(Runnable r) {
        this.r = r;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (r) {
            r.notifyAll();
        }
        System.out.println("R2 over");
    }

}

public class R1 implements Runnable {

    @Override
    public void run() {
        boolean b = false;
        synchronized (this) {
            while (!(b = Thread.interrupted())) {
                try {
                    wait();
                    System.out.println("R1 awakend");
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException, interu:" + b);
                }
            }
            System.out.println("R1 over, interrupted:" + b);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Runnable r1 = new R1();

        Future<?> f = exec.submit(r1);
        exec.execute(new R2(r1));

        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
//      不加解除不了
//		 boolean ret = f.cancel(true);
//		 System.out.println("main cancel:" + ret );

    }
}

