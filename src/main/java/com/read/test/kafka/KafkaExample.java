package com.read.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by bjyfwangrui on 2016/6/20.
 * <p>
 * kafka example
 */
public class KafkaExample {

    @Test
    public void testProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.23.3:9091");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 100; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<>("kafka-test", String.valueOf(i), "hello world"), (metadata, exception) -> exception.printStackTrace());
            try {
                RecordMetadata data = future.get();
                System.out.println(data);
                Thread.sleep(1000);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }

    @Test
    public void testConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.23.3:9091,192.168.23.3:9092,192.168.23.3:9093");
        props.put("group.id", "group-3");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("kafka-test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                System.out.println("\n");
            }
        }
    }
}
