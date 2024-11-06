
package com.uniandes.metapenta.io.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import metapenta.model.Reaction;
import metapenta.model.ReactionComponent;

@Getter
@Setter
public class ReactionDTO {
    private String name;
    private String id;

    private List<ReactionComponentDTO> reactants = new ArrayList<>();
    private List<ReactionComponentDTO> products = new ArrayList<>();

    private List<String> edgesOut = new ArrayList<>();

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
            products.add(new ReactionComponentDTO(m, r.getStoichiometry()));
        }
    }

    private void loadReactants(Reaction reaction){
        List<ReactionComponent> reactions = reaction.getReactants();
        for (ReactionComponent r: reactions){
            MetaboliteDTO m = new MetaboliteDTO(r.getMetabolite());
            reactants.add(new ReactionComponentDTO(m, r.getStoichiometry()));
        }
    }

    public void addEdgeOut(String edgeOut){
        edgesOut.add(edgeOut);
    }
}
