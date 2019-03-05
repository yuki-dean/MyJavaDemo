package com.lf.demo.kafka;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class MyKfConsumer {
    public static void main(String[] args)throws InterruptedException{
//        receiveString();
//        receiveProto();
//        receiveProtoAsych();

        Thread thread =  new Thread(()-> receiveProto());

        Thread thread2 =  new Thread(()-> receiveProto());

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
    }

    public static void receiveString(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "group-1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer =  new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test"));
        while(true){
            System.out.println("begin Polling");
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            System.out.println("Polling done");
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }
    }

    public static void receiveProto(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "group-1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.lf.demo.kafka.ProtobufDeserializer");
        KafkaConsumer<String, Protobufable> consumer =  new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("proto"));
        int counter=0;

        while(true){
            System.out.println("begin Polling");
            ConsumerRecords<String,Protobufable> records = consumer.poll(Duration.ofSeconds(10000));
            System.out.println("Thread " + Thread.currentThread().getId());
            for (ConsumerRecord<String, Protobufable> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println("Get Message count:" + ++counter);
            }
        }
    }

    public static void receiveProtoAsych(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "group-1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.lf.demo.kafka.ProtobufDeserializer");
        KafkaConsumer<String, Protobufable> consumer =  new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("proto") , listener);
        while(true){
            ConsumerRecords<String,Protobufable> records = consumer.poll(Duration.ofSeconds(10000));
            System.out.println("Polling done");
            for (ConsumerRecord<String, Protobufable> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }
    }

    public static ConsumerRebalanceListener listener =  new ConsumerRebalanceListener() {
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

            System.out.println("onPartitionsRevoked");
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            System.out.println("onPartitionsAssigned");
        }
    };
}
