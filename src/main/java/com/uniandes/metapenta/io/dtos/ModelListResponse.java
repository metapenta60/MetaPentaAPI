package com.uniandes.metapenta.io.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelListResponse {
    @JsonProperty("results_count")
    private int resultsCount;
    
    @JsonProperty("results")
    private List<Model> results;


}
