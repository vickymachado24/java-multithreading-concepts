package com.machado.deadlock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DeadLockDetection {

    public static void main(String[] args) {
        new DeadlockDetector().start();  // Start deadlock monitoring
        simulateDeadlock();  // Create deadlock scenario
    }

    static void simulateDeadlock() {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        DeadLockRunnable deadLockRunnable = new DeadLockRunnable(lock1,lock2);
        DeadLockRunnable2 deadLockRunnable2 = new DeadLockRunnable2(lock1,lock2);

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
