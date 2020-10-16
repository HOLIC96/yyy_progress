package RDF_API;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;



public class query extends Object {

    static final String inputFileName = "vc-db-1.rdf";

    public static void main (String args[]) {
        // 创建模型
        Model model = ModelFactory.createDefaultModel();

        // 导入数据
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // 读取数据至模型
        model.read( in, "" );

        // select all the resources with a VCARD.FN property
        ResIterator iter = model.listResourcesWithProperty(VCARD.FN);
        if (iter.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (iter.hasNext()) {
                System.out.println("  " + iter.nextResource()
                        .getRequiredProperty(VCARD.FN)
                        .getString() );
            }
        }
        else {
            System.out.println("No vcards were found in the database");
        }
    }
}