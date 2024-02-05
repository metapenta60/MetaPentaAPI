package com.uniandes.metapenta.model.metabolicnetwork;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metabolite {
	private String id;
	private String name;
	private String compartment;
	private String chemicalFormula;	

	public Metabolite(String id, String name, String compartment) {
		this.id = id;
		this.name = name;
		this.compartment = compartment;
	}


}
