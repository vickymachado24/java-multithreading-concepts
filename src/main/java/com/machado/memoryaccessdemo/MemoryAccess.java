package com.machado.memoryaccessdemo;

public class MemoryAccess {

    public static void main(String[] args) {
        //Not shared data or runnable
        Thread thread1 = new Thread(new MemAccessRunnable(new RandomObject(0)),"1");
        Thread thread2 = new Thread(new MemAccessRunnable(new RandomObject(0)),"2");

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //shared data & runnable
        MemAccessRunnable memAccessRunnableCommon = new MemAccessRunnable(new RandomObject(0));

        Thread thread3 = new Thread(memAccessRunnableCommon, "3");
        Thread thread4 = new Thread(memAccessRunnableCommon, "4");

        thread3.start();
        thread4.start();

        try{
            thread3.join();
            thread4.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
