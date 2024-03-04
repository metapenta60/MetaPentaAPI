package com.uniandes.metapenta.service;

import com.uniandes.metapenta.io.BIGGClient;
import com.uniandes.metapenta.io.dtos.MetabolicNetworkDTO;
import metapenta.services.MetabolicNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class MetaPentaService {

    @Autowired
    BIGGClient client;

    public MetabolicNetworkDTO loadModel(String model) throws Exception {
        InputStream is = new FileInputStream("C:\\Users\\v25a0\\OneDrive\\Documents\\Repositories\\Flag\\MetaPentaAPI\\src\\main\\java\\examples\\e_coli_core.xml");
        //InputStream is = client.downloadModel(model);
        MetabolicNetworkService ms = new MetabolicNetworkService(is);

        return new MetabolicNetworkDTO(ms.getNetwork());
    }
}
