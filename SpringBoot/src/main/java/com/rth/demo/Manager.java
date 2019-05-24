package com.rth.demo;

import com.rth.demo2.XService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Manager {
    @Autowired
    private XService xService;
    public void test(){
        xService.foo();
    }

}
