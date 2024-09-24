package com.uniandes.metapenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;
import com.uniandes.metapenta.service.MetaPentaService;


@RestController
public class MetapentaController {
    @Autowired
    private MetaPentaService service;

    @GetMapping("/{model}")
    public MetabolicNetworkDTO getModel(@PathVariable String model) throws Exception {
        return service.loadModel(model);
    }

    @PostMapping("/upload")
    public ResponseEntity<MetabolicNetworkDTO> processFile(@RequestParam("file") MultipartFile file) {
        try {
            // Your file processing logic here
            MetabolicNetworkDTO networkDTO = service.uploadFile(file);
            return ResponseEntity.ok(networkDTO);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Ensure a response is returned
        }
    }

    
    
}
