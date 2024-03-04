package com.uniandes.metapenta.io.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metapenta.model.metabolic.network.Metabolite;
import metapenta.model.metabolic.network.Reaction;
import metapenta.model.petrinet.Edge;
import metapenta.model.petrinet.Transition;

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
    private List<String> edgesIn = new ArrayList<>();

    public MetaboliteDTO(Metabolite metabolite){
        this.compartment = metabolite.getCompartment();
        this.name = metabolite.getName();
        this.id = metabolite.getId();
    }

    public void addEdgesIn(List<Edge<Transition>> edgesIn){
        for(Edge<Transition> edge: edgesIn){
            Transition<Reaction> t = edge.getTarget();
            String id = t.getObject().getId();
            this.addEdgeIn(id);
        }
    }

    public void addEdgesOut(List<Edge<Transition>> edgesOut){
        for(Edge<Transition> edge: edgesOut){
            Transition<Reaction> t = edge.getTarget();
            String id = t.getObject().getId();
            this.addEdgeOut(id);
        }
    }
    private void addEdgeOut(String edgeOut){
        edgesOut.add(edgeOut);
    }

    private void addEdgeIn(String edgeIn){
        edgesIn.add(edgeIn);
    }
}
