package com.lf.demo.jvm;

public class ThreadID {
    public static void main(String[] args)throws InterruptedException{
        Thread.currentThread().getId();

        Thread thread =  new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
            }
        };

        Thread thread2 =  new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
            }
        };

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
    }


}
