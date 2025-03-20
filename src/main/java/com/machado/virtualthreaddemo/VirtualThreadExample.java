package com.machado.virtualthreaddemo;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadExample {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        Thread vThread = Thread.ofVirtual().unstarted(() -> System.out.println(Thread.currentThread().getName() + " Started."));
        vThread.start();

        List<Thread> threadList = new ArrayList<>();

        for( int i =0; i< 100; i++){
            threadList.add(Thread.ofVirtual().name("VThread " + i).start(() ->{
                System.out.println(Thread.currentThread().getName() + " started");
                System.out.println(Thread.currentThread().getName() + " ended");
            }));
        }

        for( Thread thread : threadList) {
            try{
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
