package com.lf.demo.integration.jdsl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {
//        AbstractApplicationContext context
//                = new AnnotationConfigApplicationContext(DslDefinition.class);
//        context.registerShutdownHook();


        DslFactory  dslFactory =new DslFactory();

        dslFactory.fileTransfer();

//        dslFactory.myChannel();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter q and press <enter> to exit the program: ");

        while (true) {
            String input = scanner.nextLine();
            if("q".equals(input.trim())) {
                break;
            }
        }
        System.exit(0);
    }
}
