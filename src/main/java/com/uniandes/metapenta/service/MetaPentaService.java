package com.uniandes.metapenta.service;

import com.uniandes.metapenta.io.BIGGClient;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;
import metapenta.services.MetabolicNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class MetaPentaService {

    @Autowired
    BIGGClient client;


    // public MetabolicNetworkDTO loadModel(String model) throws Exception {
    //     InputStream is = new FileInputStream("/Users/pabluchenko/2024_1/jorge/MetaPentaAPI/src/main/java/com/uniandes/metapenta/service/e_coli_core.xml");
    //     MetabolicNetworkService ms = new MetabolicNetworkService(is);

    //     return new MetabolicNetworkDTO(ms.getNetwork());
    // }

    public MetabolicNetworkDTO processUploadedFile(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream()) {
            MetabolicNetworkService ms = new MetabolicNetworkService(is);
            return new MetabolicNetworkDTO(ms.getNetwork());
        }
    }
}
