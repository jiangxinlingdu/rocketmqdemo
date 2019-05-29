
package org.mq.test.demo2;


import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
    private static ReentrantLock lock = new ReentrantLock(); // NonfairSync

    public static void main(String[] args) {

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("a");
                Thread.sleep(50000000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("b");
                Thread.sleep(60000000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

    }
}
