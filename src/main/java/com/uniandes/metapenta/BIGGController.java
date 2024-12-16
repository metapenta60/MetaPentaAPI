package com.uniandes.metapenta;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniandes.metapenta.io.dtos.Model;
import com.uniandes.metapenta.service.BIGGClient;

@RestController
@RequestMapping("/bigg")
public class BIGGController {

    @Autowired
    private BIGGClient biggClient;
    
    @GetMapping("/models")
    public List<Model> listModels() {
        return biggClient.getModelsList();
    }

    @GetMapping("/models/{modelId}/download")
    public ResponseEntity<InputStreamResource> downloadModel(@PathVariable String modelId) {

        String format="xml";
        InputStream modelStream = biggClient.downloadModel(modelId, format);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + modelId + "." + format);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(modelStream));
    }
}