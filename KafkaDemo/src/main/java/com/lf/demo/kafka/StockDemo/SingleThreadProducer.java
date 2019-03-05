package com.lf.demo.kafka.StockDemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class SingleThreadProducer {
    private static final int MSG_SIZE = 100;
    private static final String TOPIC =  "stock-quotation";

    private static final String BROKER_LIST =  "tcp://localhost:9092";
    private static KafkaProducer producer =  null;
    static {
        Properties  configs =  initConfig();
        producer =  new KafkaProducer<String, String >(configs);
    }

    private static Properties initConfig(){
        Properties properties =  new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        return properties;
    }
    private static StockQuotationInfo createQuotationInfo(){
        Random r =  new Random();
        return StockQuotationInfo.builder()
                .stockCode(String.valueOf( 6100+ r.nextInt()) )
                .tradeTime(System.currentTimeMillis())
                .build();
    }
    static public void main(String[] args){
        log.info("Start Single Thread Producer...");
        ProducerRecord<String, String> record = null;
        StockQuotationInfo info = null;
        try{
            for(int i=0; i < MSG_SIZE; i++){
                info =  createQuotationInfo();
                record =  new ProducerRecord<>(TOPIC, null,
                        info.getTradeTime(),
                        info.getStockCode(),
                        info.toString());
                Future<RecordMetadata> future =
                  producer.send(record, (metadata, ex)-> {
                    if(null != ex){
                        log.error("Send message error" , ex);
                    }
                    if(null !=  metadata){
                        log.info(String.format("offset:%s, partition:%s", metadata.offset(), metadata.partition()));
                    }
                });
                RecordMetadata recordMetadata = future.get();
                log.info("Get metadata from futuer: " +recordMetadata.toString());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
