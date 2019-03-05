package com.lf.demo.activemq;

import java.io.Serializable;

public class MyMessage implements Serializable {
    private static final long serialVersionUID =  -245693081951194415L;
    private  String payload;

    public MyMessage(String payload){
        this.payload = payload;
    }

    @Override
    public String toString() {
        return payload;
    }
}
