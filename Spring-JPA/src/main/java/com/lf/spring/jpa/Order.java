package com.lf.spring.jpa;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
public class Order {
    @Getter
    private long id;
    @Getter
    @Setter
    private String desc;
}
