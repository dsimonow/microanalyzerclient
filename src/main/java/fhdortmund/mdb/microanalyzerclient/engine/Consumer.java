package fhdortmund.mdb.microanalyzerclient.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "USERS", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

    @KafkaListener(topics = "zipkin", groupId = "group_id")
    public void consumeTrace(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }


}
