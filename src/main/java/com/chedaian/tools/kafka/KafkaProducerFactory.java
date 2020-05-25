package com.chedaian.tools.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.chedaian.tools.config.PropertiesUtil;

public class KafkaProducerFactory {

    public Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", PropertiesUtil.getString("dest.kafka.bootstrap.servers"));
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        return props;
    }

    public Producer<String, byte[]> getKafkaProducer() {
        Properties props = getProperties();
        Producer<String, byte[]> producer = new KafkaProducer<>(props);
        return producer;
    }
}
