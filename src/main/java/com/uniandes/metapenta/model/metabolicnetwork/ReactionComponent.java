package com.uniandes.metapenta.model.metabolicnetwork;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionComponent {
	private Metabolite metabolite;
	private double stoichiometry;
	/**
	 * Creates a reaction component with the given data
	 * @param metabolite that participates in the reaction
	 * @param stoichiometry coefficient of the metabolite in the reaction
	 */
	public ReactionComponent(Metabolite metabolite, double stoichiometry) {
		this.metabolite = metabolite;
		this.stoichiometry = stoichiometry;
	}

	@Override
	public String toString() {
		String JsonReactionComponent="{";
		JsonReactionComponent+="\"metaboliteId\":"+"\""+metabolite.getId()+"\", ";
		JsonReactionComponent+="\"metaboliteName\":"+"\""+metabolite.getName()+"\", ";
		JsonReactionComponent+="\"stoichiometry\":"+"\""+stoichiometry+"\" ";
		JsonReactionComponent+="}";
		return JsonReactionComponent;
	}
	
	
}