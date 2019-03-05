package com.lf.spring.jpa;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Data
@Builder
@Entity(name = "Customer")
@Table(name = "T_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name= "sequence-generator", sequenceName = "customer_sequence")
    private long id;
    @Column(name = "customer_name", nullable = true, length = 12, insertable = true, updatable = true)
    String name;
    public void test(){
        log.debug("hello world");
        log.error("hello world");
    }
}
