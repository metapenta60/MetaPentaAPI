package com.uniandes.metapenta.model.petrinet;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Transition<A> {
    private String ID;
    private String label;
    private A object;
    List<Edge> edgesIn;
    List<Edge> edgesOut;

    public Transition(String id, String label, A object) {
        ID = id;
        this.label = label;
        this.object = object;
        this.edgesIn = new ArrayList<>();
        this.edgesOut = new ArrayList<>();
    }

    public void AddEdgesIn(List<Edge<?>> edges){
        for(Edge edge: edges){
            this.edgesIn.add(edge);
        }
    }
    public void AddEdgeOut(Edge<?> edge){
        this.edgesOut.add(edge);
    }

    public void AddEdgesOut(List<Edge> edgesOut){
        for(Edge edge: edgesOut){
            this.edgesOut.add(edge);
        }
    }

}
