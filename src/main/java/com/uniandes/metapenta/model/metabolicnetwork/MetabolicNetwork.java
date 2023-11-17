package com.uniandes.metapenta.model.metabolicnetwork;


import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Represents a metabolic network of reactions on metabolites
 * @author Jorge Duitama
 */

@Component
public class MetabolicNetwork {
	private Map<String,Reaction> reactions = new TreeMap();
	private Map<String,Metabolite> metabolites = new TreeMap();
	private Map<String,GeneProduct> geneProducts = new TreeMap();
	private Set<String> compartments = new TreeSet();

	public void addReaction(Reaction r) {
		reactions.put(r.getId(),r);
	}
	public Metabolite getMetabolite (String id) {
		return metabolites.get(id);
	}

	public void addGeneProduct(GeneProduct product) {
		geneProducts.put(product.getId(), product);
	}

	public GeneProduct getGeneProduct (String id) {
		return geneProducts.get(id);
	}

	public void addMetabolite(Metabolite metabolite) {
		metabolites.put(metabolite.getId(), metabolite);
		compartments.add(metabolite.getCompartment());
	}

	public List<String> getReactionIds(){
		List<String> reactionIds = new ArrayList();
		Set<String> keys = reactions.keySet();

		for(String key: keys) {
			reactionIds.add(key);
		}

		return reactionIds;
	}

	public Reaction getReaction(String id) {
		return reactions.get(id);
	}

}