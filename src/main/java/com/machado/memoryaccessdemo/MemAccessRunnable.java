package com.machado.memoryaccessdemo;

public class MemAccessRunnable implements Runnable{

    int count = 0;
    RandomObject object = null;

    public MemAccessRunnable(){}

    public MemAccessRunnable(RandomObject object){
        this.object = object;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " started");

        for( int i =0; i< 10000; i++) {
            //avoid race conditions

            //Synchronizing on this works if the threads share the same MemAccessRunnable object.
            synchronized (this) {
                this.count++;
                this.object.count++;
            }

            //However, if the critical section modifies another shared object (this.object),
            // it's better to synchronize on that object directly.
            synchronized (this.object) {
                this.count++;
                this.object.count++;
            }
        }
        System.out.println(" normal count = " + this.count);
        System.out.println(" obj count = " + this.object.count);
        System.out.println(Thread.currentThread().getName() + " ended");

    }
}
