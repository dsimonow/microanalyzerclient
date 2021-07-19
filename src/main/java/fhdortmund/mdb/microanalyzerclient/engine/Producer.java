package fhdortmund.mdb.microanalyzerclient.engine;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "API";
    private static final String InitTOPIC = "InitAPI";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(JSONObject message){
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC,"test", message.toString());
    }
    public void sendMessageInit(JSONObject message){
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(InitTOPIC,"init", message.toString());
    }

    public void instantiateInNeo4j(String appName){
        JSONObject result = new JSONObject();
        result.put("name", appName);
        sendMessageInit(result);
    }

    public JSONObject makeJSON(String message){
        JSONObject result = new JSONObject();
        result.put("name", "testname");
        result.put("userName", message);
        result.put("userSurname", "Santurbano");
        result.put("productId", 100);
        result.put("productName", "My Awesome Product!");
        result.put("price", 10);
        result.put("currency", "€");
        return result;
    }

    public JSONObject makeRelationshipJSON(String name, String apibefehl, String zielname){
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("apibefehl", apibefehl);
        result.put("zielname", zielname);
        return result;
    }


}
