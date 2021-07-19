package fhdortmund.mdb.microanalyzerclient.controllers;

import brave.sampler.Sampler;
import fhdortmund.mdb.microanalyzerclient.engine.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Profile("three")
@RestController
public class DummyController3 {
    private static final Logger LOG = LoggerFactory.getLogger(DummyController3.class);

    String serviceName = "askTwoEndpoint";
    @Autowired
    RestTemplate restTemplate;

    private final Producer producer;
    @Autowired
    DummyController3(Producer producer) {
        this.producer = producer;
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @GetMapping(value = "/dummy")
    public String dummyrequest() {
        LOG.info("In Dummy 3");
        String url = "http://localhost:9002/dummy";
        String response = (String) restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        this.producer.sendMessage(this.producer.makeRelationshipJSON(serviceName, "/dummy2", "extraRequestEndpoint"));
        LOG.info("Antwort von Dummy 3 " + response + "und eine Extra Ebene");
        return "Dummyserver 3 Ergebnis: "+ response;
    }

}

