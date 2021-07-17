package fhdortmund.mdb.microanalyzerclient.engine;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "USERS";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(JSONObject message){
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC,"test", message.toString());
    }
}
