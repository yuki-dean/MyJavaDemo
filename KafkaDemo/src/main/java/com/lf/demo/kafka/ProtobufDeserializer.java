package com.lf.demo.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProtobufDeserializer implements  Deserializer<Protobufable>{
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Protobufable deserialize(String topic, byte[] data) {
        if(topic.equals("proto")) {
            try {
                System.out.println("deserialize proto bytes");
                UserOuterClass.User user = UserOuterClass.User.parseFrom(data);
                return  MyUser.builder().id(user.getId()).name(user.getName()).build();
            }catch (InvalidProtocolBufferException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void close() {

    }
}
