package com.machado.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        TryLockRunnable deadLockRunnable = new TryLockRunnable(lock1,lock2);
        TryLockRunnable2 deadLockRunnable2 = new TryLockRunnable2(lock1,lock2);

        Thread thread1 = new Thread(deadLockRunnable);
        Thread thread2 = new Thread(deadLockRunnable2);

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
