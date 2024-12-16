package com.uniandes.metapenta.io.dtos;

import java.util.List;

public class CustomShortestPathsDTO {

    private String originId;
    private String destinationId;
    private List<String> path;

    // Constructor
    public CustomShortestPathsDTO(String originId, String destinationId, List<String> path) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.path = path;
    }

    // Getters and setters
    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}