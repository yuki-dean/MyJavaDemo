package com.rth.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "MyService1")
public class MyService implements IService{

    @Autowired
    private Manager manager;
    public MyService(){
        System.out.println("Create MyService");
    }
    @Override
    public void foo() {
        manager.test();
    }
}
