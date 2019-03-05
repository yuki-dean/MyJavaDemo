package com.lf.spring.jpa;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo1 {
    public void test(){
    }

    public static void main(String[] args){
        Customer customer = Customer.builder()
                .id(1)
                .name("username").build();
        customer.getName();
        log.debug("hello world");
        log.info("hello  info");
        log.error("hello  error");

        Order order =  new Order(1, "sdf");
        System.out.println( order.toString() );
    }
}
