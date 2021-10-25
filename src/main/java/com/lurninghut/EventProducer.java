package com.lurninghut;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class EventProducer {
    public static void main(String[] args) {
        String topicName = "actors";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer
                <>(props);
        producer.send(new ProducerRecord<>(topicName,
                "actor-1", "amitabh"));
        System.out.println("Message sent successfully");
        producer.close();
    }
}
