package com.uniandes.metapenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;
import com.uniandes.metapenta.service.MetaPentaService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MetapentaController {
    @Autowired
    private MetaPentaService service;

    @GetMapping("/{model}")
    public MetabolicNetworkDTO getModel(@PathVariable String model) throws Exception {
        return service.loadModel(model);
    }

    // @PostMapping("/upload")
    // public ResponseEntity<MetabolicNetworkDTO> uploadFile(@RequestParam("file") MultipartFile file) {
    //     try {
    //         MetabolicNetworkDTO networkDTO = service.processUploadedFile(file);
    //         return ResponseEntity.ok(networkDTO);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    
    
}
