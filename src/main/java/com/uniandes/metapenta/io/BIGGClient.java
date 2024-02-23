package com.uniandes.metapenta.io;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class BIGGClient {
    private final static String URL = "http://bigg.ucsd.edu/static/models/";

    public InputStream downloadModel(String model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.execute(
                buildURL(model),
                org.springframework.http.HttpMethod.GET,
                null,
                restTemplate.responseEntityExtractor(byte[].class)
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            byte[] xmlBytes = responseEntity.getBody();
            InputStream inputStream = new ByteArrayInputStream(xmlBytes);
            return inputStream;
        }

        return null;
    }

    private String buildURL(String model){
        return  URL + model + ".xml";
    }
}