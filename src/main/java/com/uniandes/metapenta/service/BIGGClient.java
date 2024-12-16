package com.uniandes.metapenta.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uniandes.metapenta.io.dtos.Model;
import com.uniandes.metapenta.io.dtos.ModelListResponse;

@Service
public class BIGGClient {

    private static final String BASE_URL = "http://bigg.ucsd.edu/api/v2";
    private static final String MODEL_LIST_ENDPOINT = BASE_URL + "/models";
    private static final String MODEL_DOWNLOAD_URL = "http://bigg.ucsd.edu/static/models/";

    @Autowired
    @Qualifier("customRestTemplate") 
    private final RestTemplate restTemplate;

    public BIGGClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Model> getModelsList() {
        ResponseEntity<ModelListResponse> response = restTemplate.getForEntity(MODEL_LIST_ENDPOINT, ModelListResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getResults();
        } else {
            throw new RuntimeException("Failed to fetch model list from BiGG");
        }
    }

    public InputStream downloadModel(String modelId, String format) {
        String url = MODEL_DOWNLOAD_URL + modelId + "." + format;
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return new ByteArrayInputStream(responseEntity.getBody());
        } else {
            throw new RuntimeException("Failed to download model: " + modelId);
        }
    }
}
