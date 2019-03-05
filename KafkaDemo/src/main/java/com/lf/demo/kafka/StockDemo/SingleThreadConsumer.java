package com.lf.demo.kafka.StockDemo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class SingleThreadConsumer {
    private static final int MSG_SIZE = 100;
    private static final String TOPIC =  "stock-quotation";
    private static final String GROUP_ID= "stock_consumer_group1";
    private static final String BROKER_LIST =  "tcp://localhost:9092";
    private  static KafkaConsumer<String, String> consumer;
    public static void main(String[] args){
        initConfig();
        consumer.subscribe(Arrays.asList(TOPIC), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitSync();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                long committedOffset = -1;
                for(TopicPartition topicPartition:partitions){
                    committedOffset = consumer.committed(topicPartition).offset();
                    consumer.seek(topicPartition, committedOffset +1);
                }
            }
        });
        while(true) {
            consumer.poll(Duration.ofSeconds(1000));
        }
    }

    static void initConfig(){
        Properties props= new Properties ();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST) ;
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "test");

        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        consumer = new KafkaConsumer<>(props);

    }
}
