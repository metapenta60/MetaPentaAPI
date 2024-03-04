package com.uniandes.metapenta.io.dtos;

import lombok.Getter;
import lombok.Setter;
import metapenta.model.metabolic.network.Reaction;
import metapenta.model.metabolic.network.ReactionComponent;
import metapenta.model.petrinet.Edge;
import metapenta.model.petrinet.Place;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReactionDTO {
    private String name;
    private String id;

    private List<ReactionComponentDTO> reactants = new ArrayList<>();
    private List<ReactionComponentDTO> products = new ArrayList<>();

    private List<String> edgesOut = new ArrayList<>();
    private List<String> edgesIn = new ArrayList<>();

    public ReactionDTO(Reaction reaction){
        this.name = reaction.getName();
        this.id = reaction.getId();
        loadReactants(reaction);
        loadProducts(reaction);
    }

    private void loadProducts(Reaction reaction){
        List<ReactionComponent> reactions = reaction.getProducts();
        for (ReactionComponent r: reactions){
            MetaboliteDTO m = new MetaboliteDTO(r.getMetabolite());
            products.add(new ReactionComponentDTO(m.getId(), r.getStoichiometry()));
        }
    }

    private void loadReactants(Reaction reaction){
        List<ReactionComponent> reactions = reaction.getReactants();
        for (ReactionComponent r: reactions){
            MetaboliteDTO m = new MetaboliteDTO(r.getMetabolite());
            reactants.add(new ReactionComponentDTO(m.getId(), r.getStoichiometry()));
        }
    }

    public void addEdgesIn(List<Edge<Place>> edgesIn){
        for(Edge<Place> ep: edgesIn){
            String id = ep.getTarget().getID();
            addEdgeIn(id);
        }
    }
    public void addEdgesOut(List<Edge<Place>> edgesOut){
        for(Edge<Place> ep: edgesOut){
            String id = ep.getTarget().getID();
            addEdgeOut(id);
        }
    }

    public void addEdgeOut(String edgeOut){
        edgesOut.add(edgeOut);
    }

    public void addEdgeIn(String edgeOut){
        edgesIn.add(edgeOut);
    }
}
