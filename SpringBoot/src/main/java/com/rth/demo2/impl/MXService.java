package com.rth.demo2.impl;

import com.rth.demo2.XService;
import org.springframework.stereotype.Service;

@Service
public class MXService implements XService {
    public MXService(){
        System.out.println("MXService.....");
    }
    @Override
    public void foo() {
        System.out.println("foo......");
    }
}
