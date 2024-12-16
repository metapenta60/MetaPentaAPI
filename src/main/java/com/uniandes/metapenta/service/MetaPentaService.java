package com.uniandes.metapenta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uniandes.metapenta.io.dtos.CustomShortestPathsDTO;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;

import metapenta.model.MetabolicNetwork;
import metapenta.services.MetabolicNetworkService;
import metapenta.services.ShortestPathService;
import metapenta.services.dto.MetaboliteReactionsDTO;
import metapenta.services.dto.PathsDTO;
import metapenta.services.dto.ShortestPathsDTO;

@Service
public class MetaPentaService {

    @Autowired
    BIGGClient client;


    public MetabolicNetworkDTO uploadFile(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return new MetabolicNetworkDTO(ms.getNetwork());
        }
    }

    public MetabolicNetworkDTO downloadAndProcessModel(String modelId, String format) throws Exception {
        try (InputStream modelStream = client.downloadModel(modelId, format)) {
            MetabolicNetworkService ms = new MetabolicNetworkService(modelStream);
            return new MetabolicNetworkDTO(ms.getNetwork());
        }
    }

    public MetaboliteReactionsDTO getMetaboliteReactions(MultipartFile file, String metaboliteId) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return ms.getMetaboliteReactions(metaboliteId);
        }
    }

    public CustomShortestPathsDTO getShortestPaths(MultipartFile file, String originId, String destinationId) throws Exception {
        File tempFile = File.createTempFile("metabolic_network", ".xml");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
    
        try {
            MetabolicNetwork metabolicNetwork = MetabolicNetwork.load(tempFile.getAbsolutePath());
    
            ShortestPathService shortestPathService = new ShortestPathService();
            shortestPathService.setMetabolicNetwork(metabolicNetwork);
    
            ShortestPathsDTO shortestPaths = shortestPathService.getShortestPath(originId, destinationId);
    
            return new CustomShortestPathsDTO(
                shortestPaths.getOriginId(),
                shortestPaths.getDestinationId(),
                shortestPaths.getPath()
            );
        } finally {
            tempFile.delete();
        }
    }

    public PathsDTO getAllPaths(MultipartFile file, List<String> metaboliteIds, String targetId) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return ms.getAllPaths(metaboliteIds, targetId);
        }
    }
}
