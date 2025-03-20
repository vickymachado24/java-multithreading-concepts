package com.machado.threadpool;

public class ThreadPoolExample {

    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(3, 10);

        for( int i =0; i< 10; i++) {
            int taskNo = i;
            threadPool.execute( () -> System.out.println(Thread.currentThread().getName() +" task " + taskNo));
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();
    }
}
