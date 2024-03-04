package com.uniandes.metapenta.io.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReactionComponentDTO {

    private String metaboliteID;

    private double stoichiometry;

}
