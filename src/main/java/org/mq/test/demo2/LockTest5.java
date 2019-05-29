
package org.mq.test.demo2;


import java.util.concurrent.CyclicBarrier;

public class LockTest5 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("cyclicBarrier");
        }
    });

    public static void main(String[] args) {


        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    cyclicBarrier.await();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();


        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    cyclicBarrier.await();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    cyclicBarrier.await();
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();


        System.out.println("===========");

    }
}
