package com.machado.deadlock;

import java.util.concurrent.locks.Lock;

public class DeadLockRunnable implements Runnable{

    Lock lock1 = null;
    Lock lock2 = null;

    public DeadLockRunnable(Lock lock1, Lock lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        //even synchronising wont work atall, because if it follows different order it eventually
        //will end up in a deadlock
        System.out.println(Thread.currentThread().getName()+ " locking lock 1");
        lock1.lock();
        System.out.println(Thread.currentThread().getName()+ " lock 1 locked");

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " locking lock 2");
        lock2.lock();
        System.out.println(Thread.currentThread().getName()+ " lock 2 locked");

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " unlocking lock 2");
        lock2.unlock();
        System.out.println(Thread.currentThread().getName()+ " unlocked lock 2 ");


        System.out.println(Thread.currentThread().getName()+ " unlocking lock 1");
        lock1.unlock();
        System.out.println(Thread.currentThread().getName()+ " unlocked lock 1 ");

    }
}
