package com.uniandes.metapenta;

import metapenta.services.InterceptMetabolicNetworksService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MetapentaController {


    @GetMapping("/{model}")
    public String getPetriNet(@PathVariable String model){
        return new String();
    }

}
