package com.uniandes.metapenta;

import metapenta.model.petrinet.PetriNet;
import com.uniandes.metapenta.service.MetaPentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MetapentaController {
    @Autowired
    private MetaPentaService metaPenta;

    @GetMapping("/{model}")
    public PetriNet getPetriNet(@PathVariable String model){
        return metaPenta.getPetriNet(model);
    }

}
