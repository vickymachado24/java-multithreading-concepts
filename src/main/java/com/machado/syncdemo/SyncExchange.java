package com.machado.syncdemo;

public class SyncExchange {

    private Object object = null;

    //treats instance of SyncExchange as monitor object
    public synchronized void setObject(Object object){
        this.object = object;
    }

    public synchronized Object getObject(){
        return this.object;
    }

    public void setObj(Object object){
        //treats instance of SyncExchange as monitor object
        //can use any other object also as monitor object
        synchronized (this){
            this.object = object;
        }
    }

    public Object getObj(){
        synchronized (this){
            return this.object;
        }
    }
}
