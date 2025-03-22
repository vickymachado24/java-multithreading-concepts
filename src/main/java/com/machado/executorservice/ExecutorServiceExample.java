package com.machado.executorservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        //fixed pool size
        //cannot grow more than this
        //limited resources
        ExecutorService executorServiceFixed = Executors.newFixedThreadPool(10);
        //indefinite amount of threads, can grow in size
        //unlimited
        ExecutorService executorServiceCached = Executors.newCachedThreadPool();
        //only single
        ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor();


        executorServiceFixed.execute(new RunnableExample("Thread 1"));
        executorServiceFixed.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " Thread 2");
        });
        executorServiceFixed.execute(new RunnableExample("Thread 3"));

        executorServiceFixed.shutdown();

        List<Callable<String>> callables = Arrays.asList(new CallableExample("List Thread 1"), new CallableExample("List Thread 3"), () -> "List Thread 2");
        try {
            List<Future<String>> futureList = executorServiceCached.invokeAll(callables);

            for(Future<String> future : futureList) System.out.println(future.get());

        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorServiceCached.shutdown();


        Future<String> future = executorServiceSingle.submit(new CallableExample("Submit Thread 3"));

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorServiceSingle.shutdown();
    }

}
