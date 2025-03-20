package com.machado.syncdemo;

public class SyncExchangeImplementation {

    public static void main(String[] args) {

        SyncExchange syncExchange = new SyncExchange();

        Thread thread1 = new Thread(() -> {
            for( int i =0; i< 1000; i++) syncExchange.setObj( "" + i);
        });
        Thread thread2 = new Thread(() -> {
            for( int i =0; i< 1000; i++) System.out.println(syncExchange.getObj());
        });

        thread1.start();
        thread2.start();
    }
}
