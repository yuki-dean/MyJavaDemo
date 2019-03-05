package com.lf.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    static String url = "tcp://localhost:61616";
    public static void main(String[] args){

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("yuwei","123",url);
        Connection conn =null;
        try {
            conn = connectionFactory.createConnection();
            conn.start();
            Session session =  conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MyQueue1");
            MessageConsumer consumer = session.createConsumer(destination);
            while (true) { getMessage(consumer); }
        }catch (JMSException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            }catch (JMSException e){
                e.printStackTrace();
            }
        }
    }

    public static void getMessage(MessageConsumer consumer){
        System.out.println("get message....");
        try {
            Message message = consumer.receive();
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage =  (ObjectMessage) message;
                MyMessage msg=  (MyMessage) objectMessage.getObject();
                System.out.println("Receive Object message: "+ msg.toString());
            }
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                String msg = textMessage.getText();
                System.out.println("Receive text msg: " + msg);
            }
            System.out.println("Get message done....");
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

}
