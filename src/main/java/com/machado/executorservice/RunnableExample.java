package com.machado.executorservice;


public class RunnableExample implements Runnable{

    String message;

    public RunnableExample(String message){
        this.message = message;
    }

    @Override
    public void run(){
        System.out.println( Thread.currentThread().getName() + " " + message);
    }
}
