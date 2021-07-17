package fhdortmund.mdb.microanalyzerclient.controllers;

import fhdortmund.mdb.microanalyzerclient.engine.Producer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "kafka")
public class KafkaController {
    private int counter = 0;

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publishthis")
    public void sendThisMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(makeJSON(message));
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(makeJSON(message));
    }

    public JSONObject makeJSON(String message){
        JSONObject result = new JSONObject();
        result.put("userId", ++counter);
        result.put("userName", message);
        result.put("userSurname", "Santurbano");
        result.put("productId", 100);
        result.put("productName", "My Awesome Product!");
        result.put("price", 10);
        result.put("currency", "€");
        return result;
    }
}
