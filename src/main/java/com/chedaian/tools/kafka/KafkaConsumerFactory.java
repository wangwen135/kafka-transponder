package com.chedaian.tools.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.chedaian.tools.config.PropertiesUtil;

public class KafkaConsumerFactory {

    public Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", PropertiesUtil.getString("source.kafka.bootstrap.servers"));
        props.put("group.id", PropertiesUtil.getString("source.kafka.group.id"));
        props.put("enable.auto.commit", "true");
        props.setProperty("auto.offset.reset", PropertiesUtil.getString("source.kafka.auto.offset.reset")); // latest
        props.put("auto.commit.interval.ms", PropertiesUtil.getString("source.kafka.auto.commit.interval.ms"));
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        return props;
    }

    public KafkaConsumer<String, byte[]> getKafkaConsumer() {
        Properties props = getProperties();
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        return consumer;
    }
}
