package com.chedaian.tools.test.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.224:9091,192.168.1.224:9092,192.168.1.224:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("开始发送100条消息.....");

        for (int i = 0; i < 100; i++) {
            producer
                .send(new ProducerRecord<String, String>("st.topic.yery.test", Integer.toString(i), "message id:" + i));

        }
        System.out.println("消息发送完成！");

        producer.close();
    }
}
