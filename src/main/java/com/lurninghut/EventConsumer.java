package com.lurninghut;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lurninghut.contract.Movie;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EventConsumer {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        String topicName = "actors";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("group.id", "test");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer");
        props.put("schema.registry.url", "http://0.0.0.0:8085");
        KafkaConsumer<String, Movie> consumer = new KafkaConsumer
                <>(props);
        consumer.subscribe(Collections.singletonList(topicName));
        ConsumerRecords<String, Movie> records = consumer.poll(Duration.ofMillis(5000));
        for (ConsumerRecord<String, Movie> record : records){
            System.out.printf("offset = %d, key = %s, value = %s\n",
                    record.offset(), record.key(), record.value());
        }
        consumer.commitSync();
    }
}
