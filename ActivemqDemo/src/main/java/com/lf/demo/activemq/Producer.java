package com.lf.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    static String url = "tcp://localhost:61616";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("yuwei","123",url);
        try {
            Connection conn = connectionFactory.createConnection();
            conn.start();
            Session session =  conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MyQueue1");
            MessageProducer producer = session.createProducer(destination);
            sendTextMessage(session, producer);
            sendObjectMessage(session, producer);
            System.out.println("Message sent");
            session.commit();
            conn.close();
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    static  void sendTextMessage(Session session , MessageProducer producer){
        try {
            Message message = session.createTextMessage("Hello world, this producer");
            producer.send(message);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    static void sendObjectMessage(Session session, MessageProducer producer){
        try{
            Message message =  session.createObjectMessage(new MyMessage("My message from client"));
            producer.send(message);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    //send message by topic
    static void sendByTopic(Session session){
        try{
            Topic tp = session.createTopic("MyTopic");
            MessageProducer producer =  session.createProducer(tp);
            TextMessage message = session.createTextMessage("Hello world");
            producer.send(message);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
