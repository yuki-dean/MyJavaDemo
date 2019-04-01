package com.lf.demo.integration.jdsl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class DslDefinition {
    private final String SOURCE_PATH = "target/source_path";
    private final String DEST_PATH =  "target/dest_path";
    private final String FILE_TYPE = "*.img";

    @Bean
    public MessageSource<File> sourceDir(){
        FileReadingMessageSource messageSource =  new FileReadingMessageSource();
        messageSource.setDirectory(new File(SOURCE_PATH));
        return messageSource;
    }

    @Bean
    public MessageHandler targetDirectory(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(DEST_PATH));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    public IntegrationFlow fileTransfer(){
       return  IntegrationFlows
            .from(sourceDir(), configurer -> configurer.poller(Pollers.fixedDelay(5000)))
            .filter(s -> ((File)s).getName().endsWith(".img") )
            .channel(myChannel())
//               .channel("TestChannel")
//          .handle(targetDirectory())
            .get();
    }

    @Bean("TestChannel")
    public MessageChannel myChannel(){
        return new DirectChannel() {
            @Override
            protected boolean doSend(Message<?> message, long timeout) {
                System.out.println("Do send Message: " + message);
                return super.doSend(message, timeout);
            }
        };
    }

    @Bean
    public IntegrationFlow fileWriter() {
        return IntegrationFlows.from("TestChannel")
//        return IntegrationFlows.from(myChannel())
//                .bridge(e -> e.poller(Pollers.fixedRate(1, TimeUnit.SECONDS, 20)))
                .handle(targetDirectory()).get();
    }

}
