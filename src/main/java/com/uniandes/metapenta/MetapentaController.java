package com.uniandes.metapenta;

import com.uniandes.metapenta.model.petrinet.PetriNet;
import com.uniandes.metapenta.services.MetaPentaService;
import com.uniandes.metapenta.services.AsciiFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetapentaController {

    @Autowired
    private MetaPentaService metaPenta;

    @Autowired
    private AsciiFaceService asciiFaceService;

    @GetMapping("/{model}")
    public PetriNet getPetriNet(@PathVariable String model){
        return metaPenta.getPetriNet(model);
    }


    @GetMapping("/faces")
    public List<String> getAsciiFaces(){
        return asciiFaceService.getAsciiFaces();
    }
}
