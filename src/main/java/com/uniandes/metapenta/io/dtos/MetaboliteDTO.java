package com.uniandes.metapenta.io.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metapenta.model.metabolic.network.Metabolite;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaboliteDTO {
    private String id;

    private String name;

    private String compartment;

    private List<String> edgesOut = new ArrayList<>();

    public MetaboliteDTO(Metabolite metabolite){
        this.compartment = metabolite.getCompartment();
        this.name = metabolite.getName();
        this.id = metabolite.getId();
    }
    public void addEdgeOut(String edgeOut){
        edgesOut.add(edgeOut);
    }
}
