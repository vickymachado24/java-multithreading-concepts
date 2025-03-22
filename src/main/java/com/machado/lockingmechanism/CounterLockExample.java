package com.machado.lockingmechanism;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLockExample {

    private int count = 0;

    private Lock lock = new ReentrantLock();

    public void incCount(){
        try {
            lock.lock();
            this.count++;
        }finally {
            lock.unlock();
        }
    }
    public int getCount() {
        try{
            lock.lock();
            return this.count;
        }finally {
            lock.unlock();
        }
    }
}
