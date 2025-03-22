package com.machado.executorservice;

import java.util.concurrent.*;

public class ExecutorConstructorExample {
    static int corePoolSize = 10;
    static int maxPoolSize = 20;
    static int timeToLiveAfterExecution = 3000;

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                timeToLiveAfterExecution,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(128)
        );
        Future<String> future = executorService.submit(new CallableExample("Executor Thread 1"));
        System.out.println(future.isDone());

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdownNow();
        executorService.shutdown();


    }
}
