package com.lurninghut;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EventProducer {
    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);
    public static void main(String[] args) throws IOException {
        String topicName = "actors";
        Properties props = new Properties();
        props.load(new FileReader("producer.properties"));
        Producer<String, String> producer = new KafkaProducer
                <>(props);
        producer.send(new ProducerRecord<>(topicName,
                "actor-1", "amitabh"));
        producer.metrics().forEach((key, value) -> System.out.println(key.name() + " : " + value.metricValue()));
        log.info("Message sent successfully");
        producer.close();
    }
}
