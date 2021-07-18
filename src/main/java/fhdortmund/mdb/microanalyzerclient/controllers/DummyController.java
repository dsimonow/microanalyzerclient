package fhdortmund.mdb.microanalyzerclient.controllers;

import brave.sampler.Sampler;
import fhdortmund.mdb.microanalyzerclient.engine.Producer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Profile("one")
@RestController
public class DummyController {
    private static final Logger LOG = LoggerFactory.getLogger(DummyController.class);

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
        LOG.info("In Dummy 1");
        String response = "1 Ebene Tiefe Anfrage";
        //String url = "http://localhost:9002/dummy";
        //String response = (String) restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        return "Dummyserver 1 Ergebnis: "+ response;
    }

}

