package com.uniandes.metapenta.io.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metapenta.model.Metabolite;
import metapenta.model.Reaction;
import metapenta.model.MetabolicNetwork;
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

    public MetabolicNetworkDTO(MetabolicNetwork metabolicNetwork){
        loadMetabolites(metabolicNetwork);
        loadReactions(metabolicNetwork);
    }

    private void loadReactions(MetabolicNetwork mn){
        Collection<Transition<Reaction>> transitions = mn.getTransitions().values();
        for(Transition<Reaction> t: transitions){
            ReactionDTO r = new ReactionDTO(t.getObject());
            this.reactions.add(r);

            List<Edge<Place>> edgesOut = t.getEdgesOut();
            for(Edge<Place> ep: edgesOut){
                r.addEdgeOut(ep.getTarget().getID());
            }
        }
    }

    private void loadMetabolites(MetabolicNetwork mn){
       Collection<Place<Metabolite>> places = mn.getPlaces().values();
       for(Place<Metabolite> place: places){
           MetaboliteDTO mdto = new MetaboliteDTO(place.getObject());
           this.metabolites.add(mdto);

           List<Edge<Transition>> edgesOut = place.getEdgesOut();
           for (Edge<Transition> edge: edgesOut){
               Transition<Reaction> reaction = edge.getTarget();
               mdto.addEdgeOut(reaction.getID());
           }
       }
    }
}
