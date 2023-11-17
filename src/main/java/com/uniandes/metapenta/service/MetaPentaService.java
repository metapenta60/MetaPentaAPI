package com.uniandes.metapenta.service;

import com.uniandes.metapenta.io.MetabolicNetworkXMLLoader;
import com.uniandes.metapenta.model.metabolicnetwork.MetabolicNetwork;
import com.uniandes.metapenta.model.metabolicnetwork.Metabolite;
import com.uniandes.metapenta.model.metabolicnetwork.Reaction;
import com.uniandes.metapenta.model.metabolicnetwork.ReactionComponent;
import com.uniandes.metapenta.model.petrinet.Edge;
import com.uniandes.metapenta.model.petrinet.PetriNet;
import com.uniandes.metapenta.model.petrinet.Place;
import com.uniandes.metapenta.model.petrinet.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaPentaService {
    @Autowired
    private MetabolicNetworkXMLLoader loader;



    @Autowired
    private PetriNet petriNet;
    public PetriNet getPetriNet(String model){
        loadPetriNet(model);
        return petriNet;
    }

    private void loadPetriNet(String model){
        try {
            MetabolicNetwork network = loader.loadNetwork(model);
            loadPetriNet(network);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPetriNet(MetabolicNetwork network){
        List<String> keysReaction = network.getReactionIds();
        for (String key : keysReaction) {
            Reaction reaction = network.getReaction(key);
            Transition transition = this.createAndLoadTransitionToPetriNet(reaction);

            List<ReactionComponent> reactants = reaction.getReactants();
            List<Edge> edgesIn = this.loadMetabolitesAndCreateEdgeList(reactants);
            transition.AddEdgesIn(edgesIn);


            List<ReactionComponent> products = reaction.getProducts();
            List<Edge> edgesOut = this.loadMetabolitesAndCreateEdgeList(products);
            transition.AddEdgesOut(edgesOut);
        }
    }

    private Transition createAndLoadTransitionToPetriNet(Reaction reaction){
        Transition transition = petriNet.getTransition(reaction.getId());

        if ( transition == null ){
            transition = new Transition(reaction.getId(), reaction.getName(), reaction);
            petriNet.AddTransition(reaction.getId(), transition);
        }

        return transition;
    }

    private List<Edge> loadMetabolitesAndCreateEdgeList(List<ReactionComponent> reactionComponents){
        List<Edge> edges = new ArrayList<>();
        for (ReactionComponent reactionComponent : reactionComponents) {
            Metabolite metabolite = reactionComponent.getMetabolite();

            Place<Metabolite> place = petriNet.getPlace(metabolite.getId());
            if (place == null){
                place = createAndAddPlaceToNet(metabolite);
            }

            Edge<Place> edge = new Edge(place, reactionComponent.getStoichiometry());
            edges.add(edge);
        }

        return edges;
    }

    private Place createAndAddPlaceToNet(Metabolite metabolite){
        Place<Metabolite> place = new Place<>(metabolite.getId(), metabolite.getName(), metabolite);
        petriNet.addPlace(metabolite.getId(), place);

        return place;
    }


}
