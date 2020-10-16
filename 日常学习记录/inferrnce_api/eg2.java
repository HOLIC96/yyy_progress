package inferrnce_api;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.PrintUtil;

import java.util.Iterator;

public class eg2 {
    public static void main(String args[]){
       OntModel m = ModelFactory.createOntologyModel();
       OntClass c0 = m.createClass("c0");
       OntClass c1 = m.createClass("c1");
       OntClass c2 = m.createClass("c2");
       RDFList cs = m.createList(new RDFNode[] {c0,c1,c2});

       cs = cs.cons(m.createClass("c3"));
       cs = cs.cons(m.createClass("c4"));
       cs = cs.cons(m.createClass("c5"));

       System.out.println("List has " + cs.size() + " members:");
       for (Iterator<RDFNode> i = cs.iterator();i.hasNext();){
           System.out.println(i.next());
       }

    }
}
