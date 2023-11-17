package com.uniandes.metapenta;

import com.uniandes.metapenta.model.petrinet.PetriNet;
import com.uniandes.metapenta.service.MetaPentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetapentaController {
    @Autowired
    private MetaPentaService metaPenta;

    @GetMapping("/{model}")
    public PetriNet getPetriNet(@PathVariable String model){
        return metaPenta.getPetriNet(model);
    }

}
