package com.uniandes.metapenta.model.petrinet;

import com.uniandes.metapenta.model.metabolicnetwork.Metabolite;
import com.uniandes.metapenta.model.metabolicnetwork.Reaction;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PetriNet implements IPetriNet {
    private Map<String, Place<Metabolite>> places;
    private Map<String, Transition<Reaction>> transitions;

    public PetriNet(){
        this.places = new TreeMap<>();
        this.transitions = new TreeMap<>();
    }

    public Transition getTransition(String key){
        return transitions.get(key);
    }

    public void AddTransition(String id, Transition<Reaction> transition){
        this.transitions.put(id, transition);
    }
    @Override
    public List<Place<?>> getSources() {
        return getPlacesByStatus(Place.SOURCE);
    }

    @Override
    public List<Place<?>> getSinks() {
        return getPlacesByStatus(Place.SINK);
    }


    public void addPlace(String key, Place place){
        places.put(key, place);
    }

    public Place getPlace(String key){
        return places.get(key);
    }

    private List<Place<?>> getPlacesByStatus(String status){
        List<Place<?>> placesByCriteria = new ArrayList<>();
        for (String key : places.keySet()) {
            if (places.get(key).isStatus(status)){
                placesByCriteria.add(places.get(key));
            }
        }

        return placesByCriteria;
    }
}
