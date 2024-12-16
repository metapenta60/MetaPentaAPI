package com.uniandes.metapenta.io.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Model {
    @JsonProperty("bigg_id")
    private String biggId;
    
    @JsonProperty("organism")
    private String organism;
    
    @JsonProperty("metabolite_count")
    private int metaboliteCount;
    
    @JsonProperty("reaction_count")
    private int reactionCount;
    
    @JsonProperty("gene_count")
    private int geneCount;

}
