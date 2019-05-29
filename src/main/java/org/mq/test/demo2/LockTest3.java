
package org.mq.test.demo2;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest3 {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(); // NonfairSync

    public static void main(String[] args) {


        new Thread(() -> {
            for (int i = 0; i < 2001; i++) {
                readWriteLock.readLock().lock();
                try {
                    System.out.println("b");
                } finally {
                    readWriteLock.readLock().unlock();
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
                readWriteLock.readLock().lock();
                try {
                    System.out.println("c");
                } finally {
                    readWriteLock.readLock().unlock();
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
                readWriteLock.writeLock().lock();
                try {
                    System.out.println("a");
                } finally {
                    readWriteLock.writeLock().unlock();
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
