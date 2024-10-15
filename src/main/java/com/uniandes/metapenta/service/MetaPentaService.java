package com.uniandes.metapenta.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uniandes.metapenta.io.BIGGClient;
import com.uniandes.metapenta.io.dtos.CustomShortestPathsDTO;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;

import metapenta.services.MetabolicNetworkService;
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

    // Get metabolite reactions from the uploaded file
    public MetaboliteReactionsDTO getMetaboliteReactions(MultipartFile file, String metaboliteId) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return ms.getMetaboliteReactions(metaboliteId);
        }
    }

    public CustomShortestPathsDTO getShortestPaths(MultipartFile file, String metaboliteId) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            ShortestPathsDTO shortestPaths = ms.getShortestPaths(metaboliteId);

            return new CustomShortestPathsDTO(shortestPaths.getPaths());
        }
    }

    public PathsDTO getAllPaths(MultipartFile file, List<String> metaboliteIds, String targetId) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return ms.getAllPaths(metaboliteIds, targetId);
        }
    }
}
