package com.lf.spring.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class Demo1 implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    public void test(){
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

    @Override
    public void run(String... args) throws Exception{
        showConnection();
    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn =  dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }

    public static void main(String[] args){
        SpringApplication.run(Demo1.class, args);
    }

}
