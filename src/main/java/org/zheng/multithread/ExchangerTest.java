package org.zheng.multithread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 调用Exchanger的exchange方法后，会阻塞线程，直到其他线程调用exchange方法交换数据；
 * 比如说定义3段，那么第3段就会等待，如果加上第4段，程序就能正常结束
 * Create by zxb on 2017/5/5
 */
public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(
                            Thread.currentThread().getName() + " here A，Return From B：" + exchanger.exchange("I'm A"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(
                            Thread.currentThread().getName() + " here B，Return From A：" + exchanger.exchange("I'm B"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(
                            Thread.currentThread().getName() + " here C，Return From ?：" + exchanger.exchange("I'm C"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(
                            Thread.currentThread().getName() + " here D，Return From ?：" + exchanger.exchange("I'm D"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
