package inferrnce_api;

import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;

import java.io.InputStream;
import java.util.Iterator;

public class readOWL {
    static final String inputFileName  = "E:/研究生课程/高级软件工程/IDEA_CODE/src/inferrnce_api/KG.owl";

    public static void main (String args[]) {
        // create an empty model

        OntModel model = ModelFactory.createOntologyModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        // write it to standard out
        model.write(System.out);
    }
}
