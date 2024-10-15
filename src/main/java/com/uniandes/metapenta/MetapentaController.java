package com.uniandes.metapenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uniandes.metapenta.io.dtos.CustomShortestPathsDTO;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;
import com.uniandes.metapenta.service.MetaPentaService;

import metapenta.services.dto.MetaboliteReactionsDTO;
import metapenta.services.dto.PathsDTO;
import metapenta.services.dto.ShortestPathsDTO;


@RestController
public class MetapentaController {
    @Autowired
    private MetaPentaService service;


    @PostMapping("/upload")
    public ResponseEntity<MetabolicNetworkDTO> processFile(@RequestParam("file") MultipartFile file) {
        try {
            MetabolicNetworkDTO networkDTO = service.uploadFile(file);
            return ResponseEntity.ok(networkDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/metabolite/{metaboliteId}/reactions")
    public ResponseEntity<MetaboliteReactionsDTO> getMetaboliteReactions(@RequestParam("file") MultipartFile file,
                                                                         @PathVariable String metaboliteId) {
        try {
            MetaboliteReactionsDTO metaboliteReactions = service.getMetaboliteReactions(file, metaboliteId);
            return ResponseEntity.ok(metaboliteReactions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/metabolite/{metaboliteId}/shortest-path")
    public ResponseEntity<CustomShortestPathsDTO> getShortestPaths(@RequestParam("file") MultipartFile file,
                                                             @PathVariable String metaboliteId) {
        try {
            CustomShortestPathsDTO shortestPaths = service.getShortestPaths(file, metaboliteId);
            return ResponseEntity.ok(shortestPaths);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/metabolites/paths")
    public ResponseEntity<PathsDTO> getAllPaths(@RequestParam("file") MultipartFile file,
                                                @RequestBody List<String> metaboliteIds,
                                                @RequestParam String targetId) {
        try {
            PathsDTO allPaths = service.getAllPaths(file, metaboliteIds, targetId);
            return ResponseEntity.ok(allPaths);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    
}
