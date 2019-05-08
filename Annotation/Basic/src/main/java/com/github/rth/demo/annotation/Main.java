package com.github.rth.demo.annotation;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws Exception{
        Annotation[] annotations =  Class.forName(MyTest.class.getName()).getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof MyTag){
                System.out.println("MyTag annotation");
                System.out.println("annotation value:"+ ((MyTag) annotation).name());
                System.out.println("annotation value:"+ ((MyTag) annotation).id());
            }
        }
    }
}
