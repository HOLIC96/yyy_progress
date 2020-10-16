package inferrnce_api;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

public class example {
    public static void main(String[] args) {
        String NS = "urn:x-hp-jena:eg/";

        Model rdfsExample = ModelFactory.createDefaultModel();
        Property p = rdfsExample.createProperty(NS, "p");
        Property q = rdfsExample.createProperty(NS, "q");
        rdfsExample.add(p, RDFS.subPropertyOf, q);
        rdfsExample.createResource(NS + "a").addProperty(p, "foo");

        InfModel inf = ModelFactory.createRDFSModel(rdfsExample);
        Resource a = inf.getResource(NS+ "a");
        System.out.println("statement: "+ a.getProperty(q));
    }
}
