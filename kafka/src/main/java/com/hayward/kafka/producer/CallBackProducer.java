package com.hayward.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class CallBackProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.120.125:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");



        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("taopic","namekey", "hello"+ i);//topic，value
            producer.send(record, ((recordMetadata, e) -> {
                if(e==null){
                    System.out.println(recordMetadata.partition()+"----"+recordMetadata.offset());
                }
            }));
            //producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i))); //topic, key, value
        }

        producer.close();//不要忘记close
    }
}
