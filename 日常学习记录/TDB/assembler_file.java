package TDB;


import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class assembler_file {
    public static void main(String args[]) {
        OntModel m = ModelFactory.createOntologyModel();
        String list[] = new String[3];
        list[0] = "one";
        list[1] = "two";
        list[2] = "three";
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
    }
}
