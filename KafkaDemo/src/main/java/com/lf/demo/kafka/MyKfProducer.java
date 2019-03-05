package com.lf.demo.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyKfProducer {
    static public void main(String[] args) {
//        sendStringMessage();
        sendProtobufMessage();
//        sendProtobufMessage2();

    }

    static void sendStringMessage() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(properties);
        String msg = "This is message from kafka client";
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("string", msg));
        }
        producer.close();
    }

    static void sendProtobufMessage() {
        Properties properties = new Properties();
        properties.put("group.id", "group-1");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.lf.demo.kafka.ProtobufSerializer");
        Producer<String, MyUser> producer = new KafkaProducer<>(properties);
        MyUser user = new MyUser(1000, "Dengyuwei");
        for (int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<>("proto", user));
        }
        producer.close();
    }

    static void sendProtobufMessage2() {
        Properties properties = new Properties();
        properties.put("group.id", "group-1");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.lf.demo.kafka.ProtobufSerializer");
        Producer<String, MyUser> producer = new KafkaProducer<>(properties);
        MyUser user = new MyUser(1000, "Dengyuwei");
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("proto", user));
        }
        producer.close();
    }
}
