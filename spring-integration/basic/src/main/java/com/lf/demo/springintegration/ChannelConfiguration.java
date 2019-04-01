package com.lf.demo.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration

public class ChannelConfiguration {
    @Bean
    public MessageChannel pubSubChannel(){
        return new PublishSubscribeChannel();
    }


    @Bean
    public MessageChannel myPubSubChannel(){
        return new MyChannel();
    }

    public static class MyChannel extends DirectChannel {

        @Override
        public boolean send(Message<?> message) {
            System.out.println("Send message ....");
            return this.send(message, -1);
        }
    }

}
