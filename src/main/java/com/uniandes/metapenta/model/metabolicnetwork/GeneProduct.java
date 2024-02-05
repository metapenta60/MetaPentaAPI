package com.uniandes.metapenta.model.metabolicnetwork;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a gene product (usually an enzyme) that participates in one reaction
 * @author Jorge Duitama
 */

@Getter
@Setter
public class GeneProduct implements Comparable<GeneProduct>{
	private String id;
	private String name;
	private String label;
	
	/**
	 * Builds a new gene product
	 * @param id of the product
	 * @param name of the product
	 */
	public GeneProduct(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public int compareTo(GeneProduct o) {	
		return this.id.compareTo(o.id);
	}
	@Override
	public String toString() {
		return "GeneProduct [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
