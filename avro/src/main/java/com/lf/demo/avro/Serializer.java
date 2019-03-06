package com.lf.demo.avro;


import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 *  code example from :
 *  http://avro.apache.org/docs/current/gettingstartedjava.html#Creating+Users
 */
public class Serializer {

    public static void  main(String[] args) throws IOException {
        User user = User.newBuilder()
                .setName("Yuwei")
                .setFavoriteNumber(10)
                .setFavoriteColor("blue")
                .build();

        DatumWriter<User> writer =  new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> userDataFileWriter  = new DataFileWriter<>(writer);
        userDataFileWriter.create(user.getSchema(), new File("users.avro"));
        userDataFileWriter.append(user);
        userDataFileWriter.close();
    }
}
