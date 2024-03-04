package com.uniandes.metapenta.io.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metapenta.model.metabolic.network.Metabolite;
import metapenta.model.metabolic.network.Reaction;
import metapenta.model.networks.MetabolicNetwork;
import metapenta.model.petrinet.Edge;
import metapenta.model.petrinet.Place;
import metapenta.model.petrinet.Transition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MetabolicNetworkDTO {

    private List<ReactionDTO> reactions = new ArrayList<>();

    private List<MetaboliteDTO> metabolites = new ArrayList<>();

    public MetabolicNetworkDTO(MetabolicNetwork mn){
        loadMetabolites(mn);
        loadReactions(mn);
    }

    private void loadReactions(MetabolicNetwork mn){
        Collection<Transition<Reaction>> transitions = mn.getTransitions().values();
        for(Transition<Reaction> transition: transitions){
            ReactionDTO reaction = new ReactionDTO(transition.getObject());
            this.reactions.add(reaction);
            addEdgesToReaction(reaction,transition);
        }
    }

    private void addEdgesToReaction(ReactionDTO r, Transition<Reaction> t){
        List<Edge<Place>> edgesOut = t.getEdgesOut();
        r.addEdgesOut(edgesOut);

        List<Edge<Place>> edgesIn = t.getEdgesIn();
        r.addEdgesIn(edgesIn);
    }

    private void loadMetabolites(MetabolicNetwork mn){
       Collection<Place<Metabolite>> places = mn.getPlaces().values();
       for(Place<Metabolite> place: places){
           MetaboliteDTO mdto = new MetaboliteDTO(place.getObject());
           this.metabolites.add(mdto);
           addEdgesToMetabolite(mdto, place);
       }
    }

    private void addEdgesToMetabolite(MetaboliteDTO mdto, Place<Metabolite> place){
        List<Edge<Transition>> edgesOut = place.getEdgesOut();
        mdto.addEdgesOut(edgesOut);

        List<Edge<Transition>> edgesIn = place.getEdgesIn();
        mdto.addEdgesIn(edgesIn);
    }
}
