package com.lf.sdk.api;

public class Main {
    public static void main(String[] args){
        if( MyFlow.class.isAssignableFrom(Flow.class) ) System.out.println("is assigned from flow");
        if( Flow.class.isAssignableFrom(MyFlow.class) ) System.out.println("is assigned from flow");
        if( Flow.class.isAssignableFrom(MyFlow2.class) ) System.out.println("is assigned from flow2");
    }
}
