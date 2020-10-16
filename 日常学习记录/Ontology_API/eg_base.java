package Ontology_API;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import java.util.Iterator;

public class eg_base {
    public static void main(String args[]){
        String SOURCE = "E:\\研究生课程\\高级软件工程\\IDEA_CODE\\src\\2006-09-21.rdf";
        String NS = SOURCE + "#";
        OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        base.read(SOURCE);

        OntModel inf =ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF,base);

        OntClass paper = base.getOntClass(NS+ "paper");
        Individual p1 = base.createIndividual(NS + "paper1",paper);

        for (Iterator<Resource> i = p1.listRDFTypes(true); i.hasNext();){
            System.out.println((p1.getURI() + "is asserted in class" + i.next()));
        }

        p1 = inf.getIndividual(NS + "paper1");
        for (Iterator<Resource> i = p1.listRDFTypes(true); i.hasNext();){
            System.out.println(p1.getURI() + "is inferred to be in class" + i.next());
        }
    }
}
