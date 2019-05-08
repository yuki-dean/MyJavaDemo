package com.lf.camel.basichttp;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.InputStream;

public class App {

    public static void main(String[] args) throws Exception{
        RouteBuilder routeBuilder =  new RouteBuilder() {
            @Override
            public void configure() {
                from("netty4-http:http://localhost:8080/foo")
                        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST)).process(ex->{
                            InputStream inputStream =(InputStream) ex.getIn().getBody();
                            byte[] data =  new byte[1024];
                            int size = inputStream.read(data);
                            String in = new String(data);
                            System.out.println("size:=" +size  + " sys:" + in);
                            inputStream.close();
                            ex.getOut().setBody("ok");
                });
            }
        };

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(routeBuilder);

        context.start();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("netty4-http:http://localhost:8080/fee").transform().simple("hello world, fee\n");
            }
        });

        Thread.sleep(100000);

    }

}
