package com.lurninghut;

import com.lurninghut.contract.Movie;
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
        Producer<String, Movie> producer = new KafkaProducer
                <>(props);
        Movie movie = Movie.newBuilder().setName("Kaliya").setUid("1").addActors(Movie.Actor.newBuilder().setId("1").setName("amitabh").build()).build();
        producer.send(new ProducerRecord<>(topicName,
                movie.getUid(), movie));
        producer.metrics().forEach((key, value) -> System.out.println(key.name() + " : " + value.metricValue()));
        log.info("Message sent successfully");
        producer.close();
    }
}
