package com.lf.demo.protobuf;

import com.lf.demo.protobuf.coffeshop.CoffeeShop;

public class Main {
    public static void main(String[] args) throws Exception{
        CoffeeShop.Order.Builder builder  =
                CoffeeShop.Order.newBuilder();
        builder.setOrderId("id00001");
        builder.setAmount(100.1f);
        builder.setDesc("This is order of coffe");
        CoffeeShop.Order order = builder.build();


        byte[] data = order.toByteArray();

        CoffeeShop.Order order1 = CoffeeShop.Order
                .parseFrom(data);
        System.out.println(order1.getOrderId());
        System.out.println(order1.getAmount());
        System.out.println(order1.getDesc());
    }
}
