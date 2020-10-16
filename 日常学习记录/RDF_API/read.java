package RDF_API;
//rdf读写
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.*;


public class read {

    static final String inputFileName  = "E:\\研究生课程\\高级软件工程\\IDEA_CODE\\src\\RDF_API\\vc-db-2.rdf";

    public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

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