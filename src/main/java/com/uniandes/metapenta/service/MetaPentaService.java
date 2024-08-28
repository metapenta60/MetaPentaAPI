package com.uniandes.metapenta.service;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniandes.metapenta.io.BIGGClient;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;

import metapenta.services.MetabolicNetworkService;

@Service
public class MetaPentaService {

    @Autowired
    BIGGClient client;


    public MetabolicNetworkDTO loadModel(String model) throws Exception {
        InputStream is = new FileInputStream("/home/pabluchenko/2024_2/tesis/MetaPentaAPI/src/main/java/examples/e_coli_core.xml");
        MetabolicNetworkService ms = new MetabolicNetworkService(is);

        return new MetabolicNetworkDTO(ms.getNetwork());
    }

    // public MetabolicNetworkDTO processUploadedFile(MultipartFile file) throws Exception {
    //     try (InputStream is = file.getInputStream()) {
    //         MetabolicNetworkService ms = new MetabolicNetworkService(is);
    //         return new MetabolicNetworkDTO(ms.getNetwork());
    //     }
    // }
}
