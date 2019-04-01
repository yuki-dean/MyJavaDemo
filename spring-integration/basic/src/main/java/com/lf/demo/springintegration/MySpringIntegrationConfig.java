package com.lf.demo.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

import java.io.File;

@Configuration
@EnableIntegration
public class MySpringIntegrationConfig {
    private final String SOURCE_PATH = "target/source_path";
    private final String DEST_PATH =  "/target/dest_path";
    private final String FILE_TYPE = "*.img";

    @Bean(name = "fileChannel")
    public MessageChannel myChannel(){
        return new DirectChannel();
    }

    @Bean
//    @InboundChannelAdapter( value = "fileChannel", poller = @Poller(fixedDelay = "10000"))
    @InboundChannelAdapter( value = "myPubSubChannel", poller = @Poller(fixedDelay = "10000"))
    public MessageSource<File> readFile(){
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(SOURCE_PATH) );
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_TYPE) );
        return sourceReader;
    }



    @Bean
//    @InboundChannelAdapter( value = "fileChannel", poller = @Poller(fixedDelay = "10000"))
    @InboundChannelAdapter( value = "myPubSubChannel", poller = @Poller(fixedDelay = "10000"))
    public MessageSource<File> readFile2(){
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(SOURCE_PATH + "_new") );
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_TYPE) );
        return sourceReader;
    }


    @Bean
    @ServiceActivator(inputChannel = "myPubSubChannel")
    public MessageHandler fileWritingMessageHandler(){
        FileWritingMessageHandler  handler
                =  new FileWritingMessageHandler(new File(DEST_PATH));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }


    @Bean
    @ServiceActivator(inputChannel = "myPubSubChannel")

    public MessageHandler fileWritingMessageHandler2(){
        FileWritingMessageHandler  handler
                =  new FileWritingMessageHandler(new File(DEST_PATH+ "_new"));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

}
