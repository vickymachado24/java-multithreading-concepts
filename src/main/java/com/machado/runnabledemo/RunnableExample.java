package com.machado.runnabledemo;

public class RunnableExample {

    static class RunnableClass implements Runnable{

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + " Runnable Thread Running");
            System.out.println(Thread.currentThread().getName() + " Runnable Thread Exiting");
        }
    }
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");

        //lambda expression runnable
        Runnable runnableLambda = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "Running");
        };

        new Thread(new RunnableClass(), "Runnable Lambda Thread").start();

        Thread threadRunnable = new Thread(new RunnableClass(), "RunnableThread 1");
        threadRunnable.start();
        Thread threadRunnable2 = new Thread(new RunnableClass(), "RunnableThread 2");
        threadRunnable2.start();
        Thread threadRunnable3 = new Thread(new RunnableClass(), "RunnableThread 3");
        threadRunnable3.start();

        System.out.println(Thread.currentThread().getName() + " end");
    }
}
