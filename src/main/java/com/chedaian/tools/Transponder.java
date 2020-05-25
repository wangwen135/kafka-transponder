package com.chedaian.tools;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chedaian.tools.config.PropertiesUtil;
import com.chedaian.tools.handler.MessageHandler;
import com.chedaian.tools.handler.MessageHandlerFactory;
import com.chedaian.tools.kafka.KafkaConsumerFactory;
import com.chedaian.tools.kafka.KafkaProducerFactory;

public class Transponder {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private KafkaConsumer<String, byte[]> consumer;
    private Producer<String, byte[]> producer;

    private MessageHandler messageHandler;

    public Transponder(){
        consumer = new KafkaConsumerFactory().getKafkaConsumer();
        producer = new KafkaProducerFactory().getKafkaProducer();
        messageHandler = new MessageHandlerFactory().getHandler();
    }

    public void startTransport() {
        logger.info("开始数据传输...");

        // 原队列
        String sourceTopic = PropertiesUtil.getString("source.kafka.topic");
        consumer.subscribe(Arrays.asList(sourceTopic));

        // 目标队列
        String destTopic = PropertiesUtil.getString("dest.kafka.topic");

        logger.info("将数据从原队列【{}】转发到目标队列【{}】", sourceTopic, destTopic);

        long receiveCount = 0;
        long transferCount = 0;

        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(100);
            for (ConsumerRecord<String, byte[]> record : records) {
                if (receiveCount > 0 && receiveCount % 1000 == 0) {
                    logger.info("已消费数据【{}】条，转发数据【{}】条", receiveCount, transferCount);
                }

                receiveCount++;

                byte[] sendValue = record.value();

                if (messageHandler != null) {
                    sendValue = messageHandler.handle(sendValue);
                }

                if (sendValue == null) {
                    continue;
                }

                transferCount++;
                producer.send(new ProducerRecord<String, byte[]>(destTopic, record.key(), sendValue));
            }

        }
    }
}
