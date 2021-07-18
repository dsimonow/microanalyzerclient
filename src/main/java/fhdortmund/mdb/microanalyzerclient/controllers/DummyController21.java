package fhdortmund.mdb.microanalyzerclient.controllers;

import brave.sampler.Sampler;
import fhdortmund.mdb.microanalyzerclient.engine.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Profile("twoone")
@RestController
public class DummyController21 {
    private static final Logger LOG = LoggerFactory.getLogger(DummyController21.class);

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

    @GetMapping(value = "/dummy")
    public String dummyrequest() {
        LOG.info("In Dummy 2 Helfer");
        String url = "http://localhost:9021/dummy";
        String response = "2 Ebenen API Anfrage. Dummy 2 und 2.1";
        LOG.info("Dummy 2 Helfer fertig");
        return "Dummyserver 21 Ergebnis: "+ response;
    }

}

