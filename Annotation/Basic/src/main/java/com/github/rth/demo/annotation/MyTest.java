package com.github.rth.demo.annotation;

@MyTag(name = "mytest", id = 100)
public class MyTest {
    public void foo2(){
        System.out.println("foo2");
    }
    public void foo1(){
        System.out.println("foo1");
    }
}
