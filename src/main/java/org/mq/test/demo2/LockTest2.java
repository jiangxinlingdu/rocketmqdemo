
package org.mq.test.demo2;


import java.util.concurrent.Semaphore;

public class LockTest2 {
    private static Semaphore semaphore = new Semaphore(2); // NonfairSync

    public static void main(String[] args) {


        new Thread(() -> {
            for (int i = 0; i < 2001; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("b");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("c");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
