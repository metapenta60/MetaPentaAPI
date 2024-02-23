package com.uniandes.metapenta.model;

import metapenta.model.petrinet.PetriNet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetriNetConfig {

    @Bean
    public PetriNet petriNet() {
        // Return an instance of PetriNet
        return new PetriNet();
    }
}
