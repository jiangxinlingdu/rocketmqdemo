
package org.mq.test.demo2;


import java.util.concurrent.CountDownLatch;

public class LockTest4 {
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    ; // NonfairSync

    public static void main(String[] args) {


        new Thread(() -> {
            countDownLatch.countDown();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        new Thread(() -> {
            countDownLatch.countDown();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            countDownLatch.countDown();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===========");

    }
}
