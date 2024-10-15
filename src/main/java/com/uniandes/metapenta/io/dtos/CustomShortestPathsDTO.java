package com.uniandes.metapenta.io.dtos;

import java.util.HashMap;
import java.util.Map;

import metapenta.model.Metabolite;
import metapenta.model.petrinet.Place;
import metapenta.model.petrinet.Transition;
import metapenta.services.dto.ShortestPathsDTO;

public class CustomShortestPathsDTO {

    private Map<String, String[]> paths = new HashMap<>();

    // Constructor to map data from ShortestPathsDTO
    public CustomShortestPathsDTO(Map<String, String[]> originalPaths) {
        this.paths = originalPaths;
    }

    public Map<String, String[]> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, String[]> paths) {
        this.paths = paths;
    }

    public void addPath(String metaboliteId, String[] path) {
        this.paths.put(metaboliteId, path);
    }

}