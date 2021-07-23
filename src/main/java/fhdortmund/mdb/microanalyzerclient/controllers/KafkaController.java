package fhdortmund.mdb.microanalyzerclient.controllers;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import brave.sampler.Sampler;
import fhdortmund.mdb.microanalyzerclient.engine.Producer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@Profile("default")
@RequestMapping(value = "kafka")
public class KafkaController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    String serviceName = "mainService";

    private int counter = 0;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/init")
    public void init() {
        LOG.info("Initstart");
        initSequence();
        LOG.info("Init Fertig");
    }

    @GetMapping(value = "/dummy1")
    public String dummyrequest1() {
        LOG.info("Dummy 1 anfragen");
        String url = "http://localhost:9001/dummy";
        String response = (String) restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        this.producer.sendMessage(this.producer.makeRelationshipJSON(serviceName, "/dummy1", "simpleEndpoint"));
        LOG.info("Antwort von Dummy 1 " + response);
        return "Dummyserver 1 Ergebnis: "+ response;
    }

    @GetMapping(value = "/dummy2")
    public String dummyrequest2() {
        LOG.info("Dummy 2 anfragen");
        String url = "http://localhost:9002/dummy";
        String response = (String) restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        this.producer.sendMessage(this.producer.makeRelationshipJSON(serviceName, "/dummy2", "extraRequestEndpoint"));
        LOG.info("Antwort von Dummy 1 " + response);
        return "Dummyserver 2 Ergebnis: "+ response;
    }
    @GetMapping(value = "/dummy3")
    public String dummyrequest3() {
        LOG.info("Dummy 3 anfragen");
        String url = "http://localhost:9003/dummy";
        String response = (String) restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        this.producer.sendMessage(this.producer.makeRelationshipJSON(serviceName, "/dummy3", "askTwoEndpoint"));
        LOG.info("Antwort von Dummy 1 " + response);
        return "Dummyserver 3 Ergebnis: "+ response;
    }

    @GetMapping(value = "/publishthis")
    public void sendThisMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(producer.makeJSON(message));
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(producer.makeJSON(message));
    }

    public void initSequence(){
        this.producer.instantiateInNeo4j(serviceName);
        this.producer.instantiateInNeo4j("simpleEndpoint");
        this.producer.instantiateInNeo4j("extraRequestEndpoint");
        this.producer.instantiateInNeo4j("extraRequestEndpointHelper");
        this.producer.instantiateInNeo4j("askTwoEndpoint");
    }

}
