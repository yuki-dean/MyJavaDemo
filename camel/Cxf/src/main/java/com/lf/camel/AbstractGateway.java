package com.lf.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public abstract class AbstractGateway {
    protected static CamelContext context =  new DefaultCamelContext();

    public void run(){
        addRoute();
        try {
            if(!context.getStatus().isStarted()){
                System.out.println("Begin to run the context....");
                context.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract  void addRoute();

    public void stop(){
        try {
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
