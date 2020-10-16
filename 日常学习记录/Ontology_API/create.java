package Ontology_API;

import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.vocabulary.VCARD;

public class create {
    OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
    OntModelSpec s = new OntModelSpec((OntModelSpec.OWL_MEM));
    OntModel n = ModelFactory.createOntologyModel(s);
    OntDocumentManager dm = n.getDocumentManager();
        }


