package com.uniandes.metapenta.io.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metapenta.model.MetabolicNetwork;
import metapenta.model.Metabolite;
import metapenta.model.Reaction;
import metapenta.model.ReactionComponent;

@Getter
@Setter
@NoArgsConstructor
public class MetabolicNetworkDTO {

    private List<ReactionDTO> reactions = new ArrayList<>();
    private List<MetaboliteDTO> metabolites = new ArrayList<>();
    private Map<String, MetaboliteDTO> metaboliteMap = new HashMap<>();

    public MetabolicNetworkDTO(MetabolicNetwork metabolicNetwork){
        loadMetabolites(metabolicNetwork);
        loadReactions(metabolicNetwork);
    }

    private void loadMetabolites(MetabolicNetwork mn) {
        for (Metabolite metabolite : mn.getMetabolitesAsList()) {
            MetaboliteDTO mdto = new MetaboliteDTO(metabolite);
            this.metabolites.add(mdto);
            this.metaboliteMap.put(metabolite.getId(), mdto);
        }
    }

    private void loadReactions(MetabolicNetwork mn){
        List<Reaction> reactions = mn.getReactionsAsList();
        for(Reaction r:reactions){
            ReactionDTO rd = new ReactionDTO(r);
            this.reactions.add(rd);
            //Load all edges from here. search the metabolitedto object and add edge out to this reaction name
            // Load edges from reactants
            List<ReactionComponent> reactants = r.getReactants();
            for (ReactionComponent rc : reactants) {
                MetaboliteDTO metaboliteDTO = metaboliteMap.get(rc.getMetaboliteId());
                if (metaboliteDTO != null) {
                    metaboliteDTO.addEdgeOut(r.getId());
                }
            }


            List<ReactionComponent> products =  r.getProducts();
            for(ReactionComponent rc:products){
                rd.addEdgeOut(rc.getMetaboliteId());
            }

        }
    }

    
}
