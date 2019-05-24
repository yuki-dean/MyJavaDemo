package com.rth.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rth")
public class SpringBoot {
    @Autowired
    private IService service;
    static public void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(SpringBoot.class, args);
        MyService object = (MyService) context.getBean("MyService1");
        object.foo();
    }
}
