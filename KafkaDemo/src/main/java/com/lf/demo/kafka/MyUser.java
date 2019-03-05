package com.lf.demo.kafka;


import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyUser implements Protobufable{
    private  long id;
    private String name;
    public MyUser(long id, String name){
        this.id = id;
        this.name =name;
    }

    public MyUser(byte[] bytes){
        try{
            UserOuterClass.User user = UserOuterClass.User.parseFrom(bytes);
            this.name = user.getName();
            this.id = user.getId();
        }catch (InvalidProtocolBufferException e){
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encode() {
         return UserOuterClass.User.newBuilder()
                .setId(id)
                .setName(name)
                .build()
                .toByteArray();
    }
}
