package org.zheng.multithread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create by zxb on 2017/5/4
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<Integer>> taskList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            taskList.add(executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int j = 0; j < 10000; j++) {
                        sum += j;
                        System.out.println(Thread.currentThread().getName()+",i="+sum+",flag="+Thread.interrupted());
                    }
                    return 1;
                }
            }));
        }
        for (Future<Integer> future : taskList) {
            System.out.println(future.cancel(true));
        }
        System.out.println("执行完成"+new Date());
    }

}

//public class Test {
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = new Task();
//        Future<Integer> result = executor.submit(task);
//        executor.shutdown();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//
//        System.out.println("主线程在执行任务");
//
//        try {
//            System.out.println("task运行结果"+result.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("所有任务执行完毕");
//    }
//}
//class Task implements Callable<Integer> {
//    @Override
//    public Integer call() throws Exception {
//        System.out.println("子线程在进行计算");
//        Thread.sleep(3000);
//        int sum = 0;
//        for(int i=0;i<100;i++)
//            sum += i;
//        return sum;
//    }
//}