package com.uniandes.metapenta.service;

import metapenta.tools.io.loaders.MetabolicNetworkXMLLoader;
import metapenta.model.MetabolicNetwork;
import metapenta.model.metabolic.network.*;
import metapenta.model.petrinet.Edge;
import metapenta.model.petrinet.PetriNet;
import metapenta.model.petrinet.Place;
import metapenta.model.petrinet.Transition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetaPentaService {


    private MetabolicNetworkXMLLoader loader;


    private PetriNet petriNet;

    public MetaPentaService( ){
        MetabolicNetworkXMLLoader loader = new MetabolicNetworkXMLLoader();
        PetriNet petriNet = new PetriNet();
        this.loader = loader;
        this.petriNet = petriNet;
    }



    public PetriNet getPetriNet(String model){
        loadPetriNet(model);
        return petriNet;
    }

    private void loadPetriNet(String model){
        try {
            MetabolicNetwork network = loader.loadNetwork(model);
            loadPetriNet(network);
        } catch (Exception e) {
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
