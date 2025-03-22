package com.machado.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TryLockRunnable2 implements Runnable {
    Lock lock1 = null;
    Lock lock2 = null;

    public TryLockRunnable2(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        boolean lock2Acquired = false;
        boolean lock1Acquired = false;

        try {
            System.out.println(Thread.currentThread().getName() + " attempting to lock lock2...");
            lock2Acquired = lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            if (lock2Acquired) {
                System.out.println(Thread.currentThread().getName() + " locked lock2");

                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " attempting to lock lock1...");
                lock1Acquired = lock1.tryLock(1000, TimeUnit.MILLISECONDS);

                if (lock1Acquired) {
                    System.out.println(Thread.currentThread().getName() + " locked lock1");
                    Thread.sleep(1000);
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to lock lock1, releasing lock2");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to lock lock2");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1Acquired) {
                lock1.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked lock1");
            }
            if (lock2Acquired) {
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked lock2");
            }
        }
    }
}