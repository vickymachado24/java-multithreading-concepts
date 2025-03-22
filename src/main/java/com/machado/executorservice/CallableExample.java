package com.machado.executorservice;

import java.util.concurrent.Callable;


//returns something instead of void in case of Runnable
public class CallableExample implements Callable {

    String msg;

    public CallableExample(String msg){
        this.msg = msg;
    }
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + " " + msg;
    }
}
